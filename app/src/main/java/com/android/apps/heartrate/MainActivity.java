package com.android.apps.heartrate;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends FragmentActivity {

    private FragmentManager currentManager;
    public static Fragment currentFragment = new HomeFragment();
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

}
