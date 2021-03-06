package ui.GuiModule;

import java.awt.GridLayout;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ui.ArtikelTableModel;
import valueobjects.Artikel;

public class Gui_artikelpanel {
	
	private JPanel artikelPanel;
	private ArtikelTableModel artikeltable;
	public Gui_artikelpanel(List<Artikel> artikelliste) {
		//Artikel Bereich
		JPanel artikelPanel = new JPanel();
		artikelPanel.setLayout(new GridLayout());
		artikelPanel.setBorder(BorderFactory.createTitledBorder("Artikel")); //Überschrift Artikel
				
		Vector spalten = new Vector();		
		spalten.add("Nummer");
		spalten.add("Name");
		spalten.add("Bestand");
		spalten.add("Preis");
		spalten.add("Packungsgröße");
		spalten.add("Massengut");
		
		// TableModel als "Datencontainer" anlegen:
		artikeltable = new ArtikelTableModel(new Vector<Artikel>(), spalten);
		
		// JTable-Objekt erzeugen und mit Datenmodell initialisieren:
		JTable ausgabeTabelle = new JTable(artikeltable);
		
		// JTable in ScrollPane platzieren:
		JScrollPane scrollPane = new JScrollPane(ausgabeTabelle);
				
		// Anzeige der Artikelliste auch in der Kunden-Ansicht
		artikeltable.setDataVector(artikelliste);
		artikelPanel.add(scrollPane);
		
		setArtikelPanel(artikelPanel);
	}
	public ArtikelTableModel getArtikeltable() {
		return artikeltable;
	}
	public void setArtikeltable(ArtikelTableModel artikeltable) {
		this.artikeltable = artikeltable;
	}
	public JPanel getArtikelPanel() {
		return this.artikelPanel;
	}

	public void setArtikelPanel(JPanel artikelPanel) {
		this.artikelPanel = artikelPanel;
	}	
	
}
