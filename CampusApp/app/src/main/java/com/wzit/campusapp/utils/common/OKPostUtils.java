package com.wzit.campusapp.utils.common;


import static com.wzit.campusapp.utils.common.CommonUtils.TWarning;

import android.annotation.SuppressLint;
import android.app.Activity;


import com.wzit.campusapp.interfaces.OkSuccessInterface;
import com.wzit.campusapp.view.dialog.DYLoading;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * OKHttp请求
 */
public class OKPostUtils {

//    /**
//     * OKhttp封装(json参数传递方式)
//     */
//    public static void TXJsonPost(Activity activity, String json, String url, OkSuccessInterface successInterface){
//        DYLoading loading = new DYLoading(activity);
//        loading.show();
//        //创建OkHttpClient请求对象
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .connectTimeout(60, TimeUnit.SECONDS)//设置连接超时时间
//                .readTimeout(60, TimeUnit.SECONDS)//设置读取超时时间
//                .build();
//        //创建一个RequestBody(参数1：数据类型 参数2传递的json串)
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), json);
//        //创建一个请求对象
//        Request request = new Request.Builder()
//                .addHeader("Content-Type","application/x-www-form-urlencoded")
//                .url(url)
//                .post(requestBody)
//                .build();
//        okHttpClient.newCall(request).enqueue(new Callback() {
//            @SuppressLint("CheckResult")
//            @Override
//            public void onFailure(@NotNull Call call, @NotNull IOException e) {
//                activity.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        loading.dismiss();
//                        TWarning("服务器无响应");
//                    }
//                });
//            }
//
//            @Override
//            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                if (successInterface != null){
//                    String json = Objects.requireNonNull(response.body()).string();
//                    activity.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            loading.dismiss();
//                            successInterface.OnSuccess(json);
//                        }
//                    });
//                }
//            }
//        });
//    }

    /**
     * OKhttp封装
     * @param activity 主活动activity
     * @param param 传递的参数
     * @param url 接口地址
     * @param successInterface 服务器响应成功接口
     */
    public static void TXJsonPost(Activity activity, HashMap<Object, Object> param, String url, OkSuccessInterface successInterface) {
        DYLoading loading = new DYLoading(activity);
        loading.show();
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
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loading.dismiss();
                        TWarning("服务器无响应");
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (successInterface != null){
                    String json = Objects.requireNonNull(response.body()).string();
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            loading.dismiss();
                            successInterface.OnSuccess(json);
                        }
                    });
                }
            }
        });
    }

    /**
     * OKhttp封装
     * @param activity 主活动activity
     * @param param 传递的参数
     * @param url 接口地址
     * @param successInterface 服务器响应成功接口
     */
    public static void TXHJsonPost(Activity activity, HashMap<Object, Object> param, String url, OkSuccessInterface successInterface) {
        DYLoading loading = new DYLoading(activity);
        loading.show();
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
                .addHeader("Content-Type","application/x-www-form-urlencoded")
                .url(url)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @SuppressLint("CheckResult")
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loading.dismiss();
                        TWarning("服务器无响应");
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (successInterface != null){
                    String json = Objects.requireNonNull(response.body()).string();
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            loading.dismiss();
                            successInterface.OnSuccess(json);
                        }
                    });
                }
            }
        });
    }
}
