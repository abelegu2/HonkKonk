package com.example.honkkonk;

import android.view.MotionEvent;
import android.view.ViewConfiguration;

public abstract class DoubleTapDetector {

	private static final int TIMEOUT = ViewConfiguration.getDoubleTapTimeout() + 100;
    private long mFirstDownTime = 0;
    private boolean mSeparateTouches = false;

    private void reset(long time) {
        mFirstDownTime = time;
        mSeparateTouches = false;
    }

    public boolean onDoubleTapEvent(MotionEvent event) {
        switch(event.getActionMasked()) {
        case MotionEvent.ACTION_DOWN:
            if(mFirstDownTime == 0 || event.getEventTime() - mFirstDownTime > TIMEOUT) 
                reset(event.getDownTime());
            break;
        case MotionEvent.ACTION_POINTER_UP:
        	mFirstDownTime = 0;
            break;
        case MotionEvent.ACTION_UP:
            if(!mSeparateTouches)
                mSeparateTouches = true;
            else if(event.getEventTime() - mFirstDownTime < TIMEOUT) {
            	onDoubleTap();
                mFirstDownTime = 0;
                return true;
            }
        }               

        return false;
    }

    public abstract void onDoubleTap();
}
