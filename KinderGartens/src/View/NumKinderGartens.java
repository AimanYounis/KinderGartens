package View;

import java.awt.EventQueue;
import java.util.HashMap;

import javax.swing.JInternalFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class NumKinderGartens extends JInternalFrame {
	private HashMap<String,Integer> hm;
	
	public NumKinderGartens(HashMap<String,Integer> hm) {
		 super("Number of Kids in Kindergartens",true,true,false,true); 
		setBounds(100, 100, 450, 300);
		
		this.hm = hm;
		
		JFreeChart barChart = ChartFactory.createBarChart(
				"Number of Kids in Kindergartens",           
		         "Category",            
		         "Number",            
		         createDataset(),          
		         PlotOrientation.VERTICAL,           
		         true, true, false);
		         
		      ChartPanel chartPanel = new ChartPanel( barChart );        
		      chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );        
		      setContentPane( chartPanel ); 
		      
		     

	}
	
	private CategoryDataset createDataset( ) {
	      final String Private = "Private";        
	      final String Public = "Public";        
	            
	      final DefaultCategoryDataset dataset = 
	      new DefaultCategoryDataset( );  

	      dataset.addValue( hm.get("private"),  Private , Private );        
	      dataset.addValue( hm.get("public"),  Public , Public );           

	    
	      return dataset; 
	   }
}