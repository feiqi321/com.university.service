package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.Vacate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* @Description:    请假
* @Author:         ZQX
* @CreateDate:     2019/4/15 11:02
* @Version:        1.0
*/
@Mapper
public interface VacateMapper {

    public List<Map<String,Object>> selectByUserId(@Param("userId") Integer userId);

    public void applyVacate(Vacate vacate);

    public void updateCheck(@Param("vacateId") Integer vacateId, @Param("isCheck") Integer isCheck);

}
