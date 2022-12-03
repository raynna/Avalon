/* Class82_Sub18 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class82_Sub18 extends Class82 {
    int anInt6890;
    int anInt6891;
    public static Class405 aClass405_6892;

    @Override
    public void method869() {
	Class136.method1495(this.anInt6890 * 115033111, 0, this.anInt6891 * -1734052405, -649427988);
    }

    @Override
    public void method866(int i) {
	try {
	    Class136.method1495(this.anInt6890 * 115033111, 0, this.anInt6891 * -1734052405, -649427988);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("yl.f(").append(')').toString());
	}
    }

    @Override
    public void method868() {
	Class136.method1495(this.anInt6890 * 115033111, 0, this.anInt6891 * -1734052405, -649427988);
    }

    Class82_Sub18(RsByteBuffer class298_sub53) {
	super(class298_sub53);
	this.anInt6890 = class298_sub53.readUnsignedShort() * 2089431975;
	this.anInt6891 = class298_sub53.readUnsignedByte() * 779683811;
    }

    public static final void method922(byte i) {
	try {
	    if (!client.aBoolean8762) {
		client.aFloat8759 += (24.0F - client.aFloat8759) / 2.0F;
		client.aBoolean8763 = true;
		client.aBoolean8762 = true;
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("yl.hc(").append(')').toString());
	}
    }

    static final void method923(Class403 class403, short i) {
	try {
	    class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919 - 1)] = client.aBoolean8638 ? 1 : 0;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("yl.afb(").append(')').toString());
	}
    }
}
