package com.ovft.configure.sys.service.impl;

import com.ovft.configure.sys.bean.EduVersion;
import com.ovft.configure.sys.dao.EduVersionMapper;
import com.ovft.configure.sys.service.EduVersionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author vvtxw
 * @create 2019-08-28 18:01
 */
@Service
public class EduVersionServiceImpl implements EduVersionService {

    @Resource
    private EduVersionMapper eduVersionMapper;

    //增加版本
    @Override
    public Integer insertVersion(EduVersion eduVersion) {
       return eduVersionMapper.insertVersion(eduVersion);
    }

    //查看版本
    @Override
    public EduVersion selectVersion(Integer id) {
        return eduVersionMapper.selectVersion(id);
    }
}
