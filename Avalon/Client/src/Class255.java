
/* Class255 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Class255 implements Runnable {
    boolean aBoolean2791;
    Thread aThread2792;
    int anInt2793;
    Class461 aClass461_2794 = new Class461();
    static Class4 aClass4_2795;

    Class298_Sub37_Sub16_Sub2 method2429(int i, Class329 class329, byte i_0_) {
	try {
	    Class298_Sub37_Sub16_Sub2 class298_sub37_sub16_sub2 = new Class298_Sub37_Sub16_Sub2();
	    class298_sub37_sub16_sub2.anInt10006 = -745165359;
	    class298_sub37_sub16_sub2.aLong7406 = i * 1476940603538232441L;
	    class298_sub37_sub16_sub2.aClass329_10010 = class329;
	    class298_sub37_sub16_sub2.aBoolean9672 = false;
	    method2432(class298_sub37_sub16_sub2, (byte) 103);
	    return class298_sub37_sub16_sub2;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("kr.b(").append(')').toString());
	}
    }

    Class298_Sub37_Sub16_Sub2 method2430(int i, Class329 class329, int i_1_) {
	try {
	    Class298_Sub37_Sub16_Sub2 class298_sub37_sub16_sub2 = new Class298_Sub37_Sub16_Sub2();
	    class298_sub37_sub16_sub2.anInt10006 = -248388453;
	    synchronized (this.aClass461_2794) {
		for (Class298_Sub37_Sub16_Sub2 class298_sub37_sub16_sub2_2_ = ((Class298_Sub37_Sub16_Sub2) this.aClass461_2794.method5984(686363137)); class298_sub37_sub16_sub2_2_ != null; class298_sub37_sub16_sub2_2_ = ((Class298_Sub37_Sub16_Sub2) this.aClass461_2794.method5985(-852978429))) {
		    if (i == (-5533549728640346679L * class298_sub37_sub16_sub2_2_.aLong7406) && class329 == (class298_sub37_sub16_sub2_2_.aClass329_10010) && -1906220653 * (class298_sub37_sub16_sub2_2_.anInt10006) == 2) {
			class298_sub37_sub16_sub2.aByteArray10011 = class298_sub37_sub16_sub2_2_.aByteArray10011;
			class298_sub37_sub16_sub2.aBoolean9670 = false;
			Class298_Sub37_Sub16_Sub2 class298_sub37_sub16_sub2_3_ = class298_sub37_sub16_sub2;
			return class298_sub37_sub16_sub2_3_;
		    }
		}
	    }
	    class298_sub37_sub16_sub2.aByteArray10011 = class329.method3992(i, -250604251);
	    class298_sub37_sub16_sub2.aBoolean9670 = false;
	    class298_sub37_sub16_sub2.aBoolean9672 = true;
	    return class298_sub37_sub16_sub2;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("kr.a(").append(')').toString());
	}
    }

    Class298_Sub37_Sub16_Sub2 method2431(int i, byte[] is, Class329 class329, int i_4_) {
	try {
	    Class298_Sub37_Sub16_Sub2 class298_sub37_sub16_sub2 = new Class298_Sub37_Sub16_Sub2();
	    class298_sub37_sub16_sub2.anInt10006 = -496776906;
	    class298_sub37_sub16_sub2.aLong7406 = 1476940603538232441L * i;
	    class298_sub37_sub16_sub2.aByteArray10011 = is;
	    class298_sub37_sub16_sub2.aClass329_10010 = class329;
	    class298_sub37_sub16_sub2.aBoolean9672 = false;
	    method2432(class298_sub37_sub16_sub2, (byte) 39);
	    return class298_sub37_sub16_sub2;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("kr.f(").append(')').toString());
	}
    }

    void method2432(Class298_Sub37_Sub16_Sub2 class298_sub37_sub16_sub2, byte i) {
	try {
	    synchronized (this.aClass461_2794) {
		this.aClass461_2794.method5982(class298_sub37_sub16_sub2, (byte) -98);
		this.anInt2793 += 872113935;
		this.aClass461_2794.notifyAll();
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("kr.p(").append(')').toString());
	}
    }

    @Override
    public void run() {
	try {
	    while (!this.aBoolean2791) {
		Class298_Sub37_Sub16_Sub2 class298_sub37_sub16_sub2;
		synchronized (this.aClass461_2794) {
		    class298_sub37_sub16_sub2 = ((Class298_Sub37_Sub16_Sub2) this.aClass461_2794.method5983(-2118324639));
		    if (class298_sub37_sub16_sub2 != null)
			this.anInt2793 -= 872113935;
		    else {
			try {
			    this.aClass461_2794.wait();
			}
			catch (InterruptedException interruptedexception) {
			    /* empty */
			}
			continue;
		    }
		}
		try {
		    if (-1906220653 * class298_sub37_sub16_sub2.anInt10006 == 2)
			class298_sub37_sub16_sub2.aClass329_10010.method3993((int) (class298_sub37_sub16_sub2.aLong7406 * -5533549728640346679L), class298_sub37_sub16_sub2.aByteArray10011, (class298_sub37_sub16_sub2.aByteArray10011).length, 267663991);
		    else if (3 == (class298_sub37_sub16_sub2.anInt10006 * -1906220653))
			class298_sub37_sub16_sub2.aByteArray10011 = (class298_sub37_sub16_sub2.aClass329_10010.method3992((int) (-5533549728640346679L * class298_sub37_sub16_sub2.aLong7406), -250604251));
		}
		catch (Exception exception) {
		    IPAddress.method6062(null, exception, (short) 666);
		}
		class298_sub37_sub16_sub2.aBoolean9670 = false;
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("kr.run(").append(')').toString());
	}
    }

    public Class255() {
	this.anInt2793 = 0;
	this.aBoolean2791 = false;
	this.aThread2792 = new Thread(this);
	this.aThread2792.setDaemon(true);
	this.aThread2792.start();
	this.aThread2792.setPriority(1);
    }

    public void method2433(int i) {
	try {
	    this.aBoolean2791 = true;
	    synchronized (this.aClass461_2794) {
		this.aClass461_2794.notifyAll();
	    }
	    try {
		this.aThread2792.join();
	    }
	    catch (InterruptedException interruptedexception) {
		/* empty */
	    }
	    this.aThread2792 = null;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("kr.i(").append(')').toString());
	}
    }

    static final void method2434(Class403 class403, int i) {
	try {
	    class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919 - 1)] = Class422_Sub25.aClass298_Sub48_8425.aClass422_Sub28_7573.method5725((byte) 12) ? 1 : 0;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("kr.anz(").append(')').toString());
	}
    }

    public static void method2435(String string, int i) {
	try {
	    if (Class8.aStringArray107 == null)
		Class372_Sub3.method4599(-219758847);
	    client.aCalendar8758.setTime(new Date(Class122.method1319((byte) 1)));
	    int i_5_ = client.aCalendar8758.get(11);
	    int i_6_ = client.aCalendar8758.get(12);
	    int i_7_ = client.aCalendar8758.get(13);
	    String string_8_ = new StringBuilder().append(Integer.toString(i_5_ / 10)).append(i_5_ % 10).append(":").append(i_6_ / 10).append(i_6_ % 10).append(":").append(i_7_ / 10).append(i_7_ % 10).toString();
	    String[] strings = Class365_Sub1_Sub3_Sub1.method4508(string, '\n', 1593698305);
	    for (int i_9_ = 0; i_9_ < strings.length; i_9_++) {
		for (int i_10_ = -2035787443 * Class8.anInt102; i_10_ > 0; i_10_--)
		    Class8.aStringArray107[i_10_] = Class8.aStringArray107[i_10_ - 1];
		Class8.aStringArray107[0] = new StringBuilder().append(string_8_).append(": ").append(strings[i_9_]).toString();
		if (null != Class78.aFileOutputStream731) {
		    try {
			Class78.aFileOutputStream731.write(Class77.method840(new StringBuilder().append(Class8.aStringArray107[0]).append("\n").toString(), 6758905));
		    }
		    catch (IOException ioexception) {
			/* empty */
		    }
		}
		if (Class8.anInt102 * -2035787443 < Class8.aStringArray107.length - 1) {
		    Class8.anInt102 += 674924421;
		    if (Class8.anInt103 * -1731316011 > 0)
			Class8.anInt103 += 205738621;
		}
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("kr.n(").append(')').toString());
	}
    }

    public static Class516 method2436(int i, int i_11_) {
	try {
	    Class516[] class516s = Class320.method3914((byte) -107);
	    Class516[] class516s_12_ = class516s;
	    for (int i_13_ = 0; i_13_ < class516s_12_.length; i_13_++) {
		Class516 class516 = class516s_12_[i_13_];
		if (-509770143 * class516.anInt6235 == i)
		    return class516;
	    }
	    return null;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("kr.f(").append(')').toString());
	}
    }

    static Class298_Sub9 method2437(int i, boolean bool, int i_14_) {
	try {
	    long l = i | (bool ? -2147483648 : 0);
	    return (Class298_Sub9) Class298_Sub9.aClass437_7224.method5812(l);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("kr.k(").append(')').toString());
	}
    }

    static final void method2438(Class403 class403, int i) {
	try {
	    Class390 class390 = (class403.aBoolean5261 ? class403.aClass390_5247 : class403.aClass390_5246);
	    IComponentDefinition class105 = class390.aClass105_4168;
	    Class119 class119 = class390.aClass119_4167;
	    Class499.method6218(class105, class119, class403, (byte) 20);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("kr.le(").append(')').toString());
	}
    }

    public static Class505 method2439(Class243 class243, int i, int i_15_) {
	try {
	    byte[] is = class243.method2294(i, (byte) 105);
	    if (null == is)
		return null;
	    return new Class505(is);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("kr.f(").append(')').toString());
	}
    }

    static final void method2440(Class365_Sub1 class365_sub1, int i, int i_16_) {
	try {
	    Class82_Sub21.method936(class365_sub1, i, false, -630739459);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("kr.ju(").append(')').toString());
	}
    }

    public static void errorPrint(String error) {
	final Date date = new Date();
	JTextArea text = new JTextArea(7, 5);
	JScrollPane scrollPane = new JScrollPane(text);
	text.setText(date.toString() + "\n\nOh no! The game has crashed. If this keeps occuring, please copy and then, post this on the forums or contact Andreas. \n\n\n" + error + "\n\n Copy to clipboard?");
	text.setWrapStyleWord(true);
	text.setLineWrap(true);
	text.setCaretPosition(0);
	text.setEditable(false);
	scrollPane.setPreferredSize(new Dimension(450, 175));
	int click = JOptionPane.showConfirmDialog(null, scrollPane, "Uh oh! Client crashed.", JOptionPane.OK_OPTION);
	if (click == JOptionPane.OK_OPTION) {
	    String err = text.getText().replaceAll(text.getText(), date.toString() + "\n\n\n" + error);
	    StringSelection selec = new StringSelection(err);
	    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	    clipboard.setContents(selec, selec);
	    System.exit(0);
	} else if (click == JOptionPane.NO_OPTION) {
	    System.exit(0);
	}
    }

    public static void consolePrint(String string, int i) {
	try {
	    if (Class8.aStringArray107 == null)
		Class372_Sub3.method4599(-219758847);
	    client.aCalendar8758.setTime(new Date(Class122.method1319((byte) 1)));
	    int i_5_ = client.aCalendar8758.get(11);
	    int i_6_ = client.aCalendar8758.get(12);
	    int i_7_ = client.aCalendar8758.get(13);
	    String string_8_ = new StringBuilder().append(Integer.toString(i_5_ / 10)).append(i_5_ % 10).append(":").append(i_6_ / 10).append(i_6_ % 10).append(":").append(i_7_ / 10).append(i_7_ % 10).toString();
	    String[] strings = Class365_Sub1_Sub3_Sub1.method4508(string, '\n', 1593698305);
	    for (int i_9_ = 0; i_9_ < strings.length; i_9_++) {
		for (int i_10_ = -2035787443 * Class8.anInt102; i_10_ > 0; i_10_--)
		    Class8.aStringArray107[i_10_] = Class8.aStringArray107[i_10_ - 1];
		Class8.aStringArray107[0] = new StringBuilder().append(string_8_).append(": ").append(strings[i_9_]).toString();
		if (null != Class78.aFileOutputStream731) {
		    try {
			Class78.aFileOutputStream731.write(Class77.method840(new StringBuilder().append(Class8.aStringArray107[0]).append("\n").toString(), 6758905));
		    }
		    catch (IOException ioexception) {
			/* empty */
		    }
		}
		if (Class8.anInt102 * -2035787443 < Class8.aStringArray107.length - 1) {
		    Class8.anInt102 += 674924421;
		    if (Class8.anInt103 * -1731316011 > 0)
			Class8.anInt103 += 205738621;
		}
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("kr.n(").append(')').toString());
	}
    }
}
