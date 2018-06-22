package View;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import Model.Activity;
import Model.TakesPlace;

public class ActivityTable extends JInternalFrame { //chnged

	private JTable table;
	private JPanel panel;
	private JScrollPane scrollPane;
	private ArrayList<TakesPlace> hm;
	private ArrayList<Activity> acts;
	private JButton btnNewButton;
	
	
	public ActivityTable(ArrayList<TakesPlace> hm){
		super("Activities",true,true,false,false);
		iniStuff();
		this.hm = hm;
		initTable();
	}
	
	private void iniStuff(){
		setBounds(100, 100, 532, 466);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		btnNewButton = new JButton("Select");
		getContentPane().add(btnNewButton, BorderLayout.EAST);
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
	}
	
	private void initTable() {
		
		Object[][] databaseInfo;
		Object[] columns = {"ID","Name", "length", "cost", "Operator ID", "Operator Name", "Day", "Start Time"};
		Object[] tempRow;
		databaseInfo = new Object[0][columns.length];
		
		DefaultTableModel dTableModel = new DefaultTableModel(databaseInfo, columns);
		String day;
		for(TakesPlace A : hm){
			
			switch(A.getDayInWeek()){
        	
    		case 1: day = "SUNDAY";
    			break;
    		case 2: day = "MONDAY";
    			break;
    		case 3: day = "TUESDAY";
    			break;
    		case 4: day = "WEDNESDAY";
    			break;
    		case 5: day = "THURSDAY";
    			break;
    		case 6: day = "FRIDAY";
    			break;
    		default :
    			day = "Not Available";
    			break;
    	}
			tempRow = new Object[]{A.getActivity().getID(), A.getActivity().getName(), A.getActivity().getLength(), 
							A.getActivity().getCost(),A.getOperator().getID(), A.getOperator().getFirstName()+" " + A.getOperator().getSurName(), 
								day, A.getStartTime()};
			dTableModel.addRow(tempRow);
		}

        
		table = new JTable(dTableModel);
		table.setRowHeight(table.getRowHeight()+10);
		table.setAutoCreateRowSorter(true);
		resizeColumnWidth(table);
		table.setFont(new Font("Serif", Font.PLAIN, 20));
		
		scrollPane = new JScrollPane(table);
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
