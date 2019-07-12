package com.tsa.nccapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tsa.nccapp.Checkout;
import com.tsa.nccapp.R;

/**
 * Created by hp on 17-Jan-2019.
 */

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.ItemViewHolder> {

    public LayoutInflater inflater;
    Context context;

    public CheckoutAdapter(Context context) {
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public CheckoutAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_subproduct, parent, false);
        ItemViewHolder viewHolder = new ItemViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(CheckoutAdapter.ItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView text_mcq,text_subjective;
        public ItemViewHolder(View itemView) {
            super(itemView);
            text_mcq = (TextView)itemView.findViewById(R.id.text_mcq);
            text_subjective = (TextView)itemView.findViewById(R.id.text_subjective);



        }
    }
}
