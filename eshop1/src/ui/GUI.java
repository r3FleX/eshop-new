package ui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
import valueobjects.Account;
import valueobjects.Artikel;
import valueobjects.Kunde;
import valueobjects.Mitarbeiter;


public class GUI extends JFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;

	private JPasswordField passwordField;
	private JTextField textField;

	private Container hauptscreen = null;
	private JTable ausgabeTabelle = null;

	private Shopverwaltung shop;
	private Account user;
	private Vector<Artikel> alleArtikel;
	
	@SuppressWarnings("rawtypes")
	private Vector spalten;
	private JScrollPane scrollPane = null;
	private JTable table;

	JTextField nummerFeld = new JTextField();
	JTextField bezeichnungsFeld = new JTextField();
	JTextField preisFeld = new JTextField();
	JTextField bestandFeld = new JTextField();
	JRadioButton ja = new JRadioButton("Ja");
	JRadioButton nein = new JRadioButton("Nein", true);
	ButtonGroup istMassenartikel = new ButtonGroup();
	JButton einfuegeButton = new JButton("Einfügen");

	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI("Shop");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 * @throws StatExistiertBereitsException 
	 */
	public GUI(String datei) {
		super("Shop");

		try {
			shop = new Shopverwaltung(datei);
			gebeBestandAus();

		} catch (IOException e2) {

		}
		this.initialize();
	};
	private void initialize() {
		setTitle("E-Shop");
//		setSize(800, 600);

		// Menü definieren
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnDatei = new JMenu("Datei");
		menuBar.add(mnDatei);

		JMenuItem mntmBeenden = new JMenuItem("Beenden");
		mntmBeenden.addActionListener(this);
		mnDatei.add(mntmBeenden);

		JMenu mnHilfe = new JMenu("Hilfe");
		menuBar.add(mnHilfe);

		JMenuItem menuItem = new JMenuItem("?");
		mnHilfe.add(menuItem);
		menuItem.addActionListener(this);

		JMenuItem mntmber = new JMenuItem("\u00DCber...");
		mnHilfe.add(mntmber);
		mntmber.addActionListener(this);

		getContentPane().setLayout(new GridLayout(1, 2));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		// Unterteilung in linke Spalte und rechte Spalte
		JPanel leftpanel = new JPanel();
		getContentPane().add(leftpanel);
		leftpanel.setLayout(new GridLayout(2, 1));

		JPanel rightpanel = new JPanel();
		rightpanel.setPreferredSize(new Dimension(400, 600));
		getContentPane().add(rightpanel);

		// linke Spalte oben
		JPanel upperpanel = new JPanel();
		leftpanel.add(upperpanel);

		upperpanel.setLayout(new GridLayout(2, 1));

		JPanel upperpanel_up = new JPanel();
		JPanel upperpanel_low = new JPanel();

		// linke Spalte unten
		JPanel lowerpanel = new JPanel();
		leftpanel.add(lowerpanel);

		// Inhalt links oben

		// Rahmen mit Text
		upperpanel.setBorder(BorderFactory.createTitledBorder("Login"));

		upperpanel_up.setLayout(new GridLayout(4, 1));

		upperpanel.add(upperpanel_up);
		upperpanel.add(upperpanel_low);

		JLabel lblName = new JLabel("Name:");
		upperpanel_up.add(lblName);

		textField = new JTextField();
		upperpanel_up.add(textField);

		JLabel lblPasswort = new JLabel("Passwort:");
		upperpanel_up.add(lblPasswort);

		passwordField = new JPasswordField();
		upperpanel_up.add(passwordField);

		upperpanel_low.setLayout(new GridLayout(2, 2));

		JPanel upperpanel_low_left_up = new JPanel();
		JPanel upperpanel_low_left_low = new JPanel();
		JPanel upperpanel_low_right_up = new JPanel();
		JPanel upperpanel_low_right_low = new JPanel();

		upperpanel_low.add(upperpanel_low_left_up);
		upperpanel_low.add(upperpanel_low_right_up);
		upperpanel_low.add(upperpanel_low_left_low);
		upperpanel_low.add(upperpanel_low_right_low);

		JButton login = new JButton("Login");
		upperpanel_low_right_low.add(login);
		login.addActionListener(this);

		// Inhalt links unten

		// Rahmen mit Text
		lowerpanel.setBorder(BorderFactory.createTitledBorder("Neuer Account"));

		lowerpanel.setLayout(new GridLayout(5, 1));

		JButton kundeRegistrieren = new JButton("Kunde");
		lowerpanel.add(kundeRegistrieren);
		kundeRegistrieren.addActionListener(this);

		JButton mitarbeiterRegistrieren = new JButton("Mitarbeiter");
		lowerpanel.add(mitarbeiterRegistrieren);
		mitarbeiterRegistrieren.addActionListener(this);

		JScrollPane scrollPane_1 = new JScrollPane();
		rightpanel.add(scrollPane_1);
		scrollPane_1.setBorder(BorderFactory.createTitledBorder("Artikel"));

		this.pack();
//		this.show();

	}

	@SuppressWarnings("serial")
	class DateiMenu extends JMenu implements ActionListener {

		public DateiMenu() {
			super("Datei");
			this.addSeparator();

		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		if (command.equals("Beenden")) {
			setVisible(false);
			dispose();

			System.exit(0);
		}

		else if (command.equals("Login")) {

			String name = textField.getText();
			String passwort = String.valueOf(passwordField.getPassword());

			try {
				user = shop.loginAccount(name, passwort);

				if (user instanceof Kunde) {

					JFrame kundeEingeloggt = new JFrame();

					this.hauptscreen = this.getContentPane();

					Container container = kundeEingeloggt.getContentPane();
					setContentPane(container);
					container.setLayout(new GridLayout(1, 2));

					JPanel panel = new JPanel();
					container.add(panel);

					panel.setBorder(BorderFactory
							.createTitledBorder("Kundenbereich - Willkommen "
									+ user.getName() + "!"));
					panel.setLayout(new GridLayout(1, 2));

					JPanel linkeSeite = new JPanel();
					JPanel rechteSeite = new JPanel();

					panel.add(linkeSeite);
					panel.add(rechteSeite);

					// linke Seite

					JButton suchen = new JButton("Suchen");
					final JTextField suchbegriff = new JTextField();

					JButton ausloggen = new JButton("Ausloggen");

					JPanel links_oben = new JPanel();
					JPanel links_unten = new JPanel();
					JPanel links_ausloggen = new JPanel();

					linkeSeite.setLayout(new GridLayout(3, 1));

					linkeSeite.add(links_oben);
					linkeSeite.add(links_unten);
					linkeSeite.add(links_ausloggen);

					// links oben

					links_oben.setBorder(BorderFactory
							.createTitledBorder("Artikel suchen"));
					links_oben.setLayout(new GridLayout(4, 2));
					links_oben.add(new JLabel("Suchen:"));
					links_oben.add(new JLabel());
					links_oben.add(suchbegriff);
					links_oben.add(new JLabel());
					links_oben.add(new JLabel());
					links_oben.add(suchen);
					suchen.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String suche = suchbegriff.getText();
							java.util.List<Artikel> suchErgebnis;
							if (suche.isEmpty()) {
								suchErgebnis = shop.gibAlleArtikel();
							} else {
								suchErgebnis = shop.sucheNachArtikel(suche);
							}

							
							ArtikelTableModel tModel = (ArtikelTableModel) ausgabeTabelle
									.getModel();
							tModel.setDataVector(suchErgebnis);

						}
					});

					// Logout-Bereich

					links_ausloggen.add(ausloggen);
					ausloggen.addActionListener(this);

					// rechte Seite

					//rechteSeite.setLayout(new GridLayout(3, 1));
					rechteSeite.setLayout(new GridLayout(1, 1));
					JPanel rechts_oben = new JPanel();
					//JPanel rechts_mitte = new JPanel();
					//JPanel rechts_unten = new JPanel();
					rechteSeite.add(rechts_oben);
					//rechteSeite.add(rechts_mitte);
					//rechteSeite.add(rechts_unten);

					// rechts oben

					rechts_oben.setBorder(BorderFactory
							.createTitledBorder("Artikel"));

					JScrollPane scrollpane = new JScrollPane();
					// leer

					Vector spalten = new Vector();
					spalten.add("Nummer");
					spalten.add("Name");
					spalten.add("Bestand");
					spalten.add("Preis");
					spalten.add("Packungsgröße");
					spalten.add("Massengut");
					// TableModel als "Datencontainer" anlegen:
					ArtikelTableModel tModel = new ArtikelTableModel(
							new Vector<Artikel>(), spalten);
					// JTable-Objekt erzeugen und mit Datenmodell
					// initialisieren:
					ausgabeTabelle = new JTable(tModel);
					ausgabeTabelle.setPreferredSize(new Dimension(400, 600));
					
					// JTable in ScrollPane platzieren:
					scrollPane = new JScrollPane(ausgabeTabelle);
					ausgabeTabelle.setAutoCreateRowSorter(true);
					scrollPane.setViewportView(ausgabeTabelle);

					// Anzeige der Artikelliste auch in der Kunden-Ansicht
					tModel.setDataVector(shop.gibAlleArtikel());

					rechts_oben.add(scrollPane);
					
					pack();

					/*JScrollPane scrollPane2 = new JScrollPane();

					Vector spalten2 = new Vector();
					spalten2.add("Nummer");
					spalten2.add("Name");
					spalten2.add("Anzahl");
					spalten2.add("Preis");

					Kunde kunde = (Kunde) user;

					table = new JTable();
					scrollPane2.setColumnHeaderView(table);

					rechts_mitte.add(scrollPane2);*/

				}

				else if (user instanceof Mitarbeiter) {

					JFrame mitarbeiterEingeloggt = new JFrame();

					this.hauptscreen = this.getContentPane();

					Container container = mitarbeiterEingeloggt
							.getContentPane();
					setContentPane(container);
					container.setLayout(new GridLayout(1, 2));

					JPanel panel = new JPanel();
					container.add(panel);

					panel.setBorder(BorderFactory
							.createTitledBorder("Mitarbeiterbereich - Willkommen "
									+ user.getName() + "!"));

					JPanel left = new JPanel();
					JPanel right = new JPanel();

					panel.setLayout(new GridLayout(1, 2));
					panel.add(left);
					panel.add(right);

					JPanel left_up = new JPanel();
					JPanel left_low = new JPanel();
					JPanel left_logout = new JPanel();

					JPanel right_up = new JPanel();
					JPanel right_low = new JPanel();

					// alles für links oben

					left_up.setBorder(BorderFactory
							.createTitledBorder("Neuen Artikel einfügen"));

					istMassenartikel.add(ja);
					istMassenartikel.add(nein);

					left.setLayout(new GridLayout(3, 2));
					left.add(left_up);
					left.add(left_low);
					left.add(left_logout);

					left_up.setLayout(new GridLayout(6, 2));
					left_up.add(new JLabel("Nummer:"));
					left_up.add(new JLabel());
					left_up.add(nummerFeld);
					left_up.add(new JLabel());
					left_up.add(new JLabel("Name:"));
					left_up.add(new JLabel());
					left_up.add(bezeichnungsFeld);
					left_up.add(new JLabel());
					left_up.add(new JLabel("Preis:"));
					left_up.add(new JLabel());
					left_up.add(preisFeld);
					left_up.add(new JLabel("€"));
					left_up.add(new JLabel("Bestand:"));
					left_up.add(new JLabel());
					left_up.add(bestandFeld);
					left_up.add(new JLabel());
					left_up.add(new JLabel("Massenartikel:"));
					left_up.add(new JLabel());
					left_up.add(ja);
					ja.addMouseListener(this);
					left_up.add(nein);
					nein.addActionListener(this);
					left_up.add(new JLabel());
					left_up.add(new JLabel());
					left_up.add(new JLabel());
					left_up.add(einfuegeButton);
					einfuegeButton.addActionListener(this);
					// TODO einfügen noch nicht implementiert

					// alles für links unten

					left_low.setBorder(BorderFactory
							.createTitledBorder("Artikel suchen"));

					final JTextField suchbegriff = new JTextField();
					JButton suchen = new JButton("Suchen");

					left_low.setLayout(new GridLayout(5, 2));
					left_low.add(new JLabel("Suchen:"));
					left_low.add(new JLabel());
					left_low.add(suchbegriff);
					left_low.add(new JLabel());
					left_low.add(new JLabel());
					left_low.add(suchen);
					suchen.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String suche = suchbegriff.getText();
							java.util.List<Artikel> suchErgebnis;
							if (suche.isEmpty()) {
								suchErgebnis = shop.gibAlleArtikel();
							} else {
								suchErgebnis = shop.sucheNachArtikel(suche);
							}
							ArtikelTableModel tModel = (ArtikelTableModel) ausgabeTabelle
									.getModel();
							tModel.setDataVector(suchErgebnis);

						}
					});

					// Logout-Button

					JButton logout = new JButton("Ausloggen");

					left_logout.add(logout);
					logout.addActionListener(this);

					// alles für rechts
					JButton bestandAendern = new JButton("Bestand ändern");
					JButton loeschen = new JButton("Artikel löschen");
					// JButton packungAendern = new
					// JButton("Packungsgröße ändern");

					right.setLayout(new GridLayout(2, 1));
					right.add(right_up);
					right.add(right_low);

					right_up.setBorder(BorderFactory
							.createTitledBorder("Artikel"));

					JScrollPane scrollpane = new JScrollPane();
					// JScrollBar scrollbar = new JScrollBar();

					// TODO funktioniert, aber Preis, Packung, Massengut sind
					// leer

					Vector spalten = new Vector();
					spalten.add("Nummer");
					spalten.add("Name");
					spalten.add("Bestand");
					spalten.add("Preis");
					spalten.add("Packungsgröße");
					spalten.add("Massengut");
					// TableModel als "Datencontainer" anlegen:
					ArtikelTableModel tModel = new ArtikelTableModel(
							new Vector<Artikel>(), spalten);
					// JTable-Objekt erzeugen und mit Datenmodell
					// initialisieren:
					ausgabeTabelle = new JTable(tModel);
					// ausgabeTabelle.add(scrollbar);
					// JTable in ScrollPane platzieren:
					scrollPane = new JScrollPane(ausgabeTabelle);
					ausgabeTabelle.setAutoCreateRowSorter(true);
					scrollPane.setViewportView(ausgabeTabelle);

					tModel.setDataVector(shop.gibAlleArtikel());

					right_up.add(scrollPane);

					// rechts unten

					right_low.setLayout(new GridLayout(9, 3));
					right_low.add(new JLabel());
					right_low.add(new JLabel());
					right_low.add(new JLabel());
					right_low.add(new JLabel());
					right_low.add(bestandAendern);
					bestandAendern.addActionListener(this);

					right_low.add(loeschen);
					loeschen.addActionListener(this);
					right_low.add(new JLabel());
					right_low.add(new JLabel());
					right_low.add(new JLabel());

					this.setSize(1024, 576);
					this.setVisible(true);

					/**
					 * Menü anlegen
					 */

					JMenuBar menuBar = new JMenuBar();
					setJMenuBar(menuBar);

					JMenu mnDatei = new JMenu("Datei");
					menuBar.add(mnDatei);

					JMenuItem mntmBeenden = new JMenuItem("Beenden");
					mnDatei.add(mntmBeenden);
					mntmBeenden.addActionListener(this);

					JMenu mnHilfe = new JMenu("Hilfe");
					menuBar.add(mnHilfe);

					JMenuItem menuItem = new JMenuItem("?");
					mnHilfe.add(menuItem);
					menuItem.addActionListener(this);

					JMenuItem mntmber = new JMenuItem("\u00DCber...");
					mnHilfe.add(mntmber);
					mntmber.addActionListener(this);

					JMenuBar menuBar_1 = new JMenuBar();
					menuBar.add(menuBar_1);
				}

			} catch (AccountExistiertNichtException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}

		}

		else if (command.equals("Kunde")) {
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

			regis.addActionListener(new ActionListener() {

				int plz1;

				public void actionPerformed(ActionEvent arg0) {

					// TODO Kundennummer stimmt nicht mehr (auch bei
					// Mitarbeiter)
					// wird auch gespeichert, wenn PLZ nicht stimmt

					if (Integer.parseInt(plzFeld.getText()) > 99999
							|| Integer.parseInt(plzFeld.getText()) < 10000) {
						JOptionPane
								.showMessageDialog(null,
										"Bitte geben Sie eine gültige Postleitzahl ein!");
					}

					else {
						String name1 = nameFeld.getText();
						String passwort1 = String.valueOf(passwortFeld
								.getPassword());
						String strasse1 = adressFeld.getText();
						plz1 = Integer.parseInt(plzFeld.getText());
						String ort1 = ortFeld.getText();

						try {
							shop.fuegeKundenAccountEin(name1, passwort1,
									strasse1, plz1, ort1);
							try {
								shop.schreibeKundendaten();
								registrieren.setVisible(false);
							} catch (IOException e) {
								e.printStackTrace();
							}
						} catch (AccountExistiertBereitsException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());

						}
					}
				}

			});

			registrieren.setVisible(true);
		}

		else if (command.equals("Mitarbeiter")) {
			final JFrame registrieren = new JFrame("Mitarbeiter registrieren");

			registrieren.setSize(400, 200);
			registrieren.getContentPane().setLayout(new GridLayout(5, 1));

			JLabel name = new JLabel("Name:");
			registrieren.getContentPane().add(name);

			final JTextField nameFeld = new JTextField();
			registrieren.getContentPane().add(nameFeld);

			JLabel passwort = new JLabel("Passwort:");
			registrieren.getContentPane().add(passwort);

			final JPasswordField passwortfeld = new JPasswordField();
			registrieren.getContentPane().add(passwortfeld);

			JButton regis = new JButton("Mitarbeiter registrieren");
			registrieren.getContentPane().add(regis);
			regis.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					String name1 = nameFeld.getText();
					String passwort1 = String.valueOf(passwortfeld
							.getPassword());

					try {
						shop.fuegeMitarbeiterAccountEin(name1, passwort1);
						try {
							shop.schreibeMitarbeiterdaten();
							registrieren.setVisible(false);
						} catch (IOException e) {

						}
					} catch (AccountExistiertBereitsException e2) {
						JOptionPane.showMessageDialog(null, e2.getMessage());
					}
				}
			});

			registrieren.setVisible(true);
		}

		else if (command.equals("?")) {
			JOptionPane
					.showMessageDialog(
							null,
							"Willkommen im E-Shop. \n Wenn Sie Artikel kaufen wollen, dann registrieren"
									+ "Sie sich und loggen Sie sich ein! \n Anschließend können Sie die gewünschten "
									+ "Artikel kaufen.");
		}

		else if (command.equals("\u00DCber...")) {
			JOptionPane.showMessageDialog(null, "Entwickler: \n"
					+ "Stefan Meyer\n"
					+ "Daniel Böckmann\n"
					+ "Immanuel Zimmermann\n"
					+ " \n" + "\n"
					+ "\n");
		}

		else if (command.equals("Ausloggen")) {
			user = shop.logoutAccount(user.getName(), user.getPasswort());
			this.setContentPane(this.hauptscreen);
			// System.out.println(user);
		}

		else if (command.equals("Einfügen")) {

			// TODO is noch grün hinter den Ohren!!


			try {

				String artname = bezeichnungsFeld.getText();
				int artnr = Integer.parseInt(nummerFeld.getText());
				int artbestand = Integer.parseInt(bestandFeld.getText());
				float preis = Float.parseFloat(preisFeld.getText());
				boolean massengut = false;
				int packungsgroesse = 0;

				if (nein.isSelected()) {
					System.out.println("nein");
					massengut = false;
					packungsgroesse = 0;

					shop.fuegeArtikelEin(artname, artnr, artbestand, preis,
							packungsgroesse);
					shop.schreibeArtikeldaten();
					JOptionPane.showMessageDialog(null,
							"Artikel wurde eingefügt.");

				}

			} catch (ArtikelExistiertBereitsException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (command.equals("Artikel löschen")) {
			try {
				try {
					shop.entferneArtikel(Integer.parseInt(((ausgabeTabelle
							.getValueAt(ausgabeTabelle.getSelectedRow(), 0))
							.toString())));
				} catch (ArtikelExistiertNichtException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Artikel wurde gelöscht.");
				try {
					shop.schreibeArtikeldaten();
				} catch (IOException e1) {

				}
			} catch (NumberFormatException e2) {

				e2.printStackTrace();
			}

		}

		else if (command.equals("Bestand ändern")) {

			final JFrame bestand = new JFrame("Bestand ändern");
			JLabel best = new JLabel("Neuer Bestand:");
			final JTextField bestandNeu = new JTextField();
			JButton ok = new JButton("OK");

			bestand.getContentPane().setLayout(new GridLayout(2, 2));
			bestand.getContentPane().add(best);
			bestand.getContentPane().add(bestandNeu);
			bestand.getContentPane().add(new JLabel());
			bestand.getContentPane().add(ok);
			bestand.setSize(300, 100);
			bestand.setVisible(true);

			ok.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {

					try {

						int artnr = (Integer.parseInt((ausgabeTabelle
								.getValueAt(ausgabeTabelle.getSelectedRow(), 0))
								.toString()));
						// int bestandAendern =
						// ausgabeTabelle.getSelectedColumn();
						int newBestand1 = Integer.parseInt(bestandNeu.getText());

						if (newBestand1 <= 0) {
							shop.entferneArtikel(Integer.parseInt((ausgabeTabelle
									.getValueAt(
											ausgabeTabelle.getSelectedRow(), 0))
									.toString()));
							JOptionPane.showMessageDialog(null,
									"Artikel wurde gelöscht.");
							shop.schreibeArtikeldaten();
							bestand.setVisible(false);
						} else {

							// TODO geht noch nicht so ganz

							shop.aendereBestand(artnr, newBestand1);
							JOptionPane.showMessageDialog(null,
									"Bestand wurde geändert.");
							shop.schreibeArtikeldaten();
							bestand.setVisible(false);
						}
					} catch (IOException e1) {

					} catch (ArtikelExistiertNichtException e2) {
						JOptionPane.showMessageDialog(null, e2.getMessage());
						bestand.setVisible(false);
					}
				}
			});
		}

		else if (command.equals("In den Warenkorb legen")) {
			//TODO warenkorb
		}

		else if (command.equals("Warenkorb leeren")) {
			//TODO warenkorb

		}

		else if (command.equals("Bestellung abschließen")) {
			//TODO Bestellungen
			
		}

		else if (command.equals("Warenkorb anzeigen")) {
			//TODO Warenkorb

		}

	}

	@Override
	public void mouseClicked(MouseEvent me) {

		final String artname = bezeichnungsFeld.getText();
		final int artnr = Integer.parseInt(nummerFeld.getText());
		final int artbestand = Integer.parseInt(bestandFeld.getText());
		final float preis = Float.parseFloat(preisFeld.getText());
		int packungsgroesse = 0;

		if (me.getSource().equals(ja)) {

			JFrame packung = new JFrame("Packungsgröße festlegen");
			JLabel packungsgr = new JLabel("Packungsgröße:");
			final JTextField packungsgroesse2 = new JTextField();
			JButton okay = new JButton("OK");
			okay.addActionListener(new ActionListener() {

				int packungsgroesse = 0;

				public void actionPerformed(ActionEvent ae) {
					packungsgroesse = Integer.parseInt(packungsgroesse2
							.getText());

					try {
						shop.fuegeMassengutEin(artname, artnr, artbestand,
								preis, packungsgroesse);
						shop.schreibeArtikeldaten();
						JOptionPane.showMessageDialog(null,
								"Artikel wurde eingefügt.");
					} catch (ArtikelExistiertBereitsException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}

					catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			});
			packung.getContentPane().setLayout(new GridLayout(2, 2));
			packung.getContentPane().add(packungsgr);
			packung.getContentPane().add(packungsgroesse2);
			packung.getContentPane().add(new JLabel());
			packung.getContentPane().add(okay);
			// okay.addActionListener(this);
			// // TODO Artikel einfügen hier rein (AktionListener)
			packung.setSize(300, 100);
			packung.setVisible(true);

		} else if (me.getSource().equals(nein)) {
			packungsgroesse = 0;

			try {
				shop.fuegeArtikelEin(artname, artnr, artbestand, preis,
						packungsgroesse);
				// JOptionPane.showMessageDialog(null,
				// "Artikel wurde eingefügt.");
				shop.schreibeArtikeldaten();
			} catch (ArtikelExistiertBereitsException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private void gebeBestandAus() {
		alleArtikel = (Vector<Artikel>) shop.gibAlleArtikel();
		Vector<String> a = new Vector<String>();
		for (Artikel art : alleArtikel) {
			Vector<String> artikelVector = new Vector<String>();
			artikelVector.add(art.getName());
			a.addAll(artikelVector);

			ausgabeTabelle = new JTable();
			TableModel itemsModel = new ArtikelTableModel(alleArtikel, spalten);
			ausgabeTabelle.setModel(itemsModel);
			ausgabeTabelle.setAutoCreateRowSorter(true); // <<< sortiert hier
															// nach name
		}

	}
	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	};
}
