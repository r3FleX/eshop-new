package ui.GuiModule;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import domain.Shopverwaltung;
import valueobjects.Account;
import ui.GUI_2;

public class Gui_menuepanel implements ActionListener{

	private JMenuBar menuBar;
	private Shopverwaltung shop;
	private Account user;
	JLabel gesamt = new JLabel();
	
	public Gui_menuepanel(Shopverwaltung shop) {
		this.shop = shop;
		
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
		mnLogout.setEnabled(false);                                // <---- so deaktiviert man eintr�ge
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
	
	public JMenuBar getMenue() {
		return this.menuBar;
	}
	
	public void setMenue(JMenuBar menue) {
		this.menuBar = menue;
	}

	public void initialize() {
		//Men� Bereich
		
		
	}

	//F�r Men� Datei -> Beenden Button
	public void actionPerformed(ActionEvent arg0) {
		String command = arg0.getActionCommand();
		
		//F�r Men� Datei -> Beenden Button
		if (command.equals("Beenden")) {
			System.exit(0);
		}
		else if(command.equals("Ausloggen")){
			user = shop.logoutAccount(user.getName(), user.getPasswort());
			gesamt.setVisible(true);
			//this.setContentPane(this.hauptscreen);
			System.out.println("Tschüss!");
		}
		//F�r Men� Hilfe -> Artikel kaufen? Button
		else if (command.equals("Wie Artikel kaufen?")) {
			JOptionPane.showMessageDialog(null,
				"Willkommen im E-Shop. \n Wenn Sie Artikel kaufen wollen, dann registrieren"
				+ "Sie sich und loggen Sie sich ein! \n Anschliessend koennen Sie die gewuenschten "
			    + "Artikel kaufen.");
		}
		//F�r Men� Hilfe -> �ber uns Button
		else if (command.equals("\u00DCber uns")) {
			JOptionPane.showMessageDialog(null, "Entwickler: \n\n"
					+ "Immanuel Zimmermann \n" 
					+ "Stefan Meyer \n"
					+ "Daniel Boeckmann \n\n" 
					+ "HS Bremen, Prog 2, SS 2016");
		}
		
		
		System.out.println("menuepanel aktion ausgefuehrt");
	}
}
