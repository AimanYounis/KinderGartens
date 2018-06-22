package View;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.Kindergarden;

import javax.swing.JPanel;

public class KinTable extends JInternalFrame {
	
	private JTable table;
	private JPanel panel;
	private JScrollPane scrollPane;
	private HashMap<Kindergarden,Double> hm;
	private JButton btnNewButton;
	
	
	public KinTable(HashMap<Kindergarden,Double> hm){
		super("Kindergartens",false,true,false,true);
		setBounds(100, 100, 532, 466);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		btnNewButton = new JButton("Select");
		getContentPane().add(btnNewButton, BorderLayout.EAST);
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		this.hm = hm;
		initTable();
	}
	private void initTable() {
		
		Object[][] databaseInfo;
		Object[] columns = {"kindergartenID", "Name", "Monthly Cost", "Rating Average"};
		Object[] tempRow;
		databaseInfo = new Object[0][columns.length];
		
		DefaultTableModel dTableModel = new DefaultTableModel(databaseInfo, columns);
        
        for(Map.Entry<Kindergarden, Double> k : hm.entrySet()){
        	double b = k.getValue().doubleValue();
        	if(b <= 0)
        		tempRow = new Object[]{k.getKey().getID(), k.getKey().getName(), 
            			k.getKey().getPrice(), "Not rated"};
        	else
        		tempRow = new Object[]{k.getKey().getID(), k.getKey().getName(), 
        			k.getKey().getPrice(), k.getValue().doubleValue()};
        	
        	dTableModel.addRow(tempRow);
        }
        
		table = new JTable(dTableModel);
		table.setRowHeight(table.getRowHeight()+10);
		table.setAutoCreateRowSorter(true);
		table.setFont(new Font("Serif", Font.PLAIN, 20));
		
		scrollPane = new JScrollPane(table);
		panel.add(scrollPane);

	}
	public void okClicked(ActionListener listener){
		btnNewButton.addActionListener(listener);
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
