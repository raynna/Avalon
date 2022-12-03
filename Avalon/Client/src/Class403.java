
/* Class403 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.awt.EventQueue;

public class Class403 {
    Interface3 anInterface3_5233;
    Object[] anObjectArray5234;
    long[] aLongArray5235;
    int[] anIntArray5236 = new int[5];
    ClanChannel myClanChannel;
    int[][] anIntArrayArray5238 = new int[5][5000];
    int anInt5239;
    Object[] anObjectArray5240;
    int anInt5241;
    Entity aClass365_Sub1_Sub1_Sub2_5242;
    Class177 aClass177_5243;
    int[] anIntArray5244 = new int[1000];
    int anInt5245;
    Class390 aClass390_5246;
    Class390 aClass390_5247;
    int[] anIntArray5248;
    Class402[] aClass402Array5249;
    int anInt5250;
    long[] aLongArray5251;
    ClanSettings aClass162_5252;
    Class365_Sub1_Sub4_Sub1 aClass365_Sub1_Sub4_Sub1_5253;
    int anInt5254;
    int anInt5255;
    Class214 aClass214_5256;
    int[] anIntArray5257;
    Class394[] aClass394Array5258;
    int anInt5259;
    ClientScript aClass298_Sub37_Sub17_5260;
    boolean aBoolean5261;
    static EventQueue anEventQueue5262;

    Class403() {
	this.anInt5239 = 0;
	this.anObjectArray5240 = new Object[1000];
	this.anInt5241 = 0;
	this.aLongArray5251 = new long[1000];
	this.anInt5245 = 0;
	this.anInt5250 = 0;
	this.aClass402Array5249 = new Class402[50];
	this.aClass390_5246 = new Class390();
	this.aClass390_5247 = new Class390();
	this.anInt5254 = 0;
	this.anInt5259 = -286750741;
    }

    static final void method4942(Class403 class403, byte i) {
	try {
	    class403.anInt5239 -= -783761378;
	    int i_0_ = (class403.anIntArray5244[class403.anInt5239 * 681479919]);
	    int i_1_ = (class403.anIntArray5244[1 + 681479919 * class403.anInt5239]);
	    class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919 - 1)] = (Class447.aClass469_5618.method6045(i_0_, (short) -1307).anIntArray9653[i_1_]);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("qt.aci(").append(')').toString());
	}
    }

    static final void method4943(Class403 class403, int i) {
	try {
	    Class390 class390 = (class403.aBoolean5261 ? class403.aClass390_5247 : class403.aClass390_5246);
	    IComponentDefinition class105 = class390.aClass105_4168;
	    Class119 class119 = class390.aClass119_4167;
	    Class79.method849(class105, class119, class403, 1666748525);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("qt.jr(").append(')').toString());
	}
    }

    static final void method4944(long l) {
	try {
	    int i = -2080858977 * client.anInt8753;
	    int i_2_ = client.anInt8755 * 1818837461;
	    if (Class75.anInt708 * 1103750049 != i) {
		int i_3_ = i - 1103750049 * Class75.anInt708;
		int i_4_ = (int) (i_3_ * l / 320L);
		if (i_3_ > 0) {
		    if (0 == i_4_)
			i_4_ = 1;
		    else if (i_4_ > i_3_)
			i_4_ = i_3_;
		} else if (i_4_ == 0)
		    i_4_ = -1;
		else if (i_4_ < i_3_)
		    i_4_ = i_3_;
		Class75.anInt708 += -1832337311 * i_4_;
	    }
	    if (Class106.anInt1309 * 1346160791 != i_2_) {
		int i_5_ = i_2_ - 1346160791 * Class106.anInt1309;
		int i_6_ = (int) (l * i_5_ / 320L);
		if (i_5_ > 0) {
		    if (i_6_ == 0)
			i_6_ = 1;
		    else if (i_6_ > i_5_)
			i_6_ = i_5_;
		} else if (0 == i_6_)
		    i_6_ = -1;
		else if (i_6_ < i_5_)
		    i_6_ = i_5_;
		Class106.anInt1309 += i_6_ * 1831024423;
	    }
	    client.aFloat8949 += client.aFloat8759 * l / 40.0F * 8.0F;
	    client.aFloat8757 += 8.0F * (l * client.aFloat8760 / 40.0F);
	    Class91.method1002((byte) -110);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("qt.hd(").append(')').toString());
	}
    }

    static final void method4945(Class403 class403, int i) {
	try {
	    if (-932179775 * class403.anInt5250 != 0) {
		Class402 class402 = (class403.aClass402Array5249[(class403.anInt5250 -= 373063489) * -932179775]);
		class403.aClass298_Sub37_Sub17_5260 = class402.aClass298_Sub37_Sub17_5229;
		class403.aClass394Array5258 = (class403.aClass298_Sub37_Sub17_5260.aClass394Array9675);
		class403.anIntArray5257 = (class403.aClass298_Sub37_Sub17_5260.anIntArray9676);
		class403.anInt5259 = class402.anInt5226 * -2092362779;
		class403.anIntArray5248 = class402.anIntArray5228;
		class403.anObjectArray5234 = class402.anObjectArray5231;
		class403.aLongArray5235 = class402.aLongArray5230;
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("qt.av(").append(')').toString());
	}
    }
}
