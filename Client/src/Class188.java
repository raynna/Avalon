
/* Class188 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Class188 implements Iterator {
    int anInt1911;
    int anInt1912 = 0;
    AbstractQueue_Sub1 anAbstractQueue_Sub1_1913;

    @Override
    public void remove() {
	try {
	    throw new UnsupportedOperationException();
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("hw.remove(").append(')').toString());
	}
    }

    @Override
    public boolean hasNext() {
	try {
	    return (this.anInt1912 * -1320296169 < -400292665 * (this.anAbstractQueue_Sub1_1913).anInt6314);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("hw.hasNext(").append(')').toString());
	}
    }

    @Override
    public Object next() {
	try {
	    if (1539110513 * this.anInt1911 != -772584077 * (this.anAbstractQueue_Sub1_1913.anInt6316))
		throw new ConcurrentModificationException();
	    if (this.anInt1912 * -1320296169 < (this.anAbstractQueue_Sub1_1913.anInt6314 * -400292665)) {
		Object object = ((this.anAbstractQueue_Sub1_1913.aClass170Array6313[this.anInt1912 * -1320296169]).anObject1738);
		this.anInt1912 += 1769301671;
		return object;
	    }
	    throw new NoSuchElementException();
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("hw.next(").append(')').toString());
	}
    }

    Class188(AbstractQueue_Sub1 abstractqueue_sub1) {
	this.anInt1911 = (this.anAbstractQueue_Sub1_1913.anInt6316 * 266165283);
	this.anAbstractQueue_Sub1_1913 = abstractqueue_sub1;
    }

    static final void method1850(Class403 class403, int i) {
	try {
	    Class390 class390 = (class403.aBoolean5261 ? class403.aClass390_5247 : class403.aClass390_5246);
	    IComponentDefinition class105 = class390.aClass105_4168;
	    Class119 class119 = class390.aClass119_4167;
	    Class214.method1989(class105, class119, class403, (byte) 74);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("hw.kq(").append(')').toString());
	}
    }

    static final void method1851(Class403 class403, int i) {
	try {
	    class403.anInt5239 -= -783761378;
	    int i_0_ = (class403.anIntArray5244[class403.anInt5239 * 681479919]);
	    int i_1_ = (class403.anIntArray5244[1 + class403.anInt5239 * 681479919]);
	    class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919 - 1)] = Class82_Sub6.method886(i_0_, i_1_, true, 15837685);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("hw.td(").append(')').toString());
	}
    }

    static final void method1852(Class403 class403, int i) {
	try {
	    class403.anInt5239 -= -1175642067;
	    int i_2_ = (class403.anIntArray5244[681479919 * class403.anInt5239]);
	    int i_3_ = (class403.anIntArray5244[1 + 681479919 * class403.anInt5239]);
	    int i_4_ = (class403.anIntArray5244[class403.anInt5239 * 681479919 + 2]);
	    Class301_Sub1.method3713(4, i_2_ << 16 | i_3_, i_4_, "", -1102364535);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("hw.ald(").append(')').toString());
	}
    }
}
