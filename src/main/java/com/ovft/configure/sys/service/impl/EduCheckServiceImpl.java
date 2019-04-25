package com.ovft.configure.sys.service.impl;

import com.ovft.configure.sys.bean.EduCheck;
import com.ovft.configure.sys.bean.EduCheckExample;
import com.ovft.configure.sys.dao.EduCheckMapper;
import com.ovft.configure.sys.service.EduCheckService;
import com.ovft.configure.sys.vo.EduCheckVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author vvtxw
 * @create 2019-04-15 21:47
 */
@Service
public class EduCheckServiceImpl implements EduCheckService {
    @Resource
    private EduCheckMapper eduCheckMapper;


    @Override
    public int doSign(EduCheck eduCheck) {
        return eduCheckMapper.insertSelective(eduCheck);
    }

    /**
     * 查询打卡记录
     *
     * @param userId
     * @return
     */
    @Override
    public List<EduCheckVo> queryAllPunchRecord(Integer userId) {
        return eduCheckMapper.queryAllPunchRecord(userId);
    }

    @Override
    public List<EduCheckVo> queryNowDayRecord() {
        Date nowTime = new Date();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        String nowDay = date.format(nowTime);//获取当天日期字符串
        //查询当天的需要打卡的课程信息
        List<EduCheckVo> eduCheckVos = eduCheckMapper.queryNowDayRecord(nowDay);
        return eduCheckVos;
    }
}
