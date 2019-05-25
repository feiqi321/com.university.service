package com.ovft.configure.sys.vo;

import com.ovft.configure.sys.bean.EduBooksInfo;

import java.math.BigDecimal;

/**
 * @author vvtxw
 * @create 2019-05-17 11:49
 */
public class EduBooksInfoVo extends EduBooksInfo {
    /**
     * 商品名称
     */
    private String booksName;
    /**
     * 商品作者
     */
    private String booksAuthor;

    /**
     * 库存数量
     */
    private Short storeCount;

    /**
     * 商品上传原始图
     */
    private String originalImg;

    /**
     * 本店价
     */
    private BigDecimal shopPrice;

    private long countNum;

    public long getCountNum() {
        return countNum;
    }

    public void setCountNum(long countNum) {
        this.countNum = countNum;
    }

    public String getOriginalImg() {
        return originalImg;
    }

    public void setOriginalImg(String originalImg) {
        this.originalImg = originalImg;
    }

    public String getBooksName() {
        return booksName;
    }

    public void setBooksName(String booksName) {
        this.booksName = booksName;
    }

    public String getBooksAuthor() {
        return booksAuthor;
    }

    public void setBooksAuthor(String booksAuthor) {
        this.booksAuthor = booksAuthor;
    }

    public Short getStoreCount() {
        return storeCount;
    }

    public void setStoreCount(Short storeCount) {
        this.storeCount = storeCount;
    }

    public BigDecimal getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(BigDecimal shopPrice) {
        this.shopPrice = shopPrice;
    }
}
