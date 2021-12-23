package Pattern.Singleton;

public class Singleton {

	private static Singleton singleton = null;
	//자신을 객체로 가지고 있음.
	
	private Singleton() {
		//디폴트 생성자 사용 못하도록 막기 
	}

	public static Singleton getInstance() {
		if(singleton == null) {
			singleton = new Singleton();
		}
		return singleton;
	}
	
	public void call() {
		System.out.println("singleton call!");
	}
}
