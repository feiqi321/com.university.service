package com.ovft.configure.sys.web;

import com.jfinal.aop.Before;
import com.ovft.configure.config.CORSInterceptor;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zqx
 */

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    /**
     * 消息列表
     *
     * @return
     */
    @GetMapping(value = "/findMessage")
    public WebResult findMessage(HttpServletRequest request, @RequestParam(value = "pageSize") Integer pageSize,
                                 @RequestParam(value = "pageNum") Integer pageNum) {
        Integer userId = (Integer) request.getAttribute("userId");
        Integer schoolId = (Integer) request.getAttribute("schoolId");
        return messageService.findMessage(userId, schoolId, pageNum, pageSize);
    }


}
