package com.ovft.configure.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.dao.EduArticleMapper;
import com.ovft.configure.sys.service.MessageService;
import com.ovft.configure.sys.vo.EduArticleVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName MessageService
 * @Author zqx
 * @Date 2019/4/15 16:01
 * @Version 1.0
 **/
@Service
public class MessageServiceImpl implements MessageService {

    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

    @Resource
    public EduArticleMapper eduArticleMapper;

    @Override
    public WebResult findMessage(Integer userId, Integer schoolId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum==null?0:pageNum, pageSize==null?10:pageSize);
        List<EduArticleVo> noticeList =  eduArticleMapper.findNoticeAll(null, schoolId, null, null);
        PageInfo pageInfo = new PageInfo<>(noticeList);
        return new WebResult("200", "查询成功", pageInfo);
    }
}
