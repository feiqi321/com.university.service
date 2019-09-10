package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.PersonnelClassification;
import com.ovft.configure.sys.vo.PersonnelClassificationVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface PersonnelClassificationMapper {

    public void createPersonnelClass(PersonnelClassification personnelClassification);

    public List<PersonnelClassification> selectPersonnelClass(PersonnelClassificationVo personnelClassificationVo);

    public void updatePersonnelClass(PersonnelClassification personnelClassification);

    public void deletePersonnelClass(PersonnelClassification personnelClassification);
}
