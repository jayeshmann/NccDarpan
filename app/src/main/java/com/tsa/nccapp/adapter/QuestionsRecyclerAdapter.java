package com.tsa.nccapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tsa.nccapp.DetailsActivity;
import com.tsa.nccapp.R;
import com.tsa.nccapp.models.FAQModel;

import java.util.ArrayList;


/**
 * Created by Wajahat Karim on 2/12/2017.
 */

public class QuestionsRecyclerAdapter extends RecyclerView.Adapter<QuestionsRecyclerAdapter.QAViewHolder> {

    public Context context;
    public ArrayList<FAQModel> faqModelArrayList;

    public QuestionsRecyclerAdapter(Context context, ArrayList<FAQModel> faqModelArrayList) {
        this.context = context;
        this.faqModelArrayList=faqModelArrayList;
    }

    @Override
    public QAViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.qa_item_layout, parent, false);
        return new QAViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final QAViewHolder holder,final  int position) {
        holder.questionTv.setText(faqModelArrayList.get(position).getQuestion());
        holder.layoutQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentapiVersion = android.os.Build.VERSION.SDK_INT;
                if (currentapiVersion >= android.os.Build.VERSION_CODES.LOLLIPOP)
                {
                    Intent intent = new Intent(context, DetailsActivity.class);

                    ////////////////////////////////////////////////////////////////////////////////
                    intent.putExtra("id",faqModelArrayList.get(position).getFaqID());
                    intent.putExtra("question",faqModelArrayList.get(position).getQuestion());
                    intent.putExtra("answer",faqModelArrayList.get(position).getAns());
                    ////////////////////////////////////////////////////////////////////////////////

                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation((Activity)context, holder.layoutQuestion, "questionTransition");
                   context.startActivity(intent, options.toBundle());
                }
                else {
                    Intent ii = new Intent(context, DetailsActivity.class);
                    context.startActivity(ii);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return faqModelArrayList.size();
    }

    public class QAViewHolder extends RecyclerView.ViewHolder
    {

        LinearLayout layoutQuestion;
        TextView questionTv;

        public QAViewHolder(View itemView) {
            super(itemView);
            layoutQuestion=itemView.findViewById(R.id.layoutQuestion_item);
            questionTv=itemView.findViewById(R.id.question_tv);
        }
    }
}
