/**
 * 
 */
package Pattern.Strategy;

/**
 * @author Min-Ki Ahn
 *
 */
public class Reverse implements CarMove {

	@Override
	public void move() {
		System.out.println("CarMove : Reverse");
	}

}