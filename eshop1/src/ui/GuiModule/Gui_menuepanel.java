package ui.GuiModule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class Gui_menuepanel implements ActionListener{

	private JPanel menuepanel;
	
	//Menü Bereich
	JMenuBar menuBar = new JMenuBar();
	setJMenuBar(menuBar);

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

	JMenu mnHilfe = new JMenu("Hilfe");
	menuBar.add(mnHilfe);

	JMenuItem menuItem = new JMenuItem("Wie Artikel kaufen?");
	mnHilfe.add(menuItem);
	menuItem.addActionListener(this);

	JMenuItem mntmber = new JMenuItem("\u00DCber uns");
	mnHilfe.add(mntmber);
	mntmber.addActionListener(this);
	
	public void actionPerformed(ActionEvent e) {
		
	}
}
