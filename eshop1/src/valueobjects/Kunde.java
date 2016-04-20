package valueobjects;


public class Kunde extends Account {

	// Attribute zur Beschreibung des Kunden

	private String strasse;
	private int plz;
	private String wohnort;
	private Warenkorb warenkorb = new Warenkorb();

	public Kunde(String name, String passwort, int accnummer, String strasse1, int plz1, String ort) {
		super(name, passwort, accnummer);
		this.strasse = strasse1;
		this.plz = plz1;
		this.wohnort = ort;
	}

	public String toString() {

		return ("Kunde:\n  " + accountname + " \n  " + strasse + "\n " + plz + " " + wohnort);
	}

	// Getter und Setter

	public int getPlz() {
		return plz;
	}

	public void setPlz(int plz) {
		this.plz = plz;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public String getWohnort() {
		return wohnort;
	}


	public void setWohnort(String wohnort) {
		this.wohnort = wohnort;
	}

	public Warenkorb getWarenkorb() {
		return warenkorb;
	}

	public void setWarenkorb(Warenkorb warenkorb) {
		this.warenkorb = warenkorb;
	}
}