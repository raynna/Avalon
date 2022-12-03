/* Class298_Sub40_Sub6 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class298_Sub40_Sub6 extends Class298_Sub40 {
    Class160 aClass160_9703;
    byte aByte9704;
    byte aByte9705;
    boolean aBoolean9706;
    byte aByte9707;
    byte aByte9708;

    @Override
    void method3509(RsByteBuffer class298_sub53) {
	this.aBoolean9706 = class298_sub53.readUnsignedByte() == 1;
	this.aByte9704 = class298_sub53.readByte(-12558881);
	this.aByte9705 = class298_sub53.readByte(-12558881);
	this.aByte9708 = class298_sub53.readByte(-12558881);
	this.aByte9707 = class298_sub53.readByte(-12558881);
    }

    @Override
    void method3508(RsByteBuffer class298_sub53, int i) {
	try {
	    this.aBoolean9706 = class298_sub53.readUnsignedByte() == 1;
	    this.aByte9704 = class298_sub53.readByte(-12558881);
	    this.aByte9705 = class298_sub53.readByte(-12558881);
	    this.aByte9708 = class298_sub53.readByte(-12558881);
	    this.aByte9707 = class298_sub53.readByte(-12558881);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("agi.a(").append(')').toString());
	}
    }

    Class298_Sub40_Sub6(Class160 class160) {
	super();
	this.aClass160_9703 = class160;
    }

    @Override
    void method3511(ClanSettings class162) {
	class162.aBoolean1656 = this.aBoolean9706;
	class162.aByte1672 = this.aByte9704;
	class162.aByte1658 = this.aByte9705;
	class162.aByte1659 = this.aByte9708;
	class162.aByte1660 = this.aByte9707;
    }

    @Override
    void method3510(ClanSettings class162, byte i) {
	try {
	    class162.aBoolean1656 = this.aBoolean9706;
	    class162.aByte1672 = this.aByte9704;
	    class162.aByte1658 = this.aByte9705;
	    class162.aByte1659 = this.aByte9708;
	    class162.aByte1660 = this.aByte9707;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("agi.f(").append(')').toString());
	}
    }

    @Override
    void method3512(RsByteBuffer class298_sub53) {
	this.aBoolean9706 = class298_sub53.readUnsignedByte() == 1;
	this.aByte9704 = class298_sub53.readByte(-12558881);
	this.aByte9705 = class298_sub53.readByte(-12558881);
	this.aByte9708 = class298_sub53.readByte(-12558881);
	this.aByte9707 = class298_sub53.readByte(-12558881);
    }
}
