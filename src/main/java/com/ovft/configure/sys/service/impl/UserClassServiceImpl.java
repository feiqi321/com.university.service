package com.ovft.configure.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.EduLivePay;
import com.ovft.configure.sys.bean.User;
import com.ovft.configure.sys.bean.UserClass;
import com.ovft.configure.sys.bean.VateType;
import com.ovft.configure.sys.dao.*;
import com.ovft.configure.sys.service.UserClassService;
import com.ovft.configure.sys.utils.FindUserCourseUtil;
import com.ovft.configure.sys.vo.LivePayVo;
import com.ovft.configure.sys.vo.MyCourseAll;
import com.ovft.configure.sys.vo.PageVo;
import com.ovft.configure.sys.vo.UserClassVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName UserClassServiceImpl 学员班级
 * @Author xzy
 * @Version 2.5
 **/
@Service
public class UserClassServiceImpl implements UserClassService {
    @Resource
    private UserClassMapper userClassMapper;
    @Resource
    private EduOfflineNumMapper eduOfflineNumMapper;
    @Resource
    private EduPayrecordMapper eduPayrecordMapper;
    @Resource
    private SchoolMapper schoolMapper;
    @Resource
    private QuestionSearchMapper questionSearchMapper;
    @Resource
    private EduCourseMapper eduCourseMapper;
    @Resource
    private OrderMapper    orderMapper;
    @Resource
    private  EduLivePayMapper eduLivePayMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    public WebResult deleteUserClass(Integer classId) {
        userClassMapper.deleteUserClass(classId);
        return new WebResult("200", "删除成功", "");
    }

    @Transactional
    @Override
    public WebResult updateUserClass(UserClass userClass) {
        userClassMapper.updateUserClass(userClass);
        return new WebResult("200", "修改成功", "");
    }

