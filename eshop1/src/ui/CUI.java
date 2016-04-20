package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import valueobjects.Account;
import valueobjects.Artikel;
import valueobjects.Kunde;
import valueobjects.Mitarbeiter;
import valueobjects.Rechnung;
import valueobjects.Warenkorb;
import domain.Shopverwaltung;
import domain.exceptions.AccountExistiertBereitsException;
import domain.exceptions.AccountExistiertNichtException;
import domain.exceptions.ArtikelExistiertBereitsException;
import domain.exceptions.ArtikelExistiertNichtException;
import domain.exceptions.BestandUeberschrittenException;

public class CUI {

	// Instanzierung der ben�tigten Objekte
	private Shopverwaltung shop;
	private BufferedReader reader;
	private Account user;

	public CUI(String datei) throws IOException {

		// Die Shop-Verwaltung erledigt, die Aufgaben,
		// die nichts mit Ein-/Ausgabe zu tun haben

		shop = new Shopverwaltung(datei);

		// Stream-Objekt fuer Texteingabe ueber Konsolenfenster erzeugen
		// Der Buffered Reader wird benutzt um die Eingabe einer einzelnen Zeile
		// zu bekommen.
		reader = new BufferedReader(new InputStreamReader(System.in));
	}

	// Startmenue: interne (private) Methode zur Ausgabe des Menues

	private void menueStart() {

		System.out.println();
		System.out.println("[Startmenue]");
		System.out.println("Los geht's!");
		System.out.println();
		System.out.println("Account anlegen: n");
		System.out.println("Login: l");
		System.out.println("Beenden: q");
		System.out.println();
		System.out.println("Eingabe: ");
		System.out.flush();
	}

	// Startmenue: interne (private) Methode zur Ausgabe des Menues f�r den Kunden
	
	private void menueKunde() {

		System.out.println();
		System.out.println("[Kundenbereich]");
		System.out.println();
		System.out.println("Artikel im Warenkorb ablegen: k");
		System.out.println("Warenkorb einsehen: w");
		System.out.println("Warenkorb - Bestellung abschliessen: b");
		System.out.println("Artikel ausgeben: a");
		System.out.println("Artikel ordnen: o");
		System.out.println("Artikel suchen: f");
		System.out.println();
		System.out.println("Ausloggen: al");
		System.out.println("");
		System.out.println("Eingabe: ");
		System.out.flush();
	}

	// Startmenue: interne (private) Methode zur Ausgabe des Menues f�r den Mitarbeiter
	
	private void menueMitarbeiter() {
		System.out.println("[Mitarbeiterbereich]");
		System.out.println();
		System.out.println("Artikel einfuegen: e");
		System.out.println("Artikel entfernen: d");
		System.out.println("Artikel ausgeben: a");
		System.out.println("Artikel ordnen: o");
		System.out.println("Artikelmenge aendern: z");
		System.out.println();
		System.out.println("Ausloggen: al");
		System.out.println();
		System.out.println("Ihre Eingabe: ");
		System.out.flush();
	}

	// Interne, private Methode zur Verarbeitung der Eingaben und Ausgaben der
	// Ergebnisse

	private String liesEingabe() throws IOException {
		return reader.readLine();
	}

