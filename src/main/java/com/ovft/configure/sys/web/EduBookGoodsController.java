package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.StatusCode;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.EduBookGoods;
import com.ovft.configure.sys.bean.EduBooksInfo;
import com.ovft.configure.sys.service.EduBookGoodsService;
import com.ovft.configure.sys.service.EduBooksInfoService;
import com.ovft.configure.sys.vo.EduBookGoodsVo;
import com.ovft.configure.sys.vo.PageBean;
import com.ovft.configure.sys.vo.QueryBookVos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author vvtxw
 * @create 2019-05-16 11:47
 */
@RestController
@RequestMapping("booksell")
public class EduBookGoodsController {

    @Autowired
    private EduBookGoodsService eduBookGoodsService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private EduBooksInfoService eduBooksInfoService;


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
    @PostMapping(value = "server/addbooks")
    public WebResult addBooks(@RequestBody EduBookGoods eduBookGoods) {
        Integer res = eduBookGoodsService.addBooks(eduBookGoods);
        if (res > 0) {
            return new WebResult(StatusCode.OK, "添加成功", "");
        }
        return new WebResult(StatusCode.OK, "添加失败", "");
    }

    /**
     * 添加新教材
     *
     * @param eduBookGoodsVo
     * @return
     */
    @PostMapping(value = "server/addbooksInfo")
    public WebResult addBooksInfo(@RequestBody EduBookGoodsVo eduBookGoodsVo) {
        EduBookGoods eduBookGoods = new EduBookGoods();
        eduBookGoods.setCatId(eduBookGoodsVo.getCatId());
        eduBookGoods.setExtendCatId(eduBookGoodsVo.getExtendCatId());
        eduBookGoods.setBooksSn(eduBookGoodsVo.getBooksSn());
        eduBookGoods.setBooksAuthor(eduBookGoodsVo.getBooksAuthor());
        eduBookGoods.setBooksName(eduBookGoodsVo.getBooksName());
        eduBookGoods.setStoreCount(eduBookGoodsVo.getStoreCount());
        eduBookGoods.setShopPrice(eduBookGoodsVo.getShopPrice());
        eduBookGoods.setOriginalImg(eduBookGoodsVo.getOriginalImg());
        eduBookGoods.setIsOnSale(eduBookGoodsVo.getIsOnSale());
        eduBookGoods.setUpdateTime(new Date());
        eduBookGoods.setSchoolId(eduBookGoodsVo.getSchoolId());
        eduBookGoods.setSendPrice(eduBookGoodsVo.getSendPrice());
        Integer res1 = eduBookGoodsService.addBooks(eduBookGoods);

        EduBooksInfo eduBooksInfo = new EduBooksInfo();
        eduBooksInfo.setBookGoodsId(eduBookGoods.getId());
        eduBooksInfo.setPublishAddress(eduBookGoodsVo.getPublishAddress());
        eduBooksInfo.setFormatBook(eduBookGoodsVo.getFormatBook());
        eduBooksInfo.setEditionBook(eduBookGoodsVo.getEditionBook());
        eduBooksInfo.setPageSize(eduBookGoodsVo.getPageSize());
        eduBooksInfo.setRecommendUnit(eduBookGoodsVo.getRecommendUnit());
        eduBooksInfo.setBookSn(eduBookGoodsVo.getBookSn());
        eduBooksInfo.setDescribeNote(eduBookGoodsVo.getDescribeNote());
        eduBooksInfo.setDescribeBook(eduBookGoodsVo.getDescribeBook());
        eduBooksInfo.setIntroductionAuthor(eduBookGoodsVo.getIntroductionAuthor());
        eduBooksInfo.setSchoolId(Integer.valueOf(eduBookGoodsVo.getSchoolId()));
        Integer res2 = eduBooksInfoService.addBookInfo(eduBooksInfo);

        if (res1 > 0 && res2 > 0) {
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
    @GetMapping(value = "server/showone")
    public WebResult queryById(Integer id) {
        EduBookGoods eduBookGoods = eduBookGoodsService.queryById(id);
        EduBooksInfo eduBooksInfo = eduBooksInfoService.selectoneByGoodsId(id);
        EduBookGoodsVo eduBookGoodsVo = new EduBookGoodsVo();
        eduBookGoodsVo.setId(eduBookGoods.getId());
        eduBookGoodsVo.setCatId(eduBookGoods.getCatId());
        eduBookGoodsVo.setExtendCatId(eduBookGoods.getExtendCatId());
        eduBookGoodsVo.setBooksSn(eduBookGoods.getBooksSn());
        eduBookGoodsVo.setBooksAuthor(eduBookGoods.getBooksAuthor());
        eduBookGoodsVo.setBooksName(eduBookGoods.getBooksName());
        eduBookGoodsVo.setStoreCount(eduBookGoods.getStoreCount());
        eduBookGoodsVo.setShopPrice(eduBookGoods.getShopPrice());
        eduBookGoodsVo.setOriginalImg(eduBookGoods.getOriginalImg());
        eduBookGoodsVo.setIsOnSale(eduBookGoods.getIsOnSale());
        eduBookGoodsVo.setUpdateTime(new Date());
        eduBookGoodsVo.setSchoolId(eduBookGoods.getSchoolId());
        eduBookGoodsVo.setSendPrice(eduBookGoods.getSendPrice());


        eduBookGoodsVo.setInfoId(eduBooksInfo.getId());
        eduBookGoodsVo.setBookGoodsId(eduBooksInfo.getBookGoodsId());
        eduBookGoodsVo.setPublishAddress(eduBooksInfo.getPublishAddress());
        eduBookGoodsVo.setFormatBook(eduBooksInfo.getFormatBook());
        eduBookGoodsVo.setEditionBook(eduBooksInfo.getEditionBook());
        eduBookGoodsVo.setPageSize(eduBooksInfo.getPageSize());
        eduBookGoodsVo.setRecommendUnit(eduBooksInfo.getRecommendUnit());
        eduBookGoodsVo.setBookSn(eduBooksInfo.getBookSn());
        eduBookGoodsVo.setDescribeNote(eduBooksInfo.getDescribeNote());
        eduBookGoodsVo.setDescribeBook(eduBooksInfo.getDescribeBook());
        eduBookGoodsVo.setIntroductionAuthor(eduBooksInfo.getIntroductionAuthor());
        eduBookGoodsVo.setSchoolId(String.valueOf(eduBooksInfo.getSchoolId()));

        return new WebResult(StatusCode.OK, "查询成功", eduBookGoodsVo);
    }

    /**
     * 分页显示教材列表
     *
     * @param page
     * @param size
     * @param schoolId
     * @return
     */
    @GetMapping(value = "server/shows")
    public WebResult showPage(@RequestParam("pageNum") Integer page, @RequestParam("pageSize") Integer size, String schoolId, String booksAuthor, String booksSn, Byte isOnSale) {
        PageBean pageBean = eduBookGoodsService.showPageBooks(page, size, schoolId, booksAuthor, booksSn, isOnSale);
        return new WebResult(StatusCode.OK, "查询成功", pageBean);
    }


    /**
     * 修改教材
     *
     * @param eduBookGoodsVo
     * @return
     */
    @PostMapping(value = "server/updateco")
    public WebResult updateCodition(@RequestBody EduBookGoodsVo eduBookGoodsVo) {
        EduBookGoods eduBookGoods = new EduBookGoods();
        eduBookGoods.setId(eduBookGoodsVo.getId());
        eduBookGoods.setCatId(eduBookGoodsVo.getCatId());
        eduBookGoods.setExtendCatId(eduBookGoodsVo.getExtendCatId());
        eduBookGoods.setBooksSn(eduBookGoodsVo.getBooksSn());
        eduBookGoods.setBooksAuthor(eduBookGoodsVo.getBooksAuthor());
        eduBookGoods.setBooksName(eduBookGoodsVo.getBooksName());
        eduBookGoods.setStoreCount(eduBookGoodsVo.getStoreCount());
        eduBookGoods.setShopPrice(eduBookGoodsVo.getShopPrice());
        eduBookGoods.setOriginalImg(eduBookGoodsVo.getOriginalImg());
        eduBookGoods.setIsOnSale(eduBookGoodsVo.getIsOnSale());
        eduBookGoods.setUpdateTime(new Date());
        eduBookGoods.setSchoolId(eduBookGoodsVo.getSchoolId());
        eduBookGoods.setSendPrice(eduBookGoodsVo.getSendPrice());

        int result = eduBookGoodsService.updateBook(eduBookGoods);

        EduBooksInfo eduBooksInfo = new EduBooksInfo();
        eduBooksInfo.setId(eduBookGoodsVo.getInfoId());
        eduBooksInfo.setBookGoodsId(eduBookGoodsVo.getBookGoodsId());
        eduBooksInfo.setPublishAddress(eduBookGoodsVo.getPublishAddress());
        eduBooksInfo.setFormatBook(eduBookGoodsVo.getFormatBook());
        eduBooksInfo.setEditionBook(eduBookGoodsVo.getEditionBook());
        eduBooksInfo.setPageSize(eduBookGoodsVo.getPageSize());
        eduBooksInfo.setRecommendUnit(eduBookGoodsVo.getRecommendUnit());
        eduBooksInfo.setBookSn(eduBookGoodsVo.getBookSn());
        eduBooksInfo.setDescribeNote(eduBookGoodsVo.getDescribeNote());
        eduBooksInfo.setDescribeBook(eduBookGoodsVo.getDescribeBook());
        eduBooksInfo.setIntroductionAuthor(eduBookGoodsVo.getIntroductionAuthor());
        eduBooksInfo.setSchoolId(Integer.valueOf(eduBookGoodsVo.getSchoolId()));

        Integer res2 = eduBooksInfoService.updateBookInfo(eduBooksInfo);
        if (result > 0 && res2 > 0) {
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
    @PostMapping(value = "server/deleteco")
    public WebResult deleteCodition(@RequestBody EduBookGoods eduBookGoods) {
        int result1 = eduBookGoodsService.deleteBook(eduBookGoods);
        int result2 = eduBooksInfoService.deleteBookInfos(eduBookGoods.getId());
        if (result1 > 0 && result2 > 0) {
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
    @PostMapping(value = "server/UpdataIsOnSale")
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
    @PostMapping(value = "server/bathUpdataIsOnSale")
    public WebResult bathUpdataPaystatus(@RequestBody EduBookGoods eduBookGoods) {
        Integer res = eduBookGoodsService.bathUpdataIsOnSale(eduBookGoods);
        if (res > 0) {
            return new WebResult(StatusCode.OK, "修改成功", "");
        }
        return new WebResult(StatusCode.ERROR, "修改失败", "");
    }


}
