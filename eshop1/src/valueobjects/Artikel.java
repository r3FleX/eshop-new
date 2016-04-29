package valueobjects;

//import valueobjects.Artikel;


public class Artikel {

	// Attribute zur Beschreibung der Artikel

	private String artname;
	private int bestand;
	private int nummer;
	private float preis;
	private int packungsgroesse;
	public String getArtname() {
		return artname;
	}

	public void setArtname(String artname) {
		this.artname = artname;
	}

	public int getPackungsgroesse() {
		return packungsgroesse;
	}

	public void setPackungsgroesse(int packungsgroesse) {
		this.packungsgroesse = packungsgroesse;
	}

	public boolean isMassengut() {
		return massengut;
	}

	public void setMassengut(boolean massengut) {
		this.massengut = massengut;
	}

	public void setNummer(int nummer) {
		this.nummer = nummer;
	}

	private boolean massengut;

	public Artikel(String artname, int artnr, int artbestand, float artpreis, int packungsgroesse) {
		this.nummer = artnr;
		this.artname = artname;
		this.bestand = artbestand;
		this.preis = artpreis;
		this.packungsgroesse = packungsgroesse;
	}

	public Artikel(Artikel a) {
		// Copy-Konstruktor
		this(a.artname, a.nummer, a.bestand, a.preis, a.packungsgroesse);
	}

	public String toString() {

		return ("Artikelnummer: " + nummer + " / Artikelname: " + artname + " / " + "Bestand: " + bestand + " / "
				+ "Preis: " + preis + " " + '\u20ac');
	}

	public boolean equals(Object andererArtikel) {
		if (andererArtikel instanceof Artikel)
			return ((nummer == ((Artikel) andererArtikel).nummer) && (artname
					.equals(((Artikel) andererArtikel).artname)));
		else
			return false;
	}

	// Getter und Setter
	public int getNummer() {
		return nummer;
	}

	public String getName() {
		return artname;
	}

	public int getBestand() {
		return bestand;
	}

	public void setBestand(int bestand) {
		this.bestand = bestand;
	}

	public float getPreis() {
		return preis;
	}

	public void setPreis(float preis) {
		this.preis = preis;
	}

}