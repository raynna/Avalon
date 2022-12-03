
/* Class93 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.awt.Frame;

import jaclib.ping.IcmpService;

final class Class93 implements Interface23 {
    @Override
    public int method254(int i) {
	Class298_Sub35 class298_sub35 = (Class298_Sub35) Class87.aClass437_793.method5812(i);
	if (class298_sub35 == null)
	    return Class128.aClass148_6331.method252(i, (byte) 84);
	return class298_sub35.anInt7394 * -774922497;
    }

    @Override
    public int method255(int i) {
	Class298_Sub35 class298_sub35 = (Class298_Sub35) Class87.aClass437_793.method5812(i);
	if (class298_sub35 == null)
	    return Class128.aClass148_6331.method252(i, (byte) 1);
	return class298_sub35.anInt7394 * -774922497;
    }

    @Override
    public int method252(int i, byte i_0_) {
	try {
	    Class298_Sub35 class298_sub35 = (Class298_Sub35) Class87.aClass437_793.method5812(i);
	    if (class298_sub35 == null)
		return Class128.aClass148_6331.method252(i, (byte) 21);
	    return class298_sub35.anInt7394 * -774922497;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("du.d(").append(')').toString());
	}
    }

    @Override
    public int method251(int i) {
	Class298_Sub35 class298_sub35 = (Class298_Sub35) Class87.aClass437_793.method5812(i);
	if (class298_sub35 == null)
	    return Class128.aClass148_6331.method252(i, (byte) 101);
	return class298_sub35.anInt7394 * -774922497;
    }

    @Override
    public int method253(int i) {
	Class298_Sub35 class298_sub35 = (Class298_Sub35) Class87.aClass437_793.method5812(i);
	if (class298_sub35 == null)
	    return Class128.aClass148_6331.method252(i, (byte) 98);
	return class298_sub35.anInt7394 * -774922497;
    }

    @Override
    public int method250(int i, byte i_1_) {
	try {
	    Class298_Sub35 class298_sub35 = ((Class298_Sub35) Class87.aClass437_793.method5812(0x100000000L | i));
	    if (class298_sub35 == null)
		return Class128.aClass148_6331.method250(i, (byte) 36);
	    return -774922497 * class298_sub35.anInt7394;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("du.u(").append(')').toString());
	}
    }

    @Override
    public int method249(int i) {
	Class298_Sub35 class298_sub35 = ((Class298_Sub35) Class87.aClass437_793.method5812(0x100000000L | i));
	if (class298_sub35 == null)
	    return Class128.aClass148_6331.method250(i, (byte) 43);
	return -774922497 * class298_sub35.anInt7394;
    }

    static final void method1009(Class403 class403, int i) {
	try {
	    if (null != client.aString8804)
		class403.anObjectArray5240[((class403.anInt5241 += 969361751) * -203050393) - 1] = Class227.method2114(client.aString8804, 864183945);
	    else
		class403.anObjectArray5240[((class403.anInt5241 += 969361751) * -203050393) - 1] = "";
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("du.wy(").append(')').toString());
	}
    }

    public static void method1010(Class457 class457, Frame frame, int i) {
	try {
	    class457.method5960();
	    frame.setVisible(false);
	    frame.dispose();
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("du.b(").append(')').toString());
	}
    }

    public static void method1011(byte i) {
	try {
	    Class478.aClass453_6002 = new Class453();
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("du.a(").append(')').toString());
	}
    }

    public static void method1012(Class298_Sub50 class298_sub50, int i) {
	try {
	    if (!Class435.method5804(client.anInt8752 * -1233866115, (byte) -26))
		class298_sub50.method3550(1342402184);
	    else
		ClientScriptsExecutor.aClass374_Sub1_4125.method4620(class298_sub50, 2143317873);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("du.p(").append(')').toString());
	}
    }

    public static boolean method1014(int i) {
	try {
	    do {
		boolean bool;
		try {
		    if (IcmpService.available())
			break;
		    bool = false;
		}
		catch (Throwable throwable) {
		    return false;
		}
		return bool;
	    }
	    while (false);
	    if (null != IcmpService_Sub1.anIcmpService_Sub1_8551)
		throw new IllegalStateException("");
	    IcmpService_Sub1.anIcmpService_Sub1_8551 = new IcmpService_Sub1();
	    Thread thread = new Thread(new Class393());
	    thread.setDaemon(true);
	    thread.start();
	    return true;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("du.k(").append(')').toString());
	}
    }
}
