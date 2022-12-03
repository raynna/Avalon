/* Class336_Sub6 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class ExactStrategy extends PathStrategy {
    @Override
    public boolean method4091(int i, int i_0_, int i_1_, Class289 class289) {
	return (-1331662251 * toX == i_0_ && i_1_ == 1517720743 * toY);
    }

    ExactStrategy() {
	/* empty */
    }

    @Override
    public boolean method4089(int i, int i_2_, int i_3_, Class289 class289) {
	return (-1331662251 * toX == i_2_ && i_3_ == 1517720743 * toY);
    }

    @Override
    public boolean method4090(int i, int i_4_, int i_5_, Class289 class289, int i_6_) {
	try {
	    return (-1331662251 * toX == i_4_ && i_5_ == 1517720743 * toY);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("acy.a(").append(')').toString());
	}
    }

    static final void method4106(Class403 class403, byte i) {
	try {
	    String string = (String) (class403.anObjectArray5240[(class403.anInt5241 -= 969361751) * -203050393]);
	    Class12.method338(string, true, (short) 5563);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("acy.ww(").append(')').toString());
	}
    }

    public static void method4107(Class113 class113, int i) {
	try {
	    Class106.aClass113_1308 = class113;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("acy.a(").append(')').toString());
	}
    }

    public static GameObjectType[] method4108(int i) {
	try {
	    return (new GameObjectType[] { GameObjectType.T0, GameObjectType.T17, GameObjectType.T14, GameObjectType.T5, GameObjectType.T2, GameObjectType.T6, GameObjectType.T3, GameObjectType.T11, GameObjectType.T16, GameObjectType.T12, GameObjectType.T20, GameObjectType.T21, GameObjectType.T15, GameObjectType.T19, GameObjectType.T18, GameObjectType.T22, GameObjectType.T9, GameObjectType.T8, GameObjectType.T4, GameObjectType.T1, GameObjectType.T7, GameObjectType.T10, GameObjectType.T13 });
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("acy.a(").append(')').toString());
	}
    }
}
