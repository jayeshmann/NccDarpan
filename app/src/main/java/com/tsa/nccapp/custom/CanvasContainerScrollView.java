package com.tsa.nccapp.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by Akhil Tripathi on 12-01-2018.
 */

public class CanvasContainerScrollView extends ScrollView {
    public boolean enableScrollView = true;

    public CanvasContainerScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        android.util.Log.v ("scrollview", "touched");
        if (enableScrollView) {
            return super.onTouchEvent(event);
        }

        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (enableScrollView) {
            return super.onInterceptTouchEvent(event);
        }
        return false;
    }
}
