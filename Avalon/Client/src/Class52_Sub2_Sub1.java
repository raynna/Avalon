
/* Class52_Sub2_Sub1 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.awt.Canvas;

public abstract class Class52_Sub2_Sub1 extends Class52_Sub2 {
    Canvas aCanvas9072;
    Class_ra_Sub1 aClass_ra_Sub1_9073;
    int anInt9074;
    int anInt9075;
    int[] anIntArray9076;
    float[] aFloatArray9077;
    boolean aBoolean9078;
    public static Canvas aCanvas9079;

    @Override
    final boolean method546() {
	try {
	    this.aBoolean9078 = false;
	    return true;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("afi.i(").append(')').toString());
	}
    }

    void method591(int i) {
	try {
	    this.anIntArray9076 = new int[(2061776189 * this.anInt9075 * (1038713159 * this.anInt9074))];
	    this.aFloatArray9077 = new float[(2061776189 * this.anInt9075 * (1038713159 * this.anInt9074))];
	    if (this.aBoolean9078)
		this.aClass_ra_Sub1_9073.method5207(1038713159 * this.anInt9074, 2061776189 * this.anInt9075, this.anIntArray9076, this.aFloatArray9077);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("afi.z(").append(')').toString());
	}
    }

    Class52_Sub2_Sub1(Class_ra_Sub1 class_ra_sub1, Canvas canvas, int i, int i_0_) {
	this.aCanvas9072 = canvas;
	this.aClass_ra_Sub1_9073 = class_ra_sub1;
	this.anInt9074 = -1172842377 * i;
	this.anInt9075 = -374060523 * i_0_;
    }

    @Override
    public int method547() {
	return this.anInt9074 * 1038713159;
    }

    @Override
    final boolean method136() {
	try {
	    this.aClass_ra_Sub1_9073.method5207(1038713159 * this.anInt9074, 2061776189 * this.anInt9075, this.anIntArray9076, this.aFloatArray9077);
	    this.aBoolean9078 = true;
	    return true;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("afi.p(").append(')').toString());
	}
    }

    @Override
    public int method552() {
	try {
	    return 2061776189 * this.anInt9075;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("afi.f(").append(')').toString());
	}
    }

    @Override
    public int method544() {
	return 2061776189 * this.anInt9075;
    }

    @Override
    final boolean method134() {
	this.aClass_ra_Sub1_9073.method5207(1038713159 * this.anInt9074, 2061776189 * this.anInt9075, this.anIntArray9076, this.aFloatArray9077);
	this.aBoolean9078 = true;
	return true;
    }

    @Override
    final boolean method548() {
	this.aBoolean9078 = false;
	return true;
    }

    @Override
    final void method582(int i, int i_1_) {
	try {
	    if (i != 1038713159 * this.anInt9074 || 2061776189 * this.anInt9075 != i_1_) {
		this.anInt9074 = -1172842377 * i;
		this.anInt9075 = -374060523 * i_1_;
		method591(251162115);
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("afi.e(").append(')').toString());
	}
    }

    @Override
    final void method583(int i, int i_2_) {
	if (i != 1038713159 * this.anInt9074 || 2061776189 * this.anInt9075 != i_2_) {
	    this.anInt9074 = -1172842377 * i;
	    this.anInt9075 = -374060523 * i_2_;
	    method591(251162115);
	}
    }

    @Override
    public int method549() {
	return this.anInt9074 * 1038713159;
    }

    @Override
    public int method545() {
	try {
	    return this.anInt9074 * 1038713159;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("afi.a(").append(')').toString());
	}
    }

    @Override
    public int method551() {
	return this.anInt9074 * 1038713159;
    }

    @Override
    public int method550() {
	return this.anInt9074 * 1038713159;
    }
}
