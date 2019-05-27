package com.ovft.configure.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.FineCourse;
import com.ovft.configure.sys.bean.School;
import com.ovft.configure.sys.dao.FineCourseMapper;
import com.ovft.configure.sys.dao.SchoolMapper;
import com.ovft.configure.sys.service.FineCourseService;
import com.ovft.configure.sys.vo.PageVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName ActivitiesServiceImpl
 * @Author zqx
 * @Version 1.0
 **/
@Service
public class FineCourseServiceImpl implements FineCourseService {

    private static final Logger logger = LoggerFactory.getLogger(FineCourseServiceImpl.class);

    @Resource
    private FineCourseMapper fineCourseMapper;
    @Resource
    private SchoolMapper schoolMapper;

    @Override
    public WebResult fineCourseList(PageVo pageVo) {
        if (pageVo.getPageSize() == 0) {
            List<FineCourse> courseList = fineCourseMapper.selectFineCourseList(pageVo);
            findSchool(courseList);
            return new WebResult("200", "查询成功", courseList);
        }
        PageHelper.startPage(pageVo.getPageNum(), pageVo.getPageSize());
        List<FineCourse> courseList = fineCourseMapper.selectFineCourseList(pageVo);
        findSchool(courseList);
        PageInfo pageInfo = new PageInfo<>(courseList);

        return new WebResult("200", "查询成功", pageInfo);
    }

    public void findSchool(List<FineCourse> courseList) {
        for (FineCourse fineCourse : courseList) {
            School school = schoolMapper.selectById(fineCourse.getSchoolId());
            if(school != null) {
                fineCourse.setSchoolName(school.getSchoolName());
                fineCourse.setImage(school.getImage());
            }
        }
    }

    @Transactional
    @Override
    public WebResult createFineCourse(FineCourse fineCourse) {
        if (StringUtils.isBlank(fineCourse.getTitle())) {
            return new WebResult("400", "课程标题不能为空", "");
        }
        if (fineCourse.getSchoolId() == null) {
            return new WebResult("400", "请选择学校", "");
        }
        if (StringUtils.isBlank(fineCourse.getVideo())) {
            return new WebResult("400", "请上传课程视频", "");
        }
        if (StringUtils.isBlank(fineCourse.getTeacher())) {
            return new WebResult("400", "课程教师不能为空", "");
        }
        if (StringUtils.isBlank(fineCourse.getIntroduce())) {
            return new WebResult("400", "课程介绍不能为空", "");
        }
        if (StringUtils.isBlank(fineCourse.getCover())) {
            return new WebResult("400", "课程封面不能为空", "");
        }
        if(fineCourse.getFineId() == null) {
            fineCourse.setVisits(0);
            fineCourse.setThumbup(0);
            fineCourseMapper.createFineCourse(fineCourse);
            return new WebResult("200", "保存成功", "");
        } else {
            fineCourseMapper.updateFineCourse(fineCourse);
            return new WebResult("200", "修改成功", "");
        }
    }

    @Override
    public WebResult findFineCourse(Integer fineId) {
        FineCourse fineCourse = fineCourseMapper.selectById(fineId);
        return new WebResult("200", "查询成功", fineCourse);
    }

    @Transactional
    @Override
    public WebResult deleteFineCourse(Integer fineId) {
        fineCourseMapper.deleteFineCourse(fineId);
        return new WebResult("200", "删除成功", "");
    }

    @Transactional
    @Override
    public WebResult findCourse(Integer fineId) {
        FineCourse fineCourse = fineCourseMapper.selectById(fineId);
        //浏览量加1
        fineCourse.setVisits(fineCourse.getVisits() + 1);
        fineCourseMapper.updatVisitOrThumbup(fineCourse.getFineId(), fineCourse.getVisits(), null);
        return new WebResult("200", "查询成功", fineCourse);
    }

    @Transactional
    @Override
    public Integer addThumbup(Integer fineId) {
        FineCourse fineCourse = fineCourseMapper.selectById(fineId);
        //点赞数 + 1
        Integer thumbup = fineCourse.getThumbup() + 1;
        fineCourseMapper.updatVisitOrThumbup(fineCourse.getFineId(), null, thumbup);
        return  thumbup;
    }
}
