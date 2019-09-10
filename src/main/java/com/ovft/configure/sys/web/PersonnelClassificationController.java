package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.PersonnelClassification;
import com.ovft.configure.sys.service.PersonnelClassificationService;
import com.ovft.configure.sys.vo.PersonnelClassificationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SchoolController
 * @Author xzy      人员分类
 * @Date 2019/4/10 15:45
 * @Version 1.0
 **/
@RestController
public class PersonnelClassificationController {
    @Autowired
    private PersonnelClassificationService personnelClassificationService;
    /**
     * 增加
     * @return
     */
    @PostMapping(value = "/server/createPersonnelClass")
    public WebResult createPersonnelClass(@RequestBody PersonnelClassification personnelClassification) {
        return  personnelClassificationService.createPersonnelClass(personnelClassification);
    }
    /**
     * 查询
     * @return
     */
    @PostMapping(value = "/server/selectPersonnelClass")
    public WebResult selectPersonnelClass(@RequestBody PersonnelClassificationVo personnelClassificationVo) {

        return  personnelClassificationService.selectPersonnelClass(personnelClassificationVo);
    }

    /**
     * 修改
     * @return
     */
    @PostMapping(value = "/server/updatePersonnelClass")
    public WebResult updatePersonnelClass(@RequestBody PersonnelClassification personnelClassification) {

        return  personnelClassificationService.updatePersonnelClass(personnelClassification);
    }
    /**
     * 删除
     * @return
     */
    @PostMapping(value = "/server/deletePersonnelClass")
    public WebResult deletePersonnelClass(@RequestBody PersonnelClassification personnelClassification) {

        return  personnelClassificationService.deletePersonnelClass(personnelClassification);
    }


}
