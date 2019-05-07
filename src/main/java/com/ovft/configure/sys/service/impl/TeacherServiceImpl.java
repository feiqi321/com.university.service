package com.ovft.configure.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ovft.configure.constant.ConstantClassField;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.*;
import com.ovft.configure.sys.dao.*;
import com.ovft.configure.sys.service.TeacherService;
import com.ovft.configure.sys.service.UserService;
import com.ovft.configure.sys.utils.MD5Utils;
import com.ovft.configure.sys.utils.SecurityUtils;
import com.ovft.configure.sys.vo.EduCourseVo;
import com.ovft.configure.sys.vo.PageVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
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
    @Resource
    public SchoolMapper schoolMapper;
    @Resource
    public UserMapper userMapper;
    @Resource
    public UserService userService;

    /**
     * 请假申请列表
     *
     * @param pageVo
     * @return
     */
    @Override
    public WebResult vacateChackList(PageVo pageVo) {
        if (pageVo.getPageSize() == 0) {
            List<Map<String, Object>> maps = teacherMapper.seleceVacateList(pageVo.getSchoolId());
            return new WebResult("200", "查询成功", maps);
        }
        PageHelper.startPage(pageVo.getPageNum(), pageVo.getPageSize());
        List<Map<String, Object>> maps = teacherMapper.seleceVacateList(pageVo.getSchoolId());
        PageInfo pageInfo = new PageInfo<>(maps);
        return new WebResult("200", "查询成功", pageInfo);
    }

    /**
     * 请假审批
     *
     * @param vacateId
     * @param isCheck
     * @return
     */
    @Override
    public WebResult vacateApprover(Integer vacateId, Integer isCheck) {
        if (isCheck == null || vacateId == null) {
            return new WebResult("400", "审批出错", "");
        }
        vacateMapper.updateCheck(vacateId, isCheck);
        return new WebResult("200", "审批成功", "");
    }

    /**
     * 课程相关信息验证
     *
     * @param courseVo
     * @param course
     * @return
     */
    public WebResult security(EduCourseVo courseVo, EduCourse course) {
        List<EduClass> classList = courseVo.getClassList();
        if (classList == null || classList.size() == 0) {
            return new WebResult("400", "请选择上课时间", "");
        }

        if (StringUtils.isBlank(courseVo.getCourseName())) {
            return new WebResult("400", "课程名称不能为空", "");
        }
        course.setCourseName(courseVo.getCourseName());

        if (courseVo.getCoursePrice() == null || courseVo.getCoursePrice().compareTo(BigDecimal.ZERO) < 0) {
            return new WebResult("400", "课程价格不正确", "");
        }
        course.setCoursePrice(courseVo.getCoursePrice());

        if (courseVo.getCourseTeacher() == null) {
            return new WebResult("400", "请添加课程教师", "");
        }
        course.setCourseTeacher(courseVo.getCourseTeacher());

        if (StringUtils.isBlank(courseVo.getPlaceClass())) {
            return new WebResult("400", "请添加上课地点", "");
        }
        course.setPlaceClass(courseVo.getPlaceClass());

        Date startDate = courseVo.getStartDate();
        Date endDate = courseVo.getEndDate();
        if (startDate == null || endDate == null) {
            return new WebResult("400", "请添加课程日期", "");
        }
        if (startDate.after(endDate)) {
            return new WebResult("400", "结束日期不能早于开课日期", "");
        }
        course.setStartDate(startDate);
        course.setEndDate(endDate);

        if (courseVo.getPeopleNumber() == null || courseVo.getPeopleNumber().compareTo(0) <= 0) {
            return new WebResult("400", "请添加课程人数", "");
        }
        course.setPeopleNumber(courseVo.getPeopleNumber());

        if (courseVo.getSchoolId() == null) {
            return new WebResult("400", "请选择学校", "");
        }
        course.setSchoolId(courseVo.getSchoolId());

        //判断“HH:mm:ss”
        //Pattern p = Pattern.compile("((((0?[0-9])|([1][0-9])|([2][0-4]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        //判断“HH:mm”
        Pattern p = Pattern.compile("([0-1]?[0-9]|2[0-3]):([0-5][0-9])");
        for (EduClass eduClass : classList) {
            String startTime = eduClass.getStartTime();
            String endTime = eduClass.getEndTime();
            if (StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime)) {
                return new WebResult("400", "请添加课程时间", "");
            }
            if (!p.matcher(startTime).matches() || !p.matcher(endTime).matches()) {
                return new WebResult("400", "请添加课程时间", "");
            }
            if (StringUtils.isBlank(eduClass.getWeek())) {
                return new WebResult("400", "请选择周几上课", "");
            }
        }
        return null;
    }

    /**
     * 进入添加课程页面
     *
     * @param schoolId
     * @return
     */
    @Override
    public WebResult intoCourse(Integer schoolId) {
        List<Map<String, Object>> teacherList = adminMapper.selectBySchool(ConstantClassField.ROLE_TEACHER_STATUS, schoolId);
        return new WebResult("200", "查询成功", teacherList);
    }

    /**
     * 添加/修改  课程
     *
     * @param courseVo
     * @return
     */
    @Transactional
    @Override
    public WebResult createCourse(EduCourseVo courseVo) {
        EduCourse course = new EduCourse();
        WebResult security = security(courseVo, course);
        if (security != null) {
            return security;
        }
        Integer courseId = courseVo.getCourseId();
        if (courseId != null) {
            course.setCourseId(courseId);
            teacherMapper.updateCourseByCourseId(course);
            //先删除原有的详细信息
            teacherMapper.deleteClassByCourseId(course.getCourseId());
        } else {
            teacherMapper.insertCourse(course);
        }
        List<EduClass> classList = courseVo.getClassList();
        for (EduClass eduClass : classList) {
            eduClass.setCourseIds(courseId);
            classMapper.insert(eduClass);
        }
        return new WebResult("200", "课程添加成功", "");
    }

    /**
     * 课程列表
     *
     * @param pageVo
     * @return
     */
    @Override
    public WebResult courseList(PageVo pageVo) {
        if (pageVo.getPageSize() == 0) {
            List<EduCourse> courseList = teacherMapper.selectCourseListBySchoolId(pageVo.getSchoolId(), pageVo.getSearch());
            return new WebResult("200", "查询成功", courseList);
        }
        PageHelper.startPage(pageVo.getPageNum(), pageVo.getPageSize(), "course_id");
        List<EduCourse> courseList = teacherMapper.selectCourseListBySchoolId(pageVo.getSchoolId(), pageVo.getSearch());
        PageInfo pageInfo = new PageInfo<>(courseList);

        return new WebResult("200", "查询成功", pageInfo);
    }

    /**
     * 进入修改课程页面
     *
     * @param courseId
     * @return
     */
    @Override
    public WebResult findCourse(Integer courseId) {
        EduCourseVo courseVo = teacherMapper.selectByCourseId(courseId);
        if (courseVo == null) {
            return new WebResult("400", "请选择课程", "");
        }
        List<EduClass> classList = teacherMapper.selectClassByCourseId(courseVo.getCourseId());
        courseVo.setClassList(classList);
        School school = schoolMapper.selectById(Integer.valueOf(courseVo.getSchoolId()));
        courseVo.setSchoolName(school == null ? "" : school.getSchoolName());
        Admin admin = adminMapper.selectById(Integer.valueOf(courseVo.getCourseTeacher()));
        courseVo.setTeacherName(admin == null ? "" : admin.getName());

        return new WebResult("200", "查询成功", courseVo);
    }

    /**
     * 修改课程
     *
     * @param courseVo
     * @return
     */
    @Transactional
    @Override
    public WebResult updateCourse(EduCourseVo courseVo) {
        EduCourse course = new EduCourse();
        WebResult security = security(courseVo, course);

        if (security != null) {
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
        return new WebResult("200", "课程修改成功", "");
    }

    /**
     * 删除课程
     *
     * @param courseId
     * @return
     */
    @Override
    public WebResult deleteCourse(Integer courseId) {
        teacherMapper.deleteClassByCourseId(courseId);
        teacherMapper.deleteCourseById(courseId);
        return new WebResult("200", "删除成功", "");
    }

    /**
     * 学员列表
     *
     * @return
     */
    @Override
    public WebResult userList(PageVo pageVo) {

              if (pageVo.getPageSize() == 0) {
                  List<User> users = teacherMapper.selectUserList(pageVo.getSchoolId(), null);
                  return new WebResult("200", "查询成功", users);
              }
        if (pageVo.getCheckin()==null) {
              PageHelper.startPage(pageVo.getPageNum(), pageVo.getPageSize());
              List<User> users = teacherMapper.selectUserList(pageVo.getSchoolId(), null);

              PageInfo pageInfo = new PageInfo<>(users);
              return new WebResult("200", "查询成功", pageInfo);
          }else{
                    User user=new User();
                    user.setSchoolId(pageVo.getSchoolId());
                    user.setCheckin(pageVo.getCheckin());
               if (user.getSchoolId()==null)  {
                   List<User> user1 = userMapper.findUserByCheckin(user);
                   PageInfo pageInfo = new PageInfo<>(user1);
                   return new WebResult("200", "查询成功", pageInfo);
               }
              List<User> users2 = userMapper.findUserByCheckinAndSchoolId(user);
            PageInfo pageInfo = new PageInfo<>(users2);
            return new WebResult("200", "查询成功", pageInfo);

          }

    }
    /**
     * 学员注销申请列表
     * @return
     */
    @Override
    public WebResult userWithdrawVoList(PageVo pageVo) {
        if(pageVo.getPageSize() == 0) {
            List<User> users = teacherMapper.selectWithdrawList(pageVo.getSchoolId(), null);
            return new WebResult("200","查询成功", users);
        }
        PageHelper.startPage(pageVo.getPageNum(), pageVo.getPageSize());
        List<User> users = teacherMapper.selectWithdrawList(pageVo.getSchoolId(), null);
        PageInfo pageInfo = new PageInfo<>(users);
        return new WebResult("200","查询成功", pageInfo);
    }

    @Transactional
    @Override
    public WebResult savaUserInfo(User user) {
        //手机号码格式验证
        if (!SecurityUtils.securityPhone(user.getPhone())) {
            return new WebResult("400", "请输入正确的手机号", "");
        }
        if (!SecurityUtils.securityPhone(user.getEmergencyPhone1())) {
            return new WebResult("400", "紧急联系人1电话不能为空", "");
        }
        if (StringUtils.isBlank(user.getEmergencyContact1())) {
            return new WebResult("400", "请输入紧急联系人1", "");
        }
        if (StringUtils.isBlank(user.getEmergencyRelation1())) {
            return new WebResult("400", "请输入紧急联系人关系1", "");
        }
        //紧急联系人二手机号验证
        if (user.getEmergencyPhone2() != null) {
            if (!SecurityUtils.securityPhone(user.getEmergencyPhone2())) {
                return new WebResult("400", "紧急联系人2电话不能为空", "");
            }
        }
        if (StringUtils.isBlank(user.getUserName())) {
            return new WebResult("400", "用户名不能为空");
        }
        if (StringUtils.isBlank(user.getIdentityCard())) {
            return new WebResult("400", "身份证号码不能为空");
        }
        if (user.getSex() == null) {
            return new WebResult("400", "性别不能为空");
        }
        user.setCheckin(1);
        //验证手机号是否被注册
        User finduserbyphone = userMapper.findUserByPhone(user);
        if (finduserbyphone == null) {
            //初始密码为000000
            String password = "000000";
            user.setPassword(MD5Utils.md5Password(password));
            userMapper.addUser(user);
            userMapper.saveInfoItems(user);
            return new WebResult("200", "保存成功", "");
        } else {
            user.setUserId(finduserbyphone.getUserId());
            User findUser = userMapper.queryByItemsId(user.getUserId());
            Integer schoolId = findUser.getSchoolId();
            Integer schoolId1 = user.getSchoolId();
            if (findUser.getSchoolId() != null && user.getSchoolId().compareTo(findUser.getSchoolId())!=0) {
                return new WebResult("400", "该用户已是其他学校学员", "");
            }
            if (findUser.getSchoolId() != null && user.getSchoolId().compareTo(findUser.getSchoolId())==0) {
                userMapper.updateInfoItems(user);
                return new WebResult("200", "修改成功", "");
            }
            userMapper.saveInfoItems(user);
            return new WebResult("200", "保存成功", "");
        }
    }
    /**
     * 学员审核状态修改
     * @return
     */
    @Transactional
    @Override
    public WebResult updateCheckIn(Integer userId,Integer checkin) {
        teacherMapper.updateCheckIn(userId,checkin);
        return new WebResult("200","操作成功","");
    }
    /**
     * 学员注销状态修改
     * @return
     */
    @Transactional
    @Override
    public WebResult updateWithdrawCheckIn(Integer wid,Integer checkin) {
        teacherMapper.updateWithdrawCheckIn(wid,checkin);
        return new WebResult("200","操作成功","");
    }

}
