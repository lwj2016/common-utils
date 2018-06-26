package com.lwj.utils;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lwj.utils.context.GlobalContext;

/**
 * Created by lwj on 2017/11/3.
 * lwjfork@gmail.com
 */

public class ToastUtil {


    public static  int DEFAULT_GRAVITY = -1;
    public static  int X_OFFSET_DP = -1;
    public static  int Y_OFFSET_DP = -1;


    public static class ToastBuilder {
        int duration = Toast.LENGTH_LONG;
        String message;
        int msgId;
        int gravity = DEFAULT_GRAVITY;
        int xOffset = X_OFFSET_DP;
        int yOffset = Y_OFFSET_DP;


        private View view;

        private TextView tv;

        public ToastBuilder(Context context) {
            yOffset = DisplayUtil.dp2px(Y_OFFSET_DP);
            xOffset = DisplayUtil.dp2px(X_OFFSET_DP);
        }

        public ToastBuilder setView(int layoutID, int tvID) {
            view = ViewUtil.inflate(layoutID);
            return setMsgViewID(tvID);
        }

        public ToastBuilder setView(View view, int tvID) {
            this.view = view;
            tv = (TextView) view.findViewById(tvID);
            return this;
        }


        public ToastBuilder setView(int layoutID) {
            view = ViewUtil.inflate(layoutID);
            return this;
        }

        public ToastBuilder setView(View view) {
            this.view = view;
            return this;
        }

        public ToastBuilder setMsgViewID(int tvID) {
            tv = ViewUtil.findViewById(view, tvID);
            return this;
        }


        public ToastBuilder setDuration(int duration) {
            this.duration = duration;
            return this;
        }

        public ToastBuilder setMsg(String message) {
            this.message = message;
            return this;
        }

        public ToastBuilder setMsgStrId(int resStrId) {
            this.msgId = resStrId;
            return this;
        }

        /**
         * 设置显示位置
         */
        public ToastBuilder setGravity(int gravity) {
            setGravity(gravity, 0, 0);
            return this;
        }

        /**
         * 设置显示位置
         */
        public ToastBuilder setGravity(int gravity, int xOffset, int yOffset) {
            this.gravity = gravity;
            this.xOffset = xOffset;
            this.yOffset = yOffset;
            return this;
        }

        public ToastBuilder setOffset(int xOffset, int yOffset) {
            this.xOffset = xOffset;
            this.yOffset = yOffset;
            return this;
        }

        public ToastBuilder setXOffset(int xOffset) {
            this.xOffset = xOffset;
            return this;
        }

        public ToastBuilder setYOffset(int yOffset) {
            this.yOffset = yOffset;
            return this;
        }

        private String getMsg() {
            if (msgId > 0) {
                return ResUtil.getString(msgId);
            }
            if (message != null && message.length() > 0) {
                return message;
            }
            return "";
        }

        public void show() {
            if (view == null) {
                Toast toast = Toast.makeText(GlobalContext.getContext(), getMsg(), duration);
                toast.setGravity(gravity, xOffset, yOffset);
                toast.show();
            } else {
                Toast toast = new Toast(GlobalContext.getContext());
                toast.setGravity(gravity, xOffset, yOffset);
                toast.setDuration(duration);
                toast.setView(view);
                if (tv != null) {
                    tv.setText(message);
                }
                toast.show();
            }

        }


    }


    public static ToastBuilder get(Context context) {
        return new ToastBuilder(context);
    }

    public static ToastBuilder get() {
        return get(GlobalContext.getContext());
    }

    public static void show(Context context, String msg) {
        show(context, msg, Toast.LENGTH_SHORT, DEFAULT_GRAVITY, DisplayUtil.dp2px(X_OFFSET_DP), DisplayUtil.dp2px(Y_OFFSET_DP));
    }

    public static void show(Context context, String msg, int duration, int gravity, int xOffset, int yOffset) {
        Toast toast = Toast.makeText(context, msg, duration);
        toast.setGravity(gravity, xOffset, yOffset);
        toast.show();
    }

    public static void show(String msg, int duration, int gravity, int xOffset, int yOffset) {
        show(GlobalContext.getContext(), msg, duration, gravity, xOffset, yOffset);
    }


    public static void show(Context context, int msg) {
        show(context, msg, Toast.LENGTH_SHORT);
    }

    public static void show(Context context, int msg, int duration) {
        Toast.makeText(context, msg, duration).show();
    }

    public static void show(String msg) {
        show(msg, Toast.LENGTH_SHORT);
    }

    public static void show(String msg, int duration) {
        Toast.makeText(GlobalContext.getContext(), msg, duration).show();
    }

    public static void show(int msg) {
        show(msg, Toast.LENGTH_SHORT);
    }

    public static void show(int msg, int duration) {
        Toast.makeText(GlobalContext.getContext(), msg, duration).show();
    }


}
