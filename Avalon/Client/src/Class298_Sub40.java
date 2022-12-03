/* Class298_Sub40 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public abstract class Class298_Sub40 extends Class298 {
    abstract void method3508(RsByteBuffer class298_sub53, int i);

    abstract void method3509(RsByteBuffer class298_sub53);

    abstract void method3510(ClanSettings class162, byte i);

    abstract void method3511(ClanSettings class162);

    abstract void method3512(RsByteBuffer class298_sub53);

    Class298_Sub40() {
	/* empty */
    }

    static final void method3513(Class403 class403, byte i) {
	try {
	    class403.anInt5239 -= -1175642067;
	    int i_0_ = (class403.anIntArray5244[class403.anInt5239 * 681479919]);
	    int i_1_ = (class403.anIntArray5244[681479919 * class403.anInt5239 + 1]);
	    int i_2_ = (class403.anIntArray5244[2 + class403.anInt5239 * 681479919]);
	    IComponentDefinition class105 = Class50.getIComponentDefinitions(i_2_, (byte) 7);
	    Class523.method6330(class105, i_0_, i_1_, 1882686738);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aby.sq(").append(')').toString());
	}
    }
}
