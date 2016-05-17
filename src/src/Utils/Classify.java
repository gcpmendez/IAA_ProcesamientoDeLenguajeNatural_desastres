package Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Classify {
	Filter filterWords = new Filter();
	TreeMap<String, Double> aprendizajeRel = new TreeMap<String, Double>();
	TreeMap<String, Double> aprendizajeNRel = new TreeMap<String, Double>();
	
	public Classify(String fileAprendizajeRel, String fileAprendizajeNrel/*, String inputFile, String nameOutput*/) {
		createAprendizRel(fileAprendizajeRel, aprendizajeRel);
		createAprendizRel(fileAprendizajeNrel, aprendizajeNRel);
		/*try {
			BufferedReader buf = new BufferedReader(new FileReader(inputFile));
			String line;
			FileWriter fichero =  new FileWriter(nameOutput);
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
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	private void createAprendizRel(String file, TreeMap<String, Double> tree) {
		try {
			BufferedReader buf = new BufferedReader(new FileReader(file));
			String line;
			
			line = buf.readLine();	// Eliminamos las dos primeras lineas
			line = buf.readLine();
			
			while ((line = buf.readLine()) != null) {
				
				StringTokenizer tokensLine = new StringTokenizer(line);
				String word = "";
				while (tokensLine.hasMoreTokens()) {
					String token = tokensLine.nextToken();
					
					if (token.substring(0, 6).equals("Palabr")) {							// Eliminamos palabras con menos de 3 letras.
						token = token.substring(8);							// Cogemos la palabra
						word = token;
						//System.out.println(token);
					} else if (token.substring(0, 6).equals("LogPro")) {
						token = token.substring(8);							// Cogemos la probabilidad
						token = token.replace(',', '.');					// Cambiamos la , por .
						tree.put(word, Double.parseDouble(token));
						//System.out.println(token);
					}
				}
			}
			
			buf.close();
			
			/*Iterator<String> itr = tree.keySet().iterator();    
			while (itr.hasNext()) {
				String word = (String)itr.next();
				System.out.println("Palabra:" + word + " Prob:" + tree.get(word));
			}*/
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
