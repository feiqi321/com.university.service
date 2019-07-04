package com.ovft.configure.sys.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zqx
 * @since 2019-07-04
 */
public class EduFlowers implements Serializable {

    private static final long serialVersionUID = 1L;

	private Integer flowerId;
	private Integer userId;
    /**
     * 来源标题
     */
	private String title;
    /**
     * 来源 1-签到  2-志愿活动
     */
	private Integer type;
    /**
     * 来源id
     */
	private Integer typeId;
    /**
     * 获得数量
     */
	private Integer acquire;
    /**
     * 红花总数
     */
	private Integer totalCount;

	private Date time;

	public EduFlowers() {

	}

	public EduFlowers(Integer userId, String title, Integer type, Integer typeId, Integer acquire, Integer totalCount, Date time) {
		this.userId = userId;
		this.title = title;
		this.type = type;
		this.typeId = typeId;
		this.acquire = acquire;
		this.totalCount = totalCount;
		this.time = time;
	}

	public Integer getFlowerId() {
		return flowerId;
	}

	public void setFlowerId(Integer flowerId) {
		this.flowerId = flowerId;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getAcquire() {
		return acquire;
	}

	public void setAcquire(Integer acquire) {
		this.acquire = acquire;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
