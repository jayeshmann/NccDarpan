package com.tsa.nccapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Product extends AppCompatActivity {
    // Ncc test series
    RelativeLayout relative_ncctest;
    ImageView image_ncc_test;
    TextView text_test,header_ncc;

    // Chapter wise questions
    RelativeLayout relative_chapter;
    TextView text_chapter_questions,text_answers;
    ImageView image_ncc_questions;

    // Test Series
    RelativeLayout relative_test_series;
    TextView text_testseries,text_combo_pack;
    ImageView image_test_series;

    // solved paper
    RelativeLayout relative_solved_paper;
    ImageView image_solved_papers;
    TextView text_solved , text_combopack;

    ImageView header_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        relative_ncctest = (RelativeLayout)findViewById(R.id.relative_ncctest);
        image_ncc_test = (ImageView)findViewById(R.id.image_ncc_test);
        text_test = (TextView)findViewById(R.id.text_test);
        header_ncc = (TextView)findViewById(R.id.header_ncc);

        // Chapter wise question

        relative_chapter = (RelativeLayout)findViewById(R.id.relative_chapter);
        image_ncc_questions = (ImageView)findViewById(R.id.image_ncc_questions);
        text_chapter_questions = (TextView)findViewById(R.id.text_chapter_questions);
        text_answers = (TextView)findViewById(R.id.text_answers);

        // Test series

        relative_test_series = (RelativeLayout)findViewById(R.id.relative_test_series);
        image_test_series = (ImageView)findViewById(R.id.image_test_series);
        text_testseries = (TextView)findViewById(R.id.text_testseries);
        text_combo_pack = (TextView)findViewById(R.id.text_combo_pack);

        //Solved Paper


        relative_solved_paper = (RelativeLayout)findViewById(R.id.relative_solved_paper);
        image_solved_papers = (ImageView)findViewById(R.id.image_solved_papers);
        text_solved = (TextView)findViewById(R.id.text_solved);
        text_combopack = (TextView)findViewById(R.id.text_combopack);

        header_back = (ImageView)findViewById(R.id.header_back);
        header_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back =new Intent(Product.this,Main2Activity.class);
                startActivity(back);
            }
        });

        relative_ncctest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ncc_test = new Intent(Product.this,WelcomeActivity.class);
                startActivity(ncc_test);


            }
        });

        relative_chapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chapter = new Intent(Product.this,NCC_Test_Series.class);
                startActivity(chapter);
            }
        });

        relative_test_series.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent test_series = new Intent(Product.this,ChapterWisw_QuestionActivity.class);
                startActivity(test_series);
            }
        });

        relative_solved_paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent solved_paper = new Intent(Product.this,SOlved_sampleActivity.class);
                startActivity(solved_paper);

            }
        });

    }
}
