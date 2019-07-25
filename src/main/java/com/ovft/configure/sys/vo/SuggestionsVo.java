package com.ovft.configure.sys.vo;

public class SuggestionsVo {
        private int pageSize;
        private int pageNum;
        private Integer schoolId;
        private Integer id;
        private Integer typeId;
        private String userName;
        private String typeName;
        private String search;

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getUserName() {
        return userName;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getSearch() {
        return search;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public void setPageSize(int pageSize) {
        if (pageSize <= 0) {
            pageSize = 10;
        }
        this.pageSize = pageSize;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }
}
