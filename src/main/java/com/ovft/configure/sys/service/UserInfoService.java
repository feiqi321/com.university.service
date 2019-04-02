package com.ovft.configure.sys.service;

import com.ovft.configure.sys.bean.UserInfoBean;

import java.util.List;

/**
 * Created by looyer on 2019/4/2.
 */
public interface UserInfoService {

    public List<UserInfoBean> listUserInfo(UserInfoBean userInfoBean);

}
