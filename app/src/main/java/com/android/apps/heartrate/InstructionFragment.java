package com.android.apps.heartrate;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class InstructionFragment extends Fragment {

    private ImageButton startButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View instructionView = inflater.inflate(R.layout.fragment_instruction, container, false);
        startButton = (ImageButton) instructionView.findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getFragmentManager().beginTransaction().replace(R.id.main_container, new PulseFragment());
            }
        });

        return instructionView;
    }

}
