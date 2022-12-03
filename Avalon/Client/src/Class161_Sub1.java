/* Class161_Sub1 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public abstract class Class161_Sub1 extends Class161 {
    Class298_Sub31_Sub1 aClass298_Sub31_Sub1_7029;
    Class298_Sub31_Sub1 aClass298_Sub31_Sub1_7030;
    static int anInt7031 = 16;
    static int anInt7032 = 4;
    protected Class123 aClass123_7033;
    Class298_Sub31_Sub1 aClass298_Sub31_Sub1_7034;
    float[] aFloatArray7035 = new float[3];
    Class298_Sub31_Sub1 aClass298_Sub31_Sub1_7036;
    Class298_Sub31_Sub1 aClass298_Sub31_Sub1_7037;
    Class298_Sub31_Sub1 aClass298_Sub31_Sub1_7038;
    Class298_Sub31_Sub1 aClass298_Sub31_Sub1_7039;
    Class298_Sub31_Sub1 aClass298_Sub31_Sub1_7040;
    Class298_Sub31_Sub1 aClass298_Sub31_Sub1_7041;
    Class298_Sub31_Sub1 aClass298_Sub31_Sub1_7042;
    Class298_Sub31_Sub1 aClass298_Sub31_Sub1_7043;
    Class298_Sub31_Sub1 aClass298_Sub31_Sub1_7044;
    Class298_Sub31_Sub1 aClass298_Sub31_Sub1_7045;
    static int anInt7046 = 1;
    static int anInt7047 = 2;
    Class298_Sub31_Sub1 aClass298_Sub31_Sub1_7048;
    int[] anIntArray7049;
    static int anInt7050 = 8;
    static int anInt7051 = 32;
    Class298_Sub31_Sub1 aClass298_Sub31_Sub1_7052;
    static int anInt7053 = 128;
    int anInt7054;
    Class298_Sub31_Sub1 aClass298_Sub31_Sub1_7055;
    static Class233 aClass233_7056 = new Class233();
    Class233[] aClass233Array7057;
    static int anInt7058 = 64;

    boolean method1725(String string) throws Exception_Sub2 {
	aClass123_7033 = aClass_ra_Sub3_1648.method5297(string);
	if (aClass123_7033 == null)
	    throw new Exception_Sub2("");
	this.aClass298_Sub31_Sub1_7030 = aClass123_7033.method1351("textureMatrix", (byte) 108);
	this.aClass298_Sub31_Sub1_7055 = aClass123_7033.method1351("modelMatrix", (byte) 117);
	this.aClass298_Sub31_Sub1_7029 = aClass123_7033.method1351("viewMatrix", (byte) 116);
	this.aClass298_Sub31_Sub1_7048 = aClass123_7033.method1351("projectionMatrix", (byte) 107);
	this.aClass298_Sub31_Sub1_7043 = aClass123_7033.method1351("modelViewMatrix", (byte) 87);
	this.aClass298_Sub31_Sub1_7036 = aClass123_7033.method1351("modelViewProjectionMatrix", (byte) 81);
	this.aClass298_Sub31_Sub1_7037 = aClass123_7033.method1351("viewProjectionMatrix", (byte) 126);
	this.aClass298_Sub31_Sub1_7041 = aClass123_7033.method1351("ambientColour", (byte) 114);
	this.aClass298_Sub31_Sub1_7044 = aClass123_7033.method1351("sunDirection", (byte) 73);
	this.aClass298_Sub31_Sub1_7042 = aClass123_7033.method1351("sunColour", (byte) 107);
	this.aClass298_Sub31_Sub1_7045 = aClass123_7033.method1351("antiSunColour", (byte) 122);
	this.aClass298_Sub31_Sub1_7052 = aClass123_7033.method1351("sunExponent", (byte) 80);
	this.aClass298_Sub31_Sub1_7038 = aClass123_7033.method1351("eyePosition", (byte) 89);
	this.aClass298_Sub31_Sub1_7039 = aClass123_7033.method1351("eyePositionOS", (byte) 79);
	this.aClass298_Sub31_Sub1_7040 = aClass123_7033.method1351("sunDirectionOS", (byte) 100);
	if (!method1726()) {
	    aClass123_7033 = null;
	    this.aClass298_Sub31_Sub1_7030 = null;
	    this.aClass298_Sub31_Sub1_7055 = null;
	    this.aClass298_Sub31_Sub1_7029 = null;
	    this.aClass298_Sub31_Sub1_7048 = null;
	    this.aClass298_Sub31_Sub1_7043 = null;
	    this.aClass298_Sub31_Sub1_7036 = null;
	    this.aClass298_Sub31_Sub1_7037 = null;
	    this.aClass298_Sub31_Sub1_7041 = null;
	    this.aClass298_Sub31_Sub1_7044 = null;
	    this.aClass298_Sub31_Sub1_7042 = null;
	    this.aClass298_Sub31_Sub1_7045 = null;
	    this.aClass298_Sub31_Sub1_7052 = null;
	    this.aClass298_Sub31_Sub1_7038 = null;
	    this.aClass298_Sub31_Sub1_7039 = null;
	    this.aClass298_Sub31_Sub1_7040 = null;
	    return false;
	}
	if (aClass123_7033.method1332(1789586042) == null) {
	    Class110 class110 = aClass123_7033.method1330(-1437671487);
	    if (class110 == null)
		throw new Exception_Sub2(new StringBuilder().append(aClass123_7033.method1325(232845756)).append("").toString());
	    aClass123_7033.method1331(class110);
	}
	this.anInt7054 = aClass123_7033.method1328(-1467018991);
	this.anIntArray7049 = new int[this.anInt7054];
	return true;
    }

    abstract boolean method1726() throws Exception_Sub2;

    @Override
    void method1713() {
	aClass123_7033.method1340();
	this.anIntArray7049[aClass123_7033.method1333(1713213788)] = -1;
	method1733(0);
	method1735();
    }

    Class161_Sub1(Class_ra_Sub3 class_ra_sub3) {
	super(class_ra_sub3);
	new Class222();
	this.aClass233Array7057 = new Class233[aClass_ra_Sub3_1648.anInt8347];
	for (int i = 0; i < aClass_ra_Sub3_1648.anInt8347; i++)
	    this.aClass233Array7057[i] = new Class233();
    }

    void method1727(Class233 class233) {
	if (aClass123_7033 != null && aClass123_7033.method1374() && aClass_ra_Sub3_1648.method5386() == 0) {
	    int i = aClass123_7033.method1333(1799039973);
	    if ((this.anIntArray7049[i] & 0x80) != 0 && this.aClass298_Sub31_Sub1_7030 != null) {
		aClass123_7033.method1337((this.aClass298_Sub31_Sub1_7030), class233, (byte) 61);
		this.anIntArray7049[i] &= ~0x80;
	    }
	}
    }

    @Override
    void method1718() {
	method1731(Class222.aClass222_2504);
    }

    @Override
    void method1714() {
	if (aClass123_7033 != null)
	    aClass123_7033.method1372();
    }

    void method1728(int i, Class222 class222) {
	if (this.aClass298_Sub31_Sub1_7040 != null) {
	    float[] fs = { aClass_ra_Sub3_1648.aFloatArray8283[0], aClass_ra_Sub3_1648.aFloatArray8283[1], aClass_ra_Sub3_1648.aFloatArray8283[2] };
	    class222.method2075(fs);
	    aClass123_7033.method1334((this.aClass298_Sub31_Sub1_7040), fs[0], fs[1], fs[2], 1498331649);
	}
    }

    void method1729(boolean bool) {
	if (aClass123_7033 != null && aClass123_7033.method1374()) {
	    if (this.aClass298_Sub31_Sub1_7041 != null)
		aClass123_7033.method1334((this.aClass298_Sub31_Sub1_7041), (aClass_ra_Sub3_1648.aFloat8362 * aClass_ra_Sub3_1648.aFloat8291), (aClass_ra_Sub3_1648.aFloat8289 * aClass_ra_Sub3_1648.aFloat8291), (aClass_ra_Sub3_1648.aFloat8290 * aClass_ra_Sub3_1648.aFloat8291), 1720867292);
	    if (this.aClass298_Sub31_Sub1_7044 != null)
		aClass123_7033.method1334(this.aClass298_Sub31_Sub1_7044, aClass_ra_Sub3_1648.aFloatArray8283[0], aClass_ra_Sub3_1648.aFloatArray8283[1], aClass_ra_Sub3_1648.aFloatArray8283[2], 1461810975);
	    if (this.aClass298_Sub31_Sub1_7042 != null)
		aClass123_7033.method1334((this.aClass298_Sub31_Sub1_7042), (aClass_ra_Sub3_1648.aFloat8362 * aClass_ra_Sub3_1648.aFloat8364), (aClass_ra_Sub3_1648.aFloat8289 * aClass_ra_Sub3_1648.aFloat8364), (aClass_ra_Sub3_1648.aFloat8290 * aClass_ra_Sub3_1648.aFloat8364), 1139320433);
	    if (this.aClass298_Sub31_Sub1_7045 != null)
		aClass123_7033.method1334((this.aClass298_Sub31_Sub1_7045), (-aClass_ra_Sub3_1648.aFloat8362 * aClass_ra_Sub3_1648.aFloat8293), (-aClass_ra_Sub3_1648.aFloat8289 * aClass_ra_Sub3_1648.aFloat8293), (-aClass_ra_Sub3_1648.aFloat8290 * aClass_ra_Sub3_1648.aFloat8293), 1046940970);
	    if (this.aClass298_Sub31_Sub1_7052 != null)
		aClass123_7033.method1335((this.aClass298_Sub31_Sub1_7052), 64.0F + Math.abs(aClass_ra_Sub3_1648.aFloatArray8283[1]) * 928.0F, 1825088849);
	}
    }

    @Override
    void method1723() {
	/* empty */
    }

    void method1730(int i) {
	for (int i_0_ = 0; i_0_ < this.anInt7054; i_0_++)
	    this.anIntArray7049[i_0_] |= 128 << i;
    }

    void method1731(Class222 class222) {
	Class233 class233 = aClass_ra_Sub3_1648.aClass233_8236;
	class233.method2145(class222);
	int i = aClass123_7033.method1333(1366764376);
	if (this.aClass298_Sub31_Sub1_7055 != null) {
	    aClass123_7033.method1368((this.aClass298_Sub31_Sub1_7055), class233, 600012267);
	    this.anIntArray7049[i] &= ~0x1;
	}
	if (this.aClass298_Sub31_Sub1_7043 != null) {
	    aClass233_7056.method2143(class233, aClass_ra_Sub3_1648.method5328());
	    aClass123_7033.method1368((this.aClass298_Sub31_Sub1_7043), aClass233_7056, 600012267);
	    this.anIntArray7049[i] &= ~0x10;
	}
	if (this.aClass298_Sub31_Sub1_7036 != null) {
	    aClass233_7056.method2143(class233, aClass_ra_Sub3_1648.method5334());
	    aClass123_7033.method1368((this.aClass298_Sub31_Sub1_7036), aClass233_7056, 600012267);
	    this.anIntArray7049[i] &= ~0x20;
	}
	method1737(i, class222);
	method1728(i, class222);
    }

    void method1732() {
	if (aClass123_7033 != null && aClass123_7033.method1374()) {
	    int i = aClass123_7033.method1333(1999891583);
	    int i_1_ = this.anIntArray7049[i];
	    if ((i_1_ & 0x1) != 0 && this.aClass298_Sub31_Sub1_7055 != null)
		aClass123_7033.method1368((this.aClass298_Sub31_Sub1_7055), aClass_ra_Sub3_1648.method5431(), 600012267);
	    if ((i_1_ & 0x2) != 0) {
		if (this.aClass298_Sub31_Sub1_7029 != null)
		    aClass123_7033.method1368((this.aClass298_Sub31_Sub1_7029), aClass_ra_Sub3_1648.method5328(), 600012267);
		if (this.aClass298_Sub31_Sub1_7038 != null) {
		    this.aFloatArray7035[0] = 0.0F;
		    this.aFloatArray7035[1] = 0.0F;
		    this.aFloatArray7035[2] = 0.0F;
		    aClass_ra_Sub3_1648.method5044().method2066(this.aFloatArray7035[0], this.aFloatArray7035[1], this.aFloatArray7035[2], this.aFloatArray7035);
		    aClass123_7033.method1334(this.aClass298_Sub31_Sub1_7038, this.aFloatArray7035[0], this.aFloatArray7035[1], this.aFloatArray7035[2], 1289241490);
		}
	    }
	    if ((i_1_ & 0x10) != 0 && this.aClass298_Sub31_Sub1_7043 != null)
		aClass123_7033.method1368((this.aClass298_Sub31_Sub1_7043), aClass_ra_Sub3_1648.method5387(), 600012267);
	    if ((i_1_ & 0x8) != 0 && this.aClass298_Sub31_Sub1_7034 != null)
		aClass123_7033.method1368((this.aClass298_Sub31_Sub1_7034), aClass_ra_Sub3_1648.method5329(), 600012267);
	    if ((i_1_ & 0x4) != 0 && this.aClass298_Sub31_Sub1_7048 != null)
		aClass123_7033.method1368((this.aClass298_Sub31_Sub1_7048), aClass_ra_Sub3_1648.method5301(), 600012267);
	    if ((i_1_ & 0x20) != 0 && this.aClass298_Sub31_Sub1_7036 != null) {
		aClass233_7056.method2143(aClass_ra_Sub3_1648.method5387(), aClass_ra_Sub3_1648.method5301());
		aClass123_7033.method1368((this.aClass298_Sub31_Sub1_7036), aClass233_7056, 600012267);
	    }
	    if ((i_1_ & 0x40) != 0 && this.aClass298_Sub31_Sub1_7037 != null) {
		aClass233_7056.method2143(aClass_ra_Sub3_1648.method5328(), aClass_ra_Sub3_1648.method5301());
		aClass123_7033.method1368((this.aClass298_Sub31_Sub1_7037), aClass233_7056, 600012267);
	    }
	    if (aClass_ra_Sub3_1648.method5386() == 0 && (this.anIntArray7049[i] & 0x80) != 0 && this.aClass298_Sub31_Sub1_7030 != null) {
		aClass123_7033.method1337((this.aClass298_Sub31_Sub1_7030), aClass_ra_Sub3_1648.method5316(), (byte) 14);
		this.anIntArray7049[i] &= ~0x80;
	    }
	    this.anIntArray7049[i] = 0;
	}
    }

    void method1733(int i) {
	this.aClass233Array7057[0].method2172();
	method1730(i);
    }

    @Override
    void method1719() {
	aClass123_7033.method1340();
	this.anIntArray7049[aClass123_7033.method1333(1318478189)] = -1;
	method1733(0);
	method1735();
    }

    @Override
    void method1720() {
	if (aClass123_7033 != null)
	    aClass123_7033.method1372();
    }

    @Override
    void method1717() {
	if (aClass123_7033 != null)
	    aClass123_7033.method1372();
    }

    @Override
    void method1721() {
	if (aClass123_7033 != null)
	    aClass123_7033.method1372();
    }

    void method1734() {
	/* empty */
    }

    void method1735() {
	method1729(aClass_ra_Sub3_1648.aBoolean8264);
	method1727(aClass_ra_Sub3_1648.method5316());
	method1732();
	method1723();
    }

    abstract boolean method1736() throws Exception_Sub2;

    void method1737(int i, Class222 class222) {
	if (this.aClass298_Sub31_Sub1_7039 != null) {
	    Class233 class233 = aClass_ra_Sub3_1648.method5329();
	    float[] fs = { class233.aFloatArray2594[12], class233.aFloatArray2594[13], class233.aFloatArray2594[14] };
	    float[] fs_2_ = { 0.0F, 0.0F, 0.0F };
	    class233.method2168(fs_2_);
	    float[] fs_3_ = { 0.0F, 0.0F, 0.0F };
	    aClass_ra_Sub3_1648.method5044().method2077(0.0F, 0.0F, 0.0F, fs_3_);
	    class222.method2069(fs);
	    aClass123_7033.method1334((this.aClass298_Sub31_Sub1_7039), fs[0], fs[1], fs[2], 1346905508);
	}
    }

    @Override
    void method1722() {
	method1731(Class222.aClass222_2504);
    }

    @Override
    void method1724() {
	method1731(Class222.aClass222_2504);
    }
}
