
/* Class200_Sub2 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.nio.ByteBuffer;

import jagdx.IDirect3DCubeTexture;
import jagdx.IDirect3DDevice;

public class Class200_Sub2 extends Class200 implements Interface9_Impl1 {
    int anInt8628;

    @Override
    public void method122(Class179 class179) {
	super.method122(class179);
    }

    @Override
    public void method128() {
	this.aClass_ra_Sub3_Sub2_6410.method5544(this);
    }

    @Override
    public void b() {
	super.b();
    }

    @Override
    public void method126() {
	this.aClass_ra_Sub3_Sub2_6410.method5544(this);
    }

    @Override
    public void d() {
	super.b();
    }

    @Override
    public void method125() {
	this.aClass_ra_Sub3_Sub2_6410.method5544(this);
    }

    Class200_Sub2(Class_ra_Sub3_Sub2 class_ra_sub3_sub2, int i, boolean bool, int[][] is) {
	super(class_ra_sub3_sub2, Class55.aClass55_557, Class77.aClass77_717, bool && class_ra_sub3_sub2.aBoolean9857, i * i * 6);
	this.anInt8628 = i;
	if (this.aBoolean6406)
	    this.aLong6407 = (IDirect3DDevice.CreateCubeTexture((this.aClass_ra_Sub3_Sub2_6410.aLong9847), this.anInt8628, 0, 1024, 21, 1));
	else
	    this.aLong6407 = (IDirect3DDevice.CreateCubeTexture((this.aClass_ra_Sub3_Sub2_6410.aLong9847), this.anInt8628, 1, 0, 21, 1));
	ByteBuffer bytebuffer = this.aClass_ra_Sub3_Sub2_6410.aByteBuffer8216;
	for (int i_0_ = 0; i_0_ < 6; i_0_++) {
	    bytebuffer.clear();
	    bytebuffer.asIntBuffer().put(is[i_0_]);
	    IDirect3DCubeTexture.Upload(this.aLong6407, i_0_, 0, 0, 0, this.anInt8628, this.anInt8628, this.anInt8628 * 4, 0, (this.aClass_ra_Sub3_Sub2_6410.aLong8217));
	}
    }

    @Override
    public void x() {
	super.b();
    }

    @Override
    public void u() {
	super.b();
    }

    @Override
    public void method123() {
	this.aClass_ra_Sub3_Sub2_6410.method5544(this);
    }

    @Override
    public void method127(Class179 class179) {
	super.method122(class179);
    }

    @Override
    public void method124(Class179 class179) {
	super.method122(class179);
    }

    @Override
    public void method129(Class179 class179) {
	super.method122(class179);
    }
}
