package com.ovft.configure.sys.dao;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.vo.EduArticleVo;
import com.ovft.configure.sys.vo.PageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

   @Mapper
public interface WorksShowMapper {
    public List<EduArticleVo> findUserShowAll(@Param("id")Integer id, @Param("schoolId")Integer schoolId, @Param("type")String type, @Param("search") String search);
}
