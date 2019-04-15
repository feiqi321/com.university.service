package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Vacate;
import org.springframework.stereotype.Service;

@Service
public interface VacateService {
    public WebResult applyVacate(Vacate vacate);

    public WebResult intoVacate(Integer userId);

    public WebResult vacateList(Integer userId);
}
