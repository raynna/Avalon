/* Class365_Sub1_Sub2_Sub1 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class365_Sub1_Sub2_Sub1 extends Class365_Sub1_Sub2 implements Interface3 {
    boolean aBoolean9907 = true;
    boolean aBoolean9908;
    Class334 aClass334_9909;
    public Class60 aClass60_9910;
    static long aLong9911;

    @Override
    final void method4378() {
	throw new IllegalStateException();
    }

    @Override
    boolean method4350(GraphicsToolkit class_ra, int i, int i_0_, byte i_1_) {
	try {
	    Class387 class387 = aClass60_9910.method700(class_ra, 131072, false, false, (byte) -17);
	    if (class387 == null)
		return false;
	    return class387.method4787(i, i_0_, method4347(), false, 0);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("wm.bu(").append(')').toString());
	}
    }

    @Override
    boolean method4376(short i) {
	try {
	    return this.aBoolean9907;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("wm.be(").append(')').toString());
	}
    }

    @Override
    public Class334 method4358(GraphicsToolkit class_ra, byte i) {
	try {
	    return this.aClass334_9909;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("wm.bc(").append(')').toString());
	}
    }

    @Override
    boolean method4385(GraphicsToolkit class_ra, int i, int i_2_) {
	Class387 class387 = aClass60_9910.method700(class_ra, 131072, false, false, (byte) -18);
	if (class387 == null)
	    return false;
	return class387.method4787(i, i_2_, method4347(), false, 0);
    }

    @Override
    public int method4363(byte i) {
	try {
	    return aClass60_9910.method699(2132571778);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("wm.bx(").append(')').toString());
	}
    }

    @Override
    Class335 method4394(GraphicsToolkit class_ra, int i) {
	try {
	    Class387 class387 = aClass60_9910.method700(class_ra, 2048, false, true, (byte) 42);
	    if (null == class387)
		return null;
	    Class222 class222 = method4347();
	    Class235 class235 = method4337();
	    Class335 class335 = Class73.method818((this.aBoolean9908), 1432921779);
	    int i_3_ = (int) class235.aClass217_2599.aFloat2451 >> 9;
	    int i_4_ = (int) class235.aClass217_2599.aFloat2454 >> 9;
	    aClass60_9910.method701(class_ra, class387, class222, i_3_, i_3_, i_4_, i_4_, true, 244174707);
	    class387.method4739(class222, aClass302_Sub1Array7726[0], 0);
	    if (null != aClass60_9910.aClass351_602) {
		Class69 class69 = aClass60_9910.aClass351_602.method4229();
		class_ra.method5042(class69);
	    }
	    this.aBoolean9907 = (class387.i() || null != aClass60_9910.aClass351_602);
	    if (null == this.aClass334_9909)
		this.aClass334_9909 = Class472.method6063((int) (class235.aClass217_2599.aFloat2451), (int) (class235.aClass217_2599.aFloat2455), (int) (class235.aClass217_2599.aFloat2454), class387, 2034122433);
	    else
		Class264_Sub2.method2507(this.aClass334_9909, (int) class235.aClass217_2599.aFloat2451, (int) class235.aClass217_2599.aFloat2455, (int) class235.aClass217_2599.aFloat2454, class387, (byte) 83);
	    return class335;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("wm.bo(").append(')').toString());
	}
    }

    public Class365_Sub1_Sub2_Sub1(Class331 class331, GraphicsToolkit class_ra, Class433 class433, ObjectDefinitions class432, int i, int i_5_, int i_6_, int i_7_, int i_8_, boolean bool, int i_9_, int i_10_) {
	super(class331, i_6_, i_7_, i_8_, i, i_5_, -228547261 * class432.anInt5418);
	aClass60_9910 = new Class60(class_ra, class433, class432, GameObjectType.T22.type * -1976050083, i_9_, i, i_5_, this, bool, i_10_);
	this.aBoolean9908 = class432.anInt5382 * 1532834983 != 0 && !bool;
	method4362(1, 397760713);
    }

    @Override
    public void method40(GraphicsToolkit class_ra) {
	aClass60_9910.method707(class_ra, -475225909);
    }

    @Override
    final boolean method4366(int i) {
	try {
	    return false;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("wm.bw(").append(')').toString());
	}
    }

    @Override
    final void method4355(GraphicsToolkit class_ra, Class365_Sub1 class365_sub1, int i, int i_11_, int i_12_, boolean bool, int i_13_) {
	try {
	    throw new IllegalStateException();
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("wm.bk(").append(')').toString());
	}
    }

    @Override
    final void method4398(byte i) {
	try {
	    throw new IllegalStateException();
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("wm.bq(").append(')').toString());
	}
    }

    @Override
    public int method32(byte i) {
	try {
	    return 1686561661 * aClass60_9910.anInt601;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("wm.a(").append(')').toString());
	}
    }

    @Override
    public int method29(int i) {
	try {
	    return -1598457753 * aClass60_9910.anInt589;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("wm.f(").append(')').toString());
	}
    }

    @Override
    public int method30(short i) {
	try {
	    return 748228569 * aClass60_9910.anInt590;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("wm.b(").append(')').toString());
	}
    }

    @Override
    boolean method4372(GraphicsToolkit class_ra, int i, int i_14_) {
	Class387 class387 = aClass60_9910.method700(class_ra, 131072, false, false, (byte) -51);
	if (class387 == null)
	    return false;
	return class387.method4787(i, i_14_, method4347(), false, 0);
    }

    @Override
    boolean method4399(byte i) {
	try {
	    return false;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("wm.bf(").append(')').toString());
	}
    }

    @Override
    final void method4388(GraphicsToolkit class_ra, Class365_Sub1 class365_sub1, int i, int i_15_, int i_16_, boolean bool) {
	throw new IllegalStateException();
    }

    public void method4495(Class435 class435, byte i) {
	try {
	    aClass60_9910.method698(class435, -748656560);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("wm.by(").append(')').toString());
	}
    }

    @Override
    public void method37(GraphicsToolkit class_ra, int i) {
	try {
	    aClass60_9910.method707(class_ra, -475225909);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("wm.d(").append(')').toString());
	}
    }

    @Override
    public int method36() {
	return -1598457753 * aClass60_9910.anInt589;
    }

    @Override
    public int method35() {
	return -1598457753 * aClass60_9910.anInt589;
    }

    @Override
    public int method42() {
	return 748228569 * aClass60_9910.anInt590;
    }

    @Override
    public int method38() {
	return 748228569 * aClass60_9910.anInt590;
    }

    @Override
    public void method34() {
	/* empty */
    }

    @Override
    public boolean method41() {
	return aClass60_9910.method706(16957801);
    }

    @Override
    public void method28(GraphicsToolkit class_ra) {
	aClass60_9910.method702(class_ra, -1400920433);
    }

    @Override
    public void method43(GraphicsToolkit class_ra) {
	aClass60_9910.method707(class_ra, -475225909);
    }

    @Override
    void method4373(GraphicsToolkit class_ra) {
	Class387 class387 = aClass60_9910.method700(class_ra, 262144, true, true, (byte) 5);
	if (null != class387) {
	    Class217 class217 = method4337().aClass217_2599;
	    int i = (int) class217.aFloat2451 >> 9;
	    int i_17_ = (int) class217.aFloat2454 >> 9;
	    aClass60_9910.method701(class_ra, class387, method4347(), i, i, i_17_, i_17_, false, 264840409);
	}
    }

    @Override
    public int method4361(int i) {
	try {
	    return aClass60_9910.method705(-2145027593);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("wm.bm(").append(')').toString());
	}
    }

    @Override
    public int method45() {
	return 1686561661 * aClass60_9910.anInt601;
    }

    @Override
    boolean method4365() {
	return false;
    }

    @Override
    boolean method4374() {
	return false;
    }

    @Override
    public Class334 method4367(GraphicsToolkit class_ra) {
	return this.aClass334_9909;
    }

    @Override
    public Class334 method4368(GraphicsToolkit class_ra) {
	return this.aClass334_9909;
    }

    public int method4496() {
	return aClass60_9910.method699(2084300626);
    }

    @Override
    public void method33(GraphicsToolkit class_ra, int i) {
	try {
	    aClass60_9910.method702(class_ra, 1192057266);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("wm.k(").append(')').toString());
	}
    }

    @Override
    public void method44(GraphicsToolkit class_ra) {
	aClass60_9910.method707(class_ra, -475225909);
    }

    @Override
    void method4371(GraphicsToolkit class_ra) {
	Class387 class387 = aClass60_9910.method700(class_ra, 262144, true, true, (byte) -9);
	if (null != class387) {
	    Class217 class217 = method4337().aClass217_2599;
	    int i = (int) class217.aFloat2451 >> 9;
	    int i_18_ = (int) class217.aFloat2454 >> 9;
	    aClass60_9910.method701(class_ra, class387, method4347(), i, i, i_18_, i_18_, false, 1404836454);
	}
    }

    @Override
    public int method4380() {
	return aClass60_9910.method705(1657985578);
    }

    @Override
    final boolean method4387() {
	return false;
    }

    @Override
    public int method4381() {
	return aClass60_9910.method705(-1816306113);
    }

    @Override
    boolean method4352(GraphicsToolkit class_ra, int i, int i_19_) {
	Class387 class387 = aClass60_9910.method700(class_ra, 131072, false, false, (byte) -102);
	if (class387 == null)
	    return false;
	return class387.method4787(i, i_19_, method4347(), false, 0);
    }

    @Override
    final void method4375(GraphicsToolkit class_ra, Class365_Sub1 class365_sub1, int i, int i_20_, int i_21_, boolean bool) {
	throw new IllegalStateException();
    }

    @Override
    public boolean method39(int i) {
	try {
	    return aClass60_9910.method706(260525653);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("wm.i(").append(')').toString());
	}
    }

    @Override
    final void method4377() {
	throw new IllegalStateException();
    }

    @Override
    void method4357(GraphicsToolkit class_ra, int i) {
	try {
	    Class387 class387 = aClass60_9910.method700(class_ra, 262144, true, true, (byte) -16);
	    if (null != class387) {
		Class217 class217 = method4337().aClass217_2599;
		int i_22_ = (int) class217.aFloat2451 >> 9;
		int i_23_ = (int) class217.aFloat2454 >> 9;
		aClass60_9910.method701(class_ra, class387, method4347(), i_22_, i_22_, i_23_, i_23_, false, 1937927561);
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("wm.bb(").append(')').toString());
	}
    }

    @Override
    public int method4379() {
	return aClass60_9910.method705(1926384337);
    }

    @Override
    final boolean method4386() {
	return false;
    }

    @Override
    boolean method4369() {
	return this.aBoolean9907;
    }

    @Override
    boolean method4382() {
	return this.aBoolean9907;
    }

    @Override
    boolean method4349() {
	return this.aBoolean9907;
    }

    @Override
    boolean method4383() {
	return this.aBoolean9907;
    }

    @Override
    boolean method4351() {
	return this.aBoolean9907;
    }

    @Override
    final boolean method4384() {
	return false;
    }

    @Override
    boolean method4353() {
	return false;
    }

    @Override
    public void method31(byte i) {
	try {
	    /* empty */
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("wm.p(").append(')').toString());
	}
    }

    @Override
    Class335 method4370(GraphicsToolkit class_ra) {
	Class387 class387 = aClass60_9910.method700(class_ra, 2048, false, true, (byte) -3);
	if (null == class387)
	    return null;
	Class222 class222 = method4347();
	Class235 class235 = method4337();
	Class335 class335 = Class73.method818(this.aBoolean9908, 1939994642);
	int i = (int) class235.aClass217_2599.aFloat2451 >> 9;
	int i_24_ = (int) class235.aClass217_2599.aFloat2454 >> 9;
	aClass60_9910.method701(class_ra, class387, class222, i, i, i_24_, i_24_, true, 1228885360);
	class387.method4739(class222, aClass302_Sub1Array7726[0], 0);
	if (null != aClass60_9910.aClass351_602) {
	    Class69 class69 = aClass60_9910.aClass351_602.method4229();
	    class_ra.method5042(class69);
	}
	this.aBoolean9907 = class387.i() || null != aClass60_9910.aClass351_602;
	if (null == this.aClass334_9909)
	    this.aClass334_9909 = Class472.method6063((int) class235.aClass217_2599.aFloat2451, (int) class235.aClass217_2599.aFloat2455, (int) class235.aClass217_2599.aFloat2454, class387, 1982662132);
	else
	    Class264_Sub2.method2507((this.aClass334_9909), (int) class235.aClass217_2599.aFloat2451, (int) class235.aClass217_2599.aFloat2455, (int) class235.aClass217_2599.aFloat2454, class387, (byte) 26);
	return class335;
    }

    public int method4497() {
	return aClass60_9910.method699(2101198661);
    }

    @Override
    final boolean method4400() {
	return false;
    }

    static final void method4498(Class403 class403, byte i) {
	try {
	    Class25 class25 = Class429.method5760((short) 512);
	    Class298_Sub36 class298_sub36 = Class18.method359(OutcommingPacket.FRIEND_CHAT_QUICK_CHAT_MESSAGE, class25.aClass449_330, (byte) 18);
	    class298_sub36.out.writeByte(0);
	    int i_25_ = (class298_sub36.out.index * 385051775);
	    class298_sub36.out.writeByte(2);
	    class298_sub36.out.writeShort(class403.aClass177_5243.anInt1787 * -2034569943, 16711935);
	    class403.aClass177_5243.aClass298_Sub37_Sub14_1788.method3464(class298_sub36.out, class403.aClass177_5243.anIntArray1789, 1820223429);
	    class298_sub36.out.method3649(385051775 * (class298_sub36.out.index) - i_25_, (byte) -61);
	    class25.method390(class298_sub36, (byte) -22);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("wm.adk(").append(')').toString());
	}
    }

    public static void method4499(byte i) {
	try {
	    Class439.method5851(17, 1188643494);
	    Class173.method1826(-1182326447);
	    System.gc();
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("wm.lx(").append(')').toString());
	}
    }

    static final void method4500(Class403 class403, short i) {
	try {
	    int i_26_ = (class403.anIntArray5244[((class403.anInt5239 -= -391880689) * 681479919)]);
	    int[] is = Interface.method3576(i_26_, 1679514983);
	    Class425.method5741(is, 0, class403.anIntArray5244, 681479919 * class403.anInt5239, 3);
	    class403.anInt5239 += -1175642067;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("wm.akm(").append(')').toString());
	}
    }
}
