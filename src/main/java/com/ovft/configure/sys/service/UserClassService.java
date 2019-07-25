package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.UserClass;
import com.ovft.configure.sys.vo.MyCourseAll;
import com.ovft.configure.sys.vo.UserClassVo;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @ClassName UserClassService 学员班级
 * @Author xzy
 * @Version 2.5
 **/
public interface UserClassService {
      public WebResult deleteUserClass(Integer classId);          //删除对应班级记录
      public WebResult updateUserClass(UserClass userClass);
      public WebResult userClassList(UserClassVo userClassVo);
      public WebResult classdeleteUser(MyCourseAll myCourseAll);    //删除对应班级的学员
}
