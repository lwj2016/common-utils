package com.lwj.utils;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.CharacterStyle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lwj on 2017/12/14.
 * lwjfork@gmail.com
 */

public final class SpanBuilder {


    private StringBuilder allStr = new StringBuilder();

    private ArrayList<SpanModel> spanModels = new ArrayList<>();

    public static SpanBuilder newInstance() {
        return new SpanBuilder();
    }

    public static SpanBuilder newInstance(String allStr) {
        return new SpanBuilder(allStr);
    }


    private SpanBuilder() {

    }

    private SpanBuilder(String allStr) {
        this.allStr = new StringBuilder(allStr);

    }


    private class SpanModel {
        public int start; // 开始位置
        public int end;// 结束位置
        int flags = Integer.MIN_VALUE;
        public Object span;
    }


    public SpanBuilder setSpan(String string, Object... spans) {
        return setSpan(string, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE, spans);
    }

    public SpanBuilder setSpan(String string, int flags, Object... spans) {
        int start = allStr.indexOf(string);
        if (start < 0) {
            return this;
        }
        int end = start + string.length();
        addSpanModel(start, end, flags, spans);
        return this;
    }

    // 正则匹配
    public SpanBuilder setRegexSpan(String regex, Object... spans) {
        return setRegexSpan(regex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE, spans);
    }

    // 正则匹配
    public SpanBuilder setRegexSpan(String regex, int flags, Object... spans) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(allStr);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            addSpanModel(start, end, flags, spans);
        }
        return this;
    }


    public SpanBuilder setSpan(String[] strings, Object... spans) {

        return setSpan(strings, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE, spans);
    }


    public SpanBuilder setSpan(String[] strings, int flags, Object... spans) {
        for (String s : strings) {
            setSpan(s, flags, spans);
        }
        return this;
    }


    public SpanBuilder append(String string, Object... spans) {
        return append(string, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE, spans);
    }


    public SpanBuilder append(String string, int flags, Object... spans) {
        int start = allStr.length();
        allStr.append(string);
        int end = allStr.length();
        addSpanModel(start, end, flags, spans);
        return this;
    }

    public SpanBuilder append(String[] strings, Object... spans) {
        return append(strings, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE, spans);
    }


    public SpanBuilder append(String[] strings, int flags, Object... spans) {
        for (String string : strings) {
            append(string, flags, spans);
        }
        return this;
    }

    public SpanBuilder append(String string) {
        allStr.append(string);
        return this;
    }


    public SpanBuilder append(String[] string) {
        for (String s : string) {
            append(s);
        }
        return this;
    }


    private void addSpanModel(int start, int end, int flags, Object... spans) {
        for (Object span : spans) {
            SpanModel spanModel = new SpanModel();
            spanModel.start = start;
            spanModel.end = end;
            if (span instanceof CharacterStyle) { // fix 每个span 只能用一次的bug
                spanModel.span = CharacterStyle.wrap((CharacterStyle) span);
            } else {
                spanModel.span = span;
            }

            spanModel.flags = flags;
            spanModels.add(spanModel);
        }
    }


    public SpannableString buildSpan() {
        SpannableString spannableString = new SpannableString(allStr.toString());
        for (SpanModel spanModel : spanModels) {
            spannableString.setSpan(spanModel.span, spanModel.start, spanModel.end, spanModel.flags);
        }
        return spannableString;
    }

    public <T extends TextView> void apply(T view) {
        view.setText(buildSpan());
    }


}
