package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.vo.PageVo;

public interface NewsTypeService {
    //获取所有栏目
    public WebResult queryNewsTypeAll(PageVo pageVo);
}
