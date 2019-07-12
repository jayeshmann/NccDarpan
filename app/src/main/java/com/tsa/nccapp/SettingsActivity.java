package com.tsa.nccapp;

import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.tsa.nccapp.utils.GLOBAL;

public class SettingsActivity extends AppCompatActivity {
    TextView frg_title;
    private SeekBar fontSize;
    private Switch nightMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(SettingsActivity.this, R.color.light_vilate));
        }
        init();
    }

    private void init() {

        frg_title=findViewById(R.id.frg_title);
        frg_title.setText("Settings");

        nightMode=findViewById(R.id.night_mode);
        nightMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(nightMode.isChecked())
                {
                    GLOBAL.BGColor=R.color.black;
                    GLOBAL.textColor=R.color.white;
                }
                else
                {
                    GLOBAL.BGColor=R.color.white;
                    GLOBAL.textColor=R.color.black;
                }
            }
        });

        fontSize=findViewById(R.id.font_size);
        fontSize.setProgress((GLOBAL.textSize/2)-6);
        fontSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                GLOBAL.textSize=6+(i*2);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(SettingsActivity.this,Main2Activity.class));
        finish();
    }
}
