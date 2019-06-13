package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.StatusCode;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.EduBooksInfo;
import com.ovft.configure.sys.service.EduBooksInfoService;
import com.ovft.configure.sys.service.EduCartService;
import com.ovft.configure.sys.vo.EduBooksInfoVo;
import com.ovft.configure.sys.vo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    /**
     * 添加教材详情
     *
     * @param eduBooksInfo
     * @return
     */
    @PostMapping(value = "add")
    public WebResult addBookInfo(@RequestBody EduBooksInfo eduBooksInfo) {
        Integer res = eduBooksInfoService.addBookInfo(eduBooksInfo);
        if (res > 0) {
            return new WebResult(StatusCode.OK, "添加成功", "");
        }
        return new WebResult(StatusCode.ERROR, "添加失败", "");
    }

    /**
     * 删除教材详情
     *
     * @param eduBooksInfo
     * @return
     */
    @PostMapping(value = "delete")
    public WebResult deleteBookInfo(@RequestBody EduBooksInfo eduBooksInfo) {
        Integer res = eduBooksInfoService.deleteBookInfo(eduBooksInfo);
        if (res > 0) {
            return new WebResult(StatusCode.OK, "删除成功", "");
        }
        return new WebResult(StatusCode.ERROR, "删除失败", "");
    }

    /**
     * 修改教材详情
     *
     * @param eduBooksInfo
     * @return
     */
    @PostMapping(value = "update")
    public WebResult updateBookInfo(@RequestBody EduBooksInfo eduBooksInfo) {
        Integer res = eduBooksInfoService.updateBookInfo(eduBooksInfo);
        if (res > 0) {
            return new WebResult(StatusCode.OK, "修改成功", "");
        }
        return new WebResult(StatusCode.ERROR, "修改失败", "");
    }

    /**
     * 修改教材详情
     *
     * @param id
     * @return
     */
    @GetMapping(value = "selectone")
    public WebResult selectoneBookInfo(Integer id) {
        EduBooksInfo eduBooksInfo = eduBooksInfoService.selectoneBookInfo(id);
        return new WebResult(StatusCode.OK, "查询成功", eduBooksInfo);
    }

    /**
     * 分页显示教材详情
     *
     * @param page
     * @param size
     * @param schoolId
     * @param id
     * @return
     */
    @GetMapping(value = "shows")
    public WebResult showPage(@RequestParam("pageNum") Integer page, @RequestParam("pageSize") Integer size, String schoolId, Integer id) {
        PageBean pageBean = eduBooksInfoService.showPageBookInfo(page, size, schoolId, id);
        return new WebResult(StatusCode.OK, "查询成功", pageBean);
    }

    /**
     * 根据教材id查询教材的名称
     *
     * @param id
     * @return
     */
    @GetMapping(value = "selectone")
    public WebResult queryBookNameById(Integer id) {
        List<String> eduBooksName = eduBooksInfoService.queryBookNameById(id);
        return new WebResult(StatusCode.OK, "查询成功", eduBooksName);
    }


}
