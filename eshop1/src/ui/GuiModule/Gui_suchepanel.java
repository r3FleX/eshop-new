package ui.GuiModule;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Gui_suchepanel implements ActionListener {
	private JPanel suchPanel;
	
	public Gui_suchepanel() {
		//Suchleiste
		JPanel suchPanel = new JPanel();
		suchPanel.setLayout(new GridLayout(1, 3));
		suchPanel.add(new JLabel("Suchbegriff: "));
		JTextField searchTextField = new JTextField();
		searchTextField.addActionListener(this);
		searchTextField.setToolTipText("Suchbegriff hier eintragen!");
		suchPanel.add(searchTextField);
		JButton suchButton = new JButton("Such!");
		suchButton.addActionListener(this);
		suchPanel.add(suchButton);		
		suchPanel.setBorder(BorderFactory.createTitledBorder("Suchen"));
		setSuchPanel(suchPanel);
	}

	public JPanel getSuchPanel() {
		return suchPanel;
	}

	public void setSuchPanel(JPanel suchPanel) {
		this.suchPanel = suchPanel;
	}

	
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("Some tests");
		
	}	
	

}
