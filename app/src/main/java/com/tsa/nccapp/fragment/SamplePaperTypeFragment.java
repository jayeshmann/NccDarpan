package com.tsa.nccapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tsa.nccapp.NCCActivity;
import com.tsa.nccapp.R;
import com.tsa.nccapp.utils.GLOBAL;


public class SamplePaperTypeFragment extends Fragment {
    View view;

    private int itemArr[]=new int[]{R.id.item1,R.id.item2,R.id.item3};
    private TextView[] textViewArr=new TextView[3];

    private String[] title=new String[]{"Certificate A","Certificate B","Certificate C"};


    private View[] viewArr=new View[3];
    private LinearLayout root[]=new LinearLayout[17];
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup content_frame,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sample_paper_type_layout, content_frame, false);
        init();

        return view;

    }

    private void init() {

        ///////////////////////////////////////////////////////////
        for (int i = 0; i < 3; i++) {
            viewArr[i] = view.findViewById(itemArr[i]);
            textViewArr[i] = viewArr[i].findViewById(R.id.title);
            textViewArr[i].setText(title[i]);
            root[i]=viewArr[i].findViewById(R.id.root1);
        }
        /////////////////////////////////////////////////////////
        if(GLOBAL.lastVisitedIndex.empty())
        {
            GLOBAL.lastVisitedIndex.push(0);
        }
        //////////////////////////////////////////////////////////

        textViewArr[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLOBAL.cert="A";
                GLOBAL.lastVisitedIndex.push(0);
                ((NCCActivity) getActivity()).changeFragment(new SamplePapersFragment(), null, "Sample Papers(A)", true);
            }
        });

        textViewArr[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLOBAL.lastVisitedIndex.push(1);
                GLOBAL.cert="B";
                ((NCCActivity) getActivity()).changeFragment(new SamplePapersFragment(), null, "Sample Papers(B)", true);
            }
        });


        textViewArr[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLOBAL.lastVisitedIndex.push(2);
                GLOBAL.cert="C";
                ((NCCActivity) getActivity()).changeFragment(new SamplePapersFragment(), null, "Sample Papers(C)", true);
            }
        });

        root[GLOBAL.lastVisitedIndex.peek()].setBackgroundResource(R.drawable.shadow_194355);

     }

   /* @Override
    public void onStop() {
        super.onStop();
        if (GLOBAL.lastVisitedIndex!=null&&!GLOBAL.lastVisitedIndex.isEmpty())
        {
            GLOBAL.lastVisitedIndex.pop();
        }
    }*/
}