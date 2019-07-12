package com.tsa.nccapp.fragment.DetailedPrifile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tsa.nccapp.DetailedProfileActivity;
import com.tsa.nccapp.R;
import com.tsa.nccapp.fragment.PersonalInfoFragment;

import java.util.Calendar;

public class ProfileFragment extends Fragment {
    private View view;
    private int[] title=new int[]{R.string.profile_item1,R.string.profile_item2,R.string.profile_item3};
    private Fragment[] fragments=new Fragment[]{new EduInfoFragment(),new PersonalInfoFragment()};
    private int mMonth;
    private int mYear;
    private int mDay;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.profile_new, container, false);
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH+1);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        return view;
    }

    public void editEdu() {
        ((DetailedProfileActivity)getActivity()).changeFragment(new EduInfoFragment(), null, "Educational Information", true);
    }

    public void editPersonal() {
        ((DetailedProfileActivity)getActivity()).changeFragment(new PersonalInfoFragment(), null, "Personal Information", true);
    }
}
