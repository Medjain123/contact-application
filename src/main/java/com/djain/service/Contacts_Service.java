package com.djain.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.djain.trie.Trie;

public class Contacts_Service {
	/*
	 * This map will store last name to first names mapping
	 */
	private Map<String, List<String>> reverseContactMap = new HashMap<String, List<String>>();
	
	/*
	 * This Trie will store all contacts
	 */
	Trie contactsTrie = new Trie();
	
	/**
	 * add contact.
	 * @param String name
	 */
	public void addContact(String name) {
		/*
		 * Assumption:Duplicate name addition will be considered as one.
		 * eg. addContact(Chris) followed by addContact(Chris) , both will be treated as one entry in contact list
		 * while addContact(Chris) and addContact(Chris, Chris), both will be treated as diffrent names. i.e Chris , Chris Chris(first and last name are same).
		 */
		contactsTrie.insert(name); //insert complete name to trie
		String firstName = null;
		String lastName = null;
		
		if (name.contains(" ")) {
			firstName = name.split("\\s+")[0];
			lastName =  name.split("\\s+")[1];
		} else {
			firstName = name;
		}
		
		if(lastName != null && !firstName.equalsIgnoreCase(lastName)){
			List<String> first_names= new ArrayList<String>();
			if(reverseContactMap.containsKey(lastName)){
				first_names = reverseContactMap.get(lastName);
			}
			first_names.add(firstName);
			reverseContactMap.put(lastName, first_names);
			
			/*
			 * insert last name to trie
			 * it will be used to support search by last name
			 */
			contactsTrie.insert(lastName);
		}
	}
	
	/**
	 * search contact.
	 * @param String name
	 */
	
	public Set<String> search(String name) {
		 
		List<String> result=new ArrayList<String>();
		Set<String> finResult = new LinkedHashSet<String>();//set to store final result
		List<String> first_names = new ArrayList<String>();//list of first names that are mapped to a last name
		/*
		 * This will ensure that exact match should be the first result
		 */
		Boolean exactMatch = contactsTrie.search(name);
		if(exactMatch && (!reverseContactMap.containsKey(name))){
			finResult.add(name);
		}
		/*
		 * Search contacts that starts with the prefix(name)
		 */
		result = contactsTrie.searchWithPrefix(name);
		
		if (!result.isEmpty()) {
			for (String prefix : result) {
				/*
				 * few prefixes will be matching only the last name as trie is having last name entry also.
				 * so constructing full name out of it using reverseContactMap
				 * This is how I enabled search on last name.
				 */
				if(reverseContactMap.containsKey(prefix)){
					/*
					 * Prefix is last name so construct full name
					 */
					first_names = reverseContactMap.get(prefix);
					for(String first_name:first_names){
						String full_name = first_name+' '+prefix;
						finResult.add(full_name);
					}
				}else{
					/*
					 * prefix is not last name
					 */
					finResult.add(prefix);
				}
			}
		}
		
		
		return finResult;
	}

}