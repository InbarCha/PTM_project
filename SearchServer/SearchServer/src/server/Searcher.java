package server;

import java.util.ArrayList;

public interface Searcher<T> {
	public ArrayList<State<T>> search(Searchable<T> problem); 
}
