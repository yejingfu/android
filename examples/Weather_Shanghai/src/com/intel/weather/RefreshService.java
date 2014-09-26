package com.intel.weather;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class RefreshService extends IntentService {

	public RefreshService() {
		super("RefreshService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		try {
			// transporter for our in->out call
			//TODO pune orasul din preferinte
			String urlWeather = "http://api.openweathermap.org/data/2.5/weather?q=" + "Brasov" + ",ro&units=metric";
			
			//get pe url
			HttpClient client = new DefaultHttpClient();
			HttpGet httpget = new HttpGet(urlWeather);
			String response = client.execute(httpget, new BasicResponseHandler());

			//parsare JSON
			JSONObject jObj = new JSONObject(response);
			JSONObject jsonObj = jObj.getJSONObject("main");
			String weatherString = new Float(jsonObj.getString("temp")).toString();
			
			Log.d("WeatherShanghai", "The new weather via IntentService is: " + weatherString);
			
		} catch (Throwable e) {
		}
	}

}
