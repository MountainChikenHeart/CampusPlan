package com.wzit.campusapp.utils.common;

import static android.view.animation.Animation.INFINITE;
import static com.kongzue.dialog.v2.DialogSettings.STYLE_MATERIAL;
import static com.kongzue.dialog.v2.DialogSettings.THEME_LIGHT;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;
import com.kongzue.dialog.v2.DialogSettings;
import com.kongzue.dialog.v2.TipDialog;
import com.kongzue.dialog.v2.WaitDialog;
import com.lcw.library.imagepicker.ImagePicker;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnCancelListener;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.wzit.campusapp.bean.UserLoginBean;
import com.wzit.campusapp.interfaces.OkSuccessInterface;
import com.wzit.campusapp.interfaces.XPopupInterface;
import com.wzit.campusapp.utils.api.AppNetConfig;
import com.wzit.campusapp.view.DYLoadingView;
import com.wzit.campusapp.view.GlideLoader;
import com.wzit.campusapp.view.CustomVideoView;
import com.wzit.campusapp.view.dialog.DYLoading;
import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xui.widget.dialog.LoadingDialog;
import com.xuexiang.xui.widget.toast.XToast;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * 通用工具类封装
 */
public class CommonUtils {

    private static Application context;//上下文
    private static long exitTime = 0;//起始时间为0
    @SuppressLint("StaticFieldLeak")
    private static LoadingDialog mLoadingDialog;
    public static String TAG = "日志输出：";

    /**
     * 获取上下文
     */
    public static void init(Application application){
       context = application;
    }

    /**
     * 底部切换默认颜色
     */
    public static final String DefaultColor = "#8a8a8a";
    /**
     * 底部切换默认颜色2
     */
    public static final String DefaultColor2 = "#8a8a8a";

    /**
     * 底部切换改变颜色
     */
    public static final String ChangeColor = "#FF03DAC5";

    /**
     * 让状态栏字体颜色变黑色
     * @param activity 主活动activity
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void SetTitleBarFontColor(Activity activity){
        activity.getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
    }

    /**
     *  隐藏状态栏和导航栏、标题栏(全屏)
     * @param activity 主活动activity
     */
    public static void HideBarAll(@NonNull Activity activity) {
        ImmersionBar.with(activity).hideBar(BarHide.FLAG_HIDE_BAR);
    }

    /**
     * 全屏
     * @param activity 主活动activity
     */
    public static void FullScreen(Activity activity){
        //沉浸式
        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }

    /**
     * 存储信息
     * @param Mode Mode
     * @param key 键值
     * @param text 存储的内容
     */
    public static void SaveKey(String Mode,String key,String text){
        SharedPreferences preferences = context.getSharedPreferences(Mode, Context.MODE_PRIVATE);
        preferences.edit().putString(key, text).apply();
    }


    /**
     * 获取存储信息
     * @param Mode Mode
     * @param key 键值
     */
    public static String ShareKey(String Mode, String key){
        SharedPreferences preferences = context.getSharedPreferences(Mode, Context.MODE_PRIVATE);
        String text = preferences.getString(key,null);
        return text;
    }

    /**
     * 清除保存的存储信息
     * @param Mode 要清除的Mode
     */
    public static void ClearKey(String Mode){
        SharedPreferences preferences = context.getSharedPreferences(Mode, Context.MODE_PRIVATE);
        preferences.edit().clear().apply();
    }

    /**
     * Toast封装
     * @param text 显示的内容
     */
    public static void ToastShow(String text){
       Toast toast = Toast.makeText(context,text,Toast.LENGTH_SHORT);
       toast.setGravity(Gravity.CENTER,0,0);
       toast.show();
    }

