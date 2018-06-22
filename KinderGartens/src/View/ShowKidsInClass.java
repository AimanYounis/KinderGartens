package View;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import Model.Kid;

public class ShowKidsInClass extends JInternalFrame {

	private JTable table;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JPanel panel_1;
	private JLabel lblKindergartenId;
	private JComboBox kID;
	private JLabel lblClassNumber;
	private JComboBox cID;
	private JButton btnNewButton;
	
	public ShowKidsInClass(){
		super("Kids In Class",false,true,false,false);
		setBounds(100, 100, 608, 520);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 572, 73);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		lblKindergartenId = new JLabel("Kindergarten ID");
		lblKindergartenId.setBounds(10, 11, 88, 51);
		panel_1.add(lblKindergartenId);
		
		kID = new JComboBox();
		kID.setBounds(116, 11, 88, 36);
		panel_1.add(kID);
		
		lblClassNumber = new JLabel("Class Number");
		lblClassNumber.setBounds(225, 11, 76, 51);
		panel_1.add(lblClassNumber);
		
		cID = new JComboBox();
		cID.setBounds(324, 11, 88, 36);
		panel_1.add(cID);
		
		btnNewButton = new JButton("Select");
		btnNewButton.setBounds(422, 11, 140, 36);
		panel_1.add(btnNewButton);
		
		
	}
	
	public void initTable(ArrayList<Kid> hm) {
		
		if(table != null){
			panel.remove(scrollPane);
			scrollPane = null;
			table = null;
		}
		
		Object[][] databaseInfo;
		Object[] columns = {"ID", "Full Name", "Date Of Birth"};
		Object[] tempRow;
		databaseInfo = new Object[0][columns.length];
		
		DefaultTableModel dTableModel = new DefaultTableModel(databaseInfo, columns);
        
        for(Kid k : hm){

        	tempRow = new Object[]{k.getID(), k.getFirstName()+" " +k.getSurName(), k.getDateOfBirth()};

        	dTableModel.addRow(tempRow);
        }
		
        
		table = new JTable(dTableModel);
		table.setRowHeight(table.getRowHeight()+10);
		resizeColumnWidth(table);
		
		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(false);
		table.setFont(new Font("Serif", Font.PLAIN, 20));
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 95, 569, 380);
		panel.add(scrollPane);
		

	}
	private void resizeColumnWidth(JTable table) {
		    final TableColumnModel columnModel = table.getColumnModel();
		    for (int column = 0; column < table.getColumnCount(); column++) {
		        int width = 15; // Min width
		        for (int row = 0; row < table.getRowCount(); row++) {
		            TableCellRenderer renderer = table.getCellRenderer(row, column);
		            Component comp = table.prepareRenderer(renderer, row, column);
		            width = Math.max(comp.getPreferredSize().width +1 , width);
		        }
		        if(width > 300)
		            width=300;
		        columnModel.getColumn(column).setPreferredWidth(width);
		    }
		
	}
	public void okClicked(ActionListener listener){
		btnNewButton.addActionListener(listener);
	}
	
	public void comboboxKListener(ActionListener listener){
		kID.addActionListener(listener);
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

	public JButton getBtnNewButton() {
		return btnNewButton;
	}
	public void setBtnNewButton(JButton btnNewButton) {
		this.btnNewButton = btnNewButton;
	}
	public JTable getTable(){
		return table;
	}
}
