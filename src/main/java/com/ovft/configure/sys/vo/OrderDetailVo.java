package com.ovft.configure.sys.vo;


import com.ovft.configure.sys.bean.OrderDetail;

import java.math.BigDecimal;

/**
 * @author vvtxw
 * @create 2019-06-10 12:25
 */


public class OrderDetailVo extends OrderDetail {
    /**
     * 商品作者
     */
    private String booksAuthor;


    /**
     * 商品名称
     */
    private String booksName;

    /**
     * 商品上传原始图
     */
    private String originalImg;

    /**
     * 本店价
     */
    private BigDecimal shopPrice;

    private Integer goodsId;

    public String getBooksAuthor() {
        return booksAuthor;
    }

    public void setBooksAuthor(String booksAuthor) {
        this.booksAuthor = booksAuthor;
    }

    public String getBooksName() {
        return booksName;
    }

    public void setBooksName(String booksName) {
        this.booksName = booksName;
    }

    public String getOriginalImg() {
        return originalImg;
    }

    public void setOriginalImg(String originalImg) {
        this.originalImg = originalImg;
    }

    public BigDecimal getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(BigDecimal shopPrice) {
        this.shopPrice = shopPrice;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }
}
