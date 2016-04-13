package com.android.apps.heartrate;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends FragmentActivity {

    private FragmentManager currentManager;
    private static Fragment currentFragment = new HomeFragment();
    private WiFiSocketTask connection;
    private BeatsData beats;
    private String connectionStatus;

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
        if(currentFragment instanceof DecisionFragment) {
            currentManager.beginTransaction().replace(R.id.main_container, currentFragment).commit();
        }else if(currentFragment instanceof CreditsFragment){

            finish();
            System.exit(0);
        }
    }

    public WiFiSocketTask getConnection() {
        return connection;
    }

    public void setConnection(WiFiSocketTask connection) {
        this.connection = connection;
    }

    public BeatsData getBeats() {
        return beats;
    }

    public void setBeats(BeatsData beats) {
        this.beats = beats;
    }

    public String getConnectionStatus() {
        return connectionStatus;
    }

    public void setConnectionStatus(String connectionStatus) {
        this.connectionStatus = connectionStatus;
    }
}
