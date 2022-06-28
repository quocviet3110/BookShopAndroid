package com.example.nhom15bookshop.models;

import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class PaginationScrollListener extends  RecyclerView.OnScrollListener {
    private GridLayoutManager gridLayoutManager;

    public PaginationScrollListener(GridLayoutManager gridLayoutManager) {
        this.gridLayoutManager = gridLayoutManager;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int visibleitemCount = gridLayoutManager.getChildCount();
        int totalItemCounnt = gridLayoutManager.getItemCount();
        int firstCompletelyVisibleItemPosition = gridLayoutManager.findFirstCompletelyVisibleItemPosition();
        if(isLoading() || isLastPage()){
            return;
        }
        if(firstCompletelyVisibleItemPosition >= 0 && (visibleitemCount +firstCompletelyVisibleItemPosition)> totalItemCounnt){
            loadMoreItem();
        }
    }
    public abstract void loadMoreItem();
    public  abstract  boolean isLoading();
    public  abstract boolean isLastPage();
}
