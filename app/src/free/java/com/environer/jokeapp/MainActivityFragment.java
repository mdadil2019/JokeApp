package com.environer.jokeapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.environer.jokeapp.R;

/**
 * Created by Mohammad Adil on 15-06-2017.
 */

public class MainActivityFragment extends Fragment {
    public MainActivityFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_main,container,false);
        return root;
    }
}
