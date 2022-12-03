
/* Class34 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import jaggl.OpenGL;

public class Class34 {
    Class32 aClass32_399;
    int anInt400 = 1600;
    int anInt401 = 64;
    static float aFloat402;
    Interface1 anInterface1_403;
    Class32 aClass32_404;
    Class32 aClass32_405;
    int anInt406 = 768;
    RsFloatBuffer aClass298_Sub53_Sub1_407;
    int anInt408;
    float[] aFloatArray409 = new float[16];
    int[] anIntArray410;
    int[] anIntArray411;
    int[] anIntArray412;
    Class302_Sub3_Sub1[][] aClass302_Sub3_Sub1ArrayArray413;
    int anInt414 = 64;
    int anInt415;
    Class302_Sub3_Sub1[][] aClass302_Sub3_Sub1ArrayArray416;

    void method436(Class_ra_Sub2 class_ra_sub2, Class69 class69) {
	if (class_ra_sub2.aClass233_8110 != null) {
	    method438(class_ra_sub2);
	    float f = (class_ra_sub2.aClass233_8110.aFloatArray2594[2]);
	    float f_0_ = (class_ra_sub2.aClass233_8110.aFloatArray2594[6]);
	    float f_1_ = (class_ra_sub2.aClass233_8110.aFloatArray2594[10]);
	    float f_2_ = (class_ra_sub2.aClass233_8110.aFloatArray2594[14]);
	    try {
		int i = 0;
		int i_3_ = 2147483647;
		int i_4_ = 0;
		Class302_Sub3 class302_sub3 = class69.aClass448_680.aClass302_Sub3_5621;
		for (Class302_Sub3 class302_sub3_5_ = class302_sub3.aClass302_Sub3_7650; class302_sub3_5_ != class302_sub3; class302_sub3_5_ = class302_sub3_5_.aClass302_Sub3_7650) {
		    Class302_Sub3_Sub1 class302_sub3_sub1 = (Class302_Sub3_Sub1) class302_sub3_5_;
		    int i_6_ = (int) (f * (class302_sub3_sub1.anInt9785 >> 12) + f_0_ * (class302_sub3_sub1.anInt9789 >> 12) + f_1_ * (class302_sub3_sub1.anInt9790 >> 12) + f_2_);
		    if (i_6_ > i_4_)
			i_4_ = i_6_;
		    if (i_6_ < i_3_)
			i_3_ = i_6_;
		    this.anIntArray410[i++] = i_6_;
		}
		int i_7_ = i_4_ - i_3_;
		int i_8_;
		if (i_7_ + 2 > 1600) {
		    i_8_ = (1 + Class87.method970(i_7_, 1151745545) - this.anInt408);
		    i_7_ = (i_7_ >> i_8_) + 2;
		} else {
		    i_8_ = 0;
		    i_7_ += 2;
		}
		Class302_Sub3 class302_sub3_9_ = class302_sub3.aClass302_Sub3_7650;
		i = 0;
		int i_10_ = -2;
		boolean bool = true;
		boolean bool_11_ = true;
		while (class302_sub3_9_ != class302_sub3) {
		    this.anInt415 = 0;
		    for (int i_12_ = 0; i_12_ < i_7_; i_12_++)
			this.anIntArray411[i_12_] = 0;
		    for (int i_13_ = 0; i_13_ < 64; i_13_++)
			this.anIntArray412[i_13_] = 0;
		    for (/**/; class302_sub3_9_ != class302_sub3; class302_sub3_9_ = class302_sub3_9_.aClass302_Sub3_7650) {
			Class302_Sub3_Sub1 class302_sub3_sub1 = (Class302_Sub3_Sub1) class302_sub3_9_;
			if (bool_11_) {
			    i_10_ = class302_sub3_sub1.anInt9794;
			    bool = class302_sub3_sub1.aBoolean9787;
			    bool_11_ = false;
			}
			if (i > 0 && (i_10_ != class302_sub3_sub1.anInt9794 || bool != class302_sub3_sub1.aBoolean9787)) {
			    bool_11_ = true;
			    break;
			}
			int i_14_ = (this.anIntArray410[i++] - i_3_ >> i_8_);
			if (i_14_ < 1600) {
			    if (this.anIntArray411[i_14_] < 64)
				this.aClass302_Sub3_Sub1ArrayArray413[i_14_][this.anIntArray411[i_14_]++] = class302_sub3_sub1;
			    else {
				if (this.anIntArray411[i_14_] == 64) {
				    if (this.anInt415 == 64)
					continue;
				    this.anIntArray411[i_14_] += 1 + this.anInt415++;
				}
				this.aClass302_Sub3_Sub1ArrayArray416[(this.anIntArray411[i_14_] - 64 - 1)][this.anIntArray412[(this.anIntArray411[i_14_]) - 64 - 1]++] = class302_sub3_sub1;
			    }
			}
		    }
		    if (i_10_ >= 0)
			class_ra_sub2.method5275(i_10_);
		    else
			class_ra_sub2.method5275(-1);
		    if (bool && (class_ra_sub2.aFloat8130 != aFloat402))
			class_ra_sub2.IA(aFloat402);
		    else if (class_ra_sub2.aFloat8130 != 1.0F)
			class_ra_sub2.IA(1.0F);
		    method437(class_ra_sub2, i_7_);
		}
	    }
	    catch (Exception exception) {
		/* empty */
	    }
	    method440(class_ra_sub2);
	}
    }

    void method437(Class_ra_Sub2 class_ra_sub2, int i) {
	OpenGL.glGetFloatv(2982, this.aFloatArray409, 0);
	float f = this.aFloatArray409[0];
	float f_15_ = this.aFloatArray409[4];
	float f_16_ = this.aFloatArray409[8];
	float f_17_ = this.aFloatArray409[1];
	float f_18_ = this.aFloatArray409[5];
	float f_19_ = this.aFloatArray409[9];
	float f_20_ = f + f_17_;
	float f_21_ = f_15_ + f_18_;
	float f_22_ = f_16_ + f_19_;
	float f_23_ = f - f_17_;
	float f_24_ = f_15_ - f_18_;
	float f_25_ = f_16_ - f_19_;
	float f_26_ = f_17_ - f;
	float f_27_ = f_18_ - f_15_;
	float f_28_ = f_19_ - f_16_;
	this.aClass298_Sub53_Sub1_407.index = 0;
	if (class_ra_sub2.aBoolean8143) {
	    for (int i_29_ = i - 1; i_29_ >= 0; i_29_--) {
		int i_30_ = (this.anIntArray411[i_29_] > 64 ? 64 : this.anIntArray411[i_29_]);
		if (i_30_ > 0) {
		    for (int i_31_ = i_30_ - 1; i_31_ >= 0; i_31_--) {
			Class302_Sub3_Sub1 class302_sub3_sub1 = (this.aClass302_Sub3_Sub1ArrayArray413[i_29_][i_31_]);
			int i_32_ = class302_sub3_sub1.anInt9792;
			byte i_33_ = (byte) (i_32_ >> 16);
			byte i_34_ = (byte) (i_32_ >> 8);
			byte i_35_ = (byte) i_32_;
			byte i_36_ = (byte) (i_32_ >>> 24);
			float f_37_ = class302_sub3_sub1.anInt9785 >> 12;
			float f_38_ = class302_sub3_sub1.anInt9789 >> 12;
			float f_39_ = class302_sub3_sub1.anInt9790 >> 12;
			int i_40_ = class302_sub3_sub1.anInt9791 >> 12;
			this.aClass298_Sub53_Sub1_407.method3658(0.0F);
			this.aClass298_Sub53_Sub1_407.method3658(0.0F);
			this.aClass298_Sub53_Sub1_407.method3658(f_37_ + f_20_ * -i_40_);
			this.aClass298_Sub53_Sub1_407.method3658(f_38_ + f_21_ * -i_40_);
			this.aClass298_Sub53_Sub1_407.method3658(f_39_ + f_22_ * -i_40_);
			this.aClass298_Sub53_Sub1_407.writeByte(i_33_);
			this.aClass298_Sub53_Sub1_407.writeByte(i_34_);
			this.aClass298_Sub53_Sub1_407.writeByte(i_35_);
			this.aClass298_Sub53_Sub1_407.writeByte(i_36_);
			this.aClass298_Sub53_Sub1_407.method3658(0.0F);
			this.aClass298_Sub53_Sub1_407.method3658(1.0F);
			this.aClass298_Sub53_Sub1_407.method3658(f_37_ + f_26_ * i_40_);
			this.aClass298_Sub53_Sub1_407.method3658(f_38_ + f_27_ * i_40_);
			this.aClass298_Sub53_Sub1_407.method3658(f_39_ + f_28_ * i_40_);
			this.aClass298_Sub53_Sub1_407.writeByte(i_33_);
			this.aClass298_Sub53_Sub1_407.writeByte(i_34_);
			this.aClass298_Sub53_Sub1_407.writeByte(i_35_);
			this.aClass298_Sub53_Sub1_407.writeByte(i_36_);
			this.aClass298_Sub53_Sub1_407.method3658(1.0F);
			this.aClass298_Sub53_Sub1_407.method3658(1.0F);
			this.aClass298_Sub53_Sub1_407.method3658(f_37_ + f_20_ * i_40_);
			this.aClass298_Sub53_Sub1_407.method3658(f_38_ + f_21_ * i_40_);
			this.aClass298_Sub53_Sub1_407.method3658(f_39_ + f_22_ * i_40_);
			this.aClass298_Sub53_Sub1_407.writeByte(i_33_);
			this.aClass298_Sub53_Sub1_407.writeByte(i_34_);
			this.aClass298_Sub53_Sub1_407.writeByte(i_35_);
			this.aClass298_Sub53_Sub1_407.writeByte(i_36_);
			this.aClass298_Sub53_Sub1_407.method3658(1.0F);
			this.aClass298_Sub53_Sub1_407.method3658(0.0F);
			this.aClass298_Sub53_Sub1_407.method3658(f_37_ + f_23_ * i_40_);
			this.aClass298_Sub53_Sub1_407.method3658(f_38_ + f_24_ * i_40_);
			this.aClass298_Sub53_Sub1_407.method3658(f_39_ + f_25_ * i_40_);
			this.aClass298_Sub53_Sub1_407.writeByte(i_33_);
			this.aClass298_Sub53_Sub1_407.writeByte(i_34_);
			this.aClass298_Sub53_Sub1_407.writeByte(i_35_);
			this.aClass298_Sub53_Sub1_407.writeByte(i_36_);
		    }
		    if (this.anIntArray411[i_29_] > 64) {
			int i_41_ = this.anIntArray411[i_29_] - 64 - 1;
			for (int i_42_ = this.anIntArray412[i_41_] - 1; i_42_ >= 0; i_42_--) {
			    Class302_Sub3_Sub1 class302_sub3_sub1 = (this.aClass302_Sub3_Sub1ArrayArray416[i_41_][i_42_]);
			    int i_43_ = class302_sub3_sub1.anInt9792;
			    byte i_44_ = (byte) (i_43_ >> 16);
			    byte i_45_ = (byte) (i_43_ >> 8);
			    byte i_46_ = (byte) i_43_;
			    byte i_47_ = (byte) (i_43_ >>> 24);
			    float f_48_ = class302_sub3_sub1.anInt9785 >> 12;
			    float f_49_ = class302_sub3_sub1.anInt9789 >> 12;
			    float f_50_ = class302_sub3_sub1.anInt9790 >> 12;
			    int i_51_ = class302_sub3_sub1.anInt9791 >> 12;
			    this.aClass298_Sub53_Sub1_407.method3658(0.0F);
			    this.aClass298_Sub53_Sub1_407.method3658(0.0F);
			    this.aClass298_Sub53_Sub1_407.method3658(f_48_ + f_20_ * -i_51_);
			    this.aClass298_Sub53_Sub1_407.method3658(f_49_ + f_21_ * -i_51_);
			    this.aClass298_Sub53_Sub1_407.method3658(f_50_ + f_22_ * -i_51_);
			    this.aClass298_Sub53_Sub1_407.writeByte(i_44_);
			    this.aClass298_Sub53_Sub1_407.writeByte(i_45_);
			    this.aClass298_Sub53_Sub1_407.writeByte(i_46_);
			    this.aClass298_Sub53_Sub1_407.writeByte(i_47_);
			    this.aClass298_Sub53_Sub1_407.method3658(0.0F);
			    this.aClass298_Sub53_Sub1_407.method3658(1.0F);
			    this.aClass298_Sub53_Sub1_407.method3658(f_48_ + f_26_ * i_51_);
			    this.aClass298_Sub53_Sub1_407.method3658(f_49_ + f_27_ * i_51_);
			    this.aClass298_Sub53_Sub1_407.method3658(f_50_ + f_28_ * i_51_);
			    this.aClass298_Sub53_Sub1_407.writeByte(i_44_);
			    this.aClass298_Sub53_Sub1_407.writeByte(i_45_);
			    this.aClass298_Sub53_Sub1_407.writeByte(i_46_);
			    this.aClass298_Sub53_Sub1_407.writeByte(i_47_);
			    this.aClass298_Sub53_Sub1_407.method3658(1.0F);
			    this.aClass298_Sub53_Sub1_407.method3658(1.0F);
			    this.aClass298_Sub53_Sub1_407.method3658(f_48_ + f_20_ * i_51_);
			    this.aClass298_Sub53_Sub1_407.method3658(f_49_ + f_21_ * i_51_);
			    this.aClass298_Sub53_Sub1_407.method3658(f_50_ + f_22_ * i_51_);
			    this.aClass298_Sub53_Sub1_407.writeByte(i_44_);
			    this.aClass298_Sub53_Sub1_407.writeByte(i_45_);
			    this.aClass298_Sub53_Sub1_407.writeByte(i_46_);
			    this.aClass298_Sub53_Sub1_407.writeByte(i_47_);
			    this.aClass298_Sub53_Sub1_407.method3658(1.0F);
			    this.aClass298_Sub53_Sub1_407.method3658(0.0F);
			    this.aClass298_Sub53_Sub1_407.method3658(f_48_ + f_23_ * i_51_);
			    this.aClass298_Sub53_Sub1_407.method3658(f_49_ + f_24_ * i_51_);
			    this.aClass298_Sub53_Sub1_407.method3658(f_50_ + f_25_ * i_51_);
			    this.aClass298_Sub53_Sub1_407.writeByte(i_44_);
			    this.aClass298_Sub53_Sub1_407.writeByte(i_45_);
			    this.aClass298_Sub53_Sub1_407.writeByte(i_46_);
			    this.aClass298_Sub53_Sub1_407.writeByte(i_47_);
			}
		    }
		}
	    }
	} else {
	    for (int i_52_ = i - 1; i_52_ >= 0; i_52_--) {
		int i_53_ = (this.anIntArray411[i_52_] > 64 ? 64 : this.anIntArray411[i_52_]);
		if (i_53_ > 0) {
		    for (int i_54_ = i_53_ - 1; i_54_ >= 0; i_54_--) {
			Class302_Sub3_Sub1 class302_sub3_sub1 = (this.aClass302_Sub3_Sub1ArrayArray413[i_52_][i_54_]);
			int i_55_ = class302_sub3_sub1.anInt9792;
			byte i_56_ = (byte) (i_55_ >> 16);
			byte i_57_ = (byte) (i_55_ >> 8);
			byte i_58_ = (byte) i_55_;
			byte i_59_ = (byte) (i_55_ >>> 24);
			float f_60_ = class302_sub3_sub1.anInt9785 >> 12;
			float f_61_ = class302_sub3_sub1.anInt9789 >> 12;
			float f_62_ = class302_sub3_sub1.anInt9790 >> 12;
			int i_63_ = class302_sub3_sub1.anInt9791 >> 12;
			this.aClass298_Sub53_Sub1_407.method3659(0.0F);
			this.aClass298_Sub53_Sub1_407.method3659(0.0F);
			this.aClass298_Sub53_Sub1_407.method3659(f_60_ + f_20_ * -i_63_);
			this.aClass298_Sub53_Sub1_407.method3659(f_61_ + f_21_ * -i_63_);
			this.aClass298_Sub53_Sub1_407.method3659(f_62_ + f_22_ * -i_63_);
			this.aClass298_Sub53_Sub1_407.writeByte(i_56_);
			this.aClass298_Sub53_Sub1_407.writeByte(i_57_);
			this.aClass298_Sub53_Sub1_407.writeByte(i_58_);
			this.aClass298_Sub53_Sub1_407.writeByte(i_59_);
			this.aClass298_Sub53_Sub1_407.method3659(0.0F);
			this.aClass298_Sub53_Sub1_407.method3659(1.0F);
			this.aClass298_Sub53_Sub1_407.method3659(f_60_ + f_26_ * i_63_);
			this.aClass298_Sub53_Sub1_407.method3659(f_61_ + f_27_ * i_63_);
			this.aClass298_Sub53_Sub1_407.method3659(f_62_ + f_28_ * i_63_);
			this.aClass298_Sub53_Sub1_407.writeByte(i_56_);
			this.aClass298_Sub53_Sub1_407.writeByte(i_57_);
			this.aClass298_Sub53_Sub1_407.writeByte(i_58_);
			this.aClass298_Sub53_Sub1_407.writeByte(i_59_);
			this.aClass298_Sub53_Sub1_407.method3659(1.0F);
			this.aClass298_Sub53_Sub1_407.method3659(1.0F);
			this.aClass298_Sub53_Sub1_407.method3659(f_60_ + f_20_ * i_63_);
			this.aClass298_Sub53_Sub1_407.method3659(f_61_ + f_21_ * i_63_);
			this.aClass298_Sub53_Sub1_407.method3659(f_62_ + f_22_ * i_63_);
			this.aClass298_Sub53_Sub1_407.writeByte(i_56_);
			this.aClass298_Sub53_Sub1_407.writeByte(i_57_);
			this.aClass298_Sub53_Sub1_407.writeByte(i_58_);
			this.aClass298_Sub53_Sub1_407.writeByte(i_59_);
			this.aClass298_Sub53_Sub1_407.method3659(1.0F);
			this.aClass298_Sub53_Sub1_407.method3659(0.0F);
			this.aClass298_Sub53_Sub1_407.method3659(f_60_ + f_23_ * i_63_);
			this.aClass298_Sub53_Sub1_407.method3659(f_61_ + f_24_ * i_63_);
			this.aClass298_Sub53_Sub1_407.method3659(f_62_ + f_25_ * i_63_);
			this.aClass298_Sub53_Sub1_407.writeByte(i_56_);
			this.aClass298_Sub53_Sub1_407.writeByte(i_57_);
			this.aClass298_Sub53_Sub1_407.writeByte(i_58_);
			this.aClass298_Sub53_Sub1_407.writeByte(i_59_);
		    }
		    if (this.anIntArray411[i_52_] > 64) {
			int i_64_ = this.anIntArray411[i_52_] - 64 - 1;
			for (int i_65_ = this.anIntArray412[i_64_] - 1; i_65_ >= 0; i_65_--) {
			    Class302_Sub3_Sub1 class302_sub3_sub1 = (this.aClass302_Sub3_Sub1ArrayArray416[i_64_][i_65_]);
			    int i_66_ = class302_sub3_sub1.anInt9792;
			    byte i_67_ = (byte) (i_66_ >> 16);
			    byte i_68_ = (byte) (i_66_ >> 8);
			    byte i_69_ = (byte) i_66_;
			    byte i_70_ = (byte) (i_66_ >>> 24);
			    float f_71_ = class302_sub3_sub1.anInt9785 >> 12;
			    float f_72_ = class302_sub3_sub1.anInt9789 >> 12;
			    float f_73_ = class302_sub3_sub1.anInt9790 >> 12;
			    int i_74_ = class302_sub3_sub1.anInt9791 >> 12;
			    this.aClass298_Sub53_Sub1_407.method3659(0.0F);
			    this.aClass298_Sub53_Sub1_407.method3659(0.0F);
			    this.aClass298_Sub53_Sub1_407.method3659(f_71_ + f_20_ * -i_74_);
			    this.aClass298_Sub53_Sub1_407.method3659(f_72_ + f_21_ * -i_74_);
			    this.aClass298_Sub53_Sub1_407.method3659(f_73_ + f_22_ * -i_74_);
			    this.aClass298_Sub53_Sub1_407.writeByte(i_67_);
			    this.aClass298_Sub53_Sub1_407.writeByte(i_68_);
			    this.aClass298_Sub53_Sub1_407.writeByte(i_69_);
			    this.aClass298_Sub53_Sub1_407.writeByte(i_70_);
			    this.aClass298_Sub53_Sub1_407.method3659(0.0F);
			    this.aClass298_Sub53_Sub1_407.method3659(1.0F);
			    this.aClass298_Sub53_Sub1_407.method3659(f_71_ + f_26_ * i_74_);
			    this.aClass298_Sub53_Sub1_407.method3659(f_72_ + f_27_ * i_74_);
			    this.aClass298_Sub53_Sub1_407.method3659(f_73_ + f_28_ * i_74_);
			    this.aClass298_Sub53_Sub1_407.writeByte(i_67_);
			    this.aClass298_Sub53_Sub1_407.writeByte(i_68_);
			    this.aClass298_Sub53_Sub1_407.writeByte(i_69_);
			    this.aClass298_Sub53_Sub1_407.writeByte(i_70_);
			    this.aClass298_Sub53_Sub1_407.method3659(1.0F);
			    this.aClass298_Sub53_Sub1_407.method3659(1.0F);
			    this.aClass298_Sub53_Sub1_407.method3659(f_71_ + f_20_ * i_74_);
			    this.aClass298_Sub53_Sub1_407.method3659(f_72_ + f_21_ * i_74_);
			    this.aClass298_Sub53_Sub1_407.method3659(f_73_ + f_22_ * i_74_);
			    this.aClass298_Sub53_Sub1_407.writeByte(i_67_);
			    this.aClass298_Sub53_Sub1_407.writeByte(i_68_);
			    this.aClass298_Sub53_Sub1_407.writeByte(i_69_);
			    this.aClass298_Sub53_Sub1_407.writeByte(i_70_);
			    this.aClass298_Sub53_Sub1_407.method3659(1.0F);
			    this.aClass298_Sub53_Sub1_407.method3659(0.0F);
			    this.aClass298_Sub53_Sub1_407.method3659(f_71_ + f_23_ * i_74_);
			    this.aClass298_Sub53_Sub1_407.method3659(f_72_ + f_24_ * i_74_);
			    this.aClass298_Sub53_Sub1_407.method3659(f_73_ + f_25_ * i_74_);
			    this.aClass298_Sub53_Sub1_407.writeByte(i_67_);
			    this.aClass298_Sub53_Sub1_407.writeByte(i_68_);
			    this.aClass298_Sub53_Sub1_407.writeByte(i_69_);
			    this.aClass298_Sub53_Sub1_407.writeByte(i_70_);
			}
		    }
		}
	    }
	}
	if (this.aClass298_Sub53_Sub1_407.index * 385051775 != 0) {
	    this.anInterface1_403.method8(24, this.aClass298_Sub53_Sub1_407.buffer, (this.aClass298_Sub53_Sub1_407.index * 385051775));
	    class_ra_sub2.method5248(this.aClass32_405, null, this.aClass32_399, this.aClass32_404);
	    class_ra_sub2.method5285(7, 0, (this.aClass298_Sub53_Sub1_407.index) * 385051775 / 24);
	}
    }

    void method438(Class_ra_Sub2 class_ra_sub2) {
	aFloat402 = class_ra_sub2.aFloat8130;
	class_ra_sub2.method5252();
	OpenGL.glDisable(16384);
	OpenGL.glDisable(16385);
	class_ra_sub2.method5281(false);
	OpenGL.glNormal3f(0.0F, -1.0F, 0.0F);
    }

    Class34() {
	this.aClass298_Sub53_Sub1_407 = new RsFloatBuffer(786336);
	this.anInt401 = 64;
	this.anInt406 = 768;
	this.anInt400 = 1600;
	this.anInt408 = Class87.method970(1600, -1704489835);
	this.anInt414 = 64;
	this.anIntArray410 = new int[8191];
	this.anIntArray411 = new int[1600];
	this.anIntArray412 = new int[64];
	this.aClass302_Sub3_Sub1ArrayArray413 = new Class302_Sub3_Sub1[1600][64];
	this.aClass302_Sub3_Sub1ArrayArray416 = new Class302_Sub3_Sub1[64][768];
	this.anInt415 = 0;
    }

    void method439(Class_ra_Sub2 class_ra_sub2) {
	this.anInterface1_403 = class_ra_sub2.method5244(24, null, 196584, true);
	this.aClass32_404 = new Class32(this.anInterface1_403, 5126, 2, 0);
	this.aClass32_405 = new Class32(this.anInterface1_403, 5126, 3, 8);
	this.aClass32_399 = new Class32(this.anInterface1_403, 5121, 4, 20);
    }

    void method440(Class_ra_Sub2 class_ra_sub2) {
	class_ra_sub2.method5281(true);
	OpenGL.glEnable(16384);
	OpenGL.glEnable(16385);
	if (class_ra_sub2.aFloat8130 != aFloat402)
	    class_ra_sub2.IA(aFloat402);
    }
}
