package View;


import java.awt.CardLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;

public class GuestIn extends JFrame {

	private JPanel contentPane;
	private static GuestIn view;
	private ActivityTable AT;
	private AddOpinion ao;
	private RemoveKid rk;
	private JDesktopPane panel;
	private JTextField textField;
	private JPanel PanelUp, PanelDown;
	private JButton btnValidate,btnAddReview,btnKidToActivity,btnRemoveKid,btnLogout;
	private JButton btnBack;
	private JButton btnViewKindergartenReviews;

	
	public static GuestIn getInstance(){
		if(view == null){
			view = new GuestIn();
		}
		return view;
	}
	public void SetAddOpinionFrame(AddOpinion ao){
		this.ao=ao;
		panel.add(ao);
	}
	public void SetRemoveKid(RemoveKid rk){
		this.rk=rk;
		panel.add(rk);
	}
	public void executeView(){
		this.setVisible(true);
	}
	/**
	 * Create the frame.
	 */
	public GuestIn() {
		initPanel();
		initAll();
	}
	public void SetViewOpListener(ActionListener listener){
		btnViewKindergartenReviews.addActionListener(listener);
	}

	public void SetValidateActionListener(ActionListener listener){
		btnValidate.addActionListener(listener);
	}
	public void SetButtonLogoutActionListener(ActionListener listener){
		btnLogout.addActionListener(listener);
	}
	public void setButtonBackListener(ActionListener listener){
		btnBack.addActionListener(listener);
	}
	public String GetID(){
		return textField.getText();
	}
	public void SetText(String s){
		textField.setText("");
	}
	public void SetAddReviewListener(ActionListener listener){
		btnAddReview.addActionListener(listener);
	}
	public void SetKidToActivityListener(ActionListener listener){
		btnKidToActivity.addActionListener(listener);
	}
	public void SetRemoveKid(ActionListener listener){
		btnRemoveKid.addActionListener(listener);
	}
	public void setActivityTableFrame(ActivityTable AT){
		this.AT=AT;
		panel.add(this.AT);
	}
	private void initAll(){
	
	}
	private void initPanel(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		panel = new JDesktopPane();
		contentPane.add(panel, "name_440262604620374");
		panel.setLayout(new CardLayout(0, 0));
		
		PanelUp = new JPanel();
		panel.add(PanelUp, "name_580626680062223");
		PanelUp.setVisible(false);
		PanelUp.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Please enter kid id :");
		lblNewLabel.setBounds(151, 9, 94, 14);
		PanelUp.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(250, 6, 86, 20);
		PanelUp.add(textField);
		textField.setColumns(10);
		
		 btnValidate = new JButton("Validate");
		 btnValidate.setBounds(341, 5, 71, 23);
		 PanelUp.add(btnValidate);
		 
		 btnBack = new JButton("Back");
		 btnBack.setBounds(465, 297, 89, 23);
		 PanelUp.add(btnBack);
		 
		PanelDown = new JPanel();
		PanelDown.setBackground(Color.WHITE);
		panel.add(PanelDown, "name_580611391931747");
		PanelDown.setLayout(null);
		PanelDown.setVisible(false);
		
		btnAddReview = new JButton("Add Review");
		btnAddReview.setBackground(Color.CYAN);
		btnAddReview.setBounds(67, 105, 117, 23);
		PanelDown.add(btnAddReview);
		btnAddReview.setEnabled(false);
		
		 btnKidToActivity = new JButton("Add Kid to Activity");
		 btnKidToActivity.setBackground(Color.CYAN);
		 btnKidToActivity.setBounds(67, 154, 151, 23);
		 PanelDown.add(btnKidToActivity);
		 btnKidToActivity.setEnabled(false);
		 
		 btnRemoveKid = new JButton("Remove kid from Activity");
		 btnRemoveKid.setBackground(Color.CYAN);
		 btnRemoveKid.setBounds(321, 154, 151, 23);
		 PanelDown.add(btnRemoveKid);
		 btnRemoveKid.setEnabled(false);
		 
		 JLabel lblWhatYouLike = new JLabel("What do you like to do ?");
		 lblWhatYouLike.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 16));
		 lblWhatYouLike.setBounds(178, 52, 202, 28);
		 PanelDown.add(lblWhatYouLike);
		 
		  btnLogout = new JButton("Logout");
		 btnLogout.setBounds(481, 297, 73, 23);
		 PanelDown.add(btnLogout);
		 
		 btnViewKindergartenReviews = new JButton("View Kindergarten Reviews");
		 btnViewKindergartenReviews.setBackground(Color.CYAN);
		 btnViewKindergartenReviews.setBounds(321, 105, 202, 23);
		 btnViewKindergartenReviews.setEnabled(false);
		 PanelDown.add(btnViewKindergartenReviews);
		  

		   
		}
	
	public void RemoveFirstPanel(){
		panel.setVisible(false);
		PanelDown.setVisible(true);
		PanelUp.setVisible(false);
	}
	public void RemoveSecondPanel(){
		
		PanelDown.setVisible(false);
	}
	public void AddPanelDown(){
		btnViewKindergartenReviews.setEnabled(true);
		btnRemoveKid.setEnabled(true);
		btnKidToActivity.setEnabled(true);
		btnAddReview.setEnabled(true);
	}
	public void setOp(AddOpinion ad) {
		panel.add(ad);
		
	}
}
