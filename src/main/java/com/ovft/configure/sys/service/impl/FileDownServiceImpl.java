package com.ovft.configure.sys.service.impl;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Admin;
import com.ovft.configure.sys.bean.EduCourse;
import com.ovft.configure.sys.dao.AdminMapper;
import com.ovft.configure.sys.dao.TeacherMapper;
import com.ovft.configure.sys.service.FileDownService;
import com.ovft.configure.sys.vo.PageVo;
import org.apache.poi.hssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * @ClassName FileDownServiceImpl
 * @Author zqx
 * @Version 1.0
 **/
@Service
public class FileDownServiceImpl implements FileDownService {

    private static final Logger logger = LoggerFactory.getLogger(FileDownServiceImpl.class);

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
        sheet.setColumnWidth(2, 12*256);
        sheet.setColumnWidth(3, 17*256);
        sheet.setColumnWidth(4, 17*256);
        sheet.setColumnWidth(5, 17*256);

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

    @Override
    public WebResult courseListDowm(PageVo pageVo) {
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

            Admin teacher = adminMapper.selectById(Integer.valueOf(course.getCourseTeacher()));
            row.createCell(1).setCellValue(teacher.getName());
            row.createCell(2).setCellValue(course.getCourseName());
            row.createCell(3).setCellValue(course.getPlaceClass());

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

        String filePath = pageVo.getFilePath() + "\\" + fileName;
        filePath="C:\\Users\\Administrator\\Desktop\\" + fileName;//文件路径

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filePath);
            workbook.write(out);//保存Excel文件
            return new WebResult("200", "下载成功,已保存在桌面!", "");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("课程列表下载失败: " + e.getMessage());
            return new WebResult("400", "下载失败", "");
        }finally {
            try {
                out.close();//关闭文件流
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;

    }
}
