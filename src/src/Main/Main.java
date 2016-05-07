package Main;
import java.util.TreeMap;

import Utils.Learn;
import Utils.Parser;

public class Main {
	
	/*
	 * Parameters: files/corpustodo.txt files/corpusrel.txt files/corpusnrel.txt
	 */
	public static void main(String[] args) {
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		new Parser(args[0], map);
		new Learn(args[1], args[2], map);
	}

}
