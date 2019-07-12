package com.tsa.nccapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tsa.nccapp.NCCActivity;
import com.tsa.nccapp.R;
import com.tsa.nccapp.utils.GLOBAL;

public class SyllabusFragment extends Fragment {
    View view;

    private int itemArr[]=new int[]{R.id.item1,R.id.item2,R.id.item3,R.id.item4};
    private TextView[] textViewArr=new TextView[10];

    private int[] title=new int[]{R.string.sly_item1,R.string.sly_item2,R.string.sly_item3,
            R.string.sly_item4};

    private ImageView[] imageArr=new ImageView[4];

    private int[] iconArr=new int[]{R.drawable.ic_wordlist,
            R.drawable.doc_icon,R.drawable.ic_wordlist,R.drawable.doc_icon};

    private String url=GLOBAL.baseURL+"content/SYLLABUS/";


    private View[] viewArr=new View[4];
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup content_frame,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_syllabus, content_frame, false);
        init();

        return view;

    }

    private void init() {

        for (int i = 0; i < 4; i++) {
            viewArr[i] = view.findViewById(itemArr[i]);
            textViewArr[i] = viewArr[i].findViewById(R.id.title);
            textViewArr[i].setText(title[i]);
            imageArr[i] = viewArr[i].findViewById(R.id.image);
            imageArr[i].setImageResource(iconArr[i]);
        }
        /////////////////////////////////////////////////////////

        textViewArr[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLOBAL.url = url + "BLOCK_SYLLABUS/BLOCK_SYLLABUS_SD_SW.docx";
                ((NCCActivity) getActivity()).changeFragment(new CampSOPFragment(), null, "BLOCK SYLLABUS SD/SW", true);
            }
        });

        textViewArr[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLOBAL.url = url + "DETAILED_BLOCK_SYLLABUS/DETAILED_SYLLABUS_SD_SW.doc";
                ((NCCActivity) getActivity()).changeFragment(new CampSOPFragment(), null, "DETAILED SYLLABUS SD SW", true);
            }
        });

        textViewArr[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLOBAL.url = url + "BLOCK_SYLLABUS/BLOCK_SYLLABUS_JD_JW.docx";
                ((NCCActivity) getActivity()).changeFragment(new CampSOPFragment(), null, "BLOCK SYLLABUS JD JW", true);
            }
        });

        textViewArr[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLOBAL.url = url + "DETAILED_BLOCK_SYLLABUS/DETAILED_SYLLABUS_JD_JW.doc";
                ((NCCActivity) getActivity()).changeFragment(new CampSOPFragment(), null, "DETAILED SYLLABUS JD JW", true);
            }
        });

    }
}
