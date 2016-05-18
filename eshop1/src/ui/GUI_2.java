package ui;

import java.awt.BorderLayout;
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
	private JList<String> buecherListe;
	private JTable buecherTabelle;
	
	public GUI_2(String datei) {
		super("Shop");

		try {
			shop = new Shopverwaltung(datei);

		} catch (IOException e2) {

		}
		this.initialize();
	}
	
	private void initialize() {
		setTitle("E-Shop");
		setSize(800, 600);
		

		//TOP PANEL (LOGIN PANEL)
		JPanel loginPanel = new JPanel();
		
		loginPanel.setLayout(new GridLayout(2, 3));
		loginPanel.add(new JLabel("Name"));
		loginPanel.add(new JLabel("Passwort"));
		loginPanel.add(new JLabel(""));
		
		nameTextField = new JTextField();
		passTextField = new JPasswordField();
		loginPanel.add(nameTextField);
		loginPanel.add(passTextField);
		
		//LoginButton
		JButton loginButton = new JButton("Login");
		loginPanel.add(loginButton);
		
		loginPanel.setBorder(BorderFactory.createTitledBorder("Login"));
		
		
		//MAIN PANEL
		JPanel mainPanel = new JPanel();
		
		
		
		
		// Inhalt des Frames zusammenbauen
		setLayout(new BorderLayout());
		add(loginPanel, BorderLayout.NORTH);

		//setVisible(true);	
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