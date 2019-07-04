package com.ovft.configure.sys.service.impl;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.EduFlowers;
import com.ovft.configure.sys.dao.EduFlowersMapper;
import com.ovft.configure.sys.service.EduFlowersService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zqx
 * @since 2019-07-04
 */
@Service
public class EduFlowersServiceImpl implements EduFlowersService {

    @Resource
    private EduFlowersMapper flowersMapper;

    //获取学员的总的花朵数
    @Override
    public WebResult totalFlower(Integer userId) {
        Integer count = flowersMapper.selectTotalFlower(userId);
        return new WebResult("200", "查询成功", count==null?0:count);
    }

    @Override
    public WebResult flowerList(Integer userId) {
        List<EduFlowers> eduFlowers = flowersMapper.selectListByUserId(userId);
        return new WebResult("200", "查询成功", eduFlowers);
    }

    @Transactional
    @Override
    public void createFlower(EduFlowers eduFlowers) {
        Integer count = flowersMapper.selectTotalFlower(eduFlowers.getUserId());
        if (count == null) {
            count = 0 ;
        }
        eduFlowers.setTotalCount(eduFlowers.getAcquire() + count);
        flowersMapper.createFlower(eduFlowers);
    }
}
