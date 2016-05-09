package valueobjects;

import java.util.Calendar;
import java.util.Date;

/** statistik für Artikel
 */

public class Stats {
	private int bestand;
	private int arklnummer;
	private Date datum = null;
	
	public Stats(int arklnummer, int bestand) {
		this.arklnummer = arklnummer;
		this.bestand = bestand;
		Calendar cal = Calendar.getInstance();
		this.datum = cal.getTime();
	}	
	public Date getDatum() {
		return this.datum;
	}
	public void setDatum(Date datum) {
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