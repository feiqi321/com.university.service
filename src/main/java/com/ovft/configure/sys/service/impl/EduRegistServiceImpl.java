package com.ovft.configure.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ovft.configure.constant.ConstantClassField;
import com.ovft.configure.constant.Status;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.EduCourse;
import com.ovft.configure.sys.bean.EduRegist;
import com.ovft.configure.sys.bean.EduRegistExample;
import com.ovft.configure.sys.dao.EduCourseMapper;
import com.ovft.configure.sys.dao.EduRegistMapper;
import com.ovft.configure.sys.dao.SchoolMapper;
import com.ovft.configure.sys.service.EduRegistService;
import com.ovft.configure.sys.vo.CoditionVo;
import com.ovft.configure.sys.vo.PageBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author vvtxw
 * @create 2019-04-29 10:03
 */
@Service
public class EduRegistServiceImpl implements EduRegistService {

    @Resource
    private EduRegistMapper eduRegistMapper;
    @Resource
    private EduCourseMapper eduCourseMapper;
    @Resource
    private SchoolMapper schoolMapper;

    /**
     * 添加报名条件
     *
     * @param eduRegist
     * @return
     */
    @Transactional
    @Override
    public int addRegistCondition(EduRegist eduRegist) {
        //添加全局设置
        //1.查询出是否有已经全局的设置
        EduRegistExample eduRegistExample0 = new EduRegistExample();
        eduRegistExample0.createCriteria().andCourseIdEqualTo(eduRegist.getCourseId()).andSchoolIdEqualTo(eduRegist.getSchoolId());
        List<EduRegist> eduRegistA = eduRegistMapper.selectByExample(eduRegistExample0);

        if (eduRegistA.size() != 0) {
            return -2;
        }
        //判断该学校是否已经上架的课程。如果没有提示信息
        //添加全局条件之前要判断是否有全局的课程
        //根据学校查出所有的课程设置
        EduRegistExample eduRegistExample = new EduRegistExample();
        eduRegistExample.createCriteria().andSchoolIdEqualTo(eduRegist.getSchoolId());
        List<EduRegist> eduRegists = eduRegistMapper.selectByExample(eduRegistExample);

        if (eduRegists.size() == 0) {
            return -3;
        }
        int i = eduRegistMapper.insertSelective(eduRegist);

        if (i > 0) {
            updateAllCondition(eduRegist);
        }
        return i;
    }

    //查询全部
    @Override
    public WebResult queryAllCodition2(CoditionVo coditionVo) {

        if (coditionVo.getPageSize() == 0) {
            coditionVo.setCourseId(0);
            List<EduRegist> eduRegists = eduRegistMapper.CourseCoditionAll(coditionVo);
            UpDateSchoolName(eduRegists);
            return new WebResult("200", "查询成功", eduRegists);
        }
        PageHelper.startPage(coditionVo.getPageNum(), coditionVo.getPageSize());
        coditionVo.setCourseId(0);
        List<EduRegist> eduRegists = eduRegistMapper.CourseCoditionAll(coditionVo);
        UpDateSchoolName(eduRegists);
        PageInfo pageInfo = new PageInfo<>(eduRegists);
        return new WebResult("200", "查询成功", pageInfo);
    }

    /**
     * 全查询条件
     *
     * @param size
     * @param page
     * @return
     */
    @Override
    public PageInfo queryAllCodition(Integer size, Integer page) {

        PageHelper.startPage(page, size);
        EduRegistExample eduRegistExample = new EduRegistExample();
        eduRegistExample.createCriteria().andCourseIdNotEqualTo(0);
        List<EduRegist> eduRegists = eduRegistMapper.selectByExample(eduRegistExample);
        UpDateSchoolName(eduRegists);
        PageInfo pageInfo = new PageInfo<>(eduRegists);

        return pageInfo;
    }


