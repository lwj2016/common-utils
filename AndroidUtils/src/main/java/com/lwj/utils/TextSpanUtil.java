package com.lwj.utils;

import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;

import java.util.ArrayList;

/**
 * Created by lwj on 2017/12/14.
 * lwjfork@gmail.com
 */

public class TextSpanUtil {


    public static TextSpanUtil getInstance() {
        return new TextSpanUtil();
    }

    private TextSpanUtil() {
    }


    public class SpanModel {
        public int start; // 开始位置
        public int end;// 结束位置
        int flags = Integer.MIN_VALUE;
        public Object span;
    }

    private ArrayList<SpanModel> spanModels = new ArrayList<>();

    public SpannableStringBuilder stringBuilder = new SpannableStringBuilder();

    public TextSpanUtil append(String string, Object... spans) {
        int start = stringBuilder.length();
        stringBuilder.append(string);
        int end = stringBuilder.length();
        for (Object span : spans) {
            SpanModel spanModel = new SpanModel();
            spanModel.start = start;
            spanModel.end = end;
            spanModel.span = span;
            spanModel.flags = Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;
            spanModels.add(spanModel);
        }
        return this;
    }

    public TextSpanUtil append(String string, int flags, Object... spans) {
        int start = stringBuilder.length();
        stringBuilder.append(string);
        int end = stringBuilder.length();
        for (Object span : spans) {
            SpanModel spanModel = new SpanModel();
            spanModel.start = start;
            spanModel.end = end;
            spanModel.span = span;
            spanModel.flags = flags;
            spanModels.add(spanModel);
        }
        return this;
    }

    public TextSpanUtil append(String string) {
        stringBuilder.append(string);
        return this;
    }

    public TextSpanUtil append(SpannableStringBuilder stringBuilder) {
        stringBuilder.append(stringBuilder);
        return this;
    }

    public SpannableString buildSpan() {
        SpannableString spannableString = new SpannableString(stringBuilder.toString());
        for (SpanModel spanModel : spanModels) {
            spannableString.setSpan(spanModel.span, spanModel.start, spanModel.end, spanModel.flags);
        }
        return spannableString;
    }
}
