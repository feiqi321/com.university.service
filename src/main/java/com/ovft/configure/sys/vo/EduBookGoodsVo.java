package com.ovft.configure.sys.vo;

import com.ovft.configure.sys.bean.EduBookGoods;

/**
 * @author vvtxw
 * @create 2019-07-10 9:44
 */
public class EduBookGoodsVo extends EduBookGoods {

    private Integer infoId;
    /**
     * 教材id
     */
    private Integer bookGoodsId;

    /**
     * 出版社
     */
    private String publishAddress;

    /**
     * 开本
     */
    private String formatBook;

    /**
     * 版本
     */
    private String editionBook;

    /**
     * 页数
     */
    private Double pageSize;

    /**
     * 推荐单位
     */
    private String recommendUnit;

    /**
     * 标准书号
     */
    private String bookSn;

    /**
     * 讲义描述
     */
    private String describeNote;

    /**
     * 教材描述
     */
    private String describeBook;

    /**
     * 作者介绍
     */
    private String introductionAuthor;

    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    public Integer getBookGoodsId() {
        return bookGoodsId;
    }

    public void setBookGoodsId(Integer bookGoodsId) {
        this.bookGoodsId = bookGoodsId;
    }

    public String getPublishAddress() {
        return publishAddress;
    }

    public void setPublishAddress(String publishAddress) {
        this.publishAddress = publishAddress;
    }

    public String getFormatBook() {
        return formatBook;
    }

    public void setFormatBook(String formatBook) {
        this.formatBook = formatBook;
    }

    public String getEditionBook() {
        return editionBook;
    }

    public void setEditionBook(String editionBook) {
        this.editionBook = editionBook;
    }

    public Double getPageSize() {
        return pageSize;
    }

    public void setPageSize(Double pageSize) {
        this.pageSize = pageSize;
    }

    public String getRecommendUnit() {
        return recommendUnit;
    }

    public void setRecommendUnit(String recommendUnit) {
        this.recommendUnit = recommendUnit;
    }

    public String getBookSn() {
        return bookSn;
    }

    public void setBookSn(String bookSn) {
        this.bookSn = bookSn;
    }

    public String getDescribeNote() {
        return describeNote;
    }

    public void setDescribeNote(String describeNote) {
        this.describeNote = describeNote;
    }

    public String getDescribeBook() {
        return describeBook;
    }

    public void setDescribeBook(String describeBook) {
        this.describeBook = describeBook;
    }

    public String getIntroductionAuthor() {
        return introductionAuthor;
    }

    public void setIntroductionAuthor(String introductionAuthor) {
        this.introductionAuthor = introductionAuthor;
    }

}
