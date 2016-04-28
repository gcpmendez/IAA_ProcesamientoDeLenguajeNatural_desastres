package Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Parser {
	TreeMap<String, Integer> map = new TreeMap<String, Integer>();
	
	public Parser(String route) {
		try {
			BufferedReader buf = new BufferedReader(new FileReader(route));
			String line;
			FileWriter fichero =  new FileWriter("vocabulario.txt");
			PrintWriter pw = new PrintWriter(fichero);
			
			while ((line = buf.readLine()) != null) {
				line = line.substring(6,line.length());						// Eliminamos "Texto:" del string
				StringTokenizer tokensLine = new StringTokenizer(line);
				while (tokensLine.hasMoreTokens()) {
					String token = tokensLine.nextToken();
					if (map.containsKey(token)) {				
						map.put(token, map.get(token) + 1);
					} else {
						map.put(token, 1);
					}
				}
			}
			
			pw.println("Número de palabras:" + map.size());     // Número de palabras: <número entero>
			String extract = "";
			Iterator<String> itr = map.keySet().iterator();
			while (itr.hasNext()) {     						
				extract += (String) itr.next() + " ";
			}
			pw.println("Palabra:" + extract);					// Palabra: <cadena>
			buf.close();
			pw.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
