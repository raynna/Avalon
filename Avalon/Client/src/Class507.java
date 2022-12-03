/* Class507 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class507 {
    Class348 aClass348_6204;
    Class411 aClass411_6205;
    Class348 aClass348_6206 = new Class348(64);
    Class243 aClass243_6207;
    Class243 aClass243_6208;
    boolean aBoolean6209;
    int anInt6210;
    Class348 aClass348_6211;
    Class429 aClass429_6212;
    String[] aStringArray6213;

    public NPCDefinitions method6269(int i, int i_0_) {
	try {
	    NPCDefinitions class503;
	    synchronized (this.aClass348_6206) {
		class503 = (NPCDefinitions) this.aClass348_6206.method4184(i);
	    }
	    if (null != class503)
		return class503;
	    byte[] is;
	    synchronized (this.aClass243_6207) {
		is = (this.aClass243_6207.getFile(Class120.aClass120_1410.method1307(i, -790155853), Class120.aClass120_1410.method1305(i, -576275965)));
	    }
	    class503 = new NPCDefinitions();
	    class503.anInt6126 = -1840892671 * i;
	    class503.aClass507_6125 = this;
	    class503.aStringArray6142 = this.aStringArray6213.clone();
	    if (is != null)
		class503.method6235(new RsByteBuffer(is), -975728899);
	    class503.method6243(1323410423);
	    synchronized (this.aClass348_6206) {
		this.aClass348_6206.method4194(class503, i);
	    }
	    return class503;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("uy.a(").append(')').toString());
	}
    }

    public void method6270(boolean bool, byte i) {
	try {
	    if (this.aBoolean6209 != bool) {
		this.aBoolean6209 = bool;
		method6272(-1071306617);
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("uy.f(").append(')').toString());
	}
    }

    public void method6271(int i, int i_1_) {
	try {
	    this.anInt6210 = i * -1576689997;
	    synchronized (this.aClass348_6204) {
		this.aClass348_6204.method4187();
	    }
	    synchronized (this.aClass348_6211) {
		this.aClass348_6211.method4187();
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("uy.b(").append(')').toString());
	}
    }

    public void method6272(int i) {
	try {
	    synchronized (this.aClass348_6206) {
		this.aClass348_6206.method4187();
	    }
	    synchronized (this.aClass348_6204) {
		this.aClass348_6204.method4187();
	    }
	    synchronized (this.aClass348_6211) {
		this.aClass348_6211.method4187();
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("uy.p(").append(')').toString());
	}
    }

    public void method6273(byte i) {
	try {
	    synchronized (this.aClass348_6204) {
		this.aClass348_6204.method4187();
	    }
	    synchronized (this.aClass348_6211) {
		this.aClass348_6211.method4187();
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("uy.i(").append(')').toString());
	}
    }

    public void method6274(int i) {
	try {
	    synchronized (this.aClass348_6206) {
		this.aClass348_6206.method4189();
	    }
	    synchronized (this.aClass348_6204) {
		this.aClass348_6204.method4189();
	    }
	    synchronized (this.aClass348_6211) {
		this.aClass348_6211.method4189();
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("uy.d(").append(')').toString());
	}
    }

    public void method6275(int i, int i_2_) {
	try {
	    synchronized (this.aClass348_6206) {
		this.aClass348_6206.method4186(i, 612752798);
	    }
	    synchronized (this.aClass348_6204) {
		this.aClass348_6204.method4186(i, -709168280);
	    }
	    synchronized (this.aClass348_6211) {
		this.aClass348_6211.method4186(i, -1808092959);
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("uy.k(").append(')').toString());
	}
    }

    public Class507(Class411 class411, Class429 class429, boolean bool, Class243 class243, Class243 class243_3_) {
	this.aClass348_6204 = new Class348(50);
	this.aClass348_6211 = new Class348(5);
	this.aClass411_6205 = class411;
	this.aClass429_6212 = class429;
	this.aBoolean6209 = bool;
	this.aClass243_6207 = class243;
	this.aClass243_6208 = class243_3_;
	if (this.aClass243_6207 != null) {
	    int i = this.aClass243_6207.method2296(2037724352) - 1;
	    Class120.aClass120_1410.method1306((short) 12429);
	    this.aClass243_6207.method2316(i, 678420951);
	}
	if (Class411.game_rs == this.aClass411_6205)
	    this.aStringArray6213 = (new String[] { null, null, null, null, null, Tradution.aClass470_5905.method6049((this.aClass429_6212), -875414210) });
	else
	    this.aStringArray6213 = new String[] { null, null, null, null, null, null };
    }

    static void method6276(int i) {
	try {
	    Class229.aClass348_6452.method4187();
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("uy.a(").append(')').toString());
	}
    }

    static final void method6277(Class403 class403, int i) {
	try {
	    int i_4_ = (class403.anIntArray5244[((class403.anInt5239 -= -391880689) * 681479919)]);
	    IComponentDefinition class105 = Class50.getIComponentDefinitions(i_4_, (byte) -51);
	    Class119 class119 = Class389.aClass119Array4165[i_4_ >> 16];
	    Class79.method849(class105, class119, class403, 1226850164);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("uy.jp(").append(')').toString());
	}
    }
}
