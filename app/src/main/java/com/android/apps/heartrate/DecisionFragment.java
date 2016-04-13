package com.android.apps.heartrate;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;


public class DecisionFragment extends Fragment {

    private ImageButton brainButton;
    private ImageButton distillButton;
    private MainActivity hostActivity;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View currentLayout = inflater.inflate(R.layout.fragment_decision, container, false);

        brainButton = (ImageButton) currentLayout.findViewById(R.id.brain_button);
        distillButton = (ImageButton) currentLayout.findViewById(R.id.distillation_button);
        hostActivity = (MainActivity) getActivity();


        brainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hostActivity.setCurrentFragment(new ThankFragment());
                Intent i = new Intent(hostActivity, PulseActivity.class);
                i.putExtra("marker", true);
                startActivity(i);
            }
        });

        distillButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                hostActivity.setCurrentFragment(new DistillationFragment());
                hostActivity.getFragmentManager().beginTransaction().replace(R.id.main_container, hostActivity.getCurrentFragment()).commit();
            }
        });


        return currentLayout;
    }

}
