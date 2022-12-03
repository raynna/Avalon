
/* Class365_Sub1_Sub5_Sub1 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.io.File;

public class Class365_Sub1_Sub5_Sub1 extends Class365_Sub1_Sub5 implements Interface3 {
    static int[] anIntArray9890 = { 1, 2, 4, 8 };
    static int[] anIntArray9891 = { 16, 32, 64, 128 };
    boolean aBoolean9892;
    Class387 aClass387_9893;
    Class_na aClass_na9894;
    Class334 aClass334_9895;
    int anInt9896;
    byte aByte9897;
    Class433 aClass433_9898;
    byte aByte9899;
    boolean aBoolean9900;
    boolean aBoolean9901;
    boolean aBoolean9902;

    @Override
    boolean method4384() {
	return this.aBoolean9892;
    }

    @Override
    boolean method4399(byte i) {
	try {
	    if (null != this.aClass387_9893)
		return !this.aClass387_9893.u();
	    return true;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("wg.bf(").append(')').toString());
	}
    }

    @Override
    boolean method4376(short i) {
	try {
	    if (null != this.aClass387_9893)
		return this.aClass387_9893.i();
	    return false;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("wg.be(").append(')').toString());
	}
    }

    @Override
    public int method4361(int i) {
	try {
	    return (this.aClass387_9893 != null ? this.aClass387_9893.YA() : 0);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("wg.bm(").append(')').toString());
	}
    }

    Class387 method4523(GraphicsToolkit class_ra, int i, int i_0_) {
	try {
	    if (null != this.aClass387_9893 && class_ra.method5017(this.aClass387_9893.m(), i) == 0)
		return this.aClass387_9893;
	    Class454 class454 = method4524(class_ra, i, false, (byte) -90);
	    if (null != class454)
		return (Class387) class454.anObject5645;
	    return null;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("wg.bl(").append(')').toString());
	}
    }

    Class454 method4524(GraphicsToolkit class_ra, int i, boolean bool, byte i_1_) {
	try {
	    ObjectDefinitions class432 = (this.aClass433_9898.getObjectDefinitions(1940788231 * this.anInt9896));
	    Class_xa class_xa;
	    Class_xa class_xa_2_;
	    if (this.aBoolean9901) {
		class_xa = aClass331_7722.aClass_xaArray3521[aByte7724];
		class_xa_2_ = aClass331_7722.aClass_xaArray3519[0];
	    } else {
		class_xa = aClass331_7722.aClass_xaArray3519[aByte7724];
		if (aByte7724 < 3)
		    class_xa_2_ = aClass331_7722.aClass_xaArray3519[aByte7724 + 1];
		else
		    class_xa_2_ = null;
	    }
	    Class217 class217 = method4337().aClass217_2599;
	    return (class432.method5785(class_ra, i, this.aByte9899, this.aByte9897, class_xa, class_xa_2_, (int) class217.aFloat2451, (int) class217.aFloat2455, (int) class217.aFloat2454, bool, null, 566759128));
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("wg.br(").append(')').toString());
	}
    }

    @Override
    public boolean method39(int i) {
	try {
	    return this.aBoolean9902;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("wg.i(").append(')').toString());
	}
    }

    @Override
    Class335 method4394(GraphicsToolkit class_ra, int i) {
	try {
	    if (this.aClass387_9893 == null)
		return null;
	    Class222 class222 = method4347();
	    Class335 class335 = Class73.method818((this.aBoolean9900), 1739349745);
	    this.aClass387_9893.method4739(class222, aClass302_Sub1Array7726[0], 0);
	    return class335;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("wg.bo(").append(')').toString());
	}
    }

    @Override
    boolean method4382() {
	if (null != this.aClass387_9893)
	    return this.aClass387_9893.i();
	return false;
    }

    @Override
    boolean method4366(int i) {
	try {
	    return this.aBoolean9892;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("wg.bw(").append(')').toString());
	}
    }

    @Override
    void method4355(GraphicsToolkit class_ra, Class365_Sub1 class365_sub1, int i, int i_3_, int i_4_, boolean bool, int i_5_) {
	try {
	    if (class365_sub1 instanceof Class365_Sub1_Sub5_Sub1) {
		Class365_Sub1_Sub5_Sub1 class365_sub1_sub5_sub1_6_ = (Class365_Sub1_Sub5_Sub1) class365_sub1;
		if (this.aClass387_9893 != null && (class365_sub1_sub5_sub1_6_.aClass387_9893) != null)
		    this.aClass387_9893.method4745(class365_sub1_sub5_sub1_6_.aClass387_9893, i, i_3_, i_4_, bool);
	    } else if (class365_sub1 instanceof Class365_Sub1_Sub1_Sub6) {
		Class365_Sub1_Sub1_Sub6 class365_sub1_sub1_sub6 = (Class365_Sub1_Sub1_Sub6) class365_sub1;
		if (this.aClass387_9893 != null && (class365_sub1_sub1_sub6.aClass387_9879) != null)
		    this.aClass387_9893.method4745((class365_sub1_sub1_sub6.aClass387_9879), i, i_3_, i_4_, bool);
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("wg.bk(").append(')').toString());
	}
    }

    @Override
    void method4398(byte i) {
	try {
	    this.aBoolean9892 = false;
	    if (this.aClass387_9893 != null)
		this.aClass387_9893.KA(this.aClass387_9893.m() & ~0x10000);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("wg.bq(").append(')').toString());
	}
    }

    @Override
    public int method35() {
	return this.aByte9899;
    }

    @Override
    public int method29(int i) {
	try {
	    return this.aByte9899;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("wg.f(").append(')').toString());
	}
    }

    @Override
    public int method30(short i) {
	try {
	    return this.aByte9897;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("wg.b(").append(')').toString());
	}
    }

    @Override
    boolean method4351() {
	if (null != this.aClass387_9893)
	    return this.aClass387_9893.i();
	return false;
    }

    @Override
    Class335 method4370(GraphicsToolkit class_ra) {
	if (this.aClass387_9893 == null)
	    return null;
	Class222 class222 = method4347();
	Class335 class335 = Class73.method818(this.aBoolean9900, 1174280404);
	this.aClass387_9893.method4739(class222, aClass302_Sub1Array7726[0], 0);
	return class335;
    }

    @Override
    boolean method4383() {
	if (null != this.aClass387_9893)
	    return this.aClass387_9893.i();
	return false;
    }

    @Override
    void method4377() {
	this.aBoolean9892 = false;
	if (this.aClass387_9893 != null)
	    this.aClass387_9893.KA(this.aClass387_9893.m() & ~0x10000);
    }

    @Override
    public int method45() {
	return 1940788231 * this.anInt9896;
    }

    @Override
    public int method36() {
	return this.aByte9899;
    }

    @Override
    public void method40(GraphicsToolkit class_ra) {
	Object object = null;
	Class_na class_na;
	if (null == this.aClass_na9894 && this.aBoolean9902) {
	    Class454 class454 = method4524(class_ra, 262144, true, (byte) -75);
	    class_na = (Class_na) (class454 != null ? class454.anObject5646 : null);
	} else {
	    class_na = this.aClass_na9894;
	    this.aClass_na9894 = null;
	}
	Class217 class217 = method4337().aClass217_2599;
	if (null != class_na)
	    aClass331_7722.method4047(class_na, aByte7724, (int) class217.aFloat2451, (int) class217.aFloat2454, null, 1555439691);
    }

    @Override
    public int method42() {
	return this.aByte9897;
    }

    @Override
    boolean method4350(GraphicsToolkit class_ra, int i, int i_7_, byte i_8_) {
	try {
	    Class387 class387 = method4523(class_ra, 131072, -546231639);
	    if (class387 != null) {
		Class222 class222 = method4347();
		return class387.method4787(i, i_7_, class222, false, 0);
	    }
	    return false;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("wg.bu(").append(')').toString());
	}
    }

    @Override
    public void method34() {
	if (null != this.aClass387_9893)
	    this.aClass387_9893.method4784();
    }

    @Override
    public Class334 method4367(GraphicsToolkit class_ra) {
	Class217 class217 = method4337().aClass217_2599;
	if (null == this.aClass334_9895)
	    this.aClass334_9895 = Class472.method6063((int) class217.aFloat2451, (int) class217.aFloat2455, (int) class217.aFloat2454, method4523(class_ra, 0, -541574626), 2030586614);
	return this.aClass334_9895;
    }

    @Override
    public void method28(GraphicsToolkit class_ra) {
	Object object = null;
	Class_na class_na;
	if (null == this.aClass_na9894 && this.aBoolean9902) {
	    Class454 class454 = method4524(class_ra, 262144, true, (byte) -35);
	    class_na = (Class_na) (null != class454 ? class454.anObject5646 : null);
	} else {
	    class_na = this.aClass_na9894;
	    this.aClass_na9894 = null;
	}
	Class217 class217 = method4337().aClass217_2599;
	if (null != class_na)
	    aClass331_7722.method4055(class_na, aByte7724, (int) class217.aFloat2451, (int) class217.aFloat2454, null, 932138106);
    }

    @Override
    public int method32(byte i) {
	try {
	    return 1940788231 * this.anInt9896;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("wg.a(").append(')').toString());
	}
    }

    @Override
    public void method44(GraphicsToolkit class_ra) {
	Object object = null;
	Class_na class_na;
	if (null == this.aClass_na9894 && this.aBoolean9902) {
	    Class454 class454 = method4524(class_ra, 262144, true, (byte) -118);
	    class_na = (Class_na) (class454 != null ? class454.anObject5646 : null);
	} else {
	    class_na = this.aClass_na9894;
	    this.aClass_na9894 = null;
	}
	Class217 class217 = method4337().aClass217_2599;
	if (null != class_na)
	    aClass331_7722.method4047(class_na, aByte7724, (int) class217.aFloat2451, (int) class217.aFloat2454, null, 1480402841);
    }

    @Override
    public Class334 method4368(GraphicsToolkit class_ra) {
	Class217 class217 = method4337().aClass217_2599;
	if (null == this.aClass334_9895)
	    this.aClass334_9895 = Class472.method6063((int) class217.aFloat2451, (int) class217.aFloat2455, (int) class217.aFloat2454, method4523(class_ra, 0, -2141517966), 2144329713);
	return this.aClass334_9895;
    }

    @Override
    boolean method4353() {
	if (null != this.aClass387_9893)
	    return !this.aClass387_9893.u();
	return true;
    }

    public Class365_Sub1_Sub5_Sub1(Class331 class331, GraphicsToolkit class_ra, Class433 class433, ObjectDefinitions class432, int i, int i_9_, int i_10_, int i_11_, int i_12_, boolean bool, int i_13_, int i_14_, boolean bool_15_) {
	super(class331, i_10_, i_11_, i_12_, i, i_9_, Class84.method952(i_13_, i_14_, 800544861));
	this.aClass433_9898 = class433;
	this.anInt9896 = class432.anInt5365 * 584655573;
	this.aBoolean9901 = bool;
	this.aByte9899 = (byte) i_13_;
	this.aByte9897 = (byte) i_14_;
	this.aBoolean9900 = 0 != 1532834983 * class432.anInt5382 && !bool;
	this.aBoolean9892 = bool_15_;
	this.aBoolean9902 = (class_ra.method5082() && class432.aBoolean5433 && !this.aBoolean9901 && Class422_Sub25.aClass298_Sub48_8425.aClass422_Sub7_7581.method5650(57385915) != 0);
	int i_16_ = 2048;
	if (this.aBoolean9892)
	    i_16_ |= 0x10000;
	if (class432.aBoolean5441)
	    i_16_ |= 0x80000;
	Class454 class454 = method4524(class_ra, i_16_, this.aBoolean9902, (byte) -72);
	if (class454 != null) {
	    this.aClass387_9893 = (Class387) class454.anObject5645;
	    this.aClass_na9894 = (Class_na) class454.anObject5646;
	    if (this.aBoolean9892 || class432.aBoolean5441) {
		this.aClass387_9893 = this.aClass387_9893.method4755((byte) 0, i_16_, false);
		if (class432.aBoolean5441) {
		    Class287 class287 = client.aClass283_8716.method2632(-2131923625);
		    this.aClass387_9893.PA(class287.anInt3096 * 1599271859, 1630923113 * class287.anInt3097, class287.anInt3098 * -1560648831, class287.anInt3099 * -57569897);
		}
	    }
	}
	method4362(1, -1008898449);
    }

    @Override
    boolean method4374() {
	if (null != this.aClass387_9893)
	    return !this.aClass387_9893.u();
	return true;
    }

    @Override
    public void method37(GraphicsToolkit class_ra, int i) {
	try {
	    Object object = null;
	    Class_na class_na;
	    if (null == this.aClass_na9894 && this.aBoolean9902) {
		Class454 class454 = method4524(class_ra, 262144, true, (byte) -99);
		class_na = (Class_na) (class454 != null ? class454.anObject5646 : null);
	    } else {
		class_na = this.aClass_na9894;
		this.aClass_na9894 = null;
	    }
	    Class217 class217 = method4337().aClass217_2599;
	    if (null != class_na)
		aClass331_7722.method4047(class_na, aByte7724, (int) class217.aFloat2451, (int) class217.aFloat2454, null, 580390895);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("wg.d(").append(')').toString());
	}
    }

    @Override
    boolean method4365() {
	if (null != this.aClass387_9893)
	    return !this.aClass387_9893.u();
	return true;
    }

    @Override
    void method4388(GraphicsToolkit class_ra, Class365_Sub1 class365_sub1, int i, int i_17_, int i_18_, boolean bool) {
	if (class365_sub1 instanceof Class365_Sub1_Sub5_Sub1) {
	    Class365_Sub1_Sub5_Sub1 class365_sub1_sub5_sub1_19_ = (Class365_Sub1_Sub5_Sub1) class365_sub1;
	    if (this.aClass387_9893 != null && (class365_sub1_sub5_sub1_19_.aClass387_9893) != null)
		this.aClass387_9893.method4745((class365_sub1_sub5_sub1_19_.aClass387_9893), i, i_17_, i_18_, bool);
	} else if (class365_sub1 instanceof Class365_Sub1_Sub1_Sub6) {
	    Class365_Sub1_Sub1_Sub6 class365_sub1_sub1_sub6 = (Class365_Sub1_Sub1_Sub6) class365_sub1;
	    if (this.aClass387_9893 != null && (class365_sub1_sub1_sub6.aClass387_9879) != null)
		this.aClass387_9893.method4745((class365_sub1_sub1_sub6.aClass387_9879), i, i_17_, i_18_, bool);
	}
    }

    @Override
    void method4371(GraphicsToolkit class_ra) {
	/* empty */
    }

    @Override
    void method4373(GraphicsToolkit class_ra) {
	/* empty */
    }

    @Override
    boolean method4372(GraphicsToolkit class_ra, int i, int i_20_) {
	Class387 class387 = method4523(class_ra, 131072, -1405605049);
	if (class387 != null) {
	    Class222 class222 = method4347();
	    return class387.method4787(i, i_20_, class222, false, 0);
	}
	return false;
    }

    @Override
    boolean method4385(GraphicsToolkit class_ra, int i, int i_21_) {
	Class387 class387 = method4523(class_ra, 131072, -189727936);
	if (class387 != null) {
	    Class222 class222 = method4347();
	    return class387.method4787(i, i_21_, class222, false, 0);
	}
	return false;
    }

    @Override
    boolean method4352(GraphicsToolkit class_ra, int i, int i_22_) {
	Class387 class387 = method4523(class_ra, 131072, -673650245);
	if (class387 != null) {
	    Class222 class222 = method4347();
	    return class387.method4787(i, i_22_, class222, false, 0);
	}
	return false;
    }

    @Override
    void method4375(GraphicsToolkit class_ra, Class365_Sub1 class365_sub1, int i, int i_23_, int i_24_, boolean bool) {
	if (class365_sub1 instanceof Class365_Sub1_Sub5_Sub1) {
	    Class365_Sub1_Sub5_Sub1 class365_sub1_sub5_sub1_25_ = (Class365_Sub1_Sub5_Sub1) class365_sub1;
	    if (this.aClass387_9893 != null && (class365_sub1_sub5_sub1_25_.aClass387_9893) != null)
		this.aClass387_9893.method4745((class365_sub1_sub5_sub1_25_.aClass387_9893), i, i_23_, i_24_, bool);
	} else if (class365_sub1 instanceof Class365_Sub1_Sub1_Sub6) {
	    Class365_Sub1_Sub1_Sub6 class365_sub1_sub1_sub6 = (Class365_Sub1_Sub1_Sub6) class365_sub1;
	    if (this.aClass387_9893 != null && (class365_sub1_sub1_sub6.aClass387_9879) != null)
		this.aClass387_9893.method4745((class365_sub1_sub1_sub6.aClass387_9879), i, i_23_, i_24_, bool);
	}
    }

    @Override
    public void method33(GraphicsToolkit class_ra, int i) {
	try {
	    Object object = null;
	    Class_na class_na;
	    if (null == this.aClass_na9894 && this.aBoolean9902) {
		Class454 class454 = method4524(class_ra, 262144, true, (byte) 2);
		class_na = (Class_na) (null != class454 ? class454.anObject5646 : null);
	    } else {
		class_na = this.aClass_na9894;
		this.aClass_na9894 = null;
	    }
	    Class217 class217 = method4337().aClass217_2599;
	    if (null != class_na)
		aClass331_7722.method4055(class_na, aByte7724, (int) class217.aFloat2451, (int) class217.aFloat2454, null, 2001227588);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("wg.k(").append(')').toString());
	}
    }

    @Override
    boolean method4387() {
	return this.aBoolean9892;
    }

    @Override
    void method4378() {
	this.aBoolean9892 = false;
	if (this.aClass387_9893 != null)
	    this.aClass387_9893.KA(this.aClass387_9893.m() & ~0x10000);
    }

    @Override
    public Class334 method4358(GraphicsToolkit class_ra, byte i) {
	try {
	    Class217 class217 = method4337().aClass217_2599;
	    if (null == this.aClass334_9895)
		this.aClass334_9895 = Class472.method6063((int) class217.aFloat2451, (int) class217.aFloat2455, (int) class217.aFloat2454, method4523(class_ra, 0, -187426704), 2051250714);
	    return this.aClass334_9895;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("wg.bc(").append(')').toString());
	}
    }

    @Override
    void method4357(GraphicsToolkit class_ra, int i) {
	try {
	    /* empty */
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("wg.bb(").append(')').toString());
	}
    }

    @Override
    public int method4381() {
	return (this.aClass387_9893 != null ? this.aClass387_9893.YA() : 0);
    }

    @Override
    boolean method4369() {
	if (null != this.aClass387_9893)
	    return this.aClass387_9893.i();
	return false;
    }

    @Override
    boolean method4400() {
	return this.aBoolean9892;
    }

    @Override
    boolean method4349() {
	if (null != this.aClass387_9893)
	    return this.aClass387_9893.i();
	return false;
    }

    @Override
    public void method43(GraphicsToolkit class_ra) {
	Object object = null;
	Class_na class_na;
	if (null == this.aClass_na9894 && this.aBoolean9902) {
	    Class454 class454 = method4524(class_ra, 262144, true, (byte) -118);
	    class_na = (Class_na) (class454 != null ? class454.anObject5646 : null);
	} else {
	    class_na = this.aClass_na9894;
	    this.aClass_na9894 = null;
	}
	Class217 class217 = method4337().aClass217_2599;
	if (null != class_na)
	    aClass331_7722.method4047(class_na, aByte7724, (int) class217.aFloat2451, (int) class217.aFloat2454, null, 310306064);
    }

    @Override
    public boolean method41() {
	return this.aBoolean9902;
    }

    @Override
    public void method31(byte i) {
	try {
	    if (null != this.aClass387_9893)
		this.aClass387_9893.method4784();
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("wg.p(").append(')').toString());
	}
    }

    @Override
    public int method4380() {
	return (this.aClass387_9893 != null ? this.aClass387_9893.YA() : 0);
    }

    @Override
    boolean method4386() {
	return this.aBoolean9892;
    }

    @Override
    public int method38() {
	return this.aByte9897;
    }

    @Override
    public int method4379() {
	return (this.aClass387_9893 != null ? this.aClass387_9893.YA() : 0);
    }

    public static boolean method4525(boolean bool, int i) {
	try {
	    boolean bool_26_ = Class373.aClass_ra4071.method5054();
	    if (bool != bool_26_) {
		if (bool) {
		    if (!Class373.aClass_ra4071.method5052())
			bool = false;
		} else
		    Class373.aClass_ra4071.method5053();
		if (bool != bool_26_) {
		    Class422_Sub25.aClass298_Sub48_8425.method3540((Class422_Sub25.aClass298_Sub48_8425.aClass422_Sub6_7551), bool ? 1 : 0, -1863602535);
		    Class3.method300(656179282);
		    return true;
		}
		return false;
	    }
	    return true;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("wg.gg(").append(')').toString());
	}
    }

    static final void method4526(Class403 class403, int i) {
	try {
	    int i_27_ = (class403.anIntArray5257[class403.anInt5259 * 1883543357]);
	    String string = (String) Class313.anObjectArray3298[i_27_];
	    if (string == null)
		class403.anObjectArray5240[((class403.anInt5241 += 969361751) * -203050393) - 1] = "";
	    else
		class403.anObjectArray5240[((class403.anInt5241 += 969361751) * -203050393) - 1] = string;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("wg.bp(").append(')').toString());
	}
    }

    static final void method4527(Class403 class403, int i) {
	try {
	    int i_28_ = (class403.anIntArray5244[((class403.anInt5239 -= -391880689) * 681479919)]);
	    Class352 class352 = Class363.aClass339_3931.method4116(i_28_, -1520948528);
	    class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919 - 1)] = class352.anInt3783 * 1023000389;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("wg.amx(").append(')').toString());
	}
    }

    static void method4528(File file, byte i) {
	try {
	    Class304.aFile3265 = file;
	    if (!Class304.aFile3265.exists())
		throw new RuntimeException("");
	    Class304.aBoolean3266 = true;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("wg.a(").append(')').toString());
	}
    }

    static void method4529(int i, int i_29_, int i_30_, int i_31_, int i_32_, int i_33_) {
	try {
	    Class82_Sub22.method940(Class372.anIntArrayArray4047[i_30_++], i, i_29_, i_32_, 718097597);
	    Class82_Sub22.method940(Class372.anIntArrayArray4047[i_31_--], i, i_29_, i_32_, 1200953445);
	    for (int i_34_ = i_30_; i_34_ <= i_31_; i_34_++) {
		int[] is = Class372.anIntArrayArray4047[i_34_];
		is[i] = is[i_29_] = i_32_;
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("wg.v(").append(')').toString());
	}
    }
}
