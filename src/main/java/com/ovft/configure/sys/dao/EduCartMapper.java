package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.EduCart;
import com.ovft.configure.sys.bean.EduCartExample;

import java.util.List;
import java.util.Map;

import com.ovft.configure.sys.vo.EduCartVo;
import org.apache.ibatis.annotations.Param;

public interface EduCartMapper {
    long countByExample(EduCartExample example);

    int deleteByExample(EduCartExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EduCart record);

    int insertSelective(EduCart record);

    List<EduCart> selectByExample(EduCartExample example);

    EduCart selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EduCart record, @Param("example") EduCartExample example);

    int updateByExample(@Param("record") EduCart record, @Param("example") EduCartExample example);

    int updateByPrimaryKeySelective(EduCart record);

    int updateByPrimaryKey(EduCart record);

    /**
     * 展示购物车
     *
     * @param userId
     * @return
     */
    List<Object> selectCartByUserId(Integer userId);

    //统计该商品的数量
    Integer countNum(Map<String, Object> map);
}