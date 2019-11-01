package com.huiminsheng.app.utils;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
    private int spanCount; //列数
    private int spacing; //间隔
    private boolean includeEdge; //是否包含边缘

    public GridSpacingItemDecoration( int spanCount,int spacing,boolean includeEdge) {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

        //这里是关键，需要根据你有几列来判断
        int position = parent.getChildAdapterPosition(view); // item position
        int column = position % spanCount; // item column
        if (column==1){
            outRect.left = spacing/2;
            outRect.right = spacing;
        }else {
            outRect.left = spacing;
            outRect.right = spacing/2;
        }
        outRect.bottom = spacing;

    }
}
