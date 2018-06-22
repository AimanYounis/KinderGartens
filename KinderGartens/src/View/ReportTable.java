package View;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class ReportTable extends JInternalFrame {
	
	private JTable table;
	ArrayList<Object[]> ok;
	private JPanel panel;
	private Object[] columns;
	private JScrollPane scrollPane;
	
	public ReportTable(ArrayList<Object[]> hm,Object[] columns) {
		super("Report",true,true,true,true);
		init();
		this.ok=hm;
		this.columns=columns;
		initTable();
	}

	private void init(){
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
	}
	
	private void initTable(){
		Object[][] databaseInfo;
		Object[] tempRow = null;
		databaseInfo = new Object[0][columns.length];
		
		DefaultTableModel dTableModel = new DefaultTableModel(databaseInfo, columns);
		
		
		for(int i =0 ; i<ok.size();i++){
			tempRow = ok.get(i);
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
}
