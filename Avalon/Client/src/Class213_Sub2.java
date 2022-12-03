/* Class213_Sub2 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class213_Sub2 extends Class213 {
    int anInt7063;
    byte[] aByteArray7064;
    int anInt7065;
    int anInt7066;
    int anInt7067;
    int anInt7068;
    int anInt7069;
    int anInt7070;
    int anInt7071;

    @Override
    void method1961(int i, int i_0_) {
	if (i != 0) {
	    this.anInt7065 = (this.anInt7066 * this.anInt7067) >> 12;
	    if (this.anInt7065 < 0)
		this.anInt7065 = 0;
	    else if (this.anInt7065 > 4096)
		this.anInt7065 = 4096;
	    this.anInt7066 = this.anInt7070 - (i_0_ < 0 ? -i_0_ : i_0_);
	    this.anInt7066 = (this.anInt7066 * this.anInt7066) >> 12;
	    this.anInt7066 = (this.anInt7066 * this.anInt7065) >> 12;
	    this.anInt7068 += (this.anInt7066 * this.anInt7069) >> 12;
	    this.anInt7069 = (this.anInt7069 * this.anInt7063) >> 12;
	} else {
	    this.anInt7066 = this.anInt7070 - (i_0_ < 0 ? -i_0_ : i_0_);
	    this.anInt7066 = (this.anInt7066 * this.anInt7066) >> 12;
	    this.anInt7065 = 4096;
	    this.anInt7068 = this.anInt7066;
	}
    }

    Class213_Sub2(int i, int i_1_, int i_2_, int i_3_, int i_4_, float f, float f_5_, float f_6_) {
	super(i, i_1_, i_2_, i_3_, i_4_);
	this.anInt7067 = (int) (f_6_ * 4096.0F);
	this.anInt7070 = (int) (f_5_ * 4096.0F);
	this.anInt7069 = this.anInt7063 = (int) (Math.pow(0.5, -f) * 4096.0);
    }

    @Override
    void method1970() {
	this.anInt7069 = this.anInt7063;
	this.anInt7068 >>= 4;
	if (this.anInt7068 < 0)
	    this.anInt7068 = 0;
	else if (this.anInt7068 > 255)
	    this.anInt7068 = 255;
	method1976(this.anInt7071++, (byte) this.anInt7068);
	this.anInt7068 = 0;
    }

    void method1976(int i, byte i_7_) {
	this.aByteArray7064[i] = i_7_;
    }

    @Override
    void method1959() {
	this.anInt7071 = 0;
	this.anInt7068 = 0;
    }

    @Override
    void method1966() {
	this.anInt7071 = 0;
	this.anInt7068 = 0;
    }

    @Override
    void method1967() {
	this.anInt7069 = this.anInt7063;
	this.anInt7068 >>= 4;
	if (this.anInt7068 < 0)
	    this.anInt7068 = 0;
	else if (this.anInt7068 > 255)
	    this.anInt7068 = 255;
	method1976(this.anInt7071++, (byte) this.anInt7068);
	this.anInt7068 = 0;
    }

    @Override
    void method1960(int i, int i_8_) {
	if (i != 0) {
	    this.anInt7065 = (this.anInt7066 * this.anInt7067) >> 12;
	    if (this.anInt7065 < 0)
		this.anInt7065 = 0;
	    else if (this.anInt7065 > 4096)
		this.anInt7065 = 4096;
	    this.anInt7066 = this.anInt7070 - (i_8_ < 0 ? -i_8_ : i_8_);
	    this.anInt7066 = (this.anInt7066 * this.anInt7066) >> 12;
	    this.anInt7066 = (this.anInt7066 * this.anInt7065) >> 12;
	    this.anInt7068 += (this.anInt7066 * this.anInt7069) >> 12;
	    this.anInt7069 = (this.anInt7069 * this.anInt7063) >> 12;
	} else {
	    this.anInt7066 = this.anInt7070 - (i_8_ < 0 ? -i_8_ : i_8_);
	    this.anInt7066 = (this.anInt7066 * this.anInt7066) >> 12;
	    this.anInt7065 = 4096;
	    this.anInt7068 = this.anInt7066;
	}
    }

    @Override
    void method1968(int i, int i_9_) {
	if (i != 0) {
	    this.anInt7065 = (this.anInt7066 * this.anInt7067) >> 12;
	    if (this.anInt7065 < 0)
		this.anInt7065 = 0;
	    else if (this.anInt7065 > 4096)
		this.anInt7065 = 4096;
	    this.anInt7066 = this.anInt7070 - (i_9_ < 0 ? -i_9_ : i_9_);
	    this.anInt7066 = (this.anInt7066 * this.anInt7066) >> 12;
	    this.anInt7066 = (this.anInt7066 * this.anInt7065) >> 12;
	    this.anInt7068 += (this.anInt7066 * this.anInt7069) >> 12;
	    this.anInt7069 = (this.anInt7069 * this.anInt7063) >> 12;
	} else {
	    this.anInt7066 = this.anInt7070 - (i_9_ < 0 ? -i_9_ : i_9_);
	    this.anInt7066 = (this.anInt7066 * this.anInt7066) >> 12;
	    this.anInt7065 = 4096;
	    this.anInt7068 = this.anInt7066;
	}
    }

    @Override
    void method1969(int i, int i_10_) {
	if (i != 0) {
	    this.anInt7065 = (this.anInt7066 * this.anInt7067) >> 12;
	    if (this.anInt7065 < 0)
		this.anInt7065 = 0;
	    else if (this.anInt7065 > 4096)
		this.anInt7065 = 4096;
	    this.anInt7066 = this.anInt7070 - (i_10_ < 0 ? -i_10_ : i_10_);
	    this.anInt7066 = (this.anInt7066 * this.anInt7066) >> 12;
	    this.anInt7066 = (this.anInt7066 * this.anInt7065) >> 12;
	    this.anInt7068 += (this.anInt7066 * this.anInt7069) >> 12;
	    this.anInt7069 = (this.anInt7069 * this.anInt7063) >> 12;
	} else {
	    this.anInt7066 = this.anInt7070 - (i_10_ < 0 ? -i_10_ : i_10_);
	    this.anInt7066 = (this.anInt7066 * this.anInt7066) >> 12;
	    this.anInt7065 = 4096;
	    this.anInt7068 = this.anInt7066;
	}
    }
}
