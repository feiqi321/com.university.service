package com.ovft.configure.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ovft.configure.sys.bean.EduBookGoods;
import com.ovft.configure.sys.bean.EduBooksCategory;
import com.ovft.configure.sys.bean.EduBooksExtendcategory;
import com.ovft.configure.sys.dao.EduBookGoodsMapper;
import com.ovft.configure.sys.dao.EduBooksCategoryMapper;
import com.ovft.configure.sys.dao.EduBooksExtendcategoryMapper;
import com.ovft.configure.sys.dao.EduBooksInfoMapper;
import com.ovft.configure.sys.service.EduBookGoodsService;
import com.ovft.configure.sys.vo.PageBean;
import com.ovft.configure.sys.vo.QueryBookVos;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author vvtxw
 * @create 2019-05-16 12:05
 */
@Service
public class EduBookGoodsServiceImpl implements EduBookGoodsService {

    @Resource
    private EduBookGoodsMapper eduBookGoodsMapper;

    @Resource
    private EduBooksCategoryMapper eduBooksCategoryMapper;

    @Resource
    private EduBooksExtendcategoryMapper eduBooksExtendcategoryMapper;

    @Resource
    private EduBooksInfoMapper eduBooksInfoMapper;


    @Override
    public QueryBookVos queryAllBookesForPage(Integer catId, Integer extendCatId) {
        HashMap<String, Object> map = new HashMap<>();
        Page<Object> pageAll = PageHelper.startPage(1, 6);
        List<EduBookGoods> eduBookGoods = null;
        if (catId == null && extendCatId == null) {
            map.put("catId", 1);
            map.put("extendCatId", 1);
            eduBookGoods = eduBookGoodsMapper.queryAllBooksGoods(map);
        } else {
            map.put("catId", catId);
            map.put("extendCatId", extendCatId);

            eduBookGoods = eduBookGoodsMapper.queryAllBooksGoods(map);

        }

        long total = pageAll.getTotal();
        PageBean pageBean = new PageBean(total, eduBookGoods);
        List<EduBooksCategory> eduBooksCategories = eduBooksCategoryMapper.queryAllCategory();
        List<EduBooksExtendcategory> eduBooksExtendcategories = eduBooksExtendcategoryMapper.queryAllExtendcategory();


        QueryBookVos queryBookVos = new QueryBookVos();
        queryBookVos.setEduBookGoods(pageBean);
        queryBookVos.setBooksCategory(eduBooksCategories);
        queryBookVos.setExtendcategory(eduBooksExtendcategories);

        return queryBookVos;
    }

    @Override
    public PageBean queryAllBookesForlike(String searchkey) {
        Page<Object> pageAll = PageHelper.startPage(1, 6);
        Map<String, Object> map = new HashMap<>();
        map.put("searchkey", searchkey);
        List<EduBookGoods> eduBookGoods = eduBookGoodsMapper.queryAllBookesForlike(map);

        long total = pageAll.getTotal();
        return new PageBean(total, eduBookGoods);
    }


}