	/**
	 * Methode zum Verarbeiten der Eingabe im Men�
	 * 
	 * @param line
	 * @throws IOException
	 */
	private void verarbeiteEingabe(String line) throws IOException {
		
		// Eingabe bearbeiten
		
		/**
		 * Befehl e: Artikel einf�gen, nur als Mitarbeiter
		 */
		
		if (line.equals("e")) {

			if (user instanceof Mitarbeiter) {

				// lies die notwendigen Parameter einzeln pro Zeile

				System.out.print("Artikelnummer > ");
				String nummer = liesEingabe();
				int artnr = Integer.parseInt(nummer);
				System.out.print("Artikelname  > ");
				String artname = liesEingabe();

				int artbestand;

				System.out.println("Bestand  > ");
				String bestand = liesEingabe();

				artbestand = Integer.parseInt(bestand);

				System.out.println("Einzelpreis > ");
				String preis = liesEingabe();
				float artpreis = Float.parseFloat(preis);
				boolean ok;
				
			// wenn als Kunde eingeloggt 
			} else
				System.out.println("Das darfst du nicht!");
		}

		/**
		 * Befehl a: Artikelliste wird ausgegeben
		 * 
		 */

		else if (line.equals("a")) {

			if (user != null) {
				List<Artikel> artikelListe = shop.gibAlleArtikel();
				gibArtikellisteAus(artikelListe);
			} else
				System.out.println("Bitte loggen Sie ein!");
		}

		/**
		 * Befehl d: Artikel wird entfernt, nur als Mitarbeiter
		 * 
		 */

		else if (line.equals("d")) {

			if (user instanceof Mitarbeiter) {
				List<Artikel> artikelListe = shop.gibAlleArtikel();
				gibArtikellisteAus(artikelListe);

				System.out
						.println("Artikelnummer zum entfernen des Produktes:");
				System.out.println("Eingabe >");
				String nummer = liesEingabe();
				int artnr = Integer.parseInt(nummer);

				boolean ok = shop.entferneArtikel(artnr);
				shop.schreibeArtikeldaten();

				if (ok) {
					System.out.println("Artikel wurde gelöscht.");
				} else
					System.out.println("Fehler beim Entfernen.");
				
			// wenn als Kunde eingeloggt	
			} else
				System.out.println("Darfst du nicht!");
		}

		/**
		 * Befehl o: Artikelliste sortieren nach Name oder Nummer
		 * 
		 */

		else if (line.equals("o")) {

			if (user != null) {
				System.out.println("Sortieren nach Name (x) oder Nummer (y)");
				System.out.println("Eingabe >");
				String name = liesEingabe();

				List<Artikel> artikelListe;
				// TODO: Die Sortierung sollte eigentlich nicht in der CUI
				// stehen

				if (name.equals("x")) {

					// Nach Name sortieren
					artikelListe = shop.getSortierteArtikelnamen();

					// Artikel ausgeben
					gibArtikellisteAus(artikelListe);

				} else if (name.equals("y")) {

					// Nach Nummer sortieren

					artikelListe = shop.getSortierteArtikelnummern();

					// Artikel ausgeben
					gibArtikellisteAus(artikelListe);

				} else {

					System.out.println("Ungueltige Eingabe!");
					System.out
							.println("Zum Sortieren nur Befehl x oder y moeglich.");
					System.out
							.println("Sortiervorgang wiederholen");
				}

			} else
				System.out.println("Bitte einloggen");
		}

		/**
		 * Befehl f: Artikel finden --> nach Artikelname
		 * 
		 */
		
		//Artikel suchen
		else if (line.equals("f")) {
			
		}

		/**
		 * Befehl n: Neuen Account anlegen
		 * 
		 */

		else if (line.equals("n")) {
			System.out.println("Account anlegen.");
			System.out.println("Name eingeben");
			String name = liesEingabe();
			System.out.println("Passwort eingeben");
			String passwort = liesEingabe();
			System.out
					.println("Kunde oder Mitarbeiter? Kunde = k / Mitarbeiter = m");
			String account = liesEingabe();

			// Auswahldialog mit erweiterter Eingabe
			// Kunde k

			if (account.equals("k")) {
				System.out.println("Strasse: ");
				String strasse = liesEingabe();

				int plz = 0;
				do {
					System.out.println("Postleitzahl: ");
					String plz1 = liesEingabe();
					plz = Integer.parseInt(plz1);

					if (plz > 99999 || plz < 10000) {
						System.out.println("Ungueltige Postleitzahl!");
					}
				} while (plz == 0 || plz > 99999 || plz < 10000);

				System.out.println("Wohnort: ");
				String ort = liesEingabe();
				// boolean ok =
				try {
					shop.fuegeKundenAccountEin(name, passwort, strasse, plz,
							ort);
					shop.schreibeKundendaten();
				} catch (AccountExistiertBereitsException e1) {
					System.out.println(e1.getMessage());
					System.out
							.println("Registrierungsvorgang wiederholen");
				}

			// Mitarbeiter m	
			} else if (account.equals("m")) {
				boolean ok;
				try {
					ok = shop.fuegeMitarbeiterAccountEin(name, passwort);

					if (ok) {
						try {
							int accnummer = shop.loginAccount(name, passwort)
									.getAccountNr();
							shop.schreibeMitarbeiterdaten();
							System.out
									.println("Mitarbeiter-Account wurde angelegt. Accountnummer: "
											+ accnummer + ".");
						} catch (AccountExistiertNichtException e) {
							e.printStackTrace();
						}

					} else {
						System.out.println("Fehler beim Einfuegen.");
					}

				} catch (AccountExistiertBereitsException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}

		/**
		 * Befehl l: einloggen
		 * 
		 */

		else if (line.equals("l")) {

			if (user == null) {

				System.out.println("Login:");
				System.out.println("Namen eingeben:");
				String name = liesEingabe();
				System.out.println("Passwort eingeben:");
				String passwort = liesEingabe();

				
				try {
					user = shop.loginAccount(name, passwort);
					System.out.println("Einloggen erfolgreich.");
					System.out.println("Hallo " + user.getName());

					do {

						// Accountunterscheidung Mitarbeiter bzw Kunde
						if (user instanceof Mitarbeiter)
							menueMitarbeiter();
						else if (user instanceof Kunde)

							menueKunde();

						try {
							line = liesEingabe();
							verarbeiteEingabe(line);
						} catch (IOException e) {
							e.printStackTrace();
						}

					} while (!line.equals("q"));


				} catch (AccountExistiertNichtException e) {

				}
			} else
				System.out.println("Sie sind bereits eingeloggt!");

		}

		/**
		 * Befehl k: Artikel kaufen, nur als Kunde
		 * 
		 */
		else if (line.equals("k")) {

			if (user instanceof Kunde) {
				List<Artikel> artikelListe = shop.gibAlleArtikel();
				gibArtikellisteAus(artikelListe);

				System.out.println("Welchen Artikel wollen Sie in den Warenkorb legen? Bitte Artikelnummer eingeben.");

				try {

					String kaufen = liesEingabe();
					int einkaufen = Integer.parseInt(kaufen);
					Artikel a = shop.artikelSuchen(einkaufen);
					if (a==null)

					System.out.println("Wie oft wollen Sie den Artikel kaufen?");

					String anzahl = liesEingabe();
					int anzahl1 = Integer.parseInt(anzahl);
					
					// Artikel in den Warenkorb einfuegen
					shop.inWarenkorbEinfuegen(shop.artikelSuchen(einkaufen),anzahl1, (Kunde) user);
					System.out.println("Artikel in den Warenkorb gelegt.");
				} catch (BestandUeberschrittenException e) {
					// Text siehe Exception

				} catch (ArtikelExistiertNichtException e) {
					System.out.println(e.getMessage());
				}
				
			// wenn als Mitarbeiter eingeloggt
			} else
				System.out.println("Bitte loggen Sie sich fuer diesen Vorgang als"
								+ "Kunde ein!");
		}
		
		/**
		 * Befehl w: Warenkorb anzeigen, nur als Kunde
		 * 
		 */

		else if (line.equals("w")) {

			if (user instanceof Kunde) {
				System.out.println("Der Inhalt des Warenkorbes:");

				Kunde kunde = (Kunde) user;

				Warenkorb warenkorb = kunde.getWarenkorb();

				Set<Artikel> articles = warenkorb.getInhalt().keySet();

				for (Artikel artikel : articles) {
					int anzahl = (Integer) warenkorb.getInhalt().get(artikel);

					float artikelGesamtpreis = (anzahl * artikel.getPreis());
					System.out.println(artikel.getName() + " - Anzahl: "
							+ anzahl + " - Einzelpreis: " + artikel.getPreis()
							+ " - Artikel-Gesamtpreis: " + artikelGesamtpreis);
				}
				
			// wenn als Mitarbeiter eingeloggt	
			} else
				System.out.println("Bitte loggen Sie sich f�r diesen Vorgang als"
								+ "Kunde ein!");

		}

		/**
		 * Befehl z: Bestand �ndern, nur als Mitarbeiter
		 * 
		 */
		
		else if (line.equals("z")) {
			//Bestand �ndern
		}

		/**
		 * Befehl al: User ausloggen
		 * 
		 */
		
		else if (line.equals("al")) {
			user = shop.logoutAccount(user.getName(), user.getPasswort());
			menueStart();
		}

		/**
		 * Befehl b: Bestellung abschliessen, Kauf abewickeln nur als Kunde
		 * 
		 */
		
		// Bestellung abschliessen
	}

	// Ausgabe des Warenkorbbestandes
	private void gibWarenbestandAus(HashMap<Artikel, Integer> warenkorbBestand) {
		if (warenkorbBestand.isEmpty()) {
			System.out.println("Liste ist leer.");
		} else {
			Iterator<Artikel> iter = warenkorbBestand.keySet().iterator();
			while (iter.hasNext()) {
				Artikel artikel = iter.next();
				System.out.println(artikel.toString());
			}
		}
	}

	private void gibArtikellisteAus(List<Artikel> artikel2) {
		if (artikel2.isEmpty()) {
			System.out.println("Liste ist leer.");
		} else {
			Iterator<Artikel> iter = artikel2.iterator();
			while (iter.hasNext()) {
				Artikel artikel = iter.next();
				System.out.println(artikel.toString());
			}
		}
	}

	public void run() throws IOException {
		// Variable fuer Eingaben von der Konsole
		String input = "";

		// Hauptschleife der Benutzungsschnittstelle
		do {
			if (user == null) {
				menueStart();
			}
			try {
				input = liesEingabe();
				verarbeiteEingabe(input);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} while (!input.equals("q"));
	}

	// Die main-Methode
	// .....

	public static void main(String[] args) {

		CUI cui;
		try {
			cui = new CUI("Shop");
			cui.run();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}