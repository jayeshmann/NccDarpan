package com.tsa.nccapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tsa.nccapp.Exam_CardActivity;
import com.tsa.nccapp.InstructionActivity;
import com.tsa.nccapp.R;
import com.tsa.nccapp.models.Exam_listModel;

import java.util.ArrayList;


public class Exam_ViewAdapter extends RecyclerView.Adapter<Exam_ViewAdapter.ViewHolder> {
    ArrayList<Exam_listModel> exam_list ;
    public LayoutInflater inflater;
    Context context;

    public Exam_ViewAdapter(Context applicationContext, ArrayList<Exam_listModel> exam_list) {
        this.exam_list = exam_list;
        this.context = applicationContext;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }

    @Override
    public Exam_ViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_exam_detail, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

        }

    @Override
    public void onBindViewHolder(Exam_ViewAdapter.ViewHolder itemViewHolder, int position) {

        itemViewHolder.text_mini_exam.setText(exam_list.get(position).getExam_name());
        itemViewHolder.text_certificate.setText(exam_list.get(position).getExam_cert());
        itemViewHolder.total_questions.setText(exam_list.get(position).getTotal_question());
        itemViewHolder.text_exam_timing.setText(exam_list.get(position).getExam_hours());

        itemViewHolder.btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, InstructionActivity.class);
                context.startActivity(i);

            }
        });

        }

    @Override
    public int getItemCount() {
        return exam_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView text_mini_exam,text_certificate,total_questions,text_exam_timing;
        Button btn_start,btn_seeresult;
        public ViewHolder(View itemView) {
            super(itemView);

            text_mini_exam = (TextView)itemView.findViewById(R.id.text_mimi_exam);
            text_certificate = (TextView)itemView.findViewById(R.id.text_certificate);
            total_questions = (TextView)itemView.findViewById(R.id.total_questions);
            text_exam_timing = (TextView)itemView.findViewById(R.id.text_exam_timing);

            btn_start = (Button)itemView.findViewById(R.id.btn_start);
            btn_seeresult = (Button)itemView.findViewById(R.id.btn_seeresult);



        }
    }
}
