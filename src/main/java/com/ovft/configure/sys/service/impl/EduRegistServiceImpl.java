package com.ovft.configure.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ovft.configure.sys.bean.EduRegist;
import com.ovft.configure.sys.dao.EduRegistMapper;
import com.ovft.configure.sys.service.EduRegistService;
import com.ovft.configure.sys.vo.PageBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.rmi.registry.Registry;
import java.util.List;

/**
 * @author vvtxw
 * @create 2019-04-29 10:03
 */
@Service
public class EduRegistServiceImpl implements EduRegistService {

    @Resource
    private EduRegistMapper eduRegistMapper;

    /**
     * 添加报名条件
     *
     * @param eduRegist
     * @return
     */
    @Transactional
    @Override
    public int addRegistCondition(EduRegist eduRegist) {

        return eduRegistMapper.insert(eduRegist);
    }

    /**
     * 分页查询条件
     *
     * @param size
     * @param page
     * @return
     */
    @Override
    public PageBean queryAllCodition(Integer size, Integer page) {
        Page<Object> pageAll = PageHelper.startPage(page, size);
        List<EduRegist> eduRegists = eduRegistMapper.selectByExample(null);
        long total = pageAll.getTotal();
        return new PageBean(page, size, total, eduRegists);
    }

    @Override
    public int updateCodition(EduRegist eduRegist) {

        return eduRegistMapper.updateByPrimaryKeySelective(eduRegist);
    }

    @Override
    public int deleteCodition(EduRegist eduRegist) {
        return eduRegistMapper.deleteByPrimaryKey(eduRegist.getId());
    }

    @Override
    public EduRegist queryById(Integer id) {
        EduRegist eduRegist = eduRegistMapper.selectByOne(id);
        System.out.println(eduRegist);
        return eduRegist;
    }
}
