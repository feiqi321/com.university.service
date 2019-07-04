package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.service.EduFlowersService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zqx
 * @since 2019-07-04
 */
@RestController
@RequestMapping("/eduFlowers")
public class EduFlowersContrroller {

    @Autowired
    private EduFlowersService flowersService;

    //获取学员的总的花朵数
    @GetMapping("/totalFlower")
    public WebResult totalFlower(HttpServletRequest request) {
        String userId = request.getHeader("userId");
        if(StringUtils.isBlank(userId) || userId.equals("null")) {
            return new WebResult("400", "请登录！", "");
        }
        return flowersService.totalFlower(Integer.valueOf(userId));
    }

    //获取学员红花记录列表
    @GetMapping("/flowerList")
    public WebResult flowerList(HttpServletRequest request) {
        String userId = request.getHeader("userId");
        if(StringUtils.isBlank(userId) || userId.equals("null")) {
            return new WebResult("400", "请登录！", "");
        }
        return flowersService.flowerList(Integer.valueOf(userId));
    }
	
}
