package com.ovft.configure.sys.bean;

/**
 * @ClassName School   学校
 * @Author zqx
 * @Date 2019/4/10 11:52
 * @Version 1.0
 **/
public class School {

    private Integer schoolId;
    private String schoolName;

    /**
     * 管理员id
     */
    private Integer adminId;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     *学校头像
     */
    private String image;

    /**
     *字母标识
     */
    private String schoolChar;

    private Integer isUsing;

    /**
     * 轮播图
     */
    private String slideshow;

    public String getSlideshow() {
        return slideshow;
    }

    public void setSlideshow(String slideshow) {
        this.slideshow = slideshow;
    }

    public Integer getIsUsing() {
        return isUsing;
    }

    public void setIsUsing(Integer isUsing) {
        this.isUsing = isUsing;
    }

    @Override
    public String toString() {
        return "School{" +
                "schoolId=" + schoolId +
                ", schoolName='" + schoolName + '\'' +
                ", adminId=" + adminId +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", image='" + image + '\'' +
                ", schoolChar='" + schoolChar + '\'' +
                '}';
    }

    public String getSchoolChar() {
        return schoolChar;
    }

    public void setSchoolChar(String schoolChar) {

        this.schoolChar = schoolChar;
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

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
