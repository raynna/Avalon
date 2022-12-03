/* Class98_Sub2 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class98_Sub2 extends Class98 {
    int anInt6927;
    static int anInt6928 = 0;
    int anInt6929;
    int anInt6930;
    int anInt6931;
    static IndexedImage aClass89_6932;

    @Override
    void method1051(int i, int i_0_) {
	/* empty */
    }

    @Override
    void method1054(int i, int i_1_, byte i_2_) {
	try {
	    int i_3_ = i * (this.anInt6930 * -737606811) >> 12;
	    int i_4_ = i * (this.anInt6929 * 627591597) >> 12;
	    int i_5_ = i_1_ * (870171747 * this.anInt6927) >> 12;
	    int i_6_ = i_1_ * (this.anInt6931 * 1837883371) >> 12;
	    Class264_Sub1.method2503(i_3_, i_5_, i_4_, i_6_, (1785836763 * this.anInt947), -479015291);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("yh.a(").append(')').toString());
	}
    }

    @Override
    void method1047(int i, int i_7_, byte i_8_) {
	try {
	    /* empty */
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("yh.f(").append(')').toString());
	}
    }

    @Override
    void method1046(int i, int i_9_) {
	/* empty */
    }

    @Override
    void method1048(int i, int i_10_, byte i_11_) {
	try {
	    /* empty */
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("yh.b(").append(')').toString());
	}
    }

    @Override
    void method1049(int i, int i_12_) {
	int i_13_ = i * (this.anInt6930 * -737606811) >> 12;
	int i_14_ = i * (this.anInt6929 * 627591597) >> 12;
	int i_15_ = i_12_ * (870171747 * this.anInt6927) >> 12;
	int i_16_ = i_12_ * (this.anInt6931 * 1837883371) >> 12;
	Class264_Sub1.method2503(i_13_, i_15_, i_14_, i_16_, 1785836763 * this.anInt947, -1924061875);
    }

    Class98_Sub2(int i, int i_17_, int i_18_, int i_19_, int i_20_, int i_21_) {
	super(-1, i_20_, i_21_);
	this.anInt6930 = 388331117 * i;
	this.anInt6927 = i_17_ * -1813324469;
	this.anInt6929 = i_18_ * -2080243163;
	this.anInt6931 = 1437683907 * i_19_;
    }

    @Override
    void method1052(int i, int i_22_) {
	/* empty */
    }

    @Override
    void method1050(int i, int i_23_) {
	/* empty */
    }

    @Override
    void method1053(int i, int i_24_) {
	/* empty */
    }

    static Class298_Sub34 method1060(Class243 class243, int i, int i_25_) {
	try {
	    byte[] is = class243.method2294(i, (byte) 120);
	    if (is == null)
		return null;
	    return new Class298_Sub34(is);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("yh.a(").append(')').toString());
	}
    }

    static void method1061(int i) {
	try {
	    /* empty */
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("yh.y(").append(')').toString());
	}
    }

    static final void method1062(Class403 class403, int i) {
	try {
	    int i_26_ = (class403.anIntArray5244[((class403.anInt5239 -= -391880689) * 681479919)]);
	    Class25 class25 = Class429.method5760((short) 512);
	    Class298_Sub36 class298_sub36 = Class18.method359(OutcommingPacket.CHAT_TYPE_PACKET, class25.aClass449_330, (byte) 56);
	    class298_sub36.out.writeByte(i_26_);
	    class25.method390(class298_sub36, (byte) -69);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("yh.abr(").append(')').toString());
	}
    }

    static final void method1063(Class403 class403, byte i) {
	try {
	    String string = (String) (class403.anObjectArray5240[(class403.anInt5241 -= 969361751) * -203050393]);
	    if (null != Class112.aString1369 && Class112.aString1369.equalsIgnoreCase(string))
		class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919) - 1] = 1;
	    else
		class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919) - 1] = 0;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("yh.vu(").append(')').toString());
	}
    }

    public static void method1064(Class502 class502, int i, int i_27_, Class400 class400, Class365_Sub1_Sub4_Sub1 class365_sub1_sub4_sub1, int i_28_) {
	try {
	    Class403 class403 = HandshakePacket.method1950(1868919888);
	    class403.aClass365_Sub1_Sub4_Sub1_5253 = class365_sub1_sub4_sub1;
	    SubIncommingPacket.method1925(class502, i, i_27_, class403, 536465062);
	    class403.aClass365_Sub1_Sub4_Sub1_5253 = null;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("yh.x(").append(')').toString());
	}
    }

    static void method1065(byte i) {
	try {
	    for (Class298_Sub37_Sub5 class298_sub37_sub5 = ((Class298_Sub37_Sub5) Class436.aClass461_5482.method5984(-431286975)); null != class298_sub37_sub5; class298_sub37_sub5 = ((Class298_Sub37_Sub5) Class436.aClass461_5482.method5985(-2120194897))) {
		if ((-628325139 * class298_sub37_sub5.anInt9583) > 1) {
		    class298_sub37_sub5.anInt9583 = 0;
		    Class436.aClass348_5464.method4194(class298_sub37_sub5, (6619564980435866523L * ((Class298_Sub37_Sub15) (class298_sub37_sub5.aClass461_9584.aClass298_Sub37_5679.aClass298_Sub37_7405)).aLong9666));
		    class298_sub37_sub5.aClass461_9584.method5988(2132471610);
		}
	    }
	    Class436.anInt5479 = 0;
	    Class436.anInt5506 = 0;
	    Class436.aClass453_5480.method5943((byte) 1);
	    Class436.aClass437_5470.method5811((byte) -19);
	    Class436.aClass461_5482.method5988(-116656816);
	    Class282.method2618(Class436.aClass298_Sub37_Sub15_5466, 1921095656);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("yh.ax(").append(')').toString());
	}
    }

    static final void method1066(Class403 class403, byte i) {
	try {
	    class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919 - 1)] = 2119035647 * Class216.anInt6659;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("yh.amq(").append(')').toString());
	}
    }
}
