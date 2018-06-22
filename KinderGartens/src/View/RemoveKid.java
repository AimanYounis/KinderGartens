package View;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class RemoveKid extends JInternalFrame {
	
	private JButton btnRemove;
	private JComboBox comboBox;
	
	public RemoveKid() {
		
		super("Add Kid",false,true,false,true);
		setBounds(100, 100, 450, 159);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 71, 414, 47);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		btnRemove = new JButton("Remove");
		btnRemove.setBounds(280, 11, 124, 25);
		panel.add(btnRemove);
		
		comboBox = new JComboBox();
		comboBox.setBounds(10, 11, 260, 25);
		panel.add(comboBox);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 414, 49);
		getContentPane().add(panel_1);
		
		JLabel lblEnterKidFor = new JLabel("Enter Kid ID For Removal :");
		lblEnterKidFor.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 17));
		panel_1.add(lblEnterKidFor);
	}
	
	public void removeKidListener(ActionListener listener){
		btnRemove.addActionListener(listener);
	}

	public JComboBox getComboBox() {
		return comboBox;
	}

	public void setComboBox(JComboBox comboBox) {
		this.comboBox = comboBox;
	}
	
}
