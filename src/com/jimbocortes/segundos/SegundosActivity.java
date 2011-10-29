package com.jimbocortes.segundos;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SegundosActivity extends Activity {
    
	private View mTextTimer;
	private View mButtonPlayPause;
	private View mButtonStop;
	private SharedPreferences mPrefs;
	private Editor mPrefEditor;
	 
	private OnClickListener sButtonPlayPauseListener = new OnClickListener() 
	{ 	
		@Override
		public void onClick(View v) {
			String currentText = ((Button)mButtonPlayPause).getText().toString();
			String playText = getResources().getString(R.string.play);
			String pauseText = getResources().getString(R.string.pause);
			
			if (currentText == playText)
				((Button)mButtonPlayPause).setText(R.string.pause);
			else
				((Button)mButtonPlayPause).setText(R.string.play);
		}
	};
	
	private OnClickListener sButtonStopListener = new OnClickListener() 
	{ 	
		@Override
		public void onClick(View v) {
			
		}
	}; 
 
	private void setMembers()
    {
    	mTextTimer = findViewById(R.id.textTime);
    	mButtonPlayPause = findViewById(R.id.buttonPlayPause);
    	mButtonStop = findViewById(R.id.buttonStop);
    	
    	mButtonPlayPause.setOnClickListener(sButtonPlayPauseListener);
    }
    
    private void setPreferences()
    {
    	mPrefs = getSharedPreferences(PreferenceConstants.PREFERENCE_NAME, MODE_PRIVATE);
    	if (mPrefs != null)
    	{
    		mPrefEditor = mPrefs.edit();
    		mPrefEditor.putInt(PreferenceConstants.PREFERENCE_NO_OF_SETS, PreferenceConstants.DEFAULT_PREFERENCE_NO_OF_SETS);
    		mPrefEditor.putInt(PreferenceConstants.PREFERENCE_COUNTDOWN_TIME, PreferenceConstants.DEFAULT_PREFERENCE_COUNTDOWN_TIME);
    		mPrefEditor.putInt(PreferenceConstants.PREFERENCE_LOW_INTERVAL_TIME, PreferenceConstants.DEFAULT_PREFERENCE_LOW_INTERVAL_TIME);
    		mPrefEditor.putInt(PreferenceConstants.PREFERENCE_HIGH_INTERVAL_TIME, PreferenceConstants.DEFAULT_PREFERENCE_HIGH_INTERVAL_TIME);
    	}
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setMembers();
        setPreferences();
    }
}