
/* Class52_Sub1_Sub3_Sub1 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import jaggl.OpenGL;

public class Class52_Sub1_Sub3_Sub1 extends Class52_Sub1_Sub3 {
    int anInt9969;
    Class_ra_Sub3_Sub1 aClass_ra_Sub3_Sub1_9970;
    static int anInt9971 = 16;
    int anInt9972;
    int anInt9973;
    int anInt9974;
    Interface8_Impl1_Impl1_Impl3 anInterface8_Impl1_Impl1_Impl3_9975;
    int anInt9976;
    Interface8_Impl1_Impl1_Impl3[] anInterface8_Impl1_Impl1_Impl3Array9977 = new Interface8_Impl1_Impl1_Impl3[4];

    @Override
    public boolean method560() {
	int i = OpenGL.glCheckFramebufferStatusEXT(36160);
	if (i != 36053)
	    return false;
	return true;
    }

    @Override
    public int method545() {
	return this.anInt9976;
    }

    @Override
    public int method552() {
	return this.anInt9973;
    }

    @Override
    public void method563(int i, Interface8_Impl1_Impl1 interface8_impl1_impl1) {
	int i_0_ = 1 << i;
	Interface8_Impl1_Impl1_Impl3 interface8_impl1_impl1_impl3 = (Interface8_Impl1_Impl1_Impl3) interface8_impl1_impl1;
	if (interface8_impl1_impl1 != null) {
	    if (this.anInt9972 != 0) {
		if ((this.anInt9976 != interface8_impl1_impl1_impl3.a()) || (this.anInt9973 != interface8_impl1_impl1_impl3.f()))
		    throw new RuntimeException();
	    } else {
		this.anInt9973 = interface8_impl1_impl1_impl3.f();
		this.anInt9976 = interface8_impl1_impl1_impl3.a();
		method573();
	    }
	    this.anInt9972 |= i_0_;
	    this.anInterface8_Impl1_Impl1_Impl3Array9977[i] = interface8_impl1_impl1_impl3;
	} else {
	    this.anInt9972 &= i_0_ ^ 0xffffffff;
	    this.anInterface8_Impl1_Impl1_Impl3Array9977[i] = null;
	    if (this.anInt9972 == 0) {
		this.anInt9973 = 0;
		this.anInt9976 = 0;
	    }
	}
	if (this == this.aClass_ra_Sub3_Sub1_9970.method4992((short) -4810))
	    method575(i);
	else
	    this.anInt9969 |= i_0_;
    }

    void method574() {
	if (this.anInterface8_Impl1_Impl1_Impl3_9975 == null)
	    OpenGL.glFramebufferRenderbufferEXT(36160, 36096, 36161, 0);
	else
	    this.anInterface8_Impl1_Impl1_Impl3_9975.method167(36096);
    }

    @Override
    public void method138() {
	if (this.anInt9974 != 0) {
	    this.aClass_ra_Sub3_Sub1_9970.method5528(this.anInt9974);
	    this.anInt9974 = 0;
	}
    }

    Class52_Sub1_Sub3_Sub1(Class_ra_Sub3_Sub1 class_ra_sub3_sub1) {
	super(class_ra_sub3_sub1);
	this.aClass_ra_Sub3_Sub1_9970 = class_ra_sub3_sub1;
	int[] is = new int[1];
	OpenGL.glGenFramebuffersEXT(1, is, 0);
	this.anInt9974 = is[0];
    }

    @Override
    public void method135() {
	if (this.anInt9974 != 0) {
	    this.aClass_ra_Sub3_Sub1_9970.method5528(this.anInt9974);
	    this.anInt9974 = 0;
	}
    }

    @Override
    boolean method136() {
	OpenGL.glBindFramebufferEXT(36160, this.anInt9974);
	for (int i = 0; i < 4; i++) {
	    if ((this.anInt9969 & 1 << i) != 0)
		method575(i);
	}
	if ((this.anInt9969 & 0x10) != 0)
	    method574();
	this.anInt9969 = 0;
	return super.method136();
    }

    @Override
    boolean method546() {
	OpenGL.glBindFramebufferEXT(36160, 0);
	return true;
    }

    @Override
    public int method549() {
	return this.anInt9976;
    }

    void method575(int i) {
	Interface8_Impl1_Impl1_Impl3 interface8_impl1_impl1_impl3 = (this.anInterface8_Impl1_Impl1_Impl3Array9977[i]);
	if (interface8_impl1_impl1_impl3 == null)
	    OpenGL.glFramebufferRenderbufferEXT(36160, 36064 + i, 36161, 0);
	else
	    interface8_impl1_impl1_impl3.method167(36064 + i);
    }

    @Override
    public int method544() {
	return this.anInt9973;
    }

    @Override
    public void method561(int i, Interface8_Impl1_Impl1 interface8_impl1_impl1) {
	int i_1_ = 1 << i;
	Interface8_Impl1_Impl1_Impl3 interface8_impl1_impl1_impl3 = (Interface8_Impl1_Impl1_Impl3) interface8_impl1_impl1;
	if (interface8_impl1_impl1 != null) {
	    if (this.anInt9972 != 0) {
		if ((this.anInt9976 != interface8_impl1_impl1_impl3.a()) || (this.anInt9973 != interface8_impl1_impl1_impl3.f()))
		    throw new RuntimeException();
	    } else {
		this.anInt9973 = interface8_impl1_impl1_impl3.f();
		this.anInt9976 = interface8_impl1_impl1_impl3.a();
		method573();
	    }
	    this.anInt9972 |= i_1_;
	    this.anInterface8_Impl1_Impl1_Impl3Array9977[i] = interface8_impl1_impl1_impl3;
	} else {
	    this.anInt9972 &= i_1_ ^ 0xffffffff;
	    this.anInterface8_Impl1_Impl1_Impl3Array9977[i] = null;
	    if (this.anInt9972 == 0) {
		this.anInt9973 = 0;
		this.anInt9976 = 0;
	    }
	}
	if (this == this.aClass_ra_Sub3_Sub1_9970.method4992((short) -5292))
	    method575(i);
	else
	    this.anInt9969 |= i_1_;
    }

    @Override
    public void method562(int i, Interface8_Impl1_Impl1 interface8_impl1_impl1) {
	int i_2_ = 1 << i;
	Interface8_Impl1_Impl1_Impl3 interface8_impl1_impl1_impl3 = (Interface8_Impl1_Impl1_Impl3) interface8_impl1_impl1;
	if (interface8_impl1_impl1 != null) {
	    if (this.anInt9972 != 0) {
		if ((this.anInt9976 != interface8_impl1_impl1_impl3.a()) || (this.anInt9973 != interface8_impl1_impl1_impl3.f()))
		    throw new RuntimeException();
	    } else {
		this.anInt9973 = interface8_impl1_impl1_impl3.f();
		this.anInt9976 = interface8_impl1_impl1_impl3.a();
		method573();
	    }
	    this.anInt9972 |= i_2_;
	    this.anInterface8_Impl1_Impl1_Impl3Array9977[i] = interface8_impl1_impl1_impl3;
	} else {
	    this.anInt9972 &= i_2_ ^ 0xffffffff;
	    this.anInterface8_Impl1_Impl1_Impl3Array9977[i] = null;
	    if (this.anInt9972 == 0) {
		this.anInt9973 = 0;
		this.anInt9976 = 0;
	    }
	}
	if (this == this.aClass_ra_Sub3_Sub1_9970.method4992((short) 4564))
	    method575(i);
	else
	    this.anInt9969 |= i_2_;
    }

    @Override
    public void method564(Interface8_Impl1_Impl2 interface8_impl1_impl2) {
	Interface8_Impl1_Impl1_Impl3 interface8_impl1_impl1_impl3 = (Interface8_Impl1_Impl1_Impl3) interface8_impl1_impl2;
	if (interface8_impl1_impl2 != null) {
	    if (this.anInt9972 != 0) {
		if ((this.anInt9976 != interface8_impl1_impl1_impl3.a()) || (this.anInt9973 != interface8_impl1_impl1_impl3.f()))
		    throw new RuntimeException();
	    } else {
		this.anInt9973 = interface8_impl1_impl1_impl3.f();
		this.anInt9976 = interface8_impl1_impl1_impl3.a();
		method573();
	    }
	    this.anInt9972 |= 0x10;
	    this.anInterface8_Impl1_Impl1_Impl3_9975 = interface8_impl1_impl1_impl3;
	} else {
	    this.anInt9972 &= ~0x10;
	    this.anInterface8_Impl1_Impl1_Impl3_9975 = null;
	    if (this.anInt9972 == 0) {
		this.anInt9973 = 0;
		this.anInt9976 = 0;
	    }
	}
	if (this == this.aClass_ra_Sub3_Sub1_9970.method4992((short) 18572))
	    method574();
	else
	    this.anInt9969 |= 0x10;
    }

    @Override
    public boolean method557() {
	int i = OpenGL.glCheckFramebufferStatusEXT(36160);
	if (i != 36053)
	    return false;
	return true;
    }

    @Override
    public boolean method559() {
	int i = OpenGL.glCheckFramebufferStatusEXT(36160);
	if (i != 36053)
	    return false;
	return true;
    }

    @Override
    boolean method134() {
	OpenGL.glBindFramebufferEXT(36160, this.anInt9974);
	for (int i = 0; i < 4; i++) {
	    if ((this.anInt9969 & 1 << i) != 0)
		method575(i);
	}
	if ((this.anInt9969 & 0x10) != 0)
	    method574();
	this.anInt9969 = 0;
	return super.method136();
    }

    @Override
    boolean method548() {
	OpenGL.glBindFramebufferEXT(36160, 0);
	return true;
    }

    @Override
    public void method137() {
	if (this.anInt9974 != 0) {
	    this.aClass_ra_Sub3_Sub1_9970.method5528(this.anInt9974);
	    this.anInt9974 = 0;
	}
    }

    @Override
    public void method558(Interface8_Impl1_Impl2 interface8_impl1_impl2) {
	Interface8_Impl1_Impl1_Impl3 interface8_impl1_impl1_impl3 = (Interface8_Impl1_Impl1_Impl3) interface8_impl1_impl2;
	if (interface8_impl1_impl2 != null) {
	    if (this.anInt9972 != 0) {
		if ((this.anInt9976 != interface8_impl1_impl1_impl3.a()) || (this.anInt9973 != interface8_impl1_impl1_impl3.f()))
		    throw new RuntimeException();
	    } else {
		this.anInt9973 = interface8_impl1_impl1_impl3.f();
		this.anInt9976 = interface8_impl1_impl1_impl3.a();
		method573();
	    }
	    this.anInt9972 |= 0x10;
	    this.anInterface8_Impl1_Impl1_Impl3_9975 = interface8_impl1_impl1_impl3;
	} else {
	    this.anInt9972 &= ~0x10;
	    this.anInterface8_Impl1_Impl1_Impl3_9975 = null;
	    if (this.anInt9972 == 0) {
		this.anInt9973 = 0;
		this.anInt9976 = 0;
	    }
	}
	if (this == this.aClass_ra_Sub3_Sub1_9970.method4992((short) -4671))
	    method574();
	else
	    this.anInt9969 |= 0x10;
    }

    @Override
    public int method550() {
	return this.anInt9976;
    }

    @Override
    public int method551() {
	return this.anInt9976;
    }

    @Override
    public int method547() {
	return this.anInt9976;
    }

    @Override
    public boolean method565() {
	int i = OpenGL.glCheckFramebufferStatusEXT(36160);
	if (i != 36053)
	    return false;
	return true;
    }
}
