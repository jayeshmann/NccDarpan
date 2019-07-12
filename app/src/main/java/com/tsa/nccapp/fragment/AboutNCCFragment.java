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


public class AboutNCCFragment extends Fragment {
    View view;

    AdView mAdView;

    private int itemArr[]=new int[]{R.id.item1,R.id.item2,R.id.item3,R.id.item4,R.id.item5,R.id.item6,
            R.id.item7,R.id.item8,R.id.item9,R.id.item10};
    private TextView[] textViewArr=new TextView[10];

    private int[] title=new int[]{R.string.about_ncc_item1,R.string.about_ncc_item2,R.string.about_ncc_item3
            ,R.string.ncc_item4,R.string.about_ncc_item5,
            R.string.about_ncc_item6,R.string.about_ncc_item7,R.string.about_ncc_item8
            ,R.string.about_ncc_item9,R.string.about_ncc_item10};

    private ImageView[] imageArr=new ImageView[10];

    private int[] iconArr=new int[]{R.drawable.intro_icon,R.drawable.organization,R.drawable.aims_icon,
            R.drawable.about_icon,R.drawable.moto_icon,
            R.drawable.values_icon,R.drawable.song_icon,R.drawable.flag_icon,R.drawable.pledge_icon,R.drawable.commandments};

    private View[] viewArr=new View[10];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup content_frame,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.grid_layout, content_frame, false);

        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(getActivity(), getResources().getString(R.string.add_mob_id));
        init();

        return view;

    }

    private void init() {
        mAdView =  view.findViewById(R.id.adView);
        AdRequest adRequest1 = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest1);

        for(int i=0;i<10;i++)
        {
            viewArr[i]=view.findViewById(itemArr[i]);
            textViewArr[i]=viewArr[i].findViewById(R.id.title);
            textViewArr[i].setText(title[i]);
            imageArr[i]=viewArr[i].findViewById(R.id.image);
            imageArr[i].setImageResource(iconArr[i]);
            final Fragment frgArr[]=new Fragment[]{new IntroductionFragment(),new OrgFragment()
                    ,new AimFragment(),new ObjectiveFragment()
                    ,new MottoFragment(),new CoreFragment(),new NCCSongFragment(),new NCCFlagFragment(),
                    new PledgeFragment(),new CommandmentsFragment()
            };
            final int finalI = i;
            textViewArr[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((NCCActivity)getActivity()).changeFragment(frgArr[finalI], null, getResources().getString(title[finalI]), true);
                }
            });
        }


        /////////////////////////////////////////////////////////


    }



}