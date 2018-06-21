package com.lwj.utils;

/**
 * Created by lwj on 2017/11/2.
 * lwjfork@gmail.com
 * <p>
 * 1-- 一
 * 二--二
 */

public class GetNum2Chinese {

    private static final String ZEROstr = "零";
    private static final long ZEROnum = 0;
    private static final String TENstr = "十";
    private static final long TENnum = 10;
    private static final String Hstr = "百";
    private static final long Hnum = TENnum * 10L;
    private static final String Tstr = "千";
    private static final long Tnum = Hnum * 10L;

    private static final String TTstr = "万";
    private static final long TTnum = Tnum * 10;
    private static final String HMstr = "亿";
    private static final long HMnum = TTnum * 10000L;

    private static final String TTHMstr = "万亿";
    private static final long TTHMNUM = HMnum * 10000L;


    private static final String ONE = "一";
    private static final String TWO = "二";
    private static final String THREE = "三";
    private static final String FOUR = "四";
    private static final String FIVE = "五";
    private static final String SIX = "六";
    private static final String SEVEN = "七";
    private static final String EIGHT = "八";
    private static final String NINE = "九";

    public static String getCnString(long intInput) {

        if (intInput >= TTHMNUM) { //  大于千亿不考虑
            String str = String.valueOf(intInput);
            str = str.substring(str.length() - 12);
            intInput = Long.valueOf(str);
        }
        StringBuilder stringBuilder = new StringBuilder();

        if (intInput >= HMnum) {  // 过亿了
            long ttNum = intInput / HMnum;
            intInput = intInput % HMnum;
            stringBuilder.append(getNumString(ttNum)).append(HMstr);
        }

        if (intInput >= TTnum) {  // 过万
            long ttNum = intInput / TTnum;
            intInput = intInput % TTnum;
            stringBuilder.append(getNumString(ttNum)).append(TTstr);
        }

        if (intInput > 0) {
            if (stringBuilder.length() > 0 && (intInput < Tnum || intInput < Hnum || intInput < TENnum)) {
                stringBuilder.append(ZEROstr);
            }
            stringBuilder.append(getNumString(intInput));
        }
        return stringBuilder.toString();
    }


    private static String getNumString(long intInput) {
        if (intInput <= 0) {
            return ZEROstr;
        }
        long x000 = intInput / 1000; // 千
        intInput = intInput - x000 * 1000;
        long x00 = intInput / 100;   // 百
        intInput = intInput - x00 * 100;
        long x0 = intInput / 10;     // 十
        intInput = intInput - x0 * 10;
        long x = intInput;          // 个
        StringBuilder stringBuilder = new StringBuilder();
        if (x000 > 0) {
            stringBuilder.append(GetCH(x000)).append(Tstr);
        }
        if (x00 > 0) {
            stringBuilder.append(GetCH(x00)).append(Hstr);
        } else {
            if (x000 > 0 && (x0 > 0 || x > 0)) {
                stringBuilder.append(ZEROstr);
            }
        }
        if (x0 > 0) {
            if (x000 <= 0 && x00 <= 0 && x0 == 1) {
                stringBuilder.append(TENstr);
            } else {
                stringBuilder.append(GetCH(x0)).append(TENstr);
            }
        } else {
            if (x00 > 0 && x > 0) {
                stringBuilder.append(ZEROstr);
            }
        }
        if (x > 0) {
            stringBuilder.append(GetCH(x));
        }

        return stringBuilder.toString();
    }


    private static String GetCH(long input) {
        String sd = "";
        int x = (int) input;
        switch (x) {
            case 1:
                sd = ONE;
                break;
            case 2:
                sd = TWO;
                break;
            case 3:
                sd = THREE;
                break;
            case 4:
                sd = FOUR;
                break;
            case 5:
                sd = FIVE;
                break;
            case 6:
                sd = SIX;
                break;
            case 7:
                sd = SEVEN;
                break;
            case 8:
                sd = EIGHT;
                break;
            case 9:
                sd = NINE;
                break;
            default:
                break;
        }
        return sd;
    }


}
