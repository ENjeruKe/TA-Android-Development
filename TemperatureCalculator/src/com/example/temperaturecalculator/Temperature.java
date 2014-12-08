package com.example.temperaturecalculator;

public class Temperature {

	private double degree;
	private char symbol;

	public Temperature(double value) {
		this.degree = value;
		// this.symbol = 'C';
	}

	public Temperature(char symbol) {
		this.degree = 0;
		this.symbol = symbol;
	}

	public Temperature(char symbol, double value) {
		this.degree = value;
		this.symbol = symbol;
	}

	public double getTemp(char scale) {
		if (scale == 'F') {
			return 9 * (this.degree / 5) + 32;
		} else {
			return 5 * (this.degree - 32) / 9;
		}
	}

	public double getDegree() {
		return degree;
	}

	public void setDegree(double degree) {
		this.degree = degree;
	}

	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
}
