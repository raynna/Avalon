
/* Class47_Sub7 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import jaggl.OpenGL;

public class Class47_Sub7 extends Class47 {
    static char aChar6794 = '\001';
    boolean aBoolean6795 = false;
    Class28 aClass28_6796;
    static char aChar6797 = '\0';

    @Override
    void method508(boolean bool) {
	this.aClass_ra_Sub2_491.method5258(8448, 7681);
    }

    @Override
    boolean method501() {
	return true;
    }

    @Override
    void method505(boolean bool) {
	Class30_Sub1 class30_sub1 = this.aClass_ra_Sub2_491.method5280();
	if (this.aClass28_6796 != null && class30_sub1 != null && bool) {
	    this.aClass28_6796.method404('\0');
	    this.aClass_ra_Sub2_491.method5255(1);
	    this.aClass_ra_Sub2_491.method5256(class30_sub1);
	    OpenGL.glMatrixMode(5890);
	    OpenGL.glLoadMatrixf((this.aClass_ra_Sub2_491.aClass233_8089.method2160(this.aClass_ra_Sub2_491.aFloatArray8106)), 0);
	    OpenGL.glMatrixMode(5888);
	    this.aClass_ra_Sub2_491.method5255(0);
	    this.aBoolean6795 = true;
	} else
	    this.aClass_ra_Sub2_491.method5286(0, 34168, 770);
    }

    @Override
    void method518(boolean bool) {
	this.aClass_ra_Sub2_491.method5258(8448, 7681);
    }

    @Override
    void method509(boolean bool) {
	this.aClass_ra_Sub2_491.method5258(8448, 7681);
    }

    @Override
    void method503(int i, int i_0_) {
	/* empty */
    }

    @Override
    void method500(Class30 class30, int i) {
	this.aClass_ra_Sub2_491.method5256(class30);
	this.aClass_ra_Sub2_491.method5243(i);
    }

    @Override
    void method506(boolean bool) {
	Class30_Sub1 class30_sub1 = this.aClass_ra_Sub2_491.method5280();
	if (this.aClass28_6796 != null && class30_sub1 != null && bool) {
	    this.aClass28_6796.method404('\0');
	    this.aClass_ra_Sub2_491.method5255(1);
	    this.aClass_ra_Sub2_491.method5256(class30_sub1);
	    OpenGL.glMatrixMode(5890);
	    OpenGL.glLoadMatrixf((this.aClass_ra_Sub2_491.aClass233_8089.method2160(this.aClass_ra_Sub2_491.aFloatArray8106)), 0);
	    OpenGL.glMatrixMode(5888);
	    this.aClass_ra_Sub2_491.method5255(0);
	    this.aBoolean6795 = true;
	} else
	    this.aClass_ra_Sub2_491.method5286(0, 34168, 770);
    }

    @Override
    void method510(boolean bool) {
	this.aClass_ra_Sub2_491.method5258(8448, 7681);
    }

    @Override
    void method516(int i, int i_1_) {
	/* empty */
    }

    @Override
    void method507(boolean bool) {
	Class30_Sub1 class30_sub1 = this.aClass_ra_Sub2_491.method5280();
	if (this.aClass28_6796 != null && class30_sub1 != null && bool) {
	    this.aClass28_6796.method404('\0');
	    this.aClass_ra_Sub2_491.method5255(1);
	    this.aClass_ra_Sub2_491.method5256(class30_sub1);
	    OpenGL.glMatrixMode(5890);
	    OpenGL.glLoadMatrixf((this.aClass_ra_Sub2_491.aClass233_8089.method2160(this.aClass_ra_Sub2_491.aFloatArray8106)), 0);
	    OpenGL.glMatrixMode(5888);
	    this.aClass_ra_Sub2_491.method5255(0);
	    this.aBoolean6795 = true;
	} else
	    this.aClass_ra_Sub2_491.method5286(0, 34168, 770);
    }

    Class47_Sub7(Class_ra_Sub2 class_ra_sub2) {
	super(class_ra_sub2);
	if (class_ra_sub2.aBoolean8178) {
	    this.aClass28_6796 = new Class28(class_ra_sub2, 2);
	    this.aClass28_6796.method405(0);
	    this.aClass_ra_Sub2_491.method5255(1);
	    this.aClass_ra_Sub2_491.method5258(34165, 7681);
	    this.aClass_ra_Sub2_491.method5259(2, 34168, 770);
	    this.aClass_ra_Sub2_491.method5286(0, 34167, 770);
	    OpenGL.glTexGeni(8192, 9472, 34066);
	    OpenGL.glTexGeni(8193, 9472, 34066);
	    OpenGL.glTexGeni(8194, 9472, 34066);
	    OpenGL.glEnable(3168);
	    OpenGL.glEnable(3169);
	    OpenGL.glEnable(3170);
	    this.aClass_ra_Sub2_491.method5255(0);
	    this.aClass28_6796.method403();
	    this.aClass28_6796.method405(1);
	    this.aClass_ra_Sub2_491.method5255(1);
	    this.aClass_ra_Sub2_491.method5258(8448, 8448);
	    this.aClass_ra_Sub2_491.method5259(2, 34166, 770);
	    this.aClass_ra_Sub2_491.method5286(0, 5890, 770);
	    OpenGL.glDisable(3168);
	    OpenGL.glDisable(3169);
	    OpenGL.glDisable(3170);
	    OpenGL.glMatrixMode(5890);
	    OpenGL.glLoadIdentity();
	    OpenGL.glMatrixMode(5888);
	    this.aClass_ra_Sub2_491.method5255(0);
	    this.aClass28_6796.method403();
	}
    }

    @Override
    void method511() {
	if (this.aBoolean6795) {
	    this.aClass28_6796.method404('\001');
	    this.aClass_ra_Sub2_491.method5255(1);
	    this.aClass_ra_Sub2_491.method5256(null);
	    this.aClass_ra_Sub2_491.method5255(0);
	} else
	    this.aClass_ra_Sub2_491.method5286(0, 5890, 770);
	this.aClass_ra_Sub2_491.method5258(8448, 8448);
	this.aBoolean6795 = false;
    }

    @Override
    void method512() {
	if (this.aBoolean6795) {
	    this.aClass28_6796.method404('\001');
	    this.aClass_ra_Sub2_491.method5255(1);
	    this.aClass_ra_Sub2_491.method5256(null);
	    this.aClass_ra_Sub2_491.method5255(0);
	} else
	    this.aClass_ra_Sub2_491.method5286(0, 5890, 770);
	this.aClass_ra_Sub2_491.method5258(8448, 8448);
	this.aBoolean6795 = false;
    }

    @Override
    void method513(int i, int i_2_) {
	/* empty */
    }

    @Override
    void method504() {
	if (this.aBoolean6795) {
	    this.aClass28_6796.method404('\001');
	    this.aClass_ra_Sub2_491.method5255(1);
	    this.aClass_ra_Sub2_491.method5256(null);
	    this.aClass_ra_Sub2_491.method5255(0);
	} else
	    this.aClass_ra_Sub2_491.method5286(0, 5890, 770);
	this.aClass_ra_Sub2_491.method5258(8448, 8448);
	this.aBoolean6795 = false;
    }

    @Override
    void method515(int i, int i_3_) {
	/* empty */
    }

    @Override
    void method502(int i, int i_4_) {
	/* empty */
    }

    @Override
    void method517(int i, int i_5_) {
	/* empty */
    }

    @Override
    void method514(Class30 class30, int i) {
	this.aClass_ra_Sub2_491.method5256(class30);
	this.aClass_ra_Sub2_491.method5243(i);
    }

    @Override
    void method519(Class30 class30, int i) {
	this.aClass_ra_Sub2_491.method5256(class30);
	this.aClass_ra_Sub2_491.method5243(i);
    }

    @Override
    boolean method520() {
	return true;
    }
}
