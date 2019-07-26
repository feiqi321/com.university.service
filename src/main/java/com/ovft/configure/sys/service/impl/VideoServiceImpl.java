package com.ovft.configure.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.MyVideo;
import com.ovft.configure.sys.bean.Video;
import com.ovft.configure.sys.bean.VideoItem;
import com.ovft.configure.sys.dao.VideoMapper;
import com.ovft.configure.sys.service.CollectService;
import com.ovft.configure.sys.service.VideoService;
import com.ovft.configure.sys.vo.PageVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ClassName VideoServiceImpl
 * @Author zqx
 * @Version 1.0
 **/
@Service
public class VideoServiceImpl implements VideoService {

    private static final Logger logger = LoggerFactory.getLogger(VideoServiceImpl.class);

    @Resource
    private VideoMapper videoMapper;
    @Resource
    private CollectService collectService;

    @Override
    public WebResult videoList(PageVo pageVo) {
        if (pageVo.getPageSize() == 0) {
            List<Video> videos = videoMapper.selectList(pageVo.getSchoolId(), pageVo.getIsFree());
            return new WebResult("200", "查询成功", videos);
        }
        PageHelper.startPage(pageVo.getPageNum(), pageVo.getPageSize());
        List<Video> videos = videoMapper.selectList(pageVo.getSchoolId(), pageVo.getIsFree());
        PageInfo pageInfo = new PageInfo<>(videos);

        return new WebResult("200", "查询成功", pageInfo);
    }

    @Transactional
    @Override
    public WebResult createVideo(Video video) {
        if(video.getSchoolId() == null) {
            return new WebResult("400", "请选择学校", "");
        }
        if(StringUtils.isBlank(video.getTitle())) {
            return new WebResult("400", "标题不能为空", "");
        }
        if(StringUtils.isBlank(video.getContent())) {
            return new WebResult("400", "视频介绍不能为空", "");
        }
        if(StringUtils.isBlank(video.getCover())) {
            return new WebResult("400", "请上传视频封面", "");
        }
        if(StringUtils.isBlank(video.getTeacher())) {
            return new WebResult("400", "教师不能为空", "");
        }
        if(video.getIsFree() == null) {
            return new WebResult("400", "请选择视频是否免费", "");
        }
        if(video.getIsFree().equals(1) && video.getPrice() == null) {
            return new WebResult("400", "视频价格不能为空", "");
        }
        if(video.getVideoId() == null) {
            video.setVisits(0);
            videoMapper.create(video);
            return new WebResult("200", "保存成功", "");
        } else {
            videoMapper.update(video);
            return new WebResult("200", "修改成功", "");
        }
    }

    @Override
    public WebResult findVideo(Integer videoId) {
        Video video = videoMapper.selectById(videoId);
        return new WebResult("200", "查询成功", video);
    }

    @Transactional
    @Override
    public WebResult deleteVideo(Integer videoId) {
        List<MyVideo> myVideos = videoMapper.selectMyVideo(null, videoId);
        if(myVideos.size() != 0) {
            return new WebResult("400", "该视频已有人学习，不能删除", "");
        }
        videoMapper.deleteItemByVideoId(videoId);
        videoMapper.delete(videoId);
        return new WebResult("200", "删除成功", "");
    }

    //视频教学 目录
    //子表 视频教学 列表
    @Override
    public WebResult videoItemList(Integer userId, Integer videoId) {
        Video video = videoMapper.selectById(videoId);
        List<VideoItem> videoItemList = videoMapper.selectItemList(videoId);
        if(userId != null) {
            videoItemList.forEach(videoItem -> {
                videoItem.setVideo(null);
                videoItem.setTotalTitle(video.getTitle());
            });
            List<MyVideo> myVideos = videoMapper.selectMyVideo(userId, videoId);
            if(myVideos.size()!=0) {
                isLearn(myVideos.get(0).getItems(), videoItemList);
            }
        }
        return new WebResult("200", "查询成功", videoItemList);
    }

    @Transactional
    @Override
    public WebResult createItemVideo(VideoItem videoItem) {
        if(videoItem.getVideoId() == null) {
            return new WebResult("400", "请选择需要添加的视频ID", "");
        }
        if(StringUtils.isBlank(videoItem.getTitle())) {
            return new WebResult("400", "标题不能为空", "");
        }
        if(StringUtils.isBlank(videoItem.getVideo())) {
            return new WebResult("400", "请上传视频", "");
        }
        if(videoItem.getCount() == null) {
            return new WebResult("400", "课时不能为空", "");
        }
        if(videoItem.getIsTest() == null) {
            return new WebResult("400", "请选择是否试听", "");
        }

        if(videoItem.getItemId() == null) {
            videoMapper.createItem(videoItem);
            return new WebResult("200", "保存成功", "");
        } else {
            videoMapper.updateItem(videoItem);
            return new WebResult("200", "修改成功", "");
        }
    }

    @Override
    public WebResult findItemVideo(Integer itemId) {
        VideoItem videoItem = videoMapper.selectItemById(itemId);
        return new WebResult("200", "查询成功", videoItem);
    }

    @Transactional
    @Override
    public WebResult deleteItemVideo(Integer itemId) {
        videoMapper.deleteItem(itemId);
        return new WebResult("200", "删除成功", "");
    }

