
/* Class298_Sub37_Sub1_Sub2 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.lang.ref.SoftReference;

public class Class298_Sub37_Sub1_Sub2 extends Class298_Sub37_Sub1 {
    SoftReference aSoftReference9999;

    @Override
    boolean method3409() {
	return true;
    }

    Class298_Sub37_Sub1_Sub2(Interface18 interface18, Object object, int i) {
	super(interface18, i);
	this.aSoftReference9999 = new SoftReference(object);
    }

    @Override
    boolean method3407() {
	return true;
    }

    @Override
    Object method3410() {
	return this.aSoftReference9999.get();
    }

    @Override
    Object method3406() {
	return this.aSoftReference9999.get();
    }

    @Override
    Object method3408() {
	return this.aSoftReference9999.get();
    }
}
