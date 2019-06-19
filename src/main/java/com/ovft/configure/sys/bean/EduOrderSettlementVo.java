package com.ovft.configure.sys.bean;

import com.ovft.configure.sys.vo.PageBean;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author vvtxw
 * @create 2019-06-15 10:22
 */
public class EduOrderSettlementVo {
    private List<EduSettlementShow> eduSettlementShows;

    private Integer[] ids;

    private PageBean eduOrderSettlements;


    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    public PageBean getEduOrderSettlements() {
        return eduOrderSettlements;
    }

    public void setEduOrderSettlements(PageBean eduOrderSettlements) {
        this.eduOrderSettlements = eduOrderSettlements;
    }

    public List<EduSettlementShow> getEduSettlementShows() {
        return eduSettlementShows;
    }

    public void setEduSettlementShows(List<EduSettlementShow> eduSettlementShows) {
        this.eduSettlementShows = eduSettlementShows;
    }
}
