package com.lwj.utils.log;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lwj on 2018/4/10.
 * lwjfork@gmail.com
 *
 * Copy from https://github.com/JakeWharton/timber
 *
 */

public class DebugTree extends Tree {
    private static final int MAX_LOG_LENGTH = 4000;
    private static final int CALL_STACK_INDEX = 5;
    private static final Pattern ANONYMOUS_CLASS = Pattern.compile("\\$\\d+$");

    public DebugTree() {
    }

    protected String createStackElementTag(StackTraceElement element) {
        String tag = element.getClassName();
        String method = element.getMethodName();
        Matcher m = ANONYMOUS_CLASS.matcher(tag);
        if (m.find()) {
            tag = m.replaceAll("");
        }
        return tag.substring(tag.lastIndexOf(".") + 1);
    }

    final String getTag() {
        String tag = super.getTag();
        if (tag != null) {
            return tag;
        } else {
            StackTraceElement[] stackTrace = (new Throwable()).getStackTrace();
            if (stackTrace.length <= CALL_STACK_INDEX) {
                throw new IllegalStateException("Synthetic stacktrace didn\'t have enough elements: are you using proguard?");
            } else {
                return this.createStackElementTag(stackTrace[CALL_STACK_INDEX]);
            }
        }
    }

    protected void log(int priority, String tag, String message, Throwable t) {
        if (message.length() < MAX_LOG_LENGTH) {
            if (priority == Log.ASSERT) {
                Log.wtf(tag, message);
            } else {
                Log.println(priority, tag, message);
            }

        } else {
            int i = 0;

            int end;
            for (int length = message.length(); i < length; i = end + 1) {
                int newline = message.indexOf(10, i);
                newline = newline != -1 ? newline : length;

                do {
                    end = Math.min(newline, i + MAX_LOG_LENGTH);
                    String part = message.substring(i, end);
                    if (priority == Log.ASSERT) {
                        Log.wtf(tag, part);
                    } else {
                        Log.println(priority, tag, part);
                    }

                    i = end;
                } while (end < newline);
            }

        }
    }
}