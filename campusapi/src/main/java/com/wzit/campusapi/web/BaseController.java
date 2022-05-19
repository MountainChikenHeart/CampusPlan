package com.wzit.campusapi.web;

import cn.dev33.satoken.exception.DisableLoginException;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.dev33.satoken.stp.StpUtil;
import com.wzit.campusapi.entity.UserInfo;
import com.wzit.campusapi.service.UserInfoService;
import com.wzit.campusapi.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.BindException;
import java.util.HashMap;
import java.util.List;

import static com.wzit.campusapi.utils.ResultCode.*;
import static com.wzit.campusapi.utils.ResultMsg.*;
import static com.wzit.campusapi.utils.ResultUtils.*;


/**
 * 控制层基类,全局异常处理，sa-token要加上@RestControllerAdvice才生效（重点）
 *
 * @ControllerAdvice 捕获 Controller 层抛出的异常，如果添加 @ResponseBody 返回信息则为JSON 格式。
 * @RestControllerAdvice 相当于 @ControllerAdvice 与 @ResponseBody 的结合体。
 * @ExceptionHandler 统一处理一种类的异常，减少代码重复率，降低复杂度。
 */
@RestControllerAdvice
public class BaseController {
    //日志输出
    protected static final Logger log = LoggerFactory.getLogger(BaseController.class);

    @Resource
    HttpServletRequest request;

    @Resource
    private UserInfoService userInfoService;

    /**
     * 全局异常拦截（拦截项目中的所有异常）
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handlerException(Exception e) {
        // 打印堆栈，以供调试
        System.out.println("全局异常---------------");
        e.printStackTrace();
        HashMap<Object, Object> map = new HashMap<>();
        // 不同异常返回不同状态码
        if (e instanceof NotLoginException) {// 如果是未登录异常
            NotLoginException ee = (NotLoginException) e;
            map = ResultData(CODE_ERR, ee.getMessage());
        } else if (e instanceof NotRoleException) {// 如果是角色异常
            NotRoleException ee = (NotRoleException) e;
            map = ResultData(CODE_ERR, ee.getMessage());
        } else if (e instanceof NotPermissionException) {// 如果是权限异常
            NotPermissionException ee = (NotPermissionException) e;
            map = ResultData(CODE_ERR, ee.getMessage());
        } else if (e instanceof DisableLoginException) {// 如果是被封禁异常
            DisableLoginException ee = (DisableLoginException) e;
            map = ResultData(CODE_ERR, "账号被封禁：" + ee.getDisableTime() + "秒后解封");
        } else {// 普通异常, 输出：500 + 异常信息
            map = ResultData(CODE_ERR, e.getMessage());
        }
        return map;
    }

    /**
     * 请求缺失参数
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public Object handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error(e.getMessage(), e);
        return ResultData(CODE_MIS_PARAM, MSG_MIS_PARAM + e.getMessage());
    }

    /**
     * 请求参数解析失败(参数读取失败)
     */
    @ExceptionHandler(HttpMessageConversionException.class)
    @ResponseBody
    public Object handleHttpMessageNotReadableException(HttpMessageConversionException e) {
        log.error(e.getMessage(), e);
        return ResultData(CODE_READ_PARAM_ERR, MSG_READ_PARAM_ERR + e.getMessage());
    }

    /**
     * 请求参数验证失败
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        return ResultData(CODE_VER_PARAM_ERR, MSG_VER_PARAM_ERR + e.getMessage());
    }

    /**
     * 请求参数绑定失败
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Object handleBindException(BindException e) {
        log.error(e.getMessage(), e);
        return ResultData(CODE_BIND_PARAM_ERR, MSG_BIND_PARAM_ERR + e.getMessage());
    }

    /**
     * 空指针异常处理
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public Object handleNullPointerException(NullPointerException e) {
        log.error(e.getMessage(), e);
        return ResultData(CODE_NULL_EXC, MSG_NULL_EXC);
    }

    /**
     * 处理运行时异常（500）
     */
    @ExceptionHandler({RuntimeException.class})
    @ResponseBody
    public Object handleRuntimeException(RuntimeException e) {
        log.error(e.getMessage(), e);
        return ResultData(CODE_RUN_EXC, MSG_RUN_EXC + e.getMessage());
    }

    /**
     * 404异常处理（请求路径不存在）
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public Object NoHandlerFoundExceptionHandler(NoHandlerFoundException e) {
        log.error(e.getMessage(), e);
        return ResultData(CODE_NOT_FOUND, MSG_NOT_FOUND);
    }

    /**
     * 全局异常拦截（拦截项目中的NotLoginException异常）登录异常
     */
    @ExceptionHandler(NotLoginException.class)
    public Object handlerNotLoginException(NotLoginException nle) {
        // 打印堆栈，以供调试
        nle.printStackTrace();
        // 判断场景值，定制化异常信息
        switch (nle.getType()) {
            case NotLoginException.NOT_TOKEN: //未提供TOKEN
                return ResultData(CODE_NO_TOKEN, MSG_NO_TOKEN);
            case NotLoginException.INVALID_TOKEN: //token无效
                return ResultData(CODE_INVALID_TOKEN, MSG_INVALID_TOKEN);
            case NotLoginException.TOKEN_TIMEOUT: //token已过期
                return ResultData(CODE_TOKEN_EXP, MSG_TOKEN_EXP);
            case NotLoginException.BE_REPLACED: //token已被顶下线
                return ResultData(CODE_TOKEN_OFFLINE, MSG_TOKEN_OFFLINE);
            case NotLoginException.KICK_OUT: //token已被踢下线
                return ResultData(CODE_TOKEN_KICK, MSG_TOKEN_KICK);
            default: //当前会话未登录
                return ResultData(CODE_NOT_LOGIN, MSG_NOT_LOGIN);
        }
    }


