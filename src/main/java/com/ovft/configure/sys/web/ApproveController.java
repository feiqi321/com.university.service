package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Approve;
import com.ovft.configure.sys.service.ApproveService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
  @RequestMapping("")
public class ApproveController {
       @Resource
        private ApproveService approveService;
      /**
       * 点赞
       *
       * @param approve
       *
       * @return
       */
      @PostMapping(value = "/approve")
       public WebResult approve(HttpServletRequest request, @RequestBody Approve approve){
          String userId = request.getHeader("userId");
          if(StringUtils.isBlank(userId) || userId.equals("null")) {
              return new WebResult("400", "请登录！", "");
          }
           return  approveService.approve(approve.getUserId(),approve.getTypeId(),approve.getType());
       }
}
