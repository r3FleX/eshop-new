package valueobjects;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Vector;

/**
 * Klasse zur Repr�sentation der Rechnung.
 */

public class Rechnung {

	// Attribute zur Beschreibung einer Rechnung

	private Kunde kunde = null;
	private Date datum = null;
	private HashMap<Artikel, Integer> positionen = null;
	private float gesamtpreis = 0.0f;

	public Rechnung(Kunde kunde) {
		this.kunde = kunde;

		// Rechnungsdatum erstellen
		Calendar cal = Calendar.getInstance();
		this.datum = cal.getTime();
		this.positionen = kunde.getWarenkorb().getInhalt();

		this.berechneGesamtpreis();
	}

	
	  
	//Methode zum Berechnen des Gesamtpreises
	private void berechneGesamtpreis() {
		// 1. Schritt: Artikel extrahieren
		Set<Artikel> articles = this.positionen.keySet();

		// 2. Schritt: Siehe Schmierzettel
		for (Artikel artikel : articles) {
			int anzahl = this.positionen.get(artikel);

			this.gesamtpreis = this.gesamtpreis + (anzahl * artikel.getPreis());
		}
	}


	public Kunde getKunde() {
		return kunde;
	}

	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public HashMap<Artikel, Integer> getPositionen() {
		return positionen;
	}

	public void setPositionen(HashMap<Artikel, Integer> positionen) {
		this.positionen = positionen;
	}

	public float getGesamtpreis() {
		return gesamtpreis;
	}

	public void setGesamtpreis(float gesamtpreis) {
		this.gesamtpreis = gesamtpreis;
	}
}