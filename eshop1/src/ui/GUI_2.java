package ui;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;
import domain.Shopverwaltung;
import domain.exceptions.AccountExistiertBereitsException;
import domain.exceptions.AccountExistiertNichtException;
import domain.exceptions.ArtikelExistiertBereitsException;
import domain.exceptions.ArtikelExistiertNichtException;
import domain.exceptions.StatExistiertBereitsException;
import ui.GuiModule.Gui_suchepanel;
import valueobjects.Account;
import valueobjects.Artikel;
import valueobjects.Kunde;
import valueobjects.Mitarbeiter;
import valueobjects.Warenkorb;

public class GUI_2 extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	private Shopverwaltung shop;
	private JTextField suchenTextField;
	private JPasswordField passwortFeld;
	private JTextField nameFeld;
	private Account user;

	public GUI_2(String datei) {
		try {
			shop = new Shopverwaltung(datei);

		} catch (IOException e2) {

		}
		this.initialize();
	}
	
	private void initialize() {
		setTitle("E-Shop");
		setSize(800, 600); //Fenstergröße
		
		//Menü Bereich
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnDatei = new JMenu("Datei");
		menuBar.add(mnDatei);

		JMenuItem mntmBeenden = new JMenuItem("Beenden");
		mntmBeenden.addActionListener(this);
		mnDatei.add(mntmBeenden);
		
		JMenu mnAccount = new JMenu("Account");
		menuBar.add(mnAccount);
		
		JMenuItem mnLogin = new JMenuItem("Einloggen");
		mnAccount.add(mnLogin);
		mnLogin.addActionListener(this);
		
		JMenuItem mnReg = new JMenuItem("Registrieren");
		mnReg.addActionListener(this);
		mnAccount.add(mnReg);

		JMenu mnHilfe = new JMenu("Hilfe");
		menuBar.add(mnHilfe);

		JMenuItem menuItem = new JMenuItem("Wie Artikel kaufen?");
		mnHilfe.add(menuItem);
		menuItem.addActionListener(this);

		JMenuItem mntmber = new JMenuItem("\u00DCber uns");
		mnHilfe.add(mntmber);
		mntmber.addActionListener(this);

		//Such Bereich
		JPanel suchPanel = new JPanel();
		suchPanel.setLayout(new GridLayout(1, 2));
		
		suchenTextField = new JTextField();
		suchPanel.add(suchenTextField);
		
		JButton suchButton = new JButton("Suchen");
		suchPanel.add(suchButton);
		suchButton.addActionListener(this);
		suchPanel.setBorder(BorderFactory.createTitledBorder("Suchen")); //Überschrift Suchen
		
		//Artikel Bereich
		JPanel artikelPanel = new JPanel();
		artikelPanel.setBorder(BorderFactory.createTitledBorder("Artikel")); //Überschrift Artikel
		
		Vector spalten = new Vector();
		spalten.add("Nummer");
		spalten.add("Name");
		spalten.add("Bestand");
		spalten.add("Preis");
		spalten.add("Packungsgröße");
		spalten.add("Massengut");
		
		// TableModel als "Datencontainer" anlegen:
		ArtikelTableModel artikeltable = new ArtikelTableModel(new Vector<Artikel>(), spalten);
		
		// JTable-Objekt erzeugen und mit Datenmodell initialisieren:
		JTable ausgabeTabelle = new JTable(artikeltable);
		
		// JTable in ScrollPane platzieren:
		JScrollPane scrollPane = new JScrollPane(ausgabeTabelle);
		
		// Anzeige der Artikelliste auch in der Kunden-Ansicht
		artikeltable.setDataVector(shop.gibAlleArtikel());
		
		//PANELS ANLEGEN
		add(suchPanel, BorderLayout.NORTH); //SuchPanel
		add(new JScrollPane(artikelPanel));	//ArtikelPanel	
		artikelPanel.add(scrollPane);
		artikelPanel.setLayout(new GridLayout());
	}
	
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		//Für Menü Datei -> Beenden Button
		if (command.equals("Beenden")) {
			System.exit(0);
		}
		//Für Menü Account -> Einloggen Button
		else if(command.equals("Einloggen")){

			final JFrame login = new JFrame();

			login.setSize(200, 300);
			login.setLayout(new GridLayout(7, 1));

			JLabel labelname = new JLabel("Name:");
			login.add(labelname);

			final JTextField nameFeld = new JTextField();
			login.add(nameFeld);

			JLabel labelpasswort = new JLabel("Passwort:");
			login.add(labelpasswort);

			final JPasswordField passwortFeld = new JPasswordField();
			login.add(passwortFeld);

			JButton loginButton = new JButton("Login");
			login.add(loginButton);
			loginButton.addActionListener(this);;
			
			login.setVisible(true);
			
			//Für Menü Account -> Einloggen -> Login Button
			loginButton.addActionListener(new ActionListener() { 
				
			public void actionPerformed(ActionEvent arg0) {
				
			//hole Name und Passwort aus Textfelder
			String name = nameFeld.getText();
			String passwort = String.valueOf(passwortFeld.getPassword());
	
			//überprüfe ob Kunde oder Mitarbeiter
			try {
				user = shop.loginAccount(name, passwort);
				
				if (user instanceof Kunde) {
					System.out.println("Kunde eingeloggt");
					//loginPanel.setBorder(BorderFactory.createTitledBorder("Kundenbereich - Willkommen "+ user.getName() + "!"));
				}
				else if (user instanceof Mitarbeiter){
					System.out.println("Mitarbeiter eingeloggt");
				}
			} catch (AccountExistiertNichtException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
	    }
	  });
	}
		//Für Menü Account -> Registrieren Button
		else if (command.equals("Registrieren")){
			final JFrame registrieren = new JFrame("Kunde registrieren");

			registrieren.setSize(400, 300);
			registrieren.getContentPane().setLayout(new GridLayout(11, 1));

			JLabel name = new JLabel("Name:");
			registrieren.getContentPane().add(name);

			final JTextField nameFeld = new JTextField();
			registrieren.getContentPane().add(nameFeld);

			JLabel passwort = new JLabel("Passwort:");
			registrieren.getContentPane().add(passwort);

			final JPasswordField passwortFeld = new JPasswordField();
			registrieren.getContentPane().add(passwortFeld);

			JLabel adresse = new JLabel("Adresse:");
			registrieren.getContentPane().add(adresse);

			final JTextField adressFeld = new JTextField();
			registrieren.getContentPane().add(adressFeld);

			JLabel plz = new JLabel("Postleitzahl:");
			registrieren.getContentPane().add(plz);

			final JTextField plzFeld = new JTextField();
			registrieren.getContentPane().add(plzFeld);

			JLabel wohnort = new JLabel("Ort:");
			registrieren.getContentPane().add(wohnort);

			final JTextField ortFeld = new JTextField();
			registrieren.getContentPane().add(ortFeld);

			JButton regis = new JButton("Registrieren");
			registrieren.getContentPane().add(regis);
			
			registrieren.setVisible(true);
		}
		//Für Menü Hilfe -> Artikel kaufen?
		else if (command.equals("Wie Artikel kaufen?")) {
			JOptionPane.showMessageDialog(null,
				"Willkommen im E-Shop. \n Wenn Sie Artikel kaufen wollen, dann registrieren"
				+ "Sie sich und loggen Sie sich ein! \n Anschließend können Sie die gewünschten "
			    + "Artikel kaufen.");
		}
		//Für Menü Hilfe -> Über uns
		else if (command.equals("\u00DCber uns")) {
			JOptionPane.showMessageDialog(null, "Entwickler: \n\n"
					+ "Immanuel Zimmermann \n" 
					+ "Stefan Meyer \n"
					+ "Daniel Böckmann \n\n" 
					+ "HS Bremen, Prog 2, SS 2016");
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