/* Class427 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class427 {
    Class348 aClass348_5360 = new Class348(64);
    Class243 aClass243_5361;
    static int cameraLookSpeed2;

    public void method5745(byte i) {
	try {
	    synchronized (this.aClass348_5360) {
		this.aClass348_5360.method4187();
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("rr.f(").append(')').toString());
	}
    }

    public Class427(Class411 class411, Class429 class429, Class243 class243) {
	this.aClass243_5361 = class243;
	if (this.aClass243_5361 != null)
	    this.aClass243_5361.method2316(Class120.aClass120_1430.anInt1460 * -1006924897, -1236940551);
    }

    public void method5746(int i, int i_0_) {
	try {
	    synchronized (this.aClass348_5360) {
		this.aClass348_5360.method4186(i, -1679910575);
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("rr.b(").append(')').toString());
	}
    }

    public void method5747(int i) {
	try {
	    synchronized (this.aClass348_5360) {
		this.aClass348_5360.method4189();
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("rr.p(").append(')').toString());
	}
    }

    public Class434 method5748(int i, int i_1_) {
	try {
	    Class434 class434;
	    synchronized (this.aClass348_5360) {
		class434 = (Class434) this.aClass348_5360.method4184(i);
	    }
	    if (null != class434)
		return class434;
	    byte[] is = (this.aClass243_5361.getFile(-1006924897 * Class120.aClass120_1430.anInt1460, i));
	    class434 = new Class434();
	    if (null != is)
		class434.method5801(new RsByteBuffer(is), 2030266422);
	    synchronized (this.aClass348_5360) {
		this.aClass348_5360.method4194(class434, i);
	    }
	    return class434;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("rr.a(").append(')').toString());
	}
    }

    static final void method5749(Class403 class403, int i) {
	try {
	    class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919 - 1)] = OutcommingPacket.anInt2099 * -815951577;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("rr.amt(").append(')').toString());
	}
    }

    static final void method5750(Class403 class403, short i) {
	try {
	    if (14 == -1233866115 * client.anInt8752)
		Class474.aBoolean5970 = (class403.anIntArray5244[(class403.anInt5239 -= -391880689) * 681479919]) == 1;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("rr.alm(").append(')').toString());
	}
    }

    static final void method5751(Class403 class403, int i) {
	try {
	    class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919 - 1)] = 647547479 * Class95.anInt924;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("rr.amj(").append(')').toString());
	}
    }
}
