package com.android.apps.heartrate;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class DecisionFragment extends Fragment {

    private ImageButton brainButton;
    private ImageButton distillButton;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View currentLayout = inflater.inflate(R.layout.fragment_decision, container, false);
        final ProcessingActivity currentActivity = (ProcessingActivity) getActivity();

        brainButton = (ImageButton) currentLayout.findViewById(R.id.brain_button);
        distillButton = (ImageButton) currentLayout.findViewById(R.id.distillation_button);

        brainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //getActivity().getFragmentManager().beginTransaction().replace(R.id.main_container, new PulseActivity()).commit();
            }
        });

        distillButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                getActivity().getFragmentManager().beginTransaction().replace(R.id.process_container, new DistillationFragment()).commit();
            }
        });


        return currentLayout;
    }

}
