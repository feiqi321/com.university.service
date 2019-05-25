package com.ovft.configure.sys.service;

import com.ovft.configure.sys.bean.EduRegist;
import com.ovft.configure.sys.vo.PageBean;

/**
 * @author vvtxw
 * @create 2019-04-29 10:03
 */
public interface EduRegistService {
    //添加报名条件
    public int addRegistCondition(EduRegist eduRegist);

    //分页全查询
    public PageBean queryAllCodition(Integer size, Integer page);

    public PageBean queryAllCoditionBySchoold(Integer size, Integer page, Integer schoolId);

    //修改条件
    int updateCodition(EduRegist eduRegist);

    //删除条件
    int deleteCodition(EduRegist eduRegist);

    //查单一条件
    EduRegist queryById(Integer id);

    //查询特殊条件
    PageBean queryspecialCoditionBySchoold(Integer size, Integer page, Integer schoolId);

    //查询所有的特殊条件
    PageBean queryspecialCodition(Integer size, Integer page);

    //去除特殊条件
    int deleteSepecialCondition(EduRegist eduRegist);

    //查询状态
    Integer queryOffRegist(int schoolId, Integer courseId);
}
