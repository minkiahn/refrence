/**
 * 
 */
package Pattern.Strategy;

/**
 * @author Min-Ki Ahn
 *
 */
public class Drive implements CarMove {

	@Override
	public void move() {
		System.out.println("CarMove : Drive");
	}

}
