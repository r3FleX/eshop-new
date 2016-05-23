package ui.GuiModule;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Gui_loginpanel implements ActionListener{

	private JPanel loginPanel;	

	public Gui_loginpanel() {
	
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(new GridLayout(1, 3));
		// Rahmen mit Text
		loginPanel.setBorder(BorderFactory.createTitledBorder("Login"));
	
		loginPanel.setLayout(new GridLayout(4, 1));
	
		JLabel lblName = new JLabel("Name:");
		loginPanel.add(lblName);
	
		JTextField textField = new JTextField();
		loginPanel.add(textField);
	
		JLabel lblPasswort = new JLabel("Passwort:");
		loginPanel.add(lblPasswort);
	
		JPasswordField passwordField = new JPasswordField();
		loginPanel.add(passwordField);
	
		loginPanel.setLayout(new GridLayout(2, 2));
	
		JButton login = new JButton("Login");
		login.addActionListener(this);	
		loginPanel.add(login);
		setloginPanel(loginPanel);	
	
	}
	public JPanel getloginPanel() {
		return loginPanel;
	}

	public void setloginPanel(JPanel suchPanel) {
		this.loginPanel = suchPanel;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("Some tests");	
	}
}
