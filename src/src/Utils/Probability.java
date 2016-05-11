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

public class Probability {
	private Filter filterWords = new Filter();
	
	/** CONSTRUCTOR */
	public Probability(String corpusRel, String corpusNRel, TreeMap<String, Integer> map) {
		ArrayList<String> wordsCorpusRel = new ArrayList<String>();
		ArrayList<String> wordsCorpusN_Rel = new ArrayList<String>();
		
		TreeMap<String, Integer> mapCopy = new TreeMap<String, Integer>();
		mapCopy.putAll(map);							// Copy original TreeMap
		
		// FILES -> ARRAY
		readWords(corpusRel, wordsCorpusRel);			// Read words corpusrel.txt
		readWords(corpusNRel, wordsCorpusN_Rel);		// Read words corpusnrel.txt
		
		
		writeFiles("aprendizajerel.txt", mapCopy, wordsCorpusRel);		// Write aprendizajerel.txt
		writeFiles("aprendizajenrel.txt", mapCopy, wordsCorpusN_Rel);	// Write aprendizajenrel.txt
	}
	
	public void writeFiles(String file, TreeMap<String, Integer> mapCopy, ArrayList<String> array) {
		// Set all words to 0 times
		Iterator<String> itr = mapCopy.keySet().iterator();    
		while (itr.hasNext()) {
			String word = (String)itr.next();
			mapCopy.put(word, 0);
		}
		
		// Count occurrences
		for (int i = 0; i < array.size(); i++) {				
			String word = array.get(i);
			mapCopy.put(word, mapCopy.get(word) + 1);
		}
		
		// Write words in the file
		try {
			FileWriter fichero = new FileWriter(file);
			PrintWriter pw = new PrintWriter(fichero);
			
			pw.println("Número de documentos del corpus: " + Corpus.getNumberTweets());
			pw.println("Número de palabras del corpus: " + Corpus.getN());
			
			Iterator<String> itr_2 = mapCopy.keySet().iterator();		
			while (itr_2.hasNext()) {
				String word = (String)itr_2.next();
				double result = (double)(mapCopy.get(word) + 1 )/ (double)(Corpus.getN() + Corpus.getV() + 1);
				pw.println("Palabra:" + word + " Frec:" + mapCopy.get(word) + " LogProb:" + String.format("%.7f", result) );
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
