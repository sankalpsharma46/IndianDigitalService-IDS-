package com.indian.digitalservices.widget;
import android.content.Context;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class SwipeableViewPager extends ViewPager {

    private static boolean ALWAYS_IGNORE_SWIPE = false;

    public SwipeableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (swipeEnabled()) {
            return super.onTouchEvent(event);
        }

        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (swipeEnabled()) {
            return super.onInterceptTouchEvent(event);
        }

        return false;
    }

    boolean swipeEnabled(){
        if (getAdapter().getCount() == 1 || ALWAYS_IGNORE_SWIPE)
            return false;
        else
            return true;
    }
}