package Pattern.Adaptor;

public class Call {

	public static void main(String[] args) {
		Hairdryer hairdryer = new Hairdryer();
		connect(hairdryer);
		
		Cleaner cleaner = new Cleaner();
		
		Electronic110V adaptor = new SocketAdaptor(cleaner); //220v -> 110v 어뎁터
		connect(adaptor);

	}

	//110v 콘센트
	public static void connect(Electronic110V electronic) {
		electronic.poworOn();
	}
	
}


