
/* Class33 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import jaggl.OpenGL;

public class Class33 implements Interface8_Impl1_Impl1_Impl2 {
    int anInt10161;
    int anInt10162;
    Class30_Sub1 aClass30_Sub1_10163;

    Class33(Class30_Sub1 class30_sub1, int i, int i_0_) {
	this.anInt10162 = i_0_;
	this.aClass30_Sub1_10163 = class30_sub1;
	this.anInt10161 = i;
    }

    @Override
    public void method1(int i) {
	OpenGL.glFramebufferTexture2DEXT(36160, i, this.anInt10161, (this.aClass30_Sub1_10163).anInt376, this.anInt10162);
    }

    @Override
    public int i() {
	return this.aClass30_Sub1_10163.anInt6745;
    }

    @Override
    public void method3(int i) {
	OpenGL.glFramebufferTexture2DEXT(36160, i, this.anInt10161, (this.aClass30_Sub1_10163).anInt376, this.anInt10162);
    }

    @Override
    public void b() {
	/* empty */
    }

    @Override
    public int p() {
	return this.aClass30_Sub1_10163.anInt6745;
    }

    @Override
    public int f() {
	return this.aClass30_Sub1_10163.anInt6745;
    }

    @Override
    public int k() {
	return this.aClass30_Sub1_10163.anInt6745;
    }

    @Override
    public void d() {
	/* empty */
    }

    @Override
    public void u() {
	/* empty */
    }

    @Override
    public void x() {
	/* empty */
    }

    @Override
    public void method2(int i) {
	OpenGL.glFramebufferTexture2DEXT(36160, i, this.anInt10161, (this.aClass30_Sub1_10163).anInt376, this.anInt10162);
    }

    @Override
    public int a() {
	return this.aClass30_Sub1_10163.anInt6745;
    }

    @Override
    public void method4(int i) {
	OpenGL.glFramebufferTexture2DEXT(36160, i, this.anInt10161, (this.aClass30_Sub1_10163).anInt376, this.anInt10162);
    }
}