    /**
     * 统一返回json数据 (单条查询调用)
     *
     * @param data 返回的数据
     */
    protected Object ResultApi(Object data) {
        if (StringUtils.isEmpty(request.getHeader("satoken"))) {//识别satoken
            return ResultData(CODE_NO_TOKEN, MSG_NO_TOKEN);
        } else {
            //检查登录
            StpUtil.checkLogin();
            //先检查是否已过期
            StpUtil.checkActivityTimeout();
            //检查通过后继续续签
            StpUtil.updateLastActivityToNow();
            return ResultData(data);
        }
    }

    /**
     * 统一返回json数据 (多条查询调用)
     *
     * @param data     返回的数据
     * @param pagenum  当前页码
     * @param pagesize 每页数量
     */
    protected Object ResultApi(Object data, int pagenum, int pagesize) {
        if (StringUtils.isEmpty(request.getHeader("satoken"))) {//识别satoken
            return ResultData(CODE_NO_TOKEN, MSG_NO_TOKEN);
        } else {
            //检查登录
            StpUtil.checkLogin();
            //先检查是否已过期
            StpUtil.checkActivityTimeout();
            //检查通过后继续续签
            StpUtil.updateLastActivityToNow();
            return ResultData(data, pagenum, pagesize);
        }
    }

    /**
     * 统一返回json数据 (多条查询调用)
     *
     * @param data     返回的数据
     * @param pagenum  当前页码
     * @param pagesize 每页数量
     * @param pagesum  页面总数
     */
    protected Object ResultApi(Object data, int pagenum, int pagesize, int pagesum) {
        if (StringUtils.isEmpty(request.getHeader("satoken"))) {//识别satoken
            return ResultData(CODE_NO_TOKEN, MSG_NO_TOKEN);
        } else {
            //检查登录
            StpUtil.checkLogin();
            //先检查是否已过期
            StpUtil.checkActivityTimeout();
            //检查通过后继续续签
            StpUtil.updateLastActivityToNow();
            return ResultData(data, pagenum, pagesize, pagesum);
        }
    }

    /**
     * 无验证 统一返回json数据
     *
     * @param data 返回的数据
     */
    protected Object NResultApi(Object data) {
        return ResultData(data);
    }

    /**
     * 无验证 统一返回json数据
     *
     * @param data     返回的数据
     * @param pagenum  当前页码
     * @param pagesize 每页数量
     */
    protected Object NResultApi(Object data, int pagenum, int pagesize) {
        return ResultData(data, pagenum, pagesize);
    }

    /**
     * 无验证 统一返回json数据
     *
     * @param data     返回的数据
     * @param pagenum  当前页码
     * @param pagesize 每页数量
     * @param pagesum  页面总数
     */
    protected Object NResultApi(Object data, int pagenum, int pagesize, int pagesum) {
        return ResultData(data, pagenum, pagesize, pagesum);
    }


    /**
     * 统一返回json数据 (单条查询调用)
     *
     * @param data 返回的数据
     * @param code 状态码
     * @param msg  消息
     */
    protected Object ResultApi(Object data, int code, String msg) {
        if (StringUtils.isEmpty(request.getHeader("satoken"))) {//识别satoken
            return ResultData(CODE_NO_TOKEN, MSG_NO_TOKEN);
        } else {
            //检查登录
            StpUtil.checkLogin();
            //先检查是否已过期
            StpUtil.checkActivityTimeout();
            //检查通过后继续续签
            StpUtil.updateLastActivityToNow();
            return ResultData(data, code, msg, 0);
        }
    }

    /**
     * 统一返回json数据 (多条查询调用)
     *
     * @param data     返回的数据
     * @param code     状态码
     * @param msg      消息
     * @param pagenum  当前页码
     * @param pagesize 每页数量
     */
    protected Object ResultApi(Object data, int code, String msg, int pagenum, int pagesize) {
        if (StringUtils.isEmpty(request.getHeader("satoken"))) {//识别satoken
            return ResultData(CODE_NO_TOKEN, MSG_NO_TOKEN);
        } else {
            //检查登录
            StpUtil.checkLogin();
            //先检查是否已过期
            StpUtil.checkActivityTimeout();
            //检查通过后继续续签
            StpUtil.updateLastActivityToNow();
            return ResultData(data, code, msg, pagenum, pagesize);
        }
    }

