package domain;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import domain.exceptions.StatExistiertBereitsException;
import persistence.FilePersistenceManager;
import persistence.PersistenceManager;
import valueobjects.Stats;

public class StatsVerwaltung {
	// Verwaltung des Artikelbestands als Liste
	// Als Implementierung der Liste dient ein Vektor

	private List<Stats> alleStats = new Vector<Stats>();

	
	// Persistenz-Schnittstelle, die fuer die Details des Dateizugriffs
	// verantwortlich ist
	
	private PersistenceManager pm = new FilePersistenceManager();

	public StatsVerwaltung() {
		
	}
	/**
	 * Methode zum Einlesen von Artikeldaten aus einer Datei.
	 * 
	 * @param datei Datei, die einzulesenden Artikelbestand enthaelt
	 * @throws IOException
	 */

	public void liesDaten(String datei) throws IOException {
		// PersistenzManager fuer Lesevorgaenge oeffnen
		pm.openForReading(datei);

		Stats eineStat;
/*TODO		do {
			// Artikel-Objekt einlesen
			eineStat = pm.ladeStats();

			if (eineStat != null) {
				//
				try {
					einfuegen(eineStat);					
				} catch (StatExistiertBereitsException e1) {
					//
					// Kann hier eigentlich nicht auftreten,
					// daher auch keine Fehlerbehandlung...
					//
				}
			}
		} while (eineStat != null);
*/
		// Persistenz-Schnittstelle wieder schliessen
		pm.close();
	}
}
