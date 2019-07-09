package com.ovft.configure.sys.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 志愿活动
 * @author zqx
 * @since 2019-07-04
 */
public class EduVolunteer implements Serializable {

    private static final long serialVersionUID = 1L;

	private Integer volunteerId;
	private Integer userId;
    /**
     * 标题
     */
	private String title;
    /**
     * 封面图片
     */
	private String image;
    /**
     * 活动地点
     */
	private String place;
    /**
     * 活动人数
     */
	private Integer number;
    /**
     * 获得红花数
     */
	private Integer flowers;
    /**
     * 详情
     */
	private String content;
    /**
     * 浏览量
     */
	private Integer visits;
    /**
     * 发布时间
     */
	private Date releaseDate;
    /**
     * 活动时间
     */
	private Date activityDate;

	private Integer isRegist;

	public Integer getIsRegist() {
		return isRegist;
	}

	public void setIsRegist(Integer isRegist) {
		this.isRegist = isRegist;
	}

	public Integer getVolunteerId() {
		return volunteerId;
	}

	public void setVolunteerId(Integer volunteerId) {
		this.volunteerId = volunteerId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getFlowers() {
		return flowers;
	}

	public void setFlowers(Integer flowers) {
		this.flowers = flowers;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getVisits() {
		return visits;
	}

	public void setVisits(Integer visits) {
		this.visits = visits;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Date getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}

}
