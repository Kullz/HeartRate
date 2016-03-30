package com.android.apps.heartrate;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class PulseFragment extends Fragment {


    //private ImageButton arrowButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View homeView = inflater.inflate(R.layout.fragment_pulse, container, false);
//        arrowButton = (ImageButton) homeView.findViewById(R.id.start_button);
//        arrowButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getActivity().getFragmentManager().beginTransaction().replace(R.id.main_container, new PulseFragment());
//            }
//        });

        return homeView;
    }

}
