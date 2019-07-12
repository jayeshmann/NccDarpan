package com.tsa.nccapp.interfaces;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public interface FragmentMessenger {
    public void changeFragment(Fragment fragment, Bundle bundle, String fragmentTag, boolean addToBackStack);

}
