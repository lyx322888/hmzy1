package com.huiminsheng.app.unify;

import android.animation.Animator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;

/**
 * Created by Lyx on 2018/1/22.
 */

public class FooterBehaviorDependAppBar extends CoordinatorLayout.Behavior<View> {
    private static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();
    private int sinceDirectionChange;
    private boolean ishide = false;
    public FooterBehaviorDependAppBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    //1.判断滑动的方向 我们需要垂直滑动

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int nestedScrollAxes, int type) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;

    }

    //2.根据滑动的距离显示和隐藏footer view
    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
       if (type==ViewCompat.TYPE_TOUCH){
           if (dy > 0 && sinceDirectionChange < 0 || dy < 0 && sinceDirectionChange > 0) {
               sinceDirectionChange = 0;
           }
           sinceDirectionChange += dy;
           if (sinceDirectionChange > child.getHeight() ) {
               if (!ishide){
                   hide(child);
               }
           } else if (sinceDirectionChange < 0 ) {
               if (ishide) {
                   show(child);
               }
           }
       }else {
           if (dy>0){
               if (!ishide){
                   hide(child);
               }
           }
           if (dy<0){
               if (ishide) {
                   show(child);
               }
           }
       }
    }
    private void hide(final View view) {
        ViewPropertyAnimator animator = view.animate().translationY(0).setInterpolator(INTERPOLATOR).setDuration(200);
        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                    ishide = true;
            }

            @Override
            public void onAnimationEnd(Animator animator) {
            }

            @Override
            public void onAnimationCancel(Animator animator) {
//                show(view);
            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animator.start();
    }
    private void show(final View view) {
        ViewPropertyAnimator animator = view.animate().translationY(view.getHeight()).setInterpolator(INTERPOLATOR).setDuration(200);
        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                ishide = false;
            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animator.start();

    }
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof AppBarLayout;
    }


    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        float translationY = Math.abs(dependency.getTranslationY());
        child.setTranslationY(translationY);
        return true;
    }
}
