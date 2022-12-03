/* Class303 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class303 {
    int anInt3260;
    int anInt3261;
    int anInt3262;
    int anInt3263;
    int anInt3264;

    Class303(int i, int i_0_, int i_1_, int i_2_, int i_3_) {
	this.anInt3264 = -727927571 * i_0_;
	this.anInt3261 = 1245468715 * i_1_;
	this.anInt3262 = i_2_ * -75483929;
	this.anInt3263 = i_3_ * -821826869;
	this.anInt3260 = (-1374344735 * this.anInt3263 - this.anInt3262 * -193549091);
    }

    static final void method3732(Class403 class403, byte i) {
	try {
	    Class390 class390 = (class403.aBoolean5261 ? class403.aClass390_5247 : class403.aClass390_5246);
	    IComponentDefinition class105 = class390.aClass105_4168;
	    int i_4_ = (class403.anIntArray5244[((class403.anInt5239 -= -391880689) * 681479919)]);
	    i_4_--;
	    if (class105.aStringArray1195 == null || i_4_ >= class105.aStringArray1195.length || class105.aStringArray1195[i_4_] == null)
		class403.anObjectArray5240[((class403.anInt5241 += 969361751) * -203050393) - 1] = "";
	    else
		class403.anObjectArray5240[((class403.anInt5241 += 969361751) * -203050393) - 1] = class105.aStringArray1195[i_4_];
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("mp.pu(").append(')').toString());
	}
    }

    static final void method3733(Class403 class403, byte i) {
	try {
	    class403.anInt5239 -= -1567522756;
	    Class239.method2210((class403.anIntArray5244[class403.anInt5239 * 681479919]), (class403.anIntArray5244[681479919 * class403.anInt5239 + 1]), (class403.anIntArray5244[681479919 * class403.anInt5239 + 2]), (class403.anIntArray5244[3 + 681479919 * class403.anInt5239]), 256, 1676570424);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("mp.tz(").append(')').toString());
	}
    }

    static final void method3734(Class403 class403, int i) {
	try {
	    int i_5_ = (class403.anIntArray5244[((class403.anInt5239 -= -391880689) * 681479919)]);
	    class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919 - 1)] = client.GRAND_EXCHANGE_SLOTS[i_5_].anInt2771 * 5713347;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("mp.yu(").append(')').toString());
	}
    }

    static final void method3735(Class403 class403, byte i) {
	try {
	    class403.anInt5239 -= -783761378;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("mp.afs(").append(')').toString());
	}
    }

    static final void method3736(Class403 class403, byte i) {
	try {
	    int i_6_ = (class403.anIntArray5244[((class403.anInt5239 -= -391880689) * 681479919)]);
	    Class390 class390;
	    if (class403.aBoolean5261)
		class390 = class403.aClass390_5247;
	    else
		class390 = class403.aClass390_5246;
	    class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919 - 1)] = class390.method4866((class403.aClass365_Sub1_Sub1_Sub2_5242.aClass119_10131), i_6_, -1, 867608709) ? 1 : 0;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("mp.aob(").append(')').toString());
	}
    }

    public static final void method3737(Player class365_sub1_sub1_sub2_sub2, int i, int i_7_, byte i_8_, byte i_9_) {
	try {
	    int i_10_ = class365_sub1_sub1_sub2_sub2.scenePositionXQueue[0];
	    int i_11_ = class365_sub1_sub1_sub2_sub2.scenePositionYQueue[0];
	    if (i_10_ >= 0 && i_10_ < client.aClass283_8716.method2629(-2022633621) && i_11_ >= 0 && i_11_ < client.aClass283_8716.method2630(211521322) && (i >= 0 && i < client.aClass283_8716.method2629(-2074801405) && i_7_ >= 0 && i_7_ < client.aClass283_8716.method2630(1180671988))) {
		int i_12_ = (Class298_Sub37.calculateRoute(i_10_, i_11_, class365_sub1_sub1_sub2_sub2.getSize(), Routes.createExactStrategy(i, i_7_), (client.aClass283_8716.getSceneClipDataPlane(class365_sub1_sub1_sub2_sub2.plane)), true, client.pathBufferX, client.pathBufferY));
		if (i_12_ >= 1 && i_12_ <= 3) {
		    for (int i_13_ = 0; i_13_ < i_12_ - 1; i_13_++)
			class365_sub1_sub1_sub2_sub2.method4473(client.pathBufferX[i_13_], client.pathBufferY[i_13_], i_8_, (byte) 55);
		}
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("mp.jl(").append(')').toString());
	}
    }
}
