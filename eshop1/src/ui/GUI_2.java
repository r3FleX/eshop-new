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


public class GUI_2 extends JFrame implements ActionListener{

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
					GUI_2 frame = new GUI_2("Shop");
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
	public GUI_2(String datei) {
		super("Shop");

		try {
			shop = new Shopverwaltung(datei);
			//gebeBestandAus();

		} catch (IOException e2) {

		}
		this.initialize();
	}
}