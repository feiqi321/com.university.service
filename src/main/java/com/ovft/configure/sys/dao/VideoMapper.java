package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.MyVideo;
import com.ovft.configure.sys.bean.Video;
import com.ovft.configure.sys.bean.VideoItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName VideoMapper
 * @Author zqx
 * @Version 1.0
 **/
@Mapper
public interface VideoMapper {

    public Video selectById(@Param("videoId") Integer videoId);

    public List<Video> selectList(@Param("schoolId") Integer schoolId, @Param("isFree") Integer isFree);

    public void create(Video video);

    public void update(Video video);

    public void delete(@Param("videoId") Integer videoId);

    /**
     *  细单
     * @param itemId
     * @return
     */
    public VideoItem selectItemById(@Param("itemId") Integer itemId);

    public List<VideoItem> selectItemList(@Param("videoId") Integer videoId);

    public void createItem(VideoItem videoItem);

    public void updateItem(VideoItem videoItem);

    public void deleteItem(@Param("itemId") Integer itemId);

    public void deleteItemByVideoId(@Param("videoId") Integer videoId);

    /**
     * 我的视频
     */
    public void createMyVideo(MyVideo myVideo);

    public void updateMyVideo(MyVideo myVideo);

    public List<MyVideo> selectMyVideo(@Param("userId") Integer userId, @Param("videoId") Integer videoId);

    public List<MyVideo> myLearnList(@Param("userId") Integer userId);
}
