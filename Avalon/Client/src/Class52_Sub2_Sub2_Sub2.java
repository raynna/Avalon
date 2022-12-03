
/* Class52_Sub2_Sub2_Sub2 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.awt.Canvas;

import jagdx.D3DPRESENT_PARAMETERS;
import jagdx.IDirect3DDevice;
import jagdx.IDirect3DSwapChain;
import jagdx.IUnknown;

public class Class52_Sub2_Sub2_Sub2 extends Class52_Sub2_Sub2 implements Interface11 {
    boolean aBoolean9955;
    Canvas aCanvas9956;
    long aLong9957;
    Class_ra_Sub3_Sub2 aClass_ra_Sub3_Sub2_9958;
    boolean aBoolean9959 = false;
    int anInt9960;
    int anInt9961;
    long aLong9962;
    long aLong9963;
    D3DPRESENT_PARAMETERS aD3DPRESENT_PARAMETERS9964;

    @Override
    void method582(int i, int i_0_) {
	method135();
	this.anInt9960 = i;
	this.anInt9961 = i_0_;
	if (this.aBoolean9955)
	    this.aClass_ra_Sub3_Sub2_9958.method5539();
	method133();
	super.method582(i, i_0_);
    }

    @Override
    public void method133() {
	method135();
	this.aD3DPRESENT_PARAMETERS9964 = new D3DPRESENT_PARAMETERS(this.aCanvas9956);
	this.aD3DPRESENT_PARAMETERS9964.Windowed = true;
	this.aD3DPRESENT_PARAMETERS9964.BackBufferCount = 1;
	this.aD3DPRESENT_PARAMETERS9964.BackBufferWidth = this.anInt9960;
	this.aD3DPRESENT_PARAMETERS9964.BackBufferHeight = this.anInt9961;
	if (this.aBoolean9955) {
	    this.aLong9957 = IDirect3DDevice.GetSwapChain(((this.aClass_ra_Sub3_Sub2_9958).aLong9847), 0);
	    this.aLong9963 = IDirect3DSwapChain.GetBackBuffer(this.aLong9957, 0, 0);
	    this.aLong9962 = (IDirect3DDevice.GetDepthStencilSurface(this.aClass_ra_Sub3_Sub2_9958.aLong9847));
	} else {
	    if (!Class_ra_Sub3_Sub2.method5551((this.aClass_ra_Sub3_Sub2_9958).anInt9844, (this.aClass_ra_Sub3_Sub2_9958).anInt9871, (this.aClass_ra_Sub3_Sub2_9958).aLong9852, (this.aClass_ra_Sub3_Sub2_9958.anInt8341), this.aD3DPRESENT_PARAMETERS9964))
		throw new RuntimeException();
	    int i = (this.aD3DPRESENT_PARAMETERS9964.AutoDepthStencilFormat);
	    this.aLong9957 = (IDirect3DDevice.CreateAdditionalSwapChain((this.aClass_ra_Sub3_Sub2_9958.aLong9847), (this.aD3DPRESENT_PARAMETERS9964)));
	    this.aLong9963 = IDirect3DSwapChain.GetBackBuffer(this.aLong9957, 0, 0);
	    this.aLong9962 = (IDirect3DDevice.CreateDepthStencilSurface((this.aClass_ra_Sub3_Sub2_9958.aLong9847), this.anInt9960, this.anInt9961, i, (this.aD3DPRESENT_PARAMETERS9964.MultiSampleType), (this.aD3DPRESENT_PARAMETERS9964.MultiSampleQuality), false));
	}
	if (this.aBoolean9959)
	    method136();
    }

    @Override
    public int method544() {
	return this.anInt9961;
    }

    @Override
    public int method552() {
	return this.anInt9961;
    }

    @Override
    public int method581(int i, int i_1_) {
	return IDirect3DSwapChain.Present(this.aLong9957, 0);
    }

    @Override
    public int method580() {
	return IDirect3DSwapChain.Present(this.aLong9957, 0);
    }

    void method596() {
	if (this.aLong9963 != 0L) {
	    IUnknown.Release(this.aLong9963);
	    this.aLong9963 = 0L;
	}
	if (this.aLong9962 != 0L) {
	    IUnknown.Release(this.aLong9962);
	    this.aLong9962 = 0L;
	}
	if (this.aLong9957 != 0L) {
	    IUnknown.Release(this.aLong9957);
	    this.aLong9957 = 0L;
	}
	this.aClass_ra_Sub3_Sub2_9958.method5565(this);
    }

    @Override
    public int method545() {
	return this.anInt9960;
    }

    Class52_Sub2_Sub2_Sub2(Class_ra_Sub3_Sub2 class_ra_sub3_sub2, Canvas canvas, int i, int i_2_, boolean bool) {
	super(class_ra_sub3_sub2);
	this.aBoolean9955 = false;
	this.aCanvas9956 = canvas;
	this.aClass_ra_Sub3_Sub2_9958 = class_ra_sub3_sub2;
	this.anInt9960 = i;
	this.anInt9961 = i_2_;
	this.aBoolean9955 = bool;
	method133();
	this.aClass_ra_Sub3_Sub2_9958.method5542(this);
    }

    @Override
    public boolean method136() {
	this.aBoolean9959 = true;
	if (jagdx.a.a(IDirect3DDevice.SetRenderTarget(((this.aClass_ra_Sub3_Sub2_9958).aLong9847), 0, this.aLong9963)))
	    return false;
	if (jagdx.a.a(IDirect3DDevice.SetDepthStencilSurface(((this.aClass_ra_Sub3_Sub2_9958).aLong9847), this.aLong9962)))
	    return false;
	return super.method136();
    }

    @Override
    public void method138() {
	if (this.aLong9963 != 0L) {
	    IUnknown.Release(this.aLong9963);
	    this.aLong9963 = 0L;
	}
	if (this.aLong9962 != 0L) {
	    IUnknown.Release(this.aLong9962);
	    this.aLong9962 = 0L;
	}
	if (this.aLong9957 != 0L) {
	    IUnknown.Release(this.aLong9957);
	    this.aLong9957 = 0L;
	}
	this.aClass_ra_Sub3_Sub2_9958.method5565(this);
    }

    @Override
    public boolean method134() {
	this.aBoolean9959 = true;
	if (jagdx.a.a(IDirect3DDevice.SetRenderTarget(((this.aClass_ra_Sub3_Sub2_9958).aLong9847), 0, this.aLong9963)))
	    return false;
	if (jagdx.a.a(IDirect3DDevice.SetDepthStencilSurface(((this.aClass_ra_Sub3_Sub2_9958).aLong9847), this.aLong9962)))
	    return false;
	return super.method136();
    }

    @Override
    boolean method548() {
	this.aBoolean9959 = false;
	return jagdx.a.f(IDirect3DDevice.SetDepthStencilSurface((this.aClass_ra_Sub3_Sub2_9958).aLong9847, 0L));
    }

    @Override
    boolean method546() {
	this.aBoolean9959 = false;
	return jagdx.a.f(IDirect3DDevice.SetDepthStencilSurface((this.aClass_ra_Sub3_Sub2_9958).aLong9847, 0L));
    }

    @Override
    public int method547() {
	return this.anInt9960;
    }

    @Override
    void method583(int i, int i_3_) {
	method135();
	this.anInt9960 = i;
	this.anInt9961 = i_3_;
	if (this.aBoolean9955)
	    this.aClass_ra_Sub3_Sub2_9958.method5539();
	method133();
	super.method582(i, i_3_);
    }

    @Override
    public void method135() {
	if (this.aLong9963 != 0L) {
	    IUnknown.Release(this.aLong9963);
	    this.aLong9963 = 0L;
	}
	if (this.aLong9962 != 0L) {
	    IUnknown.Release(this.aLong9962);
	    this.aLong9962 = 0L;
	}
	if (this.aLong9957 != 0L) {
	    IUnknown.Release(this.aLong9957);
	    this.aLong9957 = 0L;
	}
	this.aClass_ra_Sub3_Sub2_9958.method5565(this);
    }

    @Override
    public int method550() {
	return this.anInt9960;
    }

    @Override
    public int method551() {
	return this.anInt9960;
    }

    @Override
    public void method137() {
	if (this.aLong9963 != 0L) {
	    IUnknown.Release(this.aLong9963);
	    this.aLong9963 = 0L;
	}
	if (this.aLong9962 != 0L) {
	    IUnknown.Release(this.aLong9962);
	    this.aLong9962 = 0L;
	}
	if (this.aLong9957 != 0L) {
	    IUnknown.Release(this.aLong9957);
	    this.aLong9957 = 0L;
	}
	this.aClass_ra_Sub3_Sub2_9958.method5565(this);
    }

    @Override
    public int method579() {
	return IDirect3DSwapChain.Present(this.aLong9957, 0);
    }

    @Override
    public int method584(int i, int i_4_) {
	return IDirect3DSwapChain.Present(this.aLong9957, 0);
    }

    @Override
    public int method585(int i, int i_5_) {
	return IDirect3DSwapChain.Present(this.aLong9957, 0);
    }

    @Override
    public int method586(int i, int i_6_) {
	return IDirect3DSwapChain.Present(this.aLong9957, 0);
    }

    @Override
    public int method587(int i, int i_7_) {
	return IDirect3DSwapChain.Present(this.aLong9957, 0);
    }

    @Override
    public int method549() {
	return this.anInt9960;
    }

    @Override
    public void method139() {
	method135();
	this.aD3DPRESENT_PARAMETERS9964 = new D3DPRESENT_PARAMETERS(this.aCanvas9956);
	this.aD3DPRESENT_PARAMETERS9964.Windowed = true;
	this.aD3DPRESENT_PARAMETERS9964.BackBufferCount = 1;
	this.aD3DPRESENT_PARAMETERS9964.BackBufferWidth = this.anInt9960;
	this.aD3DPRESENT_PARAMETERS9964.BackBufferHeight = this.anInt9961;
	if (this.aBoolean9955) {
	    this.aLong9957 = IDirect3DDevice.GetSwapChain(((this.aClass_ra_Sub3_Sub2_9958).aLong9847), 0);
	    this.aLong9963 = IDirect3DSwapChain.GetBackBuffer(this.aLong9957, 0, 0);
	    this.aLong9962 = (IDirect3DDevice.GetDepthStencilSurface(this.aClass_ra_Sub3_Sub2_9958.aLong9847));
	} else {
	    if (!Class_ra_Sub3_Sub2.method5551((this.aClass_ra_Sub3_Sub2_9958).anInt9844, (this.aClass_ra_Sub3_Sub2_9958).anInt9871, (this.aClass_ra_Sub3_Sub2_9958).aLong9852, (this.aClass_ra_Sub3_Sub2_9958.anInt8341), this.aD3DPRESENT_PARAMETERS9964))
		throw new RuntimeException();
	    int i = (this.aD3DPRESENT_PARAMETERS9964.AutoDepthStencilFormat);
	    this.aLong9957 = (IDirect3DDevice.CreateAdditionalSwapChain((this.aClass_ra_Sub3_Sub2_9958.aLong9847), (this.aD3DPRESENT_PARAMETERS9964)));
	    this.aLong9963 = IDirect3DSwapChain.GetBackBuffer(this.aLong9957, 0, 0);
	    this.aLong9962 = (IDirect3DDevice.CreateDepthStencilSurface((this.aClass_ra_Sub3_Sub2_9958.aLong9847), this.anInt9960, this.anInt9961, i, (this.aD3DPRESENT_PARAMETERS9964.MultiSampleType), (this.aD3DPRESENT_PARAMETERS9964.MultiSampleQuality), false));
	}
	if (this.aBoolean9959)
	    method136();
    }

    @Override
    public void method140() {
	method135();
	this.aD3DPRESENT_PARAMETERS9964 = new D3DPRESENT_PARAMETERS(this.aCanvas9956);
	this.aD3DPRESENT_PARAMETERS9964.Windowed = true;
	this.aD3DPRESENT_PARAMETERS9964.BackBufferCount = 1;
	this.aD3DPRESENT_PARAMETERS9964.BackBufferWidth = this.anInt9960;
	this.aD3DPRESENT_PARAMETERS9964.BackBufferHeight = this.anInt9961;
	if (this.aBoolean9955) {
	    this.aLong9957 = IDirect3DDevice.GetSwapChain(((this.aClass_ra_Sub3_Sub2_9958).aLong9847), 0);
	    this.aLong9963 = IDirect3DSwapChain.GetBackBuffer(this.aLong9957, 0, 0);
	    this.aLong9962 = (IDirect3DDevice.GetDepthStencilSurface(this.aClass_ra_Sub3_Sub2_9958.aLong9847));
	} else {
	    if (!Class_ra_Sub3_Sub2.method5551((this.aClass_ra_Sub3_Sub2_9958).anInt9844, (this.aClass_ra_Sub3_Sub2_9958).anInt9871, (this.aClass_ra_Sub3_Sub2_9958).aLong9852, (this.aClass_ra_Sub3_Sub2_9958.anInt8341), this.aD3DPRESENT_PARAMETERS9964))
		throw new RuntimeException();
	    int i = (this.aD3DPRESENT_PARAMETERS9964.AutoDepthStencilFormat);
	    this.aLong9957 = (IDirect3DDevice.CreateAdditionalSwapChain((this.aClass_ra_Sub3_Sub2_9958.aLong9847), (this.aD3DPRESENT_PARAMETERS9964)));
	    this.aLong9963 = IDirect3DSwapChain.GetBackBuffer(this.aLong9957, 0, 0);
	    this.aLong9962 = (IDirect3DDevice.CreateDepthStencilSurface((this.aClass_ra_Sub3_Sub2_9958.aLong9847), this.anInt9960, this.anInt9961, i, (this.aD3DPRESENT_PARAMETERS9964.MultiSampleType), (this.aD3DPRESENT_PARAMETERS9964.MultiSampleQuality), false));
	}
	if (this.aBoolean9959)
	    method136();
    }
}
