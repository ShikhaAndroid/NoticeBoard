package com.rajinder.noticeboard.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.rajinder.noticeboard.UI.ViewHolder.FilterCategoryViewHolder;
import com.rajinder.noticeboard.models.Category.Category;
import java.util.List;

public class FilterCategoryAdapter extends RecyclerView.Adapter<FilterCategoryViewHolder>{

    private Context context;
    private List<Category> categoryList;

    public FilterCategoryAdapter(List<Category> categories) {
        this.categoryList=categories;
    }

    @NonNull
    @Override
    public FilterCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
//        return FilterCategoryViewHolder.getHolderInstance(parent);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull FilterCategoryViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

}
