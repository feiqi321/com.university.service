package com.ovft.configure.sys.web;

import com.ovft.configure.constant.ConstantClassField;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Admin;
import com.ovft.configure.sys.bean.Video;
import com.ovft.configure.sys.bean.VideoItem;
import com.ovft.configure.sys.service.VideoService;
import com.ovft.configure.sys.utils.RedisUtil;
import com.ovft.configure.sys.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName VideoController 视频教学
 * @Author zqx
 * @Version 1.0
 **/

@RestController
@RequestMapping("/server/video")
public class VideoController {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private VideoService videoService;

    /**
     *  视频教学 列表
     * @param request
     * @return
     */
    @PostMapping(value = "/videoList")
    public WebResult videoList(HttpServletRequest request, @RequestBody PageVo pageVo) {
        String token = request.getHeader("token");
        Object o = redisUtil.get(token);
        if(o != null) {
            Integer id = (Integer) o;
            // 如果是pc端登录，更新token缓存失效时间
            redisUtil.expire(token, ConstantClassField.PC_CACHE_EXPIRATION_TIME);
            Admin hget =(Admin) redisUtil.hget(ConstantClassField.ADMIN_INFO, id.toString());
            if(hget.getRole() != 0) {
                pageVo.setSchoolId(hget.getSchoolId());
            }
            return videoService.videoList(pageVo);
        }else {
            return new WebResult("50012", "请先登录", "");
        }
    }

    /**
     * 添加/修改  视频教学
     * @param video
     * @return
     */
    @PostMapping(value = "/createVideo")
    public WebResult createVideo(HttpServletRequest request, @RequestBody Video video) {
        String token = request.getHeader("token");
        Object o = redisUtil.get(token);
        if(o != null) {
            Integer id = (Integer) o;
            // 如果是pc端登录，更新token缓存失效时间
            redisUtil.expire(token, ConstantClassField.PC_CACHE_EXPIRATION_TIME);
            Admin hget =(Admin) redisUtil.hget(ConstantClassField.ADMIN_INFO, id.toString());
            if(hget.getRole() != 0) {
                video.setSchoolId(hget.getSchoolId());
            }
            return videoService.createVideo(video);
        }else {
            return new WebResult("50012", "请先登录", "");
        }
    }

    /**
     * 进入 视频教学  修改页面
     * @param videoId
     * @return
     */
    @GetMapping(value = "/findVideo")
    public WebResult findVideo(@RequestParam(value = "videoId") Integer videoId) {
        return videoService.findVideo(videoId);
    }

    /**
     * 删除 视频教学
     * @param videoId
     * @return
     */
    @GetMapping(value = "/deleteVideo")
    public WebResult deleteVideo(@RequestParam(value = "videoId") Integer videoId) {
        return videoService.deleteVideo(videoId);
    }

    /**
     *  子表 视频教学 列表
     * @param videoId
     * @return
     */
    @GetMapping(value = "/videoItemList")
    public WebResult videoItemList(@RequestParam(value = "videoId") Integer videoId) {
        return videoService.videoItemList(null, videoId);
    }

    /**
     *  子表  添加/修改  视频教学
     * @param videoItem
     * @return
     */
    @PostMapping(value = "/createVideoItem")
    public WebResult createVideoItem(HttpServletRequest request, @RequestBody VideoItem videoItem) {
        return videoService.createItemVideo(videoItem);
    }

    /**
     * 子表 进入 视频教学  修改页面
     * @param itemId
     * @return
     */
    @GetMapping(value = "/findVideoItem")
    public WebResult findVideoItem(@RequestParam(value = "itemId") Integer itemId) {
        return videoService.findItemVideo(itemId);
    }

    /**
     * 子表 删除 视频教学
     * @param itemId
     * @return
     */
    @GetMapping(value = "/deleteVideoItem")
    public WebResult deleteVideoItem(@RequestParam(value = "itemId") Integer itemId) {
        return videoService.deleteItemVideo(itemId);
    }

}
