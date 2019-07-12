package com.tsa.nccapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.startapp.android.publish.ads.nativead.NativeAdDetails;
import com.startapp.android.publish.ads.nativead.NativeAdPreferences;
import com.startapp.android.publish.ads.nativead.StartAppNativeAd;
import com.startapp.android.publish.adsCommon.Ad;
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.StartAppSDK;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.tsa.nccapp.custom.CircularNetworkImageView;
import com.tsa.nccapp.custom.CustomActivity;
import com.tsa.nccapp.custom.CustomVolleyRequest;
import com.tsa.nccapp.utils.GLOBAL;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static com.tsa.nccapp.R.string.item8;

public class Main2Activity extends CustomActivity implements View.OnClickListener {

private TextView otherExam;
    //private ListView cardList;
    private int imagesMain[] = new int[]{R.drawable.menu_w, R.drawable.sample_paper_w};

    private int mainArr[] = new int[]{R.id.main_menu, R.id.sample_paper};

    private int textArr[] = new int[]{R.id.item1, R.id.item2, R.id.item3, R.id.item4, R.id.item5, R.id.item6, R.id.item7,R.id.item8};
    private TextView[] textViewArr = new TextView[8];

    private int[] title = new int[]{R.string.item1, R.string.item2, R.string.item3, R.string.item4, R.string.item5,
            R.string.item6, R.string.item7,R.string.item8};

    private ImageView[] imageArr = new ImageView[7];

    private int[] iconArr = new int[]{R.drawable.language_icon, R.drawable.edit_pro, R.drawable.syllabus_lcon, R.drawable.flag,
            R.drawable.setting_icon,
            R.drawable.coutact, R.drawable.share_icon};

    private View[] viewArr = new View[7];

    private GoogleSignInClient mGoogleSignInClient;

    private Context context;
    private CircularNetworkImageView userImage;
    private ImageLoader imageLoader;

    private LinearLayout root;

    boolean doubleBackToExitPressedOnce = false;

    private TextView userName;
    private TextView userEmail;
    private ImageView notification;

    private LinearLayout[] options = new LinearLayout[3];

    SharedPreferences sharedPref;
    private TextView notification_text;

    private StartAppAd startAppAd = new StartAppAd(this);

    /**
     * StartApp Native Ad declaration
     */
    private StartAppNativeAd startAppNativeAd = new StartAppNativeAd(this);
    private NativeAdDetails nativeAd = null;

    private ImageView imgFreeApp = null;
    private TextView txtFreeApp = null;
    /**
     * Native Ad Callback
     */
    private AdEventListener nativeAdListener = new AdEventListener() {

        @Override
        public void onReceiveAd(Ad ad) {

            // Get the native ad
            ArrayList<NativeAdDetails> nativeAdsList = startAppNativeAd.getNativeAds();
            if (nativeAdsList.size() > 0) {
                nativeAd = nativeAdsList.get(0);
            }

            // Verify that an ad was retrieved
            if (nativeAd != null) {

                // When ad is received and displayed - we MUST send impression
                nativeAd.sendImpression(Main2Activity.this);

                if (imgFreeApp != null && txtFreeApp != null) {

                    // Set button as enabled
                    imgFreeApp.setEnabled(true);
                    txtFreeApp.setEnabled(true);

                    // Set ad's image
                    imgFreeApp.setImageBitmap(nativeAd.getImageBitmap());

                    // Set ad's title
                    txtFreeApp.setText(nativeAd.getTitle());
                }
            }
        }

        @Override
        public void onFailedToReceiveAd(Ad ad) {

            // Error occurred while loading the native ad
            if (txtFreeApp != null) {
                txtFreeApp.setText("Error while loading Native Ad");
            }
        }
    };
    ////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//
        //TODO: Replace with your Application ID
        StartAppSDK.init(this, "203080696", true);
        setContentView(R.layout.activity_main2);

        ////////////////////////////////////////////////////////////////////////////////////////////

        /*edited by gaurav */

        /** Initialize Native Ad views
        imgFreeApp = findViewById(R.id.image_view);
        txtFreeApp = findViewById(R.id.lblListItem);
        if (txtFreeApp != null) {
            txtFreeApp.setText("Loading Native Ad...");
        }

        /**
         * Load Native Ad with the following parameters:

        startAppNativeAd.loadAd(
                new NativeAdPreferences()
                        .setAdsNumber(1)
                        .setAutoBitmapDownload(true)
                        .setPrimaryImageSize(2),
                nativeAdListener);  **/
        ////////////////////////////////////////////////////////////////////////////////////////////
        Log.d("FirebaseID", FirebaseInstanceId.getInstance().getToken());
        ////////////////////////////////////////////////////////////////////////////////////////////

           context = Main2Activity.this;

        generateNoteOnSD(context, "Firebase ID", "" + FirebaseInstanceId.getInstance().getToken());
        ///////////////////////////////////////////////////////////////////////////////////

        sharedPref = context.getSharedPreferences(
                "login", Context.MODE_PRIVATE);

        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();