    /**
     * 统一返回json数据 (多条查询调用)
     *
     * @param data     返回的数据
     * @param code     状态码
     * @param msg      消息
     * @param pagenum  当前页码
     * @param pagesize 每页数量
     * @param pagesum  页面总数
     */
    protected Object ResultApi(Object data, int code, String msg, int pagenum, int pagesize, int pagesum) {
        if (StringUtils.isEmpty(request.getHeader("satoken"))) {//识别satoken
            return ResultData(CODE_NO_TOKEN, MSG_NO_TOKEN);
        } else {
            //检查登录
            StpUtil.checkLogin();
            //先检查是否已过期
            StpUtil.checkActivityTimeout();
            //检查通过后继续续签
            StpUtil.updateLastActivityToNow();
            return ResultData(data, code, msg, pagenum, pagesize, pagesum);
        }
    }

    /**
     * 无验证 统一返回json数据
     *
     * @param data 返回的数据
     * @param code 状态码
     * @param msg  消息
     */
    protected Object NResultApi(Object data, int code, String msg) {
        return ResultData(data, code, msg, 0);
    }

    /**
     * 无验证 统一返回json数据
     *
     * @param data     返回的数据
     * @param code     状态码
     * @param msg      消息
     * @param pagenum  当前页码
     * @param pagesize 每页数量
     */
    protected Object NResultApi(Object data, int code, String msg, int pagenum, int pagesize) {
        return ResultData(data, code, msg, pagenum, pagesize);
    }

    /**
     * 无验证 统一返回json数据
     *
     * @param data     返回的数据
     * @param code     状态码
     * @param msg      消息
     * @param pagenum  当前页码
     * @param pagesize 每页数量
     * @param pagesum  页面总数
     */
    protected Object NResultApi(Object data, int code, String msg, int pagenum, int pagesize, int pagesum) {
        return ResultData(data, code, msg, pagenum, pagesize, pagesum);
    }

    /**
     * 通过token查询用户信息
     */
    protected UserInfo getUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(StpUtil.getLoginId()+"");
        System.out.println(StpUtil.getLoginId()+"");
        List<UserInfo> list = userInfoService.selectall(userInfo);
        log.info("登录用户：------->" + list.get(0).getUserName());
        log.info("登录用户token：------->" + StpUtil.getTokenValue());
        return list.get(0);
    }

    /**
     * Satoken校验
     */
    protected int SaTokenCheck() {
        if (StringUtils.isEmpty(request.getHeader("satoken"))) {//识别satoken
            return 2;
        }
        //检查登录
        StpUtil.checkLogin();
        //先检查是否已过期
        StpUtil.checkActivityTimeout();
        //检查通过后继续续签
        StpUtil.updateLastActivityToNow();
        return 1;
    }

    /**
     * 错误返回
     *
     * @param operation 1.添加（增） 2.删除（删） 3.修改（改） 4.查询（查）
     */
    protected Object ResultErr(int operation) {
        if (operation == 1) {//增
            return ResultData(new HashMap<>(), CODE_INSERT_ERR, MSG_INSERT_ERR, 0);
        } else if (operation == 2) {//删除
            return ResultData(new HashMap<>(), CODE_DELETE_ERR, MSG_DELETE_ERR, 0);
        } else if (operation == 3) {//修改
            return ResultData(new HashMap<>(), CODE_UPDATE_ERR, MSG_UPDATE_ERR, 0);
        } else {//查询
            return ResultData(new HashMap<>(), CODE_SELECT_ERR, MSG_SELECT_ERR, 0);
        }
    }


    /**
     * 成功返回
     *
     * @param operation 1.添加（增） 2.删除（删） 3.修改（改） 4.查询（查）
     */
    protected Object ResultOK(int operation,Object data, long pagesum) {
        if (operation == 1) {//增
            return ResultData(data, CODE_INSERT_OK, MSG_INSERT_OK, pagesum);
        } else if (operation == 2) {//删除
            return ResultData(data, CODE_DELETE_OK, MSG_DELETE_OK, pagesum);
        } else if (operation == 3) {//修改
            return ResultData(data, CODE_UPDATE_OK, MSG_UPDATE_OK, pagesum);
        } else {//查询
            return ResultData(data, CODE_SELECT_OK, MSG_SELECT_OK, pagesum);
        }
    }

    /**
     * 成功返回
     */
    protected Object ResultOK(Object data) {
        return ResultData(data, CODE_OK, MSG_OK, 1);
    }

    /**
     * 无satoken返回
     */
    protected Object NoSatoken() {
        return ResultData(new HashMap<>(), CODE_NO_TOKEN, MSG_NO_TOKEN, 0);
    }

    /**
     * 已存在返回
     */
    protected Object ResultEXI() {
        return ResultData(new HashMap<>(), CODE_EXI, MSG_EXI, 1);
    }
    /**
     * 已存在返回
     */
    protected Object ResultEXI(String msg) {
        return ResultData(new HashMap<>(), CODE_EXI, msg + MSG_EXI, 1);
    }
}
