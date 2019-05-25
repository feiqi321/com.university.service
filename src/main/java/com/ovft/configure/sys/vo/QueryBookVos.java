package com.ovft.configure.sys.vo;

import com.ovft.configure.sys.bean.EduBooksCategory;
import com.ovft.configure.sys.bean.EduBooksExtendcategory;

import java.util.List;

/**
 * @author vvtxw
 * @create 2019-05-16 11:44
 */
public class QueryBookVos {
    private PageBean eduBookGoods;

    private List<EduBooksCategory> booksCategory;

    private List<EduBooksExtendcategory> extendcategory;


    public PageBean getEduBookGoods() {
        return eduBookGoods;
    }

    public void setEduBookGoods(PageBean eduBookGoods) {
        this.eduBookGoods = eduBookGoods;
    }

    public List<EduBooksExtendcategory> getExtendcategory() {
        return extendcategory;
    }

    public void setExtendcategory(List<EduBooksExtendcategory> extendcategory) {
        this.extendcategory = extendcategory;
    }

    public List<EduBooksCategory> getBooksCategory() {
        return booksCategory;
    }

    public void setBooksCategory(List<EduBooksCategory> booksCategory) {
        this.booksCategory = booksCategory;
    }
}
