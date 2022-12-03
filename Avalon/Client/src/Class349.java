/* Class349 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class349 {
    Class348 aClass348_3706 = new Class348(64);
    Class405 aClass405_3707;
    public static Class350 aClass350_3708 = new Class350();
    Class243 idx2;

    public void method4199(int i) {
	try {
	    synchronized (this.aClass348_3706) {
		this.aClass348_3706.method4187();
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("oo.f(").append(')').toString());
	}
    }

    public void method4200(int i, byte i_0_) {
	try {
	    synchronized (this.aClass348_3706) {
		this.aClass348_3706.method4186(i, 88605032);
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("oo.b(").append(')').toString());
	}
    }

    public void method4201(byte i) {
	try {
	    synchronized (this.aClass348_3706) {
		this.aClass348_3706.method4189();
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("oo.p(").append(')').toString());
	}
    }

    public Class350 method4202(int i, byte i_1_) {
	try {
	    Class350 class350;
	    synchronized (this.aClass348_3706) {
		class350 = (Class350) this.aClass348_3706.method4184(i);
	    }
	    if (class350 != null)
		return class350;
	    byte[] is;
	    synchronized (this.idx2) {
		is = (this.idx2.getFile(-1006924897 * Class120.aClass120_1437.anInt1460, i));
	    }
	    class350 = new Class350();
	    class350.aClass349_3725 = this;
	    if (null != is)
		class350.method4211(new RsByteBuffer(is), 162499609);
	    synchronized (this.aClass348_3706) {
		this.aClass348_3706.method4194(class350, i);
	    }
	    return class350;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("oo.a(").append(')').toString());
	}
    }

    public Class349(Class411 class411, Class429 class429, Class243 class243, Class405 class405) {
	this.idx2 = class243;
	this.idx2.method2316((-1006924897 * (Class120.aClass120_1437.anInt1460)), -994427694);
	this.aClass405_3707 = class405;
    }

    static final void method4203(Class403 class403, byte i) {
	try {
	    class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919 - 1)] = (class403.myClanChannel.anInt7356 * 649879491);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("oo.xp(").append(')').toString());
	}
    }

    static final void method4204(Class403 class403, byte i) {
	try {
	    Class422_Sub25.aClass298_Sub48_8425.method3540(Class422_Sub25.aClass298_Sub48_8425.aClass422_Sub29_7583, (class403.anIntArray5244[((class403.anInt5239 -= -391880689) * 681479919)]), 951880368);
	    Class3.method300(656179282);
	    client.aBoolean8666 = false;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("oo.aiy(").append(')').toString());
	}
    }

    static final void method4205(Class403 class403, short i) {
	try {
	    class403.anInt5239 -= -783761378;
	    int i_2_ = (class403.anIntArray5244[681479919 * class403.anInt5239]);
	    int i_3_ = (class403.anIntArray5244[1 + class403.anInt5239 * 681479919]);
	    if (Class452.aBoolean5642) {
		Class357.method4276(3, i_2_, i_3_, false, -1076746695);
		class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919) - 1] = null != Class231.aFrame2589 ? 1 : 0;
	    } else
		class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919) - 1] = 0;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("oo.aed(").append(')').toString());
	}
    }
}
