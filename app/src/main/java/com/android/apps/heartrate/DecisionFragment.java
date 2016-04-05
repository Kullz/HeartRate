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
        final MainActivity currentActivity = (MainActivity) getActivity();

        brainButton = (ImageButton) currentLayout.findViewById(R.id.brain_button);
        distillButton = (ImageButton) currentLayout.findViewById(R.id.distillation_button);

        brainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //getActivity().getFragmentManager().beginTransaction().replace(R.id.main_container, new PulseFragment()).commit();
            }
        });

        distillButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setCurrentFragment(new DistillationFragment());
                getActivity().getFragmentManager().beginTransaction().replace(R.id.main_container, ((MainActivity)getActivity()).getCurrentFragment()).commit();
            }
        });


        return currentLayout;
    }

}
