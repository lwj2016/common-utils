package com.lwj.utils.view;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.lwj.utils.StringUtil;
import com.lwj.utils.ToastUtil;

/**
 * Created by lwj on 16/9/18.
 * liuwenjie@goumin.com
 */
public class MaxLengthTextWatcher implements TextWatcher {

    private int maxLen = 200;
    private String limitHint;
    private EditText editText = null;
    public int selectionStart = 0;
    public int selectionEnd = 0;
    private CharSequence temp = null;

    public MaxLengthTextWatcher() {
    }

    public MaxLengthTextWatcher(int maxLen, EditText editText) {
        this.maxLen = maxLen;
        this.editText = editText;
    }

    public MaxLengthTextWatcher(int maxLen, EditText editText, String limitHint) {
        this.maxLen = maxLen;
        this.editText = editText;
        this.limitHint = limitHint;
    }

    public MaxLengthTextWatcher(EditText editText) {
        this(200, editText);
    }


    public void afterTextChanged(Editable arg0) {
        selectionStart = editText.getSelectionStart();
        selectionEnd = editText.getSelectionEnd();
        if (temp.length() > maxLen) {
            if (StringUtil.isValid(limitHint)) {
                ToastUtil.showToast(limitHint);
            }
            arg0.delete(selectionStart - 1, selectionEnd);
            int tempSelection = selectionStart;
            editText.setText(arg0);
            editText.setSelection(tempSelection);
        }
        if (onTextCountListener != null) {
            onTextCountListener.onTextCount(editText.getText().toString().length());
        }
    }

    public void beforeTextChanged(CharSequence arg0, int arg1,
                                  int arg2, int arg3) {
        temp = arg0;
    }

    public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                              int arg3) {
    }

    public interface OnTextCountListener {
        void onTextCount(int count);
    }

    OnTextCountListener onTextCountListener;

    public OnTextCountListener getOnTextCountListener() {
        return onTextCountListener;
    }

    public void setOnTextCountListener(OnTextCountListener onTextCountListener) {
        this.onTextCountListener = onTextCountListener;
    }

}
