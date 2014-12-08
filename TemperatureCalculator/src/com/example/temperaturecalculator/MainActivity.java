package com.example.temperaturecalculator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

	final Context context = this;
	EditText temperatureInput;
	private Button fahrenheitButton, celsiusButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		celsiusButton = (Button) this.findViewById(R.id.celsiusButton);
		fahrenheitButton = (Button) this.findViewById(R.id.fahrenheitButton);

		celsiusButton.setOnClickListener(this);
		fahrenheitButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void onClick(View v) {
		EditText text = (EditText) this.findViewById(R.id.editText1);
		double value = 0;

		try {
			value = Double.parseDouble(text.getText().toString());
		} catch (Exception e) {
			Toast.makeText(context, "Invalid value!", Toast.LENGTH_SHORT)
					.show();
			return;
		}

		Intent intent = new Intent(MainActivity.this,
				CalculatorResultActivity.class);

		Temperature temp = new Temperature(value);
		// intent.putExtra("CLASS", temp);

		if (v.getId() == R.id.celsiusButton) {
			intent.putExtra("SYMBOL", 'C');
			intent.putExtra("VALUE", temp.getTemp('C'));
			Toast.makeText(context, "From Fahrenheit to Celsius",
					Toast.LENGTH_SHORT).show();
		}
		if (v.getId() == R.id.fahrenheitButton) {
			intent.putExtra("SYMBOL", 'F');
			intent.putExtra("VALUE", temp.getTemp('F'));
			Toast.makeText(context, "From Celsius to Fahrenheit",
					Toast.LENGTH_SHORT).show();
		}
		this.startActivity(intent);
	}

}
