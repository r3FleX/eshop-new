package valueobjects;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/** statistik für Artikel
 */

public class Stats {
	private int arklnummer;
	private int bestand;
	private String datum = null;
	
	/** Erstellt ein Statistik objekt 
	 * 
	 * @param arklnummer
	 * @param bestand
	 */
	public Stats(int arklnummer, int bestand,String datum) {
		this.arklnummer = arklnummer;
		this.bestand = bestand;
		//datum als string einfügen
	//	DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	//	Date today = Calendar.getInstance().getTime();        
	//	this.datum = df.format(today);
		this.datum = datum;
	}	
	public String getDatum() {
		return this.datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public int getArklnummer() {
		return this.arklnummer;
	}
	public void setArklnummer(int arklnummer) {
		this.arklnummer = arklnummer;
	}
	public int getBestand() {
		return this.bestand;
	}
	public void setBestand(int bestand) {
		this.bestand = bestand;
	}
}