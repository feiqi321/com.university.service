package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.Collect;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName CollectMapper
 * @Author zqx
 * @Version 1.0
 **/
@Mapper
public interface CollectMapper {

    public Collect selectById(@Param("collectId") Integer collectId);

    public List<Collect> selectList(@Param("uid") Integer uid, @Param("type") Integer type, @Param("typeId") Integer typeId);

    public void createCollect(Collect collect);

    public void updateCollect(Collect collect);

    public void deleteCollect(@Param("collectId") Integer collectId);

}
