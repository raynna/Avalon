/* Class298_Sub40_Sub2 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class298_Sub40_Sub2 extends Class298_Sub40 {
    Class160 aClass160_9690;
    String aString9691;
    long aLong9692;
    public static int anInt9693;

    @Override
    void method3510(ClanSettings class162, byte i) {
	try {
	    class162.addBannedMember((5516397286925612321L * this.aLong9692), this.aString9691, 534643319);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("afp.f(").append(')').toString());
	}
    }

    @Override
    void method3508(RsByteBuffer class298_sub53, int i) {
	try {
	    if (class298_sub53.readUnsignedByte() != 255) {
		class298_sub53.index -= 116413311;
		this.aLong9692 = (class298_sub53.readLong((short) 12001) * 6229729792981765857L);
	    }
	    this.aString9691 = class298_sub53.readJNullString(-517364695);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("afp.a(").append(')').toString());
	}
    }

    @Override
    void method3509(RsByteBuffer class298_sub53) {
	if (class298_sub53.readUnsignedByte() != 255) {
	    class298_sub53.index -= 116413311;
	    this.aLong9692 = (class298_sub53.readLong((short) 8513) * 6229729792981765857L);
	}
	this.aString9691 = class298_sub53.readJNullString(-517364695);
    }

    @Override
    void method3511(ClanSettings class162) {
	class162.addBannedMember((5516397286925612321L * this.aLong9692), this.aString9691, 1643740294);
    }

    @Override
    void method3512(RsByteBuffer class298_sub53) {
	if (class298_sub53.readUnsignedByte() != 255) {
	    class298_sub53.index -= 116413311;
	    this.aLong9692 = (class298_sub53.readLong((short) 19557) * 6229729792981765857L);
	}
	this.aString9691 = class298_sub53.readJNullString(-517364695);
    }

    Class298_Sub40_Sub2(Class160 class160) {
	super();
	this.aClass160_9690 = class160;
	this.aLong9692 = -6229729792981765857L;
	this.aString9691 = null;
    }
}
