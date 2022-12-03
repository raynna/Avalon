/* Class464 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class464 {
    public int[] anIntArray5690;
    static int anInt5691 = 2;
    static int anInt5692 = 4;
    static int anInt5693 = 1;
    public int[] anIntArray5694 = new int[3];
    public int[] anIntArray5695 = new int[3];
    static int anInt5696 = 8;
    public int[] anIntArray5697;
    public short[] aShortArray5698;
    public short[] aShortArray5699;

    Class464(ItemDefinitions class468) {
	anIntArray5690 = new int[2];
	anIntArray5697 = new int[2];
	anIntArray5694[0] = 1343198193 * class468.maleEquipModelId1;
	anIntArray5694[1] = class468.maleEquipModelId2 * 34210967;
	anIntArray5694[2] = class468.maleEquipModelId3 * 1313278521;
	anIntArray5695[0] = 1585491093 * class468.femaleEquipModelId1;
	anIntArray5695[1] = class468.femaleEquipModelId2 * -1284247975;
	anIntArray5695[2] = class468.femaleEquipModelId3 * -1767718263;
	anIntArray5690[0] = class468.anInt5760 * -1282951055;
	anIntArray5690[1] = 86274879 * class468.anInt5749;
	anIntArray5697[0] = class468.anInt5750 * -1531415419;
	anIntArray5697[1] = class468.anInt5764 * 1578724433;
	if (class468.modifiedModelColors != null) {
	    aShortArray5698 = new short[class468.modifiedModelColors.length];
	    System.arraycopy(class468.modifiedModelColors, 0, aShortArray5698, 0, aShortArray5698.length);
	}
	if (null != class468.modifiedTextureColors) {
	    aShortArray5699 = new short[class468.modifiedTextureColors.length];
	    System.arraycopy(class468.modifiedTextureColors, 0, aShortArray5699, 0, aShortArray5699.length);
	}
    }

    public static final void method6015(int i) {
	try {
	    if (!client.aBoolean8762) {
		client.aFloat8759 += (-24.0F - client.aFloat8759) / 2.0F;
		client.aBoolean8763 = true;
		client.aBoolean8762 = true;
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("td.hr(").append(')').toString());
	}
    }

    static final void method6016(Class403 class403, int i) {
	try {
	    int i_0_ = (class403.anIntArray5257[class403.anInt5259 * 1883543357]);
	    ClientScriptsExecutor.aStringArray4126[i_0_] = (String) (class403.anObjectArray5240[((class403.anInt5241 -= 969361751) * -203050393)]);
	    Class272.method2558(i_0_, 1525227442);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("td.am(").append(')').toString());
	}
    }
}
