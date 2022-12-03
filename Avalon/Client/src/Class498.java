/* Class498 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class498 {
    public short[] aShortArray6107;
    public int[] anIntArray6108;
    public short[] aShortArray6109;
    public long aLong6110;

    public Class498(NPCDefinitions class503, boolean bool) {
	if (bool) {
	    anIntArray6108 = new int[class503.anIntArray6129.length];
	    System.arraycopy(class503.anIntArray6129, 0, anIntArray6108, 0, anIntArray6108.length);
	} else {
	    anIntArray6108 = new int[class503.anIntArray6130.length];
	    System.arraycopy(class503.anIntArray6130, 0, anIntArray6108, 0, anIntArray6108.length);
	}
	if (null != class503.aShortArray6134) {
	    aShortArray6109 = new short[class503.aShortArray6134.length];
	    System.arraycopy(class503.aShortArray6134, 0, aShortArray6109, 0, aShortArray6109.length);
	}
	if (null != class503.aShortArray6154) {
	    aShortArray6107 = new short[class503.aShortArray6154.length];
	    System.arraycopy(class503.aShortArray6154, 0, aShortArray6107, 0, aShortArray6107.length);
	}
    }

    public Class498(long l, int[] is, short[] is_0_, short[] is_1_) {
	aLong6110 = 3177550440302969639L * l;
	anIntArray6108 = is;
	aShortArray6109 = is_0_;
	aShortArray6107 = is_1_;
    }

    public static Class177 method6209(RsByteBuffer class298_sub53, byte i) {
	try {
	    Class177 class177 = new Class177();
	    class177.anInt1787 = class298_sub53.readUnsignedShort() * -1496580327;
	    class177.aClass298_Sub37_Sub14_1788 = Class447.aClass469_5618.method6045((class177.anInt1787 * -2034569943), (short) -12386);
	    return class177;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("up.a(").append(')').toString());
	}
    }

    static final void method6210(Class403 class403, byte i) {
	try {
	    class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919 - 1)] = Class422_Sub25.aClass298_Sub48_8425.aClass422_Sub29_7587.method5726(-2147059683);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("up.akq(").append(')').toString());
	}
    }

    static final void method6211(Class403 class403, int i) {
	try {
	    class403.anInt5241 -= 1938723502;
	    String string = (String) (class403.anObjectArray5240[-203050393 * class403.anInt5241]);
	    String string_2_ = ((String) (class403.anObjectArray5240[-203050393 * class403.anInt5241 + 1]));
	    int i_3_ = (class403.anIntArray5244[((class403.anInt5239 -= -391880689) * 681479919)]);
	    class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919 - 1)] = string.indexOf(string_2_, i_3_);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("up.aay(").append(')').toString());
	}
    }
}
