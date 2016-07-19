package com.android.apps.heartrate;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageButton;


public class TextData1 extends Fragment {


    private ImageButton arrow;




    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_text_data1, container, false);
        MainActivity host = (MainActivity) getActivity();

        arrow = (ImageButton)view.findViewById(R.id.distilling_arrow);
        ScaleAnimation arrowAnim = new ScaleAnimation(1.0f, 0.0f, 1.0f, 1.0f, Animation.RELATIVE_TO_SELF,1.0f, Animation.RELATIVE_TO_SELF, 0.5f);
        arrowAnim.setDuration(3000);
        arrowAnim.setRepeatCount(10000);
        arrow.startAnimation(arrowAnim);

        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity activity = (MainActivity)getActivity();
                activity.setCurrentFragment(new TextData2());
                activity.getFragmentManager().beginTransaction().replace(R.id.main_container, activity.getCurrentFragment()).commit();
            }
        });


        return view;
    }
}
