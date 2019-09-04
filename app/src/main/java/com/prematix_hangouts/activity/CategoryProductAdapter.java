package com.prematix_hangouts.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.prematix_hangouts.R;

import java.util.ArrayList;
import java.util.List;

class CategoryProductAdapter extends RecyclerView.Adapter<CategoryProductAdapter.ViewHolder> {
    Context context;
    List<ProductModel> productModels = new ArrayList<>();
    public CategoryProductAdapter(Context context, List<ProductModel> productModels) {
        this.context = context;
        this.productModels = productModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_list_details, viewGroup, false);
        return new CategoryProductAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.textView.setText(productModels.get(position).getProductName());
        holder.Image.setBackgroundResource( productModels.get(position).productImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2= new Intent(context,SecondActivity.class);
                context.startActivity(i2);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView Image;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Image = (ImageView)itemView.findViewById(R.id.image1);
            textView = (TextView)itemView.findViewById(R.id.text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
