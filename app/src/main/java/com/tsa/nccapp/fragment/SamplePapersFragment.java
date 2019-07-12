package com.tsa.nccapp.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tsa.nccapp.NCCActivity;
import com.tsa.nccapp.R;
import com.tsa.nccapp.utils.GLOBAL;


public class SamplePapersFragment extends Fragment {
    private View view;

    private int itemArr[]=new int[]{R.id.item1,R.id.item2,R.id.item3,R.id.item4,R.id.item5,R.id.item6,R.id.item7,R.id.item8,
            R.id.item9,R.id.item10,R.id.item11,R.id.item12,
            R.id.item13,R.id.item14,R.id.item15};
    private TextView[] textViewArr=new TextView[15];


    private String[] path1=new String[]{
            "/A_CERT/p1.pdf",
            "/A_CERT/p2.pdf",
            "/A_CERT/p3.pdf",
            "/A_CERT/p4.pdf",
            "/A_CERT/p5.pdf",
            "/A_CERT/p6.pdf"};


    private String[] path2=new String[]{
            "/B_CERT/PB1.pdf",
            "/B_CERT/PB2.pdf",
            "/B_CERT/PB3.pdf",
            "/B_CERT/PB4.pdf",
            "/B_CERT/PB5.pdf",
            "/B_CERT/PB6.pdf"
            };


    private String[] path3=new String[]{
            "/C_CERT/PC1.pdf",
            "/C_CERT/PC2.pdf",
            "/C_CERT/PC3.pdf",
            "/C_CERT/PC4.pdf",
            "/C_CERT/PC5.pdf"};


    private String TAG3[]=new String[]{"Sample Paper1","Sample Paper2","Sample Paper3",
            "Sample Paper4","Sample Paper5"};


    private String TAG1[]=new String[]{"Sample Paper1","Sample Paper2","Sample Paper3",
            "Sample Paper4","Sample Paper5","Practical Qusetion Paper"};

    private String TAG2[]=new String[]{"Sample Paper1","Sample Paper2","Sample Paper3"
            ,"Sample Paper4","Sample Paper5","Sample Paper6"};


    private ImageView[] imageArr=new ImageView[17];

    private View[] viewArr=new View[17];

    private String url=GLOBAL.baseURL+"/content/SAMPLE_PAPERS";

    private LinearLayout root[]=new LinearLayout[17];

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup
            content_frame, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.certificate_type, content_frame, false);
        init();
        return view;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    private void init() {
        String tempTag[]=null;
        String tempPath[]=null;

        if(GLOBAL.cert.equals("A"))
        {
            tempTag=TAG1;
            tempPath=path1;
        }
        else if(GLOBAL.cert.equals("B"))
        {
            tempTag=TAG2;
            tempPath=path2;
        }
        else if(GLOBAL.cert.equals("C")) {
            tempTag = TAG3;
            tempPath = path3;
        }
////////////////////////////////////////////////////////////////////////////////////////////////////
        if(GLOBAL.lastVisitedIndex.empty())
        {
            GLOBAL.lastVisitedIndex.push(0);
        }

////////////////////////////////////////////////////////////////////////////////////////////////////
        for(int i=0;i<tempTag.length;i++)
        {
            viewArr[i]=view.findViewById(itemArr[i]);
            viewArr[i].setVisibility(View.VISIBLE);
            textViewArr[i]=viewArr[i].findViewById(R.id.title);
            textViewArr[i].setText(tempTag[i]);
            imageArr[i]=viewArr[i].findViewById(R.id.image);
            root[i]=viewArr[i].findViewById(R.id.root1);

            final int finalI = i;
            final String[] finalTempPath = tempPath;
            final String[] finalTempTag = tempTag;
            textViewArr[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    GLOBAL.url=url+ finalTempPath[finalI];
                    GLOBAL.lastVisitedIndex.push(finalI);
                    Log.e("GLOBAL.lastVisitedIndex",""+GLOBAL.lastVisitedIndex.peek());
                    ((NCCActivity)getActivity()).changeFragment(new CampSOPFragment(),
                            null, finalTempTag[finalI], true);
                }
            });
        }

        root[GLOBAL.lastVisitedIndex.peek()].setBackgroundResource(R.drawable.shadow_194355);
////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    /*@Override
    public void onStop() {
        super.onStop();
        if (GLOBAL.lastVisitedIndex!=null&&!GLOBAL.lastVisitedIndex.isEmpty())
        {
            GLOBAL.lastVisitedIndex.pop();
        }
    }*/

}

