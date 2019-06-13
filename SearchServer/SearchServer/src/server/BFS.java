package server;

import java.util.ArrayList;
import java.util.*;

public class BFS extends CommonSearcher {
	ArrayList<State<int[]>> closedSet;
	
	BFS() {
		closedSet = new ArrayList<>();
	}
	
	private boolean closedSetContains(State<int[]> s) {
		for(State<int[]> s1 : closedSet)
			if(Arrays.equals(s1.getState(), s.getState()))
					return true;
		
		return false;
	}
	
	@Override
	public ArrayList<State<int[]>> search(Searchable<int[]> problem) {
		addToOpenList(problem.getInitialState());
		
		while(openList.size() > 0) {
			State<int[]> n = popOpenList(); //dequeue
			closedSet.add(n);
			
			if(Arrays.equals(problem.getGoalState().getState(), n.getState()))
				return backTrace(problem.getInitialState(), n);
			
			List<State<int[]>> successors = problem.getAllPossibleStates(n);
			
			for(State<int[]> state: successors) {
				if(!closedSetContains(state) && !openListContainsState(state)) {
					state.setCameFrom(n);
					addToOpenList(state);
				}
				
				else if (n.getCost() + state.getState()[2] < state.getCost()) {
					state.setCost(n.getCost() + state.getState()[2]);
					state.setCameFrom(n);
					if(!openListContainsState(state)) 
						openList.add(state);
					else {
						openList.remove(state);
						openList.add(state);
					}
				}
			}
			
		}
		return null;
	}
	
	private ArrayList<State<int[]>> backTrace(State<int[]> initialState, State<int[]> goalState) {
		ArrayList<State<int[]>> trace = new ArrayList<>();
		State<int[]> currentState = goalState;
		
		while(currentState.getCameFrom() != null) {
			trace.add(0, currentState);
			currentState = currentState.getCameFrom();
		}
		trace.add(0, currentState);
		
		return trace;
	}


}
