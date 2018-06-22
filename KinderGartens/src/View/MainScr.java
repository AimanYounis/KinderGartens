package View;


import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JDesktopPane;


public class MainScr extends JFrame {

	private static MainScr view;
	private AddKidd addKiddFrame; 
	private KinTable kt; 
	private AllActivities allActs; 
	private ReportTable RT;
	private KidsPctAvgInActivities kpa; 
	private ActivityOrganizor ao;
	private ScheduleView sv; 
	private AddActivityToClass aac,rac; 
	private AddOpinion op; 
	private RemoveKid remKid;
	private ActsAvgKids ActsPct;
	private ViewActivsInClass vac;
	private ActivityTable AT, SignedFor, 
				ATforRemoval, AActsToReArrange; 
	private ShowKids sk; 
	private ShowKidsInClass skc; 
	private AddKidToActivity AddKidToActivity; 
	private JMenu mnNewMenu, mnNewMenu_1, mnStatistics, mnNewMenu_2, mnRemoveFromDb;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenuItem exit, mntmLogout, mntmKidToKindergarten,
					mntmActivities, mntmKidToActivity, mntmImportTrainingFile, 
					mntmPrivateKindergartens, mntmImportAssistantsTraining,mntmAddOpin, mntmKid,
					mntmRemoveOpinion, mntmAllKids ,mntmKidsPerc, mntmOpinions, mntmKidsActivities_1,
					mntmKidsActivities, mntmKidsInClass,
					mntmKidsInAcitvity 
					, mntmManegeActivities; 
	private JDesktopPane panel;
	private JMenu mnReports;
	private JMenuItem mntmReporttwo;
	private JMenuItem mntmReportthree;
	private JMenuItem mntmReportfour;
	private JMenuItem mntmReportfive;
	private JMenuItem mntmReprotsix;
	private JMenuItem mntmReportseven;
	private JMenuItem mntmReporteight;
	JMenuItem mntmNumberOfKids;
	private JMenuItem mntmReporteleven;
	 

