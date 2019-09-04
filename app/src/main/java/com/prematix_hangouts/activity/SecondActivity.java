package com.prematix_hangouts.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.prematix_hangouts.R;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<ProductModel> productModels = new ArrayList<>();
    CategoryProductAdapter categoryProductAdapter;
    GridLayoutManager gridLayoutManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondlayout);
        recyclerView = (RecyclerView)findViewById(R.id.recycler);
        for (int i=0;i<=10;i++){
            productModels.add(new ProductModel(R.drawable.banner_1,"Book"));
        }

        categoryProductAdapter =  new CategoryProductAdapter(this,productModels);
        recyclerView.setAdapter(categoryProductAdapter);
        gridLayoutManager = new GridLayoutManager(this, 5, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
    }
}
