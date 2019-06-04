package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Collect;
import com.ovft.configure.sys.service.CollectService;
import com.ovft.configure.sys.vo.PageVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/collect")
public class CollectController {

   @Autowired
    private CollectService collectService;

  /**
   *     收藏
   * @param collect
   * @return
   */
  @PostMapping(value = "/addCollect")
   public WebResult addCollect(HttpServletRequest request, @RequestBody Collect collect){
      String userId = request.getHeader("userId");
      if(StringUtils.isBlank(userId) || userId.equals("null")) {
          return new WebResult("400", "请登录！", "");
      }
      collect.setUid(Integer.valueOf(userId));
       return  collectService.addCollect(collect);
   }

    /**
     *  收藏列表
     * @param request
     * @param pageVo
     * @return
     */
   @PostMapping(value = "/collectList")
   public WebResult collectList(HttpServletRequest request, @RequestBody PageVo pageVo) {
       String userId = request.getHeader("userId");
       if(StringUtils.isBlank(userId) || userId.equals("null")) {
           return new WebResult("400", "请登录！", "");
       }
       pageVo.setUserId(Integer.valueOf(userId));
       return collectService.collectList(pageVo);
   }

    /**
     * 删除收藏
     * @param collectId
     * @return
     */
    @GetMapping(value = "/deleteCollect")
    public WebResult deleteCollect(@RequestParam(value = "collectId") Integer collectId) {
        return collectService.deleteCollect(collectId);
    }
}
