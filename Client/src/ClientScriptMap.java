
/* Class483 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.applet.Applet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ClientScriptMap {
    int anInt6036;
    public char aChar6037;
    int anInt6038;
    HashMap aHashMap6039;
    Map aMap6040;
    Object[] anObjectArray6041;
    public char aChar6042;
    String aString6043 = "null";
    public static Applet anApplet6044;

    public String method6122(int i, byte i_0_) {
	try {
	    Object object = method6126(i, (short) 5996);
	    if (object == null)
		return this.aString6043;
	    return (String) object;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("tx.p(").append(')').toString());
	}
    }

    void method6123(RsByteBuffer class298_sub53, int i) {
	try {
	    for (;;) {
		int i_1_ = class298_sub53.readUnsignedByte();
		if (0 == i_1_) {
		    if (i >= 1635270652) {
			/* empty */
		    }
		    break;
		}
		method6124(class298_sub53, i_1_, 950174382);
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("tx.a(").append(')').toString());
	}
    }

    void method6124(RsByteBuffer class298_sub53, int i, int i_2_) {
	try {
	    if (1 == i)
		aChar6042 = Class493.method6190(class298_sub53.readByte(-12558881), 2015897871);
	    else if (i == 2)
		aChar6037 = Class493.method6190(class298_sub53.readByte(-12558881), 1877656812);
	    else if (3 == i)
		this.aString6043 = class298_sub53.readString(1756453424);
	    else if (4 == i)
		this.anInt6038 = class298_sub53.readInt((byte) 94) * -1961153765;
	    else if (i == 5 || i == 6) {
		this.anInt6036 = class298_sub53.readUnsignedShort() * -1158380671;
		this.aMap6040 = new HashMap(-1179140991 * this.anInt6036);
		for (int i_3_ = 0; i_3_ < this.anInt6036 * -1179140991; i_3_++) {
		    int i_4_ = class298_sub53.readInt((byte) -59);
		    java.io.Serializable serializable;
		    if (i == 5)
			serializable = class298_sub53.readString(-968722088);
		    else
			serializable = new Integer(class298_sub53.readInt((byte) -67));
		    this.aMap6040.put(new Integer(i_4_), serializable);
		}
	    } else if (7 == i || 8 == i) {
		int i_5_ = class298_sub53.readUnsignedShort();
		this.anInt6036 = class298_sub53.readUnsignedShort() * -1158380671;
		this.anObjectArray6041 = new Object[i_5_];
		for (int i_6_ = 0; i_6_ < -1179140991 * this.anInt6036; i_6_++) {
		    int i_7_ = class298_sub53.readUnsignedShort();
		    if (i == 7)
			this.anObjectArray6041[i_7_] = class298_sub53.readString(1730348772);
		    else
			this.anObjectArray6041[i_7_] = new Integer(class298_sub53.readInt((byte) -14));
		}
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("tx.f(").append(')').toString());
	}
    }

    public int method6125(int i, int i_8_) {
	try {
	    Object object = method6126(i, (short) 31709);
	    if (null == object)
		return this.anInt6038 * -1363462381;
	    return ((Integer) object).intValue();
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("tx.b(").append(')').toString());
	}
    }

    Object method6126(int i, short i_9_) {
	try {
	    if (this.anObjectArray6041 != null) {
		if (i < 0 || i >= this.anObjectArray6041.length)
		    return null;
		return this.anObjectArray6041[i];
	    }
	    if (null != this.aMap6040)
		return this.aMap6040.get(new Integer(i));
	    return null;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("tx.i(").append(')').toString());
	}
    }

    public boolean method6127(Object object, byte i) {
	try {
	    if (this.anInt6036 * -1179140991 == 0)
		return false;
	    if (this.aHashMap6039 == null)
		method6130(671224629);
	    return this.aHashMap6039.containsKey(object);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("tx.k(").append(')').toString());
	}
    }

    public int[] method6128(Object object, short i) {
	try {
	    if (0 == this.anInt6036 * -1179140991)
		return null;
	    if (this.aHashMap6039 == null)
		method6130(817562642);
	    return (int[]) this.aHashMap6039.get(object);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("tx.d(").append(')').toString());
	}
    }

    public int method6129(byte i) {
	try {
	    return this.anInt6036 * -1179140991;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("tx.u(").append(')').toString());
	}
    }

    ClientScriptMap() {
	this.anInt6036 = 0;
    }

    void method6130(int i) {
	try {
	    HashMap hashmap = new HashMap();
	    if (null != this.anObjectArray6041) {
		for (int i_10_ = 0; i_10_ < this.anObjectArray6041.length; i_10_++) {
		    if (null == this.anObjectArray6041[i_10_]) {
			if (i <= 49152) {
			    /* empty */
			}
		    } else {
			Object object = this.anObjectArray6041[i_10_];
			List list = (List) hashmap.get(object);
			if (null == list) {
			    list = new LinkedList();
			    hashmap.put(object, list);
			}
			list.add(new Integer(i_10_));
		    }
		}
	    } else if (null != this.aMap6040) {
		Iterator iterator = this.aMap6040.entrySet().iterator();
		while (iterator.hasNext()) {
		    Map.Entry entry = (Map.Entry) iterator.next();
		    Object object = entry.getValue();
		    List list = (List) hashmap.get(object);
		    if (list == null) {
			list = new LinkedList();
			hashmap.put(object, list);
		    }
		    list.add(entry.getKey());
		}
	    } else
		throw new IllegalStateException();
	    this.aHashMap6039 = new HashMap();
	    Iterator iterator = hashmap.entrySet().iterator();
	    while (iterator.hasNext()) {
		Map.Entry entry = (Map.Entry) iterator.next();
		List list = (List) entry.getValue();
		int[] is = new int[list.size()];
		int i_11_ = 0;
		Iterator iterator_12_ = list.iterator();
		while (iterator_12_.hasNext()) {
		    Integer integer = (Integer) iterator_12_.next();
		    is[i_11_++] = integer.intValue();
		}
		this.aHashMap6039.put(entry.getKey(), is);
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("tx.x(").append(')').toString());
	}
    }

    public static String method6131(byte i) {
	try {
	    String string = "www";
	    if (Class401.aClass401_6555 == Class242.aClass401_2708)
		string = "www-wtrc";
	    else if (Class242.aClass401_2708 == Class401.aClass401_6554)
		string = "www-wtqa";
	    else if (Class401.aClass401_6560 == Class242.aClass401_2708)
		string = "www-wtwip";
	    else if (Class401.aClass401_6553 == Class242.aClass401_2708)
		string = "www-wti";
	    String string_13_ = "";
	    if (client.aString8927 != null)
		string_13_ = new StringBuilder().append("/p=").append(client.aString8927).toString();
	    String string_14_ = new StringBuilder().append(client.gametype.aString5317).append(".com").toString();
	    return new StringBuilder().append("http://").append(string).append(".").append(string_14_).append("/l=").append(Class321.aClass429_3357).append("/a=").append(client.anInt8665 * -1154804873).append(string_13_).append("/").toString();
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("tx.nn(").append(')').toString());
	}
    }

    static void method6132(int i, int i_15_, int i_16_, byte i_17_) {
	try {
	    Class298_Sub37_Sub12 class298_sub37_sub12 = Class410.method4985(11, i);
	    class298_sub37_sub12.method3449((byte) 23);
	    class298_sub37_sub12.type = 1274450087 * i_15_;
	    class298_sub37_sub12.value = i_16_ * 293101103;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("tx.aj(").append(')').toString());
	}
    }
}
