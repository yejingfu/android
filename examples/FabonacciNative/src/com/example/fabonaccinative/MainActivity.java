package com.example.fabonaccinative;

import java.util.Locale;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.app.ProgressDialog;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	
	private EditText input;
	private RadioGroup type;
	private TextView output;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		input = (EditText) this.findViewById(R.id.input);
		type = (RadioGroup) this.findViewById(R.id.type);
		output = (TextView) this.findViewById(R.id.output);
		Button btn = (Button) this.findViewById(R.id.button);
		btn.setOnClickListener(this);
	}
	
	public void onClick(View view) {
		String s = this.input.getText().toString();
		if (TextUtils.isEmpty(s))
			return;
		final ProgressDialog dlg = ProgressDialog.show(this, "calculate", "calcaulating", true);
		final long n = Long.parseLong(s);
		final Locale locale = this.getResources().getConfiguration().locale;
		
		new AsyncTask<Void, Void, String>() {
			@Override
			protected String doInBackground(Void... params) {
				long result = 0;
				long t = SystemClock.uptimeMillis();
				switch (MainActivity.this.type.getCheckedRadioButtonId()) {
				case R.id.type_fib_jr:
					result = FabLib.fibJR(n);
					break;
				case R.id.type_fib_ji:
					result = FabLib.fibJI(n);
					break;
				case R.id.type_fib_nr:
					result = FabLib.fibNR(n);
					break;
				case R.id.type_fib_ni:
					result = FabLib.fibNI(n);
					break;
				}
				t = SystemClock.uptimeMillis() - t;
				return String.format(locale, "fib(%d)=%d in %d ms", n, result, t);
			}
			@Override
			protected void onPostExecute(String s) {
				dlg.dismiss();
				MainActivity.this.output.setText(s);
			}
		}.execute(); // End AsyncTask
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
