package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.Dict;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName DictMapper
 * @Author zqx
 * @Date 2019/4/10 16:04
 * @Version 1.0
 **/
@Mapper
public interface DictMapper {

    public Dict selectById(@Param("dictId") Integer dictId);

    public List<Dict> selectByDictType(@Param("dictType") String dictType);
}
