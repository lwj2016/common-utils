package com.utils.test;

import java.io.Serializable;

/**
 * Created:2018/5/25
 * User：lwjfork
 * Email:lwjfork@gmail.com
 * Des:
 * ====================
 */

public class TestData implements Serializable {

    private String key = "111";

    public TestData(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "TestData{" +
                "key='" + key + '\'' +
                '}';
    }
}
