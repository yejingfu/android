package com.intel.weather;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class WeatherActivity extends Activity {
	String request;
	String response;
	TextView actualTemp;
	TextView minTemp;
	TextView maxtemp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weather);
		
		// we setup the proxy in case we need this
		//System.setProperty("http.proxyHost", "........");
		//System.setProperty("http.proxyPort", "911");

		// get the passed values from the MainActivty and prepare tge
		Intent intent = getIntent();
		String city = intent.getStringExtra("city");
		city = city.replaceAll(" ", "%20");
		request = "http://api.openweathermap.org/data/2.5/weather?q=" + city
				+ ",en&units=metric";

		actualTemp = (TextView) findViewById(R.id.actualTemp);
		minTemp = (TextView) findViewById(R.id.minTemp);
		maxtemp = (TextView) findViewById(R.id.maxTemp);

		// get the data in an AsyncTask
		new WeatherUpdater().execute();
	}

	class WeatherUpdater extends AsyncTask<String, Integer, String> {
		private ProgressDialog progress;

		@Override
		protected String doInBackground(String... statuses) {
			try {
				// transporter for our in->out call
				HttpClient client = new DefaultHttpClient();
				HttpGet httpget = new HttpGet(request);
				response = client.execute(httpget, new BasicResponseHandler());
				Log.d("WeatherShanghai", response);
				return response;

			} catch (Throwable e) {
				Log.d("WeatherShanghai", e.getMessage());
				return "Error on posting" + e.getMessage();
			}
		}

		@Override
		protected void onPostExecute(String result) {
			progress.dismiss();

			// parse the JSON and get the temperature
			try {
				JSONObject jObj = new JSONObject(response);
				JSONObject jsonObj = jObj.getJSONObject("main");
				actualTemp.setText(new Float(jsonObj.getString("temp"))
						.intValue() + "\u00B0");
				minTemp.setText(new Float(jsonObj.getString("temp_min"))
						.intValue() + "\u00B0");
				maxtemp.setText(new Float(jsonObj.getString("temp_max"))
						.intValue() + "\u00B0");

			} catch (Throwable e) {
				e.printStackTrace();
			}
		}

		@Override
		protected void onPreExecute() {
			progress = ProgressDialog.show(WeatherActivity.this,
					"Get the weather", "Please wait ....");
		}

	}

}
