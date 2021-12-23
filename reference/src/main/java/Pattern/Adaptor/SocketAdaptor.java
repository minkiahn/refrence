package Pattern.Adaptor;

//110v를 받아서 220v으로 변환
public class SocketAdaptor implements Electronic110V {
	
	private Electronic220V electronic220V;
	
	
	public SocketAdaptor(Electronic220V electronic220v) {
		super();
		electronic220V = electronic220v;
	}

	@Override
	public void poworOn() {
		electronic220V.connect();
	}

}
