package com.ovft.configure.sys.service;

import com.ovft.configure.sys.bean.EduCheck;
import com.ovft.configure.sys.bean.EduCheckExample;
import com.ovft.configure.sys.vo.EduCheckVo;
import com.ovft.configure.sys.vo.EduCourseVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author vvtxw
 * @create 2019-04-15 14:39
 */
public interface EduCheckService {
    /**
     * 根据userId打卡
     *
     * @param record
     * @return
     */
    int doSign(EduCheck record);

    /**
     * 根据userId查询打卡记录
     *
     * @param userIds
     * @return
     */
    List<EduCheckVo> queryAllPunchRecord(Integer userId);

}
