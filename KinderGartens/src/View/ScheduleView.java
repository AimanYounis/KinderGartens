package View;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JInternalFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.SimpleTimePeriod;

import Model.TakesPlace;

public class ScheduleView extends JInternalFrame {
	
	private ArrayList <TakesPlace> tp;
	
	public ScheduleView(ArrayList <TakesPlace> tp) {
		super("Schedule",true,true,true,true);
		setBounds(100, 100, 450, 300);
		
		this.tp = tp;
		initView();
	}
	
	private void initView(){
		IntervalCategoryDataset dataset = createDataset();
		JFreeChart chart = ChartFactory.createGanttChart(
	            "Activities Scheduled for Day",  // chart title
	            "Activity",              // domain axis label
	            "Hour",              // range axis label
	            dataset,             // data
	            true,                // include legend
	            false,                // tooltips
	            false                // urls
	        );
		
		CategoryPlot plot = chart.getCategoryPlot();
		DateAxis axis = (DateAxis) plot.getRangeAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("HH:mm"));
        
		setContentPane(new ChartPanel(chart));
	}
	
	private  IntervalCategoryDataset createDataset() {

        final TaskSeries s1 = new TaskSeries("Scheduled");
        long minute = 60000;
        long length;
        
        for(TakesPlace t : tp){
        	length = minute * (long)t.getActivity().getLength();
        	Task tt = new Task(t.getActivity().getName(),
                    new SimpleTimePeriod(t.getStartTime(),
                            new Time(t.getStartTime().getTime()+length)));
        	s1.add(tt);
        
       }

        final TaskSeriesCollection collection = new TaskSeriesCollection();
        collection.add(s1);

        return collection;
    }

}
