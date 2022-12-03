
/* Class199 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import jagdx.IDirect3DDevice;
import jagdx.IUnknown;

public class Class199 implements Interface8_Impl1_Impl2_Impl1 {
    int anInt10169;
    Class77 aClass77_10170;
    int anInt10171;
    Class_ra_Sub3_Sub2 aClass_ra_Sub3_Sub2_10172;
    long aLong10173;

    @Override
    public int f() {
	return this.anInt10169;
    }

    @Override
    public int a() {
	return this.anInt10171;
    }

    @Override
    public long method144() {
	return this.aLong10173;
    }

    @Override
    public void b() {
	if (this.aLong10173 != 0L) {
	    IUnknown.Release(this.aLong10173);
	    this.aLong10173 = 0L;
	}
	this.aClass_ra_Sub3_Sub2_10172.method5293(this);
    }

    @Override
    public void method141() {
	if (this.aLong10173 != 0L) {
	    this.aClass_ra_Sub3_Sub2_10172.method5552(this.aLong10173);
	    this.aLong10173 = 0L;
	}
    }

    @Override
    public void x() {
	if (this.aLong10173 != 0L) {
	    IUnknown.Release(this.aLong10173);
	    this.aLong10173 = 0L;
	}
	this.aClass_ra_Sub3_Sub2_10172.method5293(this);
    }

    @Override
    public void method142() {
	if (this.aLong10173 != 0L) {
	    this.aClass_ra_Sub3_Sub2_10172.method5552(this.aLong10173);
	    this.aLong10173 = 0L;
	}
    }

    @Override
    public int i() {
	return this.anInt10171;
    }

    @Override
    public int k() {
	return this.anInt10169;
    }

    Class199(Class_ra_Sub3_Sub2 class_ra_sub3_sub2, Class77 class77, int i, int i_0_) {
	this.aClass_ra_Sub3_Sub2_10172 = class_ra_sub3_sub2;
	this.anInt10171 = i;
	this.anInt10169 = i_0_;
	this.aClass77_10170 = class77;
	this.aLong10173 = (IDirect3DDevice.CreateDepthStencilSurface(this.aClass_ra_Sub3_Sub2_10172.aLong9847, i, i_0_, Class_ra_Sub3_Sub2.method5550(this.aClass77_10170), 0, 0, false));
	this.aClass_ra_Sub3_Sub2_10172.method5323(this);
    }

    @Override
    public void d() {
	if (this.aLong10173 != 0L) {
	    IUnknown.Release(this.aLong10173);
	    this.aLong10173 = 0L;
	}
	this.aClass_ra_Sub3_Sub2_10172.method5293(this);
    }

    @Override
    public long method147() {
	return this.aLong10173;
    }

    @Override
    public long method145() {
	return this.aLong10173;
    }

    @Override
    public int p() {
	return this.anInt10171;
    }

    @Override
    public void method146() {
	if (this.aLong10173 != 0L) {
	    this.aClass_ra_Sub3_Sub2_10172.method5552(this.aLong10173);
	    this.aLong10173 = 0L;
	}
    }

    @Override
    public void method143() {
	if (this.aLong10173 != 0L) {
	    this.aClass_ra_Sub3_Sub2_10172.method5552(this.aLong10173);
	    this.aLong10173 = 0L;
	}
    }

    @Override
    public void u() {
	if (this.aLong10173 != 0L) {
	    IUnknown.Release(this.aLong10173);
	    this.aLong10173 = 0L;
	}
	this.aClass_ra_Sub3_Sub2_10172.method5293(this);
    }

    @Override
    public void method148() {
	if (this.aLong10173 != 0L) {
	    this.aClass_ra_Sub3_Sub2_10172.method5552(this.aLong10173);
	    this.aLong10173 = 0L;
	}
    }
}
