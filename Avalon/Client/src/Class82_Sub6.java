
/* Class82_Sub6 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.util.Arrays;

public class Class82_Sub6 extends Class82 {
    int anInt6838;
    int anInt6839;
    int anInt6840;
    int[] anIntArray6841;
    static int anInt6842;
    static Class227 aClass227_6843;
    public static Class243 aClass243_6844;
    static String aString6845;
    public static Class7[] aClass7Array6846;

    @Override
    public void method866(int i) {
	try {
	    Entity class365_sub1_sub1_sub2 = Class87.aClass94Array794[this.anInt6840 * -2094920785].method1015(1714980357);
	    if (0 == 237701933 * this.anInt6838)
		Class431.method5768(class365_sub1_sub1_sub2, this.anIntArray6841, 0, false, (byte) 11);
	    else
		Class298_Sub37_Sub7.method3428(class365_sub1_sub1_sub2, (new int[] { this.anInt6839 * 2090514815 }), new int[] { 0 }, new int[] { this.anInt6838 * 237701933 }, 1644064563);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("xm.f(").append(')').toString());
	}
    }

    @Override
    boolean method870(int i) {
	try {
	    Class391 class391 = Class501.aClass395_6122.method4903((2090514815 * (this.anInt6839)), (byte) 111);
	    return class391.method4880(1778758091);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("xm.p(").append(')').toString());
	}
    }

    @Override
    public void method869() {
	Entity class365_sub1_sub1_sub2 = Class87.aClass94Array794[this.anInt6840 * -2094920785].method1015(318010293);
	if (0 == 237701933 * this.anInt6838)
	    Class431.method5768(class365_sub1_sub1_sub2, this.anIntArray6841, 0, false, (byte) -52);
	else
	    Class298_Sub37_Sub7.method3428(class365_sub1_sub1_sub2, new int[] { this.anInt6839 * 2090514815 }, new int[] { 0 }, new int[] { this.anInt6838 * 237701933 }, 1644064563);
    }

    @Override
    public void method868() {
	Entity class365_sub1_sub1_sub2 = Class87.aClass94Array794[this.anInt6840 * -2094920785].method1015(1968398843);
	if (0 == 237701933 * this.anInt6838)
	    Class431.method5768(class365_sub1_sub1_sub2, this.anIntArray6841, 0, false, (byte) -20);
	else
	    Class298_Sub37_Sub7.method3428(class365_sub1_sub1_sub2, new int[] { this.anInt6839 * 2090514815 }, new int[] { 0 }, new int[] { this.anInt6838 * 237701933 }, 1644064563);
    }

    boolean method884() {
	Class391 class391 = Class501.aClass395_6122.method4903(2090514815 * this.anInt6839, (byte) 25);
	return class391.method4880(524582595);
    }

    Class82_Sub6(RsByteBuffer class298_sub53) {
	super(class298_sub53);
	this.anInt6840 = class298_sub53.readUnsignedShort() * 838347599;
	this.anIntArray6841 = new int[Class522.method6325((byte) -63).length];
	this.anInt6839 = class298_sub53.readBigSmart(1235052657) * 1100678783;
	Arrays.fill(this.anIntArray6841, 0, this.anIntArray6841.length, this.anInt6839 * 2090514815);
	this.anInt6838 = class298_sub53.readInt((byte) 72) * -1086817115;
    }

    public static Class298_Sub36 method885(int i) {
	try {
	    Class298_Sub36 class298_sub36 = Class500.method6220((byte) -12);
	    class298_sub36.aClass198_7401 = null;
	    class298_sub36.anInt7397 = 0;
	    class298_sub36.out = new RsBitsBuffer(5000);
	    return class298_sub36;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("xm.b(").append(')').toString());
	}
    }

    public static int method886(int i, int i_0_, boolean bool, int i_1_) {
	try {
	    Class298_Sub9 class298_sub9 = Class255.method2437(i, bool, 692761742);
	    if (null == class298_sub9)
		return -1;
	    if (i_0_ < 0 || i_0_ >= (class298_sub9.anIntArray7226).length)
		return -1;
	    return class298_sub9.anIntArray7226[i_0_];
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("xm.a(").append(')').toString());
	}
    }

    static final void method887(Class403 class403, byte i) {
	try {
	    class403.anInt5239 -= -783761378;
	    int i_2_ = (class403.anIntArray5244[681479919 * class403.anInt5239]);
	    int i_3_ = (class403.anIntArray5244[1 + class403.anInt5239 * 681479919]);
	    class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919 - 1)] = i_2_ | 1 << i_3_;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("xm.yj(").append(')').toString());
	}
    }

    static final void method888(String string, byte i) {
	try {
	    System.out.println(new StringBuilder().append("Error: ").append(Class298_Sub31.method3115(string, "%0a", "\n", -2025331206)).toString());
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("xm.p(").append(')').toString());
	}
    }
}
