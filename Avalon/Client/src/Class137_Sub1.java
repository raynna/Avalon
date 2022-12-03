/* Class137_Sub1 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class137_Sub1 extends Class137 {
    static int anInt6961 = 13;
    int[][] anIntArrayArray6962;
    static int anInt6963 = 1;
    static int anInt6964 = 0;
    static int anInt6965 = 2;
    static int anInt6966 = 4;
    static int anInt6967 = 5;
    static int anInt6968 = 6;
    static int anInt6969 = 15;
    static int anInt6970 = 8;
    static int anInt6971 = 9;
    static int anInt6972 = 10;
    static int anInt6973 = 1;
    static int anInt6974 = 12;
    static int anInt6975 = 7;
    static int anInt6976 = 14;
    Class233 aClass233_6977 = new Class233();
    static int anInt6978 = 16;
    static int anInt6979 = 17;
    static int anInt6980 = 2;
    static int anInt6981 = 11;
    static int anInt6982 = 3;
    static int anInt6983 = 7;
    static int anInt6984 = 12;
    static int anInt6985 = 17;
    static int anInt6986 = 18;
    Class123 aClass123_6987;
    Class110[] aClass110Array6988 = new Class110[18];
    static int anInt6989 = 0;
    int[] anIntArray6990;
    static int anInt6991;

    boolean method1522(int i) throws Exception_Sub2 {
	try {
	    this.aClass123_6987 = aClass_ra_Sub3_1520.method5297("Model");
	    Class298_Sub31_Sub1 class298_sub31_sub1 = this.aClass123_6987.method1360("DiffuseSampler", 1135001419);
	    Class298_Sub31_Sub1 class298_sub31_sub1_0_ = this.aClass123_6987.method1360("EnvironmentSampler", -972656168);
	    Class298_Sub31_Sub1 class298_sub31_sub1_1_ = this.aClass123_6987.method1360("PointLightsPosAndRadiusSq", 585215200);
	    Class298_Sub31_Sub1 class298_sub31_sub1_2_ = this.aClass123_6987.method1360("PointLightsDiffuseColour", -1873919067);
	    Class298_Sub31_Sub1 class298_sub31_sub1_3_ = this.aClass123_6987.method1360("WVPMatrix", 43401113);
	    Class298_Sub31_Sub1 class298_sub31_sub1_4_ = this.aClass123_6987.method1360("TexCoordMatrix", -706631042);
	    Class298_Sub31_Sub1 class298_sub31_sub1_5_ = this.aClass123_6987.method1360("HeightFogPlane", 363843467);
	    Class298_Sub31_Sub1 class298_sub31_sub1_6_ = this.aClass123_6987.method1360("HeightFogColour", 177108279);
	    Class298_Sub31_Sub1 class298_sub31_sub1_7_ = this.aClass123_6987.method1360("DistanceFogPlane", -1871545778);
	    Class298_Sub31_Sub1 class298_sub31_sub1_8_ = this.aClass123_6987.method1360("DistanceFogColour", 245551574);
	    Class298_Sub31_Sub1 class298_sub31_sub1_9_ = this.aClass123_6987.method1360("SunDir", 1373721238);
	    Class298_Sub31_Sub1 class298_sub31_sub1_10_ = this.aClass123_6987.method1360("SunColour", -1112379488);
	    Class298_Sub31_Sub1 class298_sub31_sub1_11_ = this.aClass123_6987.method1360("AntiSunColour", 737015999);
	    Class298_Sub31_Sub1 class298_sub31_sub1_12_ = this.aClass123_6987.method1360("AmbientColour", -602820050);
	    Class298_Sub31_Sub1 class298_sub31_sub1_13_ = this.aClass123_6987.method1360("EyePos", -2130760607);
	    Class298_Sub31_Sub1 class298_sub31_sub1_14_ = this.aClass123_6987.method1360("SpecularExponent", -893975178);
	    Class298_Sub31_Sub1 class298_sub31_sub1_15_ = this.aClass123_6987.method1360("WorldMatrix", -1633360167);
	    this.aClass110Array6988[0] = this.aClass123_6987.method1327("Unlit", (byte) -128);
	    this.aClass110Array6988[1] = this.aClass123_6987.method1327("Unlit_IgnoreAlpha", (byte) -73);
	    this.aClass110Array6988[17] = this.aClass123_6987.method1327("UnderwaterGround", (byte) -78);
	    for (int i_16_ = 0; i_16_ <= 4; i_16_++) {
		this.aClass110Array6988[i_16_ + 2] = (this.aClass123_6987.method1327(new StringBuilder().append("Standard_").append(i_16_).append("PointLights").toString(), (byte) -123));
		this.aClass110Array6988[i_16_ + 7] = (this.aClass123_6987.method1327(new StringBuilder().append("Specular_").append(i_16_).append("PointLights").toString(), (byte) -11));
		this.aClass110Array6988[12 + i_16_] = (this.aClass123_6987.method1327(new StringBuilder().append("EnvironmentalMapping_").append(i_16_).append("PointLights").toString(), (byte) -25));
	    }
	    for (int i_17_ = 0; i_17_ < 18; i_17_++) {
		int i_18_ = (this.aClass123_6987.method1329(this.aClass110Array6988[i_17_], -180449856));
		this.anIntArrayArray6962[i_17_][2] = class298_sub31_sub1.method3119(i_18_);
		this.anIntArrayArray6962[i_17_][3] = class298_sub31_sub1_0_.method3119(i_18_);
		this.anIntArrayArray6962[i_17_][1] = class298_sub31_sub1_1_.method3119(i_18_);
		this.anIntArrayArray6962[i_17_][0] = class298_sub31_sub1_2_.method3119(i_18_);
		this.anIntArrayArray6962[i_17_][4] = class298_sub31_sub1_3_.method3119(i_18_);
		this.anIntArrayArray6962[i_17_][5] = class298_sub31_sub1_4_.method3119(i_18_);
		this.anIntArrayArray6962[i_17_][7] = class298_sub31_sub1_5_.method3119(i_18_);
		this.anIntArrayArray6962[i_17_][8] = class298_sub31_sub1_6_.method3119(i_18_);
		this.anIntArrayArray6962[i_17_][9] = class298_sub31_sub1_7_.method3119(i_18_);
		this.anIntArrayArray6962[i_17_][10] = class298_sub31_sub1_8_.method3119(i_18_);
		this.anIntArrayArray6962[i_17_][11] = class298_sub31_sub1_9_.method3119(i_18_);
		this.anIntArrayArray6962[i_17_][12] = class298_sub31_sub1_10_.method3119(i_18_);
		this.anIntArrayArray6962[i_17_][13] = class298_sub31_sub1_11_.method3119(i_18_);
		this.anIntArrayArray6962[i_17_][14] = class298_sub31_sub1_12_.method3119(i_18_);
		this.anIntArrayArray6962[i_17_][6] = class298_sub31_sub1_13_.method3119(i_18_);
		this.anIntArrayArray6962[i_17_][15] = class298_sub31_sub1_15_.method3119(i_18_);
		this.anIntArrayArray6962[i_17_][16] = class298_sub31_sub1_14_.method3119(i_18_);
	    }
	    this.aClass123_6987.method1331(this.aClass110Array6988[2]);
	    return true;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("yg.m(").append(')').toString());
	}
    }

    @Override
    public void method1519() {
	this.aClass123_6987.method1331(this.aClass110Array6988[17]);
	this.anIntArray6990 = (this.anIntArrayArray6962[this.aClass123_6987.method1333(1705171320)]);
	this.aClass123_6987.method1340();
	method1523(0, -1073004345);
    }

    @Override
    public void method1506(Class233 class233) {
	try {
	    this.aClass233_6977.method2142(class233);
	    this.aClass233_6977.method2144(aClass_ra_Sub3_1520.aClass233_8251);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("yg.f(").append(')').toString());
	}
    }

    @Override
    public void method1504(boolean bool) {
	if (bool)
	    this.aClass123_6987.method1331(this.aClass110Array6988[1]);
	else
	    this.aClass123_6987.method1331(this.aClass110Array6988[0]);
	this.anIntArray6990 = (this.anIntArrayArray6962[this.aClass123_6987.method1333(1149539641)]);
	this.aClass123_6987.method1340();
	this.aClass123_6987.method1348(this.anIntArray6990[2], 0, anInterface9_Impl2_1517, -1231900613);
	this.aClass123_6987.method1336(this.anIntArray6990[4], this.aClass233_6977, (byte) -9);
	this.aClass123_6987.method1346((this.anIntArray6990[5]), aClass233_1519, -1599730439);
	this.aClass123_6987.method1342(this.anIntArray6990[7], aClass232_1523.aFloat2593, aClass232_1523.aFloat2592, aClass232_1523.aFloat2590, aClass232_1523.aFloat2591, (byte) 40);
	this.aClass123_6987.method1365(this.anIntArray6990[8], aClass217_1524.aFloat2451, aClass217_1524.aFloat2455, aClass217_1524.aFloat2454, 123660505);
	this.aClass123_6987.method1342(this.anIntArray6990[9], aClass232_1525.aFloat2593, aClass232_1525.aFloat2592, aClass232_1525.aFloat2590, aClass232_1525.aFloat2591, (byte) 95);
	this.aClass123_6987.method1365(this.anIntArray6990[10], aClass217_1522.aFloat2451, aClass217_1522.aFloat2455, aClass217_1522.aFloat2454, -461195201);
	aClass_ra_Sub3_1520.method5392(Class187.aClass187_1907, anInt1532, anInt1533, anInt1534, anInt1514);
    }

    @Override
    public void method1510() {
	try {
	    this.aClass123_6987.method1331(this.aClass110Array6988[17]);
	    this.anIntArray6990 = (this.anIntArrayArray6962[this.aClass123_6987.method1333(1837632307)]);
	    this.aClass123_6987.method1340();
	    method1523(0, -445981727);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("yg.d(").append(')').toString());
	}
    }

    @Override
    public void method1508(int i) {
	try {
	    this.aClass123_6987.method1331(this.aClass110Array6988[7 + i]);
	    this.anIntArray6990 = (this.anIntArrayArray6962[this.aClass123_6987.method1333(1308408550)]);
	    this.aClass123_6987.method1340();
	    this.aClass123_6987.method1365(this.anIntArray6990[6], aClass217_1530.aFloat2451, aClass217_1530.aFloat2455, aClass217_1530.aFloat2454, 526269137);
	    this.aClass123_6987.method1342(this.anIntArray6990[16], aFloat1521, aFloat1513, 0.0F, 0.0F, (byte) 86);
	    method1523(i, -283020523);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("yg.i(").append(')').toString());
	}
    }

    @Override
    public void method1507(int i) {
	try {
	    this.aClass123_6987.method1331(this.aClass110Array6988[i + 2]);
	    this.anIntArray6990 = (this.anIntArrayArray6962[this.aClass123_6987.method1333(1833355992)]);
	    this.aClass123_6987.method1340();
	    method1523(i, -1046248356);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("yg.p(").append(')').toString());
	}
    }

    void method1523(int i, int i_19_) {
	try {
	    this.aClass123_6987.method1348(this.anIntArray6990[2], 0, anInterface9_Impl2_1517, -1687300053);
	    this.aClass123_6987.method1336(this.anIntArray6990[4], this.aClass233_6977, (byte) -39);
	    this.aClass123_6987.method1346(this.anIntArray6990[5], aClass233_1519, -1599730439);
	    this.aClass123_6987.method1342(this.anIntArray6990[7], aClass232_1523.aFloat2593, aClass232_1523.aFloat2592, aClass232_1523.aFloat2590, aClass232_1523.aFloat2591, (byte) 81);
	    this.aClass123_6987.method1365(this.anIntArray6990[8], aClass217_1524.aFloat2451, aClass217_1524.aFloat2455, aClass217_1524.aFloat2454, -1324328823);
	    this.aClass123_6987.method1342(this.anIntArray6990[9], aClass232_1525.aFloat2593, aClass232_1525.aFloat2592, aClass232_1525.aFloat2590, aClass232_1525.aFloat2591, (byte) 20);
	    this.aClass123_6987.method1365(this.anIntArray6990[10], aClass217_1522.aFloat2451, aClass217_1522.aFloat2455, aClass217_1522.aFloat2454, 168782883);
	    this.aClass123_6987.method1343(this.anIntArray6990[11], aClass217_1527, 902988581);
	    this.aClass123_6987.method1343(this.anIntArray6990[12], aClass217_1528, -1254001480);
	    this.aClass123_6987.method1343(this.anIntArray6990[13], aClass217_1529, 2097192794);
	    this.aClass123_6987.method1343(this.anIntArray6990[14], aClass217_1515, -1756213043);
	    if (i > 0) {
		this.aClass123_6987.method1344(this.anIntArray6990[1], aFloatArray1516, 4 * i, 2080438398);
		this.aClass123_6987.method1344(this.anIntArray6990[0], aFloatArray1526, 4 * i, 358111941);
	    }
	    aClass_ra_Sub3_1520.method5392(Class187.aClass187_1907, anInt1532, anInt1533, anInt1534, anInt1514);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("yg.w(").append(')').toString());
	}
    }

    @Override
    public void method1520(int i) {
	this.aClass123_6987.method1331(this.aClass110Array6988[7 + i]);
	this.anIntArray6990 = (this.anIntArrayArray6962[this.aClass123_6987.method1333(1161240102)]);
	this.aClass123_6987.method1340();
	this.aClass123_6987.method1365(this.anIntArray6990[6], aClass217_1530.aFloat2451, aClass217_1530.aFloat2455, aClass217_1530.aFloat2454, 246564478);
	this.aClass123_6987.method1342(this.anIntArray6990[16], aFloat1521, aFloat1513, 0.0F, 0.0F, (byte) 76);
	method1523(i, -277375124);
    }

    @Override
    public void method1511(Class233 class233) {
	this.aClass233_6977.method2142(class233);
	this.aClass233_6977.method2144(aClass_ra_Sub3_1520.aClass233_8251);
    }

    @Override
    public void method1512(Class233 class233) {
	this.aClass233_6977.method2142(class233);
	this.aClass233_6977.method2144(aClass_ra_Sub3_1520.aClass233_8251);
    }

    @Override
    public void method1513(boolean bool) {
	if (bool)
	    this.aClass123_6987.method1331(this.aClass110Array6988[1]);
	else
	    this.aClass123_6987.method1331(this.aClass110Array6988[0]);
	this.anIntArray6990 = (this.anIntArrayArray6962[this.aClass123_6987.method1333(2019533415)]);
	this.aClass123_6987.method1340();
	this.aClass123_6987.method1348(this.anIntArray6990[2], 0, anInterface9_Impl2_1517, 26639341);
	this.aClass123_6987.method1336(this.anIntArray6990[4], this.aClass233_6977, (byte) -12);
	this.aClass123_6987.method1346((this.anIntArray6990[5]), aClass233_1519, -1599730439);
	this.aClass123_6987.method1342(this.anIntArray6990[7], aClass232_1523.aFloat2593, aClass232_1523.aFloat2592, aClass232_1523.aFloat2590, aClass232_1523.aFloat2591, (byte) 115);
	this.aClass123_6987.method1365(this.anIntArray6990[8], aClass217_1524.aFloat2451, aClass217_1524.aFloat2455, aClass217_1524.aFloat2454, 1153447117);
	this.aClass123_6987.method1342(this.anIntArray6990[9], aClass232_1525.aFloat2593, aClass232_1525.aFloat2592, aClass232_1525.aFloat2590, aClass232_1525.aFloat2591, (byte) 32);
	this.aClass123_6987.method1365(this.anIntArray6990[10], aClass217_1522.aFloat2451, aClass217_1522.aFloat2455, aClass217_1522.aFloat2454, -25851858);
	aClass_ra_Sub3_1520.method5392(Class187.aClass187_1907, anInt1532, anInt1533, anInt1534, anInt1514);
    }

    @Override
    public void method1514(boolean bool) {
	try {
	    if (bool)
		this.aClass123_6987.method1331(this.aClass110Array6988[1]);
	    else
		this.aClass123_6987.method1331(this.aClass110Array6988[0]);
	    this.anIntArray6990 = (this.anIntArrayArray6962[this.aClass123_6987.method1333(1072994601)]);
	    this.aClass123_6987.method1340();
	    this.aClass123_6987.method1348(this.anIntArray6990[2], 0, anInterface9_Impl2_1517, 1542479016);
	    this.aClass123_6987.method1336(this.anIntArray6990[4], this.aClass233_6977, (byte) -116);
	    this.aClass123_6987.method1346(this.anIntArray6990[5], aClass233_1519, -1599730439);
	    this.aClass123_6987.method1342(this.anIntArray6990[7], aClass232_1523.aFloat2593, aClass232_1523.aFloat2592, aClass232_1523.aFloat2590, aClass232_1523.aFloat2591, (byte) 50);
	    this.aClass123_6987.method1365(this.anIntArray6990[8], aClass217_1524.aFloat2451, aClass217_1524.aFloat2455, aClass217_1524.aFloat2454, 105504318);
	    this.aClass123_6987.method1342(this.anIntArray6990[9], aClass232_1525.aFloat2593, aClass232_1525.aFloat2592, aClass232_1525.aFloat2590, aClass232_1525.aFloat2591, (byte) 87);
	    this.aClass123_6987.method1365(this.anIntArray6990[10], aClass217_1522.aFloat2451, aClass217_1522.aFloat2455, aClass217_1522.aFloat2454, 937631726);
	    aClass_ra_Sub3_1520.method5392(Class187.aClass187_1907, anInt1532, anInt1533, anInt1534, anInt1514);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("yg.b(").append(')').toString());
	}
    }

    @Override
    public void method1515(boolean bool) {
	if (bool)
	    this.aClass123_6987.method1331(this.aClass110Array6988[1]);
	else
	    this.aClass123_6987.method1331(this.aClass110Array6988[0]);
	this.anIntArray6990 = (this.anIntArrayArray6962[this.aClass123_6987.method1333(1256833385)]);
	this.aClass123_6987.method1340();
	this.aClass123_6987.method1348(this.anIntArray6990[2], 0, anInterface9_Impl2_1517, -1297905269);
	this.aClass123_6987.method1336(this.anIntArray6990[4], this.aClass233_6977, (byte) -110);
	this.aClass123_6987.method1346((this.anIntArray6990[5]), aClass233_1519, -1599730439);
	this.aClass123_6987.method1342(this.anIntArray6990[7], aClass232_1523.aFloat2593, aClass232_1523.aFloat2592, aClass232_1523.aFloat2590, aClass232_1523.aFloat2591, (byte) 8);
	this.aClass123_6987.method1365(this.anIntArray6990[8], aClass217_1524.aFloat2451, aClass217_1524.aFloat2455, aClass217_1524.aFloat2454, 1284230441);
	this.aClass123_6987.method1342(this.anIntArray6990[9], aClass232_1525.aFloat2593, aClass232_1525.aFloat2592, aClass232_1525.aFloat2590, aClass232_1525.aFloat2591, (byte) 22);
	this.aClass123_6987.method1365(this.anIntArray6990[10], aClass217_1522.aFloat2451, aClass217_1522.aFloat2455, aClass217_1522.aFloat2454, 140507609);
	aClass_ra_Sub3_1520.method5392(Class187.aClass187_1907, anInt1532, anInt1533, anInt1534, anInt1514);
    }

    @Override
    public void method1518(int i) {
	this.aClass123_6987.method1331(this.aClass110Array6988[i + 2]);
	this.anIntArray6990 = (this.anIntArrayArray6962[this.aClass123_6987.method1333(1157195426)]);
	this.aClass123_6987.method1340();
	method1523(i, -591376513);
    }

    @Override
    public void method1516(int i) {
	this.aClass123_6987.method1331(this.aClass110Array6988[i + 12]);
	this.anIntArray6990 = (this.anIntArrayArray6962[this.aClass123_6987.method1333(1091683918)]);
	this.aClass123_6987.method1340();
	this.aClass123_6987.method1345((this.anIntArray6990[15]), aClass233_1535, (byte) -55);
	this.aClass123_6987.method1365(this.anIntArray6990[6], aClass217_1530.aFloat2451, aClass217_1530.aFloat2455, aClass217_1530.aFloat2454, 822436728);
	this.aClass123_6987.method1348(this.anIntArray6990[3], 1, anInterface9_Impl1_1518, 1019506106);
	method1523(i, -410682702);
    }

    @Override
    public void method1517(int i) {
	this.aClass123_6987.method1331(this.aClass110Array6988[i + 12]);
	this.anIntArray6990 = (this.anIntArrayArray6962[this.aClass123_6987.method1333(1368039593)]);
	this.aClass123_6987.method1340();
	this.aClass123_6987.method1345((this.anIntArray6990[15]), aClass233_1535, (byte) -88);
	this.aClass123_6987.method1365(this.anIntArray6990[6], aClass217_1530.aFloat2451, aClass217_1530.aFloat2455, aClass217_1530.aFloat2454, 852060499);
	this.aClass123_6987.method1348(this.anIntArray6990[3], 1, anInterface9_Impl1_1518, -1849462852);
	method1523(i, 108146039);
    }

    @Override
    public void method1521(int i) {
	this.aClass123_6987.method1331(this.aClass110Array6988[7 + i]);
	this.anIntArray6990 = (this.anIntArrayArray6962[this.aClass123_6987.method1333(2005581211)]);
	this.aClass123_6987.method1340();
	this.aClass123_6987.method1365(this.anIntArray6990[6], aClass217_1530.aFloat2451, aClass217_1530.aFloat2455, aClass217_1530.aFloat2454, 1321729054);
	this.aClass123_6987.method1342(this.anIntArray6990[16], aFloat1521, aFloat1513, 0.0F, 0.0F, (byte) 102);
	method1523(i, 1261826682);
    }

    public Class137_Sub1(Class_ra_Sub3 class_ra_sub3) throws Exception_Sub2 {
	super(class_ra_sub3);
	this.anIntArrayArray6962 = new int[18][17];
	method1522(-1327173096);
    }

    @Override
    public void method1503(int i) {
	try {
	    this.aClass123_6987.method1331(this.aClass110Array6988[i + 12]);
	    this.anIntArray6990 = (this.anIntArrayArray6962[this.aClass123_6987.method1333(1210039282)]);
	    this.aClass123_6987.method1340();
	    this.aClass123_6987.method1345(this.anIntArray6990[15], aClass233_1535, (byte) -116);
	    this.aClass123_6987.method1365(this.anIntArray6990[6], aClass217_1530.aFloat2451, aClass217_1530.aFloat2455, aClass217_1530.aFloat2454, -1454092733);
	    this.aClass123_6987.method1348(this.anIntArray6990[3], 1, anInterface9_Impl1_1518, 67553741);
	    method1523(i, -50147859);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("yg.k(").append(')').toString());
	}
    }

    @Override
    public void method1505(int i) {
	this.aClass123_6987.method1331(this.aClass110Array6988[7 + i]);
	this.anIntArray6990 = (this.anIntArrayArray6962[this.aClass123_6987.method1333(1300617197)]);
	this.aClass123_6987.method1340();
	this.aClass123_6987.method1365(this.anIntArray6990[6], aClass217_1530.aFloat2451, aClass217_1530.aFloat2455, aClass217_1530.aFloat2454, -1538662459);
	this.aClass123_6987.method1342(this.anIntArray6990[16], aFloat1521, aFloat1513, 0.0F, 0.0F, (byte) 124);
	method1523(i, 1244225223);
    }
}
