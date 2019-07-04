package com.ovft.configure.sys.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 签到
 * @author zqx
 * @since 2019-07-04
 */
public class EduSign implements Serializable {

    private static final long serialVersionUID = 1L;

	private Integer signId;
	private Integer userId;
    /**
     * 签到时间
     */
	private Date signTime;


	public Integer getSignId() {
		return signId;
	}

	public void setSignId(Integer signId) {
		this.signId = signId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getSignTime() {
		return signTime;
	}

	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}

}
