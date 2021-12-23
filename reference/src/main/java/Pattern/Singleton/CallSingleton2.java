package Pattern.Singleton;

public class CallSingleton2 {
	private Singleton singleton;
	
	public CallSingleton2() {
		this.singleton = Singleton.getInstance();
	}
	public Singleton getSingleton() {
		return singleton;
	}
	
}
