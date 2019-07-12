package com.tsa.nccapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tsa.nccapp.R;
import com.tsa.nccapp.database.NCCDarpanDB;
import com.tsa.nccapp.models.NotificationModel;

import java.util.ArrayList;

/**
 * Created by Akhil Tripathi on 24-01-2018.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private ArrayList<NotificationModel> notificationModels;
    private Context context;

    public NotificationAdapter(ArrayList<NotificationModel> notificationModels, Context context) {
        this.notificationModels = notificationModels;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_card_test, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.e("NOTIFICATION", notificationModels.toString());

        final NotificationModel notificationModel = notificationModels.get(position);
        holder.title.setText(notificationModel.getHead());
        holder.date.setText(notificationModel.getDate());
        holder.des.setText(notificationModel.getName());

        if (notificationModel.getImgname().equals(""))
            Glide.with(context).load("https://www.tsassessors.com/sanya-shakti/notifi_banner.png").into(holder.image);
        else
            Glide.with(context).load(notificationModel.getImgname()).into(holder.image);

        holder.image.setImageResource(R.drawable.profile_bg);

        holder.mainRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /*  notificationModel.setView("YES");
                new AsyncTask<Void, Void, Integer>() {
                    @Override
                    protected Integer doInBackground(Void... params) {
                        NCCDarpanDB
                                .getInstance(context)
                                .getNccDarpanDao()
                                .updateNotification(notificationModel);
                        return 0;
                    }
                }.execute();*/
                Intent viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse(notificationModel.getUrl()));
                context.startActivity(viewIntent);
            }
        });

        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.feb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationModel.setWish("YES");
              /*  new AsyncTask<Void, Void, Integer>() {
                    @Override
                    protected Integer doInBackground(Void... params) {
                        NCCDarpanDB
                                .getInstance(context)
                                .getNccDarpanDao()
                                .updateNotification(notificationModel);
                        return 0;
                    }
                }.execute();*/
            }
        });

        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, notificationModel.getUrl());
                context.startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });
    }

    @Override
    public int getItemCount() {
        return notificationModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;

        TextView title;
        TextView date;
        TextView des;

        ImageView home;
        ImageView like;
        ImageView share;
        ImageView feb;

        LinearLayout mainRoot;

        public ViewHolder(View view) {
            super(view);

            image = view.findViewById(R.id.image);

            title = view.findViewById(R.id.title);
            date = view.findViewById(R.id.date);
            des = view.findViewById(R.id.des);

            home = view.findViewById(R.id.home);
            like = view.findViewById(R.id.like);
            share = view.findViewById(R.id.share);
            feb = view.findViewById(R.id.feb);

            mainRoot=view.findViewById(R.id.main_root);
        }
    }
}
