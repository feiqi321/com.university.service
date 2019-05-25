package com.ovft.configure.sys.service.impl;

import com.ovft.configure.sys.bean.EduBooksInfo;
import com.ovft.configure.sys.dao.EduBooksInfoMapper;
import com.ovft.configure.sys.service.EduBooksInfoService;
import com.ovft.configure.sys.vo.EduBooksInfoVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author vvtxw
 * @create 2019-05-17 11:42
 */
@Service
public class EduBooksInfoServiceImpl implements EduBooksInfoService {

    @Resource
    private EduBooksInfoMapper eduBooksInfoMapper;

    @Override
    public EduBooksInfoVo selectBookById(Integer id) {
        return eduBooksInfoMapper.selectBookById(id);
    }
}
