package com.ovft.configure.sys.service.impl;

import com.ovft.configure.sys.bean.UserInfoBean;
import com.ovft.configure.sys.dao.UserInfoMapper;
import com.ovft.configure.sys.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by looyer on 2019/4/2.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public List<UserInfoBean> listUserInfo(UserInfoBean userInfoBean){
        return userInfoMapper.listUserInfo(userInfoBean);
    }

}
