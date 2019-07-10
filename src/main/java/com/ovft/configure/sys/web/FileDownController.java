package com.ovft.configure.sys.web;

import com.ovft.configure.constant.ConstantClassField;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Admin;
import com.ovft.configure.sys.bean.EduCourse;
import com.ovft.configure.sys.dao.AdminMapper;
import com.ovft.configure.sys.dao.TeacherMapper;
import com.ovft.configure.sys.service.FileDownService;
import com.ovft.configure.sys.utils.RedisUtil;
import com.ovft.configure.sys.vo.AdminVo;
import com.ovft.configure.sys.vo.PageVo;
import org.apache.poi.hssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassName FileDownController  文件下载
 * @Author zqx
 * @Version 1.0
 **/

@RestController
@RequestMapping("/server/fileDown")
public class FileDownController {
    private static final Logger logger = LoggerFactory.getLogger(FileDownController.class);

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private FileDownService fileDownService;
    @Resource
    public TeacherMapper teacherMapper;
    @Resource
    public AdminMapper adminMapper;

    /***
     * 创建表头
     * @param workbook
     * @param sheet
     */
    private void createTitle(HSSFWorkbook workbook, HSSFSheet sheet, String cellValue){
        String[] valueList = cellValue.split(",");

        HSSFRow row = sheet.createRow(0);
        //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
        for (int i = 0, length = valueList.length; i < length; i++) {
            sheet.setColumnWidth(i, 17*256);
        }

        //设置为居中加粗
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();

        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setFont(font);

        HSSFCell cell;
        for (int i = 0, length = valueList.length; i < length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(valueList[i]);
            cell.setCellStyle(style);
        }

    }

    /**
     * 课程列表下载
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/courseListDowm")
    public ResponseEntity<byte[]> courseListDowm(HttpServletRequest request, @RequestBody PageVo pageVo) {
        String token = request.getHeader("token");
        Object o = redisUtil.get(token);
        if(o != null) {
            Integer id = (Integer) o;
            // 如果是pc端登录，更新token缓存失效时间
            redisUtil.expire(token, ConstantClassField.PC_CACHE_EXPIRATION_TIME);
            Admin hget =(Admin) redisUtil.hget(ConstantClassField.ADMIN_INFO, id.toString());
            if(hget.getRole() != 0) {
                pageVo.setSchoolId(hget.getSchoolId());
            }

            HSSFWorkbook workbook = new HSSFWorkbook();
            //创建工作表(Sheet)
            HSSFSheet sheet = workbook.createSheet("课程列表");

            String cellValue = "课程ID,教师名称,课程名,上课地址,开课时间,结课时间,课程价格,状态,课程人数";
            createTitle(workbook, sheet, cellValue);
            List<EduCourse> courseList = teacherMapper.selectCourseListBySchoolId(pageVo);

            //设置日期格式
            HSSFCellStyle style=workbook.createCellStyle();
            style.setDataFormat(HSSFDataFormat.getBuiltinFormat("yyyy/MM/dd"));
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

            //新增数据行，并且设置单元格数据
            int rowNum = 1;
            for (EduCourse course:courseList) {
                HSSFRow row = sheet.createRow(rowNum);
                row.createCell(0).setCellValue(course.getCourseId());

                List<AdminVo> adminVos = adminMapper.selectByAdminAndSchool(Integer.valueOf(course.getCourseTeacher()), pageVo.getSchoolId(), null);
                if(adminVos.size() != 0) {
                    AdminVo teacher = adminVos.get(0);
                    row.createCell(1).setCellValue(teacher.getName());
                    row.createCell(2).setCellValue(course.getCourseName());
                    row.createCell(3).setCellValue(course.getPlaceClass());
                }

                HSSFCell cell = null;
                if(course.getStartDate() != null) {
                    row.createCell(4).setCellValue(dateFormat.format(course.getStartDate()));
                }
                if(course.getEndDate() != null) {
                    row.createCell(5).setCellValue(dateFormat.format(course.getEndDate()));
                }

                if(course.getCoursePrice() != null){
                    cell=row.createCell(6);
                    cell.setCellValue(course.getCoursePrice().doubleValue());
                    style=workbook.createCellStyle();
                    style.setDataFormat(HSSFDataFormat.getBuiltinFormat("￥#,##"));
                }

                Integer isenable = course.getIsenable();
                String enable = isenable.equals(1)? "启用":isenable.equals(0)?"未启用":"下架";
                row.createCell(7).setCellValue(enable);
                if(course.getPeopleNumber() != null) {
                    row.createCell(8).setCellValue(course.getPeopleNumber());
                }
                rowNum++;
            }

            //拼装blobName
            String fileName = "课程数据统计表.xlsx";

            dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String dateTime = dateFormat.format(new Date());
            fileName =  dateTime +  "_" + fileName;

//            String filePath = pageVo.getFilePath() + "\\" + fileName;
//            filePath="C:\\Users\\Administrator\\Desktop\\" + fileName;//文件路径

            HttpHeaders headers = null;
            ByteArrayOutputStream baos = null;
            try {
                //生成Excel，出于篇幅考虑，这里省略掉，小伙伴可以直接在源码中查看
                headers = new HttpHeaders();
                headers.setContentDispositionFormData("attachment", new String(fileName.getBytes("UTF-8"), "iso-8859-1"));
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                baos = new ByteArrayOutputStream();
                workbook.write(baos);
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("课程列表下载失败: " + e.getMessage());
            }
            return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.CREATED);
        }
        return null;
    }

    /**
     * 课程列表上传
     * @param file
     * @return
     */
    @PostMapping(value = "/courseListImport")
    public WebResult courseListImport(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        String token = request.getHeader("token");
        Object o = redisUtil.get(token);
        Integer schoolId = null;
        if(o != null) {
            Integer id = (Integer) o;
            // 如果是pc端登录，更新token缓存失效时间
            redisUtil.expire(token, ConstantClassField.PC_CACHE_EXPIRATION_TIME);
            Admin hget =(Admin) redisUtil.hget(ConstantClassField.ADMIN_INFO, id.toString());
            if(hget.getRole() != 0) {
                schoolId = hget.getSchoolId();
            }
            return fileDownService.courseListImport(file, schoolId);
        }else {
            return new WebResult("50012", "请重新登录", "");
        }
    }

