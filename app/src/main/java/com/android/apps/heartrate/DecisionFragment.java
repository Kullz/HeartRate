package com.android.apps.heartrate;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


public class DecisionFragment extends Fragment {

    private ImageButton brainButton;
    private ImageButton distillButton;
    private MainActivity hostActivity;
    private String IP = ConnectionInfo.IP;
    private int PORT = ConnectionInfo.PORT;

    private String beats = BeatsData.beats + "";
    private Socket socket;
    private ServerSocket server;



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View currentLayout = inflater.inflate(R.layout.fragment_decision, container, false);

        brainButton = (ImageButton) currentLayout.findViewById(R.id.brain_button);
        distillButton = (ImageButton) currentLayout.findViewById(R.id.distillation_button);
        hostActivity = (MainActivity) getActivity();
        //new Thread(new ClientThread()).start();
        new Thread(new ServerThread()).start();

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
                sendData(1);
                try {
                    server.close();
                } catch (IOException e) {
                }
                MainActivity activity = (MainActivity)getActivity();
                activity.setCurrentFragment(new DistillationFragment());
                activity.getFragmentManager().beginTransaction().replace(R.id.main_container, activity.getCurrentFragment()).commit();
            }
        });


        return currentLayout;
    }

    private void sendData(){
        try {
            String str = BeatsData.beats + "";
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())),
                    true);
            out.println(str);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendData(int k){
        try {
            String str = BeatsData.beats + "";
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())),
                    true);
            out.println(str);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class ClientThread implements Runnable {
        @Override
        public void run() {
            try {
                InetAddress serverAddr = InetAddress.getByName(IP);
                socket = new Socket(serverAddr, PORT);
            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    class ServerThread implements Runnable {
        @Override
        public void run(){
            try{
                server = new ServerSocket(PORT);
                socket = server.accept();
            }catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
