package server;

public class MainTrain {

	public static void main(String[] args) {
		MySerialServer searchServer = new MySerialServer(Integer.parseInt(args[0]));
		searchServer.start(new MyClientHandler()); //the server is up
	}

}
