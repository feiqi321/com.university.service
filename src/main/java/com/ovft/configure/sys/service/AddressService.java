package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Address;

public interface AddressService {

    public WebResult createAddress(Address address);

    public WebResult findAddress(Integer addressId);

    public WebResult addressList(Integer userId);

    public WebResult deleteAddress(Integer addressId);
}
