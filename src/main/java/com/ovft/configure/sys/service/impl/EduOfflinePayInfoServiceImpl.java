package com.ovft.configure.sys.service.impl;

import com.alibaba.druid.sql.visitor.functions.If;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ovft.configure.constant.Status;
import com.ovft.configure.sys.bean.EduOfflinePayInfo;
import com.ovft.configure.sys.bean.EduOfflinePayInfoExample;
import com.ovft.configure.sys.dao.EduOfflinePayInfoMapper;
import com.ovft.configure.sys.service.EduOfflinePayInfoService;
import com.ovft.configure.sys.vo.PageBean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author vvtxw
 * @create 2019-05-26 21:27
 */
@Service
public class EduOfflinePayInfoServiceImpl implements EduOfflinePayInfoService {
    @Resource
    private EduOfflinePayInfoMapper eduOfflinePayInfoMapper;

    @Override
    public Integer insertPayInfo(EduOfflinePayInfo eduOfflinePayInfo) {
        return eduOfflinePayInfoMapper.insertSelective(eduOfflinePayInfo);
    }

    @Override
    public PageBean queryAllOffInfo(Integer page, Integer size, Integer schoolId, String schoolName, String telephone, String payStatus) {
        Page<Object> pageAll = PageHelper.startPage(page, size);
        Map<String, Object> map = new HashMap<>();
        map.put("schoolName", schoolName);
        map.put("telephone", telephone);
        map.put("payStatus", payStatus);
        map.put("schoolId", schoolId);
        //显示线下的所有数据
        List<EduOfflinePayInfo> eduOfflinePayInfos = eduOfflinePayInfoMapper.selectOfflineList(map);
        //过滤要显示的数据
        List<EduOfflinePayInfo> showsInfo = new ArrayList<>();
        for (EduOfflinePayInfo eduOfflinePayInfo : eduOfflinePayInfos) {
            //查询出最新的下单信息保留它
            EduOfflinePayInfo showInfo = eduOfflinePayInfoMapper.queryShowOfflinePayInfo(eduOfflinePayInfo.getTelephone());
            //id与之不同则删除，相同则保留
            if (eduOfflinePayInfo.getId() != showInfo.getId()) {
                eduOfflinePayInfoMapper.deleteByPrimaryKey(eduOfflinePayInfo.getId());
            } else {
                showsInfo.add(showInfo);
            }
        }
        long total = pageAll.getTotal();
        return new PageBean(page, size, total, showsInfo);
    }


    @Override
    public List<EduOfflinePayInfo> queryAllOff(Integer schoolId, String schoolName, String telephone, String payStatus) {
        Map<String, Object> map = new HashMap<>();
        map.put("schoolName", schoolName);
        map.put("telephone", telephone);
        map.put("payStatus", payStatus);
        map.put("schoolId", schoolId);
        List<EduOfflinePayInfo> eduOfflinePayInfos = eduOfflinePayInfoMapper.selectOfflineList(map);
        return eduOfflinePayInfos;
    }

    @Override
    public Integer updatePayStatus(EduOfflinePayInfo eduOfflinePayInfo) {
        return eduOfflinePayInfoMapper.updateByPrimaryKeySelective(eduOfflinePayInfo);
    }

    @Override
    public Integer bathUpdataPaystatus(EduOfflinePayInfo eduOfflinePayInfo) {
        //查出所有课程的id
        List<EduOfflinePayInfo> eduOfflinePayInfos = eduOfflinePayInfoMapper.selectByExample(null);
        //批量修改
        int res = 0;
        for (EduOfflinePayInfo OfflinePayInfo : eduOfflinePayInfos) {
            eduOfflinePayInfo.setId(OfflinePayInfo.getId());
            res = eduOfflinePayInfoMapper.updateByPrimaryKeySelective(eduOfflinePayInfo);

        }
        return res;
    }


}
