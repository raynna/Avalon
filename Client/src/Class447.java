
/* Class447 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.util.Iterator;

public class Class447 implements Iterator {
    Class461 aClass461_5615;
    Class298_Sub37 aClass298_Sub37_5616;
    Class298_Sub37 aClass298_Sub37_5617 = null;
    public static Class469 aClass469_5618;

    void method5903(int i) {
	try {
	    this.aClass298_Sub37_5616 = (this.aClass461_5615.aClass298_Sub37_5679.aClass298_Sub37_7405);
	    this.aClass298_Sub37_5617 = null;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sl.d(").append(')').toString());
	}
    }

    public Class298_Sub37 method5904(short i) {
	try {
	    method5903(2013094835);
	    return (Class298_Sub37) next();
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sl.u(").append(')').toString());
	}
    }

    @Override
    public Object next() {
	try {
	    Class298_Sub37 class298_sub37 = this.aClass298_Sub37_5616;
	    if (this.aClass461_5615.aClass298_Sub37_5679 == class298_sub37) {
		class298_sub37 = null;
		this.aClass298_Sub37_5616 = null;
	    } else
		this.aClass298_Sub37_5616 = class298_sub37.aClass298_Sub37_7405;
	    this.aClass298_Sub37_5617 = class298_sub37;
	    return class298_sub37;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sl.next(").append(')').toString());
	}
    }

    public Class447(Class461 class461) {
	this.aClass461_5615 = class461;
	this.aClass298_Sub37_5616 = (this.aClass461_5615.aClass298_Sub37_5679.aClass298_Sub37_7405);
	this.aClass298_Sub37_5617 = null;
    }

    @Override
    public void remove() {
	try {
	    if (this.aClass298_Sub37_5617 == null)
		throw new IllegalStateException();
	    this.aClass298_Sub37_5617.method3402(-404609448);
	    this.aClass298_Sub37_5617 = null;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sl.remove(").append(')').toString());
	}
    }

    @Override
    public boolean hasNext() {
	try {
	    return (this.aClass461_5615.aClass298_Sub37_5679 != this.aClass298_Sub37_5616);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sl.hasNext(").append(')').toString());
	}
    }

    public static void method5905(int i) {
	try {
	    for (Class298_Sub37_Sub5 class298_sub37_sub5 = ((Class298_Sub37_Sub5) Class436.aClass461_5482.method5984(1694693115)); null != class298_sub37_sub5; class298_sub37_sub5 = ((Class298_Sub37_Sub5) Class436.aClass461_5482.method5985(283371396))) {
		if ((-628325139 * class298_sub37_sub5.anInt9583) > 1) {
		    class298_sub37_sub5.anInt9583 = 0;
		    Class436.aClass348_5464.method4194(class298_sub37_sub5, (((Class298_Sub37_Sub15) (class298_sub37_sub5.aClass461_9584.aClass298_Sub37_5679.aClass298_Sub37_7405)).aLong9666 * 6619564980435866523L));
		    class298_sub37_sub5.aClass461_9584.method5988(2069073381);
		}
	    }
	    Class436.anInt5479 = 0;
	    Class436.anInt5506 = 0;
	    Class436.aClass453_5480.method5943((byte) 1);
	    Class436.aClass437_5470.method5811((byte) -2);
	    Class436.aClass461_5482.method5988(-330141284);
	    Class436.aBoolean5478 = false;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sl.aa(").append(')').toString());
	}
    }

    static final void method5906(Class403 class403, int i) {
	try {
	    String string = (String) (class403.anObjectArray5240[(class403.anInt5241 -= 969361751) * -203050393]);
	    class403.anInt5239 -= -783761378;
	    int i_0_ = (class403.anIntArray5244[681479919 * class403.anInt5239]);
	    int i_1_ = (class403.anIntArray5244[681479919 * class403.anInt5239 + 1]);
	    class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919 - 1)] = string.indexOf(i_0_, i_1_);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sl.aau(").append(')').toString());
	}
    }
}
