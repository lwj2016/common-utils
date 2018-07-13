package com.lwj.utils;

import android.app.Activity;
import android.app.Dialog;
import android.app.VoiceInteractor;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lwj.utils.context.GlobalContext;

/**
 * Created by lwj on 2016/3/8.
 * lwjfork@gmail.com
 */
public class ViewUtil {
    /**
     * Find view by id v.
     *
     * @param <V> the type parameter
     * @param ac  the ac
     * @param id  the id
     * @return the v
     * @author Created by liuwenjie on 2018/07/09 17:44
     */
    @SuppressWarnings("unchecked")
    public static <V extends View> V findViewById(Activity ac, @IdRes int id) {
        return (V) ac.findViewById(id);
    }

    /**
     * Find view by id v.
     *
     * @param <V>  the type parameter
     * @param view the view
     * @param id   the id
     * @return the v
     * @author Created by liuwenjie on 2018/07/09 17:44
     */
    @SuppressWarnings("unchecked")
    public static <V extends View> V findViewById(View view, @IdRes int id) {
        return (V) view.findViewById(id);
    }

    /**
     * Find view by id v.
     *
     * @param <V>  the type parameter
     * @param view the view
     * @param id   the id
     * @return the v
     * @author Created by liuwenjie on 2018/07/09 17:44
     */
    @SuppressWarnings("unchecked")
    public static <V extends View> V findViewById(Dialog view, @IdRes int id) {
        return (V) view.findViewById(id);
    }

    /**
     * Find view by id v.
     *
     * @param <V>    the type parameter
     * @param window the window
     * @param id     the id
     * @return the v
     * @author Created by liuwenjie on 2018/07/09 17:44
     */
    @SuppressWarnings("unchecked")
    public static <V extends View> V findViewById(Window window, @IdRes int id) {
        return (V) window.findViewById(id);
    }


    /**
     * Is visible boolean.
     *
     * @param _view the view
     * @return the boolean
     * @author Created by liuwenjie on 2018/07/09 17:44
     */
    public static boolean isVisible(View _view) {
        return _view.getVisibility() == View.VISIBLE;
    }

    /**
     * Is gone boolean.
     *
     * @param view the view
     * @return the boolean
     * @author Created by liuwenjie on 2018/07/09 17:44
     */
    public static boolean isGone(View view) {
        return view.getVisibility() == View.GONE;
    }

    /**
     * View gone.
     *
     * @param _view the view
     * @author Created by liuwenjie on 2018/07/09 17:44
     */
    public static void viewGone(View _view) {
        if (_view != null && _view.getVisibility() != View.GONE) {
            _view.setVisibility(View.GONE);
        }

    }

    /**
     * View visible.
     *
     * @param _view the view
     * @author Created by liuwenjie on 2018/07/09 17:44
     */
    public static void viewVisible(View _view) {
        if (_view != null && _view.getVisibility() != View.VISIBLE) {
            _view.setVisibility(View.VISIBLE);
        }

    }


    /**
     * New inflate layout inflater.
     *
     * @return the layout inflater
     * @author Created by liuwenjie on 2018/07/09 17:44
     */
    public static LayoutInflater newInflate() {
        return LayoutInflater.from(GlobalContext.getContext());
    }

    /**
     * Inflate view.
     *
     * @param layoutId the layout id
     * @return the view
     * @author Created by liuwenjie on 2018/07/09 17:44
     */
    public static View inflate(@LayoutRes int layoutId) {
        return inflate(layoutId, null, false);
    }

    /**
     * Inflate view.
     *
     * @param resource     the resource
     * @param root         the root
     * @param attachToRoot the attach to root
     * @return the view
     * @author Created by liuwenjie on 2018/07/09 17:44
     */
    public static View inflate(@LayoutRes int resource, @Nullable ViewGroup root, boolean attachToRoot) {
        return newInflate().inflate(resource, root, attachToRoot);
    }

    /**
     * Inflate view.
     *
     * @param resource the resource
     * @param root     the root
     * @return the view
     * @author Created by liuwenjie on 2018/07/09 17:44
     */
    public static View inflate(@LayoutRes int resource, @Nullable ViewGroup root) {
        return newInflate().inflate(resource, root, root != null);
    }

    /**
     * Find linear layout by id linear layout.
     *
     * @param view the view
     * @param id   the id
     * @return the linear layout
     * @author Created by liuwenjie on 2018/07/09 17:44
     */
    public static LinearLayout findLinearLayoutById(View view, @IdRes int id) {
        return findViewById(view, id);
    }

