package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.MyActivities;

public interface MyActivitiesService {

    public WebResult myActivitiesList(Integer userId, Integer adminId);

    public WebResult registMyActivities(MyActivities myActivities);

    public WebResult deleteMyActivities(Integer activitiesId, Integer userId);

    public WebResult deleteMyActivitiesPc(Integer userId);

    public WebResult findMyActivities(Integer activitiesId, Integer userId);
}
