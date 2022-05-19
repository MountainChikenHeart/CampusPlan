package com.wzit.campusapi.controller;

import cn.hutool.core.date.DateUtil;
import com.wzit.campusapi.entity.ComtInfo;
import com.wzit.campusapi.entity.UserComtUrge;
import com.wzit.campusapi.service.ComtInfoService;
import org.springframework.web.bind.annotation.*;
import com.wzit.campusapi.web.BaseController;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.*;

import static com.wzit.campusapi.utils.ResultCode.CODE_SELECT_OK;
import static com.wzit.campusapi.utils.ResultMsg.MSG_SELECT_OK;
import static com.wzit.campusapi.utils.ResultUtils.ResultData;

/**
 * 圈子任务表(ComtInfo)表控制层
 *
 * @author yyw
 */
@RestController
@RequestMapping("/comtInfo")
public class ComtInfoController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private ComtInfoService comtInfoService;

    /**
     * 添加
     */
    @PostMapping("/insert")
    public Object insert(@RequestBody ComtInfo comtInfo) {
        comtInfo.setCreateTime(new Date());
        if (SaTokenCheck() == 1) {
            ComtInfo ins = comtInfoService.insert(comtInfo);
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
    public Object delete(@RequestBody ComtInfo comtInfo) {
        if (SaTokenCheck() == 1) {
            boolean b = comtInfoService.delete(comtInfo);
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
    public Object update(@RequestBody ComtInfo comtInfo) {
        if (SaTokenCheck() == 1) {
            boolean b = comtInfoService.update(comtInfo);
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
    public Object selectpage(@RequestBody ComtInfo comtInfo) {
        int pageNum = comtInfo.getPageNum();
        List<ComtInfo> list = comtInfoService.selectpage(comtInfo);
        return ResultApi(list, pageNum, comtInfo.getPageSize());
    }

    /**
     * 查询所有
     */
    @PostMapping("/selectall")
    public Object selectall(@RequestBody ComtInfo comtInfo) {
        if (SaTokenCheck() == 1) {
            HashMap<Object, Object> map = new HashMap<>();
            List<ComtInfo> list = comtInfoService.selectall(comtInfo);
            if (list.size() <= 0) {
                return ResultErr(4);
            }
            List<Object> list3 = new ArrayList<>();
            //LinkedHashMap能解决HashMap排序错乱问题
            LinkedHashMap<Object, Object> map1 = new LinkedHashMap<>();

            //设置KEY数据
            for (ComtInfo info: list) {
                String format = DateUtil.format(info.getEndTime(), "MM月dd日");
                info.setDay(format);
                map1.putIfAbsent(format, format);
            }

            //统计合并
            List<Object> list1 = new ArrayList<>();
            for(Object key: map1.keySet()){
                list3.add(key);//填充头部
                List<ComtInfo> list2 = new ArrayList<>();
                for (ComtInfo info : list) {
                    if (key.equals(info.getDay())) {
                        list2.add(info);
                    }
                }
                //子布局排序
                list2.sort(new Comparator<ComtInfo>() {
                    @Override
                    public int compare(ComtInfo o1, ComtInfo o2) {
                        return (int) (o1.getEndTime().getTime() - o2.getEndTime().getTime());
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
     * 提醒用户
     */
    @PostMapping("/updateurge")
    public Object updateUrge(@RequestBody UserComtUrge userComtUrge) {
        if (SaTokenCheck() == 1) {
            boolean b = comtInfoService.updateUrge(userComtUrge);
            if (!b) {
                return ResultErr(3);
            }
            return ResultOK(3, new HashMap<>(), 1);
        } else {
            return NoSatoken();
        }
    }

    /**
     * 查询是否被提醒
     */
    @PostMapping("/selecturge")
    public Object selectUrge(@RequestBody UserComtUrge userComtUrge) {
        if (SaTokenCheck() == 1) {
            boolean b = comtInfoService.selectUrge(userComtUrge);
            if (!b) {
                return ResultErr(4);
            }
            return ResultOK(4, new HashMap<>(), 1);
        } else {
            return NoSatoken();
        }
    }
}

