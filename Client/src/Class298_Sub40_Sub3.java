/* Class298_Sub40_Sub3 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class298_Sub40_Sub3 extends Class298_Sub40 {
    String aString9694;
    int anInt9695;
    Class160 aClass160_9696;

    @Override
    void method3508(RsByteBuffer class298_sub53, int i) {
	try {
	    this.anInt9695 = class298_sub53.readInt((byte) -78) * 1400658899;
	    this.aString9694 = class298_sub53.readString(951209118);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aga.a(").append(')').toString());
	}
    }

    @Override
    void method3512(RsByteBuffer class298_sub53) {
	this.anInt9695 = class298_sub53.readInt((byte) -96) * 1400658899;
	this.aString9694 = class298_sub53.readString(-131087516);
    }

    @Override
    void method3510(ClanSettings class162, byte i) {
	try {
	    class162.method1760((this.anInt9695 * -419842981), this.aString9694, (byte) 24);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aga.f(").append(')').toString());
	}
    }

    @Override
    void method3511(ClanSettings class162) {
	class162.method1760((this.anInt9695 * -419842981), this.aString9694, (byte) 24);
    }

    Class298_Sub40_Sub3(Class160 class160) {
	super();
	this.aClass160_9696 = class160;
    }

    @Override
    void method3509(RsByteBuffer class298_sub53) {
	this.anInt9695 = class298_sub53.readInt((byte) -1) * 1400658899;
	this.aString9694 = class298_sub53.readString(-184200581);
    }
}
