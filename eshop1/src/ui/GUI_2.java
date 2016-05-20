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

public class GUI_2 extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	private Shopverwaltung shop;
	private JButton addButton;
	private JTextField titleField;
	private JTextField numberField;
	private JTextField searchTextField;
	private JTextField nameTextField;
	private JTextField suchenTextField;
	private JPasswordField passTextField;
	private JTextField textField;
	private Account user;
	private Container hauptscreen = null;

	public GUI_2(String datei) {
		try {
			shop = new Shopverwaltung(datei);

		} catch (IOException e2) {

		}
		this.initialize();
	}
	
	private void initialize() {
		setTitle("E-Shop");
		setSize(800, 600);
		
		//Menü definieren
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnDatei = new JMenu("Datei");
		menuBar.add(mnDatei);

		JMenuItem mntmBeenden = new JMenuItem("Beenden");
		mntmBeenden.addActionListener(this);
		mnDatei.add(mntmBeenden);
		
		JMenu mnAccount = new JMenu("Account");
		menuBar.add(mnAccount);
		
		JMenuItem mnReg = new JMenuItem("Registrieren");
		mnReg.addActionListener(this);
		mnAccount.add(mnReg);

		JMenu mnHilfe = new JMenu("Hilfe");
		menuBar.add(mnHilfe);

		JMenuItem menuItem = new JMenuItem("Artikel kaufen?");
		mnHilfe.add(menuItem);
		menuItem.addActionListener(this);

		JMenuItem mntmber = new JMenuItem("\u00DCber uns");
		mnHilfe.add(mntmber);
		mntmber.addActionListener(this);

		//Login Bereich
		JPanel loginPanel = new JPanel();
		
		loginPanel.setLayout(new GridLayout(2, 3));
		loginPanel.add(new JLabel("Name"));
		loginPanel.add(new JLabel("Passwort"));
		loginPanel.add(new JLabel(""));
		
		nameTextField = new JTextField();
		passTextField = new JPasswordField();
		loginPanel.add(nameTextField);
		loginPanel.add(passTextField);

		JButton loginButton = new JButton("Login");
		loginPanel.add(loginButton);
		loginButton.addActionListener(this);
		loginPanel.setBorder(BorderFactory.createTitledBorder("Login")); //Überschrift Login
		
		//Such Bereich
		JPanel suchPanel = new JPanel();
		suchPanel.setLayout(new GridLayout(1, 2));
		
		suchenTextField = new JTextField();
		suchPanel.add(suchenTextField);
		
		JButton suchButton = new JButton("Suchen");
		suchPanel.add(suchButton);
		suchButton.addActionListener(this);
		suchPanel.setBorder(BorderFactory.createTitledBorder("Suchen")); //Überschrift Suchen
		
		//MAIN PANEL
		JPanel mainPanel = new JPanel();
		
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
		add(loginPanel, BorderLayout.NORTH); //LoginPanel
		add(suchPanel, BorderLayout.SOUTH); //SuchPanel
		add(new JScrollPane(artikelPanel));	//ArtikelPanel	
		artikelPanel.add(scrollPane);
		artikelPanel.setLayout(new GridLayout());
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

	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		//Für Beenden Button
		if (command.equals("Beenden")) {
			System.exit(0);
		}
		//Für Account Registrieren
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
		//Für Login Button
		else if (command.equals("Login")) {

			String name = nameTextField.getText();
			String passwort = String.valueOf(passTextField.getPassword());
			try {
				user = shop.loginAccount(name, passwort);
			}
			catch (AccountExistiertNichtException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
			if (user instanceof Kunde) {

				JFrame kundeEingeloggt = new JFrame();

				this.hauptscreen = this.getContentPane();

				Container container = kundeEingeloggt.getContentPane();
				setContentPane(container);
				container.setLayout(new GridLayout(1, 2));

				JPanel panel = new JPanel();
				container.add(panel);

				panel.setBorder(BorderFactory.createTitledBorder("Kundenbereich - Willkommen "+ user.getName() + "!"));
				panel.setLayout(new GridLayout(1, 2));
			}
		}
		//Für Menü Button "Artikel kaufen?"
		else if (command.equals("Artikel kaufen?")) {
			JOptionPane.showMessageDialog(null,
				"Willkommen im E-Shop. \n Wenn Sie Artikel kaufen wollen, dann registrieren"
				+ "Sie sich und loggen Sie sich ein! \n Anschließend können Sie die gewünschten "
			    + "Artikel kaufen.");
		}
		//Für Menü Button "Über uns"
		else if (command.equals("\u00DCber uns")) {
			JOptionPane.showMessageDialog(null, "Entwickler: \n\n"
					+ "Immanuel Zimmermann \n" 
					+ "Stefan Meyer \n"
					+ "Daniel Böckmann \n\n" 
					+ "HS Bremen, Prog 2, SS 2016");
		}
	}
}