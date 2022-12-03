package com.rs.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @Author Tristam <Hassan>
 * @Project - 1. Rain
 * @Date - 28 Apr 2016
 *
 **/
public class Credentials {
	
	public static List<String> blocked = new ArrayList<String>();
	public static List<String> common = new ArrayList<String>();
	
	public static void init() {
		loadUsernames();
		loadPasswords();
	}
	
	private static void loadUsernames() {
		String username = null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader("data/censor/BannedUsernames/usernames.txt"));
			while ((username = reader.readLine()) != null) {
				blocked.add(username);
			}
			reader.close();
			reader = null;
		} catch (Exception e) {
			System.err.print("Error loading the blocked username list.");
		}
	}
	
	private static void loadPasswords() {
		String password = null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader("data/censor/CommonPasswords/passwords.txt"));
			while ((password = reader.readLine()) != null) {
				common.add(password);
			}
			reader.close();
			reader = null;
		} catch (Exception e) {
			System.err.print("Error loading the blocked password list.");
		}
	}

}
