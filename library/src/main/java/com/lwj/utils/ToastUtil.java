package com.lwj.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lwj.utils.context.GlobalContext;

/**
 * Created by lwj on 2017/11/3.
 * lwjfork@gmail.com
 */

public class ToastUtil {


    public static class ToastBuilder {
        public Context context;
        int duration = Toast.LENGTH_LONG;
        String message;
        int msgId;
        int gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
        int xOffset = 0;
        int yOffset = 0;


        private View view;

        private TextView tv;

        public ToastBuilder(Context context) {
            this.context = context;
            yOffset = DisplayUtil.dp2px(context, 64);
        }

        public ToastBuilder setView(int layoutID, int tvID) {
            view = View.inflate(context, layoutID, null);
            tv = (TextView) view.findViewById(tvID);
            return this;
        }

        public ToastBuilder setView(int layoutID) {
            view = View.inflate(context, layoutID, null);
            return this;
        }

        public ToastBuilder setMsgViewID(int tvID) {
            tv = (TextView) view.findViewById(tvID);
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
                return context.getResources().getString(msgId);
            }
            if (message != null && message.length() > 0) {
                return message;
            }
            return "";
        }

        public void show() {

            if (view == null) {
                Toast.makeText(context, getMsg(), duration).show();
            } else {
                Toast toast = new Toast(context);
                toast.setGravity(gravity, xOffset, yOffset);
                toast.setDuration(duration);
                toast.setView(view);
                tv.setText(message);
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
        show(context, msg, Toast.LENGTH_SHORT);
    }

    public static void show(Context context, String msg, int duration) {
        Toast.makeText(context, msg, duration).show();
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
