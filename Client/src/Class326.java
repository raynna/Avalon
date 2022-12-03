/* Class326 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class326 {
    public Class326 aClass326_3455;
    public Class322 aClass322_3456;
    public Class365_Sub1_Sub5 aClass365_Sub1_Sub5_3457;
    public Class365_Sub1_Sub5 aClass365_Sub1_Sub5_3458;
    public Class365_Sub1_Sub3 aClass365_Sub1_Sub3_3459;
    public Class365_Sub1_Sub3 aClass365_Sub1_Sub3_3460;
    public Class365_Sub1_Sub2 aClass365_Sub1_Sub2_3461;
    Class365_Sub1_Sub4 aClass365_Sub1_Sub4_3462;
    short aShort3463;
    short aShort3464;
    short aShort3465;
    public byte aByte3466;
    short aShort3467;

    public Class326(int i) {
	aByte3466 = (byte) i;
    }

    static void method3968(int i) {
	try {
	    if (null == Class379.anIntArray4096)
		Class379.anIntArray4096 = new int[65536];
	    else
		return;
	    double d = 0.7 + (Math.random() * 0.03 - 0.015);
	    int i_0_ = 0;
	    for (int i_1_ = 0; i_1_ < 512; i_1_++) {
		float f = 360.0F * ((i_1_ >> 3) / 64.0F + 0.0078125F);
		float f_2_ = 0.0625F + (i_1_ & 0x7) / 8.0F;
		for (int i_3_ = 0; i_3_ < 128; i_3_++) {
		    float f_4_ = i_3_ / 128.0F;
		    float f_5_ = 0.0F;
		    float f_6_ = 0.0F;
		    float f_7_ = 0.0F;
		    float f_8_ = f / 60.0F;
		    int i_9_ = (int) f_8_;
		    int i_10_ = i_9_ % 6;
		    float f_11_ = f_8_ - i_9_;
		    float f_12_ = (1.0F - f_2_) * f_4_;
		    float f_13_ = (1.0F - f_11_ * f_2_) * f_4_;
		    float f_14_ = (1.0F - (1.0F - f_11_) * f_2_) * f_4_;
		    if (0 == i_10_) {
			f_5_ = f_4_;
			f_6_ = f_14_;
			f_7_ = f_12_;
		    } else if (i_10_ == 1) {
			f_5_ = f_13_;
			f_6_ = f_4_;
			f_7_ = f_12_;
		    } else if (2 == i_10_) {
			f_5_ = f_12_;
			f_6_ = f_4_;
			f_7_ = f_14_;
		    } else if (3 == i_10_) {
			f_5_ = f_12_;
			f_6_ = f_13_;
			f_7_ = f_4_;
		    } else if (4 == i_10_) {
			f_5_ = f_14_;
			f_6_ = f_12_;
			f_7_ = f_4_;
		    } else if (5 == i_10_) {
			f_5_ = f_4_;
			f_6_ = f_12_;
			f_7_ = f_13_;
		    }
		    f_5_ = (float) Math.pow(f_5_, d);
		    f_6_ = (float) Math.pow(f_6_, d);
		    f_7_ = (float) Math.pow(f_7_, d);
		    int i_15_ = (int) (256.0F * f_5_);
		    int i_16_ = (int) (f_6_ * 256.0F);
		    int i_17_ = (int) (256.0F * f_7_);
		    int i_18_ = i_17_ + ((i_15_ << 16) + -16777216 + (i_16_ << 8));
		    Class379.anIntArray4096[i_0_++] = i_18_;
		}
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("np.b(").append(')').toString());
	}
    }

    static final void method3969(Class403 class403, int i) {
	try {
	    Class390 class390 = (class403.aBoolean5261 ? class403.aClass390_5247 : class403.aClass390_5246);
	    IComponentDefinition class105 = class390.aClass105_4168;
	    Class119 class119 = class390.aClass119_4167;
	    Class75.method835(class105, class119, class403, (byte) 65);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("np.ju(").append(')').toString());
	}
    }

    static final void method3970(Class403 class403, byte i) {
	try {
	    class403.anInt5241 -= 1938723502;
	    class403.anInt5239 -= -783761378;
	    String string = (String) (class403.anObjectArray5240[class403.anInt5241 * -203050393]);
	    int i_19_ = (class403.anIntArray5244[681479919 * class403.anInt5239]);
	    int i_20_ = (class403.anIntArray5244[681479919 * class403.anInt5239 + 1]);
	    String string_21_ = ((String) (class403.anObjectArray5240[-203050393 * class403.anInt5241 + 1]));
	    Class358.method4288(string, i_19_ == 1, i_20_, string_21_, 37914209);
	    class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919 - 1)] = Class344.anInt3688 * 367592105;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("np.abb(").append(')').toString());
	}
    }

    static final void method3971(Class403 class403, short i) {
	try {
	    int i_22_ = (class403.anIntArray5244[((class403.anInt5239 -= -391880689) * 681479919)]);
	    class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919 - 1)] = Class422_Sub25.aClass298_Sub48_8425.aClass422_Sub5_7572.method5612(i_22_, 1352882135);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("np.aoa(").append(')').toString());
	}
    }

    static final void method3972(Class403 class403, byte i) {
	try {
	    int i_23_ = (class403.anIntArray5244[((class403.anInt5239 -= -391880689) * 681479919)]);
	    IComponentDefinition class105 = Class50.getIComponentDefinitions(i_23_, (byte) 24);
	    Class119 class119 = Class389.aClass119Array4165[i_23_ >> 16];
	    Class21.method365(class105, class119, true, 1, class403, 1939929714);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("np.hy(").append(')').toString());
	}
    }

    static final void method3973(Class403 class403, int i) {
	try {
	    class403.anInt5245 -= -1365138610;
	    if ((class403.aLongArray5251[1685767703 * class403.anInt5245]) == (class403.aLongArray5251[1 + class403.anInt5245 * 1685767703]))
		class403.anInt5259 += (286750741 * (class403.anIntArray5257[class403.anInt5259 * 1883543357]));
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("np.bx(").append(')').toString());
	}
    }

    static void method3974(int i) {
	try {
	    Class298_Sub36 class298_sub36 = Class18.method359(OutcommingPacket.SCREEN_PACKET, client.aClass25_8711.aClass449_330, (byte) 103);
	    class298_sub36.out.writeByte(Class190.method1859((byte) -123));
	    class298_sub36.out.writeShort(Class462.anInt5683 * -2110394505, 16711935);
	    class298_sub36.out.writeShort(Class298_Sub40_Sub9.anInt9716 * -1111710645, 16711935);
	    class298_sub36.out.writeByte(Class422_Sub25.aClass298_Sub48_8425.aClass422_Sub13_7550.method5675(-196354448));
	    client.aClass25_8711.method390(class298_sub36, (byte) -93);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("np.gp(").append(')').toString());
	}
    }
}
