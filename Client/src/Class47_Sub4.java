
/* Class47_Sub4 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import jaggl.OpenGL;

public class Class47_Sub4 extends Class47 {
    Class30_Sub3 aClass30_Sub3_6769;
    static char aChar6770 = '\001';
    static char aChar6771 = '\0';
    Class28 aClass28_6772;
    Class45 aClass45_6773;
    static float[] aFloatArray6774 = { 0.0F, 0.0F, 0.0F, 0.0F };

    @Override
    void method516(int i, int i_0_) {
	if ((i & 0x1) == 1) {
	    if (this.aClass45_6773.texture3d) {
		this.aClass_ra_Sub2_491.method5256(this.aClass45_6773.aClass30_Sub4_482);
		aFloatArray6774[0] = 0.0F;
		aFloatArray6774[1] = 0.0F;
		aFloatArray6774[2] = 0.0F;
		aFloatArray6774[3] = (this.aClass_ra_Sub2_491.anInt8062) % 4000 / 4000.0F;
		OpenGL.glTexGenfv(8194, 9473, aFloatArray6774, 0);
	    } else {
		int i_1_ = ((this.aClass_ra_Sub2_491).anInt8062 % 4000 * 16 / 4000);
		this.aClass_ra_Sub2_491.method5256(this.aClass45_6773.aClass30_Sub2Array487[i_1_]);
	    }
	} else if (this.aClass45_6773.texture3d) {
	    this.aClass_ra_Sub2_491.method5256(this.aClass45_6773.aClass30_Sub4_482);
	    aFloatArray6774[0] = 0.0F;
	    aFloatArray6774[1] = 0.0F;
	    aFloatArray6774[2] = 0.0F;
	    aFloatArray6774[3] = 0.0F;
	    OpenGL.glTexGenfv(8194, 9473, aFloatArray6774, 0);
	} else
	    this.aClass_ra_Sub2_491.method5256(this.aClass45_6773.aClass30_Sub2Array487[0]);
    }

    @Override
    boolean method501() {
	return true;
    }

    @Override
    void method518(boolean bool) {
	this.aClass_ra_Sub2_491.method5258(260, 8448);
    }

    Class47_Sub4(Class_ra_Sub2 class_ra_sub2, Class45 class45) {
	super(class_ra_sub2);
	this.aClass45_6773 = class45;
	method522();
	this.aClass30_Sub3_6769 = new Class30_Sub3(this.aClass_ra_Sub2_491, Class55.aClass55_567, Class77.aClass77_717, 2, new byte[] { 0, -1 }, Class55.aClass55_567);
	this.aClass30_Sub3_6769.method430(false);
    }

    @Override
    void method503(int i, int i_2_) {
	if ((i & 0x1) == 1) {
	    if (this.aClass45_6773.texture3d) {
		this.aClass_ra_Sub2_491.method5256(this.aClass45_6773.aClass30_Sub4_482);
		aFloatArray6774[0] = 0.0F;
		aFloatArray6774[1] = 0.0F;
		aFloatArray6774[2] = 0.0F;
		aFloatArray6774[3] = (this.aClass_ra_Sub2_491.anInt8062) % 4000 / 4000.0F;
		OpenGL.glTexGenfv(8194, 9473, aFloatArray6774, 0);
	    } else {
		int i_3_ = ((this.aClass_ra_Sub2_491).anInt8062 % 4000 * 16 / 4000);
		this.aClass_ra_Sub2_491.method5256(this.aClass45_6773.aClass30_Sub2Array487[i_3_]);
	    }
	} else if (this.aClass45_6773.texture3d) {
	    this.aClass_ra_Sub2_491.method5256(this.aClass45_6773.aClass30_Sub4_482);
	    aFloatArray6774[0] = 0.0F;
	    aFloatArray6774[1] = 0.0F;
	    aFloatArray6774[2] = 0.0F;
	    aFloatArray6774[3] = 0.0F;
	    OpenGL.glTexGenfv(8194, 9473, aFloatArray6774, 0);
	} else
	    this.aClass_ra_Sub2_491.method5256(this.aClass45_6773.aClass30_Sub2Array487[0]);
    }

    @Override
    void method500(Class30 class30, int i) {
	/* empty */
    }

    @Override
    void method508(boolean bool) {
	this.aClass_ra_Sub2_491.method5258(260, 8448);
    }

    @Override
    void method506(boolean bool) {
	if ((this.aClass_ra_Sub2_491.anInt8007) > 0) {
	    float f = -0.5F / ((this.aClass_ra_Sub2_491).anInt8007);
	    this.aClass_ra_Sub2_491.method5255(1);
	    aFloatArray6774[0] = 0.0F;
	    aFloatArray6774[1] = 0.0F;
	    aFloatArray6774[2] = f;
	    aFloatArray6774[3] = (this.aClass_ra_Sub2_491.aFloat8142) * f + 0.25F;
	    OpenGL.glPushMatrix();
	    OpenGL.glLoadIdentity();
	    OpenGL.glTexGenfv(8192, 9474, aFloatArray6774, 0);
	    OpenGL.glPopMatrix();
	    this.aClass_ra_Sub2_491.method5238(0.5F, this.aClass_ra_Sub2_491.anInt8007);
	    this.aClass_ra_Sub2_491.method5256(this.aClass30_Sub3_6769);
	    this.aClass_ra_Sub2_491.method5255(0);
	}
	this.aClass28_6772.method404('\0');
	OpenGL.glMatrixMode(5890);
	OpenGL.glPushMatrix();
	OpenGL.glScalef(0.25F, 0.25F, 1.0F);
	OpenGL.glMatrixMode(5888);
    }

    @Override
    void method507(boolean bool) {
	if ((this.aClass_ra_Sub2_491.anInt8007) > 0) {
	    float f = -0.5F / ((this.aClass_ra_Sub2_491).anInt8007);
	    this.aClass_ra_Sub2_491.method5255(1);
	    aFloatArray6774[0] = 0.0F;
	    aFloatArray6774[1] = 0.0F;
	    aFloatArray6774[2] = f;
	    aFloatArray6774[3] = (this.aClass_ra_Sub2_491.aFloat8142) * f + 0.25F;
	    OpenGL.glPushMatrix();
	    OpenGL.glLoadIdentity();
	    OpenGL.glTexGenfv(8192, 9474, aFloatArray6774, 0);
	    OpenGL.glPopMatrix();
	    this.aClass_ra_Sub2_491.method5238(0.5F, this.aClass_ra_Sub2_491.anInt8007);
	    this.aClass_ra_Sub2_491.method5256(this.aClass30_Sub3_6769);
	    this.aClass_ra_Sub2_491.method5255(0);
	}
	this.aClass28_6772.method404('\0');
	OpenGL.glMatrixMode(5890);
	OpenGL.glPushMatrix();
	OpenGL.glScalef(0.25F, 0.25F, 1.0F);
	OpenGL.glMatrixMode(5888);
    }

    @Override
    void method511() {
	this.aClass28_6772.method404('\001');
	if ((this.aClass_ra_Sub2_491.anInt8007) > 0) {
	    this.aClass_ra_Sub2_491.method5255(1);
	    this.aClass_ra_Sub2_491.method5256(null);
	    this.aClass_ra_Sub2_491.method5238(1.0F, 0.0F);
	    this.aClass_ra_Sub2_491.method5255(0);
	}
	this.aClass_ra_Sub2_491.method5258(8448, 8448);
	OpenGL.glMatrixMode(5890);
	OpenGL.glPopMatrix();
	OpenGL.glMatrixMode(5888);
    }

    @Override
    void method509(boolean bool) {
	this.aClass_ra_Sub2_491.method5258(260, 8448);
    }

    @Override
    void method510(boolean bool) {
	this.aClass_ra_Sub2_491.method5258(260, 8448);
    }

    void method522() {
	this.aClass28_6772 = new Class28(this.aClass_ra_Sub2_491, 2);
	this.aClass28_6772.method405(0);
	this.aClass_ra_Sub2_491.method5255(1);
	this.aClass_ra_Sub2_491.method5258(7681, 260);
	this.aClass_ra_Sub2_491.method5259(0, 34168, 768);
	OpenGL.glTexGeni(8192, 9472, 9216);
	OpenGL.glEnable(3168);
	this.aClass_ra_Sub2_491.method5255(0);
	OpenGL.glTexEnvf(8960, 34163, 2.0F);
	if (this.aClass45_6773.texture3d) {
	    OpenGL.glTexGeni(8194, 9472, 9217);
	    OpenGL.glTexGeni(8195, 9472, 9217);
	    OpenGL.glTexGenfv(8195, 9473, new float[] { 0.0F, 0.0F, 0.0F, 1.0F }, 0);
	    OpenGL.glEnable(3170);
	    OpenGL.glEnable(3171);
	}
	this.aClass28_6772.method403();
	this.aClass28_6772.method405(1);
	this.aClass_ra_Sub2_491.method5255(1);
	this.aClass_ra_Sub2_491.method5258(8448, 8448);
	this.aClass_ra_Sub2_491.method5259(0, 5890, 768);
	OpenGL.glDisable(3168);
	this.aClass_ra_Sub2_491.method5255(0);
	OpenGL.glTexEnvf(8960, 34163, 1.0F);
	if (this.aClass45_6773.texture3d) {
	    OpenGL.glDisable(3170);
	    OpenGL.glDisable(3171);
	}
	this.aClass28_6772.method403();
    }

    @Override
    void method504() {
	this.aClass28_6772.method404('\001');
	if ((this.aClass_ra_Sub2_491.anInt8007) > 0) {
	    this.aClass_ra_Sub2_491.method5255(1);
	    this.aClass_ra_Sub2_491.method5256(null);
	    this.aClass_ra_Sub2_491.method5238(1.0F, 0.0F);
	    this.aClass_ra_Sub2_491.method5255(0);
	}
	this.aClass_ra_Sub2_491.method5258(8448, 8448);
	OpenGL.glMatrixMode(5890);
	OpenGL.glPopMatrix();
	OpenGL.glMatrixMode(5888);
    }

    @Override
    void method513(int i, int i_4_) {
	if ((i & 0x1) == 1) {
	    if (this.aClass45_6773.texture3d) {
		this.aClass_ra_Sub2_491.method5256(this.aClass45_6773.aClass30_Sub4_482);
		aFloatArray6774[0] = 0.0F;
		aFloatArray6774[1] = 0.0F;
		aFloatArray6774[2] = 0.0F;
		aFloatArray6774[3] = (this.aClass_ra_Sub2_491.anInt8062) % 4000 / 4000.0F;
		OpenGL.glTexGenfv(8194, 9473, aFloatArray6774, 0);
	    } else {
		int i_5_ = ((this.aClass_ra_Sub2_491).anInt8062 % 4000 * 16 / 4000);
		this.aClass_ra_Sub2_491.method5256(this.aClass45_6773.aClass30_Sub2Array487[i_5_]);
	    }
	} else if (this.aClass45_6773.texture3d) {
	    this.aClass_ra_Sub2_491.method5256(this.aClass45_6773.aClass30_Sub4_482);
	    aFloatArray6774[0] = 0.0F;
	    aFloatArray6774[1] = 0.0F;
	    aFloatArray6774[2] = 0.0F;
	    aFloatArray6774[3] = 0.0F;
	    OpenGL.glTexGenfv(8194, 9473, aFloatArray6774, 0);
	} else
	    this.aClass_ra_Sub2_491.method5256(this.aClass45_6773.aClass30_Sub2Array487[0]);
    }

    @Override
    void method502(int i, int i_6_) {
	if ((i & 0x1) == 1) {
	    if (this.aClass45_6773.texture3d) {
		this.aClass_ra_Sub2_491.method5256(this.aClass45_6773.aClass30_Sub4_482);
		aFloatArray6774[0] = 0.0F;
		aFloatArray6774[1] = 0.0F;
		aFloatArray6774[2] = 0.0F;
		aFloatArray6774[3] = (this.aClass_ra_Sub2_491.anInt8062) % 4000 / 4000.0F;
		OpenGL.glTexGenfv(8194, 9473, aFloatArray6774, 0);
	    } else {
		int i_7_ = ((this.aClass_ra_Sub2_491).anInt8062 % 4000 * 16 / 4000);
		this.aClass_ra_Sub2_491.method5256(this.aClass45_6773.aClass30_Sub2Array487[i_7_]);
	    }
	} else if (this.aClass45_6773.texture3d) {
	    this.aClass_ra_Sub2_491.method5256(this.aClass45_6773.aClass30_Sub4_482);
	    aFloatArray6774[0] = 0.0F;
	    aFloatArray6774[1] = 0.0F;
	    aFloatArray6774[2] = 0.0F;
	    aFloatArray6774[3] = 0.0F;
	    OpenGL.glTexGenfv(8194, 9473, aFloatArray6774, 0);
	} else
	    this.aClass_ra_Sub2_491.method5256(this.aClass45_6773.aClass30_Sub2Array487[0]);
    }

    @Override
    void method512() {
	this.aClass28_6772.method404('\001');
	if ((this.aClass_ra_Sub2_491.anInt8007) > 0) {
	    this.aClass_ra_Sub2_491.method5255(1);
	    this.aClass_ra_Sub2_491.method5256(null);
	    this.aClass_ra_Sub2_491.method5238(1.0F, 0.0F);
	    this.aClass_ra_Sub2_491.method5255(0);
	}
	this.aClass_ra_Sub2_491.method5258(8448, 8448);
	OpenGL.glMatrixMode(5890);
	OpenGL.glPopMatrix();
	OpenGL.glMatrixMode(5888);
    }

    @Override
    void method505(boolean bool) {
	if ((this.aClass_ra_Sub2_491.anInt8007) > 0) {
	    float f = -0.5F / ((this.aClass_ra_Sub2_491).anInt8007);
	    this.aClass_ra_Sub2_491.method5255(1);
	    aFloatArray6774[0] = 0.0F;
	    aFloatArray6774[1] = 0.0F;
	    aFloatArray6774[2] = f;
	    aFloatArray6774[3] = (this.aClass_ra_Sub2_491.aFloat8142) * f + 0.25F;
	    OpenGL.glPushMatrix();
	    OpenGL.glLoadIdentity();
	    OpenGL.glTexGenfv(8192, 9474, aFloatArray6774, 0);
	    OpenGL.glPopMatrix();
	    this.aClass_ra_Sub2_491.method5238(0.5F, this.aClass_ra_Sub2_491.anInt8007);
	    this.aClass_ra_Sub2_491.method5256(this.aClass30_Sub3_6769);
	    this.aClass_ra_Sub2_491.method5255(0);
	}
	this.aClass28_6772.method404('\0');
	OpenGL.glMatrixMode(5890);
	OpenGL.glPushMatrix();
	OpenGL.glScalef(0.25F, 0.25F, 1.0F);
	OpenGL.glMatrixMode(5888);
    }

    @Override
    void method517(int i, int i_8_) {
	if ((i & 0x1) == 1) {
	    if (this.aClass45_6773.texture3d) {
		this.aClass_ra_Sub2_491.method5256(this.aClass45_6773.aClass30_Sub4_482);
		aFloatArray6774[0] = 0.0F;
		aFloatArray6774[1] = 0.0F;
		aFloatArray6774[2] = 0.0F;
		aFloatArray6774[3] = (this.aClass_ra_Sub2_491.anInt8062) % 4000 / 4000.0F;
		OpenGL.glTexGenfv(8194, 9473, aFloatArray6774, 0);
	    } else {
		int i_9_ = ((this.aClass_ra_Sub2_491).anInt8062 % 4000 * 16 / 4000);
		this.aClass_ra_Sub2_491.method5256(this.aClass45_6773.aClass30_Sub2Array487[i_9_]);
	    }
	} else if (this.aClass45_6773.texture3d) {
	    this.aClass_ra_Sub2_491.method5256(this.aClass45_6773.aClass30_Sub4_482);
	    aFloatArray6774[0] = 0.0F;
	    aFloatArray6774[1] = 0.0F;
	    aFloatArray6774[2] = 0.0F;
	    aFloatArray6774[3] = 0.0F;
	    OpenGL.glTexGenfv(8194, 9473, aFloatArray6774, 0);
	} else
	    this.aClass_ra_Sub2_491.method5256(this.aClass45_6773.aClass30_Sub2Array487[0]);
    }

    @Override
    void method514(Class30 class30, int i) {
	/* empty */
    }

    @Override
    void method515(int i, int i_10_) {
	if ((i & 0x1) == 1) {
	    if (this.aClass45_6773.texture3d) {
		this.aClass_ra_Sub2_491.method5256(this.aClass45_6773.aClass30_Sub4_482);
		aFloatArray6774[0] = 0.0F;
		aFloatArray6774[1] = 0.0F;
		aFloatArray6774[2] = 0.0F;
		aFloatArray6774[3] = (this.aClass_ra_Sub2_491.anInt8062) % 4000 / 4000.0F;
		OpenGL.glTexGenfv(8194, 9473, aFloatArray6774, 0);
	    } else {
		int i_11_ = ((this.aClass_ra_Sub2_491).anInt8062 % 4000 * 16 / 4000);
		this.aClass_ra_Sub2_491.method5256(this.aClass45_6773.aClass30_Sub2Array487[i_11_]);
	    }
	} else if (this.aClass45_6773.texture3d) {
	    this.aClass_ra_Sub2_491.method5256(this.aClass45_6773.aClass30_Sub4_482);
	    aFloatArray6774[0] = 0.0F;
	    aFloatArray6774[1] = 0.0F;
	    aFloatArray6774[2] = 0.0F;
	    aFloatArray6774[3] = 0.0F;
	    OpenGL.glTexGenfv(8194, 9473, aFloatArray6774, 0);
	} else
	    this.aClass_ra_Sub2_491.method5256(this.aClass45_6773.aClass30_Sub2Array487[0]);
    }

    @Override
    boolean method520() {
	return true;
    }

    @Override
    void method519(Class30 class30, int i) {
	/* empty */
    }
}
