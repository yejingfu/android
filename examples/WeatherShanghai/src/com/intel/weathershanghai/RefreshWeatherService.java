package com.intel.weathershanghai;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class RefreshWeatherService extends IntentService {

	public RefreshWeatherService() {
		super("RefreshWeatherService");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent arg0) {
		// TODO Auto-generated method stub
		Log.d("Shanghai", "RefreshWeatherService-onHandleIntent");
		Toast.makeText(this, "service is started", Toast.LENGTH_LONG).show();
	}

}
