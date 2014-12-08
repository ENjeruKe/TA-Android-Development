public class TemperatureConsoleClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Temperature temp = new Temperature('F', 123.4);

		System.out.println("T = " + temp.getDegree() + temp.getSymbol());

		System.out.println("In Fahrenheit: ");
		System.out.println("T = " + temp.getTemp('F') + 'F');
		System.out.println("In Celsius: ");
		System.out.println("T = " + temp.getTemp('C') + 'C');
	}

}
