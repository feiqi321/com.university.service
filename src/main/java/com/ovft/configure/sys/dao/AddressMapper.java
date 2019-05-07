package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName AddressMapper
 * @Author zqx
 * @Version 1.0
 **/
@Mapper
public interface AddressMapper {

    public Address selectByAddressId(@Param("addressId") Integer addressId);

    public List<Address> selectByUserId(@Param("userId") Integer userId);

    public void createAddress(Address address);

    public void updateAddress(Address address);

    public void deleteAddress(@Param("addressId") Integer addressId);
}
