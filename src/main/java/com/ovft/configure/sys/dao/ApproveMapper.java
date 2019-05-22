package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.Approve;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName ApproveMapper
 * @Author zqx
 * @Version 1.0
 **/
@Mapper
public interface ApproveMapper {

    public Approve selectById(@Param("approveId") Integer approveId);

    public Approve selectByType(@Param("userId") Integer userId, @Param("typeId") Integer typeId, @Param("type") Integer type);

    public void createApprove(Approve approve);

    public void updateApproveCount(Approve approve);

    public void deleteApprove(@Param("userId") Integer userId, @Param("typeId") Integer typeId, @Param("type") Integer type);
}
