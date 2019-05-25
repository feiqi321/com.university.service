package com.ovft.configure.sys.bean;

import java.io.Serializable;

/**
 * @author 
 */
public class EduBooksInfo implements Serializable {
    /**
     * 详情id
     */
    private Integer id;

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

    /**
     * 运费
     */
    private String freightPirce;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getFreightPirce() {
        return freightPirce;
    }

    public void setFreightPirce(String freightPirce) {
        this.freightPirce = freightPirce;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        EduBooksInfo other = (EduBooksInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBookGoodsId() == null ? other.getBookGoodsId() == null : this.getBookGoodsId().equals(other.getBookGoodsId()))
            && (this.getPublishAddress() == null ? other.getPublishAddress() == null : this.getPublishAddress().equals(other.getPublishAddress()))
            && (this.getFormatBook() == null ? other.getFormatBook() == null : this.getFormatBook().equals(other.getFormatBook()))
            && (this.getEditionBook() == null ? other.getEditionBook() == null : this.getEditionBook().equals(other.getEditionBook()))
            && (this.getPageSize() == null ? other.getPageSize() == null : this.getPageSize().equals(other.getPageSize()))
            && (this.getRecommendUnit() == null ? other.getRecommendUnit() == null : this.getRecommendUnit().equals(other.getRecommendUnit()))
            && (this.getBookSn() == null ? other.getBookSn() == null : this.getBookSn().equals(other.getBookSn()))
            && (this.getDescribeNote() == null ? other.getDescribeNote() == null : this.getDescribeNote().equals(other.getDescribeNote()))
            && (this.getDescribeBook() == null ? other.getDescribeBook() == null : this.getDescribeBook().equals(other.getDescribeBook()))
            && (this.getIntroductionAuthor() == null ? other.getIntroductionAuthor() == null : this.getIntroductionAuthor().equals(other.getIntroductionAuthor()))
            && (this.getFreightPirce() == null ? other.getFreightPirce() == null : this.getFreightPirce().equals(other.getFreightPirce()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBookGoodsId() == null) ? 0 : getBookGoodsId().hashCode());
        result = prime * result + ((getPublishAddress() == null) ? 0 : getPublishAddress().hashCode());
        result = prime * result + ((getFormatBook() == null) ? 0 : getFormatBook().hashCode());
        result = prime * result + ((getEditionBook() == null) ? 0 : getEditionBook().hashCode());
        result = prime * result + ((getPageSize() == null) ? 0 : getPageSize().hashCode());
        result = prime * result + ((getRecommendUnit() == null) ? 0 : getRecommendUnit().hashCode());
        result = prime * result + ((getBookSn() == null) ? 0 : getBookSn().hashCode());
        result = prime * result + ((getDescribeNote() == null) ? 0 : getDescribeNote().hashCode());
        result = prime * result + ((getDescribeBook() == null) ? 0 : getDescribeBook().hashCode());
        result = prime * result + ((getIntroductionAuthor() == null) ? 0 : getIntroductionAuthor().hashCode());
        result = prime * result + ((getFreightPirce() == null) ? 0 : getFreightPirce().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", bookGoodsId=").append(bookGoodsId);
        sb.append(", publishAddress=").append(publishAddress);
        sb.append(", formatBook=").append(formatBook);
        sb.append(", editionBook=").append(editionBook);
        sb.append(", pageSize=").append(pageSize);
        sb.append(", recommendUnit=").append(recommendUnit);
        sb.append(", bookSn=").append(bookSn);
        sb.append(", describeNote=").append(describeNote);
        sb.append(", describeBook=").append(describeBook);
        sb.append(", introductionAuthor=").append(introductionAuthor);
        sb.append(", freightPirce=").append(freightPirce);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}