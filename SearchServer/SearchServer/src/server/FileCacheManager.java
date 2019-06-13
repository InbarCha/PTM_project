package server;

import java.io.*;

public class FileCacheManager implements CacheManager<String, String> {
	
	public FileCacheManager() {}
	
	@Override
	public boolean doesSolExist(String p) {
		try {
			FileReader f = new FileReader(p);
			f.close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	@Override
	public String getSolution(String p) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(p));
			String s = reader.readLine();
			reader.close();
			return s;
		} catch (IOException e) {
			return "solution not found in file";
		}

	}

	@Override
	public void saveSolution(String p, String s) {
		try {
			PrintWriter printToFile= new PrintWriter(new FileOutputStream(p));
			printToFile.println(s);
			printToFile.close();
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
		}
		
	}	
	
	
	
}