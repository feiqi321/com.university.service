package com.ovft.configure.config;

import com.ovft.configure.sys.service.EduArticleService;
import com.ovft.configure.sys.service.FileDownService;
import com.ovft.configure.sys.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
//@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class SaticScheduleTask {

    @Autowired
    public EduArticleService eduArticleService;
    @Autowired
    public TeacherService teacherService;
    @Autowired
    FileDownService fileDownService;

    //3.添加定时任务
    @Scheduled(cron = "0 0 1 * * ?")
    private void configureTasks() {
        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
        //文章置顶的定时任务
        try {
            eduArticleService.topScheduleTask();
        }catch (Exception e) {
            e.getMessage();
        }
        //下架过期课程
        try {
            teacherService.shelvesCourse();
        }catch (Exception e) {
            e.getMessage();
        }
        //定期删除文件
//        try {
//            fileDownService.deleteFile();
//        }catch (Exception e) {
//            e.getMessage();
//        }
    }
    private  void  configureTasksVote(){

    }
}