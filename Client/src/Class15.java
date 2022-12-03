/* Class15 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class15 {
    int[] anIntArray178;
    Runnable aRunnable179;
    static float aFloat180 = 255.0F;
    float[] aFloatArray181;
    Class_ra_Sub1 aClass_ra_Sub1_182;
    float aFloat183;
    int anInt184;
    int anInt185;
    boolean aBoolean186;
    Class387_Sub1[] aClass387_Sub1Array187;
    float[] aFloatArray188;
    Class222 aClass222_189;
    Class233 aClass233_190;
    float aFloat191;
    Class2 aClass2_192;
    float aFloat193;
    Class233 aClass233_194;
    Class233 aClass233_195;
    int anInt196;
    float[] aFloatArray197;
    float[] aFloatArray198;
    float[] aFloatArray199;
    float[] aFloatArray200;
    int[] anIntArray201;
    int[] anIntArray202;
    int[] anIntArray203;
    int[] anIntArray204;
    float aFloat205 = 0.85F;
    int[] anIntArray206;
    boolean aBoolean207;
    float aFloat208;
    float aFloat209;
    float[] aFloatArray210;
    float aFloat211;
    float aFloat212;
    int[] anIntArray213;
    int anInt214;
    int anInt215;
    boolean aBoolean216;
    Class387_Sub1[] aClass387_Sub1Array217;
    float[] aFloatArray218;
    Class222 aClass222_219;
    float aFloat220;
    float[] aFloatArray221;
    float[] aFloatArray222;
    float[] aFloatArray223;
    public static Class507 aClass507_224;

    Class15(Class_ra_Sub1 class_ra_sub1) {
	this.aFloat183 = 1.0F - this.aFloat205;
	this.anInt184 = 0;
	this.anInt185 = 0;
	this.aBoolean186 = false;
	this.anInt196 = 0;
	this.anInt215 = 0;
	this.aBoolean207 = true;
	this.aClass233_190 = new Class233();
	this.aClass222_219 = new Class222();
	this.aClass222_189 = new Class222();
	this.aClass233_194 = new Class233();
	this.aClass233_195 = new Class233();
	this.anIntArray213 = new int[Class387_Sub1.anInt7810];
	this.aFloatArray197 = new float[Class387_Sub1.anInt7810];
	this.aFloatArray188 = new float[Class387_Sub1.anInt7810];
	this.aFloatArray199 = new float[Class387_Sub1.anInt7810];
	this.aFloatArray200 = new float[Class387_Sub1.anInt7810];
	this.anIntArray201 = new int[8];
	this.anIntArray202 = new int[8];
	this.anIntArray203 = new int[8];
	this.anIntArray204 = new int[10000];
	this.anIntArray178 = new int[10000];
	this.aFloat211 = 1.0F;
	this.aFloat212 = 0.0F;
	this.aFloat191 = 1.0F;
	this.aFloatArray210 = new float[2];
	this.aClass387_Sub1Array187 = new Class387_Sub1[7];
	this.aClass387_Sub1Array217 = new Class387_Sub1[7];
	this.aFloatArray218 = new float[64];
	this.aFloatArray181 = new float[64];
	this.aFloatArray223 = new float[64];
	this.aFloatArray221 = new float[64];
	this.aFloatArray222 = new float[64];
	this.aFloatArray198 = new float[3];
	this.aClass_ra_Sub1_182 = class_ra_sub1;
	this.aClass2_192 = new Class2(class_ra_sub1, this);
	for (int i = 0; i < 7; i++) {
	    this.aClass387_Sub1Array187[i] = new Class387_Sub1(this.aClass_ra_Sub1_182);
	    this.aClass387_Sub1Array217[i] = new Class387_Sub1(this.aClass_ra_Sub1_182);
	}
	this.anIntArray206 = new int[Class387_Sub1.anInt7787];
	for (int i = 0; i < Class387_Sub1.anInt7787; i++)
	    this.anIntArray206[i] = -1;
    }

    void method347(Runnable runnable, byte i) {
	try {
	    this.aRunnable179 = runnable;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("ap.f(").append(')').toString());
	}
    }

    void method348(int i) {
	try {
	    this.aClass2_192 = new Class2(this.aClass_ra_Sub1_182, this);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("ap.a(").append(')').toString());
	}
    }

    static final void method349(Class403 class403, int i) {
	try {
	    int i_0_ = (class403.anIntArray5244[((class403.anInt5239 -= -391880689) * 681479919)]);
	    ItemDefinitions class468 = Class298_Sub32_Sub14.aClass477_9400.getItemDefinitions(i_0_);
	    if (-1673957995 * class468.anInt5755 == -1 && 809765849 * class468.anInt5709 >= 0)
		class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919) - 1] = 809765849 * class468.anInt5709;
	    else
		class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919) - 1] = i_0_;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("ap.aal(").append(')').toString());
	}
    }

    static final void method350(Class403 class403, int i) {
	try {
	    int i_1_ = (class403.anIntArray5244[((class403.anInt5239 -= -391880689) * 681479919)]);
	    class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919 - 1)] = (Class379.anIntArray4096[Class173.method1823(i_1_, (byte) 0) & 0xffff]);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("ap.zl(").append(')').toString());
	}
    }

    static final void method351(Class403 class403, int i) {
	try {
	    Class390 class390 = (class403.aBoolean5261 ? class403.aClass390_5247 : class403.aClass390_5246);
	    IComponentDefinition class105 = class390.aClass105_4168;
	    Class119 class119 = class390.aClass119_4167;
	    Class21.method365(class105, class119, false, 1, class403, 1620962996);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("ap.hp(").append(')').toString());
	}
    }
}
