/* Class84 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class84 {
    public int anInt766;
    public int anInt767;
    public int anInt768;
    public int anInt769;
    public static Class305 aClass305_770;

    Class84 method945(int i, int i_0_) {
	try {
	    return new Class84(1834782277 * anInt768, i, -1606786303 * anInt766, anInt769 * 2010148771);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("dk.a(").append(')').toString());
	}
    }

    Class84(int i, int i_1_, int i_2_, int i_3_) {
	anInt768 = 1558926477 * i;
	anInt767 = i_1_ * 2005037049;
	anInt766 = i_2_ * 655269121;
	anInt769 = -1163183093 * i_3_;
    }

    static void method946(String[] strings, short[] is, int i, int i_4_, int i_5_) {
	try {
	    if (i < i_4_) {
		int i_6_ = (i_4_ + i) / 2;
		int i_7_ = i;
		String string = strings[i_6_];
		strings[i_6_] = strings[i_4_];
		strings[i_4_] = string;
		short i_8_ = is[i_6_];
		is[i_6_] = is[i_4_];
		is[i_4_] = i_8_;
		for (int i_9_ = i; i_9_ < i_4_; i_9_++) {
		    if (null == string || (null != strings[i_9_] && strings[i_9_].compareTo(string) < (i_9_ & 0x1))) {
			String string_10_ = strings[i_9_];
			strings[i_9_] = strings[i_7_];
			strings[i_7_] = string_10_;
			short i_11_ = is[i_9_];
			is[i_9_] = is[i_7_];
			is[i_7_++] = i_11_;
		    }
		}
		strings[i_4_] = strings[i_7_];
		strings[i_7_] = string;
		is[i_4_] = is[i_7_];
		is[i_7_] = i_8_;
		method946(strings, is, i, i_7_ - 1, 2073959826);
		method946(strings, is, i_7_ + 1, i_4_, 2006941575);
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("dk.f(").append(')').toString());
	}
    }

    static final void method947(Class403 class403, int i) {
	try {
	    int i_12_ = (class403.anIntArray5244[((class403.anInt5239 -= -391880689) * 681479919)]);
	    IComponentDefinition class105 = Class50.getIComponentDefinitions(i_12_, (byte) 26);
	    Class119 class119 = Class389.aClass119Array4165[i_12_ >> 16];
	    Class283.method2677(class105, class119, class403, (byte) -11);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("dk.mw(").append(')').toString());
	}
    }

    static final void method948(Class403 class403, int i) {
	try {
	    int i_13_ = (class403.anIntArray5244[((class403.anInt5239 -= -391880689) * 681479919)]);
	    Class298_Sub36 class298_sub36 = Class18.method359(OutcommingPacket.COLOR_ID_PACKET, client.aClass25_8711.aClass449_330, (byte) 54);
	    class298_sub36.out.writeShort(i_13_, 16711935);
	    client.aClass25_8711.method390(class298_sub36, (byte) -99);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("dk.sd(").append(')').toString());
	}
    }

    static final void method949(Class403 class403, int i) {
	try {
	    int i_14_ = (class403.anIntArray5244[((class403.anInt5239 -= -391880689) * 681479919)]);
	    Class124.method1385(i_14_, (byte) 2);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("dk.ahg(").append(')').toString());
	}
    }

    static final void method950(Class403 class403, int i) {
	try {
	    Class390 class390 = (class403.aBoolean5261 ? class403.aClass390_5247 : class403.aClass390_5246);
	    IComponentDefinition class105 = class390.aClass105_4168;
	    class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919 - 1)] = class105.anInt1210 * 1508815983;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("dk.pp(").append(')').toString());
	}
    }

    static final void method951(Class403 class403, int i) {
	try {
	    IComponentDefinition class105 = (class403.aClass365_Sub1_Sub1_Sub2_5242.aClass119_10131.method1297((class403.anIntArray5244[((class403.anInt5239 -= -391880689) * 681479919)]), (short) 13294));
	    class105.aClass105Array1292 = null;
	    class105.aClass105Array1293 = null;
	    Tradution.method6054(class105, -1361442228);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("dk.apv(").append(')').toString());
	}
    }

    static int method952(int i, int i_15_, int i_16_) {
	try {
	    if (-1976050083 * GameObjectType.T1.type == i || i == -1976050083 * GameObjectType.T3.type)
		return Class365_Sub1_Sub5_Sub1.anIntArray9891[i_15_ & 0x3];
	    return Class365_Sub1_Sub5_Sub1.anIntArray9890[i_15_ & 0x3];
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("dk.by(").append(')').toString());
	}
    }
}
