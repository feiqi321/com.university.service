package com.ovft.configure.sys.vo;

import com.ovft.configure.sys.bean.EduClass;
import com.ovft.configure.sys.bean.EduCourse;

import java.util.List;

/**
 * 课程的扩展类
 * @author vvtxw
 * @create 2019-04-14 7:53
 */
public class EduCourseVo extends EduCourse {
    private List<EduClass> classList;

    public List<EduClass> getClassList() {
        return classList;
    }

    public void setClassList(List<EduClass> classList) {
        this.classList = classList;
    }



}
