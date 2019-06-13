package server;

import java.io.*;

public class MyTestClientHandler implements ClientHandler {
	private Solver<String, String> solver;
	private CacheManager<String, String> cm;
	
	public MyTestClientHandler() {
		this.solver = s->new StringBuilder(s).reverse().toString();
		this.cm = new FileCacheManager();
	}
	 
	public void handleClient(InputStream in, OutputStream out) {
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(in));
		PrintWriter outputWriter = new PrintWriter(out);
		String problem;
		try {
			while((problem = inputReader.readLine()) != "end" && problem != null) {
				String solution = null;
				if(cm.doesSolExist(problem))
					solution = cm.getSolution(problem);
				else {
					solution = solver.solve(problem);
					cm.saveSolution(problem, solution);
				}
				outputWriter.println(solution);
				outputWriter.flush();
			}
			outputWriter.close();
			inputReader.close();
			
		} catch (IOException e) {
			//e.printStackTrace();
		}
	}
	
}

