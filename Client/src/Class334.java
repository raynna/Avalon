/* Class334 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class334 {
    int anInt3606;
    int anInt3607;
    int anInt3608;
    int anInt3609;
    int anInt3610;
    int anInt3611;
    int anInt3612;
    int anInt3613;
    int anInt3614;
    int anInt3615;

    Class334(int i, int i_0_, int i_1_, int i_2_, int i_3_, int i_4_, int i_5_, int i_6_, int i_7_, int i_8_) {
	this.anInt3611 = i;
	this.anInt3606 = i_0_;
	this.anInt3608 = i_1_;
	this.anInt3609 = i_2_ * i_2_;
	this.anInt3610 = this.anInt3611 + i_3_;
	this.anInt3614 = this.anInt3611 + i_4_;
	this.anInt3612 = this.anInt3606 + i_5_;
	this.anInt3607 = this.anInt3606 + i_6_;
	this.anInt3613 = this.anInt3608 + i_7_;
	this.anInt3615 = this.anInt3608 + i_8_;
    }

    public boolean method4082(int i, int i_9_, int i_10_) {
	if (i < this.anInt3610 || i > this.anInt3614)
	    return false;
	if (i_9_ < this.anInt3612 || i_9_ > this.anInt3607)
	    return false;
	if (i_10_ < this.anInt3613 || i_10_ > this.anInt3615)
	    return false;
	int i_11_ = i - this.anInt3611;
	int i_12_ = i_10_ - this.anInt3608;
	return i_11_ * i_11_ + i_12_ * i_12_ < this.anInt3609;
    }

    void method4083(int i, int i_13_, int i_14_, int i_15_, int i_16_, int i_17_, int i_18_, int i_19_, int i_20_, int i_21_) {
	this.anInt3611 = i;
	this.anInt3606 = i_13_;
	this.anInt3608 = i_14_;
	this.anInt3609 = i_15_ * i_15_;
	this.anInt3610 = this.anInt3611 + i_16_;
	this.anInt3614 = this.anInt3611 + i_17_;
	this.anInt3612 = this.anInt3606 + i_18_;
	this.anInt3607 = this.anInt3606 + i_19_;
	this.anInt3613 = this.anInt3608 + i_20_;
	this.anInt3615 = this.anInt3608 + i_21_;
    }
}
