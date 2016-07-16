package me.roneythomas.newsapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = getApplicationContext();
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.contianer_main);
        if (fragment == null && isConnected) {
            fragment = NewsFragment.newInstance();
            fragmentManager.beginTransaction().add(R.id.contianer_main, fragment).commit();
        } else if (fragment == null) {
            //we do the error here.
            fragment = ErrorFragment.newInstance();
            fragmentManager.beginTransaction().add(R.id.contianer_main, fragment).commit();
        }
    }
}
