package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.UserInfoBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by looyer on 2019/4/2.
 */
@Mapper
public interface UserInfoMapper {

    public List<UserInfoBean> listUserInfo(UserInfoBean userInfoBean);

}
