package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.StatusCode;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.EduBookGoods;
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


    /**
     * 添加新教材
     *
     * @param eduBookGoods
     * @return
     */
    @PostMapping(value = "addbooks")
    public WebResult addBooks(@RequestBody EduBookGoods eduBookGoods) {
        Integer res = eduBookGoodsService.addBooks(eduBookGoods);
        if (res > 0) {
            return new WebResult(StatusCode.OK, "添加成功", "");
        }
        return new WebResult(StatusCode.OK, "添加失败", "");
    }

    /**
     * 查询1条信息
     *
     * @param id
     * @return
     */
    @GetMapping(value = "showone")
    public WebResult queryById(Integer id) {
        EduBookGoods eduBookGoods = eduBookGoodsService.queryById(id);
        return new WebResult(StatusCode.OK, "查询成功", eduBookGoods);
    }

    /**
     * 分页显示教材列表
     *
     * @param page
     * @param size
     * @param schoolId
     * @return
     */
    @GetMapping(value = "shows")
    public WebResult showPage(@RequestParam("pageNum") Integer page, @RequestParam("pageSize") Integer size, String schoolId, String booksAuthor, String booksSn, Byte isOnSale) {
        PageBean pageBean = eduBookGoodsService.showPageBooks(page, size, schoolId, booksAuthor, booksSn, isOnSale);
        return new WebResult(StatusCode.OK, "查询成功", pageBean);
    }


    /**
     * 修改教材
     *
     * @param eduBookGoods
     * @return
     */
    @PostMapping(value = "updateco")
    public WebResult updateCodition(@RequestBody EduBookGoods eduBookGoods) {
        int result = eduBookGoodsService.updateBook(eduBookGoods);
        if (result > 0) {
            return new WebResult(StatusCode.OK, "修改设置成功", "");
        }
        return new WebResult(StatusCode.ERROR, "修改设置失败", "");
    }

    /**
     * 删材
     *
     * @param eduBookGoods
     * @return
     */
    @PostMapping(value = "deleteco")
    public WebResult deleteCodition(@RequestBody EduBookGoods eduBookGoods) {
        int result = eduBookGoodsService.deleteBook(eduBookGoods);
        if (result > 0) {
            return new WebResult(StatusCode.OK, "删除设置成功", "");
        }
        return new WebResult(StatusCode.ERROR, "删除设置失败", "");
    }

    /**
     * 上架和下架
     *
     * @param eduBookGoods
     * @return
     */
    @PostMapping(value = "UpdataIsOnSale")
    public WebResult upGoodsBook(@RequestBody EduBookGoods eduBookGoods) {
        Integer res = eduBookGoodsService.UpdataIsOnSale(eduBookGoods);
        if (res > 0) {
            return new WebResult(StatusCode.OK, "修改成功", "");
        }
        return new WebResult(StatusCode.ERROR, "修改失败", "");
    }

    /**
     * 批量上架和下架
     *
     * @param eduBookGoods
     * @return
     */
    @PostMapping(value = "bathUpdataIsOnSale")
    public WebResult bathUpdataPaystatus(@RequestBody EduBookGoods eduBookGoods) {
        Integer res = eduBookGoodsService.bathUpdataIsOnSale(eduBookGoods);
        if (res > 0) {
            return new WebResult(StatusCode.OK, "修改成功", "");
        }
        return new WebResult(StatusCode.ERROR, "修改失败", "");
    }


}
