package server;

public interface CacheManager<Problem, Solution> {
	public boolean doesSolExist(Problem p);
	public Solution getSolution(Problem p);
	public void saveSolution(Problem p, Solution s);
}
