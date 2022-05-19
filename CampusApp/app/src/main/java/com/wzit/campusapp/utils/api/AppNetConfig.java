package com.wzit.campusapp.utils.api;


/**
 * 后台API数据接口
 */
public class AppNetConfig {

    //接口前缀
    //服务器
//    public static final String ApiAddress = "http://120.24.109.123:8087/";
    //本地
    public static final String ApiAddress = "http://47.93.80.50:8087/";
    //加载图片
    public static final String ImgApi = "http://47.93.80.50:8087";
    //用户
    public static final String userInfo = ApiAddress + "userInfo/";
    //任务计划表
    public static final String planInfo = ApiAddress + "planInfo/";
    //圈子
    public static final String communityInfo = ApiAddress + "communityInfo/";
    //圈子视图
    public static final String communityInfoView = ApiAddress + "communityInfoView/";
    //加入圈子
    public static final String cmtPersonInfo = ApiAddress + "cmtPersonInfo/";
    //加入圈子视图
    public static final String cmtPersonInfoView = ApiAddress + "cmtPersonInfoView/";
    //圈子任务
    public static final String comtInfo = ApiAddress + "comtInfo/";
    //圈子任务提交
    public static final String subComtInfo = ApiAddress + "subComtInfo/";
    //用户注册
    public static final String Register = userInfo + "insert";
    //用户登录
    public static final String Login = userInfo + "login";
    //用户更新
    public static final String getUserUpdate = userInfo + "update";
    //提交任务
    public static final String InsertPlan = planInfo + "insert";
    //删除任务
    public static final String DeletePlan = planInfo + "delete";
    //获取任务
    public static final String getPlan = planInfo + "selectall";
    //任务更新
    public static final String PlanInfoUpdate = planInfo + "update";
    //获取四象限
    public static final String getSxx = planInfo + "sxx";
    //获取四象限数据数量
    public static final String getSxxCount = planInfo + "sxxCount";
    //上传图片
    public static final String upload = ApiAddress + "common/upload";
    //发布圈子
    public static final String InsertCommunityInfo = communityInfo + "insert";
    //删除圈子
    public static final String DeleteCommunityInfo = communityInfo + "delete";
    //查询圈子
    public static final String getCommunityInfo = communityInfoView + "selectall";
    //查询所有圈子数量
    public static final String getCommunityInfoCount = communityInfoView + "selectcount";
    //根据条件查询圈子
    public static final String getCommunityInfoLimit = communityInfoView + "selectallLimit";
    //根据条件查询圈子的数量
    public static final String getCommunityInfoLimitCount = communityInfoView + "selectallLimitCount";
    //加入圈子
    public static final String InsertCmtPersonInfo = cmtPersonInfo + "insert";
    //查询圈子成员
    public static final String getCmtPersonInfo= cmtPersonInfoView + "selectall";
    //增加圈子任务
    public static final String insertComtInfo= comtInfo + "insert";
    //查询圈子任务
    public static final String getComtInfo= comtInfo + "selectall";
    //删除圈子任务
    public static final String DeleteComtInfo= comtInfo + "delete";
    //提交圈子任务
    public static final String insertSubComtInfo= subComtInfo + "insert";
    //查询提交的圈子任务
    public static final String getSubComtInfo= subComtInfo + "selectall";
    //查询提交的圈子任务总数
    public static final String getSubComtInfoCount= subComtInfo + "selectcount";
    //查询未完成圈子任务的用户
    public static final String updateSubComtInfo= subComtInfo + "update";
    //提醒未完成圈子任务的用户
    public static final String updateComtInfoUrge= comtInfo + "updateurge";
    //查询是否被提醒
    public static final String getComtInfoUrge= comtInfo + "selecturge";
}
