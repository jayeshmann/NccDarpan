package com.tsa.nccapp.adapter;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tsa.nccapp.ChaptersActivity;
import com.tsa.nccapp.ExamActivity;
import com.tsa.nccapp.R;
import com.tsa.nccapp.models.ChapterListModel;
import com.tsa.nccapp.models.MockTestModel;

import java.util.ArrayList;

/**
 * Created by Akhil Tripathi on 30-06-2017.
 */

public class MockTestListAdapter extends RecyclerView.Adapter<MockTestListAdapter.ViewHolder> {

    private ArrayList<MockTestModel> mockTestModels;
    private Context context;

    public MockTestListAdapter(ArrayList<MockTestModel> mockTestModels, Context context) {
        this.mockTestModels = mockTestModels;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chapter_card1, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {

        final MockTestModel mockTestModel=mockTestModels.get(position);
        holder.chapterName.setText(mockTestModel.getTestName());
        holder.startExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ExamActivity.class);
                intent.putExtra("id",mockTestModel.getId());
                intent.putExtra("moc_no",mockTestModel.getTestName());

                context.startActivity(intent);
                ((Activity)context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mockTestModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView chapterName;
        Button startExam;
        LinearLayout root;

        public  ViewHolder(View view)
        {
            super(view);
            chapterName=view.findViewById(R.id.chapter_name);
            startExam=view.findViewById(R.id.start_exam);
            root=view.findViewById(R.id.root1);
        }
    }
}
