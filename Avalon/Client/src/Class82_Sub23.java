/* Class82_Sub23 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class82_Sub23 extends Class82 {
    Class95 aClass95_6918;
    int anInt6919;
    int anInt6920;
    protected static Class295 aClass295_6921;

    Class82_Sub23(RsByteBuffer class298_sub53) {
	super(class298_sub53);
	this.anInt6920 = class298_sub53.readUnsignedShort() * 192120791;
	this.anInt6919 = class298_sub53.readUnsignedByte() * -1335683137;
    }

    @Override
    public void method869() {
	Class116.method1268(this.aClass95_6918, -915613633 * this.anInt6919, (byte) -39);
    }

    @Override
    boolean method870(int i) {
	try {
	    if (null == this.aClass95_6918)
		this.aClass95_6918 = new Class95(Class374_Sub1.aClass243_7731, (this.anInt6920 * -949920793));
	    return this.aClass95_6918.method1030((byte) 1);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("yv.p(").append(')').toString());
	}
    }

    @Override
    public void method868() {
	Class116.method1268(this.aClass95_6918, -915613633 * this.anInt6919, (byte) 16);
    }

    boolean method941() {
	if (null == this.aClass95_6918)
	    this.aClass95_6918 = new Class95(Class374_Sub1.aClass243_7731, this.anInt6920 * -949920793);
	return this.aClass95_6918.method1030((byte) 1);
    }

    @Override
    public void method866(int i) {
	try {
	    Class116.method1268(this.aClass95_6918, -915613633 * this.anInt6919, (byte) -9);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("yv.f(").append(')').toString());
	}
    }
}
