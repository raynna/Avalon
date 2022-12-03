
/* Class404 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.net.InetAddress;
import java.util.LinkedList;
import java.util.Queue;

import jaclib.ping.Ping;

public class Class404 implements Runnable {
    Thread aThread5263;
    Object anObject5264 = new Object();
    Queue aQueue5265 = new LinkedList();

    public Class396 method4946(String string, byte i) {
	try {
	    if (this.aThread5263 == null)
		throw new IllegalStateException("");
	    if (string == null)
		throw new IllegalArgumentException("");
	    Class396 class396 = new Class396(string);
	    method4947(class396, 1206892874);
	    return class396;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("qu.a(").append(')').toString());
	}
    }

    void method4947(Object object, int i) {
	try {
	    synchronized (this.aQueue5265) {
		this.aQueue5265.add(object);
		this.aQueue5265.notify();
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("qu.b(").append(')').toString());
	}
    }

    public void method4948(int i) {
	try {
	    if (this.aThread5263 != null) {
		method4947(this.anObject5264, 1206892874);
		try {
		    this.aThread5263.join();
		}
		catch (InterruptedException interruptedexception) {
		    /* empty */
		}
		this.aThread5263 = null;
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("qu.f(").append(')').toString());
	}
    }

    public Class404() {
	this.aThread5263 = new Thread(this);
	this.aThread5263.setDaemon(true);
	this.aThread5263.start();
    }

    @Override
    public void run() {
	try {
	    for (;;) {
		Class396 class396;
		synchronized (this.aQueue5265) {
		    Object object;
		    for (object = this.aQueue5265.poll(); null == object; object = this.aQueue5265.poll()) {
			try {
			    this.aQueue5265.wait();
			}
			catch (InterruptedException interruptedexception) {
			    /* empty */
			}
		    }
		    if (this.anObject5264 == object)
			break;
		    class396 = (Class396) object;
		}
		int i;
		try {
		    byte[] is = InetAddress.getByName(class396.aString5194).getAddress();
		    i = Ping.a(is[0], is[1], is[2], is[3], 1000L);
		}
		catch (Throwable throwable) {
		    i = 1000;
		}
		class396.anInt5195 = i * 842879005;
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("qu.run(").append(')').toString());
	}
    }

    static final void method4949(Class403 class403, int i) {
	try {
	    int i_0_ = (class403.anIntArray5257[class403.anInt5259 * 1883543357]);
	    Class374.anIntArray4078[i_0_] = (class403.anIntArray5244[((class403.anInt5239 -= -391880689) * 681479919)]);
	    Class384.method4703(i_0_, (byte) 0);
	    client.aBoolean8736 |= Class254.aBooleanArray2790[i_0_];
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("qu.ag(").append(')').toString());
	}
    }

    static final void method4950(Class403 class403, byte i) {
	try {
	    int i_1_ = (class403.anIntArray5244[((class403.anInt5239 -= -391880689) * 681479919)]);
	    class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919 - 1)] = (Class298_Sub32_Sub14.aClass477_9400.getItemDefinitions(i_1_).anInt5733) * -906758929;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("qu.aai(").append(')').toString());
	}
    }

    static final void method4951(Class403 class403, int i) {
	try {
	    class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919 - 1)] = Class422_Sub25.aClass298_Sub48_8425.aClass422_Sub14_7570.method5677(-1832021198);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("qu.akf(").append(')').toString());
	}
    }

    public static String method4952(long l) {
	try {
	    if (l <= 0L || l >= 6582952005840035281L)
		return null;
	    if (l % 37L == 0L)
		return null;
	    int i = 0;
	    for (long l_2_ = l; l_2_ != 0L; l_2_ /= 37L)
		i++;
	    StringBuilder stringbuilder = new StringBuilder(i);
	    while (0L != l) {
		long l_3_ = l;
		l /= 37L;
		char c = Class420.aCharArray5342[(int) (l_3_ - 37L * l)];
		if (c == '_') {
		    int i_4_ = stringbuilder.length() - 1;
		    stringbuilder.setCharAt(i_4_, Character.toUpperCase(stringbuilder.charAt(i_4_)));
		    c = '\u00a0';
		}
		stringbuilder.append(c);
	    }
	    stringbuilder.reverse();
	    stringbuilder.setCharAt(0, Character.toUpperCase(stringbuilder.charAt(0)));
	    return stringbuilder.toString();
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("qu.b(").append(')').toString());
	}
    }

    static final void method4953(Class403 class403, int i) {
	try {
	    Class390 class390 = (class403.aBoolean5261 ? class403.aClass390_5247 : class403.aClass390_5246);
	    IComponentDefinition class105 = class390.aClass105_4168;
	    class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919 - 1)] = -1232467723 * class105.anInt1280;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("qu.pv(").append(')').toString());
	}
    }

    static final void method4954(Class403 class403, int i) {
	try {
	    Class390 class390 = (class403.aBoolean5261 ? class403.aClass390_5247 : class403.aClass390_5246);
	    IComponentDefinition class105 = class390.aClass105_4168;
	    Class119 class119 = class390.aClass119_4167;
	    Class102.method1085(class105, class119, class403, -1934689497);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("qu.do(").append(')').toString());
	}
    }
}
