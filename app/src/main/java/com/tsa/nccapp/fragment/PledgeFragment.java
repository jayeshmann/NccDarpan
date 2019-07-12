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

public class PledgeFragment extends Fragment{

    private View view;
    private TextView coreTV;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup content_frame,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pledge_fragment, content_frame, false);

        init();
        return view;

    }

    private void init() {
        coreTV=view.findViewById(R.id.pledgeTV);
        coreTV.setText(Html.fromHtml(getString(R.string.pledge)));
        coreTV.setTextColor(getResources().getColor(GLOBAL.textColor));
        coreTV.setBackgroundColor(getResources().getColor(GLOBAL.BGColor));
        coreTV.setTextSize(GLOBAL.textSize);
       }

}
