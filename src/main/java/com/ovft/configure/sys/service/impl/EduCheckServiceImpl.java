package com.ovft.configure.sys.service.impl;

import com.ovft.configure.sys.bean.EduCheck;
import com.ovft.configure.sys.bean.EduCheckExample;
import com.ovft.configure.sys.dao.EduCheckMapper;
import com.ovft.configure.sys.service.EduCheckService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author vvtxw
 * @create 2019-04-15 21:47
 */
@Service
public class EduCheckServiceImpl implements EduCheckService {
    @Resource
    private EduCheckMapper eduCheckMapper;

    @Override
    public int doSign(EduCheck eduCheck, EduCheckExample example) {
        return eduCheckMapper.updateByExampleSelective(eduCheck,example);
    }
}
