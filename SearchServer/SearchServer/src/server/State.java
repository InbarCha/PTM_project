package server;

public class State<T> { //T is int[][]
	private T state;
	private int cost;
	private State<T> cameFrom;
	
	public State(T state) {
		this.state = state;
	}
	
	public boolean equals(State<T> s) {
		return state.equals(s.state);
	}
	
	public State<T> getCameFrom() {
		return cameFrom;
	}
	
	public T getState() {
		return state;
	}
	
	public void setCameFrom(State<T> fatherState) {
		cameFrom = fatherState;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public int getCost() {
		return cost;
	}
	
}
