package com.ovft.configure.sys.service.impl;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.EduFlowers;
import com.ovft.configure.sys.bean.EduSign;
import com.ovft.configure.sys.dao.EduSignMapper;
import com.ovft.configure.sys.service.EduFlowersService;
import com.ovft.configure.sys.service.EduSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author zqx
 * @since 2019-07-04
 */
@Service
public class EduSignServiceImpl implements EduSignService {

    @Resource
    private EduSignMapper signMapper;
    @Autowired
    private EduFlowersService flowersService;

    @Transactional
    @Override
    public WebResult sign(Integer userId) {
        //每天只能签到一次
        EduSign sign = signMapper.selectLastByUserId(userId);
        if(sign != null) {
            return new WebResult("400", "今天已签到了哟！", "");
        } else {
            sign = new EduSign();
            sign.setUserId(userId);
            sign.setSignTime(new Date());
            signMapper.createSign(sign);

            //添加小红花
            EduFlowers flowers = new EduFlowers(userId,"签到奖励", 1, sign.getSignId(), 1, null, new Date());
            flowersService.createFlower(flowers);

            //当月签到次数
            Integer count = signMapper.selectMonthCount(userId);
            return new WebResult("200", "签到成功", count);
        }
    }
}
