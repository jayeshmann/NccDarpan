package com.tsa.nccapp;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;
import com.tsa.nccapp.custom.CircularNetworkImageView;
import com.tsa.nccapp.custom.CustomVolleyRequest;
import com.tsa.nccapp.utils.GLOBAL;

public class DetailsActivity extends AppCompatActivity {

    public final String TAG = DetailsActivity.class.getSimpleName();

    Toolbar toolbar;
    TextView txtName;
    TextView txtHandle;
    TextView questions;
    CircularNetworkImageView imgPic;
    private Bundle bundle;
    private ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        bundle = getIntent().getExtras();

        toolbar = findViewById(R.id.toolbar);

        txtName = findViewById(R.id.txtName_details);
        txtName.setText(GLOBAL.globalUserModel.getName());

        txtHandle = findViewById(R.id.txtHandle_details);
        txtHandle.setText(GLOBAL.globalUserModel.getEmail());

        imgPic = findViewById(R.id.imgPic_details);
        questions = findViewById(R.id.question);

        ///////////////////////////////////////////////////////////////////////////
        imageLoader = CustomVolleyRequest.getInstance(this.getApplicationContext())
                .getImageLoader();
        imageLoader.get(GLOBAL.photoURL, ImageLoader.getImageListener(imgPic,
                R.drawable.user, android.R.drawable
                        .ic_dialog_alert));
        imgPic.setImageUrl(GLOBAL.photoURL, imageLoader);
        ////////////////////////////////////////////////////////////////////////////

        if (bundle != null) {
            bundle.getString("id");
            questions.setText(bundle.getString("question") + "\n" + bundle.getString("answer"));
        }


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTransionsAnimations();

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setTransionsAnimations() {
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            //Transition transition = getWindow().getSharedElementEnterTransition();
        }
    }

    @Override
    public void onEnterAnimationComplete() {
        super.onEnterAnimationComplete();
        Log.e(TAG, "onEnterAnimationComplete: ");

        // Pic, name, handle
        ViewAnimator
                .animate(imgPic)
                .alpha(0.2f, 1)
                .translationX(-20, 0)
                .translationY(20, 0)
                .duration(200)
                .andAnimate(txtName)
                .alpha(0.0f, 1f)
                .translationX(30, 0)
                .duration(300)
                .startDelay(100)
                .andAnimate(txtHandle)
                .alpha(0.0f, 1f)
                .translationX(50, 0)
                .duration(300)
                .startDelay(200)
                .start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                endActivity();
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void endActivity() {
        ViewAnimator
                .animate(imgPic)
                .alpha(1, 0.2f)
                .translationX(0, -20).translationY(0, 20).duration(200).andAnimate(txtName).alpha(1f, 0f).translationX(0, 30)
                .duration(300).startDelay(100).andAnimate(txtHandle).alpha(1f, 0f).translationX(0, 50).duration(300).startDelay(200)
                .onStop(new AnimationListener.Stop() {
                    @Override
                    public void onStop() {
                        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
                        if (currentapiVersion >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                            supportFinishAfterTransition();
                        } else {
                            finish();
                        }
                    }
                })
                .start();
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        endActivity();
    }
}
