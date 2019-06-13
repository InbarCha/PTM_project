package server;
import java.io.IOException;
import java.net.*;

public class MySerialServer implements Server{
	private volatile boolean stop; //the variable will never be cached
	int port;
	
	public MySerialServer(int port) {
		this.port = port;
		stop = false;
	}
	
	public void start(ClientHandler c) {
		Thread th = new Thread() {
			public void run() {
				try {
					ServerSocket server = new ServerSocket(port);
					server.setSoTimeout(1000 * 60 * 5); //timeout to 5 mins
					while(!stop) {
						Socket aClient = server.accept(); //it's a blocking call
						c.handleClient(aClient.getInputStream(), aClient.getOutputStream());
						//aClient's inputStream and outputStream are closed inside handleClient
						aClient.close();
					}
					server.close();
					
				} catch (IOException e) {
					//e.printStackTrace();
				}
			}
		};
		
		th.start();
	}
	
	public void stop() {
		stop = true;
	}
	
}
