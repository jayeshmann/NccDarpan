package com.tsa.nccapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.tsa.nccapp.NCCActivity;
import com.tsa.nccapp.R;


public class MainFragment extends Fragment {
    View view;

    AdView mAdView;

    private int itemArr[]=new int[]{R.id.item1,R.id.item2,R.id.item3,R.id.item4,R.id.item5,R.id.item6,
            R.id.item7,R.id.item8,R.id.item9,R.id.item10,R.id.item11,R.id.item12};

    private TextView[] textViewArr=new TextView[12];

    private int[] title=new int[]{R.string.ncc_item1,R.string.ncc_item2,R.string.ncc_item3,R.string.ncc_item4,
            R.string.ncc_item6,R.string.ncc_item7,R.string.ncc_item8,R.string.ncc_item9,R.string.ncc_item10,
            R.string.ncc_item11,R.string.ncc_item12, R.string.ncc_item13};


    private ImageView[] imageArr=new ImageView[12];

    private Fragment[] fragments=new Fragment[]{new AboutNCCFragment(),new EnrolmentFragment(),new TrainingFragment()
            ,new SyllabusFragment(),new COCFragment(),new IncentiveBenefitFragment(),new CertificateTypeFragment(),
            new ANOFragment(),new AuthorisationFragment(),new PoliciesFragment(),new SamplePaperTypeFragment(),new DownloadFragment()};


    private int[] iconArr=new int[]{R.drawable.about_w,R.drawable.enrolment_w,R.drawable.tranning_w,
            R.drawable.sly_w, R.drawable.camps_w,R.drawable.benefits_w,R.drawable.exam_w,R.drawable.institution_w
            ,R.drawable.authorization_w,R.drawable.policy_w,R.drawable.sample_paper_w, R.drawable.download_w};

    private View[] viewArr=new View[12];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup content_frame,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_fragment, content_frame, false);

        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(getActivity(), getResources().getString(R.string.add_mob_id));
        init();

        return view;

    }

    private void init() {
        mAdView =  view.findViewById(R.id.adView);
        AdRequest adRequest1 = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest1);

        for(int i=0;i<12;i++)
        {
            viewArr[i]=view.findViewById(itemArr[i]);
            textViewArr[i]=viewArr[i].findViewById(R.id.title);
            textViewArr[i].setText(title[i]);
            imageArr[i]=viewArr[i].findViewById(R.id.image);
            imageArr[i].setImageResource(iconArr[i]);

            final int finalI = i;
            textViewArr[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((NCCActivity)getActivity()).changeFragment(fragments[finalI], null,
                            getResources().getString(title[finalI]), true);
                }
            });
        }

        /////////////////////////////////////////////////////////
    }



}