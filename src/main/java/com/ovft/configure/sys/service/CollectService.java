package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Collect;
import com.ovft.configure.sys.vo.PageVo;

public interface CollectService {

    //收藏
    public WebResult addCollect(Collect collect);

    //收藏列表
    public WebResult collectList(PageVo pageVo);

    //是否收藏
    public Integer isCollect(Integer userId, Integer type, Integer typeId);

    //删除收藏
    public WebResult deleteCollect(Integer collectId);
}
