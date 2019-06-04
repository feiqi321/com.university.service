package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Video;
import com.ovft.configure.sys.bean.VideoItem;
import com.ovft.configure.sys.vo.PageVo;

public interface VideoService {

    public WebResult videoList(PageVo pageVo);

    public WebResult createVideo(Video video);

    public WebResult findVideo(Integer videoId);

    public WebResult deleteVideo(Integer videoId);

    /**
     * 细单
     * @return
     */
    public WebResult videoItemList(Integer userId, Integer videoId);

    public WebResult createItemVideo(VideoItem videoItem);

    public WebResult findItemVideo(Integer itemId);

    public WebResult deleteItemVideo(Integer itemId);

    /**
     * app
     */
    public WebResult findMyVideo(Integer videoId, String userId);

    public WebResult myVideoLearn(Integer videoId, Integer userId);

    public WebResult myLearnList(Integer userId);

    public WebResult videoItem(Integer userId, Integer itemId);
}
