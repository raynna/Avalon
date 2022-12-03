
/* Class124 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.net.InetAddress;
import java.net.UnknownHostException;

import jaclib.ping.Ping;

public class Class124 implements Runnable {
    volatile long aLong1476 = -887263504067647559L;
    InetAddress anInetAddress1477;
    String aString1478;
    volatile boolean aBoolean1479 = true;
    static Class243 aClass243_1480;

    void method1381(String string, int i) {
	try {
	    this.aString1478 = string;
	    this.anInetAddress1477 = null;
	    this.aLong1476 = -887263504067647559L;
	    if (this.aString1478 != null) {
		try {
		    this.anInetAddress1477 = InetAddress.getByName(this.aString1478);
		}
		catch (UnknownHostException unknownhostexception) {
		    /* empty */
		}
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("f.a(").append(')').toString());
	}
    }

    void method1382(int i) {
	try {
	    this.aBoolean1479 = false;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("f.b(").append(')').toString());
	}
    }

    @Override
    public void run() {
	try {
	    while (this.aBoolean1479)
		method1383((byte) 52);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("f.run(").append(')').toString());
	}
    }

    void method1383(byte i) {
	try {
	    if (this.anInetAddress1477 != null) {
		try {
		    byte[] is = this.anInetAddress1477.getAddress();
		    this.aLong1476 = (Ping.a(is[0], is[1], is[2], is[3], 10000L) * 887263504067647559L);
		}
		catch (Throwable throwable) {
		    /* empty */
		}
	    }
	    IPAddress.method6060(1000L);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("f.p(").append(')').toString());
	}
    }

    long method1384(int i) {
	try {
	    return this.aLong1476 * -8472299103493205641L;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("f.f(").append(')').toString());
	}
    }

    Class124() {
	/* empty */
    }

    public static void method1385(int i, byte i_0_) {
	try {
	    if (Class400.method4931((byte) 71)) {
		if (2084404473 * Class360.anInt3873 != i)
		    Class360.aLong3874 = 2742373017286080113L;
		Class360.anInt3873 = i * 2035975497;
		Class439.method5851(3, 1633403726);
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("f.x(").append(')').toString());
	}
    }

    static void method1386(byte i) {
	try {
	    Class360.anInt3868 = -1058684408;
	    Class360.aClass25_3905 = client.aClass25_8693;
	    Class460.method5981((-1L == 122690138525332847L * Class360.aLong3874), true, "", "", Class360.aLong3874 * 122690138525332847L);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("f.g(").append(')').toString());
	}
    }

    public static byte method1387(char c, int i) {
	try {
	    byte i_1_;
	    if (c > 0 && c < '\u0080' || c >= '\u00a0' && c <= '\u00ff')
		i_1_ = (byte) c;
	    else if (c == '\u20ac')
		i_1_ = (byte) -128;
	    else if ('\u201a' == c)
		i_1_ = (byte) -126;
	    else if ('\u0192' == c)
		i_1_ = (byte) -125;
	    else if (c == '\u201e')
		i_1_ = (byte) -124;
	    else if (c == '\u2026')
		i_1_ = (byte) -123;
	    else if ('\u2020' == c)
		i_1_ = (byte) -122;
	    else if ('\u2021' == c)
		i_1_ = (byte) -121;
	    else if ('\u02c6' == c)
		i_1_ = (byte) -120;
	    else if (c == '\u2030')
		i_1_ = (byte) -119;
	    else if (c == '\u0160')
		i_1_ = (byte) -118;
	    else if ('\u2039' == c)
		i_1_ = (byte) -117;
	    else if ('\u0152' == c)
		i_1_ = (byte) -116;
	    else if ('\u017d' == c)
		i_1_ = (byte) -114;
	    else if (c == '\u2018')
		i_1_ = (byte) -111;
	    else if ('\u2019' == c)
		i_1_ = (byte) -110;
	    else if ('\u201c' == c)
		i_1_ = (byte) -109;
	    else if (c == '\u201d')
		i_1_ = (byte) -108;
	    else if ('\u2022' == c)
		i_1_ = (byte) -107;
	    else if ('\u2013' == c)
		i_1_ = (byte) -106;
	    else if ('\u2014' == c)
		i_1_ = (byte) -105;
	    else if (c == '\u02dc')
		i_1_ = (byte) -104;
	    else if (c == '\u2122')
		i_1_ = (byte) -103;
	    else if ('\u0161' == c)
		i_1_ = (byte) -102;
	    else if ('\u203a' == c)
		i_1_ = (byte) -101;
	    else if ('\u0153' == c)
		i_1_ = (byte) -100;
	    else if (c == '\u017e')
		i_1_ = (byte) -98;
	    else if (c == '\u0178')
		i_1_ = (byte) -97;
	    else
		i_1_ = (byte) 63;
	    return i_1_;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("f.a(").append(')').toString());
	}
    }

    static final void method1388(Class403 class403, int i) {
	try {
	    class403.anInt5239 -= -1175642067;
	    int i_2_ = (class403.anIntArray5244[class403.anInt5239 * 681479919]);
	    int i_3_ = (class403.anIntArray5244[class403.anInt5239 * 681479919 + 1]);
	    int i_4_ = (class403.anIntArray5244[class403.anInt5239 * 681479919 + 2]);
	    Class301_Sub1.method3713(6, i_2_ << 16 | i_3_, i_4_, "", -759655050);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("f.alw(").append(')').toString());
	}
    }

    static final void method1389(Entity class365_sub1_sub1_sub2, int i) {
	try {
	    int i_5_ = (class365_sub1_sub1_sub2.anInt10095 * -412225079 - client.ticketCycle * 443738891);
	    int i_6_ = (class365_sub1_sub1_sub2.anInt10098 * -719582720 + class365_sub1_sub1_sub2.getSize() * 256);
	    int i_7_ = (class365_sub1_sub1_sub2.anInt10100 * 1363846656 + class365_sub1_sub1_sub2.getSize() * 256);
	    Class217 class217 = class365_sub1_sub1_sub2.method4337().aClass217_2599;
	    class365_sub1_sub1_sub2.method4341((i_6_ - (int) class217.aFloat2451) / i_5_ + (int) class217.aFloat2451, (int) class217.aFloat2455, (int) class217.aFloat2454 + (i_7_ - (int) class217.aFloat2454) / i_5_);
	    class365_sub1_sub1_sub2.anInt10124 = 0;
	    class365_sub1_sub1_sub2.method4414((class365_sub1_sub1_sub2.anInt10104) * -251594591, (byte) 1);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("f.he(").append(')').toString());
	}
    }
}
