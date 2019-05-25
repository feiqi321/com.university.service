package com.ovft.configure.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ovft.configure.sys.bean.EduOfflineAddresstime;
import com.ovft.configure.sys.bean.EduOfflineAddresstimeExample;
import com.ovft.configure.sys.bean.EduRegist;
import com.ovft.configure.sys.dao.EduOfflineAddresstimeMapper;
import com.ovft.configure.sys.service.EduOfflineAddressService;
import com.ovft.configure.sys.service.EduOfflineOrderService;
import com.ovft.configure.sys.vo.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author vvtxw
 * @create 2019-05-23 16:10
 */
@Service
public class EduOfflineAddressServiceImpl implements EduOfflineAddressService {

    @Resource
    private EduOfflineAddresstimeMapper eduOfflineAddresstimeMapper;


    @Override
    public List<EduOfflineAddresstime> queryAddressByschoolId(Integer schoolId) {
        EduOfflineAddresstimeExample eduOfflineAddresstimeExample = new EduOfflineAddresstimeExample();
        eduOfflineAddresstimeExample.createCriteria().andSchoolIdEqualTo(schoolId);
        List<EduOfflineAddresstime> eduOfflineAddresstimes = eduOfflineAddresstimeMapper.selectByExample(eduOfflineAddresstimeExample);
        return eduOfflineAddresstimes;
    }

    @Override
    public Integer addAddressInfo(EduOfflineAddresstime eduOfflineAddresstime) {
        return eduOfflineAddresstimeMapper.insert(eduOfflineAddresstime);
    }

    @Override
    public Integer updateInfo(EduOfflineAddresstime eduOfflineAddresstime) {
        return eduOfflineAddresstimeMapper.updateByPrimaryKeySelective(eduOfflineAddresstime);
    }

    @Override
    public PageBean queryAllAddressInfoBySchoold(Integer size, Integer page, int schoolId) {
        Page<Object> pageAll = PageHelper.startPage(page, size);
        EduOfflineAddresstimeExample eduOfflineAddresstimeExample = new EduOfflineAddresstimeExample();
        eduOfflineAddresstimeExample.createCriteria().andSchoolIdEqualTo(schoolId);
        List<EduOfflineAddresstime> eduOfflineAddresstimes = eduOfflineAddresstimeMapper.selectByExample(eduOfflineAddresstimeExample);
        long total = pageAll.getTotal();
        return new PageBean(page, size, total, eduOfflineAddresstimes);
    }

    @Override
    public PageBean queryAllAddressInfo(Integer size, Integer page) {
        Page<Object> pageAll = PageHelper.startPage(page, size);
        List<EduOfflineAddresstime> eduOfflineAddresstimes = eduOfflineAddresstimeMapper.selectByExample(null);
        long total = pageAll.getTotal();
        return new PageBean(page, size, total, eduOfflineAddresstimes);
    }


    @Override

    public EduOfflineAddresstime queryById(Integer id) {
        EduOfflineAddresstime eduOfflineAddresstime = eduOfflineAddresstimeMapper.selectByPrimaryKey(id);
        return eduOfflineAddresstime;
    }


}
