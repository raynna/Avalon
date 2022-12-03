/* Class385 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class385 {
    Class217 aClass217_4129;
    float aFloat4130;
    float aFloat4131;
    float aFloat4132;
    int anInt4133;
    int anInt4134;
    int anInt4135;
    Class66 aClass66_4136;
    float aFloat4137;
    float aFloat4138;
    float aFloat4139;
    Class277 aClass277_4140;
    public static client aClient4141;
    static IndexedImage aClass89_4142;

    public Class385(RsByteBuffer class298_sub53, Class364 class364) {
	method4706(class298_sub53, class364, (short) 9475);
    }

    void method4704(Class385 class385_0_, byte i) {
	try {
	    this.anInt4133 = class385_0_.anInt4133 * 1;
	    this.aFloat4130 = class385_0_.aFloat4130;
	    this.aFloat4131 = class385_0_.aFloat4131;
	    this.aFloat4137 = class385_0_.aFloat4137;
	    this.aClass217_4129.method2013(class385_0_.aClass217_4129);
	    this.anInt4134 = class385_0_.anInt4134 * 1;
	    this.anInt4135 = 1 * class385_0_.anInt4135;
	    this.aClass66_4136 = class385_0_.aClass66_4136;
	    this.aFloat4139 = class385_0_.aFloat4139;
	    this.aFloat4138 = class385_0_.aFloat4138;
	    this.aFloat4132 = class385_0_.aFloat4132;
	    this.aClass277_4140 = class385_0_.aClass277_4140;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("pz.f(").append(')').toString());
	}
    }

    void method4705(GraphicsToolkit class_ra, Class385 class385_1_, Class385 class385_2_, float f, int i) {
	try {
	    this.anInt4133 = Class350.method4215((-1951302757 * class385_1_.anInt4133), (class385_2_.anInt4133 * -1951302757), f * 255.0F, -394247366) * 743535251;
	    this.aFloat4131 = (f * (class385_2_.aFloat4131 - class385_1_.aFloat4131) + class385_1_.aFloat4131);
	    this.aFloat4137 = ((class385_2_.aFloat4137 - class385_1_.aFloat4137) * f + class385_1_.aFloat4137);
	    this.aFloat4130 = (f * (class385_2_.aFloat4130 - class385_1_.aFloat4130) + class385_1_.aFloat4130);
	    this.aFloat4132 = ((class385_2_.aFloat4132 - class385_1_.aFloat4132) * f + class385_1_.aFloat4132);
	    this.aFloat4139 = (class385_1_.aFloat4139 + f * (class385_2_.aFloat4139 - class385_1_.aFloat4139));
	    this.aFloat4138 = (class385_1_.aFloat4138 + f * (class385_2_.aFloat4138 - class385_1_.aFloat4138));
	    this.anInt4134 = Class350.method4215((class385_1_.anInt4134 * -1269717659), (-1269717659 * class385_2_.anInt4134), f * 255.0F, 561967704) * -316996499;
	    this.anInt4135 = (1820271565 * (int) (class385_1_.anInt4135 * -1939701499 + f * ((class385_2_.anInt4135 * -1939701499) - (class385_1_.anInt4135) * -1939701499)));
	    if (class385_1_.aClass66_4136 != class385_2_.aClass66_4136)
		this.aClass66_4136 = class_ra.method5179((class385_1_.aClass66_4136), (class385_2_.aClass66_4136), f, this.aClass66_4136);
	    if (class385_2_.aClass277_4140 != class385_1_.aClass277_4140) {
		if (null == class385_1_.aClass277_4140) {
		    this.aClass277_4140 = class385_2_.aClass277_4140;
		    if (this.aClass277_4140 != null)
			this.aClass277_4140.method2583((int) (f * 255.0F), 0, -909735354);
		} else {
		    this.aClass277_4140 = class385_1_.aClass277_4140;
		    if (this.aClass277_4140 != null)
			this.aClass277_4140.method2583((int) (f * 255.0F), 255, 398258366);
		}
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("pz.b(").append(')').toString());
	}
    }

    public void method4706(RsByteBuffer class298_sub53, Class364 class364, short i) {
	try {
	    int i_3_ = class298_sub53.readUnsignedByte();
	    if (Class422_Sub25.aClass298_Sub48_8425.aClass422_Sub15_7560.method5684(-1934130167) == 1 && Class373.aClass_ra4071.method5048() > 0) {
		if (0 != (i_3_ & 0x1))
		    this.anInt4133 = class298_sub53.readInt((byte) 33) * 743535251;
		else
		    this.anInt4133 = -980012211 * Class364.anInt3952;
		if ((i_3_ & 0x2) != 0)
		    this.aFloat4130 = (class298_sub53.readUnsignedShort() / 256.0F);
		else
		    this.aFloat4130 = 1.1523438F;
		if (0 != (i_3_ & 0x4))
		    this.aFloat4131 = (class298_sub53.readUnsignedShort() / 256.0F);
		else
		    this.aFloat4131 = 0.69921875F;
		if (0 != (i_3_ & 0x8))
		    this.aFloat4137 = (class298_sub53.readUnsignedShort() / 256.0F);
		else
		    this.aFloat4137 = 1.2F;
	    } else {
		if ((i_3_ & 0x1) != 0)
		    class298_sub53.readInt((byte) 50);
		if ((i_3_ & 0x2) != 0)
		    class298_sub53.readUnsignedShort();
		if ((i_3_ & 0x4) != 0)
		    class298_sub53.readUnsignedShort();
		if ((i_3_ & 0x8) != 0)
		    class298_sub53.readUnsignedShort();
		this.anInt4133 = Class364.anInt3952 * -980012211;
		this.aFloat4137 = 1.2F;
		this.aFloat4131 = 0.69921875F;
		this.aFloat4130 = 1.1523438F;
	    }
	    if ((i_3_ & 0x10) != 0)
		this.aClass217_4129 = Class217.method2004(class298_sub53.readShort(2126608204), class298_sub53.readShort(1708860776), class298_sub53.readShort(1771057202));
	    else
		this.aClass217_4129 = Class217.method2004(-50.0F, -60.0F, -50.0F);
	    if ((i_3_ & 0x20) != 0)
		this.anInt4134 = class298_sub53.readInt((byte) 9) * -316996499;
	    else
		this.anInt4134 = Class364.anInt3933 * 1359380751;
	    if (0 != (i_3_ & 0x40))
		this.anInt4135 = class298_sub53.readUnsignedShort() * 1820271565;
	    else
		this.anInt4135 = 0;
	    if (0 != (i_3_ & 0x80)) {
		int i_4_ = class298_sub53.readUnsignedShort();
		int i_5_ = class298_sub53.readUnsignedShort();
		int i_6_ = class298_sub53.readUnsignedShort();
		int i_7_ = class298_sub53.readUnsignedShort();
		int i_8_ = class298_sub53.readUnsignedShort();
		int i_9_ = class298_sub53.readUnsignedShort();
		this.aClass66_4136 = class364.method4329(i_4_, i_5_, i_6_, i_7_, i_8_, i_9_, 257018874);
	    } else
		this.aClass66_4136 = Class82_Sub12.aClass66_6864;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("pz.p(").append(')').toString());
	}
    }

    public void method4707(RsByteBuffer class298_sub53, int i) {
	try {
	    this.aFloat4139 = class298_sub53.readUnsignedByte() * 8 / 255.0F;
	    this.aFloat4138 = class298_sub53.readUnsignedByte() * 8 / 255.0F;
	    this.aFloat4132 = (class298_sub53.readUnsignedByte() * 8 / 255.0F);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("pz.i(").append(')').toString());
	}
    }

    public Class385() {
	method4711((byte) 115);
    }

    boolean method4708(Class385 class385_10_, int i) {
	try {
	    return ((-1951302757 * class385_10_.anInt4133 == this.anInt4133 * -1951302757) && (this.aFloat4130 == class385_10_.aFloat4130) && (class385_10_.aFloat4131 == this.aFloat4131) && (class385_10_.aFloat4137 == this.aFloat4137) && (class385_10_.aFloat4138 == this.aFloat4138) && (this.aFloat4139 == class385_10_.aFloat4139) && (class385_10_.aFloat4132 == this.aFloat4132) && (-1269717659 * this.anInt4134 == class385_10_.anInt4134 * -1269717659) && (class385_10_.anInt4135 * -1939701499 == this.anInt4135 * -1939701499) && (class385_10_.aClass66_4136 == this.aClass66_4136) && (class385_10_.aClass277_4140 == this.aClass277_4140));
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("pz.d(").append(')').toString());
	}
    }

    public int method4709(int i) {
	try {
	    return -1269717659 * this.anInt4134;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("pz.u(").append(')').toString());
	}
    }

    public Class277 method4710(byte i) {
	try {
	    return this.aClass277_4140;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("pz.x(").append(')').toString());
	}
    }

    void method4711(byte i) {
	try {
	    this.anInt4133 = Class364.anInt3952 * -980012211;
	    this.aClass217_4129 = Class217.method2004(-50.0F, -60.0F, -50.0F);
	    this.aFloat4130 = 1.1523438F;
	    this.aFloat4131 = 0.69921875F;
	    this.aFloat4137 = 1.2F;
	    this.anInt4134 = Class364.anInt3933 * 1359380751;
	    this.anInt4135 = 0;
	    this.aClass66_4136 = Class82_Sub12.aClass66_6864;
	    this.aFloat4139 = 1.0F;
	    this.aFloat4138 = 0.25F;
	    this.aFloat4132 = 1.0F;
	    this.aClass277_4140 = Class254.aClass277_2789;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("pz.a(").append(')').toString());
	}
    }

    public void method4712(RsByteBuffer class298_sub53, Class364 class364, byte i) {
	try {
	    int i_11_ = class298_sub53.readUnsignedShort();
	    int i_12_ = class298_sub53.readShort(2107553233);
	    int i_13_ = class298_sub53.readShort(1662518170);
	    int i_14_ = class298_sub53.readShort(2045049677);
	    int i_15_ = class298_sub53.readUnsignedShort();
	    Class163.anInt1685 = 1827318333 * i_15_;
	    this.aClass277_4140 = class364.method4328(i_11_, i_12_, i_13_, i_14_, (short) -3311);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("pz.k(").append(')').toString());
	}
    }

    static final void method4713(Class403 class403, int i) {
	try {
	    class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919 - 1)] = client.aByte8959;
	    if (client.aByte8959 != -1)
		client.aByte8959 = (byte) -6;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("pz.abk(").append(')').toString());
	}
    }

    public static void method4714(int i) {
	try {
	    if (IcmpService_Sub1.anIcmpService_Sub1_8551 != null) {
		try {
		    IcmpService_Sub1.anIcmpService_Sub1_8551.quit();
		}
		catch (Throwable throwable) {
		    /* empty */
		}
		IcmpService_Sub1.anIcmpService_Sub1_8551 = null;
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("pz.d(").append(')').toString());
	}
    }

    static final void method4715(Class403 class403, int i) {
	try {
	    class403.anInt5241 -= 969361751;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("pz.aj(").append(')').toString());
	}
    }

    public static Class343_Sub1 method4716(byte i) {
	try {
	    Class474.anInt5977 = 0;
	    return Class365_Sub1_Sub5_Sub2.method4537(-1079868050);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("pz.i(").append(')').toString());
	}
    }
}
