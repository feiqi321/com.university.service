package com.ovft.configure.sys.service.impl;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.dao.EduCourseMapper;
import com.ovft.configure.sys.dao.TeacherMapper;
import com.ovft.configure.sys.dao.VacateMapper;
import com.ovft.configure.sys.service.TeacherService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName TeacherServiceImpl
 * @Author zqx
 * @Date 2019/4/11 10:15
 * @Version 1.0
 **/
@Service
public class TeacherServiceImpl implements TeacherService {

    @Resource
    public TeacherMapper teacherMapper;
    @Resource
    public VacateMapper vacateMapper;
    @Resource
    public EduCourseMapper courseMapper;

    /**
     * 请假申请列表
     * @param adminId
     * @return
     */
    @Override
    public WebResult vacateChackList(Integer adminId) {
        if(adminId == null) {
            return new WebResult("400", "请登录");
        }
        List<Map<String, Object>> maps = teacherMapper.seleceVacateByTeacherId(adminId);

        return new WebResult("200", "", maps);
    }

    /**
     * 添加课程
     * @param course
     * @return
     */
    @Transactional
    @Override
    public WebResult createCourse(HashMap<String, Object> course) {

        return null;
    }

    /**
     * 请假审批
     * @param map
     * @return
     */
    @Override
    public WebResult vacateApprover(HashMap<String, Object> map) {
        if(!map.containsKey("isCheck") || !map.containsKey("vacateId")) {
            return new WebResult("400","审批出错");
        }
        Integer isCheck = (Integer) map.get("isCheck");
        Integer vacateId = (Integer) map.get("vacateId");
        vacateMapper.updateCheck(vacateId, isCheck);
        return new WebResult("200","");
    }


}
