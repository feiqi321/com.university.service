package com.ovft.configure.sys.service.impl;

import com.ovft.configure.sys.bean.EduPayrecord;
import com.ovft.configure.sys.bean.EduPayrecordExample;
import com.ovft.configure.sys.dao.EduPayrecordMapper;
import com.ovft.configure.sys.service.EduPayrecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author vvtxw
 * @create 2019-05-29 17:43
 */
@Service
public class EduPayrecordServiceImpl
        implements EduPayrecordService {
    @Resource
    private EduPayrecordMapper eduPayrecordMapper;

    @Override
    public Integer insertPayRecord(EduPayrecord eduPayrecord) {
        return eduPayrecordMapper.insertSelective(eduPayrecord);
    }

    @Override
    public List<EduPayrecord> queryAllRecordByUserId(Integer userId) {
        EduPayrecordExample eduPayrecordExample = new EduPayrecordExample();
        eduPayrecordExample.createCriteria().andUserIdEqualTo(userId);
        List<EduPayrecord> eduPayrecords = eduPayrecordMapper.selectByExample(eduPayrecordExample);
        return eduPayrecords;
    }

    @Override
    public void deleteAllrecord(Integer id) {
        eduPayrecordMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer deleterecordByCourseId(Integer courseId) {
        EduPayrecordExample eduPayrecordExample = new EduPayrecordExample();
        eduPayrecordExample.createCriteria().andCourseIdEqualTo(courseId);
        int i = eduPayrecordMapper.deleteByExample(eduPayrecordExample);
        return i;
    }
    @Transactional
    @Override
    public int updateIsDeleteStaues(EduPayrecord eduPayrecord) {
       return     eduPayrecordMapper.updateIsDeleteStaues(eduPayrecord);
    }

    @Override
    public List<EduPayrecord> selectByUserIdAndIsdelete(EduPayrecord eduPayrecord) {
        return eduPayrecordMapper.selectByUserIdAndIsdelete(eduPayrecord);
    }
}
