/* Class298_Sub19_Sub2 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class298_Sub19_Sub2 extends Class298_Sub19 {
    int anInt9241;
    int anInt9242;
    int anInt9243;
    int anInt9244;
    int anInt9245;
    int anInt9246;
    int anInt9247;
    int anInt9248;
    int anInt9249;
    int anInt9250;
    boolean aBoolean9251;
    int anInt9252;
    int anInt9253;
    int anInt9254;
    int anInt9255;

    static int method2988(int i, int i_0_) {
	return (i_0_ < 0 ? -i : (int) (i * Math.sqrt(i_0_ * 1.220703125E-4) + 0.5));
    }

    public synchronized void method2989(int i) {
	method2995(method3019(), i);
    }

    @Override
    int method2950() {
	int i = this.anInt9241 * 3 >> 6;
	i = (i ^ i >> 31) + (i >>> 31);
	if (this.anInt9245 == 0)
	    i -= (i * this.anInt9248 / ((((Class298_Sub26_Sub1) this.aClass298_Sub26_7312).aByteArray9309).length << 8));
	else if (this.anInt9245 >= 0)
	    i -= (i * this.anInt9249 / (((Class298_Sub26_Sub1) this.aClass298_Sub26_7312).aByteArray9309).length);
	return i > 255 ? 255 : i;
    }

    Class298_Sub19_Sub2(Class298_Sub26_Sub1 class298_sub26_sub1, int i, int i_1_, int i_2_) {
	this.aClass298_Sub26_7312 = class298_sub26_sub1;
	this.anInt9249 = class298_sub26_sub1.anInt9311;
	this.anInt9250 = class298_sub26_sub1.anInt9310;
	this.aBoolean9251 = class298_sub26_sub1.aBoolean9313;
	this.anInt9242 = i;
	this.anInt9243 = i_1_;
	this.anInt9254 = i_2_;
	this.anInt9248 = 0;
	method2990();
    }

    void method2990() {
	this.anInt9241 = this.anInt9243;
	this.anInt9246 = method3001(this.anInt9243, this.anInt9254);
	this.anInt9247 = method2988(this.anInt9243, this.anInt9254);
    }

    public synchronized void method2991(int i) {
	this.anInt9245 = i;
    }

    public synchronized void method2992(int i) {
	method2995(i << 6, method2996());
    }

    public synchronized void method2993(int i) {
	if (i == 0) {
	    method3006(0);
	    method2839(-1460969981);
	} else if (this.anInt9246 == 0 && this.anInt9247 == 0) {
	    this.anInt9252 = 0;
	    this.anInt9243 = 0;
	    this.anInt9241 = 0;
	    method2839(-1460969981);
	} else {
	    int i_3_ = -this.anInt9241;
	    if (this.anInt9241 > i_3_)
		i_3_ = this.anInt9241;
	    if (-this.anInt9246 > i_3_)
		i_3_ = -this.anInt9246;
	    if (this.anInt9246 > i_3_)
		i_3_ = this.anInt9246;
	    if (-this.anInt9247 > i_3_)
		i_3_ = -this.anInt9247;
	    if (this.anInt9247 > i_3_)
		i_3_ = this.anInt9247;
	    if (i > i_3_)
		i = i_3_;
	    this.anInt9252 = i;
	    this.anInt9243 = -2147483648;
	    this.anInt9253 = -this.anInt9241 / i;
	    this.anInt9244 = -this.anInt9246 / i;
	    this.anInt9255 = -this.anInt9247 / i;
	}
    }

    int method2994() {
	int i = this.anInt9241 * 3 >> 6;
	i = (i ^ i >> 31) + (i >>> 31);
	if (this.anInt9245 == 0)
	    i -= (i * this.anInt9248 / ((((Class298_Sub26_Sub1) this.aClass298_Sub26_7312).aByteArray9309).length << 8));
	else if (this.anInt9245 >= 0)
	    i -= (i * this.anInt9249 / (((Class298_Sub26_Sub1) this.aClass298_Sub26_7312).aByteArray9309).length);
	return i > 255 ? 255 : i;
    }

    synchronized void method2995(int i, int i_4_) {
	this.anInt9243 = i;
	this.anInt9254 = i_4_;
	this.anInt9252 = 0;
	method2990();
    }

    public synchronized int method2996() {
	return (this.anInt9254 < 0 ? -1 : this.anInt9254);
    }

    public synchronized void method2997(int i) {
	int i_5_ = ((((Class298_Sub26_Sub1) this.aClass298_Sub26_7312).aByteArray9309).length << 8);
	if (i < -1)
	    i = -1;
	if (i > i_5_)
	    i = i_5_;
	this.anInt9248 = i;
    }

    void method2998() {
	if (this.anInt9252 != 0) {
	    if (this.anInt9243 == -2147483648)
		this.anInt9243 = 0;
	    this.anInt9252 = 0;
	    method2990();
	}
    }

    public synchronized void method2999(int i, int i_6_) {
	method3023(i, i_6_, method2996());
    }

    @Override
    public synchronized void method2935(int i) {
	if (this.anInt9252 > 0) {
	    if (i >= this.anInt9252) {
		if (this.anInt9243 == -2147483648) {
		    this.anInt9243 = 0;
		    this.anInt9247 = 0;
		    this.anInt9246 = 0;
		    this.anInt9241 = 0;
		    method2839(-1460969981);
		    i = this.anInt9252;
		}
		this.anInt9252 = 0;
		method2990();
	    } else {
		this.anInt9241 += this.anInt9253 * i;
		this.anInt9246 += this.anInt9244 * i;
		this.anInt9247 += this.anInt9255 * i;
		this.anInt9252 -= i;
	    }
	}
	Class298_Sub26_Sub1 class298_sub26_sub1 = ((Class298_Sub26_Sub1) this.aClass298_Sub26_7312);
	int i_7_ = this.anInt9249 << 8;
	int i_8_ = this.anInt9250 << 8;
	int i_9_ = class298_sub26_sub1.aByteArray9309.length << 8;
	int i_10_ = i_8_ - i_7_;
	if (i_10_ <= 0)
	    this.anInt9245 = 0;
	if (this.anInt9248 < 0) {
	    if (this.anInt9242 > 0)
		this.anInt9248 = 0;
	    else {
		method2998();
		method2839(-1460969981);
		return;
	    }
	}
	if (this.anInt9248 >= i_9_) {
	    if (this.anInt9242 < 0)
		this.anInt9248 = i_9_ - 1;
	    else {
		method2998();
		method2839(-1460969981);
		return;
	    }
	}
	this.anInt9248 += this.anInt9242 * i;
	if (this.anInt9245 < 0) {
	    if (this.aBoolean9251) {
		if (this.anInt9242 < 0) {
		    if (this.anInt9248 >= i_7_)
			return;
		    this.anInt9248 = (i_7_ + i_7_ - 1 - this.anInt9248);
		    this.anInt9242 = -this.anInt9242;
		}
		while (this.anInt9248 >= i_8_) {
		    this.anInt9248 = (i_8_ + i_8_ - 1 - this.anInt9248);
		    this.anInt9242 = -this.anInt9242;
		    if (this.anInt9248 >= i_7_)
			break;
		    this.anInt9248 = (i_7_ + i_7_ - 1 - this.anInt9248);
		    this.anInt9242 = -this.anInt9242;
		}
	    } else if (this.anInt9242 < 0) {
		if (this.anInt9248 < i_7_)
		    this.anInt9248 = i_8_ - 1 - (i_8_ - 1 - (this.anInt9248)) % i_10_;
	    } else if (this.anInt9248 >= i_8_)
		this.anInt9248 = i_7_ + ((this.anInt9248 - i_7_) % i_10_);
	} else {
	    do {
		if (this.anInt9245 > 0) {
		    if (this.aBoolean9251) {
			if (this.anInt9242 < 0) {
			    if (this.anInt9248 >= i_7_)
				return;
			    this.anInt9248 = (i_7_ + i_7_ - 1 - this.anInt9248);
			    this.anInt9242 = -this.anInt9242;
			    if (--this.anInt9245 == 0)
				break;
			}
			do {
			    if (this.anInt9248 < i_8_)
				return;
			    this.anInt9248 = (i_8_ + i_8_ - 1 - this.anInt9248);
			    this.anInt9242 = -this.anInt9242;
			    if (--this.anInt9245 == 0)
				break;
			    if (this.anInt9248 >= i_7_)
				return;
			    this.anInt9248 = (i_7_ + i_7_ - 1 - this.anInt9248);
			    this.anInt9242 = -this.anInt9242;
			}
			while (--this.anInt9245 != 0);
		    } else {
			if (this.anInt9242 < 0) {
			    if (this.anInt9248 < i_7_) {
				int i_11_ = (i_8_ - 1 - (this.anInt9248)) / i_10_;
				if (i_11_ >= (this.anInt9245)) {
				    this.anInt9248 += i_10_ * this.anInt9245;
				    this.anInt9245 = 0;
				    break;
				}
				this.anInt9248 += i_10_ * i_11_;
				this.anInt9245 -= i_11_;
			    }
			} else if (this.anInt9248 >= i_8_) {
			    int i_12_ = (this.anInt9248 - i_7_) / i_10_;
			    if (i_12_ >= this.anInt9245) {
				this.anInt9248 -= i_10_ * (this.anInt9245);
				this.anInt9245 = 0;
				break;
			    }
			    this.anInt9248 -= i_10_ * i_12_;
			    this.anInt9245 -= i_12_;
			}
			return;
		    }
		}
	    }
	    while (false);
	    if (this.anInt9242 < 0) {
		if (this.anInt9248 < 0) {
		    this.anInt9248 = -1;
		    method2998();
		    method2839(-1460969981);
		}
	    } else if (this.anInt9248 >= i_9_) {
		this.anInt9248 = i_9_;
		method2998();
		method2839(-1460969981);
	    }
	}
    }

    public synchronized void method3000(int i) {
	if (this.anInt9242 < 0)
	    this.anInt9242 = -i;
	else
	    this.anInt9242 = i;
    }

    static int method3001(int i, int i_13_) {
	return (i_13_ < 0 ? i : (int) (i * Math.sqrt((16384 - i_13_) * 1.220703125E-4) + 0.5));
    }

    public synchronized boolean method3002() {
	return (this.anInt9248 < 0 || (this.anInt9248 >= (((Class298_Sub26_Sub1) this.aClass298_Sub26_7312).aByteArray9309).length << 8));
    }

    public synchronized boolean method3003() {
	return this.anInt9252 != 0;
    }

    @Override
    public synchronized void method2948(int i) {
	if (this.anInt9252 > 0) {
	    if (i >= this.anInt9252) {
		if (this.anInt9243 == -2147483648) {
		    this.anInt9243 = 0;
		    this.anInt9247 = 0;
		    this.anInt9246 = 0;
		    this.anInt9241 = 0;
		    method2839(-1460969981);
		    i = this.anInt9252;
		}
		this.anInt9252 = 0;
		method2990();
	    } else {
		this.anInt9241 += this.anInt9253 * i;
		this.anInt9246 += this.anInt9244 * i;
		this.anInt9247 += this.anInt9255 * i;
		this.anInt9252 -= i;
	    }
	}
	Class298_Sub26_Sub1 class298_sub26_sub1 = ((Class298_Sub26_Sub1) this.aClass298_Sub26_7312);
	int i_14_ = this.anInt9249 << 8;
	int i_15_ = this.anInt9250 << 8;
	int i_16_ = class298_sub26_sub1.aByteArray9309.length << 8;
	int i_17_ = i_15_ - i_14_;
	if (i_17_ <= 0)
	    this.anInt9245 = 0;
	if (this.anInt9248 < 0) {
	    if (this.anInt9242 > 0)
		this.anInt9248 = 0;
	    else {
		method2998();
		method2839(-1460969981);
		return;
	    }
	}
	if (this.anInt9248 >= i_16_) {
	    if (this.anInt9242 < 0)
		this.anInt9248 = i_16_ - 1;
	    else {
		method2998();
		method2839(-1460969981);
		return;
	    }
	}
	this.anInt9248 += this.anInt9242 * i;
	if (this.anInt9245 < 0) {
	    if (this.aBoolean9251) {
		if (this.anInt9242 < 0) {
		    if (this.anInt9248 >= i_14_)
			return;
		    this.anInt9248 = (i_14_ + i_14_ - 1 - this.anInt9248);
		    this.anInt9242 = -this.anInt9242;
		}
		while (this.anInt9248 >= i_15_) {
		    this.anInt9248 = (i_15_ + i_15_ - 1 - this.anInt9248);
		    this.anInt9242 = -this.anInt9242;
		    if (this.anInt9248 >= i_14_)
			break;
		    this.anInt9248 = (i_14_ + i_14_ - 1 - this.anInt9248);
		    this.anInt9242 = -this.anInt9242;
		}
	    } else if (this.anInt9242 < 0) {
		if (this.anInt9248 < i_14_)
		    this.anInt9248 = i_15_ - 1 - (i_15_ - 1 - (this.anInt9248)) % i_17_;
	    } else if (this.anInt9248 >= i_15_)
		this.anInt9248 = i_14_ + ((this.anInt9248 - i_14_) % i_17_);
	} else {
	    do {
		if (this.anInt9245 > 0) {
		    if (this.aBoolean9251) {
			if (this.anInt9242 < 0) {
			    if (this.anInt9248 >= i_14_)
				return;
			    this.anInt9248 = (i_14_ + i_14_ - 1 - this.anInt9248);
			    this.anInt9242 = -this.anInt9242;
			    if (--this.anInt9245 == 0)
				break;
			}
			do {
			    if (this.anInt9248 < i_15_)
				return;
			    this.anInt9248 = (i_15_ + i_15_ - 1 - this.anInt9248);
			    this.anInt9242 = -this.anInt9242;
			    if (--this.anInt9245 == 0)
				break;
			    if (this.anInt9248 >= i_14_)
				return;
			    this.anInt9248 = (i_14_ + i_14_ - 1 - this.anInt9248);
			    this.anInt9242 = -this.anInt9242;
			}
			while (--this.anInt9245 != 0);
		    } else {
			if (this.anInt9242 < 0) {
			    if (this.anInt9248 < i_14_) {
				int i_18_ = ((i_15_ - 1 - (this.anInt9248)) / i_17_);
				if (i_18_ >= (this.anInt9245)) {
				    this.anInt9248 += i_17_ * this.anInt9245;
				    this.anInt9245 = 0;
				    break;
				}
				this.anInt9248 += i_17_ * i_18_;
				this.anInt9245 -= i_18_;
			    }
			} else if (this.anInt9248 >= i_15_) {
			    int i_19_ = (this.anInt9248 - i_14_) / i_17_;
			    if (i_19_ >= this.anInt9245) {
				this.anInt9248 -= i_17_ * (this.anInt9245);
				this.anInt9245 = 0;
				break;
			    }
			    this.anInt9248 -= i_17_ * i_19_;
			    this.anInt9245 -= i_19_;
			}
			return;
		    }
		}
	    }
	    while (false);
	    if (this.anInt9242 < 0) {
		if (this.anInt9248 < 0) {
		    this.anInt9248 = -1;
		    method2998();
		    method2839(-1460969981);
		}
	    } else if (this.anInt9248 >= i_16_) {
		this.anInt9248 = i_16_;
		method2998();
		method2839(-1460969981);
	    }
	}
    }

    @Override
    int method2929() {
	if (this.anInt9243 == 0 && this.anInt9252 == 0)
	    return 0;
	return 1;
    }

    @Override
    public synchronized void method2934(int[] is, int i, int i_20_) {
	if (this.anInt9243 == 0 && this.anInt9252 == 0)
	    method2935(i_20_);
	else {
	    Class298_Sub26_Sub1 class298_sub26_sub1 = ((Class298_Sub26_Sub1) this.aClass298_Sub26_7312);
	    int i_21_ = this.anInt9249 << 8;
	    int i_22_ = this.anInt9250 << 8;
	    int i_23_ = class298_sub26_sub1.aByteArray9309.length << 8;
	    int i_24_ = i_22_ - i_21_;
	    if (i_24_ <= 0)
		this.anInt9245 = 0;
	    int i_25_ = i;
	    i_20_ += i;
	    if (this.anInt9248 < 0) {
		if (this.anInt9242 > 0)
		    this.anInt9248 = 0;
		else {
		    method2998();
		    method2839(-1460969981);
		    return;
		}
	    }
	    if (this.anInt9248 >= i_23_) {
		if (this.anInt9242 < 0)
		    this.anInt9248 = i_23_ - 1;
		else {
		    method2998();
		    method2839(-1460969981);
		    return;
		}
	    }
	    if (this.anInt9245 < 0) {
		if (this.aBoolean9251) {
		    if (this.anInt9242 < 0) {
			i_25_ = method3025(is, i_25_, i_21_, i_20_, (class298_sub26_sub1.aByteArray9309[(this.anInt9249)]));
			if (this.anInt9248 >= i_21_)
			    return;
			this.anInt9248 = (i_21_ + i_21_ - 1 - this.anInt9248);
			this.anInt9242 = -this.anInt9242;
		    }
		    for (;;) {
			i_25_ = method3004(is, i_25_, i_22_, i_20_, (class298_sub26_sub1.aByteArray9309[(this.anInt9250) - 1]));
			if (this.anInt9248 < i_22_)
			    break;
			this.anInt9248 = (i_22_ + i_22_ - 1 - this.anInt9248);
			this.anInt9242 = -this.anInt9242;
			i_25_ = method3025(is, i_25_, i_21_, i_20_, (class298_sub26_sub1.aByteArray9309[(this.anInt9249)]));
			if (this.anInt9248 >= i_21_)
			    break;
			this.anInt9248 = (i_21_ + i_21_ - 1 - this.anInt9248);
			this.anInt9242 = -this.anInt9242;
		    }
		} else if (this.anInt9242 < 0) {
		    for (;;) {
			i_25_ = method3025(is, i_25_, i_21_, i_20_, (class298_sub26_sub1.aByteArray9309[(this.anInt9250) - 1]));
			if (this.anInt9248 >= i_21_)
			    break;
			this.anInt9248 = i_22_ - 1 - (i_22_ - 1 - (this.anInt9248)) % i_24_;
		    }
		} else {
		    for (;;) {
			i_25_ = method3004(is, i_25_, i_22_, i_20_, (class298_sub26_sub1.aByteArray9309[(this.anInt9249)]));
			if (this.anInt9248 < i_22_)
			    break;
			this.anInt9248 = i_21_ + (this.anInt9248 - i_21_) % i_24_;
		    }
		}
	    } else {
		do {
		    if (this.anInt9245 > 0) {
			if (this.aBoolean9251) {
			    if (this.anInt9242 < 0) {
				i_25_ = method3025(is, i_25_, i_21_, i_20_, (class298_sub26_sub1.aByteArray9309[this.anInt9249]));
				if (this.anInt9248 >= i_21_)
				    return;
				this.anInt9248 = (i_21_ + i_21_ - 1 - (this.anInt9248));
				this.anInt9242 = -this.anInt9242;
				if (--this.anInt9245 == 0)
				    break;
			    }
			    do {
				i_25_ = method3004(is, i_25_, i_22_, i_20_, (class298_sub26_sub1.aByteArray9309[this.anInt9250 - 1]));
				if (this.anInt9248 < i_22_)
				    return;
				this.anInt9248 = (i_22_ + i_22_ - 1 - (this.anInt9248));
				this.anInt9242 = -this.anInt9242;
				if (--this.anInt9245 == 0)
				    break;
				i_25_ = method3025(is, i_25_, i_21_, i_20_, (class298_sub26_sub1.aByteArray9309[this.anInt9249]));
				if (this.anInt9248 >= i_21_)
				    return;
				this.anInt9248 = (i_21_ + i_21_ - 1 - (this.anInt9248));
				this.anInt9242 = -this.anInt9242;
			    }
			    while (--this.anInt9245 != 0);
			} else if (this.anInt9242 < 0) {
			    for (;;) {
				i_25_ = method3025(is, i_25_, i_21_, i_20_, (class298_sub26_sub1.aByteArray9309[this.anInt9250 - 1]));
				if (this.anInt9248 >= i_21_)
				    return;
				int i_26_ = ((i_22_ - 1 - (this.anInt9248)) / i_24_);
				if (i_26_ >= (this.anInt9245)) {
				    this.anInt9248 += i_24_ * this.anInt9245;
				    this.anInt9245 = 0;
				    break;
				}
				this.anInt9248 += i_24_ * i_26_;
				this.anInt9245 -= i_26_;
			    }
			} else {
			    for (;;) {
				i_25_ = method3004(is, i_25_, i_22_, i_20_, (class298_sub26_sub1.aByteArray9309[this.anInt9249]));
				if (this.anInt9248 < i_22_)
				    return;
				int i_27_ = ((this.anInt9248 - i_21_) / i_24_);
				if (i_27_ >= (this.anInt9245)) {
				    this.anInt9248 -= i_24_ * this.anInt9245;
				    this.anInt9245 = 0;
				    break;
				}
				this.anInt9248 -= i_24_ * i_27_;
				this.anInt9245 -= i_27_;
			    }
			}
		    }
		}
		while (false);
		if (this.anInt9242 < 0) {
		    method3025(is, i_25_, 0, i_20_, 0);
		    if (this.anInt9248 < 0) {
			this.anInt9248 = -1;
			method2998();
			method2839(-1460969981);
		    }
		} else {
		    method3004(is, i_25_, i_23_, i_20_, 0);
		    if (this.anInt9248 >= i_23_) {
			this.anInt9248 = i_23_;
			method2998();
			method2839(-1460969981);
		    }
		}
	    }
	}
    }

    int method3004(int[] is, int i, int i_28_, int i_29_, int i_30_) {
	while_17_: do {
	    do {
		if (this.anInt9252 <= 0)
		    break while_17_;
		int i_31_ = i + this.anInt9252;
		if (i_31_ > i_29_)
		    i_31_ = i_29_;
		this.anInt9252 += i;
		if (this.anInt9242 == 256 && (this.anInt9248 & 0xff) == 0) {
		    if (Class284.aBoolean3047)
			i = method3012(0, (((Class298_Sub26_Sub1) (this.aClass298_Sub26_7312)).aByteArray9309), is, this.anInt9248, i, this.anInt9246, this.anInt9247, this.anInt9244, this.anInt9255, 0, i_31_, i_28_, this);
		    else
			i = method3005((((Class298_Sub26_Sub1) (this.aClass298_Sub26_7312)).aByteArray9309), is, this.anInt9248, i, this.anInt9241, this.anInt9253, 0, i_31_, i_28_, this);
		} else if (Class284.aBoolean3047)
		    i = method3018(0, 0, ((Class298_Sub26_Sub1) (this.aClass298_Sub26_7312)).aByteArray9309, is, this.anInt9248, i, this.anInt9246, this.anInt9247, this.anInt9244, this.anInt9255, 0, i_31_, i_28_, this, this.anInt9242, i_30_);
		else
		    i = method3022(0, 0, ((Class298_Sub26_Sub1) (this.aClass298_Sub26_7312)).aByteArray9309, is, this.anInt9248, i, this.anInt9241, this.anInt9253, 0, i_31_, i_28_, this, this.anInt9242, i_30_);
		this.anInt9252 -= i;
		if (this.anInt9252 != 0)
		    return i;
	    }
	    while (!method3021());
	    return i_29_;
	}
	while (false);
	if (this.anInt9242 == 256 && (this.anInt9248 & 0xff) == 0) {
	    if (Class284.aBoolean3047)
		return method3028(0, ((Class298_Sub26_Sub1) (this.aClass298_Sub26_7312)).aByteArray9309, is, this.anInt9248, i, this.anInt9246, this.anInt9247, 0, i_29_, i_28_, this);
	    return method3017(((Class298_Sub26_Sub1) (this.aClass298_Sub26_7312)).aByteArray9309, is, this.anInt9248, i, this.anInt9241, 0, i_29_, i_28_, this);
	}
	if (Class284.aBoolean3047)
	    return method3010(0, 0, ((Class298_Sub26_Sub1) (this.aClass298_Sub26_7312)).aByteArray9309, is, this.anInt9248, i, this.anInt9246, this.anInt9247, 0, i_29_, i_28_, this, this.anInt9242, i_30_);
	return method3009(0, 0, (((Class298_Sub26_Sub1) this.aClass298_Sub26_7312).aByteArray9309), is, this.anInt9248, i, this.anInt9241, 0, i_29_, i_28_, this, this.anInt9242, i_30_);
    }

    static int method3005(byte[] is, int[] is_32_, int i, int i_33_, int i_34_, int i_35_, int i_36_, int i_37_, int i_38_, Class298_Sub19_Sub2 class298_sub19_sub2) {
	i >>= 8;
	i_38_ >>= 8;
	i_34_ <<= 2;
	i_35_ <<= 2;
	if ((i_36_ = i_33_ + i_38_ - i) > i_37_)
	    i_36_ = i_37_;
	class298_sub19_sub2.anInt9246 += (class298_sub19_sub2.anInt9244 * (i_36_ - i_33_));
	class298_sub19_sub2.anInt9247 += (class298_sub19_sub2.anInt9255 * (i_36_ - i_33_));
	i_36_ -= 3;
	while (i_33_ < i_36_) {
	    is_32_[i_33_++] += is[i++] * i_34_;
	    i_34_ += i_35_;
	    is_32_[i_33_++] += is[i++] * i_34_;
	    i_34_ += i_35_;
	    is_32_[i_33_++] += is[i++] * i_34_;
	    i_34_ += i_35_;
	    is_32_[i_33_++] += is[i++] * i_34_;
	    i_34_ += i_35_;
	}
	i_36_ += 3;
	while (i_33_ < i_36_) {
	    is_32_[i_33_++] += is[i++] * i_34_;
	    i_34_ += i_35_;
	}
	class298_sub19_sub2.anInt9241 = i_34_ >> 2;
	class298_sub19_sub2.anInt9248 = i << 8;
	return i_33_;
    }

    synchronized void method3006(int i) {
	method2995(i, method2996());
    }

    static int method3007(byte[] is, int[] is_39_, int i, int i_40_, int i_41_, int i_42_, int i_43_, int i_44_, Class298_Sub19_Sub2 class298_sub19_sub2) {
	i >>= 8;
	i_44_ >>= 8;
	i_41_ <<= 2;
	if ((i_42_ = i_40_ + i - (i_44_ - 1)) > i_43_)
	    i_42_ = i_43_;
	i_42_ -= 3;
	while (i_40_ < i_42_) {
	    is_39_[i_40_++] += is[i--] * i_41_;
	    is_39_[i_40_++] += is[i--] * i_41_;
	    is_39_[i_40_++] += is[i--] * i_41_;
	    is_39_[i_40_++] += is[i--] * i_41_;
	}
	i_42_ += 3;
	while (i_40_ < i_42_)
	    is_39_[i_40_++] += is[i--] * i_41_;
	class298_sub19_sub2.anInt9248 = i << 8;
	return i_40_;
    }

    static int method3008(int i, byte[] is, int[] is_45_, int i_46_, int i_47_, int i_48_, int i_49_, int i_50_, int i_51_, int i_52_, Class298_Sub19_Sub2 class298_sub19_sub2) {
	i_46_ >>= 8;
	i_52_ >>= 8;
	i_48_ <<= 2;
	i_49_ <<= 2;
	if ((i_50_ = i_47_ + i_46_ - (i_52_ - 1)) > i_51_)
	    i_50_ = i_51_;
	i_47_ <<= 1;
	i_50_ <<= 1;
	i_50_ -= 6;
	while (i_47_ < i_50_) {
	    i = is[i_46_--];
	    is_45_[i_47_++] += i * i_48_;
	    is_45_[i_47_++] += i * i_49_;
	    i = is[i_46_--];
	    is_45_[i_47_++] += i * i_48_;
	    is_45_[i_47_++] += i * i_49_;
	    i = is[i_46_--];
	    is_45_[i_47_++] += i * i_48_;
	    is_45_[i_47_++] += i * i_49_;
	    i = is[i_46_--];
	    is_45_[i_47_++] += i * i_48_;
	    is_45_[i_47_++] += i * i_49_;
	}
	i_50_ += 6;
	while (i_47_ < i_50_) {
	    i = is[i_46_--];
	    is_45_[i_47_++] += i * i_48_;
	    is_45_[i_47_++] += i * i_49_;
	}
	class298_sub19_sub2.anInt9248 = i_46_ << 8;
	return i_47_ >> 1;
    }

    static int method3009(int i, int i_53_, byte[] is, int[] is_54_, int i_55_, int i_56_, int i_57_, int i_58_, int i_59_, int i_60_, Class298_Sub19_Sub2 class298_sub19_sub2, int i_61_, int i_62_) {
	if (i_61_ == 0 || (i_58_ = i_56_ + (i_60_ - i_55_ + i_61_ - 257) / i_61_) > i_59_)
	    i_58_ = i_59_;
	while (i_56_ < i_58_) {
	    i_53_ = i_55_ >> 8;
	    i = is[i_53_];
	    is_54_[i_56_++] += (((i << 8) + (is[i_53_ + 1] - i) * (i_55_ & 0xff)) * i_57_ >> 6);
	    i_55_ += i_61_;
	}
	if (i_61_ == 0 || (i_58_ = i_56_ + (i_60_ - i_55_ + i_61_ - 1) / i_61_) > i_59_)
	    i_58_ = i_59_;
	i_53_ = i_62_;
	while (i_56_ < i_58_) {
	    i = is[i_55_ >> 8];
	    is_54_[i_56_++] += ((i << 8) + (i_53_ - i) * (i_55_ & 0xff)) * i_57_ >> 6;
	    i_55_ += i_61_;
	}
	class298_sub19_sub2.anInt9248 = i_55_;
	return i_56_;
    }

    static int method3010(int i, int i_63_, byte[] is, int[] is_64_, int i_65_, int i_66_, int i_67_, int i_68_, int i_69_, int i_70_, int i_71_, Class298_Sub19_Sub2 class298_sub19_sub2, int i_72_, int i_73_) {
	if (i_72_ == 0 || (i_69_ = i_66_ + (i_71_ - i_65_ + i_72_ - 257) / i_72_) > i_70_)
	    i_69_ = i_70_;
	i_66_ <<= 1;
	i_69_ <<= 1;
	while (i_66_ < i_69_) {
	    i_63_ = i_65_ >> 8;
	    i = is[i_63_];
	    i = (i << 8) + (is[i_63_ + 1] - i) * (i_65_ & 0xff);
	    is_64_[i_66_++] += i * i_67_ >> 6;
	    is_64_[i_66_++] += i * i_68_ >> 6;
	    i_65_ += i_72_;
	}
	if (i_72_ == 0 || ((i_69_ = (i_66_ >> 1) + (i_71_ - i_65_ + i_72_ - 1) / i_72_) > i_70_))
	    i_69_ = i_70_;
	i_69_ <<= 1;
	i_63_ = i_73_;
	while (i_66_ < i_69_) {
	    i = is[i_65_ >> 8];
	    i = (i << 8) + (i_63_ - i) * (i_65_ & 0xff);
	    is_64_[i_66_++] += i * i_67_ >> 6;
	    is_64_[i_66_++] += i * i_68_ >> 6;
	    i_65_ += i_72_;
	}
	class298_sub19_sub2.anInt9248 = i_65_;
	return i_66_ >> 1;
    }

    @Override
    int method2936() {
	if (this.anInt9243 == 0 && this.anInt9252 == 0)
	    return 0;
	return 1;
    }

    static int method3011(int i, int i_74_, byte[] is, int[] is_75_, int i_76_, int i_77_, int i_78_, int i_79_, int i_80_, int i_81_, int i_82_, Class298_Sub19_Sub2 class298_sub19_sub2, int i_83_, int i_84_) {
	if (i_83_ == 0 || (i_80_ = i_77_ + (i_82_ + 256 - i_76_ + i_83_) / i_83_) > i_81_)
	    i_80_ = i_81_;
	i_77_ <<= 1;
	i_80_ <<= 1;
	while (i_77_ < i_80_) {
	    i_74_ = i_76_ >> 8;
	    i = is[i_74_ - 1];
	    i = (i << 8) + (is[i_74_] - i) * (i_76_ & 0xff);
	    is_75_[i_77_++] += i * i_78_ >> 6;
	    is_75_[i_77_++] += i * i_79_ >> 6;
	    i_76_ += i_83_;
	}
	if (i_83_ == 0 || ((i_80_ = (i_77_ >> 1) + (i_82_ - i_76_ + i_83_) / i_83_) > i_81_))
	    i_80_ = i_81_;
	i_80_ <<= 1;
	i_74_ = i_84_;
	while (i_77_ < i_80_) {
	    i = (i_74_ << 8) + (is[i_76_ >> 8] - i_74_) * (i_76_ & 0xff);
	    is_75_[i_77_++] += i * i_78_ >> 6;
	    is_75_[i_77_++] += i * i_79_ >> 6;
	    i_76_ += i_83_;
	}
	class298_sub19_sub2.anInt9248 = i_76_;
	return i_77_ >> 1;
    }

    static int method3012(int i, byte[] is, int[] is_85_, int i_86_, int i_87_, int i_88_, int i_89_, int i_90_, int i_91_, int i_92_, int i_93_, int i_94_, Class298_Sub19_Sub2 class298_sub19_sub2) {
	i_86_ >>= 8;
	i_94_ >>= 8;
	i_88_ <<= 2;
	i_89_ <<= 2;
	i_90_ <<= 2;
	i_91_ <<= 2;
	if ((i_92_ = i_87_ + i_94_ - i_86_) > i_93_)
	    i_92_ = i_93_;
	class298_sub19_sub2.anInt9241 += (class298_sub19_sub2.anInt9253 * (i_92_ - i_87_));
	i_87_ <<= 1;
	i_92_ <<= 1;
	i_92_ -= 6;
	while (i_87_ < i_92_) {
	    i = is[i_86_++];
	    is_85_[i_87_++] += i * i_88_;
	    i_88_ += i_90_;
	    is_85_[i_87_++] += i * i_89_;
	    i_89_ += i_91_;
	    i = is[i_86_++];
	    is_85_[i_87_++] += i * i_88_;
	    i_88_ += i_90_;
	    is_85_[i_87_++] += i * i_89_;
	    i_89_ += i_91_;
	    i = is[i_86_++];
	    is_85_[i_87_++] += i * i_88_;
	    i_88_ += i_90_;
	    is_85_[i_87_++] += i * i_89_;
	    i_89_ += i_91_;
	    i = is[i_86_++];
	    is_85_[i_87_++] += i * i_88_;
	    i_88_ += i_90_;
	    is_85_[i_87_++] += i * i_89_;
	    i_89_ += i_91_;
	}
	i_92_ += 6;
	while (i_87_ < i_92_) {
	    i = is[i_86_++];
	    is_85_[i_87_++] += i * i_88_;
	    i_88_ += i_90_;
	    is_85_[i_87_++] += i * i_89_;
	    i_89_ += i_91_;
	}
	class298_sub19_sub2.anInt9246 = i_88_ >> 2;
	class298_sub19_sub2.anInt9247 = i_89_ >> 2;
	class298_sub19_sub2.anInt9248 = i_86_ << 8;
	return i_87_ >> 1;
    }

    static int method3013(byte[] is, int[] is_95_, int i, int i_96_, int i_97_, int i_98_, int i_99_, int i_100_, int i_101_, Class298_Sub19_Sub2 class298_sub19_sub2) {
	i >>= 8;
	i_101_ >>= 8;
	i_97_ <<= 2;
	i_98_ <<= 2;
	if ((i_99_ = i_96_ + i - (i_101_ - 1)) > i_100_)
	    i_99_ = i_100_;
	class298_sub19_sub2.anInt9246 += (class298_sub19_sub2.anInt9244 * (i_99_ - i_96_));
	class298_sub19_sub2.anInt9247 += (class298_sub19_sub2.anInt9255 * (i_99_ - i_96_));
	i_99_ -= 3;
	while (i_96_ < i_99_) {
	    is_95_[i_96_++] += is[i--] * i_97_;
	    i_97_ += i_98_;
	    is_95_[i_96_++] += is[i--] * i_97_;
	    i_97_ += i_98_;
	    is_95_[i_96_++] += is[i--] * i_97_;
	    i_97_ += i_98_;
	    is_95_[i_96_++] += is[i--] * i_97_;
	    i_97_ += i_98_;
	}
	i_99_ += 3;
	while (i_96_ < i_99_) {
	    is_95_[i_96_++] += is[i--] * i_97_;
	    i_97_ += i_98_;
	}
	class298_sub19_sub2.anInt9241 = i_97_ >> 2;
	class298_sub19_sub2.anInt9248 = i << 8;
	return i_96_;
    }

    @Override
    Class298_Sub19 method2930() {
	return null;
    }

    static int method3014(int i, int i_102_, byte[] is, int[] is_103_, int i_104_, int i_105_, int i_106_, int i_107_, int i_108_, int i_109_, int i_110_, Class298_Sub19_Sub2 class298_sub19_sub2, int i_111_, int i_112_) {
	class298_sub19_sub2.anInt9246 -= class298_sub19_sub2.anInt9244 * i_105_;
	class298_sub19_sub2.anInt9247 -= class298_sub19_sub2.anInt9255 * i_105_;
	if (i_111_ == 0 || ((i_108_ = i_105_ + (i_110_ + 256 - i_104_ + i_111_) / i_111_) > i_109_))
	    i_108_ = i_109_;
	while (i_105_ < i_108_) {
	    i_102_ = i_104_ >> 8;
	    i = is[i_102_ - 1];
	    is_103_[i_105_++] += (((i << 8) + (is[i_102_] - i) * (i_104_ & 0xff)) * i_106_ >> 6);
	    i_106_ += i_107_;
	    i_104_ += i_111_;
	}
	if (i_111_ == 0 || ((i_108_ = i_105_ + (i_110_ - i_104_ + i_111_) / i_111_) > i_109_))
	    i_108_ = i_109_;
	i = i_112_;
	i_102_ = i_111_;
	while (i_105_ < i_108_) {
	    is_103_[i_105_++] += (((i << 8) + (is[i_104_ >> 8] - i) * (i_104_ & 0xff)) * i_106_) >> 6;
	    i_106_ += i_107_;
	    i_104_ += i_102_;
	}
	class298_sub19_sub2.anInt9246 += class298_sub19_sub2.anInt9244 * i_105_;
	class298_sub19_sub2.anInt9247 += class298_sub19_sub2.anInt9255 * i_105_;
	class298_sub19_sub2.anInt9241 = i_106_;
	class298_sub19_sub2.anInt9248 = i_104_;
	return i_105_;
    }

    static int method3015(int i, int i_113_, byte[] is, int[] is_114_, int i_115_, int i_116_, int i_117_, int i_118_, int i_119_, int i_120_, int i_121_, int i_122_, int i_123_, Class298_Sub19_Sub2 class298_sub19_sub2, int i_124_, int i_125_) {
	class298_sub19_sub2.anInt9241 -= class298_sub19_sub2.anInt9253 * i_116_;
	if (i_124_ == 0 || ((i_121_ = i_116_ + (i_123_ + 256 - i_115_ + i_124_) / i_124_) > i_122_))
	    i_121_ = i_122_;
	i_116_ <<= 1;
	i_121_ <<= 1;
	while (i_116_ < i_121_) {
	    i_113_ = i_115_ >> 8;
	    i = is[i_113_ - 1];
	    i = (i << 8) + (is[i_113_] - i) * (i_115_ & 0xff);
	    is_114_[i_116_++] += i * i_117_ >> 6;
	    i_117_ += i_119_;
	    is_114_[i_116_++] += i * i_118_ >> 6;
	    i_118_ += i_120_;
	    i_115_ += i_124_;
	}
	if (i_124_ == 0 || ((i_121_ = (i_116_ >> 1) + (i_123_ - i_115_ + i_124_) / i_124_) > i_122_))
	    i_121_ = i_122_;
	i_121_ <<= 1;
	i_113_ = i_125_;
	while (i_116_ < i_121_) {
	    i = (i_113_ << 8) + (is[i_115_ >> 8] - i_113_) * (i_115_ & 0xff);
	    is_114_[i_116_++] += i * i_117_ >> 6;
	    i_117_ += i_119_;
	    is_114_[i_116_++] += i * i_118_ >> 6;
	    i_118_ += i_120_;
	    i_115_ += i_124_;
	}
	i_116_ >>= 1;
	class298_sub19_sub2.anInt9241 += class298_sub19_sub2.anInt9253 * i_116_;
	class298_sub19_sub2.anInt9246 = i_117_;
	class298_sub19_sub2.anInt9247 = i_118_;
	class298_sub19_sub2.anInt9248 = i_115_;
	return i_116_;
    }

    @Override
    Class298_Sub19 method2944() {
	return null;
    }

    @Override
    Class298_Sub19 method2937() {
	return null;
    }

    @Override
    Class298_Sub19 method2938() {
	return null;
    }

    @Override
    Class298_Sub19 method2946() {
	return null;
    }

    @Override
    Class298_Sub19 method2939() {
	return null;
    }

    @Override
    Class298_Sub19 method2940() {
	return null;
    }

    @Override
    Class298_Sub19 method2941() {
	return null;
    }

    public synchronized int method3016() {
	return (this.anInt9242 < 0 ? -this.anInt9242 : this.anInt9242);
    }

    @Override
    int method2943() {
	if (this.anInt9243 == 0 && this.anInt9252 == 0)
	    return 0;
	return 1;
    }

    static int method3017(byte[] is, int[] is_126_, int i, int i_127_, int i_128_, int i_129_, int i_130_, int i_131_, Class298_Sub19_Sub2 class298_sub19_sub2) {
	i >>= 8;
	i_131_ >>= 8;
	i_128_ <<= 2;
	if ((i_129_ = i_127_ + i_131_ - i) > i_130_)
	    i_129_ = i_130_;
	i_129_ -= 3;
	while (i_127_ < i_129_) {
	    is_126_[i_127_++] += is[i++] * i_128_;
	    is_126_[i_127_++] += is[i++] * i_128_;
	    is_126_[i_127_++] += is[i++] * i_128_;
	    is_126_[i_127_++] += is[i++] * i_128_;
	}
	i_129_ += 3;
	while (i_127_ < i_129_)
	    is_126_[i_127_++] += is[i++] * i_128_;
	class298_sub19_sub2.anInt9248 = i << 8;
	return i_127_;
    }

    @Override
    public synchronized void method2947(int i) {
	if (this.anInt9252 > 0) {
	    if (i >= this.anInt9252) {
		if (this.anInt9243 == -2147483648) {
		    this.anInt9243 = 0;
		    this.anInt9247 = 0;
		    this.anInt9246 = 0;
		    this.anInt9241 = 0;
		    method2839(-1460969981);
		    i = this.anInt9252;
		}
		this.anInt9252 = 0;
		method2990();
	    } else {
		this.anInt9241 += this.anInt9253 * i;
		this.anInt9246 += this.anInt9244 * i;
		this.anInt9247 += this.anInt9255 * i;
		this.anInt9252 -= i;
	    }
	}
	Class298_Sub26_Sub1 class298_sub26_sub1 = ((Class298_Sub26_Sub1) this.aClass298_Sub26_7312);
	int i_132_ = this.anInt9249 << 8;
	int i_133_ = this.anInt9250 << 8;
	int i_134_ = class298_sub26_sub1.aByteArray9309.length << 8;
	int i_135_ = i_133_ - i_132_;
	if (i_135_ <= 0)
	    this.anInt9245 = 0;
	if (this.anInt9248 < 0) {
	    if (this.anInt9242 > 0)
		this.anInt9248 = 0;
	    else {
		method2998();
		method2839(-1460969981);
		return;
	    }
	}
	if (this.anInt9248 >= i_134_) {
	    if (this.anInt9242 < 0)
		this.anInt9248 = i_134_ - 1;
	    else {
		method2998();
		method2839(-1460969981);
		return;
	    }
	}
	this.anInt9248 += this.anInt9242 * i;
	if (this.anInt9245 < 0) {
	    if (this.aBoolean9251) {
		if (this.anInt9242 < 0) {
		    if (this.anInt9248 >= i_132_)
			return;
		    this.anInt9248 = (i_132_ + i_132_ - 1 - this.anInt9248);
		    this.anInt9242 = -this.anInt9242;
		}
		while (this.anInt9248 >= i_133_) {
		    this.anInt9248 = (i_133_ + i_133_ - 1 - this.anInt9248);
		    this.anInt9242 = -this.anInt9242;
		    if (this.anInt9248 >= i_132_)
			break;
		    this.anInt9248 = (i_132_ + i_132_ - 1 - this.anInt9248);
		    this.anInt9242 = -this.anInt9242;
		}
	    } else if (this.anInt9242 < 0) {
		if (this.anInt9248 < i_132_)
		    this.anInt9248 = i_133_ - 1 - (i_133_ - 1 - (this.anInt9248)) % i_135_;
	    } else if (this.anInt9248 >= i_133_)
		this.anInt9248 = i_132_ + (this.anInt9248 - i_132_) % i_135_;
	} else {
	    do {
		if (this.anInt9245 > 0) {
		    if (this.aBoolean9251) {
			if (this.anInt9242 < 0) {
			    if (this.anInt9248 >= i_132_)
				return;
			    this.anInt9248 = (i_132_ + i_132_ - 1 - this.anInt9248);
			    this.anInt9242 = -this.anInt9242;
			    if (--this.anInt9245 == 0)
				break;
			}
			do {
			    if (this.anInt9248 < i_133_)
				return;
			    this.anInt9248 = (i_133_ + i_133_ - 1 - this.anInt9248);
			    this.anInt9242 = -this.anInt9242;
			    if (--this.anInt9245 == 0)
				break;
			    if (this.anInt9248 >= i_132_)
				return;
			    this.anInt9248 = (i_132_ + i_132_ - 1 - this.anInt9248);
			    this.anInt9242 = -this.anInt9242;
			}
			while (--this.anInt9245 != 0);
		    } else {
			if (this.anInt9242 < 0) {
			    if (this.anInt9248 < i_132_) {
				int i_136_ = ((i_133_ - 1 - (this.anInt9248)) / i_135_);
				if (i_136_ >= (this.anInt9245)) {
				    this.anInt9248 += i_135_ * this.anInt9245;
				    this.anInt9245 = 0;
				    break;
				}
				this.anInt9248 += i_135_ * i_136_;
				this.anInt9245 -= i_136_;
			    }
			} else if (this.anInt9248 >= i_133_) {
			    int i_137_ = ((this.anInt9248 - i_132_) / i_135_);
			    if (i_137_ >= this.anInt9245) {
				this.anInt9248 -= i_135_ * (this.anInt9245);
				this.anInt9245 = 0;
				break;
			    }
			    this.anInt9248 -= i_135_ * i_137_;
			    this.anInt9245 -= i_137_;
			}
			return;
		    }
		}
	    }
	    while (false);
	    if (this.anInt9242 < 0) {
		if (this.anInt9248 < 0) {
		    this.anInt9248 = -1;
		    method2998();
		    method2839(-1460969981);
		}
	    } else if (this.anInt9248 >= i_134_) {
		this.anInt9248 = i_134_;
		method2998();
		method2839(-1460969981);
	    }
	}
    }

    @Override
    public synchronized void method2949(int i) {
	if (this.anInt9252 > 0) {
	    if (i >= this.anInt9252) {
		if (this.anInt9243 == -2147483648) {
		    this.anInt9243 = 0;
		    this.anInt9247 = 0;
		    this.anInt9246 = 0;
		    this.anInt9241 = 0;
		    method2839(-1460969981);
		    i = this.anInt9252;
		}
		this.anInt9252 = 0;
		method2990();
	    } else {
		this.anInt9241 += this.anInt9253 * i;
		this.anInt9246 += this.anInt9244 * i;
		this.anInt9247 += this.anInt9255 * i;
		this.anInt9252 -= i;
	    }
	}
	Class298_Sub26_Sub1 class298_sub26_sub1 = ((Class298_Sub26_Sub1) this.aClass298_Sub26_7312);
	int i_138_ = this.anInt9249 << 8;
	int i_139_ = this.anInt9250 << 8;
	int i_140_ = class298_sub26_sub1.aByteArray9309.length << 8;
	int i_141_ = i_139_ - i_138_;
	if (i_141_ <= 0)
	    this.anInt9245 = 0;
	if (this.anInt9248 < 0) {
	    if (this.anInt9242 > 0)
		this.anInt9248 = 0;
	    else {
		method2998();
		method2839(-1460969981);
		return;
	    }
	}
	if (this.anInt9248 >= i_140_) {
	    if (this.anInt9242 < 0)
		this.anInt9248 = i_140_ - 1;
	    else {
		method2998();
		method2839(-1460969981);
		return;
	    }
	}
	this.anInt9248 += this.anInt9242 * i;
	if (this.anInt9245 < 0) {
	    if (this.aBoolean9251) {
		if (this.anInt9242 < 0) {
		    if (this.anInt9248 >= i_138_)
			return;
		    this.anInt9248 = (i_138_ + i_138_ - 1 - this.anInt9248);
		    this.anInt9242 = -this.anInt9242;
		}
		while (this.anInt9248 >= i_139_) {
		    this.anInt9248 = (i_139_ + i_139_ - 1 - this.anInt9248);
		    this.anInt9242 = -this.anInt9242;
		    if (this.anInt9248 >= i_138_)
			break;
		    this.anInt9248 = (i_138_ + i_138_ - 1 - this.anInt9248);
		    this.anInt9242 = -this.anInt9242;
		}
	    } else if (this.anInt9242 < 0) {
		if (this.anInt9248 < i_138_)
		    this.anInt9248 = i_139_ - 1 - (i_139_ - 1 - (this.anInt9248)) % i_141_;
	    } else if (this.anInt9248 >= i_139_)
		this.anInt9248 = i_138_ + (this.anInt9248 - i_138_) % i_141_;
	} else {
	    do {
		if (this.anInt9245 > 0) {
		    if (this.aBoolean9251) {
			if (this.anInt9242 < 0) {
			    if (this.anInt9248 >= i_138_)
				return;
			    this.anInt9248 = (i_138_ + i_138_ - 1 - this.anInt9248);
			    this.anInt9242 = -this.anInt9242;
			    if (--this.anInt9245 == 0)
				break;
			}
			do {
			    if (this.anInt9248 < i_139_)
				return;
			    this.anInt9248 = (i_139_ + i_139_ - 1 - this.anInt9248);
			    this.anInt9242 = -this.anInt9242;
			    if (--this.anInt9245 == 0)
				break;
			    if (this.anInt9248 >= i_138_)
				return;
			    this.anInt9248 = (i_138_ + i_138_ - 1 - this.anInt9248);
			    this.anInt9242 = -this.anInt9242;
			}
			while (--this.anInt9245 != 0);
		    } else {
			if (this.anInt9242 < 0) {
			    if (this.anInt9248 < i_138_) {
				int i_142_ = ((i_139_ - 1 - (this.anInt9248)) / i_141_);
				if (i_142_ >= (this.anInt9245)) {
				    this.anInt9248 += i_141_ * this.anInt9245;
				    this.anInt9245 = 0;
				    break;
				}
				this.anInt9248 += i_141_ * i_142_;
				this.anInt9245 -= i_142_;
			    }
			} else if (this.anInt9248 >= i_139_) {
			    int i_143_ = ((this.anInt9248 - i_138_) / i_141_);
			    if (i_143_ >= this.anInt9245) {
				this.anInt9248 -= i_141_ * (this.anInt9245);
				this.anInt9245 = 0;
				break;
			    }
			    this.anInt9248 -= i_141_ * i_143_;
			    this.anInt9245 -= i_143_;
			}
			return;
		    }
		}
	    }
	    while (false);
	    if (this.anInt9242 < 0) {
		if (this.anInt9248 < 0) {
		    this.anInt9248 = -1;
		    method2998();
		    method2839(-1460969981);
		}
	    } else if (this.anInt9248 >= i_140_) {
		this.anInt9248 = i_140_;
		method2998();
		method2839(-1460969981);
	    }
	}
    }

    static int method3018(int i, int i_144_, byte[] is, int[] is_145_, int i_146_, int i_147_, int i_148_, int i_149_, int i_150_, int i_151_, int i_152_, int i_153_, int i_154_, Class298_Sub19_Sub2 class298_sub19_sub2, int i_155_, int i_156_) {
	class298_sub19_sub2.anInt9241 -= class298_sub19_sub2.anInt9253 * i_147_;
	if (i_155_ == 0 || ((i_152_ = i_147_ + (i_154_ - i_146_ + i_155_ - 257) / i_155_) > i_153_))
	    i_152_ = i_153_;
	i_147_ <<= 1;
	i_152_ <<= 1;
	while (i_147_ < i_152_) {
	    i_144_ = i_146_ >> 8;
	    i = is[i_144_];
	    i = (i << 8) + (is[i_144_ + 1] - i) * (i_146_ & 0xff);
	    is_145_[i_147_++] += i * i_148_ >> 6;
	    i_148_ += i_150_;
	    is_145_[i_147_++] += i * i_149_ >> 6;
	    i_149_ += i_151_;
	    i_146_ += i_155_;
	}
	if (i_155_ == 0 || (i_152_ = (i_147_ >> 1) + (i_154_ - i_146_ + i_155_ - 1) / i_155_) > i_153_)
	    i_152_ = i_153_;
	i_152_ <<= 1;
	i_144_ = i_156_;
	while (i_147_ < i_152_) {
	    i = is[i_146_ >> 8];
	    i = (i << 8) + (i_144_ - i) * (i_146_ & 0xff);
	    is_145_[i_147_++] += i * i_148_ >> 6;
	    i_148_ += i_150_;
	    is_145_[i_147_++] += i * i_149_ >> 6;
	    i_149_ += i_151_;
	    i_146_ += i_155_;
	}
	i_147_ >>= 1;
	class298_sub19_sub2.anInt9241 += class298_sub19_sub2.anInt9253 * i_147_;
	class298_sub19_sub2.anInt9246 = i_148_;
	class298_sub19_sub2.anInt9247 = i_149_;
	class298_sub19_sub2.anInt9248 = i_146_;
	return i_147_;
    }

    public synchronized int method3019() {
	return (this.anInt9243 == -2147483648 ? 0 : this.anInt9243);
    }

    int method3020() {
	int i = this.anInt9241 * 3 >> 6;
	i = (i ^ i >> 31) + (i >>> 31);
	if (this.anInt9245 == 0)
	    i -= (i * this.anInt9248 / ((((Class298_Sub26_Sub1) this.aClass298_Sub26_7312).aByteArray9309).length << 8));
	else if (this.anInt9245 >= 0)
	    i -= (i * this.anInt9249 / (((Class298_Sub26_Sub1) this.aClass298_Sub26_7312).aByteArray9309).length);
	return i > 255 ? 255 : i;
    }

    boolean method3021() {
	int i = this.anInt9243;
	int i_157_;
	int i_158_;
	if (i == -2147483648) {
	    i_158_ = 0;
	    i_157_ = 0;
	    i = 0;
	} else {
	    i_157_ = method3001(i, this.anInt9254);
	    i_158_ = method2988(i, this.anInt9254);
	}
	if (this.anInt9241 != i || this.anInt9246 != i_157_ || this.anInt9247 != i_158_) {
	    if (this.anInt9241 < i) {
		this.anInt9253 = 1;
		this.anInt9252 = i - this.anInt9241;
	    } else if (this.anInt9241 > i) {
		this.anInt9253 = -1;
		this.anInt9252 = this.anInt9241 - i;
	    } else
		this.anInt9253 = 0;
	    if (this.anInt9246 < i_157_) {
		this.anInt9244 = 1;
		if (this.anInt9252 == 0 || (this.anInt9252 > i_157_ - this.anInt9246))
		    this.anInt9252 = i_157_ - this.anInt9246;
	    } else if (this.anInt9246 > i_157_) {
		this.anInt9244 = -1;
		if (this.anInt9252 == 0 || (this.anInt9252 > this.anInt9246 - i_157_))
		    this.anInt9252 = this.anInt9246 - i_157_;
	    } else
		this.anInt9244 = 0;
	    if (this.anInt9247 < i_158_) {
		this.anInt9255 = 1;
		if (this.anInt9252 == 0 || (this.anInt9252 > i_158_ - this.anInt9247))
		    this.anInt9252 = i_158_ - this.anInt9247;
	    } else if (this.anInt9247 > i_158_) {
		this.anInt9255 = -1;
		if (this.anInt9252 == 0 || (this.anInt9252 > this.anInt9247 - i_158_))
		    this.anInt9252 = this.anInt9247 - i_158_;
	    } else
		this.anInt9255 = 0;
	    return false;
	}
	if (this.anInt9243 == -2147483648) {
	    this.anInt9243 = 0;
	    this.anInt9247 = 0;
	    this.anInt9246 = 0;
	    this.anInt9241 = 0;
	    method2839(-1460969981);
	    return true;
	}
	method2990();
	return false;
    }

    static int method3022(int i, int i_159_, byte[] is, int[] is_160_, int i_161_, int i_162_, int i_163_, int i_164_, int i_165_, int i_166_, int i_167_, Class298_Sub19_Sub2 class298_sub19_sub2, int i_168_, int i_169_) {
	class298_sub19_sub2.anInt9246 -= class298_sub19_sub2.anInt9244 * i_162_;
	class298_sub19_sub2.anInt9247 -= class298_sub19_sub2.anInt9255 * i_162_;
	if (i_168_ == 0 || ((i_165_ = i_162_ + (i_167_ - i_161_ + i_168_ - 257) / i_168_) > i_166_))
	    i_165_ = i_166_;
	while (i_162_ < i_165_) {
	    i_159_ = i_161_ >> 8;
	    i = is[i_159_];
	    is_160_[i_162_++] += (((i << 8) + (is[i_159_ + 1] - i) * (i_161_ & 0xff)) * i_163_) >> 6;
	    i_163_ += i_164_;
	    i_161_ += i_168_;
	}
	if (i_168_ == 0 || ((i_165_ = i_162_ + (i_167_ - i_161_ + i_168_ - 1) / i_168_) > i_166_))
	    i_165_ = i_166_;
	i_159_ = i_169_;
	while (i_162_ < i_165_) {
	    i = is[i_161_ >> 8];
	    is_160_[i_162_++] += ((i << 8) + (i_159_ - i) * (i_161_ & 0xff)) * i_163_ >> 6;
	    i_163_ += i_164_;
	    i_161_ += i_168_;
	}
	class298_sub19_sub2.anInt9246 += class298_sub19_sub2.anInt9244 * i_162_;
	class298_sub19_sub2.anInt9247 += class298_sub19_sub2.anInt9255 * i_162_;
	class298_sub19_sub2.anInt9241 = i_163_;
	class298_sub19_sub2.anInt9248 = i_161_;
	return i_162_;
    }

    @Override
    int method2942() {
	if (this.anInt9243 == 0 && this.anInt9252 == 0)
	    return 0;
	return 1;
    }

    @Override
    public synchronized void method2932(int[] is, int i, int i_170_) {
	if (this.anInt9243 == 0 && this.anInt9252 == 0)
	    method2935(i_170_);
	else {
	    Class298_Sub26_Sub1 class298_sub26_sub1 = ((Class298_Sub26_Sub1) this.aClass298_Sub26_7312);
	    int i_171_ = this.anInt9249 << 8;
	    int i_172_ = this.anInt9250 << 8;
	    int i_173_ = class298_sub26_sub1.aByteArray9309.length << 8;
	    int i_174_ = i_172_ - i_171_;
	    if (i_174_ <= 0)
		this.anInt9245 = 0;
	    int i_175_ = i;
	    i_170_ += i;
	    if (this.anInt9248 < 0) {
		if (this.anInt9242 > 0)
		    this.anInt9248 = 0;
		else {
		    method2998();
		    method2839(-1460969981);
		    return;
		}
	    }
	    if (this.anInt9248 >= i_173_) {
		if (this.anInt9242 < 0)
		    this.anInt9248 = i_173_ - 1;
		else {
		    method2998();
		    method2839(-1460969981);
		    return;
		}
	    }
	    if (this.anInt9245 < 0) {
		if (this.aBoolean9251) {
		    if (this.anInt9242 < 0) {
			i_175_ = method3025(is, i_175_, i_171_, i_170_, (class298_sub26_sub1.aByteArray9309[(this.anInt9249)]));
			if (this.anInt9248 >= i_171_)
			    return;
			this.anInt9248 = (i_171_ + i_171_ - 1 - this.anInt9248);
			this.anInt9242 = -this.anInt9242;
		    }
		    for (;;) {
			i_175_ = method3004(is, i_175_, i_172_, i_170_, (class298_sub26_sub1.aByteArray9309[(this.anInt9250) - 1]));
			if (this.anInt9248 < i_172_)
			    break;
			this.anInt9248 = (i_172_ + i_172_ - 1 - this.anInt9248);
			this.anInt9242 = -this.anInt9242;
			i_175_ = method3025(is, i_175_, i_171_, i_170_, (class298_sub26_sub1.aByteArray9309[(this.anInt9249)]));
			if (this.anInt9248 >= i_171_)
			    break;
			this.anInt9248 = (i_171_ + i_171_ - 1 - this.anInt9248);
			this.anInt9242 = -this.anInt9242;
		    }
		} else if (this.anInt9242 < 0) {
		    for (;;) {
			i_175_ = method3025(is, i_175_, i_171_, i_170_, (class298_sub26_sub1.aByteArray9309[(this.anInt9250) - 1]));
			if (this.anInt9248 >= i_171_)
			    break;
			this.anInt9248 = i_172_ - 1 - (i_172_ - 1 - (this.anInt9248)) % i_174_;
		    }
		} else {
		    for (;;) {
			i_175_ = method3004(is, i_175_, i_172_, i_170_, (class298_sub26_sub1.aByteArray9309[(this.anInt9249)]));
			if (this.anInt9248 < i_172_)
			    break;
			this.anInt9248 = i_171_ + (this.anInt9248 - i_171_) % i_174_;
		    }
		}
	    } else {
		do {
		    if (this.anInt9245 > 0) {
			if (this.aBoolean9251) {
			    if (this.anInt9242 < 0) {
				i_175_ = method3025(is, i_175_, i_171_, i_170_, (class298_sub26_sub1.aByteArray9309[this.anInt9249]));
				if (this.anInt9248 >= i_171_)
				    return;
				this.anInt9248 = (i_171_ + i_171_ - 1 - (this.anInt9248));
				this.anInt9242 = -this.anInt9242;
				if (--this.anInt9245 == 0)
				    break;
			    }
			    do {
				i_175_ = method3004(is, i_175_, i_172_, i_170_, (class298_sub26_sub1.aByteArray9309[this.anInt9250 - 1]));
				if (this.anInt9248 < i_172_)
				    return;
				this.anInt9248 = (i_172_ + i_172_ - 1 - (this.anInt9248));
				this.anInt9242 = -this.anInt9242;
				if (--this.anInt9245 == 0)
				    break;
				i_175_ = method3025(is, i_175_, i_171_, i_170_, (class298_sub26_sub1.aByteArray9309[this.anInt9249]));
				if (this.anInt9248 >= i_171_)
				    return;
				this.anInt9248 = (i_171_ + i_171_ - 1 - (this.anInt9248));
				this.anInt9242 = -this.anInt9242;
			    }
			    while (--this.anInt9245 != 0);
			} else if (this.anInt9242 < 0) {
			    for (;;) {
				i_175_ = method3025(is, i_175_, i_171_, i_170_, (class298_sub26_sub1.aByteArray9309[this.anInt9250 - 1]));
				if (this.anInt9248 >= i_171_)
				    return;
				int i_176_ = ((i_172_ - 1 - (this.anInt9248)) / i_174_);
				if (i_176_ >= (this.anInt9245)) {
				    this.anInt9248 += i_174_ * this.anInt9245;
				    this.anInt9245 = 0;
				    break;
				}
				this.anInt9248 += i_174_ * i_176_;
				this.anInt9245 -= i_176_;
			    }
			} else {
			    for (;;) {
				i_175_ = method3004(is, i_175_, i_172_, i_170_, (class298_sub26_sub1.aByteArray9309[this.anInt9249]));
				if (this.anInt9248 < i_172_)
				    return;
				int i_177_ = ((this.anInt9248 - i_171_) / i_174_);
				if (i_177_ >= (this.anInt9245)) {
				    this.anInt9248 -= i_174_ * this.anInt9245;
				    this.anInt9245 = 0;
				    break;
				}
				this.anInt9248 -= i_174_ * i_177_;
				this.anInt9245 -= i_177_;
			    }
			}
		    }
		}
		while (false);
		if (this.anInt9242 < 0) {
		    method3025(is, i_175_, 0, i_170_, 0);
		    if (this.anInt9248 < 0) {
			this.anInt9248 = -1;
			method2998();
			method2839(-1460969981);
		    }
		} else {
		    method3004(is, i_175_, i_173_, i_170_, 0);
		    if (this.anInt9248 >= i_173_) {
			this.anInt9248 = i_173_;
			method2998();
			method2839(-1460969981);
		    }
		}
	    }
	}
    }

    public synchronized void method3023(int i, int i_178_, int i_179_) {
	if (i == 0)
	    method2995(i_178_, i_179_);
	else {
	    int i_180_ = method3001(i_178_, i_179_);
	    int i_181_ = method2988(i_178_, i_179_);
	    if (this.anInt9246 == i_180_ && this.anInt9247 == i_181_)
		this.anInt9252 = 0;
	    else {
		int i_182_ = i_178_ - this.anInt9241;
		if (this.anInt9241 - i_178_ > i_182_)
		    i_182_ = this.anInt9241 - i_178_;
		if (i_180_ - this.anInt9246 > i_182_)
		    i_182_ = i_180_ - this.anInt9246;
		if (this.anInt9246 - i_180_ > i_182_)
		    i_182_ = this.anInt9246 - i_180_;
		if (i_181_ - this.anInt9247 > i_182_)
		    i_182_ = i_181_ - this.anInt9247;
		if (this.anInt9247 - i_181_ > i_182_)
		    i_182_ = this.anInt9247 - i_181_;
		if (i > i_182_)
		    i = i_182_;
		this.anInt9252 = i;
		this.anInt9243 = i_178_;
		this.anInt9254 = i_179_;
		this.anInt9253 = (i_178_ - this.anInt9241) / i;
		this.anInt9244 = (i_180_ - this.anInt9246) / i;
		this.anInt9255 = (i_181_ - this.anInt9247) / i;
	    }
	}
    }

    @Override
    public synchronized void method2928(int[] is, int i, int i_183_) {
	if (this.anInt9243 == 0 && this.anInt9252 == 0)
	    method2935(i_183_);
	else {
	    Class298_Sub26_Sub1 class298_sub26_sub1 = ((Class298_Sub26_Sub1) this.aClass298_Sub26_7312);
	    int i_184_ = this.anInt9249 << 8;
	    int i_185_ = this.anInt9250 << 8;
	    int i_186_ = class298_sub26_sub1.aByteArray9309.length << 8;
	    int i_187_ = i_185_ - i_184_;
	    if (i_187_ <= 0)
		this.anInt9245 = 0;
	    int i_188_ = i;
	    i_183_ += i;
	    if (this.anInt9248 < 0) {
		if (this.anInt9242 > 0)
		    this.anInt9248 = 0;
		else {
		    method2998();
		    method2839(-1460969981);
		    return;
		}
	    }
	    if (this.anInt9248 >= i_186_) {
		if (this.anInt9242 < 0)
		    this.anInt9248 = i_186_ - 1;
		else {
		    method2998();
		    method2839(-1460969981);
		    return;
		}
	    }
	    if (this.anInt9245 < 0) {
		if (this.aBoolean9251) {
		    if (this.anInt9242 < 0) {
			i_188_ = method3025(is, i_188_, i_184_, i_183_, (class298_sub26_sub1.aByteArray9309[(this.anInt9249)]));
			if (this.anInt9248 >= i_184_)
			    return;
			this.anInt9248 = (i_184_ + i_184_ - 1 - this.anInt9248);
			this.anInt9242 = -this.anInt9242;
		    }
		    for (;;) {
			i_188_ = method3004(is, i_188_, i_185_, i_183_, (class298_sub26_sub1.aByteArray9309[(this.anInt9250) - 1]));
			if (this.anInt9248 < i_185_)
			    break;
			this.anInt9248 = (i_185_ + i_185_ - 1 - this.anInt9248);
			this.anInt9242 = -this.anInt9242;
			i_188_ = method3025(is, i_188_, i_184_, i_183_, (class298_sub26_sub1.aByteArray9309[(this.anInt9249)]));
			if (this.anInt9248 >= i_184_)
			    break;
			this.anInt9248 = (i_184_ + i_184_ - 1 - this.anInt9248);
			this.anInt9242 = -this.anInt9242;
		    }
		} else if (this.anInt9242 < 0) {
		    for (;;) {
			i_188_ = method3025(is, i_188_, i_184_, i_183_, (class298_sub26_sub1.aByteArray9309[(this.anInt9250) - 1]));
			if (this.anInt9248 >= i_184_)
			    break;
			this.anInt9248 = i_185_ - 1 - (i_185_ - 1 - (this.anInt9248)) % i_187_;
		    }
		} else {
		    for (;;) {
			i_188_ = method3004(is, i_188_, i_185_, i_183_, (class298_sub26_sub1.aByteArray9309[(this.anInt9249)]));
			if (this.anInt9248 < i_185_)
			    break;
			this.anInt9248 = i_184_ + (this.anInt9248 - i_184_) % i_187_;
		    }
		}
	    } else {
		do {
		    if (this.anInt9245 > 0) {
			if (this.aBoolean9251) {
			    if (this.anInt9242 < 0) {
				i_188_ = method3025(is, i_188_, i_184_, i_183_, (class298_sub26_sub1.aByteArray9309[this.anInt9249]));
				if (this.anInt9248 >= i_184_)
				    return;
				this.anInt9248 = (i_184_ + i_184_ - 1 - (this.anInt9248));
				this.anInt9242 = -this.anInt9242;
				if (--this.anInt9245 == 0)
				    break;
			    }
			    do {
				i_188_ = method3004(is, i_188_, i_185_, i_183_, (class298_sub26_sub1.aByteArray9309[this.anInt9250 - 1]));
				if (this.anInt9248 < i_185_)
				    return;
				this.anInt9248 = (i_185_ + i_185_ - 1 - (this.anInt9248));
				this.anInt9242 = -this.anInt9242;
				if (--this.anInt9245 == 0)
				    break;
				i_188_ = method3025(is, i_188_, i_184_, i_183_, (class298_sub26_sub1.aByteArray9309[this.anInt9249]));
				if (this.anInt9248 >= i_184_)
				    return;
				this.anInt9248 = (i_184_ + i_184_ - 1 - (this.anInt9248));
				this.anInt9242 = -this.anInt9242;
			    }
			    while (--this.anInt9245 != 0);
			} else if (this.anInt9242 < 0) {
			    for (;;) {
				i_188_ = method3025(is, i_188_, i_184_, i_183_, (class298_sub26_sub1.aByteArray9309[this.anInt9250 - 1]));
				if (this.anInt9248 >= i_184_)
				    return;
				int i_189_ = ((i_185_ - 1 - (this.anInt9248)) / i_187_);
				if (i_189_ >= (this.anInt9245)) {
				    this.anInt9248 += i_187_ * this.anInt9245;
				    this.anInt9245 = 0;
				    break;
				}
				this.anInt9248 += i_187_ * i_189_;
				this.anInt9245 -= i_189_;
			    }
			} else {
			    for (;;) {
				i_188_ = method3004(is, i_188_, i_185_, i_183_, (class298_sub26_sub1.aByteArray9309[this.anInt9249]));
				if (this.anInt9248 < i_185_)
				    return;
				int i_190_ = ((this.anInt9248 - i_184_) / i_187_);
				if (i_190_ >= (this.anInt9245)) {
				    this.anInt9248 -= i_187_ * this.anInt9245;
				    this.anInt9245 = 0;
				    break;
				}
				this.anInt9248 -= i_187_ * i_190_;
				this.anInt9245 -= i_190_;
			    }
			}
		    }
		}
		while (false);
		if (this.anInt9242 < 0) {
		    method3025(is, i_188_, 0, i_183_, 0);
		    if (this.anInt9248 < 0) {
			this.anInt9248 = -1;
			method2998();
			method2839(-1460969981);
		    }
		} else {
		    method3004(is, i_188_, i_186_, i_183_, 0);
		    if (this.anInt9248 >= i_186_) {
			this.anInt9248 = i_186_;
			method2998();
			method2839(-1460969981);
		    }
		}
	    }
	}
    }

    static int method3024(int i, int i_191_, byte[] is, int[] is_192_, int i_193_, int i_194_, int i_195_, int i_196_, int i_197_, int i_198_, Class298_Sub19_Sub2 class298_sub19_sub2, int i_199_, int i_200_) {
	if (i_199_ == 0 || ((i_196_ = i_194_ + (i_198_ + 256 - i_193_ + i_199_) / i_199_) > i_197_))
	    i_196_ = i_197_;
	while (i_194_ < i_196_) {
	    i_191_ = i_193_ >> 8;
	    i = is[i_191_ - 1];
	    is_192_[i_194_++] += (((i << 8) + (is[i_191_] - i) * (i_193_ & 0xff)) * i_195_ >> 6);
	    i_193_ += i_199_;
	}
	if (i_199_ == 0 || ((i_196_ = i_194_ + (i_198_ - i_193_ + i_199_) / i_199_) > i_197_))
	    i_196_ = i_197_;
	i = i_200_;
	i_191_ = i_199_;
	while (i_194_ < i_196_) {
	    is_192_[i_194_++] += (((i << 8) + (is[i_193_ >> 8] - i) * (i_193_ & 0xff)) * i_195_) >> 6;
	    i_193_ += i_191_;
	}
	class298_sub19_sub2.anInt9248 = i_193_;
	return i_194_;
    }

    @Override
    Class298_Sub19 method2931() {
	return null;
    }

    int method3025(int[] is, int i, int i_201_, int i_202_, int i_203_) {
	while_18_: do {
	    do {
		if (this.anInt9252 <= 0)
		    break while_18_;
		int i_204_ = i + this.anInt9252;
		if (i_204_ > i_202_)
		    i_204_ = i_202_;
		this.anInt9252 += i;
		if (this.anInt9242 == -256 && (this.anInt9248 & 0xff) == 0) {
		    if (Class284.aBoolean3047)
			i = method3029(0, (((Class298_Sub26_Sub1) (this.aClass298_Sub26_7312)).aByteArray9309), is, this.anInt9248, i, this.anInt9246, this.anInt9247, this.anInt9244, this.anInt9255, 0, i_204_, i_201_, this);
		    else
			i = method3013((((Class298_Sub26_Sub1) (this.aClass298_Sub26_7312)).aByteArray9309), is, this.anInt9248, i, this.anInt9241, this.anInt9253, 0, i_204_, i_201_, this);
		} else if (Class284.aBoolean3047)
		    i = method3015(0, 0, ((Class298_Sub26_Sub1) (this.aClass298_Sub26_7312)).aByteArray9309, is, this.anInt9248, i, this.anInt9246, this.anInt9247, this.anInt9244, this.anInt9255, 0, i_204_, i_201_, this, this.anInt9242, i_203_);
		else
		    i = method3014(0, 0, ((Class298_Sub26_Sub1) (this.aClass298_Sub26_7312)).aByteArray9309, is, this.anInt9248, i, this.anInt9241, this.anInt9253, 0, i_204_, i_201_, this, this.anInt9242, i_203_);
		this.anInt9252 -= i;
		if (this.anInt9252 != 0)
		    return i;
	    }
	    while (!method3021());
	    return i_202_;
	}
	while (false);
	if (this.anInt9242 == -256 && (this.anInt9248 & 0xff) == 0) {
	    if (Class284.aBoolean3047)
		return method3008(0, ((Class298_Sub26_Sub1) (this.aClass298_Sub26_7312)).aByteArray9309, is, this.anInt9248, i, this.anInt9246, this.anInt9247, 0, i_202_, i_201_, this);
	    return method3007(((Class298_Sub26_Sub1) (this.aClass298_Sub26_7312)).aByteArray9309, is, this.anInt9248, i, this.anInt9241, 0, i_202_, i_201_, this);
	}
	if (Class284.aBoolean3047)
	    return method3011(0, 0, ((Class298_Sub26_Sub1) (this.aClass298_Sub26_7312)).aByteArray9309, is, this.anInt9248, i, this.anInt9246, this.anInt9247, 0, i_202_, i_201_, this, this.anInt9242, i_203_);
	return method3024(0, 0, (((Class298_Sub26_Sub1) this.aClass298_Sub26_7312).aByteArray9309), is, this.anInt9248, i, this.anInt9241, 0, i_202_, i_201_, this, this.anInt9242, i_203_);
    }

    public synchronized void method3026(boolean bool) {
	this.anInt9242 = ((this.anInt9242 ^ this.anInt9242 >> 31) + (this.anInt9242 >>> 31));
	if (bool)
	    this.anInt9242 = -this.anInt9242;
    }

    public static Class298_Sub19_Sub2 method3027(Class298_Sub26_Sub1 class298_sub26_sub1, int i, int i_205_, int i_206_) {
	if (class298_sub26_sub1.aByteArray9309 == null || class298_sub26_sub1.aByteArray9309.length == 0)
	    return null;
	return new Class298_Sub19_Sub2(class298_sub26_sub1, i, i_205_, i_206_);
    }

    @Override
    public synchronized void method2945(int[] is, int i, int i_207_) {
	if (this.anInt9243 == 0 && this.anInt9252 == 0)
	    method2935(i_207_);
	else {
	    Class298_Sub26_Sub1 class298_sub26_sub1 = ((Class298_Sub26_Sub1) this.aClass298_Sub26_7312);
	    int i_208_ = this.anInt9249 << 8;
	    int i_209_ = this.anInt9250 << 8;
	    int i_210_ = class298_sub26_sub1.aByteArray9309.length << 8;
	    int i_211_ = i_209_ - i_208_;
	    if (i_211_ <= 0)
		this.anInt9245 = 0;
	    int i_212_ = i;
	    i_207_ += i;
	    if (this.anInt9248 < 0) {
		if (this.anInt9242 > 0)
		    this.anInt9248 = 0;
		else {
		    method2998();
		    method2839(-1460969981);
		    return;
		}
	    }
	    if (this.anInt9248 >= i_210_) {
		if (this.anInt9242 < 0)
		    this.anInt9248 = i_210_ - 1;
		else {
		    method2998();
		    method2839(-1460969981);
		    return;
		}
	    }
	    if (this.anInt9245 < 0) {
		if (this.aBoolean9251) {
		    if (this.anInt9242 < 0) {
			i_212_ = method3025(is, i_212_, i_208_, i_207_, (class298_sub26_sub1.aByteArray9309[(this.anInt9249)]));
			if (this.anInt9248 >= i_208_)
			    return;
			this.anInt9248 = (i_208_ + i_208_ - 1 - this.anInt9248);
			this.anInt9242 = -this.anInt9242;
		    }
		    for (;;) {
			i_212_ = method3004(is, i_212_, i_209_, i_207_, (class298_sub26_sub1.aByteArray9309[(this.anInt9250) - 1]));
			if (this.anInt9248 < i_209_)
			    break;
			this.anInt9248 = (i_209_ + i_209_ - 1 - this.anInt9248);
			this.anInt9242 = -this.anInt9242;
			i_212_ = method3025(is, i_212_, i_208_, i_207_, (class298_sub26_sub1.aByteArray9309[(this.anInt9249)]));
			if (this.anInt9248 >= i_208_)
			    break;
			this.anInt9248 = (i_208_ + i_208_ - 1 - this.anInt9248);
			this.anInt9242 = -this.anInt9242;
		    }
		} else if (this.anInt9242 < 0) {
		    for (;;) {
			i_212_ = method3025(is, i_212_, i_208_, i_207_, (class298_sub26_sub1.aByteArray9309[(this.anInt9250) - 1]));
			if (this.anInt9248 >= i_208_)
			    break;
			this.anInt9248 = i_209_ - 1 - (i_209_ - 1 - (this.anInt9248)) % i_211_;
		    }
		} else {
		    for (;;) {
			i_212_ = method3004(is, i_212_, i_209_, i_207_, (class298_sub26_sub1.aByteArray9309[(this.anInt9249)]));
			if (this.anInt9248 < i_209_)
			    break;
			this.anInt9248 = i_208_ + (this.anInt9248 - i_208_) % i_211_;
		    }
		}
	    } else {
		do {
		    if (this.anInt9245 > 0) {
			if (this.aBoolean9251) {
			    if (this.anInt9242 < 0) {
				i_212_ = method3025(is, i_212_, i_208_, i_207_, (class298_sub26_sub1.aByteArray9309[this.anInt9249]));
				if (this.anInt9248 >= i_208_)
				    return;
				this.anInt9248 = (i_208_ + i_208_ - 1 - (this.anInt9248));
				this.anInt9242 = -this.anInt9242;
				if (--this.anInt9245 == 0)
				    break;
			    }
			    do {
				i_212_ = method3004(is, i_212_, i_209_, i_207_, (class298_sub26_sub1.aByteArray9309[this.anInt9250 - 1]));
				if (this.anInt9248 < i_209_)
				    return;
				this.anInt9248 = (i_209_ + i_209_ - 1 - (this.anInt9248));
				this.anInt9242 = -this.anInt9242;
				if (--this.anInt9245 == 0)
				    break;
				i_212_ = method3025(is, i_212_, i_208_, i_207_, (class298_sub26_sub1.aByteArray9309[this.anInt9249]));
				if (this.anInt9248 >= i_208_)
				    return;
				this.anInt9248 = (i_208_ + i_208_ - 1 - (this.anInt9248));
				this.anInt9242 = -this.anInt9242;
			    }
			    while (--this.anInt9245 != 0);
			} else if (this.anInt9242 < 0) {
			    for (;;) {
				i_212_ = method3025(is, i_212_, i_208_, i_207_, (class298_sub26_sub1.aByteArray9309[this.anInt9250 - 1]));
				if (this.anInt9248 >= i_208_)
				    return;
				int i_213_ = ((i_209_ - 1 - (this.anInt9248)) / i_211_);
				if (i_213_ >= (this.anInt9245)) {
				    this.anInt9248 += i_211_ * this.anInt9245;
				    this.anInt9245 = 0;
				    break;
				}
				this.anInt9248 += i_211_ * i_213_;
				this.anInt9245 -= i_213_;
			    }
			} else {
			    for (;;) {
				i_212_ = method3004(is, i_212_, i_209_, i_207_, (class298_sub26_sub1.aByteArray9309[this.anInt9249]));
				if (this.anInt9248 < i_209_)
				    return;
				int i_214_ = ((this.anInt9248 - i_208_) / i_211_);
				if (i_214_ >= (this.anInt9245)) {
				    this.anInt9248 -= i_211_ * this.anInt9245;
				    this.anInt9245 = 0;
				    break;
				}
				this.anInt9248 -= i_211_ * i_214_;
				this.anInt9245 -= i_214_;
			    }
			}
		    }
		}
		while (false);
		if (this.anInt9242 < 0) {
		    method3025(is, i_212_, 0, i_207_, 0);
		    if (this.anInt9248 < 0) {
			this.anInt9248 = -1;
			method2998();
			method2839(-1460969981);
		    }
		} else {
		    method3004(is, i_212_, i_210_, i_207_, 0);
		    if (this.anInt9248 >= i_210_) {
			this.anInt9248 = i_210_;
			method2998();
			method2839(-1460969981);
		    }
		}
	    }
	}
    }

    static int method3028(int i, byte[] is, int[] is_215_, int i_216_, int i_217_, int i_218_, int i_219_, int i_220_, int i_221_, int i_222_, Class298_Sub19_Sub2 class298_sub19_sub2) {
	i_216_ >>= 8;
	i_222_ >>= 8;
	i_218_ <<= 2;
	i_219_ <<= 2;
	if ((i_220_ = i_217_ + i_222_ - i_216_) > i_221_)
	    i_220_ = i_221_;
	i_217_ <<= 1;
	i_220_ <<= 1;
	i_220_ -= 6;
	while (i_217_ < i_220_) {
	    i = is[i_216_++];
	    is_215_[i_217_++] += i * i_218_;
	    is_215_[i_217_++] += i * i_219_;
	    i = is[i_216_++];
	    is_215_[i_217_++] += i * i_218_;
	    is_215_[i_217_++] += i * i_219_;
	    i = is[i_216_++];
	    is_215_[i_217_++] += i * i_218_;
	    is_215_[i_217_++] += i * i_219_;
	    i = is[i_216_++];
	    is_215_[i_217_++] += i * i_218_;
	    is_215_[i_217_++] += i * i_219_;
	}
	i_220_ += 6;
	while (i_217_ < i_220_) {
	    i = is[i_216_++];
	    is_215_[i_217_++] += i * i_218_;
	    is_215_[i_217_++] += i * i_219_;
	}
	class298_sub19_sub2.anInt9248 = i_216_ << 8;
	return i_217_ >> 1;
    }

    static int method3029(int i, byte[] is, int[] is_223_, int i_224_, int i_225_, int i_226_, int i_227_, int i_228_, int i_229_, int i_230_, int i_231_, int i_232_, Class298_Sub19_Sub2 class298_sub19_sub2) {
	i_224_ >>= 8;
	i_232_ >>= 8;
	i_226_ <<= 2;
	i_227_ <<= 2;
	i_228_ <<= 2;
	i_229_ <<= 2;
	if ((i_230_ = i_225_ + i_224_ - (i_232_ - 1)) > i_231_)
	    i_230_ = i_231_;
	class298_sub19_sub2.anInt9241 += (class298_sub19_sub2.anInt9253 * (i_230_ - i_225_));
	i_225_ <<= 1;
	i_230_ <<= 1;
	i_230_ -= 6;
	while (i_225_ < i_230_) {
	    i = is[i_224_--];
	    is_223_[i_225_++] += i * i_226_;
	    i_226_ += i_228_;
	    is_223_[i_225_++] += i * i_227_;
	    i_227_ += i_229_;
	    i = is[i_224_--];
	    is_223_[i_225_++] += i * i_226_;
	    i_226_ += i_228_;
	    is_223_[i_225_++] += i * i_227_;
	    i_227_ += i_229_;
	    i = is[i_224_--];
	    is_223_[i_225_++] += i * i_226_;
	    i_226_ += i_228_;
	    is_223_[i_225_++] += i * i_227_;
	    i_227_ += i_229_;
	    i = is[i_224_--];
	    is_223_[i_225_++] += i * i_226_;
	    i_226_ += i_228_;
	    is_223_[i_225_++] += i * i_227_;
	    i_227_ += i_229_;
	}
	i_230_ += 6;
	while (i_225_ < i_230_) {
	    i = is[i_224_--];
	    is_223_[i_225_++] += i * i_226_;
	    i_226_ += i_228_;
	    is_223_[i_225_++] += i * i_227_;
	    i_227_ += i_229_;
	}
	class298_sub19_sub2.anInt9246 = i_226_ >> 2;
	class298_sub19_sub2.anInt9247 = i_227_ >> 2;
	class298_sub19_sub2.anInt9248 = i_224_ << 8;
	return i_225_ >> 1;
    }
}
