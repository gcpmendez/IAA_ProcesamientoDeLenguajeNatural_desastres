package Utils;

public class Filter {
	public String filterLine(String line) {
		/** Posibles reemplazos, entrada de datos grande
		 * 	i, me, my, myself, we, our, ours, ourselves, you, your, yours, 
		 *  	yourself, yourselves, he, him, his, himself, she, her, hers, 
		 *  	herself, it, its, itself, they, them, their, theirs, themselves, 
		 *  	herself, it, its, itself, they, them, their, theirs, themselves, 
		 *  	what, which, who, whom, this, that, these, those, am, is, are, was,
		 *  	were, be, been, being, have, has, had, having, do, does, did, doing, 
		 *  	a, an, the, and, but, if, or, because, as, until, while, of, at, by, 
		 *  	for, with, about, against, between, into, through, during, before, 
		 *  	after, above, below, to, from, up, down, in, out, on, off, over, 
		 *  	under, again, further, then, once, here, there, when, where, why, 
		 *  	how, all, any, both, each, few, more, most, other, some, such, no, 
		 *  	nor, not, only, own, same, so, than, too, very, s, t, can, will, just, 
		 *  	don, should, now
		 */
		line = line.toLowerCase();								// Conversión a minúsculas
		line = line.substring(6,line.length());					// Eliminamos "Texto:" del string
		line = line.replaceAll("#.*", "");						// Eliminamos "#x" del string
		line = line.replaceAll("@.+", "");						// Eliminamos "@x" del string
		line = line.replaceAll("http.+", "");					// Eliminamos enlaces
		line = line.replaceAll("[`|\\[|\\]|$|=|%|+|\\-|/|;|)|(|.|,|*|!|?|{|}|~|'|:|_]+", "");		
		line = line.replace("\\", ""); 							// Eliminamos "\"
		line = line.replaceAll("&amp", "");						// Eliminamos "&amp" del string
		line = line.replaceAll("&gt", "");						// Eliminamos "&gt" del string
		line = line.replaceAll("&lt", "");						// Eliminamos "&lt" del string
		line = line.replaceAll("\\d+.*", "");					// Eliminamos cadenas de números del string
		
		return line;
	}
}
