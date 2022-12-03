
/* Class196 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import jagdx.IDirect3DDevice;
import jagdx.IDirect3DIndexBuffer;
import jagdx.IUnknown;

public class Class196 implements Interface7_Impl2 {
    Class_ra_Sub3_Sub2 aClass_ra_Sub3_Sub2_8585;
    int anInt8586;
    int anInt8587;
    boolean aBoolean8588;
    Class77 aClass77_8589;
    long aLong8590 = 0L;

    Class196(Class_ra_Sub3_Sub2 class_ra_sub3_sub2, Class77 class77, boolean bool) {
	this.aClass_ra_Sub3_Sub2_8585 = class_ra_sub3_sub2;
	this.aClass77_8589 = class77;
	this.aBoolean8588 = bool;
	this.aClass_ra_Sub3_Sub2_8585.method5323(this);
    }

    @Override
    public void method113(int i) {
	this.anInt8587 = this.aClass77_8589.anInt724 * 685647847 * i;
	if (this.anInt8587 > this.anInt8586) {
	    if (this.aLong8590 != 0L)
		IUnknown.Release(this.aLong8590);
	    int i_0_ = 8;
	    int i_1_;
	    if (this.aBoolean8588) {
		i_1_ = 0;
		i_0_ |= 0x200;
	    } else
		i_1_ = 1;
	    this.aLong8590 = (IDirect3DDevice.CreateIndexBuffer(this.aClass_ra_Sub3_Sub2_8585.aLong9847, this.anInt8587, i_0_, (this.aClass77_8589 == Class77.aClass77_718 ? 101 : 102), i_1_));
	    this.anInt8586 = this.anInt8587;
	}
    }

    @Override
    public void method115(int i) {
	this.anInt8587 = this.aClass77_8589.anInt724 * 685647847 * i;
	if (this.anInt8587 > this.anInt8586) {
	    if (this.aLong8590 != 0L)
		IUnknown.Release(this.aLong8590);
	    int i_2_ = 8;
	    int i_3_;
	    if (this.aBoolean8588) {
		i_3_ = 0;
		i_2_ |= 0x200;
	    } else
		i_3_ = 1;
	    this.aLong8590 = (IDirect3DDevice.CreateIndexBuffer(this.aClass_ra_Sub3_Sub2_8585.aLong9847, this.anInt8587, i_2_, (this.aClass77_8589 == Class77.aClass77_718 ? 101 : 102), i_3_));
	    this.anInt8586 = this.anInt8587;
	}
    }

    @Override
    public void method69() {
	IDirect3DIndexBuffer.Unlock(this.aLong8590);
    }

    @Override
    public boolean method63(int i, int i_4_, long l) {
	return jagdx.a.f(IDirect3DIndexBuffer.Upload(this.aLong8590, i, i_4_, this.aBoolean8588 ? 8192 : 0, l));
    }

    @Override
    public void b() {
	if (this.aLong8590 != 0L) {
	    IUnknown.Release(this.aLong8590);
	    this.aLong8590 = 0L;
	}
	this.anInt8586 = 0;
	this.anInt8587 = 0;
	this.aClass_ra_Sub3_Sub2_8585.method5293(this);
    }

    void method1874() {
	if (this.aLong8590 != 0L) {
	    this.aClass_ra_Sub3_Sub2_8585.method5552(this.aLong8590);
	    this.aLong8590 = 0L;
	}
	this.anInt8586 = 0;
	this.anInt8587 = 0;
    }

    @Override
    public int method60() {
	return this.anInt8587;
    }

    @Override
    public void d() {
	if (this.aLong8590 != 0L) {
	    IUnknown.Release(this.aLong8590);
	    this.aLong8590 = 0L;
	}
	this.anInt8586 = 0;
	this.anInt8587 = 0;
	this.aClass_ra_Sub3_Sub2_8585.method5293(this);
    }

    @Override
    public void x() {
	if (this.aLong8590 != 0L) {
	    IUnknown.Release(this.aLong8590);
	    this.aLong8590 = 0L;
	}
	this.anInt8586 = 0;
	this.anInt8587 = 0;
	this.aClass_ra_Sub3_Sub2_8585.method5293(this);
    }

    @Override
    public long method62(int i, int i_5_) {
	return IDirect3DIndexBuffer.Lock(this.aLong8590, i, i_5_, (this.aBoolean8588 ? 8192 : 0));
    }

    @Override
    public int method65() {
	return this.anInt8587;
    }

    @Override
    public boolean method61(int i, int i_6_, long l) {
	return jagdx.a.f(IDirect3DIndexBuffer.Upload(this.aLong8590, i, i_6_, this.aBoolean8588 ? 8192 : 0, l));
    }

    @Override
    public boolean method67(int i, int i_7_, long l) {
	return jagdx.a.f(IDirect3DIndexBuffer.Upload(this.aLong8590, i, i_7_, this.aBoolean8588 ? 8192 : 0, l));
    }

    @Override
    public void u() {
	if (this.aLong8590 != 0L) {
	    IUnknown.Release(this.aLong8590);
	    this.aLong8590 = 0L;
	}
	this.anInt8586 = 0;
	this.anInt8587 = 0;
	this.aClass_ra_Sub3_Sub2_8585.method5293(this);
    }

    @Override
    public long method68(int i, int i_8_) {
	return IDirect3DIndexBuffer.Lock(this.aLong8590, i, i_8_, (this.aBoolean8588 ? 8192 : 0));
    }

    @Override
    public void method112(int i) {
	this.anInt8587 = this.aClass77_8589.anInt724 * 685647847 * i;
	if (this.anInt8587 > this.anInt8586) {
	    if (this.aLong8590 != 0L)
		IUnknown.Release(this.aLong8590);
	    int i_9_ = 8;
	    int i_10_;
	    if (this.aBoolean8588) {
		i_10_ = 0;
		i_9_ |= 0x200;
	    } else
		i_10_ = 1;
	    this.aLong8590 = (IDirect3DDevice.CreateIndexBuffer(this.aClass_ra_Sub3_Sub2_8585.aLong9847, this.anInt8587, i_9_, (this.aClass77_8589 == Class77.aClass77_718 ? 101 : 102), i_10_));
	    this.anInt8586 = this.anInt8587;
	}
    }

    @Override
    public void method114(int i) {
	this.anInt8587 = this.aClass77_8589.anInt724 * 685647847 * i;
	if (this.anInt8587 > this.anInt8586) {
	    if (this.aLong8590 != 0L)
		IUnknown.Release(this.aLong8590);
	    int i_11_ = 8;
	    int i_12_;
	    if (this.aBoolean8588) {
		i_12_ = 0;
		i_11_ |= 0x200;
	    } else
		i_12_ = 1;
	    this.aLong8590 = (IDirect3DDevice.CreateIndexBuffer(this.aClass_ra_Sub3_Sub2_8585.aLong9847, this.anInt8587, i_11_, (this.aClass77_8589 == Class77.aClass77_718 ? 101 : 102), i_12_));
	    this.anInt8586 = this.anInt8587;
	}
    }

    @Override
    public int method64() {
	return this.anInt8587;
    }

    @Override
    public void method116(int i) {
	this.anInt8587 = this.aClass77_8589.anInt724 * 685647847 * i;
	if (this.anInt8587 > this.anInt8586) {
	    if (this.aLong8590 != 0L)
		IUnknown.Release(this.aLong8590);
	    int i_13_ = 8;
	    int i_14_;
	    if (this.aBoolean8588) {
		i_14_ = 0;
		i_13_ |= 0x200;
	    } else
		i_14_ = 1;
	    this.aLong8590 = (IDirect3DDevice.CreateIndexBuffer(this.aClass_ra_Sub3_Sub2_8585.aLong9847, this.anInt8587, i_13_, (this.aClass77_8589 == Class77.aClass77_718 ? 101 : 102), i_14_));
	    this.anInt8586 = this.anInt8587;
	}
    }

    @Override
    public void method66() {
	IDirect3DIndexBuffer.Unlock(this.aLong8590);
    }
}
