package com.android.apps.heartrate;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by kull on 7.4.2016.
 */
public class CreditsFragment extends Fragment {
    ImageView start;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_credits,container, false);
        start = (ImageView) view.findViewById(R.id.start_over);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MainActivity.class);
                BeatsData.beats = 0;
                ((MainActivity)getActivity()).setCurrentFragment(new HomeFragment());
                startActivity(i);
                getActivity().finish();
            }
        });
        return view;
    }

}
