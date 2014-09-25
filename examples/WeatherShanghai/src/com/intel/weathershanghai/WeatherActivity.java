package com.intel.weathershanghai;

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
import android.view.View;
import android.widget.TextView;

public class WeatherActivity extends Activity {
    private String cityName;
	private String request;
	private String response;
	private TextView minTemp;
	private TextView maxTemp;
	private TextView curTemp;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cityName = getIntent().getStringExtra("city");
        Log.d("Shanghai", "we are in the weather activity: " + cityName);
        setContentView(R.layout.activity_weather);
        cityName = cityName.replaceAll(" ", "%20");
        request = "http://api.openweathermap.org/data/2.5/weather?q=" + cityName
        		+ ",en&units=metric";
        curTemp = (TextView)this.findViewById(R.id.curTemp);
        minTemp = (TextView)this.findViewById(R.id.minTemp);
        maxTemp = (TextView)this.findViewById(R.id.maxTemp);
        
        new WeatherUpdate().execute();
    }
    
    protected void onStart() {
    	super.onStart();
        //cityName = getIntent().getStringExtra("city");
        Log.d("Shanghai", "onStart: we are in the weather activity: " + cityName);
    }
    
    public void refreshWeather(View v) {
    	Intent intent = new Intent(this, RefreshWeatherService.class);
    	this.startService(intent);
    	
    }
    
    public class WeatherUpdate extends AsyncTask<String, Integer, String> {
    	private ProgressDialog dlg; //= new ProgressDialog(WeatherActivity.this);

    	@Override
    	protected String doInBackground(String... params) {
    		try {
    			HttpClient client = new DefaultHttpClient();
    			HttpGet getter = new HttpGet(request);
    			response = client.execute(getter, new BasicResponseHandler());
    			Log.d("Shanghai", response);
    			return response;
    		} catch (Throwable e) {
    			Log.d("Shanghai", e.getMessage());
    			return "Error on posting " + e.getMessage();
    		}
    		
    	}
    	
    	@Override
    	protected void onPostExecute(String result) {
    		dlg.dismiss();
    		try {
    			JSONObject jObj = new JSONObject(response);
    			JSONObject mainObj = jObj.getJSONObject("main"); 
    			String temp = mainObj.getString("temp");
    			String temp_min = mainObj.getString("temp_min");
    			String temp_max = mainObj.getString("temp_max");
    			String sufix = "\u00B0";
    			curTemp.setText(new Float(temp).intValue() + sufix);
    			minTemp.setText(new Float(temp_min).intValue() + sufix);
    			maxTemp.setText(new Float(temp_max).intValue() + sufix);
    			
    		} catch (Throwable e) {
    			e.printStackTrace();
    		}
    	}
    	
    	@Override
    	protected void onPreExecute() {
    		//dlg.show(WeatherActivity.this, "hi", "aaa");
    		dlg = ProgressDialog.show(WeatherActivity.this,
    				"Info", "Fetching weather from server", true);
    	}

    }   // End of WeatherUpdate
}

/**
 * public class SimpleListActivity extends ListActivity {...
 * onCreate() {
 * String[] values = getResources().getStringArray(R.array.countries_array);
 * setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);
 * }
 */
