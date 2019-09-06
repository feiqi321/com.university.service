package com.ovft.configure.sys.service;

import com.ovft.configure.sys.bean.EduVersion;

/**
 * @author vvtxw
 * @create 2019-08-28 17:58
 */
public interface EduVersionService {

    //增加版本
    public Integer insertVersion(EduVersion eduVersion);
    //查看版本
    public EduVersion selectVersion(Integer id);
}
