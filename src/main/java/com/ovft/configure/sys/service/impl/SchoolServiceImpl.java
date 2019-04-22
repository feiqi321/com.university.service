package com.ovft.configure.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.School;
import com.ovft.configure.sys.dao.SchoolMapper;
import com.ovft.configure.sys.service.SchoolService;
import com.ovft.configure.sys.utils.PinyinUtil;
import com.ovft.configure.sys.vo.PageVo;
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
            return new WebResult("400", "学校名称不能为空", "");
        }
        if(StringUtils.isBlank(school.getLongitude()) || StringUtils.isBlank(school.getLatitude())) {
            return new WebResult("400", "学校位置不能为空", "");
        }
        String pinYinHeadChar = PinyinUtil.getPinYinHeadChar(school.getSchoolName());
        school.setSchoolChar(pinYinHeadChar.substring(0,1).toUpperCase());
        //默认启用学校
        school.setIsUsing(1);
        schoolmapper.createSchool(school);
        return new WebResult("200", "添加成功", school);
    }

    /**
     * 根据学校id获取学校
     * @param schoolId
     * @return
     */
    @Override
    public WebResult findSchool(Integer schoolId) {
        School school = schoolmapper.selectById(schoolId);
        return new WebResult("200", "查询成功", school);
    }

    /**
     * 修改学校
     * @param school
     * @return
     */
    @Transactional
    @Override
    public WebResult updateSchool(School school) {
        if(StringUtils.isBlank(school.getSchoolName())) {
            return new WebResult("400", "学校名称不能为空", "");
        }
        if(StringUtils.isBlank(school.getLongitude()) || StringUtils.isBlank(school.getLatitude())) {
            return new WebResult("400", "学校位置不能为空", "");
        }
        String pinYinHeadChar = PinyinUtil.getPinYinHeadChar(school.getSchoolName());
        school.setSchoolChar(pinYinHeadChar.substring(0,1).toUpperCase());

        schoolmapper.updateSchool(school);
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

    /**
     * 学校列表
     * @param adminId
     * @param pageVo
     * @return
     */
    @Override
    public WebResult schoolList(Integer adminId, PageVo pageVo) {
        PageHelper.startPage(pageVo.getPageNum(), pageVo.getPageSize(), "school_id");
        //todo  测试
        adminId = 1;

        List<School> schoolList = schoolmapper.selectSchoolByAdminId(adminId, pageVo.getSearch());
        PageInfo pageInfo = new PageInfo<>(schoolList);
        return new WebResult("200", "获取学校列表成功", pageInfo);
    }

    /**
     * 删除学校
     * @param schoolId
     * @return
     */
    @Transactional
    @Override
    public WebResult deleteSchool(Integer schoolId) {
        //停用
        schoolmapper.isUsingSchool(schoolId, 0);
        return new WebResult("200", "删除成功", "");
    }

}
