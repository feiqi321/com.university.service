package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.StatusCode;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.service.EduBooksInfoService;
import com.ovft.configure.sys.service.EduCartService;
import com.ovft.configure.sys.vo.EduBooksInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author vvtxw
 * @create 2019-05-17 11:40
 */

@RestController
@RequestMapping("bookInfo")
public class EduBooksInfoController {

    @Autowired
    private EduBooksInfoService eduBooksInfoService;

    @Autowired
    private EduCartService eduCartService;

    /**
     * 展示详情页
     *
     * @param id
     * @param request
     * @return
     */
    @GetMapping(value = "showBookInfo")
    public WebResult queryBookById(Integer id, HttpServletRequest request) {
        String userId1 = request.getHeader("userId");
        if (userId1.equals("null")) {
            return new WebResult(StatusCode.ERROR, "请先登录，然后浏览");
        }
        Integer userId = Integer.valueOf(userId1);

/*        String shcoolId1 = request.getHeader("shcoolId");
        if (shcoolId1.equals("null")) {
            return new WebResult(StatusCode.ERROR, "选择的学校不能为空");
        }
        Integer shcoolId = Integer.valueOf(shcoolId1);*/


        if (id == null) {
            return new WebResult(StatusCode.ERROR, "教材id不能为空");
        }

        EduBooksInfoVo eduBooksInfo = eduBooksInfoService.selectBookById(id);

        long cartNum = eduCartService.countCartNum(userId);
        eduBooksInfo.setCountNum(cartNum);

        return new WebResult(StatusCode.OK, "查询成功", eduBooksInfo);
    }
}
