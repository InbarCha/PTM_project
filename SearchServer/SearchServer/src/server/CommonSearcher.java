package server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public abstract class CommonSearcher implements Searcher<int[]> {
	protected Queue<State<int[]>> openList;
	
	public CommonSearcher() {
		openList = new PriorityQueue<>((o1, o2)->(o1.getCost() - o2.getCost()));
	}
	
	public State<int[]> popOpenList() {
		return openList.poll();
	}
	
	public boolean openListContainsState(State<int[]> s) {
		for(State<int[]> s1 : openList) 
			if(Arrays.equals(s1.getState(), s.getState()))
					return true;
		return false;
	}
	
	
	public void addToOpenList(State<int[]> s) {
		openList.add(s);
	}
	
	@Override
	public abstract ArrayList<State<int[]>> search(Searchable<int[]> problem); 
	
}
