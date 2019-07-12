package com.tsa.nccapp.adapter;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tsa.nccapp.ChapterListActivity;
import com.tsa.nccapp.ChaptersActivity;
import com.tsa.nccapp.R;
import com.tsa.nccapp.models.ChapterListModel;
import com.tsa.nccapp.utils.GLOBAL;

import java.util.ArrayList;

/**
 * Created by Akhil Tripathi on 30-06-2017.
 */

public class ChaptersListAdapter extends RecyclerView.Adapter<ChaptersListAdapter.ViewHolder> {

    private ArrayList<ChapterListModel> chapterListModels;
//    private ArrayList<ChapterListModel> chapterListModels;
    private Context context;

    public ChaptersListAdapter(ArrayList<ChapterListModel> chapterListModels, Context context) {
        this.chapterListModels = chapterListModels;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chapter_card, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {

        final ChapterListModel chapterListModel=chapterListModels.get(position);
        holder.chapterName.setText(chapterListModel.getChapterName());
        holder.chapterName.setTextSize(14);
        holder.chapterNumber.setText(""+(position+1));

        holder.chapterName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context, ChaptersActivity.class);
                intent.putExtra("id",chapterListModel.getId());
                intent.putExtra("c_no",chapterListModel.getChapter());
                intent.putExtra("c_name",chapterListModel.getChapterName());
                intent.putExtra("sub_id",chapterListModel.getSubjectId());
                intent.putExtra("sub_name",chapterListModel.getSubjectName());

                context.startActivity(intent);
                ((Activity)context).finish();

            }
        });
    }

    @Override
    public int getItemCount() {
        return chapterListModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView chapterName;
        TextView chapterNumber;
        LinearLayout root;

        public  ViewHolder(View view)
        {
            super(view);
            chapterName=view.findViewById(R.id.chapter_name);
            chapterNumber=view.findViewById(R.id.cap_no);
            root=view.findViewById(R.id.root1);
        }
    }
}
