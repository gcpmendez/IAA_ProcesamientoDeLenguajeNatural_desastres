package Utils;

public class Filter {
	public String filterLine(String line) {
		line = line.substring(6,line.length());					// Eliminamos "Texto:" del string
		line = line.replaceAll("#.*", "");						// Eliminamos "#x" del string
		line = line.replaceAll("@.+", "");						// Eliminamos "@x" del string
		line = line.replaceAll("http.+", "");					// Eliminamos enlaces
		line = line.replaceAll("[\\[|\\]|$|=|%|+|\\-|/|;|)|(|.|,|*|!|?|{|}|~|'|:|_]+", "");		
																// Eliminamos símbolos de puntuación del string
		line = line.replaceAll("&amp", "");						// Eliminamos "&amp" del string
		line = line.replaceAll("&gt", "");						// Eliminamos "&gt" del string
		line = line.replaceAll("&lt", "");						// Eliminamos "&lt" del string
		line = line.replaceAll("\\d+.*", "");					// Eliminamos cadenas de números del string
		
		return line;
	}
}
