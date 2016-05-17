package Main;
import java.util.TreeMap;

import Utils.Probability;
import Utils.Classify;
import Utils.Corpus;

public class Main {
	
	/**
	 * Parámetros: 
	 * 0 - files/corpustodo.txt
	 * 1 - files/corpusrel.txt
	 * 2 - files/corpusnrel.txt
	 * 3 - aprendizajerel.txt
	 * 4 - aprendizajenrel.txt
	 * 5 - archivoAnalizar.txt
	 */
	public static void main(String[] args) {
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		
		/** Creación corpus */
		//new Corpus(args[0], map);
		
		/** Estimación de probabilidades */
		//new Probability(args[1], args[2], map);
		
		/** Clasificacion */
		new Classify(args[3], args[4]/*, args[5], "clasificacion.txt"*/);
	}

}
