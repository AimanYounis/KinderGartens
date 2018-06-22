package View;


import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import java.awt.event.ActionEvent;

public class AddKidd extends JInternalFrame {
	
	private JTextField txtID, txtFname, txtLname, txtBDate, 
						txtHouseNum, txtCity, txtStreet, txtLat, txtLong, 
						txtfathName, txtMothname, comboBox;
	private ButtonGroup bg;
	private JButton button, btnViewKinders, btnClos;
	private JPanel panel_2, panel, panel_1, panel_3, panel_4,panel_5;
	private JLabel lblinfo, lblKidid, fname, lname, numHouse, 
					bDay, lblCity, lblStreet, lblLat, lblLongtitude,
					fathName, lblMotherName, lbloptional, label, lblchoose, label_1, label_2;
	private JRadioButton rdbtnPublic, rdbtnPrivate;
	private JComboBox txtCLassNum;
	private JTextField PlaceInF;
	private JLabel lblPlacInFamily;

	/**
	 * Create the frame.
	 */
	public AddKidd() {
		super("Add Kid",false,true,false,true);
		setBounds(100, 100, 642, 586);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		intPanels();
		initLabels();
		initTextFields();
		initRadiobtn();
		initBtns();
	
	}
	
	public void setClostBtnListener(ActionListener listener){
		btnClos.addActionListener(listener);
	}
	
	public void addRegisterListener(ActionListener listener){
		button.addActionListener(listener);
	}
	
	public void addComboBoxListenerK(ActionListener listener){
		comboBox.addActionListener(listener);
	}
	
	public void addComboBoxListenerC(ActionListener listener){
		txtCLassNum.addActionListener(listener);
	}
	
	public void addRadioButtonPubListener(ActionListener listener){
		rdbtnPublic.addActionListener(listener);
	}
	
	public void addRadioButtonPrivListener(ActionListener listener){
		rdbtnPrivate.addActionListener(listener);
		
	}
	public void addViewKindersListener(ActionListener listener){
		btnViewKinders.addActionListener(listener);
		
	}
	
	public JButton getBtnClos() {
		return btnClos;
	}

	public void setBtnClos(JButton btnClos) {
		this.btnClos = btnClos;
	}

	public JTextField getPlaceInF() {
		return PlaceInF;
	}

	public void setPlaceInF(JTextField placeInF) {
		PlaceInF = placeInF;
	}

	public JButton getBtnViewKinders() {
		return btnViewKinders;
	}

	public void setBtnViewKinders(JButton btnViewKinders) {
		this.btnViewKinders = btnViewKinders;
	}

	public JComboBox getTxtCLassNum() {
		return txtCLassNum;
	}

	public JTextField getTxtKinNum() {
		return comboBox;
	}

	public JTextField getTxtID() {
		return txtID;
	}

	public JTextField getTxtFname() {
		return txtFname;
	}

	public JTextField getTxtLname() {
		return txtLname;
	}

	public JTextField getTxtBDate() {
		return txtBDate;
	}

	public JTextField getTxtHouseNum() {
		return txtHouseNum;
	}

	public JTextField getTxtCity() {
		return txtCity;
	}

	public JTextField getTxtStreet() {
		return txtStreet;
	}

	public JTextField getTxtLat() {
		return txtLat;
	}

	public JTextField getTxtLong() {
		return txtLong;
	}

	public JTextField getTxtfathName() {
		return txtfathName;
	}

	public JTextField getTxtMothname() {
		return txtMothname;
	}

	public JRadioButton getRdbtnPublic() {
		return rdbtnPublic;
	}

	public JRadioButton getRdbtnPrivate() {
		return rdbtnPrivate;
	}
	
	public void setTxtCLassNum(JComboBox txtCLassNum) {
		this.txtCLassNum = txtCLassNum;
	}

	public void setTxtKinNum(JTextField txtKinNum) {
		this.comboBox = txtKinNum;
	}

	public void setTxtID(JTextField txtID) {
		this.txtID = txtID;
	}

	public void setTxtFname(JTextField txtFname) {
		this.txtFname = txtFname;
	}

	public void setTxtLname(JTextField txtLname) {
		this.txtLname = txtLname;
	}

	public void setTxtBDate(JTextField txtBDate) {
		this.txtBDate = txtBDate;
	}

	public void setTxtHouseNum(JTextField txtHouseNum) {
		this.txtHouseNum = txtHouseNum;
	}

