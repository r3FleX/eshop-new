package ui.GuiModule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import domain.Shopverwaltung;


public class Gui_menuepanel implements ActionListener{

	private JPanel menuepanel;
	
	public Gui_menuepanel(Shopverwaltung shop){
		
	}
	
	//Men� Bereich
	JMenuBar menuBar = new JMenuBar();
	setJMenuBar(menuBar);
	/*
	JMenu mnDatei = new JMenu("Datei");
	menuBar.add(mnDatei);

	JMenuItem mntmBeenden = new JMenuItem("Beenden");
	mntmBeenden.addActionListener(this);
	mnDatei.add(mntmBeenden);
	
	JMenu mnAccount = new JMenu("Account");
	menuBar.add(mnAccount);
	
	JMenuItem mnLogin = new JMenuItem("Einloggen");
	mnAccount.add(mnLogin);
	mnLogin.addActionListener(this);
	
	JMenuItem mnReg = new JMenuItem("Registrieren");
	mnReg.addActionListener(this);
	mnAccount.add(mnReg);
	
	JMenuItem mnLogout = new JMenuItem("Ausloggen");
	mnAccount.add(mnLogout);
	mnLogout.addActionListener(this);

	JMenu mnHilfe = new JMenu("Hilfe");
	menuBar.add(mnHilfe);

	JMenuItem menuItem = new JMenuItem("Wie Artikel kaufen?");
	mnHilfe.add(menuItem);
	menuItem.addActionListener(this);

	JMenuItem mntmber = new JMenuItem("\u00DCber uns");
	mnHilfe.add(mntmber);
	mntmber.addActionListener(this);
	*/
	//F�r Men� Datei -> Beenden Button
	public void actionPerformed(ActionEvent arg0) {
		String command = arg0.getActionCommand();
		
		//F�r Men� Datei -> Beenden Button
		if (command.equals("Beenden")) {
			System.exit(0);
		}
	}
}
