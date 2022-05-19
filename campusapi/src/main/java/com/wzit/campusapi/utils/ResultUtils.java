package com.wzit.campusapi.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static com.wzit.campusapi.utils.ResultCode.*;
import static com.wzit.campusapi.utils.ResultMsg.*;

/**
 * 返回json数据封装
 */
public class ResultUtils {

    private static final HashMap<Object, Object> map = new HashMap<>();

    /**
     * 统一json数组构造
     * @param data 返回的数据
     */
    public static ArrayList<Object> DataList(Object data){
        ArrayList<Object> list = new ArrayList<>();
        list.add(data);
        return list;
    }

    /**
     * 异常返回
     * @param code 状态码
     * @param msg 消息
     */
    public static HashMap<Object, Object> ResultData(int code, String msg) {
        map.put("code", code);
        map.put("msg", msg);
        map.put("data", new HashMap<>());
        map.put("pagesum", 0);
        map.put("timestamp", new Date().getTime());
        return map;
    }

    /**
     * 正常返回 ----------------> 查询某一条数据时调用
     * @param data 返回的数据
     */
    public static HashMap<Object, Object> ResultData(Object data) {
        map.put("code", CODE_OK);
        map.put("msg", MSG_OK);
        map.put("data", data);
        map.put("pagesum", 0);
        map.put("timestamp", new Date().getTime());
        return map;
    }

    /**
     * 正常返回 -----------------> 分页查询时调用
     * @param data 返回的数据
     * @param pagenum 当前页码
     * @param pagesize 每页数量
     */
    public static HashMap<Object, Object> ResultData(Object data,int pagenum, int pagesize) {
        map.put("code", CODE_OK);
        map.put("msg", MSG_OK);
        map.put("data", data);
        map.put("pagesize", pagesize);
        map.put("timestamp", new Date().getTime());
        return map;
    }

    /**
     * 正常返回(带页数) -----------------> 分页查询时调用
     * @param data 返回的数据
     * @param pagenum 当前页码
     * @param pagesize 每页数量
     * @param pagesum 页面总数
     */
    public static HashMap<Object, Object> ResultData(Object data,int pagenum, int pagesize,int pagesum) {
        map.put("code", CODE_OK);
        map.put("msg", MSG_OK);
        map.put("data", data);
        map.put("pagesum", pagesum);
        map.put("timestamp", new Date().getTime());
        return map;
    }

    /**
     * 正常返回 ----------------> 查询某一条数据时调用
     * @param data 返回的数据
     */
    public static HashMap<Object, Object> ResultData(Object data,int code,String msg,long pagesum) {
        map.put("code", code);
        map.put("msg", msg);
        map.put("data", data);
        map.put("pagesum",pagesum);
        map.put("timestamp", new Date().getTime());
        return map;
    }


    /**
     * 正常返回 -----------------> 分页查询时调用
     * @param data 返回的数据
     * @param code 状态码
     * @param msg 消息
     * @param pagenum 当前页码
     * @param pagesize 每页数量
     */
    public static HashMap<Object, Object> ResultData(Object data,int code,String msg, int pagenum, int pagesize) {
        map.put("code", code);
        map.put("msg", msg);
        map.put("data", data);
        map.put("pagesize", pagesize);
        map.put("timestamp", new Date().getTime());
        return map;
    }

    /**
     * 正常返回(带页数) -----------------> 分页查询时调用
     * @param data 返回的数据
     * @param code 状态码
     * @param msg 消息
     * @param pagenum 当前页码
     * @param pagesize 每页数量
     * @param pagesum 页面总数
     */
    public static HashMap<Object, Object> ResultData(Object data,int code,String msg, int pagenum, int pagesize,int pagesum) {
        map.put("code", code);
        map.put("msg", msg);
        map.put("data", data);
        map.put("pagesum", pagesum);
        map.put("timestamp", new Date().getTime());
        return map;
    }

}
