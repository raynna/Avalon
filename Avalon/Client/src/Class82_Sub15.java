/* Class82_Sub15 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class82_Sub15 extends Class82 {
    int anInt6876;
    int anInt6877;
    int anInt6878;
    int anInt6879;
    Class297 aClass297_6880;

    void method913() {
	if (this.aClass297_6880 != null) {
	    Class405.method4957(this.aClass297_6880, (short) 26607);
	    this.aClass297_6880 = null;
	}
    }

    @Override
    public void method866(int i) {
	try {
	    this.aClass297_6880 = (Class320.method3913(this.anInt6878 * 772949935, 190389055 * this.anInt6879, 0, 1498822233 * this.anInt6877, false, this.anInt6876 * -715391467, 1770406300));
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("yc.f(").append(')').toString());
	}
    }

    @Override
    void method867(int i) {
	try {
	    if (this.aClass297_6880 != null) {
		Class405.method4957(this.aClass297_6880, (short) 11811);
		this.aClass297_6880 = null;
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("yc.b(").append(')').toString());
	}
    }

    @Override
    public void method868() {
	this.aClass297_6880 = (Class320.method3913(this.anInt6878 * 772949935, 190389055 * this.anInt6879, 0, 1498822233 * this.anInt6877, false, this.anInt6876 * -715391467, 1772225108));
    }

    @Override
    public void method869() {
	this.aClass297_6880 = (Class320.method3913(this.anInt6878 * 772949935, 190389055 * this.anInt6879, 0, 1498822233 * this.anInt6877, false, this.anInt6876 * -715391467, 1251112831));
    }

    Class82_Sub15(RsByteBuffer class298_sub53) {
	super(class298_sub53);
	this.anInt6878 = class298_sub53.readUnsignedShort() * 1944678223;
	this.anInt6877 = class298_sub53.readUnsignedByte() * 2013114857;
	this.anInt6876 = class298_sub53.readUnsignedByte() * 146071869;
	this.anInt6879 = class298_sub53.readUnsignedByte() * -211004225;
    }

    static final void method914(Class403 class403, int i) {
	try {
	    Class390 class390 = (class403.aBoolean5261 ? class403.aClass390_5247 : class403.aClass390_5246);
	    IComponentDefinition class105 = class390.aClass105_4168;
	    class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919 - 1)] = class105.anInt1190 * 7329457;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("yc.pj(").append(')').toString());
	}
    }

    static void method915(byte i) {
	try {
	    Class323_Sub1.anIntArray7704[44] = 71;
	    Class323_Sub1.anIntArray7704[45] = 26;
	    Class323_Sub1.anIntArray7704[46] = 72;
	    Class323_Sub1.anIntArray7704[47] = 73;
	    Class323_Sub1.anIntArray7704[59] = 57;
	    Class323_Sub1.anIntArray7704[61] = 27;
	    Class323_Sub1.anIntArray7704[91] = 42;
	    Class323_Sub1.anIntArray7704[92] = 74;
	    Class323_Sub1.anIntArray7704[93] = 43;
	    Class323_Sub1.anIntArray7704[192] = 28;
	    Class323_Sub1.anIntArray7704[222] = 58;
	    Class323_Sub1.anIntArray7704[520] = 59;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("yc.t(").append(')').toString());
	}
    }

    static final void method916(Class403 class403, int i) {
	try {
	    int i_0_ = (class403.anIntArray5244[((class403.anInt5239 -= -391880689) * 681479919)]);
	    IComponentDefinition class105 = Class50.getIComponentDefinitions(i_0_, (byte) -74);
	    class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919 - 1)] = class105.anInt1297 * -407676483;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("yc.rp(").append(')').toString());
	}
    }
}
