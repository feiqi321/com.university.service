package com.ovft.configure.sys.service.impl;

import com.ovft.configure.sys.bean.EduOfflineNum;
import com.ovft.configure.sys.bean.EduOfflineNumExample;
import com.ovft.configure.sys.dao.EduOfflineNumMapper;
import com.ovft.configure.sys.service.EduOfflineNumService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author vvtxw
 * @create 2019-06-04 15:16
 */
@Service
public class EduOfflineNumServiceImpl implements EduOfflineNumService {

    @Resource
    private EduOfflineNumMapper eduOfflineNumMapper;

    @Override
    public List<EduOfflineNum> queryAllCourse(Integer userId, Integer courseId) {
        EduOfflineNumExample eduOfflineNumExample = new EduOfflineNumExample();
        eduOfflineNumExample.createCriteria().andUserIdEqualTo(userId).andCourseIdEqualTo(courseId);
        List<EduOfflineNum> eduOfflineNums = eduOfflineNumMapper.selectByExample(eduOfflineNumExample);
        return eduOfflineNums;
    }

    @Override
    public int addOfflineNum(EduOfflineNum eduOfflineNum) {
        return eduOfflineNumMapper.insertSelective(eduOfflineNum);
    }

    @Override
    public Integer deleteOffLineNumCourse(EduOfflineNum eduOfflineNum) {
        return eduOfflineNumMapper.deleteByPrimaryKey(eduOfflineNum.getId());
    }
}
