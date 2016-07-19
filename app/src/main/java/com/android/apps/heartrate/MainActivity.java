package com.android.apps.heartrate;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends FragmentActivity {

    private FragmentManager currentManager;
    private static Fragment currentFragment = new HomeFragment();
    private BeatsData beats;


    public MainActivity mMainActivity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentManager = getFragmentManager();
        currentManager.beginTransaction().add(R.id.main_container, currentFragment).commit();

    }


    public FragmentManager getCurrentManager() {
        return currentManager;
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.currentManager = fragmentManager;
    }

    public Fragment getCurrentFragment() {
        return currentFragment;
    }

    public void setCurrentFragment(Fragment currentFragment) {
        this.currentFragment = currentFragment;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(currentFragment instanceof TextData1) {
            currentManager.beginTransaction().replace(R.id.main_container, currentFragment).commit();
        }else if(currentFragment instanceof CreditsFragment){

            finish();
            System.exit(0);
        }
    }

    public BeatsData getBeats() {
        return beats;
    }

    public void setBeats(BeatsData beats) {
        this.beats = beats;
    }
}
