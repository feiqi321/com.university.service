package com.ovft.configure.sys.service;

import com.ovft.configure.sys.vo.PageBean;
import com.ovft.configure.sys.vo.QueryBookVos;

/**
 * @author vvtxw
 * @create 2019-05-16 11:57
 */
public interface EduBookGoodsService {
    //查询所有教材
    QueryBookVos queryAllBookesForPage(Integer catId, Integer extendCatId);

    //搜索分页展示
    PageBean queryAllBookesForlike(String searchkey);

}
