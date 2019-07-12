package com.tsa.nccapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tsa.nccapp.NCCActivity;
import com.tsa.nccapp.Ncc_Act_Rule_Fragment;
import com.tsa.nccapp.R;
import com.tsa.nccapp.utils.GLOBAL;

/**
 * A simple {@link Fragment} subclass.
 */
public class DownloadFragment extends Fragment {

    View view;
    private String url=GLOBAL.baseURL+"content/DOWNLOADS/";


    private int itemArr[] = new int[]{R.id.item1, R.id.item2, R.id.item3, R.id.item4, R.id.item5, R.id.item6, R.id.item7,
            R.id.item8, R.id.item9, R.id.item10, R.id.item11, R.id.item12, R.id.item13,};

    private TextView[] textViewArr = new TextView[14];

    private String[] title = new String[]{"ANO ", "Camps", "Enrolment", "Incentives-Benefits", "Institution",
            "Ncc Act and Rule", "PI Handbook", "Special Entry Scheme", "Training", "Uniform","Red Book 2017", "Cadet Hand Book-2017","NCC Cyber Security SOP" };

    private View[] viewArr = new View[13];


    public DownloadFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup content_frame,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_download, content_frame, false);
        init();

        return view;
    }

    private void init() {

        ///////////////////////////////////////////////////////////
        for (int i = 0; i < 13; i++) {
            viewArr[i] = view.findViewById(itemArr[i]);
            textViewArr[i] = viewArr[i].findViewById(R.id.title);
            textViewArr[i].setText(title[i]);

        }
        /////////////////////////////////////////////////////////

        textViewArr[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NCCActivity)getActivity()).changeFragment(new Ano_new_fragment(), null, "ANO", true);
            }
        });


        textViewArr[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NCCActivity)getActivity()).changeFragment(new Camps_Fragment(), null, "CAMPS", true);
            }
        });


        textViewArr[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NCCActivity)getActivity()).changeFragment(new Enrolment_Fragment(), null, "Enrolment", true);
            }
        });

        textViewArr[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NCCActivity)getActivity()).changeFragment(new Incentives_Benefits_Fragment(), null, "Incentives Benefits", true);
            }
        });


        textViewArr[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NCCActivity)getActivity()).changeFragment(new Institution_Fragment(), null, "Institution", true);
            }
        });


        textViewArr[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NCCActivity)getActivity()).changeFragment(new Ncc_Act_Rule_Fragment(), null, "Ncc Act Rule", true);
            }
        });


        textViewArr[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NCCActivity)getActivity()).changeFragment(new PI_HandbookFragment(), null, "PI Handbook", true);
            }
        });


        textViewArr[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NCCActivity)getActivity()).changeFragment(new Special_Entry_SchemeFragment(), null, "Special Entry Scheme", true);
            }
        });


        textViewArr[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NCCActivity)getActivity()).changeFragment(new Training_Fragment(), null, "Training", true);
            }
        });


        textViewArr[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NCCActivity)getActivity()).changeFragment(new Uniform_Fragment(), null, "Uniform", true);
            }
        });

        textViewArr[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLOBAL.url=url+"Red_Book_2017.pdf";
                ((NCCActivity)getActivity()).changeFragment(new CampSOPFragment(), null, "Red Book", true);
            }
        });


        textViewArr[11].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NCCActivity)getActivity()).changeFragment(new CadetHandbook2017Fragment(), null, "Cadet Hand Book-2017", true);
            }
        });

        textViewArr[12].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLOBAL.url=url+"Red_Book_2017.pdf";
                ((NCCActivity)getActivity()).changeFragment(new CampSOPFragment(), null, "NCC Cyber Security SOP", true);
            }
        });


    }

}
