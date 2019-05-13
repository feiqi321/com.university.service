package com.ovft.configure.sys.service.impl;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Activities;
import com.ovft.configure.sys.bean.MyActivities;
import com.ovft.configure.sys.dao.ActivitiesMapper;
import com.ovft.configure.sys.dao.MyActivitiesMapper;
import com.ovft.configure.sys.service.MyActivitiesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

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


    @Override
    public WebResult myActivitiesList(int userId) {

        return null;
    }

    @Override
    public WebResult registMyActivities(MyActivities myActivities) {
        return null;
    }

    @Override
    public WebResult deleteMyActivities(Integer id) {
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
