package com.ovft.configure.sys.bean;
/**
* @Description:    Dict   字典数据表
* @Author:         ZQX
* @CreateDate:     2019/4/15 12:11
* @Version:        1.0
*/
public class Dict {
    private Integer dictId;
    private String dictType;
    private Integer dictValue;
    private String dictName;

    public Integer getDictId() {
        return dictId;
    }

    public void setDictId(Integer dictId) {
        this.dictId = dictId;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public Integer getDictValue() {
        return dictValue;
    }

    public void setDictValue(Integer dictValue) {
        this.dictValue = dictValue;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }
}
