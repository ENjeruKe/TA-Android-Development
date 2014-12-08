package com.example.fragments_products;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity implements
		OnItemClickListener {

	private ArrayList<Product> products;
	private ListView productsList;
	private TextView productName;
	private TextView productDetails;
	ProductListViewAdapter productsAdapter;
	Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		this.products = getProducts();

		productsList = (ListView) this.findViewById(R.id.products_list_view);

		productsAdapter = new ProductListViewAdapter(context,
				R.layout.product_list_item, products);

		productsList.setAdapter(productsAdapter);
		productsList.setOnItemClickListener(this);

	}

	private ArrayList<Product> getProducts() {
		Product tomato = new Product("Tomato", "Vegetable", 1000, 1.23);
		Product coconut = new Product("Coconut", "Fruit", 1, 2.99);
		Product orange = new Product("Orange", "Fruit", 1000, 2.23);
		Product cabbage = new Product("Cabbage", "Vegetable", 1000, 0.56);
		Product turnups = new Product("Turnups", "Vegetable", 1000, 2.12);
		Product pineapple = new Product("Pineapple", "Fruit", 1, 3.99);
		Product melon = new Product("Melon", "Fruit", 1000, 3.12);
		Product corn = new Product("Corn", "Vegetable", 1000, 0.89);
		Product cucumber = new Product("Cucumber", "Vegetable", 1000, 0.65);
		Product carrot = new Product("Carrot", "Vegetable", 1000, 0.70);
		Product salad = new Product("Salad", "Vegetable", 1, 1.45);
		ArrayList<Product> basket = new ArrayList<Product>();
		basket.add(tomato);
		basket.add(coconut);
		basket.add(orange);
		basket.add(cabbage);
		basket.add(turnups);
		basket.add(pineapple);
		basket.add(melon);
		basket.add(corn);
		basket.add(cucumber);
		basket.add(carrot);
		basket.add(salad);
		return basket;
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
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		productName = (TextView) this.findViewById(R.id.productDetailText);
		productName.setText("Name: " + products.get(position).name);

		productDetails = (TextView) this.findViewById(R.id.productDetailInfo);
		
		if (products.get(position).quantity > 100) {
			productDetails.setText("Category: " + products.get(position).category
					+ "\nPrice: " + products.get(position).price
					+ "$ per " + products.get(position).quantity + " grams");
		}else {
			productDetails.setText("Category: " + products.get(position).category
					+ "\nPrice: " + products.get(position).price
					+ "$ per " + products.get(position).quantity + " piece(s)");
		}
		
	}
}
