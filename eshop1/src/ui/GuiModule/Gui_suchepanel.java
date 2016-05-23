package ui.GuiModule;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import domain.Shopverwaltung;
import valueobjects.Artikel;

public class Gui_suchepanel implements ActionListener {
	private JPanel suchPanel;
	
	public Gui_suchepanel(Shopverwaltung shop) {
		
		JPanel suchPanel = new JPanel();
		suchPanel.setLayout(new GridLayout(1, 2));
		
		JTextField suchenTextField = new JTextField();
		suchPanel.add(suchenTextField);
		
		JButton suchButton = new JButton("Suchen");
		suchPanel.add(suchButton);
		suchButton.addActionListener(this);
		
		suchPanel.setBorder(BorderFactory.createTitledBorder("Suchen")); //Überschrift Suchen
		
		setSuchPanel(suchPanel);
	}

	public JPanel getSuchPanel() {
		return suchPanel;
	}

	public void setSuchPanel(JPanel suchPanel) {
		this.suchPanel = suchPanel;
	}
	
	public void actionPerformed(ActionEvent arg0) {		
		System.out.println("Test Suchen");		
		AbstractButton suchenTextField;
/*		String suche = suchenTextField.getText();
		java.util.List<Artikel> suchErgebnis;
		
		if (suche.isEmpty()) {
		//	suchErgebnis = arg0.gibAlleArtikel();
		} else {
		//	suchErgebnis = arg0.sucheNachArtikel(suche);
			//suchErgebnis = shop.sucheNachArtikelNummer(suche);	
		}
	//	artikelListe.removeAll();
		for (Artikel b: suchErgebnis) {
//			artikelListe.add(b.toString());
		}	*/	
	}	
}
