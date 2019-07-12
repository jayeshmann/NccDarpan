package com.tsa.nccapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tsa.nccapp.R;
import com.tsa.nccapp.utils.GLOBAL;

public class ObjectiveFragment extends Fragment{

    private View view;
    private TextView itemTV1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup content_frame,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_objective, content_frame, false);

        init();
        return view;

    }

    private void init() {
        itemTV1=view.findViewById(R.id.mottoTv);
        itemTV1.setText(Html.fromHtml(getString(R.string.objective)));
        itemTV1.setTextColor(getResources().getColor(GLOBAL.textColor));
        itemTV1.setBackgroundColor(getResources().getColor(GLOBAL.BGColor));
        itemTV1.setTextSize(GLOBAL.textSize);
       }

}
