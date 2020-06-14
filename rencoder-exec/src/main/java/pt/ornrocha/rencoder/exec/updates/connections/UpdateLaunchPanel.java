/*
 * Copyright 2015
 *
 * This is free software: you can redistribute it and/or modify 
 * it under the terms of the GNU Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. 
 * 
 * This code is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the 
 * GNU Public License for more details. 
 * 
 * You should have received a copy of the GNU Public License 
 * along with this code. If not, see http://www.gnu.org/licenses/ 
 * 
 * Created by Orlando Rocha
 */
package pt.ornrocha.rencoder.exec.updates.connections;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import pt.ornrocha.rencoder.exec.updates.auxiliar.StaticFunctionsUpdater;
import pt.ornrocha.rencoder.exec.updates.auxiliar.WarnJTextPanePanel;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class UpdateLaunchPanel extends JFrame implements ActionListener,PropertyChangeListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton jButtonupdate;
	private JButton jButton1;
	private JPanel jPanel1;
	private JProgressBar jProgressBar1;
	private JTextArea jTextAreanew;
	private JScrollPane jScrollPane1;
	private JLabel jLabelmsg;
	private JTextArea jTextAreaprogress;
	private JButton jButtonignore;
	private ArrayList<String> updatesinfo=null;

	private WarnJTextPanePanel warn=null;

	private static String CLOSETHISPANEL="closethispanel";
	private static String IGNOREUPDATES="ignoreupdates";
	private static String PROCEEDUPDATE="proceedupdate";



	private boolean running=true;

	public UpdateLaunchPanel(ArrayList<String> updates){
		super();
		this.updatesinfo=updates;
		initGUI();
		String iconpath = new File("icons/rencoderpic2medium.png").getAbsolutePath();
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(iconpath));
		this.setTitle("Rencoder Updater");
		setInitialMSG();
		setInitUpdatesInfo();
		setInitPRogressbar();
		setJtextareaStyle();

		this.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				running=false;
				dispose();
			}
		});


	}



	private void setInitialMSG(){

		jLabelmsg.setText("A new update was found");
		jLabelmsg.setFont(new Font("Sherif", Font.BOLD, 25));
		jLabelmsg.setHorizontalAlignment(SwingConstants.CENTER);
		String iconpath = new File("icons/Rencoderblack.png").getAbsolutePath();
		jLabelmsg.setIcon(new ImageIcon(((new ImageIcon(iconpath)).getImage()).getScaledInstance(90, 90, java.awt.Image.SCALE_SMOOTH)));

	}

	private void setInitUpdatesInfo(){
		if(this.updatesinfo!=null){
			String up="";
			for (String line : updatesinfo) {
				up=up+line+"\n";
			}
			jTextAreanew.setText(up);
			jTextAreanew.setEditable(false);
		}
	}

	private void setJtextareaStyle(){
		jTextAreaprogress.setFont(new Font("Sherif", Font.BOLD, 18));
	}


	private void setInitPRogressbar() {
		jProgressBar1.setMinimum(0);
		jProgressBar1.setMaximum(100);

		jProgressBar1.setStringPainted(true);
	}


	public boolean checkifrunning(){
		return this.running;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		if(cmd.equals(CLOSETHISPANEL)){
			this.running=false;
			this.dispose();

		}
		else if(cmd.equals(IGNOREUPDATES)){
			StaticFunctionsUpdater.setCheckUpdatesBlock();
			this.running=false;
			this.dispose();
		}
		else if(cmd.equals(PROCEEDUPDATE)){
			ConnectionManager conn=getServerConnection();
			performUpdates(conn);
			//System.out.println("done");

		}
	}


	private ConnectionManager getServerConnection(){

		String updatefileurl=ConnectionActions.getUpdateFileURL();
		ConnectionManager conn = null;
		try {
			conn = new ConnectionManager(updatefileurl);
		} catch (IOException e) {
			warn= new WarnJTextPanePanel("Cannot connect to server the updater will close", this);
			this.running=false;
			this.dispose();
			System.out.println("Error in connection to the server to perform updates");
		}
		return conn;
	}


	private void performUpdates(ConnectionManager conn){
		UpdateExecutor up = new UpdateExecutor(conn);
		up.addUpdaterPropertyChangeListener(this);
		try {
			Thread t = new Thread(up);
			t.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initGUI() {
		try {
			{   GridBagLayout thisLayout = new GridBagLayout();
			thisLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
			thisLayout.rowHeights = new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
			thisLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1};

			thisLayout.columnWidths = new int[] {7, 7, 7, 7, 7, 7};
			getContentPane().setLayout(thisLayout);
			{
				jButtonupdate = new JButton();
				getContentPane().add(jButtonupdate, new GridBagConstraints(2, 9, 4, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				jButtonupdate.setText("Update");
				jButtonupdate.setActionCommand(PROCEEDUPDATE);
				jButtonupdate.addActionListener(this);
			}
			{
				jLabelmsg = new JLabel();
				getContentPane().add(jLabelmsg, new GridBagConstraints(0, 0, 6, 2, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				jLabelmsg.setText("msg");
			}
			{
				jTextAreaprogress = new JTextArea();
				getContentPane().add(jTextAreaprogress, new GridBagConstraints(0, 8, 6, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				jTextAreaprogress.setText("Progress...");
			}
			{
				jScrollPane1 = new JScrollPane();
				getContentPane().add(jScrollPane1, new GridBagConstraints(0, 2, 6, 5, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				jScrollPane1.setBorder(BorderFactory.createTitledBorder("Whats new?"));
				{
					jTextAreanew = new JTextArea();
					jScrollPane1.setViewportView(jTextAreanew);
					jTextAreanew.setText(" ");
				}
			}
			{
				jProgressBar1 = new JProgressBar();
				getContentPane().add(jProgressBar1, new GridBagConstraints(0, 7, 6, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			}
			jPanel1 = new JPanel();
			GridBagLayout jPanel1Layout = new GridBagLayout();
			jPanel1Layout.rowWeights = new double[] {0.1, 0.1};
			jPanel1Layout.rowHeights = new int[] {7, 7};
			jPanel1Layout.columnWeights = new double[] {0.1};
			jPanel1Layout.columnWidths = new int[] {7};
			jPanel1.setLayout(jPanel1Layout);
			{
				jButtonignore = new JButton();
				jButtonignore.setText("Ignore");
				jButtonignore.setActionCommand(CLOSETHISPANEL);
				jButtonignore.addActionListener(this);
				jPanel1.add(jButtonignore, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

				jButton1 = new JButton();
				jButton1.setText("Do not show again");
				jButton1.setActionCommand(IGNOREUPDATES);
				jButton1.addActionListener(this);

				jPanel1.add(jButton1 , new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

			}

			getContentPane().add(jPanel1, new GridBagConstraints(0, 9, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			}

			{
				this.setSize(471, 345);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}



	@Override
	public void propertyChange(PropertyChangeEvent evt) {

		if ("downprogressbar" == evt.getPropertyName()) {
			int val = (Integer) evt.getNewValue();

			if(val>0){
				jTextAreaprogress.setText("Downloading update file");
				jLabelmsg.setText("Updating...");
			}
			jProgressBar1.setValue(val);	

		}

		else  if ("installprogressbar" == evt.getPropertyName()) {
			int val = (Integer) evt.getNewValue();

			if(val>0){
				jTextAreaprogress.setText("Installing updates...");
			}
			if(val>85){
				jTextAreaprogress.setText("The update was finished");
				jLabelmsg.setText("Starting Rencoder...");
			}

			jProgressBar1.setValue(val);
		}

		else if("startapp" == evt.getPropertyName()){
			jTextAreaprogress.setText("Finishing... update");
			jLabelmsg.setText("Starting Rencoder...");
			this.warn=new WarnJTextPanePanel("Updates were successfully installed. Close this windows to start Rencoder",this, "Rencoder Update");
			this.running=false;
			this.dispose();
		}

		else if("installerror" == evt.getPropertyName()){
			jLabelmsg.setText("Installation  Error...");
			this.warn=new WarnJTextPanePanel("An error occurred during update installation, please try to download the new version directly from Rencoder website",this, "Rencoder Update");
			this.running=false;
			this.dispose();
		}

	}



}
