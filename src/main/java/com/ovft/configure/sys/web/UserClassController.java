package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.vo.PageVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName UserClassController 学员班级
 * @Author xzy
 * @Version 2.5
 **/

@RestController
public class UserClassController {

    /**
     * 后台班级列表（分页）
     *
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/server/findServerClassAll")
    public WebResult findServerClassAll(HttpServletRequest request, @RequestBody PageVo pageVo) {
          return null;
    }
    /**
     * 学员班级列表（分页）
     *
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/user/findUserClassAll")
    public WebResult findUserClassAll(HttpServletRequest request, @RequestBody PageVo pageVo) {
          return null;
    }
}
