/* Class298_Sub40_Sub7 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class298_Sub40_Sub7 extends Class298_Sub40 {
    Class160 aClass160_9709;
    int anInt9710;

    @Override
    void method3512(RsByteBuffer class298_sub53) {
	this.anInt9710 = class298_sub53.readUnsignedShort() * -489279757;
    }

    @Override
    void method3508(RsByteBuffer class298_sub53, int i) {
	try {
	    this.anInt9710 = class298_sub53.readUnsignedShort() * -489279757;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("agm.a(").append(')').toString());
	}
    }

    @Override
    void method3510(ClanSettings class162, byte i) {
	try {
	    class162.deleteMember((2034735675 * this.anInt9710), (byte) -37);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("agm.f(").append(')').toString());
	}
    }

    @Override
    void method3511(ClanSettings class162) {
	class162.deleteMember((2034735675 * this.anInt9710), (byte) 51);
    }

    Class298_Sub40_Sub7(Class160 class160) {
	super();
	this.aClass160_9709 = class160;
	this.anInt9710 = 489279757;
    }

    @Override
    void method3509(RsByteBuffer class298_sub53) {
	this.anInt9710 = class298_sub53.readUnsignedShort() * -489279757;
    }
}
