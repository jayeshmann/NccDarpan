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


public class TrainingFragment extends Fragment {
View view;
    private int itemArr[]=new int[]{R.id.item1,R.id.item2,R.id.item3,R.id.item4,R.id.item5,R.id.item6,R.id.item7,R.id.item8,R.id.item9};
    private TextView[] textViewArr=new TextView[10];

    private int[] title=new int[]{R.string.institutional_item1,R.string.institutional_item2,R.string.institutional_item3,
            R.string.institutional_item4,R.string.institutional_item5,R.string.institutional_item6,R.string.institutional_item7,R.string.institutional_item8};

    private ImageView[] imageArr=new ImageView[10];

    private int[] iconArr=new int[]{R.drawable.organization,R.drawable.aims_icon,R.drawable.traning,
            R.drawable.camp3_icon,R.drawable.camp2_icon,R.drawable.attachment_icon
            ,R.drawable.advature2_icon,R.drawable.advature2_icon};

    private View[] viewArr=new View[10];
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup content_frame,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cadet_ranks, content_frame, false);
        init();
        return view;
    }

    private void init() {

        for(int i=0;i<8;i++)
        {
            viewArr[i]=view.findViewById(itemArr[i]);
            textViewArr[i]=viewArr[i].findViewById(R.id.title);
            textViewArr[i].setText(title[i]);
            imageArr[i]=viewArr[i].findViewById(R.id.image);
            imageArr[i].setImageResource(iconArr[i]);
        }

        textViewArr[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NCCActivity)getActivity()).changeFragment(new FetureFragment(), null, "INSTITUTIONAL TRAINING FEATURE", true);
            }
        });

        textViewArr[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NCCActivity)getActivity()).changeFragment(new TrainingProgramFragment(), null, "Training Program", true);
            }
        });

        textViewArr[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NCCActivity)getActivity()).changeFragment(new SocialFragment(), null, "Social Services", true);
            }
        });

        textViewArr[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLOBAL.url=GLOBAL.baseURL+"content/TRAINING/CAMP/CAMP_SOP.docx";
                ((NCCActivity)getActivity()).changeFragment(new CampSOPFragment(), null, "Camp SOP", true);
            }
        });

        textViewArr[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NCCActivity)getActivity()).changeFragment(new TOCFragment(), null, "TYPES OF NCC CAMPS", true);
            }
        });

        textViewArr[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((NCCActivity)getActivity()).changeFragment(new AttachmentTraningFragment(), null, "Attachment Traning", true);

            }
        });

        textViewArr[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((NCCActivity)getActivity()).changeFragment(new AdventureFragment(), null, "Adventure Training", true);

            }
        });

        textViewArr[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((NCCActivity)getActivity()).changeFragment(new SportFragment(), null, "Sports", true);

            }
        });

        /////////////////////////////////////////////////////////
    }


}