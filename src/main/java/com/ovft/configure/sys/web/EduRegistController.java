package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.StatusCode;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.EduRegist;
import com.ovft.configure.sys.service.EduRegistService;
import com.ovft.configure.sys.vo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author vvtxw
 * @create 2019-04-29 10:08
 */

@RestController
@RequestMapping("regist")
public class EduRegistController {

    @Autowired
    private EduRegistService eduRegistService;

    /**
     * 报名条件设置
     *
     * @param eduRegist
     * @return
     */
    @PostMapping(value = "condition")
    public WebResult addRegistCondition(@RequestBody EduRegist eduRegist) {
        eduRegist.setUpateTime(new Date());
        EduRegist oldEduRegist = eduRegistService.queryById(eduRegist.getCourseId());
        if (oldEduRegist != null) {
            return new WebResult(StatusCode.ERROR, "课程设置重复，请删除已有再设置", "");
        }
        int result = eduRegistService.addRegistCondition(eduRegist);
        if (result > 0) {
            return new WebResult(StatusCode.OK, "条件设置成功", "");
        }
        if (result == -2) {
            return new WebResult(StatusCode.ERROR, "温馨提示：一个学校只能对应一个模板，请点击编辑即可修改", "");
        }
        if (result == -3) {
            return new WebResult(StatusCode.ERROR, "温馨提示：先查出您选定所有课程，然后才能添加全局设置", "");
        }
        return new WebResult(StatusCode.ERROR, "条件设置失败", "");
    }


    /**
     * 查询条件
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "show")
    public WebResult queryAllCourseCodition(@RequestParam("pageNum") Integer page, @RequestParam("pageSize") Integer size, String schoolId) {
        if (!schoolId.equals("null")) {
            PageBean pageBean = eduRegistService.queryAllCoditionBySchoold(size, page, Integer.parseInt(schoolId));
            return new WebResult(StatusCode.OK, "查询成功", pageBean);
        }
        PageBean pageBean = eduRegistService.queryAllCodition(size, page);
        return new WebResult(StatusCode.OK, "查询成功", pageBean);
    }

    /**
     * 查询特殊条件
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "showspecial")
    public WebResult queryspecialCodition(@RequestParam("pageNum") Integer page, @RequestParam("pageSize") Integer size, String schoolId) {
        if (!schoolId.equals("null")) {
            PageBean pageBean = eduRegistService.queryspecialCoditionBySchoold(size, page, Integer.parseInt(schoolId));
            return new WebResult(StatusCode.OK, "查询成功", pageBean);
        }
        PageBean pageBean = eduRegistService.queryspecialCodition(size, page);
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
        EduRegist eduRegist = eduRegistService.queryById(id);
        return new WebResult(StatusCode.OK, "查询成功", eduRegist);
    }


    /**
     * 修改条件设置
     *
     * @param eduRegist
     * @return
     */
    @PostMapping(value = "updateco")
    public WebResult updateCodition(@RequestBody EduRegist eduRegist) {
        int result = eduRegistService.updateCodition(eduRegist);
        if (result > 0) {
            return new WebResult(StatusCode.OK, "修改设置成功", "");
        }
        return new WebResult(StatusCode.ERROR, "修改设置失败", "");
    }

    /**
     * 删除条件设置
     *
     * @param eduRegist
     * @return
     */
    @PostMapping(value = "deleteco")
    public WebResult deleteCodition(@RequestBody EduRegist eduRegist) {
        int result = eduRegistService.deleteCodition(eduRegist);
        if (result > 0) {
            return new WebResult(StatusCode.OK, "删除设置成功", "");
        }
        return new WebResult(StatusCode.ERROR, "删除设置失败", "");
    }

    /**
     * 移除条件设置
     *
     * @param eduRegist
     * @return
     */
    @PostMapping(value = "deletesp")
    public WebResult deleteSepecialCondition(@RequestBody EduRegist eduRegist) {
        int result = eduRegistService.deleteSepecialCondition(eduRegist);
        if (result > 0) {
            return new WebResult(StatusCode.OK, "删除特殊设置成功,已经添加到全局设置里", "");
        }
        return new WebResult(StatusCode.ERROR, "删除特殊设置失败", "");
    }


}
