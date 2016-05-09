package domain;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import domain.exceptions.StatExistiertBereitsException;
import persistence.FilePersistenceManager;
import persistence.PersistenceManager;
import valueobjects.Artikel;
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
		do {
			// Artikel-Objekt einlesen
			eineStat = pm.ladeStats();
			if (eineStat != null) {
				einfuegen(eineStat);
			}
		} while (eineStat != null);
		// Persistenz-Schnittstelle wieder schliessen
		pm.close();
	}
	/**
	 * Methode zum Schreiben der Artikeldaten
	 * 
	 * @param datei Datei, in die der Artikelbestand geschrieben werden soll
	 * @throws IOException
	 */	
	public void schreibeDaten(String datei) throws IOException {
		// PersistenzManager fÃ¼r SchreibvorgÃ¤nge oeffnen
		pm.openForWriting(datei);

		Iterator<Stats> iter = alleStats.iterator();
		while (iter.hasNext()) {
			Stats s = iter.next();
			pm.speichereStats(s);
		}
		// Persistenz-Schnittstelle wieder schlieÃŸen
		pm.close();
	}
	/**
	 * Methode, die einen Artikel an das Ende der Artikeliste einfuegt.
	 * 
	 * @param einArtikel der einzufuegende Artikel
	 * @throws ArtikelExistiertBereitsException wenn der Artikel bereits existiert
	 */
	public void einfuegen(Stats eineStat) {
		alleStats.add(eineStat);
	}
	public void statupdate(int artklnummer,String name, int bestand, String type) {
		//statistik daten einfügen
		//datum als string einfügen
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date today = Calendar.getInstance().getTime();        
		String datum = df.format(today);
		Stats stat = new Stats(artklnummer,name, bestand,datum,type);
		einfuegen(stat);
	}	
}
