package com.intel.weather;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class RebootCompletedReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d("WeatherShanghai", "We are in RebootCompletedReceiver onReceive");
		//context.startService(new Intent(context, RefreshService.class));
	}

}