    /**
     * Toast封装
     * @param text 显示的内容
     */
    public static void ToastShow2(String text){
        Toast toast = Toast.makeText(context,text,Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * 跳转到指定class页面
     * @param cls 要跳转的页面
     */
    public static void ToPage(Class<?> cls){
        Intent intent = new Intent(context,cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 跳转到指定class页面并传递参数
     * @param cls 要跳转的页面
     */
    public static void ToPageAndSend(Class<?> cls, String name, String value){
        Intent intent = new Intent(context,cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(name, value);
        context.startActivity(intent);
    }

    /**
     * 获取EditText的文字
     * @param editText EditText
     */
    public static String GetEditStr(EditText editText){
        return editText.getText().toString().trim();
    }

    /**
     * 获取TextView的文字
     * @param textView TextView
     */
    public static String GetTextStr(TextView textView){
        return textView.getText().toString().trim();
    }

    /**
     * 获取当前时间
     */
    public static String CurrentTimeYMDHMS(){
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        String time = simpleDateFormat.format(date);
        return time;
    }

    /**
     * 验证手机格式
     * @param number s手机号
     */
    public static boolean IsMobile(String number) {
    /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188、170
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    添加新号段:第二位新增4、6、7、9
    */
        String num = "[1][3456789]\\d{9}";//"[1]"代表第1位为数字1，"[3578]"代表第二位可以为3、5、7、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (!IsString(number)) {
            return false;
        } else {
            //matches():字符串是否在给定的正则表达式匹配
            return number.matches(num);
        }
    }

    /**
     * 手机号用****号隐藏前面中间数字
     * @param phone 手机号
     */
    public static String HideMiddlePhone(String phone) {
        String phones = phone.substring(0, 3) + "****" + phone.substring(7);
        return phones;
    }

    /**
     * 验证邮箱格式
     * @param email 邮箱
     */
    public static boolean IsEmail(String email) {
        String emailMatch = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        if (TextUtils.isEmpty(email)) {
            return false;
        } else {
            //matches():字符串是否在给定的正则表达式匹配
            return email.matches(emailMatch);
        }
    }

    /**
     * 判断是否为整数
     * @param str 传入的字符串
     * @return 是整数返回true, 否则返回false
     */
    public static boolean IsInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 邮箱用****号隐藏前面的字母
     * @param email 邮箱
     */
    public static String HideFrontEmail(String email) {
        String emails = email.replaceAll("(\\w?)(\\w+)(\\w)(@\\w+\\.[a-z]+(\\.[a-z]+)?)", "$1****$3$4");
        return emails;
    }

    /**
     * 验证身份证号是否符合规则
     * @param IDNumber 身份证号
     */
    public static boolean IsIdCard(String IDNumber) {

        if (!IsString(IDNumber))
            return false;
        // 定义判别用户身份证号的正则表达式（15位或者18位，最后一位可以为字母）
        String regularExpression = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" +
                "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";
        //假设18位身份证号码:41000119910101123X  410001 19910101 123X
        //^开头
        //[1-9] 第一位1-9中的一个      4
        //\\d{5} 五位数字           10001（前六位省市县地区）
        //(18|19|20)                19（现阶段可能取值范围18xx-20xx年）
        //\\d{2}                    91（年份）
        //((0[1-9])|(10|11|12))     01（月份）
        //(([0-2][1-9])|10|20|30|31)01（日期）
        //\\d{3} 三位数字            123（第十七位奇数代表男，偶数代表女）
        //[0-9Xx] 0123456789Xx其中的一个 X（第十八位为校验值）
        //$结尾

        //假设15位身份证号码:410001910101123  410001 910101 123
        //^开头
        //[1-9] 第一位1-9中的一个      4
        //\\d{5} 五位数字           10001（前六位省市县地区）
        //\\d{2}                    91（年份）
        //((0[1-9])|(10|11|12))     01（月份）
        //(([0-2][1-9])|10|20|30|31)01（日期）
        //\\d{3} 三位数字            123（第十五位奇数代表男，偶数代表女），15位身份证不含X
        //$结尾
        return IDNumber.matches(regularExpression);
    }

    /**
     * 判断字符串是否为空
     * @param s 字符串
     */
    public static String IsEmpry(String s) {
        if (null == s) {
            return "";
        } else if (s.equals("")) {
            return "";
        } else if (s.equals("null")) {
            return "";
        } else {
            return s;
        }
    }

    /**
     * 判断字符串是否是String
     * @param s 字符串
     */
    public static boolean IsString(String s) {
        if (null == s) {
            return false;
        } else if (s.equals("")) {
            return false;
        } else if (s.equals("null")) {
            return false;
        } else return s.length() > 0;
    }

    /**
     * 判断字符串数组是否是String
     * @param s 动态字符串数组
     */
    public static boolean IsString(String... s) {
        if (s.length <= 0)
            return false;
        for (String str : s) {
            if (null == str) {
                return false;
            } else if (str.equals("")) {
                return false;
            } else if (str.equals("null")) {
                return false;
            } else if (str.length() <= 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 从字符串中提取数字
     * @param str 要提提取的字符串
     */
    public static String GetStrNum(String str) {
        if (IsString(str)) {
            String regEx = "[^0-9]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(str);
            return m.replaceAll("").trim();
        } else {
            return "";
        }
    }

    /**
     * 设置导航栏颜色
     * @param activity 主活动activity
     * @param color 显示的颜色
     */
    public static void SetSystemBarColor(Activity activity,int color) {
        ImmersionBar.with(activity).navigationBarColor(color);
    }

    /**
     * 底部tab切换颜色变化
     */
    public static void SetCurPoint(TextView tvone, ImageView ivsel,int a){
        ivsel.setImageResource(a);
        ivsel.setColorFilter(Color.parseColor(ChangeColor));
        tvone.setTextColor(Color.parseColor(ChangeColor));
    }


    /**
     * 底部tab切换颜色变化(3个Tab)
     */
    public static void SetCurPoint(TextView icone, TextView ictwo, TextView icthree,
                                   String one, String two, String three){
        icone.setTextColor(Color.parseColor(one));
        ictwo.setTextColor(Color.parseColor(two));
        icthree.setTextColor(Color.parseColor(three));
    }

    /**
     * 跳转到显示的页面
     * @param fragment 要跳转的Fragment
     * @param activity 主活动activity
     */
    public static void IntoFragment(Fragment fragment, FragmentActivity activity,int pos) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
//        beginTransaction.add(R.id.childrenshow, fragment);//初始化页面
        beginTransaction.commit();
        if (pos == 1){
            beginTransaction.show(fragment);
        }

    }

    /**
     * 获取本机IPV4地址的方法
     */
    public static String GetIpAddress() {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                if (netInterface.isLoopback() || netInterface.isVirtual() || !netInterface.isUp()) {
                    continue;
                } else {
                    Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        ip = addresses.nextElement();
                        if (ip != null && ip instanceof Inet4Address) {
                            return ip.getHostAddress();
                        }
                    }
                }
            }
        } catch (Exception e) {
            return "IP地址获取失败";
        }
        return "";
    }

    /**
     * 将 Date 类型转为时间字符串
     * @param date date类型的时间
     * @param format 时间格式类型
     */
    public static String DateToString(Date date, DateFormat format) {
        if (format != null) {
            return format.format(date);
        } else {
            return "";
        }
    }

    /**
     * XUI时间选择需要
     * 时间格式yyyy-MM-dd
     */
    public static final ThreadLocal<DateFormat> YYYYMMDD = new ThreadLocal<DateFormat>() {
        @SuppressLint("SimpleDateFormat")
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    /**
     * 转换时间日期格式字串为long型
     * @param time 时间字符串
     */
    public static Long ConvertTimeToLong(String time) {
        Date date = null;
        try {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf.parse(time);
            assert date != null;
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    /**
     * 获取当前时间
     */
    public static long CurrentTimeYMD(){
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        return Long.parseLong(simpleDateFormat.format(date));
    }

    /**
     *  XToast.error封装
     * @param text 显示的内容
     */
    @SuppressLint("CheckResult")
    public static void TError(String text) {
        Toast toast = XToast.error(context, text);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }
    /**
     *  XToast.error封装
     * @param text 显示的内容
     */
    @SuppressLint("CheckResult")
    public static void TError2(String text) {
        Toast toast = XToast.error(context, text);
        toast.show();
    }


    /**
     *  XToast.warning封装
     * @param text 显示的内容
     */
    public static void TWarning(String text) {
        Toast toast = XToast.warning(context, text);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    /**
     *  XToast.warning封装
     *  @param text 显示的内容
     */
    public static void TWarning2(String text) {
        Toast toast = XToast.warning(context, text);
        toast.show();
    }

    /**
     *  XToast.success封装
     * @param text 显示的内容
     */
    public static void TSuccess(String text) {
        Toast toast = XToast.success(context, text);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    /**
     *  XToast.success封装
     * @param text 显示的内容
     */
    public static void TSuccess2(String text) {
        Toast toast = XToast.success(context, text);
        toast.show();
    }

    /**
     *  XToast.info封装
     * @param text 显示的内容
     */
    public static void TInfo(String text) {
        Toast toast = XToast.info(context, text);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    /**
     *  XToast.info封装
     * @param text 显示的内容
     */
    public static void TInfo2(String text) {
        Toast toast = XToast.info(context, text);
        toast.show();
    }

    /**
     * 保持不息屏
     * @param activity 主活动activity
     */
    public static void KeepScreenOn(Activity activity) {
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    /**
     * 选择图片
     * @param activity 主活动activity
     * @param code 请求码
     */
    public static void SelectImage(Activity activity, int code) {
        ImagePicker.getInstance()
                .setTitle("选择图片")//设置标题
                .showCamera(true)//设置是否显示拍照按钮
                .showImage(true)//设置是否展示图片
                .showVideo(false)//设置是否展示视频
                .setSingleType(true)//设置图片视频不能同时选择
                .setMaxCount(1)//设置最大选择图片数目(默认为1，单选)
//                        .setImagePaths(mImageList)//保存上一次选择图片的状态，如果不需要可以忽略
                .setImageLoader(new GlideLoader())//设置自定义图片加载器
                .start(activity, code);
    }

    /**
     *  再按一次退出提醒
     * @param activity 主活动activity
     */
    public static void QuitShow(Activity activity ){
        if ((System.currentTimeMillis() - exitTime) > 2000) {//判断此次按键于上一次按键的时间差是否>2s
            XToast.warning(context,"再按一次退出应用").show();
            exitTime = System.currentTimeMillis();//纪录这次按键的时间，下次有用
            return;//时间差大于2s，退出返回事件
        }
        activity.finishAffinity();//时间差小于2s，销毁
    }

    /**
     * OKhttp封装
     * @param activity 主活动activity
     * @param param 传递的参数
     * @param url 接口地址
     * @param successInterface 服务器响应成功接口
     */
    public static void OKHttpPost(Activity activity, HashMap<Object, Object> param, String url, OkSuccessInterface successInterface) {
        LoadingDialog loadingDialog = LoadingShow(activity);
        loadingDialog.show();
        //创建OkHttpClient请求对象
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)//设置连接超时时间
                .readTimeout(20, TimeUnit.SECONDS)//设置读取超时时间
                .build();
        FormBody.Builder formBody = new FormBody.Builder();
        //封装请求的参数
        if (!param.isEmpty()) {
            //遍历集合
            for (Map.Entry<Object, Object> entry : param.entrySet()) {
                formBody.add(entry.getKey().toString(), entry.getValue().toString());
            }
        }
        RequestBody form = formBody.build();
        Request.Builder builder = new Request.Builder();
        Request request = builder.post(form)
                .url(url)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @SuppressLint("CheckResult")
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                loadingDialog.dismiss();
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TWarning("服务器无响应");
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (successInterface != null){
                    loadingDialog.dismiss();
                    String json = Objects.requireNonNull(response.body()).string();
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            successInterface.OnSuccess(json);
                        }
                    });
                }
            }
        });
    }

    /**
     * 播放音乐
     */
    public static void PlayMusic(){
//        Uri uri = Uri.parse("android.resource://com.example.recitewordsapp/" + R.raw.shuaxin);
//        MediaPlayer mediaPlayer = MediaPlayer.create(context, uri);
//        mediaPlayer.start();//开始播放
//        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                mediaPlayer.release();//释放
//            }
//        });
    }

    /**
     * 2秒后销毁页面
     * @param activity 主活动activity
     */
    public static void DelayClosed(final Activity activity){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                activity.finish();
            }
        },2000);
    }

