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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;


// Creates the user interface of the application
public class GUI extends JFrame{
	static PeopleDatabase pd;
	private int width;
	private int height;
	static JPanel traceBody;
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
						
						if((formatPhoneNumber(phoneNumberField.getText()).length() >= 10) && (addressField.getText().length() > 0) && (ratingField.getText().length() >= 0 && isNumberBetween0to10(ratingField.getText()))){
							// format phoneNumber
							String phoneNumber = formatPhoneNumber(phoneNumberField.getText());
									
							// format addresses
							ArrayList<String> addresses = new ArrayList<String>();
							for(String address: addressField.getText().split(",")){
								address = deleteSpacingPrefixes(address);
								address = getReverse(deleteSpacingPrefixes(getReverse(address)));
								addresses.add(address);
							}
							
							// format rating
							float rating = Float.valueOf(ratingField.getText());
							
							// adds a person to the database!
							pd.addPerson(phoneNumber, addresses, rating);
							
							
							// deploy success message!
							JLabel message = new JLabel("Successfully created new user!");
							message.setFont(new Font("Tahoma", Font.PLAIN, 15));
							
							JButton acceptBtn = new JButton("OK");
							acceptBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
							acceptBtn.addActionListener(new ActionListener(){
								public void actionPerformed(ActionEvent e){
									Container dialog = acceptBtn.getParent();
									do
									{
										dialog = dialog.getParent();
									}
									while(!(dialog instanceof JDialog));
									((JDialog) dialog).setVisible(false);
									
									
								}
							});
							
							JPanel alertPanel = new JPanel(new MigLayout("gap 0, insets 0, al center center"));
							alertPanel.add(message, "wrap, al center, gapbottom 10");
							alertPanel.add(acceptBtn, "wrap, al center");
							
							
							JDialog alertBox = new JDialog();
							int dWidth = 240;
							int dHeight = 120;
							alertBox.setBounds(width - dWidth/2, height - dHeight/2, dWidth, dHeight);
							alertBox.setLayout(new BorderLayout());
							alertBox.add(alertPanel, BorderLayout.CENTER);
							alertBox.setVisible(true);
							
							
						}
						else{
							// Error handling
							
							ArrayList<JLabel> errorMessages = new ArrayList<JLabel>();
							
							if(formatPhoneNumber(phoneNumberField.getText()).length() < 10){
								errorMessages.add(createErrorMessage("Invalid phone number!"));
							}
							
							if(addressField.getText().length() <= 0){
								errorMessages.add(createErrorMessage("Invalid address!"));
							}
							
							if(ratingField.getText().length() < 0){
								errorMessages.add(createErrorMessage("Invalid rating value!"));
							}
							
							if(!isNumberBetween0to10(ratingField.getText())){
								errorMessages.add(createErrorMessage("Rating value must be a value between 0-10."));
							}
							
							
							JPanel errorPanel = new JPanel(new MigLayout("gap 0, insets 0, al center center"));
							
							if(errorMessages.size() >= 1){
								errorPanel.add(createErrorMessageBold("Invalid entry:"), "wrap, al center center");
								for(JLabel messageSegment : errorMessages){
									errorPanel.add(messageSegment, "wrap, al center center");
								}
							}
							else{
								errorPanel.add(createErrorMessage("An Error has occured."), "wrap, al center center");
							}
							
							
							
							
							
								
							JDialog errorBox = new JDialog();
							int dWidth = 400;
							int dHeight = 120;
							errorBox.setBounds(width - dWidth/2, height - dHeight/2, dWidth, dHeight);
							errorBox.setLayout(new BorderLayout());
							errorBox.add(errorPanel, BorderLayout.CENTER);
							errorBox.setVisible(true);
							
							
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
				
				// create panel to hold "Enter phone number, address, and rating here" stuff
				JPanel dataEntryPanel = new JPanel(new MigLayout("insets 0, gap 0, al center center"));
				dataEntryPanel.add(phoneNumberLabel, "al left center, gap 10 2 20 5");
				dataEntryPanel.add(phoneNumberField, "al center center, gap 0 10 20 5, wrap");
				dataEntryPanel.add(addressLabel, "al left center, gap 10 2 0 5");
				dataEntryPanel.add(addressField, "al center center, gap 0 10 0 5, wrap");
				dataEntryPanel.add(ratingLabel, "al left center, gap 10 2 0 5");
				dataEntryPanel.add(ratingField, "al center center, gap 0 10 0 5, wrap");
				
				JPanel buttonPanel = new JPanel(new MigLayout("insets 0, gap 0, al center center"));
				buttonPanel.add(addBtn, "al center center, gapright 50, gapbottom 20");
				buttonPanel.add(cancelBtn, "al center center, gapbottom 20, wrap");
				
				
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
				popUpDataEntry.add(dataEntryPanel, BorderLayout.CENTER);
				popUpDataEntry.add(buttonPanel, BorderLayout.AFTER_LAST_LINE);
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
				pd.importDatabase();
				
				ArrayList<Integer> locations = pd.searchByPhoneNumber(searchField.getText());
				for(int location : locations){
					System.out.println("Found at location: " + location);
					updateTraceBody(pd.getLine(location));
				}
				
				
				
				
				
			}
		});
		
		
		
		panel.add(searchBtn, "h 30, dock east, gap 0 20 10 10");
		
		
		add(panel, BorderLayout.NORTH);
	}
	
	
	private void createTraceBody(){
		traceBody = new JPanel(new MigLayout("insets 0, gap 10 10, h 50%"));
		// set boundaries to be placed under the toolbar
		traceBody.setBounds(0, 50, this.width, this.height);
		traceBody.setBackground(Color.WHITE);
		traceBody.setBorder(BorderFactory.createTitledBorder("Trace Data"));
		traceBody.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		add(traceBody, BorderLayout.CENTER);
	}
	
	private void updateTraceBody(ArrayList<String> data){
		JPanel personDataBlock = new JPanel(new MigLayout("gap 0, insets 0, al center center"));
		for(String dataSegment : data){
			JLabel userInfo = new JLabel(dataSegment);
			userInfo.setFont(new Font("Tahoma", Font.PLAIN, 15));
			personDataBlock.add(userInfo, "wrap, al center center");
		}
		
		personDataBlock.setBounds(0, 0, this.width, 200);
		personDataBlock.setBackground(Color.DARK_GRAY);
		
		traceBody.add(personDataBlock, "wrap");
		
		
	}
	
	// main method to invoke GUI creation
	public static void main(String []args){
		GUI window = new GUI();
		window.setVisible(true);
		pd = new PeopleDatabase();
		
	}
	
	
	
	
	
	// miscellaneous private methods
	private boolean isPhoneNumber(String phoneNumber){
		phoneNumber = formatPhoneNumber(phoneNumber);
		for(char number: phoneNumber.toCharArray()){
			if(!Character.isDigit(number)){
				return false;
			}
		}
		return true; 
	}
	
	
	private boolean isNumberBetween0to10(String inputNumber){
		if(inputNumber.length() > 0){
			// make sure all information given is numeric
			for(char number: inputNumber.toCharArray()){
				if(!Character.isDigit(number) && number != '.'){
					return false;
				}
			}
			
			// return false if value exceeds 10 or is negative.
			if(Float.valueOf(inputNumber) < 0 || Float.valueOf(inputNumber) > 10){
				return false;
			}
			
			
		}
		else{
			return false;
		}
		
		return true;
	}
	
	private String deleteSpacingPrefixes(String userInput){
		
		if (userInput.charAt(0) == ' '){
			return deleteSpacingPrefixes(userInput.substring(1, userInput.length()));
		} 
		else{
			return userInput;
		}
	}
	
	private String getReverse(String userInput){
		if((null) == userInput || userInput.length() <= 1){
			return userInput;
		}
		
		return getReverse(userInput.substring(1)) + userInput.charAt(0);
		
	}
	
	private String formatPhoneNumber(String phoneNumber){
		String formatted = "";
		for(char number: phoneNumber.toCharArray()){
			if((int) number >= 48 && (int) number <= 57){
				formatted = formatted + Character.toString(number);
			}
		}
		return formatted;
	}
	
	
	private JLabel createErrorMessage(String message){
		JLabel error = new JLabel(message);
		error.setFont(new Font("Tahoma", Font.PLAIN, 15));
		error.setForeground(Color.RED);
		return error;
	}
	
	private JLabel createErrorMessageBold(String message){
		JLabel error = new JLabel(message);
		error.setFont(new Font("Tahoma", Font.BOLD, 15));
		error.setForeground(Color.RED);
		return error;
	}
	
}
