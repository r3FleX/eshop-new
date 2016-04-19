package domain;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

//import domain.exceptions.ArtikelExistiertBereitsException;

// import bib.local.domain.exceptions.BuchExistiertBereitsException;
import domain.Artikelverwaltung;
import domain.exceptions.AccountExistiertBereitsException;
import domain.exceptions.AccountExistiertNichtException;
import domain.exceptions.ArtikelExistiertBereitsException;
import domain.exceptions.ArtikelExistiertNichtException;
import domain.exceptions.BestandUeberschrittenException;
import ui.CUI;
import valueobjects.Account;
import valueobjects.Artikel;
import valueobjects.Kunde;
import valueobjects.Mitarbeiter;
import valueobjects.Rechnung;
import valueobjects.Warenkorb;



public class Shopverwaltung {

	// Verwaltungsklassen fuer Artikel, Accounts etc.
	private Artikelverwaltung meineArtikel;
	private BenutzerVerwaltung meineAccounts;
	private Rechnungsverwaltung meineRechnungen;
	
	// Namen der Dateien, in der die Daten des Shops gespeichert sind
	private String datei = "";

	// Konstrukter
	public Shopverwaltung(String datei) throws IOException {
		this.datei = datei;

		//Artikelbestand einlesen
		meineArtikel = new Artikelverwaltung();
		meineArtikel.liesDaten(datei+"_A.txt");
		//Accounts einlesen
		meineAccounts = new BenutzerVerwaltung();  
		meineAccounts.liesKundendaten(datei+"_Kunde.txt");
		meineAccounts.liesMitarbeiterdaten(datei+"_Mitarbeiter.txt");
		// Rechnungen einlesen
		meineRechnungen = new Rechnungsverwaltung();
		
	}

	
	public List<Artikel> gibAlleArtikel() {
		// -> an Artikelverwaltung
		return meineArtikel.getArtikelBestand();
	}
	
	/*
	// Methode zur Artikelsuche anhand des Artikelnamens
	public List<Artikel> sucheNachArtikel(String artname) {
		//delegieren an meineArtikel (Artikelverwaltung)
		return meineArtikel.sucheArtikel(artname);
	}
	*/
	
	// Füge Artikel ein
	public boolean fuegeArtikelEin(String artname, int artnr, int artbestand, float preis) throws ArtikelExistiertBereitsException{
		Artikel a = new Artikel(artname, artnr, artbestand, preis);
		meineArtikel.einfuegen(a);
		return true;
	}

	//Füge Mitarbeiter Account ein	
	public boolean fuegeMitarbeiterAccountEin(String name, String passwort) throws AccountExistiertBereitsException{
			
			// Accountnummer wird zugewiesen (AccountBestand + 1)
			int accnummer = meineAccounts.getAccountBestand().size()+1;
			
			Mitarbeiter mitarbeiter = new Mitarbeiter(name, passwort, accnummer);
			try {
				meineAccounts.MitarbeiterEinfuegen(mitarbeiter);
			} catch (AccountExistiertBereitsException e) {
				return false;
			}
			return true;
		}
	
	//Füge Kunden Account ein	
	public boolean fuegeKundenAccountEin(String name, String passwort, String strasse, int plz, String ort) throws AccountExistiertBereitsException {
		
		// Accountnummer wird zugewiesen (AccountBestand + 1)
		int accnummer = meineAccounts.getAccountBestand().size()+1;
		
		Kunde kunde = new Kunde(name, passwort, accnummer, strasse, plz, ort);
		try {
			meineAccounts.KundeEinfuegen(kunde);
		} catch (AccountExistiertBereitsException e) {
			return false;
		}
		return true;
	}
	
	
	//Methode zur Überprüfung des Warenkorbs zum Kauf (Bestandsabfragen etc.)
	
	
	public HashMap<Artikel, Integer> pruefeKauf(Kunde user) {
		HashMap<Artikel, Integer> fehlerliste = new HashMap<Artikel, Integer>();
		HashMap<Artikel, Integer> pruefkorb = user.getWarenkorb().getInhalt();
		
		if (!pruefkorb.isEmpty()){
		Set<Artikel> articles = pruefkorb.keySet();
		for(Artikel artikel : articles) {
			int anzahl = (Integer) pruefkorb.get(artikel);
			
			if ((artikel.getBestand() - anzahl) < 0) {
				fehlerliste.put(artikel, anzahl);
			}
			/*else {
				if (artikel.getPackungsgroesse() > 0 && anzahl%artikel.getPackungsgroesse() != 0) {
					fehlerliste.put(artikel, anzahl);
				}
			}	*/	
		}
		if (!fehlerliste.isEmpty()) {
			Set<Artikel> articlos = fehlerliste.keySet();
			for(Artikel artikel : articlos) {
				user.getWarenkorb().loeschen(artikel);
				
			}
		}
	}	
		return fehlerliste;
	}

	/**
	 * Methode zur Kaufabwicklung
	 * 
	 * @param kaeufer
	 * @param warenkorbBestand
	 * @throws IOException 
	 */

