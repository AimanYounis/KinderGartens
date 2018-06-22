package View;

import java.awt.event.ActionListener;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class ActivityOrganizor extends JInternalFrame {
	
	private JButton btnAddActivityTo, btnShowSchedualeFor, btnRemove, btnEditActivittyDetails;
	private JComboBox kID, cID, dow;

	public ActivityOrganizor() {
		super("Manage Activities In Class",false,true,false,false);
		setBounds(100, 100, 573, 419);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 537, 92);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblKindergartenId = new JLabel("Kindergarten ID");
		lblKindergartenId.setBounds(10, 11, 94, 22);
		panel_1.add(lblKindergartenId);
		
		 kID = new JComboBox();
		kID.setBounds(114, 13, 60, 20);
		panel_1.add(kID);
		
		JLabel lblClassNumber = new JLabel("Class Number");
		lblClassNumber.setBounds(202, 11, 94, 22);
		panel_1.add(lblClassNumber);
		
		 cID = new JComboBox();
		cID.setBounds(306, 11, 60, 22);
		panel_1.add(cID);
		
		JLabel lblDayOfWeek = new JLabel("Day Of week");
		lblDayOfWeek.setBounds(10, 44, 94, 22);
		panel_1.add(lblDayOfWeek);
		
		 dow = new JComboBox();
		dow.setBounds(114, 44, 180, 22);
		panel_1.add(dow);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 114, 537, 264);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		 btnShowSchedualeFor = new JButton("Show Scheduale for Selected Day");
		btnShowSchedualeFor.setBounds(10, 11, 517, 52);
		panel_2.add(btnShowSchedualeFor);
		
		 btnAddActivityTo = new JButton("Add Activity To Class");
		btnAddActivityTo.setBounds(10, 74, 517, 52);
		panel_2.add(btnAddActivityTo);
		
		 btnRemove = new JButton("Remove Activity from Class");
		btnRemove.setBounds(10, 201, 517, 52);
		panel_2.add(btnRemove);
		
		 btnEditActivittyDetails = new JButton("Edit Activitty Details");
		btnEditActivittyDetails.setBounds(10, 137, 517, 52);
		panel_2.add(btnEditActivittyDetails);

	}
	
	public void btnRemoveListener(ActionListener listener){
		btnRemove.addActionListener(listener);
	}
	
	public void btnAddActivityToListener(ActionListener listener){
		btnAddActivityTo.addActionListener(listener);
	}
	
	public void btnShowSchedualeForListener(ActionListener listener){
		btnShowSchedualeFor.addActionListener(listener);
	}
	
	public void addReArrangeListener(ActionListener actionListener) {
		btnEditActivittyDetails.addActionListener(actionListener);
		
	}
	
	

	public JComboBox getkID() {
		return kID;
	}

	public JComboBox getcID() {
		return cID;
	}

	public JComboBox getDow() {
		return dow;
	}

	public void setkID(JComboBox kID) {
		this.kID = kID;
	}

	public void setcID(JComboBox cID) {
		this.cID = cID;
	}

	public void setDow(JComboBox dow) {
		this.dow = dow;
	}
}
