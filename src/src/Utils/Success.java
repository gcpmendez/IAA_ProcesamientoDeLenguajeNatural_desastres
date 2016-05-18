package Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Success {
	private final int NUMBER_REL = 4654;
	
	public Success() {
		int relevantes = 0;
		
		try {
			BufferedReader buf = new BufferedReader(new FileReader("clasificacion.txt"));
			String line;
			int numberLine = 0;
			
			while (numberLine <= NUMBER_REL) {
				line = buf.readLine();
				
				StringTokenizer tokensLine = new StringTokenizer(line);
				String token = tokensLine.nextToken();
				token = tokensLine.nextToken();
				if (token.equals("rel")) {
					relevantes++;
				}
				numberLine++;
			}
			buf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Porcentage de exito del clasificador es: " + (relevantes / (double)4654) * 100 + " %");
	}
}
