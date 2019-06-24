package com.ovft.configure.sys.web;

import com.ovft.configure.constant.ConstantClassField;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Admin;
import com.ovft.configure.sys.bean.Approve;
import com.ovft.configure.sys.bean.User;
import com.ovft.configure.sys.service.ApproveService;
import com.ovft.configure.sys.utils.RedisUtil;
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
       @Resource
       private RedisUtil redisUtil;
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
          String token = request.getHeader("token");
          Object o = redisUtil.get(token);
          if (o != null) {
              Integer id = (Integer) o;
              // 如果是pc端登录，更新token缓存失效时间
              redisUtil.expire(token, ConstantClassField.PC_CACHE_EXPIRATION_TIME);
              User hget = (User) redisUtil.hget(ConstantClassField.USER_INFO, id.toString());
              String name = hget.getUserName();
              return  approveService.approve(approve.getUserId(),approve.getTypeId(),approve.getType());
          } else {
              return new WebResult("50012", "请先登录", "");
          }





       }
}
