package com.ovft.configure.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ovft.configure.sys.bean.EduBooksInfo;
import com.ovft.configure.sys.bean.EduBooksInfoExample;
import com.ovft.configure.sys.dao.EduBooksInfoMapper;
import com.ovft.configure.sys.service.EduBooksInfoService;
import com.ovft.configure.sys.vo.EduBooksInfoVo;
import com.ovft.configure.sys.vo.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author vvtxw
 * @create 2019-05-17 11:42
 */
@Service
public class EduBooksInfoServiceImpl implements EduBooksInfoService {

    @Resource
    private EduBooksInfoMapper eduBooksInfoMapper;

    @Override
    public EduBooksInfoVo selectBookById(Integer id) {
        return eduBooksInfoMapper.selectBookById(id);
    }

    @Override
    public Integer addBookInfo(EduBooksInfo eduBooksInfo) {
        return eduBooksInfoMapper.insertSelective(eduBooksInfo);
    }

    @Override
    public Integer deleteBookInfo(EduBooksInfo eduBooksInfo) {
        return eduBooksInfoMapper.deleteByPrimaryKey(eduBooksInfo.getId());
    }

    @Override
    public Integer updateBookInfo(EduBooksInfo eduBooksInfo) {
        return eduBooksInfoMapper.updateByPrimaryKeySelective(eduBooksInfo);
    }

    @Override
    public EduBooksInfo selectoneBookInfo(Integer id) {
        return eduBooksInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageBean showPageBookInfo(Integer page, Integer size, String schoolId, Integer bookGoodsId) {
        Page<Object> pageAll = PageHelper.startPage(page, size);
        Map<String, Object> map = new HashMap<>();
        map.put("schoolId", schoolId);
        map.put("bookGoodsId", bookGoodsId);
        List<EduBooksInfo> eduBooksInfos = eduBooksInfoMapper.queryForPage(map);
        long total = pageAll.getTotal();
        return new PageBean(page, size, total, eduBooksInfos);
    }

    @Override
    public List<String> queryBookNameById(Integer schoolId) {
        //查询出教材全部信息
        EduBooksInfoExample eduBooksInfoExample = new EduBooksInfoExample();
        eduBooksInfoExample.createCriteria().andSchoolIdEqualTo(schoolId);
        List<EduBooksInfo> eduBooksInfos = eduBooksInfoMapper.selectByExample(eduBooksInfoExample);
        List<String> bookNames = new ArrayList<>();
        for (EduBooksInfo eduBooksInfo : eduBooksInfos) {
            Map<String, Object> map = new HashMap<>();
            map.put("schoolId", schoolId);
            map.put("bookGoodsId", eduBooksInfo.getBookGoodsId());
            String name = eduBooksInfoMapper.queryBookNameById(map);
            bookNames.add(name);
        }
        return bookNames;
    }

    @Override
    public int deleteBookInfos(Integer id) {
        EduBooksInfoExample eduBooksInfoExample = new EduBooksInfoExample();
        eduBooksInfoExample.createCriteria().andBookGoodsIdEqualTo(id);
        int i = eduBooksInfoMapper.deleteByExample(eduBooksInfoExample);
        return i;
    }

    @Override
    public EduBooksInfo selectoneByGoodsId(Integer id) {
        EduBooksInfoExample eduBooksInfoExample = new EduBooksInfoExample();
        eduBooksInfoExample.createCriteria().andBookGoodsIdEqualTo(id);
        List<EduBooksInfo> eduBooksInfos = eduBooksInfoMapper.selectByExample(eduBooksInfoExample);
        for (EduBooksInfo eduBooksInfo : eduBooksInfos) {
            return eduBooksInfo;
        }
        return null;

    }
}
