package ui.GuiModule;

import java.awt.BorderLayout;
import java.awt.Container;
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
import ui.GUI_2;
import valueobjects.Artikel;

public class Gui_suchepanel implements ActionListener {
	private GUI_2 GUI;
	private JPanel suchPanel;
	
	private JPanel warenkorbPanel;
	
	public Gui_suchepanel(GUI_2 GUI) {
		this.GUI = GUI;
		JPanel suchPanel = new JPanel();
		suchPanel.setLayout(new GridLayout(1, 4));
		
		JTextField suchenTextField = new JTextField();
		suchPanel.add(suchenTextField);
		
		JButton suchButton = new JButton("Suchen");
		suchPanel.add(suchButton);
		suchButton.addActionListener(this);
		
		suchPanel.add(new JLabel()); //Platzhalter
		
		suchPanel.setLayout(new GridLayout(1, 4));
		
		JButton inWarenKorbLegenButton = new JButton("in Warenkorb legen");
		suchPanel.add(inWarenKorbLegenButton);
		inWarenKorbLegenButton.addActionListener(this);
		
		JButton zumWarenKorbButton = new JButton("zum Warenkorb");
		suchPanel.add(zumWarenKorbButton);
		zumWarenKorbButton.addActionListener(this);
		
		suchPanel.setBorder(BorderFactory.createTitledBorder("Shop")); //�berschrift Suchen
		
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
		JPanel contentframe = new JPanel();	
		Gui_artikelpanel artikelPanel = new Gui_artikelpanel(this.GUI.getShop().gibAlleArtikel());			
		contentframe.add(artikelPanel.getArtikelPanel(), BorderLayout.CENTER);	
	
		this.GUI.updateGUIcontent(contentframe);
		
		
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
