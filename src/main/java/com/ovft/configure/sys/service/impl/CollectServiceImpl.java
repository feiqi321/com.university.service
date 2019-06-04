package com.ovft.configure.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Collect;
import com.ovft.configure.sys.dao.CollectMapper;
import com.ovft.configure.sys.service.CollectService;
import com.ovft.configure.sys.vo.PageVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ClassName CollectServiceImpl
 * @Author zqx
 * @Version 1.0
 **/
@Service
public class CollectServiceImpl implements CollectService {

    @Resource
    private CollectMapper collectMapper;

    private static final Logger logger = LoggerFactory.getLogger(CollectServiceImpl.class);

    @Transactional
    @Override
    public WebResult addCollect(Collect collect) {
        List<Collect> collects = collectMapper.selectList(collect.getUid(), collect.getType(), collect.getTypeId());
        if(collects.size() != 0) {
            return new WebResult("200", "收藏成功", "");
        }
        collect.setDate(new Date());
        collectMapper.createCollect(collect);
        return new WebResult("200", "收藏成功", "");
    }

    @Override
    public WebResult collectList(PageVo pageVo) {
        if (pageVo.getPageSize() == 0) {
            List<Collect> collects = collectMapper.selectList(pageVo.getUserId(), Integer.valueOf(pageVo.getType()), null);
            return new WebResult("200", "查询成功", collects);
        }
        PageHelper.startPage(pageVo.getPageNum(), pageVo.getPageSize());
        List<Collect> collects = collectMapper.selectList(pageVo.getUserId(), Integer.valueOf(pageVo.getType()), null);
        PageInfo pageInfo = new PageInfo<>(collects);
        return new WebResult("200", "查询成功", pageInfo);
    }


    @Override
    public Integer isCollect(Integer userId, Integer type, Integer typeId) {
        List<Collect> collects = collectMapper.selectList(userId, type, typeId);
        return collects.size();
    }

    @Transactional
    @Override
    public WebResult deleteCollect(Integer collectId) {
        collectMapper.deleteCollect(collectId);
        return new WebResult("200", "删除成功", "");
    }


}
