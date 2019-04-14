package com.ovft.configure.sys.service.impl;

import com.ovft.configure.sys.bean.CourseManager;
import com.ovft.configure.sys.dao.CourseManagerMapper;
import com.ovft.configure.sys.service.CourseManagerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author vvtxw
 * @create 2019-04-14 18:03
 */
@Service
public class CourseManagerServiceImpl implements CourseManagerService {
    @Resource
    private CourseManagerMapper courseManagerMapper;

    @Override
    public List<CourseManager> queryAllCourseNotice() {
        return courseManagerMapper.selectByExample(null);
    }
}
