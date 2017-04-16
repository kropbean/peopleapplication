package peopleapplication;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;


// Creates the user interface of the application
public class GUI extends JFrame{
	
	private int width;
	private int height;
	
	// constructor
	public GUI(){
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screen.width / 2;
		int height = screen.height / 2;
		this.width = width;
		this.height = height;
		createUI();
	}
	
	// creates a window half of the screen dimensions.
	private void createUI(){
		
		setTitle("People Application");
		setSize(width, height);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		createToolbar();
		createTraceBody();
		
	}
	
	
	private void createToolbar(){
		JPanel panel = new JPanel(new MigLayout("insets 0, gap 0, al center center"));
		// set boundaries to be placed at the top of the window
		panel.setBounds(0, 0, this.width, 50);
		panel.setBackground(Color.GRAY);
		
		
		// create a search heading: label
		JLabel searchLabel = new JLabel("Search:");
		searchLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		searchLabel.setForeground(Color.BLACK);
		panel.add(searchLabel, "h 30, dock west, gapright 20");
		
		
		
		// create a search field: textfield
		JTextField searchField = new JTextField();
		searchField.setColumns(20);
		searchField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		searchField.setForeground(Color.BLACK);
		panel.add(searchField, "h 30, w " + this.width / 2 + ", gapright 20");
		
		// create a search button: button
		JButton searchBtn = new JButton("Trace");
		searchBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		searchBtn.setFocusPainted(false);
		searchBtn.setForeground(Color.BLACK);
		searchBtn.setBackground(Color.WHITE);
		panel.add(searchBtn, "h 30, dock east");
		
	
		
		
	
		
		
		add(panel);
	}
	
	
	private void createTraceBody(){
		JPanel panel = new JPanel(new MigLayout("insets 0, gap 0, h 50%"));
		// set boundaries to be placed under the toolbar
		panel.setBounds(0, 50, width, height);
		panel.setBackground(Color.WHITE);
		
		add(panel);
	}
	
	// main method to invoke GUI creation
	public static void main(String []args){
		
		EventQueue.invokeLater(() -> {
			GUI window = new GUI();
			window.setVisible(true);
		});
		
	}
	
	
	
}
