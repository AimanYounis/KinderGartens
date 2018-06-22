package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileReader;
import java.sql.Time;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;

import Control.Controller;
import Model.Kid;
import Model.Model;
import Model.Opinion;
import Model.TakesPlace;
import Utills.Check;
import Utills.Constants;
import View.ActivityOrganizor;
import View.ActivityTable;
import View.ActsAvgKids;
import View.AddActivityToClass;
import View.AddKidd;
import View.AddOpinion;
import View.AllActivities;
import View.Guest;
import View.GuestIn;
import View.KidsPctAvgInActivities;
import View.KinTable;
import View.LoginScreen;
import View.MainScr;
import View.NumKinderGartens;
import View.RemoveKid;
import View.ReportTable;
import View.ScheduleView;
import View.ShowKids;
import View.ShowKidsInClass;
import View.ViewActivsInClass;
import View.ViewAssistantsInClass;
import au.com.bytecode.opencsv.CSVReader;

public class Controller {

	private static Controller cont;
	private Model model;
	private MainScr view;
	private	LoginScreen LogSc;
	private GuestIn guestIn;
	private Guest guest;


	private Controller() {
		try {
			model = Model.getInstance();
			LoginLaunchView();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR : something went wrong and couldn't load program.", null,
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static Controller getInstance() {
		if (cont == null)
			cont = new Controller();

		return cont;
	}
	
	public void LoginLaunchView(){
		LogSc = LoginScreen.getInstance();
		LogSc.AddLoginActionListener(LoginToMainScreen());
		LogSc.AddGuestActionListener(LoginToGuestScreen());
		LogSc.executeLoginView();
	}
	
	public void GuestInView(){
		guestIn = GuestIn.getInstance();
		guestIn.SetValidateActionListener(CheckValidation());
		guestIn.setButtonBackListener(Logout(null,null,guestIn));
		guestIn.SetButtonLogoutActionListener(Logout(null,null,guestIn));
		guestIn.SetViewOpListener(ViewOpions(null,guestIn)); // view opinions
		guestIn.executeView();
	}
	public void GuestView(){
		guest = Guest.getInstance();
		guest.SetLogoutActionListener(Logout(guest,null,null));
		guest.SetIWantActionListener(addKidToClass(guest,null));
		guest.SetIhaveActionListener(RadioButtonIalready());
		guest.executeLoginView();
		
	}
	
	public void LuanchView() {

		view = MainScr.getInstance();
		view.menuListener(new WindowClosed()); // setting the exit menu button
		view.setImportListener(addTrainingToDatabase()); // import Training File
		view.setImportTOFListener(addTOFToDatabase()); // import TrainingForAssistant File
		view.KidToActivitiesistener(AddKidToActivity(true)); // add kid to activity
		view.addWindowListener(new WindowClosed());// adding window listener for  exis click
		view.setRegisterKidListener(addKidToClass(null,view)); // Add Kid to kindergarten 
		view.addOpinionListener(addOpinion(true,null,view)); // add Opinon
		view.removeOpinionListener(addOpinion(false,null,view)); // remove Opinon
		view.viewKindergartensListener(viewKinderGartens(null,null,view)); // view private ikndergartens
		view.showActivitiesListener(viewAllActsInClass()); // view activities in calsses
		view.removeKidListener(removeKid()); // remove Kid from database
		view.showAllKids(ViewKids(false)); // ahow all kids
		view.showKidsInClass(ViewKids(true)); // ahow all kids in class
		view.removekidsActivities(AddKidToActivity(false)); // remove kid's activities
		view.viewkidsActivities(ViewKidsActivities()); // view kid's activities
		view.viewOpinios(ViewOpions(view,null)); // view opinions
		view.viewPctKidsInActivities(showPctKidsInActivities()); // Activities register Pct 
		view.viewPctKidsInActsPct(showPctKidsInActsPct()); 
		view.viewTakesPlaceForClass(showTakesPlaceForClass()); 
		view.setLogOutListener(Logout(null,view,null));
		view.executeLoginView();
		view.SetReportTwoAction(SecondReport());
		view.setReportThreeAction(ThirdReport());
		view.SetReportFourAction(FourthReport());
		view.SetReportFiveAction(FiveReport());
		view.SetReportSixAction(SixthReport());
		view.SetReportSevenAction(SeventhReport());
		view.SetReportEightAction(EightReport());
		view.setNumKinderGartensListener(setNumKinderGartens());
		view.setViewAssistantsInClassListener(setViewAssistantsInClass()); 
	}

private ActionListener setViewAssistantsInClass(){
		return new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				final ViewAssistantsInClass vac = new ViewAssistantsInClass();
				model.getKinNums(vac.getkID());
				view.setViewAssistantsInClassFrame(vac);
				
				vac.getkID().addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						int kID = ((Integer)vac.getkID().getSelectedItem()).intValue();
						model.getClassNums(vac.getcID(), kID);
						
					}
					
				});
				
