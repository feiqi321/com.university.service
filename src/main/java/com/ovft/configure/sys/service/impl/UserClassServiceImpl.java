package com.ovft.configure.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.User;
import com.ovft.configure.sys.bean.UserClass;
import com.ovft.configure.sys.bean.VateType;
import com.ovft.configure.sys.dao.*;
import com.ovft.configure.sys.service.UserClassService;
import com.ovft.configure.sys.utils.FindUserCourseUtil;
import com.ovft.configure.sys.vo.MyCourseAll;
import com.ovft.configure.sys.vo.PageVo;
import com.ovft.configure.sys.vo.UserClassVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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

    //学员报名记录（已支付的===>> 线上报名+线下报名）
    public List<MyCourseAll> findMyCourseList(PageVo pageVo) {

        pageVo.setPayStatus(5);
        List<MyCourseAll> myCourseAlltop = questionSearchMapper.findMyCourseAlltop(pageVo);
        pageVo.setPayStatus(1);
        List<MyCourseAll> myCourseAlldown = questionSearchMapper.findMyCourseAlldown(pageVo);
        VateType vateType = questionSearchMapper.findCourseImage(2);
        List<MyCourseAll> list = new ArrayList();

        for (int m = 0; m < myCourseAlltop.size(); m++) {



            myCourseAlltop.get(m).setCourseImage(vateType.getImage());
            myCourseAlltop.get(m).setStatu("线上报名");
            Integer schoolId = myCourseAlltop.get(m).getSchoolId();
            String schoolName = schoolMapper.findSchoolById(schoolId);
            myCourseAlltop.get(m).setSchoolName(schoolName);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
            String format = formatter.format(myCourseAlltop.get(m).getEndDate());
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
            String schoolName = schoolMapper.findSchoolById(schoolId);
            myCourseAlldown.get(n).setSchoolName(schoolName);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
            String format = formatter.format(myCourseAlldown.get(n).getEndDate());
            if (Integer.parseInt(format.substring(5, 7)) > 6) {
                myCourseAlldown.get(n).setCourseyear(format.substring(0, 4) + "下半年度");
            } else {
                myCourseAlldown.get(n).setCourseyear(format.substring(0, 4) + "上半年度");
            }
            list.add(myCourseAlldown.get(n));
        }

        if (pageVo.getPageSize() == 0) {

            for (int i = 0; i < list.size(); i++) {      //通过身份证计算出每个学员的年龄并返回
                String card=list.get(i).getIdentityCard();
                int age;
                if (card!=null) {
                    age = TeacherServiceImpl.getAgeByCertId(card);
                    list.get(i).setAge(age);
                }
            }

            return  list;
        }


        PageHelper.startPage(pageVo.getPageNum(), pageVo.getPageSize());

        for (int i = 0; i < list.size(); i++) {      //通过身份证计算出每个学员的年龄并返回
            String card=list.get(i).getIdentityCard();
            int age;
            if (card!=null) {
                age = TeacherServiceImpl.getAgeByCertId(card);
                list.get(i).setAge(age);
            }
        }


        return list;
    }

    @Override
    public WebResult userClassList(UserClassVo userClassVo) {

        PageVo pageVo=new PageVo();
        if (userClassVo.getUserId()!=null) {
            pageVo.setUserId(userClassVo.getUserId());
        }
        if (userClassVo.getPageSize() == 0) {

            List<UserClass> userClassList = userClassMapper.userClassList(userClassVo);




            List<MyCourseAll> myCourseList = findMyCourseList(pageVo);
            for (int i=0;i<userClassList.size();i++){    //给userClassList里的元素封装班级人数
                for (int n=0;n<myCourseList.size();n++){
                    if (userClassList.get(i).getCourseId()==myCourseList.get(n).getCourseId()){
                        if(userClassList.get(i).getUsers() == null) {
                            userClassList.get(i).setUsers(new ArrayList<>());
                        }
                        userClassList.get(i).getUsers().add(myCourseList.get(n));
                    }

                }

            }
                 if (userClassVo.getUserId()!=null) {
                     List<UserClass> endUserClassList = new LinkedList<>();      //处理前台只显示用户已报名的班级
                     for (int n = 0; n < myCourseList.size(); n++) {
                         if (userClassList.get(n).getCourseId() == myCourseList.get(n).getCourseId()) {

                             endUserClassList.add(userClassList.get(n));
                         }

                     }
                     return new WebResult("200", "查询成功", endUserClassList);
                 }else {

                     return new WebResult("200", "查询成功", userClassList);
                 }

        } else {

            PageHelper.startPage(userClassVo.getPageNum(), userClassVo.getPageSize());
            List<UserClass> userClassList = userClassMapper.userClassList(userClassVo);
            FindUserCourseUtil findUserCourseUtil=new FindUserCourseUtil();

            List<MyCourseAll> myCourseList = findMyCourseList(pageVo);
            for (int i=0;i<userClassList.size();i++){    //给userClassList里的元素封装班级人数
                for (int n=0;n<myCourseList.size();n++){
                    if (userClassList.get(i).getCourseId()==myCourseList.get(n).getCourseId()){
                        if(userClassList.get(i).getUsers() == null) {
                            userClassList.get(i).setUsers(new ArrayList<>());
                        }
                        userClassList.get(i).getUsers().add(myCourseList.get(n));
                    }

                }

            }
            if (userClassVo.getUserId()!=null) {
                List<UserClass> endUserClassList = new LinkedList<>();      //处理前台只显示用户已报名的班级
                for (int n = 0; n < myCourseList.size(); n++) {
                    if (userClassList.get(n).getCourseId() == myCourseList.get(n).getCourseId()) {

                        endUserClassList.add(userClassList.get(n));
                    }

                }
                PageInfo pageInfo = new PageInfo<>(endUserClassList);
                return new WebResult("200", "查询成功", pageInfo);
            }else {
                PageInfo pageInfo = new PageInfo<>(userClassList);
                return new WebResult("200", "查询成功", pageInfo);
            }
        }


    }
    @Transactional
    @Override
    public WebResult classdeleteUser(MyCourseAll myCourseAll) {
           if (myCourseAll.getStatu().equals("线上报名")){
               eduPayrecordMapper.deleteByPrimaryKey(myCourseAll.getId());
           }else {
               eduOfflineNumMapper.deleteByPrimaryKey(myCourseAll.getId());
           }
        return new WebResult("200", "删除成功", "");
    }
}
