package com.tsa.nccapp.interfaces;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Akhil Tripathi on 19-12-2017.
 */

public class OnSwipeTouchListener implements View.OnTouchListener {
    // Fields:
    /** Whether a swipe motion has been detected */
    protected boolean isSwipeDetected = false;
    private final GestureDetector gestureDetector;


    // Constructors:
    public OnSwipeTouchListener (Context ctx) {
        gestureDetector = new GestureDetector(ctx, new GestureListener());
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int HORIZONTAL_SWIPE_THRESHOLD = 0;
        private static final int HORIZONTAL_SWIPE_VELOCITY_THRESHOLD = 0;
        private static final int VERTICAL_SWIPE_THRESHOLD = 100;
        private static final int VERTICAL_SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {    // More of a horizontal movement
                    if (Math.abs(diffX) > HORIZONTAL_SWIPE_THRESHOLD && Math.abs(velocityX) > HORIZONTAL_SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            isSwipeDetected = true;
                            onSwipeRight();
                        } else {
                            isSwipeDetected = true;
                            onSwipeLeft();
                        }
                    }
                    result = true;
                } else {    // Vertical movement
                    if (Math.abs(diffY) > VERTICAL_SWIPE_THRESHOLD && Math.abs(velocityY) > VERTICAL_SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            isSwipeDetected = true;
                            onSwipeBottom();
                        } else {
                            isSwipeDetected = true;
                            onSwipeTop();
                        }
                    }
                    result = true;
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            return result;
        }
    }

    public void onSwipeRight() {
    }

    public void onSwipeLeft() {
    }

    public void onSwipeTop() {
    }

    public void onSwipeBottom() {
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        boolean isSwipe = gestureDetector.onTouchEvent(event);

        return isSwipe;
    }
}