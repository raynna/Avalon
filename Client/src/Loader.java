import java.applet.Applet;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class Loader extends Applet implements ActionListener {

    private static final long serialVersionUID = 7639088664641445302L;
    public static Properties client_parameters = new Properties();
    public JFrame client_frame;
    public JPanel client_panel = new JPanel();

    private static final double clientVersion = 1.0;

    public static String host_IP = "0.0.0.0";
    public static boolean hosted = true;

    public static boolean usingRS = false;
    public static boolean useIsaac = false;
    public static boolean useRoute = false;
    public static boolean useMapsTest = false;
    public boolean takeScreeny;
    public int screenshot;

    public static String SERVER_NAME = "Avalon";
    public final static int PORT = 43594;
    public static boolean LOBBY_ENABLED = false;
    public static boolean DISABLE_XTEA_CRASH = true;
    public static boolean DISABLE_USELESS_PACKETS = false;
    public static boolean DISABLE_RSA = false;
    public static boolean DISABLE_CS_MAP_CHAR_CHECK = true;
    public static boolean DISABLE_SOFTWARE_MODE = false;
    public static final int REVISION = 718;
    public static final int LOBBY_PORT = PORT;
    public static int SUB_REVISION = 1;
    public static final boolean ACCOUNT_CREATION_DISABLED = true;
    public static final boolean RS = false;

    public static String LOBBY_IP = host_IP;
    public static Loader instance;
    public static int[] outSizes = new int[256];

    public static TrayIcon trayIcon;

    public static void main(String[] args) {
        ItemPrices.init();
        System.setProperty("java.net.preferIPv4Stack", "true");
        System.setProperty("java.net.preferIPv6Addresses", "false");
        setParams();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("Theme not detected, reverting to OS Default.");
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Throwable e2) {
                e.printStackTrace();
            }
        }
        Loader loader = new Loader();
        final JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(loader);
        panel.setPreferredSize(new Dimension(768, 503));
        frame.setMinimumSize(new Dimension(768, 503));
        panel.setBackground(Color.BLACK);
        JMenuBar bar = new JMenuBar();

        for (final Option o : Option.values()) {
            final MenuButton menu = new MenuButton(o.name);
            menu.createToolTip();
            menu.addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    frame.getJMenuBar().createToolTip();
                    switch (o.option) {
                        case 4:
                            sendWebsiteURL("https://avalon-rsps.com/");
                            break;
                        case 5:
                            sendWebsiteURL("https://avalon-rsps.com/vote/");
                            break;
                        case 6:
                            sendWebsiteURL("https://avalon-rsps.com/donate/");
                            break;
                        case 7:
                            sendWebsiteURL("https://avalon-rsps.com/highscores/");
                            break;
                        case 8:
                            sendWebsiteURL("https://avalon-rsps.com/forum/");
                            break;
                        case 9:
                            sendWebsiteURL("https://discord.gg/upt55A7");
                            break;
                        case 10:
                        	sendInfo();
                        	break;
                        case 11:
                            client.FPS = !client.FPS;
                            if (client.FPS)
                                Class255.method2435("FPS on", 899052076);
                            else
                                Class255.method2435("FPS off", 1184714257);
                            JOptionPane.showMessageDialog(frame, "Display fps has been " + (client.FPS ? "Activated" : "Deactivated"), "FPS", JOptionPane.INFORMATION_MESSAGE);
                            break;
                    }
                    Preferences.saveSettings();

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }

            });

            bar.add(menu);
        }
        frame.setJMenuBar(bar);
        frame.setFont(new Font("System", Font.PLAIN, 14));
        Font f = frame.getFont();
        FontMetrics fm = frame.getFontMetrics(f);
        int x = fm.stringWidth("");
        int y = fm.stringWidth(" ");
        int z = frame.getWidth() / 2 - (x / 10);
        int w = z / y;
        String pad = "";
        pad = String.format("%" + w + "s", pad);
        frame.setTitle(pad + "\u00A9 " + SERVER_NAME + " - Version: " + clientVersion);
        frame.setResizable(true);
        //frame.addWindowListener(client);
        frame.getContentPane().add(panel, "Center");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/resources/ghostBg1.png"));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.toFront();
        client client = new client();
        client.supplyApplet(loader);
        client.init();
        client.start();
    }

    static void setParams() {
        client_parameters.put("separate_jvm", "true");
        client_parameters.put("boxbgcolor", "black");
        client_parameters.put("image", "http://www.runescape.com/img/game/splash2.gif");
        client_parameters.put("centerimage", "true");
        client_parameters.put("boxborder", "false");
        client_parameters.put("java_arguments", "-Xmx256m -Xss2m -Dsun.java2d.noddraw=true -XX:CompileThreshold=1500 -Xincgc -XX:+UseConcMarkSweepGC -XX:+UseParNewGC");
        client_parameters.put("27", "0");
        client_parameters.put("1", "0");
        client_parameters.put("16", "false");
        client_parameters.put("17", "false");
        client_parameters.put("21", "1"); // WORLD ID
        client_parameters.put("30", "false");
        client_parameters.put("20", LOBBY_IP);
        client_parameters.put("29", "");
        client_parameters.put("11", "true");
        client_parameters.put("25", "1378752098");
        client_parameters.put("28", "0");
        client_parameters.put("8", ".runescape.com");
        client_parameters.put("23", "false");
        client_parameters.put("32", "0");
        client_parameters.put("15", "wwGlrZHF5gKN6D3mDdihco3oPeYN2KFybL9hUUFqOvk");
        client_parameters.put("0", "IjGJjn4L3q5lRpOR9ClzZQ");
        client_parameters.put("2", "");
        client_parameters.put("4", "1"); // WORLD ID
        client_parameters.put("14", "");
        client_parameters.put("5", "8194");
        client_parameters.put("-1", "QlwePyRU5GcnAn1lr035ag");
        client_parameters.put("6", "0");
        client_parameters.put("24", "true,false,0,43,200,18,0,21,354,-15,Verdana,11,0xF4ECE9,candy_bar_middle.gif,candy_bar_back.gif,candy_bar_outline_left.gif,candy_bar_outline_right.gif,candy_bar_outline_top.gif,candy_bar_outline_bottom.gif,loadbar_body_left.gif,loadbar_body_right.gif,loadbar_body_fill.gif,6");
        client_parameters.put("3", "hAJWGrsaETglRjuwxMwnlA/d5W6EgYWx");
        client_parameters.put("12", "false");
        client_parameters.put("13", "0");
        client_parameters.put("26", "0");
        client_parameters.put("9", "77");
        client_parameters.put("22", "false");
        client_parameters.put("18", "false");
        client_parameters.put("33", "");
        client_parameters.put("haveie6", "false");
    }

    public static enum Option {
        WEBSITE("Website", 4, "Opens website"),

        VOTE("Vote", 5, "Opens voting website"),

        DONATE("Store", 6, "Opens donate website"),

        HIGHSCORES("Highscores", 7, "Opens highscore website"),

        FORUMS("Forums", 8, "Opens " + SERVER_NAME + "-forum page"),

        DISCORD("Discord", 9, "Opens " + SERVER_NAME + " own discord"),

        INFO("Info", 10, "Information"),

        DISPLAYFPS("Display-FPS", 11, "Toggle displaying fps");

        public String name, toolTip;
        public int option;

        private Option(String name, int option, String toolTip) {
            this.name = name;
            this.option = option;
            this.toolTip = toolTip;
        }
    }

    public String getParameter(String string) {
        return (String) client_parameters.get(string);
    }

    public URL getDocumentBase() {
        return getCodeBase();
    }

    @Override
    public URL getCodeBase() {
        try {
            return new URL("https://" + host_IP);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public static void sendWebsiteURL(String url) {
        String os = System.getProperty("os.name");
        try {
            if (os.startsWith("Windows")) {
                Runtime.getRuntime().exec("rund1132 url.dll, FileProtocolHandler " + url);
            } else {
                final String[] BROWSER = {"firefox", "mozilla", "chrome", "opera"};
                String MYBROWSER = null;
                for (int i = 0; i < BROWSER.length && MYBROWSER == null; i++)
                    if (Runtime.getRuntime().exec(new String[]{"which", BROWSER[i]}).waitFor() == 0)
                        MYBROWSER = BROWSER[i];
                Runtime.getRuntime().exec(new String[]{MYBROWSER, url});

            }
        } catch (Exception e) {
            System.err.println(os + " : Failed to open.");
        }
    }


    private static void sendInfo() {
        int cores = Runtime.getRuntime().availableProcessors();
        JTextArea text = new JTextArea(7, 5);
        JScrollPane scrollPane = new JScrollPane(text);
        text.setText("Avalon - Client Version " + clientVersion + "\n\n\n � Avalon is created for educational purposes only. All credits goes to Jagex and its respective owners. " + "\n\n� Avalon is not affiliated with Jagex Ltd/RuneScape in anyway. \n \n Operating system: " + System.getProperty("os.name") + " " + System.getProperty("os.arch") + " \n Java version: " + System.getProperty("java.version") + " \n " + (cores > 1 ? "Total CPU threads: " : "CPU thread: ") + cores);
        text.setWrapStyleWord(true);
        text.setLineWrap(true);
        text.setCaretPosition(0);
        text.setEditable(false);
        scrollPane.setPreferredSize(new Dimension(450, 175));
        JOptionPane.showMessageDialog(null, scrollPane, "About Avalon", JOptionPane.INFORMATION_MESSAGE);
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
