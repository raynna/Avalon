/* Class30_Sub2_Sub1 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class30_Sub2_Sub1 extends Class30_Sub2 {
    boolean aBoolean9046;
    int anInt9047;
    int anInt9048;
    float aFloat9049;
    boolean aBoolean9050;
    float aFloat9051;

    static Class30_Sub2_Sub1 method425(Class_ra_Sub2 class_ra_sub2, int i, int i_0_, boolean bool, int[] is, int i_1_, int i_2_) {
	if (class_ra_sub2.aBoolean8031 || (AccountCreationStage.method6152(i, 1860235026) && AccountCreationStage.method6152(i_0_, 2077876811)))
	    return new Class30_Sub2_Sub1(class_ra_sub2, 3553, i, i_0_, bool, is, i_1_, i_2_);
	if (class_ra_sub2.aBoolean8179)
	    return new Class30_Sub2_Sub1(class_ra_sub2, 34037, i, i_0_, bool, is, i_1_, i_2_);
	return new Class30_Sub2_Sub1(class_ra_sub2, i, i_0_, Class416.method5590(i, (byte) 16), Class416.method5590(i_0_, (byte) 16), is);
    }

    static Class30_Sub2_Sub1 method426(Class_ra_Sub2 class_ra_sub2, Class55 class55, Class77 class77, int i, int i_3_) {
	if (class_ra_sub2.aBoolean8031 || (AccountCreationStage.method6152(i, 2054031633) && AccountCreationStage.method6152(i_3_, 1814052546)))
	    return new Class30_Sub2_Sub1(class_ra_sub2, 3553, class55, class77, i, i_3_);
	if (class_ra_sub2.aBoolean8179)
	    return new Class30_Sub2_Sub1(class_ra_sub2, 34037, class55, class77, i, i_3_);
	return new Class30_Sub2_Sub1(class_ra_sub2, class55, class77, i, i_3_, Class416.method5590(i, (byte) 16), Class416.method5590(i_3_, (byte) 16));
    }

    static Class30_Sub2_Sub1 method427(Class_ra_Sub2 class_ra_sub2, int i, int i_4_, int i_5_, int i_6_) {
	if (class_ra_sub2.aBoolean8031 || (AccountCreationStage.method6152(i_5_, 1814986255) && AccountCreationStage.method6152(i_6_, 2130033438)))
	    return new Class30_Sub2_Sub1(class_ra_sub2, 3553, i, i_4_, i_5_, i_6_, true);
	if (class_ra_sub2.aBoolean8179)
	    return new Class30_Sub2_Sub1(class_ra_sub2, 34037, i, i_4_, i_5_, i_6_, true);
	return new Class30_Sub2_Sub1(class_ra_sub2, i, i_4_, i_5_, i_6_, Class416.method5590(i_5_, (byte) 16), Class416.method5590(i_6_, (byte) 16), true);
    }

    Class30_Sub2_Sub1(Class_ra_Sub2 class_ra_sub2, int i, int i_7_, int i_8_, boolean bool, int[] is, int i_9_, int i_10_) {
	super(class_ra_sub2, i, i_7_, i_8_, bool, is, i_9_, i_10_, true);
	this.anInt9048 = i_7_;
	this.anInt9047 = i_8_;
	if (this.anInt372 == 34037) {
	    this.aFloat9051 = i_8_;
	    this.aFloat9049 = i_7_;
	    this.aBoolean9050 = false;
	} else {
	    this.aFloat9051 = 1.0F;
	    this.aFloat9049 = 1.0F;
	    this.aBoolean9050 = true;
	}
	this.aBoolean9046 = false;
    }

    Class30_Sub2_Sub1(Class_ra_Sub2 class_ra_sub2, int i, Class55 class55, Class77 class77, int i_11_, int i_12_, boolean bool, byte[] is, Class55 class55_13_) {
	super(class_ra_sub2, i, class55, class77, i_11_, i_12_, bool, is, class55_13_, true);
	this.anInt9048 = i_11_;
	this.anInt9047 = i_12_;
	if (this.anInt372 == 34037) {
	    this.aFloat9051 = i_12_;
	    this.aFloat9049 = i_11_;
	    this.aBoolean9050 = false;
	} else {
	    this.aFloat9051 = 1.0F;
	    this.aFloat9049 = 1.0F;
	    this.aBoolean9050 = true;
	}
	this.aBoolean9046 = false;
    }

    Class30_Sub2_Sub1(Class_ra_Sub2 class_ra_sub2, int i, int i_14_, int i_15_, int i_16_, int i_17_, boolean bool) {
	super(class_ra_sub2, i, i_14_, i_15_, i_16_, i_17_);
	this.anInt9048 = i_16_;
	this.anInt9047 = i_17_;
	if (this.anInt372 == 34037) {
	    this.aFloat9051 = i_17_;
	    this.aFloat9049 = i_16_;
	    this.aBoolean9050 = false;
	} else {
	    this.aFloat9051 = 1.0F;
	    this.aFloat9049 = 1.0F;
	    this.aBoolean9050 = true;
	}
	this.aBoolean9046 = false;
    }

    Class30_Sub2_Sub1(Class_ra_Sub2 class_ra_sub2, int i, int i_18_, int i_19_, int i_20_, int[] is) {
	super(class_ra_sub2, 3553, Class55.aClass55_557, Class77.aClass77_717, i_19_, i_20_);
	this.anInt9048 = i;
	this.anInt9047 = i_18_;
	method422(0, i_20_ - i_18_, i, i_18_, is, 0, 0, true);
	this.aFloat9051 = (float) i_18_ / (float) i_20_;
	this.aFloat9049 = (float) i / (float) i_19_;
	this.aBoolean9050 = false;
	this.aBoolean9046 = true;
	method420(false, false);
    }

    Class30_Sub2_Sub1(Class_ra_Sub2 class_ra_sub2, Class55 class55, Class77 class77, int i, int i_21_, int i_22_, int i_23_, byte[] is, Class55 class55_24_) {
	super(class_ra_sub2, 3553, class55, class77, i_22_, i_23_);
	this.anInt9048 = i;
	this.anInt9047 = i_21_;
	method421(0, i_23_ - i_21_, i, i_21_, is, class55_24_, 0, 0, true);
	this.aFloat9051 = (float) i_21_ / (float) i_23_;
	this.aFloat9049 = (float) i / (float) i_22_;
	this.aBoolean9050 = false;
	this.aBoolean9046 = true;
	method420(false, false);
    }

    Class30_Sub2_Sub1(Class_ra_Sub2 class_ra_sub2, int i, int i_25_, int i_26_, int i_27_, int i_28_, int i_29_, boolean bool) {
	super(class_ra_sub2, 3553, i, i_25_, i_28_, i_29_);
	this.anInt9048 = i_26_;
	this.anInt9047 = i_27_;
	this.aFloat9051 = (float) i_27_ / (float) i_29_;
	this.aFloat9049 = (float) i_26_ / (float) i_28_;
	this.aBoolean9050 = false;
	this.aBoolean9046 = true;
	method420(false, false);
    }

    @Override
    void method410(boolean bool) {
	super.method410(bool && !this.aBoolean9046);
    }

    Class30_Sub2_Sub1(Class_ra_Sub2 class_ra_sub2, Class55 class55, Class77 class77, int i, int i_30_, int i_31_, int i_32_) {
	super(class_ra_sub2, 3553, class55, class77, i_31_, i_32_);
	this.anInt9048 = i;
	this.anInt9047 = i_30_;
	this.aFloat9051 = (float) i_30_ / (float) i_32_;
	this.aFloat9049 = (float) i / (float) i_31_;
	this.aBoolean9050 = false;
	this.aBoolean9046 = true;
	method420(false, false);
    }

    static Class30_Sub2_Sub1 method428(Class_ra_Sub2 class_ra_sub2, Class55 class55, Class77 class77, int i, int i_33_, boolean bool, byte[] is, Class55 class55_34_) {
	if (class_ra_sub2.aBoolean8031 || (AccountCreationStage.method6152(i, 2005734954) && AccountCreationStage.method6152(i_33_, 1816892397)))
	    return new Class30_Sub2_Sub1(class_ra_sub2, 3553, class55, class77, i, i_33_, bool, is, class55_34_);
	if (class_ra_sub2.aBoolean8179)
	    return new Class30_Sub2_Sub1(class_ra_sub2, 34037, class55, class77, i, i_33_, bool, is, class55_34_);
	return new Class30_Sub2_Sub1(class_ra_sub2, class55, class77, i, i_33_, Class416.method5590(i, (byte) 16), Class416.method5590(i_33_, (byte) 16), is, class55_34_);
    }

    Class30_Sub2_Sub1(Class_ra_Sub2 class_ra_sub2, int i, Class55 class55, Class77 class77, int i_35_, int i_36_) {
	super(class_ra_sub2, i, class55, class77, i_35_, i_36_);
	this.anInt9048 = i_35_;
	this.anInt9047 = i_36_;
	if (this.anInt372 == 34037) {
	    this.aFloat9051 = i_36_;
	    this.aFloat9049 = i_35_;
	    this.aBoolean9050 = false;
	} else {
	    this.aFloat9051 = 1.0F;
	    this.aFloat9049 = 1.0F;
	    this.aBoolean9050 = true;
	}
	this.aBoolean9046 = false;
    }

    void method429(boolean bool) {
	super.method410(bool && !this.aBoolean9046);
    }
}
