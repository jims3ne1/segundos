package com.jimbocortes.segundos;
 
import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.text.format.DateFormat;

public class SegundosActivity extends Activity {
    
	private View mTextTimer;
	private View mButtonPlayPause;
	private View mButtonStop;
	private View mImageButtonSettings;
	private SharedPreferences mPrefs;
	private Editor mPrefEditor;
	private Handler mHandler;  
	private Calendar mCalendar; 
	private Format mDateFormat;
	
	private static long DELAY_IN_MILLESECONDS = 1000; 
	 
	private OnClickListener sButtonPlayPauseListener = new OnClickListener() 
	{ 	
		@Override
		public void onClick(View v) {
			String currentText = ((Button)mButtonPlayPause).getText().toString();
			String playText = getResources().getString(R.string.play);
			String pauseText = getResources().getString(R.string.pause);
			
			if (currentText == playText)
			{
				((Button)mButtonPlayPause).setText(R.string.pause);
				mButtonStop.setEnabled(true);
				mHandler.removeCallbacks(sUpdateTimer);
				mHandler.postDelayed(sUpdateTimer, DELAY_IN_MILLESECONDS);
			}
			else
			{
				((Button)mButtonPlayPause).setText(R.string.play);
				mHandler.removeCallbacks(sUpdateTimer);
			}
		}
	};
	
	private OnClickListener sButtonStopListener = new OnClickListener() 
	{ 	
		@Override
		public void onClick(View v) {
			mHandler.removeCallbacks(sUpdateTimer);
			((Button)mButtonPlayPause).setText(R.string.play);
			mButtonStop.setEnabled(false);
			resetCalendar();
		}
	}; 
	
	private OnClickListener sImageButtonListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent i = new Intent(getBaseContext(), SetPreferencesActivity.class);
			startActivity(i);
		}
	};
	
	private Runnable sUpdateTimer = new Runnable() {
		
		@Override
		public void run() {
			mCalendar.add(Calendar.SECOND, 1);
			((TextView)mTextTimer).setText(mDateFormat.format(mCalendar.getTime()));
			mHandler.postDelayed(this, DELAY_IN_MILLESECONDS);
		}
	};
 
	private void setDefaults()
    {
    	mTextTimer = findViewById(R.id.textTime);
    	mButtonPlayPause = findViewById(R.id.buttonPlayPause);
    	mButtonStop = findViewById(R.id.buttonStop);
    	mImageButtonSettings = findViewById(R.id.imageButtonSettings);
    	mHandler = new Handler();
    	mDateFormat = new SimpleDateFormat("HH:mm:ss");
    	resetCalendar();
    	
    	mButtonPlayPause.setOnClickListener(sButtonPlayPauseListener);
    	mButtonStop.setOnClickListener(sButtonStopListener);
    	mImageButtonSettings.setOnClickListener(sImageButtonListener);
    	mButtonStop.setEnabled(false);
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
    
    private void resetCalendar()
    {
    	if (mCalendar == null)
    		mCalendar = Calendar.getInstance();
    	
    	mCalendar.set(Calendar.HOUR_OF_DAY, 0);
    	mCalendar.set(Calendar.MINUTE, 0);
    	mCalendar.set(Calendar.SECOND, 0);
    	mCalendar.set(Calendar.MILLISECOND,0);
    	((TextView)mTextTimer).setText(R.string.time_default);
    	
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setDefaults();
        setPreferences();
    }
    
    @Override
    protected void onPause() 
    {
    	super.onPause();
    	
    }
    
    @Override
    protected void onResume() 
    {
    	super.onResume();
    }
    
    
}