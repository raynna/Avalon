
/* Class262 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import jaggl.OpenGL;

public class Class262 implements Interface8_Impl1_Impl1, Interface8_Impl1_Impl2, Interface8_Impl1_Impl1_Impl3 {
    int anInt10155;
    Class55 aClass55_10156;
    Class77 aClass77_10157;
    int anInt10158;
    Class_ra_Sub3_Sub1 aClass_ra_Sub3_Sub1_10159;
    int anInt10160;

    @Override
    public int a() {
	return this.anInt10158;
    }

    @Override
    public void method168(int i) {
	OpenGL.glFramebufferRenderbufferEXT(36160, i, 36161, this.anInt10155);
    }

    @Override
    public void method167(int i) {
	OpenGL.glFramebufferRenderbufferEXT(36160, i, 36161, this.anInt10155);
    }

    @Override
    public void b() {
	if (this.anInt10155 > 0) {
	    int[] is = new int[1];
	    is[0] = this.anInt10155;
	    OpenGL.glDeleteRenderbuffersEXT(1, is, 0);
	    this.anInt10155 = 0;
	}
    }

    void method2466() {
	if (this.anInt10155 > 0) {
	    this.aClass_ra_Sub3_Sub1_10159.method5530(this.anInt10155, (this.anInt10158 * this.anInt10160 * (this.aClass77_10157.anInt724 * 685647847) * (this.aClass55_10156.anInt556 * 845115459)));
	    this.anInt10155 = 0;
	}
    }

    @Override
    public void method165(int i) {
	OpenGL.glFramebufferRenderbufferEXT(36160, i, 36161, this.anInt10155);
    }

    @Override
    public int p() {
	return this.anInt10158;
    }

    @Override
    public int i() {
	return this.anInt10158;
    }

    @Override
    public int k() {
	return this.anInt10160;
    }

    @Override
    public void u() {
	if (this.anInt10155 > 0) {
	    int[] is = new int[1];
	    is[0] = this.anInt10155;
	    OpenGL.glDeleteRenderbuffersEXT(1, is, 0);
	    this.anInt10155 = 0;
	}
    }

    @Override
    public void x() {
	if (this.anInt10155 > 0) {
	    int[] is = new int[1];
	    is[0] = this.anInt10155;
	    OpenGL.glDeleteRenderbuffersEXT(1, is, 0);
	    this.anInt10155 = 0;
	}
    }

    @Override
    public void d() {
	if (this.anInt10155 > 0) {
	    int[] is = new int[1];
	    is[0] = this.anInt10155;
	    OpenGL.glDeleteRenderbuffersEXT(1, is, 0);
	    this.anInt10155 = 0;
	}
    }

    @Override
    public int f() {
	return this.anInt10160;
    }

    @Override
    public void method166(int i) {
	OpenGL.glFramebufferRenderbufferEXT(36160, i, 36161, this.anInt10155);
    }

    @Override
    public void method164(int i) {
	OpenGL.glFramebufferRenderbufferEXT(36160, i, 36161, this.anInt10155);
    }

    Class262(Class_ra_Sub3_Sub1 class_ra_sub3_sub1, Class55 class55, Class77 class77, int i, int i_0_) {
	this.aClass55_10156 = class55;
	this.aClass77_10157 = class77;
	this.anInt10158 = i;
	this.anInt10160 = i_0_;
	this.aClass_ra_Sub3_Sub1_10159 = class_ra_sub3_sub1;
	int[] is = new int[1];
	OpenGL.glGenRenderbuffersEXT(1, is, 0);
	this.anInt10155 = is[0];
	OpenGL.glBindRenderbufferEXT(36161, this.anInt10155);
	OpenGL.glRenderbufferStorageEXT(36161, Class_ra_Sub3_Sub1.method5533(this.aClass55_10156, this.aClass77_10157), i, i_0_);
    }
}
