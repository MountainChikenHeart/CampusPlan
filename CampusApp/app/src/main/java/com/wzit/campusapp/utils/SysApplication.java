package com.wzit.campusapp.utils;

import static com.kongzue.dialog.v2.DialogSettings.STYLE_KONGZUE;
import static com.kongzue.dialog.v2.DialogSettings.THEME_LIGHT;

import android.app.Application;
import android.content.Context;
import com.kongzue.dialog.v2.DialogSettings;
import com.scwang.smartrefresh.header.WaveSwipeHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.wzit.campusapp.R;
import com.wzit.campusapp.utils.common.CommonUtils;
import com.xuexiang.xui.XUI;

/**
 * 全局配置
 */
public class SysApplication extends Application {

    private static Context context;//全局上下文
    public static Application sApplication;//全局context
    private static SysApplication INSTANCE = null;//实例对象
    public static boolean sDeBug;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        //XUI
        XUI.debug(true);  //开启UI框架调试日志
        //空祖对话框
        DialogSettings.style = STYLE_KONGZUE;//MATERIAL主题
        DialogSettings.tip_theme = THEME_LIGHT;         //设置提示框主题为亮色主题
        DialogSettings.use_blur = true;                 //设置是否启用模糊
        CommonUtils.init(this);//工具类初始化
        context = getApplicationContext();
    }
    //SmartRefresh刷新配置
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                return new WaveSwipeHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
    }


    /**
     * 获取全局上下文
     */
    public static Context getContext() {
        return context;
    }

    /**
     * 获取实例对象
     */
    public static SysApplication getInstance() {
        return INSTANCE;
    }
}
