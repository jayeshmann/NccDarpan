package com.tsa.nccapp.fragment.DetailedPrifile;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tsa.nccapp.R;

import com.tsa.nccapp.databinding.FragmentExam1Binding;

public class AchiveFragment extends Fragment {
    FragmentExam1Binding binding;
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_exam1, container, false);
        View view = binding.getRoot();

        init();
        return view;
    }

    private void init() {

        //binding.
    }
}
