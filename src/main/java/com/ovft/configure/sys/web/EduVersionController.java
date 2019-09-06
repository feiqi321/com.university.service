package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.StatusCode;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.EduVersion;
import com.ovft.configure.sys.service.EduVersionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author vvtxw
 * @create 2019-08-28 18:15
 */

@RestController
@RequestMapping("/eduVersion")
public class EduVersionController {

    @Resource
    private EduVersionService eduVersionService;

    /**
     * 添加新版本
     *
     * @param eduVersion
     * @return
     */
    @PostMapping(value = "/insertversion")
    public WebResult insertversion(@RequestBody EduVersion eduVersion) {
        Integer res = eduVersionService.insertVersion(eduVersion);
        if (res > 0) {
            return new WebResult(StatusCode.OK, "添加成功", "");
        }
        return new WebResult(StatusCode.OK, "添加失败", "");
    }

    /**
     * 查看版本
     *v
     * @param id
     * @return
     */
    @GetMapping(value = "/selectVersion")
    public WebResult selectVersion(@RequestParam(value = "id", required = true) Integer id) {
        EduVersion eduVersion = eduVersionService.selectVersion(id);
       /* if (eduVersion.getVersionCode().equals(2.5)){随便写的 为什么不能判断相等
            return new WebResult(StatusCode.OK, "您当前已经是最新版本", "");
            测试pull
        }*/
        return new WebResult(StatusCode.OK, "查看版本成功", eduVersion);


    }



}
