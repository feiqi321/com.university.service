package com.ovft.configure.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.EduNewstype;
import com.ovft.configure.sys.dao.EduNewstypeMapper;
import com.ovft.configure.sys.service.NewsTypeService;
import com.ovft.configure.sys.vo.PageVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
     @Service
public class NewsTypeServiceImpl implements NewsTypeService {
         @Resource
         private EduNewstypeMapper eduNewstypeMapper;
    @Override
    public WebResult queryNewsTypeAll(PageVo pageVo) {
        if(pageVo.getPageSize() == 0) {
            List<EduNewstype> newstypeList = eduNewstypeMapper.queryNewsTypeAll(pageVo);
            return new WebResult("200","查询成功", newstypeList);
        }
        PageHelper.startPage(pageVo.getPageNum(), pageVo.getPageSize());
        List<EduNewstype> newstypeList2 =eduNewstypeMapper.queryNewsTypeAll(pageVo);
        PageInfo pageInfo = new PageInfo<>(newstypeList2);
        return new WebResult("200","查询成功", pageInfo);
    }
}
