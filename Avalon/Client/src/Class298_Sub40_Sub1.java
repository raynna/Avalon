/* Class298_Sub40_Sub1 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class298_Sub40_Sub1 extends Class298_Sub40 {
    long aLong9688;
    String aString9689;

    @Override
    void method3511(ClanSettings class162) {
	class162.addMember(this.aString9689, (31082448682122173L * this.aLong9688), 0, -783761378);
    }

    Class298_Sub40_Sub1(Class160 class160) {
	super();
	this.aLong9688 = 9050845699687573611L;
	this.aString9689 = null;
    }

    @Override
    void method3510(ClanSettings class162, byte i) {
	try {
	    class162.addMember(this.aString9689, (31082448682122173L * this.aLong9688), 0, -783761378);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("afd.f(").append(')').toString());
	}
    }

    @Override
    void method3508(RsByteBuffer class298_sub53, int i) {
	try {
	    if (class298_sub53.readUnsignedByte() != 255) {
		class298_sub53.index -= 116413311;
		this.aLong9688 = (class298_sub53.readLong((short) 32060) * -9050845699687573611L);
	    }
	    this.aString9689 = class298_sub53.readJNullString(-517364695);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("afd.a(").append(')').toString());
	}
    }

    @Override
    void method3512(RsByteBuffer class298_sub53) {
	if (class298_sub53.readUnsignedByte() != 255) {
	    class298_sub53.index -= 116413311;
	    this.aLong9688 = (class298_sub53.readLong((short) 4817) * -9050845699687573611L);
	}
	this.aString9689 = class298_sub53.readJNullString(-517364695);
    }

    @Override
    void method3509(RsByteBuffer class298_sub53) {
	if (class298_sub53.readUnsignedByte() != 255) {
	    class298_sub53.index -= 116413311;
	    this.aLong9688 = (class298_sub53.readLong((short) 2363) * -9050845699687573611L);
	}
	this.aString9689 = class298_sub53.readJNullString(-517364695);
    }

    static final void method3514(Class403 class403, int i) {
	try {
	    class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919 - 1)] = Class282.method2624(-333964684).getType(694163818);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("afd.ahc(").append(')').toString());
	}
    }
}
