package com.tsa.nccapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tsa.nccapp.R;
import com.tsa.nccapp.models.ReportModel;

import java.util.ArrayList;

/**
 * Created by Akhil Tripathi on 24-01-2018.
 */

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ViewHolder> {

    private ArrayList<ReportModel> reportModels;
    private Context context;

    public ReportAdapter(ArrayList<ReportModel> reportModels, Context context) {
        this.reportModels = reportModels;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.report_card, parent, false);
        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final ReportModel reportModel = reportModels.get(position);

        holder.question.setText("Q."+(position+1)+": "+reportModel.getmQuestion());

        holder.optionTVA.setText(reportModel.getmOptionA());
        holder.optionTVB.setText(reportModel.getmOptionB());
        holder.optionTVC.setText(reportModel.getmOptionC());
        holder.optionTVD.setText(reportModel.getmOptionD());

        holder.answer.setText("Answer: "+reportModel.getmAnswer());

            if (reportModel.getmQStatus().equals("CORRECT")) {

                holder.root.setBackgroundResource(R.drawable.green_background);
                switch (reportModel.getmAttempted()) {
                    case "A":
                        holder.optionIVA.setVisibility(View.VISIBLE);
                        holder.optionTVA.setTextColor(context.getResources().getColor(R.color.Green));
                        holder.optionIVA.setImageResource(R.drawable.right_icon);
                        holder.optionTVB.setTextColor(context.getResources().getColor(R.color.black));
                        holder.optionIVB.setVisibility(View.INVISIBLE);
                        holder.optionTVC.setTextColor(context.getResources().getColor(R.color.black));
                        holder.optionIVC.setVisibility(View.INVISIBLE);
                        holder.optionTVD.setTextColor(context.getResources().getColor(R.color.black));
                        holder.optionIVD.setVisibility(View.INVISIBLE);
                        break;
                    case "B":
                        holder.optionIVB.setVisibility(View.VISIBLE);
                        holder.optionTVA.setTextColor(context.getResources().getColor(R.color.black));
                        holder.optionIVA.setVisibility(View.INVISIBLE);
                        holder.optionTVB.setTextColor(context.getResources().getColor(R.color.Green));
                        holder.optionIVB.setImageResource(R.drawable.right_icon);
                        holder.optionTVC.setTextColor(context.getResources().getColor(R.color.black));
                        holder.optionIVC.setVisibility(View.INVISIBLE);
                        holder.optionTVD.setTextColor(context.getResources().getColor(R.color.black));
                        holder.optionIVD.setVisibility(View.INVISIBLE);
                        break;
                    case "C":
                        holder.optionIVC.setVisibility(View.VISIBLE);
                        holder.optionTVA.setTextColor(context.getResources().getColor(R.color.black));
                        holder.optionIVA.setVisibility(View.INVISIBLE);
                        holder.optionTVB.setTextColor(context.getResources().getColor(R.color.black));
                        holder.optionIVB.setVisibility(View.INVISIBLE);
                        holder.optionTVC.setTextColor(context.getResources().getColor(R.color.Green));
                        holder.optionIVC.setImageResource(R.drawable.right_icon);
                        holder.optionTVD.setTextColor(context.getResources().getColor(R.color.black));
                        holder.optionIVD.setVisibility(View.INVISIBLE);
                        break;
                    case "D":
                        holder.optionIVD.setVisibility(View.VISIBLE);
                        holder.optionTVA.setTextColor(context.getResources().getColor(R.color.black));
                        holder.optionIVA.setVisibility(View.INVISIBLE);
                        holder.optionTVB.setTextColor(context.getResources().getColor(R.color.black));
                        holder.optionIVB.setVisibility(View.INVISIBLE);
                        holder.optionTVC.setTextColor(context.getResources().getColor(R.color.black));
                        holder.optionIVC.setVisibility(View.INVISIBLE);
                        holder.optionTVD.setTextColor(context.getResources().getColor(R.color.Green));
                        holder.optionIVD.setImageResource(R.drawable.right_icon);
                        break;
                }
            }
            else if(reportModel.getmQStatus().equals("INCORRECT")) {
                holder.root.setBackgroundResource(R.drawable.red_background);
                switch (reportModel.getmAttempted()) {
                    case "A":
                        holder.optionTVD.setTextColor(context.getResources().getColor(R.color.black));
                        holder.optionIVD.setVisibility(View.INVISIBLE);
                        holder.optionTVB.setTextColor(context.getResources().getColor(R.color.black));
                        holder.optionIVB.setVisibility(View.INVISIBLE);
                        holder.optionTVC.setTextColor(context.getResources().getColor(R.color.black));
                        holder.optionIVC.setVisibility(View.INVISIBLE);
                        holder.optionTVA.setTextColor(context.getResources().getColor(R.color.red));
                        holder.optionIVA.setImageResource(R.drawable.wrong_icon);
                        holder.optionIVA.setVisibility(View.VISIBLE);
                        break;
                    case "B":
                        holder.optionTVA.setTextColor(context.getResources().getColor(R.color.black));
                        holder.optionIVA.setVisibility(View.INVISIBLE);
                        holder.optionTVD.setTextColor(context.getResources().getColor(R.color.black));
                        holder.optionIVD.setVisibility(View.INVISIBLE);
                        holder.optionTVC.setTextColor(context.getResources().getColor(R.color.black));
                        holder.optionIVC.setVisibility(View.INVISIBLE);
                        holder.optionTVB.setTextColor(context.getResources().getColor(R.color.red));
                        holder.optionIVB.setImageResource(R.drawable.wrong_icon);
                        holder.optionIVB.setVisibility(View.VISIBLE);
                        break;
                    case "C":
                        holder.optionTVA.setTextColor(context.getResources().getColor(R.color.black));
                        holder.optionIVA.setVisibility(View.INVISIBLE);
                        holder.optionTVB.setTextColor(context.getResources().getColor(R.color.black));
                        holder.optionIVB.setVisibility(View.INVISIBLE);
                        holder.optionTVD.setTextColor(context.getResources().getColor(R.color.black));
                        holder.optionIVD.setVisibility(View.INVISIBLE);
                        holder.optionTVC.setTextColor(context.getResources().getColor(R.color.red));
                        holder.optionIVC.setImageResource(R.drawable.wrong_icon);
                        holder.optionIVC.setVisibility(View.VISIBLE);
                        break;
                    case "D":
                        holder.optionTVA.setTextColor(context.getResources().getColor(R.color.black));
                        holder.optionIVA.setVisibility(View.INVISIBLE);
                        holder.optionTVB.setTextColor(context.getResources().getColor(R.color.black));
                        holder.optionIVB.setVisibility(View.INVISIBLE);
                        holder.optionTVC.setTextColor(context.getResources().getColor(R.color.black));
                        holder.optionIVC.setVisibility(View.INVISIBLE);
                        holder.optionTVD.setTextColor(context.getResources().getColor(R.color.red));
                        holder.optionIVD.setImageResource(R.drawable.wrong_icon);
                        holder.optionIVD.setVisibility(View.VISIBLE);
                        break;
                }
            }
                else
                {
                            holder.root.setBackgroundResource(R.drawable.empty_boarder);

                            holder.optionTVD.setTextColor(context.getResources().getColor(R.color.black));
                            holder.optionIVD.setVisibility(View.INVISIBLE);
                            holder.optionTVB.setTextColor(context.getResources().getColor(R.color.black));
                            holder.optionIVB.setVisibility(View.INVISIBLE);
                            holder.optionTVC.setTextColor(context.getResources().getColor(R.color.black));
                            holder.optionIVC.setVisibility(View.INVISIBLE);
                            holder.optionTVA.setTextColor(context.getResources().getColor(R.color.black));
                            holder.optionIVA.setVisibility(View.INVISIBLE);
                    }
                }

    @Override
    public int getItemCount() {
        return reportModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView optionTVA;
        TextView optionTVB;
        TextView optionTVC;
        TextView optionTVD;

        TextView optTVA;
        TextView optTVB;
        TextView optTVC;
        TextView optTVD;

        ImageView optionIVA;
        ImageView optionIVB;
        ImageView optionIVC;
        ImageView optionIVD;

        LinearLayout linearLayoutA;
        LinearLayout linearLayoutB;
        LinearLayout linearLayoutC;
        LinearLayout linearLayoutD;

        LinearLayout root;
        TextView question;

        TextView answer;

        public ViewHolder(View view) {
            super(view);

            root = view.findViewById(R.id.root);
            question=view.findViewById(R.id.question);

            optionTVA = view.findViewById(R.id.opt_a_text);
            optionTVB = view.findViewById(R.id.opt_b_text);
            optionTVC = view.findViewById(R.id.opt_c_text);
            optionTVD = view.findViewById(R.id.opt_d_text);

            optTVA = view.findViewById(R.id.opt_a);
            optTVB = view.findViewById(R.id.opt_b);
            optTVC = view.findViewById(R.id.opt_c);
            optTVD = view.findViewById(R.id.opt_d);

            optionIVA = view.findViewById(R.id.image_a);
            optionIVB = view.findViewById(R.id.image_b);
            optionIVC = view.findViewById(R.id.image_c);
            optionIVD = view.findViewById(R.id.image_d);

            linearLayoutA = view.findViewById(R.id.lay_a);
            linearLayoutB = view.findViewById(R.id.lay_b);
            linearLayoutC = view.findViewById(R.id.lay_c);
            linearLayoutD = view.findViewById(R.id.lay_d);

            answer=view.findViewById(R.id.answer);

        }
    }
}
