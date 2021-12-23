/**
 * 
 */
package Pattern.Strategy;

/**
 * @author Min-Ki Ahn
 *
 */
public class Call {

	public static void main(String[] args) {
		Car car = new Car(new Drive());
		car.move();
		
		Car car2 = new Car(new Reverse());
		car2.move();
	}

}
