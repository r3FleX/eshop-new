package ui.GuiModule;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Gui_warenkorbpanel implements ActionListener {

	private JPanel warenkorbPanel;
	


	public Gui_warenkorbpanel() {	
		
		//Warenkorb Bereich
		JPanel warenKorbPanel = new JPanel();
		warenKorbPanel.setLayout(new GridLayout(1, 3));
		
		JButton inWarenKorbLegenButton = new JButton("in Warenkorb legen");
		warenKorbPanel.add(inWarenKorbLegenButton);
		inWarenKorbLegenButton.addActionListener(this);
		
		warenKorbPanel.add(new JLabel()); //Platzhalter
		
		JButton zumWarenKorbButton = new JButton("zum Warenkorb");
		warenKorbPanel.add(zumWarenKorbButton);
		zumWarenKorbButton.addActionListener(this);
		
		warenKorbPanel.setBorder(BorderFactory.createTitledBorder("Warenkorb")); //Überschrift Warenkorb
		setWarenkorbPanel(warenKorbPanel);		
	}
	
	public JPanel getWarenkorbPanel() {
		return warenkorbPanel;
	}

	public void setWarenkorbPanel(JPanel warenkorbPanel) {
		this.warenkorbPanel = warenkorbPanel;
	}	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("Warenkorb aktion ausgeführt");
		
	}		
	
}
