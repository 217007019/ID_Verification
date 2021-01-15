package com.example.id_verification;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.example.id_verification.Classes.Civilian;
import com.example.id_verification.Classes.HomeAffairs;
import com.example.id_verification.Classes.User;

import java.util.List;

public class ApplicationClass extends Application
{
    public static  final String APPLICATION_ID = "21B311AE-AD25-BC25-FF8A-E04E130A5A00";
    public static final String API_KEY = "929DAD06-85C4-43C9-B75A-9DED0038F7FD";
    public static  final String SERVER_URL ="https://api.backendless.com";

    public static BackendlessUser user;
    public static List<User> userList;
    public  static List<HomeAffairs> homeAffairs;
    public static List<Civilian> civilians;

    @Override
    public void onCreate()
    {
        super.onCreate();

        Backendless.setUrl(SERVER_URL);
        Backendless.initApp(getApplicationContext(), APPLICATION_ID, API_KEY);
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
