package com.ovft.configure.sys.vo;

import com.ovft.configure.sys.bean.Video;
import com.ovft.configure.sys.bean.VideoItem;

import java.util.List;

/**
 * 课程的扩展类
 *
 * @author vvtxw
 * @create 2019-04-14 7:53
 */
public class VideoVo extends Video {

    private List<VideoItem> itemList;

    public List<VideoItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<VideoItem> itemList) {
        this.itemList = itemList;
    }
}
