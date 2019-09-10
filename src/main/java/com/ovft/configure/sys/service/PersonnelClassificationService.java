package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.PersonnelClassification;
import com.ovft.configure.sys.vo.PersonnelClassificationVo;

/**
 * @ClassName
 * @Author xzy      人员分类
 * @Date 2019/4/10 15:45
 * @Version
 **/
public interface PersonnelClassificationService {

    public WebResult createPersonnelClass(PersonnelClassification personnelClassification);

    public WebResult selectPersonnelClass(PersonnelClassificationVo personnelClassificationVo);

    public WebResult updatePersonnelClass(PersonnelClassification personnelClassification);

    public WebResult deletePersonnelClass(PersonnelClassification personnelClassification);

}
