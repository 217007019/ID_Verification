package com.example.id_verification;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.TextView;

public class ApplicationClass extends Application
{
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public static void showProgress(final boolean show, final View progressBar,
                                    final View mainLayout, final TextView textView,
                                    Context context)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2)
        {
            int shortAnimTime = context.getResources().
                    getInteger(android.R.integer.config_shortAnimTime);

            mainLayout.setVisibility(show ? View.GONE : View.VISIBLE);
            mainLayout.animate().setDuration(shortAnimTime).alpha(
                    show ? 0:1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation)
                {
                    mainLayout.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
            progressBar.animate().setDuration(shortAnimTime).alpha(
                    show ? 1:0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });

            textView.setVisibility(show ? View.VISIBLE : View.GONE);
            textView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1:0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    textView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });

        }else {
            progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
            textView.setVisibility( show ? View.VISIBLE : View.GONE);
            mainLayout.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


}
