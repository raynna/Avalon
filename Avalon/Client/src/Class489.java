/* Class489 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class489 {
    static int anInt6070 = 3;
    public static int anInt6071;

    Class489() throws Throwable {
	throw new Error();
    }

    static boolean method6167(int i) {
	if (Loader.ACCOUNT_CREATION_DISABLED)
	    return false;
	try {
	    return null != Class525.accountCreationStage;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("ud.b(").append(')').toString());
	}
    }

    static final void method6168(Class403 class403, int i) {
	try {
	    Class390 class390 = (class403.aBoolean5261 ? class403.aClass390_5247 : class403.aClass390_5246);
	    IComponentDefinition class105 = class390.aClass105_4168;
	    Class119 class119 = class390.aClass119_4167;
	    Class60.method714(class105, class119, class403, (byte) 0);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("ud.gj(").append(')').toString());
	}
    }
}
