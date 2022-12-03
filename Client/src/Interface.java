/* Class298_Sub51 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Interface extends Class298 {
    public int clipped;
    public int interfaceId;

    public boolean method3573(int i) {
	try {
	    return true;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("acs.a(").append(')').toString());
	}
    }

    public Interface(int interfaceId, int cliped) {
	this.interfaceId = interfaceId * 84132523;
	this.clipped = -1287090225 * cliped;
    }

    static final void method3574(Class403 class403, int i) {
	try {
	    int i_1_ = (class403.anIntArray5244[((class403.anInt5239 -= -391880689) * 681479919)]);
	    IComponentDefinition class105 = Class50.getIComponentDefinitions(i_1_, (byte) -86);
	    if (null == class105.aString1228)
		class403.anObjectArray5240[((class403.anInt5241 += 969361751) * -203050393) - 1] = "";
	    else
		class403.anObjectArray5240[((class403.anInt5241 += 969361751) * -203050393) - 1] = class105.aString1228;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("acs.sw(").append(')').toString());
	}
    }

    public static void method3575(byte i) {
	try {
	    Class87.aShort792 = client.aShort8934;
	    Class315.aShort3306 = client.aShort8935;
	    Class51.aShort504 = client.aShort8839;
	    Class396.aShort5193 = client.aShort8743;
	    Class87.aBoolean802 = true;
	    if (Class87.anInt801 * 57998513 != 0 && 0 != 1705830085 * Class87.anInt800) {
		client.aShort8839 = (short) 334;
		client.aShort8743 = (short) 334;
		client.aShort8935 = client.aShort8934 = (short) (Class87.anInt801 * -369532416 / (1705830085 * Class87.anInt800));
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("acs.p(").append(')').toString());
	}
    }

    public static int[] method3576(int i, int i_2_) {
	try {
	    int[] is = new int[3];
	    Class422_Sub9.method5658(Class411.method5574(i, 1691290576));
	    is[0] = Class490.aCalendar6073.get(5);
	    is[1] = Class490.aCalendar6073.get(2);
	    is[2] = Class490.aCalendar6073.get(1);
	    return is;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("acs.u(").append(')').toString());
	}
    }
}