    /**
     *  2秒后跳转到指定页面
     * @param activity 主活动activity
     * @param cls 要跳转到的页面
     */
    public static void DelayTwo(final Activity activity, final Class<?> cls) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ToPage(cls);
                activity.finish();
            }
        }, 1000);
    }

    /**
     * 获取用户信息
     */
    public static UserLoginBean.DataDTO getUserInfo() {
        String userinfo = ShareKey("USER", "user");
        if (userinfo != null) {
            return new Gson().fromJson(userinfo, UserLoginBean.class).getData();
        } else {
            return null;
        }
    }

    /**
     * 请求接口加载框
     * @param activity 主活动activity
     */
    public static LoadingDialog LoadingShow(Activity activity){
        mLoadingDialog = WidgetUtils.getLoadingDialog(activity)
                .setIconScale(0.7F)
                .setLoadingSpeed(8);
      return mLoadingDialog;
    }


    /**
     * 退出登录对话框
     * @param activity 主活动activity
     * @param clss 要跳转到的页面
     */
    public static void LoginOutShow( final Activity activity, final Class<?> clss) {
        new XPopup.Builder(activity).asConfirm("", "确定退出登录吗？",
                new OnConfirmListener() {
                    @Override
                    public void onConfirm() {
                        activity.startActivity(new Intent(activity, clss));
                        ClearKey("USER");
                        activity.finish();
                    }
                })
                .show();
    }


    /**
     * 确认对话框（灵活使用吧，原生的Xpop直接使用也很好）
     * @param activity 主活动activity
     * @param title 标题
     * @param content 内容
     * @param xPopupInterface 对话框确认/取消接口
     */
    public static void ConfirmDialogShow(Activity activity, String title,String content, XPopupInterface xPopupInterface) {
        new XPopup.Builder(activity).asConfirm(title, content,
                new OnConfirmListener() {
                    @Override
                    public void onConfirm() {//确认
                        if (xPopupInterface != null){
                            xPopupInterface.onConfirm();
                        }
                    }
                }, new OnCancelListener() {//取消
                    @Override
                    public void onCancel() {
                        if (xPopupInterface != null){
                            xPopupInterface.onCancel();
                        }
                    }
                })
                .show();
    }

    /**
     * OKhttp封装(json参数传递方式)
     */
    public static void OKJsonPost(Activity activity, String json, String url, OkSuccessInterface successInterface){
        DYLoading loading = new DYLoading(activity);
        loading.show();
        //创建OkHttpClient请求对象
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)//设置连接超时时间
                .readTimeout(60, TimeUnit.SECONDS)//设置读取超时时间
                .build();
        //创建一个RequestBody(参数1：数据类型 参数2传递的json串)
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), json);
        //创建一个请求对象
        Request request = new Request.Builder()
                .addHeader("Content-Type","application/json;charset=UTF-8")
                .addHeader("satoken", getUserInfo() == null ? "" : getUserInfo().getUserToken())
                .url(url)
                .post(requestBody)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @SuppressLint("CheckResult")
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                assert activity != null;
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loading.dismiss();
                        TWarning2("服务器无响应");
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (successInterface != null){
                    String json = Objects.requireNonNull(response.body()).string();
                    assert activity != null;
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.d(TAG, TAG + json);
                            loading.dismiss();
                            successInterface.OnSuccess(json);
                        }
                    });
                }
            }
        });
    }

    /**
     * OKhttp封装(json参数传递方式)无加载动画
     */
