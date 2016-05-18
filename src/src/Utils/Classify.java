package Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Classify {
	Filter filterWords = new Filter();
	TreeMap<String, Double> aprendizajeRel = new TreeMap<String, Double>();
	private int numberWordsRel = 0;
	TreeMap<String, Double> aprendizajeNRel = new TreeMap<String, Double>();
	private int numberWordsNrel = 0;
	
	public Classify(String fileAprendizajeRel, String fileAprendizajeNrel, String inputFile, String nameOutput) {
		// Cargamos los TreeMap con los ficheros a utilizar
		createAprendizRel(fileAprendizajeRel, aprendizajeRel, "Rel");
		createAprendizRel(fileAprendizajeNrel, aprendizajeNRel, "Nrel");
		
		// Definimos variables a comparar con la suma de los logaritmos calculados
		Double valorRel;
		Double valorNRel;
		
		try {
			BufferedReader buf = new BufferedReader(new FileReader(inputFile));
			String line;
			FileWriter fichero =  new FileWriter(nameOutput);
			PrintWriter pw = new PrintWriter(fichero);
			
			while ((line = buf.readLine()) != null) {
				line = line.substring(6,line.length());
				valorRel = 0.0;
				valorNRel = 0.0;

				StringTokenizer tokensLine = new StringTokenizer(line);
				while (tokensLine.hasMoreTokens()) {
					String token = tokensLine.nextToken();
					if (aprendizajeRel.containsKey(token)) {			
						valorRel += aprendizajeRel.get(token);
					} else {
						valorRel += Math.log((double)1/ (double)(aprendizajeRel.size() + getNumberWordsRel() + 1));
					}
					if (aprendizajeNRel.containsKey(token)) {			
						valorNRel += aprendizajeNRel.get(token);
					} else {
						valorNRel += Math.log((double)1/ (double)(aprendizajeNRel.size() + getNumberWordsNrel() + 1));
					}
				}
				valorRel += Math.log((double)4654 / (double) 10806);
				valorNRel += Math.log((double)6152 / (double) 10806);
				
				if (valorRel > valorNRel ) {			
					pw.println("Clase: rel Texto: " + line);
					
				} else {
					pw.println("Clase: nrel Texto: " + line);
				}
			}
			pw.close();
			buf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	private void createAprendizRel(String file, TreeMap<String, Double> tree, String whatIs) {
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
					} else if (token.substring(0, 4).equals("Frec")) {
						if (whatIs.equals("Rel")) {
							setNumberWordsRel(Integer.parseInt(token.substring(5)));
						} else {
							setNumberWordsNrel(Integer.parseInt(token.substring(5)));
						}
					} else if (token.substring(0, 6).equals("LogPro")) {
						token = token.substring(8);							// Cogemos la probabilidad
						token = token.replace(',', '.');					// Cambiamos la , por .
						tree.put(word, Double.parseDouble(token));
					}
				}
			}
			
			buf.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void setNumberWordsRel(int value) {
		numberWordsRel += value;
	}
	
	private int getNumberWordsRel() {
		return numberWordsRel;
	}
	
	private void setNumberWordsNrel(int value) {
		numberWordsNrel += value;
	}
	
	private int getNumberWordsNrel() {
		return numberWordsNrel;
	}
}
