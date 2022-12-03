/* Class309 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public abstract class Class309 {
    final int method3787(long l) {
	try {
	    long l_0_ = method3789(-2070209737);
	    if (l_0_ > 0L)
		IPAddress.method6060(l_0_);
	    return method3791(l);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("mv.f(").append(')').toString());
	}
    }

    abstract void method3788(int i);

    abstract long method3789(int i);

    abstract long method3790();

    abstract int method3791(long l);

    abstract void method3792();

    abstract void method3793();

    abstract long method3794(int i);

    abstract long method3795();

    abstract long method3796();

    abstract long method3797();

    Class309() {
	/* empty */
    }

    abstract void method3798();

    abstract int method3799(long l);

    static final void method3800(Class403 class403, byte i) {
	try {
	    class403.anInt5239 -= -783761378;
	    int i_1_ = (class403.anIntArray5244[681479919 * class403.anInt5239]);
	    int i_2_ = (class403.anIntArray5244[681479919 * class403.anInt5239 + 1]);
	    if (Class287.myPlayer.aClass366_10209 != null) {
		for (int i_3_ = 0; i_3_ < Class132.anIntArray1506.length; i_3_++) {
		    if (i_1_ == Class132.anIntArray1506[i_3_]) {
			Class287.myPlayer.aClass366_10209.method4538(i_3_, i_2_, Class212.aClass144_2433, -1907587275);
			return;
		    }
		}
		for (int i_4_ = 0; i_4_ < Class132.anIntArray1499.length; i_4_++) {
		    if (Class132.anIntArray1499[i_4_] == i_1_) {
			Class287.myPlayer.aClass366_10209.method4538(i_4_, i_2_, Class212.aClass144_2433, 909710113);
			break;
		    }
		}
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("mv.cj(").append(')').toString());
	}
    }

    static final void method3801(Class403 class403, int i) {
	try {
	    Class390 class390 = (class403.aBoolean5261 ? class403.aClass390_5247 : class403.aClass390_5246);
	    IComponentDefinition class105 = class390.aClass105_4168;
	    if (null == class105.aString1228)
		class403.anObjectArray5240[((class403.anInt5241 += 969361751) * -203050393) - 1] = "";
	    else
		class403.anObjectArray5240[((class403.anInt5241 += 969361751) * -203050393) - 1] = class105.aString1228;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("mv.pk(").append(')').toString());
	}
    }

    static final void method3802(IComponentDefinition class105, Class119 class119, Class403 class403, byte i) {
	try {
	    class403.anInt5239 -= -783761378;
	    class105.anInt1168 = (-1609060375 * (class403.anIntArray5244[class403.anInt5239 * 681479919]));
	    class105.anInt1169 = (-1638942269 * (class403.anIntArray5244[1 + 681479919 * class403.anInt5239]));
	    Tradution.method6054(class105, -1480345787);
	    if (0 == class105.type * -1215239439)
		Class65.method761(class119, class105, false, 112223880);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("mv.ft(").append(')').toString());
	}
    }

    static final void method3803(Class403 class403, int i) {
	try {
	    Class390 class390 = (class403.aBoolean5261 ? class403.aClass390_5247 : class403.aClass390_5246);
	    IComponentDefinition class105 = class390.aClass105_4168;
	    Class491.method6177(class105, class403, 761620533);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("mv.qf(").append(')').toString());
	}
    }

    static boolean method3804(int i) {
	try {
	    boolean bool = true;
	    if (null == Class98_Sub2.aClass89_6932) {
		if (Class158.idx8.method2310((Class410.anInt5290 * 2131310071), -457216440))
		    Class98_Sub2.aClass89_6932 = IndexedImage.method979(Class158.idx8, Class410.anInt5290 * 2131310071);
		else
		    bool = false;
	    }
	    if (Class422_Sub30.aClass89_8437 == null) {
		if (Class158.idx8.method2310(1636425877 * Class14.anInt174, -457216440))
		    Class422_Sub30.aClass89_8437 = IndexedImage.method979(Class158.idx8, 1636425877 * Class14.anInt174);
		else
		    bool = false;
	    }
	    if (null == Class385.aClass89_4142) {
		if (Class158.idx8.method2310((1475733439 * Class318.anInt3323), -457216440))
		    Class385.aClass89_4142 = IndexedImage.method979(Class158.idx8, Class318.anInt3323 * 1475733439);
		else
		    bool = false;
	    }
	    if (Class14.aClass505_176 == null) {
		if (Class173.aClass243_1758.method2310(Class14.anInt168 * -227610265, -457216440))
		    Class14.aClass505_176 = Class255.method2439(Class173.aClass243_1758, Class14.anInt168 * -227610265, -1873768956);
		else
		    bool = false;
	    }
	    if (Class14.aClass89Array169 == null) {
		if (Class158.idx8.method2310(-227610265 * Class14.anInt168, -457216440))
		    Class14.aClass89Array169 = IndexedImage.method981(Class158.idx8, -227610265 * Class14.anInt168);
		else
		    bool = false;
	    }
	    return bool;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("mv.f(").append(')').toString());
	}
    }
}
