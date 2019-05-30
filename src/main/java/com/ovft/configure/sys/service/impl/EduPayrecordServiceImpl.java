package com.ovft.configure.sys.service.impl;

import com.ovft.configure.sys.bean.EduPayrecord;
import com.ovft.configure.sys.dao.EduPayrecordMapper;
import com.ovft.configure.sys.service.EduPayrecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author vvtxw
 * @create 2019-05-29 17:43
 */
@Service
public class EduPayrecordServiceImpl implements EduPayrecordService {
    @Resource
    private EduPayrecordMapper eduPayrecordMapper;

    @Override
    public Integer insertPayRecord(EduPayrecord eduPayrecord) {
        return eduPayrecordMapper.insertSelective(eduPayrecord);
    }
}