    /**
     * 按学校分页查询条件
     *
     * @param size
     * @param page
     * @return
     */
    @Override
    @Transactional
    public PageInfo queryAllCoditionBySchoold(Integer size, Integer page, Integer schoolId) {
        //1.查询设置全部条件的条件
//        PageHelper.startPage(page, size);
        EduRegistExample eduRegistExample = new EduRegistExample();
        eduRegistExample.createCriteria().andSchoolIdEqualTo(schoolId).andCourseIdEqualTo(Status.ALLCOURSE);
        List<EduRegist> eduRegists = eduRegistMapper.selectByExample(eduRegistExample);
        PageInfo pageInfo = new PageInfo<>(eduRegists);
        //2.查询学校所有课程的id及报名课程名字
//        PageHelper.startPage(page, size);
        EduCourse course = new EduCourse();
        course.setSchoolId(String.valueOf(schoolId));
        course.setIsenable(ConstantClassField.ISONLINE);
        List<EduCourse> eduCourses = eduCourseMapper.listCourseCategoryByShoolId(course);
        PageInfo pageInfo2 = new PageInfo<>(eduCourses);
        //3.查询出条件表对应的课程
//         PageHelper.startPage(page, size);
        EduRegistExample eduRegistExample3 = new EduRegistExample();
        eduRegistExample3.createCriteria().andSchoolIdEqualTo(schoolId).andCourseIdNotEqualTo(0);
        List<EduRegist> eduRegistsNum = eduRegistMapper.selectByExample(eduRegistExample3);
        PageInfo pageInfo3 = new PageInfo<>(eduRegistsNum);
        //4.第一次的查询和第二次的查询不同，则删除之前的
//        EduCourse course2 = new EduCourse();
//        course.setSchoolId(String.valueOf(schoolId));
//        course.setIsenable(ConstantClassField.ISONLINE);
//        List<EduCourse> eduCourses2 = eduCourseMapper.listCourseCategoryByShoolId(course);
//        PageInfo pageInfo22 = new PageInfo<>(eduCourses);
        if (eduCourses.size() != eduRegistsNum.size()) {  //当eduCourses新增或删除时
//            for (EduCourse eduCours : eduCourses) {
//                //删除所有的条件记录课程
            EduRegistExample eduRegistExample4 = new EduRegistExample();
            //  eduRegistExample4.createCriteria().andCourseIdEqualTo(eduCours.getCourseId());

            eduRegistExample4.createCriteria().andCourseIdNotEqualTo(0).andSchoolIdEqualTo(schoolId).andRegistPriorityEqualTo(String.valueOf(Status.PRIORITYTWO));//.andRegistPriorityEqualTo(String.valueOf(Status.PRIORITYTWO))
            eduRegistMapper.deleteByExample(eduRegistExample4);
        }
//        }
        PageHelper.startPage(page, size);
        //5.查询所有课程的条件列表
        EduRegistExample eduRegistExample2 = new EduRegistExample();
        //6.根据查询所有全部设置的课程
        eduRegistExample2.createCriteria().andSchoolIdEqualTo(schoolId).andCourseIdNotEqualTo(0).andRegistPriorityEqualTo(String.valueOf(Status.PRIORITYTWO));

        List<EduRegist> eduRegistsOld = eduRegistMapper.selectByExample(eduRegistExample2);
        PageInfo pageInfo4 = new PageInfo<>(eduRegistsOld);

        //6.如果条件课程列表为空
        if (eduRegistsOld.size() == 0) {    //待优化
            //重新插入，生成新的的条件列表
            PageInfo pageInfo5 = getEduRegists(schoolId, eduCourses);
            List<EduRegist> eduRegistsNew = pageInfo5.getList();
            for (EduRegist eduRegist : eduRegistsNew) {      //todo  待优化
                if (eduRegists.size() != 0) {
                    updateAllConditions(eduRegist);
                }
            }
            PageHelper.startPage(page, size);
            //3.查询按照全部模版设置默认原始条件列表
            EduRegistExample eduRegistExample1 = new EduRegistExample();
            eduRegistExample1.createCriteria().andSchoolIdEqualTo(schoolId).andRegistPriorityEqualTo(String.valueOf(Status.PRIORITYTWO));
            List<EduRegist> eduRegistsNew2 = eduRegistMapper.selectByExample(eduRegistExample1);
            PageInfo pageInfo6 = new PageInfo<>(eduRegistsNew2);
            UpDateSchoolName(eduRegistsNew2);

            return pageInfo6;
        }

        //有直接展示报名条件列表
        //如果总条件查询不为空，添加进去
        if (eduRegists.size() != 0)
            for (EduRegist eduRegist : eduRegists) {
//                eduRegistsOld.add(eduRegist);
            }


        return pageInfo4;
    }

