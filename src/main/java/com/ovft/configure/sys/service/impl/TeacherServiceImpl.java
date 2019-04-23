package com.ovft.configure.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Admin;
import com.ovft.configure.sys.bean.EduClass;
import com.ovft.configure.sys.bean.EduCourse;
import com.ovft.configure.sys.dao.AdminMapper;
import com.ovft.configure.sys.dao.EduClassMapper;
import com.ovft.configure.sys.dao.TeacherMapper;
import com.ovft.configure.sys.dao.VacateMapper;
import com.ovft.configure.sys.service.TeacherService;
import com.ovft.configure.sys.vo.EduCourseVo;
import com.ovft.configure.sys.vo.PageVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @ClassName TeacherServiceImpl
 * @Author zqx
 * @Date 2019/4/11 10:15
 * @Version 1.0
 **/
@Service
public class TeacherServiceImpl implements TeacherService {

    @Resource
    public TeacherMapper teacherMapper;
    @Resource
    public VacateMapper vacateMapper;
    @Resource
    public EduClassMapper classMapper;
    @Resource
    public AdminMapper adminMapper;

    /**
     * 请假申请列表
     * @param adminId
     * @return
     */
    @Override
    public WebResult vacateChackList(Integer adminId) {
        if(adminId == null) {
            return new WebResult("400", "请登录","");
        }
        List<Map<String, Object>> maps = teacherMapper.seleceVacateByTeacherId(adminId);

        return new WebResult("200", "请求成功", maps);
    }

    /**
     * 请假审批
     * @param vacateId
     * @param isCheck
     * @return
     */
    @Override
    public WebResult vacateApprover(Integer vacateId, Integer isCheck) {
        if(isCheck == null || vacateId == null) {
            return new WebResult("400","审批出错","");
        }
        vacateMapper.updateCheck(vacateId, isCheck);
        return new WebResult("200","审批成功","");
    }

    /**
     * 课程相关信息验证
     * @param courseVo
     * @param course
     * @return
     */
    public WebResult security(EduCourseVo courseVo, EduCourse course) {
        List<EduClass> classList = courseVo.getClassList();
        if(classList == null || classList.size() == 0){
            return new WebResult("400","请选择上课时间","");
        }

        if(StringUtils.isBlank(courseVo.getCourseName())) {
            return new WebResult("400","课程名称不能为空","");
        }
        course.setCourseName(courseVo.getCourseName());

        if(courseVo.getCoursePrice() == null || courseVo.getCoursePrice().compareTo(BigDecimal.ZERO)< 0) {
            return new WebResult("400","课程价格不正确","");
        }
        course.setCoursePrice(courseVo.getCoursePrice());

        if(courseVo.getCourseTeacher() == null) {
            return new WebResult("400","请添加课程教师","");
        }
        course.setCourseTeacher(courseVo.getCourseTeacher());

        if(StringUtils.isBlank(courseVo.getPlaceClass())) {
            return new WebResult("400","请添加上课地点","");
        }
        course.setPlaceClass(courseVo.getPlaceClass());

        Date startDate = courseVo.getStartDate();
        Date endDate = courseVo.getEndDate();
        if(startDate == null || endDate == null){
            return new WebResult("400","请添加课程日期","");
        }
        if(startDate.after(endDate)) {
            return new WebResult("400","结束日期不能早于开课日期","");
        }
        course.setStartDate(startDate);
        course.setEndDate(endDate);

        if(courseVo.getPeopleNumber() == null || courseVo.getPeopleNumber().compareTo(0)<=0) {
            return new WebResult("400","请添加课程人数","");
        }
        course.setPeopleNumber(courseVo.getPeopleNumber());

        if(courseVo.getSchoolId() == null) {
            return new WebResult("400","请选择学校","");
        }
        course.setSchoolId(courseVo.getSchoolId());

        //判断“HH:mm:ss”
        //Pattern p = Pattern.compile("((((0?[0-9])|([1][0-9])|([2][0-4]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        //判断“HH:mm”
        Pattern p = Pattern.compile("([0-1]?[0-9]|2[0-3]):([0-5][0-9])");
        for (EduClass eduClass : classList) {
            String startTime = eduClass.getStartTime();
            String endTime = eduClass.getEndTime();
            if(!p.matcher(startTime).matches() || !p.matcher(endTime).matches()) {
                return new WebResult("400","请添加课程时间","");
            }
            if(StringUtils.isBlank(eduClass.getWeek())) {
                return new WebResult("400","请选择周几上课","");
            }
        }
        return null;
    }

