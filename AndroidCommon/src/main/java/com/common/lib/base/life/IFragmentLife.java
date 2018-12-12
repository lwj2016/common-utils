package com.common.lib.base.life;

import android.support.v4.app.Fragment;

/**
 * Created by lwj on 2017/1/16.
 * lwjfork@gmail.com
 */

public interface IFragmentLife {

    void onAttach(Fragment fragment);

    void onCreate(Fragment fragment);

    void onCreateView(Fragment fragment);

    void onActivityCreated(Fragment fragment);

    void onStart(Fragment fragment);

    void onResume(Fragment fragment);

    void onPause(Fragment fragment);

    void onStop(Fragment fragment);

    void onDestroyView(Fragment fragment);

    void onDestroy(Fragment fragment);

    void onDetach(Fragment fragment);

    void setUserVisibleHint(boolean isVisibleToUser, Fragment fragment);

    void onHiddenChanged(boolean hidden, Fragment fragment);

}
