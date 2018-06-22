package View;

import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AddActivityToClass extends JInternalFrame {
	
	private JTextField time, aID;
	private JButton btnSelectActivity, btnAdd;
	private JComboBox opID, dow;
	
	public AddActivityToClass() {
		super("Add Activity To Class",false,true,false,false);
		setBounds(100, 100, 711, 414);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		
		JLabel lblDay = new JLabel("Day");
		panel_1.add(lblDay);
		
		 dow = new JComboBox();
		panel_1.add(dow);
		
		JLabel lblOperatorId = new JLabel("Operator ID");
		panel_1.add(lblOperatorId);
		
		 opID = new JComboBox();
		panel_1.add(opID);
		
		JLabel lblStartTime = new JLabel("Start Time");
		panel_1.add(lblStartTime);
		
		time = new JTextField();
		panel_1.add(time);
		time.setColumns(10);
		
		 btnAdd = new JButton("Add");
		panel.add(btnAdd, BorderLayout.EAST);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.NORTH);
		
		JLabel lblActivityId = new JLabel("Activity ID");
		panel_3.add(lblActivityId);
		
		aID = new JTextField();
		panel_3.add(aID);
		aID.setColumns(10);
		
		 btnSelectActivity = new JButton("Select Activity");
		panel_2.add(btnSelectActivity, BorderLayout.CENTER);
		
		aID.setEditable(false);

	}
	
	public void btnSelectListener(ActionListener listener){
		btnSelectActivity.addActionListener(listener);
	}
	
	public void btnAddActivityListener(ActionListener listener){
		btnAdd.addActionListener(listener);
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public void setBtnAdd(JButton btnAdd) {
		this.btnAdd = btnAdd;
	}

	public JTextField getTime() {
		return time;
	}

	public JTextField getaID() {
		return aID;
	}

	public JComboBox getOpID() {
		return opID;
	}

	public JComboBox getDow() {
		return dow;
	}

	public void setTime(JTextField time) {
		this.time = time;
	}

	public void setaID(JTextField aID) {
		this.aID = aID;
	}

	public void setOpID(JComboBox opID) {
		this.opID = opID;
	}

	public void setDow(JComboBox dow) {
		this.dow = dow;
	}
	
	

}