    //生成默认的条件列表
    private PageInfo getEduRegists(Integer schoolId, List<EduCourse> eduCourses) {
        //2.插入到数据库表
        EduRegistExample eduRegistExample = new EduRegistExample();
        eduRegistExample.createCriteria().andSchoolIdEqualTo(schoolId).andRegistPriorityEqualTo(String.valueOf(Status.PRIORITYONE));
        List<EduRegist> eduRegistsNew2 = eduRegistMapper.selectByExample(eduRegistExample);
//        for (EduCourse eduCours2 : eduCourses) {
//            for (EduRegist eduRegist : eduRegistsNew2) {
//                if (eduRegist.getCourseId().equals(eduCours2.getCourseId())) {
////                        eduRegistMapper.insertSelective(eduRegist);
////                          continue OK;
//                    eduCourses.remove(eduCours2);
////                    }
//
//                }
//
//            }
            for (EduCourse eduCours : eduCourses) {
                EduRegist eduRegist = new EduRegist();
                eduRegist.setCourseId(eduCours.getCourseId());
                eduRegist.setCourseName(eduCours.getCourseName());
                eduRegist.setSchoolId(schoolId);
                eduRegist.setRegistPriority(String.valueOf(Status.PRIORITYTWO));
                eduRegistMapper.insertSelective(eduRegist);
//            if (!eduRegistsNew2.isEmpty()) {     //当有特殊条件设置的时候
//                for (EduRegist eduRegist1 : eduRegistsNew2) {
//                    if (!eduRegist1.getCourseId().equals(eduCours.getCourseId()) ) {
//                        eduRegistMapper.insertSelective(eduRegist);
//
//                    }
//                }
//            }else {
//                eduRegistMapper.insertSelective(eduRegist);
//            }

////            if (!eduRegistsNew2.isEmpty()) {     //当有特殊条件设置的时候
////                for (EduRegist eduRegist1 : eduRegistsNew2) {
////                    if (!eduRegist1.getCourseId().equals(eduCours.getCourseId()) ) {
////                        eduRegistMapper.insertSelective(eduRegist);
////                          break;
////                    }
////                }
////            }else {
////                eduRegistMapper.insertSelective(eduRegist);
////            }
////        }
////
//            if (eduRegistsNew2.size() == 0) {
//                eduRegistMapper.insertSelective(eduRegist);
//            }
//
//            if (eduRegistsNew2.size() == 1) {     //当有特殊条件设置的时候
//                for (EduRegist eduRegist1 : eduRegistsNew2) {
//                    if (!eduCours.getCourseId().equals(eduRegist1.getCourseId())) {
//                        eduRegistMapper.insertSelective(eduRegist);
//
//                    }
//                }
//            }
//            if (eduRegistsNew2.size() > 1) {
//               // 当有特殊条件设置的时候
//                for (EduRegist eduRegist1 : eduRegistsNew2) {
//                    if (!eduCours.getCourseId().equals(eduRegist1.getCourseId())) {
//                        eduRegistMapper.insertSelective(eduRegist);
//                        break;
//                    }
//                }
//            }


        }
        for (EduRegist eduRegist1 : eduRegistsNew2) {
            EduRegistExample eduRegistExample6 = new EduRegistExample();
            eduRegistExample6.createCriteria().andCourseIdEqualTo(eduRegist1.getCourseId()).andRegistPriorityEqualTo(String.valueOf(Status.PRIORITYTWO));
            eduRegistMapper.deleteByExample(eduRegistExample6);
        }
            //3.生成默认原始条件列表
            EduRegistExample eduRegistExample1 = new EduRegistExample();
            eduRegistExample1.createCriteria().andSchoolIdEqualTo(schoolId).andRegistPriorityEqualTo(String.valueOf(Status.PRIORITYTWO));
            List<EduRegist> eduRegistsNew = eduRegistMapper.selectByExample(eduRegistExample1);
            PageInfo pageInfo5 = new PageInfo<>(eduRegistsNew);
            UpDateSchoolName(eduRegistsNew);
            return pageInfo5;

    }

//    public PageBean queryAllCoditionBySchoold(Integer size, Integer page, Integer schoolId) {
//        //1.查询设置全部条件的条件
//        Page<Object> pageAll = PageHelper.startPage(page, size);
//        EduRegistExample eduRegistExample = new EduRegistExample();
//        eduRegistExample.createCriteria().andSchoolIdEqualTo(schoolId).andCourseIdEqualTo(Status.ALLCOURSE);
//        List<EduRegist> eduRegists = eduRegistMapper.selectByExample(eduRegistExample);
//
//        //2.查询学校所有课程的id及报名课程名字
//        EduCourse course = new EduCourse();
//        course.setSchoolId(String.valueOf(schoolId));
//        course.setIsenable(ConstantClassField.ISONLINE);
//        List<EduCourse> eduCourses = eduCourseMapper.listCourseCategoryByShoolId(course);
//
//        //3.查询出条件表对应的课程
//        Page<Object> pageAll2 = PageHelper.startPage(page, size);
//        EduRegistExample eduRegistExample3 = new EduRegistExample();
//        eduRegistExample3.createCriteria().andSchoolIdEqualTo(schoolId).andCourseIdNotEqualTo(0);
//        List<EduRegist> eduRegistsNum = eduRegistMapper.selectByExample(eduRegistExample3);
//        //4.查询出特殊条件表对应的课程
//        Page<Object> pageAll4 = PageHelper.startPage(page, size);
//        EduRegistExample eduRegistExample3 = new EduRegistExample();
//        eduRegistExample3.createCriteria().andSchoolIdEqualTo(schoolId).andCourseIdNotEqualTo(0);
//        List<EduRegist> eduRegistsNum = eduRegistMapper.selectByExample(eduRegistExample3);
//
//        //4.第一次的查询和第二次的查询不同，则删除之前的        ===》情况：有新增或者减少的课程
//        if (eduCourses.size() != eduRegistsNum.size()) {
//            for (EduCourse eduCours : eduCourses) {
//                //删除所有的条件记录课程
//                EduRegistExample eduRegistExample4 = new EduRegistExample();
//                eduRegistExample4.createCriteria().andCourseIdEqualTo(eduCours.getCourseId()).andCourseIdNotEqualTo(Status.PRIORITYTWO);
//                eduRegistMapper.deleteByExample(eduRegistExample4);
//            }
//            String schoolName = schoolMapper.findSchoolById(schoolId);
//            //5.if  eduRegists  不为空
//            if (!eduRegists.isEmpty()) {
//                for (EduCourse eduCours : eduCourses) {
//                    EduRegist eduRegist = new EduRegist();
//                    eduRegist.setCourseId(eduCours.getCourseId());
//                    eduRegist.setCourseName(eduCours.getCourseName());
//                    eduRegist.setSchoolId(schoolId);
//                    eduRegist.setRegistPriority(String.valueOf(Status.PRIORITYTWO));
//                    eduRegist.setRegiststartTime(eduRegists.get(0).getRegiststartTime());
//                    eduRegist.setRegistendTime(eduRegists.get(0).getRegistendTime());
//                    eduRegist.setStartAge(eduRegists.get(0).getStartAge());
//                    eduRegist.setEndAge(eduRegists.get(0).getEndAge());
//                    eduRegist.setRegistCategoryOne(eduRegists.get(0).getRegistCategoryOne());
//                    eduRegist.setRegistCategoryTwo(eduRegists.get(0).getRegistCategoryTwo());
//                    eduRegist.setRegistCategoryThree(eduRegists.get(0).getRegistCategoryThree());
//                    eduRegist.setRegistCategoryFour(eduRegists.get(0).getRegistCategoryFour());
//                    eduRegist.setRegistCategoryFive(eduRegists.get(0).getRegistCategorySix());
//                    eduRegist.setUpateTime(new Date());
//                    eduRegist.setSchoolName(schoolName);
//                    eduRegist.set
//
//                    eduRegistMapper.insertSelective(eduRegist);
//                }
//
//            }
//        }
//
//
//
//
//
//    }

