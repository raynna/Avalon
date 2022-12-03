/* Class57_Sub2 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class57_Sub2 extends Class57 {
    int anInt9023;
    Interface9_Impl2 anInterface9_Impl2_9024;
    Interface8_Impl1_Impl1 anInterface8_Impl1_Impl1_9025;
    int anInt9026;
    boolean aBoolean9027;
    boolean aBoolean9028 = false;
    int anInt9029 = 0;
    int anInt9030;
    int anInt9031;
    int anInt9032;
    Class_ra_Sub3 aClass_ra_Sub3_9033;
    boolean aBoolean9034;
    boolean aBoolean9035;
    boolean aBoolean9036;

    @Override
    public int method623() {
	return this.anInt9023;
    }

    Class57_Sub2(Class_ra_Sub3 class_ra_sub3, int i, int i_0_, int[] is, int i_1_, int i_2_) {
	this.anInt9026 = 0;
	this.anInt9031 = 0;
	this.anInt9032 = 0;
	this.aClass_ra_Sub3_9033 = class_ra_sub3;
	this.anInt9023 = i;
	this.anInt9030 = i_0_;
	this.anInterface8_Impl1_Impl1_9025 = null;
	this.anInterface9_Impl2_9024 = class_ra_sub3.method5355(i, i_0_, false, is, i_1_, i_2_);
	this.anInterface9_Impl2_9024.method80(true, true);
	this.aBoolean9036 = this.anInterface9_Impl2_9024.method92() != i;
	this.aBoolean9034 = this.anInterface9_Impl2_9024.method76() != i_0_;
	this.aBoolean9035 = (!this.aBoolean9036 && this.anInterface9_Impl2_9024.method79());
	this.aBoolean9027 = (!this.aBoolean9034 && this.anInterface9_Impl2_9024.method79());
	this.aClass_ra_Sub3_9033.method5304(12);
    }

    @Override
    void method666(float f, float f_3_, float f_4_, float f_5_, float f_6_, float f_7_, int i, int i_8_, int i_9_, int i_10_) {
	this.aClass_ra_Sub3_9033.method5337();
	this.aClass_ra_Sub3_9033.method5374(i_9_);
	if (this.aBoolean9028) {
	    float f_11_ = method271();
	    float f_12_ = method626();
	    float f_13_ = (f_4_ - f) / f_11_;
	    float f_14_ = (f_5_ - f_3_) / f_11_;
	    float f_15_ = (f_6_ - f) / f_12_;
	    float f_16_ = (f_7_ - f_3_) / f_12_;
	    float f_17_ = f_15_ * this.anInt9029;
	    float f_18_ = f_16_ * this.anInt9029;
	    float f_19_ = f_13_ * this.anInt9026;
	    float f_20_ = f_14_ * this.anInt9026;
	    float f_21_ = -f_13_ * this.anInt9032;
	    float f_22_ = -f_14_ * this.anInt9032;
	    float f_23_ = -f_15_ * this.anInt9031;
	    float f_24_ = -f_16_ * this.anInt9031;
	    f = f + f_19_ + f_17_;
	    f_3_ = f_3_ + f_20_ + f_18_;
	    f_4_ = f_4_ + f_21_ + f_17_;
	    f_5_ = f_5_ + f_22_ + f_18_;
	    f_6_ = f_6_ + f_19_ + f_23_;
	    f_7_ = f_7_ + f_20_ + f_24_;
	}
	Class138 class138 = (this.aClass_ra_Sub3_9033.aClass138_8257);
	class138.anInterface9_Impl2_1537 = this.anInterface9_Impl2_9024;
	class138.method1526(i, i_8_);
	float f_25_ = this.aClass_ra_Sub3_9033.method4992((short) -3800).method545();
	float f_26_ = this.aClass_ra_Sub3_9033.method4992((short) -10695).method552();
	class138.aClass233_1540.method2172();
	class138.aClass233_1540.aFloatArray2594[0] = (f_4_ - f) * 2.0F / f_25_;
	class138.aClass233_1540.aFloatArray2594[1] = (f_5_ - f_3_) * 2.0F / f_26_;
	class138.aClass233_1540.aFloatArray2594[4] = (f_6_ - f) * 2.0F / f_25_;
	class138.aClass233_1540.aFloatArray2594[5] = (f_7_ - f_3_) * 2.0F / f_26_;
	class138.aClass233_1540.aFloatArray2594[12] = ((f + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_25_) - 1.0F;
	class138.aClass233_1540.aFloatArray2594[13] = ((f_3_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_26_) - 1.0F;
	class138.aClass233_1540.aFloatArray2594[14] = -1.0F;
	class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(this.anInt9023), this.anInterface9_Impl2_9024.method78(this.anInt9030), 1.0F, 1.0F);
	class138.anInterface7_Impl1_1538 = (this.aClass_ra_Sub3_9033.anInterface7_Impl1_8202);
	class138.anInt1543 = 0;
	class138.aClass153_1544 = (this.aClass_ra_Sub3_9033.aClass153_8356);
	class138.method1527();
    }

    @Override
    public void method655(int i, int i_27_, int i_28_, int i_29_) {
	this.anInt9026 = i;
	this.anInt9029 = i_27_;
	this.anInt9032 = i_28_;
	this.anInt9031 = i_29_;
	this.aBoolean9028 = (this.anInt9026 != 0 || this.anInt9029 != 0 || this.anInt9032 != 0 || this.anInt9031 != 0);
    }

    @Override
    public void method675(int i, int i_30_, int i_31_, int i_32_, int i_33_, int i_34_) {
	int[] is = this.aClass_ra_Sub3_9033.aq(i_33_, i_34_, i_31_, i_32_);
	if (is != null) {
	    for (int i_35_ = 0; i_35_ < is.length; i_35_++)
		is[i_35_] |= ~0xffffff;
	    method688(i, i_30_, i_31_, i_32_, is, 0, i_31_);
	}
    }

    @Override
    public void method632(int i, int i_36_, int i_37_) {
	int[] is = (this.aClass_ra_Sub3_9033.aq(i, i_36_, this.anInt9023, this.anInt9030));
	int[] is_38_ = new int[(this.anInt9023 * this.anInt9030)];
	this.anInterface9_Impl2_9024.method83(0, 0, this.anInt9023, this.anInt9030, is_38_, 0);
	if (i_37_ == 0) {
	    for (int i_39_ = 0; i_39_ < this.anInt9030; i_39_++) {
		int i_40_ = i_39_ * this.anInt9023;
		for (int i_41_ = 0; i_41_ < this.anInt9023; i_41_++)
		    is_38_[i_40_ + i_41_] = (is_38_[i_40_ + i_41_] & 0xffffff | is[i_40_ + i_41_] << 8 & ~0xffffff);
	    }
	} else if (i_37_ == 1) {
	    for (int i_42_ = 0; i_42_ < this.anInt9030; i_42_++) {
		int i_43_ = i_42_ * this.anInt9023;
		for (int i_44_ = 0; i_44_ < this.anInt9023; i_44_++)
		    is_38_[i_43_ + i_44_] = (is_38_[i_43_ + i_44_] & 0xffffff | is[i_43_ + i_44_] << 16 & ~0xffffff);
	    }
	} else if (i_37_ == 2) {
	    for (int i_45_ = 0; i_45_ < this.anInt9030; i_45_++) {
		int i_46_ = i_45_ * this.anInt9023;
		for (int i_47_ = 0; i_47_ < this.anInt9023; i_47_++)
		    is_38_[i_46_ + i_47_] = (is_38_[i_46_ + i_47_] & 0xffffff | is[i_46_ + i_47_] << 24 & ~0xffffff);
	    }
	} else if (i_37_ == 3) {
	    for (int i_48_ = 0; i_48_ < this.anInt9030; i_48_++) {
		int i_49_ = i_48_ * this.anInt9023;
		for (int i_50_ = 0; i_50_ < this.anInt9023; i_50_++)
		    is_38_[i_49_ + i_50_] = (is_38_[i_49_ + i_50_] & 0xffffff | (is[i_49_ + i_50_] != 0 ? -16777216 : 0));
	    }
	}
	method688(0, 0, this.anInt9023, this.anInt9030, is_38_, 0, this.anInt9023);
    }

    @Override
    public void method621(int i, int i_51_, int i_52_, int i_53_) {
	this.anInt9026 = i;
	this.anInt9029 = i_51_;
	this.anInt9032 = i_52_;
	this.anInt9031 = i_53_;
	this.aBoolean9028 = (this.anInt9026 != 0 || this.anInt9029 != 0 || this.anInt9032 != 0 || this.anInt9031 != 0);
    }

    @Override
    public int method272() {
	return (this.anInt9023 + this.anInt9026 + this.anInt9032);
    }

    @Override
    public void method628(int i, int i_54_, int i_55_) {
	int[] is = (this.aClass_ra_Sub3_9033.aq(i, i_54_, this.anInt9023, this.anInt9030));
	int[] is_56_ = new int[(this.anInt9023 * this.anInt9030)];
	this.anInterface9_Impl2_9024.method83(0, 0, this.anInt9023, this.anInt9030, is_56_, 0);
	if (i_55_ == 0) {
	    for (int i_57_ = 0; i_57_ < this.anInt9030; i_57_++) {
		int i_58_ = i_57_ * this.anInt9023;
		for (int i_59_ = 0; i_59_ < this.anInt9023; i_59_++)
		    is_56_[i_58_ + i_59_] = (is_56_[i_58_ + i_59_] & 0xffffff | is[i_58_ + i_59_] << 8 & ~0xffffff);
	    }
	} else if (i_55_ == 1) {
	    for (int i_60_ = 0; i_60_ < this.anInt9030; i_60_++) {
		int i_61_ = i_60_ * this.anInt9023;
		for (int i_62_ = 0; i_62_ < this.anInt9023; i_62_++)
		    is_56_[i_61_ + i_62_] = (is_56_[i_61_ + i_62_] & 0xffffff | is[i_61_ + i_62_] << 16 & ~0xffffff);
	    }
	} else if (i_55_ == 2) {
	    for (int i_63_ = 0; i_63_ < this.anInt9030; i_63_++) {
		int i_64_ = i_63_ * this.anInt9023;
		for (int i_65_ = 0; i_65_ < this.anInt9023; i_65_++)
		    is_56_[i_64_ + i_65_] = (is_56_[i_64_ + i_65_] & 0xffffff | is[i_64_ + i_65_] << 24 & ~0xffffff);
	    }
	} else if (i_55_ == 3) {
	    for (int i_66_ = 0; i_66_ < this.anInt9030; i_66_++) {
		int i_67_ = i_66_ * this.anInt9023;
		for (int i_68_ = 0; i_68_ < this.anInt9023; i_68_++)
		    is_56_[i_67_ + i_68_] = (is_56_[i_67_ + i_68_] & 0xffffff | (is[i_67_ + i_68_] != 0 ? -16777216 : 0));
	    }
	}
	method688(0, 0, this.anInt9023, this.anInt9030, is_56_, 0, this.anInt9023);
    }

    @Override
    public int method271() {
	return (this.anInt9023 + this.anInt9026 + this.anInt9032);
    }

    @Override
    public int method625() {
	return this.anInt9030;
    }

    @Override
    public int method626() {
	return (this.anInt9030 + this.anInt9029 + this.anInt9031);
    }

    @Override
    public Interface8_Impl1_Impl1 method627() {
	return this.anInterface8_Impl1_Impl1_9025;
    }

    @Override
    void method671(float f, float f_69_, float f_70_, float f_71_, float f_72_, float f_73_, int i, Class_ta class_ta, int i_74_, int i_75_) {
	this.aClass_ra_Sub3_9033.method5337();
	Class138 class138 = (this.aClass_ra_Sub3_9033.aClass138_8257);
	class138.anInterface9_Impl2_1537 = this.anInterface9_Impl2_9024;
	class138.method1526(1, -1);
	float f_76_ = this.aClass_ra_Sub3_9033.method4992((short) 8222).method545();
	float f_77_ = this.aClass_ra_Sub3_9033.method4992((short) -4129).method552();
	class138.aClass233_1540.method2172();
	if (this.aBoolean9028) {
	    float f_78_ = ((float) this.anInt9023 / (float) method271());
	    float f_79_ = ((float) this.anInt9030 / (float) method626());
	    class138.aClass233_1540.aFloatArray2594[0] = (f_70_ - f) * f_78_;
	    class138.aClass233_1540.aFloatArray2594[1] = (f_71_ - f_69_) * f_78_;
	    class138.aClass233_1540.aFloatArray2594[4] = (f_72_ - f) * f_79_;
	    class138.aClass233_1540.aFloatArray2594[5] = (f_73_ - f_69_) * f_79_;
	    class138.aClass233_1540.aFloatArray2594[12] = ((f + this.anInt9026) * f_78_ + this.aClass_ra_Sub3_9033.method5315());
	    class138.aClass233_1540.aFloatArray2594[13] = ((f_69_ + this.anInt9029) * f_79_ + this.aClass_ra_Sub3_9033.method5315());
	} else {
	    class138.aClass233_1540.aFloatArray2594[0] = f_70_ - f;
	    class138.aClass233_1540.aFloatArray2594[1] = f_71_ - f_69_;
	    class138.aClass233_1540.aFloatArray2594[4] = f_72_ - f;
	    class138.aClass233_1540.aFloatArray2594[5] = f_73_ - f_69_;
	    class138.aClass233_1540.aFloatArray2594[12] = f + this.aClass_ra_Sub3_9033.method5315();
	    class138.aClass233_1540.aFloatArray2594[13] = (f_69_ + this.aClass_ra_Sub3_9033.method5315());
	}
	Class233 class233 = this.aClass_ra_Sub3_9033.aClass233_8236;
	class233.method2172();
	class233.aFloatArray2594[0] = 2.0F / f_76_;
	class233.aFloatArray2594[5] = 2.0F / f_77_;
	class233.aFloatArray2594[12] = -1.0F;
	class233.aFloatArray2594[13] = -1.0F;
	class233.aFloatArray2594[14] = -1.0F;
	class138.aClass233_1540.method2144(class233);
	class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(this.anInt9023), this.anInterface9_Impl2_9024.method78(this.anInt9030), 1.0F, 1.0F);
	class138.anInterface7_Impl1_1538 = (this.aClass_ra_Sub3_9033.anInterface7_Impl1_8202);
	class138.anInt1543 = 0;
	class138.aClass153_1544 = (this.aClass_ra_Sub3_9033.aClass153_8356);
	Interface9_Impl2 interface9_impl2 = (((Class_ta_Sub3) class_ta).anInterface9_Impl2_8444);
	class138.anInterface9_Impl2_1536 = interface9_impl2;
	class138.aClass233_1542.method2172();
	class138.aClass233_1542.aFloatArray2594[0] = (f_70_ - f) * interface9_impl2.method77(1.0F);
	class138.aClass233_1542.aFloatArray2594[1] = (f_71_ - f_69_) * interface9_impl2.method77(1.0F);
	class138.aClass233_1542.aFloatArray2594[4] = (f_72_ - f) * interface9_impl2.method78(1.0F);
	class138.aClass233_1542.aFloatArray2594[5] = (f_73_ - f_69_) * interface9_impl2.method78(1.0F);
	class138.aClass233_1542.aFloatArray2594[12] = (f - i_74_) * interface9_impl2.method77(1.0F);
	class138.aClass233_1542.aFloatArray2594[13] = (f_69_ - i_75_) * interface9_impl2.method78(1.0F);
	class138.method1528();
    }

    @Override
    public void method631(int i, int i_80_, int i_81_, int i_82_, int i_83_) {
	this.aClass_ra_Sub3_9033.method5337();
	this.aClass_ra_Sub3_9033.method5374(i_83_);
	Class138 class138 = (this.aClass_ra_Sub3_9033.aClass138_8257);
	class138.anInterface9_Impl2_1537 = this.anInterface9_Impl2_9024;
	class138.method1526(i_81_, i_82_);
	i += this.anInt9026;
	i_80_ += this.anInt9029;
	float f = this.aClass_ra_Sub3_9033.method4992((short) -1641).method545();
	float f_84_ = this.aClass_ra_Sub3_9033.method4992((short) -380).method552();
	class138.aClass233_1540.method2147(this.anInt9023 * 2.0F / f, this.anInt9030 * 2.0F / f_84_, 1.0F, 1.0F);
	class138.aClass233_1540.aFloatArray2594[12] = ((i + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f) - 1.0F;
	class138.aClass233_1540.aFloatArray2594[13] = ((i_80_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_84_) - 1.0F;
	class138.aClass233_1540.aFloatArray2594[14] = -1.0F;
	class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(this.anInt9023), this.anInterface9_Impl2_9024.method78(this.anInt9030), 1.0F, 1.0F);
	class138.anInterface7_Impl1_1538 = (this.aClass_ra_Sub3_9033.anInterface7_Impl1_8202);
	class138.anInt1543 = 0;
	class138.aClass153_1544 = (this.aClass_ra_Sub3_9033.aClass153_8356);
	class138.method1527();
    }

    @Override
    public void method650(int i, int i_85_, Class_ta class_ta, int i_86_, int i_87_) {
	this.aClass_ra_Sub3_9033.method5337();
	Class138 class138 = (this.aClass_ra_Sub3_9033.aClass138_8257);
	class138.anInterface9_Impl2_1537 = this.anInterface9_Impl2_9024;
	class138.method1526(1, -1);
	i += this.anInt9026;
	i_85_ += this.anInt9029;
	float f = this.aClass_ra_Sub3_9033.method4992((short) -9173).method545();
	float f_88_ = this.aClass_ra_Sub3_9033.method4992((short) -16185).method552();
	class138.aClass233_1540.method2147(this.anInt9023 * 2.0F / f, this.anInt9030 * 2.0F / f_88_, 1.0F, 1.0F);
	class138.aClass233_1540.aFloatArray2594[12] = ((i + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f) - 1.0F;
	class138.aClass233_1540.aFloatArray2594[13] = ((i_85_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_88_) - 1.0F;
	class138.aClass233_1540.aFloatArray2594[14] = -1.0F;
	class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(this.anInt9023), this.anInterface9_Impl2_9024.method78(this.anInt9030), 1.0F, 1.0F);
	class138.anInterface7_Impl1_1538 = (this.aClass_ra_Sub3_9033.anInterface7_Impl1_8202);
	class138.anInt1543 = 0;
	class138.aClass153_1544 = (this.aClass_ra_Sub3_9033.aClass153_8356);
	Interface9_Impl2 interface9_impl2 = (((Class_ta_Sub3) class_ta).anInterface9_Impl2_8444);
	class138.anInterface9_Impl2_1536 = interface9_impl2;
	class138.aClass233_1542.method2147(interface9_impl2.method77(this.anInt9023), interface9_impl2.method78(this.anInt9030), 1.0F, 1.0F);
	class138.aClass233_1542.aFloatArray2594[12] = interface9_impl2.method77(i - i_86_);
	class138.aClass233_1542.aFloatArray2594[13] = interface9_impl2.method78(i_85_ - i_87_);
	class138.method1528();
    }

    @Override
    void method635(int i, int i_89_, int i_90_, int i_91_, int i_92_, int i_93_, int i_94_, int i_95_) {
	this.aClass_ra_Sub3_9033.method5337();
	this.aClass_ra_Sub3_9033.method5374(i_94_);
	Class138 class138 = (this.aClass_ra_Sub3_9033.aClass138_8257);
	class138.anInterface9_Impl2_1537 = this.anInterface9_Impl2_9024;
	class138.method1526(i_92_, i_93_);
	if (this.aBoolean9028) {
	    i_90_ = i_90_ * this.anInt9023 / method271();
	    i_91_ = i_91_ * this.anInt9030 / method626();
	    i += (this.anInt9026 * i_90_ / this.anInt9023);
	    i_89_ += (this.anInt9029 * i_91_ / this.anInt9030);
	}
	float f = this.aClass_ra_Sub3_9033.method4992((short) 7730).method545();
	float f_96_ = this.aClass_ra_Sub3_9033.method4992((short) -15790).method552();
	class138.aClass233_1540.method2147(i_90_ * 2.0F / f, i_91_ * 2.0F / f_96_, 1.0F, 1.0F);
	class138.aClass233_1540.aFloatArray2594[12] = ((i + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f) - 1.0F;
	class138.aClass233_1540.aFloatArray2594[13] = ((i_89_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_96_) - 1.0F;
	class138.aClass233_1540.aFloatArray2594[14] = -1.0F;
	class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(this.anInt9023), this.anInterface9_Impl2_9024.method78(this.anInt9030), 1.0F, 1.0F);
	class138.anInterface7_Impl1_1538 = (this.aClass_ra_Sub3_9033.anInterface7_Impl1_8202);
	class138.anInt1543 = 0;
	class138.aClass153_1544 = (this.aClass_ra_Sub3_9033.aClass153_8356);
	class138.method1527();
    }

    @Override
    public void method622(int[] is) {
	is[0] = this.anInt9026;
	is[1] = this.anInt9029;
	is[2] = this.anInt9032;
	is[3] = this.anInt9031;
    }

    @Override
    void method642(float f, float f_97_, float f_98_, float f_99_, float f_100_, float f_101_, int i, int i_102_, int i_103_, int i_104_) {
	this.aClass_ra_Sub3_9033.method5337();
	this.aClass_ra_Sub3_9033.method5374(i_103_);
	if (this.aBoolean9028) {
	    float f_105_ = method271();
	    float f_106_ = method626();
	    float f_107_ = (f_98_ - f) / f_105_;
	    float f_108_ = (f_99_ - f_97_) / f_105_;
	    float f_109_ = (f_100_ - f) / f_106_;
	    float f_110_ = (f_101_ - f_97_) / f_106_;
	    float f_111_ = f_109_ * this.anInt9029;
	    float f_112_ = f_110_ * this.anInt9029;
	    float f_113_ = f_107_ * this.anInt9026;
	    float f_114_ = f_108_ * this.anInt9026;
	    float f_115_ = -f_107_ * this.anInt9032;
	    float f_116_ = -f_108_ * this.anInt9032;
	    float f_117_ = -f_109_ * this.anInt9031;
	    float f_118_ = -f_110_ * this.anInt9031;
	    f = f + f_113_ + f_111_;
	    f_97_ = f_97_ + f_114_ + f_112_;
	    f_98_ = f_98_ + f_115_ + f_111_;
	    f_99_ = f_99_ + f_116_ + f_112_;
	    f_100_ = f_100_ + f_113_ + f_117_;
	    f_101_ = f_101_ + f_114_ + f_118_;
	}
	Class138 class138 = (this.aClass_ra_Sub3_9033.aClass138_8257);
	class138.anInterface9_Impl2_1537 = this.anInterface9_Impl2_9024;
	class138.method1526(i, i_102_);
	float f_119_ = this.aClass_ra_Sub3_9033.method4992((short) -6090).method545();
	float f_120_ = this.aClass_ra_Sub3_9033.method4992((short) 1699).method552();
	class138.aClass233_1540.method2172();
	class138.aClass233_1540.aFloatArray2594[0] = (f_98_ - f) * 2.0F / f_119_;
	class138.aClass233_1540.aFloatArray2594[1] = (f_99_ - f_97_) * 2.0F / f_120_;
	class138.aClass233_1540.aFloatArray2594[4] = (f_100_ - f) * 2.0F / f_119_;
	class138.aClass233_1540.aFloatArray2594[5] = (f_101_ - f_97_) * 2.0F / f_120_;
	class138.aClass233_1540.aFloatArray2594[12] = ((f + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_119_) - 1.0F;
	class138.aClass233_1540.aFloatArray2594[13] = ((f_97_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_120_) - 1.0F;
	class138.aClass233_1540.aFloatArray2594[14] = -1.0F;
	class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(this.anInt9023), this.anInterface9_Impl2_9024.method78(this.anInt9030), 1.0F, 1.0F);
	class138.anInterface7_Impl1_1538 = (this.aClass_ra_Sub3_9033.anInterface7_Impl1_8202);
	class138.anInt1543 = 0;
	class138.aClass153_1544 = (this.aClass_ra_Sub3_9033.aClass153_8356);
	class138.method1527();
    }

    @Override
    void method644(float f, float f_121_, float f_122_, float f_123_, float f_124_, float f_125_, int i, Class_ta class_ta, int i_126_, int i_127_) {
	this.aClass_ra_Sub3_9033.method5337();
	Class138 class138 = (this.aClass_ra_Sub3_9033.aClass138_8257);
	class138.anInterface9_Impl2_1537 = this.anInterface9_Impl2_9024;
	class138.method1526(1, -1);
	float f_128_ = this.aClass_ra_Sub3_9033.method4992((short) 1715).method545();
	float f_129_ = this.aClass_ra_Sub3_9033.method4992((short) 551).method552();
	class138.aClass233_1540.method2172();
	if (this.aBoolean9028) {
	    float f_130_ = ((float) this.anInt9023 / (float) method271());
	    float f_131_ = ((float) this.anInt9030 / (float) method626());
	    class138.aClass233_1540.aFloatArray2594[0] = (f_122_ - f) * f_130_;
	    class138.aClass233_1540.aFloatArray2594[1] = (f_123_ - f_121_) * f_130_;
	    class138.aClass233_1540.aFloatArray2594[4] = (f_124_ - f) * f_131_;
	    class138.aClass233_1540.aFloatArray2594[5] = (f_125_ - f_121_) * f_131_;
	    class138.aClass233_1540.aFloatArray2594[12] = ((f + this.anInt9026) * f_130_ + this.aClass_ra_Sub3_9033.method5315());
	    class138.aClass233_1540.aFloatArray2594[13] = ((f_121_ + this.anInt9029) * f_131_ + this.aClass_ra_Sub3_9033.method5315());
	} else {
	    class138.aClass233_1540.aFloatArray2594[0] = f_122_ - f;
	    class138.aClass233_1540.aFloatArray2594[1] = f_123_ - f_121_;
	    class138.aClass233_1540.aFloatArray2594[4] = f_124_ - f;
	    class138.aClass233_1540.aFloatArray2594[5] = f_125_ - f_121_;
	    class138.aClass233_1540.aFloatArray2594[12] = f + this.aClass_ra_Sub3_9033.method5315();
	    class138.aClass233_1540.aFloatArray2594[13] = (f_121_ + this.aClass_ra_Sub3_9033.method5315());
	}
	Class233 class233 = this.aClass_ra_Sub3_9033.aClass233_8236;
	class233.method2172();
	class233.aFloatArray2594[0] = 2.0F / f_128_;
	class233.aFloatArray2594[5] = 2.0F / f_129_;
	class233.aFloatArray2594[12] = -1.0F;
	class233.aFloatArray2594[13] = -1.0F;
	class233.aFloatArray2594[14] = -1.0F;
	class138.aClass233_1540.method2144(class233);
	class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(this.anInt9023), this.anInterface9_Impl2_9024.method78(this.anInt9030), 1.0F, 1.0F);
	class138.anInterface7_Impl1_1538 = (this.aClass_ra_Sub3_9033.anInterface7_Impl1_8202);
	class138.anInt1543 = 0;
	class138.aClass153_1544 = (this.aClass_ra_Sub3_9033.aClass153_8356);
	Interface9_Impl2 interface9_impl2 = (((Class_ta_Sub3) class_ta).anInterface9_Impl2_8444);
	class138.anInterface9_Impl2_1536 = interface9_impl2;
	class138.aClass233_1542.method2172();
	class138.aClass233_1542.aFloatArray2594[0] = (f_122_ - f) * interface9_impl2.method77(1.0F);
	class138.aClass233_1542.aFloatArray2594[1] = (f_123_ - f_121_) * interface9_impl2.method77(1.0F);
	class138.aClass233_1542.aFloatArray2594[4] = (f_124_ - f) * interface9_impl2.method78(1.0F);
	class138.aClass233_1542.aFloatArray2594[5] = (f_125_ - f_121_) * interface9_impl2.method78(1.0F);
	class138.aClass233_1542.aFloatArray2594[12] = (f - i_126_) * interface9_impl2.method77(1.0F);
	class138.aClass233_1542.aFloatArray2594[13] = (f_121_ - i_127_) * interface9_impl2.method78(1.0F);
	class138.method1528();
    }

    Class57_Sub2(Class_ra_Sub3 class_ra_sub3, Interface9_Impl2 interface9_impl2, int i, int i_132_) {
	this.anInt9026 = 0;
	this.anInt9031 = 0;
	this.anInt9032 = 0;
	this.aClass_ra_Sub3_9033 = class_ra_sub3;
	this.anInt9023 = i;
	this.anInt9030 = i_132_;
	this.anInterface9_Impl2_9024 = interface9_impl2;
	this.anInterface8_Impl1_Impl1_9025 = null;
	this.aBoolean9036 = this.anInterface9_Impl2_9024.method92() != i;
	this.aBoolean9034 = (this.anInterface9_Impl2_9024.method76() != i_132_);
	this.aBoolean9035 = (!this.aBoolean9036 && this.anInterface9_Impl2_9024.method79());
	this.aBoolean9027 = (!this.aBoolean9034 && this.anInterface9_Impl2_9024.method79());
	this.aClass_ra_Sub3_9033.method5304(12);
    }

    @Override
    public void method649(int i, int i_133_, int i_134_, int i_135_, int i_136_) {
	this.aClass_ra_Sub3_9033.method5337();
	this.aClass_ra_Sub3_9033.method5374(i_136_);
	Class138 class138 = (this.aClass_ra_Sub3_9033.aClass138_8257);
	class138.anInterface9_Impl2_1537 = this.anInterface9_Impl2_9024;
	class138.method1526(i_134_, i_135_);
	i += this.anInt9026;
	i_133_ += this.anInt9029;
	float f = this.aClass_ra_Sub3_9033.method4992((short) -1445).method545();
	float f_137_ = this.aClass_ra_Sub3_9033.method4992((short) 11490).method552();
	class138.aClass233_1540.method2147(this.anInt9023 * 2.0F / f, this.anInt9030 * 2.0F / f_137_, 1.0F, 1.0F);
	class138.aClass233_1540.aFloatArray2594[12] = ((i + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f) - 1.0F;
	class138.aClass233_1540.aFloatArray2594[13] = ((i_133_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_137_) - 1.0F;
	class138.aClass233_1540.aFloatArray2594[14] = -1.0F;
	class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(this.anInt9023), this.anInterface9_Impl2_9024.method78(this.anInt9030), 1.0F, 1.0F);
	class138.anInterface7_Impl1_1538 = (this.aClass_ra_Sub3_9033.anInterface7_Impl1_8202);
	class138.anInt1543 = 0;
	class138.aClass153_1544 = (this.aClass_ra_Sub3_9033.aClass153_8356);
	class138.method1527();
    }

    @Override
    public Interface8_Impl1_Impl1 method647() {
	return this.anInterface8_Impl1_Impl1_9025;
    }

    @Override
    public void method643(int i, int i_138_, int i_139_, int i_140_, int i_141_) {
	this.aClass_ra_Sub3_9033.method5337();
	this.aClass_ra_Sub3_9033.method5374(i_141_);
	Class138 class138 = (this.aClass_ra_Sub3_9033.aClass138_8257);
	class138.anInterface9_Impl2_1537 = this.anInterface9_Impl2_9024;
	class138.method1526(i_139_, i_140_);
	i += this.anInt9026;
	i_138_ += this.anInt9029;
	float f = this.aClass_ra_Sub3_9033.method4992((short) -24161).method545();
	float f_142_ = this.aClass_ra_Sub3_9033.method4992((short) 11826).method552();
	class138.aClass233_1540.method2147(this.anInt9023 * 2.0F / f, this.anInt9030 * 2.0F / f_142_, 1.0F, 1.0F);
	class138.aClass233_1540.aFloatArray2594[12] = ((i + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f) - 1.0F;
	class138.aClass233_1540.aFloatArray2594[13] = ((i_138_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_142_) - 1.0F;
	class138.aClass233_1540.aFloatArray2594[14] = -1.0F;
	class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(this.anInt9023), this.anInterface9_Impl2_9024.method78(this.anInt9030), 1.0F, 1.0F);
	class138.anInterface7_Impl1_1538 = (this.aClass_ra_Sub3_9033.anInterface7_Impl1_8202);
	class138.anInt1543 = 0;
	class138.aClass153_1544 = (this.aClass_ra_Sub3_9033.aClass153_8356);
	class138.method1527();
    }

    @Override
    public void method648(int i, int i_143_, int i_144_, int i_145_, int i_146_) {
	this.aClass_ra_Sub3_9033.method5337();
	this.aClass_ra_Sub3_9033.method5374(i_146_);
	Class138 class138 = (this.aClass_ra_Sub3_9033.aClass138_8257);
	class138.anInterface9_Impl2_1537 = this.anInterface9_Impl2_9024;
	class138.method1526(i_144_, i_145_);
	i += this.anInt9026;
	i_143_ += this.anInt9029;
	float f = this.aClass_ra_Sub3_9033.method4992((short) 14710).method545();
	float f_147_ = this.aClass_ra_Sub3_9033.method4992((short) 15315).method552();
	class138.aClass233_1540.method2147(this.anInt9023 * 2.0F / f, this.anInt9030 * 2.0F / f_147_, 1.0F, 1.0F);
	class138.aClass233_1540.aFloatArray2594[12] = ((i + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f) - 1.0F;
	class138.aClass233_1540.aFloatArray2594[13] = ((i_143_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_147_) - 1.0F;
	class138.aClass233_1540.aFloatArray2594[14] = -1.0F;
	class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(this.anInt9023), this.anInterface9_Impl2_9024.method78(this.anInt9030), 1.0F, 1.0F);
	class138.anInterface7_Impl1_1538 = (this.aClass_ra_Sub3_9033.anInterface7_Impl1_8202);
	class138.anInt1543 = 0;
	class138.aClass153_1544 = (this.aClass_ra_Sub3_9033.aClass153_8356);
	class138.method1527();
    }

    @Override
    public void method674(int i, int i_148_, int i_149_, int i_150_, int i_151_) {
	this.aClass_ra_Sub3_9033.method5337();
	this.aClass_ra_Sub3_9033.method5374(i_151_);
	Class138 class138 = (this.aClass_ra_Sub3_9033.aClass138_8257);
	class138.anInterface9_Impl2_1537 = this.anInterface9_Impl2_9024;
	class138.method1526(i_149_, i_150_);
	i += this.anInt9026;
	i_148_ += this.anInt9029;
	float f = this.aClass_ra_Sub3_9033.method4992((short) 24362).method545();
	float f_152_ = this.aClass_ra_Sub3_9033.method4992((short) 10959).method552();
	class138.aClass233_1540.method2147(this.anInt9023 * 2.0F / f, this.anInt9030 * 2.0F / f_152_, 1.0F, 1.0F);
	class138.aClass233_1540.aFloatArray2594[12] = ((i + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f) - 1.0F;
	class138.aClass233_1540.aFloatArray2594[13] = ((i_148_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_152_) - 1.0F;
	class138.aClass233_1540.aFloatArray2594[14] = -1.0F;
	class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(this.anInt9023), this.anInterface9_Impl2_9024.method78(this.anInt9030), 1.0F, 1.0F);
	class138.anInterface7_Impl1_1538 = (this.aClass_ra_Sub3_9033.anInterface7_Impl1_8202);
	class138.anInt1543 = 0;
	class138.aClass153_1544 = (this.aClass_ra_Sub3_9033.aClass153_8356);
	class138.method1527();
    }

    @Override
    void method676(float f, float f_153_, float f_154_, float f_155_, float f_156_, float f_157_, int i, int i_158_, int i_159_, int i_160_) {
	this.aClass_ra_Sub3_9033.method5337();
	this.aClass_ra_Sub3_9033.method5374(i_159_);
	if (this.aBoolean9028) {
	    float f_161_ = method271();
	    float f_162_ = method626();
	    float f_163_ = (f_154_ - f) / f_161_;
	    float f_164_ = (f_155_ - f_153_) / f_161_;
	    float f_165_ = (f_156_ - f) / f_162_;
	    float f_166_ = (f_157_ - f_153_) / f_162_;
	    float f_167_ = f_165_ * this.anInt9029;
	    float f_168_ = f_166_ * this.anInt9029;
	    float f_169_ = f_163_ * this.anInt9026;
	    float f_170_ = f_164_ * this.anInt9026;
	    float f_171_ = -f_163_ * this.anInt9032;
	    float f_172_ = -f_164_ * this.anInt9032;
	    float f_173_ = -f_165_ * this.anInt9031;
	    float f_174_ = -f_166_ * this.anInt9031;
	    f = f + f_169_ + f_167_;
	    f_153_ = f_153_ + f_170_ + f_168_;
	    f_154_ = f_154_ + f_171_ + f_167_;
	    f_155_ = f_155_ + f_172_ + f_168_;
	    f_156_ = f_156_ + f_169_ + f_173_;
	    f_157_ = f_157_ + f_170_ + f_174_;
	}
	Class138 class138 = (this.aClass_ra_Sub3_9033.aClass138_8257);
	class138.anInterface9_Impl2_1537 = this.anInterface9_Impl2_9024;
	class138.method1526(i, i_158_);
	float f_175_ = this.aClass_ra_Sub3_9033.method4992((short) -15231).method545();
	float f_176_ = this.aClass_ra_Sub3_9033.method4992((short) -4660).method552();
	class138.aClass233_1540.method2172();
	class138.aClass233_1540.aFloatArray2594[0] = (f_154_ - f) * 2.0F / f_175_;
	class138.aClass233_1540.aFloatArray2594[1] = (f_155_ - f_153_) * 2.0F / f_176_;
	class138.aClass233_1540.aFloatArray2594[4] = (f_156_ - f) * 2.0F / f_175_;
	class138.aClass233_1540.aFloatArray2594[5] = (f_157_ - f_153_) * 2.0F / f_176_;
	class138.aClass233_1540.aFloatArray2594[12] = ((f + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_175_) - 1.0F;
	class138.aClass233_1540.aFloatArray2594[13] = ((f_153_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_176_) - 1.0F;
	class138.aClass233_1540.aFloatArray2594[14] = -1.0F;
	class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(this.anInt9023), this.anInterface9_Impl2_9024.method78(this.anInt9030), 1.0F, 1.0F);
	class138.anInterface7_Impl1_1538 = (this.aClass_ra_Sub3_9033.anInterface7_Impl1_8202);
	class138.anInt1543 = 0;
	class138.aClass153_1544 = (this.aClass_ra_Sub3_9033.aClass153_8356);
	class138.method1527();
    }

    @Override
    public int method653() {
	return (this.anInt9030 + this.anInt9029 + this.anInt9031);
    }

    @Override
    void method651(int i, int i_177_, int i_178_, int i_179_, int i_180_, int i_181_, int i_182_, int i_183_) {
	this.aClass_ra_Sub3_9033.method5337();
	this.aClass_ra_Sub3_9033.method5374(i_182_);
	Class138 class138 = (this.aClass_ra_Sub3_9033.aClass138_8257);
	class138.anInterface9_Impl2_1537 = this.anInterface9_Impl2_9024;
	class138.method1526(i_180_, i_181_);
	if (this.aBoolean9028) {
	    i_178_ = i_178_ * this.anInt9023 / method271();
	    i_179_ = i_179_ * this.anInt9030 / method626();
	    i += (this.anInt9026 * i_178_ / this.anInt9023);
	    i_177_ += (this.anInt9029 * i_179_ / this.anInt9030);
	}
	float f = this.aClass_ra_Sub3_9033.method4992((short) -12376).method545();
	float f_184_ = this.aClass_ra_Sub3_9033.method4992((short) -8111).method552();
	class138.aClass233_1540.method2147(i_178_ * 2.0F / f, i_179_ * 2.0F / f_184_, 1.0F, 1.0F);
	class138.aClass233_1540.aFloatArray2594[12] = ((i + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f) - 1.0F;
	class138.aClass233_1540.aFloatArray2594[13] = ((i_177_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_184_) - 1.0F;
	class138.aClass233_1540.aFloatArray2594[14] = -1.0F;
	class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(this.anInt9023), this.anInterface9_Impl2_9024.method78(this.anInt9030), 1.0F, 1.0F);
	class138.anInterface7_Impl1_1538 = (this.aClass_ra_Sub3_9033.anInterface7_Impl1_8202);
	class138.anInt1543 = 0;
	class138.aClass153_1544 = (this.aClass_ra_Sub3_9033.aClass153_8356);
	class138.method1527();
    }

    @Override
    void method652(int i, int i_185_, int i_186_, int i_187_, int i_188_, int i_189_, int i_190_, int i_191_) {
	this.aClass_ra_Sub3_9033.method5337();
	this.aClass_ra_Sub3_9033.method5374(i_190_);
	Class138 class138 = (this.aClass_ra_Sub3_9033.aClass138_8257);
	class138.anInterface9_Impl2_1537 = this.anInterface9_Impl2_9024;
	class138.method1526(i_188_, i_189_);
	if (this.aBoolean9028) {
	    i_186_ = i_186_ * this.anInt9023 / method271();
	    i_187_ = i_187_ * this.anInt9030 / method626();
	    i += (this.anInt9026 * i_186_ / this.anInt9023);
	    i_185_ += (this.anInt9029 * i_187_ / this.anInt9030);
	}
	float f = this.aClass_ra_Sub3_9033.method4992((short) 3413).method545();
	float f_192_ = this.aClass_ra_Sub3_9033.method4992((short) -15345).method552();
	class138.aClass233_1540.method2147(i_186_ * 2.0F / f, i_187_ * 2.0F / f_192_, 1.0F, 1.0F);
	class138.aClass233_1540.aFloatArray2594[12] = ((i + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f) - 1.0F;
	class138.aClass233_1540.aFloatArray2594[13] = ((i_185_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_192_) - 1.0F;
	class138.aClass233_1540.aFloatArray2594[14] = -1.0F;
	class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(this.anInt9023), this.anInterface9_Impl2_9024.method78(this.anInt9030), 1.0F, 1.0F);
	class138.anInterface7_Impl1_1538 = (this.aClass_ra_Sub3_9033.anInterface7_Impl1_8202);
	class138.anInt1543 = 0;
	class138.aClass153_1544 = (this.aClass_ra_Sub3_9033.aClass153_8356);
	class138.method1527();
    }

    @Override
    public void method665(int i, int i_193_, int i_194_, int i_195_, int i_196_, int i_197_) {
	int[] is = this.aClass_ra_Sub3_9033.aq(i_196_, i_197_, i_194_, i_195_);
	if (is != null) {
	    for (int i_198_ = 0; i_198_ < is.length; i_198_++)
		is[i_198_] |= ~0xffffff;
	    method688(i, i_193_, i_194_, i_195_, is, 0, i_194_);
	}
    }

    @Override
    public void method624(int i, int i_199_, int i_200_, int i_201_, int i_202_, int i_203_) {
	int[] is = this.aClass_ra_Sub3_9033.aq(i_202_, i_203_, i_200_, i_201_);
	if (is != null) {
	    for (int i_204_ = 0; i_204_ < is.length; i_204_++)
		is[i_204_] |= ~0xffffff;
	    method688(i, i_199_, i_200_, i_201_, is, 0, i_200_);
	}
    }

    @Override
    public Interface8_Impl1_Impl1 method646() {
	return this.anInterface8_Impl1_Impl1_9025;
    }

    @Override
    public void method656(int[] is) {
	is[0] = this.anInt9026;
	is[1] = this.anInt9029;
	is[2] = this.anInt9032;
	is[3] = this.anInt9031;
    }

    @Override
    public void method677(int[] is) {
	is[0] = this.anInt9026;
	is[1] = this.anInt9029;
	is[2] = this.anInt9032;
	is[3] = this.anInt9031;
    }

    Class57_Sub2(Class_ra_Sub3 class_ra_sub3, Interface9_Impl2 interface9_impl2) {
	this(class_ra_sub3, interface9_impl2, interface9_impl2.method92(), interface9_impl2.method76());
    }

    @Override
    public int method630() {
	return this.anInt9023;
    }

    @Override
    public int method658() {
	return this.anInt9023;
    }

    Class57_Sub2(Class_ra_Sub3 class_ra_sub3, int i, int i_205_, boolean bool, boolean bool_206_) {
	this.anInt9026 = 0;
	this.anInt9031 = 0;
	this.anInt9032 = 0;
	this.aClass_ra_Sub3_9033 = class_ra_sub3;
	this.anInt9023 = i;
	this.anInt9030 = i_205_;
	if (bool_206_) {
	    Interface9_Impl2_Impl1 interface9_impl2_impl1 = class_ra_sub3.method5356((bool ? Class55.aClass55_557 : Class55.aClass55_560), Class77.aClass77_717, i, i_205_);
	    this.anInterface8_Impl1_Impl1_9025 = interface9_impl2_impl1.method117(0);
	    this.anInterface9_Impl2_9024 = interface9_impl2_impl1;
	} else {
	    this.anInterface9_Impl2_9024 = class_ra_sub3.method5353((bool ? Class55.aClass55_557 : Class55.aClass55_560), Class77.aClass77_717, i, i_205_);
	    this.anInterface8_Impl1_Impl1_9025 = null;
	}
	this.anInterface9_Impl2_9024.method80(true, true);
	this.aBoolean9036 = this.anInterface9_Impl2_9024.method92() != i;
	this.aBoolean9034 = (this.anInterface9_Impl2_9024.method76() != i_205_);
	this.aBoolean9035 = (!this.aBoolean9036 && this.anInterface9_Impl2_9024.method79());
	this.aBoolean9027 = (!this.aBoolean9034 && this.anInterface9_Impl2_9024.method79());
	this.aClass_ra_Sub3_9033.method5304(12);
    }

    @Override
    public void method662(int i, int i_207_, int i_208_, int i_209_, int i_210_, int i_211_, int i_212_) {
	this.aClass_ra_Sub3_9033.method5337();
	this.aClass_ra_Sub3_9033.method5374(i_212_);
	Class138 class138 = (this.aClass_ra_Sub3_9033.aClass138_8257);
	class138.anInterface9_Impl2_1537 = this.anInterface9_Impl2_9024;
	class138.method1526(i_210_, i_211_);
	float f = this.aClass_ra_Sub3_9033.method4992((short) 4017).method545();
	float f_213_ = this.aClass_ra_Sub3_9033.method4992((short) -4315).method552();
	class138.anInterface7_Impl1_1538 = (this.aClass_ra_Sub3_9033.anInterface7_Impl1_8202);
	class138.anInt1543 = 0;
	class138.aClass153_1544 = (this.aClass_ra_Sub3_9033.aClass153_8356);
	boolean bool = (this.aBoolean9027 && this.anInt9029 == 0 && this.anInt9031 == 0);
	boolean bool_214_ = (this.aBoolean9035 && this.anInt9026 == 0 && this.anInt9032 == 0);
	if (bool_214_ & bool) {
	    class138.aClass233_1540.method2147(i_208_ * 2.0F / f, i_209_ * 2.0F / f_213_, 1.0F, 1.0F);
	    class138.aClass233_1540.aFloatArray2594[12] = ((i + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f) - 1.0F;
	    class138.aClass233_1540.aFloatArray2594[13] = ((i_207_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_213_) - 1.0F;
	    class138.aClass233_1540.aFloatArray2594[14] = -1.0F;
	    class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(i_208_), this.anInterface9_Impl2_9024.method78(i_209_), 1.0F, 1.0F);
	    class138.method1527();
	} else if (bool_214_) {
	    int i_215_ = i_207_ + i_209_;
	    int i_216_ = method626();
	    class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(i_208_), this.anInterface9_Impl2_9024.method78(this.anInt9030), 1.0F, 1.0F);
	    int i_217_ = i_207_ + this.anInt9029;
	    for (int i_218_ = i_217_ + this.anInt9030; i_218_ <= i_215_; i_218_ += i_216_) {
		class138.aClass233_1540.method2147(i_208_ * 2.0F / f, (this.anInt9030 * 2.0F / f_213_), 1.0F, 1.0F);
		class138.aClass233_1540.aFloatArray2594[12] = (i + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f - 1.0F;
		class138.aClass233_1540.aFloatArray2594[13] = ((i_217_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_213_) - 1.0F;
		class138.aClass233_1540.aFloatArray2594[14] = -1.0F;
		class138.method1527();
		i_217_ += i_216_;
	    }
	    if (i_217_ < i_215_) {
		int i_219_ = i_215_ - i_217_;
		class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(i_208_), this.anInterface9_Impl2_9024.method78(i_219_), 1.0F, 1.0F);
		class138.aClass233_1540.method2147(i_208_ * 2.0F / f, (i_219_ * 2.0F / f_213_), 1.0F, 1.0F);
		class138.aClass233_1540.aFloatArray2594[12] = (i + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f - 1.0F;
		class138.aClass233_1540.aFloatArray2594[13] = ((i_217_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_213_) - 1.0F;
		class138.aClass233_1540.aFloatArray2594[14] = -1.0F;
		class138.method1527();
	    }
	} else if (bool) {
	    int i_220_ = i + i_208_;
	    int i_221_ = method271();
	    class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(this.anInt9023), this.anInterface9_Impl2_9024.method78(i_209_), 1.0F, 1.0F);
	    int i_222_ = i + this.anInt9026;
	    for (int i_223_ = i_222_ + this.anInt9023; i_223_ <= i_220_; i_223_ += i_221_) {
		class138.aClass233_1540.method2147(this.anInt9023 * 2.0F / f, i_209_ * 2.0F / f_213_, 1.0F, 1.0F);
		class138.aClass233_1540.aFloatArray2594[12] = ((i_222_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f) - 1.0F;
		class138.aClass233_1540.aFloatArray2594[13] = ((i_207_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_213_) - 1.0F;
		class138.aClass233_1540.aFloatArray2594[14] = -1.0F;
		class138.method1527();
		i_222_ += i_221_;
	    }
	    if (i_222_ < i_220_) {
		int i_224_ = i_220_ - i_222_;
		class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(i_224_), this.anInterface9_Impl2_9024.method78(i_209_), 1.0F, 1.0F);
		class138.aClass233_1540.method2147(i_224_ * 2.0F / f, (i_209_ * 2.0F / f_213_), 1.0F, 1.0F);
		class138.aClass233_1540.aFloatArray2594[12] = ((i_222_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f) - 1.0F;
		class138.aClass233_1540.aFloatArray2594[13] = ((i_207_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_213_) - 1.0F;
		class138.aClass233_1540.aFloatArray2594[14] = -1.0F;
		class138.method1527();
	    }
	} else {
	    int i_225_ = i_207_ + i_209_;
	    int i_226_ = i + i_208_;
	    int i_227_ = method271();
	    int i_228_ = method626();
	    int i_229_ = i_207_ + this.anInt9029;
	    for (int i_230_ = i_229_ + this.anInt9030; i_230_ <= i_225_; i_230_ += i_228_) {
		class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(this.anInt9023), this.anInterface9_Impl2_9024.method78(this.anInt9030), 1.0F, 1.0F);
		int i_231_ = i + this.anInt9026;
		for (int i_232_ = i_231_ + this.anInt9023; i_232_ <= i_226_; i_232_ += i_227_) {
		    class138.aClass233_1540.method2147(this.anInt9023 * 2.0F / f, (this.anInt9030 * 2.0F / f_213_), 1.0F, 1.0F);
		    class138.aClass233_1540.aFloatArray2594[12] = (i_231_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f - 1.0F;
		    class138.aClass233_1540.aFloatArray2594[13] = (i_229_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_213_ - 1.0F;
		    class138.aClass233_1540.aFloatArray2594[14] = -1.0F;
		    class138.method1527();
		    i_231_ += i_227_;
		}
		if (i_231_ < i_226_) {
		    int i_233_ = i_226_ - i_231_;
		    class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(i_233_), (this.anInterface9_Impl2_9024.method78(this.anInt9030)), 1.0F, 1.0F);
		    class138.aClass233_1540.method2147(i_233_ * 2.0F / f, (this.anInt9030 * 2.0F / f_213_), 1.0F, 1.0F);
		    class138.aClass233_1540.aFloatArray2594[12] = (i_231_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f - 1.0F;
		    class138.aClass233_1540.aFloatArray2594[13] = (i_229_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_213_ - 1.0F;
		    class138.aClass233_1540.aFloatArray2594[14] = -1.0F;
		    class138.method1527();
		}
		i_229_ += i_228_;
	    }
	    if (i_229_ < i_225_) {
		int i_234_ = i_225_ - i_229_;
		class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(this.anInt9023), this.anInterface9_Impl2_9024.method78(i_234_), 1.0F, 1.0F);
		int i_235_ = i + this.anInt9026;
		for (int i_236_ = i_235_ + this.anInt9023; i_236_ <= i_226_; i_236_ += i_227_) {
		    class138.aClass233_1540.method2147(this.anInt9023 * 2.0F / f, i_234_ * 2.0F / f_213_, 1.0F, 1.0F);
		    class138.aClass233_1540.aFloatArray2594[12] = (i_235_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f - 1.0F;
		    class138.aClass233_1540.aFloatArray2594[13] = (i_229_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_213_ - 1.0F;
		    class138.aClass233_1540.aFloatArray2594[14] = -1.0F;
		    class138.method1527();
		    i_235_ += i_227_;
		}
		if (i_235_ < i_226_) {
		    int i_237_ = i_226_ - i_235_;
		    class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(i_237_), this.anInterface9_Impl2_9024.method78(i_234_), 1.0F, 1.0F);
		    class138.aClass233_1540.method2147((i_237_ * 2.0F / f), (i_234_ * 2.0F / f_213_), 1.0F, 1.0F);
		    class138.aClass233_1540.aFloatArray2594[12] = (i_235_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f - 1.0F;
		    class138.aClass233_1540.aFloatArray2594[13] = (i_229_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_213_ - 1.0F;
		    class138.aClass233_1540.aFloatArray2594[14] = -1.0F;
		    class138.method1527();
		}
	    }
	}
    }

    @Override
    public void method660(int i, int i_238_, int i_239_, int i_240_, int i_241_, int i_242_, int i_243_) {
	this.aClass_ra_Sub3_9033.method5337();
	this.aClass_ra_Sub3_9033.method5374(i_243_);
	Class138 class138 = (this.aClass_ra_Sub3_9033.aClass138_8257);
	class138.anInterface9_Impl2_1537 = this.anInterface9_Impl2_9024;
	class138.method1526(i_241_, i_242_);
	float f = this.aClass_ra_Sub3_9033.method4992((short) 9885).method545();
	float f_244_ = this.aClass_ra_Sub3_9033.method4992((short) -16102).method552();
	class138.anInterface7_Impl1_1538 = (this.aClass_ra_Sub3_9033.anInterface7_Impl1_8202);
	class138.anInt1543 = 0;
	class138.aClass153_1544 = (this.aClass_ra_Sub3_9033.aClass153_8356);
	boolean bool = (this.aBoolean9027 && this.anInt9029 == 0 && this.anInt9031 == 0);
	boolean bool_245_ = (this.aBoolean9035 && this.anInt9026 == 0 && this.anInt9032 == 0);
	if (bool_245_ & bool) {
	    class138.aClass233_1540.method2147(i_239_ * 2.0F / f, i_240_ * 2.0F / f_244_, 1.0F, 1.0F);
	    class138.aClass233_1540.aFloatArray2594[12] = ((i + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f) - 1.0F;
	    class138.aClass233_1540.aFloatArray2594[13] = ((i_238_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_244_) - 1.0F;
	    class138.aClass233_1540.aFloatArray2594[14] = -1.0F;
	    class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(i_239_), this.anInterface9_Impl2_9024.method78(i_240_), 1.0F, 1.0F);
	    class138.method1527();
	} else if (bool_245_) {
	    int i_246_ = i_238_ + i_240_;
	    int i_247_ = method626();
	    class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(i_239_), this.anInterface9_Impl2_9024.method78(this.anInt9030), 1.0F, 1.0F);
	    int i_248_ = i_238_ + this.anInt9029;
	    for (int i_249_ = i_248_ + this.anInt9030; i_249_ <= i_246_; i_249_ += i_247_) {
		class138.aClass233_1540.method2147(i_239_ * 2.0F / f, (this.anInt9030 * 2.0F / f_244_), 1.0F, 1.0F);
		class138.aClass233_1540.aFloatArray2594[12] = (i + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f - 1.0F;
		class138.aClass233_1540.aFloatArray2594[13] = ((i_248_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_244_) - 1.0F;
		class138.aClass233_1540.aFloatArray2594[14] = -1.0F;
		class138.method1527();
		i_248_ += i_247_;
	    }
	    if (i_248_ < i_246_) {
		int i_250_ = i_246_ - i_248_;
		class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(i_239_), this.anInterface9_Impl2_9024.method78(i_250_), 1.0F, 1.0F);
		class138.aClass233_1540.method2147(i_239_ * 2.0F / f, (i_250_ * 2.0F / f_244_), 1.0F, 1.0F);
		class138.aClass233_1540.aFloatArray2594[12] = (i + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f - 1.0F;
		class138.aClass233_1540.aFloatArray2594[13] = ((i_248_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_244_) - 1.0F;
		class138.aClass233_1540.aFloatArray2594[14] = -1.0F;
		class138.method1527();
	    }
	} else if (bool) {
	    int i_251_ = i + i_239_;
	    int i_252_ = method271();
	    class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(this.anInt9023), this.anInterface9_Impl2_9024.method78(i_240_), 1.0F, 1.0F);
	    int i_253_ = i + this.anInt9026;
	    for (int i_254_ = i_253_ + this.anInt9023; i_254_ <= i_251_; i_254_ += i_252_) {
		class138.aClass233_1540.method2147(this.anInt9023 * 2.0F / f, i_240_ * 2.0F / f_244_, 1.0F, 1.0F);
		class138.aClass233_1540.aFloatArray2594[12] = ((i_253_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f) - 1.0F;
		class138.aClass233_1540.aFloatArray2594[13] = ((i_238_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_244_) - 1.0F;
		class138.aClass233_1540.aFloatArray2594[14] = -1.0F;
		class138.method1527();
		i_253_ += i_252_;
	    }
	    if (i_253_ < i_251_) {
		int i_255_ = i_251_ - i_253_;
		class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(i_255_), this.anInterface9_Impl2_9024.method78(i_240_), 1.0F, 1.0F);
		class138.aClass233_1540.method2147(i_255_ * 2.0F / f, (i_240_ * 2.0F / f_244_), 1.0F, 1.0F);
		class138.aClass233_1540.aFloatArray2594[12] = ((i_253_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f) - 1.0F;
		class138.aClass233_1540.aFloatArray2594[13] = ((i_238_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_244_) - 1.0F;
		class138.aClass233_1540.aFloatArray2594[14] = -1.0F;
		class138.method1527();
	    }
	} else {
	    int i_256_ = i_238_ + i_240_;
	    int i_257_ = i + i_239_;
	    int i_258_ = method271();
	    int i_259_ = method626();
	    int i_260_ = i_238_ + this.anInt9029;
	    for (int i_261_ = i_260_ + this.anInt9030; i_261_ <= i_256_; i_261_ += i_259_) {
		class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(this.anInt9023), this.anInterface9_Impl2_9024.method78(this.anInt9030), 1.0F, 1.0F);
		int i_262_ = i + this.anInt9026;
		for (int i_263_ = i_262_ + this.anInt9023; i_263_ <= i_257_; i_263_ += i_258_) {
		    class138.aClass233_1540.method2147(this.anInt9023 * 2.0F / f, (this.anInt9030 * 2.0F / f_244_), 1.0F, 1.0F);
		    class138.aClass233_1540.aFloatArray2594[12] = (i_262_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f - 1.0F;
		    class138.aClass233_1540.aFloatArray2594[13] = (i_260_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_244_ - 1.0F;
		    class138.aClass233_1540.aFloatArray2594[14] = -1.0F;
		    class138.method1527();
		    i_262_ += i_258_;
		}
		if (i_262_ < i_257_) {
		    int i_264_ = i_257_ - i_262_;
		    class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(i_264_), (this.anInterface9_Impl2_9024.method78(this.anInt9030)), 1.0F, 1.0F);
		    class138.aClass233_1540.method2147(i_264_ * 2.0F / f, (this.anInt9030 * 2.0F / f_244_), 1.0F, 1.0F);
		    class138.aClass233_1540.aFloatArray2594[12] = (i_262_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f - 1.0F;
		    class138.aClass233_1540.aFloatArray2594[13] = (i_260_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_244_ - 1.0F;
		    class138.aClass233_1540.aFloatArray2594[14] = -1.0F;
		    class138.method1527();
		}
		i_260_ += i_259_;
	    }
	    if (i_260_ < i_256_) {
		int i_265_ = i_256_ - i_260_;
		class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(this.anInt9023), this.anInterface9_Impl2_9024.method78(i_265_), 1.0F, 1.0F);
		int i_266_ = i + this.anInt9026;
		for (int i_267_ = i_266_ + this.anInt9023; i_267_ <= i_257_; i_267_ += i_258_) {
		    class138.aClass233_1540.method2147(this.anInt9023 * 2.0F / f, i_265_ * 2.0F / f_244_, 1.0F, 1.0F);
		    class138.aClass233_1540.aFloatArray2594[12] = (i_266_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f - 1.0F;
		    class138.aClass233_1540.aFloatArray2594[13] = (i_260_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_244_ - 1.0F;
		    class138.aClass233_1540.aFloatArray2594[14] = -1.0F;
		    class138.method1527();
		    i_266_ += i_258_;
		}
		if (i_266_ < i_257_) {
		    int i_268_ = i_257_ - i_266_;
		    class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(i_268_), this.anInterface9_Impl2_9024.method78(i_265_), 1.0F, 1.0F);
		    class138.aClass233_1540.method2147((i_268_ * 2.0F / f), (i_265_ * 2.0F / f_244_), 1.0F, 1.0F);
		    class138.aClass233_1540.aFloatArray2594[12] = (i_266_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f - 1.0F;
		    class138.aClass233_1540.aFloatArray2594[13] = (i_260_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_244_ - 1.0F;
		    class138.aClass233_1540.aFloatArray2594[14] = -1.0F;
		    class138.method1527();
		}
	    }
	}
    }

    @Override
    public void method661(int i, int i_269_, int i_270_, int i_271_, int i_272_, int i_273_, int i_274_) {
	this.aClass_ra_Sub3_9033.method5337();
	this.aClass_ra_Sub3_9033.method5374(i_274_);
	Class138 class138 = (this.aClass_ra_Sub3_9033.aClass138_8257);
	class138.anInterface9_Impl2_1537 = this.anInterface9_Impl2_9024;
	class138.method1526(i_272_, i_273_);
	float f = this.aClass_ra_Sub3_9033.method4992((short) -5713).method545();
	float f_275_ = this.aClass_ra_Sub3_9033.method4992((short) 14689).method552();
	class138.anInterface7_Impl1_1538 = (this.aClass_ra_Sub3_9033.anInterface7_Impl1_8202);
	class138.anInt1543 = 0;
	class138.aClass153_1544 = (this.aClass_ra_Sub3_9033.aClass153_8356);
	boolean bool = (this.aBoolean9027 && this.anInt9029 == 0 && this.anInt9031 == 0);
	boolean bool_276_ = (this.aBoolean9035 && this.anInt9026 == 0 && this.anInt9032 == 0);
	if (bool_276_ & bool) {
	    class138.aClass233_1540.method2147(i_270_ * 2.0F / f, i_271_ * 2.0F / f_275_, 1.0F, 1.0F);
	    class138.aClass233_1540.aFloatArray2594[12] = ((i + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f) - 1.0F;
	    class138.aClass233_1540.aFloatArray2594[13] = ((i_269_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_275_) - 1.0F;
	    class138.aClass233_1540.aFloatArray2594[14] = -1.0F;
	    class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(i_270_), this.anInterface9_Impl2_9024.method78(i_271_), 1.0F, 1.0F);
	    class138.method1527();
	} else if (bool_276_) {
	    int i_277_ = i_269_ + i_271_;
	    int i_278_ = method626();
	    class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(i_270_), this.anInterface9_Impl2_9024.method78(this.anInt9030), 1.0F, 1.0F);
	    int i_279_ = i_269_ + this.anInt9029;
	    for (int i_280_ = i_279_ + this.anInt9030; i_280_ <= i_277_; i_280_ += i_278_) {
		class138.aClass233_1540.method2147(i_270_ * 2.0F / f, (this.anInt9030 * 2.0F / f_275_), 1.0F, 1.0F);
		class138.aClass233_1540.aFloatArray2594[12] = (i + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f - 1.0F;
		class138.aClass233_1540.aFloatArray2594[13] = ((i_279_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_275_) - 1.0F;
		class138.aClass233_1540.aFloatArray2594[14] = -1.0F;
		class138.method1527();
		i_279_ += i_278_;
	    }
	    if (i_279_ < i_277_) {
		int i_281_ = i_277_ - i_279_;
		class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(i_270_), this.anInterface9_Impl2_9024.method78(i_281_), 1.0F, 1.0F);
		class138.aClass233_1540.method2147(i_270_ * 2.0F / f, (i_281_ * 2.0F / f_275_), 1.0F, 1.0F);
		class138.aClass233_1540.aFloatArray2594[12] = (i + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f - 1.0F;
		class138.aClass233_1540.aFloatArray2594[13] = ((i_279_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_275_) - 1.0F;
		class138.aClass233_1540.aFloatArray2594[14] = -1.0F;
		class138.method1527();
	    }
	} else if (bool) {
	    int i_282_ = i + i_270_;
	    int i_283_ = method271();
	    class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(this.anInt9023), this.anInterface9_Impl2_9024.method78(i_271_), 1.0F, 1.0F);
	    int i_284_ = i + this.anInt9026;
	    for (int i_285_ = i_284_ + this.anInt9023; i_285_ <= i_282_; i_285_ += i_283_) {
		class138.aClass233_1540.method2147(this.anInt9023 * 2.0F / f, i_271_ * 2.0F / f_275_, 1.0F, 1.0F);
		class138.aClass233_1540.aFloatArray2594[12] = ((i_284_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f) - 1.0F;
		class138.aClass233_1540.aFloatArray2594[13] = ((i_269_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_275_) - 1.0F;
		class138.aClass233_1540.aFloatArray2594[14] = -1.0F;
		class138.method1527();
		i_284_ += i_283_;
	    }
	    if (i_284_ < i_282_) {
		int i_286_ = i_282_ - i_284_;
		class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(i_286_), this.anInterface9_Impl2_9024.method78(i_271_), 1.0F, 1.0F);
		class138.aClass233_1540.method2147(i_286_ * 2.0F / f, (i_271_ * 2.0F / f_275_), 1.0F, 1.0F);
		class138.aClass233_1540.aFloatArray2594[12] = ((i_284_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f) - 1.0F;
		class138.aClass233_1540.aFloatArray2594[13] = ((i_269_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_275_) - 1.0F;
		class138.aClass233_1540.aFloatArray2594[14] = -1.0F;
		class138.method1527();
	    }
	} else {
	    int i_287_ = i_269_ + i_271_;
	    int i_288_ = i + i_270_;
	    int i_289_ = method271();
	    int i_290_ = method626();
	    int i_291_ = i_269_ + this.anInt9029;
	    for (int i_292_ = i_291_ + this.anInt9030; i_292_ <= i_287_; i_292_ += i_290_) {
		class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(this.anInt9023), this.anInterface9_Impl2_9024.method78(this.anInt9030), 1.0F, 1.0F);
		int i_293_ = i + this.anInt9026;
		for (int i_294_ = i_293_ + this.anInt9023; i_294_ <= i_288_; i_294_ += i_289_) {
		    class138.aClass233_1540.method2147(this.anInt9023 * 2.0F / f, (this.anInt9030 * 2.0F / f_275_), 1.0F, 1.0F);
		    class138.aClass233_1540.aFloatArray2594[12] = (i_293_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f - 1.0F;
		    class138.aClass233_1540.aFloatArray2594[13] = (i_291_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_275_ - 1.0F;
		    class138.aClass233_1540.aFloatArray2594[14] = -1.0F;
		    class138.method1527();
		    i_293_ += i_289_;
		}
		if (i_293_ < i_288_) {
		    int i_295_ = i_288_ - i_293_;
		    class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(i_295_), (this.anInterface9_Impl2_9024.method78(this.anInt9030)), 1.0F, 1.0F);
		    class138.aClass233_1540.method2147(i_295_ * 2.0F / f, (this.anInt9030 * 2.0F / f_275_), 1.0F, 1.0F);
		    class138.aClass233_1540.aFloatArray2594[12] = (i_293_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f - 1.0F;
		    class138.aClass233_1540.aFloatArray2594[13] = (i_291_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_275_ - 1.0F;
		    class138.aClass233_1540.aFloatArray2594[14] = -1.0F;
		    class138.method1527();
		}
		i_291_ += i_290_;
	    }
	    if (i_291_ < i_287_) {
		int i_296_ = i_287_ - i_291_;
		class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(this.anInt9023), this.anInterface9_Impl2_9024.method78(i_296_), 1.0F, 1.0F);
		int i_297_ = i + this.anInt9026;
		for (int i_298_ = i_297_ + this.anInt9023; i_298_ <= i_288_; i_298_ += i_289_) {
		    class138.aClass233_1540.method2147(this.anInt9023 * 2.0F / f, i_296_ * 2.0F / f_275_, 1.0F, 1.0F);
		    class138.aClass233_1540.aFloatArray2594[12] = (i_297_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f - 1.0F;
		    class138.aClass233_1540.aFloatArray2594[13] = (i_291_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_275_ - 1.0F;
		    class138.aClass233_1540.aFloatArray2594[14] = -1.0F;
		    class138.method1527();
		    i_297_ += i_289_;
		}
		if (i_297_ < i_288_) {
		    int i_299_ = i_288_ - i_297_;
		    class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(i_299_), this.anInterface9_Impl2_9024.method78(i_296_), 1.0F, 1.0F);
		    class138.aClass233_1540.method2147((i_299_ * 2.0F / f), (i_296_ * 2.0F / f_275_), 1.0F, 1.0F);
		    class138.aClass233_1540.aFloatArray2594[12] = (i_297_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f - 1.0F;
		    class138.aClass233_1540.aFloatArray2594[13] = (i_291_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_275_ - 1.0F;
		    class138.aClass233_1540.aFloatArray2594[14] = -1.0F;
		    class138.method1527();
		}
	    }
	}
    }

    @Override
    void method629(float f, float f_300_, float f_301_, float f_302_, float f_303_, float f_304_, int i, int i_305_, int i_306_, int i_307_) {
	this.aClass_ra_Sub3_9033.method5337();
	this.aClass_ra_Sub3_9033.method5374(i_306_);
	if (this.aBoolean9028) {
	    float f_308_ = method271();
	    float f_309_ = method626();
	    float f_310_ = (f_301_ - f) / f_308_;
	    float f_311_ = (f_302_ - f_300_) / f_308_;
	    float f_312_ = (f_303_ - f) / f_309_;
	    float f_313_ = (f_304_ - f_300_) / f_309_;
	    float f_314_ = f_312_ * this.anInt9029;
	    float f_315_ = f_313_ * this.anInt9029;
	    float f_316_ = f_310_ * this.anInt9026;
	    float f_317_ = f_311_ * this.anInt9026;
	    float f_318_ = -f_310_ * this.anInt9032;
	    float f_319_ = -f_311_ * this.anInt9032;
	    float f_320_ = -f_312_ * this.anInt9031;
	    float f_321_ = -f_313_ * this.anInt9031;
	    f = f + f_316_ + f_314_;
	    f_300_ = f_300_ + f_317_ + f_315_;
	    f_301_ = f_301_ + f_318_ + f_314_;
	    f_302_ = f_302_ + f_319_ + f_315_;
	    f_303_ = f_303_ + f_316_ + f_320_;
	    f_304_ = f_304_ + f_317_ + f_321_;
	}
	Class138 class138 = (this.aClass_ra_Sub3_9033.aClass138_8257);
	class138.anInterface9_Impl2_1537 = this.anInterface9_Impl2_9024;
	class138.method1526(i, i_305_);
	float f_322_ = this.aClass_ra_Sub3_9033.method4992((short) 1083).method545();
	float f_323_ = this.aClass_ra_Sub3_9033.method4992((short) -13740).method552();
	class138.aClass233_1540.method2172();
	class138.aClass233_1540.aFloatArray2594[0] = (f_301_ - f) * 2.0F / f_322_;
	class138.aClass233_1540.aFloatArray2594[1] = (f_302_ - f_300_) * 2.0F / f_323_;
	class138.aClass233_1540.aFloatArray2594[4] = (f_303_ - f) * 2.0F / f_322_;
	class138.aClass233_1540.aFloatArray2594[5] = (f_304_ - f_300_) * 2.0F / f_323_;
	class138.aClass233_1540.aFloatArray2594[12] = ((f + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_322_) - 1.0F;
	class138.aClass233_1540.aFloatArray2594[13] = ((f_300_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_323_) - 1.0F;
	class138.aClass233_1540.aFloatArray2594[14] = -1.0F;
	class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(this.anInt9023), this.anInterface9_Impl2_9024.method78(this.anInt9030), 1.0F, 1.0F);
	class138.anInterface7_Impl1_1538 = (this.aClass_ra_Sub3_9033.anInterface7_Impl1_8202);
	class138.anInt1543 = 0;
	class138.aClass153_1544 = (this.aClass_ra_Sub3_9033.aClass153_8356);
	class138.method1527();
    }

    @Override
    void method663(float f, float f_324_, float f_325_, float f_326_, float f_327_, float f_328_, int i, int i_329_, int i_330_, int i_331_) {
	this.aClass_ra_Sub3_9033.method5337();
	this.aClass_ra_Sub3_9033.method5374(i_330_);
	if (this.aBoolean9028) {
	    float f_332_ = method271();
	    float f_333_ = method626();
	    float f_334_ = (f_325_ - f) / f_332_;
	    float f_335_ = (f_326_ - f_324_) / f_332_;
	    float f_336_ = (f_327_ - f) / f_333_;
	    float f_337_ = (f_328_ - f_324_) / f_333_;
	    float f_338_ = f_336_ * this.anInt9029;
	    float f_339_ = f_337_ * this.anInt9029;
	    float f_340_ = f_334_ * this.anInt9026;
	    float f_341_ = f_335_ * this.anInt9026;
	    float f_342_ = -f_334_ * this.anInt9032;
	    float f_343_ = -f_335_ * this.anInt9032;
	    float f_344_ = -f_336_ * this.anInt9031;
	    float f_345_ = -f_337_ * this.anInt9031;
	    f = f + f_340_ + f_338_;
	    f_324_ = f_324_ + f_341_ + f_339_;
	    f_325_ = f_325_ + f_342_ + f_338_;
	    f_326_ = f_326_ + f_343_ + f_339_;
	    f_327_ = f_327_ + f_340_ + f_344_;
	    f_328_ = f_328_ + f_341_ + f_345_;
	}
	Class138 class138 = (this.aClass_ra_Sub3_9033.aClass138_8257);
	class138.anInterface9_Impl2_1537 = this.anInterface9_Impl2_9024;
	class138.method1526(i, i_329_);
	float f_346_ = this.aClass_ra_Sub3_9033.method4992((short) 16767).method545();
	float f_347_ = this.aClass_ra_Sub3_9033.method4992((short) 18071).method552();
	class138.aClass233_1540.method2172();
	class138.aClass233_1540.aFloatArray2594[0] = (f_325_ - f) * 2.0F / f_346_;
	class138.aClass233_1540.aFloatArray2594[1] = (f_326_ - f_324_) * 2.0F / f_347_;
	class138.aClass233_1540.aFloatArray2594[4] = (f_327_ - f) * 2.0F / f_346_;
	class138.aClass233_1540.aFloatArray2594[5] = (f_328_ - f_324_) * 2.0F / f_347_;
	class138.aClass233_1540.aFloatArray2594[12] = ((f + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_346_) - 1.0F;
	class138.aClass233_1540.aFloatArray2594[13] = ((f_324_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_347_) - 1.0F;
	class138.aClass233_1540.aFloatArray2594[14] = -1.0F;
	class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(this.anInt9023), this.anInterface9_Impl2_9024.method78(this.anInt9030), 1.0F, 1.0F);
	class138.anInterface7_Impl1_1538 = (this.aClass_ra_Sub3_9033.anInterface7_Impl1_8202);
	class138.anInt1543 = 0;
	class138.aClass153_1544 = (this.aClass_ra_Sub3_9033.aClass153_8356);
	class138.method1527();
    }

    void method688(int i, int i_348_, int i_349_, int i_350_, int[] is, int i_351_, int i_352_) {
	this.anInterface9_Impl2_9024.method81(i, i_348_, i_349_, i_350_, is, i_351_, i_352_);
    }

    @Override
    void method664(float f, float f_353_, float f_354_, float f_355_, float f_356_, float f_357_, int i, int i_358_, int i_359_, int i_360_) {
	this.aClass_ra_Sub3_9033.method5337();
	this.aClass_ra_Sub3_9033.method5374(i_359_);
	if (this.aBoolean9028) {
	    float f_361_ = method271();
	    float f_362_ = method626();
	    float f_363_ = (f_354_ - f) / f_361_;
	    float f_364_ = (f_355_ - f_353_) / f_361_;
	    float f_365_ = (f_356_ - f) / f_362_;
	    float f_366_ = (f_357_ - f_353_) / f_362_;
	    float f_367_ = f_365_ * this.anInt9029;
	    float f_368_ = f_366_ * this.anInt9029;
	    float f_369_ = f_363_ * this.anInt9026;
	    float f_370_ = f_364_ * this.anInt9026;
	    float f_371_ = -f_363_ * this.anInt9032;
	    float f_372_ = -f_364_ * this.anInt9032;
	    float f_373_ = -f_365_ * this.anInt9031;
	    float f_374_ = -f_366_ * this.anInt9031;
	    f = f + f_369_ + f_367_;
	    f_353_ = f_353_ + f_370_ + f_368_;
	    f_354_ = f_354_ + f_371_ + f_367_;
	    f_355_ = f_355_ + f_372_ + f_368_;
	    f_356_ = f_356_ + f_369_ + f_373_;
	    f_357_ = f_357_ + f_370_ + f_374_;
	}
	Class138 class138 = (this.aClass_ra_Sub3_9033.aClass138_8257);
	class138.anInterface9_Impl2_1537 = this.anInterface9_Impl2_9024;
	class138.method1526(i, i_358_);
	float f_375_ = this.aClass_ra_Sub3_9033.method4992((short) -2943).method545();
	float f_376_ = this.aClass_ra_Sub3_9033.method4992((short) -353).method552();
	class138.aClass233_1540.method2172();
	class138.aClass233_1540.aFloatArray2594[0] = (f_354_ - f) * 2.0F / f_375_;
	class138.aClass233_1540.aFloatArray2594[1] = (f_355_ - f_353_) * 2.0F / f_376_;
	class138.aClass233_1540.aFloatArray2594[4] = (f_356_ - f) * 2.0F / f_375_;
	class138.aClass233_1540.aFloatArray2594[5] = (f_357_ - f_353_) * 2.0F / f_376_;
	class138.aClass233_1540.aFloatArray2594[12] = ((f + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_375_) - 1.0F;
	class138.aClass233_1540.aFloatArray2594[13] = ((f_353_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_376_) - 1.0F;
	class138.aClass233_1540.aFloatArray2594[14] = -1.0F;
	class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(this.anInt9023), this.anInterface9_Impl2_9024.method78(this.anInt9030), 1.0F, 1.0F);
	class138.anInterface7_Impl1_1538 = (this.aClass_ra_Sub3_9033.anInterface7_Impl1_8202);
	class138.anInt1543 = 0;
	class138.aClass153_1544 = (this.aClass_ra_Sub3_9033.aClass153_8356);
	class138.method1527();
    }

    @Override
    public void method654(int i, int i_377_, Class_ta class_ta, int i_378_, int i_379_) {
	this.aClass_ra_Sub3_9033.method5337();
	Class138 class138 = (this.aClass_ra_Sub3_9033.aClass138_8257);
	class138.anInterface9_Impl2_1537 = this.anInterface9_Impl2_9024;
	class138.method1526(1, -1);
	i += this.anInt9026;
	i_377_ += this.anInt9029;
	float f = this.aClass_ra_Sub3_9033.method4992((short) -10311).method545();
	float f_380_ = this.aClass_ra_Sub3_9033.method4992((short) -2951).method552();
	class138.aClass233_1540.method2147(this.anInt9023 * 2.0F / f, this.anInt9030 * 2.0F / f_380_, 1.0F, 1.0F);
	class138.aClass233_1540.aFloatArray2594[12] = ((i + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f) - 1.0F;
	class138.aClass233_1540.aFloatArray2594[13] = ((i_377_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_380_) - 1.0F;
	class138.aClass233_1540.aFloatArray2594[14] = -1.0F;
	class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(this.anInt9023), this.anInterface9_Impl2_9024.method78(this.anInt9030), 1.0F, 1.0F);
	class138.anInterface7_Impl1_1538 = (this.aClass_ra_Sub3_9033.anInterface7_Impl1_8202);
	class138.anInt1543 = 0;
	class138.aClass153_1544 = (this.aClass_ra_Sub3_9033.aClass153_8356);
	Interface9_Impl2 interface9_impl2 = (((Class_ta_Sub3) class_ta).anInterface9_Impl2_8444);
	class138.anInterface9_Impl2_1536 = interface9_impl2;
	class138.aClass233_1542.method2147(interface9_impl2.method77(this.anInt9023), interface9_impl2.method78(this.anInt9030), 1.0F, 1.0F);
	class138.aClass233_1542.aFloatArray2594[12] = interface9_impl2.method77(i - i_378_);
	class138.aClass233_1542.aFloatArray2594[13] = interface9_impl2.method78(i_377_ - i_379_);
	class138.method1528();
    }

    @Override
    void method672(float f, float f_381_, float f_382_, float f_383_, float f_384_, float f_385_, int i, Class_ta class_ta, int i_386_, int i_387_) {
	this.aClass_ra_Sub3_9033.method5337();
	Class138 class138 = (this.aClass_ra_Sub3_9033.aClass138_8257);
	class138.anInterface9_Impl2_1537 = this.anInterface9_Impl2_9024;
	class138.method1526(1, -1);
	float f_388_ = this.aClass_ra_Sub3_9033.method4992((short) -1649).method545();
	float f_389_ = this.aClass_ra_Sub3_9033.method4992((short) 20235).method552();
	class138.aClass233_1540.method2172();
	if (this.aBoolean9028) {
	    float f_390_ = ((float) this.anInt9023 / (float) method271());
	    float f_391_ = ((float) this.anInt9030 / (float) method626());
	    class138.aClass233_1540.aFloatArray2594[0] = (f_382_ - f) * f_390_;
	    class138.aClass233_1540.aFloatArray2594[1] = (f_383_ - f_381_) * f_390_;
	    class138.aClass233_1540.aFloatArray2594[4] = (f_384_ - f) * f_391_;
	    class138.aClass233_1540.aFloatArray2594[5] = (f_385_ - f_381_) * f_391_;
	    class138.aClass233_1540.aFloatArray2594[12] = ((f + this.anInt9026) * f_390_ + this.aClass_ra_Sub3_9033.method5315());
	    class138.aClass233_1540.aFloatArray2594[13] = ((f_381_ + this.anInt9029) * f_391_ + this.aClass_ra_Sub3_9033.method5315());
	} else {
	    class138.aClass233_1540.aFloatArray2594[0] = f_382_ - f;
	    class138.aClass233_1540.aFloatArray2594[1] = f_383_ - f_381_;
	    class138.aClass233_1540.aFloatArray2594[4] = f_384_ - f;
	    class138.aClass233_1540.aFloatArray2594[5] = f_385_ - f_381_;
	    class138.aClass233_1540.aFloatArray2594[12] = f + this.aClass_ra_Sub3_9033.method5315();
	    class138.aClass233_1540.aFloatArray2594[13] = (f_381_ + this.aClass_ra_Sub3_9033.method5315());
	}
	Class233 class233 = this.aClass_ra_Sub3_9033.aClass233_8236;
	class233.method2172();
	class233.aFloatArray2594[0] = 2.0F / f_388_;
	class233.aFloatArray2594[5] = 2.0F / f_389_;
	class233.aFloatArray2594[12] = -1.0F;
	class233.aFloatArray2594[13] = -1.0F;
	class233.aFloatArray2594[14] = -1.0F;
	class138.aClass233_1540.method2144(class233);
	class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(this.anInt9023), this.anInterface9_Impl2_9024.method78(this.anInt9030), 1.0F, 1.0F);
	class138.anInterface7_Impl1_1538 = (this.aClass_ra_Sub3_9033.anInterface7_Impl1_8202);
	class138.anInt1543 = 0;
	class138.aClass153_1544 = (this.aClass_ra_Sub3_9033.aClass153_8356);
	Interface9_Impl2 interface9_impl2 = (((Class_ta_Sub3) class_ta).anInterface9_Impl2_8444);
	class138.anInterface9_Impl2_1536 = interface9_impl2;
	class138.aClass233_1542.method2172();
	class138.aClass233_1542.aFloatArray2594[0] = (f_382_ - f) * interface9_impl2.method77(1.0F);
	class138.aClass233_1542.aFloatArray2594[1] = (f_383_ - f_381_) * interface9_impl2.method77(1.0F);
	class138.aClass233_1542.aFloatArray2594[4] = (f_384_ - f) * interface9_impl2.method78(1.0F);
	class138.aClass233_1542.aFloatArray2594[5] = (f_385_ - f_381_) * interface9_impl2.method78(1.0F);
	class138.aClass233_1542.aFloatArray2594[12] = (f - i_386_) * interface9_impl2.method77(1.0F);
	class138.aClass233_1542.aFloatArray2594[13] = (f_381_ - i_387_) * interface9_impl2.method78(1.0F);
	class138.method1528();
    }

    @Override
    public int method667() {
	return this.anInt9030;
    }

    @Override
    public int method668() {
	return this.anInt9030;
    }

    @Override
    public int method669() {
	return this.anInt9030;
    }

    @Override
    void method670(float f, float f_392_, float f_393_, float f_394_, float f_395_, float f_396_, int i, Class_ta class_ta, int i_397_, int i_398_) {
	this.aClass_ra_Sub3_9033.method5337();
	Class138 class138 = (this.aClass_ra_Sub3_9033.aClass138_8257);
	class138.anInterface9_Impl2_1537 = this.anInterface9_Impl2_9024;
	class138.method1526(1, -1);
	float f_399_ = this.aClass_ra_Sub3_9033.method4992((short) -20975).method545();
	float f_400_ = this.aClass_ra_Sub3_9033.method4992((short) 22361).method552();
	class138.aClass233_1540.method2172();
	if (this.aBoolean9028) {
	    float f_401_ = ((float) this.anInt9023 / (float) method271());
	    float f_402_ = ((float) this.anInt9030 / (float) method626());
	    class138.aClass233_1540.aFloatArray2594[0] = (f_393_ - f) * f_401_;
	    class138.aClass233_1540.aFloatArray2594[1] = (f_394_ - f_392_) * f_401_;
	    class138.aClass233_1540.aFloatArray2594[4] = (f_395_ - f) * f_402_;
	    class138.aClass233_1540.aFloatArray2594[5] = (f_396_ - f_392_) * f_402_;
	    class138.aClass233_1540.aFloatArray2594[12] = ((f + this.anInt9026) * f_401_ + this.aClass_ra_Sub3_9033.method5315());
	    class138.aClass233_1540.aFloatArray2594[13] = ((f_392_ + this.anInt9029) * f_402_ + this.aClass_ra_Sub3_9033.method5315());
	} else {
	    class138.aClass233_1540.aFloatArray2594[0] = f_393_ - f;
	    class138.aClass233_1540.aFloatArray2594[1] = f_394_ - f_392_;
	    class138.aClass233_1540.aFloatArray2594[4] = f_395_ - f;
	    class138.aClass233_1540.aFloatArray2594[5] = f_396_ - f_392_;
	    class138.aClass233_1540.aFloatArray2594[12] = f + this.aClass_ra_Sub3_9033.method5315();
	    class138.aClass233_1540.aFloatArray2594[13] = (f_392_ + this.aClass_ra_Sub3_9033.method5315());
	}
	Class233 class233 = this.aClass_ra_Sub3_9033.aClass233_8236;
	class233.method2172();
	class233.aFloatArray2594[0] = 2.0F / f_399_;
	class233.aFloatArray2594[5] = 2.0F / f_400_;
	class233.aFloatArray2594[12] = -1.0F;
	class233.aFloatArray2594[13] = -1.0F;
	class233.aFloatArray2594[14] = -1.0F;
	class138.aClass233_1540.method2144(class233);
	class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(this.anInt9023), this.anInterface9_Impl2_9024.method78(this.anInt9030), 1.0F, 1.0F);
	class138.anInterface7_Impl1_1538 = (this.aClass_ra_Sub3_9033.anInterface7_Impl1_8202);
	class138.anInt1543 = 0;
	class138.aClass153_1544 = (this.aClass_ra_Sub3_9033.aClass153_8356);
	Interface9_Impl2 interface9_impl2 = (((Class_ta_Sub3) class_ta).anInterface9_Impl2_8444);
	class138.anInterface9_Impl2_1536 = interface9_impl2;
	class138.aClass233_1542.method2172();
	class138.aClass233_1542.aFloatArray2594[0] = (f_393_ - f) * interface9_impl2.method77(1.0F);
	class138.aClass233_1542.aFloatArray2594[1] = (f_394_ - f_392_) * interface9_impl2.method77(1.0F);
	class138.aClass233_1542.aFloatArray2594[4] = (f_395_ - f) * interface9_impl2.method78(1.0F);
	class138.aClass233_1542.aFloatArray2594[5] = (f_396_ - f_392_) * interface9_impl2.method78(1.0F);
	class138.aClass233_1542.aFloatArray2594[12] = (f - i_397_) * interface9_impl2.method77(1.0F);
	class138.aClass233_1542.aFloatArray2594[13] = (f_392_ - i_398_) * interface9_impl2.method78(1.0F);
	class138.method1528();
    }

    @Override
    public void method640(int[] is) {
	is[0] = this.anInt9026;
	is[1] = this.anInt9029;
	is[2] = this.anInt9032;
	is[3] = this.anInt9031;
    }

    @Override
    void method657(float f, float f_403_, float f_404_, float f_405_, float f_406_, float f_407_, int i, int i_408_, int i_409_, int i_410_) {
	this.aClass_ra_Sub3_9033.method5337();
	this.aClass_ra_Sub3_9033.method5374(i_409_);
	if (this.aBoolean9028) {
	    float f_411_ = method271();
	    float f_412_ = method626();
	    float f_413_ = (f_404_ - f) / f_411_;
	    float f_414_ = (f_405_ - f_403_) / f_411_;
	    float f_415_ = (f_406_ - f) / f_412_;
	    float f_416_ = (f_407_ - f_403_) / f_412_;
	    float f_417_ = f_415_ * this.anInt9029;
	    float f_418_ = f_416_ * this.anInt9029;
	    float f_419_ = f_413_ * this.anInt9026;
	    float f_420_ = f_414_ * this.anInt9026;
	    float f_421_ = -f_413_ * this.anInt9032;
	    float f_422_ = -f_414_ * this.anInt9032;
	    float f_423_ = -f_415_ * this.anInt9031;
	    float f_424_ = -f_416_ * this.anInt9031;
	    f = f + f_419_ + f_417_;
	    f_403_ = f_403_ + f_420_ + f_418_;
	    f_404_ = f_404_ + f_421_ + f_417_;
	    f_405_ = f_405_ + f_422_ + f_418_;
	    f_406_ = f_406_ + f_419_ + f_423_;
	    f_407_ = f_407_ + f_420_ + f_424_;
	}
	Class138 class138 = (this.aClass_ra_Sub3_9033.aClass138_8257);
	class138.anInterface9_Impl2_1537 = this.anInterface9_Impl2_9024;
	class138.method1526(i, i_408_);
	float f_425_ = this.aClass_ra_Sub3_9033.method4992((short) -18902).method545();
	float f_426_ = this.aClass_ra_Sub3_9033.method4992((short) 5348).method552();
	class138.aClass233_1540.method2172();
	class138.aClass233_1540.aFloatArray2594[0] = (f_404_ - f) * 2.0F / f_425_;
	class138.aClass233_1540.aFloatArray2594[1] = (f_405_ - f_403_) * 2.0F / f_426_;
	class138.aClass233_1540.aFloatArray2594[4] = (f_406_ - f) * 2.0F / f_425_;
	class138.aClass233_1540.aFloatArray2594[5] = (f_407_ - f_403_) * 2.0F / f_426_;
	class138.aClass233_1540.aFloatArray2594[12] = ((f + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_425_) - 1.0F;
	class138.aClass233_1540.aFloatArray2594[13] = ((f_403_ + this.aClass_ra_Sub3_9033.method5315()) * 2.0F / f_426_) - 1.0F;
	class138.aClass233_1540.aFloatArray2594[14] = -1.0F;
	class138.aClass233_1541.method2147(this.anInterface9_Impl2_9024.method77(this.anInt9023), this.anInterface9_Impl2_9024.method78(this.anInt9030), 1.0F, 1.0F);
	class138.anInterface7_Impl1_1538 = (this.aClass_ra_Sub3_9033.anInterface7_Impl1_8202);
	class138.anInt1543 = 0;
	class138.aClass153_1544 = (this.aClass_ra_Sub3_9033.aClass153_8356);
	class138.method1527();
    }
}
