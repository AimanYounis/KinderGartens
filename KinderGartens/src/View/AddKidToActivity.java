package View;


import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;

public class AddKidToActivity extends JInternalFrame {
	
	private JButton but;
	private JTextField textID;
	private JPanel panel;

	public AddKidToActivity() {
		super("Add Kid to Activity",false,true,false,false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 444, 167);
		Init();

	}
	
	public JTextField getIdField(){
		return textID;
	}
	
	public void SetButtonListener(ActionListener listener){
		but.addActionListener(listener);
	}

	private void Init(){
			getContentPane().setLayout(new BorderLayout(0, 0));
			
			panel = new JPanel();
			getContentPane().add(panel);
			panel.setLayout(null);
			
			
			JLabel lblKidId = new JLabel("Kid ID :");
			lblKidId.setBounds(78, 36, 46, 14);
			panel.add(lblKidId);
			
			but = new JButton("Show Activities");
			but.setBounds(150, 70, 135, 36);
			panel.add(but);
			
			textID = new JTextField();
			textID.setBounds(151, 34, 135, 20);
			panel.add(textID);


	}
	
	
}
