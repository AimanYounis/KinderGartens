package View;


import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;

public class AddOpinion extends JInternalFrame {
	
	private JPanel panel, panel_1, panel_2;
	private JLabel lblChoose, lblKindergatenId, lblActivityId, lblRating;
	private JButton btnSubmit;
	private JTextArea desc;
	private JScrollPane scrollPane;
	private JComboBox<Integer> rating, aID, kID, oID;
	private ButtonGroup bg;
	private JRadioButton rdbtnKindergarten, rdbtnActivity;
	private JLabel lblOpinionId;
	
	
	public AddOpinion() {
		super("Add Opinion",false,true,false,true);
		setBounds(100, 100, 527, 386);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		 panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 282, 42);
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		 lblChoose = new JLabel("Please Choose:");
		lblChoose.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		panel_1.add(lblChoose, BorderLayout.CENTER);
		
		 panel_2 = new JPanel();
		panel_2.setBounds(10, 64, 491, 70);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		 lblKindergatenId = new JLabel("Kindergaten ID");
		lblKindergatenId.setBounds(10, 11, 87, 24);
		panel_2.add(lblKindergatenId);
		
		 kID = new JComboBox();
		 lblKindergatenId.setLabelFor(kID);
		kID.setBounds(93, 13, 61, 20);
		panel_2.add(kID);
		
	    lblActivityId = new JLabel("Activity ID");
		lblActivityId.setBounds(185, 11, 61, 24);
		panel_2.add(lblActivityId);
		
		 aID = new JComboBox();
		 lblActivityId.setLabelFor(aID);
		aID.setBounds(259, 13, 61, 20);
		panel_2.add(aID);
		
	    lblRating = new JLabel("Rating");
		lblRating.setBounds(10, 47, 46, 14);
		panel_2.add(lblRating);
		
		 rating = new JComboBox();
		 lblRating.setLabelFor(rating);
		rating.setBounds(93, 46, 61, 20);
		panel_2.add(rating);
		
		 rdbtnKindergarten = new JRadioButton("Kindergarten");
		rdbtnKindergarten.setBounds(376, 12, 97, 23);
		panel_2.add(rdbtnKindergarten);
		rdbtnKindergarten.setSelected(true);
		
		 rdbtnActivity = new JRadioButton("Activity");
		rdbtnActivity.setBounds(376, 38, 97, 23);
		panel_2.add(rdbtnActivity);
		
		bg = new ButtonGroup();
		bg.add(rdbtnKindergarten);
		bg.add(rdbtnActivity);
		
		oID = new JComboBox();
		oID.setBounds(269, 44, 61, 20);
		panel_2.add(oID);
		
		lblOpinionId = new JLabel("Opinion ID ");
		lblOpinionId.setBounds(185, 46, 73, 15);
		panel_2.add(lblOpinionId);
		
		 scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 145, 491, 199);
		panel.add(scrollPane);
		
		 desc = new JTextArea();
		scrollPane.setViewportView(desc);
		
		 btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(302, 11, 199, 42);
		panel.add(btnSubmit);

	}
	
	public void submitListener(ActionListener listener){
		btnSubmit.addActionListener(listener);
	}
	
	public void RdbtnKindergartenListener(ActionListener listener){
		rdbtnKindergarten.addActionListener(listener);
	}
	
	public void RdbtnActivityListener(ActionListener listener){
		rdbtnActivity.addActionListener(listener);
	}
	
	public JRadioButton getRdbtnKindergarten() {
		return rdbtnKindergarten;
	}

	public JRadioButton getRdbtnActivity() {
		return rdbtnActivity;
	}

	public void setRdbtnKindergarten(JRadioButton rdbtnKindergarten) {
		this.rdbtnKindergarten = rdbtnKindergarten;
	}

	public void setRdbtnActivity(JRadioButton rdbtnActivity) {
		this.rdbtnActivity = rdbtnActivity;
	}

	public JButton getBtnSubmit() {
		return btnSubmit;
	}

	public JTextArea getDesc() {
		return desc;
	}

	public JComboBox getRating() {
		return rating;
	}

	public JComboBox getaID() {
		return aID;
	}

	public JComboBox getkID() {
		return kID;
	}

	public void setBtnSubmit(JButton btnSubmit) {
		this.btnSubmit = btnSubmit;
	}

	public void setDesc(JTextArea desc) {
		this.desc = desc;
	}

	public void setRating(JComboBox rating) {
		this.rating = rating;
	}

	public void setaID(JComboBox aID) {
		this.aID = aID;
	}

	public void setkID(JComboBox kID) {
		this.kID = kID;
	}
	
	public void setoID(JComboBox oID) {
		this.oID = oID;
	}

	public JComboBox getoID() {
		return oID;
	}
}
