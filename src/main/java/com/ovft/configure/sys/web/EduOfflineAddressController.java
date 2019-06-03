package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.StatusCode;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.EduOfflineAddresstime;
import com.ovft.configure.sys.service.EduOfflineAddressService;
import com.ovft.configure.sys.vo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author vvtxw
 * @create 2019-05-24 9:18
 */

@RestController
@RequestMapping("address")
public class EduOfflineAddressController {
    @Autowired
    private EduOfflineAddressService eduOfflineAddressService;

    /**
     * 添加线下报名地址信息
     *
     * @param eduOfflineAddresstime
     * @return
     */
    @PostMapping("addinfo")
    public WebResult addAddressInfo(@RequestBody EduOfflineAddresstime eduOfflineAddresstime) {
        List<EduOfflineAddresstime> eduOfflineAddresstimes = eduOfflineAddressService.queryAddressByschoolId(eduOfflineAddresstime.getSchoolId());
        if (eduOfflineAddresstimes.size() > 0) {
            return new WebResult(StatusCode.OK, "您已经添加了本学校的线下支付地点信息，无需重复设置", "");
        }

        Integer res = eduOfflineAddressService.addAddressInfo(eduOfflineAddresstime);
        if (res > 0) {
            return new WebResult(StatusCode.OK, "添加成功", "");
        }
        return new WebResult(StatusCode.ERROR, "添加失败", "");
    }

    /**
     * 修改线下报名地址信息
     *
     * @param eduOfflineAddresstime
     * @return
     */
    @PostMapping("updateinfo")
    public WebResult updateAddressInfo(@RequestBody EduOfflineAddresstime eduOfflineAddresstime) {
        Integer res = eduOfflineAddressService.updateInfo(eduOfflineAddresstime);
        if (res > 0) {
            return new WebResult(StatusCode.OK, "修改成功", "");
        }
        return new WebResult(StatusCode.ERROR, "修改失败", "");
    }

    /**
     * 查询条件
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "show")
    public WebResult queryAllAddressInfo(@RequestParam("pageNum") Integer page, @RequestParam("pageSize") Integer size, String schoolId) {
        if (!schoolId.equals("")) {
            PageBean pageBean = eduOfflineAddressService.queryAllAddressInfoBySchoold(size, page, Integer.parseInt(schoolId));
            return new WebResult(StatusCode.OK, "查询成功", pageBean);
        }
        PageBean pageBean = eduOfflineAddressService.queryAllAddressInfo(size, page);
        return new WebResult(StatusCode.OK, "查询成功", pageBean);
    }

    /**
     * 查询1条信息
     *
     * @param id
     * @return
     */
    @GetMapping(value = "showone")
    public WebResult queryById(Integer id) {
        EduOfflineAddresstime eduOfflineAddresstime = eduOfflineAddressService.queryById(id);
        return new WebResult(StatusCode.OK, "查询成功", eduOfflineAddresstime);
    }

    /**
     * 删除线下地址信息
     *
     * @param eduOfflineAddresstime
     * @return
     */
    @PostMapping(value = "deleteone")
    public WebResult deleteAddressTime(@RequestBody EduOfflineAddresstime eduOfflineAddresstime) {
        Integer res = eduOfflineAddressService.deleteAddressTime(eduOfflineAddresstime.getId());
        if (res > 0) {
            return new WebResult(StatusCode.OK, "修改成功", "");
        }
        return new WebResult(StatusCode.ERROR, "删除失败", "");
    }
}
