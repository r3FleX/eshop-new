package ui.GuiModule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import domain.Shopverwaltung;


public class Gui_menuepanel implements ActionListener{

	private JMenuBar menuBar;
	
	public JMenuBar getMenue() {
		return this.menuBar;
	}

	public void setMenue(JMenuBar menue) {
		this.menuBar = menue;
	}

	public Gui_menuepanel(Shopverwaltung shop){
		//Menï¿½ Bereich
		JMenuBar menueBar = new JMenuBar();		
		
		JMenu mnDatei = new JMenu("Datei");
		menueBar.add(mnDatei);

		JMenuItem mntmBeenden = new JMenuItem("Beenden");
		mntmBeenden.addActionListener(this);
		mnDatei.add(mntmBeenden);
		
		JMenu mnAccount = new JMenu("Account");
		menueBar.add(mnAccount);
		
		JMenuItem mnLogin = new JMenuItem("Einloggen");
		mnAccount.add(mnLogin);
		mnLogin.addActionListener(this);
		
		JMenuItem mnReg = new JMenuItem("Registrieren");
		mnReg.addActionListener(this);
		mnAccount.add(mnReg);
		
		JMenuItem mnLogout = new JMenuItem("Ausloggen");
//TODO trigger 
		mnLogout.setEnabled(false);                                // <---- so deaktiviert man einträge
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
		setMenue(menueBar);
		
	}

	//Fï¿½r Menï¿½ Datei -> Beenden Button
	public void actionPerformed(ActionEvent arg0) {
		String command = arg0.getActionCommand();
		
		//Fï¿½r Menï¿½ Datei -> Beenden Button
		if (command.equals("Beenden")) {
			System.exit(0);
		}
		
		
		
		System.out.println("menuepanel aktion ausgeführt");
	}
}
