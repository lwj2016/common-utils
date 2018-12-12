package com.common.lib.view;

/**
 * Created by lwj on 2017/1/16.
 * lwjfork@gmail.com
 * EditText  输入监听
 */
public interface OnTextCountListener {

    /**
     * 字体个数
     *
     * @param count
     */
    void onTextCount(int count);

    /**
     * 字体个数越界
     */
    void onTextCountOut();
}
