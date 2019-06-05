package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.StatusCode;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.EduOfflineNum;
import com.ovft.configure.sys.service.EduOfflineNumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vvtxw
 * @create 2019-06-04 18:03
 */

@RestController
@RequestMapping("OffOrdercourse")
public class EduOfflineNumController {

    @Autowired
    private EduOfflineNumService eduOfflineNumService;


    @PostMapping("delete")
    public WebResult deleteOffLineNumCourse(@RequestBody EduOfflineNum eduOfflineNum){
        Integer res = eduOfflineNumService.deleteOffLineNumCourse(eduOfflineNum);
        if (res>0){
            return  new WebResult(StatusCode.OK,"删除成功","");
        }
        return  new WebResult(StatusCode.ERROR,"删除失败","");




    }
}
