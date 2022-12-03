/* Class213_Sub1 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class213_Sub1 extends Class213 {
    byte[] aByteArray7059;
    int anInt7060;
    int anInt7061;
    int[] anIntArray7062 = new int[this.anInt2443];

    @Override
    void method1966() {
	this.anInt7060 = 0;
	this.anInt7061 = 0;
    }

    @Override
    void method1959() {
	this.anInt7060 = 0;
	this.anInt7061 = 0;
    }

    @Override
    void method1968(int i, int i_0_) {
	this.anInt7061 += i_0_ * this.anIntArray7062[i] >> 12;
    }

    @Override
    void method1961(int i, int i_1_) {
	this.anInt7061 += i_1_ * this.anIntArray7062[i] >> 12;
    }

    void method1971(int i, byte i_2_) {
	this.aByteArray7059[this.anInt7060++] = (byte) (127 + ((i_2_ & 0xff) >> 1));
    }

    @Override
    void method1967() {
	this.anInt7061 = Math.abs(this.anInt7061);
	if (this.anInt7061 >= 4096)
	    this.anInt7061 = 4095;
	method1971(this.anInt7060++, (byte) (this.anInt7061 >> 4));
	this.anInt7061 = 0;
    }

    Class213_Sub1(int i, int i_3_, int i_4_, int i_5_, int i_6_, float f) {
	super(i, i_3_, i_4_, i_5_, i_6_);
	for (int i_7_ = 0; i_7_ < this.anInt2443; i_7_++)
	    this.anIntArray7062[i_7_] = (short) (int) (Math.pow(f, i_7_) * 4096.0);
    }

    @Override
    void method1960(int i, int i_8_) {
	this.anInt7061 += i_8_ * this.anIntArray7062[i] >> 12;
    }

    @Override
    void method1970() {
	this.anInt7061 = Math.abs(this.anInt7061);
	if (this.anInt7061 >= 4096)
	    this.anInt7061 = 4095;
	method1971(this.anInt7060++, (byte) (this.anInt7061 >> 4));
	this.anInt7061 = 0;
    }

    @Override
    void method1969(int i, int i_9_) {
	this.anInt7061 += i_9_ * this.anIntArray7062[i] >> 12;
    }
}
