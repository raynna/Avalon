package com.rs.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @Author Tristam <Hassan>
 * @Project - Rain
 * @Date - 19 Jan 2016
 *
 **/
public class UpdateLogger {

	private static Date date = new Date();
	private static File file = new File("2. Updates.txt");

	public static void main(String... args) {
		init();
	}

	private static void init() {
		try {
			if (!file.exists())
				file.createNewFile();
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			String update = JOptionPane.showInputDialog("Update logger", "Type in your update here");
			if (update.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No text was detected. Logging cancelled", "=(",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
			bw.write("On " + date.toString() + ", "
					+ (System.getenv("USERNAME").equals("andreas") ? "Developer" : System.getenv("USERNAME")) + ": "
					+ update);
			bw.newLine();
			bw.flush();
			bw.close();
		} catch (Exception err) {

		}

	}

}
