package com.tsa.nccapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tsa.nccapp.R;
import com.tsa.nccapp.models.ResultModel;

import java.util.ArrayList;

/**
 * Created by Akhil Tripathi on 30-06-2017.
 */

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {

    private ArrayList<ResultModel> resultModels;
    private Context context;

    public ResultAdapter(ArrayList<ResultModel> resultModels, Context context) {
        this.resultModels = resultModels;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.result_card, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {

        final ResultModel resultModel=resultModels.get(position);
        holder.examDate.setText(resultModel.getExamDate());
        holder.totalQuestion.setText(resultModel.getNoOfQuestions());
        holder.currect.setText(resultModel.getNoOFCurrect());
        holder.total_marks.setText(resultModel.getMarksOpt());
        if(position%2==0)
        holder.root.setBackgroundResource(R.drawable.blue_background);
        else {
            holder.root.setBackgroundResource(R.drawable.org_background);
            holder.examDate.setTextColor(context.getResources().getColor(R.color.black));
            holder.totalQuestion.setTextColor(context.getResources().getColor(R.color.black));
            holder.currect.setTextColor(context.getResources().getColor(R.color.black));
            holder.total_marks.setTextColor(context.getResources().getColor(R.color.black));
        }
    }

    @Override
    public int getItemCount() {
        return resultModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView examDate;
        TextView totalQuestion;
        TextView currect;
        TextView total_marks;
        LinearLayout root;
        
        public  ViewHolder(View view)
        {        
             super(view);
             examDate=view.findViewById(R.id.exam_date);
             totalQuestion=view.findViewById(R.id.total_questions);
             currect=view.findViewById(R.id.currect);
             total_marks=view.findViewById(R.id.total_marks);
             root=view.findViewById(R.id.root);
        }
    }
}