    /**
     * 模板下载
     * @param
     * @author kaima2
     */
    @RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
    @ResponseBody
    public void downloadExcel(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String filePath = getClass().getResource("/conf/课程模板.xlsx").getPath();
        InputStream is= this.getClass().getResourceAsStream("/conf/课程模板.xlsx");
        byte[] fileData = input2byte(is);
        downloadFile(response, request, "课程模板.xlsx", fileData);
    }

    /**
     * inputstream转Byte[]
     * @param inStream
     * @return
     * @throws IOException
     */
    private  byte[] input2byte(InputStream inStream)
            throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = inStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        byte[] in2b = swapStream.toByteArray();
        return in2b;
    }
    /**
     * 下载
     * @param response
     * @param request
     * @param filename
     * @param fileData
     * @return
     */
    private boolean downloadFile(HttpServletResponse response,
                                 HttpServletRequest request, String filename, byte[] fileData) {
        OutputStream myout = null;
        // 检查时候获取到数据
        if (fileData == null) {
            return false;
        }
        try {
            if(request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
                filename = new String(filename.getBytes("GBK"),"iso-8859-1");
            }else{
                filename = URLEncoder.encode(filename, "utf-8");
            }
            response.setContentType("multipart/form-data");
            /*response.setContentType("multipart/form-data;charset=utf-8");*/
            response.setHeader("content-disposition","attachment;filename="+filename);
            // 写明要下载的文件的大小
            response.setContentLength(fileData.length);
            // 从response对象中得到输出流,准备下载
            myout = response.getOutputStream();
            myout.write(fileData);
            myout.flush();
        } catch (Exception e) {
        } finally {
            if (myout != null) {
                try {
                    myout.close();
                } catch (Exception e) {
                }
            }
        }
        return true;
    }

}
