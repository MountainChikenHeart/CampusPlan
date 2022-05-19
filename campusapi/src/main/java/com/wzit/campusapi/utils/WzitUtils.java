package com.wzit.campusapi.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * 通用封装
 */
public class WzitUtils {

    //包含数字的数组
    private static final String[] number_arr = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    //包含大写字母的数组
    private static final String[] upper_arr = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    //包含小写字母的数组
    private static final String[] lower_arr = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    //包含数字+大写字母的数组
    private static final String[] num_low_arr = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    //包含数字+小写字母的数组
    private static final String[] num_upp_arr = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    //包含大写+小写字母的数组
    private static final String[] upp_low_arr = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    //包含数字+大写字母+小写字母的数组
    private static final String[] num_upp_low_arr = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};


    /**
     * 随机生成包含数字/大/小写字母的指定位数
     *
     * @param type   生成类型 1数字   2大写字母   3小写字母   4数字+大写   5数字+小写  6大写+小写  7数字+大写+小写
     * @param length 数据长度
     */
    public static String getRandomUUID(int type, int length) {
        List<String> list = null;
        switch (type) {
            case 1:// 10位
                list = Arrays.asList(number_arr);
                break;
            case 2:// 26位
                list = Arrays.asList(upper_arr);
                break;
            case 3:// 26位
                list = Arrays.asList(lower_arr);
                break;
            case 4:// 36位
                list = Arrays.asList(num_upp_arr);
                break;
            case 5:// 36位
                list = Arrays.asList(num_low_arr);
                break;
            case 6:// 52位
                list = Arrays.asList(upp_low_arr);
                break;
            case 7:// 62位
                list = Arrays.asList(num_upp_low_arr);
                break;
        }
        assert list != null;
        //随机排序shuffle
        Collections.shuffle(list);
        StringBuilder uuid = new StringBuilder();
        for (Object o : list) {
            uuid.append(o);
        }
        return uuid.substring(0, length);
    }


    /**
     * 随机生成包含数字/大/小写字母的指定位数 测试
     */
    public static void main(String[] args) {
        System.out.println(getRandomUUID(1, 10));
        System.out.println(getRandomUUID(2, 10));
        System.out.println(getRandomUUID(3, 10));
        System.out.println(getRandomUUID(4, 10));
        System.out.println(getRandomUUID(5, 10));
        System.out.println(getRandomUUID(6, 10));
        System.out.println(getRandomUUID(7, 10));
    }

    /**
     * 随机生成图片/视频ID
     *
     * @return UUID 生成不重复的ID
     */
    public static String generateGUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 自定义
     * 文件上传路径和资源访问URL
     */
    public static final String PATH = "/profile/upload/" ;

    /**
     * 获取图片位置（随机生成67以内的图片position）
     */
    public static int getHeadImgPosition() {
        int num = (int) (Math.random() * 67);
        if (num == 0) {
            num++;
        }
        return num;
    }
}
