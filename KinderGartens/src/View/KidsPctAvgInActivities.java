package View;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class KidsPctAvgInActivities extends JInternalFrame {

	private JButton btnSelect;
	private JPanel panel, panel_1;
	private JComboBox comboBox;
	private JFreeChart chart;
	private JLabel lblKindergartenid;
	private JLabel lblNewLabel;
	private JComboBox cNum;
	
	public KidsPctAvgInActivities() {
		super("View Kids In Activties Registration Percentage",true,true,false,false);
		setBounds(100, 100, 450, 300);
		
		panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.NORTH);
	    
	    lblKindergartenid = new JLabel("KindergartenID");
	    panel_1.add(lblKindergartenid);
		
	    comboBox = new JComboBox();
		panel_1.add(comboBox);
		
		lblNewLabel = new JLabel("Class Number");
		panel_1.add(lblNewLabel);
		
		cNum = new JComboBox();
		panel_1.add(cNum);
		
		btnSelect = new JButton("Select");
		panel_1.add(btnSelect);
		
	}
	
	public void initView( HashMap<String, Double> avgActs){
		if(chart != null){
			chart = null;
		}
		
		 DefaultPieDataset dataset = new DefaultPieDataset();
		
		 for(Map.Entry<String, Double> map : avgActs.entrySet()){
			 dataset.setValue(map.getKey(), map.getValue());
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

	public JComboBox getcNum() {
		return cNum;
	}

	public void setcNum(JComboBox cNum) {
		this.cNum = cNum;
	}

	public JComboBox getBox() {
		
		return comboBox;
	}

}
