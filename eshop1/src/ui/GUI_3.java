package ui;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
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
import valueobjects.Account;
import valueobjects.Artikel;
import valueobjects.Kunde;
import valueobjects.Mitarbeiter;

public class GUI_3 extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	private Shopverwaltung shop;
	private JTextField nameTextField;
	private JPasswordField passTextField;
	private JTable ausgabeTabelle = null;
	
	private JPanel navframe = new JPanel();		
	private JPanel contentframe = new JPanel();
	private JPanel mainPanel = new JPanel();
	private JMenuBar menueBar = new JMenuBar();
	//private JLabel gesamt = new JLabel();
	private JPanel loginPanel = new JPanel();
	private JPanel suchPanel = new JPanel();
	private JPanel artikelPanel = new JPanel();
	private JTextField suchenTextField;
	
	public GUI_3(String datei) {
		try {
			shop = new Shopverwaltung(datei);

		} catch (IOException e2) {

		}
		this.initialize();
	}
	
	private void initialize() {
		setTitle("E-Shop");
		setSize(800, 600);
	
		//  ---- PANELS anlege ----
		
		mainPanel.setLayout(new BorderLayout());
		navframe.setLayout(new BorderLayout());
		contentframe.setLayout(new BorderLayout());
		
		//GUI setzen
		mainPanel.add(navframe,BorderLayout.NORTH);
		mainPanel.add(contentframe,BorderLayout.CENTER);	
		//add(mainPanel);
		
		//LoginPanel
		loginPanel.setLayout(new GridLayout(2, 3));
		navframe.add(loginPanel);	
		loginPanel.setBorder(BorderFactory.createTitledBorder("Login")); //Ueberschrift Login
		add(loginPanel, BorderLayout.NORTH); 
		loginPanel.setVisible(true);	
		
		//SuchPanel
		suchPanel.setLayout(new GridLayout(1, 4));
		contentframe.add(suchPanel);
		this.contentframe.add(suchPanel, BorderLayout.NORTH);
		add(suchPanel, BorderLayout.NORTH); 
	
		/**
			Menue Panel Bereich
		**/
	
		setJMenuBar(menueBar);
		
		JMenu mnDatei = new JMenu("Datei");
		menueBar.add(mnDatei);
		
		JMenuItem mntmBeenden = new JMenuItem("Beenden");
		mntmBeenden.addActionListener(this);
		mnDatei.add(mntmBeenden);
		
		JMenu mnAccount = new JMenu("Account");
		menueBar.add(mnAccount);
		
		JMenuItem mnLogin = new JMenuItem("Einloggen");
		mnAccount.add(mnLogin);
		
		JMenuItem mnReg = new JMenuItem("Registrieren");
		mnAccount.add(mnReg);
		
		JMenuItem mnLogout = new JMenuItem("Ausloggen");
		//TODO trigger 
		mnLogout.setEnabled(false); //Ausgegraut                            
		mnAccount.add(mnLogout);
		mnLogout.addActionListener(this);

		JMenu mnHilfe = new JMenu("Hilfe");
		menueBar.add(mnHilfe);

		JMenuItem menuItem = new JMenuItem("Wie Artikel kaufen?");
		mnHilfe.add(menuItem);
		menuItem.addActionListener(this);

		JMenuItem mntmber = new JMenuItem("\u00DCber uns");
		mnHilfe.add(mntmber);
		mntmber.addActionListener(this);
		
		/**
			Such Panel Bereich	
		 **/
		suchenTextField = new JTextField();		
		suchPanel.add(suchenTextField);
		
		JButton suchButton = new JButton("Suchen");
		suchPanel.add(suchButton);
		
		suchPanel.add(new JLabel()); //Platzhalter
		suchPanel.add(new JLabel()); //Platzhalter
		
		suchButton.addActionListener(this);
		suchPanel.setBorder(BorderFactory.createTitledBorder("Suchen")); //Uberschrift Suchen
		
		/**
			Login Panel Bereich	
		**/

		
		/**
			Artikel Panel Bereich
		**/
		Vector spalten = new Vector();
		spalten.add("Nummer");
		spalten.add("Name");
		spalten.add("Bestand");
		spalten.add("Preis");
		spalten.add("Packungsgröße");
		spalten.add("Massengut");
		
		ausgabeTabelle = new JTable(new ArtikelTableModel(new Vector<Artikel>(), spalten) {
			// public boolean isCellEditable(int rowIndex, int vColIndex) {
			// return false;
			// }
		});
		
		// TableModel als "Datencontainer" anlegen:
		ArtikelTableModel artikeltable = new ArtikelTableModel(new Vector<Artikel>(), spalten);
		
		// JTable-Objekt erzeugen und mit Datenmodell initialisieren:
		JTable ausgabeTabelle = new JTable(artikeltable);
		
		// JTable in ScrollPane platzieren:
		JScrollPane scrollPane = new JScrollPane(ausgabeTabelle);
		
		// Anzeige der Artikelliste auch in der Kunden-Ansicht
		artikeltable.setDataVector(shop.gibAlleArtikel());	
		
		add(new JScrollPane(artikelPanel)); 
		artikelPanel.add(scrollPane);
		artikelPanel.setLayout(new GridLayout());
		artikelPanel.setBorder(BorderFactory.createTitledBorder("Artikel"));
	}
	
	//ACTIONLISTENER
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		//Für Beenden Button
		if (command.equals("Beenden")) {
			System.exit(0);
		}
		
		//Für Login Button
		else if (command.equals("Login")) {

			System.out.println("test einloggen");
			if(command.equals("Einloggen")){
				
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
				loginButton.addActionListener(new ActionListener() { 
					
					public void actionPerformed(ActionEvent arg0) {
						
						System.out.println("loginbutton");
						
						
						//LoginButton
						JButton loginButton = new JButton("Login");
						loginPanel.add(loginButton);
						loginButton.addActionListener(this);
						
						//Überschrift Login
						
						//hole Name und Passwort aus Textfelder
						String name = nameFeld.getText();
						String passwort = String.valueOf(passwortFeld.getPassword());
				
						//Ueberpruefe ob Kunde oder Mitarbeiter
						try {
							Account user = shop.loginAccount(name, passwort);
							
							if (user instanceof Kunde) {
								login.setVisible(false);
								//loginPanel.setVisible(true);
					
								loginPanel.setBorder(BorderFactory.createTitledBorder("Kundenbereich - Willkommen !"));
								System.out.println("Kunde eingeloggt");
								
								JOptionPane.showMessageDialog(null,"Erfolgreich als Kunde eingeloggt!");		
							}
							else if (user instanceof Mitarbeiter){
								System.out.println("Mitarbeiter eingeloggt");
								JOptionPane.showMessageDialog(null,"Erfolgreich als Mitarbeiter eingeloggt!");
								//login.setVisible(false); //Login Eingabefenster schlieï¿½en
							}
						} catch (AccountExistiertNichtException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage());
						}
					}
				});
				
				login.add(loginButton);
				
				login.setVisible(true);
			}
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
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_3 frame = new GUI_3("Shop");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}