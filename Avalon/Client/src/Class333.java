/* Class333 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class333 {
    int[] anIntArray3576;
    boolean aBoolean3577;
    int[] anIntArray3578;
    static int anInt3579 = 102;
    Class331 aClass331_3580;
    Class332 aClass332_3581;
    int[] anIntArray3582;
    public boolean aBoolean3583 = true;
    int anInt3584;
    int[][][] anIntArrayArrayArray3585;
    int anInt3586;
    Class328[] aClass328Array3587;
    int anInt3588;
    int[] anIntArray3589;
    int anInt3590;
    Class328[] aClass328Array3591;
    int anInt3592;
    Class328[] aClass328Array3593;
    static float aFloat3594 = 3.0F;
    int[] anIntArray3595;
    int[] anIntArray3596;
    Class328[] aClass328Array3597;
    boolean aBoolean3598;
    int[] anIntArray3599;
    int anInt3600;
    float[] aFloatArray3601;
    static GraphicsToolkit aClass_ra3602;
    int anInt3603;
    int anInt3604;
    boolean aBoolean3605;

    final boolean method4066(int i, int i_0_, int i_1_, int i_2_, int i_3_, int i_4_, int i_5_, int i_6_, int i_7_) {
	if (!method4078(i, i_0_, i_1_))
	    return false;
	i = (int) this.aFloatArray3601[0];
	i_0_ = (int) this.aFloatArray3601[1];
	i_1_ = (int) this.aFloatArray3601[2];
	if (!method4078(i_2_, i_3_, i_4_))
	    return false;
	i_2_ = (int) this.aFloatArray3601[0];
	i_3_ = (int) this.aFloatArray3601[1];
	i_4_ = (int) this.aFloatArray3601[2];
	if (!method4078(i_5_, i_6_, i_7_))
	    return false;
	i_5_ = (int) this.aFloatArray3601[0];
	i_6_ = (int) this.aFloatArray3601[1];
	i_7_ = (int) this.aFloatArray3601[2];
	return this.aClass332_3581.method4064(i_0_, i_3_, i_6_, i, i_2_, i_5_, i_1_, i_4_, i_7_);
    }

    public void method4067(int i, int i_8_, int i_9_, int i_10_, int i_11_, int i_12_) {
	if (i == 8 || i == 16) {
	    if (i == 8) {
		int i_13_ = i_9_ << (this.aClass331_3580.anInt3504 * -1688804109);
		int i_14_ = i_13_ + (this.aClass331_3580.anInt3536) * 1828905535;
		int i_15_ = i_10_ << (this.aClass331_3580.anInt3504 * -1688804109);
		int i_16_ = i_15_ + (this.aClass331_3580.anInt3536) * 1828905535;
		int i_17_ = this.aClass331_3580.aClass_xaArray3519[i_8_].method6341(i_9_, i_10_, (byte) -46);
		int i_18_ = this.aClass331_3580.aClass_xaArray3519[i_8_].method6341(i_9_ + 1, i_10_ + 1, (byte) -53);
		this.aClass328Array3597[this.anInt3590++] = new Class328(this.aClass331_3580, i, i_8_, i_13_, i_14_, i_14_, i_13_, i_17_, i_18_, i_18_ - i_11_, i_17_ - i_11_, i_15_, i_16_, i_16_, i_15_);
	    } else {
		int i_19_ = ((i_9_ << (this.aClass331_3580.anInt3504 * -1688804109)) + (this.aClass331_3580.anInt3536) * 1828905535);
		int i_20_ = i_19_ - (this.aClass331_3580.anInt3536) * 1828905535;
		int i_21_ = i_10_ << (this.aClass331_3580.anInt3504 * -1688804109);
		int i_22_ = i_21_ + (this.aClass331_3580.anInt3536) * 1828905535;
		int i_23_ = this.aClass331_3580.aClass_xaArray3519[i_8_].method6341(i_9_ + 1, i_10_, (byte) -128);
		int i_24_ = this.aClass331_3580.aClass_xaArray3519[i_8_].method6341(i_9_, i_10_ + 1, (byte) -126);
		this.aClass328Array3597[this.anInt3590++] = new Class328(this.aClass331_3580, i, i_8_, i_19_, i_20_, i_20_, i_19_, i_23_, i_24_, i_24_ - i_11_, i_23_ - i_11_, i_21_, i_22_, i_22_, i_21_);
	    }
	} else {
	    Class326 class326 = (this.aClass331_3580.aClass326ArrayArrayArray3516[i_8_][i_9_][i_10_]);
	    if (class326 == null)
		this.aClass331_3580.aClass326ArrayArrayArray3516[i_8_][i_9_][i_10_] = class326 = new Class326(i_8_);
	    if (i == 1) {
		class326.aShort3464 = (short) i_11_;
		class326.aShort3465 = (short) i_12_;
	    } else if (i == 2) {
		class326.aShort3463 = (short) i_11_;
		class326.aShort3467 = (short) i_12_;
	    }
	    if (this.aBoolean3598)
		method4079();
	}
    }

    public void method4068(int i, int i_25_, int i_26_, int i_27_) {
	if (i == 8 || i == 16) {
	    for (int i_28_ = 0; i_28_ < this.anInt3590; i_28_++) {
		Class328 class328 = this.aClass328Array3597[i_28_];
		if (class328.aByte3482 == i && class328.aByte3479 == i_25_ && ((class328.aShort3487 == i_26_ && class328.aShort3480 == i_27_) || (class328.aShort3481 == i_26_ && class328.aShort3483 == i_27_))) {
		    if (i_28_ != this.anInt3590)
			System.arraycopy(this.aClass328Array3597, i_28_ + 1, this.aClass328Array3597, i_28_, ((this.aClass328Array3597).length - (i_28_ + 1)));
		    this.anInt3590--;
		    break;
		}
	    }
	} else {
	    Class326 class326 = (this.aClass331_3580.aClass326ArrayArrayArray3516[i_25_][i_26_][i_27_]);
	    if (class326 != null) {
		if (i == 1)
		    class326.aShort3464 = (short) 0;
		else if (i == 2)
		    class326.aShort3463 = (short) 0;
	    }
	    method4079();
	}
    }

    void method4069(GraphicsToolkit class_ra, Class328 class328, int i) {
	aClass_ra3602 = class_ra;
	if (this.anIntArray3578 != null && class328.aByte3479 >= i) {
	    for (int i_29_ = 0; i_29_ < this.anIntArray3578.length; i_29_++) {
		if (this.anIntArray3578[i_29_] != -1000000 && ((class328.anIntArray3485[0] <= this.anIntArray3578[i_29_]) || (class328.anIntArray3485[1] <= this.anIntArray3578[i_29_]) || (class328.anIntArray3485[2] <= this.anIntArray3578[i_29_]) || (class328.anIntArray3485[3] <= this.anIntArray3578[i_29_])) && ((class328.anIntArray3484[0] <= this.anIntArray3596[i_29_]) || (class328.anIntArray3484[1] <= this.anIntArray3596[i_29_]) || (class328.anIntArray3484[2] <= this.anIntArray3596[i_29_]) || (class328.anIntArray3484[3] <= this.anIntArray3596[i_29_])) && ((class328.anIntArray3484[0] >= this.anIntArray3595[i_29_]) || (class328.anIntArray3484[1] >= this.anIntArray3595[i_29_]) || (class328.anIntArray3484[2] >= this.anIntArray3595[i_29_]) || (class328.anIntArray3484[3] >= this.anIntArray3595[i_29_])) && ((class328.anIntArray3486[0] <= this.anIntArray3582[i_29_]) || (class328.anIntArray3486[1] <= this.anIntArray3582[i_29_]) || (class328.anIntArray3486[2] <= this.anIntArray3582[i_29_]) || (class328.anIntArray3486[3] <= this.anIntArray3582[i_29_])) && ((class328.anIntArray3486[0] >= this.anIntArray3576[i_29_]) || (class328.anIntArray3486[1] >= this.anIntArray3576[i_29_]) || (class328.anIntArray3486[2] >= this.anIntArray3576[i_29_]) || (class328.anIntArray3486[3] >= this.anIntArray3576[i_29_])))
		    return;
	    }
	}
	if (class328.aByte3482 == 1) {
	    int i_30_ = (class328.aShort3487 - (this.aClass331_3580.anInt3553 * -804213305) + (this.aClass331_3580.anInt3538 * 583010427));
	    if (i_30_ >= 0 && i_30_ <= ((this.aClass331_3580.anInt3538) * 583010427 + (this.aClass331_3580.anInt3538) * 583010427)) {
		int i_31_ = (class328.aShort3480 - (this.aClass331_3580.anInt3513) * 465603579 + (this.aClass331_3580.anInt3538) * 583010427);
		if (i_31_ < 0)
		    i_31_ = 0;
		else if (i_31_ > ((this.aClass331_3580.anInt3538) * 583010427 + (this.aClass331_3580.anInt3538) * 583010427))
		    return;
		int i_32_ = (class328.aShort3483 - (this.aClass331_3580.anInt3513) * 465603579 + (this.aClass331_3580.anInt3538) * 583010427);
		if (i_32_ > ((this.aClass331_3580.anInt3538 * 583010427) + (this.aClass331_3580.anInt3538) * 583010427))
		    i_32_ = ((this.aClass331_3580.anInt3538) * 583010427 + (this.aClass331_3580.anInt3538) * 583010427);
		else if (i_32_ < 0)
		    return;
		boolean bool = false;
		while_79_: do {
		    do {
			if (i_31_ > i_32_)
			    break while_79_;
		    }
		    while (!this.aClass331_3580.aBooleanArrayArray3563[i_30_][i_31_++]);
		    bool = true;
		}
		while (false);
		if (bool) {
		    float f = ((this.aClass331_3580).anInt3542 * -343859235) - class328.anIntArray3484[0];
		    if (f < 0.0F)
			f *= -1.0F;
		    if (!(f < this.anInt3584) && method4076(class328, 0) && method4076(class328, 1) && method4076(class328, 2) && method4076(class328, 3))
			this.aClass328Array3593[this.anInt3592++] = class328;
		}
	    }
	} else if (class328.aByte3482 == 2) {
	    int i_33_ = (class328.aShort3480 - (this.aClass331_3580.anInt3513 * 465603579) + (this.aClass331_3580.anInt3538 * 583010427));
	    if (i_33_ >= 0 && i_33_ <= ((this.aClass331_3580.anInt3538) * 583010427 + (this.aClass331_3580.anInt3538) * 583010427)) {
		int i_34_ = (class328.aShort3487 - (this.aClass331_3580.anInt3553) * -804213305 + (this.aClass331_3580.anInt3538) * 583010427);
		if (i_34_ < 0)
		    i_34_ = 0;
		else if (i_34_ > ((this.aClass331_3580.anInt3538) * 583010427 + (this.aClass331_3580.anInt3538) * 583010427))
		    return;
		int i_35_ = (class328.aShort3481 - (this.aClass331_3580.anInt3553) * -804213305 + (this.aClass331_3580.anInt3538) * 583010427);
		if (i_35_ > ((this.aClass331_3580.anInt3538 * 583010427) + (this.aClass331_3580.anInt3538) * 583010427))
		    i_35_ = ((this.aClass331_3580.anInt3538) * 583010427 + (this.aClass331_3580.anInt3538) * 583010427);
		else if (i_35_ < 0)
		    return;
		boolean bool = false;
		while_80_: do {
		    do {
			if (i_34_ > i_35_)
			    break while_80_;
		    }
		    while (!this.aClass331_3580.aBooleanArrayArray3563[i_34_++][i_33_]);
		    bool = true;
		}
		while (false);
		if (bool) {
		    float f = ((this.aClass331_3580).anInt3543 * -1526003751) - class328.anIntArray3486[0];
		    if (f < 0.0F)
			f *= -1.0F;
		    if (!(f < this.anInt3584) && method4076(class328, 0) && method4076(class328, 1) && method4076(class328, 2) && method4076(class328, 3))
			this.aClass328Array3593[this.anInt3592++] = class328;
		}
	    }
	} else if (class328.aByte3482 == 16 || class328.aByte3482 == 8) {
	    int i_36_ = (class328.aShort3487 - (this.aClass331_3580.anInt3553 * -804213305) + (this.aClass331_3580.anInt3538 * 583010427));
	    if (i_36_ >= 0 && i_36_ <= ((this.aClass331_3580.anInt3538) * 583010427 + (this.aClass331_3580.anInt3538) * 583010427)) {
		int i_37_ = (class328.aShort3480 - (this.aClass331_3580.anInt3513) * 465603579 + (this.aClass331_3580.anInt3538) * 583010427);
		if (i_37_ >= 0 && (i_37_ <= ((this.aClass331_3580.anInt3538) * 583010427 + (this.aClass331_3580.anInt3538) * 583010427)) && (this.aClass331_3580.aBooleanArrayArray3563[i_36_][i_37_])) {
		    float f = ((this.aClass331_3580).anInt3542 * -343859235) - class328.anIntArray3484[0];
		    if (f < 0.0F)
			f *= -1.0F;
		    float f_38_ = ((this.aClass331_3580).anInt3543 * -1526003751) - class328.anIntArray3486[0];
		    if (f_38_ < 0.0F)
			f_38_ *= -1.0F;
		    if ((!(f < this.anInt3584) || !(f_38_ < this.anInt3584)) && method4076(class328, 0) && method4076(class328, 1) && method4076(class328, 2) && method4076(class328, 3))
			this.aClass328Array3593[this.anInt3592++] = class328;
		}
	    }
	} else if (class328.aByte3482 == 4) {
	    float f = class328.anIntArray3485[0] - (this.aClass331_3580.anInt3544) * 2104557959;
	    if (!(f <= this.anInt3600)) {
		int i_39_ = (class328.aShort3480 - (this.aClass331_3580.anInt3513) * 465603579 + (this.aClass331_3580.anInt3538) * 583010427);
		if (i_39_ < 0)
		    i_39_ = 0;
		else if (i_39_ > ((this.aClass331_3580.anInt3538) * 583010427 + (this.aClass331_3580.anInt3538) * 583010427))
		    return;
		int i_40_ = (class328.aShort3483 - (this.aClass331_3580.anInt3513) * 465603579 + (this.aClass331_3580.anInt3538) * 583010427);
		if (i_40_ > ((this.aClass331_3580.anInt3538 * 583010427) + (this.aClass331_3580.anInt3538) * 583010427))
		    i_40_ = ((this.aClass331_3580.anInt3538) * 583010427 + (this.aClass331_3580.anInt3538) * 583010427);
		else if (i_40_ < 0)
		    return;
		int i_41_ = (class328.aShort3487 - (this.aClass331_3580.anInt3553) * -804213305 + (this.aClass331_3580.anInt3538) * 583010427);
		if (i_41_ < 0)
		    i_41_ = 0;
		else if (i_41_ > ((this.aClass331_3580.anInt3538) * 583010427 + (this.aClass331_3580.anInt3538) * 583010427))
		    return;
		int i_42_ = (class328.aShort3481 - (this.aClass331_3580.anInt3553) * -804213305 + (this.aClass331_3580.anInt3538) * 583010427);
		if (i_42_ > ((this.aClass331_3580.anInt3538 * 583010427) + (this.aClass331_3580.anInt3538) * 583010427))
		    i_42_ = ((this.aClass331_3580.anInt3538) * 583010427 + (this.aClass331_3580.anInt3538) * 583010427);
		else if (i_42_ < 0)
		    return;
		boolean bool = false;
		while_81_: for (int i_43_ = i_41_; i_43_ <= i_42_; i_43_++) {
		    for (int i_44_ = i_39_; i_44_ <= i_40_; i_44_++) {
			if (this.aClass331_3580.aBooleanArrayArray3563[i_43_][i_44_]) {
			    bool = true;
			    break while_81_;
			}
		    }
		}
		if (bool && method4076(class328, 0) && method4076(class328, 1) && method4076(class328, 2) && method4076(class328, 3))
		    this.aClass328Array3593[this.anInt3592++] = class328;
	    }
	}
    }

    public void method4070(int i, int i_45_, int i_46_, int i_47_, int i_48_, int i_49_, int i_50_, int i_51_) {
	this.aClass328Array3587[this.anInt3586++] = new Class328(this.aClass331_3580, i, i_45_, i_46_, i_47_, i_47_, i_46_, i_50_, i_51_, i_51_, i_50_, i_48_, i_48_, i_49_, i_49_);
    }

    Class333(Class331 class331) {
	this.aBoolean3577 = true;
	this.aBoolean3598 = false;
	this.anInt3592 = 0;
	this.aFloatArray3601 = new float[3];
	this.anIntArray3589 = new int[2];
	this.anInt3603 = -1;
	this.anInt3604 = -1;
	this.aBoolean3605 = true;
	this.anInt3584 = class331.anInt3536 * 1828905535;
	this.anInt3600 = class331.anInt3536 * 1828905535;
	this.aClass331_3580 = class331;
	this.aClass328Array3587 = new Class328[535];
	this.anInt3586 = 0;
	this.aClass328Array3591 = new Class328[2045];
	this.anInt3588 = 0;
	this.aClass328Array3597 = new Class328[1027];
	this.anInt3590 = 0;
	this.aClass328Array3593 = new Class328[1045];
	this.anInt3592 = 0;
	this.anIntArrayArrayArray3585 = (new int[this.aClass331_3580.anInt3548 * 1678382205][this.aClass331_3580.anInt3514 * 1988988347 + 1][this.aClass331_3580.anInt3549 * 1340714547 + 1]);
	this.aBoolean3598 = false;
	if (this.aClass331_3580.aClass_ra3508 instanceof ja)
	    aBoolean3583 = false;
	else
	    aBoolean3583 = true;
	if (aBoolean3583)
	    this.aClass332_3581 = new Class332(this);
    }

    boolean method4071(int i, int i_52_, int i_53_) {
	if (!aBoolean3583 || !this.aBoolean3577)
	    return false;
	if (this.aClass332_3581.anInt3567 < 102)
	    return false;
	int i_54_ = this.anIntArrayArrayArray3585[i][i_52_][i_53_];
	if (i_54_ == -(this.aClass331_3580.anInt3545 * -847572923))
	    return false;
	if (i_54_ == (this.aClass331_3580.anInt3545 * -847572923))
	    return true;
	if (this.aClass331_3580.aClass_xaArray3517 == this.aClass331_3580.aClass_xaArray3521)
	    return false;
	int i_55_ = (i_52_ << this.aClass331_3580.anInt3504 * -1688804109);
	int i_56_ = (i_53_ << this.aClass331_3580.anInt3504 * -1688804109);
	if (method4066(i_55_ + 1, this.aClass331_3580.aClass_xaArray3517[i].method6341(i_52_, i_53_, (byte) -88), i_56_ + 1, i_55_ + (this.aClass331_3580.anInt3536) * 1828905535 - 1, this.aClass331_3580.aClass_xaArray3517[i].method6341(i_52_ + 1, i_53_ + 1, (byte) -108), i_56_ + (this.aClass331_3580.anInt3536) * 1828905535 - 1, i_55_ + 1, this.aClass331_3580.aClass_xaArray3517[i].method6341(i_52_, i_53_ + 1, (byte) -91), i_56_ + (this.aClass331_3580.anInt3536) * 1828905535 - 1) && method4066(i_55_ + 1, this.aClass331_3580.aClass_xaArray3517[i].method6341(i_52_, i_53_, (byte) -42), i_56_ + 1, (i_55_ + (this.aClass331_3580.anInt3536) * 1828905535 - 1), this.aClass331_3580.aClass_xaArray3517[i].method6341(i_52_ + 1, i_53_, (byte) -46), i_56_ + 1, (i_55_ + (this.aClass331_3580.anInt3536) * 1828905535 - 1), this.aClass331_3580.aClass_xaArray3517[i].method6341(i_52_ + 1, i_53_ + 1, (byte) -12), (i_56_ + (this.aClass331_3580.anInt3536) * 1828905535 - 1))) {
	    this.anIntArrayArrayArray3585[i][i_52_][i_53_] = (this.aClass331_3580.anInt3545 * -847572923);
	    return true;
	}
	this.anIntArrayArrayArray3585[i][i_52_][i_53_] = -(this.aClass331_3580.anInt3545 * -847572923);
	return false;
    }

    boolean method4072(Class365_Sub1_Sub5 class365_sub1_sub5, int i, int i_57_, int i_58_) {
	if (!aBoolean3583 || !this.aBoolean3577)
	    return false;
	if (this.aClass332_3581.anInt3567 < 102)
	    return false;
	if (!method4071(i, i_57_, i_58_))
	    return false;
	int i_59_ = (i_57_ << this.aClass331_3580.anInt3504 * -1688804109);
	int i_60_ = (i_58_ << this.aClass331_3580.anInt3504 * -1688804109);
	int i_61_ = (this.aClass331_3580.aClass_xaArray3517[i].method6341(i_57_, i_58_, (byte) -91) - 1);
	int i_62_ = i_61_ + class365_sub1_sub5.method4361(1951240662);
	if (class365_sub1_sub5.aShort9814 == 1) {
	    if (!method4066(i_59_, i_61_, i_60_, i_59_, i_62_, i_60_, i_59_, i_62_, (i_60_ + (this.aClass331_3580.anInt3536) * 1828905535)))
		return false;
	    if (!method4066(i_59_, i_61_, i_60_, i_59_, i_62_, (i_60_ + (this.aClass331_3580.anInt3536) * 1828905535), i_59_, i_61_, (i_60_ + (this.aClass331_3580.anInt3536) * 1828905535)))
		return false;
	    return true;
	}
	if (class365_sub1_sub5.aShort9814 == 2) {
	    if (!method4066(i_59_, i_61_, i_60_ + (this.aClass331_3580.anInt3536) * 1828905535, i_59_ + (this.aClass331_3580.anInt3536) * 1828905535, i_62_, i_60_ + (this.aClass331_3580.anInt3536) * 1828905535, i_59_, i_62_, i_60_ + (this.aClass331_3580.anInt3536) * 1828905535))
		return false;
	    if (!method4066(i_59_, i_61_, i_60_ + (this.aClass331_3580.anInt3536) * 1828905535, i_59_ + (this.aClass331_3580.anInt3536) * 1828905535, i_61_, i_60_ + (this.aClass331_3580.anInt3536) * 1828905535, i_59_ + (this.aClass331_3580.anInt3536) * 1828905535, i_62_, i_60_ + (this.aClass331_3580.anInt3536) * 1828905535))
		return false;
	    return true;
	}
	if (class365_sub1_sub5.aShort9814 == 4) {
	    if (!method4066(i_59_ + (this.aClass331_3580.anInt3536) * 1828905535, i_61_, i_60_, i_59_ + (this.aClass331_3580.anInt3536) * 1828905535, i_62_, i_60_, i_59_ + (this.aClass331_3580.anInt3536) * 1828905535, i_62_, i_60_ + (this.aClass331_3580.anInt3536) * 1828905535))
		return false;
	    if (!method4066(i_59_ + (this.aClass331_3580.anInt3536) * 1828905535, i_61_, i_60_, i_59_ + (this.aClass331_3580.anInt3536) * 1828905535, i_62_, i_60_ + (this.aClass331_3580.anInt3536) * 1828905535, i_59_ + (this.aClass331_3580.anInt3536) * 1828905535, i_61_, i_60_ + (this.aClass331_3580.anInt3536) * 1828905535))
		return false;
	    return true;
	}
	if (class365_sub1_sub5.aShort9814 == 8) {
	    if (!method4066(i_59_, i_61_, i_60_, (i_59_ + (this.aClass331_3580.anInt3536) * 1828905535), i_62_, i_60_, i_59_, i_62_, i_60_))
		return false;
	    if (!method4066(i_59_, i_61_, i_60_, (i_59_ + (this.aClass331_3580.anInt3536) * 1828905535), i_61_, i_60_, (i_59_ + (this.aClass331_3580.anInt3536) * 1828905535), i_62_, i_60_))
		return false;
	    return true;
	}
	if (class365_sub1_sub5.aShort9814 == 16) {
	    if (!method4077(i_59_, i_61_, (i_60_ + (this.aClass331_3580.anInt3546) * 394962841), (this.aClass331_3580.anInt3546) * 394962841, i_62_, (this.aClass331_3580.anInt3546) * 394962841))
		return false;
	    return true;
	}
	if (class365_sub1_sub5.aShort9814 == 32) {
	    if (!method4077((i_59_ + (this.aClass331_3580.anInt3546) * 394962841), i_61_, (i_60_ + (this.aClass331_3580.anInt3546) * 394962841), (this.aClass331_3580.anInt3546) * 394962841, i_62_, (this.aClass331_3580.anInt3546) * 394962841))
		return false;
	    return true;
	}
	if (class365_sub1_sub5.aShort9814 == 64) {
	    if (!method4077((i_59_ + (this.aClass331_3580.anInt3546) * 394962841), i_61_, i_60_, (this.aClass331_3580.anInt3546) * 394962841, i_62_, (this.aClass331_3580.anInt3546) * 394962841))
		return false;
	    return true;
	}
	if (class365_sub1_sub5.aShort9814 == 128) {
	    if (!method4077(i_59_, i_61_, i_60_, (this.aClass331_3580.anInt3546) * 394962841, i_62_, (this.aClass331_3580.anInt3546) * 394962841))
		return false;
	    return true;
	}
	return true;
    }

    boolean method4073(int i, int i_63_, int i_64_, int i_65_) {
	if (!aBoolean3583 || !this.aBoolean3577)
	    return false;
	if (this.aClass332_3581.anInt3567 < 102)
	    return false;
	if (!method4071(i, i_63_, i_64_))
	    return false;
	int i_66_ = (i_63_ << this.aClass331_3580.anInt3504 * -1688804109);
	int i_67_ = (i_64_ << this.aClass331_3580.anInt3504 * -1688804109);
	if (method4077(i_66_, this.aClass331_3580.aClass_xaArray3517[i].method6341(i_63_, i_64_, (byte) -113), i_67_, (this.aClass331_3580.anInt3536 * 1828905535), i_65_, (this.aClass331_3580.anInt3536 * 1828905535)))
	    return true;
	return false;
    }

    boolean method4074(int i, int i_68_, int i_69_, int i_70_, int i_71_, Class334 class334) {
	if (!aBoolean3583 || !this.aBoolean3577)
	    return false;
	if (this.aClass332_3581.anInt3567 < 102)
	    return false;
	if (i_68_ == i_69_ && i_70_ == i_71_) {
	    if (!method4071(i, i_68_, i_70_))
		return false;
	    if (method4075(class334))
		return true;
	    return false;
	}
	for (int i_72_ = i_68_; i_72_ <= i_69_; i_72_++) {
	    for (int i_73_ = i_70_; i_73_ <= i_71_; i_73_++) {
		if (this.anIntArrayArrayArray3585[i][i_72_][i_73_] == -((this.aClass331_3580.anInt3545) * -847572923))
		    return false;
	    }
	}
	if (!method4075(class334))
	    return false;
	return true;
    }

    final boolean method4075(Class334 class334) {
	if (class334 == null)
	    return false;
	return method4077(class334.anInt3610, class334.anInt3612, class334.anInt3613, (class334.anInt3614 - class334.anInt3610), (class334.anInt3607 - class334.anInt3612), (class334.anInt3615 - class334.anInt3613));
    }

    boolean method4076(Class328 class328, int i) {
	if (!method4078(class328.anIntArray3484[i], class328.anIntArray3485[i], class328.anIntArray3486[i]))
	    return false;
	class328.aShortArray3474[i] = (short) (int) this.aFloatArray3601[0];
	class328.aShortArray3488[i] = (short) (int) this.aFloatArray3601[1];
	class328.aShortArray3489[i] = (short) (int) this.aFloatArray3601[2];
	return true;
    }

    final boolean method4077(int i, int i_74_, int i_75_, int i_76_, int i_77_, int i_78_) {
	int i_79_ = i + i_76_;
	int i_80_ = i_74_ + i_77_;
	int i_81_ = i_75_ + i_78_;
	if (!method4066(i, i_80_, i_75_, i_79_, i_80_, i_81_, i, i_80_, i_81_))
	    return false;
	if (!method4066(i, i_80_, i_75_, i_79_, i_80_, i_75_, i_79_, i_80_, i_81_))
	    return false;
	if (i < (this.aClass331_3580.anInt3542 * -343859235)) {
	    if (!method4066(i, i_74_, i_81_, i, i_80_, i_75_, i, i_80_, i_81_))
		return false;
	    if (!method4066(i, i_74_, i_81_, i, i_74_, i_75_, i, i_80_, i_75_))
		return false;
	} else {
	    if (!method4066(i_79_, i_74_, i_81_, i_79_, i_80_, i_75_, i_79_, i_80_, i_81_))
		return false;
	    if (!method4066(i_79_, i_74_, i_81_, i_79_, i_74_, i_75_, i_79_, i_80_, i_75_))
		return false;
	}
	if (i_75_ < (this.aClass331_3580.anInt3543 * -1526003751)) {
	    if (!method4066(i, i_74_, i_75_, i_79_, i_80_, i_75_, i, i_80_, i_75_))
		return false;
	    if (!method4066(i, i_74_, i_75_, i_79_, i_74_, i_75_, i_79_, i_80_, i_75_))
		return false;
	} else {
	    if (!method4066(i, i_74_, i_81_, i_79_, i_80_, i_81_, i, i_80_, i_81_))
		return false;
	    if (!method4066(i, i_74_, i_81_, i_79_, i_74_, i_81_, i_79_, i_80_, i_81_))
		return false;
	}
	return true;
    }

    final boolean method4078(int i, int i_82_, int i_83_) {
	aClass_ra3602.method5060(i, i_82_, i_83_, this.aFloatArray3601);
	if (this.aFloatArray3601[2] < 50.0F)
	    return false;
	this.aFloatArray3601[0] /= 3.0F;
	this.aFloatArray3601[1] /= 3.0F;
	return true;
    }

    void method4079() {
	for (int i = 0; i < this.anInt3588; i++)
	    this.aClass328Array3591[i] = null;
	this.anInt3588 = 0;
	for (int i = 0; i < this.aClass331_3580.anInt3548 * 1678382205; i++) {
	    for (int i_84_ = 0; (i_84_ < this.aClass331_3580.anInt3514 * 1988988347); i_84_++) {
		for (int i_85_ = 0; i_85_ < (this.aClass331_3580.anInt3549 * 1340714547); i_85_++) {
		    Class326 class326 = (this.aClass331_3580.aClass326ArrayArrayArray3516[i][i_85_][i_84_]);
		    if (class326 != null) {
			if (class326.aShort3464 > 0)
			    class326.aShort3464 *= -1;
			if (class326.aShort3463 > 0)
			    class326.aShort3463 *= -1;
		    }
		}
	    }
	}
	for (int i = 0; i < this.aClass331_3580.anInt3548 * 1678382205; i++) {
	    for (int i_86_ = 0; (i_86_ < this.aClass331_3580.anInt3514 * 1988988347); i_86_++) {
		for (int i_87_ = 0; i_87_ < (this.aClass331_3580.anInt3549 * 1340714547); i_87_++) {
		    Class326 class326 = (this.aClass331_3580.aClass326ArrayArrayArray3516[i][i_87_][i_86_]);
		    if (class326 != null) {
			boolean bool = ((this.aClass331_3580.aClass326ArrayArrayArray3516[0][i_87_][i_86_]) != null && (this.aClass331_3580.aClass326ArrayArrayArray3516[0][i_87_][i_86_].aClass326_3455) != null);
			if (class326.aShort3464 < 0) {
			    int i_88_ = i_86_;
			    int i_89_ = i_86_;
			    int i_90_ = i;
			    int i_91_ = i;
			    Class326 class326_92_ = (this.aClass331_3580.aClass326ArrayArrayArray3516[i][i_87_][i_88_ - 1]);
			    int i_93_;
			    for (i_93_ = (this.aClass331_3580.aClass_xaArray3519[i].method6341(i_87_, i_86_, (byte) -58)); (i_88_ > 0 && class326_92_ != null && class326_92_.aShort3464 < 0 && (class326_92_.aShort3464 == class326.aShort3464) && (class326_92_.aShort3465 == class326.aShort3465) && i_93_ == (this.aClass331_3580.aClass_xaArray3519[i].method6341(i_87_, i_88_ - 1, (byte) -78)) && (i_88_ - 1 <= 0 || i_93_ == (this.aClass331_3580.aClass_xaArray3519[i].method6341(i_87_, i_88_ - 2, (byte) -92)))); class326_92_ = (this.aClass331_3580.aClass326ArrayArrayArray3516[i][i_87_][i_88_ - 1]))
				i_88_--;
			    for (class326_92_ = (this.aClass331_3580.aClass326ArrayArrayArray3516[i][i_87_][i_89_ + 1]); (i_89_ < (this.aClass331_3580.anInt3549) * 1340714547 && class326_92_ != null && class326_92_.aShort3464 < 0 && (class326_92_.aShort3464 == class326.aShort3464) && (class326_92_.aShort3465 == class326.aShort3465) && i_93_ == (this.aClass331_3580.aClass_xaArray3519[i].method6341(i_87_, i_89_ + 1, (byte) -51)) && (i_89_ + 1 >= ((this.aClass331_3580.anInt3549) * 1340714547) || i_93_ == (this.aClass331_3580.aClass_xaArray3519[i].method6341(i_87_, i_89_ + 2, (byte) -31)))); class326_92_ = (this.aClass331_3580.aClass326ArrayArrayArray3516[i][i_87_][i_89_ + 1]))
				i_89_++;
			    int i_94_ = i_91_ - i_90_ + 1;
			    int i_95_ = this.aClass331_3580.aClass_xaArray3519[bool ? i_90_ + 1 : i_90_].method6341(i_87_, i_88_, (byte) -52);
			    int i_96_ = (i_95_ + class326.aShort3464 * i_94_);
			    int i_97_ = (this.aClass331_3580.aClass_xaArray3519[bool ? i_90_ + 1 : i_90_].method6341(i_87_, i_89_ + 1, (byte) -12));
			    int i_98_ = (i_97_ + class326.aShort3464 * i_94_);
			    int i_99_ = i_87_ << (this.aClass331_3580.anInt3504) * -1688804109;
			    int i_100_ = i_88_ << (this.aClass331_3580.anInt3504) * -1688804109;
			    int i_101_ = ((i_89_ << (this.aClass331_3580.anInt3504) * -1688804109) + ((this.aClass331_3580).anInt3536 * 1828905535));
			    this.aClass328Array3591[this.anInt3588++] = (new Class328(this.aClass331_3580, 1, i_91_, i_99_ + class326.aShort3465, i_99_ + class326.aShort3465, i_99_ + class326.aShort3465, i_99_ + class326.aShort3465, i_95_, i_97_, i_98_, i_96_, i_100_, i_101_, i_101_, i_100_));
			    for (int i_102_ = i_90_; i_102_ <= i_91_; i_102_++) {
				for (int i_103_ = i_88_; i_103_ <= i_89_; i_103_++)
				    (this.aClass331_3580.aClass326ArrayArrayArray3516[i_102_][i_87_][i_103_]).aShort3464 *= -1;
			    }
			}
			if (class326.aShort3463 < 0) {
			    int i_104_ = i_87_;
			    int i_105_ = i_87_;
			    int i_106_ = i;
			    int i_107_ = i;
			    Class326 class326_108_ = (this.aClass331_3580.aClass326ArrayArrayArray3516[i][i_104_ - 1][i_86_]);
			    int i_109_;
			    for (i_109_ = (this.aClass331_3580.aClass_xaArray3519[i].method6341(i_87_, i_86_, (byte) -77)); (i_104_ > 0 && class326_108_ != null && class326_108_.aShort3463 < 0 && (class326_108_.aShort3463 == class326.aShort3463) && (class326_108_.aShort3467 == class326.aShort3467) && (i_109_ == (this.aClass331_3580.aClass_xaArray3519[i].method6341(i_104_ - 1, i_86_, (byte) -104))) && (i_104_ - 1 <= 0 || i_109_ == (this.aClass331_3580.aClass_xaArray3519[i].method6341(i_104_ - 2, i_86_, (byte) -71)))); class326_108_ = (this.aClass331_3580.aClass326ArrayArrayArray3516[i][i_104_ - 1][i_86_]))
				i_104_--;
			    for (class326_108_ = (this.aClass331_3580.aClass326ArrayArrayArray3516[i][i_105_ + 1][i_86_]); (i_105_ < (this.aClass331_3580.anInt3514) * 1988988347 && class326_108_ != null && class326_108_.aShort3463 < 0 && (class326_108_.aShort3463 == class326.aShort3463) && (class326_108_.aShort3467 == class326.aShort3467) && (i_109_ == (this.aClass331_3580.aClass_xaArray3519[i].method6341(i_105_ + 1, i_86_, (byte) -31))) && (i_105_ + 1 >= (this.aClass331_3580.anInt3514) * 1988988347 || i_109_ == (this.aClass331_3580.aClass_xaArray3519[i].method6341(i_105_ + 2, i_86_, (byte) -27)))); class326_108_ = (this.aClass331_3580.aClass326ArrayArrayArray3516[i][i_105_ + 1][i_86_]))
				i_105_++;
			    int i_110_ = i_107_ - i_106_ + 1;
			    int i_111_ = this.aClass331_3580.aClass_xaArray3519[bool ? i_106_ + 1 : i_106_].method6341(i_104_, i_86_, (byte) -116);
			    int i_112_ = i_111_ + (class326.aShort3463 * i_110_);
			    int i_113_ = (this.aClass331_3580.aClass_xaArray3519[bool ? i_106_ + 1 : i_106_].method6341(i_105_ + 1, i_86_, (byte) -68));
			    int i_114_ = i_113_ + (class326.aShort3463 * i_110_);
			    int i_115_ = i_104_ << (this.aClass331_3580.anInt3504) * -1688804109;
			    int i_116_ = ((i_105_ << (this.aClass331_3580.anInt3504) * -1688804109) + ((this.aClass331_3580).anInt3536 * 1828905535));
			    int i_117_ = i_86_ << (this.aClass331_3580.anInt3504) * -1688804109;
			    this.aClass328Array3591[this.anInt3588++] = (new Class328(this.aClass331_3580, 2, i_107_, i_115_, i_116_, i_116_, i_115_, i_111_, i_113_, i_114_, i_112_, i_117_ + class326.aShort3467, i_117_ + class326.aShort3467, i_117_ + class326.aShort3467, (i_117_ + class326.aShort3467)));
			    for (int i_118_ = i_106_; i_118_ <= i_107_; i_118_++) {
				for (int i_119_ = i_104_; i_119_ <= i_105_; i_119_++)
				    (this.aClass331_3580.aClass326ArrayArrayArray3516[i_118_][i_119_][i_86_]).aShort3463 *= -1;
			    }
			}
		    }
		}
	    }
	}
	this.aBoolean3598 = true;
    }

    public void method4080() {
	method4079();
    }

    void method4081(GraphicsToolkit class_ra, int i) {
	aClass_ra3602 = class_ra;
	if (!aBoolean3583 || !this.aBoolean3577)
	    this.anInt3592 = 0;
	else {
	    if (this.aBoolean3605)
		this.aClass331_3580.aClass480_3509.method6108(1748002530);
	    aClass_ra3602.method5000(this.anIntArray3589);
	    if ((this.anInt3603 != (int) (this.anIntArray3589[0] / 3.0F)) || (this.anInt3604 != (int) (this.anIntArray3589[1] / 3.0F))) {
		this.anInt3603 = (int) (this.anIntArray3589[0] / 3.0F);
		this.anInt3604 = (int) (this.anIntArray3589[1] / 3.0F);
		this.anIntArray3599 = new int[(this.anInt3603 * this.anInt3604)];
	    }
	    this.anInt3592 = 0;
	    for (int i_120_ = 0; i_120_ < this.anInt3586; i_120_++)
		method4069(aClass_ra3602, this.aClass328Array3587[i_120_], i);
	    for (int i_121_ = 0; i_121_ < this.anInt3588; i_121_++)
		method4069(aClass_ra3602, this.aClass328Array3591[i_121_], i);
	    for (int i_122_ = 0; i_122_ < this.anInt3590; i_122_++)
		method4069(aClass_ra3602, this.aClass328Array3597[i_122_], i);
	    this.aClass332_3581.anInt3567 = 0;
	    if (this.anInt3592 > 0) {
		int i_123_ = this.anIntArray3599.length;
		int i_124_ = i_123_ - i_123_ & 0x7;
		int i_125_ = 0;
		while (i_125_ < i_124_) {
		    this.anIntArray3599[i_125_++] = 2147483647;
		    this.anIntArray3599[i_125_++] = 2147483647;
		    this.anIntArray3599[i_125_++] = 2147483647;
		    this.anIntArray3599[i_125_++] = 2147483647;
		    this.anIntArray3599[i_125_++] = 2147483647;
		    this.anIntArray3599[i_125_++] = 2147483647;
		    this.anIntArray3599[i_125_++] = 2147483647;
		    this.anIntArray3599[i_125_++] = 2147483647;
		}
		while (i_125_ < i_123_)
		    this.anIntArray3599[i_125_++] = 2147483647;
		this.aClass332_3581.anInt3572 = 1;
		for (int i_126_ = 0; i_126_ < this.anInt3592; i_126_++) {
		    Class328 class328 = this.aClass328Array3593[i_126_];
		    this.aClass332_3581.method4064(class328.aShortArray3488[0], class328.aShortArray3488[1], class328.aShortArray3488[3], class328.aShortArray3474[0], class328.aShortArray3474[1], class328.aShortArray3474[3], class328.aShortArray3489[0], class328.aShortArray3489[1], class328.aShortArray3489[3]);
		    this.aClass332_3581.method4064(class328.aShortArray3488[1], class328.aShortArray3488[2], class328.aShortArray3488[3], class328.aShortArray3474[1], class328.aShortArray3474[2], class328.aShortArray3474[3], class328.aShortArray3489[1], class328.aShortArray3489[2], class328.aShortArray3489[3]);
		}
		this.aClass332_3581.anInt3572 = 2;
	    }
	    if (this.aBoolean3605)
		this.aClass331_3580.aClass480_3509.method6108(140602310);
	}
    }
}
