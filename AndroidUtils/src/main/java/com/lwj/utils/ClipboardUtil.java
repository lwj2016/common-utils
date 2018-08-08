package com.lwj.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by lwj on 2018/7/5.
 * lwjfork@gmail.com
 * 剪切板管理类
 */

public class ClipboardUtil {




    /**
     * 剪切板管理类 ClipboardManager
     *
     * @return
     */
    public static ClipboardManager getManager() {
        return OSUtils.getSystemService(Context.CLIPBOARD_SERVICE);
    }


    public static void copyText(@NonNull CharSequence text) {
        copyText(null, text);
    }


    public static void copyText(@Nullable CharSequence label, @NonNull CharSequence text) {
        getManager().setPrimaryClip(ClipData.newPlainText(label, text));
    }

    /**
     * @param text     The text of clip as plain text, for receivers that don't
     *                 handle HTML.  This is required.
     * @param htmlText The actual HTML text in the clip.
     */
    public static void copyHtmlText(CharSequence text, @NonNull String htmlText) {
        copyHtmlText(null, text, htmlText);
    }


    /**
     * @param label    User-visible label for the clip data.
     * @param text     The text of clip as plain text, for receivers that don't
     *                 handle HTML.  This is required.
     * @param htmlText The actual HTML text in the clip.
     */
    public static void copyHtmlText(@Nullable CharSequence label, @NonNull CharSequence text, @NonNull String htmlText) {
        if (OSUtils.hasJellyBean_16()) {
            getManager().setPrimaryClip(ClipData.newHtmlText(label, text, htmlText));
        } else {
            copyText(label, htmlText);
        }
    }

    /**
     * @param intent The actual Intent in the clip.
     */
    public static void copyIntent(@NonNull Intent intent) {
        copyIntent(null, intent);
    }

    /**
     * @param label  User-visible label for the clip data.
     * @param intent The actual Intent in the clip.
     */
    public static void copyIntent(@Nullable CharSequence label, @NonNull Intent intent) {
        getManager().setPrimaryClip(ClipData.newIntent(label, intent));
    }


    public static boolean hasPrimaryClip() {
        return getManager().hasPrimaryClip();
    }


    public static ClipData getClipData() {
        ClipboardManager manager = getManager();
        if (manager.hasPrimaryClip()) {
            return getManager().getPrimaryClip();
        }
        return null;
    }


    



}
