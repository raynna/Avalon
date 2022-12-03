
/* Class52_Sub1_Sub2 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import jaggl.OpenGL;

public class Class52_Sub1_Sub2 extends Class52_Sub1 {
    int anInt9057;
    Interface8_Impl1_Impl1_Impl2[] anInterface8_Impl1_Impl1_Impl2Array9058 = new Interface8_Impl1_Impl1_Impl2[4];
    static int anInt9059 = 16;
    int anInt9060;
    Class_ra_Sub2 aClass_ra_Sub2_9061;
    int anInt9062;
    int anInt9063;
    Interface8_Impl1_Impl1_Impl2 anInterface8_Impl1_Impl1_Impl2_9064;
    int anInt9065;

    @Override
    public int method547() {
	return this.anInt9060;
    }

    @Override
    public int method545() {
	return this.anInt9060;
    }

    @Override
    public int method552() {
	return this.anInt9065;
    }

    @Override
    public void method563(int i, Interface8_Impl1_Impl1 interface8_impl1_impl1) {
	int i_0_ = 1 << i;
	Interface8_Impl1_Impl1_Impl2 interface8_impl1_impl1_impl2 = (Interface8_Impl1_Impl1_Impl2) interface8_impl1_impl1;
	if (interface8_impl1_impl1 != null) {
	    if ((this.anInt9062 | i_0_) != i_0_) {
		if ((this.anInt9060 != interface8_impl1_impl1_impl2.a()) || (this.anInt9065 != interface8_impl1_impl1_impl2.f()))
		    throw new RuntimeException();
	    } else {
		this.anInt9065 = interface8_impl1_impl1_impl2.f();
		this.anInt9060 = interface8_impl1_impl1_impl2.a();
		if (this == this.aClass_ra_Sub2_9061.method4992((short) -4129))
		    this.aClass_ra_Sub2_9061.method5257();
	    }
	    this.anInt9062 |= i_0_;
	    this.anInterface8_Impl1_Impl1_Impl2Array9058[i] = interface8_impl1_impl1_impl2;
	} else {
	    this.anInt9062 &= i_0_ ^ 0xffffffff;
	    this.anInterface8_Impl1_Impl1_Impl2Array9058[i] = null;
	    if (this.anInt9062 == 0) {
		this.anInt9065 = 0;
		this.anInt9060 = 0;
	    }
	}
	if (this == this.aClass_ra_Sub2_9061.method4992((short) -22905))
	    method569(i);
	else
	    this.anInt9063 |= i_0_;
    }

    @Override
    public void method558(Interface8_Impl1_Impl2 interface8_impl1_impl2) {
	Interface8_Impl1_Impl1_Impl2 interface8_impl1_impl1_impl2 = (Interface8_Impl1_Impl1_Impl2) interface8_impl1_impl2;
	if (interface8_impl1_impl2 != null) {
	    if ((this.anInt9062 | 0x10) != 16) {
		if ((this.anInt9060 != interface8_impl1_impl1_impl2.a()) || (this.anInt9065 != interface8_impl1_impl1_impl2.f()))
		    throw new RuntimeException();
	    } else {
		this.anInt9065 = interface8_impl1_impl1_impl2.f();
		this.anInt9060 = interface8_impl1_impl1_impl2.a();
		if (this == this.aClass_ra_Sub2_9061.method4992((short) -7371))
		    this.aClass_ra_Sub2_9061.method5257();
	    }
	    this.anInt9062 |= 0x10;
	    this.anInterface8_Impl1_Impl1_Impl2_9064 = interface8_impl1_impl1_impl2;
	} else {
	    this.anInt9062 &= ~0x10;
	    this.anInterface8_Impl1_Impl1_Impl2_9064 = null;
	    if (this.anInt9062 == 0) {
		this.anInt9065 = 0;
		this.anInt9060 = 0;
	    }
	}
	if (this == this.aClass_ra_Sub2_9061.method4992((short) 16488))
	    method571();
	else
	    this.anInt9063 |= 0x10;
    }

    void method569(int i) {
	Interface8_Impl1_Impl1_Impl2 interface8_impl1_impl1_impl2 = (this.anInterface8_Impl1_Impl1_Impl2Array9058[i]);
	if (interface8_impl1_impl1_impl2 == null)
	    OpenGL.glFramebufferRenderbufferEXT(36160, 36064 + i, 36161, 0);
	else
	    interface8_impl1_impl1_impl2.method3(36064 + i);
    }

    @Override
    public void method135() {
	if (this.anInt9057 != 0) {
	    this.aClass_ra_Sub2_9061.method5249(this.anInt9057);
	    this.anInt9057 = 0;
	}
    }

    @Override
    public boolean method560() {
	int i = OpenGL.glCheckFramebufferStatusEXT(36160);
	if (i != 36053)
	    return false;
	return true;
    }

    @Override
    boolean method136() {
	OpenGL.glBindFramebufferEXT(36160, this.anInt9057);
	for (int i = 0; i < 4; i++) {
	    if ((this.anInt9063 & 1 << i) != 0)
		method569(i);
	}
	if ((this.anInt9063 & 0x10) != 0)
	    method571();
	this.anInt9063 = 0;
	this.aClass_ra_Sub2_9061.method5225();
	return true;
    }

    @Override
    boolean method546() {
	OpenGL.glBindFramebufferEXT(36160, 0);
	return true;
    }

    void method570(int i, int i_1_, int i_2_, int i_3_, int i_4_, int i_5_, boolean bool, boolean bool_6_) {
	if (bool | bool_6_) {
	    int i_7_ = this.anInt9065;
	    int i_8_ = this.aClass_ra_Sub2_9061.method4992((short) -16350).method552();
	    int i_9_ = 0;
	    if (bool_6_)
		i_9_ |= 0x100;
	    if (bool)
		i_9_ |= 0x4000;
	    OpenGL.glBindFramebufferEXT(36008, this.anInt9057);
	    OpenGL.glBlitFramebufferEXT(i, i_7_ - i_1_ - i_3_, i + i_2_, i_7_ - i_1_, i_4_, i_8_ - i_5_ - i_3_, i_4_ + i_2_, i_8_ - i_5_, i_9_, 9728);
	    OpenGL.glBindFramebufferEXT(36008, 0);
	}
    }

    @Override
    boolean method134() {
	OpenGL.glBindFramebufferEXT(36160, this.anInt9057);
	for (int i = 0; i < 4; i++) {
	    if ((this.anInt9063 & 1 << i) != 0)
		method569(i);
	}
	if ((this.anInt9063 & 0x10) != 0)
	    method571();
	this.anInt9063 = 0;
	this.aClass_ra_Sub2_9061.method5225();
	return true;
    }

    @Override
    public int method551() {
	return this.anInt9060;
    }

    @Override
    public void method561(int i, Interface8_Impl1_Impl1 interface8_impl1_impl1) {
	int i_10_ = 1 << i;
	Interface8_Impl1_Impl1_Impl2 interface8_impl1_impl1_impl2 = (Interface8_Impl1_Impl1_Impl2) interface8_impl1_impl1;
	if (interface8_impl1_impl1 != null) {
	    if ((this.anInt9062 | i_10_) != i_10_) {
		if ((this.anInt9060 != interface8_impl1_impl1_impl2.a()) || (this.anInt9065 != interface8_impl1_impl1_impl2.f()))
		    throw new RuntimeException();
	    } else {
		this.anInt9065 = interface8_impl1_impl1_impl2.f();
		this.anInt9060 = interface8_impl1_impl1_impl2.a();
		if (this == this.aClass_ra_Sub2_9061.method4992((short) 3587))
		    this.aClass_ra_Sub2_9061.method5257();
	    }
	    this.anInt9062 |= i_10_;
	    this.anInterface8_Impl1_Impl1_Impl2Array9058[i] = interface8_impl1_impl1_impl2;
	} else {
	    this.anInt9062 &= i_10_ ^ 0xffffffff;
	    this.anInterface8_Impl1_Impl1_Impl2Array9058[i] = null;
	    if (this.anInt9062 == 0) {
		this.anInt9065 = 0;
		this.anInt9060 = 0;
	    }
	}
	if (this == this.aClass_ra_Sub2_9061.method4992((short) 8575))
	    method569(i);
	else
	    this.anInt9063 |= i_10_;
    }

    @Override
    public void method562(int i, Interface8_Impl1_Impl1 interface8_impl1_impl1) {
	int i_11_ = 1 << i;
	Interface8_Impl1_Impl1_Impl2 interface8_impl1_impl1_impl2 = (Interface8_Impl1_Impl1_Impl2) interface8_impl1_impl1;
	if (interface8_impl1_impl1 != null) {
	    if ((this.anInt9062 | i_11_) != i_11_) {
		if ((this.anInt9060 != interface8_impl1_impl1_impl2.a()) || (this.anInt9065 != interface8_impl1_impl1_impl2.f()))
		    throw new RuntimeException();
	    } else {
		this.anInt9065 = interface8_impl1_impl1_impl2.f();
		this.anInt9060 = interface8_impl1_impl1_impl2.a();
		if (this == this.aClass_ra_Sub2_9061.method4992((short) 26777))
		    this.aClass_ra_Sub2_9061.method5257();
	    }
	    this.anInt9062 |= i_11_;
	    this.anInterface8_Impl1_Impl1_Impl2Array9058[i] = interface8_impl1_impl1_impl2;
	} else {
	    this.anInt9062 &= i_11_ ^ 0xffffffff;
	    this.anInterface8_Impl1_Impl1_Impl2Array9058[i] = null;
	    if (this.anInt9062 == 0) {
		this.anInt9065 = 0;
		this.anInt9060 = 0;
	    }
	}
	if (this == this.aClass_ra_Sub2_9061.method4992((short) -1151))
	    method569(i);
	else
	    this.anInt9063 |= i_11_;
    }

    @Override
    public void method564(Interface8_Impl1_Impl2 interface8_impl1_impl2) {
	Interface8_Impl1_Impl1_Impl2 interface8_impl1_impl1_impl2 = (Interface8_Impl1_Impl1_Impl2) interface8_impl1_impl2;
	if (interface8_impl1_impl2 != null) {
	    if ((this.anInt9062 | 0x10) != 16) {
		if ((this.anInt9060 != interface8_impl1_impl1_impl2.a()) || (this.anInt9065 != interface8_impl1_impl1_impl2.f()))
		    throw new RuntimeException();
	    } else {
		this.anInt9065 = interface8_impl1_impl1_impl2.f();
		this.anInt9060 = interface8_impl1_impl1_impl2.a();
		if (this == this.aClass_ra_Sub2_9061.method4992((short) -16571))
		    this.aClass_ra_Sub2_9061.method5257();
	    }
	    this.anInt9062 |= 0x10;
	    this.anInterface8_Impl1_Impl1_Impl2_9064 = interface8_impl1_impl1_impl2;
	} else {
	    this.anInt9062 &= ~0x10;
	    this.anInterface8_Impl1_Impl1_Impl2_9064 = null;
	    if (this.anInt9062 == 0) {
		this.anInt9065 = 0;
		this.anInt9060 = 0;
	    }
	}
	if (this == this.aClass_ra_Sub2_9061.method4992((short) 11041))
	    method571();
	else
	    this.anInt9063 |= 0x10;
    }

    @Override
    public boolean method557() {
	int i = OpenGL.glCheckFramebufferStatusEXT(36160);
	if (i != 36053)
	    return false;
	return true;
    }

    @Override
    public boolean method565() {
	int i = OpenGL.glCheckFramebufferStatusEXT(36160);
	if (i != 36053)
	    return false;
	return true;
    }

    @Override
    public int method550() {
	return this.anInt9060;
    }

    @Override
    boolean method548() {
	OpenGL.glBindFramebufferEXT(36160, 0);
	return true;
    }

    void method571() {
	if (this.anInterface8_Impl1_Impl1_Impl2_9064 == null)
	    OpenGL.glFramebufferRenderbufferEXT(36160, 36096, 36161, 0);
	else
	    this.anInterface8_Impl1_Impl1_Impl2_9064.method3(36096);
    }

    @Override
    public void method138() {
	if (this.anInt9057 != 0) {
	    this.aClass_ra_Sub2_9061.method5249(this.anInt9057);
	    this.anInt9057 = 0;
	}
    }

    @Override
    public int method549() {
	return this.anInt9060;
    }

    @Override
    public void method137() {
	if (this.anInt9057 != 0) {
	    this.aClass_ra_Sub2_9061.method5249(this.anInt9057);
	    this.anInt9057 = 0;
	}
    }

    @Override
    public boolean method559() {
	int i = OpenGL.glCheckFramebufferStatusEXT(36160);
	if (i != 36053)
	    return false;
	return true;
    }

    void method572(int i) {
	OpenGL.glDrawBuffer(36064 + i);
    }

    @Override
    public int method544() {
	return this.anInt9065;
    }

    Class52_Sub1_Sub2(Class_ra_Sub2 class_ra_sub2) {
	if (!class_ra_sub2.framebuffer_obj)
	    throw new IllegalStateException("");
	this.aClass_ra_Sub2_9061 = class_ra_sub2;
	int[] is = new int[1];
	OpenGL.glGenFramebuffersEXT(1, is, 0);
	this.anInt9057 = is[0];
    }
}
