package View;

import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class ViewAssistantsInClass extends JInternalFrame {
	
	private JComboBox kID,cID;
	private JButton btnView;
	
	public ViewAssistantsInClass() {
		super("AssistantsInClass",false,true,false,true);
		setBounds(100, 100, 439, 83);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel lblKindergartenId = new JLabel("Kindergarten ID");
		panel.add(lblKindergartenId);
		
		 kID = new JComboBox();
		panel.add(kID);
		
		JLabel lblClassNumber = new JLabel("Class Number");
		panel.add(lblClassNumber);
		
		 cID = new JComboBox();
		panel.add(cID);
		
		 btnView = new JButton("View");
		panel.add(btnView);

	}
	
	public void btnView(ActionListener listener){
		btnView.addActionListener(listener);
	}

	public JComboBox getkID() {
		return kID;
	}

	public JComboBox getcID() {
		return cID;
	}

	public void setkID(JComboBox kID) {
		this.kID = kID;
	}

	public void setcID(JComboBox cID) {
		this.cID = cID;
	}
	
	
	
}
