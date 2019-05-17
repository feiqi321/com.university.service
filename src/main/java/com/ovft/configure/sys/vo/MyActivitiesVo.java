package com.ovft.configure.sys.vo;

import com.ovft.configure.sys.bean.Activities;
import com.ovft.configure.sys.bean.MyActivities;

/**
 * @author vvtxw
 * @create 2019-04-17 10:37
 */
public class MyActivitiesVo extends MyActivities {
    private Activities activities;

    public MyActivitiesVo(MyActivities myActivities) {
        this.setId(myActivities.getId());
        this.setActivitiesId(myActivities.getActivitiesId());
        this.setType(myActivities.getType());
        this.setAdminId(myActivities.getAdminId());
        this.setUserId(myActivities.getUserId());
        this.setSchoolId(myActivities.getSchoolId());
        this.setRegistTime(myActivities.getRegistTime());
    }

    public MyActivitiesVo() {
        this.activities = activities;
    }

    public Activities getActivities() {
        return activities;
    }

    public void setActivities(Activities activities) {
        this.activities = activities;
    }
}
