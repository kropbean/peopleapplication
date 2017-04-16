package peopleapplication;

import java.util.ArrayList;

public class Person {
	private String phoneNumber = null;
	private ArrayList<String> addresses = null;
	private float rating = -1;
	
	// constructor called when user searches for a person in the database
	public Person(String phoneNumber){
		this.phoneNumber = phoneNumber;
		
		// call to database to get addresses and rating
		// incomplete method...
	}
	
	// overloaded constructor called when user creates a person
	public Person(String phoneNumber, ArrayList<String> addresses, float rating){
		this.phoneNumber = phoneNumber;
		this.addresses = addresses;
		this.rating = rating;
	}
	
	
}