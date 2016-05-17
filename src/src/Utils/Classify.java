package Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Classify {
	Filter filterWords = new Filter();
	TreeMap<String, Double> aprendizajeRel = new TreeMap<String, Double>();
	TreeMap<String, Double> aprendizajeNRel = new TreeMap<String, Double>();
	
	public Classify(String fileAprendizajeRel, String fileAprendizajeNrel, String inputFile, String nameOutput) throws IOException {
		// Cargamos los TreeMap con los ficheros a utilizar
		createAprendizRel(fileAprendizajeRel, aprendizajeRel);
		createAprendizRel(fileAprendizajeNrel, aprendizajeNRel);
		
		// Definimos variables a comparar con la suma de los logaritmos calculados
		Double valorRel;
		Double valorNRel;
		
		try {
			BufferedReader buf = new BufferedReader(new FileReader(inputFile));
			String line;
			FileWriter fichero =  new FileWriter(nameOutput);
			PrintWriter pw = new PrintWriter(fichero);
			String line2;
			
			while ((line = buf.readLine()) != null) {
				line2 = buf.readLine();
				line = filterWords.filterLine(line);					// Filtrado de palabras
				valorRel = 0.0;
				valorNRel = 0.0;
				
				StringTokenizer tokensLine = new StringTokenizer(line);
				while (tokensLine.hasMoreTokens()) {
					String token = tokensLine.nextToken();
					if (token.length() > 2) {							// Eliminamos palabras con menos de 3 letras.
						if (aprendizajeRel.containsKey(token)) {			
							valorRel = valorRel + aprendizajeRel.get(token);
						} 
						if (aprendizajeNRel.containsKey(token)) {			
							valorNRel = valorNRel + aprendizajeNRel.get(token);
						} 
					}
				}
				if (valorRel >= valorNRel ) {			
					pw.println("Clase: rel Texto: " + line2 );
					
				} else {
					pw.println("Clase: nrel Texto: " + line2);
				}
			}
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
