/* Class298_Sub19_Sub4 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class298_Sub19_Sub4 extends Class298_Sub19 {
    int anInt9266;
    Class458 aClass458_9267;
    int anInt9268;
    Class458 aClass458_9269 = new Class458();

    @Override
    public final synchronized void method2935(int i) {
	do {
	    if (this.anInt9268 < 0) {
		method3046(i);
		break;
	    }
	    if (this.anInt9266 + i < this.anInt9268) {
		this.anInt9266 += i;
		method3046(i);
		break;
	    }
	    int i_0_ = (this.anInt9268 - this.anInt9266);
	    method3046(i_0_);
	    i -= i_0_;
	    this.anInt9266 += i_0_;
	    method3044();
	    Class298_Sub22 class298_sub22 = (Class298_Sub22) this.aClass458_9267.method5967(2051088877);
	    synchronized (class298_sub22) {
		int i_1_ = class298_sub22.method3056(this);
		if (i_1_ < 0) {
		    class298_sub22.anInt7316 = 0;
		    method3049(class298_sub22);
		} else {
		    class298_sub22.anInt7316 = i_1_;
		    method3047(class298_sub22.aClass298_3187, class298_sub22);
		}
	    }
	}
	while (i != 0);
    }

    @Override
    public final synchronized void method2928(int[] is, int i, int i_2_) {
	do {
	    if (this.anInt9268 < 0) {
		method3045(is, i, i_2_);
		break;
	    }
	    if (this.anInt9266 + i_2_ < this.anInt9268) {
		this.anInt9266 += i_2_;
		method3045(is, i, i_2_);
		break;
	    }
	    int i_3_ = (this.anInt9268 - this.anInt9266);
	    method3045(is, i, i_3_);
	    i += i_3_;
	    i_2_ -= i_3_;
	    this.anInt9266 += i_3_;
	    method3044();
	    Class298_Sub22 class298_sub22 = (Class298_Sub22) this.aClass458_9267.method5967(1592454706);
	    synchronized (class298_sub22) {
		int i_4_ = class298_sub22.method3056(this);
		if (i_4_ < 0) {
		    class298_sub22.anInt7316 = 0;
		    method3049(class298_sub22);
		} else {
		    class298_sub22.anInt7316 = i_4_;
		    method3047(class298_sub22.aClass298_3187, class298_sub22);
		}
	    }
	}
	while (i_2_ != 0);
    }

    public final synchronized void method3043(Class298_Sub19 class298_sub19) {
	this.aClass458_9269.method5965(class298_sub19, -469116234);
    }

    void method3044() {
	if (this.anInt9266 > 0) {
	    for (Class298_Sub22 class298_sub22 = (Class298_Sub22) this.aClass458_9267.method5967(1813067709); class298_sub22 != null; class298_sub22 = (Class298_Sub22) this.aClass458_9267.method5969((byte) -54))
		class298_sub22.anInt7316 -= this.anInt9266;
	    this.anInt9268 -= this.anInt9266;
	    this.anInt9266 = 0;
	}
    }

    @Override
    Class298_Sub19 method2930() {
	return (Class298_Sub19) this.aClass458_9269.method5967(1668955802);
    }

    @Override
    int method2929() {
	return 0;
    }

    @Override
    public final synchronized void method2934(int[] is, int i, int i_5_) {
	do {
	    if (this.anInt9268 < 0) {
		method3045(is, i, i_5_);
		break;
	    }
	    if (this.anInt9266 + i_5_ < this.anInt9268) {
		this.anInt9266 += i_5_;
		method3045(is, i, i_5_);
		break;
	    }
	    int i_6_ = (this.anInt9268 - this.anInt9266);
	    method3045(is, i, i_6_);
	    i += i_6_;
	    i_5_ -= i_6_;
	    this.anInt9266 += i_6_;
	    method3044();
	    Class298_Sub22 class298_sub22 = (Class298_Sub22) this.aClass458_9267.method5967(1891133222);
	    synchronized (class298_sub22) {
		int i_7_ = class298_sub22.method3056(this);
		if (i_7_ < 0) {
		    class298_sub22.anInt7316 = 0;
		    method3049(class298_sub22);
		} else {
		    class298_sub22.anInt7316 = i_7_;
		    method3047(class298_sub22.aClass298_3187, class298_sub22);
		}
	    }
	}
	while (i_5_ != 0);
    }

    void method3045(int[] is, int i, int i_8_) {
	for (Class298_Sub19 class298_sub19 = (Class298_Sub19) this.aClass458_9269.method5967(1451947817); class298_sub19 != null; class298_sub19 = (Class298_Sub19) this.aClass458_9269.method5969((byte) -61))
	    class298_sub19.method2933(is, i, i_8_);
    }

    @Override
    public final synchronized void method2949(int i) {
	do {
	    if (this.anInt9268 < 0) {
		method3046(i);
		break;
	    }
	    if (this.anInt9266 + i < this.anInt9268) {
		this.anInt9266 += i;
		method3046(i);
		break;
	    }
	    int i_9_ = (this.anInt9268 - this.anInt9266);
	    method3046(i_9_);
	    i -= i_9_;
	    this.anInt9266 += i_9_;
	    method3044();
	    Class298_Sub22 class298_sub22 = (Class298_Sub22) this.aClass458_9267.method5967(1652253004);
	    synchronized (class298_sub22) {
		int i_10_ = class298_sub22.method3056(this);
		if (i_10_ < 0) {
		    class298_sub22.anInt7316 = 0;
		    method3049(class298_sub22);
		} else {
		    class298_sub22.anInt7316 = i_10_;
		    method3047(class298_sub22.aClass298_3187, class298_sub22);
		}
	    }
	}
	while (i != 0);
    }

    void method3046(int i) {
	for (Class298_Sub19 class298_sub19 = (Class298_Sub19) this.aClass458_9269.method5967(1824991064); class298_sub19 != null; class298_sub19 = (Class298_Sub19) this.aClass458_9269.method5969((byte) -117))
	    class298_sub19.method2935(i);
    }

    @Override
    Class298_Sub19 method2944() {
	return (Class298_Sub19) this.aClass458_9269.method5967(1918425993);
    }

    @Override
    Class298_Sub19 method2937() {
	return (Class298_Sub19) this.aClass458_9269.method5967(1485622229);
    }

    @Override
    Class298_Sub19 method2938() {
	return (Class298_Sub19) this.aClass458_9269.method5967(2107822679);
    }

    @Override
    Class298_Sub19 method2946() {
	return (Class298_Sub19) this.aClass458_9269.method5967(1966376550);
    }

    @Override
    Class298_Sub19 method2941() {
	return (Class298_Sub19) this.aClass458_9269.method5969((byte) -11);
    }

    @Override
    int method2942() {
	return 0;
    }

    @Override
    int method2936() {
	return 0;
    }

    @Override
    int method2943() {
	return 0;
    }

    @Override
    public final synchronized void method2932(int[] is, int i, int i_11_) {
	do {
	    if (this.anInt9268 < 0) {
		method3045(is, i, i_11_);
		break;
	    }
	    if (this.anInt9266 + i_11_ < this.anInt9268) {
		this.anInt9266 += i_11_;
		method3045(is, i, i_11_);
		break;
	    }
	    int i_12_ = (this.anInt9268 - this.anInt9266);
	    method3045(is, i, i_12_);
	    i += i_12_;
	    i_11_ -= i_12_;
	    this.anInt9266 += i_12_;
	    method3044();
	    Class298_Sub22 class298_sub22 = (Class298_Sub22) this.aClass458_9267.method5967(1616135530);
	    synchronized (class298_sub22) {
		int i_13_ = class298_sub22.method3056(this);
		if (i_13_ < 0) {
		    class298_sub22.anInt7316 = 0;
		    method3049(class298_sub22);
		} else {
		    class298_sub22.anInt7316 = i_13_;
		    method3047(class298_sub22.aClass298_3187, class298_sub22);
		}
	    }
	}
	while (i_11_ != 0);
    }

    void method3047(Class298 class298, Class298_Sub22 class298_sub22) {
	for (/**/; ((class298 != this.aClass458_9267.aClass298_5668) && (((Class298_Sub22) class298).anInt7316 <= class298_sub22.anInt7316)); class298 = class298.aClass298_3187) {
	    /* empty */
	}
	Class505.method6267(class298_sub22, class298, (byte) 6);
	this.anInt9268 = ((Class298_Sub22) (this.aClass458_9267.aClass298_5668.aClass298_3187)).anInt7316;
    }

    @Override
    public final synchronized void method2945(int[] is, int i, int i_14_) {
	do {
	    if (this.anInt9268 < 0) {
		method3045(is, i, i_14_);
		break;
	    }
	    if (this.anInt9266 + i_14_ < this.anInt9268) {
		this.anInt9266 += i_14_;
		method3045(is, i, i_14_);
		break;
	    }
	    int i_15_ = (this.anInt9268 - this.anInt9266);
	    method3045(is, i, i_15_);
	    i += i_15_;
	    i_14_ -= i_15_;
	    this.anInt9266 += i_15_;
	    method3044();
	    Class298_Sub22 class298_sub22 = (Class298_Sub22) this.aClass458_9267.method5967(1469523032);
	    synchronized (class298_sub22) {
		int i_16_ = class298_sub22.method3056(this);
		if (i_16_ < 0) {
		    class298_sub22.anInt7316 = 0;
		    method3049(class298_sub22);
		} else {
		    class298_sub22.anInt7316 = i_16_;
		    method3047(class298_sub22.aClass298_3187, class298_sub22);
		}
	    }
	}
	while (i_14_ != 0);
    }

    @Override
    public final synchronized void method2947(int i) {
	do {
	    if (this.anInt9268 < 0) {
		method3046(i);
		break;
	    }
	    if (this.anInt9266 + i < this.anInt9268) {
		this.anInt9266 += i;
		method3046(i);
		break;
	    }
	    int i_17_ = (this.anInt9268 - this.anInt9266);
	    method3046(i_17_);
	    i -= i_17_;
	    this.anInt9266 += i_17_;
	    method3044();
	    Class298_Sub22 class298_sub22 = (Class298_Sub22) this.aClass458_9267.method5967(1576830841);
	    synchronized (class298_sub22) {
		int i_18_ = class298_sub22.method3056(this);
		if (i_18_ < 0) {
		    class298_sub22.anInt7316 = 0;
		    method3049(class298_sub22);
		} else {
		    class298_sub22.anInt7316 = i_18_;
		    method3047(class298_sub22.aClass298_3187, class298_sub22);
		}
	    }
	}
	while (i != 0);
    }

    @Override
    public final synchronized void method2948(int i) {
	do {
	    if (this.anInt9268 < 0) {
		method3046(i);
		break;
	    }
	    if (this.anInt9266 + i < this.anInt9268) {
		this.anInt9266 += i;
		method3046(i);
		break;
	    }
	    int i_19_ = (this.anInt9268 - this.anInt9266);
	    method3046(i_19_);
	    i -= i_19_;
	    this.anInt9266 += i_19_;
	    method3044();
	    Class298_Sub22 class298_sub22 = (Class298_Sub22) this.aClass458_9267.method5967(1998133255);
	    synchronized (class298_sub22) {
		int i_20_ = class298_sub22.method3056(this);
		if (i_20_ < 0) {
		    class298_sub22.anInt7316 = 0;
		    method3049(class298_sub22);
		} else {
		    class298_sub22.anInt7316 = i_20_;
		    method3047(class298_sub22.aClass298_3187, class298_sub22);
		}
	    }
	}
	while (i != 0);
    }

    public Class298_Sub19_Sub4() {
	this.aClass458_9267 = new Class458();
	this.anInt9266 = 0;
	this.anInt9268 = -1;
    }

    public final synchronized void method3048(Class298_Sub19 class298_sub19) {
	class298_sub19.method2839(-1460969981);
    }

    @Override
    Class298_Sub19 method2939() {
	return (Class298_Sub19) this.aClass458_9269.method5967(1807785360);
    }

    @Override
    Class298_Sub19 method2931() {
	return (Class298_Sub19) this.aClass458_9269.method5969((byte) -123);
    }

    @Override
    Class298_Sub19 method2940() {
	return (Class298_Sub19) this.aClass458_9269.method5969((byte) -119);
    }

    void method3049(Class298_Sub22 class298_sub22) {
	class298_sub22.method2839(-1460969981);
	class298_sub22.method3055();
	Class298 class298 = (this.aClass458_9267.aClass298_5668.aClass298_3187);
	if (class298 == this.aClass458_9267.aClass298_5668)
	    this.anInt9268 = -1;
	else
	    this.anInt9268 = ((Class298_Sub22) class298).anInt7316;
    }
}
