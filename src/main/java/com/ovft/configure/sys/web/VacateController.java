package com.ovft.configure.sys.web;

import com.jfinal.aop.Before;
import com.ovft.configure.config.CORSInterceptor;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Vacate;
import com.ovft.configure.sys.service.VacateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Before(CORSInterceptor.class)
@RestController
public class VacateController {

    @Autowired
    public VacateService vacateService;

    /**
     * 进入请假申请
     */
    @GetMapping("/vacate/into")
    public WebResult intoVacate(HttpServletRequest request) {
        String userId = request.getHeader("userId");
        String schoolId = request.getHeader("schoolId");
        return vacateService.intoVacate(Integer.valueOf(userId), Integer.valueOf(schoolId));
    }

    /**
     * 请假申请
     *
     * @param vacate
     * @return
     */
    @PostMapping(value = "/vacate/apply")
    public WebResult applyVacate(HttpServletRequest request, @RequestBody Vacate vacate) {
        String userId = request.getHeader("userId");
        vacate.setUserId(Integer.valueOf(userId));
        return vacateService.applyVacate(vacate);
    }

    /**
     * 请假记录
     *
     * @param userId
     * @return
     */
    @GetMapping(value = "/vacate/vacateList")
    public WebResult vacateList(HttpServletRequest request) {
        String userId = request.getHeader("userId");
        return vacateService.vacateList(Integer.valueOf(userId));
    }

}
