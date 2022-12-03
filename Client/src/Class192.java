
/* Class192 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import jagdx.IDirect3DTexture;
import jagdx.IUnknown;

public class Class192 implements Interface8_Impl1_Impl1_Impl1, Interface8_Impl1_Impl2_Impl1 {
    long aLong10166;
    Class200_Sub3_Sub1 aClass200_Sub3_Sub1_10167;
    int anInt10168;

    @Override
    public void method143() {
	if (this.aLong10166 != 0L) {
	    this.aClass200_Sub3_Sub1_10167.aClass_ra_Sub3_Sub2_6410.method5552(this.aLong10166);
	    this.aLong10166 = 0L;
	}
    }

    @Override
    public int a() {
	return this.aClass200_Sub3_Sub1_10167.method92();
    }

    @Override
    public int f() {
	return this.aClass200_Sub3_Sub1_10167.method76();
    }

    @Override
    public long method144() {
	if (this.aLong10166 == 0L)
	    this.aLong10166 = (IDirect3DTexture.GetSurfaceLevel(this.aClass200_Sub3_Sub1_10167.aLong6407, this.anInt10168));
	return this.aLong10166;
    }

    @Override
    public void b() {
	if (this.aLong10166 != 0L) {
	    IUnknown.Release(this.aLong10166);
	    this.aLong10166 = 0L;
	}
	this.aClass200_Sub3_Sub1_10167.aClass_ra_Sub3_Sub2_6410.method5293(this);
    }

    @Override
    public void method141() {
	if (this.aLong10166 != 0L) {
	    this.aClass200_Sub3_Sub1_10167.aClass_ra_Sub3_Sub2_6410.method5552(this.aLong10166);
	    this.aLong10166 = 0L;
	}
    }

    @Override
    public int k() {
	return this.aClass200_Sub3_Sub1_10167.method76();
    }

    @Override
    public long method145() {
	if (this.aLong10166 == 0L)
	    this.aLong10166 = (IDirect3DTexture.GetSurfaceLevel(this.aClass200_Sub3_Sub1_10167.aLong6407, this.anInt10168));
	return this.aLong10166;
    }

    @Override
    public void u() {
	if (this.aLong10166 != 0L) {
	    IUnknown.Release(this.aLong10166);
	    this.aLong10166 = 0L;
	}
	this.aClass200_Sub3_Sub1_10167.aClass_ra_Sub3_Sub2_6410.method5293(this);
    }

    @Override
    public void x() {
	if (this.aLong10166 != 0L) {
	    IUnknown.Release(this.aLong10166);
	    this.aLong10166 = 0L;
	}
	this.aClass200_Sub3_Sub1_10167.aClass_ra_Sub3_Sub2_6410.method5293(this);
    }

    @Override
    public int p() {
	return this.aClass200_Sub3_Sub1_10167.method92();
    }

    @Override
    public int i() {
	return this.aClass200_Sub3_Sub1_10167.method92();
    }

    @Override
    public long method147() {
	if (this.aLong10166 == 0L)
	    this.aLong10166 = (IDirect3DTexture.GetSurfaceLevel(this.aClass200_Sub3_Sub1_10167.aLong6407, this.anInt10168));
	return this.aLong10166;
    }

    Class192(Class200_Sub3_Sub1 class200_sub3_sub1, int i) {
	this.anInt10168 = i;
	this.aClass200_Sub3_Sub1_10167 = class200_sub3_sub1;
	this.aClass200_Sub3_Sub1_10167.aClass_ra_Sub3_Sub2_6410.method5323(this);
    }

    @Override
    public void method146() {
	if (this.aLong10166 != 0L) {
	    this.aClass200_Sub3_Sub1_10167.aClass_ra_Sub3_Sub2_6410.method5552(this.aLong10166);
	    this.aLong10166 = 0L;
	}
    }

    @Override
    public void d() {
	if (this.aLong10166 != 0L) {
	    IUnknown.Release(this.aLong10166);
	    this.aLong10166 = 0L;
	}
	this.aClass200_Sub3_Sub1_10167.aClass_ra_Sub3_Sub2_6410.method5293(this);
    }

    @Override
    public void method142() {
	if (this.aLong10166 != 0L) {
	    this.aClass200_Sub3_Sub1_10167.aClass_ra_Sub3_Sub2_6410.method5552(this.aLong10166);
	    this.aLong10166 = 0L;
	}
    }

    @Override
    public void method148() {
	if (this.aLong10166 != 0L) {
	    this.aClass200_Sub3_Sub1_10167.aClass_ra_Sub3_Sub2_6410.method5552(this.aLong10166);
	    this.aLong10166 = 0L;
	}
    }
}
