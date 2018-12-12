package com.common.lib.view;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by lwj on 16/9/18.
 * liuwenjie@goumin.com
 */
public class MaxLengthTextWatcher implements TextWatcher {

    private int maxLen = 200;
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

    public MaxLengthTextWatcher(EditText editText) {
        this(200, editText);
    }


    public void afterTextChanged(Editable arg0) {
        selectionStart = editText.getSelectionStart();
        selectionEnd = editText.getSelectionEnd();
        if (temp.length() > maxLen) {
            if (onTextCountListener != null) {
                onTextCountListener.onTextCountOut();
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

    private OnTextCountListener onTextCountListener;

    public OnTextCountListener getOnTextCountListener() {
        return onTextCountListener;
    }

    public void setOnTextCountListener(OnTextCountListener onTextCountListener) {
        this.onTextCountListener = onTextCountListener;
    }

    public static class Builder {
        private int maxLen = 200;
        private EditText editText = null;
        public int selectionStart = 0;
        public int selectionEnd = 0;
        private CharSequence temp = null;
        private OnTextCountListener onTextCountListener;

        public Builder(EditText editText) {

            if (editText == null) {
                throw new IllegalArgumentException("this EditText must not be null");
            }
            this.editText = editText;
        }

        public Builder setMaxLength(int maxCount) {
            int maxLen = maxCount;
            return this;
        }


        public Builder setOnTextCountListener(OnTextCountListener onTextCountListener) {
            this.onTextCountListener = onTextCountListener;
            return this;
        }

        public MaxLengthTextWatcher build() {

            MaxLengthTextWatcher watcher = new MaxLengthTextWatcher(maxLen, editText);
            if (editText != null) {
                editText.addTextChangedListener(watcher);
            }
            if (onTextCountListener != null) {
                watcher.setOnTextCountListener(onTextCountListener);
            }
            return watcher;
        }
    }
}
