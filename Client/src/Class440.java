/* Class440 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public final class Class440 {
    Class298[] aClass298Array5576;
    int anInt5577;
    long aLong5578;
    Class298 aClass298_5579;
    Class298 aClass298_5580;
    int anInt5581 = 0;
    public static Class205 aClass205_5582;

    public Class298 method5852(long l) {
	try {
	    this.aLong5578 = l * 236245195989619781L;
	    Class298 class298 = (this.aClass298Array5576[(int) (l & 1721892305 * this.anInt5577 - 1)]);
	    for (this.aClass298_5580 = class298.aClass298_3187; class298 != this.aClass298_5580; this.aClass298_5580 = this.aClass298_5580.aClass298_3187) {
		if ((this.aClass298_5580.hash * 7051297995265073167L) == l) {
		    Class298 class298_0_ = this.aClass298_5580;
		    this.aClass298_5580 = this.aClass298_5580.aClass298_3187;
		    return class298_0_;
		}
	    }
	    this.aClass298_5580 = null;
	    return null;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("se.a(").append(')').toString());
	}
    }

    public Class298 method5853(int i) {
	try {
	    if (this.aClass298_5580 == null)
		return null;
	    for (Class298 class298 = (this.aClass298Array5576[(int) ((this.aLong5578 * 4770221757987511949L) & (1721892305 * this.anInt5577) - 1)]); class298 != this.aClass298_5580; this.aClass298_5580 = this.aClass298_5580.aClass298_3187) {
		if ((this.aClass298_5580.hash * 7051297995265073167L) == 4770221757987511949L * this.aLong5578) {
		    Class298 class298_1_ = this.aClass298_5580;
		    this.aClass298_5580 = this.aClass298_5580.aClass298_3187;
		    return class298_1_;
		}
	    }
	    this.aClass298_5580 = null;
	    return null;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("se.f(").append(')').toString());
	}
    }

    public int method5854(Class298[] class298s, int i) {
	try {
	    int i_2_ = 0;
	    for (int i_3_ = 0; i_3_ < 1721892305 * this.anInt5577; i_3_++) {
		Class298 class298 = this.aClass298Array5576[i_3_];
		for (Class298 class298_4_ = class298.aClass298_3187; class298_4_ != class298; class298_4_ = class298_4_.aClass298_3187)
		    class298s[i_2_++] = class298_4_;
	    }
	    return i_2_;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("se.b(").append(')').toString());
	}
    }

    public int method5855(int i) {
	try {
	    int i_5_ = 0;
	    for (int i_6_ = 0; i_6_ < 1721892305 * this.anInt5577; i_6_++) {
		Class298 class298 = this.aClass298Array5576[i_6_];
		for (Class298 class298_7_ = class298.aClass298_3187; class298_7_ != class298; class298_7_ = class298_7_.aClass298_3187)
		    i_5_++;
	    }
	    return i_5_;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("se.p(").append(')').toString());
	}
    }

    public Class298 method5856(int i) {
	try {
	    this.anInt5581 = 0;
	    return method5857((byte) -79);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("se.k(").append(')').toString());
	}
    }

    public Class298 method5857(byte i) {
	try {
	    if (1311298783 * this.anInt5581 > 0 && (this.aClass298_5579 != (this.aClass298Array5576[this.anInt5581 * 1311298783 - 1]))) {
		Class298 class298 = this.aClass298_5579;
		this.aClass298_5579 = class298.aClass298_3187;
		return class298;
	    }
	    while_103_: do {
		Class298 class298;
		do {
		    if (1311298783 * this.anInt5581 >= 1721892305 * this.anInt5577)
			break while_103_;
		    class298 = (this.aClass298Array5576[((this.anInt5581 += 489154335) * 1311298783) - 1].aClass298_3187);
		}
		while ((this.aClass298Array5576[this.anInt5581 * 1311298783 - 1]) == class298);
		this.aClass298_5579 = class298.aClass298_3187;
		return class298;
	    }
	    while (false);
	    return null;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("se.d(").append(')').toString());
	}
    }

    public Class440(int i) {
	this.anInt5577 = -249857231 * i;
	this.aClass298Array5576 = new Class298[i];
	for (int i_8_ = 0; i_8_ < i; i_8_++) {
	    Class298 class298 = this.aClass298Array5576[i_8_] = new Class298();
	    class298.aClass298_3187 = class298;
	    class298.aClass298_3189 = class298;
	}
    }

    public void method5858(Class298 class298, long l) {
	try {
	    if (null != class298.aClass298_3189)
		class298.method2839(-1460969981);
	    Class298 class298_9_ = (this.aClass298Array5576[(int) (l & this.anInt5577 * 1721892305 - 1)]);
	    class298.aClass298_3189 = class298_9_.aClass298_3189;
	    class298.aClass298_3187 = class298_9_;
	    class298.aClass298_3189.aClass298_3187 = class298;
	    class298.aClass298_3187.aClass298_3189 = class298;
	    class298.hash = l * 4191220306876042991L;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("se.i(").append(')').toString());
	}
    }

    static final void method5859(Class403 class403, byte i) {
	try {
	    int i_10_ = (class403.anIntArray5244[((class403.anInt5239 -= -391880689) * 681479919)]);
	    Class102 class102 = Class298_Sub40_Sub13.method3517(i_10_, 689328854);
	    String string = "";
	    if (null != class102 && null != class102.aString1090)
		string = class102.aString1090;
	    class403.anObjectArray5240[((class403.anInt5241 += 969361751) * -203050393 - 1)] = string;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("se.ach(").append(')').toString());
	}
    }

    public static void method5860(Interface22 interface22, byte i) {
	try {
	    if (IcmpService_Sub1.anIcmpService_Sub1_8551 == null)
		throw new IllegalStateException("");
	    IcmpService_Sub1.anIcmpService_Sub1_8551.aList8552.add(interface22);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("se.i(").append(')').toString());
	}
    }

    public static Class428[] method5861(int i) {
	try {
	    return (new Class428[] { Class428.aClass428_6618, Class428.aClass428_6619, Class428.aClass428_6620, Class428.aClass428_6617, Class428.aClass428_6616, Class428.aClass428_6621 });
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("se.a(").append(')').toString());
	}
    }

    public static void method5862(int i, int i_11_, int i_12_) {
	try {
	    Class298_Sub37_Sub12 class298_sub37_sub12 = Class410.method4985(18, (long) i_11_ << 32 | i);
	    class298_sub37_sub12.method3445(-777100649);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("se.ao(").append(')').toString());
	}
    }
}
