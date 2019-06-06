package com.ovft.configure.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ovft.configure.constant.ConstantClassField;
import com.ovft.configure.constant.OrderStatus;
import com.ovft.configure.constant.Status;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.*;
import com.ovft.configure.sys.dao.*;
import com.ovft.configure.sys.service.TeacherService;
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
    @Resource
    public SchoolMapper schoolMapper;
    @Resource
    public UserMapper userMapper;
    @Resource
    public EduCourseMapper eduCourseMapper;
    @Resource
    public EduOfflineOrderMapper eduOfflineOrderMapper;
    @Resource
    OrderMapper orderMapper;

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
     * @return
     */
    public WebResult security(EduCourse courseVo, List<EduClass> classList) {
        Date startDate = courseVo.getStartDate();
        Date endDate = courseVo.getEndDate();
        //检查是否有相同的课程已经启用
        PageVo pageVo = new PageVo();
        pageVo.setSchoolId(Integer.valueOf(courseVo.getSchoolId()));
        pageVo.setIsenable(courseVo.getIsenable());
        pageVo.setSearch(courseVo.getCourseName());
        List<EduCourse> eqName = teacherMapper.selectCourseListBySchoolId(pageVo);
        for (EduCourse eduCourse : eqName) {
            if(eduCourse.getCourseName().equals(courseVo.getCourseName())) {
                if(courseVo.getCourseId() == null) {
                    return new WebResult("400", "已有“"+courseVo.getCourseName() + "”,请先停用！", "");
                }
                if(!courseVo.getCourseId().equals(eduCourse.getCourseId())) {
                    return new WebResult("400", "已有“"+courseVo.getCourseName() + "”,请先停用！", "");
                }
            }
        }
        if (classList == null || classList.size() == 0) {
            return new WebResult("400", "请选择‘" + courseVo.getCourseName() + "’的上课时间", "");
        }
        if (courseVo.getCoursePrice() == null || courseVo.getCoursePrice().compareTo(BigDecimal.ZERO) < 0) {
            return new WebResult("400", "课程‘" + courseVo.getCourseName() + "’的价格不正确", "");
        }
        if (StringUtils.isBlank(courseVo.getPlaceClass())) {
            return new WebResult("400", "请添加‘" + courseVo.getCourseName() + "’的上课地点", "");
        }
        if (startDate == null || endDate == null) {
            return new WebResult("400", "请添加课程‘" + courseVo.getCourseName() + "’的日期", "");
        }
        if (startDate.after(endDate)) {
            return new WebResult("400", "‘" + courseVo.getCourseName() + "’的结束日期不能早于开课日期", "");
        }
        if (courseVo.getPeopleNumber() == null || courseVo.getPeopleNumber().compareTo(0) <= 0) {
            return new WebResult("400", "请添加课程‘" + courseVo.getCourseName() + "’的人数", "");
        }
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
        if (StringUtils.isBlank(courseVo.getCourseName())) {
            return new WebResult("400", "课程名称不能为空", "");
        }

        if (courseVo.getCourseTeacher() == null) {
            return new WebResult("400", "请添加课程教师", "");
        }

        if (StringUtils.isBlank(courseVo.getSchoolId())) {
            return new WebResult("400", "请选择学校", "");
        }

        List<EduClass> classList = courseVo.getClassList();
        Date startDate = courseVo.getStartDate();
        Date endDate = courseVo.getEndDate();

        //如果课程启用, 相关字段验证
        if(courseVo.getIsenable().compareTo(1) == 0) {
            WebResult security = security(courseVo, classList);
            if(security != null) {
                return security;
            }
        }
        course.setCourseName(courseVo.getCourseName());
        course.setCourseTeacher(courseVo.getCourseTeacher());
        course.setCoursePrice(courseVo.getCoursePrice());
        course.setPlaceClass(courseVo.getPlaceClass());
        course.setStartDate(startDate);
        course.setEndDate(endDate);
        course.setPeopleNumber(courseVo.getPeopleNumber());
        course.setSchoolId(courseVo.getSchoolId());
        course.setIsenable(courseVo.getIsenable());

        Integer courseId = courseVo.getCourseId();
        String msg = "课程添加成功";
        if (courseId != null) {
            course.setCourseId(courseId);
            teacherMapper.updateCourseByCourseId(course);
            //先删除原有的详细信息
            teacherMapper.deleteClassByCourseId(course.getCourseId());
            msg = "课程修改成功";
        } else {
            teacherMapper.insertCourse(course);
        }

        if(classList != null) {
            for (EduClass eduClass : classList) {
                eduClass.setCourseIds(course.getCourseId());
                classMapper.insert(eduClass);
            }
        }
        //添加成功之后，判断是否开启，

        return new WebResult("200", msg, "");
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
            List<EduCourseVo> courseList = teacherMapper.selectCourseList(pageVo);
            return new WebResult("200", "查询成功", courseList);
        }
        PageHelper.startPage(pageVo.getPageNum(), pageVo.getPageSize());
        List<EduCourseVo> courseList = teacherMapper.selectCourseList(pageVo);
        courseList.forEach(courseVo -> {
            Integer courseId = courseVo.getCourseId();
            //0.可以报名的人数
            //查询专业接受报名的人数
            int acceptNum = eduCourseMapper.queryAcceptNum(courseId);

            if (acceptNum == 0) {
                courseVo.setNowtotal(acceptNum);
            } else {
                //查询用户所对应的专业显示已经购买人数
                Map<String, Object> param = new HashMap<>();
                param.put("course_id", courseId);
                param.put("payment_status", OrderStatus.PAY);

                int olineNum = orderMapper.countPayCourseNum(param);

                //查询用户所对应的专业线下的总人数

                Integer offNum = eduOfflineOrderMapper.queryOffRecordNum(Status.PAY);

                //得到最终报名人数
                Integer payNum = olineNum + offNum;
                //可报名剩余人数
                int nowtotal = acceptNum - payNum;
                courseVo.setNowtotal(nowtotal>=0 ? nowtotal:0);
            }
        });
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
     * 删除课程
     *
     * @param courseIds
     * @return
     */
    @Override
    public WebResult deleteCourse(String[] courseIds) {
        if(courseIds == null || courseIds.length == 0) {
            return new WebResult("400", "请选择课程", "");
        }
        for (String s : courseIds) {
            Integer courseId = Integer.valueOf(s);
            teacherMapper.deleteClassByCourseId(courseId);
            teacherMapper.deleteCourseById(courseId);
        }

        return new WebResult("200", "删除成功", "");
    }

    @Override
    public void shelvesCourse() {
        Date date = new Date();
        List<EduCourse> courseList = teacherMapper.shelvesCourse(date);
        for (EduCourse course : courseList) {
            course.setIsenable(-1);
            teacherMapper.updateCourseByCourseId(course);
        }
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

              //筛选查询（按checkin条件进行查询）
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

    /**
     * 课程 启用/停用 页面
     * @param course
     * @return
     */
    @Transactional
    @Override
    public WebResult updateIsenable(EduCourseVo course) {
        String[] courseIds = course.getCourseIds();
        if(courseIds == null || courseIds.length == 0) {
            return new WebResult("400", "请选择课程", "");
        }
        PageVo pageVo = new PageVo();
        for (String s : courseIds) {
            Integer courseId = Integer.valueOf(s);
            course.setCourseId(courseId);
            if(course.getIsenable().equals(1)) {
                //检查是否有相同的课程已经启用
                EduCourse eqName = teacherMapper.selectByCourseId(courseId);
                List<EduClass> classList = teacherMapper.selectClassByCourseId(eqName.getCourseId());
                WebResult security = security(eqName, classList);
                if(security != null) {
                    return security;
                }
                pageVo.setSchoolId(Integer.valueOf(eqName.getSchoolId()));
                pageVo.setIsenable(course.getIsenable());
                pageVo.setSearch(eqName.getCourseName());
                List<EduCourse> courseList = teacherMapper.selectCourseListBySchoolId(pageVo);
                for (EduCourse eduCourse : courseList) {
                    if (eduCourse.getCourseName().equals(eqName.getCourseName())) {
                        if (!eqName.getCourseId().equals(eduCourse.getCourseId())) {
                            return new WebResult("400", "已有“" + eqName.getCourseName() + "”,请先停用！", "");
                        }
                    }
                }
            }
            teacherMapper.updateCourseByCourseId(course);
        }
        return new WebResult("200", "修改成功", "");
    }

    @Override
    public WebResult deleteVacate(Integer vacateId) {
        teacherMapper.deleteVacate(vacateId);
        return new WebResult("200", "删除成功", "");
    }

    /**
     * 添加/修改 学员
     * @param user
     * @return
     */
    @Transactional
    @Override
    public WebResult savaUserInfo(User user) {
        //手机号码格式验证
        if (!SecurityUtils.securityPhone(user.getPhone())) {
            return new WebResult("400", "请输入正确的手机号", "");
        }
        //需求修改,紧急联系人1改为选填
        /*if (!SecurityUtils.securityPhone(user.getEmergencyPhone1())) {
            return new WebResult("400", "请输入正确的紧急联系人1电话", "");
        }
        if (StringUtils.isBlank(user.getEmergencyContact1())) {
            return new WebResult("400", "请输入紧急联系人1", "");
        }
        if (StringUtils.isBlank(user.getEmergencyRelation1())) {
            return new WebResult("400", "请输入紧急联系人关系1", "");
        }*/
        //紧急联系人1手机号验证
        if (user.getEmergencyPhone1() != null) {
            if (!SecurityUtils.securityPhone(user.getEmergencyPhone1())) {
                return new WebResult("400", "请输入正确的紧急联系人1电话", "");
            }
        }
        //紧急联系人二手机号验证
        if (user.getEmergencyPhone2() != null) {
            if (!SecurityUtils.securityPhone(user.getEmergencyPhone2())) {
                return new WebResult("400", "请输入正确的紧急联系人2电话", "");
            }
        }
        if (StringUtils.isBlank(user.getUserName())) {
            return new WebResult("400", "用户名不能为空");
        }
        if (!SecurityUtils.securityIdCard(user.getIdentityCard())) {
            return new WebResult("400", "请输入正确的身份证号码", "");
        }
        if (user.getSex() == null) {
            return new WebResult("400", "性别不能为空");
        }
        user.setCheckin(1);
        //验证手机号是否被注册
        /**
         * 学员添加修改
         * 1. finduserbyphone == null && userId == null  新注册的学员
         * 2. finduserbyphone == null && userId != null  原已经注册过的学员,修改了手机号
         * 3. finduserbyphone != null && userId != null  已注册过的学员,没有修改手机号
         */
        User finduserbyphone = userMapper.findUserByPhone(user);
//        if (finduserbyphone == null) {
//            if(user.getUserId() == null) {
//                //1. finduserbyphone == null && userId == null  新注册的学员
//                //初始密码为000000
//                String password = "000000";
//                user.setPassword(MD5Utils.md5Password(password));
//                userMapper.addUser(user);
//                userMapper.saveInfoItems(user);
//                return new WebResult("200", "保存成功", "");
//            } else {
//                //2. finduserbyphone == null && userId != null  原已经注册过的学员,修改了手机号
//                userMapper.updateByUserId(user.getPhone(), user.getUserId());
//            }
//        }
        if (finduserbyphone == null) {
              //未注册的情况
            //1. finduserbyphone == null && userId == null  新注册的学员
                //初始密码为000000
                String password = "000000";
                user.setPassword(MD5Utils.md5Password(password));
                userMapper.addUser(user);
                userMapper.saveInfoItems(user);
                return new WebResult("200", "保存成功", "");
        }else {
                //2. finduserbyphone == null && userId != null  原已经注册过的学员,修改了手机号
                userMapper.updateByUserId(user.getPhone(), user.getUserId());
            }

        if(finduserbyphone != null && user.getUserId() != null && user.getUserId().compareTo(finduserbyphone.getUserId())!= 0) {
            return new WebResult("400", "该手机号已被注册", "");
        }
        user.setUserId(finduserbyphone.getUserId());
        //通过userId来寻找edu_Item
        User findUser = userMapper.queryByItemsId3(user.getUserId());
        if (findUser == null) {
            user.setCheckin(1);
            userMapper.saveInfoItems(user);
            return new WebResult("200", "保存成功", "");
        } else {

            Integer schoolId = findUser.getSchoolId();
            Integer schoolId1 = user.getSchoolId();
            if (findUser.getSchoolId() != null && user.getSchoolId().compareTo(findUser.getSchoolId())!=0) {
                return new WebResult("400", "该用户已是其他学校学员", "");
            }
            userMapper.updateInfoItems(user);
            return new WebResult("200", "修改成功", "");
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
