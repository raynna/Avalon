/* Class525 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class525 {
    static int anInt6295 = 0;
    static int anInt6296 = 3;
    static int anInt6297 = 0;
    static AccountCreationStage accountCreationStage;
    static Class412 aClass412_6299;
    public static boolean aBoolean6300;

    Class525() throws Throwable {
	throw new Error();
    }

    static final void method6378(Class403 class403, int i) {
	try {
	    Class390 class390 = (class403.aBoolean5261 ? class403.aClass390_5247 : class403.aClass390_5246);
	    IComponentDefinition class105 = class390.aClass105_4168;
	    class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919 - 1)] = class105.anInt1248 * -1523987341;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("z.pa(").append(')').toString());
	}
    }

    public static int method6379(CharSequence charsequence, byte i) {
	try {
	    int i_0_ = charsequence.length();
	    int i_1_ = 0;
	    for (int i_2_ = 0; i_2_ < i_0_; i_2_++)
		i_1_ = (i_1_ << 5) - i_1_ + charsequence.charAt(i_2_);
	    return i_1_;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("z.q(").append(')').toString());
	}
    }

    static final void method6380(Class403 class403, int i) {
	try {
	    int i_3_ = (class403.anIntArray5244[((class403.anInt5239 -= -391880689) * 681479919)]);
	    class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919 - 1)] = Class422_Sub25.aClass298_Sub48_8425.aClass422_Sub18_7561.method5612(i_3_, 1352882135);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("z.aoe(").append(')').toString());
	}
    }
}
