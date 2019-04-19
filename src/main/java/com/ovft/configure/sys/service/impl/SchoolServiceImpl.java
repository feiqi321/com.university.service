package com.ovft.configure.sys.service.impl;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.School;
import com.ovft.configure.sys.dao.SchoolMapper;
import com.ovft.configure.sys.service.SchoolService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

/**
 * @ClassName SchoolServiceImpl
 * @Author zqx
 * @Date 2019/4/11 10:15
 * @Version 1.0
 **/
@Service
public class SchoolServiceImpl implements SchoolService {

    @Resource
    public SchoolMapper schoolmapper;

    /**
     * 添加学校
     * @param school
     * @return
     */
    @Transactional
    @Override
    public WebResult createSchool(School school) {
        if(StringUtils.isBlank(school.getSchoolName())) {
            return new WebResult("400", "学校名称不能为空");
        }
        if(StringUtils.isBlank(school.getLongitude()) || StringUtils.isBlank(school.getLatitude())) {
            return new WebResult("400", "学校位置不能为空");
        }
        schoolmapper.createSchool(school);
        System.out.println("school.getSchoolId() = " + school.getSchoolId());
        return new WebResult("200", "添加成功", school);
    }

    @Transactional
    @Override
    public WebResult updateSchoolName(School school) {
        if(StringUtils.isBlank(school.getSchoolName())) {
            return new WebResult("400", "学校名称不能为空");
        }
        schoolmapper.updateSchoolName(school);
        return new WebResult("200", "修改成功", "");
    }

    /**
     * 根据学校id查询坐标
     * @param schoolId
     * @return
     */
    @Override
    public School queryRecordBySchoolId(Integer schoolId) {
        return schoolmapper.queryRecordBySchoolId(schoolId);
    }

    /**
     * 切换学校
     * @return
     */
    @Transactional
    @Override
    public WebResult switchSchool() {
        TreeMap<String, HashMap> map=new TreeMap();
        List<School> schoolList=schoolmapper.selectSchoolAll();

//        for (School school : schoolList) {
//            HashMap schoolMap = map.get(school.getSchoolChar());
//            if(schoolMap == null) {
//                schoolMap = new HashMap();
//            }
//            schoolMap.put(school.getSchoolId(), school);
//        }

        String[] alphatableb =
                {
                        "A", "B", "C", "D", "E", "F", "G", "H", "I",
                        "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"

                };
           for (int i=0;i<alphatableb.length;i++){
               HashMap value=new HashMap();
              for (int j=0;j<schoolList.size();j++){
                   School school=schoolList.get(j);

                     if (alphatableb[i].equals(school.getSchoolChar())){

                         value.put(school.getSchoolId(),school.getSchoolName());
                 map.put(alphatableb[i],value);
                     }
              }

           }

            WebResult webResult=new WebResult();
               webResult.setCode("200");
               webResult.setMsg("学校");
               webResult.setData(map);
               return webResult;
    }
    /**
     * 更换学校ID
     * @return
     */
    @Transactional
    @Override
    public WebResult switchSchoolID(Integer SchoolId,Integer userId){

        return null;
    }

}
