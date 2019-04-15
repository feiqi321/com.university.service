package com.ovft.configure.sys.service;

import com.ovft.configure.sys.bean.EduCheck;
import com.ovft.configure.sys.bean.EduCheckExample;
import org.apache.ibatis.annotations.Param;

/**
 * @author vvtxw
 * @create 2019-04-15 14:39
 */
public interface EduCheckService {
    /**
     * 根据userId更新状态
     * @param record
     * @param example
     * @return
     */
    int doSign(@Param("record") EduCheck record, @Param("example") EduCheckExample example);

}
