package Pattern.Strategy;

public class Car {
	private CarMove carMove;

	public Car(CarMove carMove) {
		this.carMove = carMove;
	}
	
	public void move() {
		carMove.move();
	}
}
