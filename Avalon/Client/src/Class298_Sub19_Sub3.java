
/* Class298_Sub19_Sub3 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.awt.Canvas;
import java.lang.reflect.Method;
import java.util.Random;

public class Class298_Sub19_Sub3 extends Class298_Sub19 {
    boolean aBoolean9256;
    int anInt9257;
    static Class253 aClass253_9258 = new Class253(64, Class254.aClass254_2787);
    Class453 aClass453_9259 = new Class453();
    int anInt9260 = 0;
    int anInt9261;
    int anInt9262;
    int anInt9263 = 723600640;
    boolean aBoolean9264;
    static int anInt9265 = 100;

    @Override
    synchronized void method2932(int[] is, int i, int i_0_) {
	if (!this.aBoolean9256) {
	    if (method3033(753213382) == null) {
		if (this.aBoolean9264) {
		    method2839(-1460969981);
		    aClass253_9258.method2416(-406924186);
		}
	    } else {
		int i_1_ = i + i_0_;
		if (Class284.aBoolean3047)
		    i_1_ <<= 1;
		int i_2_ = 0;
		int i_3_ = 0;
		if (1472501875 * this.anInt9262 == 2)
		    i_3_ = 1;
		while (i < i_1_) {
		    Class298_Sub30 class298_sub30 = method3033(753213382);
		    if (null == class298_sub30)
			break;
		    short[][] is_4_;
		    for (is_4_ = (class298_sub30.aShortArrayArray7368); (i < i_1_ && 223474833 * (this.anInt9261) < is_4_[0].length); this.anInt9261 += -491238287) {
			if (Class284.aBoolean3047) {
			    is[i++] = ((is_4_[i_2_][223474833 * (this.anInt9261)]) * (this.anInt9263 * -153466483));
			    is[i++] = (is_4_[i_3_][(this.anInt9261) * 223474833] * (691346779 * (this.anInt9257)));
			} else
			    is[i++] += (((is_4_[i_3_][223474833 * this.anInt9261]) * (691346779 * (this.anInt9257))) + ((is_4_[i_2_][223474833 * this.anInt9261]) * ((this.anInt9263) * -153466483)));
		    }
		    if (223474833 * this.anInt9261 >= is_4_[0].length)
			method3040((byte) 1);
		}
	    }
	}
    }

    public void method3030(int i, byte i_5_) {
	try {
	    this.anInt9263 = i * 489365829;
	    this.anInt9257 = 1113443027 * i;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aii.av(").append(')').toString());
	}
    }

    @Override
    synchronized void method2934(int[] is, int i, int i_6_) {
	try {
	    if (!this.aBoolean9256) {
		if (method3033(753213382) == null) {
		    if (this.aBoolean9264) {
			method2839(-1460969981);
			aClass253_9258.method2416(-600702180);
		    }
		} else {
		    int i_7_ = i + i_6_;
		    if (Class284.aBoolean3047)
			i_7_ <<= 1;
		    int i_8_ = 0;
		    int i_9_ = 0;
		    if (1472501875 * this.anInt9262 == 2)
			i_9_ = 1;
		    while (i < i_7_) {
			Class298_Sub30 class298_sub30 = method3033(753213382);
			if (null == class298_sub30)
			    break;
			short[][] is_10_;
			for (is_10_ = (class298_sub30.aShortArrayArray7368); (i < i_7_ && 223474833 * (this.anInt9261) < is_10_[0].length); this.anInt9261 += -491238287) {
			    if (Class284.aBoolean3047) {
				is[i++] = ((is_10_[i_8_][223474833 * this.anInt9261]) * ((this.anInt9263) * -153466483));
				is[i++] = ((is_10_[i_9_][(this.anInt9261) * 223474833]) * (691346779 * (this.anInt9257)));
			    } else
				is[i++] += (((is_10_[i_9_][223474833 * this.anInt9261]) * (691346779 * (this.anInt9257))) + ((is_10_[i_8_][223474833 * this.anInt9261]) * ((this.anInt9263) * -153466483)));
			}
			if (223474833 * this.anInt9261 >= is_10_[0].length)
			    method3040((byte) 1);
		    }
		}
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aii.k(").append(')').toString());
	}
    }

    @Override
    synchronized void method2935(int i) {
	try {
	    if (!this.aBoolean9256) {
		for (;;) {
		    Class298_Sub30 class298_sub30 = method3033(753213382);
		    if (null == class298_sub30) {
			if (this.aBoolean9264) {
			    method2839(-1460969981);
			    aClass253_9258.method2416(952112097);
			}
			break;
		    }
		    if (((class298_sub30.aShortArrayArray7368[0]).length - this.anInt9261 * 223474833) > i) {
			this.anInt9261 += i * -491238287;
			break;
		    }
		    i -= ((class298_sub30.aShortArrayArray7368[0]).length - (this.anInt9261 * 223474833));
		    method3040((byte) 1);
		}
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aii.d(").append(')').toString());
	}
    }

    synchronized int method3031(int i) {
	try {
	    return this.anInt9260 * 528037965;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aii.at(").append(')').toString());
	}
    }

    Class298_Sub30 method3032(int i, double d) {
	try {
	    long l = (this.anInt9262 * 1472501875 << 32) | i;
	    Class298_Sub30 class298_sub30 = ((Class298_Sub30) aClass253_9258.method2413(Long.valueOf(l), (byte) -35));
	    if (null != class298_sub30) {
		class298_sub30.aDouble7367 = d;
		aClass253_9258.method2417(Long.valueOf(l), 1695283057);
	    } else
		class298_sub30 = new Class298_Sub30((new short[(this.anInt9262) * 1472501875][i]), d);
	    return class298_sub30;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aii.al(").append(')').toString());
	}
    }

    synchronized Class298_Sub30 method3033(int i) {
	try {
	    return (Class298_Sub30) this.aClass453_9259.method5939(1766612795);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aii.ah(").append(')').toString());
	}
    }

    @Override
    Class298_Sub19 method2944() {
	return null;
    }

    synchronized void method3034(Class298_Sub30 class298_sub30, int i) {
	try {
	    for (/**/; this.anInt9260 * 528037965 >= 100; this.anInt9260 -= -1862483835)
		this.aClass453_9259.method5936(2084948407);
	    this.aClass453_9259.add(class298_sub30);
	    this.anInt9260 += -1862483835;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aii.az(").append(')').toString());
	}
    }

    synchronized void method3035(int i) {
	try {
	    this.aBoolean9264 = true;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aii.ap(").append(')').toString());
	}
    }

    synchronized void method3036(boolean bool, int i) {
	try {
	    this.aBoolean9256 = bool;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aii.af(").append(')').toString());
	}
    }

    @Override
    int method2929() {
	try {
	    return 1;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aii.p(").append(')').toString());
	}
    }

    @Override
    Class298_Sub19 method2931() {
	try {
	    return null;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aii.b(").append(')').toString());
	}
    }

    @Override
    Class298_Sub19 method2930() {
	try {
	    return null;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aii.f(").append(')').toString());
	}
    }

    static final void method3037(int i) {
	try {
	    for (int i_11_ = 0; i_11_ < -1230451913 * client.anInt8703; i_11_++) {
		int i_12_ = client.anIntArray8699[i_11_];
		Class298_Sub29 class298_sub29 = ((Class298_Sub29) client.aClass437_8696.method5812(i_12_));
		if (class298_sub29 != null) {
		    NPC class365_sub1_sub1_sub2_sub1 = ((NPC) class298_sub29.anObject7366);
		    Class135.method1494(class365_sub1_sub1_sub2_sub1, false, -2088974966);
		}
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aii.hj(").append(')').toString());
	}
    }

    @Override
    synchronized void method2948(int i) {
	if (!this.aBoolean9256) {
	    for (;;) {
		Class298_Sub30 class298_sub30 = method3033(753213382);
		if (null == class298_sub30) {
		    if (this.aBoolean9264) {
			method2839(-1460969981);
			aClass253_9258.method2416(768907089);
		    }
		    break;
		}
		if (((class298_sub30.aShortArrayArray7368[0]).length - this.anInt9261 * 223474833) > i) {
		    this.anInt9261 += i * -491238287;
		    break;
		}
		i -= ((class298_sub30.aShortArrayArray7368[0]).length - this.anInt9261 * 223474833);
		method3040((byte) 1);
	    }
	}
    }

    @Override
    Class298_Sub19 method2937() {
	return null;
    }

    @Override
    Class298_Sub19 method2938() {
	return null;
    }

    synchronized double method3038(int i) {
	try {
	    if (528037965 * this.anInt9260 < 1)
		return -1.0;
	    Class298_Sub30 class298_sub30 = (Class298_Sub30) this.aClass453_9259.method5939(1766612795);
	    if (null == class298_sub30)
		return -1.0;
	    return (class298_sub30.aDouble7367 - (float) (class298_sub30.aShortArrayArray7368[0]).length / (float) (Class284.anInt3059 * 1164070869));
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aii.as(").append(')').toString());
	}
    }

    @Override
    Class298_Sub19 method2940() {
	return null;
    }

    @Override
    Class298_Sub19 method2941() {
	return null;
    }

    @Override
    Class298_Sub19 method2939() {
	return null;
    }

    public static int method3039(Random random, int i, byte i_13_) {
	try {
	    if (i <= 0)
		throw new IllegalArgumentException();
	    if (AccountCreationStage.method6152(i, 1882495908))
		return (int) (((random.nextInt() & 0xffffffffL) * i) >> 32);
	    int i_14_ = -2147483648 - (int) (4294967296L % i);
	    int i_15_;
	    do
		i_15_ = random.nextInt();
	    while (i_15_ >= i_14_);
	    if (i_13_ == 0)
		throw new IllegalStateException();
	    return Class98.method1057(i_15_, i, 1573613656);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aii.q(").append(')').toString());
	}
    }

    @Override
    int method2943() {
	return 1;
    }

    synchronized void method3040(byte i) {
	try {
	    Class298_Sub30 class298_sub30 = method3033(753213382);
	    if (class298_sub30 != null) {
		class298_sub30.method2839(-1460969981);
		this.anInt9261 = 0;
		this.anInt9260 -= -1862483835;
		aClass253_9258.method2411(Long.valueOf(class298_sub30.method3112(-1380728898)), class298_sub30, (byte) 98);
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aii.ai(").append(')').toString());
	}
    }

    @Override
    synchronized void method2949(int i) {
	if (!this.aBoolean9256) {
	    for (;;) {
		Class298_Sub30 class298_sub30 = method3033(753213382);
		if (null == class298_sub30) {
		    if (this.aBoolean9264) {
			method2839(-1460969981);
			aClass253_9258.method2416(-1289624266);
		    }
		    break;
		}
		if (((class298_sub30.aShortArrayArray7368[0]).length - this.anInt9261 * 223474833) > i) {
		    this.anInt9261 += i * -491238287;
		    break;
		}
		i -= ((class298_sub30.aShortArrayArray7368[0]).length - this.anInt9261 * 223474833);
		method3040((byte) 1);
	    }
	}
    }

    @Override
    synchronized void method2947(int i) {
	if (!this.aBoolean9256) {
	    for (;;) {
		Class298_Sub30 class298_sub30 = method3033(753213382);
		if (null == class298_sub30) {
		    if (this.aBoolean9264) {
			method2839(-1460969981);
			aClass253_9258.method2416(-1874609537);
		    }
		    break;
		}
		if (((class298_sub30.aShortArrayArray7368[0]).length - this.anInt9261 * 223474833) > i) {
		    this.anInt9261 += i * -491238287;
		    break;
		}
		i -= ((class298_sub30.aShortArrayArray7368[0]).length - this.anInt9261 * 223474833);
		method3040((byte) 1);
	    }
	}
    }

    Class298_Sub19_Sub3(int i) {
	this.anInt9257 = 1573573376;
	this.anInt9262 = -114045765 * i;
    }

    @Override
    synchronized void method2945(int[] is, int i, int i_16_) {
	if (!this.aBoolean9256) {
	    if (method3033(753213382) == null) {
		if (this.aBoolean9264) {
		    method2839(-1460969981);
		    aClass253_9258.method2416(-1691027577);
		}
	    } else {
		int i_17_ = i + i_16_;
		if (Class284.aBoolean3047)
		    i_17_ <<= 1;
		int i_18_ = 0;
		int i_19_ = 0;
		if (1472501875 * this.anInt9262 == 2)
		    i_19_ = 1;
		while (i < i_17_) {
		    Class298_Sub30 class298_sub30 = method3033(753213382);
		    if (null == class298_sub30)
			break;
		    short[][] is_20_;
		    for (is_20_ = (class298_sub30.aShortArrayArray7368); (i < i_17_ && 223474833 * (this.anInt9261) < is_20_[0].length); this.anInt9261 += -491238287) {
			if (Class284.aBoolean3047) {
			    is[i++] = ((is_20_[i_18_][223474833 * (this.anInt9261)]) * (this.anInt9263 * -153466483));
			    is[i++] = (is_20_[i_19_][(this.anInt9261) * 223474833] * (691346779 * (this.anInt9257)));
			} else
			    is[i++] += (((is_20_[i_19_][223474833 * this.anInt9261]) * (691346779 * (this.anInt9257))) + ((is_20_[i_18_][223474833 * this.anInt9261]) * ((this.anInt9263) * -153466483)));
		    }
		    if (223474833 * this.anInt9261 >= is_20_[0].length)
			method3040((byte) 1);
		}
	    }
	}
    }

    @Override
    Class298_Sub19 method2946() {
	return null;
    }

    @Override
    int method2936() {
	return 1;
    }

    @Override
    synchronized void method2928(int[] is, int i, int i_21_) {
	if (!this.aBoolean9256) {
	    if (method3033(753213382) == null) {
		if (this.aBoolean9264) {
		    method2839(-1460969981);
		    aClass253_9258.method2416(-701511465);
		}
	    } else {
		int i_22_ = i + i_21_;
		if (Class284.aBoolean3047)
		    i_22_ <<= 1;
		int i_23_ = 0;
		int i_24_ = 0;
		if (1472501875 * this.anInt9262 == 2)
		    i_24_ = 1;
		while (i < i_22_) {
		    Class298_Sub30 class298_sub30 = method3033(753213382);
		    if (null == class298_sub30)
			break;
		    short[][] is_25_;
		    for (is_25_ = (class298_sub30.aShortArrayArray7368); (i < i_22_ && 223474833 * (this.anInt9261) < is_25_[0].length); this.anInt9261 += -491238287) {
			if (Class284.aBoolean3047) {
			    is[i++] = ((is_25_[i_23_][223474833 * (this.anInt9261)]) * (this.anInt9263 * -153466483));
			    is[i++] = (is_25_[i_24_][(this.anInt9261) * 223474833] * (691346779 * (this.anInt9257)));
			} else
			    is[i++] += (((is_25_[i_24_][223474833 * this.anInt9261]) * (691346779 * (this.anInt9257))) + ((is_25_[i_23_][223474833 * this.anInt9261]) * ((this.anInt9263) * -153466483)));
		    }
		    if (223474833 * this.anInt9261 >= is_25_[0].length)
			method3040((byte) 1);
		}
	    }
	}
    }

    public static void method3041(Canvas canvas, short i) {
	try {
	    try {
		Class var_class = java.awt.Canvas.class;
		Method method = var_class.getMethod("setIgnoreRepaint", new Class[] { Boolean.TYPE });
		method.invoke(canvas, new Object[] { Boolean.TRUE });
	    }
	    catch (Exception exception) {
		/* empty */
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aii.ad(").append(')').toString());
	}
    }

    static final void method3042(Class403 class403, int i) {
	try {
	    if ((class403.anIntArray5244[(class403.anInt5239 -= -391880689) * 681479919]) == 0)
		class403.anInt5259 += ((class403.anIntArray5257[class403.anInt5259 * 1883543357]) * 286750741);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aii.bk(").append(')').toString());
	}
    }

    @Override
    int method2942() {
	return 1;
    }
}
