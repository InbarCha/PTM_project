package server;

import java.util.ArrayList;

public class SearcherToSolverAdapter implements Solver<Matrix, String> {
	Searcher<int[]> searcher;
	
	public SearcherToSolverAdapter() {}
	public void setSearcher(Searcher<int[]> searcher) {
		this.searcher = searcher;
	}
	
	@Override
	public String solve(Matrix p) {
		ArrayList<State<int[]>> trace = searcher.search(p);
		String DirectionsToSolve = TransToDirections(trace);
		
		return DirectionsToSolve;
	}

	private String TransToDirections(ArrayList<State<int[]>> trace) {
		String directions = "";
		
		for(int i = 0; i<trace.size() - 1; i++) {
			State<int[]> currentState = trace.get(i);
			State<int[]> nextState = trace.get(i+1); 
			int currentRowIndex = currentState.getState()[0];
			int nextRowIndex = nextState.getState()[0];
			int currentColIndex = currentState.getState()[1];
			int nextColIndex = nextState.getState()[1];
			String direction = null;
			
			if(currentRowIndex == nextRowIndex) {
				if(currentColIndex < nextColIndex) 
					direction = "Right";
				else direction = "Left";
			}
			
			else if (currentRowIndex < nextRowIndex) 
				direction = "Down";
			else if (currentRowIndex > nextRowIndex) 
				direction = "Up";
			
			directions += direction;
			if(i < trace.size() - 2)
				directions += ",";
		}
		
		return directions;
	}
}
