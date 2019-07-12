package com.tsa.nccapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tsa.nccapp.fragment.DetailedPrifile.ProfileFragment;
import com.tsa.nccapp.interfaces.FragmentMessenger;

public class DetailedProfileActivity extends AppCompatActivity implements FragmentMessenger {
    private FragmentManager fragmentManager;
    public FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_profile);
        changeFragment(new ProfileFragment(), null, "Detailed Profile", true);
    }

    @Override
    public void changeFragment(Fragment fragment, Bundle data, String fragmentTag, boolean addToBackStack) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
        fragmentTransaction.replace(R.id.root, fragment, fragmentTag);
        if (data != null)
            fragment.setArguments(data);
        fragmentTransaction.addToBackStack(fragmentTag);
        fragmentTransaction.commit();
    }
}
