package View;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import Model.Kid;
import Model.Kindergarden;

public class ShowKids extends JInternalFrame { // new
	
	private JPanel panel_2;
	private JScrollPane scrollPane;
	private ArrayList<Kid> hm;
	private JTable table;
	
	
	public ShowKids(ArrayList<Kid> hm) {
		super("All Kids",true,true,true,true);
		
		setBounds(100, 100, 994, 476);
		 panel_2 = new JPanel();
		 getContentPane().add(panel_2, BorderLayout.CENTER);
		 panel_2.setLayout(new BorderLayout(0, 0));
		
		this.hm = hm;
		initTable();

	}

	private void initTable() {
		Object[][] databaseInfo;
		Object[] columns = {"ID", "Full Name", "Date Of Birth", "Street", "House Number", "City", 
				"Latitude", "Longtitude", "Father Name", "Mother Name", "Class"};
		Object[] tempRow;
		databaseInfo = new Object[0][columns.length];
		
		DefaultTableModel dTableModel = new DefaultTableModel(databaseInfo, columns);
        
        for(Kid k : hm){

        	tempRow = new Object[]{k.getID(), k.getFirstName()+" " +k.getSurName(), k.getDateOfBirth(), k.getStreet(), 
        			k.getHouseNumber(), k.getCity(), k.getLatitude(), k.getLongitude(), k.getFatherName(), k.getMotherName(),
        			k.getcLass()};

        	dTableModel.addRow(tempRow);
        }
        
		table = new JTable(dTableModel);
		table.setRowHeight(table.getRowHeight()+10);
		table.setAutoCreateRowSorter(true);
		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(false);
		resizeColumnWidth(table);
		table.setFont(new Font("Serif", Font.PLAIN, 20));
		
		scrollPane = new JScrollPane(table);
		panel_2.add(scrollPane, BorderLayout.CENTER);
		
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
}
