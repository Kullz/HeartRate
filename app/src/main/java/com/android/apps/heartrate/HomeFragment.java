package com.android.apps.heartrate;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;


public class HomeFragment extends Fragment {

    private ImageButton arrowButton;
    private ImageButton settings;
    private MainActivity host;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View homeView = inflater.inflate(R.layout.fragment_home, container, false);
        host = (MainActivity) getActivity();

        arrowButton = (ImageButton) homeView.findViewById(R.id.home_arrow_button);
        settings = (ImageButton) homeView.findViewById(R.id.settings);

        arrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                host.getFragmentManager().beginTransaction().replace(R.id.main_container, new InstructionFragment()).commit();
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                host.getFragmentManager().beginTransaction().replace(R.id.main_container, new WiFiFragment()).commit();
            }
        });

        return homeView;
    }


}