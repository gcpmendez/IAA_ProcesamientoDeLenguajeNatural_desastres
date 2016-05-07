package Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Learn {
	private Filter filterWords = new Filter();
	
	public Learn(String corpusRel, String corpusNRel, TreeMap<String, Integer> map) {
		ArrayList<String> wordsCorpusRel = new ArrayList<String>();
		ArrayList<String> wordsCorpusN_Rel = new ArrayList<String>();
		
		TreeMap<String, Integer> mapCopy = new TreeMap<String, Integer>();
		mapCopy.putAll(map);			// Copy original TreeMap
		
		readWords(corpusRel, wordsCorpusRel);		// Read words corpusrel.txt
		readWords(corpusNRel, wordsCorpusN_Rel);		// Read words corpusnrel.txt
		writeFiles("aprendizajerel.txt", mapCopy, wordsCorpusRel);	// Write aprendizajerel.txt
		writeFiles("aprendizajenrel.txt", mapCopy, wordsCorpusN_Rel);	// Write aprendizajenrel.txt
	}
	
	public void writeFiles(String file, TreeMap<String, Integer> mapCopy, ArrayList<String> array) {
		Iterator<String> itr = mapCopy.keySet().iterator();   // Set all words to 0 times
		while (itr.hasNext()) {
			String word = (String)itr.next();
			mapCopy.put(word, 0);
		}
		
		for (int i = 0; i < array.size(); i++) {		// Count occurrences
			String word = array.get(i);
			mapCopy.put(word, mapCopy.get(word) + 1);
		}
		
		try {
			FileWriter fichero = new FileWriter(file);
			PrintWriter pw = new PrintWriter(fichero);
			
			Iterator<String> itr_2 = mapCopy.keySet().iterator();		// Write words in the file
			while (itr_2.hasNext()) {
				String word = (String)itr_2.next();
				pw.println("Palabra:" + word + " Frec:" + mapCopy.get(word) + " LogProb:");
			}
			
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void readWords(String file, ArrayList<String> array) {
		try {
			BufferedReader buf = new BufferedReader(new FileReader(file));
			String line;
			
			while ((line = buf.readLine()) != null) {
				/*line = line.substring(6,line.length());					// Eliminamos "Texto:" del string
				line = line.replaceAll("#.*", "");						// Eliminamos "#x" del string
				line = line.replaceAll("@.+", "");						// Eliminamos "@x" del string
				line = line.replaceAll("http.+", "");					// Eliminamos enlaces
				line = line.replaceAll("[\\[|\\]|$|=|%|+|\\-|/|;|)|(|.|,|*|!|?|{|}|~|'|:|_]+", "");		
																		// Eliminamos símbolos de puntuación del string
				line = line.replaceAll("&amp", "");						// Eliminamos "&amp" del string
				line = line.replaceAll("&gt", "");						// Eliminamos "&gt" del string
				line = line.replaceAll("&lt", "");						// Eliminamos "&lt" del string
				line = line.replaceAll("\\d+.*", "");					// Eliminamos cadenas de números del string*/
				line = filterWords.filterLine(line);
				
				StringTokenizer tokensLine = new StringTokenizer(line);
				while (tokensLine.hasMoreTokens()) {
					String token = tokensLine.nextToken();
					if (token.length() > 2) {							// Eliminamos palabras con menos de 3 letras.
						array.add(token);
					}
				}
			}
			buf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
