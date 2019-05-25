package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.User;
import com.ovft.configure.sys.service.NewsTypeService;
import com.ovft.configure.sys.vo.PageVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName NewsTypeController
 * @Author xzy
 *   栏目类型
 * @Date 2019/5/21 15:45
 * @Version 1.0
 **/
@RestController
@RequestMapping("/NewsType")
public class NewsTypeController {
       @Resource
       private NewsTypeService newsTypeService;
    /**
     * 所有栏目类型查询
     *
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/queryNewsTypeAll")
    public WebResult queryNewsTypeAll(@RequestBody PageVo pageVo){
               return  newsTypeService.queryNewsTypeAll(pageVo);
    }
}
