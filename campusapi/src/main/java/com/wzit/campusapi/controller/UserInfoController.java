package com.wzit.campusapi.controller;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaFoxUtil;
import com.wzit.campusapi.entity.CmtPersonInfo;
import com.wzit.campusapi.entity.UserInfo;
import com.wzit.campusapi.service.CmtPersonInfoService;
import com.wzit.campusapi.service.UserInfoService;
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
 * 用户表(UserInfo)表控制层
 *
 * @author yyw
 */
@RestController
@RequestMapping("/userInfo")
public class UserInfoController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private UserInfoService userInfoService;

    @Resource
    private CmtPersonInfoService cmtPersonInfoService;

    /**
     * 添加
     */
    @PostMapping("/insert")
    public Object insert(@RequestBody UserInfo userInfo) {
        UserInfo info = new UserInfo();
        info.setUserName(userInfo.getUserName());
        //查询是否存在已注册用户
        List<UserInfo> list = userInfoService.selectall(info);
        if (list.size() > 0) {
            return ResultEXI();
        }
        //不存在则注册
        userInfo.setUserNum(SaFoxUtil.getRandomString(10));
        userInfo.setCreateTime(new Date());
        userInfo.setUserPassword(SaSecureUtil.md5(userInfo.getUserPassword()));
        userInfo.setUserHeaderimg("/profile/upload/head/head" + getHeadImgPosition() + ".png");
        userInfo.setUserSignature("这个家伙很懒，没留下什么！");
        userInfo.setIsVip(2);
        userInfo.setUserIntegral(0.0);
        UserInfo ins = userInfoService.insert(userInfo);
        if (ins == null) {
            return ResultErr(1);
        }
        if(userInfo.getUserSchool().equals("郑州大学")){
            CmtPersonInfo cmtPersonInfo = new CmtPersonInfo();
            cmtPersonInfo.setCmtId(1L);
            cmtPersonInfo.setUserId(ins.getUserId());
            cmtPersonInfo.setCreateTime(new Date());
            cmtPersonInfoService.insert(cmtPersonInfo);
        }

        return ResultOK(1,ins,1);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public Object delete(@RequestBody UserInfo userInfo) {
        if (SaTokenCheck() == 1) {
            boolean b = userInfoService.delete(userInfo);
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
    public Object update(@RequestBody UserInfo userInfo) {
        if (SaTokenCheck() == 1) {
            userInfo.setUserId(getUserInfo().getUserId());
            userInfoService.update(userInfo);
            UserInfo info = new UserInfo();
            info.setUserId(userInfo.getUserId());
            List<UserInfo> list = userInfoService.selectall(info);
            StpUtil.login(list.get(0).getUserName());
            list.get(0).setUserToken(StpUtil.getTokenValue());
            return ResultOK(3,list.get(0), 1);
        } else {
            return NoSatoken();
        }
    }

    /**
     * 分页查询
     */
    @PostMapping("/selectpage")
    public Object selectpage(@RequestBody UserInfo userInfo) {
        int pageNum = userInfo.getPageNum();
        List<UserInfo> list = userInfoService.selectpage(userInfo);
        return ResultApi(list, pageNum, userInfo.getPageSize());
    }

    /**
     * 查询所有
     */
    @PostMapping("/selectall")
    public Object selectall(@RequestBody UserInfo userInfo) {
        if (SaTokenCheck() == 1) {
            List<UserInfo> list = userInfoService.selectall(userInfo);
            if (list.size() <= 0) {
                return ResultErr(4);
            }
            return ResultOK(4, list, list.size());
        } else {
            return NoSatoken();
        }
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public Object login(@RequestBody UserInfo userInfo) {
        userInfo.setUserPassword(SaSecureUtil.md5(userInfo.getUserPassword()));
        List<UserInfo> list = userInfoService.selectall(userInfo);
        if (list.size() <= 0) {
            return ResultErr(4);
        }
        StpUtil.login(userInfo.getUserName());
        list.get(0).setUserToken(StpUtil.getTokenValue());
        return ResultOK(4,list.get(0),1);
    }
}

