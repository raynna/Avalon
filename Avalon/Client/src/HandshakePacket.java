/* Class211 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class HandshakePacket {
    static HandshakePacket aClass211_2407;
    public static HandshakePacket JAGGRAB;
    public static HandshakePacket aClass211_2409;
    static HandshakePacket[] aClass211Array2410;
    public static HandshakePacket aClass211_2411;
    static HandshakePacket aClass211_2412;
    public static HandshakePacket aClass211_2413;
    static HandshakePacket aClass211_2414;
    public static HandshakePacket ACCOUNT_CREATION;
    public static HandshakePacket aClass211_2416 = new HandshakePacket(14, 0);
    public static HandshakePacket aClass211_2417;
    public int opcode;
    public static HandshakePacket aClass211_2419;
    static Class505 aClass505_2420;

    HandshakePacket(int i, int i_0_) {
	opcode = i * 876548389;
    }

    static {
	JAGGRAB = new HandshakePacket(15, -1);
	aClass211_2409 = new HandshakePacket(16, -2);
	aClass211_2411 = new HandshakePacket(19, -2);
	aClass211_2407 = new HandshakePacket(23, 4);
	aClass211_2412 = new HandshakePacket(24, -1);
	aClass211_2419 = new HandshakePacket(26, 0);
	aClass211_2414 = new HandshakePacket(27, 0);
	ACCOUNT_CREATION = new HandshakePacket(28, -2);
	aClass211_2413 = new HandshakePacket(29, -2);
	aClass211_2417 = new HandshakePacket(30, -2);
	aClass211Array2410 = new HandshakePacket[32];
	HandshakePacket[] class211s = Class298_Sub50.method3566(-2004417351);
	for (int i = 0; i < class211s.length; i++)
	    aClass211Array2410[-1813470547 * class211s[i].opcode] = class211s[i];
    }

    static final void method1946(Class403 class403, int i) {
	try {
	    class403.anIntArray5244[class403.anInt5239 * 681479919 - 1] = (class403.myClanChannel.method3095(-1121517922)[(class403.anIntArray5244[681479919 * class403.anInt5239 - 1])]);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("ix.yt(").append(')').toString());
	}
    }

    public static void method1947(int i) {
	try {
	    if (-1233866115 * client.anInt8752 == 14 && (!Class315.method3837((byte) 46) && !Class489.method6167(-391880689)))
		Class439.method5851(18, 2092937740);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("ix.n(").append(')').toString());
	}
    }

    static final void method1948(Class403 class403, int i) {
	try {
	    int i_1_ = (class403.anIntArray5244[((class403.anInt5239 -= -391880689) * 681479919)]);
	    class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919 - 1)] = Class247.method2375((char) i_1_, -691475600) ? 1 : 0;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("ix.zg(").append(')').toString());
	}
    }

    static final void method1949(Class403 class403, byte i) {
	try {
	    if (!Class315.method3837((byte) -32))
		Class298_Sub38.method3503(-20644488);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("ix.agg(").append(')').toString());
	}
    }

    static final Class403 method1950(int i) {
	try {
	    if (597504395 * Class388.anInt4157 == Class388.anArrayList4149.size())
		Class388.anArrayList4149.add(new Class403());
	    Class403 class403 = (Class403) Class388.anArrayList4149.get(Class388.anInt4157 * 597504395);
	    Class388.anInt4157 += -1866863069;
	    return class403;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("ix.a(").append(')').toString());
	}
    }
}
