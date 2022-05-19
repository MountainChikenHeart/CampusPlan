package com.wzit.campusapi.controller;

import com.wzit.campusapi.entity.CmtPersonInfo;
import com.wzit.campusapi.service.CmtPersonInfoService;
import org.springframework.web.bind.annotation.*;
import com.wzit.campusapi.web.BaseController;
import com.wzit.campusapi.web.BaseEntity;

import javax.annotation.Resource;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static com.wzit.campusapi.utils.ResultUtils.*;
import static com.wzit.campusapi.utils.ResultCode.*;
import static com.wzit.campusapi.utils.ResultMsg.*;
import static com.wzit.campusapi.utils.WzitUtils.*;

/**
 * 圈子成员表(CmtPersonInfo)表控制层
 *
 * @author yyw
 */
@RestController
@RequestMapping("/cmtPersonInfo")
public class CmtPersonInfoController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private CmtPersonInfoService cmtPersonInfoService;

    /**
     * 添加
     */
    @PostMapping("/insert")
    public Object insert(@RequestBody CmtPersonInfo cmtPersonInfo) {
        cmtPersonInfo.setCreateTime(new Date());
        if (SaTokenCheck() == 1) {
            cmtPersonInfo.setUserId(getUserInfo().getUserId());
            CmtPersonInfo ins = cmtPersonInfoService.insert(cmtPersonInfo);
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
    public Object delete(@RequestBody CmtPersonInfo cmtPersonInfo) {
        if (SaTokenCheck() == 1) {
            boolean b = cmtPersonInfoService.delete(cmtPersonInfo);
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
    public Object update(@RequestBody CmtPersonInfo cmtPersonInfo) {
        if (SaTokenCheck() == 1) {
            boolean b = cmtPersonInfoService.update(cmtPersonInfo);
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
    public Object selectpage(@RequestBody CmtPersonInfo cmtPersonInfo) {
        int pageNum = cmtPersonInfo.getPageNum();
        List<CmtPersonInfo> list = cmtPersonInfoService.selectpage(cmtPersonInfo);
        return ResultApi(list, pageNum, cmtPersonInfo.getPageSize());
    }

    /**
     * 查询所有
     */
    @PostMapping("/selectall")
    public Object selectall(@RequestBody CmtPersonInfo cmtPersonInfo) {
        if (SaTokenCheck() == 1) {
            List<CmtPersonInfo> list = cmtPersonInfoService.selectall(cmtPersonInfo);
            if (list.size() <= 0) {
                return ResultErr(4);
            }
            return ResultOK(4, list, list.size());
        } else {
            return NoSatoken();
        }
    }
}

