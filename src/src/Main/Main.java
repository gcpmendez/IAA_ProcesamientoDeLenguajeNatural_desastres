package Main;
import java.util.TreeMap;

import Utils.Probability;
import Utils.Corpus;

public class Main {
	
	/**
	 * Parámetros: files/corpustodo.txt files/corpusrel.txt files/corpusnrel.txt
	 */
	public static void main(String[] args) {
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		
		/** Creación corpus */
		new Corpus(args[0], map);
		
		/** Estimación de probabilidades */
		new Probability(args[1], args[2], map);
	}

}
