/* Class422_Sub5 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class422_Sub5 extends Class422 {
    static int anInt8376 = 1;
    public static int anInt8377 = 2;
    public static int anInt8378 = 0;

    public Class422_Sub5(int i, Class298_Sub48 class298_sub48) {
	super(i, class298_sub48);
    }

    public void method5640(int i) {
	try {
	    if (-1598873795 * anInt5350 < 0 && anInt5350 * -1598873795 > 2)
		anInt5350 = method5611(1892526868) * 1886334997;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("adf.s(").append(')').toString());
	}
    }

    public boolean method5641(int i) {
	try {
	    return true;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("adf.z(").append(')').toString());
	}
    }

    @Override
    void method5610(int i) {
	anInt5350 = 1886334997 * i;
    }

    @Override
    void method5614(int i, int i_0_) {
	try {
	    anInt5350 = 1886334997 * i;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("adf.p(").append(')').toString());
	}
    }

    public int method5642(int i) {
	try {
	    return -1598873795 * anInt5350;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("adf.y(").append(')').toString());
	}
    }

    public Class422_Sub5(Class298_Sub48 class298_sub48) {
	super(class298_sub48);
    }

    @Override
    public int method5616(int i) {
	if (i == 0 || aClass298_Sub48_5346.aClass422_Sub28_7573.method5724(2001855764) == 1)
	    return 1;
	return 2;
    }

    @Override
    int method5615() {
	return 1;
    }

    @Override
    public int method5612(int i, int i_1_) {
	try {
	    if (i == 0 || aClass298_Sub48_5346.aClass422_Sub28_7573.method5724(544778968) == 1)
		return 1;
	    return 2;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("adf.f(").append(')').toString());
	}
    }

    @Override
    int method5611(int i) {
	try {
	    return 1;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("adf.a(").append(')').toString());
	}
    }

    static void method5643(IComponentDefinition class105, int i) {
	try {
	    if (client.aBoolean8835) {
		if (class105.anObjectArray1247 != null) {
		    IComponentDefinition class105_2_ = Class140.method1558(Class379.anInt4099 * 1262526353, 392084321 * client.anInt8836, -156511736);
		    if (class105_2_ != null) {
			ScriptEnvironment class298_sub46 = new ScriptEnvironment();
			class298_sub46.aClass105_7525 = class105;
			class298_sub46.aClass105_7529 = class105_2_;
			class298_sub46.arguements = class105.anObjectArray1247;
			Class444.executeScript(class298_sub46);
		    }
		}
		Class298_Sub36 class298_sub36 = Class18.method359(OutcommingPacket.ITEM_ON_ITEM, client.aClass25_8711.aClass449_330, (byte) 35);
		class298_sub36.out.writeShort(-1232467723 * class105.anInt1280, 16711935);
		class298_sub36.out.writeShortLE128(class105.anInt1154 * -1309843523);
		class298_sub36.out.writeInt(Class379.anInt4099 * 1262526353, -935068496);
		class298_sub36.out.writeInt(-440872681 * class105.ihash, -1060289644);
		class298_sub36.out.writeShort(392084321 * client.anInt8836, 16711935);
		class298_sub36.out.writeShortLE128(client.anInt8906 * 1408085039);
		client.aClass25_8711.method390(class298_sub36, (byte) -64);
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("adf.kx(").append(')').toString());
	}
    }

    static final void method5644(int i) {
	try {
	    for (int i_3_ = Class128.aClass148_6331.method1607(true, -2004625733); i_3_ != -1; i_3_ = Class128.aClass148_6331.method1607(false, -1890624062)) {
		Class477.method6094(i_3_, (byte) -94);
		client.anIntArray8874[((client.anInt8783 += -1763975993) * -1667357449) - 1 & 0x1f] = i_3_;
	    }
	    for (Class298_Sub37_Sub12 class298_sub37_sub12 = Class97_Sub1.method1045((byte) 12); class298_sub37_sub12 != null; class298_sub37_sub12 = Class97_Sub1.method1045((byte) 12)) {
		int opcode = class298_sub37_sub12.getOpcode(766012260);
		long hash = class298_sub37_sub12.getHash(-391880689);
		if (1 == opcode) {
		    Class374.anIntArray4078[(int) hash] = 479598359 * class298_sub37_sub12.type;
		    client.aBoolean8736 |= Class254.aBooleanArray2790[(int) hash];
		    client.anIntArray8876[((client.anInt8877 += 163718667) * 116700579) - 1 & 0x1f] = (int) hash;
		} else if (2 == opcode) {
		    ClientScriptsExecutor.aStringArray4126[(int) hash] = (class298_sub37_sub12.aString9611);
		    client.anIntArray8787[((client.anInt8879 += -646054651) * -466597939) - 1 & 0x1f] = (int) hash;
		} else if (opcode == 3) {
		    IComponentDefinition class105 = Class50.getIComponentDefinitions((int) hash, (byte) -62);
		    if (!class298_sub37_sub12.aString9611.equals(class105.aString1212)) {
			class105.aString1212 = (class298_sub37_sub12.aString9611);
			Tradution.method6054(class105, 753227077);
		    }
		} else if (opcode == 22) {
		    IComponentDefinition class105 = Class50.getIComponentDefinitions((int) hash, (byte) -82);
		    if (class105.aBoolean1246 != (1 == 479598359 * class298_sub37_sub12.type)) {
			class105.aBoolean1246 = 1 == (class298_sub37_sub12.type) * 479598359;
			Tradution.method6054(class105, 130202608);
		    }
		} else if (4 == opcode) {
		    IComponentDefinition class105 = Class50.getIComponentDefinitions((int) hash, (byte) -20);
		    int i_5_ = ((class298_sub37_sub12.type) * 479598359);
		    int i_6_ = ((class298_sub37_sub12.value) * -1447843633);
		    int i_7_ = -1074324071 * class298_sub37_sub12.modelZoom;
		    if (class105.anInt1184 * 1548853569 != i_5_ || i_6_ != class105.anInt1151 * 572201537 || i_7_ != 1148770405 * class105.anInt1140) {
			class105.anInt1184 = i_5_ * -1530138943;
			class105.anInt1151 = i_6_ * -1825442367;
			class105.anInt1140 = 908204397 * i_7_;
			class105.aClass498_1307 = null;
			Tradution.method6054(class105, 422949042);
		    }
		} else if (5 == opcode) {
		    IComponentDefinition class105 = Class50.getIComponentDefinitions((int) hash, (byte) 56);
		    if (class105.anInt1290 * 1347982601 != (class298_sub37_sub12.type) * 479598359) {
			if (479598359 * class298_sub37_sub12.type != -1) {
			    if (null == class105.aClass438_1152)
				class105.aClass438_1152 = new Class438_Sub1();
			    class105.aClass438_1152.method5821((class298_sub37_sub12.type) * 479598359, -1984687950);
			} else
			    class105.aClass438_1152 = null;
			class105.anInt1290 = (class298_sub37_sub12.type) * 1256289055;
			Tradution.method6054(class105, -2102110719);
		    }
		} else if (6 == opcode) {
		    int i_8_ = ((class298_sub37_sub12.type) * 479598359);
		    int i_9_ = i_8_ >> 10 & 0x1f;
		    int i_10_ = i_8_ >> 5 & 0x1f;
		    int i_11_ = i_8_ & 0x1f;
		    int i_12_ = (i_10_ << 11) + (i_9_ << 19) + (i_11_ << 3);
		    IComponentDefinition class105 = Class50.getIComponentDefinitions((int) hash, (byte) -37);
		    if (i_12_ != class105.anInt1170 * 1045422783) {
			class105.anInt1170 = -695431873 * i_12_;
			Tradution.method6054(class105, -37482416);
		    }
		} else if (opcode == 7) {
		    IComponentDefinition class105 = Class50.getIComponentDefinitions((int) hash, (byte) -71);
		    boolean bool = 1 == 479598359 * class298_sub37_sub12.type;
		    if (bool != class105.aBoolean1161) {
			class105.aBoolean1161 = bool;
			Tradution.method6054(class105, -1924243832);
		    }
		} else if (opcode == 8) {
		    IComponentDefinition class105 = Class50.getIComponentDefinitions((int) hash, (byte) 54);
		    if ((479598359 * class298_sub37_sub12.type != class105.anInt1190 * 7329457) || ((class298_sub37_sub12.value) * -1447843633 != class105.anInt1262 * -1086526073) || (-1074324071 * class298_sub37_sub12.modelZoom != class105.anInt1284 * -261021353)) {
			class105.anInt1190 = (class298_sub37_sub12.type) * -161133497;
			class105.anInt1262 = (class298_sub37_sub12.value) * 752254073;
			class105.anInt1284 = (class298_sub37_sub12.modelZoom) * 1673638543;
			if (-1232467723 * class105.anInt1280 != -1) {
			    if (-692202853 * class105.anInt1221 > 0)
				class105.anInt1284 = (class105.anInt1284 * 237251296 / (-692202853 * class105.anInt1221) * -1066050969);
			    else if (class105.anInt1253 * 1769572195 > 0)
				class105.anInt1284 = (class105.anInt1284 * 237251296 / (class105.anInt1253 * 1769572195) * -1066050969);
			}
			Tradution.method6054(class105, -1617809711);
		    }
		} else if (opcode == 9) {
		    IComponentDefinition class105 = Class50.getIComponentDefinitions((int) hash, (byte) 49);
		    if (((class298_sub37_sub12.type) * 479598359 != class105.anInt1280 * -1232467723) || (-1447843633 * class298_sub37_sub12.value != -66163287 * class105.anInt1281)) {
			class105.anInt1280 = (class298_sub37_sub12.type) * 1795180635;
			class105.anInt1281 = (class298_sub37_sub12.value) * 1181892023;
			Tradution.method6054(class105, 748648909);
		    }
		} else if (opcode == 10) {
		    IComponentDefinition class105 = Class50.getIComponentDefinitions((int) hash, (byte) 88);
		    if ((-407676483 * class105.anInt1297 != (class298_sub37_sub12.type) * 479598359) || ((class298_sub37_sub12.value) * -1447843633 != -1523987341 * class105.anInt1248) || (1004185785 * class105.anInt1192 != -1074324071 * (class298_sub37_sub12.modelZoom))) {
			class105.anInt1297 = 1482071907 * class298_sub37_sub12.type;
			class105.anInt1248 = -2128996555 * class298_sub37_sub12.value;
			class105.anInt1192 = 698346465 * class298_sub37_sub12.modelZoom;
			Tradution.method6054(class105, -875934873);
		    }
		} else if (11 == opcode) {
		    IComponentDefinition class105 = Class50.getIComponentDefinitions((int) hash, (byte) 37);
		    class105.aByte1146 = (byte) 0;
		    class105.anInt1143 = ((class105.anInt1286 = 1890691465 * class298_sub37_sub12.type) * -1672688609);
		    class105.aByte1147 = (byte) 0;
		    class105.anInt1155 = (class105.anInt1159 = (class298_sub37_sub12.value) * -1822519589) * 705123139;
		    Tradution.method6054(class105, -429816482);
		} else if (opcode == 12) {
		    IComponentDefinition class105 = Class50.getIComponentDefinitions((int) hash, (byte) 43);
		    int i_13_ = 479598359 * class298_sub37_sub12.type;
		    if (null != class105 && 0 == class105.type * -1215239439) {
			if (i_13_ > (class105.anInt1169 * 2053897963 - 457937409 * class105.anInt1162))
			    i_13_ = (class105.anInt1169 * 2053897963 - 457937409 * class105.anInt1162);
			if (i_13_ < 0)
			    i_13_ = 0;
			if (class105.anInt1167 * -1424956747 != i_13_) {
			    class105.anInt1167 = -1915192419 * i_13_;
			    Tradution.method6054(class105, -1007823726);
			}
		    }
		} else if (opcode == 14) {
		    IComponentDefinition class105 = Class50.getIComponentDefinitions((int) hash, (byte) 63);
		    class105.spriteId = 947110461 * class298_sub37_sub12.type;
		} else if (15 == opcode) {
		    Class3.aBoolean63 = true;
		    Class3.anInt62 = (class298_sub37_sub12.type) * 20118889;
		    Class3.anInt54 = (class298_sub37_sub12.value) * 1038073637;
		} else if (opcode == 16) {
		    IComponentDefinition class105 = Class50.getIComponentDefinitions((int) hash, (byte) -2);
		    class105.anInt1210 = 861383641 * class298_sub37_sub12.type;
		} else if (opcode == 20) {
		    IComponentDefinition class105 = Class50.getIComponentDefinitions((int) hash, (byte) -1);
		    class105.aBoolean1211 = 1 == (class298_sub37_sub12.type) * 479598359;
		} else if (21 == opcode) {
		    IComponentDefinition class105 = Class50.getIComponentDefinitions((int) hash, (byte) -52);
		    class105.aBoolean1183 = 1 == 479598359 * class298_sub37_sub12.type;
		} else if (opcode == 17) {
		    IComponentDefinition class105 = Class50.getIComponentDefinitions((int) hash, (byte) 62);
		    class105.anInt1289 = -696165999 * class298_sub37_sub12.type;
		} else if (opcode == 18) {
		    IComponentDefinition class105 = Class50.getIComponentDefinitions((int) hash, (byte) 3);
		    int i_14_ = (int) (hash >> 32);
		    class105.method1126(i_14_, (short) (479598359 * (class298_sub37_sub12.type)), (short) (-1447843633 * (class298_sub37_sub12.value)), -1799363747);
		} else if (19 == opcode) {
		    IComponentDefinition class105 = Class50.getIComponentDefinitions((int) hash, (byte) -87);
		    int i_15_ = (int) (hash >> 32);
		    class105.method1120(i_15_, (short) ((class298_sub37_sub12.type) * 479598359), (short) (-1447843633 * (class298_sub37_sub12.value)), 571287910);
		}
	    }
	}
	catch (RuntimeException runtimeexception) {
	    /*
	     * throw Class346.method4175(runtimeexception, new StringBuilder()
	     * .append("adf.gh(").append(')').toString());
	     */
	}
    }
}
