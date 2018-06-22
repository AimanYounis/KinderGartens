package View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

public class LoginScreen extends JFrame {

	private JPanel contentPane;
	private static LoginScreen view;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JButton btnGuest;
	private JButton btnLogin;

	public static LoginScreen getInstance(){
		if(view == null)
			view = new LoginScreen();
		
		return view;
	}
	
	public void executeLoginView(){
		setVisible(true);
	}
	public void DisposeView(){
		setVisible(false);
	}
	/**
	 * Create the frame.
	 */
	private LoginScreen() {
		super("Login Screen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	initFrame();
	}
	
	private void initFrame(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 379);
		final Image background = Toolkit.getDefaultToolkit().createImage("login.png");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 255, 204));
		panel.setBounds(158, 40, 278, 273);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Script MT Bold", Font.PLAIN, 22));
		lblLogin.setBounds(109, 11, 71, 36);
		panel.add(lblLogin);
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setFont(new Font("Segoe Print", Font.PLAIN, 14));
		lblUsername.setBounds(24, 63, 97, 20);
		panel.add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(119, 61, 110, 20);
		panel.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setFont(new Font("Segoe Print", Font.PLAIN, 14));
		lblPassword.setBounds(24, 103, 97, 14);
		panel.add(lblPassword);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(119, 101, 110, 20);
		panel.add(txtPassword);
		txtPassword.setColumns(10);
		
		 btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Segoe Print", Font.PLAIN, 14));
		btnLogin.setBounds(91, 145, 89, 23);
		panel.add(btnLogin);
		
		btnGuest = new JButton("Guest");
		btnGuest.setFont(new Font("Segoe Print", Font.PLAIN, 14));
		btnGuest.setBounds(91, 179, 89, 23);
		panel.add(btnGuest);
	}
	public String getUserNameText(){
		return txtUsername.getText();
	}
	public String getPassword(){
		return txtPassword.getText();
	}
	public void setUsername(String s){
		this.txtUsername.setText(s);
	}
	public void setPassword(String s){
		this.txtPassword.setText(s);
	}
	
	public void AddLoginActionListener(ActionListener listener){
		btnLogin.addActionListener(listener);
	}
	public void AddGuestActionListener(ActionListener listener){
		btnGuest.addActionListener(listener);
	}


}
