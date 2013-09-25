package com.example.honkkonk;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.view.MotionEvent;

public class HonkActivity extends Activity {

	MediaPlayer myMediaPlayer;
	
	DoubleTapDetector doubleTapListener = new DoubleTapDetector() {
        @Override
        public void onDoubleTap() {
        	myMediaPlayer.start();
        }        
    };
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_honk);
		myMediaPlayer = MediaPlayer.create(HonkActivity.this, R.raw.honkhonk);
	}
	
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(doubleTapListener.onDoubleTapEvent(event))
            return true;
        return super.onTouchEvent(event);
    }    
	

}
