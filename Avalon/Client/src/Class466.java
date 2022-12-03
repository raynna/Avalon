
/* Class466 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.applet.Applet;

public class Class466 {
    // temporary fix
    public static void method6020(Applet applet, String string, int i) throws Throwable {
	/*
	 * try { JSObject.getWindow(applet).eval(string); } catch
	 * (RuntimeException runtimeexception) { throw
	 * Class346.method4175(runtimeexception, new StringBuilder()
	 * .append("tf.a(").append(')').toString()); }
	 */
    }

    public static Object method6021(Applet applet, String string, short i) throws Throwable {
	return null;
	/*
	 * try { return JSObject.getWindow(applet).call(string, null); } catch
	 * (RuntimeException runtimeexception) { throw
	 * Class346.method4175(runtimeexception, new StringBuilder()
	 * .append("tf.f(").append(')').toString()); }
	 */
    }

    public static Object method6022(Applet applet, String string, Object[] objects, byte i) throws Throwable {
	return null;
	/*
	 * try { return JSObject.getWindow(applet).call(string, objects); }
	 * catch (RuntimeException runtimeexception) { throw
	 * Class346.method4175(runtimeexception, new StringBuilder()
	 * .append("tf.b(").append(')').toString()); }
	 */
    }

    Class466() throws Throwable {
	throw new Error();
    }
}
