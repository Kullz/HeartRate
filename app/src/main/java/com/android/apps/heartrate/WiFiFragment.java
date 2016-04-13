package com.android.apps.heartrate;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class WiFiFragment extends Fragment {

    private EditText editTextAddress, editTextPort;
    private Button buttonConnect;
    private TextView textStatus;
    private MainActivity currentActivity;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View wifiView = inflater.inflate(R.layout.wifi_fragment, container, false);

        textStatus = (TextView)wifiView.findViewById(R.id.textStatus);
        editTextAddress = (EditText)wifiView.findViewById(R.id.address);
        editTextPort = (EditText)wifiView.findViewById(R.id.port);
        buttonConnect = (Button)wifiView.findViewById(R.id.connect);

        currentActivity = (MainActivity) getActivity();

        buttonConnect.setOnClickListener(new View.OnClickListener() {

            String status = "";
            @Override
            public void onClick(View v) {
                if (currentActivity.getConnection() != null) {
                    status = "Already connected!";
                    setStatus("Already connected!");
                    return;
                }

                try {
                    // Get the remote host from the UI and start the thread
                    String host = editTextAddress.getText().toString();
                    int port = Integer.parseInt(editTextPort.getText().toString());

                    // Start the asyncronous task thread
                    setStatus("Attempting to connect...");
                    status = "Attempting to connect...";
                    currentActivity.setConnection(new WiFiSocketTask(host, port));
                    currentActivity.getConnection().execute();

                } catch (Exception e) {
                    e.printStackTrace();
                    status = "Invalid address/port!";
                    setStatus("Invalid address/port!");
                }
                currentActivity.getConnection().sendMessage("Check!");
                Toast.makeText(getActivity(), status, Toast.LENGTH_SHORT).show();
                currentActivity.getFragmentManager().beginTransaction().replace(R.id.main_container, new HomeFragment()).commit();
            }
        });

        return wifiView;
    }

    private void setStatus(String s) {

        textStatus.setText(s);

    }

}
