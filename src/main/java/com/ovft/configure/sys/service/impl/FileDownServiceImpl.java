package com.ovft.configure.sys.service.impl;

import com.ovft.configure.config.MessageCenterException;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.*;
import com.ovft.configure.sys.dao.*;
import com.ovft.configure.sys.service.FileDownService;
import com.ovft.configure.sys.vo.AdminVo;
import com.ovft.configure.sys.vo.DepartmentVo;
import com.ovft.configure.sys.vo.EduCourseVo;
import com.ovft.configure.sys.vo.PageVo;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.model.BatchStatus;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;


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
    @Resource
    public SchoolMapper schoolMapper;
    @Resource
    public EduClassMapper classMapper;
    @Resource
    private FineCourseMapper fineCourseMapper;
    @Resource
    private UserClassMapper userClassMapper;
    @Resource
    private DepartmentMapper departmentMapper;


    @Transactional
    @Override
    public WebResult courseListImport(MultipartFile uploadFile, Integer schoolId) {
        String schoolName = "";
        if(schoolId != null) {
            School school = schoolMapper.selectById(schoolId);
            schoolName = school.getSchoolName();
        }
        LinkedList<EduCourseVo> courseList = new LinkedList<>();
        HSSFWorkbook workbook = null;
        try {
            //1.创建HSSFWorkbook对象
            workbook = new HSSFWorkbook(new POIFSFileSystem(uploadFile.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("文件上传失败： " + e.getMessage());
            throw new MessageCenterException(new WebResult("400", "文件上传失败: 请上传Excel表格", ""), e);
        }
        //2.获取一共有多少sheet，然后遍历
//            int numberOfSheets = workbook.getNumberOfSheets();
        // 获取第0个sheet
        HSSFSheet sheet = workbook.getSheetAt(0);
        //3.获取sheet中一共有多少行，遍历行（注意第一行是标题）
        int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
        if(physicalNumberOfRows <= 1) {
            return new WebResult("400", "文件上传失败: 请上传课程！", "");
        }
        for (int j = 1; j < physicalNumberOfRows; j++) {
            HSSFRow row = sheet.getRow(j);
            HSSFCell cell = null;
            //4.获取每一行有多少单元格，遍历单元格
            int physicalNumberOfCells = row.getPhysicalNumberOfCells();
            EduCourseVo course = new EduCourseVo();

            //0-课程ID   1-学校名称 	2-教师名称  	3-课程名	4-上课地址  	5-开课日期	    6-结课日期  	7-课程价格  	8-状态
            // 9-课程人数 	10-星期   	11-上课开始时间	    12-上课结束时间

            // 8状态   是否启用       -1-下架, 0-未启用, 1-启用
            cell = row.getCell(8);
            if(cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                return new WebResult("400", "文件上传失败: 第" + (j + 1) + "行 的课程状态不能为空", "");
            }
            String isenable = cell.getStringCellValue();
            if("启用".equals(isenable)) {
                course.setIsenable(1);
            } else if("未启用".equals(isenable)){
                course.setIsenable(0);
            } else if("下架".equals(isenable)){
                course.setIsenable(-1);
            } else {
                return new WebResult("400", "文件上传失败: 请选择 第" + (j + 1) + "行 的课程状态", "");
            }

            //  1-学校名称
            cell = row.getCell(1);
            if(schoolId == null && (cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK)) {
                return new WebResult("400", "文件上传失败: 请选择 第" + (j + 1) + "行 的学校", "");
            }
            if(schoolId != null) {
                course.setSchoolId(schoolId.toString());
            } else {
                String schoolName2 = cell.getStringCellValue();
                List<School> schoolList = schoolMapper.selectSchoolList(null, schoolName2);
                for (School school : schoolList) {
                    if(school.getSchoolName().equals(schoolName2)) {
                        course.setSchoolId(school.getSchoolId().toString());
                        break;
                    }
                }
                if(StringUtils.isBlank(course.getSchoolId())) {
                    return new WebResult("400", "文件上传失败: 第" + (j + 1) + "行 的学校不存在", "");
                }
            }

            //  0-课程ID
            cell = row.getCell(0);
            if(cell != null && cell.getCellType() != HSSFCell.CELL_TYPE_BLANK) {
                if(cell.getCellType() != HSSFCell.CELL_TYPE_NUMERIC) {
                    return new WebResult("400", "文件上传失败: 第" + (j + 1) + "行 的课程ID格式错误", "");
                }
                double courseId = cell.getNumericCellValue();
                EduCourseVo courseVo = teacherMapper.selectByCourseId(new Double(courseId).intValue());
                if(courseVo == null) {
                    return new WebResult("400", "文件上传失败: 第" + (j + 1) + "行 的课程ID不存在", "");
                }
                if(courseVo.getSchoolId().equals(course.getSchoolId())) {
                    course.setCourseId(new Double(courseId).intValue());
                } else {
                    return new WebResult("400", "文件上传失败: 第" + (j + 1) + "行 的课程ID与学校不相符", "");
                }
            }

            //  2-教师名称
            cell = row.getCell(2);
            if(cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                if(course.getIsenable().equals(1)) {
                    return new WebResult("400", "文件上传失败: 课程启用，第" + (j + 1) + "行 的教师不能为空", "");
                }
            } else {
                String teacher = row.getCell(2).getStringCellValue();
                List<AdminVo> maps = adminMapper.selectByAdminAndSchool(null, Integer.valueOf(course.getSchoolId()), 2);
                for (AdminVo admin : maps) {
                    String name = admin.getName();
                    if(name.equals(teacher)) {
                        Integer adminId = admin.getAdminId();
                        course.setCourseTeacher(adminId.toString());
                        break;
                    }
                }
                if(StringUtils.isBlank(course.getCourseTeacher())) {
                    return new WebResult("400", "文件上传失败: 第" + (j + 1) + "行 的教师不存在", "");
                }
            }

            //  3-课程名
            cell = row.getCell(3);
            if(cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                return new WebResult("400", "文件上传失败: 第" + (j + 1) + "行 的课程名不能为空", "");
            }
            String courseName = cell.getStringCellValue();
            course.setCourseName(courseName);

            //  4-上课地址
            cell = row.getCell(4);
            if(cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                if(course.getIsenable().equals(1)){
                    return new WebResult("400", "文件上传失败: 课程启用，第" + (j + 1) + "行 的上课地点不能为空", "");
                }
            } else {
                String placeClass = row.getCell(4).getStringCellValue();
                course.setPlaceClass(placeClass);
            }


            //  5-开课日期
            cell = row.getCell(5);
            if(cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                if(course.getIsenable().equals(1)){
                    return new WebResult("400", "文件上传失败: 课程启用，第" + (j + 1) + "行 的开课日期不能为空", "");
                }
            }else {
                Date startDate = row.getCell(5).getDateCellValue();
                course.setStartDate(startDate);
            }

            //  6-结课日期
            cell = row.getCell(6);
            if(cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                if(course.getIsenable().equals(1)){
                    return new WebResult("400", "文件上传失败: 课程启用，第" + (j + 1) + "行 的结课日期不能为空", "");
                }
            }else {
                Date endDate = row.getCell(6).getDateCellValue();
                course.setEndDate(endDate);
            }

            //  7-课程价格
            cell = row.getCell(7);
            if(cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                if(course.getIsenable().equals(1)){
                    return new WebResult("400", "文件上传失败: 课程启用，第" + (j + 1) + "行 的课程价格不能为空", "");
                }
            }else {
                if(cell.getCellType() != HSSFCell.CELL_TYPE_NUMERIC) {
                    return new WebResult("400", "文件上传失败: 第" + (j + 1) + "行 的课程价格格式错误", "");
                }
                double coursePrice = row.getCell(7).getNumericCellValue();
                course.setCoursePrice(new BigDecimal(coursePrice));
            }

            // 9-课程人数
            cell = row.getCell(9);
            if(cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                if(course.getIsenable().equals(1)){
                    return new WebResult("400", "文件上传失败: 课程启用，第" + (j + 1) + "行 的课程人数不能为空", "");
                }
            }else {
                if(cell.getCellType() != HSSFCell.CELL_TYPE_NUMERIC) {
                    return new WebResult("400", "文件上传失败: 第" + (j + 1) + "行 的课程人数格式错误", "");
                }
                double peopleNumber = row.getCell(9).getNumericCellValue();
                course.setPeopleNumber(new Double(peopleNumber).intValue());
            }


            //  10-星期
            cell = row.getCell(10);
            if(cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                if(course.getIsenable().equals(1)){
                    return new WebResult("400", "文件上传失败: 课程启用，第" + (j + 1) + "行 的星期不能为空", "");
                }
            }else {
                if(cell.getCellType() != HSSFCell.CELL_TYPE_NUMERIC) {
                    return new WebResult("400", "文件上传失败: 第" + (j + 1) + "行 的课程星期格式错误", "");
                }
                double week = row.getCell(10).getNumericCellValue();
                course.setWeek(new Double(week).intValue() + "" + new Double(week).intValue());
            }

            //判断“HH:mm”
            Pattern p = Pattern.compile("([0-1]?[0-9]|2[0-3]):([0-5][0-9])");
            //  11-上课开始时间
            cell = row.getCell(11);
            if(cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                if(course.getIsenable().equals(1)){
                    return new WebResult("400", "文件上传失败: 课程启用，第" + (j + 1) + "行 的上课开始时间不能为空", "");
                }
            }else {
                String startTime = row.getCell(11).getStringCellValue();
                if(!StringUtils.isBlank(startTime) && !p.matcher(startTime).matches()) {
                    return new WebResult("400", "文件上传失败: 第" + (j + 1) + "行 的上课开始时间填写错误", "");
                }
                course.setStartTime(startTime);
            }

            //  12-上课结束时间
            cell = row.getCell(12);
            if(cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                if(course.getIsenable().equals(1)){
                    return new WebResult("400", "文件上传失败: 课程启用，第" + (j + 1) + "行 的上课结束时间不能为空", "");
                }
            }else {
                String endTime = row.getCell(12).getStringCellValue();
                if(!StringUtils.isBlank(endTime) && !p.matcher(endTime).matches()) {
                    return new WebResult("400", "文件上传失败: 第" + (j + 1) + "行 的上课结束时间填写错误", "");
                }
                course.setEndTime(endTime);
            }
            //  12-上课结束时间
            cell = row.getCell(13);
            if(cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                if(course.getIsenable().equals(1)){
                    return new WebResult("400", "文件上传失败: 课程启用，第" + (j + 1) + "行 的所属院系不能为空", "");
                }
            }else {
                String department = row.getCell(13).getStringCellValue();

                course.setDepartmentName(department);
            }

            courseList.push(course);
        }

        for (EduCourseVo courseVo : courseList) {
            //检查是否有相同的课程已经启用
            PageVo pageVo = new PageVo();
            pageVo.setSchoolId(Integer.valueOf(courseVo.getSchoolId()));
            pageVo.setIsenable(courseVo.getIsenable());
            pageVo.setSearch(courseVo.getCourseName());
            List<EduCourse> eqName = teacherMapper.selectCourseListBySchoolId(pageVo);
            for (EduCourse eduCourse : eqName) {
                if(eduCourse.getCourseName().equals(courseVo.getCourseName())) {
                    if(courseVo.getCourseId() == null) {
                        throw new MessageCenterException(new WebResult("400", "文件上传失败: 已有“"+courseVo.getCourseName() + "”,请先停用！", ""), null);
                    }
                    if(!courseVo.getCourseId().equals(eduCourse.getCourseId())) {
                        throw new MessageCenterException(new WebResult("400", "文件上传失败: 已有“"+courseVo.getCourseName() + "”,请先停用！", ""), null);
                    }
                }
            }
            Integer courseId = courseVo.getCourseId();
            EduCourse course = courseVo;
            UserClass userClass=new UserClass();
            if (courseId != null) {
                //封装班级（以课程分班级）
                DepartmentVo departmentVo=new DepartmentVo();
                departmentVo.setDepartmentName(((EduCourseVo) course).getDepartmentName());
                departmentVo.setSchoolId(Integer.valueOf(course.getSchoolId()));
                List<Department> departments = departmentMapper.departmentList(departmentVo);
                course.setDid(departments.get(0).getDid());
                teacherMapper.updateCourseByCourseId(course);
                //先删除原有的详细信息
                teacherMapper.deleteClassByCourseId(course.getCourseId());

                      if (departments.isEmpty()){
                          return new WebResult("400", "文件上传失败: 在系统里该学校不存在所填写的院系", "");
                      }else {
                          userClass.setDid(departments.get(0).getDid());
                          userClass.setSpecialty(((EduCourseVo) course).getDepartmentName());
                      }

                userClass.setClassName(course.getCourseName()+"班");
                userClass.setSchoolId(Integer.parseInt(course.getSchoolId()));
                String findSchoolName = schoolMapper.findSchoolById(Integer.parseInt(course.getSchoolId()));
                userClass.setSchoolName(findSchoolName);

                userClass.setCourseId(course.getCourseId());
                userClassMapper.updateUserClass(userClass);   //修改班级
            } else {
                //封装班级（以课程分班级）
                DepartmentVo departmentVo=new DepartmentVo();
                departmentVo.setDepartmentName(((EduCourseVo) course).getDepartmentName());
                departmentVo.setSchoolId(Integer.valueOf(course.getSchoolId()));
                List<Department> departments = departmentMapper.departmentList(departmentVo);
                course.setDid(departments.get(0).getDid());
                teacherMapper.insertCourse(course);
                if (departments.isEmpty()){
                    return new WebResult("400", "文件上传失败: 在系统里该学校不存在所填写的院系", "");
                }else {
                    userClass.setDid(departments.get(0).getDid());
                    userClass.setSpecialty(((EduCourseVo) course).getDepartmentName());
                }

                userClass.setClassName(course.getCourseName()+"班");
                userClass.setSchoolId(Integer.parseInt(course.getSchoolId()));
                String schoolName3 = schoolMapper.findSchoolById(Integer.parseInt(course.getSchoolId()));
                userClass.setSchoolName(schoolName3);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
                String format = formatter.format(new Date());
                List<UserClass> userClasses = userClassMapper.findClassNoAll(userClass);
                //处理classNo
                if (userClasses.isEmpty()){     //当班级记录一条都没有事时
                    userClass.setClassNo(format.substring(0,4)+0+0+1);
                }else{
                    String classNoEnd=userClasses.get(userClasses.size()-1).getClassNo();    //获取最后一条班级记录里面的classNo
                    String s = Integer.valueOf(classNoEnd).toString();  //将classNoEnd转化成字符串
                    String substring = s.substring(4);   //截取年后面的数字
                    int num = Integer.parseInt(substring);

                    if (num<10){

                        userClass.setClassNo(format.substring(0,4)+0+0+(num+1));
                    }
                    if (num>=10&&num<100){
                        userClass.setClassNo(format.substring(0,4)+0+(num+1));
                    }
                    if (num>=100){
                        userClass.setClassNo(format.substring(0,4)+(num+1));
                    }

                }
                userClass.setCourseId(course.getCourseId());
                userClassMapper.addUserClass(userClass);   //生成新的班级
            }

            EduClass eduClass = new EduClass();
            eduClass.setCourseIds(course.getCourseId());
            eduClass.setStartTime(courseVo.getStartTime());
            eduClass.setWeek(courseVo.getWeek());
            eduClass.setEndTime(courseVo.getEndTime());
            classMapper.insert(eduClass);
        }
        return new WebResult("200", "文件上传成功", "");

    }

    //...生成上传凭证，然后准备上传
    @Value("${qiniuyun.accessKey}")
    private String accessKey;
    @Value("${qiniuyun.secretKey}")
    private String secretKey;
    @Value("${qiniuyun.bucket}")
    private String bucket;
    @Value("${qiniuyun.filePrefix}")
    private String filePrefix;
    @Override
    public void deleteFile() {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
         //文件名前缀
        String prefix = filePrefix;
        //每次迭代的长度限制，最大1000，推荐值 1000
        int limit = 1000;
        //指定目录分隔符，列出所有公共前缀（模拟列出目录效果）。缺省值为空字符串
        String delimiter = "";
        //列举空间文件列表
        BucketManager.FileListIterator fileListIterator = bucketManager.createFileListIterator(bucket, prefix, limit, delimiter);
        LinkedList<String> keyList = new LinkedList<String>();
        Map<String, String> findFile = findFile();
        while (fileListIterator.hasNext()) {
            //处理获取的file list结果
            FileInfo[] items = fileListIterator.next();
            for (FileInfo item : items) {
                System.out.println(item.key);
                if(!findFile.containsKey(item.key)) {
                    keyList.push(item.key);
                }
            }
        }
        if(keyList.size()!=0) {
            String[] arry = new String[keyList.size()];
            arry = keyList.toArray(arry);
            bankDelete(arry);
        }
    }

    //todo  待添加上传至七牛云的图片
    public Map<String, String> findFile() {
        PageVo pageVo = new PageVo();
        List<FineCourse> fineCourseFile = fineCourseMapper.selectFineCourseList(pageVo);
        Map<String, String> map = new HashMap<>();
        for (FineCourse fineCourse : fineCourseFile) {
            String video = fineCourse.getVideo();
            if(!StringUtils.isBlank(fineCourse.getVideo())) {
                map.put(video, video);
            }
            String cover = fineCourse.getCover();
            if(!StringUtils.isBlank(cover)) {
                map.put(cover, cover);
            }
        }
        return map;
    }

    public void bankDelete(String[] keyList) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            BucketManager.BatchOperations batchOperations = new BucketManager.BatchOperations();
            batchOperations.addDeleteOp(bucket, keyList);
            Response response = bucketManager.batch(batchOperations);
            BatchStatus[] batchStatusList = response.jsonToObject(BatchStatus[].class);
            for (int i = 0; i < keyList.length; i++) {
                BatchStatus status = batchStatusList[i];
                String key = keyList[i];
                System.out.print(key + "\t");
                if (status.code == 200) {
                    System.out.println("delete success");
                    logger.info("文件删除成功！" + key);
                } else {
                    System.out.println(status.data.error);
                }
            }
        } catch (QiniuException ex) {
            System.err.println(ex.response.toString());
            logger.info("文件删除失败！" + ex.getMessage());
        }
    }
}
