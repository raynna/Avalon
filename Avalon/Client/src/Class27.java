/* Class27 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class27 {
    int anInt357 = 0;
    Class47_Sub5 aClass47_Sub5_358;
    int anInt359 = 0;
    int anInt360 = 0;
    Class_ra_Sub2 aClass_ra_Sub2_361;
    Class45 aClass45_362;
    Class47[] aClass47Array363;

    boolean method400(int i) {
	return this.aClass47Array363[i].method501();
    }

    void method401(int i, int i_0_, int i_1_, boolean bool, boolean bool_2_) {
	bool_2_ &= this.aClass_ra_Sub2_361.method5224();
	if (!bool_2_ && (i == 4 || i == 8 || i == 9)) {
	    if (i == 4)
		i_1_ = i_0_;
	    i = 2;
	}
	if (i != 0 && bool)
	    i |= ~0x7fffffff;
	if (this.anInt360 != i) {
	    if (this.anInt360 != 0)
		this.aClass47Array363[this.anInt360 & 0x7fffffff].method504();
	    if (i != 0) {
		this.aClass47Array363[i & 0x7fffffff].method505(bool);
		this.aClass47Array363[i & 0x7fffffff].method518(bool);
		this.aClass47Array363[i & 0x7fffffff].method503(i_0_, i_1_);
	    }
	    this.anInt360 = i;
	    this.anInt357 = i_0_;
	    this.anInt359 = i_1_;
	} else if (this.anInt360 != 0) {
	    this.aClass47Array363[this.anInt360 & 0x7fffffff].method518(bool);
	    if (this.anInt357 != i_0_ || this.anInt359 != i_1_) {
		this.aClass47Array363[this.anInt360 & 0x7fffffff].method503(i_0_, i_1_);
		this.anInt357 = i_0_;
		this.anInt359 = i_1_;
	    }
	}
    }

    boolean method402(Class30 class30, int i) {
	if (this.anInt360 == 0)
	    return false;
	this.aClass47Array363[this.anInt360 & 0x7fffffff].method500(class30, i);
	return true;
    }

    Class27(Class_ra_Sub2 class_ra_sub2) {
	this.aClass_ra_Sub2_361 = class_ra_sub2;
	this.aClass45_362 = new Class45(class_ra_sub2);
	this.aClass47Array363 = new Class47[16];
	this.aClass47Array363[1] = new Class47_Sub3(class_ra_sub2);
	this.aClass47Array363[2] = new Class47_Sub8(class_ra_sub2, this.aClass45_362);
	this.aClass47Array363[4] = new Class47_Sub4(class_ra_sub2, this.aClass45_362);
	this.aClass47Array363[5] = new Class47_Sub6(class_ra_sub2, this.aClass45_362);
	this.aClass47Array363[6] = new Class47_Sub1(class_ra_sub2);
	this.aClass47Array363[7] = new Class47_Sub7(class_ra_sub2);
	this.aClass47Array363[3] = this.aClass47_Sub5_358 = new Class47_Sub5(class_ra_sub2);
	this.aClass47Array363[8] = new Class47_Sub9(class_ra_sub2, this.aClass45_362);
	this.aClass47Array363[9] = new Class47_Sub2(class_ra_sub2, this.aClass45_362);
	if (!this.aClass47Array363[8].method501())
	    this.aClass47Array363[8] = this.aClass47Array363[4];
	if (!this.aClass47Array363[9].method501())
	    this.aClass47Array363[9] = this.aClass47Array363[8];
    }
}