    /**
     * Find relative layout by id relative layout.
     *
     * @param view the view
     * @param id   the id
     * @return the relative layout
     * @author Created by liuwenjie on 2018/07/09 17:44
     */
    public static RelativeLayout findRelativeLayoutById(View view, @IdRes int id) {
        return findViewById(view, id);
    }


    /**
     * Find frame layout by id frame layout.
     *
     * @param view the view
     * @param id   the id
     * @return the frame layout
     * @author Created by liuwenjie on 2018/07/09 17:44
     */
    public static FrameLayout findFrameLayoutById(View view, @IdRes int id) {
        return findViewById(view, id);
    }

    /**
     * Find text view by id text view.
     *
     * @param view the view
     * @param id   the id
     * @return the text view
     * @author Created by liuwenjie on 2018/07/09 17:44
     */
    public static TextView findTextViewById(View view, @IdRes int id) {

        return findViewById(view, id);
    }

    /**
     * Find button by id button.
     *
     * @param view the view
     * @param id   the id
     * @return the button
     * @author Created by liuwenjie on 2018/07/09 17:44
     */
    public static Button findButtonById(View view, @IdRes int id) {

        return findViewById(view, id);
    }

    /**
     * Find image view by id image view.
     *
     * @param view the view
     * @param id   the id
     * @return the image view
     * @author Created by liuwenjie on 2018/07/09 17:44
     */
    public static ImageView findImageViewById(View view, @IdRes int id) {
        return findViewById(view, id);
    }

    /**
     * Find check box by id check box.
     *
     * @param view the view
     * @param id   the id
     * @return the check box
     * @author Created by liuwenjie on 2018/07/09 17:44
     */
    public static CheckBox findCheckBoxById(View view, @IdRes int id) {
        return findViewById(view, id);
    }

    /**
     * Find linear layout by id linear layout.
     *
     * @param activity the activity
     * @param id       the id
     * @return the linear layout
     * @author Created by liuwenjie on 2018/07/09 17:44
     */
    public static LinearLayout findLinearLayoutById(Activity activity, @IdRes int id) {
        return findViewById(activity, id);
    }

    /**
     * Find relative layout by id relative layout.
     *
     * @param activity the activity
     * @param id       the id
     * @return the relative layout
     * @author Created by liuwenjie on 2018/07/09 17:44
     */
    public static RelativeLayout findRelativeLayoutById(Activity activity, @IdRes int id) {
        return findViewById(activity, id);
    }


    /**
     * Find frame layout by id frame layout.
     *
     * @param activity the activity
     * @param id       the id
     * @return the frame layout
     * @author Created by liuwenjie on 2018/07/09 17:44
     */
    public static FrameLayout findFrameLayoutById(Activity activity, @IdRes int id) {
        return findViewById(activity, id);
    }

    /**
     * Find text view by id text view.
     *
     * @param activity the activity
     * @param id       the id
     * @return the text view
     * @author Created by liuwenjie on 2018/07/09 17:44
     */
    public static TextView findTextViewById(Activity activity, @IdRes int id) {

        return findViewById(activity, id);
    }

    /**
     * Sets tv text.
     *
     * @param activity the activity
     * @param id       the id
     * @param text     the text
     * @return the tv text
     * @author Created by liuwenjie on 2018/07/09 17:44
     */
    public static TextView setTvText(Activity activity, @IdRes int id, String text) {
        TextView textView = findTextViewById(activity, id);
        textView.setText(text);
        return textView;
    }


    /**
     * Sets tv text.
     *
     * @param view the view
     * @param id   the id
     * @param text the text
     * @return the tv text
     * @author Created by liuwenjie on 2018/07/09 17:44
     */
    public static TextView setTvText(View view, @IdRes int id, String text) {
        TextView textView = findTextViewById(view, id);
        textView.setText(text);
        return textView;
    }

    /**
     * Sets btn text.
     *
     * @param activity the activity
     * @param id       the id
     * @param text     the text
     * @return the btn text
     * @author Created by liuwenjie on 2018/07/09 17:44
     */
    public static Button setBtnText(Activity activity, @IdRes int id, String text) {
        Button button = findButtonById(activity, id);
        button.setText(text);
        return button;
    }

    /**
     * Find button by id button.
     *
     * @param activity the activity
     * @param id       the id
     * @return the button
     * @author Created by liuwenjie on 2018/07/09 17:44
     */
    public static Button findButtonById(Activity activity, @IdRes int id) {

        return findViewById(activity, id);
    }

    /**
     * Find image view by id image view.
     *
     * @param activity the activity
     * @param id       the id
     * @return the image view
     * @author Created by liuwenjie on 2018/07/09 17:44
     */
    public static ImageView findImageViewById(Activity activity, @IdRes int id) {
        return findViewById(activity, id);
    }

