public class Temperature {

	private double degree;
	private char symbol;

	// degreesC = 5(degreesF-32)/9
	// degreesF = 5(degreesC-32)/9

	public Temperature() {
		this.degree = 0;
		this.symbol = 'C';
	}

	public Temperature(double value) {
		this.degree = value;
		this.symbol = 'C';
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
		if (scale == this.symbol) {
			return this.degree;
		} else if( this.symbol == 'F'){
			return 9 * (this.degree / 5) + 32;
		}else {
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
