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
	static ArrayList<String> peopleData = new ArrayList<String>();
	
	
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
		text += "|";
		for(String address : addresses){
			text += address + "#";
		}
		text = text.substring(0, text.length() - 1);
		text += "|";
		text += rating;
		text += "\n";
		
		write(text);
	}
	
	
	public void importDatabase(){
		try{
			char c;
			int i = 0;
			String message = "";
			while((i = this.in.read()) != -1){
				c = (char)i;
				if(c == '\n'){
					peopleData.add(message);
					message = "";
				}
				else{
					message += String.valueOf(c);
				}
			}
				
		}
		catch(Exception e){
			System.out.println("Failure when reading file: " + e);
		};
	}
	
	public ArrayList<Integer> searchByPhoneNumber(String phoneNumber){
		ArrayList<Integer> locations = new ArrayList<Integer>();
		int start = 0;
		int end = phoneNumber.length();
		int index = 0;
		
		for (String databasePhoneNumber : importPhoneNumbers()){
			for (int i = 0; i <= databasePhoneNumber.length() - phoneNumber.length(); i++){
				
				if(databasePhoneNumber.substring(start, end).equals(phoneNumber)){
					locations.add(index);
					break;
				}
				
				start += 1;
				end += 1;
			}
			start = 0;
			end = phoneNumber.length();
			index += 1;
			
		}
		return locations;
		
		
	}
	
	private ArrayList<String> importPhoneNumbers(){
		ArrayList<String> importedPhoneNumbers = new ArrayList<String>();
		for (String user : peopleData){
			
			importedPhoneNumbers.add(user.split("[|]")[0]);
			
		}
		return importedPhoneNumbers;
	}
	
	public ArrayList<String> getLine(int location){
		ArrayList<String> data = new ArrayList<String>();
		
		try{
			this.in.reset();
			int i = 0;
			char c;
			int skippedLines = 0;
			String message = "";
			while((i = this.in.read()) != -1){
				c = (char)i;
				
				if(c == '\n' && skippedLines != location){
					skippedLines += 1;
				}
				
				
				if(skippedLines == location){
					if(c == '\n'){
						data.add(message.split("[|]")[0]);
						data.add(message.split("[|]")[1]);
						data.add(message.split("[|]")[2]);
						message = "";
						break;
					}
					else{
						message += String.valueOf(c);
					}
					
					
					
				}
			}
			
			
		}
		catch(Exception e){
			System.out.println("Failed to search for person data (using location): " + e);
		}
		
		return data;
		
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
