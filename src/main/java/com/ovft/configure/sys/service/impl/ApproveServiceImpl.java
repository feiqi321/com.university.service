package com.ovft.configure.sys.service.impl;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Approve;
import com.ovft.configure.sys.dao.ApproveMapper;
import com.ovft.configure.sys.service.ApproveService;
import com.ovft.configure.sys.service.FineCourseService;
import com.ovft.configure.sys.service.WorksShowService;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @ClassName ApproveServiceImpl
 * @Author zqx
 * @Version 1.0
 **/
@Service
public class ApproveServiceImpl implements ApproveService {

    private static final Logger logger = LoggerFactory.getLogger(ApproveServiceImpl.class);

    @Resource
    private ApproveMapper approveMapper;
    @Autowired
    private FineCourseService fineCourseService;
    @Autowired
    private WorksShowService worksShowService;


    @Override
    public WebResult approve(Integer userId, Integer typeId, Integer type) {
        //每天只能点赞一次
        Approve findApprove = approveMapper.selectByType(userId, typeId, type);

        Approve approve=new Approve();
        if(findApprove == null) {
            approve.setUserId(userId);
            approve.setType(type);
            approve.setTypeId(typeId);
            approve.setCount(1);
            approve.setDate(new Date());
            approveMapper.createApprove(approve);
        } else {
            //判断两个Date是不是同一天
//            SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
//            boolean equals = fmt.format(approve.getDate()).equals(fmt.format(new Date()));
            boolean samedate = DateUtils.isSameDay(findApprove.getDate(), new Date());
            if(samedate) {
                return new WebResult("400", "今天已点过赞了哟！", "");
            }
            findApprove.setDate(new Date());
            findApprove.setCount(approve.getCount() + 1);
            approveMapper.updateApproveCount(findApprove);
        }
        //todo   待添加各种类型的点赞总数
        //类型 1-精品课程， 2-学员风采， 3-教师风采
        if(type.equals(1)) {
            fineCourseService.addThumbup(typeId);
        }
        if(type.equals(2)) {
            worksShowService.addThumbup(typeId);
        }
        if(type.equals(3)) {
            worksShowService.addTeacherThumbup(typeId);
        }
        return new WebResult("200", "点赞成功", "");
    }
}