    //获取学校名称
    private void UpDateSchoolName(List<EduRegist> eduRegistsNew) {
        for (EduRegist eduRegist : eduRegistsNew) {
            List<String> strings = eduRegistMapper.selectNameBySchoolId(eduRegist.getSchoolId());
            for (String name : strings) {
                eduRegist.setSchoolName(name);
            }
            eduRegistMapper.updateByPrimaryKeySelective(eduRegist);
        }
    }

    /**
     * 修改条件
     *
     * @param eduRegist
     * @return
     */
    @Transactional
    @Override
    public int updateCodition(EduRegist eduRegist) {
        //updateByPrimaryKey修改全部

        //修改优先级1
        int i = -1;
        eduRegist.setUpateTime(new Date());
        if (eduRegist.getCourseId() == 0) {
            i = eduRegistMapper.updateByPrimaryKey(eduRegist);
            if (i > 0) {
                updateAllCondition(eduRegist);
            }
        } else {
            eduRegist.setRegistPriority(String.valueOf(Status.PRIORITYONE));
            i = eduRegistMapper.updateByPrimaryKey(eduRegist);
            if (i > 0) {
                return i;
            }
        }

        return i;
    }

    //全局设置更新
    private void updateAllCondition(EduRegist eduRegist) {
        //查出全局设置条件
        EduRegistExample eduRegistExample1 = new EduRegistExample();
        eduRegistExample1.createCriteria().andCourseIdEqualTo(eduRegist.getCourseId()).andSchoolIdEqualTo(eduRegist.getSchoolId());
        List<EduRegist> eduRegistAl = eduRegistMapper.selectByExample(eduRegistExample1);
        for (EduRegist eduRegistAll : eduRegistAl) {
            //再修改其他所有设置

            //根据学校查出所有的课程设置
            EduRegistExample eduRegistExample = new EduRegistExample();
            eduRegistExample.createCriteria().andSchoolIdEqualTo(eduRegistAll.getSchoolId());
            List<EduRegist> eduRegists = eduRegistMapper.selectByExample(eduRegistExample);

            //全局设置
            for (EduRegist regist : eduRegists) {
                regist.setUpateTime(new Date());
                regist.setRegiststartTime(eduRegistAll.getRegiststartTime());
                regist.setRegistendTime(eduRegistAll.getRegistendTime());
                regist.setStartAge(eduRegistAll.getStartAge());
                regist.setEndAge(eduRegistAll.getEndAge());
                regist.setRegistCategoryOne(eduRegistAll.getRegistCategoryOne());
                regist.setRegistCategoryTwo(eduRegistAll.getRegistCategoryTwo());
                regist.setRegistCategoryThree(eduRegistAll.getRegistCategoryThree());
                regist.setRegistCategoryFour(eduRegistAll.getRegistCategoryFour());
                regist.setRegistCategoryFive(eduRegistAll.getRegistCategoryFive());
                regist.setRegistCategorySix(eduRegistAll.getRegistCategorySix());
                regist.setRegistCategoryTwo(eduRegistAll.getRegistCategoryTwo());
                regist.setSchoolName(eduRegistAll.getSchoolName());
                regist.setOfflineRegist(eduRegistAll.getOfflineRegist());
                regist.setCourseNum(eduRegistAll.getCourseNum());
                eduRegistMapper.updateByPrimaryKeySelective(regist);
            }
        }

    }

