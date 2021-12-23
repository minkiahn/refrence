package Pattern.Singleton;

public class Call {

	public static void main(String[] args) {
		CallSingleton callSingleton = new CallSingleton();
		CallSingleton2 callSingleton2 = new CallSingleton2();
		
		callSingleton.getSingleton().getInstance().call();
		callSingleton2.getSingleton().getInstance().call();
		System.out.println(callSingleton.getSingleton().equals(callSingleton2.getSingleton()));
	}

}
