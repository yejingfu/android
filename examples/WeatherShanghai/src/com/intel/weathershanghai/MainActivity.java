package com.intel.weathershanghai;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.setProperty("http.proxyHost", "proxy.cd.intel.com");
        System.setProperty("http.proxyPort", "911");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void showWeather(View btn) {
    	String cityName = getCityName();
    	//Toast.makeText(this, "Click on show weather for city: " + cityName,
    	//		Toast.LENGTH_LONG).show();
    	
    	// start activity through intent
    	Intent intent = new Intent(this, WeatherActivity.class);
    	intent.putExtra("city",	cityName);
    	this.startActivity(intent);
    }
    
    public void forecastWeather(View v) {
    	Intent intent = new Intent(this, ForecastActivity.class);
    	intent.putExtra("city", this.getCityName());
    	this.startActivity(intent);
    }
    
    private String getCityName() {
        return ((EditText)this.findViewById(R.id.inputCity))
        		.getText().toString();
    }
}
