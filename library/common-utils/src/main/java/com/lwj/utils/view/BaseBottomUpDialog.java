package com.lwj.utils.view;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.lwj.bottomdialog.R;


/**
 * Created by lwj on 16/5/5.
 * Des:   通用的底部弹出的 dialog
 */
public abstract class BaseBottomUpDialog extends Dialog {
    protected Activity mContext;
    protected View contentView;

    public BaseBottomUpDialog(Activity context) {
        this(context, R.style.bottom_out_dialog);
    }

    public BaseBottomUpDialog(Activity context, int theme) {
        super(context, theme);
        this.mContext = context;
        Window w = this.getWindow();
        w.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.x = 0;
        lp.y = -1000;
        lp.gravity = Gravity.BOTTOM;
        lp.width = WindowManager.LayoutParams.FILL_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        onWindowAttributesChanged(lp);
        setCanceledOnTouchOutside(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentView = generateContentView();
        setContentView(contentView);
    }

    public abstract View generateContentView();
}
