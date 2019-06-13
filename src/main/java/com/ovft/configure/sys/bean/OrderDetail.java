package com.ovft.configure.sys.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 
 */
public class OrderDetail implements Serializable {
    private Long id;

    /**
     * 订单编号
     */
    private Long orderId;

    /**
     * 课程ID
     */
    private Long courseId;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 课程图片
     */
    private String imgUrl;

    /**
     * 购买价格
     */
    private BigDecimal orderPrice;

    /**
     * 课程购买数量
     */
    private Integer num;

    /**
     * 教材作者
     */
    private String bookAuthor;

    /**
     * 学校id
     */
    private Integer schoolId;

    /**
     * 学校名称
     */
    private String schoolName;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
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
        OrderDetail other = (OrderDetail) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getCourseId() == null ? other.getCourseId() == null : this.getCourseId().equals(other.getCourseId()))
            && (this.getCourseName() == null ? other.getCourseName() == null : this.getCourseName().equals(other.getCourseName()))
            && (this.getImgUrl() == null ? other.getImgUrl() == null : this.getImgUrl().equals(other.getImgUrl()))
            && (this.getOrderPrice() == null ? other.getOrderPrice() == null : this.getOrderPrice().equals(other.getOrderPrice()))
            && (this.getNum() == null ? other.getNum() == null : this.getNum().equals(other.getNum()))
            && (this.getBookAuthor() == null ? other.getBookAuthor() == null : this.getBookAuthor().equals(other.getBookAuthor()))
            && (this.getSchoolId() == null ? other.getSchoolId() == null : this.getSchoolId().equals(other.getSchoolId()))
            && (this.getSchoolName() == null ? other.getSchoolName() == null : this.getSchoolName().equals(other.getSchoolName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getCourseId() == null) ? 0 : getCourseId().hashCode());
        result = prime * result + ((getCourseName() == null) ? 0 : getCourseName().hashCode());
        result = prime * result + ((getImgUrl() == null) ? 0 : getImgUrl().hashCode());
        result = prime * result + ((getOrderPrice() == null) ? 0 : getOrderPrice().hashCode());
        result = prime * result + ((getNum() == null) ? 0 : getNum().hashCode());
        result = prime * result + ((getBookAuthor() == null) ? 0 : getBookAuthor().hashCode());
        result = prime * result + ((getSchoolId() == null) ? 0 : getSchoolId().hashCode());
        result = prime * result + ((getSchoolName() == null) ? 0 : getSchoolName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderId=").append(orderId);
        sb.append(", courseId=").append(courseId);
        sb.append(", courseName=").append(courseName);
        sb.append(", imgUrl=").append(imgUrl);
        sb.append(", orderPrice=").append(orderPrice);
        sb.append(", num=").append(num);
        sb.append(", bookAuthor=").append(bookAuthor);
        sb.append(", schoolId=").append(schoolId);
        sb.append(", schoolName=").append(schoolName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}