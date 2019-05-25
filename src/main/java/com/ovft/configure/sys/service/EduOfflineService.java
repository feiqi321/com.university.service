package com.ovft.configure.sys.service;

import com.ovft.configure.sys.vo.PageBean;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author vvtxw
 * @create 2019-05-24 15:36
 */
public interface EduOfflineService {


    //查询线下报名信息详情
    PageBean queryAllOffInfo(Integer page, Integer size, String schoolId, Integer userId);


}
