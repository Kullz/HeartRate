package com.android.apps.heartrate;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageButton;
import android.widget.Toast;

public class DistillationFragment extends Fragment {

    private ImageButton arrow;
    private MainActivity host;
    private WiFiSocketTask connection;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_distillation, container, false);
        host = (MainActivity)getActivity();
        connection = host.getConnection();


        if(connection != null) {
            Toast.makeText(host, "sending beats" + " : sent beats: " + BeatsData.beats, Toast.LENGTH_SHORT).show();
            connection.sendMessage(BeatsData.beats+"");
        }

        host.setConnection(null);

        arrow = (ImageButton)view.findViewById(R.id.distilling_arrow);
        ScaleAnimation arrowAnim = new ScaleAnimation(1.0f, 0.0f, 1.0f, 1.0f, Animation.RELATIVE_TO_SELF,1.0f, Animation.RELATIVE_TO_SELF, 0.5f);
        arrowAnim.setDuration(3000);
        arrowAnim.setRepeatCount(10000);
        arrow.startAnimation(arrowAnim);

        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity activity = (MainActivity)getActivity();
                activity.setCurrentFragment(new ThankFragment());
                activity.getFragmentManager().beginTransaction().replace(R.id.main_container, activity.getCurrentFragment()).commit();
            }
        });

        return view;
    }
}
