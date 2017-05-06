package peopleapplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class PeopleDatabase {
	private String filePath = "src\\people.txt";
	private FileInputStream in = null;
	private FileOutputStream out = null;
	
	// database constructor: creates IO files if not already created.
	public PeopleDatabase(){
		try{
			if(this.out == null){
				System.out.println("Opening file...");
				
				// WARNING: must initialize output file first, before input file.
				// true to allow for file appending instead of overwriting.
				this.out = new FileOutputStream(this.filePath, true);		
				this.in = new FileInputStream(this.filePath);
			}
			
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	
	// writes to the file the provided text
	private void write(String text){
		try{
			// convert text to bytes before writing to file.
			byte[] textToBytes = text.getBytes();
			this.out.write(textToBytes);
		}
		catch(Exception e){
			System.out.println("Error on write(): " + e);
		}
	}
	
	public void addPerson(String phoneNumber, ArrayList<String> addresses, float rating){
		// Text format: 
		// <Phone Number>*<address 1>#<address 2>*<rating>\n
		String text = "";
		
		text += phoneNumber;
		text += "*";
		for(String address : addresses){
			text += address + "#";
		}
		text = text.substring(0, text.length() - 1);
		text += "*";
		text += rating;
		text += "\n";
		
		write(text);
	}
	
	
	public void read(){
		
	}
	
	
	// manual close destructor
	public void close(){
		try{
			this.in.close();
			this.out.close();
		}
		catch(Exception e){
			System.out.println("Error on close(): " + e);
		}
	}
	
	
	
	
	
	
	
}
