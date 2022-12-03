
/* Class273 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.awt.Canvas;
import java.awt.Desktop;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URI;

public class Class273 {
    public static Class273 aClass273_2904;
    static Class273 aClass273_2905 = new Class273();
    public static Class273 aClass273_2906 = new Class273();
    static Class273 aClass273_2907;

    static {
	aClass273_2904 = new Class273();
	aClass273_2907 = new Class273();
    }

    Class273() {
	/* empty */
    }

    public static void method2559(String string, boolean bool, boolean bool_0_, String string_1_, boolean bool_2_, boolean bool_3_, int i) {
	try {
	    if (bool) {
		do {
		    if (!bool_2_ && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
			try {
			    Desktop.getDesktop().browse(new URI(string));
			}
			catch (Exception exception) {
			    break;
			}
			return;
		    }
		}
		while (false);
		if (Class82_Sub8.aString6856.startsWith("win") && !bool_2_)
		    Class254.method2427(string, 0, (byte) 23);
		else if (Class82_Sub8.aString6856.startsWith("mac"))
		    Class65.method762(string, 1, string_1_, -1988096952);
		else
		    Class254.method2427(string, 2, (byte) 77);
	    } else
		Class254.method2427(string, 3, (byte) 71);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("li.f(").append(')').toString());
	}
    }

    static final void method2560(Class403 class403, int i) {
	try {
	    class403.anInt5241 -= 1938723502;
	    class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919 - 1)] = Class26.method397((String) (class403.anObjectArray5240[(class403.anInt5241 * -203050393)]), ((String) (class403.anObjectArray5240[-203050393 * (class403.anInt5241) + 1])), Class321.aClass429_3357, -1813623072);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("li.zz(").append(')').toString());
	}
    }

    static final void method2561(Class403 class403, int i) {
	try {
	    boolean bool = false;
	    if (client.aBoolean8638) {
		try {
		    Object object = (Class212.aClass212_2426.method1953((new Object[] { Integer.valueOf(Class298_Sub41.anInt7456 * 1914527151), (Boolean.valueOf(1 == (Class287.myPlayer.aByte10220))), Integer.valueOf(class403.anIntArray5244[((class403.anInt5239 -= -391880689) * 681479919)]) }), -1838433046));
		    if (null != object)
			bool = ((Boolean) object).booleanValue();
		}
		catch (Throwable throwable) {
		    /* empty */
		}
	    }
	    class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919 - 1)] = bool ? 1 : 0;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("li.ant(").append(')').toString());
	}
    }

    static void method2562(int i, int i_4_, int i_5_, boolean bool, int i_6_, boolean bool_7_, byte i_8_) {
	try {
	    if (i < i_4_) {
		int i_9_ = (i + i_4_) / 2;
		int i_10_ = i;
		Class343_Sub1 class343_sub1 = Class474.aClass343_Sub1Array5975[i_9_];
		Class474.aClass343_Sub1Array5975[i_9_] = Class474.aClass343_Sub1Array5975[i_4_];
		Class474.aClass343_Sub1Array5975[i_4_] = class343_sub1;
		for (int i_11_ = i; i_11_ < i_4_; i_11_++) {
		    if (Class310.method3809((Class474.aClass343_Sub1Array5975[i_11_]), class343_sub1, i_5_, bool, i_6_, bool_7_, -279850410) <= 0) {
			Class343_Sub1 class343_sub1_12_ = Class474.aClass343_Sub1Array5975[i_11_];
			Class474.aClass343_Sub1Array5975[i_11_] = Class474.aClass343_Sub1Array5975[i_10_];
			Class474.aClass343_Sub1Array5975[i_10_++] = class343_sub1_12_;
		    }
		}
		Class474.aClass343_Sub1Array5975[i_4_] = Class474.aClass343_Sub1Array5975[i_10_];
		Class474.aClass343_Sub1Array5975[i_10_] = class343_sub1;
		method2562(i, i_10_ - 1, i_5_, bool, i_6_, bool_7_, (byte) -43);
		method2562(1 + i_10_, i_4_, i_5_, bool, i_6_, bool_7_, (byte) 54);
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("li.x(").append(')').toString());
	}
    }

    public static GraphicsToolkit method2563(Canvas canvas, Interface_ma interface_ma, int i, int i_13_, byte i_14_) {
	try {
	    return new ja(canvas, interface_ma, i, i_13_);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("li.a(").append(')').toString());
	}
    }

    static final void method2564(Class403 class403, short i) {
	try {
	    class403.anInt5239 -= -1175642067;
	    int i_15_ = (class403.anIntArray5244[class403.anInt5239 * 681479919]);
	    int i_16_ = (class403.anIntArray5244[class403.anInt5239 * 681479919 + 1]);
	    int i_17_ = (class403.anIntArray5244[681479919 * class403.anInt5239 + 2]);
	    Class301_Sub1.method3713(9, i_15_ << 16 | i_16_, i_17_, "", -529750443);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("li.alx(").append(')').toString());
	}
    }

    public static int method2565(short i) {
	try {
	    return 14;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("li.k(").append(')').toString());
	}
    }

    public static void method2566(RsByteBuffer class298_sub53, int i, byte i_18_) {
	try {
	    Class298_Sub47 class298_sub47 = new Class298_Sub47();
	    class298_sub47.anInt7538 = class298_sub53.readUnsignedByte() * -468176359;
	    class298_sub47.anInt7542 = class298_sub53.readInt((byte) 43) * -530104791;
	    class298_sub47.anIntArray7537 = (new int[class298_sub47.anInt7538 * 719522345]);
	    class298_sub47.anIntArray7536 = (new int[class298_sub47.anInt7538 * 719522345]);
	    class298_sub47.aFieldArray7539 = (new Field[719522345 * class298_sub47.anInt7538]);
	    class298_sub47.anIntArray7535 = (new int[class298_sub47.anInt7538 * 719522345]);
	    class298_sub47.aMethodArray7541 = (new Method[719522345 * class298_sub47.anInt7538]);
	    class298_sub47.aByteArrayArrayArray7540 = (new byte[class298_sub47.anInt7538 * 719522345][][]);
	    for (int i_19_ = 0; (i_19_ < class298_sub47.anInt7538 * 719522345); i_19_++) {
		try {
		    int i_20_ = class298_sub53.readUnsignedByte();
		    if (i_20_ == 0 || i_20_ == 1 || 2 == i_20_) {
			String string = class298_sub53.readString(-1707497835);
			String string_21_ = class298_sub53.readString(-976277803);
			int i_22_ = 0;
			if (1 == i_20_)
			    i_22_ = class298_sub53.readInt((byte) -46);
			class298_sub47.anIntArray7537[i_19_] = i_20_;
			class298_sub47.anIntArray7535[i_19_] = i_22_;
			if (Class136.method1498(string, (byte) 67).getClassLoader() == null)
			    throw new SecurityException();
			class298_sub47.aFieldArray7539[i_19_] = Class136.method1498(string, (byte) 81).getDeclaredField(string_21_);
		    } else if (i_20_ == 3 || 4 == i_20_) {
			String string = class298_sub53.readString(-1377559322);
			String string_23_ = class298_sub53.readString(1945683710);
			int i_24_ = class298_sub53.readUnsignedByte();
			String[] strings = new String[i_24_];
			for (int i_25_ = 0; i_25_ < i_24_; i_25_++)
			    strings[i_25_] = class298_sub53.readString(-1854507369);
			String string_26_ = class298_sub53.readString(-1619475079);
			byte[][] is = new byte[i_24_][];
			if (i_20_ == 3) {
			    for (int i_27_ = 0; i_27_ < i_24_; i_27_++) {
				int i_28_ = class298_sub53.readInt((byte) -44);
				is[i_27_] = new byte[i_28_];
				class298_sub53.readBytes(is[i_27_], 0, i_28_, -953523806);
			    }
			}
			class298_sub47.anIntArray7537[i_19_] = i_20_;
			Class[] var_classes = new Class[i_24_];
			for (int i_29_ = 0; i_29_ < i_24_; i_29_++)
			    var_classes[i_29_] = Class136.method1498(strings[i_29_], (byte) 64);
			Class var_class = Class136.method1498(string_26_, (byte) 5);
			if (Class136.method1498(string, (byte) 23).getClassLoader() == null)
			    throw new SecurityException();
			Method[] methods = Class136.method1498(string, (byte) 42).getDeclaredMethods();
			Method[] methods_30_ = methods;
			for (int i_31_ = 0; i_31_ < methods_30_.length; i_31_++) {
			    Method method = methods_30_[i_31_];
			    if (method.getName().equals(string_23_)) {
				Class[] var_classes_32_ = method.getParameterTypes();
				if (var_classes.length == var_classes_32_.length) {
				    boolean bool = true;
				    for (int i_33_ = 0; i_33_ < var_classes.length; i_33_++) {
					if (var_classes[i_33_] != var_classes_32_[i_33_]) {
					    bool = false;
					    break;
					}
				    }
				    if (bool && var_class == method.getReturnType())
					class298_sub47.aMethodArray7541[i_19_] = method;
				}
			    }
			}
			class298_sub47.aByteArrayArrayArray7540[i_19_] = is;
		    }
		}
		catch (ClassNotFoundException classnotfoundexception) {
		    class298_sub47.anIntArray7536[i_19_] = -1;
		}
		catch (SecurityException securityexception) {
		    class298_sub47.anIntArray7536[i_19_] = -2;
		}
		catch (NullPointerException nullpointerexception) {
		    class298_sub47.anIntArray7536[i_19_] = -3;
		}
		catch (Exception exception) {
		    class298_sub47.anIntArray7536[i_19_] = -4;
		}
		catch (Throwable throwable) {
		    class298_sub47.anIntArray7536[i_19_] = -5;
		}
	    }
	    Class478.aClass453_6002.add(class298_sub47);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("li.p(").append(')').toString());
	}
    }
}
