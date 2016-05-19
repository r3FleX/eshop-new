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
	private JPasswordField passTextField;

	public GUI_2(String datei) {
		setTitle("E-Shop");
		setSize(800, 600); //fenstergröße
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		try {
			shop = new Shopverwaltung(datei);

		} catch (IOException e2) {

		}
		this.initialize();
	}
	
	private void initialize() {
		//Inhalt der Startseite festlegen
		setLayout(new BorderLayout());
		Gui_suchepanel suchPanel = new Gui_suchepanel();
		add(suchPanel.getSuchPanel(), BorderLayout.NORTH);	
		
		//MAIN PANEL
		JPanel mainPanel = new JPanel();
		
		//Artikel
		JPanel artikelPanel = new JPanel();
		artikelPanel.setBorder(BorderFactory.createTitledBorder("Artikel"));
		
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
		/********
		//ausgabeTabelle.setAutoCreateRowSorter(true);
		//scrollPane.setViewportView(ausgabeTabelle);

		//tModel.setDataVector(shop.gibAlleArtikel());
		
		//ausgabeTabelle = new JTable(tModel);
		//ausgabeTabelle.setPreferredSize(new Dimension(400, 600));
		
		// JTable in ScrollPane platzieren:
		//scrollPane = new JScrollPane(ausgabeTabelle);
		//ausgabeTabelle.setAutoCreateRowSorter(true);
		//scrollPane.setViewportView(ausgabeTabelle);
		********/
		// Anzeige der Artikelliste auch in der Kunden-Ansicht
		artikeltable.setDataVector(shop.gibAlleArtikel());

		artikelPanel.add(scrollPane);
		
		add(artikelPanel, BorderLayout.CENTER);
		//artikelPanel.setLayout(new GridLayout(1,2));
		
		/*String [][] inhalt = { {"Beatles","Help"}, {"Beatwatt","Is That All"}, {"ABBA","Waterloo"} };
	    //String[] titel = {"Interpret", "Titel"};
		//JTable table = new JTable(inhalt, titel);
		add(new JScrollPane(table)); 
		*/
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

	public void actionPerformed(ActionEvent arg0) {
	}
}