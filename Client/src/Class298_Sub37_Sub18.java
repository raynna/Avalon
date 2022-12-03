
/* Class298_Sub37_Sub18 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import jaggl.OpenGL;

public class Class298_Sub37_Sub18 extends Class298_Sub37 implements Interface8_Impl1_Impl1_Impl2 {
    int anInt10024;
    int anInt10025;
    int anInt10026;
    static int[] anIntArray10027 = new int[1];
    Class77 aClass77_10028;
    Class55 aClass55_10029;
    int anInt10030;
    Class_ra_Sub2 aClass_ra_Sub2_10031;

    @Override
    public int f() {
	return this.anInt10025;
    }

    @Override
    public void method1(int i) {
	OpenGL.glFramebufferRenderbufferEXT(36160, i, 36161, (this.anInt10024));
    }

    @Override
    public int a() {
	return this.anInt10026;
    }

    @Override
    public void method3(int i) {
	OpenGL.glFramebufferRenderbufferEXT(36160, i, 36161, (this.anInt10024));
    }

    @Override
    public void b() {
	if (this.anInt10024 > 0) {
	    int[] is = new int[1];
	    is[0] = this.anInt10024;
	    OpenGL.glDeleteRenderbuffersEXT(1, is, 0);
	    this.anInt10024 = 0;
	}
    }

    void method3491() {
	if (this.anInt10024 > 0) {
	    this.aClass_ra_Sub2_10031.method5268(this.anInt10024, this.anInt10030);
	    this.anInt10024 = 0;
	}
    }

    @Override
    public int p() {
	return this.anInt10026;
    }

    @Override
    public int i() {
	return this.anInt10026;
    }

    Class298_Sub37_Sub18(Class_ra_Sub2 class_ra_sub2, Class55 class55, Class77 class77, int i, int i_0_, int i_1_) {
	this.aClass_ra_Sub2_10031 = class_ra_sub2;
	this.anInt10026 = i;
	this.anInt10025 = i_0_;
	this.aClass55_10029 = class55;
	this.aClass77_10028 = class77;
	OpenGL.glGenRenderbuffersEXT(1, anIntArray10027, 0);
	this.anInt10024 = anIntArray10027[0];
	OpenGL.glBindRenderbufferEXT(36161, this.anInt10024);
	OpenGL.glRenderbufferStorageMultisampleEXT(36161, i_1_, Class_ra_Sub2.method5274((this.aClass55_10029), (this.aClass77_10028)), this.anInt10026, this.anInt10025);
	this.anInt10030 = (this.anInt10026 * this.anInt10025 * (this.aClass55_10029.anInt556 * 845115459) * (this.aClass77_10028.anInt724 * 685647847));
    }

    @Override
    public void d() {
	if (this.anInt10024 > 0) {
	    int[] is = new int[1];
	    is[0] = this.anInt10024;
	    OpenGL.glDeleteRenderbuffersEXT(1, is, 0);
	    this.anInt10024 = 0;
	}
    }

    @Override
    public void u() {
	if (this.anInt10024 > 0) {
	    int[] is = new int[1];
	    is[0] = this.anInt10024;
	    OpenGL.glDeleteRenderbuffersEXT(1, is, 0);
	    this.anInt10024 = 0;
	}
    }

    @Override
    public void x() {
	if (this.anInt10024 > 0) {
	    int[] is = new int[1];
	    is[0] = this.anInt10024;
	    OpenGL.glDeleteRenderbuffersEXT(1, is, 0);
	    this.anInt10024 = 0;
	}
    }

    @Override
    public void method2(int i) {
	OpenGL.glFramebufferRenderbufferEXT(36160, i, 36161, (this.anInt10024));
    }

    @Override
    public int k() {
	return this.anInt10025;
    }

    @Override
    public void method4(int i) {
	OpenGL.glFramebufferRenderbufferEXT(36160, i, 36161, (this.anInt10024));
    }

    Class298_Sub37_Sub18(Class_ra_Sub2 class_ra_sub2, Class55 class55, Class77 class77, int i, int i_2_) {
	this.aClass_ra_Sub2_10031 = class_ra_sub2;
	this.anInt10026 = i;
	this.anInt10025 = i_2_;
	this.aClass55_10029 = class55;
	this.aClass77_10028 = class77;
	OpenGL.glGenRenderbuffersEXT(1, anIntArray10027, 0);
	this.anInt10024 = anIntArray10027[0];
	OpenGL.glBindRenderbufferEXT(36161, this.anInt10024);
	OpenGL.glRenderbufferStorageEXT(36161, Class_ra_Sub2.method5274((this.aClass55_10029), (this.aClass77_10028)), this.anInt10026, this.anInt10025);
	this.anInt10030 = (this.anInt10026 * this.anInt10025 * (this.aClass55_10029.anInt556 * 845115459) * (this.aClass77_10028.anInt724 * 685647847));
    }
}
