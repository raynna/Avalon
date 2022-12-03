/* Class424 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class GameObjectType implements Interface21 {
    static GameObjectType T16;
    public static GameObjectType T1;
    public static GameObjectType T2;
    public static GameObjectType T3;
    public static GameObjectType T9;
    public static GameObjectType T4;
    static GameObjectType T17;
    public static GameObjectType T6;
    public static GameObjectType T7;
    public static GameObjectType T8;
    public static GameObjectType T5;
    public static GameObjectType T13;
    static GameObjectType T14;
    public static GameObjectType T11;
    static GameObjectType T15;
    static GameObjectType T21;
    static GameObjectType T19;
    static GameObjectType T18;
    static GameObjectType T20;
    public static GameObjectType T22;
    public static GameObjectType T10;
    static GameObjectType T12;
    public int type;
    public static GameObjectType T0 = new GameObjectType(0, 0);
    public int slot;

    @Override
    public int method244() {
	return -1976050083 * type;
    }

    GameObjectType(int i, int i_0_) {
	type = -1456740875 * i;
	slot = 341764099 * i_0_;
    }

    static {
	T1 = new GameObjectType(1, 0);
	T2 = new GameObjectType(2, 0);
	T3 = new GameObjectType(3, 0);
	T9 = new GameObjectType(9, 2);
	T4 = new GameObjectType(4, 1);
	T5 = new GameObjectType(5, 1);
	T6 = new GameObjectType(6, 1);
	T7 = new GameObjectType(7, 1);
	T8 = new GameObjectType(8, 1);
	T12 = new GameObjectType(12, 2);
	T13 = new GameObjectType(13, 2);
	T14 = new GameObjectType(14, 2);
	T15 = new GameObjectType(15, 2);
	T16 = new GameObjectType(16, 2);
	T17 = new GameObjectType(17, 2);
	T18 = new GameObjectType(18, 2);
	T19 = new GameObjectType(19, 2);
	T20 = new GameObjectType(20, 2);
	T21 = new GameObjectType(21, 2);
	T10 = new GameObjectType(10, 2);
	T11 = new GameObjectType(11, 2);
	T22 = new GameObjectType(22, 3);
    }

    @Override
    public int method243() {
	return -1976050083 * type;
    }

    @Override
    public int getType(int i) {
	try {
	    return -1976050083 * type;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("ro.f(").append(')').toString());
	}
    }

    static final void method5735(Class403 class403, int i) {
	try {
	    String string = (String) (class403.anObjectArray5240[(class403.anInt5241 -= 969361751) * -203050393]);
	    int i_1_ = (class403.anIntArray5244[((class403.anInt5239 -= -391880689) * 681479919)]);
	    Class82_Sub14.method911(string, i_1_ == 1, 1059138382);
	    class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919 - 1)] = 367592105 * Class344.anInt3688;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("ro.abs(").append(')').toString());
	}
    }
}
