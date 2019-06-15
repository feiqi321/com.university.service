package com.ovft.configure.sys.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 
 */
public class EduOrderSettlement implements Serializable {
    /**
     * 结算订单id
     */
    private Integer id;

    /**
     * 结算显示id
     */
    private Integer settlementShowId;

    /**
     * 结算状态  1 2 3
     */
    private Integer settlementStatus;

    /**
     * 结算时间
     */
    private Date settleTime;

    /**
     * 结算订单数
     */
    private String settleNum;

    /**
     * 结算金额
     */
    private BigDecimal settleMoney;

    /**
     * 学校id
     */
    private Integer schoolId;

    /**
     * 学校名称
     */
    private String schoolName;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSettlementShowId() {
        return settlementShowId;
    }

    public void setSettlementShowId(Integer settlementShowId) {
        this.settlementShowId = settlementShowId;
    }

    public Integer getSettlementStatus() {
        return settlementStatus;
    }

    public void setSettlementStatus(Integer settlementStatus) {
        this.settlementStatus = settlementStatus;
    }

    public Date getSettleTime() {
        return settleTime;
    }

    public void setSettleTime(Date settleTime) {
        this.settleTime = settleTime;
    }

    public String getSettleNum() {
        return settleNum;
    }

    public void setSettleNum(String settleNum) {
        this.settleNum = settleNum;
    }

    public BigDecimal getSettleMoney() {
        return settleMoney;
    }

    public void setSettleMoney(BigDecimal settleMoney) {
        this.settleMoney = settleMoney;
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
        EduOrderSettlement other = (EduOrderSettlement) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSettlementShowId() == null ? other.getSettlementShowId() == null : this.getSettlementShowId().equals(other.getSettlementShowId()))
            && (this.getSettlementStatus() == null ? other.getSettlementStatus() == null : this.getSettlementStatus().equals(other.getSettlementStatus()))
            && (this.getSettleTime() == null ? other.getSettleTime() == null : this.getSettleTime().equals(other.getSettleTime()))
            && (this.getSettleNum() == null ? other.getSettleNum() == null : this.getSettleNum().equals(other.getSettleNum()))
            && (this.getSettleMoney() == null ? other.getSettleMoney() == null : this.getSettleMoney().equals(other.getSettleMoney()))
            && (this.getSchoolId() == null ? other.getSchoolId() == null : this.getSchoolId().equals(other.getSchoolId()))
            && (this.getSchoolName() == null ? other.getSchoolName() == null : this.getSchoolName().equals(other.getSchoolName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSettlementShowId() == null) ? 0 : getSettlementShowId().hashCode());
        result = prime * result + ((getSettlementStatus() == null) ? 0 : getSettlementStatus().hashCode());
        result = prime * result + ((getSettleTime() == null) ? 0 : getSettleTime().hashCode());
        result = prime * result + ((getSettleNum() == null) ? 0 : getSettleNum().hashCode());
        result = prime * result + ((getSettleMoney() == null) ? 0 : getSettleMoney().hashCode());
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
        sb.append(", settlementShowId=").append(settlementShowId);
        sb.append(", settlementStatus=").append(settlementStatus);
        sb.append(", settleTime=").append(settleTime);
        sb.append(", settleNum=").append(settleNum);
        sb.append(", settleMoney=").append(settleMoney);
        sb.append(", schoolId=").append(schoolId);
        sb.append(", schoolName=").append(schoolName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}