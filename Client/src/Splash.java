
import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicProgressBarUI;

public class Splash extends JFrame {

	public static void main(String[] args) {
		final Splash splash = new Splash();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				splash.setVisible(true);
			}
		});
	}

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 8016433398654652789L;

	/**
	 * Download URL
	 * File Name for saving & running
	 * Logo image used for launcher display
	 * Save Directory where the client is being stored in
	 */
	private static String downloadUrl = "https://uc164de75ad1f63801ce0f8176b9.dl.dropboxusercontent.com/cd/0/get/AyeE2hRaTRZNcDaMhnlaKiQbka5Bcg1mkDjKNbsSzIIwES8wh_a4oPjaES-y1SxOEtqO5W8QdJMIUs_HtdWA7bMMWs_YVLQyCsCxQItH3JO59jYTlRdaPsS57KlQcp1BZVA/file#";
	private static String fileName = "Avalon.jar";
	private static String serverName = "Avalon";
	private static String logoImageURL = "https://i.imgur.com/siICegn.png";
	private static String saveDirectory = System.getProperty("user.home") + "/Avalon/";

	public static URL url;
	private JLabel imglabel;
	private ImageIcon img;
	private static JProgressBar pbar;

	public Splash() {
		File file = new File(saveDirectory + fileName);
		try {
			url = new URL(downloadUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		setSize(550, 350);
		setUndecorated(true);
		setBackground(new Color(0, 0, 0, 0));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setUndecorated(true);
		try {
			img = new ImageIcon(new URL(logoImageURL));
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		imglabel = new JLabel(img);
		add(imglabel);
		setLayout(null);
		pbar = new JProgressBar(0, 100);
		pbar.setStringPainted(false);
		imglabel.setBounds(0, 0, 543, 391);
		add(pbar);
		pbar.setPreferredSize(new Dimension(310, 30));
		pbar.setBounds(70, 250, 390, 20);
		pbar.setBackground(new Color(0, 0, 0, 0));
		pbar.setForeground(Color.blue);
		pbar.setBorderPainted(false);
		pbar.setUI(new BasicProgressBarUI() {
            protected Color getSelectionBackground() { return Color.WHITE; }
            protected Color getSelectionForeground() { return Color.WHITE; }
        });
		try {
			File readPath = new File(saveDirectory);
			if (!readPath.exists()) {
				readPath.mkdir();
			}
			if (file.exists()) {
				URLConnection connection = url.openConnection();
				connection.connect();
				long time = connection.getLastModified();
				if (time > file.lastModified()) {
					startApplication();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Thread thread = new Thread() {
			public void run() {
				OutputStream dest = null;
				URLConnection download;
				InputStream readFileToDownload = null;
				try {
					dest = new BufferedOutputStream(new FileOutputStream(saveDirectory + fileName));
					download = url.openConnection();
					readFileToDownload = download.getInputStream();
					byte[] data = new byte[1024];
					int numRead;
					long numWritten = 0;
					int length = download.getContentLength();
					while ((numRead = readFileToDownload.read(data)) != -1) {
						dest.write(data, 0, numRead);
						numWritten += numRead;
						int percent = (int) (((double) numWritten / (double) length) * 100D);
						pbar.setValue(percent);
						pbar.setStringPainted(true); // needed to fully re-add text.
						pbar.setString("loading " + serverName);
					}
				} catch (Exception exception) {
					exception.printStackTrace();
				} finally {
					try {
						if (readFileToDownload != null)
							readFileToDownload.close();
						if (dest != null)
							dest.close();
						startApplication();
					} catch (IOException ioe) {

					}
				}
			}
		};
		thread.start();
	}

	public static void startApplication() {
		try {
			Runtime.getRuntime().exec("java -jar " + (saveDirectory + fileName) + "");
			Thread.sleep(1000L);
			System.exit(0);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}