	public void setTxtCity(JTextField txtCity) {
		this.txtCity = txtCity;
	}

	public void setTxtStreet(JTextField txtStreet) {
		this.txtStreet = txtStreet;
	}

	public void setTxtLat(JTextField txtLat) {
		this.txtLat = txtLat;
	}

	public void setTxtLong(JTextField txtLong) {
		this.txtLong = txtLong;
	}

	public void setTxtfathName(JTextField txtfathName) {
		this.txtfathName = txtfathName;
	}

	public void setTxtMothname(JTextField txtMothname) {
		this.txtMothname = txtMothname;
	}

	public void setRdbtnPublic(JRadioButton rdbtnPublic) {
		this.rdbtnPublic = rdbtnPublic;
	}

	public void setRdbtnPrivate(JRadioButton rdbtnPrivate) {
		this.rdbtnPrivate = rdbtnPrivate;
	}

	private void intPanels(){
		
		panel = new JPanel();
		panel.setBounds(10, 56, 380, 332);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 380, 34);
		getContentPane().add(panel_1);
		
		panel_2 = new JPanel();
		panel_2.setBounds(400, 56, 216, 222);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		panel_3 = new JPanel();
		panel_3.setBounds(10, 399, 380, 42);
		getContentPane().add(panel_3);
		panel_3.setLayout(null);

