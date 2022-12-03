import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 *
 * @Author Tristam <Hassan>
 * @Project - Avalon client
 * @Date - 18 Jan 2016
 */

public class Preferences {

    /** The settings. */
    public static Properties Settings = new Properties();

    /** The file. */
    public static File file = new File(System.getenv("USERPROFILE") + "/Avalon.ini");

    /**
     * Loads the file.
     * @throws IOException 
     */
    public static void loadFile() throws IOException {
	InputStream inputStream = null;
	try {
	    if (!file.exists()) {
		file.createNewFile();
		client.FPS = false;
		client.shiftDrop = true;
		client.Zoom = true;
		client.drag = true;
	    }
	    inputStream = new FileInputStream(file);
	    Settings.load(inputStream);
	    System.out.print("File loaded successfully. \n");
	    loadSettings();
	}
	catch (Exception e) {
	    System.err.print("File was not found or could not be loaded. \n");
	    inputStream = null;
	    if (inputStream == null) {
		file.delete();
		file.createNewFile();
	    }
	}
    }

    /**
     * Saves the settings.
     */
    public static void saveSettings() {
	if (!file.exists()) {
	    System.out.print("File does not exist. \n");
	    try {
		file.createNewFile();
	    }
	    catch (IOException e) {
		
	    }
	    return;
	}
	if (file == null) {
	    System.out.print("File is null. \n");
	    return;
	}
	try {
	    Settings.setProperty("FPS", client.FPS ? "true" : "false");
	    Settings.setProperty("Zoom", client.Zoom ? "true" : "false");
	    Settings.setProperty("Shift", client.shiftDrop ? "true" : "false");
	    Settings.setProperty("Slow-Drag", client.drag ? "true" : "false");
	    System.out.print("Settings saved to " + file + ". \n");
	    OutputStream out = new FileOutputStream(file);
	    Settings.store(out, "Client settings - Avalon. ~true = enabled, false = disabled");
	    out.close();
	} catch (Exception e) {
	    
	}
    }

    /**
     * Loads the settings.
     */
    private static void loadSettings() {
	if (file.exists()) {
	    try {
		if (Settings.getProperty("FPS").equalsIgnoreCase("true")) {
		    client.FPS = true;
		}
		if (Settings.getProperty("Zoom").equalsIgnoreCase("true")) {
		    client.Zoom = true;
		}
		if (Settings.getProperty("Shift").equalsIgnoreCase("true")) {
		    client.shiftDrop = true;
		}
		if (Settings.getProperty("Slow-Drag").equalsIgnoreCase("true")) {
		    client.drag = true;
		}
		System.out.print("All settings were successfully loaded. \n");
	    }
	    catch (Exception e) {
		System.err.print("Something went wrong with loading the settings. \n");
		if (file == null || Settings == null) {
		    file.delete();
		    try {
			file.createNewFile();
			System.out.print("File created." + file.getPath());
		    }
		    catch (IOException e1) {
		    }
		}
	    }
	}
    }
    
    public static Properties getSettings() {
	return Settings;
    }

}
