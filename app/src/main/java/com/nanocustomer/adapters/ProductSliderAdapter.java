package com.nanocustomer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.PagerAdapter;

import com.nanocustomer.R;
import com.nanocustomer.databinding.SliderRow2Binding;
import com.nanocustomer.databinding.SliderRowBinding;
import com.nanocustomer.models.GalleryModel;
import com.nanocustomer.models.SliderDataModel;

import java.util.List;

public class ProductSliderAdapter extends PagerAdapter {
    private List<GalleryModel> list ;
    private Context context;
    private LayoutInflater inflater;

    public ProductSliderAdapter(List<GalleryModel> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        SliderRow2Binding binding = DataBindingUtil.inflate(inflater, R.layout.slider_row2,container,false);
        binding.setPhoto(list.get(position).getImage());
        container.addView(binding.getRoot());
        return binding.getRoot();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
