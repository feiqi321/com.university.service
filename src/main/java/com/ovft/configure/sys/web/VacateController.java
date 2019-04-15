package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Vacate;
import com.ovft.configure.sys.service.VacateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class VacateController {

    @Autowired
    public VacateService vacateService;

    /**
     * 进入请假申请
     */
    @GetMapping("/vacate/into")
    public WebResult intoVacate(@RequestParam(value = "userId")Integer userId) {
        return  vacateService.intoVacate(userId);
    }

    /**
     * 请假申请
     * @param vacate
     * @return
     */
    @PostMapping(value = "/vacate/apply")
    public WebResult applyVacate(@RequestBody Vacate vacate)  {
        return  vacateService.applyVacate(vacate);
    }

    /**
     * 请假记录
     * @param userId
     * @return
     */
    @GetMapping(value = "/vacate/vacateList")
    public WebResult vacateList(@RequestParam(value = "userId")Integer userId)  {
        return  vacateService.vacateList(userId);
    }

}
