package com.wzit.campusapi.utils;

/**
 * 通用返回码
 */
public class ResultCode {

    //操作成功
    public static final int CODE_OK = 200;
    //操作失败
    public static final int CODE_ERR = 400;
    //无权限操作
    public static final int CODE_UN_AUT = 401;
    //鉴权失败
    public static final int CODE_AUT_FAILED = 402;
    //禁止访问
    public static final int CODE_FORBIDDEN = 403;
    //页面不存在
    public static final int CODE_NOT_FOUND = 404;
    //运行时异常
    public static final int CODE_RUN_EXC= 500;
    //改邮箱已被注册
    public static final int CODE_EMAIL_EXI = -2;
    //用户已存在
    public static final int CODE_USER_EXI = -2;
    //未提供token
    public static final int CODE_NO_TOKEN = 1001;
    //token无效
    public static final int CODE_INVALID_TOKEN = 1002;
    //token已过期
    public static final int CODE_TOKEN_EXP = 1003;
    //token已被顶下线
    public static final int CODE_TOKEN_OFFLINE = 1004;
    //token已被踢下线
    public static final int CODE_TOKEN_KICK = 1005;
    //当前会话未登录
    public static final int CODE_NOT_LOGIN = 1006;
    //缺少参数
    public static final int CODE_MIS_PARAM = 1007;
    //参数验证失败
    public static final int CODE_VER_PARAM_ERR = 1008;
    //参数读取失败
    public static final int CODE_READ_PARAM_ERR = 1009;
    //参数绑定失败
    public static final int CODE_BIND_PARAM_ERR = 1010;
    //空指针异常
    public static final int CODE_NULL_EXC = 1011;
    //注册失败
    public static final int CODE_REGISTER_ERR = -1;
    //注册成功
    public static final int CODE_REGISTER_OK = 1;


    //添加失败
    public static final int CODE_INSERT_ERR = -1;
    //删除失败
    public static final int CODE_DELETE_ERR = -1;
    //修改失败
    public static final int CODE_UPDATE_ERR = -1;
    //查询失败
    public static final int CODE_SELECT_ERR = -1;


    //添加失败
    public static final int CODE_INSERT_OK = 1;
    //删除失败
    public static final int CODE_DELETE_OK = 1;
    //修改失败
    public static final int CODE_UPDATE_OK = 1;
    //查询失败
    public static final int CODE_SELECT_OK = 1;

    //已存在
    public static final int CODE_EXI = -2;

}
