package com.example.othermirror.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.othermirror.R;

public class SettingsFragment extends Fragment {
    TextView Wifi;
    Button language;
    Button cancel;
    Button save;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_settings, container, false);
         View view = inflater.inflate(R.layout.fragment_settings, container, false);
        getActivity().setTitle("Settings");

        Wifi = view.findViewById(R.id.Wifi);
         language = view.findViewById(R.id.language);
         cancel = view.findViewById(R.id.cancel_settings);
         save = view.findViewById(R.id.save_settings);

         return view;
    }
}
