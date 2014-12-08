package com.example.temperaturecalculator;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatorResultActivity extends ActionBarActivity {

	Context context = this;
	private TextView resultText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calculator_result);
		Bundle bundle = this.getIntent().getExtras();
		double value = bundle.getDouble("VALUE");
		char symbol = bundle.getChar("SYMBOL");

		resultText = (TextView) this.findViewById(R.id.resultTextView);
		resultText.setText("The result is " + String.format("%.4f", value)
				+ symbol + "\u00B0");

		Toast.makeText(context, String.valueOf(value) + symbol,
				Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.calculator_result, menu);
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
}
