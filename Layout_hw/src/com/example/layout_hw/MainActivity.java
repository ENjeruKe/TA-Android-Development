package com.example.layout_hw;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements
		View.OnClickListener {

	LinearLayout pcLayout, docsLayout, iExploderLayout;
	Context context = this;
	Point p;
	ImageView popup_img;
	TextView popup_title;
	TextView popup_description;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		pcLayout = (LinearLayout) this.findViewById(R.id.myPcLayout);
		pcLayout.setOnClickListener(this);

		docsLayout = (LinearLayout) this.findViewById(R.id.myDocsLayout);
		docsLayout.setOnClickListener(this);

		iExploderLayout = (LinearLayout) this
				.findViewById(R.id.iExploderLayout);
		iExploderLayout.setOnClickListener(this);

	}

	private void showPopup(View v) {

		int popupWidth = 200;
		int popupHeight = 100;

		int id = v.getId();

		int[] location = new int[2];
		location[0] = 100;
		location[1] = 100;
		v.getLocationOnScreen(location);
		
		
		p = new Point();
		p.x = location[0];
		p.y = location[1];

		LinearLayout viewGroup = (LinearLayout) this.findViewById(R.id.popup);
		LayoutInflater layoutInflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		// Preparing the popup layout
		View layout = layoutInflater.inflate(R.layout.popup_layout, viewGroup);
		popup_img = (ImageView) layout.findViewById(R.id.popup_image);
		popup_title = (TextView) layout.findViewById(R.id.popup_title);
		popup_description = (TextView) layout
				.findViewById(R.id.popup_desctiption);

		/*
		// Getting the resources for the popup
		Resources r = v.getContext().getResources();

		final int imageResourceId = r.getIdentifier("android:src", "drawable",
				this.getPackageName());

		final int titleResourceId = r.getIdentifier("android:text", "string",
				this.getPackageName());

		final int descriptionResourceId = r.getIdentifier(
				"android:description", "string", this.getPackageName());

		// Populating the resources
		 popup_img.setImageResource(imageResourceId);
		 popup_title.setText(titleResourceId);
		 popup_description.setText(descriptionResourceId);
		 */

		if (id == R.id.myPcLayout) {
			popup_img.setImageResource(R.drawable.my_computer);
			popup_title.setText(R.string.my_computer_text);
			popup_description.setText(
					"Manufacturer: " + android.os.Build.MANUFACTURER + "\nModel: " +android.os.Build.PRODUCT);
		}

		if (id == R.id.myDocsLayout) {
		    popup_img.setImageResource(R.drawable.my_docs);
		    popup_title.setText(R.string.my_docs_text);
			popup_description.setText(R.string.my_docs_description);
		}

		if (id == R.id.iExploderLayout) {
			popup_img.setImageResource(R.drawable.iexploder);
			popup_title.setText(R.string.iexploder_text);
			popup_description.setText(R.string.iexploder_description);
		}

		// Creating the PopupWindow
		final PopupWindow popup = new PopupWindow(this);
		popup.setContentView(layout);
		popup.setWidth(popupWidth);
		popup.setHeight(popupHeight);
		popup.setFocusable(true);
		
		// Some offset to align the popup a bit to the right, and a bit down,
		// relative to button's position.
		int OFFSET_X = 10;
		int OFFSET_Y = 10;

		// Clear the default translucent background
		popup.setBackgroundDrawable(new BitmapDrawable());

		// Displaying the popup at the specified location, + offsets.
		popup.showAtLocation(layout, Gravity.NO_GRAVITY, p.x + OFFSET_X, p.y + OFFSET_Y);
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

	@Override
	public void onClick(View v) {
		showPopup(v);
	}
}
