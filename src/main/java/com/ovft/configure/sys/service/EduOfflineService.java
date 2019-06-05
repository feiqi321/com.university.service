package com.ovft.configure.sys.service;

import com.ovft.configure.sys.vo.PageBean;
import com.ovft.configure.sys.vo.QueryOffLineVos;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author vvtxw
 * @create 2019-05-24 15:36
 */
public interface EduOfflineService {


    //查询线下报名信息详情--
    QueryOffLineVos queryAllOffInfo(String schoolId, Integer userId);


}
