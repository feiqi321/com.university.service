package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Address;
import com.ovft.configure.sys.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName AddressController
 * @Author zqx
 * @Version 1.0
 **/

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    /**
     * 添加/修改收货地址
     *
     * @param address
     * @return
     */
    @PostMapping(value = "/createAddress")
    public WebResult createAddress(HttpServletRequest request, @RequestBody Address address) {
        address.setUserId(Integer.parseInt(request.getHeader("userId")));
        return addressService.createAddress(address);
    }

    /**
     * 进入修改收货地址
     *
     * @param addressId
     * @return
     */
    @GetMapping(value = "/findAddress")
    public WebResult findAddress(@RequestParam(value = "addressId") Integer addressId) {
        return addressService.findAddress(addressId);
    }

    /**
     * 收货地址列表
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/addressList")
    public WebResult addressList(HttpServletRequest request) {
        return addressService.addressList(Integer.parseInt(request.getHeader("userId")));
    }

    /**
     * 删除收货地址
     *
     * @param addressId
     * @return
     */
    @GetMapping(value = "/deleteAddress")
    public WebResult deleteAddress(@RequestParam(value = "addressId") Integer addressId) {
        return addressService.deleteAddress(addressId);
    }

}
