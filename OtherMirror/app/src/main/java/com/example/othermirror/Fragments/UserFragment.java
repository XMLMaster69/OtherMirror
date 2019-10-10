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

public class UserFragment extends Fragment {
    TextView name, address, country, weight,
            height, email, quotes;
    Button cancel_user;
    Button save_user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_user, container, false);
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        /*name = view.findViewById(R.id.name_user);
        address = view.findViewById(R.id.address_user);
        country = view.findViewById(R.id.country_user);
        weight = view.findViewById(R.id.weight_user);
        height = view.findViewById(R.id.height_user);
        email = view.findViewById(R.id.email_user);
        quotes = view.findViewById(R.id.quotes_user); */
        cancel_user = view.findViewById(R.id.cancel_settings);
        save_user = view.findViewById(R.id.save_user);


        return view;
    }
}

