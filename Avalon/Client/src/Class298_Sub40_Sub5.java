/* Class298_Sub40_Sub5 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class298_Sub40_Sub5 extends Class298_Sub40 {
    long aLong9699;
    int anInt9700;
    String aString9701;
    Class160 aClass160_9702;

    Class298_Sub40_Sub5(Class160 class160) {
	super();
	this.aClass160_9702 = class160;
	this.aLong9699 = 2714659426994184815L;
	this.aString9701 = null;
	this.anInt9700 = 0;
    }

    @Override
    void method3508(RsByteBuffer class298_sub53, int i) {
	try {
	    if (class298_sub53.readUnsignedByte() != 255) {
		class298_sub53.index -= 116413311;
		this.aLong9699 = (class298_sub53.readLong((short) 27016) * -2714659426994184815L);
	    }
	    this.aString9701 = class298_sub53.readJNullString(-517364695);
	    this.anInt9700 = class298_sub53.readUnsignedShort() * 70895925;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("agf.a(").append(')').toString());
	}
    }

    @Override
    void method3510(ClanSettings class162, byte i) {
	try {
	    class162.addMember(this.aString9701, (this.aLong9699 * -1747233514558995599L), (this.anInt9700 * 1029636381), -783761378);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("agf.f(").append(')').toString());
	}
    }

    @Override
    void method3511(ClanSettings class162) {
	class162.addMember(this.aString9701, (this.aLong9699 * -1747233514558995599L), (this.anInt9700 * 1029636381), -783761378);
    }

    @Override
    void method3512(RsByteBuffer class298_sub53) {
	if (class298_sub53.readUnsignedByte() != 255) {
	    class298_sub53.index -= 116413311;
	    this.aLong9699 = (class298_sub53.readLong((short) 12554) * -2714659426994184815L);
	}
	this.aString9701 = class298_sub53.readJNullString(-517364695);
	this.anInt9700 = class298_sub53.readUnsignedShort() * 70895925;
    }

    @Override
    void method3509(RsByteBuffer class298_sub53) {
	if (class298_sub53.readUnsignedByte() != 255) {
	    class298_sub53.index -= 116413311;
	    this.aLong9699 = (class298_sub53.readLong((short) 5054) * -2714659426994184815L);
	}
	this.aString9701 = class298_sub53.readJNullString(-517364695);
	this.anInt9700 = class298_sub53.readUnsignedShort() * 70895925;
    }
}
