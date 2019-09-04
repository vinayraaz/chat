package com.prematix_hangouts.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.prematix_hangouts.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductHome extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    SliderLayout sliderLayout;
    HashMap<String,Integer> Hash_file_maps ;
    RecyclerView recyclerView;
    List<ProductModel> productModels = new ArrayList<>();
    CategoryProductAdapter categoryProductAdapter;
    GridLayoutManager gridLayoutManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.producthome);

        Hash_file_maps = new HashMap<String, Integer>();

        sliderLayout = (SliderLayout)findViewById(R.id.slider);

        Hash_file_maps.put("Task1", R.drawable.banner_1);
        Hash_file_maps.put("Task2", R.drawable.banner_1);
        Hash_file_maps.put("Task3", R.drawable.banner_1);
        Hash_file_maps.put("Task4", R.drawable.banner_1);
        Hash_file_maps.put("Task5", R.drawable.banner_1);
        recyclerView = (RecyclerView)findViewById(R.id.recycler);

        for(String name : Hash_file_maps.keySet()){

            TextSliderView textSliderView = new TextSliderView(ProductHome.this);
            textSliderView
                    // .description(name)
                    .image(Hash_file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);
            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(3000);
        sliderLayout.addOnPageChangeListener(this);

        for (int i=0;i<=10;i++){
            productModels.add(new ProductModel(R.drawable.banner_1,"Pen"));
        }
        categoryProductAdapter =  new CategoryProductAdapter(this,productModels);
        recyclerView.setAdapter(categoryProductAdapter);
        gridLayoutManager = new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
