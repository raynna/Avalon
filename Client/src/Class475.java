/* Class475 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class475 {
    Class243 aClass243_5981;
    Class348 aClass348_5982 = new Class348(128);

    public Class475(Class411 class411, Class429 class429, Class243 class243) {
	this.aClass243_5981 = class243;
	if (null != this.aClass243_5981) {
	    int i = this.aClass243_5981.method2296(1572683796) - 1;
	    Class120.aClass120_1413.method1306((short) 19923);
	    this.aClass243_5981.method2316(i, 146505674);
	}
    }

    public ClientScriptMap getClientScriptMap(int id, int i_0_) {
	try {
	    ClientScriptMap class483;
	    synchronized (this.aClass348_5982) {
		class483 = (ClientScriptMap) this.aClass348_5982.method4184(id);
	    }
	    if (null != class483)
		return class483;
	    byte[] is = (this.aClass243_5981.getFile(Class120.aClass120_1413.method1307(id, -1240874866), Class120.aClass120_1413.method1305(id, -2003896976)));
	    class483 = new ClientScriptMap();
	    if (null != is)
		class483.method6123(new RsByteBuffer(is), 592135620);

	    synchronized (this.aClass348_5982) {
		this.aClass348_5982.method4194(class483, id);
	    }
	    return class483;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("to.a(").append(')').toString());
	}
    }

    public static final void method6075(short i) {
	try {
	    int i_1_ = Class422_Sub25.aClass298_Sub48_8425.aClass422_Sub17_7565.method5689(-2013953489);
	    if (i_1_ == 0) {
		client.aClass283_8716.method2643(null, 1496940593);
		Class136_Sub1.method1502(0, (byte) 0);
	    } else if (i_1_ == 1) {
		Class231.method2132((byte) 0, 1452497701);
		Class136_Sub1.method1502(512, (byte) 0);
		if (client.aClass283_8716.method2675(-1611682495) != null)
		    Class379.method4674(-1148794921);
	    } else {
		Class231.method2132((byte) (client.anInt8777 * -1953789277 - 4 & 0xff), 1394791892);
		Class136_Sub1.method1502(2, (byte) 0);
	    }
	    client.anInt8792 = Class99.anInt952 * 832032973;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("to.it(").append(')').toString());
	}
    }

    static final void method6076(Class403 class403, int i) {
	try {
	    class403.anInt5239 -= -1175642067;
	    int i_2_ = (class403.anIntArray5244[681479919 * class403.anInt5239]);
	    int i_3_ = (class403.anIntArray5244[1 + class403.anInt5239 * 681479919]);
	    int i_4_ = (class403.anIntArray5244[681479919 * class403.anInt5239 + 2]);
	    Class301_Sub1.method3713(3, i_2_ << 16 | i_3_, i_4_, "", 226105576);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("to.alf(").append(')').toString());
	}
    }
}
