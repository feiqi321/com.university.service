package com.ovft.configure.sys.web;

import com.ovft.configure.constant.ConstantClassField;
import com.ovft.configure.http.result.StatusCode;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.EduCourse;
import com.ovft.configure.sys.bean.EduOfflineOrder;
import com.ovft.configure.sys.bean.User;
import com.ovft.configure.sys.dao.OrderMapper;
import com.ovft.configure.sys.service.EduCourseService;
import com.ovft.configure.sys.service.EduOfflineOrderService;
import com.ovft.configure.sys.service.UserService;
import com.ovft.configure.sys.vo.EduCourseVo;
import com.ovft.configure.sys.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author vvtxw
 * @create 2019-04-14 7:43
 */

@RestController
@RequestMapping("apply")
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    private EduOfflineOrderService eduOfflineOrderService;
    @Resource
    private OrderMapper orderMapper;

    /**
     * 按学校的id来查找专业类别信息
     *
     * @return
     */
    @GetMapping(value = "showCategory")
    public WebResult queryAllCourse(HttpServletRequest request) {
        String schoolId1 = request.getHeader("schoolId");
        //判断报名的学校不能为空
        if (schoolId1.equals("null")) {
            return new WebResult(StatusCode.ERROR, "报名的学校不能为空，请填写基本信息里的报名学校", "");
        }
        Integer schoolId = Integer.parseInt(schoolId1);


        EduCourse course = new EduCourse();
        course.setSchoolId(String.valueOf(schoolId));
        course.setIsenable(ConstantClassField.ISONLINE);//本学校的上线课程
        List<EduCourse> eduCourseId = eduCourseService.listCourseCategoryByShoolId(course);

        EduCourseVo eduCourseVo = (EduCourseVo) redisTemplate.opsForValue().get("schoolId" + schoolId);
        ArrayList<Object> courseVos = new ArrayList<>();
        if (eduCourseVo == null) {
            for (EduCourse eduCourse : eduCourseId) {
                eduCourseVo = eduCourseService.queryCourseByCategory(eduCourse.getCourseId());
                courseVos.add(eduCourseVo);
            }
            redisTemplate.opsForValue().set("course" + schoolId, eduCourseVo);
        }
        return new WebResult(StatusCode.OK, "查找成功", courseVos);
    }


    /**
     * 立即报名
     *
     * @param request
     * @return
     */
    @GetMapping(value = "order")
    public WebResult queryCourseInfoById(@RequestParam(value = "courseId") Integer courseId, HttpServletRequest request) {

        String userId1 = request.getHeader("userId");
        if (userId1.equals("null")) {
            return new WebResult(StatusCode.ERROR, "请先登录再购买", "");
        }
        Integer userId = Integer.parseInt(userId1);


        String schoolId1 = request.getHeader("schoolId");





        //查询学员的基本信息
        User user2 = userService.queryInfo(userId);

        //查询是否有订单，如果已经下单了，返回信息
        List<EduOfflineOrder> oldOrder = eduOfflineOrderService.queryOffRecord(user2.getUserId(), courseId);
        List<OrderVo> orderVos = orderMapper.queryAllRecordByCourseId(user2.getUserId(),courseId);
        if (oldOrder.size() > 0||orderVos.size()>0) {
            return new WebResult("600", "您已经报名该课程，不要重复报名");
        }

        //判断报名的学校不能为空
        if (schoolId1.equals("null")) {
            return new WebResult(StatusCode.ERROR, "您尚未报名学校，请填写基本信息报名学校", "");
        }
        Integer schoolId = Integer.parseInt(schoolId1);
        User user = userService.queryUserInfo(userId);
        if (user == null) {
            return new WebResult(StatusCode.ERROR, "请先在我要上学里面填报自己的学校，方可报名！", "");
        }

        //判断是否是通过正式学员，没有通过则不能报名
        if (user.getSchoolId() != schoolId) {
            return new WebResult(StatusCode.ERROR, "您填写报名的学校是:【" + user.getSchoolName() + "】——————请到基本信息切换正确的报名学校，方可报名！", "");
        }
        if (user.getCheckin() != 0) {
            return new WebResult(StatusCode.ERROR, "您还不是正式学员，正在审核中，请耐心等待", "");
        }

        if (courseId == null) {
            return new WebResult(StatusCode.ERROR, "课程id不能为空", "");
        }

        //获取请求头的userId
        Map<String, Object> map = eduCourseService.queryCourseByCourseId(userId, courseId, request);
        Iterator<String> it = map.keySet().iterator();

        if (it != null) {
            while (it.hasNext()) {
                String message = it.next();
                Object eduCourseVo = map.get(message);
                if (eduCourseVo == null || eduCourseVo == "") {
                    return new WebResult(StatusCode.ERROR, message, "");
                }
                return new WebResult(StatusCode.OK, message, eduCourseVo);
            }
        }
        return new WebResult(StatusCode.ERROR, "查询没有数据", "");
    }

    /**
     * 课程表
     *
     * @param week
     * @param request
     * @return
     */
    @GetMapping(value = "timetable")
    public WebResult queryAllTimeRecord(@RequestParam(value = "week") String week, HttpServletRequest request,@RequestParam(value = "schoolId") String schoolId) {

        //判断报名的学校不能为空
        if (schoolId.equals("null")) {
            return new WebResult(StatusCode.ERROR, "报名的学校不能为空，请填写基本信息里的报名学校", "");
        }
        List<EduCourseVo> eduCourseVos = eduCourseService.queryAllTimetable(week, schoolId);
        return new WebResult(StatusCode.OK, "查询成功", eduCourseVos);
    }

    /**
     * 统一设置时间
     *
     * @param eduCourse
     * @return
     */
    @PostMapping(value = "updatetime")
    public WebResult updateAlltime(@RequestBody EduCourse eduCourse) {
        EduCourse course = new EduCourse();
        course.setSchoolId(String.valueOf(eduCourse.getSchoolId()));
        course.setIsenable(ConstantClassField.ISONLINE);
        course.setStartDate(eduCourse.getStartDate());
        course.setEndDate(eduCourse.getEndDate());
        course.setDid(eduCourse.getDid());
        int i = eduCourseService.updateAllTime(course);
        if (i > 0) {
            return new WebResult(StatusCode.OK, "全部设置成功");
        }
        return new WebResult(StatusCode.ERROR, "全部设置失败");
    }


}

