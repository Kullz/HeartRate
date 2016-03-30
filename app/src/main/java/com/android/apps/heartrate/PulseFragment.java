package com.android.apps.heartrate;

import android.app.Fragment;
import android.hardware.Camera;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicBoolean;


public class PulseFragment extends Fragment {

    private static final AtomicBoolean processing = new AtomicBoolean(false);

    private static int averageIndex = 0;
    private static final int averageArraySize = 4;
    private static final int[] averageArray = new int[averageArraySize];

    public static enum TYPE{
        GREEN, RED
    };

    private static TYPE currentType = TYPE.GREEN;

    public static TYPE getCurrentType() {return currentType;}

    private static int beatsIndex = 0;
    private static final int beatsArraySize = 3;
    private static final int[] beatsArray = new int[beatsArraySize];
    private static double beats = 0;
    private static long startTime = 0;

    private ImageButton arrowButton;
    private static TextView text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View homeView = inflater.inflate(R.layout.fragment_pulse, container, false);
        text = (TextView) homeView.findViewById(R.id.text);
        Camera cam = Camera.open();
        startTime = System.currentTimeMillis();
        cam.release();


//        arrowButton = (ImageButton) homeView.findViewById(R.id.start_button);
//        arrowButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getActivity().getFragmentManager().beginTransaction().replace(R.id.main_container, new PulseFragment());
//            }
//        });

        return homeView;
    }

    private static Camera.PreviewCallback previewCallback = new Camera.PreviewCallback() {
        @Override
        public void onPreviewFrame(byte[] data, Camera camera) {

            if(data == null) throw new NullPointerException();

            Camera.Size size = camera.getParameters().getPreviewSize();

            if(size == null) throw new NullPointerException();

            AtomicBoolean processing = new AtomicBoolean(false);

            if(!processing.compareAndSet(false, true)) return;

            int width = size.width;
            int height = size.height;

            int imgAvg = ImageProcessing.decodeYUV420SPtoRedAvg(data.clone(), height, width);

            if(imgAvg == 0 || imgAvg == 256){ processing.set(false); return; }

            int averageArrayAvg = 0;
            int averageArrayCnt = 0;

            for(int i = 0; i < averageArray.length; i++){
                if(averageArray[i] > 0){
                    averageArrayAvg += averageArray[i];
                    averageArrayCnt++;
                }
            }

            int rollingAverage = (averageArrayCnt > 0) ? (averageArrayAvg / averageArrayCnt) : 0;
            TYPE newType = currentType;
            if(imgAvg < rollingAverage){
                newType = TYPE.RED;
                if(newType != currentType) {beats++;}
                else if(imgAvg > rollingAverage){ newType = TYPE.GREEN;}
            }

            if(averageIndex == averageArraySize) averageIndex = 0;
            averageArray[averageIndex] = imgAvg;
            averageIndex++;

            if(newType != currentType){ currentType = newType;}

            long endTime = System.currentTimeMillis();
            double totalTimeInSecs = (endTime - startTime)/1000d;
            if(totalTimeInSecs >= 10){
                double bps = (beats / totalTimeInSecs);
                int dpm = (int) (bps * 60d);

                if(dpm < 30 || dpm > 180){
                    startTime = System.currentTimeMillis();
                    beats = 0;
                    processing.set(false);
                    return;
                }

                if(beatsIndex == beatsArraySize) beatsIndex = 0;
                beatsArray[beatsIndex] = dpm;
                beatsIndex++;

                int beatsArrayAvg = 0;
                int beatsArrayCnt = 0;

                for(int i = 0; i < beatsArray.length; i++){
                    if(beatsArray[i] > 0){
                        beatsArrayAvg += beatsArray[i];
                        beatsArrayCnt++;
                    }
                }

                int beatsAvg = (beatsArrayAvg / beatsArrayCnt);
                text.setText(String.valueOf(beatsAvg));
                startTime = System.currentTimeMillis();
                beats = 0;

            }
            processing.set(false);


        }
    };

}
