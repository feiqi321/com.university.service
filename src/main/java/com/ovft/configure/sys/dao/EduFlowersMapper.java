package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.EduFlowers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author zqx
 * @since 2019-07-04
 */
@Mapper
public interface EduFlowersMapper {

    //获取学员的总的花朵数
    public Integer selectTotalFlower(@Param("userId") Integer userId);

    //获取当前学员的花朵列表
    public List<EduFlowers> selectListByUserId(@Param("userId") Integer userId);

    //添加花朵数
    public void createFlower(EduFlowers flowers);
}