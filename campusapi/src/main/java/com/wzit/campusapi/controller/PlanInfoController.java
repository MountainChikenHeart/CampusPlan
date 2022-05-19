package com.wzit.campusapi.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.wzit.campusapi.entity.PlanInfo;
import com.wzit.campusapi.service.PlanInfoService;
import org.springframework.web.bind.annotation.*;
import com.wzit.campusapi.web.BaseController;
import com.wzit.campusapi.web.BaseEntity;

import javax.annotation.Resource;
import java.util.*;

import static com.wzit.campusapi.utils.ResultUtils.*;
import static com.wzit.campusapi.utils.ResultCode.*;
import static com.wzit.campusapi.utils.ResultMsg.*;
import static com.wzit.campusapi.utils.WzitUtils.*;

/**
 * 任务计划表(PlanInfo)表控制层
 *
 * @author yyw
 */
@RestController
@RequestMapping("/planInfo")
public class PlanInfoController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private PlanInfoService planInfoService;

    /**
     * 添加
     */
    @PostMapping("/insert")
    public Object insert(@RequestBody PlanInfo planInfo) {
        if (SaTokenCheck() == 1) {
            planInfo.setCreateTime(new Date());
            planInfo.setUserId(getUserInfo().getUserId());
            planInfo.setPlanNum(getRandomUUID(7, 20));
            planInfo.setPlanIsFinished(2);
            PlanInfo ins = planInfoService.insert(planInfo);
            if (ins == null) {
                return ResultErr(1);
            }
            return ResultOK(1, ins, 1);
        } else {
            return NoSatoken();
        }
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public Object delete(@RequestBody PlanInfo planInfo) {
        if (SaTokenCheck() == 1) {
            boolean b = planInfoService.delete(planInfo);
            if (!b) {
                return ResultErr(2);
            }
            return ResultOK(2, new HashMap<>(), 1);
        } else {
            return NoSatoken();
        }
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public Object update(@RequestBody PlanInfo planInfo) {
        if (SaTokenCheck() == 1) {
            boolean b = planInfoService.update(planInfo);
            if (!b) {
                return ResultErr(3);
            }
            return ResultOK(3, new HashMap<>(), 1);
        } else {
            return NoSatoken();
        }
    }

    /**
     * 分页查询
     */
    @PostMapping("/selectpage")
    public Object selectpage(@RequestBody PlanInfo planInfo) {
        int pageNum = planInfo.getPageNum();
        List<PlanInfo> list = planInfoService.selectpage(planInfo);
        return ResultApi(list, pageNum, planInfo.getPageSize());
    }

    /**
     * 查询所有
     */
    @PostMapping("/selectall")
    public Object selectall(@RequestBody PlanInfo planInfo) {
        if (SaTokenCheck() == 1) {
            planInfo.setUserId(getUserInfo().getUserId());
            HashMap<Object, Object> map = new HashMap<>();
            List<PlanInfo> list = planInfoService.selectall(planInfo);
            if (list.size() <= 0) {
                return ResultErr(4);
            }

            List<Object> list3 = new ArrayList<>();
            //LinkedHashMap能解决HashMap排序错乱问题
            LinkedHashMap<Object, Object> map1 = new LinkedHashMap<>();

            //设置KEY数据
            for (PlanInfo info : list) {
                if (planInfo.getPlanIsFinished() == null) {//3.已失效
                    if (!DateUtil.format(info.getEndTime(), "MM月dd日").equals(DateUtil.format(new Date(), "MM月dd日"))
                            && info.getEndTime().before(new Date())
                            && info.getPlanIsFinished() == 2) {
                        String format = DateUtil.format(info.getEndTime(), "MM月dd日");
                        info.setDay(format);
                        map1.putIfAbsent(format, format);
                    }
                } else if (planInfo.getPlanIsFinished() == 1) {//1.已完成
                    String format = DateUtil.format(info.getEndTime(), "MM月dd日");
                    info.setDay(format);
                    map1.putIfAbsent(format, format);
                } else if (planInfo.getPlanIsFinished() == 2) {//2.未完成
                    if (info.getEndTime().after(new Date()) ||
                            DateUtil.format(info.getEndTime(), "MM月dd日").equals(DateUtil.format(new Date(), "MM月dd日"))) {
                        String format = DateUtil.format(info.getEndTime(), "MM月dd日");
                        info.setDay(format);
                        map1.putIfAbsent(format, format);
                    }
                }
            }

            //统计合并
            List<Object> list1 = new ArrayList<>();
            for (Object key : map1.keySet()) {
                list3.add(key);//填充头部
                List<PlanInfo> list2 = new ArrayList<>();
                for (PlanInfo info : list) {
                    if (key.equals(info.getDay())) {
                        list2.add(info);
                    }
                }
                //子布局排序
                list2.sort(new Comparator<PlanInfo>() {
                    @Override
                    public int compare(PlanInfo o1, PlanInfo o2) {
                        return o1.getPlanDegree() - o2.getPlanDegree();
                    }
                });
                list1.add(list2);//填充子部
            }
            map.put("group", list3);
            map.put("children", list1);
            return ResultData(map, CODE_SELECT_OK, MSG_SELECT_OK, 1);
        } else {
            return NoSatoken();
        }
    }


    /**
     * 四象限
     */
    @PostMapping("/sxx")
    public Object sxx(@RequestBody PlanInfo planInfo) {
        if (SaTokenCheck() == 1) {
            planInfo.setUserId(getUserInfo().getUserId());
            planInfo.setPlanIsFinished(2);
            List<PlanInfo> list = planInfoService.selectall(planInfo);
            if (list.size() == 0) {
                HashMap<Object, Object> map1 = new HashMap<>();
                HashMap<Object, Object> map2 = new HashMap<>();
                HashMap<Object, Object> map3 = new HashMap<>();
                HashMap<Object, Object> map4 = new HashMap<>();

                map1.put("one", new ArrayList<>());
                map2.put("two", new ArrayList<>());
                map3.put("three", new ArrayList<>());
                map4.put("four", new ArrayList<>());

                List<Object> list2 = new ArrayList<>();
                list2.add(map1);
                list2.add(map2);
                list2.add(map3);
                list2.add(map4);

                return ResultData(list2, CODE_SELECT_OK, MSG_SELECT_OK, 1);
            }

            //今天
            String today = DateUtil.format(new Date(), "MM月dd日");
            //明天
            String tomorrow = DateUtil.format(DateUtil.tomorrow(), "MM月dd日");
            //两天后
            String twodays = DateUtil.format(DateUtil.offsetDay(new Date(), 2), "MM月dd日");

            List<Object> list1 = new ArrayList<>();
            List<Object> list2 = new ArrayList<>();
            List<Object> list3 = new ArrayList<>();
            List<Object> list4 = new ArrayList<>();

            HashMap<Object, Object> map1 = new HashMap<>();
            HashMap<Object, Object> map2 = new HashMap<>();
            HashMap<Object, Object> map3 = new HashMap<>();
            HashMap<Object, Object> map4 = new HashMap<>();

            //设置KEY数据
            for (PlanInfo info : list) {

                String format = DateUtil.format(info.getEndTime(), "MM月dd日");
                //1.重要且紧急 ------ 截止日期（已过期、今天、明天）优先级（高 中）
                if ((info.getEndTime().before(new Date()) || today.equals(format) || tomorrow.equals(format)) &&
                        (info.getPlanDegree() == 0 || info.getPlanDegree() == 1)) {
                    info.setDay(format);
                    list1.add(info);
                }

                //2.重要不紧急 ------ 截止日期（2天后） 优先级（高 中）
                if (info.getEndTime().after(DateUtil.offsetDay(new Date(),2)) && (info.getPlanDegree() == 0 || info.getPlanDegree() == 1)) {
                    info.setDay(format);
                    list2.add(info);
                }

                //3.不重要但紧急 ------ 截止日期（已过期、今天、明天）优先级（低）
                if ((info.getEndTime().before(new Date()) || today.equals(format) || tomorrow.equals(format)) && (info.getPlanDegree() == 2)) {
                    info.setDay(format);
                    list3.add(info);
                }

                //4.不重要不紧急 ------ 截止日期（2天后） 优先级（低）
                if ((info.getEndTime().after(DateUtil.offsetDay(new Date(),2))) && (info.getPlanDegree() == 2)) {
                    info.setDay(format);
                    list4.add(info);
                }

            }

            //新数组
            if(list1.size()==0){
                map1.put("one", new ArrayList<>());
            }else {
                map1.put("one", list1);
            }
            if(list2.size()==0){
                map2.put("two", new ArrayList<>());
            }else {
                map2.put("two", list2);
            }
            if(list3.size()==0){
                map3.put("three", new ArrayList<>());
            }else {
                map3.put("three", list3);
            }
            if(list4.size()==0){
                map4.put("four", new ArrayList<>());
            }else {
                map4.put("four", list4);
            }

            List<Object> list5 = new ArrayList<>();
            list5.add(map1);
            list5.add(map2);
            list5.add(map3);
            list5.add(map4);

            return ResultData(list5, CODE_SELECT_OK, MSG_SELECT_OK, 1);
        } else {
            return NoSatoken();
        }
    }

    /**
     * 四象限
     */
    @PostMapping("/sxxCount")
    public Object sxxCount(@RequestBody PlanInfo planInfo) {
        if (SaTokenCheck() == 1) {
            planInfo.setUserId(getUserInfo().getUserId());
            planInfo.setPlanIsFinished(2);
            List<PlanInfo> list = planInfoService.selectall(planInfo);
            if (list.size() == 0) {
                return ResultOK(0);
            }else {
                return ResultOK(1);
            }
        } else {
            return NoSatoken();
        }
    }
}

