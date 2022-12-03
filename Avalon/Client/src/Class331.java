
/* Class331 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.util.Iterator;

public class Class331 {
    Class365_Sub1[] aClass365_Sub1Array3503;
    public int anInt3504;
    float[] aFloatArray3505;
    int anInt3506 = 16;
    int anInt3507;
    GraphicsToolkit aClass_ra3508;
    Class480 aClass480_3509;
    boolean aBoolean3510;
    boolean aBoolean3511;
    public Class333 aClass333_3512;
    int anInt3513;
    public int anInt3514;
    Class365_Sub1[] aClass365_Sub1Array3515;
    public Class326[][][] aClass326ArrayArrayArray3516;
    public Class_xa[] aClass_xaArray3517;
    public Class326[][][] aClass326ArrayArrayArray3518;
    public Class_xa[] aClass_xaArray3519;
    public Class326[][][] aClass326ArrayArrayArray3520;
    public Class_xa[] aClass_xaArray3521;
    int[][] anIntArrayArray3522;
    short[][] aShortArrayArray3523;
    byte[][] aByteArrayArray3524;
    byte[][] aByteArrayArray3525;
    byte[][] aByteArrayArray3526;
    byte[][] aByteArrayArray3527;
    public Class324 aClass324_3528;
    Class298_Sub10[] aClass298_Sub10Array3529;
    int anInt3530 = 5029;
    int anInt3531 = 10093;
    Class365_Sub1[] aClass365_Sub1Array3532;
    int anInt3533;
    Class365_Sub1[] aClass365_Sub1Array3534;
    int anInt3535;
    int anInt3536;
    int anInt3537 = 5043;
    int anInt3538;
    Class365_Sub1_Sub1[] aClass365_Sub1_Sub1Array3539;
    public static boolean aBoolean3540 = true;
    int anInt3541 = 0;
    int anInt3542;
    int anInt3543;
    int anInt3544;
    int anInt3545;
    int anInt3546;
    int anInt3547;
    public int anInt3548;
    public int anInt3549;
    int anInt3550;
    int anInt3551;
    int anInt3552;
    int anInt3553;
    long[][][] aLongArrayArrayArray3554;
    int anInt3555 = 65391;
    int anInt3556;
    Class365_Sub1[] aClass365_Sub1Array3557;
    Class321[] aClass321Array3558;
    boolean[] aBooleanArray3559;
    int anInt3560;
    boolean[][] aBooleanArrayArray3561;
    int[] anIntArray3562;
    boolean[][] aBooleanArrayArray3563;
    boolean[][] aBooleanArrayArray3564;
    static int anInt3565;

    public void method4007(int i, byte i_0_) {
	try {
	    /* empty */
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.a(").append(')').toString());
	}
    }

    public void method4008(Class264 class264, int i) {
	try {
	    /* empty */
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.f(").append(')').toString());
	}
    }

    public Class365_Sub1_Sub2 method4009(int i, int i_1_, int i_2_, int i_3_) {
	try {
	    Class326 class326 = aClass326ArrayArrayArray3516[i][i_1_][i_2_];
	    if (class326 == null)
		return null;
	    method4030(class326.aClass365_Sub1_Sub2_3461, -144193556);
	    if (class326.aClass365_Sub1_Sub2_3461 != null) {
		Class365_Sub1_Sub2 class365_sub1_sub2 = class326.aClass365_Sub1_Sub2_3461;
		class326.aClass365_Sub1_Sub2_3461 = null;
		return class365_sub1_sub2;
	    }
	    return null;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.ax(").append(')').toString());
	}
    }

    public void method4010(int i) {
	try {
	    for (int i_4_ = 0; i_4_ < 1204219039 * this.anInt3541; i_4_++) {
		Class365_Sub1_Sub1 class365_sub1_sub1 = this.aClass365_Sub1_Sub1Array3539[i_4_];
		method4029(class365_sub1_sub1, true, 2052556984);
		this.aClass365_Sub1_Sub1Array3539[i_4_] = null;
	    }
	    this.anInt3541 = 0;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.m(").append(')').toString());
	}
    }

    public void method4011(int i, int i_5_, int i_6_) {
	try {
	    Class326 class326 = aClass326ArrayArrayArray3516[0][i][i_5_];
	    for (int i_7_ = 0; i_7_ < 3; i_7_++) {
		Class326 class326_8_ = (aClass326ArrayArrayArray3516[i_7_][i][i_5_] = aClass326ArrayArrayArray3516[i_7_ + 1][i][i_5_]);
		if (null != class326_8_) {
		    for (Class322 class322 = class326_8_.aClass322_3456; class322 != null; class322 = class322.aClass322_3360) {
			Class365_Sub1_Sub1 class365_sub1_sub1 = class322.aClass365_Sub1_Sub1_3359;
			if (i == class365_sub1_sub1.aShort9798 && class365_sub1_sub1.aShort9795 == i_5_)
			    class365_sub1_sub1.plane--;
		    }
		    if (null != class326_8_.aClass365_Sub1_Sub2_3461)
			class326_8_.aClass365_Sub1_Sub2_3461.plane--;
		    if (null != class326_8_.aClass365_Sub1_Sub5_3457)
			class326_8_.aClass365_Sub1_Sub5_3457.plane--;
		    if (class326_8_.aClass365_Sub1_Sub5_3458 != null)
			class326_8_.aClass365_Sub1_Sub5_3458.plane--;
		    if (null != class326_8_.aClass365_Sub1_Sub3_3459)
			class326_8_.aClass365_Sub1_Sub3_3459.plane--;
		    if (null != class326_8_.aClass365_Sub1_Sub3_3460)
			class326_8_.aClass365_Sub1_Sub3_3460.plane--;
		}
	    }
	    if (null == aClass326ArrayArrayArray3516[0][i][i_5_]) {
		aClass326ArrayArrayArray3516[0][i][i_5_] = new Class326(0);
		aClass326ArrayArrayArray3516[0][i][i_5_].aByte3466 = (byte) 1;
	    }
	    aClass326ArrayArrayArray3516[0][i][i_5_].aClass326_3455 = class326;
	    aClass326ArrayArrayArray3516[3][i][i_5_] = null;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.i(").append(')').toString());
	}
    }

    Class326 method4012(int i, int i_9_, int i_10_, int i_11_) {
	try {
	    if (null == aClass326ArrayArrayArray3516[i][i_9_][i_10_]) {
		boolean bool = (aClass326ArrayArrayArray3516[0][i_9_][i_10_] != null && null != (aClass326ArrayArrayArray3516[0][i_9_][i_10_].aClass326_3455));
		if (bool && i >= 1678382205 * anInt3548 - 1)
		    return null;
		method4013(i, i_9_, i_10_, 127761391);
	    }
	    return aClass326ArrayArrayArray3516[i][i_9_][i_10_];
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.k(").append(')').toString());
	}
    }

    public void method4013(int i, int i_12_, int i_13_, int i_14_) {
	try {
	    boolean bool = (aClass326ArrayArrayArray3516[0][i_12_][i_13_] != null && (aClass326ArrayArrayArray3516[0][i_12_][i_13_].aClass326_3455) != null);
	    for (int i_15_ = i; i_15_ >= 0; i_15_--) {
		if (aClass326ArrayArrayArray3516[i_15_][i_12_][i_13_] == null) {
		    Class326 class326 = (aClass326ArrayArrayArray3516[i_15_][i_12_][i_13_] = new Class326(i_15_));
		    if (bool)
			class326.aByte3466++;
		}
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.d(").append(')').toString());
	}
    }

    public int method4014(int i, int i_16_, byte i_17_) {
	try {
	    return (null != this.aShortArrayArray3523 ? this.aShortArrayArray3523[i][i_16_] & 0xffff : 0);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.x(").append(')').toString());
	}
    }

    public int method4015(int i, int i_18_, byte i_19_) {
	try {
	    return (null != this.anIntArrayArray3522 ? (this.anIntArrayArray3522[i][i_18_] & 0xffffff) : 0);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.r(").append(')').toString());
	}
    }

    public int method4016(int i, int i_20_, byte i_21_) {
	try {
	    return (this.aByteArrayArray3524 != null ? this.aByteArrayArray3524[i][i_20_] & 0xff : 0);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.q(").append(')').toString());
	}
    }

    public int method4017(int i, int i_22_, byte i_23_) {
	try {
	    return (this.aByteArrayArray3525 != null ? this.aByteArrayArray3525[i][i_22_] & 0xff : 0);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.n(").append(')').toString());
	}
    }

    public int method4018(int i, int i_24_, int i_25_) {
	try {
	    return (null != this.aByteArrayArray3527 ? this.aByteArrayArray3527[i][i_24_] & 0xff : 0);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.z(").append(')').toString());
	}
    }

    public void method4019(int i, int i_26_, int i_27_, int i_28_, int i_29_, int i_30_, int i_31_, int i_32_, byte i_33_) {
	try {
	    if (null != this.anIntArrayArray3522)
		this.anIntArrayArray3522[i][i_26_] = ~0xffffff | i_27_;
	    if (this.aShortArrayArray3523 != null)
		this.aShortArrayArray3523[i][i_26_] = (short) i_28_;
	    if (null != this.aByteArrayArray3524)
		this.aByteArrayArray3524[i][i_26_] = (byte) i_29_;
	    if (this.aByteArrayArray3525 != null)
		this.aByteArrayArray3525[i][i_26_] = (byte) i_30_;
	    if (this.aByteArrayArray3526 != null)
		this.aByteArrayArray3526[i][i_26_] = (byte) i_31_;
	    if (this.aByteArrayArray3527 != null)
		this.aByteArrayArray3527[i][i_26_] = (byte) i_32_;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.y(").append(')').toString());
	}
    }

    public void method4020(int i, int i_34_, int i_35_, Class365_Sub1_Sub5 class365_sub1_sub5, Class365_Sub1_Sub5 class365_sub1_sub5_36_, byte i_37_) {
	try {
	    Class326 class326 = method4012(i, i_34_, i_35_, 1985350813);
	    if (null != class326) {
		class326.aClass365_Sub1_Sub5_3457 = class365_sub1_sub5;
		class326.aClass365_Sub1_Sub5_3458 = class365_sub1_sub5_36_;
		int i_38_ = aClass_xaArray3517 == aClass_xaArray3521 ? 1 : 0;
		if (class365_sub1_sub5.method4399((byte) 13)) {
		    if (class365_sub1_sub5.method4376((short) 255)) {
			class365_sub1_sub5.aClass365_Sub1_7721 = this.aClass365_Sub1Array3557[i_38_];
			this.aClass365_Sub1Array3557[i_38_] = class365_sub1_sub5;
		    } else {
			class365_sub1_sub5.aClass365_Sub1_7721 = this.aClass365_Sub1Array3532[i_38_];
			this.aClass365_Sub1Array3532[i_38_] = class365_sub1_sub5;
		    }
		} else {
		    class365_sub1_sub5.aClass365_Sub1_7721 = this.aClass365_Sub1Array3534[i_38_];
		    this.aClass365_Sub1Array3534[i_38_] = class365_sub1_sub5;
		}
		if (class365_sub1_sub5_36_ != null) {
		    if (class365_sub1_sub5_36_.method4399((byte) 13)) {
			if (class365_sub1_sub5_36_.method4376((short) 255)) {
			    class365_sub1_sub5_36_.aClass365_Sub1_7721 = (this.aClass365_Sub1Array3557[i_38_]);
			    this.aClass365_Sub1Array3557[i_38_] = class365_sub1_sub5_36_;
			} else {
			    class365_sub1_sub5_36_.aClass365_Sub1_7721 = (this.aClass365_Sub1Array3532[i_38_]);
			    this.aClass365_Sub1Array3532[i_38_] = class365_sub1_sub5_36_;
			}
		    } else {
			class365_sub1_sub5_36_.aClass365_Sub1_7721 = this.aClass365_Sub1Array3534[i_38_];
			this.aClass365_Sub1Array3534[i_38_] = class365_sub1_sub5_36_;
		    }
		}
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.v(").append(')').toString());
	}
    }

    public void method4021(int i, int i_39_, int i_40_, Class365_Sub1_Sub3 class365_sub1_sub3, Class365_Sub1_Sub3 class365_sub1_sub3_41_, int i_42_) {
	try {
	    Class326 class326 = method4012(i, i_39_, i_40_, 937547322);
	    if (class326 != null) {
		class326.aClass365_Sub1_Sub3_3459 = class365_sub1_sub3;
		class326.aClass365_Sub1_Sub3_3460 = class365_sub1_sub3_41_;
		int i_43_ = aClass_xaArray3517 == aClass_xaArray3521 ? 1 : 0;
		if (class365_sub1_sub3.method4399((byte) 13)) {
		    if (class365_sub1_sub3.method4376((short) 255)) {
			class365_sub1_sub3.aClass365_Sub1_7721 = this.aClass365_Sub1Array3557[i_43_];
			this.aClass365_Sub1Array3557[i_43_] = class365_sub1_sub3;
		    } else {
			class365_sub1_sub3.aClass365_Sub1_7721 = this.aClass365_Sub1Array3532[i_43_];
			this.aClass365_Sub1Array3532[i_43_] = class365_sub1_sub3;
		    }
		} else {
		    class365_sub1_sub3.aClass365_Sub1_7721 = this.aClass365_Sub1Array3534[i_43_];
		    this.aClass365_Sub1Array3534[i_43_] = class365_sub1_sub3;
		}
		if (null != class365_sub1_sub3_41_) {
		    if (class365_sub1_sub3_41_.method4399((byte) 13)) {
			if (class365_sub1_sub3_41_.method4376((short) 255)) {
			    class365_sub1_sub3_41_.aClass365_Sub1_7721 = (this.aClass365_Sub1Array3557[i_43_]);
			    this.aClass365_Sub1Array3557[i_43_] = class365_sub1_sub3_41_;
			} else {
			    class365_sub1_sub3_41_.aClass365_Sub1_7721 = (this.aClass365_Sub1Array3532[i_43_]);
			    this.aClass365_Sub1Array3532[i_43_] = class365_sub1_sub3_41_;
			}
		    } else {
			class365_sub1_sub3_41_.aClass365_Sub1_7721 = this.aClass365_Sub1Array3534[i_43_];
			this.aClass365_Sub1Array3534[i_43_] = class365_sub1_sub3_41_;
		    }
		}
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.g(").append(')').toString());
	}
    }

    public boolean method4022(Class365_Sub1_Sub1 class365_sub1_sub1, boolean bool, byte i) {
	try {
	    boolean bool_44_ = aClass_xaArray3517 == aClass_xaArray3521;
	    int i_45_ = 0;
	    short i_46_ = 0;
	    byte i_47_ = 0;
	    class365_sub1_sub1.method4404(449273804);
	    if (class365_sub1_sub1.aShort9798 < 0 || class365_sub1_sub1.aShort9795 < 0 || class365_sub1_sub1.aShort9796 >= 1988988347 * anInt3514 || class365_sub1_sub1.aShort9797 >= anInt3549 * 1340714547)
		return false;
	    short i_48_ = 0;
	    for (int i_49_ = class365_sub1_sub1.aShort9798; i_49_ <= class365_sub1_sub1.aShort9796; i_49_++) {
		for (int i_50_ = class365_sub1_sub1.aShort9795; i_50_ <= class365_sub1_sub1.aShort9797; i_50_++) {
		    Class326 class326 = method4012(class365_sub1_sub1.plane, i_49_, i_50_, 960411819);
		    if (class326 != null) {
			Class322 class322 = Class238.method2199(class365_sub1_sub1, (byte) 47);
			Class322 class322_51_ = class326.aClass322_3456;
			if (null == class322_51_)
			    class326.aClass322_3456 = class322;
			else {
			    for (/**/; class322_51_.aClass322_3360 != null; class322_51_ = class322_51_.aClass322_3360) {
				/* empty */
			    }
			    class322_51_.aClass322_3360 = class322;
			}
			if (bool_44_ && 0 != ((this.anIntArrayArray3522[i_49_][i_50_]) & ~0xffffff)) {
			    i_45_ = (this.anIntArrayArray3522[i_49_][i_50_]);
			    i_46_ = (this.aShortArrayArray3523[i_49_][i_50_]);
			    i_47_ = (this.aByteArrayArray3524[i_49_][i_50_]);
			}
			if (!bool && null != class326.aClass365_Sub1_Sub2_3461 && (class326.aClass365_Sub1_Sub2_3461.aShort9801 > i_48_))
			    i_48_ = class326.aClass365_Sub1_Sub2_3461.aShort9801;
		    }
		}
	    }
	    if (bool_44_ && 0 != (i_45_ & ~0xffffff)) {
		for (int i_52_ = class365_sub1_sub1.aShort9798; i_52_ <= class365_sub1_sub1.aShort9796; i_52_++) {
		    for (int i_53_ = class365_sub1_sub1.aShort9795; i_53_ <= class365_sub1_sub1.aShort9797; i_53_++) {
			if (((this.anIntArrayArray3522[i_52_][i_53_]) & ~0xffffff) == 0) {
			    this.anIntArrayArray3522[i_52_][i_53_] = i_45_;
			    this.aShortArrayArray3523[i_52_][i_53_] = i_46_;
			    this.aByteArrayArray3524[i_52_][i_53_] = i_47_;
			}
		    }
		}
	    }
	    if (bool) {
		this.aClass365_Sub1_Sub1Array3539[((this.anInt3541 += -1037881505) * 1204219039 - 1)] = class365_sub1_sub1;
		class365_sub1_sub1.aClass331_7722 = this;
	    } else {
		int i_54_ = aClass_xaArray3517 == aClass_xaArray3521 ? 1 : 0;
		if (class365_sub1_sub1.method4399((byte) 13)) {
		    if (class365_sub1_sub1.method4376((short) 255)) {
			class365_sub1_sub1.aClass365_Sub1_7721 = this.aClass365_Sub1Array3557[i_54_];
			this.aClass365_Sub1Array3557[i_54_] = class365_sub1_sub1;
		    } else {
			class365_sub1_sub1.aClass365_Sub1_7721 = this.aClass365_Sub1Array3532[i_54_];
			this.aClass365_Sub1Array3532[i_54_] = class365_sub1_sub1;
		    }
		} else {
		    class365_sub1_sub1.aClass365_Sub1_7721 = this.aClass365_Sub1Array3534[i_54_];
		    this.aClass365_Sub1Array3534[i_54_] = class365_sub1_sub1;
		}
	    }
	    if (bool) {
		Class217 class217 = Class217.method2005(class365_sub1_sub1.method4337().aClass217_2599);
		class217.aFloat2455 -= i_48_;
		class365_sub1_sub1.method4340(class217);
		class217.method2006();
	    }
	    return true;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.e(").append(')').toString());
	}
    }

    public void method4023(int i, int i_55_, int i_56_, int i_57_, byte i_58_) {
	try {
	    Class326 class326 = aClass326ArrayArrayArray3516[i][i_55_][i_56_];
	    if (null != class326) {
		Class365_Sub1_Sub3 class365_sub1_sub3 = class326.aClass365_Sub1_Sub3_3459;
		Class365_Sub1_Sub3 class365_sub1_sub3_59_ = class326.aClass365_Sub1_Sub3_3460;
		if (null != class365_sub1_sub3) {
		    class365_sub1_sub3.aShort9802 = (short) (i_57_ * class365_sub1_sub3.aShort9802 / (16 << anInt3504 * -1688804109 - 7));
		    class365_sub1_sub3.aShort9803 = (short) (class365_sub1_sub3.aShort9803 * i_57_ / (16 << -1688804109 * anInt3504 - 7));
		}
		if (class365_sub1_sub3_59_ != null) {
		    class365_sub1_sub3_59_.aShort9802 = (short) (i_57_ * class365_sub1_sub3_59_.aShort9802 / (16 << anInt3504 * -1688804109 - 7));
		    class365_sub1_sub3_59_.aShort9803 = (short) (class365_sub1_sub3_59_.aShort9803 * i_57_ / (16 << anInt3504 * -1688804109 - 7));
		}
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.c(").append(')').toString());
	}
    }

    public void method4024(byte i) {
	try {
	    method4048(1, anInt3548 * 1678382205, 722872945);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.am(").append(')').toString());
	}
    }

    public Class365_Sub1_Sub5 method4025(int i, int i_60_, int i_61_, int i_62_) {
	try {
	    Class326 class326 = aClass326ArrayArrayArray3516[i][i_60_][i_61_];
	    if (class326 != null) {
		method4030(class326.aClass365_Sub1_Sub5_3457, -452884210);
		if (class326.aClass365_Sub1_Sub5_3457 != null) {
		    Class365_Sub1_Sub5 class365_sub1_sub5 = class326.aClass365_Sub1_Sub5_3457;
		    class326.aClass365_Sub1_Sub5_3457 = null;
		    return class365_sub1_sub5;
		}
	    }
	    return null;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.w(").append(')').toString());
	}
    }

    public Class365_Sub1_Sub5 method4026(int i, int i_63_, int i_64_, byte i_65_) {
	try {
	    Class326 class326 = aClass326ArrayArrayArray3516[i][i_63_][i_64_];
	    if (null != class326) {
		method4030(class326.aClass365_Sub1_Sub5_3458, -123141942);
		if (null != class326.aClass365_Sub1_Sub5_3458) {
		    Class365_Sub1_Sub5 class365_sub1_sub5 = class326.aClass365_Sub1_Sub5_3458;
		    class326.aClass365_Sub1_Sub5_3458 = null;
		    return class365_sub1_sub5;
		}
	    }
	    return null;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.j(").append(')').toString());
	}
    }

    public Class365_Sub1_Sub3 method4027(int i, int i_66_, int i_67_, int i_68_) {
	try {
	    Class326 class326 = aClass326ArrayArrayArray3516[i][i_66_][i_67_];
	    if (class326 != null) {
		method4030(class326.aClass365_Sub1_Sub3_3460, -531623342);
		if (null != class326.aClass365_Sub1_Sub3_3460) {
		    Class365_Sub1_Sub3 class365_sub1_sub3 = class326.aClass365_Sub1_Sub3_3460;
		    class326.aClass365_Sub1_Sub3_3460 = null;
		    return class365_sub1_sub3;
		}
	    }
	    return null;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.l(").append(')').toString());
	}
    }

    public Class331(GraphicsToolkit class_ra, int i, int i_69_, int i_70_, int i_71_, int i_72_, boolean bool, boolean bool_73_) {
	this.anInt3531 = 10093;
	this.anInt3530 = 5029;
	this.anInt3537 = 5043;
	this.aFloatArray3505 = new float[3];
	this.aClass298_Sub10Array3529 = new Class298_Sub10[8];
	this.anInt3555 = 65391;
	this.aClass_ra3508 = class_ra;
	this.aBoolean3510 = this.aClass_ra3508.method5048() > 0;
	anInt3504 = i * -1524575173;
	this.anInt3536 = (1 << anInt3504 * -1688804109) * 732171711;
	this.anInt3546 = -413418327 * (this.anInt3536 * 1828905535 >> 1);
	anInt3548 = i_69_ * 1634141397;
	anInt3514 = 1005334387 * i_70_;
	anInt3549 = -1342636805 * i_71_;
	this.anInt3538 = i_72_ * 1571198643;
	this.aClass480_3509 = new Class480();
	aClass333_3512 = new Class333(this);
	aClass326ArrayArrayArray3518 = (new Class326[i_69_][1988988347 * anInt3514][1340714547 * anInt3549]);
	aClass_xaArray3519 = new Class_xa[i_69_];
	if (bool) {
	    this.anIntArrayArray3522 = new int[1988988347 * anInt3514][1340714547 * anInt3549];
	    this.aByteArrayArray3524 = new byte[1988988347 * anInt3514][1340714547 * anInt3549];
	    this.aShortArrayArray3523 = new short[anInt3514 * 1988988347][1340714547 * anInt3549];
	    this.aByteArrayArray3525 = new byte[1988988347 * anInt3514][1340714547 * anInt3549];
	    this.aByteArrayArray3526 = new byte[anInt3514 * 1988988347][anInt3549 * 1340714547];
	    this.aByteArrayArray3527 = new byte[anInt3514 * 1988988347][1340714547 * anInt3549];
	    aClass326ArrayArrayArray3520 = (new Class326[1][anInt3514 * 1988988347][anInt3549 * 1340714547]);
	    aClass_xaArray3521 = new Class_xa[1];
	}
	if (bool_73_) {
	    this.aLongArrayArrayArray3554 = new long[i_69_][i_70_][i_71_];
	    this.aClass321Array3558 = new Class321[65391];
	    this.aBooleanArray3559 = new boolean[65391];
	    this.anInt3556 = 0;
	}
	method4052(false, -740850409);
	this.aClass365_Sub1Array3532 = new Class365_Sub1[2];
	this.aClass365_Sub1Array3557 = new Class365_Sub1[2];
	this.aClass365_Sub1Array3534 = new Class365_Sub1[2];
	this.aClass365_Sub1Array3515 = new Class365_Sub1[10093];
	this.anInt3535 = 0;
	this.aClass365_Sub1Array3503 = new Class365_Sub1[5029];
	this.anInt3560 = 0;
	this.aClass365_Sub1_Sub1Array3539 = new Class365_Sub1_Sub1[5043];
	this.anInt3541 = 0;
	this.aBooleanArrayArray3563 = (new boolean[(this.anInt3538 * 583010427 + this.anInt3538 * 583010427 + 1)][(this.anInt3538 * 583010427 + this.anInt3538 * 583010427 + 1)]);
	this.aBooleanArrayArray3561 = (new boolean[2 + (583010427 * this.anInt3538 + this.anInt3538 * 583010427)][(583010427 * this.anInt3538 + 583010427 * this.anInt3538 + 2)]);
	this.anIntArray3562 = new int[2 + (583010427 * this.anInt3538 + this.anInt3538 * 583010427)];
	aClass324_3528 = new Class324(false);
    }

    public Class365_Sub1_Sub1 method4028(int i, int i_74_, int i_75_, Interface17 interface17, short i_76_) {
	try {
	    Class326 class326 = aClass326ArrayArrayArray3516[i][i_74_][i_75_];
	    if (null == class326)
		return null;
	    for (Class322 class322 = class326.aClass322_3456; class322 != null; class322 = class322.aClass322_3360) {
		Class365_Sub1_Sub1 class365_sub1_sub1 = class322.aClass365_Sub1_Sub1_3359;
		if ((interface17 == null || interface17.method229(class365_sub1_sub1, -98426520)) && class365_sub1_sub1.aShort9798 == i_74_ && class365_sub1_sub1.aShort9795 == i_75_) {
		    method4029(class365_sub1_sub1, false, 2114979879);
		    return class365_sub1_sub1;
		}
	    }
	    return null;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.ak(").append(')').toString());
	}
    }

    void method4029(Class365_Sub1_Sub1 class365_sub1_sub1, boolean bool, int i) {
	try {
	    for (int i_77_ = class365_sub1_sub1.aShort9798; i_77_ <= class365_sub1_sub1.aShort9796; i_77_++) {
		for (int i_78_ = class365_sub1_sub1.aShort9795; i_78_ <= class365_sub1_sub1.aShort9797; i_78_++) {
		    Class326 class326 = (aClass326ArrayArrayArray3516[class365_sub1_sub1.plane][i_77_][i_78_]);
		    if (class326 != null) {
			Class322 class322 = class326.aClass322_3456;
			Class322 class322_79_ = null;
			for (/**/; class322 != null; class322 = class322.aClass322_3360) {
			    if (class365_sub1_sub1 == class322.aClass365_Sub1_Sub1_3359) {
				if (null != class322_79_)
				    class322_79_.aClass322_3360 = class322.aClass322_3360;
				else
				    class326.aClass322_3456 = class322.aClass322_3360;
				class322.method3929((byte) -98);
				break;
			    }
			    class322_79_ = class322;
			}
		    }
		}
	    }
	    if (!bool)
		method4030(class365_sub1_sub1, -615451520);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.ae(").append(')').toString());
	}
    }

    void method4030(Class365_Sub1 class365_sub1, int i) {
	try {
	    if (class365_sub1 != null) {
		class365_sub1.method4345();
		for (int i_80_ = 0; i_80_ < 2; i_80_++) {
		    Class365_Sub1 class365_sub1_81_ = null;
		    for (Class365_Sub1 class365_sub1_82_ = (this.aClass365_Sub1Array3532[i_80_]); null != class365_sub1_82_; class365_sub1_82_ = (class365_sub1_82_.aClass365_Sub1_7721)) {
			if (class365_sub1_82_ == class365_sub1) {
			    if (null != class365_sub1_81_)
				class365_sub1_81_.aClass365_Sub1_7721 = (class365_sub1_82_.aClass365_Sub1_7721);
			    else
				this.aClass365_Sub1Array3532[i_80_] = (class365_sub1_82_.aClass365_Sub1_7721);
			    return;
			}
			class365_sub1_81_ = class365_sub1_82_;
		    }
		    class365_sub1_81_ = null;
		    for (Class365_Sub1 class365_sub1_83_ = (this.aClass365_Sub1Array3557[i_80_]); class365_sub1_83_ != null; class365_sub1_83_ = (class365_sub1_83_.aClass365_Sub1_7721)) {
			if (class365_sub1_83_ == class365_sub1) {
			    if (null != class365_sub1_81_)
				class365_sub1_81_.aClass365_Sub1_7721 = (class365_sub1_83_.aClass365_Sub1_7721);
			    else
				this.aClass365_Sub1Array3557[i_80_] = (class365_sub1_83_.aClass365_Sub1_7721);
			    return;
			}
			class365_sub1_81_ = class365_sub1_83_;
		    }
		    class365_sub1_81_ = null;
		    for (Class365_Sub1 class365_sub1_84_ = (this.aClass365_Sub1Array3534[i_80_]); null != class365_sub1_84_; class365_sub1_84_ = (class365_sub1_84_.aClass365_Sub1_7721)) {
			if (class365_sub1 == class365_sub1_84_) {
			    if (null != class365_sub1_81_)
				class365_sub1_81_.aClass365_Sub1_7721 = (class365_sub1_84_.aClass365_Sub1_7721);
			    else
				this.aClass365_Sub1Array3534[i_80_] = (class365_sub1_84_.aClass365_Sub1_7721);
			    return;
			}
			class365_sub1_81_ = class365_sub1_84_;
		    }
		}
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.ao(").append(')').toString());
	}
    }

    public Class365_Sub1_Sub5 method4031(int i, int i_85_, int i_86_, int i_87_) {
	try {
	    Class326 class326 = aClass326ArrayArrayArray3516[i][i_85_][i_86_];
	    if (class326 == null)
		return null;
	    return class326.aClass365_Sub1_Sub5_3457;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.ad(").append(')').toString());
	}
    }

    public Class365_Sub1_Sub5 method4032(int i, int i_88_, int i_89_, byte i_90_) {
	try {
	    Class326 class326 = aClass326ArrayArrayArray3516[i][i_88_][i_89_];
	    if (null == class326)
		return null;
	    return class326.aClass365_Sub1_Sub5_3458;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.av(").append(')').toString());
	}
    }

    public Class365_Sub1_Sub3 method4033(int i, int i_91_, int i_92_, int i_93_) {
	try {
	    Class326 class326 = aClass326ArrayArrayArray3516[i][i_91_][i_92_];
	    if (null == class326)
		return null;
	    return class326.aClass365_Sub1_Sub3_3459;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.at(").append(')').toString());
	}
    }

    public Class365_Sub1_Sub4 method4034(int i, int i_94_, int i_95_, int i_96_) {
	try {
	    Class326 class326 = aClass326ArrayArrayArray3516[i][i_94_][i_95_];
	    if (class326 == null)
		return null;
	    return class326.aClass365_Sub1_Sub4_3462;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.ah(").append(')').toString());
	}
    }

    public Class365_Sub1_Sub1 method4035(int i, int i_97_, int i_98_, Interface17 interface17, int i_99_) {
	try {
	    Class326 class326 = aClass326ArrayArrayArray3516[i][i_97_][i_98_];
	    if (class326 == null)
		return null;
	    for (Class322 class322 = class326.aClass322_3456; class322 != null; class322 = class322.aClass322_3360) {
		Class365_Sub1_Sub1 class365_sub1_sub1 = class322.aClass365_Sub1_Sub1_3359;
		if ((null == interface17 || interface17.method229(class365_sub1_sub1, 240755990)) && class365_sub1_sub1.aShort9798 == i_97_ && class365_sub1_sub1.aShort9795 == i_98_)
		    return class365_sub1_sub1;
	    }
	    return null;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.ai(").append(')').toString());
	}
    }

    public Class322 method4036(int i, int i_100_, int i_101_, int i_102_) {
	try {
	    Class326 class326 = aClass326ArrayArrayArray3516[i][i_100_][i_101_];
	    if (class326 == null)
		return null;
	    return class326.aClass322_3456;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.al(").append(')').toString());
	}
    }

    public void method4037() {
	for (int i = 0; i < 1678382205 * anInt3548; i++) {
	    for (int i_103_ = 0; i_103_ < anInt3514 * 1988988347; i_103_++) {
		for (int i_104_ = 0; i_104_ < 1340714547 * anInt3549; i_104_++) {
		    Class326 class326 = aClass326ArrayArrayArray3516[i][i_103_][i_104_];
		    if (null != class326) {
			Class365_Sub1_Sub5 class365_sub1_sub5 = class326.aClass365_Sub1_Sub5_3457;
			Class365_Sub1_Sub5 class365_sub1_sub5_105_ = class326.aClass365_Sub1_Sub5_3458;
			if (null != class365_sub1_sub5 && class365_sub1_sub5.method4366(1673017934)) {
			    method4039(class365_sub1_sub5, i, i_103_, i_104_, 1, 1);
			    if (class365_sub1_sub5_105_ != null && class365_sub1_sub5_105_.method4366(1884072683)) {
				method4039(class365_sub1_sub5_105_, i, i_103_, i_104_, 1, 1);
				class365_sub1_sub5_105_.method4355(this.aClass_ra3508, class365_sub1_sub5, 0, 0, 0, false, 1471147933);
				class365_sub1_sub5_105_.method4398((byte) 115);
			    }
			    class365_sub1_sub5.method4398((byte) 125);
			}
			for (Class322 class322 = class326.aClass322_3456; class322 != null; class322 = class322.aClass322_3360) {
			    Class365_Sub1_Sub1 class365_sub1_sub1 = class322.aClass365_Sub1_Sub1_3359;
			    if (class365_sub1_sub1 != null && class365_sub1_sub1.method4366(1908249416)) {
				method4039(class365_sub1_sub1, i, i_103_, i_104_, 1 + (class365_sub1_sub1.aShort9796 - (class365_sub1_sub1.aShort9798)), (class365_sub1_sub1.aShort9797 - class365_sub1_sub1.aShort9795 + 1));
				class365_sub1_sub1.method4398((byte) 61);
			    }
			}
			Class365_Sub1_Sub2 class365_sub1_sub2 = class326.aClass365_Sub1_Sub2_3461;
			if (null != class365_sub1_sub2 && class365_sub1_sub2.method4366(1396337054)) {
			    method4038(class365_sub1_sub2, i, i_103_, i_104_, 795291520);
			    class365_sub1_sub2.method4398((byte) 19);
			}
		    }
		}
	    }
	}
    }

    void method4038(Class365_Sub1 class365_sub1, int i, int i_106_, int i_107_, int i_108_) {
	try {
	    if (i_106_ < anInt3514 * 1988988347) {
		Class326 class326 = aClass326ArrayArrayArray3516[i][i_106_ + 1][i_107_];
		if (class326 != null && class326.aClass365_Sub1_Sub2_3461 != null && class326.aClass365_Sub1_Sub2_3461.method4366(1670858079))
		    class365_sub1.method4355(this.aClass_ra3508, class326.aClass365_Sub1_Sub2_3461, (1828905535 * this.anInt3536), 0, 0, true, 1480517000);
	    }
	    if (i_107_ < 1988988347 * anInt3514) {
		Class326 class326 = aClass326ArrayArrayArray3516[i][i_106_][i_107_ + 1];
		if (class326 != null && null != class326.aClass365_Sub1_Sub2_3461 && class326.aClass365_Sub1_Sub2_3461.method4366(1930175079))
		    class365_sub1.method4355(this.aClass_ra3508, class326.aClass365_Sub1_Sub2_3461, 0, 0, (1828905535 * this.anInt3536), true, 1828441739);
	    }
	    if (i_106_ < 1988988347 * anInt3514 && i_107_ < anInt3549 * 1340714547) {
		Class326 class326 = aClass326ArrayArrayArray3516[i][1 + i_106_][i_107_ + 1];
		if (null != class326 && null != class326.aClass365_Sub1_Sub2_3461 && class326.aClass365_Sub1_Sub2_3461.method4366(2111976416))
		    class365_sub1.method4355(this.aClass_ra3508, class326.aClass365_Sub1_Sub2_3461, (this.anInt3536 * 1828905535), 0, (this.anInt3536 * 1828905535), true, 1058354287);
	    }
	    if (i_106_ < 1988988347 * anInt3514 && i_107_ > 0) {
		Class326 class326 = aClass326ArrayArrayArray3516[i][i_106_ + 1][i_107_ - 1];
		if (null != class326 && class326.aClass365_Sub1_Sub2_3461 != null && class326.aClass365_Sub1_Sub2_3461.method4366(1781377638))
		    class365_sub1.method4355(this.aClass_ra3508, class326.aClass365_Sub1_Sub2_3461, (this.anInt3536 * 1828905535), 0, -(1828905535 * this.anInt3536), true, 151502999);
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.ap(").append(')').toString());
	}
    }

    void method4039(Class365_Sub1 class365_sub1, int i, int i_109_, int i_110_, int i_111_, int i_112_) {
	boolean bool = true;
	int i_113_ = i_109_;
	int i_114_ = i_111_ + i_109_;
	int i_115_ = i_110_ - 1;
	int i_116_ = i_110_ + i_112_;
	for (int i_117_ = i; i_117_ <= i + 1; i_117_++) {
	    if (anInt3548 * 1678382205 != i_117_) {
		for (int i_118_ = i_113_; i_118_ <= i_114_; i_118_++) {
		    if (i_118_ >= 0 && i_118_ < anInt3514 * 1988988347) {
			for (int i_119_ = i_115_; i_119_ <= i_116_; i_119_++) {
			    if (i_119_ >= 0 && i_119_ < 1340714547 * anInt3549 && (!bool || i_118_ >= i_114_ || i_119_ >= i_116_ || i_119_ < i_110_ && i_118_ != i_109_)) {
				Class326 class326 = (aClass326ArrayArrayArray3516[i_117_][i_118_][i_119_]);
				if (null != class326) {
				    int i_120_ = (((aClass_xaArray3517[i_117_].method6341(i_118_, i_119_, (byte) -40)) + (aClass_xaArray3517[i_117_].method6341(1 + i_118_, i_119_, (byte) -92)) + (aClass_xaArray3517[i_117_].method6341(i_118_, 1 + i_119_, (byte) -41)) + (aClass_xaArray3517[i_117_].method6341(i_118_ + 1, i_119_ + 1, (byte) -106))) / 4 - ((aClass_xaArray3517[i].method6341(i_109_, i_110_, (byte) 0)) + (aClass_xaArray3517[i].method6341(i_109_ + 1, i_110_, (byte) -79)) + (aClass_xaArray3517[i].method6341(i_109_, 1 + i_110_, (byte) -78)) + (aClass_xaArray3517[i].method6341(1 + i_109_, 1 + i_110_, (byte) -80))) / 4);
				    Class365_Sub1_Sub5 class365_sub1_sub5 = class326.aClass365_Sub1_Sub5_3457;
				    Class365_Sub1_Sub5 class365_sub1_sub5_121_ = class326.aClass365_Sub1_Sub5_3458;
				    if (class365_sub1_sub5 != null && class365_sub1_sub5.method4366(2051783389))
					class365_sub1.method4355(this.aClass_ra3508, class365_sub1_sub5, ((this.anInt3546 * 394962841 * (1 - i_111_)) + (1828905535 * this.anInt3536 * (i_118_ - i_109_))), i_120_, (((i_119_ - i_110_) * (1828905535 * (this.anInt3536))) + (this.anInt3546 * 394962841 * (1 - i_112_))), bool, 1604469537);
				    if (class365_sub1_sub5_121_ != null && class365_sub1_sub5_121_.method4366(1486894505))
					class365_sub1.method4355(this.aClass_ra3508, class365_sub1_sub5_121_, ((this.anInt3536 * 1828905535 * (i_118_ - i_109_)) + (this.anInt3546 * 394962841 * (1 - i_111_))), i_120_, ((1828905535 * this.anInt3536 * (i_119_ - i_110_)) + ((1 - i_112_) * (394962841 * (this.anInt3546)))), bool, 1798941005);
				    for (Class322 class322 = class326.aClass322_3456; null != class322; class322 = class322.aClass322_3360) {
					Class365_Sub1_Sub1 class365_sub1_sub1 = (class322.aClass365_Sub1_Sub1_3359);
					if (class365_sub1_sub1 != null && class365_sub1_sub1.method4366(1399351341) && ((class365_sub1_sub1.aShort9798 == i_118_) || i_113_ == i_118_) && ((class365_sub1_sub1.aShort9795 == i_119_) || i_115_ == i_119_)) {
					    int i_122_ = 1 + ((class365_sub1_sub1.aShort9796) - (class365_sub1_sub1.aShort9798));
					    int i_123_ = ((class365_sub1_sub1.aShort9797) - (class365_sub1_sub1.aShort9795) + 1);
					    class365_sub1.method4355((this.aClass_ra3508), class365_sub1_sub1, ((1828905535 * (this.anInt3536) * ((class365_sub1_sub1.aShort9798) - i_109_)) + ((i_122_ - i_111_) * (394962841 * (this.anInt3546)))), i_120_, ((((class365_sub1_sub1.aShort9795) - i_110_) * ((this.anInt3536) * 1828905535)) + (394962841 * (this.anInt3546) * (i_123_ - i_112_))), bool, 784338104);
					}
				    }
				}
			    }
			}
		    }
		}
		i_113_--;
		bool = false;
	    }
	}
    }

    public void method4040(int i, int i_124_, int i_125_, int i_126_, byte[][][] is, int[] is_127_, int[] is_128_, int[] is_129_, int[] is_130_, int[] is_131_, int i_132_, byte i_133_, int i_134_, int i_135_, boolean bool, boolean bool_136_, int i_137_, boolean bool_138_) {
	aClass333_3512.aBoolean3577 = true;
	this.aBoolean3511 = bool_136_;
	this.anInt3553 = (i_124_ >> anInt3504 * -1688804109) * -14089225;
	this.anInt3513 = (i_126_ >> -1688804109 * anInt3504) * 1732036403;
	this.anInt3542 = i_124_ * -648073099;
	this.anInt3543 = i_126_ * -701298583;
	this.anInt3544 = i_125_ * -1885539273;
	this.anInt3533 = (1283818765 * this.anInt3553 - this.anInt3538 * -1864237879);
	if (this.anInt3533 * 1387802915 < 0) {
	    this.anInt3547 = -(153202551 * this.anInt3533);
	    this.anInt3533 = 0;
	} else
	    this.anInt3547 = 0;
	this.anInt3551 = (this.anInt3513 * 1233371147 - this.anInt3538 * -678030197);
	if (1655515601 * this.anInt3551 < 0) {
	    this.anInt3507 = -(754782463 * this.anInt3551);
	    this.anInt3551 = 0;
	} else
	    this.anInt3507 = 0;
	this.anInt3550 = (-1827235035 * this.anInt3553 + this.anInt3538 * -679030015);
	if (this.anInt3550 * 945006459 > anInt3514 * 1988988347)
	    this.anInt3550 = anInt3514 * -1126318399;
	this.anInt3552 = (this.anInt3538 * -1363529219 + this.anInt3513 * -690201987);
	if (this.anInt3552 * 779833815 > anInt3549 * 1340714547)
	    this.anInt3552 = 1467937541 * anInt3549;
	boolean[][] bools = this.aBooleanArrayArray3563;
	boolean[][] bools_139_ = this.aBooleanArrayArray3561;
	if (this.aBoolean3511) {
	    for (int i_140_ = 0; i_140_ < (583010427 * this.anInt3538 + this.anInt3538 * 583010427 + 2); i_140_++) {
		int i_141_ = 0;
		int i_142_ = 0;
		for (int i_143_ = 0; i_143_ < (583010427 * this.anInt3538 + this.anInt3538 * 583010427 + 2); i_143_++) {
		    if (i_143_ > 1)
			this.anIntArray3562[i_143_ - 2] = i_141_;
		    i_141_ = i_142_;
		    int i_144_ = (this.anInt3553 * -804213305 - 583010427 * this.anInt3538 + i_140_);
		    int i_145_ = (465603579 * this.anInt3513 - this.anInt3538 * 583010427 + i_143_);
		    if (i_144_ >= 0 && i_145_ >= 0 && i_144_ < 1988988347 * anInt3514 && i_145_ < 1340714547 * anInt3549) {
			int i_146_ = i_144_ << anInt3504 * -1688804109;
			int i_147_ = i_145_ << -1688804109 * anInt3504;
			int i_148_ = (aClass_xaArray3519[aClass_xaArray3519.length - 1].method6341(i_144_, i_145_, (byte) -48) - (1000 << -1688804109 * anInt3504 - 7));
			int i_149_ = ((null != aClass_xaArray3521 ? (aClass_xaArray3521[0].method6341(i_144_, i_145_, (byte) -75) + 1828905535 * this.anInt3536) : (aClass_xaArray3519[0].method6341(i_144_, i_145_, (byte) -70) + 1828905535 * this.anInt3536)) + (1000 << anInt3504 * -1688804109 - 7));
			i_142_ = (this.aClass_ra3508.method5025(i_146_, i_148_, i_147_, i_146_, i_149_, i_147_));
			this.aBooleanArrayArray3561[i_140_][i_143_] = i_142_ == 0;
		    } else {
			i_142_ = -1;
			this.aBooleanArrayArray3561[i_140_][i_143_] = false;
		    }
		    if (i_140_ > 0 && i_143_ > 0) {
			int i_150_ = (this.anIntArray3562[i_143_ - 1] & this.anIntArray3562[i_143_] & i_141_ & i_142_);
			this.aBooleanArrayArray3563[i_140_ - 1][i_143_ - 1] = i_150_ == 0;
		    }
		}
		this.anIntArray3562[(583010427 * this.anInt3538 + this.anInt3538 * 583010427)] = i_141_;
		this.anIntArray3562[1 + (583010427 * this.anInt3538 + 583010427 * this.anInt3538)] = i_142_;
	    }
	    if (!bool_138_)
		aClass333_3512.aBoolean3577 = false;
	    else {
		aClass333_3512.anIntArray3578 = is_127_;
		aClass333_3512.anIntArray3595 = is_128_;
		aClass333_3512.anIntArray3596 = is_129_;
		aClass333_3512.anIntArray3582 = is_130_;
		aClass333_3512.anIntArray3576 = is_131_;
		aClass333_3512.method4081(this.aClass_ra3508, i_132_);
	    }
	} else {
	    if (null == this.aBooleanArrayArray3564)
		this.aBooleanArrayArray3564 = (new boolean[(this.anInt3538 * 583010427 + 583010427 * this.anInt3538 + 2)][(583010427 * this.anInt3538 + 583010427 * this.anInt3538 + 2)]);
	    for (int i_151_ = 0; i_151_ < this.aBooleanArrayArray3564.length; i_151_++) {
		for (int i_152_ = 0; (i_152_ < this.aBooleanArrayArray3564[0].length); i_152_++)
		    this.aBooleanArrayArray3564[i_151_][i_152_] = true;
	    }
	    this.aBooleanArrayArray3561 = this.aBooleanArrayArray3564;
	    this.aBooleanArrayArray3563 = this.aBooleanArrayArray3564;
	    this.anInt3533 = 0;
	    this.anInt3551 = 0;
	    this.anInt3550 = -1126318399 * anInt3514;
	    this.anInt3552 = anInt3549 * 1467937541;
	    aClass333_3512.aBoolean3577 = false;
	}
	ConfigDefinitions.method3977(this, this.aClass_ra3508, -809712178);
	if (!aClass324_3528.aBoolean3446) {
	    Iterator iterator = aClass324_3528.aList3448.iterator();
	    while (iterator.hasNext()) {
		Class335 class335 = (Class335) iterator.next();
		iterator.remove();
		Class205.method1918(class335, (byte) -44);
	    }
	}
	if (this.aBoolean3510) {
	    for (int i_153_ = 0; i_153_ < this.anInt3556 * -2060364501; i_153_++)
		this.aClass321Array3558[i_153_].method3918(i, bool, (byte) 1);
	}
	if (aClass326ArrayArrayArray3520 != null) {
	    method4052(true, -740850409);
	    this.aClass_ra3508.method5057(-1, new Class78(1583160, 40, 127, 63, 0, 0, 0));
	    method4041(true, is, i_132_, i_133_, i_137_);
	    this.aClass_ra3508.O();
	    method4052(false, -740850409);
	}
	method4041(false, is, i_132_, i_133_, i_137_);
	if (!this.aBoolean3511) {
	    this.aBooleanArrayArray3563 = bools;
	    this.aBooleanArrayArray3561 = bools_139_;
	}
    }

    void method4041(boolean bool, byte[][][] is, int i, byte i_154_, int i_155_) {
	int i_156_ = bool ? 1 : 0;
	this.anInt3535 = 0;
	this.anInt3560 = 0;
	this.anInt3545 += 888242829;
	if (0 == (i_155_ & 0x2)) {
	    for (Class365_Sub1 class365_sub1 = this.aClass365_Sub1Array3532[i_156_]; null != class365_sub1; class365_sub1 = class365_sub1.aClass365_Sub1_7721) {
		method4042(class365_sub1, -51028335);
		if (-1 != (-1235150701 * class365_sub1.anInt7725) && !method4045(class365_sub1, bool, is, i, i_154_))
		    this.aClass365_Sub1Array3515[((this.anInt3535 += -770473687) * -1367168231) - 1] = class365_sub1;
	    }
	}
	if (0 == (i_155_ & 0x1)) {
	    for (Class365_Sub1 class365_sub1 = this.aClass365_Sub1Array3557[i_156_]; class365_sub1 != null; class365_sub1 = class365_sub1.aClass365_Sub1_7721) {
		method4042(class365_sub1, 520875779);
		if (-1 != (-1235150701 * class365_sub1.anInt7725) && !method4045(class365_sub1, bool, is, i, i_154_))
		    this.aClass365_Sub1Array3503[((this.anInt3560 += -749214421) * 708424067) - 1] = class365_sub1;
	    }
	    for (Class365_Sub1 class365_sub1 = this.aClass365_Sub1Array3534[i_156_]; class365_sub1 != null; class365_sub1 = class365_sub1.aClass365_Sub1_7721) {
		method4042(class365_sub1, 1278144320);
		if ((class365_sub1.anInt7725 * -1235150701 != -1) && !method4045(class365_sub1, bool, is, i, i_154_)) {
		    if (class365_sub1.method4376((short) 255))
			this.aClass365_Sub1Array3503[((this.anInt3560 += -749214421) * 708424067) - 1] = class365_sub1;
		    else
			this.aClass365_Sub1Array3515[((this.anInt3535 += -770473687) * -1367168231) - 1] = class365_sub1;
		}
	    }
	    if (!bool) {
		for (int i_157_ = 0; i_157_ < 1204219039 * this.anInt3541; i_157_++) {
		    method4042((this.aClass365_Sub1_Sub1Array3539[i_157_]), -765484247);
		    if (-1 != (this.aClass365_Sub1_Sub1Array3539[i_157_]).anInt7725 * -1235150701 && !method4045((this.aClass365_Sub1_Sub1Array3539[i_157_]), bool, is, i, i_154_)) {
			if (this.aClass365_Sub1_Sub1Array3539[i_157_].method4376((short) 255))
			    this.aClass365_Sub1Array3503[((this.anInt3560 += -749214421) * 708424067) - 1] = (this.aClass365_Sub1_Sub1Array3539[i_157_]);
			else
			    this.aClass365_Sub1Array3515[((this.anInt3535 += -770473687) * -1367168231) - 1] = (this.aClass365_Sub1_Sub1Array3539[i_157_]);
		    }
		}
	    }
	}
	if (-1367168231 * this.anInt3535 > 0) {
	    method4044(this.aClass365_Sub1Array3515, 0, -1367168231 * this.anInt3535 - 1);
	    for (int i_158_ = 0; i_158_ < this.anInt3535 * -1367168231; i_158_++)
		method4046(this.aClass365_Sub1Array3515[i_158_], this.aClass298_Sub10Array3529);
	}
	if (this.aBoolean3510)
	    this.aClass_ra3508.method5021(0, null);
	if (0 == (i_155_ & 0x2)) {
	    for (int i_159_ = 0; i_159_ < anInt3548 * 1678382205; i_159_++) {
		if (i_159_ >= i && null != is) {
		    int i_160_ = this.aBooleanArrayArray3563.length;
		    if ((this.aBooleanArrayArray3563.length + 1387802915 * this.anInt3533) > 1988988347 * anInt3514)
			i_160_ -= (this.anInt3533 * 1387802915 + (this.aBooleanArrayArray3563).length - 1988988347 * anInt3514);
		    int i_161_ = this.aBooleanArrayArray3563[0].length;
		    if ((1655515601 * this.anInt3551 + this.aBooleanArrayArray3563[0].length) > anInt3549 * 1340714547)
			i_161_ -= (1655515601 * this.anInt3551 + (this.aBooleanArrayArray3563[0]).length - 1340714547 * anInt3549);
		    boolean[][] bools = this.aBooleanArrayArray3561;
		    if (this.aBoolean3511) {
			for (int i_162_ = -1810993483 * this.anInt3547; i_162_ < i_160_; i_162_++) {
			    int i_163_ = (this.anInt3533 * 1387802915 + i_162_ - (-1810993483 * this.anInt3547));
			    for (int i_164_ = (this.anInt3507 * -301075153); i_164_ < i_161_; i_164_++) {
				bools[i_162_][i_164_] = false;
				if (this.aBooleanArrayArray3563[i_162_][i_164_]) {
				    int i_165_ = (i_164_ + (1655515601 * this.anInt3551) - (this.anInt3507 * -301075153));
				    for (int i_166_ = i_159_; i_166_ >= 0; i_166_--) {
					if ((aClass326ArrayArrayArray3516[i_166_][i_163_][i_165_]) != null && (aClass326ArrayArrayArray3516[i_166_][i_163_][i_165_].aByte3466) == i_159_) {
					    if ((i_166_ >= i && (is[i_166_][i_163_][i_165_] == i_154_)) || (aClass333_3512.method4071(i_159_, i_163_, i_165_)))
						bools[i_162_][i_164_] = false;
					    else
						bools[i_162_][i_164_] = true;
					    break;
					}
				    }
				}
			    }
			}
		    }
		    aClass_xaArray3517[i_159_].method6338(-804213305 * this.anInt3553, this.anInt3513 * 465603579, this.anInt3538 * 583010427, this.aBooleanArrayArray3561, false, i_155_);
		} else {
		    int i_167_ = this.aBooleanArrayArray3563.length;
		    if ((1387802915 * this.anInt3533 + this.aBooleanArrayArray3563.length) > anInt3514 * 1988988347)
			i_167_ -= (1387802915 * this.anInt3533 + (this.aBooleanArrayArray3563).length - 1988988347 * anInt3514);
		    int i_168_ = this.aBooleanArrayArray3563[0].length;
		    if ((1655515601 * this.anInt3551 + this.aBooleanArrayArray3563[0].length) > anInt3549 * 1340714547)
			i_168_ -= ((this.aBooleanArrayArray3563[0]).length + this.anInt3551 * 1655515601 - anInt3549 * 1340714547);
		    boolean[][] bools = this.aBooleanArrayArray3561;
		    if (this.aBoolean3511) {
			for (int i_169_ = this.anInt3547 * -1810993483; i_169_ < i_167_; i_169_++) {
			    int i_170_ = (i_169_ + this.anInt3533 * 1387802915 - (-1810993483 * this.anInt3547));
			    for (int i_171_ = (this.anInt3507 * -301075153); i_171_ < i_168_; i_171_++) {
				if ((this.aBooleanArrayArray3563[i_169_][i_171_]) && !(aClass333_3512.method4071(i_159_, i_170_, (i_171_ + (this.anInt3551 * 1655515601) - (this.anInt3507 * -301075153)))))
				    bools[i_169_][i_171_] = true;
				else
				    bools[i_169_][i_171_] = false;
			    }
			}
		    }
		    aClass_xaArray3517[i_159_].method6338(this.anInt3553 * -804213305, this.anInt3513 * 465603579, 583010427 * this.anInt3538, this.aBooleanArrayArray3561, true, i_155_);
		}
	    }
	}
	if (708424067 * this.anInt3560 > 0) {
	    method4043(this.aClass365_Sub1Array3503, 0, 708424067 * this.anInt3560 - 1);
	    for (int i_172_ = 0; i_172_ < this.anInt3560 * 708424067; i_172_++)
		method4046(this.aClass365_Sub1Array3503[i_172_], this.aClass298_Sub10Array3529);
	}
    }

    void method4042(Class365_Sub1 class365_sub1, int i) {
	try {
	    Class217 class217 = class365_sub1.method4337().aClass217_2599;
	    this.aClass_ra3508.method5060((int) class217.aFloat2451, (int) class217.aFloat2455 + (class365_sub1.method4361(1951240662) >> 1), (int) class217.aFloat2454, this.aFloatArray3505);
	    class365_sub1.anInt7725 = 144688539 * (int) this.aFloatArray3505[2];
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.aj(").append(')').toString());
	}
    }

    void method4043(Class365_Sub1[] class365_sub1s, int i, int i_173_) {
	if (i < i_173_) {
	    int i_174_ = (i_173_ + i) / 2;
	    int i_175_ = i;
	    Class365_Sub1 class365_sub1 = class365_sub1s[i_174_];
	    class365_sub1s[i_174_] = class365_sub1s[i_173_];
	    class365_sub1s[i_173_] = class365_sub1;
	    int i_176_ = -1235150701 * class365_sub1.anInt7725;
	    for (int i_177_ = i; i_177_ < i_173_; i_177_++) {
		if ((-1235150701 * class365_sub1s[i_177_].anInt7725) > (i_177_ & 0x1) + i_176_) {
		    Class365_Sub1 class365_sub1_178_ = class365_sub1s[i_177_];
		    class365_sub1s[i_177_] = class365_sub1s[i_175_];
		    class365_sub1s[i_175_++] = class365_sub1_178_;
		}
	    }
	    class365_sub1s[i_173_] = class365_sub1s[i_175_];
	    class365_sub1s[i_175_] = class365_sub1;
	    method4043(class365_sub1s, i, i_175_ - 1);
	    method4043(class365_sub1s, 1 + i_175_, i_173_);
	}
    }

    void method4044(Class365_Sub1[] class365_sub1s, int i, int i_179_) {
	if (i < i_179_) {
	    int i_180_ = (i_179_ + i) / 2;
	    int i_181_ = i;
	    Class365_Sub1 class365_sub1 = class365_sub1s[i_180_];
	    class365_sub1s[i_180_] = class365_sub1s[i_179_];
	    class365_sub1s[i_179_] = class365_sub1;
	    int i_182_ = class365_sub1.anInt7725 * -1235150701;
	    for (int i_183_ = i; i_183_ < i_179_; i_183_++) {
		if ((-1235150701 * class365_sub1s[i_183_].anInt7725) < (i_183_ & 0x1) + i_182_) {
		    Class365_Sub1 class365_sub1_184_ = class365_sub1s[i_183_];
		    class365_sub1s[i_183_] = class365_sub1s[i_181_];
		    class365_sub1s[i_181_++] = class365_sub1_184_;
		}
	    }
	    class365_sub1s[i_179_] = class365_sub1s[i_181_];
	    class365_sub1s[i_181_] = class365_sub1;
	    method4044(class365_sub1s, i, i_181_ - 1);
	    method4044(class365_sub1s, i_181_ + 1, i_179_);
	}
    }

    boolean method4045(Class365_Sub1 class365_sub1, boolean bool, byte[][][] is, int i, byte i_185_) {
	if (!this.aBoolean3511)
	    return false;
	if (class365_sub1 instanceof Class365_Sub1_Sub1) {
	    int i_186_ = ((Class365_Sub1_Sub1) class365_sub1).aShort9796;
	    int i_187_ = ((Class365_Sub1_Sub1) class365_sub1).aShort9797;
	    int i_188_ = ((Class365_Sub1_Sub1) class365_sub1).aShort9798;
	    int i_189_ = ((Class365_Sub1_Sub1) class365_sub1).aShort9795;
	    for (int i_190_ = i_188_; i_190_ <= i_186_; i_190_++) {
		for (int i_191_ = i_189_; i_191_ <= i_187_; i_191_++) {
		    if (class365_sub1.aByte7724 < 1678382205 * anInt3548 && i_190_ >= this.anInt3533 * 1387802915 && i_190_ < this.anInt3550 * 945006459 && i_191_ >= 1655515601 * this.anInt3551 && i_191_ < this.anInt3552 * 779833815) {
			if ((is != null && class365_sub1.plane >= i && (is[class365_sub1.plane][i_190_][i_191_] == i_185_)) || !class365_sub1.method4360(1467136065) || class365_sub1.method4364((this.aClass_ra3508), (byte) -33)) {
			    if (!bool && i_190_ >= (this.anInt3553 * -804213305) - 16 && i_190_ <= -804213305 * (this.anInt3553) + 16 && i_191_ >= (this.anInt3513 * 465603579) - 16 && i_191_ <= 16 + 465603579 * this.anInt3513)
				class365_sub1.method4357((this.aClass_ra3508), 1659529754);
			    return true;
			}
			return false;
		    }
		}
	    }
	    return true;
	}
	Class217 class217 = class365_sub1.method4337().aClass217_2599;
	int i_192_ = (int) class217.aFloat2451 >> anInt3504 * -1688804109;
	int i_193_ = (int) class217.aFloat2454 >> -1688804109 * anInt3504;
	if (class365_sub1.aByte7724 < anInt3548 * 1678382205 && i_192_ >= 1387802915 * this.anInt3533 && i_192_ < 945006459 * this.anInt3550 && i_193_ >= 1655515601 * this.anInt3551 && i_193_ < 779833815 * this.anInt3552) {
	    if ((is != null && class365_sub1.plane >= i && is[class365_sub1.plane][i_192_][i_193_] == i_185_) || !class365_sub1.method4360(1252283375) || class365_sub1.method4364(this.aClass_ra3508, (byte) 91)) {
		if (!bool && i_192_ >= this.anInt3553 * -804213305 - 16 && i_192_ <= 16 + -804213305 * this.anInt3553 && i_193_ >= 465603579 * this.anInt3513 - 16 && i_193_ <= 16 + 465603579 * this.anInt3513)
		    class365_sub1.method4357(this.aClass_ra3508, 1423007257);
		return true;
	    }
	    return false;
	}
	return true;
    }

    void method4046(Class365_Sub1 class365_sub1, Class298_Sub10[] class298_sub10s) {
	if (this.aBoolean3510) {
	    int i = class365_sub1.method4354(class298_sub10s, -331363526);
	    this.aClass_ra3508.method5021(i, class298_sub10s);
	}
	if (aClass_xaArray3517 == aClass_xaArray3521) {
	    boolean bool = false;
	    boolean bool_194_ = false;
	    Class217 class217 = class365_sub1.method4337().aClass217_2599;
	    int i;
	    int i_195_;
	    if (class365_sub1 instanceof Class365_Sub1_Sub1) {
		i = ((Class365_Sub1_Sub1) class365_sub1).aShort9798;
		i_195_ = ((Class365_Sub1_Sub1) class365_sub1).aShort9795;
	    } else {
		i = (int) class217.aFloat2451 >> anInt3504 * -1688804109;
		i_195_ = (int) class217.aFloat2454 >> anInt3504 * -1688804109;
	    }
	    Class78 class78 = new Class78();
	    class78.anInt726 = method4015(i, i_195_, (byte) 76) * 614121861;
	    class78.anInt725 = method4014(i, i_195_, (byte) -5) * -885436027;
	    class78.anInt727 = method4016(i, i_195_, (byte) 11) * 399458545;
	    class78.anInt729 = method4017(i, i_195_, (byte) 27) * 1507836083;
	    class78.anInt730 = method4060(i, i_195_, -1020454437) * -1927451111;
	    class78.anInt732 = method4018(i, i_195_, -1965414520) * -875460563;
	    this.aClass_ra3508.method5058(aClass_xaArray3519[0].method6340((int) class217.aFloat2451, (int) class217.aFloat2454, -1986155301), class78);
	}
	Class335 class335 = class365_sub1.method4394(this.aClass_ra3508, -461368503);
	if (null != class335) {
	    if (class335.aBoolean3617) {
		class335.aClass365_Sub1_3616 = class365_sub1;
		aClass324_3528.method3957(class335, 469995096);
	    } else
		Class205.method1918(class335, (byte) -93);
	}
    }

    public void method4047(Class_na class_na, int i, int i_196_, int i_197_, boolean[] bools, int i_198_) {
	try {
	    if (aClass_xaArray3521 != aClass_xaArray3517) {
		int i_199_ = aClass_xaArray3519[i].method6340(i_196_, i_197_, -1918392290);
		for (int i_200_ = 0; i_200_ <= i; i_200_++) {
		    if (bools == null || bools[i_200_]) {
			Class_xa class_xa = aClass_xaArray3519[i_200_];
			if (null == class_xa) {
			    if (i_198_ <= 16711935)
				break;
			} else
			    class_xa.NA(class_na, i_196_, (i_199_ - class_xa.method6340(i_196_, i_197_, -1764966761)), i_197_, 0, false);
		    }
		}
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.ar(").append(')').toString());
	}
    }

    void method4048(int i, int i_201_, int i_202_) {
	try {
	    Class_na class_na = null;
	    for (int i_203_ = i; i_203_ < i_201_; i_203_++) {
		Class_xa class_xa = aClass_xaArray3519[i_203_];
		if (null != class_xa) {
		    for (int i_204_ = 0; i_204_ < 1340714547 * anInt3549; i_204_++) {
			for (int i_205_ = 0; i_205_ < anInt3514 * 1988988347; i_205_++) {
			    class_na = class_xa.w(i_205_, i_204_, class_na);
			    if (null != class_na) {
				int i_206_ = i_205_ << -1688804109 * anInt3504;
				int i_207_ = i_204_ << -1688804109 * anInt3504;
				for (int i_208_ = i_203_ - 1; i_208_ >= 0; i_208_--) {
				    Class_xa class_xa_209_ = aClass_xaArray3519[i_208_];
				    if (class_xa_209_ == null) {
					if (i_202_ == -787522086)
					    return;
				    } else {
					int i_210_ = (class_xa.method6341(i_205_, i_204_, (byte) -85) - (class_xa_209_.method6341(i_205_, i_204_, (byte) -14)));
					int i_211_ = (class_xa.method6341(1 + i_205_, i_204_, (byte) -116) - (class_xa_209_.method6341(1 + i_205_, i_204_, (byte) -55)));
					int i_212_ = (class_xa.method6341(1 + i_205_, i_204_ + 1, (byte) -109) - (class_xa_209_.method6341(1 + i_205_, i_204_ + 1, (byte) -78)));
					int i_213_ = (class_xa.method6341(i_205_, 1 + i_204_, (byte) -117) - (class_xa_209_.method6341(i_205_, 1 + i_204_, (byte) -112)));
					class_xa_209_.UA(class_na, i_206_, ((i_213_ + (i_212_ + (i_210_ + i_211_))) / 4), i_207_, 0, false);
				    }
				}
			    }
			}
		    }
		}
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.ac(").append(')').toString());
	}
    }

    public void method4049(byte i) {
	try {
	    for (int i_214_ = 0; i_214_ < -2060364501 * this.anInt3556; i_214_++) {
		if (!this.aBooleanArray3559[i_214_]) {
		    Class321 class321 = this.aClass321Array3558[i_214_];
		    Class298_Sub10 class298_sub10 = class321.aClass298_Sub10_3337;
		    int i_215_ = class321.anInt3345 * -1565952249;
		    int i_216_ = (class298_sub10.method2900(-2066581282) - this.anInt3546 * 394962841);
		    int i_217_ = 1 + (2 * i_216_ >> -1688804109 * anInt3504);
		    int i_218_ = 0;
		    int[] is = new int[i_217_ * i_217_];
		    int i_219_ = (class298_sub10.method2895(823958259) - i_216_ >> anInt3504 * -1688804109);
		    int i_220_ = (class298_sub10.method2897((byte) 84) - i_216_ >> -1688804109 * anInt3504);
		    int i_221_ = (class298_sub10.method2897((byte) 73) + i_216_ >> anInt3504 * -1688804109);
		    if (i_220_ < 0) {
			i_218_ -= i_220_;
			i_220_ = 0;
		    }
		    if (i_221_ >= anInt3549 * 1340714547)
			i_221_ = 1340714547 * anInt3549 - 1;
		    for (int i_222_ = i_220_; i_222_ <= i_221_; i_222_++) {
			int i_223_ = class321.aShortArray3350[i_218_];
			int i_224_ = i_223_ >>> 8;
			int i_225_ = i_217_ * i_218_ + i_224_;
			int i_226_ = (i_223_ >>> 8) + i_219_;
			int i_227_ = i_226_ + (i_223_ & 0xff) - 1;
			if (i_226_ < 0) {
			    i_225_ -= i_226_;
			    i_226_ = 0;
			}
			if (i_227_ >= anInt3514 * 1988988347)
			    i_227_ = 1988988347 * anInt3514 - 1;
			for (int i_228_ = i_226_; i_228_ <= i_227_; i_228_++) {
			    int i_229_ = 1;
			    Class365_Sub1_Sub1 class365_sub1_sub1 = method4035(i_215_, i_228_, i_222_, null, -741941433);
			    if (null != class365_sub1_sub1 && 0 != class365_sub1_sub1.aByte9800) {
				if (1 == class365_sub1_sub1.aByte9800) {
				    boolean bool = i_228_ - 1 >= i_226_;
				    boolean bool_230_ = i_228_ + 1 <= i_227_;
				    if (!bool && i_222_ + 1 <= i_221_) {
					int i_231_ = (class321.aShortArray3350[i_218_ + 1]);
					int i_232_ = i_219_ + (i_231_ >>> 8);
					int i_233_ = i_232_ + (i_231_ & 0xff);
					bool = (i_228_ > i_232_ && i_228_ < i_233_);
				    }
				    if (!bool_230_ && i_222_ - 1 >= i_220_) {
					int i_234_ = (class321.aShortArray3350[i_218_ - 1]);
					int i_235_ = (i_234_ >>> 8) + i_219_;
					int i_236_ = (i_234_ & 0xff) + i_235_;
					bool_230_ = (i_228_ > i_235_ && i_228_ < i_236_);
				    }
				    if (bool && !bool_230_)
					i_229_ = 4;
				    else if (bool_230_ && !bool)
					i_229_ = 2;
				} else {
				    boolean bool = i_228_ - 1 >= i_226_;
				    boolean bool_237_ = 1 + i_228_ <= i_227_;
				    if (!bool && i_222_ - 1 >= i_220_) {
					int i_238_ = (class321.aShortArray3350[i_218_ - 1]);
					int i_239_ = i_219_ + (i_238_ >>> 8);
					int i_240_ = (i_238_ & 0xff) + i_239_;
					bool = (i_228_ > i_239_ && i_228_ < i_240_);
				    }
				    if (!bool_237_ && i_222_ + 1 <= i_221_) {
					int i_241_ = (class321.aShortArray3350[1 + i_218_]);
					int i_242_ = (i_241_ >>> 8) + i_219_;
					int i_243_ = (i_241_ & 0xff) + i_242_;
					bool_237_ = (i_228_ > i_242_ && i_228_ < i_243_);
				    }
				    if (bool && !bool_237_)
					i_229_ = 3;
				    else if (bool_237_ && !bool)
					i_229_ = 5;
				}
			    }
			    is[i_225_++] = i_229_;
			}
			i_218_++;
		    }
		    this.aBooleanArray3559[i_214_] = true;
		    if (aBoolean3540)
			aClass_xaArray3517[i_215_].method6342(class298_sub10, is);
		}
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.bf(").append(')').toString());
	}
    }

    public void method4050(int i) {
	try {
	    for (int i_244_ = 0; i_244_ < anInt3514 * 1988988347; i_244_++) {
		for (int i_245_ = 0; i_245_ < anInt3549 * 1340714547; i_245_++) {
		    if (null == aClass326ArrayArrayArray3516[0][i_244_][i_245_])
			aClass326ArrayArrayArray3516[0][i_244_][i_245_] = new Class326(0);
		}
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.p(").append(')').toString());
	}
    }

    public void method4051(Class321 class321, int i) {
	try {
	    if (this.anInt3556 * -2060364501 < 65391) {
		Class298_Sub10 class298_sub10 = class321.aClass298_Sub10_3337;
		this.aClass321Array3558[-2060364501 * this.anInt3556] = class321;
		this.aBooleanArray3559[this.anInt3556 * -2060364501] = false;
		this.anInt3556 += -979058301;
		int i_246_ = -1565952249 * class321.anInt3345;
		if (class321.aBoolean3348)
		    i_246_ = 0;
		int i_247_ = class321.anInt3345 * -1565952249;
		if (class321.aBoolean3347)
		    i_247_ = anInt3548 * 1678382205 - 1;
		for (int i_248_ = i_246_; i_248_ <= i_247_; i_248_++) {
		    int i_249_ = 0;
		    int i_250_ = ((class298_sub10.method2897((byte) 46) - class298_sub10.method2900(1506691) + 394962841 * this.anInt3546) >> anInt3504 * -1688804109);
		    if (i_250_ < 0) {
			i_249_ -= i_250_;
			i_250_ = 0;
		    }
		    int i_251_ = ((class298_sub10.method2897((byte) 8) + class298_sub10.method2900(-884319007) - this.anInt3546 * 394962841) >> -1688804109 * anInt3504);
		    if (i_251_ >= 1340714547 * anInt3549)
			i_251_ = anInt3549 * 1340714547 - 1;
		    for (int i_252_ = i_250_; i_252_ <= i_251_; i_252_++) {
			int i_253_ = class321.aShortArray3350[i_249_++];
			int i_254_ = (((class298_sub10.method2895(823958259) - class298_sub10.method2900(-701525248) + this.anInt3546 * 394962841) >> anInt3504 * -1688804109) + (i_253_ >>> 8));
			int i_255_ = i_254_ + (i_253_ & 0xff) - 1;
			if (i_254_ < 0)
			    i_254_ = 0;
			if (i_255_ >= 1988988347 * anInt3514)
			    i_255_ = 1988988347 * anInt3514 - 1;
			for (int i_256_ = i_254_; i_256_ <= i_255_; i_256_++) {
			    long l = (this.aLongArrayArrayArray3554[i_248_][i_256_][i_252_]);
			    if ((l & 0xffffL) == 0L)
				this.aLongArrayArrayArray3554[i_248_][i_256_][i_252_] = l | this.anInt3556 * -2060364501;
			    else if ((l & 0xffff0000L) == 0L)
				this.aLongArrayArrayArray3554[i_248_][i_256_][i_252_] = l | (long) (this.anInt3556 * -2060364501) << 16;
			    else if ((l & 0xffff00000000L) == 0L)
				this.aLongArrayArrayArray3554[i_248_][i_256_][i_252_] = l | (long) (this.anInt3556 * -2060364501) << 32;
			    else if ((l & ~0xffffffffffffL) == 0L)
				this.aLongArrayArrayArray3554[i_248_][i_256_][i_252_] = l | (long) (this.anInt3556 * -2060364501) << 48;
			}
		    }
		}
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.bd(").append(')').toString());
	}
    }

    public void method4052(boolean bool, int i) {
	try {
	    if (bool) {
		aClass326ArrayArrayArray3516 = aClass326ArrayArrayArray3520;
		aClass_xaArray3517 = aClass_xaArray3521;
	    } else {
		aClass326ArrayArrayArray3516 = aClass326ArrayArrayArray3518;
		aClass_xaArray3517 = aClass_xaArray3519;
	    }
	    anInt3548 = aClass326ArrayArrayArray3516.length * 1634141397;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.b(").append(')').toString());
	}
    }

    public void method4053(int i, int i_257_, int i_258_, int i_259_, Class365_Sub1_Sub4 class365_sub1_sub4, int i_260_) {
	try {
	    Class326 class326 = method4012(i, i_257_, i_258_, 1185117443);
	    if (null != class326) {
		class365_sub1_sub4.method4340(new Class217((i_257_ << anInt3504 * -1688804109) + (394962841 * this.anInt3546), i_259_, (i_258_ << -1688804109 * anInt3504) + 394962841 * (this.anInt3546)));
		class326.aClass365_Sub1_Sub4_3462 = class365_sub1_sub4;
		int i_261_ = aClass_xaArray3521 == aClass_xaArray3517 ? 1 : 0;
		if (class365_sub1_sub4.method4399((byte) 13)) {
		    if (class365_sub1_sub4.method4376((short) 255)) {
			class365_sub1_sub4.aClass365_Sub1_7721 = (this.aClass365_Sub1Array3557[i_261_]);
			this.aClass365_Sub1Array3557[i_261_] = class365_sub1_sub4;
		    } else {
			class365_sub1_sub4.aClass365_Sub1_7721 = (this.aClass365_Sub1Array3532[i_261_]);
			this.aClass365_Sub1Array3532[i_261_] = class365_sub1_sub4;
		    }
		} else {
		    class365_sub1_sub4.aClass365_Sub1_7721 = this.aClass365_Sub1Array3534[i_261_];
		    this.aClass365_Sub1Array3534[i_261_] = class365_sub1_sub4;
		}
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.h(").append(')').toString());
	}
    }

    public Class365_Sub1_Sub3 method4054(int i, int i_262_, int i_263_, byte i_264_) {
	try {
	    Class326 class326 = aClass326ArrayArrayArray3516[i][i_262_][i_263_];
	    if (null != class326) {
		method4030(class326.aClass365_Sub1_Sub3_3459, 1998851021);
		if (class326.aClass365_Sub1_Sub3_3459 != null) {
		    Class365_Sub1_Sub3 class365_sub1_sub3 = class326.aClass365_Sub1_Sub3_3459;
		    class326.aClass365_Sub1_Sub3_3459 = null;
		    return class365_sub1_sub3;
		}
	    }
	    return null;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.o(").append(')').toString());
	}
    }

    public boolean method4055(Class_na class_na, int i, int i_265_, int i_266_, boolean[] bools, int i_267_) {
	try {
	    boolean bool = false;
	    if (aClass_xaArray3521 != aClass_xaArray3517) {
		int i_268_ = aClass_xaArray3519[i].method6340(i_265_, i_266_, -1298291430);
		int i_269_ = 0;
		for (/**/; i_269_ <= i; i_269_++) {
		    Class_xa class_xa = aClass_xaArray3519[i_269_];
		    if (null == class_xa) {
			if (i_267_ == 2008908432)
			    throw new IllegalStateException();
		    } else {
			int i_270_ = i_268_ - class_xa.method6340(i_265_, i_266_, -1859389272);
			if (bools != null) {
			    bools[i_269_] = class_xa.method6353(class_na, i_265_, i_270_, i_266_, 0, false);
			    if (!bools[i_269_]) {
				if (i_267_ == 2008908432)
				    throw new IllegalStateException();
				continue;
			    }
			}
			class_xa.UA(class_na, i_265_, i_270_, i_266_, 0, false);
			bool = true;
		    }
		}
	    }
	    return bool;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.au(").append(')').toString());
	}
    }

    public void method4056(int i, Class_xa class_xa, byte i_271_) {
	try {
	    aClass_xaArray3517[i] = class_xa;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.u(").append(')').toString());
	}
    }

    public void method4057(int i, int i_272_, int i_273_, Class365_Sub1_Sub2 class365_sub1_sub2, int i_274_) {
	try {
	    Class326 class326 = method4012(i, i_272_, i_273_, 996825081);
	    if (class326 != null) {
		class326.aClass365_Sub1_Sub2_3461 = class365_sub1_sub2;
		int i_275_ = aClass_xaArray3517 == aClass_xaArray3521 ? 1 : 0;
		if (class365_sub1_sub2.method4399((byte) 13)) {
		    if (class365_sub1_sub2.method4376((short) 255)) {
			class365_sub1_sub2.aClass365_Sub1_7721 = (this.aClass365_Sub1Array3557[i_275_]);
			this.aClass365_Sub1Array3557[i_275_] = class365_sub1_sub2;
		    } else {
			class365_sub1_sub2.aClass365_Sub1_7721 = (this.aClass365_Sub1Array3532[i_275_]);
			this.aClass365_Sub1Array3532[i_275_] = class365_sub1_sub2;
		    }
		} else {
		    class365_sub1_sub2.aClass365_Sub1_7721 = this.aClass365_Sub1Array3534[i_275_];
		    this.aClass365_Sub1Array3534[i_275_] = class365_sub1_sub2;
		}
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.t(").append(')').toString());
	}
    }

    public Class365_Sub1_Sub2 method4058(int i, int i_276_, int i_277_, byte i_278_) {
	try {
	    Class326 class326 = aClass326ArrayArrayArray3516[i][i_276_][i_277_];
	    if (class326 == null || class326.aClass365_Sub1_Sub2_3461 == null)
		return null;
	    return class326.aClass365_Sub1_Sub2_3461;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.az(").append(')').toString());
	}
    }

    public Class365_Sub1_Sub4 method4059(int i, int i_279_, int i_280_, byte i_281_) {
	try {
	    Class326 class326 = aClass326ArrayArrayArray3516[i][i_279_][i_280_];
	    if (class326 == null)
		return null;
	    Class365_Sub1_Sub4 class365_sub1_sub4 = class326.aClass365_Sub1_Sub4_3462;
	    class326.aClass365_Sub1_Sub4_3462 = null;
	    method4030(class365_sub1_sub4, 113491731);
	    return class365_sub1_sub4;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.aa(").append(')').toString());
	}
    }

    public int method4060(int i, int i_282_, int i_283_) {
	try {
	    return (this.aByteArrayArray3526 != null ? this.aByteArrayArray3526[i][i_282_] & 0xff : 0);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.s(").append(')').toString());
	}
    }

    static final void method4061(Class403 class403, byte i) {
	try {
	    class403.anInt5239 -= 1551802473;
	    int i_284_ = (class403.anIntArray5244[681479919 * class403.anInt5239]);
	    int i_285_ = ((class403.anIntArray5244[class403.anInt5239 * 681479919 + 1]) << 1);
	    int i_286_ = (class403.anIntArray5244[2 + class403.anInt5239 * 681479919]);
	    int i_287_ = (class403.anIntArray5244[681479919 * class403.anInt5239 + 3]);
	    int i_288_ = (class403.anIntArray5244[681479919 * class403.anInt5239 + 4]);
	    int i_289_ = (class403.anIntArray5244[class403.anInt5239 * 681479919 + 5]);
	    int i_290_ = (class403.anIntArray5244[681479919 * class403.anInt5239 + 6]);
	    if (i_284_ >= 0 && i_284_ < 2 && null != client.anIntArrayArrayArray8767[i_284_] && i_285_ >= 0 && i_285_ < client.anIntArrayArrayArray8767[i_284_].length) {
		client.anIntArrayArrayArray8767[i_284_][i_285_] = new int[] { (i_286_ >> 14 & 0x3fff) << 9, i_287_ << 2, (i_286_ & 0x3fff) << 9, i_290_ };
		client.anIntArrayArrayArray8767[i_284_][1 + i_285_] = new int[] { (i_288_ >> 14 & 0x3fff) << 9, i_289_ << 2, (i_288_ & 0x3fff) << 9 };
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.afm(").append(')').toString());
	}
    }

    static final void method4062(Class403 class403, int i) {
	try {
	    Class390 class390 = (class403.aBoolean5261 ? class403.aClass390_5247 : class403.aClass390_5246);
	    IComponentDefinition class105 = class390.aClass105_4168;
	    class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919 - 1)] = 1045422783 * class105.anInt1170;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.pc(").append(')').toString());
	}
    }

    static IComponentDefinition method4063(IComponentDefinition class105, byte i) {
	try {
	    IComponentDefinition class105_291_ = client.method2808(class105);
	    if (null == class105_291_)
		class105_291_ = class105.aClass105_1233;
	    return class105_291_;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("nu.ln(").append(')').toString());
	}
    }
}
