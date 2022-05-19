package com.wzit.campusapi.controller;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.util.SaFoxUtil;
import com.wzit.campusapi.entity.CommunityInfo;
import com.wzit.campusapi.service.CommunityInfoService;
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
 * 圈子表(CommunityInfo)表控制层
 *
 * @author yyw
 */
@RestController
@RequestMapping("/communityInfo")
public class CommunityInfoController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private CommunityInfoService communityInfoService;

    /**
     * 添加
     */
    @PostMapping("/insert")
    public Object insert(@RequestBody CommunityInfo communityInfo) {
        communityInfo.setCreateTime(new Date());
        if (SaTokenCheck() == 1) {
            communityInfo.setUserId(getUserInfo().getUserId());
            communityInfo.setCommunityCode(SaFoxUtil.getRandomString(4));
            communityInfo.setCommunityNum(SaFoxUtil.getRandomString(20));
            CommunityInfo ins = communityInfoService.insert(communityInfo);
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
    public Object delete(@RequestBody CommunityInfo communityInfo) {
        if (SaTokenCheck() == 1) {
            boolean b = communityInfoService.delete(communityInfo);
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
    public Object update(@RequestBody CommunityInfo communityInfo) {
        if (SaTokenCheck() == 1) {
            boolean b = communityInfoService.update(communityInfo);
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
    public Object selectpage(@RequestBody CommunityInfo communityInfo) {
        int pageNum = communityInfo.getPageNum();
        List<CommunityInfo> list = communityInfoService.selectpage(communityInfo);
        return ResultApi(list, pageNum, communityInfo.getPageSize());
    }

    /**
     * 查询所有
     */
    @PostMapping("/selectall")
    public Object selectall(@RequestBody CommunityInfo communityInfo) {
        if (SaTokenCheck() == 1) {
            List<CommunityInfo> list = communityInfoService.selectall(communityInfo);
            if (list.size() <= 0) {
                return ResultErr(4);
            }
            return ResultOK(4, list, list.size());
        } else {
            return NoSatoken();
        }
    }

}

