package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.FineCourse;
import com.ovft.configure.sys.vo.PageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName FineCourseMapper
 * @Author zqx
 * @Version 1.0
 **/
@Mapper
public interface FineCourseMapper {

    public FineCourse selectById(@Param("fineId") Integer fineId);

    public List<FineCourse> selectFineCourseList(PageVo pageVo);

    public void createFineCourse(FineCourse fineCourse);

    public void updateFineCourse(FineCourse fineCourse);

    public void deleteFineCourse(@Param("fineId") Integer fineId);

    public void updatVisitOrThumbup(@Param("fineId") Integer fineId, @Param("visits") Integer visits, @Param("thumbup") Integer thumbup);
}
