
/* Class453 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class Class453 implements Iterable, Collection {
    public Class298 aClass298_5643 = new Class298();
    Class298 aClass298_5644;

    public void add(Class298 class298) {
//		if (class298 instanceof ScriptEnvironment) {
//			ScriptEnvironment e = (ScriptEnvironment) class298;
//			if (e.arguements != null && (int) e.arguements[0] == 4731) {
//				System.out.println("running script: " + Arrays.toString(e.arguements));
//			}
//		}
	try {
	    if (null != class298.aClass298_3189)
		class298.method2839(-1460969981);
	    class298.aClass298_3189 = aClass298_5643.aClass298_3189;
	    class298.aClass298_3187 = aClass298_5643;
	    class298.aClass298_3189.aClass298_3187 = class298;
	    class298.aClass298_3187.aClass298_3189 = class298;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sr.f(").append(')').toString());
	}
    }

    public Class298 method5936(int i) {
	try {
	    Class298 class298 = aClass298_5643.aClass298_3187;
	    if (class298 == aClass298_5643)
		return null;
	    class298.method2839(-1460969981);
	    return class298;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sr.p(").append(')').toString());
	}
    }

    void method5937(Class453 class453_0_, Class298 class298, byte i) {
	try {
	    Class298 class298_1_ = aClass298_5643.aClass298_3189;
	    aClass298_5643.aClass298_3189 = class298.aClass298_3189;
	    class298.aClass298_3189.aClass298_3187 = aClass298_5643;
	    if (aClass298_5643 != class298) {
		class298.aClass298_3189 = class453_0_.aClass298_5643.aClass298_3189;
		class298.aClass298_3189.aClass298_3187 = class298;
		class298_1_.aClass298_3187 = class453_0_.aClass298_5643;
		class453_0_.aClass298_5643.aClass298_3189 = class298_1_;
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sr.i(").append(')').toString());
	}
    }

    public void method5938(Class453 class453_2_, int i) {
	try {
	    if (aClass298_5643 != aClass298_5643.aClass298_3187)
		method5937(class453_2_, aClass298_5643.aClass298_3187, (byte) -5);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sr.k(").append(')').toString());
	}
    }

    public Class298 method5939(int i) {
	try {
	    return method5945(null, 65280);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sr.d(").append(')').toString());
	}
    }

    Class298 method5940(Class298 class298, byte i) {
	try {
	    Class298 class298_3_;
	    if (null == class298)
		class298_3_ = aClass298_5643.aClass298_3189;
	    else
		class298_3_ = class298;
	    if (aClass298_5643 == class298_3_) {
		this.aClass298_5644 = null;
		return null;
	    }
	    this.aClass298_5644 = class298_3_.aClass298_3189;
	    return class298_3_;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sr.r(").append(')').toString());
	}
    }

    Class298[] method5941(byte i) {
	try {
	    Class298[] class298s = new Class298[method5948(1828905535)];
	    int i_4_ = 0;
	    for (Class298 class298 = aClass298_5643.aClass298_3187; class298 != aClass298_5643; class298 = class298.aClass298_3187)
		class298s[i_4_++] = class298;
	    return class298s;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sr.y(").append(')').toString());
	}
    }

    @Override
    public Iterator iterator() {
	try {
	    return new Class446(this);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sr.iterator(").append(')').toString());
	}
    }

    @Override
    public boolean isEmpty() {
	try {
	    return method5946(1085448128);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sr.isEmpty(").append(')').toString());
	}
    }

    @Override
    public boolean contains(Object object) {
	try {
	    throw new RuntimeException();
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sr.contains(").append(')').toString());
	}
    }

    @Override
    public Object[] toArray() {
	try {
	    return method5941((byte) 7);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sr.toArray(").append(')').toString());
	}
    }

    public Class298 method5942(int i) {
	try {
	    return method5940(null, (byte) 4);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sr.x(").append(')').toString());
	}
    }

    @Override
    public boolean remove(Object object) {
	try {
	    throw new RuntimeException();
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sr.remove(").append(')').toString());
	}
    }

    @Override
    public boolean containsAll(Collection collection) {
	try {
	    throw new RuntimeException();
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sr.containsAll(").append(')').toString());
	}
    }

    @Override
    public boolean addAll(Collection collection) {
	try {
	    throw new RuntimeException();
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sr.addAll(").append(')').toString());
	}
    }

    @Override
    public boolean removeAll(Collection collection) {
	try {
	    throw new RuntimeException();
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sr.removeAll(").append(')').toString());
	}
    }

    @Override
    public boolean retainAll(Collection collection) {
	try {
	    throw new RuntimeException();
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sr.retainAll(").append(')').toString());
	}
    }

    @Override
    public void clear() {
	try {
	    method5943((byte) 1);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sr.clear(").append(')').toString());
	}
    }

    @Override
    public boolean add(Object object) {
	try {
	    return method5949((Class298) object, (byte) -121);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sr.add(").append(')').toString());
	}
    }

    @Override
    public boolean equals(Object object) {
	try {
	    return super.equals(object);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sr.equals(").append(')').toString());
	}
    }

    @Override
    public int hashCode() {
	try {
	    return super.hashCode();
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sr.hashCode(").append(')').toString());
	}
    }

    public void method5943(byte i) {
	try {
	    while (aClass298_5643.aClass298_3187 != aClass298_5643)
		aClass298_5643.aClass298_3187.method2839(-1460969981);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sr.a(").append(')').toString());
	}
    }

    @Override
    public Object[] toArray(Object[] objects) {
	try {
	    int i = 0;
	    for (Class298 class298 = aClass298_5643.aClass298_3187; aClass298_5643 != class298; class298 = class298.aClass298_3187)
		objects[i++] = class298;
	    return objects;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sr.toArray(").append(')').toString());
	}
    }

    public Class298 method5944(int i) {
	try {
	    Class298 class298 = this.aClass298_5644;
	    if (aClass298_5643 == class298) {
		this.aClass298_5644 = null;
		return null;
	    }
	    this.aClass298_5644 = class298.aClass298_3187;
	    return class298;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sr.q(").append(')').toString());
	}
    }

    Class298 method5945(Class298 class298, int i) {
	try {
	    Class298 class298_5_;
	    if (class298 == null)
		class298_5_ = aClass298_5643.aClass298_3187;
	    else
		class298_5_ = class298;
	    if (aClass298_5643 == class298_5_) {
		this.aClass298_5644 = null;
		return null;
	    }
	    this.aClass298_5644 = class298_5_.aClass298_3187;
	    return class298_5_;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sr.u(").append(')').toString());
	}
    }

    public Class453() {
	aClass298_5643.aClass298_3187 = aClass298_5643;
	aClass298_5643.aClass298_3189 = aClass298_5643;
    }

    @Override
    public int size() {
	try {
	    return method5948(1828905535);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sr.size(").append(')').toString());
	}
    }

    public boolean method5946(int i) {
	try {
	    return aClass298_5643 == aClass298_5643.aClass298_3187;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sr.z(").append(')').toString());
	}
    }

    public Class298 method5947(int i) {
	try {
	    Class298 class298 = this.aClass298_5644;
	    if (class298 == aClass298_5643) {
		this.aClass298_5644 = null;
		return null;
	    }
	    this.aClass298_5644 = class298.aClass298_3189;
	    return class298;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sr.n(").append(')').toString());
	}
    }

    public int method5948(int i) {
	try {
	    int i_6_ = 0;
	    for (Class298 class298 = aClass298_5643.aClass298_3187; aClass298_5643 != class298; class298 = class298.aClass298_3187)
		i_6_++;
	    return i_6_;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sr.s(").append(')').toString());
	}
    }

    boolean method5949(Class298 class298, byte i) {
	try {
	    add(class298);
	    return true;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sr.t(").append(')').toString());
	}
    }

    static final void method5950(Class403 class403, byte i) {
	try {
	    if (client.anInt8932 * -1333485389 >= 5 && -1333485389 * client.anInt8932 <= 9)
		class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919) - 1] = 1;
	    else
		class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919) - 1] = 0;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sr.ur(").append(')').toString());
	}
    }

    public static void method5951(int i, int i_7_, int i_8_, ObjectDefinitions class432, int i_9_) {
	try {
	    for (Class298_Sub41 class298_sub41 = ((Class298_Sub41) Class298_Sub41.aClass453_7421.method5939(1766612795)); null != class298_sub41; class298_sub41 = ((Class298_Sub41) Class298_Sub41.aClass453_7421.method5944(49146))) {
		if (i == (-1926928785 * class298_sub41.anInt7424) && (class298_sub41.anInt7425 * -1808325887) == i_7_ << 9 && (class298_sub41.anInt7423 * 757346071 == i_8_ << 9) && (class432.anInt5365 * 1181652947 == (class298_sub41.aClass432_7435.anInt5365) * 1181652947)) {
		    if (null != (class298_sub41.aClass298_Sub19_Sub2_7447)) {
			Class285.aClass298_Sub19_Sub4_3083.method3048(class298_sub41.aClass298_Sub19_Sub2_7447);
			class298_sub41.aClass298_Sub19_Sub2_7447 = null;
		    }
		    if ((class298_sub41.aClass298_Sub19_Sub2_7426) != null) {
			Class285.aClass298_Sub19_Sub4_3083.method3048(class298_sub41.aClass298_Sub19_Sub2_7426);
			class298_sub41.aClass298_Sub19_Sub2_7426 = null;
		    }
		    class298_sub41.method2839(-1460969981);
		    break;
		}
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sr.i(").append(')').toString());
	}
    }

    static final void method5952(Class403 class403, int i) {
	try {
	    Class390 class390 = (class403.aBoolean5261 ? class403.aClass390_5247 : class403.aClass390_5246);
	    IComponentDefinition class105 = class390.aClass105_4168;
	    Class119 class119 = class390.aClass119_4167;
	    Class371.method4582(class105, class119, class403, -638652080);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("sr.lc(").append(')').toString());
	}
    }
}
