package com.ovft.configure.sys.service.impl;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Dict;
import com.ovft.configure.sys.bean.EduCourse;
import com.ovft.configure.sys.bean.User;
import com.ovft.configure.sys.bean.Vacate;
import com.ovft.configure.sys.dao.DictMapper;
import com.ovft.configure.sys.dao.VacateMapper;
import com.ovft.configure.sys.service.VacateService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * @ClassName SchoolServiceImpl
 * @Author zqx
 * @Date 2019/4/11 10:15
 * @Version 1.0
 **/
@Service
public class VacateServiceImpl implements VacateService {

    @Resource
    public VacateMapper vacateMapper;
    @Resource
    public DictMapper dictMapper;

    /**
     * 请假申请
     * @param vacate
     * @return
     */
    @Transactional
    @Override
    public WebResult applyVacate(Vacate vacate) {
        if(StringUtils.isBlank(vacate.getVacateName())) {
            return new WebResult("400", "请假人姓名不能为空");
        }
        if(StringUtils.isBlank(vacate.getContactsPhone()) || StringUtils.isBlank(vacate.getContacts())) {
            return new WebResult("400", "联系人电话或姓名不能为空");
        }
        if(vacate.getCourseId() == null) {
            return new WebResult("400", "请选择课程");
        }
        
        if(vacate.getVacateTime() == null) {
            return new WebResult("400", "请选择请假时间");
        }
        //是否同意  0-不同意，1-同意，2-未审核
        vacate.setIsCheck(2);
        vacate.setCreateTime(new Date());
        vacateMapper.applyVacate(vacate);

        return new WebResult("200", "");
    }

    /**
     * 进入请假页面
     * @param userId
     * @return
     */
    @Override
    public WebResult intoVacate(Integer userId) {
        HashMap<String, Object> map = new HashMap<>();
        //todo   根据id查找user
        User user = new User();
        user.setUserId(6);
        user.setUserName("17764020416");
        //todo   获取课程列表
        LinkedList<EduCourse> courseList = new LinkedList<>();
        EduCourse course = new EduCourse();
        course.setCourseId(1);
        course.setCourseName("针灸治疗学");
        courseList.push(course);

        //获取请假类型VACATE_TYPE
        List<Dict> dicts = dictMapper.selectByDictType("VACATE_TYPE");

        map.put("user", user);
        map.put("course", courseList);
        map.put("dicts", dicts);

        return new WebResult("200", "", map);
    }

    /**
     * 请假记录
     * @param userId
     * @return
     */
    @Override
    public WebResult vacateList(Integer userId) {
        if(userId == null) {
            return new WebResult("400", "请登录");
        }
        List<Map<String, Object>> maps = vacateMapper.selectByUserId(userId);

        return new WebResult("200", "", maps);
    }


}
