package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.EduVolunteer;
import com.ovft.configure.sys.vo.PageVo;

/**
 * @author zqx
 * @since 2019-07-04
 */
public interface EduVolunteerService {

    public WebResult volunteerList(PageVo pageVo);

    public WebResult findVolunteer(String userId, Integer volunteerId);

    public WebResult volunteerRegist(Integer userId, Integer volunteerId);

    public WebResult myRegistVolunteer(PageVo pageVo);

    public WebResult releaseVolunteer(EduVolunteer volunteer);

    public void giveFlower();
}