    /**
     * 删除条件
     *
     * @param eduRegist
     * @return
     */
    @Override
    public int deleteCodition(EduRegist eduRegist) {
        return eduRegistMapper.deleteByPrimaryKey(eduRegist.getId());
    }

    /**
     * 查询条件
     *
     * @param id
     * @return
     */
    @Override
    public EduRegist queryById(Integer id) {
        EduRegist eduRegist = eduRegistMapper.selectByPrimaryKey(id);
        System.out.println(eduRegist);
        return eduRegist;
    }

    /**
     * 按学校查询特殊条件
     *
     * @param size
     * @param page
     * @param schoolId
     * @return
     */
    @Override
    public PageBean queryspecialCoditionBySchoold(Integer size, Integer page, Integer schoolId) {
        Page<Object> pageAll = PageHelper.startPage(page, size);
        EduRegistExample example = new EduRegistExample();
        example.createCriteria().andSchoolIdEqualTo(schoolId).andRegistPriorityEqualTo(String.valueOf(Status.PRIORITYONE));
        List<EduRegist> eduRegists = eduRegistMapper.selectByExample(example);
        long total = pageAll.getTotal();
        return new PageBean(page, size, total, eduRegists);
    }

    /**
     * 查询特殊条件
     *
     * @param size
     * @param page
     * @return
     */
    @Override
    public PageBean queryspecialCodition(Integer size, Integer page) {
        Page<Object> pageAll = PageHelper.startPage(page, size);
        EduRegistExample example = new EduRegistExample();
        example.createCriteria().andRegistPriorityEqualTo(String.valueOf(Status.PRIORITYONE));
        List<EduRegist> eduRegists = eduRegistMapper.selectByExample(example);
        long total = pageAll.getTotal();
        return new PageBean(page, size, total, eduRegists);
    }

