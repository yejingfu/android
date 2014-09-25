package com.intel.weathershanghai;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

public class ForecastActivity extends ListActivity {

	String cityName;
	String request;
	String response;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		cityName = getIntent().getStringExtra("city");
        cityName = cityName.replaceAll(" ", "%20");
        request = "http://api.openweathermap.org/data/2.5/forecast?q=" 
        		+ cityName + ",en&units=metric";
        // 1,
		//String[] items = getResources().getStringArray(R.array.forecast_items);
		//this.setListAdapter(new ArrayAdapter<String>(
		//		this, android.R.layout.simple_list_item_1, items));
        
        // 2,
        // ListArray<String> items = new ListArray<String>
        // items.add(...)
        // Adaptor adaptor = new ...
        // adaptor.notifyDataSetChanged();
        
        new ForecastTask().execute();
	}
	
    public class ForecastTask extends AsyncTask<String, Integer, String> {
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
    			String sufix = "\u00B0";
    			String down = "\u2193";
    			String up = "\u2191";
    			
    			
    			JSONArray list = jObj.getJSONArray("list");
    			int len = list.length();
    			String[] items = new String[len];
    			for (int i = 0; i < len; i++) {
    				JSONObject curObj = list.getJSONObject(i);
    				JSONObject mainObj = curObj.getJSONObject("main");
    				String temp = mainObj.getString("temp");
    				temp = (new Float(temp).intValue() + sufix);
    				String tempMin = mainObj.getString("temp_min");
    				tempMin = (new Float(tempMin).intValue() + sufix);
    				String tempMax = mainObj.getString("temp_max");
    				tempMax = (new Float(tempMax).intValue() + sufix);
    				String output = down + " " + tempMin + " " 
    						+ up + " " + tempMax + "  " + temp + "  "
    						+ curObj.getString("dt_txt");
    				items[i] = output;
    				
    			}
    			
    			ForecastActivity.this.setListAdapter(new ArrayAdapter<String>(
    					ForecastActivity.this,
    					android.R.layout.simple_list_item_1, 
    					items));
    			
    		} catch (Throwable e) {
    			e.printStackTrace();
    		}
    	}
    	
    	@Override
    	protected void onPreExecute() {
    		//dlg.show(WeatherActivity.this, "hi", "aaa");
    		dlg = ProgressDialog.show(ForecastActivity.this,
    				"Info", "Fetching info from server", true);
    	}

    }   // End of ForecastTask

}
