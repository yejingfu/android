package com.intel.weather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Log.d("WeatherShanghai", "Suntem in onCreate");
	}
	
	public void showWeather(View view)
	{
		//we read the city
		EditText inputCity =(EditText) findViewById(R.id.inputCity);
		String city = inputCity.getText().toString();
		Log.d("WeatherShanghai", "The city is: " + city);
		
		//jump in WeatherActivity
		Intent intent = new Intent(this, WeatherActivity.class);
		intent.putExtra("city", city);
		startActivity(intent);
	}
	
	public void forecastWeather(View view)
	{
		//we read the city
		EditText inputCity =(EditText) findViewById(R.id.inputCity);
		String city = inputCity.getText().toString();
		Log.d("WeatherShanghai", "The city is: " + city);
		
		//jump in ForecastActivity
		Intent intent = new Intent(this, ForecastActivity.class);
		intent.putExtra("city", city);
		startActivity(intent);
	}	
}
