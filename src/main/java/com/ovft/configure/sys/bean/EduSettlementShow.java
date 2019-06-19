package com.ovft.configure.sys.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 
 */
public class EduSettlementShow implements Serializable {
    /**
     * 结算表id
     */
    private Integer id;

    /**
     * 未结算1
     */
    private BigDecimal noSettlement;

    /**
     * 正在结算2
     */
    private BigDecimal clearing;

    /**
     *  已结算3
     */
    private BigDecimal alreadySettled;

    /**
     * 学校id
     */
    private Integer schoolId;

    /**
     * 学校姓名
     */
    private String schoolName;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getNoSettlement() {
        return noSettlement;
    }

    public void setNoSettlement(BigDecimal noSettlement) {
        this.noSettlement = noSettlement;
    }

    public BigDecimal getClearing() {
        return clearing;
    }

    public void setClearing(BigDecimal clearing) {
        this.clearing = clearing;
    }

    public BigDecimal getAlreadySettled() {
        return alreadySettled;
    }

    public void setAlreadySettled(BigDecimal alreadySettled) {
        this.alreadySettled = alreadySettled;
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
        EduSettlementShow other = (EduSettlementShow) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getNoSettlement() == null ? other.getNoSettlement() == null : this.getNoSettlement().equals(other.getNoSettlement()))
            && (this.getClearing() == null ? other.getClearing() == null : this.getClearing().equals(other.getClearing()))
            && (this.getAlreadySettled() == null ? other.getAlreadySettled() == null : this.getAlreadySettled().equals(other.getAlreadySettled()))
            && (this.getSchoolId() == null ? other.getSchoolId() == null : this.getSchoolId().equals(other.getSchoolId()))
            && (this.getSchoolName() == null ? other.getSchoolName() == null : this.getSchoolName().equals(other.getSchoolName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getNoSettlement() == null) ? 0 : getNoSettlement().hashCode());
        result = prime * result + ((getClearing() == null) ? 0 : getClearing().hashCode());
        result = prime * result + ((getAlreadySettled() == null) ? 0 : getAlreadySettled().hashCode());
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
        sb.append(", noSettlement=").append(noSettlement);
        sb.append(", clearing=").append(clearing);
        sb.append(", alreadySettled=").append(alreadySettled);
        sb.append(", schoolId=").append(schoolId);
        sb.append(", schoolName=").append(schoolName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}