	public Rechnung kaufAbwickeln(Kunde kaeufer) throws IOException{
		
		Rechnung rechnung = new Rechnung(kaeufer);
		
		@SuppressWarnings("unchecked")
		Set<Artikel> articles = kaeufer.getWarenkorb().getInhalt().keySet();
		
		for(Artikel artikel : articles) {
			int anzahl = (Integer) kaeufer.getWarenkorb().getInhalt().get(artikel);
			
			artikel.setBestand(artikel.getBestand() - anzahl);
			schreibeArtikeldaten();
		}
		
		kaeufer.getWarenkorb().leeren();
		
		return rechnung;
	}
	
	/**
	 * Methode zum Entfernen eines Artikels
	 * 
	 * @param artnr
	 * @return
	 */
	
	public boolean entferneArtikel(int artnr) {
		// delegieren nach Artikelverwaltung
		return meineArtikel.entfernen(artnr);
	}

	/**
	 * Methoden zum Abfragen des LoginStatus (Kunde oder Mitarbeiter)
	 * 
	 * @return
	 */
	
	public boolean getLoginStatus() {
		// delegieren nach Accountverwaltung
		return meineAccounts.getLoginStatus();
	}
	
	/**
	 * Methode zum Sortieren nach Artikelnamen
	 * 
	 * @return
	 */

	public List<Artikel> getSortierteArtikelnamen() {
		// delegieren nach Artikelverwaltung
		return meineArtikel.getSortierteArtikelnamen();
	}
	
	/**
	 * Methode zum Sortieren nach Artikelnummern
	 * 
	 * @return
	 */

	public List<Artikel> getSortierteArtikelnummern() {
		// delegieren nach Artikelverwaltung
		return meineArtikel.getSortierteArtikelnummern();
	}
	
	/**
	 * Methode zum Einloggen eines Accounts
	 * 
	 * @param name 
	 * @param passwort
	 * @return
	 * @throws AccountExistiertNichtException
	 */
	
	public Account loginAccount(String name, String passwort) throws AccountExistiertNichtException {
		return meineAccounts.loginAccount(name, passwort);		
	}
	
	/**
	 * Methode zum Ausloggen eines Accounts
	 * 
	 * @param name
	 * @param passwort
	 * @return
	 */
	
	public Account logoutAccount(String name, String passwort) {
		return meineAccounts.logoutAccount(name, passwort);
	}

	/**
	 * Methode zum ï¿½ndern des Bestands
	 * 
	 * @param bestandAendern
	 * @param newBestand1
	 * @return
	 * @throws ArtikelExistiertNichtException
	 */
	
	public int aendereBestand(int bestandAendern, int newBestand1) throws ArtikelExistiertNichtException {
		return meineArtikel.aendereBestand(bestandAendern, newBestand1);
		
	}
	
	/**
	 * Methode, um Artikel in den Warenkorb einzufï¿½gen
	 * 
	 * @param art Artikel
	 * @param anzahl Artikelanzahl
	 * @param kunde Kunde
	 * @throws BestandUeberschrittenException
	 * @throws ArtikelExistiertNichtException
	 */
	
	public void inWarenkorbEinfuegen(Artikel art, int anzahl, Kunde kunde) throws BestandUeberschrittenException, ArtikelExistiertNichtException {
		Warenkorb warenkorb = kunde.getWarenkorb();
		
		warenkorb.einfuegen(art, anzahl);
	}
	
	/**
	 * Methode zum Suchen nach Artikeln
	 * 
	 * @param gesuchteNummer
	 * @return
	 * @throws BestandUeberschrittenException
	 * @throws ArtikelExistiertNichtException
	 */
	
	public Artikel artikelSuchen(int gesuchteNummer) throws BestandUeberschrittenException, ArtikelExistiertNichtException {
		return meineArtikel.artikelSuchen (gesuchteNummer);
	}
	
	/**
	 * Methode, um alle Artikel aus dem Warenkorb anzugeben
	 * 
	 * @param kunde
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public HashMap<Artikel, Integer> gibAlleArtikelAusWarenkorb(Kunde kunde) {
		Warenkorb warenkorb = kunde.getWarenkorb();
		
		return warenkorb.getInhalt();
	}

	/**
	 * Methode zum Schreiben der Artikeldaten
	 * 
	 * @throws IOException
	 */
	
	public void schreibeArtikeldaten() throws IOException {
		meineArtikel.schreibeDaten(datei+"_A.txt");
	}
	
	/**
	 * Methode zum Schreiben der Mitarbeiterdaten
	 * 
	 * @throws IOException
	 */
	
	public void schreibeMitarbeiterdaten() throws IOException {
		meineAccounts.schreibeMitarbeiterdaten(datei+"_Mitarbeiter.txt");
	}
	
	/**
	 * Methode zum Schreiben der Kundendaten
	 * 
	 * @throws IOException
	 */
	
	public void schreibeKundendaten() throws IOException {
		meineAccounts.schreibeKundendaten(datei+"_Kunde.txt");
	}
}