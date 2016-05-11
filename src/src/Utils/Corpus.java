package Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Corpus {
	private static int N;			// Número total de palabras del corpus.
	private static int V;			// Número total de palabras del vocabulario creado.
	private static int NumberTweets; // Número total de tweets.
	private Filter filterWords = new Filter();
	
	/** GETTERS AND SETTERS */
	public static int getN() {
		return N;
	}

	public void setN(int n) {
		N = n;
	}
	
	public static int getV() {
		return V;
	}

	public void setV(int v) {
		V = v;
	}
	
	public static int getNumberTweets() {
		return NumberTweets;
	}

	public static void setNumberTweets(int numberTweets) {
		NumberTweets = numberTweets;
	}
	
	/** CONSTRUCTS */
	public Corpus(String route, TreeMap<String, Integer> map) {
		setV(0);
		setN(0);
		setNumberTweets(0);
		
		try {
			BufferedReader buf = new BufferedReader(new FileReader(route));
			String line;
			FileWriter fichero =  new FileWriter("vocabulario.txt");
			PrintWriter pw = new PrintWriter(fichero);
			
			while ((line = buf.readLine()) != null) {
				line = filterWords.filterLine(line);					// Filtrado de palabras
				
				StringTokenizer tokensLine = new StringTokenizer(line);
				while (tokensLine.hasMoreTokens()) {
					String token = tokensLine.nextToken();
					if (token.length() > 2) {							// Eliminamos palabras con menos de 3 letras.
						if (map.containsKey(token)) {				
							map.put(token, map.get(token) + 1);
						} else {
							map.put(token, 1);
						}
					}
					N++;
				}
				NumberTweets++;
			}
			
			pw.println("Número de palabras:" + map.size());     // Número de palabras: <número entero>
			this.setV(map.size());
			Iterator<String> itr = map.keySet().iterator();
			while (itr.hasNext()) {     
				String word = itr.next();
				pw.println("Palabra:" + word);	// Palabra: <cadena>
			}
			buf.close();
			pw.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}







}
