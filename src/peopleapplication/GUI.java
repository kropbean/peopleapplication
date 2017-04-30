package peopleapplication;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;


// Creates the user interface of the application
public class GUI extends JFrame{
	
	private int width;
	private int height;
	//static PeopleDatabase pd = null;
	
	// constructor
	public GUI(){
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screen.width / 2;
		int height = screen.height / 2;
		this.width = width;
		this.height = height;

		// create the user interface
		setTitle("People Application");
		setSize(this.width, this.height);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		createMenubar();
		createToolbar();
		createTraceBody();
	
		
	}
	
	private void createMenubar(){
		JMenuBar menuBar = new JMenuBar();
		menuBar.setLayout(new MigLayout("insets 0, gap 0"));
		menuBar.setBackground(Color.GRAY);
		menuBar.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		JMenu fileMenu = new JMenu("File");
		fileMenu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fileMenu.setBackground(Color.BLACK);
		fileMenu.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		
		
		
		
		// create add button
		JMenuItem addBtn = new JMenuItem("Add");
		addBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		addBtn.setToolTipText("Add a person to the database.");
		addBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				// Create a dialogue to obtain user information
				
				JPanel dataEntryPanel = new JPanel(new MigLayout("insets 0, gap 0, al center center"));
				
				// phone number label and text field
				JLabel phoneNumberLabel = new JLabel("Phone Number:");
				phoneNumberLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
				
				JTextField phoneNumberField = new JTextField();
				phoneNumberField.setColumns(20);
				phoneNumberField.setFont(new Font("Tahoma", Font.PLAIN, 15));
				
				
				// address label and text field
				JLabel addressLabel = new JLabel("Address:");
				addressLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
				
				
				JTextField addressField = new JTextField();
				addressField.setFont(new Font("Tahoma", Font.PLAIN, 15));
				addressField.setColumns(20);
				
				// customer rating label and text field
				JLabel ratingLabel = new JLabel("Customer Rating:");
				ratingLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
				
				JTextField ratingField = new JTextField();
				ratingField.setColumns(20);
				ratingField.setFont(new Font("Tahoma", Font.PLAIN, 15));
				
				// add button
				JButton addBtn = new JButton("Add");
				addBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
				addBtn.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						String personalData = "";
						
						if(phoneNumberField.getText().length() >= 10 && isPhoneNumber(phoneNumberField.getText())){
							
						}
						
						if(addressField.getText().length() > 0){
						
						}
						
						
						
					}
					
				});
				
				// cancel button
				JButton cancelBtn = new JButton("Cancel");
				cancelBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
				cancelBtn.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						Container frame = cancelBtn.getParent();
						do{
							frame = frame.getParent();
						}
						while(!(frame instanceof JFrame));
						((JFrame) frame).dispose();
					}
					
				});
					
				dataEntryPanel.add(phoneNumberLabel, "al center center");
				dataEntryPanel.add(phoneNumberField, "al center center, wrap");
				dataEntryPanel.add(addressLabel, "al center center");
				dataEntryPanel.add(addressField, "al center center, wrap");
				dataEntryPanel.add(ratingLabel, "al center center");
				dataEntryPanel.add(ratingField, "al center center, wrap");
				dataEntryPanel.add(addBtn, "al center center");
				dataEntryPanel.add(cancelBtn, "al center center, wrap");
				
				
				// Get Screen Dimensions
				Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
				int width = screen.width / 4;
				int height = screen.height / 4;
				
				// Create pop up window
				JFrame popUpDataEntry = new JFrame();
				popUpDataEntry.setTitle("Add Customer");
				popUpDataEntry.setSize(width, height);
				popUpDataEntry.setLocationRelativeTo(null);
				popUpDataEntry.setLayout(new BorderLayout());
				popUpDataEntry.add(dataEntryPanel);
				popUpDataEntry.setBackground(Color.WHITE);
				popUpDataEntry.setVisible(true);
				
				
			}
		});
		
		
		
		
		
		// create exit button
		JMenuItem exitBtn = new JMenuItem("Exit");
		exitBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		exitBtn.setToolTipText("Exit Program.");
		
		exitBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Container frame = exitBtn.getParent();
				do{
					frame = frame.getParent();
				}while(!(frame instanceof JFrame));
				((JFrame) frame).dispose();
			}
		});
		
		
		
		fileMenu.add(addBtn);
		fileMenu.add(exitBtn);
		menuBar.add(fileMenu);
		setJMenuBar(menuBar);
	}
	

	
	private void createToolbar(){
		JPanel panel = new JPanel(new MigLayout("insets 0, gap 0, al center"));
		// set boundaries to be placed at the top of the window
		panel.setBounds(0, 0, this.width, 50);
		panel.setBackground(Color.GRAY);
		
		
		// create a search heading: label
		JLabel searchLabel = new JLabel("Search:");
		searchLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		searchLabel.setForeground(Color.BLACK);
		panel.add(searchLabel, "h 30, dock west, gap 20 20 10 10");
		
		
		// create a search field: text field
		JTextField searchField = new JTextField();
		searchField.setColumns(20);
		searchField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		searchField.setForeground(Color.BLACK);
		panel.add(searchField, "h 30, w " + this.width / 2 + ", gap 0 20 10 10");
		
		// create a search button: button
		JButton searchBtn = new JButton("Trace");
		searchBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		searchBtn.setFocusPainted(false);
		searchBtn.setForeground(Color.BLACK);
		searchBtn.setBackground(Color.WHITE);
		searchBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("Tracing: " + searchField.getText());
				
				ArrayList<String> addresses = new ArrayList<String>();
				addresses.add("19 Teseo Court");
				addresses.add("21 Jump Street");
				Person customer = new Person("6479724299", addresses, 10);
			}
		});
		
		
		
		panel.add(searchBtn, "h 30, dock east, gap 0 20 10 10");
		
		
		add(panel, BorderLayout.NORTH);
	}
	
	
	private void createTraceBody(){
		JPanel panel = new JPanel(new MigLayout("insets 0, gap 10 10, h 50%"));
		// set boundaries to be placed under the toolbar
		panel.setBounds(0, 50, this.width, this.height);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createTitledBorder("Trace Data"));
		panel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		add(panel, BorderLayout.CENTER);
	}
	
	// main method to invoke GUI creation
	public static void main(String []args){
		GUI window = new GUI();
		window.setVisible(true);
		//pd = new PeopleDatabase();

		
	}
	
	
	
	
	
	// miscellaneous private methods
	private boolean isPhoneNumber(String phoneNumber){
		
		return 
	}
	
	
}
