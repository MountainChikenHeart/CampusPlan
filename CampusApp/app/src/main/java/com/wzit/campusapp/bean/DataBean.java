package com.wzit.campusapp.bean;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataBean {
    public Integer imageRes;
    public String imageUrl;
    public String title;
    public int viewType;

    public DataBean(Integer imageRes, String title, int viewType, String imgurl) {
        this.imageRes = imageRes;
        this.title = title;
        this.viewType = viewType;
        this.imageUrl = imgurl;
    }

    public DataBean(String imageUrl, String title, int viewType) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.viewType = viewType;
    }

    public static List<DataBean> getTestData() {
        List<DataBean> list = new ArrayList<>();
        list.add(new DataBean(1, "", 1,"https://img0.baidu.com/it/u=1422131545,1518033551&fm=253&fmt=auto&app=120&f=JPEG?w=726&h=500"));
        list.add(new DataBean(2, "", 1,"https://img2.baidu.com/it/u=3997239494,170225337&fm=253&fmt=auto&app=138&f=JPEG?w=890&h=500"));
        list.add(new DataBean(3, "", 1,"https://img2.baidu.com/it/u=3540475629,2941770384&fm=253&fmt=auto&app=138&f=JPEG?w=831&h=500"));
        list.add(new DataBean(4, "", 1,"https://img2.baidu.com/it/u=3115262098,247299278&fm=253&fmt=auto&app=120&f=JPEG?w=1125&h=705"));
        return list;
    }

    public static List<String> getColors(int size) {
        List<String> list = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            list.add(getRandColor());
        }
        return list;
    }

    /**
     * 获取十六进制的颜色代码.例如  "#5A6677"
     * 分别取R、G、B的随机值，然后加起来即可
     */
    public static String getRandColor() {
        String R, G, B;
        Random random = new Random();
        R = Integer.toHexString(random.nextInt(256)).toUpperCase();
        G = Integer.toHexString(random.nextInt(256)).toUpperCase();
        B = Integer.toHexString(random.nextInt(256)).toUpperCase();

        R = R.length() == 1 ? "0" + R : R;
        G = G.length() == 1 ? "0" + G : G;
        B = B.length() == 1 ? "0" + B : B;

        return "#" + R + G + B;
    }
}
