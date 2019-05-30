package com.ovft.configure.sys.service;

import com.ovft.configure.sys.bean.EduOfflineAddresstime;
import com.ovft.configure.sys.vo.PageBean;

import java.util.List;

/**
 * @author vvtxw
 * @create 2019-05-23 16:07
 */
public interface EduOfflineAddressService {

    //查询学校的线下线下信息
    List<EduOfflineAddresstime> queryAddressByschoolId(Integer schoolId);

    //添加下线学校地址信息
    Integer addAddressInfo(EduOfflineAddresstime eduOfflineAddresstime);

    //修改下线学校地址信息
    Integer updateInfo(EduOfflineAddresstime eduOfflineAddresstime);

    //分页全查询
    PageBean queryAllAddressInfoBySchoold(Integer size, Integer page, int schoolId);

    //分页全查询
    PageBean queryAllAddressInfo(Integer size, Integer page);

    //查询一个
    EduOfflineAddresstime queryById(Integer id);

    //删除一个
    Integer deleteAddressTime(Integer id);



}
