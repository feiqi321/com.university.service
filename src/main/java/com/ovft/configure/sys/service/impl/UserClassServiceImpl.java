package com.ovft.configure.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.User;
import com.ovft.configure.sys.bean.UserClass;
import com.ovft.configure.sys.dao.UserClassMapper;
import com.ovft.configure.sys.service.UserClassService;
import com.ovft.configure.sys.vo.UserClassVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName UserClassServiceImpl 学员班级
 * @Author xzy
 * @Version 2.5
 **/
@Service
public class UserClassServiceImpl implements UserClassService {
    @Resource
    private UserClassMapper userClassMapper;

    @Override
    public WebResult deleteUserClass(Integer classId) {
        userClassMapper.deleteUserClass(classId);
        return new WebResult("200", "删除成功", "");
    }

    @Transactional
    @Override
    public WebResult updateUserClass(UserClass userClass) {
        userClassMapper.updateUserClass(userClass);
        return new WebResult("200", "修改成功", "");
    }

    @Override
    public WebResult userClassList(UserClassVo userClassVo) {
        if (userClassVo.getPageSize() == 0) {
            List<UserClass> userClassList = userClassMapper.userClassList(userClassVo);

            return new WebResult("200", "查询成功", userClassList);
        } else {

            PageHelper.startPage(userClassVo.getPageNum(), userClassVo.getPageSize());
            List<UserClass> userClassList = userClassMapper.userClassList(userClassVo);
            PageInfo pageInfo = new PageInfo<>(userClassList);
            return new WebResult("200", "查询成功", pageInfo);

        }


    }
}
