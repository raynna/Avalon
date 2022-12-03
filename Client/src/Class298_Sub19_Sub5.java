/* Class298_Sub19_Sub5 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class298_Sub19_Sub5 extends Class298_Sub19 {
    Class298_Sub19_Sub1 aClass298_Sub19_Sub1_9270;
    Class458 aClass458_9271 = new Class458();
    Class298_Sub19_Sub4 aClass298_Sub19_Sub4_9272 = new Class298_Sub19_Sub4();

    void method3050(Class298_Sub11 class298_sub11, int[] is, int i, int i_0_, int i_1_, int i_2_) {
	try {
	    if ((0 != ((this.aClass298_Sub19_Sub1_9270.anIntArray9224[70539643 * class298_sub11.anInt7254]) & 0x4)) && (class298_sub11.anInt7248 * 1595133323 < 0)) {
		int i_3_ = (((this.aClass298_Sub19_Sub1_9270).anIntArray9228[(class298_sub11.anInt7254 * 70539643)]) / (Class284.anInt3059 * 1164070869));
		for (;;) {
		    int i_4_ = ((1048575 + i_3_ - (-327753205 * class298_sub11.anInt7255)) / i_3_);
		    if (i_4_ > i_0_) {
			if (i_2_ == -1887548736) {
			    /* empty */
			}
			break;
		    }
		    class298_sub11.aClass298_Sub19_Sub2_7252.method2934(is, i, i_4_);
		    i += i_4_;
		    i_0_ -= i_4_;
		    class298_sub11.anInt7255 += (i_3_ * i_4_ - 1048576) * -460252765;
		    int i_5_ = Class284.anInt3059 * 1164070869 / 100;
		    int i_6_ = 262144 / i_3_;
		    if (i_6_ < i_5_)
			i_5_ = i_6_;
		    Class298_Sub19_Sub2 class298_sub19_sub2 = (class298_sub11.aClass298_Sub19_Sub2_7252);
		    if (0 == ((this.aClass298_Sub19_Sub1_9270).anIntArray9226[70539643 * (class298_sub11.anInt7254)]))
			class298_sub11.aClass298_Sub19_Sub2_7252 = (Class298_Sub19_Sub2.method3027((class298_sub11.aClass298_Sub26_Sub1_7236), class298_sub19_sub2.method3016(), class298_sub19_sub2.method3019(), class298_sub19_sub2.method2996()));
		    else {
			class298_sub11.aClass298_Sub19_Sub2_7252 = (Class298_Sub19_Sub2.method3027((class298_sub11.aClass298_Sub26_Sub1_7236), class298_sub19_sub2.method3016(), 0, class298_sub19_sub2.method2996()));
			this.aClass298_Sub19_Sub1_9270.method2966(class298_sub11, ((class298_sub11.aClass298_Sub34_7249).aShortArray7390[(class298_sub11.anInt7239 * 1458878633)]) < 0, (byte) 1);
			class298_sub11.aClass298_Sub19_Sub2_7252.method2999(i_5_, class298_sub19_sub2.method3019());
		    }
		    if (((class298_sub11.aClass298_Sub34_7249).aShortArray7390[(class298_sub11.anInt7239 * 1458878633)]) < 0)
			class298_sub11.aClass298_Sub19_Sub2_7252.method2991(-1);
		    class298_sub19_sub2.method2993(i_5_);
		    class298_sub19_sub2.method2934(is, i, i_1_ - i);
		    if (class298_sub19_sub2.method3003())
			this.aClass298_Sub19_Sub4_9272.method3043(class298_sub19_sub2);
		}
		class298_sub11.anInt7255 += i_0_ * i_3_ * -460252765;
	    }
	    class298_sub11.aClass298_Sub19_Sub2_7252.method2934(is, i, i_0_);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("ait.av(").append(')').toString());
	}
    }

    @Override
    Class298_Sub19 method2930() {
	try {
	    Class298_Sub11 class298_sub11 = (Class298_Sub11) this.aClass458_9271.method5967(1312741945);
	    if (null == class298_sub11)
		return null;
	    if (class298_sub11.aClass298_Sub19_Sub2_7252 != null)
		return (class298_sub11.aClass298_Sub19_Sub2_7252);
	    return method2931();
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("ait.f(").append(')').toString());
	}
    }

    @Override
    Class298_Sub19 method2931() {
	try {
	    Class298_Sub11 class298_sub11;
	    do {
		class298_sub11 = (Class298_Sub11) this.aClass458_9271.method5969((byte) -51);
		if (null == class298_sub11)
		    return null;
	    }
	    while (null == (class298_sub11.aClass298_Sub19_Sub2_7252));
	    return class298_sub11.aClass298_Sub19_Sub2_7252;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("ait.b(").append(')').toString());
	}
    }

    @Override
    int method2929() {
	try {
	    return 0;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("ait.p(").append(')').toString());
	}
    }

    Class298_Sub19_Sub5(Class298_Sub19_Sub1 class298_sub19_sub1) {
	this.aClass298_Sub19_Sub1_9270 = class298_sub19_sub1;
    }

    @Override
    Class298_Sub19 method2938() {
	Class298_Sub11 class298_sub11 = (Class298_Sub11) this.aClass458_9271.method5967(1592221798);
	if (null == class298_sub11)
	    return null;
	if (class298_sub11.aClass298_Sub19_Sub2_7252 != null)
	    return class298_sub11.aClass298_Sub19_Sub2_7252;
	return method2931();
    }

    @Override
    int method2942() {
	return 0;
    }

    void method3051(Class298_Sub11 class298_sub11, int i, int i_7_) {
	try {
	    if ((0 != ((this.aClass298_Sub19_Sub1_9270.anIntArray9224[70539643 * class298_sub11.anInt7254]) & 0x4)) && (1595133323 * class298_sub11.anInt7248 < 0)) {
		int i_8_ = (((this.aClass298_Sub19_Sub1_9270).anIntArray9228[(70539643 * class298_sub11.anInt7254)]) / (1164070869 * Class284.anInt3059));
		int i_9_ = ((i_8_ + 1048575 - (-327753205 * class298_sub11.anInt7255)) / i_8_);
		class298_sub11.anInt7255 = (-460252765 * (i * i_8_ + (class298_sub11.anInt7255) * -327753205 & 0xfffff));
		if (i_9_ <= i) {
		    if (0 == ((this.aClass298_Sub19_Sub1_9270).anIntArray9226[(class298_sub11.anInt7254 * 70539643)]))
			class298_sub11.aClass298_Sub19_Sub2_7252 = (Class298_Sub19_Sub2.method3027((class298_sub11.aClass298_Sub26_Sub1_7236), class298_sub11.aClass298_Sub19_Sub2_7252.method3016(), class298_sub11.aClass298_Sub19_Sub2_7252.method3019(), class298_sub11.aClass298_Sub19_Sub2_7252.method2996()));
		    else {
			class298_sub11.aClass298_Sub19_Sub2_7252 = (Class298_Sub19_Sub2.method3027((class298_sub11.aClass298_Sub26_Sub1_7236), class298_sub11.aClass298_Sub19_Sub2_7252.method3016(), 0, class298_sub11.aClass298_Sub19_Sub2_7252.method2996()));
			this.aClass298_Sub19_Sub1_9270.method2966(class298_sub11, ((class298_sub11.aClass298_Sub34_7249).aShortArray7390[(class298_sub11.anInt7239 * 1458878633)]) < 0, (byte) 1);
		    }
		    if (((class298_sub11.aClass298_Sub34_7249).aShortArray7390[(class298_sub11.anInt7239 * 1458878633)]) < 0)
			class298_sub11.aClass298_Sub19_Sub2_7252.method2991(-1);
		    i = (class298_sub11.anInt7255 * -327753205 / i_8_);
		}
	    }
	    class298_sub11.aClass298_Sub19_Sub2_7252.method2935(i);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("ait.at(").append(')').toString());
	}
    }

    @Override
    Class298_Sub19 method2944() {
	Class298_Sub11 class298_sub11 = (Class298_Sub11) this.aClass458_9271.method5967(1939327453);
	if (null == class298_sub11)
	    return null;
	if (class298_sub11.aClass298_Sub19_Sub2_7252 != null)
	    return class298_sub11.aClass298_Sub19_Sub2_7252;
	return method2931();
    }

    @Override
    Class298_Sub19 method2937() {
	Class298_Sub11 class298_sub11 = (Class298_Sub11) this.aClass458_9271.method5967(1962198238);
	if (null == class298_sub11)
	    return null;
	if (class298_sub11.aClass298_Sub19_Sub2_7252 != null)
	    return class298_sub11.aClass298_Sub19_Sub2_7252;
	return method2931();
    }

    @Override
    void method2934(int[] is, int i, int i_10_) {
	try {
	    this.aClass298_Sub19_Sub4_9272.method2934(is, i, i_10_);
	    while_21_: for (Class298_Sub11 class298_sub11 = (Class298_Sub11) this.aClass458_9271.method5967(1804222944); class298_sub11 != null; class298_sub11 = (Class298_Sub11) this.aClass458_9271.method5969((byte) -61)) {
		if (!this.aClass298_Sub19_Sub1_9270.method2986(class298_sub11, (byte) 49)) {
		    int i_11_ = i;
		    int i_12_ = i_10_;
		    while_19_: do {
			do {
			    if (i_12_ <= -129654463 * class298_sub11.anInt7253)
				break while_19_;
			    method3050(class298_sub11, is, i_11_, -129654463 * class298_sub11.anInt7253, i_11_ + i_12_, 1945030807);
			    i_11_ += -129654463 * class298_sub11.anInt7253;
			    i_12_ -= -129654463 * class298_sub11.anInt7253;
			}
			while (!this.aClass298_Sub19_Sub1_9270.method2980(class298_sub11, is, i_11_, i_12_, 308349107));
			continue while_21_;
		    }
		    while (false);
		    method3050(class298_sub11, is, i_11_, i_12_, i_12_ + i_11_, 2107590773);
		    class298_sub11.anInt7253 -= 718794433 * i_12_;
		}
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("ait.k(").append(')').toString());
	}
    }

    @Override
    int method2943() {
	return 0;
    }

    @Override
    Class298_Sub19 method2939() {
	Class298_Sub11 class298_sub11 = (Class298_Sub11) this.aClass458_9271.method5967(1902536390);
	if (null == class298_sub11)
	    return null;
	if (class298_sub11.aClass298_Sub19_Sub2_7252 != null)
	    return class298_sub11.aClass298_Sub19_Sub2_7252;
	return method2931();
    }

    @Override
    int method2936() {
	return 0;
    }

    @Override
    Class298_Sub19 method2941() {
	Class298_Sub11 class298_sub11;
	do {
	    class298_sub11 = (Class298_Sub11) this.aClass458_9271.method5969((byte) -20);
	    if (null == class298_sub11)
		return null;
	}
	while (null == (class298_sub11.aClass298_Sub19_Sub2_7252));
	return class298_sub11.aClass298_Sub19_Sub2_7252;
    }

    @Override
    void method2948(int i) {
	this.aClass298_Sub19_Sub4_9272.method2935(i);
	while_24_: for (Class298_Sub11 class298_sub11 = (Class298_Sub11) this.aClass458_9271.method5967(1342928995); class298_sub11 != null; class298_sub11 = (Class298_Sub11) this.aClass458_9271.method5969((byte) -97)) {
	    if (!this.aClass298_Sub19_Sub1_9270.method2986(class298_sub11, (byte) 62)) {
		int i_13_ = i;
		while_22_: do {
		    do {
			if (i_13_ <= (-129654463 * class298_sub11.anInt7253))
			    break while_22_;
			method3051(class298_sub11, -129654463 * class298_sub11.anInt7253, -1278171875);
			i_13_ -= (class298_sub11.anInt7253 * -129654463);
		    }
		    while (!this.aClass298_Sub19_Sub1_9270.method2980(class298_sub11, null, 0, i_13_, -657565071));
		    continue while_24_;
		}
		while (false);
		method3051(class298_sub11, i_13_, -1423260065);
		class298_sub11.anInt7253 -= i_13_ * 718794433;
	    }
	}
    }

    @Override
    void method2935(int i) {
	try {
	    this.aClass298_Sub19_Sub4_9272.method2935(i);
	    while_27_: for (Class298_Sub11 class298_sub11 = (Class298_Sub11) this.aClass458_9271.method5967(1563549422); class298_sub11 != null; class298_sub11 = (Class298_Sub11) this.aClass458_9271.method5969((byte) -111)) {
		if (!this.aClass298_Sub19_Sub1_9270.method2986(class298_sub11, (byte) 51)) {
		    int i_14_ = i;
		    while_25_: do {
			do {
			    if (i_14_ <= -129654463 * class298_sub11.anInt7253)
				break while_25_;
			    method3051(class298_sub11, -129654463 * class298_sub11.anInt7253, -1211758254);
			    i_14_ -= (class298_sub11.anInt7253 * -129654463);
			}
			while (!this.aClass298_Sub19_Sub1_9270.method2980(class298_sub11, null, 0, i_14_, -1621498050));
			continue while_27_;
		    }
		    while (false);
		    method3051(class298_sub11, i_14_, -2140903292);
		    class298_sub11.anInt7253 -= i_14_ * 718794433;
		}
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("ait.d(").append(')').toString());
	}
    }

    @Override
    void method2932(int[] is, int i, int i_15_) {
	this.aClass298_Sub19_Sub4_9272.method2934(is, i, i_15_);
	while_30_: for (Class298_Sub11 class298_sub11 = (Class298_Sub11) this.aClass458_9271.method5967(1337915005); class298_sub11 != null; class298_sub11 = (Class298_Sub11) this.aClass458_9271.method5969((byte) -57)) {
	    if (!this.aClass298_Sub19_Sub1_9270.method2986(class298_sub11, (byte) -37)) {
		int i_16_ = i;
		int i_17_ = i_15_;
		while_28_: do {
		    do {
			if (i_17_ <= (-129654463 * class298_sub11.anInt7253))
			    break while_28_;
			method3050(class298_sub11, is, i_16_, -129654463 * class298_sub11.anInt7253, i_16_ + i_17_, 3456667);
			i_16_ += (-129654463 * class298_sub11.anInt7253);
			i_17_ -= (-129654463 * class298_sub11.anInt7253);
		    }
		    while (!this.aClass298_Sub19_Sub1_9270.method2980(class298_sub11, is, i_16_, i_17_, -1959198557));
		    continue while_30_;
		}
		while (false);
		method3050(class298_sub11, is, i_16_, i_17_, i_17_ + i_16_, 955925823);
		class298_sub11.anInt7253 -= 718794433 * i_17_;
	    }
	}
    }

    @Override
    void method2928(int[] is, int i, int i_18_) {
	this.aClass298_Sub19_Sub4_9272.method2934(is, i, i_18_);
	while_33_: for (Class298_Sub11 class298_sub11 = (Class298_Sub11) this.aClass458_9271.method5967(1686060186); class298_sub11 != null; class298_sub11 = (Class298_Sub11) this.aClass458_9271.method5969((byte) -84)) {
	    if (!this.aClass298_Sub19_Sub1_9270.method2986(class298_sub11, (byte) 5)) {
		int i_19_ = i;
		int i_20_ = i_18_;
		while_31_: do {
		    do {
			if (i_20_ <= (-129654463 * class298_sub11.anInt7253))
			    break while_31_;
			method3050(class298_sub11, is, i_19_, -129654463 * class298_sub11.anInt7253, i_19_ + i_20_, 1587884521);
			i_19_ += (-129654463 * class298_sub11.anInt7253);
			i_20_ -= (-129654463 * class298_sub11.anInt7253);
		    }
		    while (!this.aClass298_Sub19_Sub1_9270.method2980(class298_sub11, is, i_19_, i_20_, -60228894));
		    continue while_33_;
		}
		while (false);
		method3050(class298_sub11, is, i_19_, i_20_, i_20_ + i_19_, -535844657);
		class298_sub11.anInt7253 -= 718794433 * i_20_;
	    }
	}
    }

    @Override
    void method2945(int[] is, int i, int i_21_) {
	this.aClass298_Sub19_Sub4_9272.method2934(is, i, i_21_);
	while_36_: for (Class298_Sub11 class298_sub11 = (Class298_Sub11) this.aClass458_9271.method5967(1507919697); class298_sub11 != null; class298_sub11 = (Class298_Sub11) this.aClass458_9271.method5969((byte) -117)) {
	    if (!this.aClass298_Sub19_Sub1_9270.method2986(class298_sub11, (byte) -31)) {
		int i_22_ = i;
		int i_23_ = i_21_;
		while_34_: do {
		    do {
			if (i_23_ <= (-129654463 * class298_sub11.anInt7253))
			    break while_34_;
			method3050(class298_sub11, is, i_22_, -129654463 * class298_sub11.anInt7253, i_22_ + i_23_, -1260559674);
			i_22_ += (-129654463 * class298_sub11.anInt7253);
			i_23_ -= (-129654463 * class298_sub11.anInt7253);
		    }
		    while (!this.aClass298_Sub19_Sub1_9270.method2980(class298_sub11, is, i_22_, i_23_, -1973772367));
		    continue while_36_;
		}
		while (false);
		method3050(class298_sub11, is, i_22_, i_23_, i_23_ + i_22_, 888947175);
		class298_sub11.anInt7253 -= 718794433 * i_23_;
	    }
	}
    }

    @Override
    Class298_Sub19 method2940() {
	Class298_Sub11 class298_sub11;
	do {
	    class298_sub11 = (Class298_Sub11) this.aClass458_9271.method5969((byte) -92);
	    if (null == class298_sub11)
		return null;
	}
	while (null == (class298_sub11.aClass298_Sub19_Sub2_7252));
	return class298_sub11.aClass298_Sub19_Sub2_7252;
    }

    @Override
    void method2949(int i) {
	this.aClass298_Sub19_Sub4_9272.method2935(i);
	while_39_: for (Class298_Sub11 class298_sub11 = (Class298_Sub11) this.aClass458_9271.method5967(1615542283); class298_sub11 != null; class298_sub11 = (Class298_Sub11) this.aClass458_9271.method5969((byte) -58)) {
	    if (!this.aClass298_Sub19_Sub1_9270.method2986(class298_sub11, (byte) 49)) {
		int i_24_ = i;
		while_37_: do {
		    do {
			if (i_24_ <= (-129654463 * class298_sub11.anInt7253))
			    break while_37_;
			method3051(class298_sub11, -129654463 * class298_sub11.anInt7253, -1252817178);
			i_24_ -= (class298_sub11.anInt7253 * -129654463);
		    }
		    while (!this.aClass298_Sub19_Sub1_9270.method2980(class298_sub11, null, 0, i_24_, 388406507));
		    continue while_39_;
		}
		while (false);
		method3051(class298_sub11, i_24_, -1608691527);
		class298_sub11.anInt7253 -= i_24_ * 718794433;
	    }
	}
    }

    @Override
    Class298_Sub19 method2946() {
	Class298_Sub11 class298_sub11 = (Class298_Sub11) this.aClass458_9271.method5967(1374349809);
	if (null == class298_sub11)
	    return null;
	if (class298_sub11.aClass298_Sub19_Sub2_7252 != null)
	    return class298_sub11.aClass298_Sub19_Sub2_7252;
	return method2931();
    }

    @Override
    void method2947(int i) {
	this.aClass298_Sub19_Sub4_9272.method2935(i);
	while_42_: for (Class298_Sub11 class298_sub11 = (Class298_Sub11) this.aClass458_9271.method5967(1835442055); class298_sub11 != null; class298_sub11 = (Class298_Sub11) this.aClass458_9271.method5969((byte) -114)) {
	    if (!this.aClass298_Sub19_Sub1_9270.method2986(class298_sub11, (byte) -80)) {
		int i_25_ = i;
		while_40_: do {
		    do {
			if (i_25_ <= (-129654463 * class298_sub11.anInt7253))
			    break while_40_;
			method3051(class298_sub11, -129654463 * class298_sub11.anInt7253, -1438243413);
			i_25_ -= (class298_sub11.anInt7253 * -129654463);
		    }
		    while (!this.aClass298_Sub19_Sub1_9270.method2980(class298_sub11, null, 0, i_25_, 93946984));
		    continue while_42_;
		}
		while (false);
		method3051(class298_sub11, i_25_, -1605363259);
		class298_sub11.anInt7253 -= i_25_ * 718794433;
	    }
	}
    }
}
