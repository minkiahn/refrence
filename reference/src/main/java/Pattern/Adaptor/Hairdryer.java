package Pattern.Adaptor;

public class Hairdryer implements Electronic110V{

	@Override
	public void poworOn() {
		System.out.println("Hairdryer 110v On");
	}

}
