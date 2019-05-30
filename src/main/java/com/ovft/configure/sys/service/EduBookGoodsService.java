package com.ovft.configure.sys.service;

import com.ovft.configure.sys.bean.EduBookGoods;
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

    //后台添加书籍教材
    Integer addBooks(EduBookGoods eduBookGoods);

    //查询书籍教材
    EduBookGoods queryById(Integer id);

    //更新书籍信息
    int updateBook(EduBookGoods eduBookGoods);

    //删除书籍教材
    int deleteBook(EduBookGoods eduBookGoods);

    //分页展示教材
    PageBean showPageBooks(Integer page, Integer size, String schoolId);
}
