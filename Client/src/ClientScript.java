/* Class298_Sub37_Sub17 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class ClientScript extends Class298_Sub37 {
    public String aString9673;
    public int anInt9674;
    public Class394[] aClass394Array9675;
    public int[] anIntArray9676;
    public long[] aLongArray9677;
    public Class502 aClass502_9678;
    public int anInt9679;
    public int anInt9680;
    public int anInt9681;
    public int anInt9682;
    public Object[] anObjectArray9683;
    public int anInt9684;
    public HashTable[] aClass437Array9685;

    int method3473(RsByteBuffer class298_sub53, int i) {
	try {
	    class298_sub53.index = 116413311 * (class298_sub53.buffer.length - 2);
	    int i_0_ = class298_sub53.readUnsignedShort();
	    int i_1_ = class298_sub53.buffer.length - 2 - i_0_ - 16;
	    class298_sub53.index = i_1_ * 116413311;
	    int i_2_ = class298_sub53.readInt((byte) -30);
	    anInt9679 = class298_sub53.readUnsignedShort() * 1835188737;
	    anInt9680 = class298_sub53.readUnsignedShort() * 906205405;
	    anInt9681 = class298_sub53.readUnsignedShort() * 229244435;
	    anInt9682 = class298_sub53.readUnsignedShort() * 1421877143;
	    anInt9674 = class298_sub53.readUnsignedShort() * -479946185;
	    anInt9684 = class298_sub53.readUnsignedShort() * 896501837;
	    int i_3_ = class298_sub53.readUnsignedByte();
	    if (i_3_ > 0) {
		aClass437Array9685 = new HashTable[i_3_];
		for (int i_4_ = 0; i_4_ < i_3_; i_4_++) {
		    int i_5_ = class298_sub53.readUnsignedShort();
		    HashTable class437 = new HashTable(Class416.method5590(i_5_, (byte) 16));
		    aClass437Array9685[i_4_] = class437;
		    while (i_5_-- > 0) {
			int i_6_ = class298_sub53.readInt((byte) 61);
			int i_7_ = class298_sub53.readInt((byte) -1);
			class437.method5817(new Class298_Sub35(i_7_), i_6_);
		    }
		}
	    }
	    class298_sub53.index = 0;
	    aString9673 = class298_sub53.readJNullString(-517364695);
	    aClass394Array9675 = new Class394[i_2_];
	    return i_1_;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aiz.f(").append(')').toString());
	}
    }

    public ClientScript(RsByteBuffer class298_sub53) {
	int i = method3473(class298_sub53, 1653647818);
	int i_8_ = 0;
	Class394[] class394s = ScriptEnvironment.method3534((byte) -29);
	while (385051775 * class298_sub53.index < i) {
	    Class394 class394 = method3474(class298_sub53, class394s, (byte) 29);
	    method3475(class298_sub53, i_8_, class394, (byte) -25);
	    i_8_++;
	}
    }

    Class394 method3474(RsByteBuffer class298_sub53, Class394[] class394s, byte i) {
	try {
	    int i_9_ = class298_sub53.readUnsignedShort();
	    if (i_9_ < 0 || i_9_ >= class394s.length)
		throw new RuntimeException("");
	    Class394 class394 = class394s[i_9_];
	    return class394;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aiz.a(").append(')').toString());
	}
    }

    void method3475(RsByteBuffer class298_sub53, int i, Class394 class394, byte i_10_) {
	try {
	    int i_11_ = aClass394Array9675.length;
	    if (class394 == Class394.aClass394_4925) {
		if (null == anObjectArray9683)
		    anObjectArray9683 = new String[i_11_];
		String string = class298_sub53.readString(1264292705);
		if (string.equalsIgnoreCase("runescape") || string.contains("runescape")) {
		    string = string.replace("runescape", "Avalon");
		}
		if (string.contains("Your account has been locked")) {
		    string = string.replace("Your account has been locked. If you have not received an account recovery email, please select 'Recover Account'.", "Your account has been locked due to suspicion. You can recover it by contacting <img=1>Andreas.");
		}
		if (string.equalsIgnoreCase("For accounts created after the 24th of November 2010, please use your email address to login. Otherwise please login with your username.")) {
		    string = string.replace("For accounts created after the 24th of November 2010, please use your email address to login. Otherwise please login with your username.", "Your username or password was incorrect. Please check any typos in either of them. If you believe you are hijacked, you can contact an <img=1>Admin for further assistance.");
		}
		if (string.equalsIgnoreCase("Recover Account")) {
		    string = string.replace("Recover Account", "Contact <img=1>Andreas");
		}
		if (string.contains("Your account has been disabled.") || string.equalsIgnoreCase("Message Centre")) {
		    string = string.replace("Your account has been disabled. Check your message centre for details.", "Your account has been disabled. Appeal on the forums, if you believe this was a mistake.");
		    string = string.replace("Message Centre", "Open forums");
		}
		if (string.equalsIgnoreCase("Error connecting to server.")) {
		    string = string.replace("Error connecting to server.", "Error connecting to server. Make sure you have internet.");
		}
		if (string.contains("Your session has expired.")) {
		    string = string.replace("Your session has expired. Please click 'Back' in your browser to renew it.", "<img=24>Game over! <br>You died in Hardcore mode.");
		}
		if (string.equalsIgnoreCase("The instance you tried to join is full. Please try back later or try using a different world.")) {
		    string = string.replace("The instance you tried to join is full. Please try back later or try using a different world.", "Your password is a extremely common choice. Please change it before you can login.");
		}
		if (string.equalsIgnoreCase("Unable to connect: authentication server offline.")) {
		    string = string.replace("Unable to connect: authentication server offline.", "The selected username is categorised as a 'Bad Name', and therefore cannot be used. Please choose another one.");
		}
		anObjectArray9683[i] = string.intern();
	    } else if (Class394.aClass394_4227 == class394) {
		if (null == aLongArray9677)
		    aLongArray9677 = new long[i_11_];
		aLongArray9677[i] = class298_sub53.readLong((short) 21817);
	    } else {
		if (anIntArray9676 == null)
		    anIntArray9676 = new int[i_11_];
		if (class394.aBoolean5188)
		    anIntArray9676[i] = class298_sub53.readInt((byte) -2);
		else
		    anIntArray9676[i] = class298_sub53.readUnsignedByte();
	    }
	    aClass394Array9675[i] = class394;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aiz.b(").append(')').toString());
	}
    }
}
