package com.rs.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URL;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @Author Tristam <Hassan>
 * @Project - Rain
 * @Date - 9 Mar 2016
 * 
 **/

public class PortChecker {

	public static void main(String... args) {
		init();
	}

	public static void init() {
		final String IP, PORT;
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		IP = JOptionPane.showInputDialog("Port Status Checker \nYour IP: " + GrabExternalIP(),
				GrabExternalIP());
		if (IP.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Something went wrong... =(", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		PORT = JOptionPane.showInputDialog("Port Status Checker", "Enter your Port");
		if (PORT.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Something went wrong... =(", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Status(IP, Integer.valueOf(PORT));

	}

	public static boolean Status(String HOST, final int PORT) {
		JOptionPane.showMessageDialog(null, "Connecting...\nPlease press OK and wait for a few moments.",
				"Please wait...", JOptionPane.QUESTION_MESSAGE);
		Socket socket = null;
		try {
			socket = new Socket(HOST, PORT);
			socket.setTcpNoDelay(true);
			JOptionPane.showMessageDialog(null, PORT + " is online!", "Connected!", JOptionPane.QUESTION_MESSAGE);
			return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, PORT + " is offline!", "Failed to connect",
					JOptionPane.QUESTION_MESSAGE);
			return false;
		} finally {
			if (socket != null)
				try {
					socket.close();
				} catch (Exception e) {

				}
		}
	}

	public static boolean Status1(final String host, final int port) {
		Socket socket = null;
		try {
			socket = new Socket(host, port);
			socket.setTcpNoDelay(true);
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (socket != null)
				try {
					socket.close();
				} catch (Exception e) {

				}
		}

	}

	private static String GrabExternalIP() {
		String IP = "";
		try {
			URL ip = new URL("http://checkip.amazonaws.com");
			BufferedReader in = new BufferedReader(new InputStreamReader(ip.openStream()));
			IP = in.readLine();
		} catch (Exception e) {

		}
		return IP;
	}

}
