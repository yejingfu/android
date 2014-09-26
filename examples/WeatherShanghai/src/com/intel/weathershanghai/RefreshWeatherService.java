package com.intel.weathershanghai;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.app.IntentService;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.widget.Toast;

public class RefreshWeatherService extends IntentService {
	private Intent intent = null;
	private static final int CALLBACK_MSG = 0;

	public RefreshWeatherService() {
		super("RefreshWeatherService");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		//Toast.makeText(this, "service is started", Toast.LENGTH_LONG).show();
		this.intent = intent;
		String city = intent.getStringExtra("city");
		Log.d("Shanghai", "RefreshWeatherService-onHandleIntent: " + city);
		
		new AsyncTask<String, Void, String>() {
			@Override
			protected String doInBackground(String... params) {
				String city = params[0].toString();
				Log.d("Shanghai", "doInbackground: " + city);
				try {
					String request = "http://api.openweathermap.org/data/2.5/weather?q="
		        		+ city + ",en&units=metric";
					HttpClient client = new DefaultHttpClient();
					HttpGet getter = new HttpGet(request);
				    String response = client.execute(getter, new BasicResponseHandler());
				    return response;
				} catch (Throwable e) {
					Log.d("Shanghai", "Failed to get weather from server: " + e.getMessage());
					return "Failed to get info: " + e.getMessage();
				}
			}
			protected void onPostExecute(String result) {
				try {
					Log.d("Shanghai", "onPostExecute: " + result);
					JSONObject jObj = new JSONObject(result);
					JSONObject mainObj = jObj.getJSONObject("main");
					String temp = mainObj.getString("temp");
					Message msg = Message.obtain();
					Bundle data = new Bundle(1);
					data.putString("tempurature", temp);
					msg.what = RefreshWeatherService.CALLBACK_MSG;
					msg.setData(data);
					Messenger messenger = RefreshWeatherService.this.intent.getParcelableExtra("callback-messenger");
					messenger.send(msg);
				} catch (Throwable e) {
					Log.e("Shanghai", "Failed to parse weather: " + e.getMessage());
				}
			}
		}.execute(city);  // End of AsyncTask
	}

}
