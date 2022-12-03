
/* Class446 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.util.Iterator;

public class Class446 implements Iterator {
    Class453 aClass453_5611;
    Class298 aClass298_5612;
    Class298 aClass298_5613 = null;
    static int anInt5614;

    public void method5898(Class453 class453, int i) {
	try {
	    this.aClass453_5611 = class453;
	    this.aClass298_5612 = (this.aClass453_5611.aClass298_5643.aClass298_3187);
	    this.aClass298_5613 = null;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sk.d(").append(')').toString());
	}
    }

    void method5899(int i) {
	try {
	    this.aClass298_5612 = (this.aClass453_5611.aClass298_5643.aClass298_3187);
	    this.aClass298_5613 = null;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sk.u(").append(')').toString());
	}
    }

    public Class298 method5900(int i) {
	try {
	    method5899(1114782715);
	    return (Class298) next();
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sk.x(").append(')').toString());
	}
    }

    @Override
    public boolean hasNext() {
	try {
	    return (this.aClass298_5612 != this.aClass453_5611.aClass298_5643);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sk.hasNext(").append(')').toString());
	}
    }

    @Override
    public void remove() {
	try {
	    if (null == this.aClass298_5613)
		throw new IllegalStateException();
	    this.aClass298_5613.method2839(-1460969981);
	    this.aClass298_5613 = null;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sk.remove(").append(')').toString());
	}
    }

    public Class446(Class453 class453) {
	this.aClass453_5611 = class453;
	this.aClass298_5612 = this.aClass453_5611.aClass298_5643.aClass298_3187;
	this.aClass298_5613 = null;
    }

    @Override
    public Object next() {
	try {
	    Class298 class298 = this.aClass298_5612;
	    if (this.aClass453_5611.aClass298_5643 == class298) {
		class298 = null;
		this.aClass298_5612 = null;
	    } else
		this.aClass298_5612 = class298.aClass298_3187;
	    this.aClass298_5613 = class298;
	    return class298;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sk.next(").append(')').toString());
	}
    }

    static final void method5901(Class403 class403, int i) {
	try {
	    int i_0_ = (class403.anIntArray5244[((class403.anInt5239 -= -391880689) * 681479919)]);
	    if (Class452.aBoolean5642) {
		Class456[] class456s = Class271.method2545((byte) 18);
		class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919) - 1] = 28445523 * class456s[i_0_].anInt5663;
		class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919) - 1] = 262154323 * class456s[i_0_].anInt5665;
	    } else {
		class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919) - 1] = 0;
		class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919) - 1] = 0;
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sk.aek(").append(')').toString());
	}
    }

    static final void method5902(Class403 class403, int i) {
	try {
	    class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919 - 1)] = (Class374.anIntArray4078[(class403.anIntArray5257[1883543357 * class403.anInt5259])]);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sk.aq(").append(')').toString());
	}
    }
}
