package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.StatusCode;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.service.EduBookGoodsService;
import com.ovft.configure.sys.vo.PageBean;
import com.ovft.configure.sys.vo.QueryBookVos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author vvtxw
 * @create 2019-05-16 11:47
 */
@RestController
@RequestMapping("booksell")
public class EduBookGoodsController {

    @Autowired
    private EduBookGoodsService eduBookGoodsService;

    /**
     * 分页查询所有教材
     *
     * @return
     */
    @GetMapping(value = "bookshow")
    public WebResult queryAllshowBooks(@RequestParam(value = "catId") Integer catId, @RequestParam(value = "extendCatId") Integer extendCatId) {
        QueryBookVos queryBookVos = eduBookGoodsService.queryAllBookesForPage(catId, extendCatId);
        return new WebResult(StatusCode.OK, "查询成功", queryBookVos);
    }

    /**
     * 搜索功能
     *
     * @param searchkey
     * @return
     */
    @GetMapping(value = "search")
    public WebResult querySearch(String searchkey) {

        if (searchkey == null || searchkey == "") {
            return new WebResult(StatusCode.OK, "查询为空", "");
        }
        PageBean pageBean = eduBookGoodsService.queryAllBookesForlike(searchkey);
        return new WebResult(StatusCode.OK, "查询成功", pageBean);
    }






}
