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
	
	
	// Methode zur Artikelsuche anhand des Artikelnamens

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
		return fehlerliste;
	}


	//Methode zur Kaufabwicklung
	public Rechnung kaufAbwickeln(Kunde kaeufer) throws IOException{
		
		Rechnung rechnung = new Rechnung(kaeufer);
		
		return rechnung;
	}
	
	
	//Artikel entfernen
	public boolean entferneArtikel(int artnr) {
		// delegieren nach Artikelverwaltung
		return meineArtikel.entfernen(artnr);
	}

	//login Status
	public boolean getLoginStatus() {
		// -> an Accountverwaltung
		return meineAccounts.getLoginStatus();
	}
	
	//Sortiere nach Artikelnamen
	public List<Artikel> getSortierteArtikelnamen() {
		// -> an Artikelverwaltung
		return meineArtikel.getSortierteArtikelnamen();
	}
	
	//Sortiere nach Artikelnummern
	public List<Artikel> getSortierteArtikelnummern() {
		// -> an Artikelverwaltung
		return meineArtikel.getSortierteArtikelnummern();
	}
	
	//Einloggen eines Accounts
	public Account loginAccount(String name, String passwort) throws AccountExistiertNichtException {
		return meineAccounts.loginAccount(name, passwort);		
	}
	
	//Ausloggen eines Accounts
	public Account logoutAccount(String name, String passwort) {
		return meineAccounts.logoutAccount(name, passwort);
	}

	
	//Bestand ändern
	public int aendereBestand(int bestandAendern, int newBestand1) throws ArtikelExistiertNichtException {
		return meineArtikel.aendereBestand(bestandAendern, newBestand1);
		
	}
	
	//Warenkorn einfügen
	public void inWarenkorbEinfuegen(Artikel art, int anzahl, Kunde kunde) throws BestandUeberschrittenException, ArtikelExistiertNichtException {
		Warenkorb warenkorb = kunde.getWarenkorb();
		
		warenkorb.einfuegen(art, anzahl);
	}
	
	// Artikel suchen
	public Artikel artikelSuchen(int gesuchteNummer) throws BestandUeberschrittenException, ArtikelExistiertNichtException {
		return meineArtikel.artikelSuchen (gesuchteNummer);
	}
	
	
	// Artikel aus dem Warenkorb ausgeben
	public HashMap<Artikel, Integer> gibAlleArtikelAusWarenkorb(Kunde kunde) {
		Warenkorb warenkorb = kunde.getWarenkorb();
		return warenkorb.getInhalt();
	}

	//schreibe Artikeldaten
	public void schreibeArtikeldaten() throws IOException {
		meineArtikel.schreibeDaten(datei+"_A.txt");
	}
	
	//Schreibe Mitarbeiterdaten
	public void schreibeMitarbeiterdaten() throws IOException {
		meineAccounts.schreibeMitarbeiterdaten(datei+"_Mitarbeiter.txt");
	}
	
	//Kundendaten
	public void schreibeKundendaten() throws IOException {
		meineAccounts.schreibeKundendaten(datei+"_Kunde.txt");
	}
}