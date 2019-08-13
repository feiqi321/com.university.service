package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * @ClassName AddressMapper
 * @Author zqx
 * @Version 1.0
 **/
@Mapper
public interface AddressMapper {

    public Address selectByAddressId(@Param("addressId") Integer addressId);

    public List<Address> selectByUserIdMap(@Param("userId") Integer userId);

    public List<Address> selectByUserId(@Param("userId") Integer userId);

    public void createAddress(Address address);

    public void updateAddress(Address address);

    public void deleteAddress(@Param("addressId") Integer addressId);

    //根据查询出的状态 返回详细地址
    public Address selectByChangeStatuas(@Param("changeStatus") Integer changeStatus);

    public void updateChangeStatusAll(@Param("userId") Integer userId);

    public void updateChangeStatusOne(@Param("addressId") Integer addressId);

}
