package com.example.othermirror.Fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.othermirror.Configuration_model.ConfigFile;
import com.example.othermirror.Database.ConfigRepository;
import com.example.othermirror.DatabaseService.DBService;
import com.example.othermirror.MainActivity;
import com.example.othermirror.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.Serializable;

public class UserFragment extends Fragment {
    TextInputLayout name, countrycode, city, weight, emailaddress, quotes;
    TextInputEditText edit_name;
    TextInputEditText edit_countrycode;
    TextInputEditText edit_city;
    TextInputEditText edit_weight;
    TextInputEditText edit_emailaddres;
    TextInputEditText edit_quotes;
    Button cancel_user;
    Button save_user;
    boolean isBound;
    ConfigFile configFile;
    Intent configIntent;
    DBService dbService;
    ConfigRepository configRepository;
    public static final String BROADCAST_OPTION = "config";
    boolean checkConfig = false;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        getActivity().setTitle("User Settings");
        configFile = new ConfigFile();
        configRepository = new ConfigRepository(getActivity().getApplication());

        name = view.findViewById(R.id.name_input_layout);
        city = view.findViewById(R.id.city_input_layout);
        countrycode = view.findViewById(R.id.countrycode_input_layout);
        weight = view.findViewById(R.id.weight_input_layout);
        emailaddress = view.findViewById(R.id.email_textinput);
        quotes = view.findViewById(R.id.quotes_input_layout);
        cancel_user = view.findViewById(R.id.cancel_settings);
        save_user = view.findViewById(R.id.save_user);

        edit_name = view.findViewById(R.id.edit_name_text);
        edit_countrycode = view.findViewById(R.id.country_code_edit_text);
        edit_city = view.findViewById(R.id.city_edit_text);
        edit_weight = view.findViewById(R.id.weight_edit_text);
        edit_emailaddres = view.findViewById(R.id.email_edit_text);
        edit_quotes = view.findViewById(R.id.quotes_edit_text);

        /*if(!checkConfig){
            getConfigsToEditText();
            checkConfig = true;
        }*/
        save_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                configFile.setName(edit_name.getText().toString());
                configFile.setCountrycode(edit_countrycode.getText().toString());
                configFile.setCity(edit_city.getText().toString());
                configFile.setWeight(edit_weight.getText().toString());
                configFile.setEmail(edit_emailaddres.getText().toString());
                configFile.setQuotes(edit_quotes.getText().toString());
                edit_name.setText(configFile.getName());

                configFile = configRepository.updateAllconfigs().get(0);
                configRepository.update(configFile);

                Toast.makeText(getActivity(), "Saving usersettings", Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }

    private BroadcastReceiver readyDatabase = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            getConfigsToEditText();
            Log.d("Ready", String.valueOf(edit_name));
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this.getActivity()).registerReceiver(readyDatabase, new IntentFilter(BROADCAST_OPTION));
        if(checkConfig) {
            configFile = configRepository.updateAllconfigs().get(0);
            checkConfig = true;
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this.getActivity()).unregisterReceiver(readyDatabase);
    }



    public void getConfigsToEditText(){
        //Intent intent = getActivity().getIntent();
        //int pos = intent.getIntExtra(MainActivity.CONFIG_DETAILS, 0);
        //configFile = configRepository.updateAllconfigs().get(pos);
        configFile = configRepository.updateAllconfigs().get(0);
        edit_name.setText(configFile.getName());
        edit_countrycode.setText(configFile.getCountrycode());
        edit_city.setText(configFile.getCity());
        edit_weight.setText(configFile.getWeight());
        edit_emailaddres.setText(configFile.getEmail());
        edit_quotes.setText(configFile.getQuotes());
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        configFile = new ConfigFile();
        outState.putSerializable("config", configFile);
        if(configFile != null){
            getConfigsToEditText();
        }
    }
}

