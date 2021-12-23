package Pattern.Singleton;

public class CallSingleton {
	private Singleton singleton;
	
	public CallSingleton() {
		this.singleton = Singleton.getInstance();
	}
	public Singleton getSingleton() {
		return singleton;
	}
	
}
