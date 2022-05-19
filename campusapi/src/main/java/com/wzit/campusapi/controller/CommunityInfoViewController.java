package com.wzit.campusapi.controller;

import com.wzit.campusapi.entity.CmtPersonInfo;
import com.wzit.campusapi.entity.CommunityInfo;
import com.wzit.campusapi.entity.CommunityInfoView;
import com.wzit.campusapi.service.CmtPersonInfoService;
import com.wzit.campusapi.service.CommunityInfoViewService;
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
 * (CommunityInfoView)表控制层
 *
 * @author yyw
 */
@RestController
@RequestMapping("/communityInfoView")
public class CommunityInfoViewController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private CommunityInfoViewService communityInfoViewService;
    @Resource
    private CmtPersonInfoService cmtPersonInfoService;

    /**
     * 添加
     */
    @PostMapping("/insert")
    public Object insert(@RequestBody CommunityInfoView communityInfoView) {
        communityInfoView.setCreateTime(new Date());
        if (SaTokenCheck() == 1) {
            CommunityInfoView ins = communityInfoViewService.insert(communityInfoView);
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
    public Object delete(@RequestBody CommunityInfoView communityInfoView) {
        if (SaTokenCheck() == 1) {
            boolean b = communityInfoViewService.delete(communityInfoView);
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
    public Object update(@RequestBody CommunityInfoView communityInfoView) {
        if (SaTokenCheck() == 1) {
            boolean b = communityInfoViewService.update(communityInfoView);
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
    public Object selectpage(@RequestBody CommunityInfoView communityInfoView) {
        int pageNum = communityInfoView.getPageNum();
        List<CommunityInfoView> list = communityInfoViewService.selectpage(communityInfoView);
        return ResultApi(list, pageNum, communityInfoView.getPageSize());
    }

    /**
     * 查询所有
     */
    @PostMapping("/selectall")
    public Object selectall(@RequestBody CommunityInfoView communityInfoView) {
        if (SaTokenCheck() == 1) {
            List<CommunityInfoView> list = communityInfoViewService.selectall(communityInfoView);
            if (list.size() <= 0) {
                return ResultErr(4);
            }
            List<CmtPersonInfo> list1 = cmtPersonInfoService.selectall(new CmtPersonInfo());
            for (CommunityInfoView communityInfoView1 : list){
                communityInfoView1.setIsJoin(2);
                for (CmtPersonInfo cmtPersonInfo : list1){
                    if (communityInfoView1.getCommunityId().equals(cmtPersonInfo.getCmtId()) &&
                    cmtPersonInfo.getUserId().equals(getUserInfo().getUserId())){
                        communityInfoView1.setIsJoin(1);
                    }
                }
            }
            return ResultOK(4, list, list.size());
        } else {
            return NoSatoken();
        }
    }

    /**
     * 根据条件查询所有
     */
    @PostMapping("/selectallLimit")
    public Object selectallLimit(@RequestBody CommunityInfoView communityInfoView) {
        if (SaTokenCheck() == 1) {
            List<CommunityInfoView> list = communityInfoViewService.selectallLimit(communityInfoView);
            if (list.size() <= 0) {
                return ResultErr(4);
            }
            List<CmtPersonInfo> list1 = cmtPersonInfoService.selectall(new CmtPersonInfo());
            for (CommunityInfoView communityInfoView1 : list){
                communityInfoView1.setIsJoin(2);
                for (CmtPersonInfo cmtPersonInfo : list1){
                    if (communityInfoView1.getCommunityId().equals(cmtPersonInfo.getCmtId()) &&
                            cmtPersonInfo.getUserId().equals(getUserInfo().getUserId())){
                        communityInfoView1.setIsJoin(1);
                    }
                }
            }
            return ResultOK(4, list, list.size());
        } else {
            return NoSatoken();
        }
    }

    /**
     * 查询所有数量
     */
    @PostMapping("/selectcount")
    public Object selectCount(@RequestBody CommunityInfoView communityInfoView) {
        if (SaTokenCheck() == 1) {
            List<CommunityInfoView> list = communityInfoViewService.selectall(communityInfoView);
            Integer count = list.size();
            return ResultOK(count);
        } else {
            return NoSatoken();
        }
    }

    /**
     * 根据条件查询所有数量
     */
    @PostMapping("/selectallLimitCount")
    public Object selectallLimitCount(@RequestBody CommunityInfoView communityInfoView) {
        if (SaTokenCheck() == 1) {
            List<CommunityInfoView> list = communityInfoViewService.selectallLimit(communityInfoView);
            List<CmtPersonInfo> list1 = cmtPersonInfoService.selectall(new CmtPersonInfo());
            for (CommunityInfoView communityInfoView1 : list){
                communityInfoView1.setIsJoin(2);
                for (CmtPersonInfo cmtPersonInfo : list1){
                    if (communityInfoView1.getCommunityId().equals(cmtPersonInfo.getCmtId()) &&
                            cmtPersonInfo.getUserId().equals(getUserInfo().getUserId())){
                        communityInfoView1.setIsJoin(1);
                    }
                }
            }
            return ResultOK(list.size());
        } else {
            return NoSatoken();
        }
    }
}

