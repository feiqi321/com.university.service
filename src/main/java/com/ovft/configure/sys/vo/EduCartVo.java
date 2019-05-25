package com.ovft.configure.sys.vo;

import com.ovft.configure.sys.bean.EduCart;

import java.math.BigDecimal;

/**
 * @author vvtxw
 * @create 2019-05-17 15:49
 */
public class EduCartVo extends EduCart {

    /**
     * 商品作者
     */
    private String booksAuthor;

    /**
     * 商品名称
     */
    private String booksName;

    /**
     * 本店价
     */
    private BigDecimal shopPrice;
    /**
     * 商品上传原始图
     */
    private String originalImg;


    /**
     * 邮费
     */
    private BigDecimal sendPrice;

    /**
     * 学校ID名称
     */
    private String schoolId;
    /**
     * 学校
     */
    private String schoolName;


    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public BigDecimal getSendPrice() {
        return sendPrice;
    }

    public void setSendPrice(BigDecimal sendPrice) {
        this.sendPrice = sendPrice;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

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

    public BigDecimal getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(BigDecimal shopPrice) {
        this.shopPrice = shopPrice;
    }

    public String getOriginalImg() {
        return originalImg;
    }

    public void setOriginalImg(String originalImg) {
        this.originalImg = originalImg;
    }

}
