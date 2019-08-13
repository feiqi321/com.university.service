package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Address;
import org.springframework.web.bind.annotation.RequestParam;

public interface AddressService {

    public WebResult createAddress(Address address);

    public WebResult findAddress(Integer addressId);

    public WebResult addressList(Integer userId);

    public WebResult deleteAddress(Integer addressId);
    //改变收货地址
    public WebResult changeAddress( Address address);
}
