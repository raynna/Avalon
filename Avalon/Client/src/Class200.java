
/* Class200 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import jagdx.IUnknown;

public abstract class Class200 implements Interface8 {
    boolean aBoolean6406;
    long aLong6407 = 0L;
    Class77 aClass77_6408;
    Class55 aClass55_6409;
    Class_ra_Sub3_Sub2 aClass_ra_Sub3_Sub2_6410;

    public void method122(Class179 class179) {
	/* empty */
    }

    long method1894() {
	return this.aLong6407;
    }

    Class200(Class_ra_Sub3_Sub2 class_ra_sub3_sub2, Class55 class55, Class77 class77, boolean bool, int i) {
	this.aClass_ra_Sub3_Sub2_6410 = class_ra_sub3_sub2;
	this.aClass55_6409 = class55;
	this.aClass77_6408 = class77;
	this.aBoolean6406 = bool;
	this.aClass_ra_Sub3_Sub2_6410.method5323(this);
    }

    @Override
    public void b() {
	if (this.aLong6407 != 0L) {
	    IUnknown.Release(this.aLong6407);
	    this.aLong6407 = 0L;
	}
	this.aClass_ra_Sub3_Sub2_6410.method5293(this);
    }

    void method1895() {
	if (this.aLong6407 != 0L) {
	    this.aClass_ra_Sub3_Sub2_6410.method5552(this.aLong6407);
	    this.aLong6407 = 0L;
	}
    }

    @Override
    public void d() {
	if (this.aLong6407 != 0L) {
	    IUnknown.Release(this.aLong6407);
	    this.aLong6407 = 0L;
	}
	this.aClass_ra_Sub3_Sub2_6410.method5293(this);
    }

    @Override
    public void u() {
	if (this.aLong6407 != 0L) {
	    IUnknown.Release(this.aLong6407);
	    this.aLong6407 = 0L;
	}
	this.aClass_ra_Sub3_Sub2_6410.method5293(this);
    }

    @Override
    public void x() {
	if (this.aLong6407 != 0L) {
	    IUnknown.Release(this.aLong6407);
	    this.aLong6407 = 0L;
	}
	this.aClass_ra_Sub3_Sub2_6410.method5293(this);
    }
}
