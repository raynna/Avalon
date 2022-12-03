/* Class221 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class221 implements Interface24 {
    String aString6661;
    boolean aBoolean6662;
    static int anInt6663;

    @Override
    public int method256(int i) {
	try {
	    if (this.aBoolean6662)
		return 100;
	    int i_0_ = Class84.aClass305_770.method3744(this.aString6661, 637615919);
	    if (i_0_ >= 0 && i_0_ <= 100)
		return i_0_;
	    this.aBoolean6662 = true;
	    return 100;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("jh.a(").append(')').toString());
	}
    }

    @Override
    public Class463 method260(int i) {
	try {
	    return Class463.aClass463_5685;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("jh.f(").append(')').toString());
	}
    }

    @Override
    public Class463 method261() {
	return Class463.aClass463_5685;
    }

    @Override
    public int method258() {
	if (this.aBoolean6662)
	    return 100;
	int i = Class84.aClass305_770.method3744(this.aString6661, 963304472);
	if (i >= 0 && i <= 100)
	    return i;
	this.aBoolean6662 = true;
	return 100;
    }

    @Override
    public Class463 method259() {
	return Class463.aClass463_5685;
    }

    @Override
    public Class463 method257() {
	return Class463.aClass463_5685;
    }

    Class221(String string) {
	this.aString6661 = string;
    }

    boolean method2050(int i) {
	try {
	    return this.aBoolean6662;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("jh.d(").append(')').toString());
	}
    }

    static final void method2051(Class403 class403, int i) {
	try {
	    String string = (String) (class403.anObjectArray5240[(class403.anInt5241 -= 969361751) * -203050393]);
	    if (client.playerRights * 1806357379 != 0 || ((!client.aBoolean8811 || client.aBoolean8812) && !client.aBoolean8802)) {
		String string_1_ = string.toLowerCase();
		int i_2_ = 0;
		if (string_1_.startsWith(Tradution.aClass470_5933.method6049(Class429.aClass429_6624, -875414210))) {
		    i_2_ = 0;
		    string = string.substring(Tradution.aClass470_5933.method6049(Class429.aClass429_6624, -875414210).length());
		} else if (string_1_.startsWith(Tradution.aClass470_5892.method6049(Class429.aClass429_6624, -875414210))) {
		    i_2_ = 1;
		    string = string.substring(Tradution.aClass470_5892.method6049(Class429.aClass429_6624, -875414210).length());
		} else if (string_1_.startsWith(Tradution.aClass470_5935.method6049(Class429.aClass429_6624, -875414210))) {
		    i_2_ = 2;
		    string = string.substring(Tradution.aClass470_5935.method6049(Class429.aClass429_6624, -875414210).length());
		} else if (string_1_.startsWith(Tradution.aClass470_5904.method6049(Class429.aClass429_6624, -875414210))) {
		    i_2_ = 3;
		    string = string.substring(Tradution.aClass470_5904.method6049(Class429.aClass429_6624, -875414210).length());
		} else if (string_1_.startsWith(Tradution.aClass470_5937.method6049(Class429.aClass429_6624, -875414210))) {
		    i_2_ = 4;
		    string = string.substring(Tradution.aClass470_5937.method6049(Class429.aClass429_6624, -875414210).length());
		} else if (string_1_.startsWith(Tradution.aClass470_5797.method6049(Class429.aClass429_6624, -875414210))) {
		    i_2_ = 5;
		    string = string.substring(Tradution.aClass470_5797.method6049(Class429.aClass429_6624, -875414210).length());
		} else if (string_1_.startsWith(Tradution.aClass470_5812.method6049(Class429.aClass429_6624, -875414210))) {
		    i_2_ = 6;
		    string = string.substring(Tradution.aClass470_5812.method6049(Class429.aClass429_6624, -875414210).length());
		} else if (string_1_.startsWith(Tradution.aClass470_5897.method6049(Class429.aClass429_6624, -875414210))) {
		    i_2_ = 7;
		    string = string.substring(Tradution.aClass470_5897.method6049(Class429.aClass429_6624, -875414210).length());
		} else if (string_1_.startsWith(Tradution.aClass470_5807.method6049(Class429.aClass429_6624, -875414210))) {
		    i_2_ = 8;
		    string = string.substring(Tradution.aClass470_5807.method6049(Class429.aClass429_6624, -875414210).length());
		} else if (string_1_.startsWith(Tradution.aClass470_5942.method6049(Class429.aClass429_6624, -875414210))) {
		    i_2_ = 9;
		    string = string.substring(Tradution.aClass470_5942.method6049(Class429.aClass429_6624, -875414210).length());
		} else if (string_1_.startsWith(Tradution.aClass470_5943.method6049(Class429.aClass429_6624, -875414210))) {
		    i_2_ = 10;
		    string = string.substring(Tradution.aClass470_5943.method6049(Class429.aClass429_6624, -875414210).length());
		} else if (string_1_.startsWith(Tradution.aClass470_5944.method6049(Class429.aClass429_6624, -875414210))) {
		    i_2_ = 11;
		    string = string.substring(Tradution.aClass470_5944.method6049(Class429.aClass429_6624, -875414210).length());
		} else if (Class321.aClass429_3357 != Class429.aClass429_6624) {
		    if (string_1_.startsWith(Tradution.aClass470_5933.method6049(Class321.aClass429_3357, -875414210))) {
			i_2_ = 0;
			string = string.substring(Tradution.aClass470_5933.method6049(Class321.aClass429_3357, -875414210).length());
		    } else if (string_1_.startsWith(Tradution.aClass470_5892.method6049(Class321.aClass429_3357, -875414210))) {
			i_2_ = 1;
			string = string.substring(Tradution.aClass470_5892.method6049(Class321.aClass429_3357, -875414210).length());
		    } else if (string_1_.startsWith(Tradution.aClass470_5935.method6049(Class321.aClass429_3357, -875414210))) {
			i_2_ = 2;
			string = string.substring(Tradution.aClass470_5935.method6049(Class321.aClass429_3357, -875414210).length());
		    } else if (string_1_.startsWith(Tradution.aClass470_5904.method6049(Class321.aClass429_3357, -875414210))) {
			i_2_ = 3;
			string = string.substring(Tradution.aClass470_5904.method6049(Class321.aClass429_3357, -875414210).length());
		    } else if (string_1_.startsWith(Tradution.aClass470_5937.method6049(Class321.aClass429_3357, -875414210))) {
			i_2_ = 4;
			string = string.substring(Tradution.aClass470_5937.method6049(Class321.aClass429_3357, -875414210).length());
		    } else if (string_1_.startsWith(Tradution.aClass470_5797.method6049(Class321.aClass429_3357, -875414210))) {
			i_2_ = 5;
			string = string.substring(Tradution.aClass470_5797.method6049(Class321.aClass429_3357, -875414210).length());
		    } else if (string_1_.startsWith(Tradution.aClass470_5812.method6049(Class321.aClass429_3357, -875414210))) {
			i_2_ = 6;
			string = string.substring(Tradution.aClass470_5812.method6049(Class321.aClass429_3357, -875414210).length());
		    } else if (string_1_.startsWith(Tradution.aClass470_5897.method6049(Class321.aClass429_3357, -875414210))) {
			i_2_ = 7;
			string = string.substring(Tradution.aClass470_5897.method6049(Class321.aClass429_3357, -875414210).length());
		    } else if (string_1_.startsWith(Tradution.aClass470_5807.method6049(Class321.aClass429_3357, -875414210))) {
			i_2_ = 8;
			string = string.substring(Tradution.aClass470_5807.method6049(Class321.aClass429_3357, -875414210).length());
		    } else if (string_1_.startsWith(Tradution.aClass470_5942.method6049(Class321.aClass429_3357, -875414210))) {
			i_2_ = 9;
			string = string.substring(Tradution.aClass470_5942.method6049(Class321.aClass429_3357, -875414210).length());
		    } else if (string_1_.startsWith(Tradution.aClass470_5943.method6049(Class321.aClass429_3357, -875414210))) {
			i_2_ = 10;
			string = string.substring(Tradution.aClass470_5943.method6049(Class321.aClass429_3357, -875414210).length());
		    } else if (string_1_.startsWith(Tradution.aClass470_5944.method6049(Class321.aClass429_3357, -875414210))) {
			i_2_ = 11;
			string = string.substring(Tradution.aClass470_5944.method6049(Class321.aClass429_3357, -875414210).length());
		    }
		}
		string_1_ = string.toLowerCase();
		int i_3_ = 0;
		if (string_1_.startsWith(Tradution.aClass470_5882.method6049(Class429.aClass429_6624, -875414210))) {
		    i_3_ = 1;
		    string = string.substring(Tradution.aClass470_5882.method6049(Class429.aClass429_6624, -875414210).length());
		} else if (string_1_.startsWith(Tradution.aClass470_5858.method6049(Class429.aClass429_6624, -875414210))) {
		    i_3_ = 2;
		    string = string.substring(Tradution.aClass470_5858.method6049(Class429.aClass429_6624, -875414210).length());
		} else if (string_1_.startsWith(Tradution.aClass470_5817.method6049(Class429.aClass429_6624, -875414210))) {
		    i_3_ = 3;
		    string = string.substring(Tradution.aClass470_5817.method6049(Class429.aClass429_6624, -875414210).length());
		} else if (string_1_.startsWith(Tradution.aClass470_5948.method6049(Class429.aClass429_6624, -875414210))) {
		    i_3_ = 4;
		    string = string.substring(Tradution.aClass470_5948.method6049(Class429.aClass429_6624, -875414210).length());
		} else if (string_1_.startsWith(Tradution.aClass470_5949.method6049(Class429.aClass429_6624, -875414210))) {
		    i_3_ = 5;
		    string = string.substring(Tradution.aClass470_5949.method6049(Class429.aClass429_6624, -875414210).length());
		} else if (Class429.aClass429_6624 != Class321.aClass429_3357) {
		    if (string_1_.startsWith(Tradution.aClass470_5882.method6049(Class321.aClass429_3357, -875414210))) {
			i_3_ = 1;
			string = string.substring(Tradution.aClass470_5882.method6049(Class321.aClass429_3357, -875414210).length());
		    } else if (string_1_.startsWith(Tradution.aClass470_5858.method6049(Class321.aClass429_3357, -875414210))) {
			i_3_ = 2;
			string = string.substring(Tradution.aClass470_5858.method6049(Class321.aClass429_3357, -875414210).length());
		    } else if (string_1_.startsWith(Tradution.aClass470_5817.method6049(Class321.aClass429_3357, -875414210))) {
			i_3_ = 3;
			string = string.substring(Tradution.aClass470_5817.method6049(Class321.aClass429_3357, -875414210).length());
		    } else if (string_1_.startsWith(Tradution.aClass470_5948.method6049(Class321.aClass429_3357, -875414210))) {
			i_3_ = 4;
			string = string.substring(Tradution.aClass470_5948.method6049(Class321.aClass429_3357, -875414210).length());
		    } else if (string_1_.startsWith(Tradution.aClass470_5949.method6049(Class321.aClass429_3357, -875414210))) {
			i_3_ = 5;
			string = string.substring(Tradution.aClass470_5949.method6049(Class321.aClass429_3357, -875414210).length());
		    }
		}
		Class25 class25 = Class429.method5760((short) 512);
		Class298_Sub36 class298_sub36 = Class18.method359(OutcommingPacket.CHAT_PACKET, class25.aClass449_330, (byte) 107);
		class298_sub36.out.writeByte(0);
		int i_4_ = (385051775 * class298_sub36.out.index);
		class298_sub36.out.writeByte(i_2_);
		class298_sub36.out.writeByte(i_3_);
		Class23.method379(class298_sub36.out, string, 1526854691);
		class298_sub36.out.method3649((class298_sub36.out.index * 385051775) - i_4_, (byte) -57);
		class25.method390(class298_sub36, (byte) -12);
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("jh.abt(").append(')').toString());
	}
    }

    public static Class145 method2052(RsByteBuffer class298_sub53, int i) {
	try {
	    int i_5_ = class298_sub53.readInt((byte) -17);
	    return new Class145(i_5_);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("jh.a(").append(')').toString());
	}
    }

    public static void method2053(int i) {
	try {
	    if (Class93.method1014(-81173133))
		Class440.method5860(new Class382(), (byte) 0);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("jh.a(").append(')').toString());
	}
    }
}
