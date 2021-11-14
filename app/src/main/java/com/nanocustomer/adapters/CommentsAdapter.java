package com.nanocustomer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.nanocustomer.R;
import com.nanocustomer.databinding.CommentRowBinding;
import com.nanocustomer.databinding.SearchRowBinding;
import com.nanocustomer.models.CommentModel;
import com.nanocustomer.models.ProductModel;
import com.nanocustomer.ui.activity_home.fragments.Fragment_Search;

import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.MyHolder> {

    private List<CommentModel> list;
    private Context context;

    public CommentsAdapter(List<CommentModel> list, Context context) {
        this.list = list;
        this.context = context;


    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CommentRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.comment_row, parent, false);
        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int position) {
        CommentModel model = list.get(position);
        holder.binding.setModel(model);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        private CommentRowBinding binding;

        public MyHolder(CommentRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


        }


    }
}
