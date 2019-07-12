package com.tsa.nccapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tsa.nccapp.R;
import com.tsa.nccapp.View_product_Activity;
import com.tsa.nccapp.models.ChapterListModel;
import com.tsa.nccapp.models.Product_view;

import java.util.ArrayList;

public class ViewProductAdapter extends RecyclerView.Adapter<ViewProductAdapter.ItemViewHolder>{
    public LayoutInflater inflater;
    Context context;


    ArrayList<Product_view> product_list;



//    public ViewProductAdapter(ArrayList<Product_view> product_view, Context context) {
//        this.product_view = product_view;
//        this.context = context;
//        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }

    public ViewProductAdapter(Context context, ArrayList<Product_view> product_list) {
        this.product_list = product_list;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }


    @Override
    public ViewProductAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_product, parent, false);
        ViewProductAdapter.ItemViewHolder viewHolder = new ViewProductAdapter.ItemViewHolder(v);
        return viewHolder;

        }

    @Override
    public void onBindViewHolder(ViewProductAdapter.ItemViewHolder itemViewHolder, int position) {


        itemViewHolder.text_user_sub.setText(product_list.get(position).getSubProductName());
        itemViewHolder.text_certificate_type.setText(product_list.get(position).getCert_type());
        itemViewHolder.text_amt_rs.setText(product_list.get(position).getAmount_paid());
        itemViewHolder.text_dates.setText(product_list.get(position).getPurchased_date());
        itemViewHolder.text_expires_date.setText(product_list.get(position).getExpiry_date());


      // itemViewHolder.text_expires_date.setText(model.getExpiry_date());
//        itemViewHolder.text_certificate_type.setText(model.getCert_type());
//        itemViewHolder.text_amt_rs.setText(model.getAmount_paid());
//        itemViewHolder.text_dates.setText(model.getPurchased_date());
// getPurchased_date

    }

    @Override
    public int getItemCount()
    {
        return  product_list.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView text_function_no,text_user_sub,text_certificate_type,text_amt_rs,text_dates,text_expires_date;
//        TextView text_view_exams;


        public ItemViewHolder(View itemView) {
            super(itemView);
            text_function_no = (TextView)itemView.findViewById(R.id.text_function_no);
            text_user_sub = (TextView)itemView.findViewById(R.id.text_user_sub);
            text_certificate_type = (TextView)itemView.findViewById(R.id.text_certificate_type);
            text_amt_rs = (TextView)itemView.findViewById(R.id.text_amt_rs);
            text_dates = (TextView)itemView.findViewById(R.id.text_dates);
            text_expires_date = (TextView)itemView.findViewById(R.id.text_expires_date);

//            text_view_exams = (TextView)itemView.findViewById(R.id.text_view_exams);

            }
    }


}
