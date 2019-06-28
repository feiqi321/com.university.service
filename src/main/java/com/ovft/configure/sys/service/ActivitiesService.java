package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Activities;
import com.ovft.configure.sys.vo.PageVo;

public interface ActivitiesService {

    public WebResult activitiesList(PageVo pageVo);

    public WebResult createActivities(Activities activities);

    public WebResult findActivities(Integer activitiesId);

    public WebResult deleteActivities(Integer activitiesId);

    public WebResult registList(Integer activitiesId);
}
