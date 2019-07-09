package com.ovft.configure.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Activities;
import com.ovft.configure.sys.dao.ActivitiesMapper;
import com.ovft.configure.sys.service.ActivitiesService;
import com.ovft.configure.sys.vo.PageVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ActivitiesServiceImpl
 * @Author zqx
 * @Version 1.0
 **/
@Service
public class ActivitiesServiceImpl implements ActivitiesService {

    private static final Logger logger = LoggerFactory.getLogger(ActivitiesServiceImpl.class);

    @Resource
    private ActivitiesMapper activitiesMapper;


    @Override
    public WebResult activitiesList(PageVo pageVo) {
        if (pageVo.getPageSize() == 0) {
            List<Activities> activitiesList = activitiesMapper.selectActivitiesList(pageVo);
            activitiesList.forEach(activities -> {
                Integer integer = activitiesMapper.registCount(activities.getActivitiesId());
                activities.setRegistNum(integer);
            });
            return new WebResult("200", "查询成功", activitiesList);
        }
        PageHelper.startPage(pageVo.getPageNum(), pageVo.getPageSize());
        List<Activities> activitiesList = activitiesMapper.selectActivitiesList(pageVo);
        activitiesList.forEach(activities -> {
            Integer integer = activitiesMapper.registCount(activities.getActivitiesId());
            activities.setRegistNum(integer);
        });
        PageInfo pageInfo = new PageInfo<>(activitiesList);

        return new WebResult("200", "查询成功", pageInfo);
    }

    @Transactional
    @Override
    public WebResult createActivities(Activities activities) {
        if (StringUtils.isBlank(activities.getTitle())) {
            return new WebResult("400", "活动标题不能为空", "");
        }
        if (activities.getSchoolId() == null) {
            return new WebResult("400", "请选择学校", "");
        }
        if (StringUtils.isBlank(activities.getType())) {
            return new WebResult("400", "活动类型不能为空", "");
        }
        if (StringUtils.isBlank(activities.getContent())) {
            return new WebResult("400", "活动内容不能为空", "");
        }
        if (activities.getNumber() == null || activities.getNumber().equals(0)) {
            return new WebResult("400", "活动人数不能为空", "");
        }
        if (StringUtils.isBlank(activities.getPlace())) {
            return new WebResult("400", "活动地点不能为空", "");
        }
        if (activities.getStartTime() == null || activities.getEndTime() == null) {
            return new WebResult("400", "请添加活动日期", "");
        }
        if (activities.getStartTime().after(activities.getEndTime())) {
            return new WebResult("400", "活动结束日期不能早于开始日期", "");
        }
        if (activities.getRegistStartTime() == null || activities.getRegistEndTime() == null) {
            return new WebResult("400", "请添加活动报名日期", "");
        }
        if (activities.getRegistStartTime().after(activities.getRegistEndTime())) {
            return new WebResult("400", "活动报名结束日期不能早于开始日期", "");
        }
        if (activities.getRegistStartTime().after(activities.getStartTime())) {
            return new WebResult("400", "活动开始日期不能早于活动报名日期", "");
        }
        if(activities.getStartAge() == null) {
            activities.setStartAge(0);
        }
        if(activities.getEndAge() == null) {
            activities.setEndAge(100);
        }
        if(activities.getEndAge().compareTo(activities.getStartAge()) < 0) {
            return new WebResult("400", "年龄范围填写错误", "");
        }
        if(activities.getType() == null) {
            return new WebResult("400", "活动类型不能为空", "");
        }

        if(activities.getActivitiesId() == null) {
            activities.setVisits(0);
            activitiesMapper.createActivities(activities);
            return new WebResult("200", "保存成功", "");
        } else {
            activitiesMapper.updateActivities(activities);
            return new WebResult("200", "修改成功", "");
        }
    }

    @Override
    public WebResult findActivities(Integer activitiesId) {
        Activities activities = activitiesMapper.selectById(activitiesId);
        return new WebResult("200", "查询成功", activities);
    }

    @Transactional
    @Override
    public WebResult deleteActivities(Integer activitiesId) {
        Integer count = activitiesMapper.registCount(activitiesId);
        if(count > 0) {
            return new WebResult("400", "该活动已有人报名，不能删除", "");
        }
        activitiesMapper.deleteActivities(activitiesId);
        return new WebResult("200", "删除成功", "");
    }

    @Override
    public WebResult registList(Integer activitiesId) {
        List<Map<String, Object>> maps = activitiesMapper.registList(activitiesId);
        return new WebResult("200", "查询成功", maps);
    }
}
