package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.AliConfigPojo;
import com.ovft.configure.sys.bean.WxConfigPojo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PayConfigMapper {
           //查询学校对应的支付配置信息（微信）
          public WxConfigPojo selectWxPayConfig(@Param("schoolId") Integer schoolId, Integer type);
           //查询学校对应的支付配置信息  （支付报）
          public AliConfigPojo selectAliPayConfig(@Param("schoolId")Integer schoolId, Integer type);
}
