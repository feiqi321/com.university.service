package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.EduRegist;
import com.ovft.configure.sys.bean.EduRegistExample;
import com.ovft.configure.sys.vo.CoditionVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EduRegistMapper {
    long countByExample(EduRegistExample example);

    int deleteByExample(EduRegistExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EduRegist record);

    int insertSelective(EduRegist record);

    List<EduRegist> selectByExample(EduRegistExample example);

    EduRegist selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EduRegist record, @Param("example") EduRegistExample example);

    int updateByExample(@Param("record") EduRegist record, @Param("example") EduRegistExample example);

    int updateByPrimaryKeySelective(EduRegist record);

    int updateByPrimaryKey(EduRegist record);
    //编辑课程时同步报名条件表课程名
    void updateByCourseId(EduRegist record);

    //通过课程id查询条件信息
    EduRegist selectByCourseId(Integer CouserId);

    //查询条件的
    EduRegist selectByOne(Integer id);

    //查找学校名称
    List<String> selectNameBySchoolId(Integer schoolId);

    //查询限制的课程门数
    int queryCourseNum(Map<String, Object> map);

    //查询全部
    public List<EduRegist> CourseCoditionAll(CoditionVo coditionVo);


}