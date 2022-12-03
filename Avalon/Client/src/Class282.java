/* Class282 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class282 implements Interface21 {
    public static Class282 aClass282_6540;
    public static Class282 aClass282_6541;
    public byte aByte6542;
    public static Class282 aClass282_6543 = new Class282((byte) -1);
    public static int anInt6544 = 4;
    public static Class282 aClass282_6545 = new Class282((byte) 0);
    static Class270 aClass270_6546;

    public int method2617(byte i) {
	try {
	    return 1 + aByte6542;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("ls.b(").append(')').toString());
	}
    }

    @Override
    public int getType(int i) {
	try {
	    return aByte6542;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("ls.f(").append(')').toString());
	}
    }

    @Override
    public int method243() {
	return aByte6542;
    }

    Class282(byte i) {
	aByte6542 = i;
    }

    @Override
    public int method244() {
	return aByte6542;
    }

    static {
	aClass282_6540 = new Class282((byte) 1);
	aClass282_6541 = new Class282((byte) 2);
    }

    static void method2618(Class298_Sub37_Sub15 class298_sub37_sub15, int i) {
	try {
	    if (class298_sub37_sub15 != null) {
		Class436.aClass453_5480.add(class298_sub37_sub15);
		Class436.anInt5506 += -970198067;
		Object object = null;
		Class298_Sub37_Sub5 class298_sub37_sub5;
		if (!class298_sub37_sub15.aBoolean9668 && !"".equals(class298_sub37_sub15.aString9657)) {
		    long l = ((class298_sub37_sub15.aLong9666) * 6619564980435866523L);
		    for (class298_sub37_sub5 = ((Class298_Sub37_Sub5) Class436.aClass437_5470.method5812(l)); class298_sub37_sub5 != null; class298_sub37_sub5 = ((Class298_Sub37_Sub5) Class436.aClass437_5470.method5813(-1492262664))) {
			if (class298_sub37_sub5.aString9585.equals(class298_sub37_sub15.aString9657)) {
			    if (i <= 1583252452)
				throw new IllegalStateException();
			    break;
			}
		    }
		    if (class298_sub37_sub5 == null) {
			class298_sub37_sub5 = ((Class298_Sub37_Sub5) Class436.aClass348_5464.method4184(l));
			if (class298_sub37_sub5 != null && !(class298_sub37_sub5.aString9585.equals(class298_sub37_sub15.aString9657)))
			    class298_sub37_sub5 = null;
			if (class298_sub37_sub5 == null)
			    class298_sub37_sub5 = (new Class298_Sub37_Sub5(class298_sub37_sub15.aString9657));
			Class436.aClass437_5470.method5817(class298_sub37_sub5, l);
			Class436.anInt5479 += -1658575779;
		    }
		} else {
		    class298_sub37_sub5 = new Class298_Sub37_Sub5(class298_sub37_sub15.aString9657);
		    Class436.anInt5479 += -1658575779;
		}
		if (class298_sub37_sub5.method3416(class298_sub37_sub15, -1961303764))
		    Class75.method837(class298_sub37_sub5, 70180712);
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("ls.t(").append(')').toString());
	}
    }

    static void method2620(Class403 class403, byte i) {
	try {
	    Class369 class369 = Class316.aClass362_3318.method4307((class403.anIntArray5244[((class403.anInt5239 -= -391880689) * 681479919)]), 245040087);
	    class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919 - 1)] = (class369.anIntArray4023 == null ? 0 : class369.anIntArray4023.length);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("ls.g(").append(')').toString());
	}
    }

    static final void method2621(Class403 class403, int i) {
	try {
	    String string = (ClientScriptsExecutor.aStringArray4126[(class403.anIntArray5257[class403.anInt5259 * 1883543357])]);
	    if (null == string)
		string = "";
	    class403.anObjectArray5240[((class403.anInt5241 += 969361751) * -203050393 - 1)] = string;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("ls.ac(").append(')').toString());
	}
    }

    public static void method2622(int i) {
	try {
	    IComponentDefinition.aClass348_1135.method4189();
	    IComponentDefinition.aClass348_1138.method4189();
	    IComponentDefinition.aClass348_1136.method4189();
	    IComponentDefinition.aClass348_1296.method4189();
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("ls.c(").append(')').toString());
	}
    }

    static final void method2623(int i, int i_4_, int i_5_, int i_6_, int i_7_) {
	try {
	    if (i >= -1424479739 * Class372.anInt4051 && i <= 1135094847 * Class372.anInt4048) {
		i_4_ = Class463.method6012(i_4_, 1155384281 * Class372.anInt4049, (Class372.anInt4050 * -1062447355), -1212608691);
		i_5_ = Class463.method6012(i_5_, 1155384281 * Class372.anInt4049, (-1062447355 * Class372.anInt4050), -1212608691);
		Class492.method6183(i, i_4_, i_5_, i_6_, -1450466434);
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("ls.x(").append(')').toString());
	}
    }

    public static Class413 method2624(int i) {
	try {
	    if (Class357.aClass413_3845 == null)
		return Class413.aClass413_6584;
	    return Class357.aClass413_3845;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("ls.u(").append(')').toString());
	}
    }

    static void method2625(int i) {
	try {
	    Class360.anInt3868 = 471358088;
	    Class360.aClass25_3905 = client.aClass25_8711;
	    Class460.method5981((122690138525332847L * Class360.aLong3874 == -1L), true, "", "", Class360.aLong3874 * 122690138525332847L);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("ls.v(").append(')').toString());
	}
    }

    static final void method2626(Class403 class403, int i) {
	try {
	    int i_8_ = (class403.anIntArray5244[((class403.anInt5239 -= -391880689) * 681479919)]);
	    IComponentDefinition class105 = Class50.getIComponentDefinitions(i_8_, (byte) -41);
	    Class119 class119 = Class389.aClass119Array4165[i_8_ >> 16];
	    Class371.method4582(class105, class119, class403, -1887827595);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("ls.lx(").append(')').toString());
	}
    }
}
