package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.MyActivities;

public interface MyActivitiesService {

    public WebResult myActivitiesList(int userId);

    public WebResult registMyActivities(MyActivities myActivities);

    public WebResult deleteMyActivities(Integer id);
}