    /**
     * 进入添加课程页面
     * @param schoolId
     * @return
     */
    @Override
    public WebResult intoCourse(Integer schoolId) {
        List<Admin> teacherList = adminMapper.selectTeacherBySchool(schoolId);
        return new WebResult("200","查询成功", teacherList);
    }

    /**
     * 添加课程
     * @param courseVo
     * @return
     */
    @Transactional
    @Override
    public WebResult createCourse(EduCourseVo courseVo) {
        EduCourse course = new EduCourse();
        WebResult security = security(courseVo, course);

        if(security != null) {
            return security;
        }

        teacherMapper.insertCourse(course);
        Integer courseId = course.getCourseId();

        List<EduClass> classList = courseVo.getClassList();
        for (EduClass eduClass : classList) {
            eduClass.setCourseIds(courseId);
            classMapper.insert(eduClass);
        }
        return new WebResult("200","课程添加成功","");
    }

    /**
     * 课程列表
     * @param pageVo
     * @return
     */
    @Override
    public WebResult courseList(PageVo pageVo) {
        PageHelper.startPage(pageVo.getPageNum(), pageVo.getPageSize(), "course_id");
        List<EduCourse> courseList = teacherMapper.selectCourseListBySchoolId(pageVo.getId(), pageVo.getSearch());
        PageInfo pageInfo = new PageInfo<>(courseList);

        return new WebResult("200","查询成功", pageInfo);
    }

    /**
     * 进入修改课程页面
     * @param courseId
     * @return
     */
    @Override
    public WebResult findCourse(Integer courseId) {
        EduCourseVo courseVo = teacherMapper.selectByCourseId(courseId);
        if(courseVo == null) {
            return new WebResult("400", "请选择课程","");
        }
        List<EduClass> classList = teacherMapper.selectClassByCourseId(courseVo.getCourseId());
        courseVo.setClassList(classList);

        List<Admin> teacherList = adminMapper.selectTeacherBySchool(Integer.valueOf(courseVo.getSchoolId()));

        HashMap<String, Object> map = new HashMap<>();
        map.put("courseVo", courseVo);
        map.put("teacherList", teacherList);

        return new WebResult("200","查询成功", map);
    }

    /**
     * 修改课程
     * @param courseVo
     * @return
     */
    @Transactional
    @Override
    public WebResult updateCourse(EduCourseVo courseVo) {
        EduCourse course = new EduCourse();
        WebResult security = security(courseVo, course);

        if(security != null) {
            return security;
        }
        course.setCourseId(courseVo.getCourseId());
        teacherMapper.updateCourseByCourseId(course);

        //先删除原有的详细信息
        teacherMapper.deleteClassByCourseId(course.getCourseId());
        //添加课程详细信息
        List<EduClass> classList = courseVo.getClassList();
        for (EduClass eduClass : classList) {
            eduClass.setCourseIds(course.getCourseId());
            classMapper.insert(eduClass);
        }
        return new WebResult("200","课程修改成功","");
    }

    /**
     * 删除课程
     * @param courseId
     * @return
     */
    @Override
    public WebResult deleteCourse(Integer courseId) {
        teacherMapper.deleteClassByCourseId(courseId);
        teacherMapper.deleteCourseById(courseId);
        return new WebResult("200","删除成功","");
    }


}
