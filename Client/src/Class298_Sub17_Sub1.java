/* Class298_Sub17_Sub1 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class298_Sub17_Sub1 extends Class298_Sub17 {
    int anInt9191;
    byte aByte9192;
    String aString9194;

    @Override
    void method2916(RsByteBuffer class298_sub53) {
	if (class298_sub53.readUnsignedByte() != 255) {
	    class298_sub53.index -= 116413311;
	    class298_sub53.readLong((short) 28399);
	}
	this.aString9194 = class298_sub53.readJNullString(-517364695);
	this.anInt9191 = class298_sub53.readUnsignedShort() * -1467351539;
	this.aByte9192 = class298_sub53.readByte(-12558881);
	class298_sub53.readLong((short) 23541);
    }

    Class298_Sub17_Sub1(Class152 class152) {
	super();
	this.aString9194 = null;
    }

    @Override
    void method2921(ClanChannel class298_sub25) {
	Class163 class163 = new Class163();
	class163.aString1681 = this.aString9194;
	class163.anInt1682 = this.anInt9191 * 1934144191;
	class163.aByte1683 = this.aByte9192;
	class298_sub25.method3096(class163, 1759515162);
    }

    @Override
    void method2919(ClanChannel class298_sub25) {
	Class163 class163 = new Class163();
	class163.aString1681 = this.aString9194;
	class163.anInt1682 = this.anInt9191 * 1934144191;
	class163.aByte1683 = this.aByte9192;
	class298_sub25.method3096(class163, -1986283944);
    }

    @Override
    void method2918(ClanChannel class298_sub25, int i) {
	try {
	    Class163 class163 = new Class163();
	    class163.aString1681 = this.aString9194;
	    class163.anInt1682 = this.anInt9191 * 1934144191;
	    class163.aByte1683 = this.aByte9192;
	    class298_sub25.method3096(class163, 1028626118);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("agh.f(").append(')').toString());
	}
    }

    @Override
    void method2920(RsByteBuffer class298_sub53) {
	if (class298_sub53.readUnsignedByte() != 255) {
	    class298_sub53.index -= 116413311;
	    class298_sub53.readLong((short) 3495);
	}
	this.aString9194 = class298_sub53.readJNullString(-517364695);
	this.anInt9191 = class298_sub53.readUnsignedShort() * -1467351539;
	this.aByte9192 = class298_sub53.readByte(-12558881);
	class298_sub53.readLong((short) 28080);
    }

    @Override
    void method2917(RsByteBuffer class298_sub53, int i) {
	try {
	    if (class298_sub53.readUnsignedByte() != 255) {
		class298_sub53.index -= 116413311;
		class298_sub53.readLong((short) 7794);
	    }
	    this.aString9194 = class298_sub53.readJNullString(-517364695);
	    this.anInt9191 = class298_sub53.readUnsignedShort() * -1467351539;
	    this.aByte9192 = class298_sub53.readByte(-12558881);
	    class298_sub53.readLong((short) 8524);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("agh.a(").append(')').toString());
	}
    }

    @Override
    void method2922(RsByteBuffer class298_sub53) {
	if (class298_sub53.readUnsignedByte() != 255) {
	    class298_sub53.index -= 116413311;
	    class298_sub53.readLong((short) 10216);
	}
	this.aString9194 = class298_sub53.readJNullString(-517364695);
	this.anInt9191 = class298_sub53.readUnsignedShort() * -1467351539;
	this.aByte9192 = class298_sub53.readByte(-12558881);
	class298_sub53.readLong((short) 9073);
    }
}
