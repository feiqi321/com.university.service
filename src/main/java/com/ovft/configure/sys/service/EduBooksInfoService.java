package com.ovft.configure.sys.service;

import com.ovft.configure.sys.bean.EduBooksInfo;
import com.ovft.configure.sys.vo.EduBooksInfoVo;

/**
 * @author vvtxw
 * @create 2019-05-17 11:41
 */
public interface EduBooksInfoService {

    //查询详情
    EduBooksInfoVo selectBookById(Integer id);
}
