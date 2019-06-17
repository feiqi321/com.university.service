package com.ovft.configure.sys.service.impl;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Activities;
import com.ovft.configure.sys.bean.MyActivities;
import com.ovft.configure.sys.dao.ActivitiesMapper;
import com.ovft.configure.sys.dao.AdminMapper;
import com.ovft.configure.sys.dao.MyActivitiesMapper;
import com.ovft.configure.sys.service.MyActivitiesService;
import com.ovft.configure.sys.vo.AdminVo;
import com.ovft.configure.sys.vo.MyActivitiesVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName MyActivitiesServiceImpl
 * @Author zqx
 * @Version 1.0
 **/
@Service
public class MyActivitiesServiceImpl implements MyActivitiesService {

    private static final Logger logger = LoggerFactory.getLogger(MyActivitiesServiceImpl.class);

    @Resource
    private MyActivitiesMapper myActivitiesMapper;
    @Resource
    private ActivitiesMapper activitiesMapper;
    @Resource
    private AdminMapper adminMapper;


    @Override
    public WebResult myActivitiesList(Integer userId, Integer adminId) {
        List<MyActivities> myActivities = myActivitiesMapper.selectByUserOrActivities(userId, adminId, null);
        LinkedList<MyActivitiesVo> voList = new LinkedList<>();
        for (MyActivities myActivity : myActivities) {
            MyActivitiesVo vo = new MyActivitiesVo(myActivity);
            Activities activities = activitiesMapper.selectById(myActivity.getActivitiesId());
            vo.setActivities(activities);
            voList.push(vo);
        }
        return new WebResult("200", "查询成功", voList);
    }

    /**使用for update一定要加上这个事务
     * 当事务处理完后，for update才会将行级锁解除*/
    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public WebResult registMyActivities(MyActivities myActivities) {
        Activities activities = activitiesMapper.selectById(myActivities.getActivitiesId());

        Date registStartTime = activities.getRegistStartTime();
        Date registEndTime = activities.getRegistEndTime();
        Date date = new Date();
        boolean after = date.before(registEndTime) || DateUtils.isSameDay(registEndTime, date);
        if(date.before(registStartTime) || !after) {
            return new WebResult("400", "不在活动报名时间内", "");
        }
        String category = activities.getCategory();
        //人员类别 管理员,教师,学员
        if(!StringUtils.isBlank(category)) {
            if(myActivities.getUserId() != null) {
                if( !category.contains("学员")){
                    return new WebResult("400", "该活动只有" + category + "能报名", "");
                }

            }
            if(myActivities.getAdminId() != null) {
                List<AdminVo> adminVos = adminMapper.selectByAdminAndSchool(myActivities.getAdminId(), activities.getSchoolId(), null);
                AdminVo admin = adminVos.get(0);
                if(admin.getRole().equals(1) && !category.contains("管理员")) {
                    return new WebResult("400", "该活动只有" + category + "能报名", "");
                }
                if(admin.getRole().equals(2) && !category.contains("教师")) {
                    return new WebResult("400", "该活动只有" + category + "能报名", "");
                }
            }
        }
        List<MyActivities> myActivities1 = myActivitiesMapper.selectByUserOrActivities(myActivities.getUserId(), myActivities.getAdminId(), myActivities.getActivitiesId());
        if(myActivities1.size() != 0) {
            return new WebResult("400", "您已报名该活动", "");
        }

        List<MyActivities> registList = myActivitiesMapper.selectByUserOrActivities(null, null, myActivities.getActivitiesId());
        //已报名人人数 >= 参与人数
        if(registList.size() >= activities.getNumber()) {
            return new WebResult("400", "报名人数已满!", "");
        }
        myActivities.setRegistTime(new Date());
        myActivitiesMapper.createMyActivities(myActivities);
        return new WebResult("200", "报名成功!", "");
    }

    @Override
    public WebResult deleteMyActivities(Integer activitiesId, Integer userId) {
        Activities activities = activitiesMapper.selectById(activitiesId);
        List<MyActivities> myActivities = myActivitiesMapper.selectByUserOrActivities(userId, null, activitiesId);
        Date registEndTime = activities.getRegistEndTime();
        Date date = new Date();
        if(date.after(registEndTime)) {
            return new WebResult("400", "活动报名已截止,不能取消报名", "");
        }
        myActivitiesMapper.deleteMyActivities(myActivities.get(0).getId());
        return new WebResult("200", "删除成功", "");
    }

    @Override
    public WebResult findMyActivities(Integer activitiesId, Integer userId) {
        Activities activities = activitiesMapper.selectById(activitiesId);

        //浏览量加1
        activities.setVisits(activities.getVisits() + 1);
        activitiesMapper.updateActivities(activities);

        // 获取报名人数
        List<MyActivities> myActivities = myActivitiesMapper.selectByUserOrActivities(null, null, activitiesId);
        activities.setRegistNum(myActivities.size());

        if(userId == null) {
            activities.setIsRegist(0);
        } else  {
            List<MyActivities> isRegist = myActivitiesMapper.selectByUserOrActivities(userId, null, activitiesId);
            activities.setIsRegist(isRegist.size());
        }
        return new WebResult("200", "查询成功", activities);
    }

    @Transactional
    @Override
    public WebResult deleteMyActivitiesPc(Integer id) {
        MyActivities myActivities = myActivitiesMapper.selectById(id);
        Activities activities = activitiesMapper.selectById(myActivities.getActivitiesId());
        Date registEndTime = activities.getRegistEndTime();
        Date date = new Date();
        if(date.after(registEndTime)) {
            return new WebResult("400", "活动报名已截止,不能取消报名", "");
        }

        myActivitiesMapper.deleteMyActivities(id);
        return new WebResult("200", "删除成功", "");
    }
}
