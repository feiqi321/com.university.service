package com.ovft.configure.sys.service.impl;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Address;
import com.ovft.configure.sys.dao.AddressMapper;
import com.ovft.configure.sys.service.AddressService;
import com.ovft.configure.sys.utils.SecurityUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AddressService
 * @Author zqx
 * @Version 1.0
 **/
@Service
public class AddressServiceImpl implements AddressService {

    private static final Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);

    @Resource
    private AddressMapper addressMapper;

    @Override
    @Transactional
    public WebResult createAddress(Address address) {
        if(StringUtils.isBlank(address.getConsigneeName())) {
            return new WebResult("400", "收货人姓名不能为空", "");
        }
        if(StringUtils.isBlank(address.getArea())) {
            return new WebResult("400", "地区不能为空", "");
        }
        if(StringUtils.isBlank(address.getAddress())) {
            return new WebResult("400", "详细地址不能为空", "");
        }
        boolean b = SecurityUtils.securityPhone(address.getPhone());
        if(!b) {
            return new WebResult("400", "请输入正确的手机号", "");
        }
        if(address.getIsdefault() == null) {
            address.setIsdefault(0);
        }

        //如果原先没有收货地址,添加的第一条默认为默认收货地址
        List<Address> addressList = addressMapper.selectByUserId(address.getUserId());
        if(addressList.size() != 0) {
            //如果新添加的地址是默认地址,先修改原先默认地址为0
            if(address.getIsdefault().compareTo(1) == 0) {
                Address isDefaultAddress = addressList.get(0);
                if(isDefaultAddress.getIsdefault() == 1) {
                    isDefaultAddress.setIsdefault(0);
                    addressMapper.updateAddress(isDefaultAddress);
                }
            }
        }

        if(address.getAddressId() == null) {
            addressMapper.createAddress(address);
            return new WebResult("200", "添加成功", "");
        } else {
            addressMapper.updateAddress(address);
            return new WebResult("200", "修改成功", "");
        }
    }

    @Override
    public WebResult findAddress(Integer addressId) {
        Address address = addressMapper.selectByAddressId(addressId);
        return new WebResult("200", "查询成功", address);
    }

    @Override
    public WebResult addressList(Integer userId) {
        List<Map<String, Object>> addressList = addressMapper.selectByUserIdMap(userId);
        return new WebResult("200", "查询成功", addressList);
    }

    @Override
    @Transactional
    public WebResult deleteAddress(Integer addressId) {
        addressMapper.deleteAddress(addressId);
        return new WebResult("200", "删除成功", "");
    }
}
