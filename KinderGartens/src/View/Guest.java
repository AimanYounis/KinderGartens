package View;

import java.awt.CardLayout;

import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;

public class Guest extends JFrame {

	private JPanel contentPane;
	private static Guest view;
	private AddKidd addkid;
	private JButton btnRegisternew;
	private JDesktopPane panel;
	private KinTable kin;
	private JButton btnAlready;
	private JPanel panel_1;
	private JButton btnLogout;

	/**
	 * Launch the application.
	 */
	public static Guest getInstance(){
		if(view == null){
			view = new Guest();
		}
		return view;
	}
	public void SetLogoutActionListener(ActionListener listener){
		btnLogout.addActionListener(listener);
	}
	public void executeLoginView(){
		setVisible(true);
	}
	/**
	 * Create the frame.
	 */
	
	private Guest() {
		super("Guest Screen");
	init();
	initBG();
	}
	public void setBound(int x , int y , int z , int j){
		this.setBounds(x,y,z,j);
	}
	public void removeFirstPanel(){
		contentPane.remove(panel_1);
	}
	private void init(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
	}
	
	private void initBG(){
		
		panel = new JDesktopPane();
		panel.setBounds(10, 385, 522, -282);
		contentPane.add(panel, "name_343813383100539");
		panel.setLayout(new CardLayout(0, 0));
		
		panel_1 = new JPanel();
		panel.add(panel_1, "name_581497971549447");
		panel_1.setLayout(null);
		
		JLabel lblPleaseChooseAn = new JLabel("Please Choose an Option : ");
		lblPleaseChooseAn.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 16));
		panel_1.add(lblPleaseChooseAn);
		lblPleaseChooseAn.setBounds(178, 73, 231, 23);
		
		btnAlready = new JButton("Kid Already exists");
		panel_1.add(btnAlready);
		btnAlready.setBounds(113, 120, 145, 23);
		btnRegisternew = new JButton("Register New kid");
		panel_1.add(btnRegisternew);
		btnRegisternew.setBounds(309, 120, 130, 23);
		
		btnLogout = new JButton("Back");
		btnLogout.setBounds(475, 307, 89, 23);
		panel_1.add(btnLogout);
		
		
	}
	public void setViewKinderFrame(KinTable add) {
		kin = add;
		panel.add(this.kin);
	}
	public void setAddKiddFrame(AddKidd ak){
		this.addkid=ak;
		panel.add(this.addkid);
	}
	public AddKidd getAddKiddFrame(){
		return addkid;
	}
	public void SetIhaveActionListener(ActionListener listener){
		btnAlready.addActionListener(listener);
	}
	public void SetIWantActionListener(ActionListener listener){
		btnRegisternew.addActionListener(listener);
	}
	
}
