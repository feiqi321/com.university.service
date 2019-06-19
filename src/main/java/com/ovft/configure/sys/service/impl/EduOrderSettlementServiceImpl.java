package com.ovft.configure.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ovft.configure.sys.bean.EduOrderSettlement;
import com.ovft.configure.sys.dao.EduOrderSettlementMapper;
import com.ovft.configure.sys.dao.EduSettlementShowMapper;
import com.ovft.configure.sys.service.EduOrderSettlementService;
import com.ovft.configure.sys.vo.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author vvtxw
 * @create 2019-06-15 10:25
 */
@Service
public class EduOrderSettlementServiceImpl implements EduOrderSettlementService {

    @Resource
    private EduOrderSettlementMapper eduOrderSettlementMapper;

    @Resource
    private EduSettlementShowMapper eduSettlementShowMapper;

    @Override
    public PageBean showsSettlement(Integer page, Integer size, Integer schoolId, Integer settlementStatus) {
        Page<Object> pageAll = PageHelper.startPage(page, size);
        Map<String, Object> map = new HashMap<>();
        map.put("schoolId", schoolId);
        map.put("settlementStatus", settlementStatus);
        List<EduOrderSettlement> eduOrderSettlements = eduOrderSettlementMapper.queryAllSettlementStatus(map);
        long total = pageAll.getTotal();
        return new PageBean(page, size, total, eduOrderSettlements);
    }
}
