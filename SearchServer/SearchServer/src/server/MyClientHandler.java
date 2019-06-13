package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MyClientHandler implements ClientHandler{
	private SearcherToSolverAdapter searcher;
	private CacheManager<String, String> cm;
	
	public MyClientHandler() {
		searcher = new SearcherToSolverAdapter();
		cm = new FileCacheManager();
	}
	
	@Override
	public void handleClient(InputStream in, OutputStream out) {
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(in));
		PrintWriter outputWriter = new PrintWriter(out);
		
		String partOfProblem;
		
		int nRows = 0; //num of rows
		int nCols = 0; //num of cols
		ArrayList<int[]> matrix = new ArrayList<>();
		ArrayList<Integer> lineInMatrix = new ArrayList<>();
		
		try {
			while(!(partOfProblem = inputReader.readLine()).equals("end") && partOfProblem != null) {
				String[] numbersAsString = partOfProblem.split(",");
				for (String number: numbersAsString)
					lineInMatrix.add(Integer.parseInt(number));
				
				if(nCols == 0)
					nCols = lineInMatrix.size();
				
				int[] l = lineInMatrix.stream().mapToInt(i->i).toArray();
				matrix.add(l);
					
				lineInMatrix.clear();	
			}
			
			nRows = matrix.size();
			ArrayList<Integer> entryExitPoints = getEntryExitPoints(inputReader);
			
			Matrix m = new Matrix(matrix, nCols, nRows, entryExitPoints.get(0), entryExitPoints.get(1), entryExitPoints.get(2), entryExitPoints.get(3));
			searcher.setSearcher(new BFS());
			String directions = searcher.solve(m);
			
			outputWriter.println(directions);
			outputWriter.flush();
			
			outputWriter.close();
			inputReader.close();
			
		} catch (IOException e) {
			//e.printStackTrace();
		}
		
	}
	
	private ArrayList<Integer> getEntryExitPoints(BufferedReader inputReader) {
			String entryPoint = null;
			String exitPoint = null;
			try {
				entryPoint = inputReader.readLine();
				exitPoint = inputReader.readLine();
			} catch (IOException e) {}

			String[] entryPointSplit = entryPoint.split(",");
			String[] exitPointSplit = exitPoint.split(",");
			
			ArrayList<Integer> entryExitPoints = new ArrayList<>();
			entryExitPoints.add(Integer.parseInt(entryPointSplit[0]));
			entryExitPoints.add(Integer.parseInt(entryPointSplit[1]));
			entryExitPoints.add(Integer.parseInt(exitPointSplit[0]));
			entryExitPoints.add(Integer.parseInt(exitPointSplit[1]));
			
			return entryExitPoints;
	}
}
