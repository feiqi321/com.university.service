package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.User;
import com.ovft.configure.sys.vo.PhoneVo;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    //用户注册
    public WebResult addUser(User user);
    //查找用户
    public WebResult findUser(User user);
    //修改密码（通过手机号）
    public WebResult updatePassword(PhoneVo phoneVo);
    //修改密码（通过原密码）
    public WebResult updatePasswordByOldPass(PhoneVo phoneVo);
    //保存基本信息
    public WebResult savaInfo(User user);
    //更换手机号码
    public WebResult updatePhone(PhoneVo phoneVo);
    //退出登录
    public WebResult userQuit(String token);
    //查询信息接口
    public WebResult selectInfo(User user);
    //查找头像地址
    public String queryAllAddress(Integer userId);
    //上传头像
    public void updateAddress(User user);
    //添加个性签名
    public void createMycontext(User user,Integer userId);
}