				vac.btnView(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						
						int kID = ((Integer)vac.getkID().getSelectedItem()).intValue(),
							cID = ((Integer)vac.getcID().getSelectedItem()).intValue();
						Object[] columns = new Object[]{"AssistantID","Full Name","NumOfTrainig"};
						try {
							ReportTable rt = new ReportTable(model.getElevenReport(kID, cID), columns);
							view.SetReportTable(rt);
							rt.setVisible(true);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
				});
				vac.setVisible(true);
			}
			
		};
	}
	private ActionListener setNumKinderGartens(){ // new
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				NumKinderGartens nok = new NumKinderGartens(model.getNumKinderGartens());
				view.setNOK(nok);
				nok.setVisible(true);
			}
		};
	}
	private ActionListener EightReport(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ReportTable RT = null;
				Object[] columns = new Object[]{"ID","Full Name"};
				try {
					 RT = new ReportTable(model.getEightReport(),columns);
	
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				view.SetReportTable(RT);
				RT.setVisible(true);
				
				
			}
		};
	}
	private ActionListener SeventhReport(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ReportTable RT = null;
				Object[] columns = new Object[]{"KindergardenID","ActivityID","AverageGrade"};
				try {
					 RT = new ReportTable(model.getSevenReport(),columns);
	
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				view.SetReportTable(RT);
				RT.setVisible(true);
				
				
			}
		};
	}
	private ActionListener SixthReport(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ReportTable RT = null;
				Object[] columns = new Object[]{"ID","Full Name","Age","Name","KindergardenID","ClassNumber","DayInAweek","StartTime","EndTime"};
				try {
					 RT = new ReportTable(model.getSixReport(),columns);
	
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				view.SetReportTable(RT);
				RT.setVisible(true);
				
			}
		};
	}
	private ActionListener FiveReport(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ReportTable RT = null;
				Object[] columns = new Object[]{"AssistantID"};
				try {
					 RT = new ReportTable(model.getFiveReport(),columns);
	
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				view.SetReportTable(RT);
				RT.setVisible(true);
				
			}
		};
	}
	private ActionListener FourthReport(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ReportTable RT = null;
				Object[] columns = new Object[]{"KindergardenID","ID","Full Name"};
				try {
					 RT = new ReportTable(model.getFourReport(),columns);
	
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				view.SetReportTable(RT);
				RT.setVisible(true);
				
			}
		};
	}
	private ActionListener ThirdReport(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ReportTable RT = null;
				Object[] columns = new Object[]{"ID","Full Name","Age","Total Price"};
				try {
					 RT = new ReportTable(model.getThreeReport(),columns);
	
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				view.SetReportTable(RT);
				RT.setVisible(true);
				
			}
		};
	}
	private ActionListener SecondReport(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ReportTable RT = null;
				Object[] columns = new Object[]{"ID","Name","Length","Cost","Kindergarden Name","Class Name","Num of Kids"};
				try {
					 RT = new ReportTable(model.getSecondReport(),columns);
	
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				view.SetReportTable(RT);
				RT.setVisible(true);
				
			}
		};
	}
	private ActionListener Logout(final Guest g,final MainScr m, final GuestIn gi ){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(g != null){
					g.dispose();
					LoginLaunchView();
				}
				if(m != null){
					m.dispose();
					LoginLaunchView();
				}
				if(gi != null){
					gi.dispose();
					LoginLaunchView();
				}
			}
		};
	}
	
	private ActionListener CheckValidation(){
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					String KidId = guestIn.GetID();
					if(Check.isID(KidId)){
						if(model.getKidById(KidId) != null){
							guestIn.AddPanelDown();
							guestIn.RemoveFirstPanel();
							guestIn.SetRemoveKid(RemovingKid(KidId));
							guestIn.SetAddReviewListener(addOpinion(true, guestIn, null));
							guestIn.SetKidToActivityListener(OpenActivityTable(KidId));
						}else{
							JOptionPane.showMessageDialog(null, "Kid doesn't exists", null,
									JOptionPane.ERROR_MESSAGE);
						}
					}else{
						JOptionPane.showMessageDialog(null, "Wrong Format !", null,
								JOptionPane.ERROR_MESSAGE);
					}
				
			}
			
			private ActionListener RemovingKid(final String KidID){
				return new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							if(Check.isID(KidID)){
							
							final ActivityTable SF = new ActivityTable(model.getSignedFor(KidID));
							guestIn.setActivityTableFrame(SF);
							//guestIn.RemoveSecondPanel();
							SF.getTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
							SF.okClicked(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									try {
									int SelectedRow = SF.getTable().getSelectedRow();
									int ID = (int) SF.getTable().getValueAt(SelectedRow, 0);

									
										if (model.RemoveKidFromActivity(KidID, ID))
											JOptionPane.showMessageDialog(null,
													"Activity has been removed from kid activity", null,
													JOptionPane.INFORMATION_MESSAGE);
										else
											JOptionPane.showMessageDialog(null, "Didn't work", null,
													JOptionPane.ERROR_MESSAGE);
									} catch (Exception e1) {
										JOptionPane.showMessageDialog(null, e1.getMessage().toString(), null,
												JOptionPane.ERROR_MESSAGE);
									}
									SF.dispose();

								}
							});
							SF.setVisible(true);
							}else
								JOptionPane.showMessageDialog(null, "Wrong ID format", null, 
										JOptionPane.WARNING_MESSAGE);
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "No Activities were selected", null,
									JOptionPane.WARNING_MESSAGE);

						}
						
					}
				};
			}
			
			private ActionListener OpenActivityTable(final String KidID){
				return new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
								//guestIn.RemoveSecondPanel();
								final ActivityTable SF = new ActivityTable(model.getActivitesforKid(KidID,
										model.getKinderGardenIDForKid(KidID), model.getClassNumberForKid(KidID)));
								guestIn.setActivityTableFrame(SF);
								SF.getBtnNewButton().setText("Select");
								SF.getTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
								SF.okClicked(new ActionListener() {

									@Override
									public void actionPerformed(ActionEvent e) {

										try {
											int SelectedRow = SF.getTable().getSelectedRow();
											int ID = (int) SF.getTable().getValueAt(SelectedRow, 0);

											if (model.AddActivitytoKids(KidID, ID)) {
												JOptionPane.showMessageDialog(null, "Activity has been added to kid", null,
														JOptionPane.INFORMATION_MESSAGE);
											} else {
												JOptionPane.showMessageDialog(null, "Didn't work", null,
														JOptionPane.INFORMATION_MESSAGE);
											}
											SF.dispose();

										} catch (Exception exception) {
											JOptionPane.showMessageDialog(null, "No Activities were selected", null,
													JOptionPane.WARNING_MESSAGE);
											SF.dispose();
										}

									}
								});
								SF.setVisible(true);
							
							
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "No Activities were selected", null,
									JOptionPane.WARNING_MESSAGE);

						}
					}
				};
			}
			private ActionListener RemovingKid(){
				return new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
					
						
					}
				};
			}
		};
	}

	private ActionListener RadioButtonIalready(){
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			guest.dispose();
			GuestInView();
				
			}
		};
	}
	
	private ActionListener LoginToGuestScreen(){
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LogSc.dispose();
				GuestView();
				
			}
		};
	}
	
	
	private ActionListener LoginToMainScreen(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(LogSc.getUserNameText().equals(Constants.USERNAME) && LogSc.getPassword().equals(Constants.PASSWORD)){
					LogSc.setPassword("");
					LogSc.setUsername("");
					LuanchView();
					LogSc.dispose();
				}
				else if(LogSc.getUserNameText().equals(Constants.USERNAME) && !LogSc.getPassword().equals(Constants.PASSWORD)){
					JOptionPane.showMessageDialog(null, "Password is wrong !", null,
									JOptionPane.ERROR_MESSAGE);
				}
				else if(!LogSc.getUserNameText().equals(Constants.USERNAME) && LogSc.getPassword().equals(Constants.PASSWORD)){
					JOptionPane.showMessageDialog(null, "Username is wrong !", null,
									JOptionPane.ERROR_MESSAGE);
				}
				else if(!LogSc.getUserNameText().equals(Constants.USERNAME) && !LogSc.getPassword().equals(Constants.PASSWORD)){
					JOptionPane.showMessageDialog(null, "Password And Username is wrong !", null,
									JOptionPane.ERROR_MESSAGE);
				}
			}
		};
	}


	private ActionListener showTakesPlaceForClass() { 

		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				final ActivityOrganizor ao = new ActivityOrganizor();
				view.setTakesPlaceForClass(ao);
				model.getDaysOfWeek(ao.getDow());
				model.getKinNums(ao.getkID());

				ao.getkID().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						int kID = ((Integer) ao.getkID().getSelectedItem()).intValue();
						model.getClassNums(ao.getcID(), kID);
					}
				});
				ao.btnShowSchedualeForListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							int kID = ((Integer) ao.getkID().getSelectedItem()).intValue();
							int cID = ((Integer) ao.getcID().getSelectedItem()).intValue();
							String day = ((String) ao.getDow().getSelectedItem());
							int d = model.getDay(day);
							
							ScheduleView sv = new ScheduleView(model.getTakesPlaceForClass(kID, cID, d));
							view.setScheduleView(sv);

							sv.setVisible(true);
						} catch (Exception e2) {
							JOptionPane.showMessageDialog(null, e2.getMessage().toString(), null,
									JOptionPane.ERROR_MESSAGE);
						}

					}

				});
				ao.btnRemoveListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {

						try {
							final int kID = ((Integer) ao.getkID().getSelectedItem()).intValue();
							final int cID = ((Integer) ao.getcID().getSelectedItem()).intValue();

							final ActivityTable at = new ActivityTable(model.activitiesInClass(kID, cID));
							view.setATforRemoval(at);
							at.getTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
							at.okClicked(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent arg0) {
									int aID = (int) at.getTable().getValueAt(at.getTable().getSelectedRow(), 0);

									if (model.removeActivityFromClass(kID, cID, aID)) {
										JOptionPane.showMessageDialog(null, "Removal was successful", null,
												JOptionPane.INFORMATION_MESSAGE);
									} else
										JOptionPane.showMessageDialog(null, "Removal was unsuccessful", null,
												JOptionPane.WARNING_MESSAGE);
								}

							});
							at.setVisible(true);

						} catch (Exception e2) {
							JOptionPane.showMessageDialog(null, e2.getMessage().toString(), null,
									JOptionPane.ERROR_MESSAGE);
						}

					}
				});
				ao.btnAddActivityToListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						try {
							final int kID = ((Integer) ao.getkID().getSelectedItem()).intValue();
							final int cID = ((Integer) ao.getcID().getSelectedItem()).intValue();

							final AddActivityToClass ac = new AddActivityToClass();

							model.getDaysOfWeek(ac.getDow());
							model.getOperatorsIDs(ac.getOpID());
							view.setAddActivityToClass(ac);

							ac.btnSelectListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent arg0) {
									final AllActivities at = new AllActivities(model.getAllActivities());
									view.setAllActivities(at);
									at.getTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
									at.okClicked(new ActionListener() {

										@Override
										public void actionPerformed(ActionEvent arg0) {
											try {
												int aID = (int) at.getTable().getValueAt(at.getTable().getSelectedRow(),
														0);

												ac.getaID().setText(String.valueOf(aID));
												at.dispose();
											} catch (Exception e) {
												JOptionPane.showMessageDialog(null, e.getMessage().toString(), null,
														JOptionPane.ERROR_MESSAGE);
											}
										}

									});
									at.setVisible(true);
								}
							});
							ac.btnAddActivityListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent arg0) {

									try {
										String opID = (String) ac.getOpID().getSelectedItem(),
												day = (String) ac.getDow().getSelectedItem(),
												time = ac.getTime().getText();
										int d = model.getDay(day);

										if (!time.isEmpty() && !ac.getaID().getText().isEmpty() && Check.isTime(time)) {
											int aID = Integer.parseInt(ac.getaID().getText());

											if (model.addActivityToClass(kID, cID, aID, d, time, opID,true))
												JOptionPane.showMessageDialog(null, "Addition was successful", null,
														JOptionPane.INFORMATION_MESSAGE);
											else
												JOptionPane.showMessageDialog(null, "Addition was unsuccessful", null,
														JOptionPane.WARNING_MESSAGE);
											ac.dispose();
										} else
											JOptionPane.showMessageDialog(null, "Missing fields or invalid input", null,
													JOptionPane.ERROR_MESSAGE);

									} catch (Exception e) {
										JOptionPane.showMessageDialog(null, e.getMessage().toString(), null,
												JOptionPane.ERROR_MESSAGE);
									}
								}
							});
							ac.setVisible(true);
						} catch (Exception e2) {
							JOptionPane.showMessageDialog(null, e2.getMessage().toString(), null,
									JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				ao.addReArrangeListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						try{
							final int kID = ((Integer) ao.getkID().getSelectedItem()).intValue();
							final int cID = ((Integer) ao.getcID().getSelectedItem()).intValue();
							String day = ((String) ao.getDow().getSelectedItem());
							final int d = model.getDay(day);
							
							final AddActivityToClass ac = new AddActivityToClass();
							
							model.getDaysOfWeek(ac.getDow());
							model.getOperatorsIDs(ac.getOpID());
							view.setReAddActivityToClass(ac);
							ac.getBtnAdd().setText("Update");
							
							ac.btnSelectListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent arg0) {
									final ActivityTable at = new ActivityTable(model.getActivitiesInClassInDay(kID,cID,d));
									view.setAActsToReArrange(at);
									at.getTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
									at.okClicked(new ActionListener() {

										@Override
										public void actionPerformed(ActionEvent arg0) {
											try {
												int aID = (int) at.getTable().getValueAt(at.getTable().getSelectedRow(),
														0);
												String opID = (String)at.getTable().getValueAt(at.getTable().getSelectedRow(),
														4),
														t = ((Time)at.getTable().getValueAt(at.getTable().getSelectedRow(),
																7)).toString(),
														time = t.substring(0,t.length()-3);
												System.out.print(opID);
														
												ac.getOpID().setSelectedItem(opID);
												ac.getTime().setText(time);
												ac.getDow().setSelectedIndex((d-1));
												ac.getaID().setText(String.valueOf(aID));
												at.dispose();
											} catch (Exception e) {
												JOptionPane.showMessageDialog(null, e.getMessage().toString(), null,
														JOptionPane.ERROR_MESSAGE);
											}
										}

									});
									at.setVisible(true);
								}
							});
							ac.btnAddActivityListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent arg0) {

									try {
										String opID = (String) ac.getOpID().getSelectedItem(),
												day = (String) ac.getDow().getSelectedItem(),
												time = ac.getTime().getText();
										int d = model.getDay(day);

										if (!time.isEmpty() && !ac.getaID().getText().isEmpty() && Check.isTime(time)) {
											int aID = Integer.parseInt(ac.getaID().getText());

											if (model.addActivityToClass(kID, cID, aID, d, time, opID,false))
												JOptionPane.showMessageDialog(null, "Update was successful", null,
														JOptionPane.INFORMATION_MESSAGE);
											else
												JOptionPane.showMessageDialog(null, "Update was unsuccessful", null,
														JOptionPane.WARNING_MESSAGE);
											ac.dispose();
										} else
											JOptionPane.showMessageDialog(null, "Missing fields or invalid input", null,
													JOptionPane.ERROR_MESSAGE);

									} catch (Exception e) {
										JOptionPane.showMessageDialog(null, e.getMessage().toString(), null,
												JOptionPane.ERROR_MESSAGE);
									}
								}
							});
							ac.setVisible(true);
						}catch(Exception e2){
							JOptionPane.showMessageDialog(null, e2.getMessage().toString(), null,
									JOptionPane.ERROR_MESSAGE);
						}
						
					}
					
				});
				ao.setVisible(true);
			}
		};
	}

	private ActionListener showPctKidsInActsPct() { 
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				final KidsPctAvgInActivities kp = new KidsPctAvgInActivities();
				view.setPctKidsInActsPct(kp);
				model.getKinNums(kp.getBox());

				kp.getBox().addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						int kid = ((Integer) kp.getBox().getSelectedItem()).intValue();
						model.getClassNums(kp.getcNum(), kid);

					}
				});
				kp.addBtnSelectListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						int kid = kid = ((Integer) kp.getBox().getSelectedItem()).intValue(),
								cid = ((Integer) kp.getcNum().getSelectedItem()).intValue();

						kp.initView(model.getPctKidsInActsPct(kid, cid));
					}

				});
				kp.setVisible(true);
			}

		};

	}

	private ActionListener showPctKidsInActivities() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				final ActsAvgKids a = new ActsAvgKids();
				view.setPctKids(a);
				model.getKinNums(a.getBox());
				a.addBtnSelectListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						try {
							int kID = ((Integer) a.getBox().getSelectedItem()).intValue();
							a.initView(model.getPctKidsInActivities(kID));
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, e.getMessage().toString(), null,
									JOptionPane.ERROR_MESSAGE);
						}
					}

				});
				a.setVisible(true);
			}

		};
	}

	private ActionListener ViewKids(final boolean b) {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (!b) {
						ShowKids sk = new ShowKids(model.getAllKids());
						view.setShowKids(sk);
						sk.setVisible(true);
					} else {
						final ShowKidsInClass skv = new ShowKidsInClass();
						view.setShowAllKidsInClass(skv);
						model.getKinNums(skv.getkID()); // init combobox
						skv.comboboxKListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) { // everytime clicked init class cmmbobox again
								int kID = ((Integer) skv.getkID().getSelectedItem()).intValue();
								model.getClassNums(skv.getcID(), kID);
							}

						});
						skv.okClicked(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								try {
									int kID = ((Integer) skv.getkID().getSelectedItem()).intValue();
									int cID = ((Integer) skv.getcID().getSelectedItem()).intValue();

									skv.initTable(model.getAllKidsInClass(kID, cID));

								} catch (Exception e2) {
									JOptionPane.showMessageDialog(null, e2.getMessage().toString(), null,
											JOptionPane.ERROR_MESSAGE);
								}
							}

						});
						skv.setVisible(true);
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage().toString(), null, JOptionPane.ERROR_MESSAGE);
				}

			}
		};
	}

	private ActionListener ViewKidsActivities() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				View.AddKidToActivity act = new View.AddKidToActivity();
				view.setAddKidToActivityFrame(act);
				act.setTitle("View Kid's Activity");
				view.AddActionListenerToAddKidToActivity(OpenTable(act));
				act.setVisible(true);

			}

			private ActionListener OpenTable(final View.AddKidToActivity act) {
				return new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							String KidID = act.getIdField().getText();
							if (Check.isID(KidID)) {
								final ActivityTable SF = new ActivityTable(model.getSignedFor(KidID));
								view.SetSignedForTable(SF);
								SF.getBtnNewButton().setText("Close");
								SF.getTable().setColumnSelectionAllowed(false);
								SF.getTable().setRowSelectionAllowed(false);
								SF.okClicked(new ActionListener() {

									@Override
									public void actionPerformed(ActionEvent e) {

										SF.dispose();

									}
								});
								SF.setVisible(true);
							} else
								JOptionPane.showMessageDialog(null, "Wrong ID format", null,
										JOptionPane.WARNING_MESSAGE);

						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "No Activities available", null,
									JOptionPane.WARNING_MESSAGE);

						}
					}
				};
			}
		};
	}

	private ActionListener ViewOpions(final MainScr m, final GuestIn g) {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				final AddOpinion ad = new AddOpinion();
				if(m != null)
					view.setOp(ad);
				else if(g != null)
					g.setOp(ad);
				
				model.getKinNums(ad.getkID());
				model.getActnNums(ad.getaID());

				ad.getaID().setEnabled(false);
				ad.RdbtnActivityListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// ad.getaID().removeAllItems();
						ad.getkID().setEnabled(false);
						ad.getaID().setEnabled(true);
					}

				});
				ad.RdbtnKindergartenListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// ad.getaID().removeAllItems();
						ad.getaID().setEnabled(false);
						ad.getkID().setEnabled(true);
					}

				});

				ad.setTitle("View Opinion");
				ad.getDesc().setEditable(false);
				ad.getRating().setEnabled(false);
				ad.getBtnSubmit().setText("Close");
				ad.getaID().addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						int aID = ((Integer) ad.getaID().getSelectedItem()).intValue();
						model.getOpinsNums(ad.getoID(), -1, aID);
					}

				});
				ad.getkID().addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						int kID = ((Integer) ad.getkID().getSelectedItem()).intValue();
						model.getOpinsNums(ad.getoID(), kID, -1);
					}

				});
				ad.getoID().addItemListener(new ItemListener() {

					@Override
					public void itemStateChanged(ItemEvent e) {
						try {
							if (e.getStateChange() == ItemEvent.SELECTED) {
								int oID = ((Integer) ad.getoID().getSelectedItem()).intValue();
								Opinion op = model.getOpinionById(oID);

								ad.getDesc().setText(op.getTalk());
								ad.getRating().removeAllItems();
								ad.getRating().addItem(op.getGrade());
							}
						} catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "There are no opinions available", null,
									JOptionPane.INFORMATION_MESSAGE);
						}
					}
				});
				ad.submitListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						ad.dispose();
					}
				});
				ad.setVisible(true);
			}
		};
	}

	private ActionListener AddKidToActivity(final boolean b) {

		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				View.AddKidToActivity act = new View.AddKidToActivity();
				view.setAddKidToActivityFrame(act);
				if (b)
					view.AddActionListenerToAddKidToActivity(AddKidToActivityInsideListener(act));
				else {
					act.setTitle("Remove Kid From Activity");
					view.AddActionListenerToAddKidToActivity(RemoveKidFromActivity(act));
				}
				act.setVisible(true);

			}

			private ActionListener AddKidToActivityInsideListener(final View.AddKidToActivity act) {
				return new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						try {
							final String KidID = act.getIdField().getText();
							if (Check.isID(KidID)) {

								final ActivityTable AT = new ActivityTable(model.getActivitesforKid(KidID,
										model.getKinderGardenIDForKid(KidID), model.getClassNumberForKid(KidID)));
								view.setActivityTableFrame(AT);
								AT.getTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
								AT.okClicked(new ActionListener() {

									@Override
									public void actionPerformed(ActionEvent e) {

										try {
											int SelectedRow = AT.getTable().getSelectedRow();
											int ID = (int) AT.getTable().getValueAt(SelectedRow, 0);

											if (model.AddActivitytoKids(KidID, ID)) {
												JOptionPane.showMessageDialog(null, "Activity has been added to kid",
														null, JOptionPane.INFORMATION_MESSAGE);
											} else {
												JOptionPane.showMessageDialog(null, "Activity was not added to kid", null,
														JOptionPane.INFORMATION_MESSAGE);
											}
											AT.dispose();

										} catch (Exception exception) {
											JOptionPane.showMessageDialog(null, "No Activities were selected", null,
													JOptionPane.WARNING_MESSAGE);
											AT.dispose();
										}

									}
								});
								AT.setVisible(true);
							} else
								JOptionPane.showMessageDialog(null, "Wrong ID format", null,
										JOptionPane.WARNING_MESSAGE);
						} catch (Exception exception) {
							JOptionPane.showMessageDialog(null, exception.getMessage().toString(), null,
									JOptionPane.ERROR_MESSAGE);
						}
					}
				};
			}

			private ActionListener RemoveKidFromActivity(final View.AddKidToActivity act) {
				return new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							final String KidID = act.getIdField().getText();
							if (Check.isID(KidID)) {

								final ActivityTable SF = new ActivityTable(model.getSignedFor(KidID));
								view.setActivityTableFrame(SF);
								SF.getTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
								SF.okClicked(new ActionListener() {

									@Override
									public void actionPerformed(ActionEvent e) {
										int SelectedRow = SF.getTable().getSelectedRow();
										int ID = (int) SF.getTable().getValueAt(SelectedRow, 0);

										try {
											if (model.RemoveKidFromActivity(KidID, ID))
												JOptionPane.showMessageDialog(null,
														"Activity has been removed from kid's activites", null,
														JOptionPane.INFORMATION_MESSAGE);
											else
												JOptionPane.showMessageDialog(null, "Activity was not removed from kid's activites", null,
														JOptionPane.ERROR_MESSAGE);
										} catch (Exception e1) {
											JOptionPane.showMessageDialog(null, e1.getMessage().toString(), null,
													JOptionPane.ERROR_MESSAGE);
										}
										SF.dispose();

									}
								});
								SF.setVisible(true);
							} else
								JOptionPane.showMessageDialog(null, "Wrong ID format", null,
										JOptionPane.WARNING_MESSAGE);
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "No Activities were selected", null,
									JOptionPane.WARNING_MESSAGE);

						}
					}
				};
			}
		};
	}

	private ActionListener viewAllActsInClass() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				final ViewActivsInClass vac = new ViewActivsInClass();
				view.voidsetVAC(vac);
				model.getKinNums(vac.getkID()); // init combobox
				vac.comboboxKListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) { // everytime clicked init class cmmbobox again
						int kID = ((Integer) vac.getkID().getSelectedItem()).intValue();
						model.getClassNums(vac.getcID(), kID);
					}

				});
				vac.okClicked(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							int kID = ((Integer) vac.getkID().getSelectedItem()).intValue();
							int cID = ((Integer) vac.getcID().getSelectedItem()).intValue();

							vac.initTable(model.activitiesInClass(kID, cID));

						} catch (Exception e2) {
							JOptionPane.showMessageDialog(null, e2.getMessage().toString(), null,
									JOptionPane.ERROR_MESSAGE);
						}
					}

				});
				vac.setVisible(true);
			}
		};
	}

	private ActionListener addOpinion(final boolean b, final GuestIn g, final MainScr m) { // Need to change for single kindergarden and activties
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				final AddOpinion ad = new AddOpinion();
				

				for (int i = Constants.MIN_RATING; i <= Constants.MAX_RATING; i++)
					ad.getRating().addItem(i);
				
				if (g == null) {
					m.setOp(ad);
					model.getKinNums(ad.getkID());
					model.getActnNums(ad.getaID());

				}
				if (m == null) {
					g.SetAddOpinionFrame(ad);
					ad.getkID().addItem(((Kid)model.getKidById(g.GetID())).getcLass().getKindergarden().getID());
					for(TakesPlace  tp : ((Kid)model.getKidById(g.GetID())).getActivities()){
						ad.getaID().addItem(tp.getActivity().getID());
					}
					
				}
				
				ad.getaID().setEnabled(false);
				ad.RdbtnActivityListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						ad.getkID().setEnabled(false);
						ad.getaID().setEnabled(true);
					}

				});
				ad.RdbtnKindergartenListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						ad.getaID().setEnabled(false);
						ad.getkID().setEnabled(true);
					}

				});

				if (!b) {
					ad.setTitle("Remove Opinion");
					ad.getDesc().setEnabled(b);
					ad.getRating().setEnabled(b);
					ad.getBtnSubmit().setText("Delete");
					ad.getaID().addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							int aID = ((Integer) ad.getaID().getSelectedItem()).intValue();
							model.getOpinsNums(ad.getoID(), -1, aID);
						}

					});
					ad.getkID().addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							int kID = ((Integer) ad.getkID().getSelectedItem()).intValue();
							model.getOpinsNums(ad.getoID(), kID, -1);
						}

					});
					ad.getoID().addItemListener(new ItemListener() {

						@Override
						public void itemStateChanged(ItemEvent e) {
							try {
								if (e.getStateChange() == ItemEvent.SELECTED) {
									int oID = ((Integer) ad.getoID().getSelectedItem()).intValue();
									Opinion op = model.getOpinionById(oID);

									ad.getDesc().setText(op.getTalk());
									ad.getRating().removeAllItems();
									ad.getRating().addItem(op.getGrade());
								}
							} catch (Exception e2) {
								JOptionPane.showMessageDialog(null, "There are no opinions availible", null,
										JOptionPane.INFORMATION_MESSAGE);
							}
						}
					});
					ad.submitListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {

							if (ad.getRdbtnActivity().isSelected()) {

								try {
									int oID = ((Integer) ad.getoID().getSelectedItem()).intValue(),
											aID = ((Integer) ad.getaID().getSelectedItem()).intValue();
									if (!model.removeOpinion(oID, aID, -1))
										JOptionPane.showMessageDialog(null, "Removal was Unsuccessful", null,
												JOptionPane.WARNING_MESSAGE);
									else {
										ad.getoID().removeItem(oID);
										JOptionPane.showMessageDialog(null, "Removal was successful", null,
												JOptionPane.INFORMATION_MESSAGE);
									}

								} catch (Exception e) {
									JOptionPane.showMessageDialog(null, e.getMessage().toString(), null,
											JOptionPane.ERROR_MESSAGE);
								}

							} else if (ad.getRdbtnKindergarten().isSelected()) {

								try {
									int oID = ((Integer) ad.getoID().getSelectedItem()).intValue(),
											kID = ((Integer) ad.getkID().getSelectedItem()).intValue();
									if (!model.removeOpinion(oID, -1, kID))
										JOptionPane.showMessageDialog(null, "Removal was Unsuccessful", null,
												JOptionPane.WARNING_MESSAGE);
									else
										JOptionPane.showMessageDialog(null, "Removal was successful", null,
												JOptionPane.INFORMATION_MESSAGE);

								} catch (Exception e) {
									JOptionPane.showMessageDialog(null, e.getMessage().toString(), null,
											JOptionPane.ERROR_MESSAGE);
								}
							}
						}
					});
				} else {

					ad.getoID().setEnabled(false);
					ad.submitListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							int rating = ((Integer) ad.getRating().getSelectedItem()).intValue();

							if (ad.getRdbtnActivity().isSelected()) {
								int aID = ((Integer) ad.getaID().getSelectedItem()).intValue();
								if (!ad.getDesc().getText().isEmpty()) {
									try {

										if (!model.addOpinion(ad.getDesc().getText(), rating, -1, aID))
											JOptionPane.showMessageDialog(null, "Addition was Unsuccessful", null,
													JOptionPane.WARNING_MESSAGE);
										else
											JOptionPane.showMessageDialog(null, "Addition was successful", null,
													JOptionPane.INFORMATION_MESSAGE);

									} catch (Exception e) {
										JOptionPane.showMessageDialog(null, e.getMessage().toString(), null,
												JOptionPane.ERROR_MESSAGE);
									}
								} else
									JOptionPane.showMessageDialog(null, "Opinion description was not added", null,
											JOptionPane.WARNING_MESSAGE);

							} else if (ad.getRdbtnKindergarten().isSelected()) {
								int kID = ((Integer) ad.getkID().getSelectedItem()).intValue();
								if (!ad.getDesc().getText().isEmpty()) {
									try {

										if (!model.addOpinion(ad.getDesc().getText(), rating, kID, -1))
											JOptionPane.showMessageDialog(null, "Addition was Unsuccessful", null,
													JOptionPane.WARNING_MESSAGE);
										else
											JOptionPane.showMessageDialog(null, "Addition was successful", null,
													JOptionPane.INFORMATION_MESSAGE);

									} catch (Exception e) {
										JOptionPane.showMessageDialog(null, e.getMessage().toString(), null,
												JOptionPane.ERROR_MESSAGE);
									}
								} else
									JOptionPane.showMessageDialog(null, "Opinion description was not added", null,
											JOptionPane.WARNING_MESSAGE);
							}
						}
					});
				}
				ad.setVisible(true);
			}
		};
	}

	private ActionListener addTOFToDatabase() {

		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV FILES", "csv", "current sheet");
				fc.setFileFilter(filter);
				if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					CSVReader reader = null;
					try {
						reader = new CSVReader(new FileReader(fc.getSelectedFile()));
						String[] line;
						boolean fail = false;

						while ((line = reader.readNext()) != null) {
							if (Check.isID(line[0])) { // row add
								if (!model.AddTrainingToAssistant(line[0], Integer.parseInt(line[1]),
										line[2])) {
									fail = true;
								}
							} else
								fail = true; // row add
						}
						if (fail)
							JOptionPane.showMessageDialog(null, "not all items were loaded from file.", null,
									JOptionPane.WARNING_MESSAGE);
						else
							JOptionPane.showMessageDialog(null, "Import was successful.", null,
									JOptionPane.INFORMATION_MESSAGE);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Failed to load file properly.", null,
								JOptionPane.ERROR_MESSAGE);
					} finally {
						try {
							reader.close();
						} catch (IOException e1) {
						}
					}
				}
			}
		};
	}

	private ActionListener addTrainingToDatabase() {

		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV FILES", "csv", "current sheet");
				fc.setFileFilter(filter);
				if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					CSVReader reader = null;
					try {
						reader = new CSVReader(new FileReader(fc.getSelectedFile()));
						String[] line;
						boolean fail = false;
						while ((line = reader.readNext()) != null) {
							if (Check.isName(line[1])) { // row add
								if (!model.AddTrainingToData(Integer.parseInt(line[0]), line[1])) {
									fail = true;
								}
							} else
								fail = true; // row add
						}
						if (fail)
							JOptionPane.showMessageDialog(null, "not all items were loaded from file.", null,
									JOptionPane.WARNING_MESSAGE);
						else
							JOptionPane.showMessageDialog(null, "Import was successful.", null,
									JOptionPane.INFORMATION_MESSAGE);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Failed to load file properly.", null,
								JOptionPane.ERROR_MESSAGE);
					} finally {
						try {
							reader.close();
						} catch (IOException e1) {
						}
					}
				}
			}
		};
	}


	private ActionListener addKidToClass(final Guest g,final MainScr m) {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				AddKidd addkiddframe = new AddKidd();
				if(g == null){
					m.setAddKiddFrame(addkiddframe);
					addkiddframe.addViewKindersListener(viewKinderGartens(addkiddframe,null,m));
				}
				if(m == null){
					g.setAddKiddFrame(addkiddframe);
					g.setBound(100, 100, 700, 600);
					addkiddframe.addViewKindersListener(viewKinderGartens(addkiddframe,g,null));
					
				}
				
				addkiddframe.getBtnViewKinders().setEnabled(false);
				addkiddframe.getTxtCLassNum().setEnabled(false);
				addkiddframe.addRegisterListener(registerButtonListener(addkiddframe,g,null));
				addkiddframe.addRadioButtonPubListener(rdPubClicked(addkiddframe));
				addkiddframe.addRadioButtonPrivListener(rdPrivClicked(addkiddframe));
				addkiddframe.setClostBtnListener(closeBtnListener(addkiddframe));
				if(g== null){
					m.getAddKiddFrame().setVisible(true);
				}
				if(m == null){
					
					g.removeFirstPanel();
					addkiddframe.setVisible(true);
					
				}
			}

			private ActionListener closeBtnListener(final AddKidd addKiddFrame) {

				return new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						addKiddFrame.dispose();
					}
				};
			}

			private ActionListener rdPrivClicked(final AddKidd addKiddFrame) {
				return new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						addKiddFrame.getBtnViewKinders().setEnabled(true);
						addKiddFrame.getTxtCLassNum().setEnabled(true);
					}

				};
			}

			private ActionListener rdPubClicked(final AddKidd addKiddFrame) {

				return new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						addKiddFrame.getBtnViewKinders().setEnabled(false);
						addKiddFrame.getTxtCLassNum().setEnabled(false);
					}

				};
			}

			private ActionListener registerButtonListener(final AddKidd addKiddFrame,final Guest g , final MainScr m) {
				return new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						String txtID = addKiddFrame.getTxtID().getText(),
								txtFname = addKiddFrame.getTxtFname().getText(),
								txtLnane = addKiddFrame.getTxtLname().getText(),
								txtDate = addKiddFrame.getTxtBDate().getText(),
								txtStreet = addKiddFrame.getTxtStreet().getText(),
								txtCity = addKiddFrame.getTxtCity().getText(),
								txtHouseNum = addKiddFrame.getTxtHouseNum().getText(),
								txtFathName = addKiddFrame.getTxtfathName().getText(),
								txtMomName = addKiddFrame.getTxtMothname().getText(),
								txtLat = addKiddFrame.getTxtLat().getText(),
								txtLong = addKiddFrame.getTxtLong().getText(),
								txtPlaceinF = addKiddFrame.getPlaceInF().getText();

						if (model.getKidById(txtID) != null) {
							Kid k = (Kid) model.getKidById(txtID);
							
							if (txtFname.equals(""))
								txtFname = k.getFirstName();
							if (txtLnane.equals(""))
								txtLnane = k.getSurName();
							if (txtDate.equals("")) {
								DateFormat df = new SimpleDateFormat(Constants.DF);
								txtDate = df.format(k.getDateOfBirth());

							}
							if (txtStreet.equals(""))
								txtStreet = k.getStreet();
							if (txtCity.equals(""))
								txtCity = k.getCity();
							if (txtHouseNum.equals(""))
								txtHouseNum = k.getHouseNumber();
							if (txtFathName.equals(""))
								txtFathName = k.getFatherName();
							if (txtMomName.equals(""))
								txtMomName = k.getMotherName();
							if (txtLat.equals(""))
								txtLat = String.valueOf(k.getLatitude());
							if (txtLong.equals(""))
								txtLong = String.valueOf(k.getLongitude());
							if (txtPlaceinF.equals(""))
								txtPlaceinF = String.valueOf(k.getPlaceInFamily());
						}

						if (Check.isID(txtID) && Check.isName(txtFname) && Check.isName(txtLnane)
								&& Check.isName(txtStreet) && Check.isName(txtCity) && Check.isName2(txtFathName)
								&& Check.isName2(txtMomName) && txtHouseNum.length() <= Constants.HOUSE_NUMBER) {

							try {
								float lat = Float.parseFloat(txtLat), longt = Float.parseFloat(txtLong);
								int place = Integer.parseInt(txtPlaceinF);
								DateFormat df = new SimpleDateFormat(Constants.DF);
								Date dd = df.parse(txtDate);
								int reply = JOptionPane.YES_OPTION;

								if (model.getKidById(txtID) != null)
									reply = JOptionPane.showConfirmDialog(null,
											"Do you want to update Kid information ? \n Please note that you can't change class or kindergarten "
													+ "unless you remove your kid from dataebase",
											"Please Choose", JOptionPane.YES_NO_OPTION);

								if (reply == JOptionPane.YES_OPTION) {
									if (addKiddFrame.getRdbtnPublic().isSelected()) {
										if (!model.AddKidToClass(txtID, txtFname, txtLnane, dd, txtStreet, txtHouseNum,
												txtCity, lat, longt, txtFathName, txtMomName, place, -1, -1)) {

											JOptionPane.showMessageDialog(null,
													"Adding/Updating " + txtFname + " was not successful", null,
													JOptionPane.WARNING_MESSAGE);
										} else {
											JOptionPane.showMessageDialog(null,
													"Adding/Updating " + txtFname + " was successful", null,
													JOptionPane.INFORMATION_MESSAGE);
											if(g!=null){
												System.out.println("Fuck i am here !!2");
												addKiddFrame.dispose();
												g.dispose();
												GuestInView();
											}
										}

									} else if (addKiddFrame.getRdbtnPrivate().isSelected()) {
										int txtKin = Integer.parseInt(addKiddFrame.getTxtKinNum().getText()),
												txtClassNum = ((Integer) addKiddFrame.getTxtCLassNum()
														.getSelectedItem()).intValue();
										;
										if (!model.AddKidToClass(txtID, txtFname, txtLnane, dd, txtStreet, txtHouseNum,
												txtCity, lat, longt, txtFathName, txtMomName, place, txtKin,
												txtClassNum)) {

											JOptionPane.showMessageDialog(null,
													"Adding/Updating " + txtFname + " was not successful", null,
													JOptionPane.WARNING_MESSAGE);
										} else{
											JOptionPane.showMessageDialog(null,
													"Adding/Updating " + txtFname + " was successful", null,
													JOptionPane.INFORMATION_MESSAGE);
											
											if(g!=null){
												System.out.println("Fuck i am here !!2");
												addKiddFrame.dispose();
												g.dispose();
												GuestInView();
											}
										}
									}
								}
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, e.getMessage().toString(), null,
										JOptionPane.ERROR_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(null, "Invalid input or missing fields", null,
									JOptionPane.ERROR_MESSAGE);
						}
					}
				};
			}
		};
	}

	private ActionListener removeKid() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				final RemoveKid rk = new RemoveKid();
				view.setRemKid(rk);
				model.getKidsIDs(rk.getComboBox());
				rk.removeKidListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						String str = (String) rk.getComboBox().getSelectedItem();
						System.out.print(str);
						if (Check.isID(str)) {
							try {
								if (!model.RemoveKidFromClass(str))
									JOptionPane.showMessageDialog(null, "Removing kid was not successful", null,
											JOptionPane.WARNING_MESSAGE);
								else {
									rk.getComboBox().removeItem(str);
									JOptionPane.showMessageDialog(null, "Removing kid was successful", null,
											JOptionPane.INFORMATION_MESSAGE);
								}
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, e.getMessage().toString(), null,
										JOptionPane.ERROR_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(null, "Invalid input or missing fields", null,
									JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				rk.setVisible(true);
			}
		};
	}

	private ActionListener viewKinderGartens(final AddKidd addKiddFrame, final Guest g, final MainScr m) {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (addKiddFrame != null) {
						final KinTable kt;
						kt = new KinTable(model.getPrivKindsWithRating());
						if (g == null) {
							m.setViewKinderFrame(kt);
						} else {
							g.setViewKinderFrame(kt);
						}
						kt.getTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						kt.okClicked(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								try {
									int selRow = kt.getTable().getSelectedRow();
									int KiD = (int) kt.getTable().getValueAt(selRow, 0);
									if (KiD > 0) {
										addKiddFrame.getTxtKinNum().setText(String.valueOf(KiD));
										model.getClassNums(addKiddFrame.getTxtCLassNum(), KiD);
										kt.dispose();
									}
								} catch (Exception e2) {
									JOptionPane.showMessageDialog(null, "No kindergartens were selected", null,
											JOptionPane.WARNING_MESSAGE);
									kt.dispose();
								}

							}

						});
						kt.setVisible(true);
					} else {
						final KinTable kt = new KinTable(model.getPrivKindsWithRating());
						if (g == null) {
							m.setViewKinderFrame(kt);
						} else {
							g.setViewKinderFrame(kt);
						}
						kt.getBtnNewButton().setText("Close");
						kt.getTable().setRowSelectionAllowed(false);
						kt.okClicked(new ActionListener(){

							@Override
							public void actionPerformed(ActionEvent e) {
								kt.dispose();
							}
						});
						kt.setVisible(true);
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Error loading data", null, JOptionPane.ERROR_MESSAGE);
				}
			}
		};
	}

	private class WindowClosed extends WindowAdapter implements ActionListener {
		@Override
		public void windowClosing(WindowEvent e) {
			System.exit(1);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(1);

		}
	}

}
