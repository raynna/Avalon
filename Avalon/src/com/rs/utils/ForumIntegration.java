package com.rs.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author Arno | Articron
 */

public class ForumIntegration {

	// sec pass for the cpanel pls website well doesnt phillip got that? check
	// ur email u gave it 2 me in the beginning
	/* database_details */
	private static final String database_location = "jdbc:mysql://104.255.227.21/avalonpk_forum";
	private static final String database_username = "avalonpk_integ";
	private static final String database_password = "wielen123"; // let me hax
																	// this real
																	// quick

	/* connection vars */
	private static Connection server_website_throttle;
	private PreparedStatement prepared_statement;
	private ResultSet result_printset;

	/* player_details */
	private String password;
	private String password_md5;
	private String salt_md5;
	private int player_right;

	/* Entry point for initiation */
	public static void init() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			server_website_throttle = DriverManager.getConnection(database_location, database_username,
					database_password);
			Logger.log("Forum Integration", "Hooked up correctly...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* Object instance to check authentication */
	public ForumIntegration(String user, String pass) {
		this.password = pass.toLowerCase();
		try {
			prepared_statement = server_website_throttle
					.prepareStatement("SELECT * FROM ipbmembers WHERE name='" + user + "'");
			result_printset = prepared_statement.executeQuery();

			/* if player exists in database */
			if (result_printset.next()) {
				Logger.log("[FORUM INTEGRATION", " FOUND USER");
				password_md5 = result_printset.getString("members_pass_hash");
				salt_md5 = result_printset.getString("members_pass_salt");
				setPlayer_right(result_printset.getInt("member_group_id"));

			} else {
				Logger.log("[FORUM INTEGRATION", " NON-FOUND USER");
				/* if player does not exist in forum database */
				password_md5 = null;
				salt_md5 = null;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* authentication */
	public boolean getValid() {
		return (password_md5 == null || salt_md5 == null) ? false
				: encryptToMD5(password, salt_md5).equalsIgnoreCase(password_md5);
	}

	/* Apache Commons Codec DigestUtility lib */
	private String encryptToMD5(String pass, String salt) {
		return DigestUtils.md5Hex(DigestUtils.md5Hex(salt).concat(DigestUtils.md5Hex(pass)));
	}

	public int getPlayer_right() {
		return player_right;
	}

	public void setPlayer_right(int player_right) {
		this.player_right = player_right;
	}

}