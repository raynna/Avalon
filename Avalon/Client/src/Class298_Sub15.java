/* Class298_Sub15 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class298_Sub15 extends Class298 {
    public static int anInt7266 = 3;
    public static int anInt7267 = 1;
    int anInt7268;
    public static int anInt7269 = 0;
    public static int anInt7270 = 5;
    static int anInt7271 = 6;
    public static int anInt7272 = 7;
    public static int anInt7273 = 8;
    public static int anInt7274 = 9;
    boolean[] aBooleanArray7275;
    int anInt7276;
    public static int anInt7277 = 10;
    int[] anIntArray7278;
    int[][] anIntArrayArray7279;
    int[] anIntArray7280;
    public static int anInt7281 = 2;

    Class298_Sub15(int i, byte[] is) {
	this.anInt7276 = 1362718155 * i;
	RsByteBuffer class298_sub53 = new RsByteBuffer(is);
	this.anInt7268 = class298_sub53.readUnsignedByte() * -1914825713;
	this.anIntArray7278 = new int[92429039 * this.anInt7268];
	this.anIntArrayArray7279 = new int[92429039 * this.anInt7268][];
	this.aBooleanArray7275 = new boolean[this.anInt7268 * 92429039];
	this.anIntArray7280 = new int[92429039 * this.anInt7268];
	for (int i_0_ = 0; i_0_ < 92429039 * this.anInt7268; i_0_++) {
	    this.anIntArray7278[i_0_] = class298_sub53.readUnsignedByte();
	    if (this.anIntArray7278[i_0_] == 6)
		this.anIntArray7278[i_0_] = 2;
	}
	for (int i_1_ = 0; i_1_ < this.anInt7268 * 92429039; i_1_++)
	    this.aBooleanArray7275[i_1_] = class298_sub53.readUnsignedByte() == 1;
	for (int i_2_ = 0; i_2_ < this.anInt7268 * 92429039; i_2_++)
	    this.anIntArray7280[i_2_] = class298_sub53.readUnsignedShort();
	for (int i_3_ = 0; i_3_ < this.anInt7268 * 92429039; i_3_++)
	    this.anIntArrayArray7279[i_3_] = new int[class298_sub53.readUnsignedByte()];
	for (int i_4_ = 0; i_4_ < this.anInt7268 * 92429039; i_4_++) {
	    for (int i_5_ = 0; (i_5_ < this.anIntArrayArray7279[i_4_].length); i_5_++)
		this.anIntArrayArray7279[i_4_][i_5_] = class298_sub53.readUnsignedByte();
	}
    }

    static final void method2909(Class403 class403, int i) {
	try {
	    int i_6_ = (class403.anIntArray5244[((class403.anInt5239 -= -391880689) * 681479919)]);
	    class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919 - 1)] = client.aBooleanArray8957[i_6_] ? 1 : 0;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aax.wu(").append(')').toString());
	}
    }
}
