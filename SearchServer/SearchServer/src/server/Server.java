package server;


public interface Server {
	public void start(ClientHandler c); //opens the server and waits for clients
	public void stop(); //closes the server
}
