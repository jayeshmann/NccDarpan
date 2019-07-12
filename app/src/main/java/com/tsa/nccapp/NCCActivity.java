    package com.tsa.nccapp;

    import android.content.Context;
    import android.content.Intent;
    import android.os.Build;
    import android.os.Bundle;
    import android.support.v4.app.Fragment;
    import android.support.v4.app.FragmentManager;
    import android.support.v4.app.FragmentTransaction;
    import android.support.v4.content.ContextCompat;
    import android.support.v4.widget.DrawerLayout;
    import android.support.v7.app.AppCompatActivity;
    import android.support.v7.app.AppCompatDelegate;
    import android.util.Log;
    import android.view.Gravity;
    import android.view.View;
    import android.view.Window;
    import android.view.WindowManager;
    import android.widget.ImageView;
    import android.widget.TextView;

    import com.android.volley.toolbox.ImageLoader;
    import com.android.volley.toolbox.NetworkImageView;
    import com.google.android.gms.ads.AdRequest;
    import com.google.android.gms.ads.AdView;
    import com.google.android.gms.ads.InterstitialAd;
    import com.google.android.gms.ads.MobileAds;

    import com.tsa.nccapp.custom.CustomActivity;
    import com.tsa.nccapp.custom.CustomVolleyRequest;
    import com.tsa.nccapp.fragment.ANOFragment;
    import com.tsa.nccapp.fragment.AboutNCCFragment;
    import com.tsa.nccapp.fragment.AuthorisationFragment;
    import com.tsa.nccapp.fragment.COCFragment;
    import com.tsa.nccapp.fragment.CertificateTypeFragment;
    import com.tsa.nccapp.fragment.DownloadFragment;
    import com.tsa.nccapp.fragment.EnrolmentFragment;
    import com.tsa.nccapp.fragment.IncentiveBenefitFragment;
    import com.tsa.nccapp.fragment.MainFragment;
    import com.tsa.nccapp.fragment.PoliciesFragment;
    import com.tsa.nccapp.fragment.SamplePaperTypeFragment;
    import com.tsa.nccapp.fragment.SyllabusFragment;
    import com.tsa.nccapp.fragment.TrainingFragment;
    import com.tsa.nccapp.interfaces.FragmentMessenger;
    import com.tsa.nccapp.utils.GLOBAL;

    import java.util.Stack;

    public class NCCActivity extends CustomActivity implements FragmentMessenger{
        View view;

        private FragmentManager fragmentManager;
        public FragmentTransaction fragmentTransaction;
        private int itemArr[]=new int[]{R.id.item1,R.id.item2,R.id.item3,R.id.item4,R.id.item5,R.id.item6,
                R.id.item7,R.id.item8,R.id.item9,R.id.item10,R.id.item11,R.id.item12, R.id.item13};
        private TextView[] textViewArr=new TextView[13];
        private DrawerLayout drawerLayout;

        AdView mAdView1;

        private int[] title=new int[]{R.string.ncc_item1,R.string.ncc_item2,R.string.ncc_item3,R.string.ncc_item4,
                R.string.ncc_item6,R.string.ncc_item7,R.string.ncc_item8,R.string.ncc_item9,
                R.string.ncc_item10,R.string.ncc_item11,R.string.ncc_item12,R.string.ncc_item13};

        private View[] viewArr=new View[13];
        private TextView frgTitle;

        private Stack<String> tagStack;

        private TextView userName;
        private TextView userEmail;

        private ImageView open;

        private InterstitialAd mInterstitialAd;


        private Context context;
        private NetworkImageView userImage;
        private ImageLoader imageLoader;


        private Fragment[] fragments=new Fragment[]{new AboutNCCFragment(),new EnrolmentFragment(),new TrainingFragment()
                ,new SyllabusFragment(),new COCFragment(),new IncentiveBenefitFragment(),new CertificateTypeFragment(),
                new ANOFragment(),new AuthorisationFragment(),new PoliciesFragment(),new SamplePaperTypeFragment(), new DownloadFragment()};

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);

            setContentView(R.layout.ncc_activity_main);

            context=NCCActivity.this;

            tagStack=new Stack<>();

            MobileAds.initialize(this,
                    getResources().getString(R.string.add_mob_id));

            mInterstitialAd = new InterstitialAd(this);
            mInterstitialAd.setAdUnitId(getResources().getString(R.string.interstitial_id));
            mInterstitialAd.loadAd(new AdRequest.Builder().build());

            init();

            changeFragment(new MainFragment(), null, "Main Menu", true);
            drawerLayout=findViewById(R.id.drawer_layout);
        }

        private void init() {

          /*  userImage=findViewById(R.id.user_image);
            imageLoader = CustomVolleyRequest.getInstance(this.getApplicationContext()).getImageLoader();

            imageLoader.get(GLOBAL.photoURL, ImageLoader.getImageListener(userImage, R.drawable.user,
                    android.R.drawable.ic_dialog_alert));
            userImage.setImageUrl(GLOBAL.photoURL, imageLoader);

            frgTitle=findViewById(R.id.frg_title);*/
           /* userName=findViewById(R.id.user_name);
            userName.setText(GLOBAL.globalLoginModel.getName_of_cadet());
            userEmail=findViewById(R.id.user_email);
            userEmail.setText(GLOBAL.globalLoginModel.getEmail());
*/
            mAdView1 =  findViewById(R.id.adView);
            AdRequest adRequest1 = new AdRequest.Builder().build();
            if(mAdView1!=null)
                mAdView1.loadAd(adRequest1);

            TextView title1=findViewById(R.id.title);
            title1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(NCCActivity.this, Main2Activity.class));
                    finish();
                }
            });

            open = findViewById(R.id.open);
            open.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    drawerLayout.openDrawer(Gravity.START);
                }
            });

            for (int i = 0; i < 12; i++) {
                viewArr[i] = findViewById(itemArr[i]);
                textViewArr[i] = viewArr[i].findViewById(R.id.title);
                textViewArr[i].setTextSize(14);
                textViewArr[i].setText(title[i]);
                final int finalI = i;
                viewArr[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        drawerLayout.closeDrawers();

                        changeFragment(fragments[finalI], null, getResources().getString(title[finalI]), true);

                        for (int i = 0; i < 12; i++) {
                            if (finalI == i) {
                                textViewArr[i].setBackground(getResources().getDrawable(R.color.Vilate));
                                viewArr[i].setBackground(getResources().getDrawable(R.color.Vilate));
                                textViewArr[i].setTextColor(getResources().getColor(R.color.white));
                            } else {
                                textViewArr[i].setBackground(getResources().getDrawable(R.color.back));
                                viewArr[i].setBackground(getResources().getDrawable(R.color.back));
                                textViewArr[i].setTextColor(getResources().getColor(R.color.black));
                            }
                        }
                    }
                });
            }
        }
        /////////////////////////////////////////////////////////

        @Override
        public void changeFragment(Fragment fragment, Bundle data, String fragmentTag, boolean addToBackStack) {
            fragmentManager = getSupportFragmentManager();
            //boolean fragmentPopped = fragmentManager.popBackStackImmediate(fragmentTag, 0);
            //if (!fragmentPopped && fragmentManager.findFragmentByTag(fragmentTag) == null) {
            fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
            tagStack.push(fragmentTag);
            fragmentTransaction.replace(R.id.content_frame1, fragment, fragmentTag);
            if (data != null)
                fragment.setArguments(data);
            fragmentTransaction.addToBackStack(fragmentTag);
            fragmentTransaction.commit();
            if (frgTitle != null)
                frgTitle.setText(fragment.getTag());

            // }
        }

        @Override
        public void onBackPressed() {
            if(fragmentManager.getBackStackEntryCount()>1&&(!tagStack.isEmpty()))
            {
                fragmentManager.popBackStack();
                tagStack.pop();
                if(!tagStack.isEmpty()) {
                    frgTitle.setText(tagStack.peek());
                }
            }
            else {
                startActivity(new Intent(NCCActivity.this,Main2Activity.class));
                finish();
            }

            if (GLOBAL.lastVisitedIndex!=null&&!GLOBAL.lastVisitedIndex.isEmpty())
            {
                GLOBAL.lastVisitedIndex.pop();
            }
        }

    }
