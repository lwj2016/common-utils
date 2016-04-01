package com.lwj.utils;

/**
 * Created by lwj on 16/3/31.
 * Des:  大小比较
 *
 */
public class CompareUtil {


    public static final float FLOAT_EPSILON = 1e-2f;  //  0.01f

    public static boolean compareFloat(float float1, float float2) {
        float abs = Math.abs(float1 - float2);
        return abs < FLOAT_EPSILON;
    }

    public static boolean compareFloat(float float1, float float2, float float_epsilon) {
        float abs = Math.abs(float1 - float2);
        return abs >= float_epsilon;


    }

}
