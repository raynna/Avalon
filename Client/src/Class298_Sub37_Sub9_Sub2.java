
/* Class298_Sub37_Sub9_Sub2 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.lang.ref.SoftReference;

public class Class298_Sub37_Sub9_Sub2 extends Class298_Sub37_Sub9 {
    public static byte[][][] aByteArrayArrayArray10001;
    SoftReference aSoftReference10002;

    @Override
    boolean method3439() {
	return true;
    }

    @Override
    Object method3437(int i) {
	try {
	    return this.aSoftReference10002.get();
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("akt.a(").append(')').toString());
	}
    }

    @Override
    boolean method3438(int i) {
	try {
	    return true;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("akt.f(").append(')').toString());
	}
    }

    @Override
    Object method3442() {
	return this.aSoftReference10002.get();
    }

    @Override
    Object method3440() {
	return this.aSoftReference10002.get();
    }

    @Override
    boolean method3441() {
	return true;
    }

    @Override
    boolean method3436() {
	return true;
    }

    Class298_Sub37_Sub9_Sub2(Object object, int i) {
	super(i);
	this.aSoftReference10002 = new SoftReference(object);
    }
}