        for (int i = 0; i < imagesMain.length; i++) {
            options[i] = findViewById(mainArr[i]);
            final int finalI = i;
            options[i].setScaleX(0);
            options[i].setScaleY(0);
            options[i].animate()
                    .setDuration(1000)
                    .scaleX(1.0f)
                    .scaleY(1.0f)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            root.setBackgroundResource(R.drawable.show_background);
                        }
                    });

            options[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    /////////////////////////////////////////////////////////////////////////////////////////////////
                    if (finalI ==0 && !showDialogue()) {
                        showDialogue();
                        startActivity(new Intent(Main2Activity.this, NCCActivity.class));
                        finish();
                    } else
                        if (finalI == 1&& !showDialogue()) {
                        startActivity(new Intent(Main2Activity.this, Product.class));
                        finish();
                    } /*else if (finalI == 2 && !showDialogue()) {
                        {
                            showDialogue();
                            startActivity(new Intent(Main2Activity.this, CategoryListActivity.class));
                            finish();
                        }
                    }*/
                    ///////////////////////////////////////////////////////////////////////////////////////////////////
                }
            });
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    /////////////////////////////////
    @Override
    public void onBackPressed() {
        startAppAd.onBackPressed();
        createMyDialogue(Main2Activity.this, "Do You Want To Logout ?", true, false, new Intent(Main2Activity.this, LoginActivity2.class));
    }

    ////////////////////////////////////
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.item1:
                //MyDialogue.createMyDialogue(Main2Activity.this,"This Service will Available Soon",true,false,null);
                break;
            case R.id.item2:
                startActivity(new Intent(Main2Activity.this, Member_ShipActivity.class));
                finish();
                break;

            case R.id.item3:
                startActivity(new Intent(Main2Activity.this, MainActivity.class));
                finish();
                break;
            case R.id.item4:
                startActivity(new Intent(Main2Activity.this, Exam_CardActivity.class));
                finish();
                break;
            case R.id.item5:
                startActivity(new Intent(Main2Activity.this, SettingsActivity.class));
                finish();
                break;
            case R.id.item6:
                startActivity(new Intent(Main2Activity.this, ContactUsActivity.class));
                finish();
                break;
            case R.id.item7:
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "https://play.google.com/store/apps/details?id=com.tsa.nccapp";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
                break;

            case R.id.item8:
                startActivity(new Intent(Main2Activity.this, Exam_CardActivity.class));
                finish();
                break;

                }
    }

    //////////////////////////////////////////////////////////////////////////////////
    public void generateNoteOnSD(Context context, String sFileName, String sBody) {
        try {
            File root = new File(Environment.getExternalStorageDirectory(), "Notes");
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, sFileName);
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(sBody);
            writer.flush();
            writer.close();
            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private void init() {


        root = findViewById(R.id.root);
        userImage = findViewById(R.id.user_image);
        notification = findViewById(R.id.notification);
        notification_text = findViewById(R.id.notification_text);

        ////////////////////////////////////////////////////////////////////////////////////////////////
        otherExam=findViewById(R.id.other_exams);
        otherExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialogue();
                startActivity(new Intent(context,OtherTestListActivity.class));
                finish();
            }
        });

        /////////////////////////////////////////////////////////////////////////////////////////////////

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity.this, NotificationActivity.class));
                finish();
            }
        });

        /* imageLoader = CustomVolleyRequest.getInstance(this.getApplicationContext())
                .getImageLoader();
        imageLoader.get(GLOBAL.photoURL, ImageLoader.getImageListener(userImage,
                R.drawable.user, android.R.drawable
                        .ic_dialog_alert));
        userImage.setImageUrl(GLOBAL.photoURL, imageLoader);*/

        ///////////////////////////////////////////////////////////
       /* if (GLOBAL.globalLoginModel != null) {
            userName = findViewById(R.id.user_name);
            userName.setText(GLOBAL.globalLoginModel.getName_of_cadet());
            userEmail = findViewById(R.id.user_email);
            userEmail.setText(GLOBAL.globalLoginModel.getEmail());
        }*/
        //////////////////////////////////////////////////////////

        for (int i = 0; i < 7; i++) {
            viewArr[i] = findViewById(textArr[i]);
            textViewArr[i] = viewArr[i].findViewById(R.id.title);
            textViewArr[i].setText(title[i]);
            imageArr[i] = viewArr[i].findViewById(R.id.image);
            imageArr[i].setImageResource(iconArr[i]);
            viewArr[i].setOnClickListener(this);
        }
    }
    //////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void createMyDialogue(final Context context, String msg, boolean okButton, boolean canButton, final Intent intent) {
        // TODO Create custom dialog
        final Dialog dialog = new Dialog(context);

        //for no Title bar
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.start_trip_dialogue);

        //setting layout hight width
        Window window = dialog.getWindow();
        assert window != null;
        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        if (okButton) {
            Button ok = dialog.findViewById(R.id.ok_button);
            final TextView msgBox = dialog.findViewById(R.id.msg_box);
            msgBox.setText(msg);

            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.clear();
                    editor.commit();

                    if (intent != null) {
                        signOut(intent);
                    }
                }
            });
            }

        Button cancle = dialog.findViewById(R.id.cancle_button);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        if (doubleBackToExitPressedOnce) {
            dialog.show();
        }
        this.doubleBackToExitPressedOnce = true;
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }

        }, 2000);

    }

    private void signOut(final Intent intent) {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                });
    }


    private boolean showDialogue() {
        /////////////////////////////////////////////////////////////////////////////
        if (GLOBAL.loginType == GLOBAL.GUESTLOGIN) {
            @SuppressLint("RestrictedApi") final ContextThemeWrapper con = new ContextThemeWrapper(this, R.style.AlertS);
            android.app.AlertDialog dialog = new AlertDialog.Builder(con)
                    .setMessage("For Subscribed Members Only")
                    .setCancelable(false)
                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(context, RegisterActivity.class));
                            finish();
                        }
                    })
                    .show();
            return true;
        }
        return false;
    }
}