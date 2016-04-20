package valueobjects;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Klasse zur Repräsentation des Warenkorbs.
 */

public class Warenkorb {

	// Verwaltung des Warenkorbbestands als Liste


	private HashMap<Artikel, Integer> inhalt = new HashMap<Artikel, Integer>();

	/**
	 * Konstruktor
	 * 
	 */

	public Warenkorb() {

	}

	public void einfuegen(Artikel a, int anzahl) {
		//bevor artikel kaufen: pruefe ob massenartik. oder ...
		inhalt.put(a, anzahl);
	}


	public void loeschen(Artikel a) {
		inhalt.remove(a);
	}


	public void leeren() {
		inhalt.clear();
	}

	public HashMap getInhalt() {
		return inhalt;
	}


	public void setWarenkorbInhalt(HashMap Inhalt) {
		this.inhalt = Inhalt;
	}

	public void wirdGekauft() {

	}
}