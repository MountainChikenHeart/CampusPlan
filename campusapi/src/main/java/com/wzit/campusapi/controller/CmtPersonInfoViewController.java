package com.wzit.campusapi.controller;

import com.wzit.campusapi.entity.CmtPersonInfoView;
import com.wzit.campusapi.service.CmtPersonInfoViewService;
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
 * (CmtPersonInfoView)表控制层
 *
 * @author yyw
 */
@RestController
@RequestMapping("/cmtPersonInfoView")
public class CmtPersonInfoViewController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private CmtPersonInfoViewService cmtPersonInfoViewService;

    /**
     * 添加
     */
    @PostMapping("/insert")
    public Object insert(@RequestBody CmtPersonInfoView cmtPersonInfoView) {
        cmtPersonInfoView.setCreateTime(new Date());
        if (SaTokenCheck() == 1) {
            CmtPersonInfoView ins = cmtPersonInfoViewService.insert(cmtPersonInfoView);
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
    public Object delete(@RequestBody CmtPersonInfoView cmtPersonInfoView) {
        if (SaTokenCheck() == 1) {
            boolean b = cmtPersonInfoViewService.delete(cmtPersonInfoView);
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
    public Object update(@RequestBody CmtPersonInfoView cmtPersonInfoView) {
        if (SaTokenCheck() == 1) {
            boolean b = cmtPersonInfoViewService.update(cmtPersonInfoView);
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
    public Object selectpage(@RequestBody CmtPersonInfoView cmtPersonInfoView) {
        int pageNum = cmtPersonInfoView.getPageNum();
        List<CmtPersonInfoView> list = cmtPersonInfoViewService.selectpage(cmtPersonInfoView);
        return ResultApi(list, pageNum, cmtPersonInfoView.getPageSize());
    }

    /**
     * 查询所有
     */
    @PostMapping("/selectall")
    public Object selectall(@RequestBody CmtPersonInfoView cmtPersonInfoView) {
        if (SaTokenCheck() == 1) {
            List<CmtPersonInfoView> list = cmtPersonInfoViewService.selectall(cmtPersonInfoView);
            if (list.size() <= 0) {
                HashMap<Object, Object> map = new HashMap<>();
                ArrayList<Object> list1 = new ArrayList<>();
                list1.add(map);
                return ResultData(list1, CODE_SELECT_ERR, MSG_SELECT_ERR, 0);
            }
            return ResultOK(4, list, list.size());
        } else {
            return NoSatoken();
        }
    }
}

