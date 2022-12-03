
/* Class72 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import jaggl.OpenGL;

public class Class72 implements Interface8_Impl1_Impl1_Impl2 {
    int anInt10164;
    Class30_Sub2 aClass30_Sub2_10165;

    @Override
    public void u() {
	/* empty */
    }

    @Override
    public int a() {
	return this.aClass30_Sub2_10165.anInt6746;
    }

    @Override
    public int f() {
	return this.aClass30_Sub2_10165.anInt6747;
    }

    @Override
    public void method3(int i) {
	OpenGL.glFramebufferTexture2DEXT(36160, i, this.aClass30_Sub2_10165.anInt372, this.aClass30_Sub2_10165.anInt376, this.anInt10164);
    }

    @Override
    public void b() {
	/* empty */
    }

    @Override
    public int p() {
	return this.aClass30_Sub2_10165.anInt6746;
    }

    @Override
    public int i() {
	return this.aClass30_Sub2_10165.anInt6746;
    }

    @Override
    public int k() {
	return this.aClass30_Sub2_10165.anInt6747;
    }

    @Override
    public void d() {
	/* empty */
    }

    @Override
    public void x() {
	/* empty */
    }

    @Override
    public void method2(int i) {
	OpenGL.glFramebufferTexture2DEXT(36160, i, this.aClass30_Sub2_10165.anInt372, this.aClass30_Sub2_10165.anInt376, this.anInt10164);
    }

    @Override
    public void method1(int i) {
	OpenGL.glFramebufferTexture2DEXT(36160, i, this.aClass30_Sub2_10165.anInt372, this.aClass30_Sub2_10165.anInt376, this.anInt10164);
    }

    Class72(Class30_Sub2 class30_sub2, int i) {
	this.anInt10164 = i;
	this.aClass30_Sub2_10165 = class30_sub2;
    }

    @Override
    public void method4(int i) {
	OpenGL.glFramebufferTexture2DEXT(36160, i, this.aClass30_Sub2_10165.anInt372, this.aClass30_Sub2_10165.anInt376, this.anInt10164);
    }
}
