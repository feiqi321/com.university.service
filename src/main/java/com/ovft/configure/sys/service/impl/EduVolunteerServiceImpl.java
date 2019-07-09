package com.ovft.configure.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.EduVolunteer;
import com.ovft.configure.sys.bean.MyVolunteer;
import com.ovft.configure.sys.dao.EduFlowersMapper;
import com.ovft.configure.sys.dao.EduVolunteerMapper;
import com.ovft.configure.sys.service.EduVolunteerService;
import com.ovft.configure.sys.vo.PageVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zqx
 * @since 2019-07-04
 */
@Service
public class EduVolunteerServiceImpl implements EduVolunteerService {

    @Resource
    private EduVolunteerMapper volunteerMapper;
    @Resource
    private EduFlowersMapper flowersMapper;

    //获取志愿活动列表
    //我发布的志愿活动列表
    @Override
    public WebResult volunteerList(PageVo pageVo) {
        if (pageVo.getPageSize() == 0) {
            List<EduVolunteer> eduVolunteers = volunteerMapper.selectList(pageVo);
            return new WebResult("200", "查询成功", eduVolunteers);
        }
        PageHelper.startPage(pageVo.getPageNum(), pageVo.getPageSize());
        List<EduVolunteer> eduVolunteers = volunteerMapper.selectList(pageVo);
        PageInfo pageInfo = new PageInfo<>(eduVolunteers);
        return new WebResult("200", "查询成功", pageInfo);
    }

    //志愿活动详情
    @Transactional
    @Override
    public WebResult findVolunteer(String userId, Integer volunteerId) {
        EduVolunteer eduVolunteer = volunteerMapper.selectById(volunteerId);
        eduVolunteer.setVisits(eduVolunteer.getVisits() + 1);
        volunteerMapper.updateVisits(eduVolunteer);

        //查找是否已经报名该志愿活动
        if(StringUtils.isBlank(userId)){
            eduVolunteer.setIsRegist(0);
        } else {
            MyVolunteer myVolunteer = volunteerMapper.selectMyVolunteer(Integer.valueOf(userId), volunteerId);
            if(myVolunteer == null) {
                eduVolunteer.setIsRegist(0);
            } else {
                eduVolunteer.setIsRegist(1);
            }
        }

        return new WebResult("200", "查询成功", eduVolunteer);
    }

    //志愿活动报名
    @Transactional
    @Override
    public WebResult volunteerRegist(Integer userId, Integer volunteerId) {
        EduVolunteer eduVolunteer = volunteerMapper.selectById(volunteerId);
        if(new Date().after(eduVolunteer.getActivityDate())) {
            return new WebResult("400", "该活动已结束", "");
        }
        MyVolunteer myVolunteer = volunteerMapper.selectMyVolunteer(Integer.valueOf(userId), volunteerId);
        if(myVolunteer != null) {
            return new WebResult("400", "您已报名该志愿活动", "");
        } else {
            myVolunteer = new MyVolunteer();
            myVolunteer.setUserId(userId);
            myVolunteer.setVolunteerId(volunteerId);
            myVolunteer.setRegistDate(new Date());
            volunteerMapper.createMyVolunteer(myVolunteer);
            return new WebResult("200", "报名成功", "");
        }
    }

    //我报名的志愿活动列表
    @Override
    public WebResult myRegistVolunteer(PageVo pageVo) {
        if (pageVo.getPageSize() == 0) {
            List<EduVolunteer> eduVolunteers = volunteerMapper.myRegistVolunteer(pageVo.getUserId());
            return new WebResult("200", "查询成功", eduVolunteers);
        }
        PageHelper.startPage(pageVo.getPageNum(), pageVo.getPageSize());
        List<EduVolunteer> eduVolunteers = volunteerMapper.myRegistVolunteer(pageVo.getUserId());
        PageInfo pageInfo = new PageInfo<>(eduVolunteers);
        return new WebResult("200", "查询成功", pageInfo);
    }

    //发布志愿活动
    @Transactional
    @Override
    public WebResult releaseVolunteer(EduVolunteer volunteer) {
        if(StringUtils.isBlank(volunteer.getTitle())) {
            return new WebResult("400", "活动标题不能为空", "");
        }
        if(StringUtils.isBlank(volunteer.getPlace())) {
            return new WebResult("400", "活动地点不能为空", "");
        }
        if(StringUtils.isBlank(volunteer.getContent())) {
            return new WebResult("400", "活动内容不能为空", "");
        }
        if(volunteer.getNumber() == null) {
            return new WebResult("400", "活动人数不能为空", "");
        }
        if(volunteer.getActivityDate() == null) {
            return new WebResult("400", "活动时间不能为空", "");
        }
        if(volunteer.getFlowers() == null) {
            volunteer.setFlowers(0);
        } else {
            Integer totalFlower = flowersMapper.selectTotalFlower(volunteer.getUserId());
            if(totalFlower == null) totalFlower = 0;
            if(totalFlower < volunteer.getNumber() * volunteer.getFlowers()) {
                return new WebResult("400", "您当前红花总数不足！", "");
            }
        }
        volunteer.setReleaseDate(new Date());
        volunteer.setVisits(0);
        volunteerMapper.createVolunteer(volunteer);
        return new WebResult("200", "发布成功", "");
    }
}
