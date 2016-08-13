package com.djain.app;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;

import com.djain.service.Contacts_Service;

public class EntryMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Contacts_Service contacts_Service = new Contacts_Service();
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String input_choice = "";
		
		while (true) {
			System.out.println("1) Add contact 2) Search 3) Exit");
			
			try {
				input_choice = input.readLine();
				
				switch(input_choice)
			      {
			         case "1" :
			        	 System.out.print("Enter name: ");
						 String name = input.readLine();
						 if(isValid(name.trim()))
							 contacts_Service.addContact(name.trim());
						 else
							 System.out.println("Please enter valid name."); 
			             break;
			         case "2" :
			        	 System.out.print("Enter name: ");
						 String searchStr = input.readLine();
							 Set<String> result = contacts_Service.search(searchStr);
							 if (!result.isEmpty()) {
								 for(String contact : result) {
								 	 System.out.println(contact);
								 }
							 } else
								 System.out.println("No contact found!");
						 break;
			         case "3":
			        	 System.out.println("Happy Searching !!");
						 System.exit(0);
			             break;
			         default :
			        	 System.out.println("Invalid input.Please try again");      
			      }
			} catch (Exception e) {
				System.err.println("Sorry something went wrong.. " + e.getMessage());
			}
		}
	}
	/**
	 * Function to validate user input
	 * @param name
	 * name length should be up to 50 char
	 * name should be of the format fisrtname and/or lastname separated by space characters.
	 */
	public static boolean isValid(String name) {
		if (((name.contains(" ") && name.split("\\s+").length > 2)||(name.length() > 50)))
			return false;
		return true;
	}
}