    @Transactional
    @Override
    public WebResult findMyVideo(Integer videoId, String userId) {
        Video video = videoMapper.selectById(videoId);
        video.setVisits(video.getVisits() + 1);
        videoMapper.update(video);

        if(StringUtils.isBlank(userId) || userId.equals("null")) {
            video.setIsCollect(0);
            video.setIsLearn(0);
        } else {
            Integer collect = collectService.isCollect(Integer.valueOf(userId), 1, videoId);
            video.setIsCollect(collect);
            List<MyVideo> myVideos = videoMapper.selectMyVideo(Integer.valueOf(userId), videoId);
            video.setIsLearn(myVideos.size());
        }
        return new WebResult("200", "查询成功", video);
    }

    //立即学习
    @Transactional
    @Override
    public WebResult myVideoLearn(Integer videoId, Integer userId) {
        List<MyVideo> myVideos = videoMapper.selectMyVideo(userId, videoId);
        List<VideoItem> videoItemList = videoMapper.selectItemList(videoId);
        if(videoItemList.size() == 0) {
            return new WebResult("400", "暂无视频", "");
        }
        Video video = videoMapper.selectById(videoId);
        if(myVideos.size() == 0) {
            if(video.getIsFree().equals(1)) {
                return new WebResult("400", "请购买该视频", "");
            }
            MyVideo myVideo = new MyVideo();
            myVideo.setUserId(userId);
            myVideo.setVideoId(videoId);
            myVideo.setDate(new Date());
            myVideo.setItems(videoItemList.get(0).getItemId()+"");
            videoMapper.createMyVideo(myVideo);
            myVideos.add(myVideo);
        }
        VideoItem lastVideoItem = findLastVideoItem(myVideos.get(0));
        lastVideoItem.setTotalTitle(video.getTitle());
        return new WebResult("200", "查询成功", lastVideoItem);
    }

    //我的视频
    @Override
    public WebResult myLearnList(PageVo pageVo) {
        if (pageVo.getPageSize() == 0) {
            List<MyVideo> myVideos = videoMapper.myLearnList(pageVo.getUserId());
            return new WebResult("200", "查询成功", myVideos);
        }
        PageHelper.startPage(pageVo.getPageNum(), pageVo.getPageSize());
        List<MyVideo> myVideos = videoMapper.myLearnList(pageVo.getUserId());
        PageInfo pageInfo = new PageInfo<>(myVideos);
        return new WebResult("200", "查询成功", pageInfo);
    }

    //视频播放
    @Transactional
    @Override
    public WebResult videoItem(Integer userId, Integer itemId) {
        VideoItem videoItem = videoMapper.selectItemById(itemId);

        Integer videoId = videoItem.getVideoId();
        Video video = videoMapper.selectById(videoId);
        videoItem.setTotalTitle(video.getTitle());
        List<MyVideo> myVideos = videoMapper.selectMyVideo(userId, videoId);
        if(myVideos.size() == 0) {
            // 是否免费   0-免费， 1-收费
            if(video.getIsFree().equals(1)) {
                //是否试听    0-非试听 1-试听
                if(videoItem.getIsTest().equals(0)) {
                    return new WebResult("400", "请购买该视频", "");
                }
                return new WebResult("200", "查询成功", videoItem);
            }
            MyVideo myVideo = new MyVideo();
            myVideo.setUserId(userId);
            myVideo.setVideoId(videoId);
            myVideo.setDate(new Date());
            myVideo.setItems(videoItem.getItemId()+"");
            videoMapper.createMyVideo(myVideo);
            return new WebResult("200", "查询成功", videoItem);
        }
        //指定item进入， 添加到学习过的视频
        addLearnVideo(videoItem.getItemId(), myVideos.get(0));
        return new WebResult("200", "查询成功", videoItem);
    }

    /**
     * 判断视频是否学习过该视频
     *  @param items
     * @param itemList
     */
    private void isLearn(String items, List<VideoItem> itemList) {
        String[] split = items.split(",");
        itemList.forEach(item -> {
            item.setIsLearn(0);
            for(String id : split){
                if(id.equals(item.getItemId()+"")){
                    item.setIsLearn(1);
                    break;
                }
            }
        });
    }

    /**
     * 寻找最后一个观看的视频
     * @return
     */
    private VideoItem findLastVideoItem(MyVideo myVideo) {
        String items = myVideo.getItems();
        List<VideoItem> videoItemList = videoMapper.selectItemList(myVideo.getVideoId());
        if(videoItemList.size() == 0) {
            return null;
        }
        if(StringUtils.isBlank(items)) {
            VideoItem videoItem = videoItemList.get(0);
            myVideo.setItems(videoItem.getItemId() + "");
            videoMapper.updateMyVideo(myVideo);
            return videoItem;
        }
        String[] ids = items.split(",");
        Integer id =Integer.valueOf(ids[ids.length - 1]);
        VideoItem videoItem = videoMapper.selectItemById(id);
        if(videoItem == null) {
            videoItem = videoItemList.get(0);
        }
        return videoItem;
    }

    /**
     * 添加学习过的视频
     */
    private void addLearnVideo(Integer itemId, MyVideo myVideo) {
        java.lang.String itemIdStr = itemId + "";
        String items = myVideo.getItems();
        if(StringUtils.isBlank(items)) {
            myVideo.setItems(itemIdStr);
            videoMapper.updateMyVideo(myVideo);
            return;
        }
        String[] ids = items.split(",");
        StringBuilder str = new StringBuilder();
        for (int i =0; i < ids.length; i++) {
            if(!ids[i].equals(itemIdStr)){
                str.append(ids[i]+",");
            }
        }
        str.append(itemIdStr);
        myVideo.setItems(str.toString());
        videoMapper.updateMyVideo(myVideo);
    }

}
