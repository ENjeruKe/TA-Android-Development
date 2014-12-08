package com.example.fragments_products;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {

	public int id;
	public String name;
	public String category;
	public int quantity;
	public double price;
	
	public Product(String name, String category, int quantity, double price){
		this.name = name;
		this.category = category;
		this.quantity = quantity;
		this.price = price;				
	}
	
	public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
		public Product createFromParcel(Parcel in) {
			return new Product(in);
		}
		
		public Product[] newArray(int size) {
			return new Product[size];
		}
	};
	
	public Product(Parcel in)
	{
		readFromParcel(in);
	}
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(name);
		dest.writeString(category);
		dest.writeInt(quantity);
		dest.writeDouble(price);
	}
	
	private void readFromParcel(Parcel in) {
		name = in.readString();
		category = in.readString();
		quantity = in.readInt();
		price = in.readDouble();
	}
}
