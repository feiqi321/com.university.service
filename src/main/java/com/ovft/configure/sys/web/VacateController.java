package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Vacate;
import com.ovft.configure.sys.service.VacateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class VacateController {

    @Autowired
    public VacateService vacateService;

    /**
     * 进入请假申请
     */
    @GetMapping("/vacate/into")
    public WebResult intoVacate(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        return  vacateService.intoVacate(userId);
    }

    /**
     * 请假申请
     * @param vacate
     * @return
     */
    @PostMapping(value = "/vacate/apply")
    public WebResult applyVacate(HttpServletRequest request, @RequestBody Vacate vacate)  {
        Integer userId = (Integer) request.getAttribute("userId");
        vacate.setUserId(userId);
        return  vacateService.applyVacate(vacate);
    }

    /**
     * 请假记录
     * @param userId
     * @return
     */
    @GetMapping(value = "/vacate/vacateList")
    public WebResult vacateList(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        return  vacateService.vacateList(userId);
    }

}
