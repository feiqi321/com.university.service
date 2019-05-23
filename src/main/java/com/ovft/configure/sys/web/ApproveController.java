package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Approve;
import com.ovft.configure.sys.service.ApproveService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
       public WebResult approve(@RequestBody Approve approve){
           return  approveService.approve(approve.getUserId(),approve.getTypeId(),approve.getType());
       }
}
