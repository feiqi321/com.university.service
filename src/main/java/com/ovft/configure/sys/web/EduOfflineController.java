package com.ovft.configure.sys.web;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ovft.configure.http.result.StatusCode;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.EduOfflineOrder;
import com.ovft.configure.sys.bean.EduOfflineOrderitem;
import com.ovft.configure.sys.service.EduOfflineOrderService;
import com.ovft.configure.sys.service.EduOfflineOrderitemService;
import com.ovft.configure.sys.service.EduOfflineService;
import com.ovft.configure.sys.vo.PageBean;
import com.ovft.configure.sys.vo.QueryOffLineVos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vvtxw
 * @create 2019-05-24 12:03
 */
@RestController
@RequestMapping("payoff")
public class EduOfflineController {

    @Autowired
    private EduOfflineOrderService eduOfflineOrderService;

    @Autowired
    private EduOfflineOrderitemService eduOfflineOrderitemService;

    @Autowired
    private EduOfflineService eduOfflineService;

    /**
     * 分页显示线下报名记录
     *
     * @param page
     * @param size
     * @param schoolId
     * @param userId
     * @return
     */
    @GetMapping("shows")
    public WebResult showsQueryOffline(@RequestParam("pageNum") Integer page, @RequestParam("pageSize") Integer size, String schoolId, Integer userId) {
        PageBean pageBean = eduOfflineService.queryAllOffInfo(page, size, schoolId, userId);
        return new WebResult(StatusCode.OK, "查询成功", pageBean);
    }


    @PostMapping("updatepay")
    public WebResult updatePayStatus(String schoolId, Integer userId, Integer courseId) {
        Integer res = eduOfflineOrderService.updatePayStatus(schoolId, userId, courseId);
        Integer res1 = eduOfflineOrderitemService.updatePayStatus(schoolId, userId, courseId);
        return null;
    }
}
