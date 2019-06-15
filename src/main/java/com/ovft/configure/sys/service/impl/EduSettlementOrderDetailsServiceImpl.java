package com.ovft.configure.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ovft.configure.sys.dao.EduSettlementOrderDetailsMapper;
import com.ovft.configure.sys.service.EduSettlementOrderDetailsService;
import com.ovft.configure.sys.vo.EduSettlementOrderDetailsVo;
import com.ovft.configure.sys.vo.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author vvtxw
 * @create 2019-06-15 11:46
 */
@Service
public class EduSettlementOrderDetailsServiceImpl implements EduSettlementOrderDetailsService {

    @Resource
    private EduSettlementOrderDetailsMapper eduSettlementOrderDetailsMapper;


    @Override
    public PageBean showSettlementDetail(EduSettlementOrderDetailsVo eduSettlementOrderDetailsVo) {
            Page<Object> pageAll = PageHelper.startPage(eduSettlementOrderDetailsVo.getPageNum(), eduSettlementOrderDetailsVo.getPageSize());
        List<EduSettlementOrderDetailsVo> list = eduSettlementOrderDetailsMapper.showSettlementDetail(eduSettlementOrderDetailsVo);
        long total = pageAll.getTotal();
        return new PageBean(total, list);
    }
}
