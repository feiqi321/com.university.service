package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.EduSign;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**

 * @author zqx
 * @since 2019-07-04
 */
@Mapper
public interface EduSignMapper {

    //寻找签到的最后一条记录
    public EduSign selectLastByUserId(@Param("userId") Integer userId);

    //当月签到次数
    public Integer selectMonthCount(@Param("userId") Integer userId);

    //添加签到
    public void createSign(EduSign eduSign);
}