//    public static void OKJsonPost(Activity activity, String json, String url, OkSuccessInterface successInterface){
//        //创建OkHttpClient请求对象
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .connectTimeout(60, TimeUnit.SECONDS)//设置连接超时时间
//                .readTimeout(60, TimeUnit.SECONDS)//设置读取超时时间
//                .build();
//        //创建一个RequestBody(参数1：数据类型 参数2传递的json串)
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), json);
//        //创建一个请求对象
//        Request request = new Request.Builder()
//                .addHeader("Content-Type","application/json;charset=UTF-8")
////                .addHeader("satoken",  GetSysInfo() == null ? "" : GetSysInfo().getUserdata().get(0).getUserToken())
//                .url(url)
//                .post(requestBody)
//                .build();
//        okHttpClient.newCall(request).enqueue(new Callback() {
//            @SuppressLint("CheckResult")
//            @Override
//            public void onFailure(@NotNull Call call, @NotNull IOException e) {
//                assert activity != null;
//                activity.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        TWarning(activity,"服务器无响应");
//                    }
//                });
//            }
//
//            @Override
//            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                if (successInterface != null){
//                    String json = Objects.requireNonNull(response.body()).string();
//                    assert activity != null;
//                    activity.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            successInterface.OnSuccess(json);
//                        }
//                    });
//                }
//            }
//        });
//    }

    /**
     * 循环旋转动画
     */
    public static void setRotateAnim(RelativeLayout rotateAnim) {
        RotateAnimation rotateAnimation = new RotateAnimation(0, 359,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setRepeatCount(INFINITE);
        rotateAnimation.setDuration(8000);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnim.startAnimation(rotateAnimation);
    }

    /**
     * 随机生成8位包含数字大小写字母的验证码
     */
    public static String randSixCode(){
//        String[] beforeShuffle = new String[] {"0","1","2", "3", "4", "5", "6", "7",
//                "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
//                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
//                "W", "X", "Y", "Z","a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
//                "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
//                "w", "x", "y", "z"  };//不包含小写
        String[] beforeShuffle = new String[] {"0","1","2", "3", "4", "5", "6", "7",
                "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
                "W", "X", "Y", "Z"};//包含小写
        List list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        System.out.println(list.size());
        String afterShuffle = sb.toString();
        return afterShuffle.substring(1, 9);
    }

    /**
     *查看大图
     */
//    public static void ViewBigPicture(List<String> images,int position,Activity activity){
//        ImageInfo imageInfo;
//        final List<ImageInfo> imageInfoList = new ArrayList<>();
//
//        for (String image : images) {
//            imageInfo = new ImageInfo();
//            imageInfo.setOriginUrl(image);
//            imageInfoList.add(imageInfo);
//        }
//
//        ImagePreview.getInstance()
//                .setShowDownButton(false)
//                .setContext(activity)
//                .setIndex(position)//从指定位置开始查看
//                .setImageList(images)
//                .start();
//    }


    /**
     * 复制文本
     */
    public static void ClipText(String txt){
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cm.setText(txt);
    }

    /**
     * 获取TextView中内容
     */
    public static String getTVContent(TextView textView){
        return textView.getText().toString().trim();
    }

    /**
     * 打开系统浏览器访问链接
     */
    public static void openSysBrowser(Activity activity,String url){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri content_uri_browsers = Uri.parse(url);
        intent.setData(content_uri_browsers);
        activity.startActivity(intent);

//        Intent intent = new Intent(Intent.ACTION_VIEW,	Uri.parse(url));
//        intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
//        activity.startActivity(intent);
    }


    /**
     * 读取json文件的方法，也可写成工具类方便使用
     */
    public static String getJson(Context context, String fileName) {
        // 将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        // 获得assets资源管理器
        AssetManager assetManager = context.getAssets();
        // 使用IO流读取json文件内容
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName), StandardCharsets.UTF_8));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * 播放登录视频
     * @param videoView CustomVideoView
     * @param url 视频地址
     */
    public static void LoginVideo(final CustomVideoView videoView, String url){
        videoView.setVideoURI(Uri.parse(url));
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

            }
        });
        //循环播放
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoView.start();
            }
        });
        videoView.start();//开始播放
    }

    /**
     * 清除所有的SharedPreferences
     */
    public static void ClearAllInfo() {
        ClearKey("UserInfo");
    }

    /**
     * 3秒关闭
     */
    public static void Delay3s(final Activity activity) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                activity.finish();
            }
        }, 3000);
    }

    /**
     * 对话框
     */
    public static void DialogShow(Context context,String text){
        DialogSettings.style = STYLE_MATERIAL;  //对话框为IOS风格
        DialogSettings.tip_theme = THEME_LIGHT;  //设置提示框主题为亮色主题
        DialogSettings.use_blur = true;
        TipDialog.show(context, text, TipDialog.SHOW_TIME_LONG, TipDialog.TYPE_WARNING);
    }

    /**
     * 判断用户是否安装客户端
     */
    public static boolean isInstallApp(Context context,String packageName) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> list = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                String pn = list.get(i).packageName;
                if (pn.equals(packageName)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 通过包名打开APP
     */
    public static void launchApp(Context context, String packageName) {
        try {
            Intent intent = new Intent();
            //通过包名启动
            PackageManager packageManager = context.getPackageManager();
            intent = packageManager.getLaunchIntentForPackage(packageName);
            if (null != intent) {
                context.startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传文件
     */
    public static void uploadFile(Activity activity,String path,String url,OkSuccessInterface successInterface) {
        DYLoading loading = new DYLoading(activity);
        loading.show();
        // 获取要上传的文件
        File mFile = new File(path);
        OkHttpClient client = new OkHttpClient.Builder().build();
        // 设置文件以及文件上传类型封装
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), mFile);
        // 文件上传的请求体封装
        MultipartBody multipartBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", mFile.getName(), requestBody)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(multipartBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loading.dismiss();
                        TWarning2("图片上传失败");
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (successInterface != null){
                    String json = Objects.requireNonNull(response.body()).string();
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.d(TAG, TAG + json);
                            loading.dismiss();
                            successInterface.OnSuccess(json);
                        }
                    });
                }
            }
        });
    }
}
