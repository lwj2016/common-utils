package com.common.lib.base.life;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by lwj on 2017/1/16.
 * lwjfork@gmail.com
 */

public abstract class LifeFragment extends Fragment {
    public static ArrayList<IFragmentLife> mSubscribers = new ArrayList<>();

    public static void addFragmentLife(IFragmentLife life) {
        mSubscribers.add(life);
    }

    public static void removeFragmentLife(IFragmentLife life) {
        mSubscribers.remove(life);
    }

    public static void clearLife() {
        mSubscribers.clear();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onAttach(this);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreate(this);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        onCreateView(this);

        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onActivityCreated(this);
    }


    @Override
    public void onStart() {
        super.onStart();
        onStart(this);
    }


    @Override
    public void onResume() {
        super.onResume();
        onResume(this);
    }


    @Override
    public void onPause() {
        super.onPause();
        onPause(this);
    }


    @Override
    public void onStop() {
        super.onStop();
        onStop(this);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        onDestroyView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        onDestroy(this);
    }


    @Override
    public void onDetach() {
        super.onDetach();
        onDetach(this);
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        setUserVisibleHint(isVisibleToUser, this);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        onHiddenChanged(hidden, this);
    }


    private void onAttach(Fragment fragment) {
        for (IFragmentLife iFragmentLife : mSubscribers) {
            iFragmentLife.onAttach(fragment);
        }
    }

    private void onCreate(Fragment fragment) {
        for (IFragmentLife iFragmentLife : mSubscribers) {
            iFragmentLife.onCreate(fragment);
        }
    }

    private void onCreateView(Fragment fragment) {
        for (IFragmentLife iFragmentLife : mSubscribers) {
            iFragmentLife.onCreateView(fragment);
        }
    }

    private void onActivityCreated(Fragment fragment) {
        for (IFragmentLife iFragmentLife : mSubscribers) {
            iFragmentLife.onActivityCreated(fragment);
        }
    }

    private void onStart(Fragment fragment) {
        for (IFragmentLife iFragmentLife : mSubscribers) {
            iFragmentLife.onStart(fragment);
        }
    }

    private void onResume(Fragment fragment) {
        for (IFragmentLife iFragmentLife : mSubscribers) {
            iFragmentLife.onResume(fragment);
        }
    }

    private void onPause(Fragment fragment) {
        for (IFragmentLife iFragmentLife : mSubscribers) {
            iFragmentLife.onPause(fragment);
        }
    }

    private void onStop(Fragment fragment) {
        for (IFragmentLife iFragmentLife : mSubscribers) {
            iFragmentLife.onStop(fragment);
        }
    }

    private void onDestroyView(Fragment fragment) {
        for (IFragmentLife iFragmentLife : mSubscribers) {
            iFragmentLife.onDestroyView(fragment);
        }
    }

    private void onDestroy(Fragment fragment) {
        for (IFragmentLife iFragmentLife : mSubscribers) {
            iFragmentLife.onDestroy(fragment);
        }
    }

    private void onDetach(Fragment fragment) {
        for (IFragmentLife iFragmentLife : mSubscribers) {
            iFragmentLife.onDetach(fragment);
        }
    }

    private void setUserVisibleHint(boolean isVisibleToUser, Fragment fragment) {
        for (IFragmentLife iFragmentLife : mSubscribers) {
            iFragmentLife.setUserVisibleHint(isVisibleToUser, fragment);
        }
    }

    private void onHiddenChanged(boolean hidden, Fragment fragment) {
        for (IFragmentLife iFragmentLife : mSubscribers) {
            iFragmentLife.onHiddenChanged(hidden, fragment);
        }
    }

}