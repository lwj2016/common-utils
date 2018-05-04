package com.utils.test.model;

import java.io.Serializable;

/**
 * Created by lwj on 2018/5/3.
 * lwjfork@gmail.com
 */

public class TestModel implements Serializable {


    public double d = 5d;


    @Override
    public String toString() {
        return "TestModel{" +
                "d=" + d +
                '}';
    }
}
