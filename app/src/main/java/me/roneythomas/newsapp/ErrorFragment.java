package me.roneythomas.newsapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by roneythomas on 2016-07-16.
 */
public class ErrorFragment extends Fragment {
    public static ErrorFragment newInstance() {

        Bundle args = new Bundle();

        ErrorFragment fragment = new ErrorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.error_layout, container, false);
        Log.d("dfssdf","i am working");
        return view;
    }
}