	/**
	 * Create the frame.
	 */
	private MainScr() {
		super("Rami-Pizzaria");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 925, 617); 
		setResizable(true);
		initPanel();
		initMenu();
	}
	
	/**
	 * displays the view
	 */
	public void executeLoginView(){
		setVisible(true);
	}
	
	public static MainScr getInstance(){
		if(view == null)
			view = new MainScr();
		
		return view;
	}
	public void setViewAssistantsInClassFrame(ViewAssistantsInClass vac2) { // new
		panel.add(vac2);
	}

	public void setViewAssistantsInClassListener(ActionListener setViewAssistantsInClass) {
		mntmReporteleven.addActionListener(setViewAssistantsInClass);
	}


	public void setNumKinderGartensListener(ActionListener setNumKinderGartens) { // new
			mntmNumberOfKids.addActionListener(setNumKinderGartens);
			
		}

	public void setNOK(NumKinderGartens nok) { // new
			panel.add(nok);
			
		}
	public void SetReportEightAction(ActionListener listener){
		mntmReporteight.addActionListener(listener);
	}
	public void SetReportSevenAction(ActionListener listener){
		mntmReportseven.addActionListener(listener);
	}
	public void SetReportSixAction(ActionListener listener){
		mntmReprotsix.addActionListener(listener);
	}
	public void SetReportFiveAction(ActionListener listener){
		mntmReportfive.addActionListener(listener);
	}
	public void SetReportFourAction(ActionListener listener){
		mntmReportfour.addActionListener(listener);
	}
	public void SetReportTwoAction(ActionListener listener){
		mntmReporttwo.addActionListener(listener);
	}
	public void setReportThreeAction(ActionListener listener){
		mntmReportthree.addActionListener(listener);
	}
	public void SetReportTable(ReportTable RT){
		this.RT=RT;
		panel.add(RT);
	}
	public void viewTakesPlaceForClass(ActionListener listener){ 
		mntmManegeActivities.addActionListener(listener);
	}
	
	public void viewPctKidsInActsPct(ActionListener listener){ 
		mntmKidsInAcitvity.addActionListener(listener);
	}
	
	public void viewPctKidsInActivities(ActionListener listener){
		mntmKidsPerc.addActionListener(listener);
	}
	
	public void viewOpinios(ActionListener listener){
		mntmOpinions.addActionListener(listener);
	}
	
	public void viewkidsActivities(ActionListener listener){
		mntmKidsActivities_1.addActionListener(listener);
	}
	
	public void removekidsActivities(ActionListener listener){
		mntmKidsActivities.addActionListener(listener);
	}
	
	public void showKidsInClass(ActionListener listener){
		mntmKidsInClass.addActionListener(listener);
	}
	
	public void showAllKids(ActionListener listener){
		mntmAllKids.addActionListener(listener);
	}
	
	public void setImportListener(ActionListener listener){
		mntmImportTrainingFile.addActionListener(listener);
	}
	
	public void setLogOutListener(ActionListener listener){
		mntmLogout.addActionListener(listener);
	}
	
	public void setRegisterKidListener(ActionListener listener){
		mntmKidToKindergarten.addActionListener(listener);
	}
	
	public void showActivitiesListener(ActionListener listener){
		mntmActivities.addActionListener(listener);
	}
	
	public void KidToActivitiesistener(ActionListener listener){
		mntmKidToActivity.addActionListener(listener);
	}
	
	public void viewKindergartensListener(ActionListener listener){
		mntmPrivateKindergartens.addActionListener(listener);
	}
	
	public void setImportTOFListener(ActionListener listener){
		mntmImportAssistantsTraining.addActionListener(listener);
	}
	
	public void menuListener(ActionListener windowClosed){
		exit.addActionListener(windowClosed);
	}
	
	public void addOpinionListener(ActionListener listener){
		mntmAddOpin.addActionListener(listener);
	}
	
	public void removeOpinionListener(ActionListener windowClosed){
		mntmRemoveOpinion.addActionListener(windowClosed);
	}
	
	public void removeKidListener(ActionListener listener){
		mntmKid.addActionListener(listener);
	}
	
	public void setScheduleView(ScheduleView sv){
		this.sv = sv;
		panel.add(this.sv);
	}
	
	public void setAddActivityToClass(AddActivityToClass aac){ 
		this.aac = aac;
		panel.add(this.aac);
	}
	
	public void setReAddActivityToClass(AddActivityToClass aac){ 
		this.rac = aac;
		panel.add(this.rac);
	}
	
	public void setAllActivities(AllActivities ATs){ 
		this.allActs = ATs;
		panel.add(this.allActs);
	}
	
	public void setAActsToReArrange(ActivityTable ATs){ 
		this.AActsToReArrange = ATs;
		panel.add(this.AActsToReArrange);
	}
	
	public void setATforRemoval(ActivityTable ATforRemoval){
		this.ATforRemoval = ATforRemoval;
		panel.add(this.ATforRemoval);
	}
	
	public void setPctKidsInActsPct(KidsPctAvgInActivities kpa) { 
		this.kpa = kpa;
		panel.add(this.kpa);
		
	}
	
	public void setPctKids(ActsAvgKids ActsPct) { 
		this.ActsPct = ActsPct;
		panel.add(this.ActsPct);
		
	}
	
	public void SetSignedForTable(ActivityTable Signedfor){ 
		this.SignedFor=Signedfor;
		panel.add(this.SignedFor);
	}
	
	public void voidsetVAC(ViewActivsInClass vac){
		this.vac = vac;
		panel.add(this.vac);
	}
	
	public AddKidd getAddKiddFrame() {
		return addKiddFrame;
	}
	
	public void setShowAllKidsInClass(ShowKidsInClass skv) {
		this.skc = skv;
		panel.add(this.skc);
		
	}
	
	public void setShowKids(ShowKids sk){
		this.sk = sk;
		panel.add(this.sk);
	}
	
	public void setOp(AddOpinion op) {
		this.op = op;
		panel.add(this.op);
	}
	
	public void setRemKid(RemoveKid remKid){
		this.remKid = remKid;
		panel.add(this.remKid);
	}
	
	public void setAddKiddFrame(AddKidd add) {
		addKiddFrame = add;
		panel.add(this.addKiddFrame);
	}
	
	public void setViewKinderFrame(KinTable add) {
		kt = add;
		panel.add(this.kt);
	}
	
	public void setAddKidToActivityFrame(AddKidToActivity ad){
		this.AddKidToActivity=ad;
		panel.add(this.AddKidToActivity);
	}
	
	public AddKidToActivity getAddKidToActivityFrame(){
		return AddKidToActivity;
	}
	
	public void setActivityTableFrame(ActivityTable AT){
		this.AT=AT;
		panel.add(this.AT);
	}
	
	public void setTakesPlaceForClass(ActivityOrganizor ao2) {
		this.ao=ao2;
		panel.add(this.ao);
	}
	
	public void AddActionListenerToAddKidToActivity(ActionListener listener){
		AddKidToActivity.SetButtonListener(listener);
	}
	
	public void setAddKidFrameListeners(ActionListener regList,ActionListener rdPubList, 
			ActionListener rdPrivList, ActionListener vKinders, ActionListener close){
		addKiddFrame.addRegisterListener(regList);
		addKiddFrame.addRadioButtonPubListener(rdPubList);
		addKiddFrame.addRadioButtonPrivListener(rdPrivList);
		addKiddFrame.addViewKindersListener(vKinders);
		addKiddFrame.setClostBtnListener(close);
	}
	
	private void initPanel(){
		
		contentPane = new JPanel();
		contentPane.setLayout(new CardLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JDesktopPane();
		contentPane.add(panel, "name_343813383100539");
			
	}
	/**
	 * initializes the menue
	 */
	private void initMenu(){
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
	    
	    mntmImportTrainingFile = new JMenuItem("Import Training File");
	    
	    mnNewMenu.add(mntmImportTrainingFile);
	    
	    mntmImportAssistantsTraining = new JMenuItem("Import Assistants Training File");
	    mnNewMenu.add(mntmImportAssistantsTraining);
		
	    mntmLogout = new JMenuItem("LogOut");
		mnNewMenu.add(mntmLogout);
		
		exit = new JMenuItem("Exit");
		mnNewMenu.add(exit);
		
		mnNewMenu_1 = new JMenu("View");
		menuBar.add(mnNewMenu_1);
		
		mntmPrivateKindergartens = new JMenuItem("Private KinderGartens");
		mnNewMenu_1.add(mntmPrivateKindergartens);
		
		 mntmAllKids = new JMenuItem("All Kids");
		mnNewMenu_1.add(mntmAllKids);
		
		mntmKidsInClass =  new JMenuItem("Kids In Class");
		mnNewMenu_1.add(mntmKidsInClass);
		
		mntmActivities = new JMenuItem("Activities in Class");
		mnNewMenu_1.add(mntmActivities);
		
		mntmKidsActivities_1 = new JMenuItem("Kid's Activities");
		mnNewMenu_1.add(mntmKidsActivities_1);
		
		mntmOpinions = new JMenuItem("Opinions");
		mnNewMenu_1.add(mntmOpinions);
		
		mnNewMenu_2 = new JMenu("Register");
		menuBar.add(mnNewMenu_2);
		
		mntmKidToKindergarten = new JMenuItem("Kid to Kindergarten");
		mnNewMenu_2.add(mntmKidToKindergarten);
		
		mntmKidToActivity = new JMenuItem("Kid to Activity");
		mnNewMenu_2.add(mntmKidToActivity);
		
		mntmAddOpin = new JMenuItem("Opinion");
		mnNewMenu_2.add(mntmAddOpin);
		
	    mnRemoveFromDb = new JMenu("Remove From DB");
		menuBar.add(mnRemoveFromDb);
		
		mntmKid = new JMenuItem("Kid");
		mnRemoveFromDb.add(mntmKid);
		
		mntmKidsActivities = new JMenuItem("Kid's Activities");
		mnRemoveFromDb.add(mntmKidsActivities);
		
		mntmRemoveOpinion = new JMenuItem("Opinion");
		mnRemoveFromDb.add(mntmRemoveOpinion);
		
		mnStatistics = new JMenu("DashBoard");
		menuBar.add(mnStatistics);
		
		 mntmKidsPerc = new JMenuItem("Kids Percentage in Activities");
		 mnStatistics.add(mntmKidsPerc);
		 
		 mntmKidsInAcitvity = new JMenuItem("Kids In Acitvity In Class Pct");
		 mnStatistics.add(mntmKidsInAcitvity);
		 
		  mntmManegeActivities = new JMenuItem("Manage Activities");
		 mnStatistics.add(mntmManegeActivities);
		 
		 mnReports = new JMenu("Reports");
		 mnStatistics.add(mnReports);
		 
		 mntmReporttwo = new JMenuItem("ReportTwo");
		 mnReports.add(mntmReporttwo);
		 
		 mntmReportthree = new JMenuItem("ReportThree");
		 mnReports.add(mntmReportthree);
		 
		 mntmReportfour = new JMenuItem("ReportFour");
		 mnReports.add(mntmReportfour);
		 
		 mntmReportfive = new JMenuItem("ReportFive");
		 mnReports.add(mntmReportfive);
		 
		 mntmReprotsix = new JMenuItem("ReprotSix");
		 mnReports.add(mntmReprotsix);
		 
		 mntmReportseven = new JMenuItem("ReportSeven");
		 mnReports.add(mntmReportseven);
		 
		 mntmReporteight = new JMenuItem("ReportEight");
		 mnReports.add(mntmReporteight);
		 
		 mntmReporteleven = new JMenuItem("ReportEleven");
		 mnReports.add(mntmReporteleven);
		 
			mntmNumberOfKids = new JMenuItem("Number of Kids In Kindergartens");
			  mnStatistics.add(mntmNumberOfKids);

	}

	
}
