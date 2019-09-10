package com.ovft.configure.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.PersonnelClassification;
import com.ovft.configure.sys.bean.Suggestions;
import com.ovft.configure.sys.dao.PersonnelClassificationMapper;
import com.ovft.configure.sys.service.PersonnelClassificationService;
import com.ovft.configure.sys.vo.PersonnelClassificationVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName
 * @Author xzy      人员分类
 * @Date 2019/4/10 15:45
 * @Version
 **/
@Service
public class PersonnelClassificationServiceImpl implements PersonnelClassificationService {
    private PersonnelClassificationMapper personnelClassificationMapper;
    @Transactional
    @Override
    public WebResult createPersonnelClass(PersonnelClassification personnelClassification) {
        personnelClassificationMapper.createPersonnelClass(personnelClassification);
        return new WebResult("200", "添加成功", "");
    }

    @Override
    public WebResult selectPersonnelClass(PersonnelClassificationVo personnelClassificationVo) {
        if (personnelClassificationVo.getPageSize() == 0) {
            List<PersonnelClassification> personnelClassifications = personnelClassificationMapper.selectPersonnelClass(personnelClassificationVo);
            return new WebResult("200", "查询成功", personnelClassifications);
        }
        PageHelper.startPage(personnelClassificationVo.getPageNum(), personnelClassificationVo.getPageSize());
        List<PersonnelClassification> personnelClassifications = personnelClassificationMapper.selectPersonnelClass(personnelClassificationVo);
        PageInfo pageInfo = new PageInfo<>(personnelClassifications);
        return new WebResult("200", "查询成功", pageInfo);
    }
    @Transactional
    @Override
    public WebResult updatePersonnelClass(PersonnelClassification personnelClassification) {
        personnelClassificationMapper.updatePersonnelClass(personnelClassification);
        return new WebResult("200", "修改成功", "");

    }
    @Transactional
    @Override
    public WebResult deletePersonnelClass(PersonnelClassification personnelClassification) {
        personnelClassificationMapper.deletePersonnelClass(personnelClassification);
        return new WebResult("200", "删除成功", "");
    }
}