    /**
     * Find check box by id check box.
     *
     * @param activity the activity
     * @param id       the id
     * @return the check box
     * @author Created by liuwenjie on 2018/07/09 17:44
     */
    public static CheckBox findCheckBoxById(Activity activity, @IdRes int id) {
        return findViewById(activity, id);
    }


    /**
     * Sets compound drawables.
     *
     * @param <T>       the type parameter
     * @param view      the view
     * @param leftRes   the left res
     * @param topRes    the top res
     * @param rightRes  the right res
     * @param bottomRes the bottom res
     * @author Created by liuwenjie on 2018/07/09 17:44
     */
    public static <T extends TextView> void setCompoundDrawables(T view, @Nullable Drawable leftRes, @Nullable Drawable topRes, @Nullable Drawable rightRes, @Nullable Drawable bottomRes) {
        DrawableUtil.setCompoundDrawables(view, leftRes, topRes, rightRes, bottomRes);
    }


    /**
     * Sets compound drawables.
     *
     * @param <T>       the type parameter
     * @param view      the view
     * @param leftRes   the left res
     * @param topRes    the top res
     * @param rightRes  the right res
     * @param bottomRes the bottom res
     * @author Created by liuwenjie on 2018/07/09 17:44
     */
    public static <T extends TextView> void setCompoundDrawables(T view, int leftRes, int topRes, int rightRes, int bottomRes) {

        DrawableUtil.setCompoundDrawables(view, leftRes, topRes, rightRes, bottomRes);

    }


    /**
     * Sets left drawable.
     *
     * @param <T>  the type parameter
     * @param view the view
     * @param id   the id
     * @author Created by liuwenjie on 2018/07/09 17:44
     */
    public static <T extends TextView> void setLeftDrawable(T view, @DrawableRes int id) {
        DrawableUtil.setLeftDrawable(view, id);
    }

    /**
     * Sets top drawable.
     *
     * @param <T>  the type parameter
     * @param view the view
     * @param id   the id
     * @author Created by liuwenjie on 2018/07/09 17:44
     */
    public static <T extends TextView> void setTopDrawable(T view, @DrawableRes int id) {
        DrawableUtil.setTopDrawable(view, id);
    }

    /**
     * Sets right drawable.
     *
     * @param <T>  the type parameter
     * @param view the view
     * @param id   the id
     * @author Created by liuwenjie on 2018/07/09 17:44
     */
    public static <T extends TextView> void setRightDrawable(T view, @DrawableRes int id) {
        DrawableUtil.setRightDrawable(view, id);
    }

    /**
     * Sets bottom drawable.
     *
     * @param <T>  the type parameter
     * @param view the view
     * @param id   the id
     * @author Created by liuwenjie on 2018/07/09 17:44
     */
    public static <T extends TextView> void setBottomDrawable(T view, @DrawableRes int id) {
        DrawableUtil.setBottomDrawable(view, id);
    }

    /**
     * Sets left drawable.
     *
     * @param <T>      the type parameter
     * @param view     the view
     * @param drawable the drawable
     * @author Created by liuwenjie on 2018/07/09 17:44
     */
    public static <T extends TextView> void setLeftDrawable(T view, Drawable drawable) {
        DrawableUtil.setLeftDrawable(view, drawable);
    }

    /**
     * Sets top drawable.
     *
     * @param <T>      the type parameter
     * @param view     the view
     * @param drawable the drawable
     * @author Created by liuwenjie on 2018/07/09 17:44
     */
    public static <T extends TextView> void setTopDrawable(T view, Drawable drawable) {
        DrawableUtil.setTopDrawable(view, drawable);
    }

    /**
     * Sets right drawable.
     *
     * @param <T>      the type parameter
     * @param view     the view
     * @param drawable the drawable
     * @author Created by liuwenjie on 2018/07/09 17:44
     */
    public static <T extends TextView> void setRightDrawable(T view, Drawable drawable) {
        DrawableUtil.setRightDrawable(view, drawable);
    }

    /**
     * Sets bottom drawable.
     *
     * @param <T>      the type parameter
     * @param view     the view
     * @param drawable the drawable
     * @author Created by liuwenjie on 2018/07/09 17:44
     */
    public static <T extends TextView> void setBottomDrawable(T view, Drawable drawable) {
        DrawableUtil.setBottomDrawable(view, drawable);
    }

    /**
     * 清除图片
     *
     * @param <T>  the type parameter
     * @param view the view
     * @author Created by liuwenjie on 2018/07/09 17:44
     */
    public static <T extends TextView> void clearCompoundDrawables(T view) {
        DrawableUtil.clearCompoundDrawables(view);
    }

}
