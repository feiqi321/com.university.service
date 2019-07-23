package com.ovft.configure.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Suggestions;
import com.ovft.configure.sys.dao.SuggestionsMapper;
import com.ovft.configure.sys.service.SuggestionsService;

import com.ovft.configure.sys.vo.SuggestionsVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/*
   意见反馈实现类
 */
@Service
public class SuggestionsServiceImpl implements SuggestionsService {
      @Resource
      private SuggestionsMapper suggestionsMapper;

    @Transactional
    @Override
    public WebResult submitSuggestions(Suggestions suggestions) {
        if (suggestions.getType()==null){

            return new WebResult("200","请选择反馈问题类型！","");
        }
        if (StringUtils.isBlank(suggestions.getContent())){
            return new WebResult("200","反馈意见不能为空！","");
        }
        Date date=new Date();
        suggestions.setCreateTime(date);
        suggestionsMapper.submitSuggestions(suggestions);

        return new WebResult("200","提交成功","");
    }

    @Override
    public WebResult SuggestionsList(SuggestionsVo suggestionsVo) {
        if (suggestionsVo.getPageSize() == 0) {
          List<Suggestions> suggestionsList = suggestionsMapper.SuggestionsList(suggestionsVo);
            return new WebResult("200", "查询成功", suggestionsList);
        }
        PageHelper.startPage(suggestionsVo.getPageNum(), suggestionsVo.getPageSize());
        List<Suggestions> suggestionsList = suggestionsMapper.SuggestionsList(suggestionsVo);
        PageInfo pageInfo = new PageInfo<>(suggestionsList);
        return new WebResult("200", "查询成功", pageInfo);
    }

    @Override
    public WebResult deleteSuggestions(Integer id) {
         suggestionsMapper.deleteSuggestions(id);
        return new WebResult("200", "删除成功", "");
    }

    @Override
    public WebResult bigDeleteSuggestions(Integer[] ids) {
        suggestionsMapper.bigDeleteSuggestions(ids);
        return new WebResult("200", "批量删除成功", "");
    }
}
