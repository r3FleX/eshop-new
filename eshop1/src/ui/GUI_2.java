package ui;

import java.awt.BorderLayout;
import java.awt.Color;
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
import ui.GuiModule.Gui_artikelpanel;
import ui.GuiModule.Gui_loginpanel;
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
		//LayoutPanel
		JPanel mainPanel = new JPanel();
		JPanel navframe = new JPanel();		
		JPanel contentframe = new JPanel();
		//
		mainPanel.setLayout(new BorderLayout());
		navframe.setLayout(new BorderLayout());
		contentframe.setLayout(new BorderLayout());
		
		//standart anzeige Laden
		// Login + account erstellen
		//TODO login + account integrieren
		Gui_loginpanel loginPanel = new Gui_loginpanel();
		navframe.add(loginPanel.getloginPanel(), BorderLayout.NORTH);			
		
		//content frame 		
		//suche
		Gui_suchepanel suchPanel = new Gui_suchepanel();
		contentframe.add(suchPanel.getSuchPanel(), BorderLayout.NORTH);	
		//Artikelliste
		Gui_artikelpanel artikelPanel = new Gui_artikelpanel(shop.gibAlleArtikel());			
		contentframe.add(artikelPanel.getArtikelPanel(), BorderLayout.CENTER);
		
		//zusammenbasteln
		
		mainPanel.add(navframe,BorderLayout.NORTH);
		mainPanel.add(contentframe,BorderLayout.CENTER);
		//ausgeben
		add(mainPanel);
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