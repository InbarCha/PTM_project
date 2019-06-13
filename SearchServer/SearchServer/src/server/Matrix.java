package server;

import java.util.ArrayList;
import java.util.List;

public class Matrix implements Searchable<int[]> {
	ArrayList<int[]> matrix;
	int nCols; 
	int nRows;
	int initialStateRow; //in array indexes: 0 to n-1
	int initialStateCol; //in array indexes: 0 to n-1
	int goalStateRow; //in array indexes: 0 to n-1
	int goalStateCol; //in array indexes: 0 to n-1
	
	Matrix(ArrayList<int[]> matrix, int nCols, int nRows, int initialStateRow, int initialStateCol, int goalStateRow, int goalStateCol) {
		this.matrix = matrix;
		this.nCols=nCols;
		this.nRows = nRows;
		this.initialStateRow = initialStateRow;
		this.initialStateCol = initialStateCol;
		this.goalStateRow = goalStateRow;
		this.goalStateCol = goalStateCol;
	}

	@Override
	public State<int[]> getInitialState() {
		int[] initialState = {initialStateRow, initialStateCol, matrix.get(initialStateRow)[initialStateCol]}; //rowIndex, colIndex, value, cost
		State<int[]> s = new State<>(initialState);
		s.setCost(matrix.get(initialStateRow)[initialStateCol]);
		return s;
	}

	@Override
	public State<int[]> getGoalState() { 
		int[] goalState = {goalStateRow, goalStateCol, matrix.get(goalStateRow)[goalStateCol]}; //rowIndex, colIndex, value, cost
		State<int[]> s = new State<>(goalState);
		s.setCost(Integer.MAX_VALUE);
		return s;
	}

	@Override
	public List<State<int[]>> getAllPossibleStates(State<int[]> s) {
		List<State<int[]>> allPossibleStates = new ArrayList<State<int[]>>();
		int rowIndex = s.getState()[0];
		int colIndex = s.getState()[1];
		ArrayList<String> moves = new ArrayList<>();
		
		if (nCols> 1) {
			if(rowIndex == 0 && colIndex == 0) { //can move only right or down
				moves.add("right");
				moves.add("down");
			}
		
			else if (rowIndex == 0 && colIndex < nCols-1) { //can move only left, right or down
				moves.add("left");
				moves.add("right");
				moves.add("down");
			}
			
			else if (rowIndex > 0 && rowIndex < nRows-1 && colIndex == 0) { //can move only up, down, right
				moves.add("up");
				moves.add("down");
				moves.add("right");
			}
			
			else if(rowIndex == 0 && colIndex == nCols-1) { //we can move only left or down
				moves.add("left");
				moves.add("down");
			}
			
			else if (rowIndex > 0 && rowIndex < nRows-1 && colIndex == nCols-1) { //we can move only up, down or left
				moves.add("up");
				moves.add("down");
				moves.add("left");
			}
			
			else if (rowIndex == nRows-1 && colIndex == 0) { //we can move only up or right
				moves.add("up");
				moves.add("right");
			}
			
			else if (rowIndex == nRows-1 && colIndex < nCols-1) { //we can move only left, right or up
				moves.add("left");
				moves.add("right");
				moves.add("up");
			}
			
			else if (rowIndex > 0 && rowIndex < nRows-1 && colIndex > 0 && colIndex < nCols-1) { //can move up, down, right, left
				moves.add("up");
				moves.add("down");
				moves.add("right");
				moves.add("left");
			}
			
			else if (rowIndex == nRows-1 && colIndex == nCols-1) { //can move up or left
				moves.add("up");
				moves.add("left");
			}
		}
		
		ArrayList<int[]> possibleStates =  generatePossibleMoves(rowIndex, colIndex, moves);
		for(int[] state: possibleStates) {
			State<int[]> newState = new State<int[]>(state);
			newState.setCost(s.getCost() + state[2]);
			allPossibleStates.add(newState);
		}
		return allPossibleStates;
	}
	
	private ArrayList<int[]> generatePossibleMoves(int rowIndex, int colIndex, ArrayList<String> moves) {
		ArrayList<int[]> possibleStates = new ArrayList<>();
		
		if(moves.contains("right")) {
			int[] s = {rowIndex, colIndex + 1, matrix.get(rowIndex)[colIndex + 1]}; //right
			possibleStates.add(s);
		}
		if(moves.contains("left")) {
			int[] s = {rowIndex, colIndex - 1, matrix.get(rowIndex)[colIndex - 1]}; //left
			possibleStates.add(s);
		}
		if(moves.contains("up")) {
			int[] s = {rowIndex - 1, colIndex, matrix.get(rowIndex - 1)[colIndex]}; //up
			possibleStates.add(s);
		}
		if(moves.contains("down")) {
			int[] s = {rowIndex + 1, colIndex, matrix.get(rowIndex + 1)[colIndex]}; //down
			possibleStates.add(s);
		}
		
		return possibleStates;
	}


}
