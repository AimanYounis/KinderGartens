package View;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JInternalFrame;

import Model.Activity;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class ActsAvgKids extends JInternalFrame {
	
	private JButton btnSelect;
	private JPanel panel, panel_1;
	private JComboBox comboBox;
	private JFreeChart chart;
	
	public ActsAvgKids() {
		super("View Activties Registration Percentage",true,true,false,false);
		setBounds(100, 100, 450, 300);
		
		panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.NORTH);
		
	    comboBox = new JComboBox();
		panel_1.add(comboBox);
		
		btnSelect = new JButton("Select");
		panel_1.add(btnSelect);
		
	}
	
	public void initView( HashMap<Activity, Double> avgActs){
		if(chart != null){
			chart = null;
		}
		
		 DefaultPieDataset dataset = new DefaultPieDataset();
		
		 for(Map.Entry<Activity, Double> map : avgActs.entrySet()){
			 dataset.setValue(map.getKey().getID()+" :"+ map.getKey().getName(), map.getValue());
			}
		 
		 chart = ChartFactory.createPieChart(
	            "Regstration Pct",  // chart title
	            dataset,             // data
	            true,               // include legend
	            true,
	            false
	        );
		 
		
		 setContentPane(new ChartPanel(chart));
	}
	
	public void addBtnSelectListener(ActionListener listener){
		btnSelect.addActionListener(listener);
	}
	

	public JButton getBtnSelect() {
		return btnSelect;
	}

	public void setBtnSelect(JButton btnSelect) {
		this.btnSelect = btnSelect;
	}

	public JComboBox getBox() {
		
		return comboBox;
	}
}
