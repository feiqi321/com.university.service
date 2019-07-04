package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.EduFlowers;

/**
 * @author zqx
 * @since 2019-07-04
 */
public interface EduFlowersService {

    public WebResult totalFlower(Integer userId);

    public WebResult flowerList(Integer userId);

    public void createFlower(EduFlowers eduFlowers);
}