    //学员报名记录（已支付的===>> 线上报名+线下报名）   分别对应 ===》 edu_payrecord，edu_offline_num
    public List<MyCourseAll> findMyCourseList(PageVo pageVo) {

        pageVo.setPayStatus(5);
        List<MyCourseAll> myCourseAlltop = questionSearchMapper.findMyCourseAlltop(pageVo);
        pageVo.setPayStatus(1);
        List<MyCourseAll> myCourseAlldown = questionSearchMapper.findMyCourseAlldown(pageVo);
        VateType vateType = questionSearchMapper.findCourseImage(2);
        List<MyCourseAll> list = new ArrayList();
        String schoolName = schoolMapper.findSchoolById(pageVo.getSchoolId());



        for (int m = 0; m < myCourseAlltop.size(); m++) {


            myCourseAlltop.get(m).setCourseImage(vateType.getImage());
            myCourseAlltop.get(m).setStatu("线上报名");
            Integer schoolId = myCourseAlltop.get(m).getSchoolId();

            myCourseAlltop.get(m).setSchoolName(schoolName);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
            String format = formatter.format(myCourseAlltop.get(m).getCoursestartTime());
            if (Integer.parseInt(format.substring(5, 7)) > 6) {
                myCourseAlltop.get(m).setCourseyear(format.substring(0, 4) + "下半年度");
            } else {
                myCourseAlltop.get(m).setCourseyear(format.substring(0, 4) + "上半年度");
            }
            list.add(myCourseAlltop.get(m));
        }
        for (int n = 0; n < myCourseAlldown.size(); n++) {
            myCourseAlldown.get(n).setCourseImage(vateType.getImage());
            myCourseAlldown.get(n).setStatu("线下报名");
            Integer schoolId = myCourseAlldown.get(n).getSchoolId();

            myCourseAlldown.get(n).setSchoolName(schoolName);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
            String format = formatter.format(myCourseAlldown.get(n).getCoursestartTime());
            if (Integer.parseInt(format.substring(5, 7)) > 6) {
                myCourseAlldown.get(n).setCourseyear(format.substring(0, 4) + "下半年度");
            } else {
                myCourseAlldown.get(n).setCourseyear(format.substring(0, 4) + "上半年度");
            }
            list.add(myCourseAlldown.get(n));
        }

        if (pageVo.getPageSize() == 0) {

            for (int i = 0; i < list.size(); i++) {      //通过身份证计算出每个学员的年龄并返回
                String card = list.get(i).getIdentityCard();
                int age;
                if (card != null) {
                    age = TeacherServiceImpl.getAgeByCertId(card);
                    list.get(i).setAge(age);
                }
            }

            return list;
        }


        PageHelper.startPage(pageVo.getPageNum(), pageVo.getPageSize());

        for (int i = 0; i < list.size(); i++) {      //通过身份证计算出每个学员的年龄并返回
            String card = list.get(i).getIdentityCard();
            int age;
            if (card != null) {
                age = TeacherServiceImpl.getAgeByCertId(card);
                list.get(i).setAge(age);
            }
        }


        return list;
    }
    //学员报名记录（已支付的===>> 线上报名+线下报名+现场报名的）   分别对应 ===》 edu_payrecord，edu_offline_num   ===>>学员班级列表
    @Override
    public WebResult userClassList(UserClassVo userClassVo) {

        PageVo pageVo = new PageVo();
        if (userClassVo.getUserId() != null) {
            pageVo.setUserId(userClassVo.getUserId());
        }
        if (userClassVo.getPageSize() == 0) {

            List<UserClass> userClassList = userClassMapper.userClassList(userClassVo);
            for (int m = 0; m <userClassList.size() ; m++) {
                //给每个班级设置封装计划人数
                userClassList.get(m).setPeopleNumber(eduCourseMapper.queryAcceptNum(userClassList.get(m).getCourseId()));
                //查询用户所对应的专业显示已经购买人数
                Map<String, Object> param = new HashMap<>();
                param.put("course_id", userClassList.get(m).getCourseId());
                param.put("payment_status", "PAID");    //查询线上报名成功的记录数
                int olineNum = orderMapper.countPayCourseNum(param);
                //查询现场报名相关成员记录
                LivePayVo livePayVo=new LivePayVo();
                livePayVo.setCourseId(userClassList.get(m).getCourseId());
                List<LivePayVo> livePayVos = eduLivePayMapper.selectLivePay(livePayVo);
                LivePayVo livePayVo2=new LivePayVo();
                livePayVo2.setCourseId(userClassList.get(m).getCourseId());   //查找当前课程的退课数量
                List<LivePayVo> livePayVos2 = questionSearchMapper.selectClassOut(livePayVo2);
                 int NowNumber= olineNum+livePayVos.size()-livePayVos2.size(); //   线上报名+现场报名的-退课的人数
                userClassList.get(m).setNowNumber(NowNumber);      //给每个班级封装实际人数

            }

            List<MyCourseAll> myCourseList = findMyCourseList(pageVo);

            if (userClassVo.getUserId() != null) {
                PageVo pageVo2=new PageVo();

                List<UserClass> endUserClassList = new LinkedList<>();      //处理前台只显示用户已报名的班级
                for (int n = 0; n < myCourseList.size(); n++) {
                            for (int m=0;m<userClassList.size();m++) {
                                if (userClassList.get(m).getCourseId() == myCourseList.get(n).getCourseId()) {

                                    endUserClassList.add(userClassList.get(m));
                                }
                            }

                }

                for (int q=0;q<endUserClassList.size();q++){    //通过课程Id查找相应班级的上课人数,然后封装 endUserClassList.get(q)
                    pageVo2.setSchoolId(pageVo.getSchoolId());
                      pageVo2.setCourseId(endUserClassList.get(q).getCourseId());
                    List<MyCourseAll> myCourseList2=findMyCourseList(pageVo2);
                    //查询现场报名相关成员记录
                    LivePayVo livePayVo2=new LivePayVo();
                    livePayVo2.setCourseId(endUserClassList.get(q).getCourseId());
                    List<LivePayVo> livePayVos3 = eduLivePayMapper.selectLivePay(livePayVo2);
                    endUserClassList.get(q).setNum(myCourseList2.size()+livePayVos3.size());   //线上报名+现场报名的

                }
                return new WebResult("200", "查询成功", endUserClassList);
            } else {

                return new WebResult("200", "查询成功", userClassList);
            }

        } else {

            PageHelper.startPage(userClassVo.getPageNum(), userClassVo.getPageSize());
            List<UserClass> userClassList = userClassMapper.userClassList(userClassVo);    //查找所有班级
            for (int m = 0; m <userClassList.size() ; m++) {
                //给每个班级设置封装计划人数
                userClassList.get(m).setPeopleNumber(eduCourseMapper.queryAcceptNum(userClassList.get(m).getCourseId()));
                //给每个班级设置封装计划人数
              userClassList.get(m).setPeopleNumber(eduCourseMapper.queryAcceptNum(userClassList.get(m).getCourseId()));
                //查询用户所对应的专业显示已经购买人数
                Map<String, Object> param = new HashMap<>();
                param.put("course_id", userClassList.get(m).getCourseId());
                param.put("payment_status", "PAID");
                int olineNum = orderMapper.countPayCourseNum(param);
                //查询现场报名相关成员记录
                LivePayVo livePayVo=new LivePayVo();
                livePayVo.setCourseId(userClassList.get(m).getCourseId());
                List<LivePayVo> livePayVos = eduLivePayMapper.selectLivePay(livePayVo);
//                LivePayVo livePayVo2=new LivePayVo();
//                livePayVo2.setCourseId(userClassList.get(m).getCourseId());   //查找当前课程的退课数量
//                List<LivePayVo> livePayVos2 = questionSearchMapper.selectClassOut(livePayVo2);
                int NowNumber= olineNum+livePayVos.size(); //   线上报名+现场报名的-退课的人数
                userClassList.get(m).setNowNumber(NowNumber);      //给每个班级封装实际人数

            }
            FindUserCourseUtil findUserCourseUtil = new FindUserCourseUtil();

            List<MyCourseAll> myCourseList = findMyCourseList(pageVo);

            if (userClassVo.getUserId() != null) {
                PageVo pageVo2=new PageVo();
                List<UserClass> userClassList2 = userClassMapper.userClassList(userClassVo);
                //查询现场报名相关成员记录
                String schoolName = schoolMapper.findSchoolById(pageVo.getSchoolId());
                User user = userMapper.selectById(pageVo.getUserId());
                LivePayVo livePayVo=new LivePayVo();

                livePayVo.setPhone(user.getPhone());
                List<LivePayVo> livePayVos = eduLivePayMapper.selectLivePay(livePayVo);

                for (int n = 0; n <livePayVos.size(); n++) {     //封装现场缴费列表记录，将现场缴费记录的人查出来然后在班级里面显示
                    MyCourseAll myCourseAll=new MyCourseAll();
                    myCourseAll.setCourseId(((EduLivePay)livePayVos.get(n)).getCourseId());
                    myCourseAll.setUserName(((EduLivePay)livePayVos.get(n)).getUserName());
                    myCourseAll.setCourseName(((EduLivePay)livePayVos.get(n)).getCourseName());
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
                    String format = formatter.format(((EduLivePay)livePayVos.get(n)).getPayDate());
                    if (Integer.parseInt(format.substring(5, 7)) > 6) {
                        myCourseAll.setCourseyear(format.substring(0, 4) + "下半年度");
                    } else {
                        myCourseAll.setCourseyear(format.substring(0, 4) + "上半年度");
                    }
                    myCourseAll.setCourseTeacher(((EduLivePay)livePayVos.get(n)).getCourseTeacher());
                    myCourseAll.setSchoolName(schoolName);
                    myCourseAll.setAddress(((EduLivePay)livePayVos.get(n)).getAddress());
                    myCourseAll.setPhone(((EduLivePay)livePayVos.get(n)).getPhone());
                    myCourseAll.setIdentityCard(((EduLivePay)livePayVos.get(n)).getIdentityCard());
                    myCourseAll.setJob(((EduLivePay)livePayVos.get(n)).getJob());
                    if (((EduLivePay)livePayVos.get(n)).getPayCode()==1) {
                        myCourseAll.setStatu("现场收费");
                    }
                    if (((EduLivePay)livePayVos.get(n)).getPayCode()==2) {
                        myCourseAll.setStatu("免费");
                    }
                    myCourseList.add(myCourseAll);
                }
                List<UserClass> endUserClassList = new LinkedList<>();      //处理前台只显示用户已报名的班级
                for (int n = 0; n < myCourseList.size(); n++) {
                        for (int m=0;m<userClassList2.size();m++) {
                            if (userClassList2.get(m).getCourseId() == myCourseList.get(n).getCourseId()) {
                                endUserClassList.add(userClassList2.get(m));
                            }
                        }
                }
                for (int q=0;q<endUserClassList.size();q++){    //通过课程Id查找相应班级的上课人数,然后封装 endUserClassList.get(q)
                    pageVo2.setSchoolId(pageVo.getSchoolId());
                    pageVo2.setCourseId(endUserClassList.get(q).getCourseId());
                    List<MyCourseAll> myCourseList2=findMyCourseList(pageVo2);
                    //查询现场报名相关成员记录
                    LivePayVo livePayVo2=new LivePayVo();
                    livePayVo2.setCourseId(endUserClassList.get(q).getCourseId());
                    List<LivePayVo> livePayVos3 = eduLivePayMapper.selectLivePay(livePayVo2);
                    endUserClassList.get(q).setNum(myCourseList2.size()+livePayVos3.size());

                }
                PageInfo pageInfo = new PageInfo<>(endUserClassList);
                return new WebResult("200", "查询成功", pageInfo);
            } else {
                PageInfo pageInfo = new PageInfo<>(userClassList);
                return new WebResult("200", "查询成功", pageInfo);
            }
        }


    }

    @Transactional
    @Override
    public WebResult classdeleteUser(MyCourseAll myCourseAll) {
        if (myCourseAll.getStatu().equals("线上报名")) {
            eduPayrecordMapper.deleteByPrimaryKey(myCourseAll.getId());
        } else {
            eduOfflineNumMapper.deleteByPrimaryKey(myCourseAll.getId());
        }
        return new WebResult("200", "删除成功", "");
    }
}
