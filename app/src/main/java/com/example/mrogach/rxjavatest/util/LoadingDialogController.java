package com.example.mrogach.rxjavatest.util;

import android.app.Activity;
import android.support.v4.app.DialogFragment;

import com.example.mrogach.rxjavatest.activity.MainActivity;
import com.example.mrogach.rxjavatest.fragment.LoadingDialog;

/**
 * Created by
 * mRogach on 11.09.2015.
 */
public class LoadingDialogController {
    private MainActivity mActivity;


    private boolean isShowing;

    public LoadingDialogController() {
    }

    public void register(final MainActivity _activity) {
        mActivity = _activity;
    }

    public final void showLoadingDialog(final String _tag) {

        if (!isShowing) {
            final LoadingDialog prev = (LoadingDialog) mActivity.getSupportFragmentManager().findFragmentByTag(_tag);
            if (prev != null) return;
            final LoadingDialog loadingDialog = new LoadingDialog();
            loadingDialog.setCancelable(false);
            //        loadingDialog.setStyle(DialogFragment.STYLE_NORMAL, R.style.LoadingDialog);
            try {
                loadingDialog.show(mActivity.getSupportFragmentManager(), _tag);
            } catch (final IllegalStateException _e) {
                _e.printStackTrace();
            }
            isShowing = true;
        }
    }

    public final void hideLoadingDialog(final String _tag) {
        final android.support.v4.app.Fragment fragment = mActivity.getSupportFragmentManager().findFragmentByTag(_tag);
        if (fragment != null) ((DialogFragment) fragment).dismissAllowingStateLoss();
        isShowing = false;
    }
}
