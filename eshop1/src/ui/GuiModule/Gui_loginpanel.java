package ui.GuiModule;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import domain.Shopverwaltung;
import domain.exceptions.AccountExistiertNichtException;
import valueobjects.Account;
import valueobjects.Kunde;
import valueobjects.Mitarbeiter;

public class Gui_loginpanel implements ActionListener{

	private JPanel loginPanel;	
	//private Account user;

	public Gui_loginpanel(Shopverwaltung shop) {
		
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(new GridLayout(1, 2));
		//loginPanel.setVisible(false);
		
		loginPanel.setBorder(BorderFactory.createTitledBorder("Kundenbereich - Willkommen "+ user.getName() + "!")); //Überschrift Login
		
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
			login.add(loginButton);
			
			login.setVisible(true);
			//hole Name und Passwort aus Textfelder
			String name = nameFeld.getText();
			String passwort = String.valueOf(passwortFeld.getPassword());
	
			//ï¿½berprï¿½fe ob Kunde oder Mitarbeiter
			try {
				user = shop.loginAccount(name, passwort);
				
				if (user instanceof Kunde) {
					//loginPanel.setVisible(true);
					System.out.println("Kunde eingeloggt");
					JOptionPane.showMessageDialog(null,"Erfolgreich als Kunde eingeloggt!");
					
				}
				else if (user instanceof Mitarbeiter){
					System.out.println("Mitarbeiter eingeloggt");
					JOptionPane.showMessageDialog(null,"Erfolgreich als Mitarbeiter eingeloggt!");
					login.setVisible(false); //Login Eingabefenster schlieï¿½en
				}
			} catch (AccountExistiertNichtException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		}
	}
}
