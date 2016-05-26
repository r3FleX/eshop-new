package ui.GuiModule;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import domain.Shopverwaltung;
import domain.exceptions.AccountExistiertBereitsException;
import domain.exceptions.AccountExistiertNichtException;
import valueobjects.Account;
import valueobjects.Kunde;
import valueobjects.Mitarbeiter;

public class Gui_loginpanel implements ActionListener{

	private JPanel loginPanel;	
	private Shopverwaltung shop;
	private Account user;
	private JTextField nameTextField;
	private JPasswordField passTextField;

	public Gui_loginpanel(Shopverwaltung shop) {
		this.shop = shop;
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(new GridLayout(1, 2));
		loginPanel.setVisible(true);		
		loginPanel.setBorder(BorderFactory.createTitledBorder("Kundenbereich - Willkommen !")); //ï¿½berschrift Login
		
		setloginPanel(loginPanel);
	}
	
	public JPanel getloginPanel() {
		return loginPanel;
	}

	public void setloginPanel(JPanel loginPanel) {
		this.loginPanel = loginPanel;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		String command = arg0.getActionCommand();
		//loginPanel.setVisible(false);	
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
					loginPanel.setVisible(true);	
					
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
			
			//Fuer Menï¿½ Account -> Registrieren -> Registrieren Button
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
	}
}