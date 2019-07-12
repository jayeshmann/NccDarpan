package com.tsa.nccapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tsa.nccapp.OtherExamActivity;
import com.tsa.nccapp.R;
import com.tsa.nccapp.models.OtherExamCatModet;
import com.tsa.nccapp.utils.TimeConverter;

import java.util.ArrayList;

/**
 * Created by Akhil Tripathi on 30-06-2017.
 */

public class OtherExamListAdapter extends RecyclerView.Adapter<OtherExamListAdapter.ViewHolder> {

    private ArrayList<OtherExamCatModet> otherExamCatModets;
    private Context context;

    public OtherExamListAdapter(ArrayList<OtherExamCatModet> otherExamCatModets, Context context) {
        this.otherExamCatModets = otherExamCatModets;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.other_card, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {

        final OtherExamCatModet otherExamCatModel=otherExamCatModets.get(position);
        holder.chapterName.setText(otherExamCatModel.getExamName());
        holder.totalMarks.setText("Marks:"+otherExamCatModel.getExamMarks());
        holder.totalTime.setText("Duration:"+ TimeConverter.getDate(Integer.parseInt(otherExamCatModel.getExamDuration())*1000));

        holder.startExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, OtherExamActivity.class);
                intent.putExtra("id",otherExamCatModel.getExamId());
                intent.putExtra("moc_no",otherExamCatModel.getExamName());
                intent.putExtra("exm_duration",otherExamCatModel.getExamDuration());
                intent.putExtra("neg_marks",otherExamCatModel.getmNegMarking());

                context.startActivity(intent);
                ((Activity)context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return otherExamCatModets.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView chapterName;
        TextView totalTime;
        TextView totalMarks;
        Button startExam;
        LinearLayout root;

        public  ViewHolder(View view)
        {
            super(view);
            chapterName=view.findViewById(R.id.chapter_name);
            startExam=view.findViewById(R.id.start_exam);
            root=view.findViewById(R.id.root1);
            totalTime=view.findViewById(R.id.time_tv);
            totalMarks=view.findViewById(R.id.total_questions);
        }
    }
}
