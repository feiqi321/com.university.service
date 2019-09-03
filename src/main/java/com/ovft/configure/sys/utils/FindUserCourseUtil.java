package com.ovft.configure.sys.utils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.VateType;
import com.ovft.configure.sys.dao.QuestionSearchMapper;
import com.ovft.configure.sys.dao.SchoolMapper;
import com.ovft.configure.sys.service.impl.TeacherServiceImpl;
import com.ovft.configure.sys.vo.MyCourseAll;
import com.ovft.configure.sys.vo.PageVo;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class FindUserCourseUtil {
    @Resource
    private SchoolMapper schoolMapper;
    @Resource
    private QuestionSearchMapper questionSearchMapper;
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
            String schoolName = schoolMapper.findSchoolById(schoolId);
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
}
