package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.service.CommentService;
import com.ovft.configure.sys.service.VideoService;
import com.ovft.configure.sys.utils.RedisUtil;
import com.ovft.configure.sys.vo.PageVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName MyVideoController 视频教学
 * @Author zqx
 * @Version 1.0
 **/

@RestController
@RequestMapping("/myVideo")
public class MyVideoController {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private VideoService videoService;
    @Autowired
    private CommentService commentService;

    /**
     *  视频教学 列表
     * @return
     */
    @PostMapping(value = "/myVideoList")
    public WebResult videoList(@RequestBody PageVo pageVo) {
        return videoService.videoList(pageVo);
    }

    /**
     * 进入 视频教学  页面
     * @param videoId
     * @return
     */
    @GetMapping(value = "/findMyVideo")
    public WebResult findVideo(HttpServletRequest request, @RequestParam(value = "videoId") Integer videoId) {
        String userId = request.getHeader("userId");
        return videoService.findMyVideo(videoId, userId);
    }

    /**
     *   视频教学 目录
     *
     * @param videoId
     * @return
     */
    @GetMapping(value = "/findMyVideoItemList")
    public WebResult findMyVideoItemList(HttpServletRequest request, @RequestParam(value = "videoId") Integer videoId) {
        String userId = request.getHeader("userId");
        if(StringUtils.isBlank(userId) || userId.equals("null")) {
            return new WebResult("400", "请登录！", "");
        }
        return videoService.videoItemList(Integer.valueOf(userId), videoId);
    }

    /**
     *   视频播放
     *
     * @param itemId
     * @return
     */
    @GetMapping(value = "/findMyVideoItem")
    public WebResult findMyVideoItem(HttpServletRequest request, @RequestParam(value = "itemId") Integer itemId) {
        String userId = request.getHeader("userId");
        if(StringUtils.isBlank(userId) || userId.equals("null")) {
            return new WebResult("400", "请登录！", "");
        }
        return videoService.videoItem(Integer.valueOf(userId), itemId);
    }

    /**
     *   视频教学 立即学习
     *
     * @param videoId
     * @return
     */
    @GetMapping(value = "/myVideoLearn")
    public WebResult myVideoLearn(HttpServletRequest request, @RequestParam(value = "videoId") Integer videoId) {
        String userId = request.getHeader("userId");
        if(StringUtils.isBlank(userId) || userId.equals("null")) {
            return new WebResult("400", "请登录！", "");
        }
        return videoService.myVideoLearn(videoId, Integer.valueOf(userId));
    }

    /**
     *   我的视频
     * @return
     */
    @GetMapping(value = "/myLearnList")
    public WebResult myLearnList(HttpServletRequest request) {
        String userId = request.getHeader("userId");
        if(StringUtils.isBlank(userId) || userId.equals("null")) {
            return new WebResult("400", "请登录！", "");
        }
        return videoService.myLearnList(Integer.valueOf(userId));
    }



}
