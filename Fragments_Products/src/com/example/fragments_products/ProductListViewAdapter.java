package com.example.fragments_products;

import java.util.List;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ProductListViewAdapter extends ArrayAdapter<Product> {

	List<Product> products;
	Context context;
	//View detailView;
	int currentPosition;
	
	
	public ProductListViewAdapter(Context context, int resource,
			List<Product> objects) {
		super(context, resource, objects);
		this.context = context;
		this.products = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolderItem holder;

		if (convertView == null) {
			holder = new ViewHolderItem();

			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			convertView = inflater.inflate(R.layout.product_list_item, parent,
					false);

			holder.productName = (TextView) convertView
					.findViewById(R.id.productNameText);
			holder.productCategory = (TextView) convertView
					.findViewById(R.id.productCategoryText);
			convertView.setTag(holder);
			this.currentPosition = position;
			
		} else {
			holder = (ViewHolderItem) convertView.getTag();
		}

		holder.productName.setText(this.products.get(position).name);
		holder.productCategory.setText(Html.fromHtml("<small><small>"
				+ this.products.get(position).category + "</small></small>"));

		return convertView;
	}

	static class ViewHolderItem {
		TextView productName;
		TextView productCategory;

	}

}
