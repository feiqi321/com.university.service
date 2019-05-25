package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.EduBookGoods;
import com.ovft.configure.sys.bean.EduBookGoodsExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface EduBookGoodsMapper {
    long countByExample(EduBookGoodsExample example);

    int deleteByExample(EduBookGoodsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EduBookGoods record);

    int insertSelective(EduBookGoods record);

    List<EduBookGoods> selectByExampleWithBLOBs(EduBookGoodsExample example);

    List<EduBookGoods> selectByExample(EduBookGoodsExample example);

    EduBookGoods selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EduBookGoods record, @Param("example") EduBookGoodsExample example);

    int updateByExampleWithBLOBs(@Param("record") EduBookGoods record, @Param("example") EduBookGoodsExample example);

    int updateByExample(@Param("record") EduBookGoods record, @Param("example") EduBookGoodsExample example);

    int updateByPrimaryKeySelective(EduBookGoods record);

    int updateByPrimaryKeyWithBLOBs(EduBookGoods record);

    int updateByPrimaryKey(EduBookGoods record);


    //查询所有的默认的学员分类
    List<EduBookGoods> queryAllBooksGoods(Map<String, Object> map);

    //查询所有的默认搜索name
    List<EduBookGoods> queryAllBookesForlike(Map<String, Object> map);
}