		panel_4 = new JPanel();
		panel_4.setBounds(10, 452, 380, 98);
		getContentPane().add(panel_4);
		panel_4.setLayout(null);

		
		panel_5 = new JPanel();
		panel_5.setBounds(400, 289, 222, 261);
		getContentPane().add(panel_5);
		
		
	}
	
	private void initLabels(){
		
		lblchoose = new JLabel("Choose a Kindergarten and a Class");
		lblchoose.setBounds(0, 0, 370, 40);
		panel_3.add(lblchoose);
		lblchoose.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		
		label_1 = new JLabel("Class Number");
		label_1.setBounds(10, 39, 102, 14);
		panel_4.add(label_1);
		
		label_2 = new JLabel("Kindergarten Number");
		label_2.setBounds(10, 14, 102, 14);
		panel_4.add(label_2);
		
		lblKidid = new JLabel("kidID");
		lblKidid.setBounds(10, 11, 56, 20);
		panel.add(lblKidid);
		
		fname = new JLabel("FirstName");
		fname.setBounds(10, 36, 90, 26);
		panel.add(fname);
		
		lbloptional = new JLabel("(Optional)");
		lbloptional.setBounds(290, 279, 64, 17);
		panel.add(lbloptional);
		
		label = new JLabel("(Optional)");
		label.setBounds(290, 304, 64, 17);
		panel.add(label);
		
		lblinfo = new JLabel("Please fill out your kid's information");
		lblinfo.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		panel_1.add(lblinfo);
		
		lblCity = new JLabel("City");
		lblCity.setBounds(10, 151, 46, 14);
		panel.add(lblCity);
		
		numHouse = new JLabel("House Number");
		numHouse.setBounds(10, 126, 77, 14);
		panel.add(numHouse);
		
		lname = new JLabel("LastName");
		lname.setBounds(10, 64, 77, 20);
		panel.add(lname);
			
		bDay = new JLabel("BirthDay");
		bDay.setBounds(10, 95, 56, 20);
		panel.add(bDay);
		
	    lblStreet = new JLabel("Street");
		lblStreet.setBounds(10, 176, 46, 14);
		panel.add(lblStreet);
		
		lblLat = new JLabel("Latitude");
		lblLat.setBounds(10, 201, 46, 14);
		panel.add(lblLat);
		
	    lblLongtitude = new JLabel("Longtitude");
		lblLongtitude.setBounds(10, 226, 56, 14);
		panel.add(lblLongtitude);

		fathName = new JLabel("Father Name");
		fathName.setBounds(10, 280, 77, 14);
		panel.add(fathName);
		
		lblPlacInFamily = new JLabel("Place in family");
		lblPlacInFamily.setBounds(10, 251, 90, 20);
		panel.add(lblPlacInFamily);

		lblMotherName = new JLabel("Mother Name");
		lblMotherName.setBounds(10, 305, 77, 14);
		panel.add(lblMotherName);
		panel_5.setLayout(null);
		
		JTextArea textArea = new JTextArea(
                "Please Note: You have to pick the kindergarten " +
                "number and class number from the " +
                "ones that can be viewed in the View tab.", 
                6, 
                20);
		textArea.setWrapStyleWord(true);
		textArea.setBounds(0, 84, 222, 136);
        textArea.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 16));
        textArea.setLineWrap(true);
        textArea.setOpaque(false);
        textArea.setEditable(false);
        panel_5.add(textArea);
        
	}
	private void initTextFields(){
		
		txtCLassNum = new JComboBox ();
		label_1.setLabelFor(txtCLassNum);
		txtCLassNum.setBounds(122, 36, 102, 20);
		panel_4.add(txtCLassNum);
		
		comboBox = new JTextField();
		comboBox.setEnabled(false);
		
		label_2.setLabelFor(comboBox);
		comboBox.setBounds(122, 11, 102, 20);
		panel_4.add(comboBox);
		
		txtID = new JTextField();
		lblKidid.setLabelFor(txtID);
		txtID.setBounds(97, 11, 108, 20);
		panel.add(txtID);
		txtID.setColumns(10);

		txtFname = new JTextField();
		fname.setLabelFor(txtFname);
		txtFname.setBounds(97, 39, 184, 20);
		panel.add(txtFname);
		txtFname.setColumns(10);
	   
		txtLname = new JTextField();
		lname.setLabelFor(txtLname);
		txtLname.setBounds(97, 64, 184, 20);
		panel.add(txtLname);
		txtLname.setColumns(10);

		txtBDate = new JTextField();
		bDay.setLabelFor(txtBDate);
		txtBDate.setToolTipText("YYYY-MM-DD");
		txtBDate.setBounds(97, 95, 184, 20);
		panel.add(txtBDate);
		txtBDate.setColumns(10);

		txtHouseNum = new JTextField();
		numHouse.setLabelFor(txtHouseNum);
		txtHouseNum.setBounds(97, 123, 184, 20);
		panel.add(txtHouseNum);
		txtHouseNum.setColumns(10);
		
		txtCity = new JTextField();
		lblCity.setLabelFor(txtCity);
		txtCity.setBounds(97, 148, 184, 20);
		panel.add(txtCity);
		txtCity.setColumns(10);
		
		txtStreet = new JTextField();
		lblStreet.setLabelFor(txtStreet);
		txtStreet.setBounds(96, 173, 184, 20);
		panel.add(txtStreet);
		txtStreet.setColumns(10);
		
		txtLat = new JTextField();
		lblLat.setLabelFor(txtLat);
		txtLat.setBounds(96, 198, 46, 20);
		panel.add(txtLat);
		txtLat.setColumns(10);
		
		txtLong = new JTextField();
		lblLongtitude.setLabelFor(txtLong);
		txtLong.setBounds(96, 223, 46, 20);
		panel.add(txtLong);
		txtLong.setColumns(10);
		
		txtfathName = new JTextField();
		fathName.setLabelFor(txtfathName);
		txtfathName.setBounds(96, 277, 184, 20);
		panel.add(txtfathName);
		txtfathName.setColumns(10);
		
		txtMothname = new JTextField();
		lblMotherName.setLabelFor(txtMothname);
		txtMothname.setBounds(96, 302, 184, 20);
		panel.add(txtMothname);
		txtMothname.setColumns(10);
		
		PlaceInF = new JTextField();
		lblPlacInFamily.setLabelFor(PlaceInF);
		PlaceInF.setBounds(96, 251, 46, 20);
		panel.add(PlaceInF);
		PlaceInF.setColumns(10);
	}
	
	private void initRadiobtn(){
		bg = new ButtonGroup();
		rdbtnPrivate = new JRadioButton("private");
		rdbtnPrivate.setBounds(10, 166, 109, 23);
		panel_2.add(rdbtnPrivate);
		
		rdbtnPublic = new JRadioButton("public");
		rdbtnPublic.setBounds(10, 192, 109, 23);
		panel_2.add(rdbtnPublic);
		
		bg.add(rdbtnPublic);
		bg.add(rdbtnPrivate);
		
		rdbtnPublic.setSelected(true);
	}
	
	private void initBtns(){
		button = new JButton("Register For Class");
		button.setBounds(10, 11, 196, 43);
		panel_2.add(button);
		
	    btnClos = new JButton("Closr Registration");
		btnClos.setBounds(10, 65, 196, 43);
		panel_2.add(btnClos);
		
	    btnViewKinders = new JButton("View Kindergartens");
	    btnViewKinders.setBounds(234, 14, 136, 42);
		panel_4.add(btnViewKinders);
	}
}
