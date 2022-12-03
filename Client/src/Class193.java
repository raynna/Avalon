
/* Class193 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import jaclib.memory.Source;
import jagdx.IDirect3DDevice;
import jagdx.IDirect3DVertexBuffer;
import jagdx.IUnknown;

public class Class193 implements Interface7_Impl1 {
    Class_ra_Sub3_Sub2 aClass_ra_Sub3_Sub2_8579;
    byte aByte8580;
    int anInt8581;
    int anInt8582;
    boolean aBoolean8583;
    long aLong8584 = 0L;

    @Override
    public int method65() {
	return this.anInt8581;
    }

    @Override
    public int method60() {
	return this.anInt8581;
    }

    int method1861() {
	return this.aByte8580;
    }

    @Override
    public void d() {
	if (this.aLong8584 != 0L) {
	    IUnknown.Release(this.aLong8584);
	    this.aLong8584 = 0L;
	}
	this.anInt8582 = 0;
	this.anInt8581 = 0;
	this.aClass_ra_Sub3_Sub2_8579.method5293(this);
    }

    @Override
    public boolean method71(int i, int i_0_, Source source) {
	if (method72(i, i_0_))
	    return jagdx.a.f(IDirect3DVertexBuffer.Upload(this.aLong8584, 0, this.anInt8581, this.aBoolean8583 ? 8192 : 0, source.f()));
	return false;
    }

    @Override
    public void x() {
	if (this.aLong8584 != 0L) {
	    IUnknown.Release(this.aLong8584);
	    this.aLong8584 = 0L;
	}
	this.anInt8582 = 0;
	this.anInt8581 = 0;
	this.aClass_ra_Sub3_Sub2_8579.method5293(this);
    }

    @Override
    public void b() {
	if (this.aLong8584 != 0L) {
	    IUnknown.Release(this.aLong8584);
	    this.aLong8584 = 0L;
	}
	this.anInt8582 = 0;
	this.anInt8581 = 0;
	this.aClass_ra_Sub3_Sub2_8579.method5293(this);
    }

    @Override
    public boolean method63(int i, int i_1_, long l) {
	return jagdx.a.f(IDirect3DVertexBuffer.Upload(this.aLong8584, i, i_1_, this.aBoolean8583 ? 8192 : 0, l));
    }

    void method1862() {
	if (this.aLong8584 != 0L) {
	    this.aClass_ra_Sub3_Sub2_8579.method5552(this.aLong8584);
	    this.aLong8584 = 0L;
	    this.anInt8582 = 0;
	    this.anInt8581 = 0;
	}
    }

    @Override
    public long method62(int i, int i_2_) {
	return IDirect3DVertexBuffer.Lock(this.aLong8584, i, i_2_, (this.aBoolean8583 ? 8192 : 0));
    }

    @Override
    public void u() {
	if (this.aLong8584 != 0L) {
	    IUnknown.Release(this.aLong8584);
	    this.aLong8584 = 0L;
	}
	this.anInt8582 = 0;
	this.anInt8581 = 0;
	this.aClass_ra_Sub3_Sub2_8579.method5293(this);
    }

    @Override
    public void method69() {
	IDirect3DVertexBuffer.Unlock(this.aLong8584);
    }

    @Override
    public boolean method70(int i, int i_3_) {
	this.anInt8581 = i;
	this.aByte8580 = (byte) i_3_;
	if (this.anInt8581 > this.anInt8582) {
	    int i_4_ = 8;
	    int i_5_;
	    if (this.aBoolean8583) {
		i_5_ = 0;
		i_4_ |= 0x200;
	    } else
		i_5_ = 1;
	    if (this.aLong8584 != 0L)
		IUnknown.Release(this.aLong8584);
	    this.aLong8584 = (IDirect3DDevice.CreateVertexBuffer(this.aClass_ra_Sub3_Sub2_8579.aLong9847, this.anInt8581, i_4_, 0, i_5_));
	    this.anInt8582 = this.anInt8581;
	}
	return this.aLong8584 != 0L;
    }

    @Override
    public void method66() {
	IDirect3DVertexBuffer.Unlock(this.aLong8584);
    }

    @Override
    public int method64() {
	return this.anInt8581;
    }

    @Override
    public boolean method74(int i, int i_6_, Source source) {
	if (method72(i, i_6_))
	    return jagdx.a.f(IDirect3DVertexBuffer.Upload(this.aLong8584, 0, this.anInt8581, this.aBoolean8583 ? 8192 : 0, source.f()));
	return false;
    }

    @Override
    public boolean method73(int i, int i_7_) {
	this.anInt8581 = i;
	this.aByte8580 = (byte) i_7_;
	if (this.anInt8581 > this.anInt8582) {
	    int i_8_ = 8;
	    int i_9_;
	    if (this.aBoolean8583) {
		i_9_ = 0;
		i_8_ |= 0x200;
	    } else
		i_9_ = 1;
	    if (this.aLong8584 != 0L)
		IUnknown.Release(this.aLong8584);
	    this.aLong8584 = (IDirect3DDevice.CreateVertexBuffer(this.aClass_ra_Sub3_Sub2_8579.aLong9847, this.anInt8581, i_8_, 0, i_9_));
	    this.anInt8582 = this.anInt8581;
	}
	return this.aLong8584 != 0L;
    }

    @Override
    public boolean method67(int i, int i_10_, long l) {
	return jagdx.a.f(IDirect3DVertexBuffer.Upload(this.aLong8584, i, i_10_, this.aBoolean8583 ? 8192 : 0, l));
    }

    @Override
    public long method68(int i, int i_11_) {
	return IDirect3DVertexBuffer.Lock(this.aLong8584, i, i_11_, (this.aBoolean8583 ? 8192 : 0));
    }

    Class193(Class_ra_Sub3_Sub2 class_ra_sub3_sub2, boolean bool) {
	this.aClass_ra_Sub3_Sub2_8579 = class_ra_sub3_sub2;
	this.aBoolean8583 = bool;
	this.aClass_ra_Sub3_Sub2_8579.method5323(this);
    }

    @Override
    public boolean method72(int i, int i_12_) {
	this.anInt8581 = i;
	this.aByte8580 = (byte) i_12_;
	if (this.anInt8581 > this.anInt8582) {
	    int i_13_ = 8;
	    int i_14_;
	    if (this.aBoolean8583) {
		i_14_ = 0;
		i_13_ |= 0x200;
	    } else
		i_14_ = 1;
	    if (this.aLong8584 != 0L)
		IUnknown.Release(this.aLong8584);
	    this.aLong8584 = (IDirect3DDevice.CreateVertexBuffer(this.aClass_ra_Sub3_Sub2_8579.aLong9847, this.anInt8581, i_13_, 0, i_14_));
	    this.anInt8582 = this.anInt8581;
	}
	return this.aLong8584 != 0L;
    }

    @Override
    public boolean method61(int i, int i_15_, long l) {
	return jagdx.a.f(IDirect3DVertexBuffer.Upload(this.aLong8584, i, i_15_, this.aBoolean8583 ? 8192 : 0, l));
    }
}
