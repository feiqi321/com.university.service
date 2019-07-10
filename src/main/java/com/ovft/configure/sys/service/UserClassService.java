package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.UserClass;
import com.ovft.configure.sys.vo.UserClassVo;

/**
 * @ClassName UserClassService 学员班级
 * @Author xzy
 * @Version 2.5
 **/
public interface UserClassService {
      public WebResult deleteUserClass(Integer classId);
      public WebResult updateUserClass(UserClass userClass);
      public WebResult userClassList(UserClassVo userClassVo);

}
