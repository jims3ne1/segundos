package com.jimbocortes.segundos;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.widget.Toast;

public class SetPreferencesActivity extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getPreferenceManager().setSharedPreferencesMode(MODE_PRIVATE);
		getPreferenceManager().setSharedPreferencesName(PreferenceConstants.PREFERENCE_NAME);
		
		addPreferencesFromResource(R.xml.preferences);
		Toast t = Toast.makeText(getBaseContext(), "dd", 1000);
		
		PreferenceScreen controlConfig = (PreferenceScreen)getPreferenceManager().findPreference("s");
		setPreferenceScreen(controlConfig);
	}

}