    /**
     * 移除特殊条件 恢复全查询条件
     *
     * @param eduRegist
     * @return
     */
    @Override
    public int deleteSepecialCondition(EduRegist eduRegist) {
        int i = updateAllConditions(eduRegist);
        return i;
    }

    @Override
    public Integer queryOffRegist(int schoolId, Integer courseId) {
        EduRegistExample eduRegistExample = new EduRegistExample();
        eduRegistExample.createCriteria().andSchoolIdEqualTo(schoolId).andCourseIdEqualTo(courseId);
        List<EduRegist> eduRegists = eduRegistMapper.selectByExample(eduRegistExample);
        for (EduRegist eduRegist : eduRegists) {
            String offlineRegist = eduRegist.getOfflineRegist();
            return Integer.valueOf(offlineRegist);
        }
        return null;
    }

    //查询全部
    @Override
    public WebResult CourseCoditionAll(CoditionVo coditionVo) {

        if (coditionVo.getPageSize() == 0) {
            coditionVo.setCourseId(0);
            List<EduRegist> eduRegists = eduRegistMapper.CourseCoditionAll(coditionVo);
            return new WebResult("200", "查询成功", eduRegists);
        }
        PageHelper.startPage(coditionVo.getPageNum(), coditionVo.getPageSize());
        coditionVo.setCourseId(0);
        List<EduRegist> eduRegists = eduRegistMapper.CourseCoditionAll(coditionVo);
        PageInfo pageInfo = new PageInfo<>(eduRegists);
        return new WebResult("200", "查询成功", pageInfo);
    }



    //全局设置部分
    private int updateAllConditions(EduRegist eduRegist) {
        //根据id查询条件信息
        EduRegist oldEduRegist = eduRegistMapper.selectByPrimaryKey(eduRegist.getId());

        //根据学校id和全部查找出全局设置条件进行修改
        EduRegistExample eduRegistExample = new EduRegistExample();
        eduRegistExample.createCriteria().andSchoolIdEqualTo(oldEduRegist.getSchoolId()).andCourseIdEqualTo(Status.ALLCOURSE);
        List<EduRegist> eduRegists = eduRegistMapper.selectByExample(eduRegistExample);

        int i = -1;
        //进行修改
        for (EduRegist regist : eduRegists) {
            EduRegist newEduRegist = new EduRegist();
            newEduRegist.setId(eduRegist.getId());
            newEduRegist.setRegistPriority(String.valueOf(Status.PRIORITYTWO));
            newEduRegist.setUpateTime(new Date());
            newEduRegist.setRegiststartTime(regist.getRegiststartTime());
            newEduRegist.setRegistendTime(regist.getRegistendTime());
            newEduRegist.setStartAge(regist.getStartAge());
            newEduRegist.setEndAge(regist.getEndAge());
            newEduRegist.setRegistCategoryOne(regist.getRegistCategoryOne());
            newEduRegist.setRegistCategoryTwo(regist.getRegistCategoryTwo());
            newEduRegist.setRegistCategoryThree(regist.getRegistCategoryThree());
            newEduRegist.setRegistCategoryFour(regist.getRegistCategoryFour());
            newEduRegist.setRegistCategoryFive(regist.getRegistCategoryFive());
            newEduRegist.setRegistCategorySix(regist.getRegistCategorySix());
            newEduRegist.setRegistCategoryTwo(regist.getRegistCategoryTwo());
            newEduRegist.setSchoolName(regist.getSchoolName());
            newEduRegist.setCourseNum(regist.getCourseNum());
            i = eduRegistMapper.updateByPrimaryKeySelective(newEduRegist);
            if (i > 0) {
                return i;
            }

        }
        return i;
    }
}
