package ui;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import domain.Shopverwaltung;
import domain.exceptions.AccountExistiertBereitsException;
import domain.exceptions.ArtikelExistiertNichtException;
import domain.exceptions.BestandUeberschrittenException;
import ui.GuiModule.Gui_artikelpanel;
import ui.GuiModule.Gui_loginpanel;
import ui.GuiModule.Gui_menuepanel;
import ui.GuiModule.Gui_suchepanel;
import ui.GuiModule.Gui_warenkorbpanel;
import valueobjects.Account;
import valueobjects.Artikel;
import valueobjects.Kunde;
import valueobjects.Warenkorb;

public class GUI_2 extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	private Shopverwaltung shop;
	private JTextField suchenTextField;
	private Account user;
	private List artikelListe = new List();
	private JTable ausgabeTabelle = null;
	private JTable warenkorbTabelle = null;
	JLabel gesamt = new JLabel();

	public GUI_2(String datei) {
		setTitle("E-Shop");
		setSize(800, 600); //Fenstergr��e
		setResizable(false);
		
		try {
			shop = new Shopverwaltung(datei);

		} catch (IOException e2) {

		}
		this.initialize();
	}	
	private void initialize() {
		//men�bar
		Gui_menuepanel menuBar = new Gui_menuepanel(shop);		
		setJMenuBar(menuBar.getMenue());
		
		//LayoutPanel
		JPanel mainPanel = new JPanel();
		JPanel navframe = new JPanel();		
		JPanel contentframe = new JPanel();
		//
		mainPanel.setLayout(new BorderLayout());
		navframe.setLayout(new BorderLayout());
		contentframe.setLayout(new BorderLayout());		
		//content frame 		
		//suche
		Gui_suchepanel suchPanel = new Gui_suchepanel(shop);
		contentframe.add(suchPanel.getSuchPanel(), BorderLayout.NORTH);
		
		//login
		Gui_loginpanel loginPanel = new Gui_loginpanel(shop);
		navframe.add(loginPanel.getloginPanel(), BorderLayout.NORTH);	
		
		//Artikelliste
		Gui_artikelpanel artikelPanel = new Gui_artikelpanel(shop.gibAlleArtikel());			
		contentframe.add(artikelPanel.getArtikelPanel(), BorderLayout.CENTER);
		
		//warenkorb
		/*Gui_warenkorbpanel warenkorbpanel = new Gui_warenkorbpanel();			
		contentframe.add(warenkorbpanel.getWarenkorbPanel(), BorderLayout.SOUTH);*/
		
		//zusammenbasteln		
		
		mainPanel.add(navframe,BorderLayout.NORTH);
		mainPanel.add(contentframe,BorderLayout.CENTER);
		//ausgeben
		add(mainPanel);	
	}
	
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		//F�r Men� Datei -> Beenden Button
		if (command.equals("Beenden")) {
		//	System.exit(0);
		}
		
		//F�r Men� Account -> Registrieren Button
		else if (command.equals("Registrieren")){
			final JFrame registrieren = new JFrame();

			registrieren.setSize(400, 300);
			registrieren.setLayout(new GridLayout(11, 1));

			JLabel name = new JLabel("Name:");
			registrieren.add(name);

			final JTextField nameFeld = new JTextField();
			registrieren.add(nameFeld);

			JLabel passwort = new JLabel("Passwort:");
			registrieren.add(passwort);

			final JPasswordField passwortFeld = new JPasswordField();
			registrieren.add(passwortFeld);

			JLabel adresse = new JLabel("Adresse:");
			registrieren.add(adresse);

			final JTextField adressFeld = new JTextField();
			registrieren.add(adressFeld);

			JLabel plz = new JLabel("Postleitzahl:");
			registrieren.add(plz);

			final JTextField plzFeld = new JTextField();
			registrieren.add(plzFeld);

			JLabel wohnort = new JLabel("Ort:");
			registrieren.add(wohnort);

			final JTextField ortFeld = new JTextField();
			registrieren.add(ortFeld);

			JButton regButton = new JButton("Registrieren");
			registrieren.add(regButton);
			
			//F�r Men� Account -> Registrieren -> Registrieren Button
			regButton.addActionListener(new ActionListener() { 
		
				public void actionPerformed(ActionEvent arg0) {
						
					//hole Name, Passwort, Starsse, PLZ und Ort aus Textfelder
					String name = nameFeld.getText();
					String passwort = String.valueOf(passwortFeld.getPassword());
					String strasse = adressFeld.getText();
					int plz = Integer.parseInt(plzFeld.getText());
					String ort = ortFeld.getText();
					
					try {
						shop.fuegeKundenAccountEin(name, passwort, strasse, plz, ort);
						try {
							shop.schreibeKundendaten();
							JOptionPane.showMessageDialog(null,"Erfolgreich als Kunde registriert!");
							registrieren.setVisible(false);
						} catch (IOException e) {
							e.printStackTrace();
						}
					} catch (AccountExistiertBereitsException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
			});
			registrieren.setVisible(true);
		}
		
		//F�r Suchen Button
		else if (command.equals("Suchen")) {
			
			System.out.println("Test Suchen");
			
			String suche = suchenTextField.getText();
			java.util.List<Artikel> suchErgebnis;
			
			if (suche.isEmpty()) {
				suchErgebnis = shop.gibAlleArtikel();
			} else {
				suchErgebnis = shop.sucheNachArtikel(suche);
				//suchErgebnis = shop.sucheNachArtikelNummer(suche);	
			}
			//ArtikelTableModel artikeltable = (ArtikelTableModel) ausgabeTabelle.getModel();
			//artikeltable.setDataVector(suchErgebnis);
			artikelListe.removeAll();
			for (Artikel b: suchErgebnis) {
				artikelListe.add(b.toString());
			}
			
		}
		//F�r Suchen Button
		else if (command.equals("in Warenkorb legen")) {
			try {

				JLabel anz = new JLabel("Wie oft wollen Sie den Artikel kaufen?");
				final JTextField anzahl1 = new JTextField();
				JButton ok = new JButton("In den Warenkorb");

				final JFrame wieViele = new JFrame();
				wieViele.getContentPane().setLayout(new GridLayout(2, 1));
				wieViele.setSize(450, 100);
				wieViele.getContentPane().add(anz);
				wieViele.getContentPane().add(anzahl1);
				wieViele.getContentPane().add(ok);
				ok.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent arg0) {
						try {
							shop.inWarenkorbEinfuegen(shop.artikelSuchen(Integer.parseInt((ausgabeTabelle.getValueAt(ausgabeTabelle.getSelectedRow(),0)).toString())),Integer.parseInt(anzahl1.getText()),(Kunde) user);
							wieViele.setVisible(false);
						} catch (NumberFormatException e) {
							e.printStackTrace();
						} catch (BestandUeberschrittenException e) {
							JOptionPane.showMessageDialog(null, e.getMessage());
						} catch (ArtikelExistiertNichtException e) {
							JOptionPane.showMessageDialog(null, e.getMessage());
						}
					}
				});

				wieViele.setVisible(true);

			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			}	
		}
		//F�r Suchen Button
		else if (command.equals("zum Warenkorb")) {
			Warenkorb suchErgebnis;
			Kunde kunde = (Kunde) user;

			suchErgebnis = kunde.getWarenkorb();

			ArtikelTableModel tModel = (ArtikelTableModel) warenkorbTabelle.getModel();
			tModel.setDataVector2(suchErgebnis);

			float gesamtpreis = 0.0f;

			gesamt.setText("Gesampreis: " + gesamtpreis + "�");
		}
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_2 frame = new GUI_2("Shop");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}