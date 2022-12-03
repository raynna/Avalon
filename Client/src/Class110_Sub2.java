
/* Class110_Sub2 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import jaggl.OpenGL;

public class Class110_Sub2 extends Class110 {
    boolean aBoolean8603;
    float[] aFloatArray8604;
    static int anInt8605 = 13;
    Class_ra_Sub3_Sub1 aClass_ra_Sub3_Sub1_8606;
    Class123_Sub2 aClass123_Sub2_8607;
    int anInt8608 = 0;
    int anInt8609 = 0;
    static int[] anIntArray8610;
    static int anInt8611 = 35632;
    int[] anIntArray8612;
    int[] anIntArray8613;
    static float[] aFloatArray8614;
    int anInt8615 = 0;
    String aString8616;
    static int anInt8617 = 35633;
    float[] aFloatArray8618;
    float[] aFloatArray8619;
    String aString8620;
    float[] aFloatArray8621;
    static boolean aBoolean8622;

    Class110_Sub2(Class_ra_Sub3_Sub1 class_ra_sub3_sub1, Class108 class108) {
	this.aBoolean8603 = false;
	this.anIntArray8612 = new int[13];
	aString6402 = class108.aString1317;
	StringBuilder stringbuilder = new StringBuilder();
	if (class108.aString1316 != null) {
	    aString6400 = class108.aString1316;
	    if (class108.aClass122Array1319 != null) {
		Class122[] class122s = class108.aClass122Array1319;
		for (int i = 0; i < class122s.length; i++) {
		    Class122 class122 = class122s[i];
		    stringbuilder.append(new StringBuilder().append("#define ").append(class122.aString1465).append(" ").append(class122.aString1464).append('\n').toString());
		}
	    }
	    stringbuilder.append(new String(class_ra_sub3_sub1.method5535(aString6400)));
	    this.aString8620 = stringbuilder.toString();
	}
	if (class108.aString1318 != null) {
	    aString6401 = class108.aString1318;
	    stringbuilder.setLength(0);
	    if (class108.aClass122Array1320 != null) {
		Class122[] class122s = class108.aClass122Array1320;
		for (int i = 0; i < class122s.length; i++) {
		    Class122 class122 = class122s[i];
		    stringbuilder.append(new StringBuilder().append("#define ").append(class122.aString1465).append(" ").append(class122.aString1464).append('\n').toString());
		}
	    }
	    stringbuilder.append(new String(class_ra_sub3_sub1.method5535(aString6401)));
	    this.aString8616 = stringbuilder.toString();
	}
	Class425.method5740(this.anIntArray8612, 0, this.anIntArray8612.length, -1);
    }

    @Override
    void method1162(Class298_Sub31_Sub1 class298_sub31_sub1, float f, float f_0_, float f_1_) {
	int i = ((Class298_Sub31_Sub1_Sub1) class298_sub31_sub1).method3125();
	if (i != -1) {
	    if (this.aFloatArray8621[i] != f || this.aFloatArray8619[i] != f_0_ || this.aFloatArray8604[i] != f_1_) {
		this.aFloatArray8621[i] = f;
		this.aFloatArray8619[i] = f_0_;
		this.aFloatArray8604[i] = f_1_;
		if (class298_sub31_sub1.method3118(585186904) != Class100.aClass100_995)
		    throw new IllegalArgumentException_Sub1(class298_sub31_sub1, "");
		OpenGL.glUniform3f(i, f, f_0_, f_1_);
	    }
	}
    }

    @Override
    void method1176(Class298_Sub31_Sub1 class298_sub31_sub1, float f) {
	int i = ((Class298_Sub31_Sub1_Sub1) class298_sub31_sub1).method3125();
	if (i != -1) {
	    if (this.aFloatArray8621[i] != f) {
		this.aFloatArray8621[i] = f;
		if ((class298_sub31_sub1.method3118(33040871) != Class100.aClass100_977) && (class298_sub31_sub1.method3118(-548966323) != Class100.aClass100_1023))
		    throw new IllegalArgumentException_Sub1(class298_sub31_sub1, "");
		OpenGL.glUniform1f(i, f);
	    }
	}
    }

    @Override
    void method1161(Class298_Sub31_Sub1 class298_sub31_sub1, float f, float f_2_) {
	int i = ((Class298_Sub31_Sub1_Sub1) class298_sub31_sub1).method3125();
	if (i != -1) {
	    if (this.aFloatArray8621[i] != f || this.aFloatArray8619[i] != f_2_) {
		this.aFloatArray8621[i] = f;
		this.aFloatArray8619[i] = f_2_;
		if (class298_sub31_sub1.method3118(155730318) != Class100.aClass100_1049)
		    throw new IllegalArgumentException_Sub1(class298_sub31_sub1, "");
		OpenGL.glUniform2f(i, f, f_2_);
	    }
	}
    }

    @Override
    void method1170(int i, float[] fs, int i_3_) {
	OpenGL.glUniform4fv(i, i_3_, fs, 0);
    }

    @Override
    void method1163(Class298_Sub31_Sub1 class298_sub31_sub1, float f, float f_4_, float f_5_, float f_6_) {
	int i = ((Class298_Sub31_Sub1_Sub1) class298_sub31_sub1).method3125();
	if (i != -1) {
	    if (this.aFloatArray8621[i] != f || this.aFloatArray8619[i] != f_4_ || this.aFloatArray8604[i] != f_5_ || this.aFloatArray8618[i] != f_6_) {
		this.aFloatArray8621[i] = f;
		this.aFloatArray8619[i] = f_4_;
		this.aFloatArray8604[i] = f_5_;
		this.aFloatArray8618[i] = f_6_;
		if (class298_sub31_sub1.method3118(-1326694725) != Class100.aClass100_1001)
		    throw new IllegalArgumentException_Sub1(class298_sub31_sub1, "");
		OpenGL.glUniform4f(i, f, f_4_, f_5_, f_6_);
	    }
	}
    }

    @Override
    void method1164(Class298_Sub31_Sub1 class298_sub31_sub1, float[] fs, int i) {
	int i_7_ = ((Class298_Sub31_Sub1_Sub1) class298_sub31_sub1).method3125();
	if (i_7_ != -1)
	    OpenGL.glUniform4fv(i_7_, i, fs, 0);
    }

    @Override
    void method1192(Class298_Sub31_Sub1 class298_sub31_sub1, Class233 class233) {
	if (!aBoolean8622 && (class298_sub31_sub1.method3118(1145351512) != Class100.aClass100_963))
	    throw new AssertionError();
	int i = ((Class298_Sub31_Sub1_Sub1) class298_sub31_sub1).method3125();
	if (i != -1)
	    OpenGL.glUniform2fv(i, 4, class233.method2162(aFloatArray8614), 0);
    }

    @Override
    void method1166(Class298_Sub31_Sub1 class298_sub31_sub1, Class233 class233) {
	if (!aBoolean8622 && (class298_sub31_sub1.method3118(-1304354022) != Class100.aClass100_996))
	    throw new AssertionError();
	int i = ((Class298_Sub31_Sub1_Sub1) class298_sub31_sub1).method3125();
	if (i != -1)
	    OpenGL.glUniform4fv(i, 4, class233.method2173(aFloatArray8614), 0);
    }

    @Override
    void method1186(Class298_Sub31_Sub1 class298_sub31_sub1, float f, float f_8_, float f_9_) {
	int i = ((Class298_Sub31_Sub1_Sub1) class298_sub31_sub1).method3125();
	if (i != -1) {
	    if (this.aFloatArray8621[i] != f || this.aFloatArray8619[i] != f_8_ || this.aFloatArray8604[i] != f_9_) {
		this.aFloatArray8621[i] = f;
		this.aFloatArray8619[i] = f_8_;
		this.aFloatArray8604[i] = f_9_;
		if (class298_sub31_sub1.method3118(-860553518) != Class100.aClass100_995)
		    throw new IllegalArgumentException_Sub1(class298_sub31_sub1, "");
		OpenGL.glUniform3f(i, f, f_8_, f_9_);
	    }
	}
    }

    @Override
    void method1168(int i, float f, float f_10_, float f_11_) {
	OpenGL.glUniform3f(i, f, f_10_, f_11_);
    }

    @Override
    void method1169(int i, float f, float f_12_, float f_13_, float f_14_) {
	OpenGL.glUniform4f(i, f, f_12_, f_13_, f_14_);
    }

    @Override
    void method1158(int i, int i_15_, Interface9 interface9) {
	this.aClass_ra_Sub3_Sub1_8606.method5357(i_15_);
	this.aClass_ra_Sub3_Sub1_8606.method5358(interface9);
	OpenGL.glUniform1i(i, i_15_);
    }

    @Override
    void method1171(int i, Class233 class233) {
	OpenGL.glUniform3fv(i, 3, class233.method2165(aFloatArray8614), 0);
    }

    @Override
    void method1159(int i, Class233 class233) {
	OpenGL.glUniform2fv(i, 4, class233.method2162(aFloatArray8614), 0);
    }

    @Override
    void method1173(int i, Class233 class233) {
	OpenGL.glUniform4fv(i, 4, class233.method2173(aFloatArray8614), 0);
    }

    @Override
    void method1174(int i, int i_16_, Interface9 interface9) {
	this.aClass_ra_Sub3_Sub1_8606.method5357(i_16_);
	this.aClass_ra_Sub3_Sub1_8606.method5358(interface9);
	OpenGL.glUniform1i(i, i_16_);
    }

    @Override
    public void b() {
	if (this.anInt8608 != 0) {
	    this.aClass_ra_Sub3_Sub1_8606.method5538(this.anInt8608);
	    if (this.anInt8609 != 0)
		this.aClass_ra_Sub3_Sub1_8606.method5531(this.anInt8609);
	    if (this.anInt8615 != 0)
		this.aClass_ra_Sub3_Sub1_8606.method5531(this.anInt8615);
	    this.anInt8608 = 0;
	    this.anInt8609 = 0;
	    this.anInt8615 = 0;
	}
    }

    @Override
    void method1191(Class298_Sub31_Sub1 class298_sub31_sub1, float f, float f_17_) {
	int i = ((Class298_Sub31_Sub1_Sub1) class298_sub31_sub1).method3125();
	if (i != -1) {
	    if (this.aFloatArray8621[i] != f || this.aFloatArray8619[i] != f_17_) {
		this.aFloatArray8621[i] = f;
		this.aFloatArray8619[i] = f_17_;
		if (class298_sub31_sub1.method3118(923188879) != Class100.aClass100_1049)
		    throw new IllegalArgumentException_Sub1(class298_sub31_sub1, "");
		OpenGL.glUniform2f(i, f, f_17_);
	    }
	}
    }

    @Override
    void method1204(Class298_Sub31_Sub1 class298_sub31_sub1, Class233 class233) {
	if (!aBoolean8622 && (class298_sub31_sub1.method3118(156069945) != Class100.aClass100_996))
	    throw new AssertionError();
	int i = ((Class298_Sub31_Sub1_Sub1) class298_sub31_sub1).method3125();
	if (i != -1)
	    OpenGL.glUniform4fv(i, 4, class233.method2173(aFloatArray8614), 0);
    }

    @Override
    public void d() {
	if (this.anInt8608 != 0) {
	    this.aClass_ra_Sub3_Sub1_8606.method5538(this.anInt8608);
	    if (this.anInt8609 != 0)
		this.aClass_ra_Sub3_Sub1_8606.method5531(this.anInt8609);
	    if (this.anInt8615 != 0)
		this.aClass_ra_Sub3_Sub1_8606.method5531(this.anInt8615);
	    this.anInt8608 = 0;
	    this.anInt8609 = 0;
	    this.anInt8615 = 0;
	}
    }

    @Override
    public void u() {
	if (this.anInt8608 != 0) {
	    this.aClass_ra_Sub3_Sub1_8606.method5538(this.anInt8608);
	    if (this.anInt8609 != 0)
		this.aClass_ra_Sub3_Sub1_8606.method5531(this.anInt8609);
	    if (this.anInt8615 != 0)
		this.aClass_ra_Sub3_Sub1_8606.method5531(this.anInt8615);
	    this.anInt8608 = 0;
	    this.anInt8609 = 0;
	    this.anInt8615 = 0;
	}
    }

    @Override
    void method1212(Class298_Sub31_Sub1 class298_sub31_sub1, int i, Interface9 interface9) {
	int i_18_ = ((Class298_Sub31_Sub1_Sub1) class298_sub31_sub1).method3125();
	if (i_18_ != -1) {
	    if (interface9 == null)
		interface9 = (this.aClass_ra_Sub3_Sub1_8606.anInterface9_Impl2_8310);
	    if (i < this.aClass_ra_Sub3_Sub1_8606.anInt8347) {
		this.aClass_ra_Sub3_Sub1_8606.method5357(i);
		this.aClass_ra_Sub3_Sub1_8606.method5358(interface9);
	    } else {
		OpenGL.glActiveTexture(33984 + i);
		OpenGL.glBindTexture((((Class263) interface9).anInt6415), (((Class263) interface9).anInt6417));
	    }
	    if (this.anIntArray8613[i_18_] != i) {
		this.anIntArray8613[i_18_] = i;
		OpenGL.glUniform1i(i_18_, i);
	    }
	}
    }

    @Override
    public boolean method1209() {
	if (this.aBoolean8603)
	    return true;
	this.aClass_ra_Sub3_Sub1_8606.aClass110_Sub2_9832 = null;
	this.anInt8609 = method1232(35633, this.aString8620, aString6400);
	this.anInt8615 = method1232(35632, this.aString8616, aString6401);
	if (this.anInt8609 == 0 || this.anInt8615 == 0) {
	    if (this.anInt8609 != 0)
		OpenGL.glDeleteShader(this.anInt8609);
	    if (this.anInt8615 != 0)
		OpenGL.glDeleteShader(this.anInt8615);
	    return false;
	}
	this.anInt8608 = OpenGL.glCreateProgram();
	if (this.anInt8609 != 0)
	    OpenGL.glAttachShader(this.anInt8608, this.anInt8609);
	if (this.anInt8615 != 0)
	    OpenGL.glAttachShader(this.anInt8608, this.anInt8615);
	OpenGL.glLinkProgram(this.anInt8608);
	OpenGL.glGetProgramiv(this.anInt8608, 35714, anIntArray8610, 0);
	if (anIntArray8610[0] == 0) {
	    if (this.anInt8609 != 0) {
		OpenGL.glDetachShader(this.anInt8608, this.anInt8609);
		OpenGL.glDeleteShader(this.anInt8609);
	    }
	    if (this.anInt8615 != 0) {
		OpenGL.glDetachShader(this.anInt8608, this.anInt8615);
		OpenGL.glDeleteShader(this.anInt8615);
	    }
	    OpenGL.glDeleteProgram(this.anInt8608);
	    return false;
	}
	OpenGL.glUseProgram(this.anInt8608);
	int i = -1;
	for (int i_19_ = 0; i_19_ < this.aClass123_Sub2_8607.method1328(-1467018991); i_19_++) {
	    if (this.aClass123_Sub2_8607.method1369(i_19_, 776391021) == this) {
		i = i_19_;
		break;
	    }
	}
	if (i == -1)
	    return false;
	int i_20_ = 0;
	int i_21_ = 0;
	for (int i_22_ = 0; i_22_ < this.aClass123_Sub2_8607.method1323(-1707407208); i_22_++) {
	    Class298_Sub31_Sub1 class298_sub31_sub1 = this.aClass123_Sub2_8607.method1352(i_22_, 444661617);
	    if (class298_sub31_sub1 != null) {
		class298_sub31_sub1.method3124(i);
		Class100 class100 = class298_sub31_sub1.method3118(-1692962885);
		int i_23_ = (((Class298_Sub31_Sub1_Sub1) class298_sub31_sub1).anIntArray9994[i]);
		if (class100 == Class100.aClass100_977 || class100 == Class100.aClass100_1023 || class100 == Class100.aClass100_1049 || class100 == Class100.aClass100_995 || class100 == Class100.aClass100_1001)
		    i_20_ = Math.max(i_23_, i_20_);
		else
		    i_21_ = Math.max(i_23_, i_21_);
	    }
	}
	for (int i_24_ = 0; i_24_ < this.aClass123_Sub2_8607.method1350((byte) -98); i_24_++) {
	    Class298_Sub31_Sub1 class298_sub31_sub1 = this.aClass123_Sub2_8607.method1370(i_24_, 1888215794);
	    if (class298_sub31_sub1 != null) {
		class298_sub31_sub1.method3124(i);
		Class100 class100 = class298_sub31_sub1.method3118(-1962382933);
		int i_25_ = (((Class298_Sub31_Sub1_Sub1) class298_sub31_sub1).anIntArray9994[i]);
		if (class100 == Class100.aClass100_977 || class100 == Class100.aClass100_1023 || class100 == Class100.aClass100_1049 || class100 == Class100.aClass100_995 || class100 == Class100.aClass100_1001)
		    i_20_ = Math.max(i_25_, i_20_);
		else
		    i_21_ = Math.max(i_25_, i_21_);
	    }
	}
	this.aFloatArray8621 = new float[i_20_ + 1];
	this.aFloatArray8619 = new float[i_20_ + 1];
	this.aFloatArray8604 = new float[i_20_ + 1];
	this.aFloatArray8618 = new float[i_20_ + 1];
	this.anIntArray8613 = new int[i_21_ + 1];
	for (int i_26_ = 0; i_26_ < this.anIntArray8613.length; i_26_++)
	    this.anIntArray8613[i_26_] = -1;
	this.aString8620 = null;
	this.aString8616 = null;
	this.aBoolean8603 = true;
	return true;
    }

    @Override
    public boolean method1180() {
	if (this.aBoolean8603)
	    return true;
	this.aClass_ra_Sub3_Sub1_8606.aClass110_Sub2_9832 = null;
	this.anInt8609 = method1232(35633, this.aString8620, aString6400);
	this.anInt8615 = method1232(35632, this.aString8616, aString6401);
	if (this.anInt8609 == 0 || this.anInt8615 == 0) {
	    if (this.anInt8609 != 0)
		OpenGL.glDeleteShader(this.anInt8609);
	    if (this.anInt8615 != 0)
		OpenGL.glDeleteShader(this.anInt8615);
	    return false;
	}
	this.anInt8608 = OpenGL.glCreateProgram();
	if (this.anInt8609 != 0)
	    OpenGL.glAttachShader(this.anInt8608, this.anInt8609);
	if (this.anInt8615 != 0)
	    OpenGL.glAttachShader(this.anInt8608, this.anInt8615);
	OpenGL.glLinkProgram(this.anInt8608);
	OpenGL.glGetProgramiv(this.anInt8608, 35714, anIntArray8610, 0);
	if (anIntArray8610[0] == 0) {
	    if (this.anInt8609 != 0) {
		OpenGL.glDetachShader(this.anInt8608, this.anInt8609);
		OpenGL.glDeleteShader(this.anInt8609);
	    }
	    if (this.anInt8615 != 0) {
		OpenGL.glDetachShader(this.anInt8608, this.anInt8615);
		OpenGL.glDeleteShader(this.anInt8615);
	    }
	    OpenGL.glDeleteProgram(this.anInt8608);
	    return false;
	}
	OpenGL.glUseProgram(this.anInt8608);
	int i = -1;
	for (int i_27_ = 0; i_27_ < this.aClass123_Sub2_8607.method1328(-1467018991); i_27_++) {
	    if (this.aClass123_Sub2_8607.method1369(i_27_, 992402337) == this) {
		i = i_27_;
		break;
	    }
	}
	if (i == -1)
	    return false;
	int i_28_ = 0;
	int i_29_ = 0;
	for (int i_30_ = 0; i_30_ < this.aClass123_Sub2_8607.method1323(-392444295); i_30_++) {
	    Class298_Sub31_Sub1 class298_sub31_sub1 = this.aClass123_Sub2_8607.method1352(i_30_, 444661617);
	    if (class298_sub31_sub1 != null) {
		class298_sub31_sub1.method3124(i);
		Class100 class100 = class298_sub31_sub1.method3118(476806539);
		int i_31_ = (((Class298_Sub31_Sub1_Sub1) class298_sub31_sub1).anIntArray9994[i]);
		if (class100 == Class100.aClass100_977 || class100 == Class100.aClass100_1023 || class100 == Class100.aClass100_1049 || class100 == Class100.aClass100_995 || class100 == Class100.aClass100_1001)
		    i_28_ = Math.max(i_31_, i_28_);
		else
		    i_29_ = Math.max(i_31_, i_29_);
	    }
	}
	for (int i_32_ = 0; i_32_ < this.aClass123_Sub2_8607.method1350((byte) -19); i_32_++) {
	    Class298_Sub31_Sub1 class298_sub31_sub1 = this.aClass123_Sub2_8607.method1370(i_32_, 1888215794);
	    if (class298_sub31_sub1 != null) {
		class298_sub31_sub1.method3124(i);
		Class100 class100 = class298_sub31_sub1.method3118(-1096712367);
		int i_33_ = (((Class298_Sub31_Sub1_Sub1) class298_sub31_sub1).anIntArray9994[i]);
		if (class100 == Class100.aClass100_977 || class100 == Class100.aClass100_1023 || class100 == Class100.aClass100_1049 || class100 == Class100.aClass100_995 || class100 == Class100.aClass100_1001)
		    i_28_ = Math.max(i_33_, i_28_);
		else
		    i_29_ = Math.max(i_33_, i_29_);
	    }
	}
	this.aFloatArray8621 = new float[i_28_ + 1];
	this.aFloatArray8619 = new float[i_28_ + 1];
	this.aFloatArray8604 = new float[i_28_ + 1];
	this.aFloatArray8618 = new float[i_28_ + 1];
	this.anIntArray8613 = new int[i_29_ + 1];
	for (int i_34_ = 0; i_34_ < this.anIntArray8613.length; i_34_++)
	    this.anIntArray8613[i_34_] = -1;
	this.aString8620 = null;
	this.aString8616 = null;
	this.aBoolean8603 = true;
	return true;
    }

    @Override
    void method1182(int i, float[] fs, int i_35_) {
	OpenGL.glUniform4fv(i, i_35_, fs, 0);
    }

    @Override
    void method1165(Class298_Sub31_Sub1 class298_sub31_sub1, float f, float f_36_, float f_37_, float f_38_) {
	int i = ((Class298_Sub31_Sub1_Sub1) class298_sub31_sub1).method3125();
	if (i != -1) {
	    if (this.aFloatArray8621[i] != f || this.aFloatArray8619[i] != f_36_ || this.aFloatArray8604[i] != f_37_ || this.aFloatArray8618[i] != f_38_) {
		this.aFloatArray8621[i] = f;
		this.aFloatArray8619[i] = f_36_;
		this.aFloatArray8604[i] = f_37_;
		this.aFloatArray8618[i] = f_38_;
		if (class298_sub31_sub1.method3118(916523803) != Class100.aClass100_1001)
		    throw new IllegalArgumentException_Sub1(class298_sub31_sub1, "");
		OpenGL.glUniform4f(i, f, f_36_, f_37_, f_38_);
	    }
	}
    }

    @Override
    void method1183(Class298_Sub31_Sub1 class298_sub31_sub1, float f) {
	int i = ((Class298_Sub31_Sub1_Sub1) class298_sub31_sub1).method3125();
	if (i != -1) {
	    if (this.aFloatArray8621[i] != f) {
		this.aFloatArray8621[i] = f;
		if ((class298_sub31_sub1.method3118(-1443132322) != Class100.aClass100_977) && (class298_sub31_sub1.method3118(-248781322) != Class100.aClass100_1023))
		    throw new IllegalArgumentException_Sub1(class298_sub31_sub1, "");
		OpenGL.glUniform1f(i, f);
	    }
	}
    }

    @Override
    void method1207(Class298_Sub31_Sub1 class298_sub31_sub1, float f) {
	int i = ((Class298_Sub31_Sub1_Sub1) class298_sub31_sub1).method3125();
	if (i != -1) {
	    if (this.aFloatArray8621[i] != f) {
		this.aFloatArray8621[i] = f;
		if ((class298_sub31_sub1.method3118(1050782678) != Class100.aClass100_977) && (class298_sub31_sub1.method3118(-754995627) != Class100.aClass100_1023))
		    throw new IllegalArgumentException_Sub1(class298_sub31_sub1, "");
		OpenGL.glUniform1f(i, f);
	    }
	}
    }

    @Override
    void method1195(int i, float f, float f_39_, float f_40_) {
	OpenGL.glUniform3f(i, f, f_39_, f_40_);
    }

    @Override
    void method1167(Class298_Sub31_Sub1 class298_sub31_sub1, int i, Interface9 interface9) {
	int i_41_ = ((Class298_Sub31_Sub1_Sub1) class298_sub31_sub1).method3125();
	if (i_41_ != -1) {
	    if (interface9 == null)
		interface9 = (this.aClass_ra_Sub3_Sub1_8606.anInterface9_Impl2_8310);
	    if (i < this.aClass_ra_Sub3_Sub1_8606.anInt8347) {
		this.aClass_ra_Sub3_Sub1_8606.method5357(i);
		this.aClass_ra_Sub3_Sub1_8606.method5358(interface9);
	    } else {
		OpenGL.glActiveTexture(33984 + i);
		OpenGL.glBindTexture((((Class263) interface9).anInt6415), (((Class263) interface9).anInt6417));
	    }
	    if (this.anIntArray8613[i_41_] != i) {
		this.anIntArray8613[i_41_] = i;
		OpenGL.glUniform1i(i_41_, i);
	    }
	}
    }

    @Override
    void method1184(Class298_Sub31_Sub1 class298_sub31_sub1, int i, Interface9 interface9) {
	int i_42_ = ((Class298_Sub31_Sub1_Sub1) class298_sub31_sub1).method3125();
	if (i_42_ != -1) {
	    if (interface9 == null)
		interface9 = (this.aClass_ra_Sub3_Sub1_8606.anInterface9_Impl2_8310);
	    if (i < this.aClass_ra_Sub3_Sub1_8606.anInt8347) {
		this.aClass_ra_Sub3_Sub1_8606.method5357(i);
		this.aClass_ra_Sub3_Sub1_8606.method5358(interface9);
	    } else {
		OpenGL.glActiveTexture(33984 + i);
		OpenGL.glBindTexture((((Class263) interface9).anInt6415), (((Class263) interface9).anInt6417));
	    }
	    if (this.anIntArray8613[i_42_] != i) {
		this.anIntArray8613[i_42_] = i;
		OpenGL.glUniform1i(i_42_, i);
	    }
	}
    }

    @Override
    void method1188(Class298_Sub31_Sub1 class298_sub31_sub1, float f, float f_43_, float f_44_) {
	int i = ((Class298_Sub31_Sub1_Sub1) class298_sub31_sub1).method3125();
	if (i != -1) {
	    if (this.aFloatArray8621[i] != f || this.aFloatArray8619[i] != f_43_ || this.aFloatArray8604[i] != f_44_) {
		this.aFloatArray8621[i] = f;
		this.aFloatArray8619[i] = f_43_;
		this.aFloatArray8604[i] = f_44_;
		if (class298_sub31_sub1.method3118(-1493198773) != Class100.aClass100_995)
		    throw new IllegalArgumentException_Sub1(class298_sub31_sub1, "");
		OpenGL.glUniform3f(i, f, f_43_, f_44_);
	    }
	}
    }

    @Override
    void method1179(Class298_Sub31_Sub1 class298_sub31_sub1, float f, float f_45_, float f_46_) {
	int i = ((Class298_Sub31_Sub1_Sub1) class298_sub31_sub1).method3125();
	if (i != -1) {
	    if (this.aFloatArray8621[i] != f || this.aFloatArray8619[i] != f_45_ || this.aFloatArray8604[i] != f_46_) {
		this.aFloatArray8621[i] = f;
		this.aFloatArray8619[i] = f_45_;
		this.aFloatArray8604[i] = f_46_;
		if (class298_sub31_sub1.method3118(-1865875752) != Class100.aClass100_995)
		    throw new IllegalArgumentException_Sub1(class298_sub31_sub1, "");
		OpenGL.glUniform3f(i, f, f_45_, f_46_);
	    }
	}
    }

    @Override
    void method1189(Class298_Sub31_Sub1 class298_sub31_sub1, float f, float f_47_, float f_48_, float f_49_) {
	int i = ((Class298_Sub31_Sub1_Sub1) class298_sub31_sub1).method3125();
	if (i != -1) {
	    if (this.aFloatArray8621[i] != f || this.aFloatArray8619[i] != f_47_ || this.aFloatArray8604[i] != f_48_ || this.aFloatArray8618[i] != f_49_) {
		this.aFloatArray8621[i] = f;
		this.aFloatArray8619[i] = f_47_;
		this.aFloatArray8604[i] = f_48_;
		this.aFloatArray8618[i] = f_49_;
		if (class298_sub31_sub1.method3118(-166326174) != Class100.aClass100_1001)
		    throw new IllegalArgumentException_Sub1(class298_sub31_sub1, "");
		OpenGL.glUniform4f(i, f, f_47_, f_48_, f_49_);
	    }
	}
    }

    @Override
    void method1219(int i, float f, float f_50_, float f_51_) {
	OpenGL.glUniform3f(i, f, f_50_, f_51_);
    }

    Class110_Sub2(Class_ra_Sub3_Sub1 class_ra_sub3_sub1, Class123_Sub2 class123_sub2, Class108 class108) {
	this(class_ra_sub3_sub1, class108);
	this.aClass123_Sub2_8607 = class123_sub2;
	this.aClass_ra_Sub3_Sub1_8606 = class_ra_sub3_sub1;
    }

    @Override
    void method1178(Class298_Sub31_Sub1 class298_sub31_sub1, float f, float f_52_, float f_53_, float f_54_) {
	int i = ((Class298_Sub31_Sub1_Sub1) class298_sub31_sub1).method3125();
	if (i != -1) {
	    if (this.aFloatArray8621[i] != f || this.aFloatArray8619[i] != f_52_ || this.aFloatArray8604[i] != f_53_ || this.aFloatArray8618[i] != f_54_) {
		this.aFloatArray8621[i] = f;
		this.aFloatArray8619[i] = f_52_;
		this.aFloatArray8604[i] = f_53_;
		this.aFloatArray8618[i] = f_54_;
		if (class298_sub31_sub1.method3118(817121503) != Class100.aClass100_1001)
		    throw new IllegalArgumentException_Sub1(class298_sub31_sub1, "");
		OpenGL.glUniform4f(i, f, f_52_, f_53_, f_54_);
	    }
	}
    }

    @Override
    void method1193(Class298_Sub31_Sub1 class298_sub31_sub1, float f, float f_55_, float f_56_, float f_57_) {
	int i = ((Class298_Sub31_Sub1_Sub1) class298_sub31_sub1).method3125();
	if (i != -1) {
	    if (this.aFloatArray8621[i] != f || this.aFloatArray8619[i] != f_55_ || this.aFloatArray8604[i] != f_56_ || this.aFloatArray8618[i] != f_57_) {
		this.aFloatArray8621[i] = f;
		this.aFloatArray8619[i] = f_55_;
		this.aFloatArray8604[i] = f_56_;
		this.aFloatArray8618[i] = f_57_;
		if (class298_sub31_sub1.method3118(1237256762) != Class100.aClass100_1001)
		    throw new IllegalArgumentException_Sub1(class298_sub31_sub1, "");
		OpenGL.glUniform4f(i, f, f_55_, f_56_, f_57_);
	    }
	}
    }

    @Override
    void method1194(Class298_Sub31_Sub1 class298_sub31_sub1, float f, float f_58_, float f_59_, float f_60_) {
	int i = ((Class298_Sub31_Sub1_Sub1) class298_sub31_sub1).method3125();
	if (i != -1) {
	    if (this.aFloatArray8621[i] != f || this.aFloatArray8619[i] != f_58_ || this.aFloatArray8604[i] != f_59_ || this.aFloatArray8618[i] != f_60_) {
		this.aFloatArray8621[i] = f;
		this.aFloatArray8619[i] = f_58_;
		this.aFloatArray8604[i] = f_59_;
		this.aFloatArray8618[i] = f_60_;
		if (class298_sub31_sub1.method3118(722666230) != Class100.aClass100_1001)
		    throw new IllegalArgumentException_Sub1(class298_sub31_sub1, "");
		OpenGL.glUniform4f(i, f, f_58_, f_59_, f_60_);
	    }
	}
    }

    @Override
    void method1177(Class298_Sub31_Sub1 class298_sub31_sub1, float[] fs, int i) {
	int i_61_ = ((Class298_Sub31_Sub1_Sub1) class298_sub31_sub1).method3125();
	if (i_61_ != -1)
	    OpenGL.glUniform4fv(i_61_, i, fs, 0);
    }

    @Override
    void method1196(Class298_Sub31_Sub1 class298_sub31_sub1, float[] fs, int i) {
	int i_62_ = ((Class298_Sub31_Sub1_Sub1) class298_sub31_sub1).method3125();
	if (i_62_ != -1)
	    OpenGL.glUniform4fv(i_62_, i, fs, 0);
    }

    @Override
    void method1210(int i, float f, float f_63_, float f_64_) {
	OpenGL.glUniform3f(i, f, f_63_, f_64_);
    }

    @Override
    void method1198(Class298_Sub31_Sub1 class298_sub31_sub1, Class233 class233) {
	if (!aBoolean8622 && (class298_sub31_sub1.method3118(205023346) != Class100.aClass100_963))
	    throw new AssertionError();
	int i = ((Class298_Sub31_Sub1_Sub1) class298_sub31_sub1).method3125();
	if (i != -1)
	    OpenGL.glUniform2fv(i, 4, class233.method2162(aFloatArray8614), 0);
    }

    @Override
    void method1215(Class298_Sub31_Sub1 class298_sub31_sub1, Class233 class233) {
	if (!aBoolean8622 && (class298_sub31_sub1.method3118(-242428530) != Class100.aClass100_963))
	    throw new AssertionError();
	int i = ((Class298_Sub31_Sub1_Sub1) class298_sub31_sub1).method3125();
	if (i != -1)
	    OpenGL.glUniform2fv(i, 4, class233.method2162(aFloatArray8614), 0);
    }

    @Override
    void method1200(Class298_Sub31_Sub1 class298_sub31_sub1, Class233 class233) {
	if (!aBoolean8622 && (class298_sub31_sub1.method3118(-405889299) != Class100.aClass100_963))
	    throw new AssertionError();
	int i = ((Class298_Sub31_Sub1_Sub1) class298_sub31_sub1).method3125();
	if (i != -1)
	    OpenGL.glUniform2fv(i, 4, class233.method2162(aFloatArray8614), 0);
    }

    @Override
    void method1216(Class298_Sub31_Sub1 class298_sub31_sub1, Class233 class233) {
	if (!aBoolean8622 && (class298_sub31_sub1.method3118(-12138906) != Class100.aClass100_963))
	    throw new AssertionError();
	int i = ((Class298_Sub31_Sub1_Sub1) class298_sub31_sub1).method3125();
	if (i != -1)
	    OpenGL.glUniform2fv(i, 4, class233.method2162(aFloatArray8614), 0);
    }

    @Override
    void method1181(Class298_Sub31_Sub1 class298_sub31_sub1, float f) {
	int i = ((Class298_Sub31_Sub1_Sub1) class298_sub31_sub1).method3125();
	if (i != -1) {
	    if (this.aFloatArray8621[i] != f) {
		this.aFloatArray8621[i] = f;
		if ((class298_sub31_sub1.method3118(-1085365061) != Class100.aClass100_977) && (class298_sub31_sub1.method3118(37824188) != Class100.aClass100_1023))
		    throw new IllegalArgumentException_Sub1(class298_sub31_sub1, "");
		OpenGL.glUniform1f(i, f);
	    }
	}
    }

    @Override
    void method1202(Class298_Sub31_Sub1 class298_sub31_sub1, Class233 class233) {
	if (!aBoolean8622 && (class298_sub31_sub1.method3118(-1779371622) != Class100.aClass100_963))
	    throw new AssertionError();
	int i = ((Class298_Sub31_Sub1_Sub1) class298_sub31_sub1).method3125();
	if (i != -1)
	    OpenGL.glUniform2fv(i, 4, class233.method2162(aFloatArray8614), 0);
    }

    @Override
    void method1203(Class298_Sub31_Sub1 class298_sub31_sub1, Class233 class233) {
	if (!aBoolean8622 && (class298_sub31_sub1.method3118(-1672915271) != Class100.aClass100_996))
	    throw new AssertionError();
	int i = ((Class298_Sub31_Sub1_Sub1) class298_sub31_sub1).method3125();
	if (i != -1)
	    OpenGL.glUniform4fv(i, 4, class233.method2173(aFloatArray8614), 0);
    }

    @Override
    public void x() {
	if (this.anInt8608 != 0) {
	    this.aClass_ra_Sub3_Sub1_8606.method5538(this.anInt8608);
	    if (this.anInt8609 != 0)
		this.aClass_ra_Sub3_Sub1_8606.method5531(this.anInt8609);
	    if (this.anInt8615 != 0)
		this.aClass_ra_Sub3_Sub1_8606.method5531(this.anInt8615);
	    this.anInt8608 = 0;
	    this.anInt8609 = 0;
	    this.anInt8615 = 0;
	}
    }

    @Override
    void method1187(Class298_Sub31_Sub1 class298_sub31_sub1, Class233 class233) {
	if (!aBoolean8622 && (class298_sub31_sub1.method3118(941424226) != Class100.aClass100_996))
	    throw new AssertionError();
	int i = ((Class298_Sub31_Sub1_Sub1) class298_sub31_sub1).method3125();
	if (i != -1)
	    OpenGL.glUniform4fv(i, 4, class233.method2173(aFloatArray8614), 0);
    }

    @Override
    void method1205(Class298_Sub31_Sub1 class298_sub31_sub1, float f, float f_65_, float f_66_, float f_67_) {
	int i = ((Class298_Sub31_Sub1_Sub1) class298_sub31_sub1).method3125();
	if (i != -1) {
	    if (this.aFloatArray8621[i] != f || this.aFloatArray8619[i] != f_65_ || this.aFloatArray8604[i] != f_66_ || this.aFloatArray8618[i] != f_67_) {
		this.aFloatArray8621[i] = f;
		this.aFloatArray8619[i] = f_65_;
		this.aFloatArray8604[i] = f_66_;
		this.aFloatArray8618[i] = f_67_;
		if (class298_sub31_sub1.method3118(363908029) != Class100.aClass100_1001)
		    throw new IllegalArgumentException_Sub1(class298_sub31_sub1, "");
		OpenGL.glUniform4f(i, f, f_65_, f_66_, f_67_);
	    }
	}
    }

    @Override
    void method1190(Class298_Sub31_Sub1 class298_sub31_sub1, float f) {
	int i = ((Class298_Sub31_Sub1_Sub1) class298_sub31_sub1).method3125();
	if (i != -1) {
	    if (this.aFloatArray8621[i] != f) {
		this.aFloatArray8621[i] = f;
		if ((class298_sub31_sub1.method3118(-1630439679) != Class100.aClass100_977) && (class298_sub31_sub1.method3118(-142026573) != Class100.aClass100_1023))
		    throw new IllegalArgumentException_Sub1(class298_sub31_sub1, "");
		OpenGL.glUniform1f(i, f);
	    }
	}
    }

    @Override
    void method1208(Class298_Sub31_Sub1 class298_sub31_sub1, int i, Interface9 interface9) {
	int i_68_ = ((Class298_Sub31_Sub1_Sub1) class298_sub31_sub1).method3125();
	if (i_68_ != -1) {
	    if (interface9 == null)
		interface9 = (this.aClass_ra_Sub3_Sub1_8606.anInterface9_Impl2_8310);
	    if (i < this.aClass_ra_Sub3_Sub1_8606.anInt8347) {
		this.aClass_ra_Sub3_Sub1_8606.method5357(i);
		this.aClass_ra_Sub3_Sub1_8606.method5358(interface9);
	    } else {
		OpenGL.glActiveTexture(33984 + i);
		OpenGL.glBindTexture((((Class263) interface9).anInt6415), (((Class263) interface9).anInt6417));
	    }
	    if (this.anIntArray8613[i_68_] != i) {
		this.anIntArray8613[i_68_] = i;
		OpenGL.glUniform1i(i_68_, i);
	    }
	}
    }

    int method1232(int i, String string, String string_69_) {
	if (string == null)
	    return 0;
	int i_70_ = OpenGL.glCreateShader(i);
	OpenGL.glShaderSource(i_70_, string);
	OpenGL.glCompileShader(i_70_);
	OpenGL.glGetShaderiv(i_70_, 35713, anIntArray8610, 0);
	if (anIntArray8610[0] == 0) {
	    OpenGL.glDeleteShader(i_70_);
	    i_70_ = 0;
	}
	return i_70_;
    }

    @Override
    void method1185(Class298_Sub31_Sub1 class298_sub31_sub1, float f, float f_71_) {
	int i = ((Class298_Sub31_Sub1_Sub1) class298_sub31_sub1).method3125();
	if (i != -1) {
	    if (this.aFloatArray8621[i] != f || this.aFloatArray8619[i] != f_71_) {
		this.aFloatArray8621[i] = f;
		this.aFloatArray8619[i] = f_71_;
		if (class298_sub31_sub1.method3118(-479725231) != Class100.aClass100_1049)
		    throw new IllegalArgumentException_Sub1(class298_sub31_sub1, "");
		OpenGL.glUniform2f(i, f, f_71_);
	    }
	}
    }

    @Override
    void method1211(int i, float f, float f_72_, float f_73_) {
	OpenGL.glUniform3f(i, f, f_72_, f_73_);
    }

    @Override
    void method1206(int i, float f, float f_74_, float f_75_) {
	OpenGL.glUniform3f(i, f, f_74_, f_75_);
    }

    @Override
    void method1213(int i, float f, float f_76_, float f_77_) {
	OpenGL.glUniform3f(i, f, f_76_, f_77_);
    }

    @Override
    void method1197(Class298_Sub31_Sub1 class298_sub31_sub1, Class233 class233) {
	if (!aBoolean8622 && (class298_sub31_sub1.method3118(123355562) != Class100.aClass100_963))
	    throw new AssertionError();
	int i = ((Class298_Sub31_Sub1_Sub1) class298_sub31_sub1).method3125();
	if (i != -1)
	    OpenGL.glUniform2fv(i, 4, class233.method2162(aFloatArray8614), 0);
    }

    @Override
    void method1214(int i, float[] fs, int i_78_) {
	OpenGL.glUniform4fv(i, i_78_, fs, 0);
    }

    static {
	aBoolean8622 = !Class110_Sub2.class.desiredAssertionStatus();
	anIntArray8610 = new int[2];
	aFloatArray8614 = new float[16];
    }

    @Override
    void method1172(int i, Class233 class233) {
	OpenGL.glUniform3fv(i, 3, class233.method2165(aFloatArray8614), 0);
    }

    @Override
    void method1175(int i, Class233 class233) {
	OpenGL.glUniform2fv(i, 4, class233.method2162(aFloatArray8614), 0);
    }

    @Override
    void method1217(int i, Class233 class233) {
	OpenGL.glUniform4fv(i, 4, class233.method2173(aFloatArray8614), 0);
    }

    @Override
    void method1218(int i, int i_79_, Interface9 interface9) {
	this.aClass_ra_Sub3_Sub1_8606.method5357(i_79_);
	this.aClass_ra_Sub3_Sub1_8606.method5358(interface9);
	OpenGL.glUniform1i(i, i_79_);
    }

    @Override
    public boolean method1221() {
	if (this.aBoolean8603)
	    return true;
	this.aClass_ra_Sub3_Sub1_8606.aClass110_Sub2_9832 = null;
	this.anInt8609 = method1232(35633, this.aString8620, aString6400);
	this.anInt8615 = method1232(35632, this.aString8616, aString6401);
	if (this.anInt8609 == 0 || this.anInt8615 == 0) {
	    if (this.anInt8609 != 0)
		OpenGL.glDeleteShader(this.anInt8609);
	    if (this.anInt8615 != 0)
		OpenGL.glDeleteShader(this.anInt8615);
	    return false;
	}
	this.anInt8608 = OpenGL.glCreateProgram();
	if (this.anInt8609 != 0)
	    OpenGL.glAttachShader(this.anInt8608, this.anInt8609);
	if (this.anInt8615 != 0)
	    OpenGL.glAttachShader(this.anInt8608, this.anInt8615);
	OpenGL.glLinkProgram(this.anInt8608);
	OpenGL.glGetProgramiv(this.anInt8608, 35714, anIntArray8610, 0);
	if (anIntArray8610[0] == 0) {
	    if (this.anInt8609 != 0) {
		OpenGL.glDetachShader(this.anInt8608, this.anInt8609);
		OpenGL.glDeleteShader(this.anInt8609);
	    }
	    if (this.anInt8615 != 0) {
		OpenGL.glDetachShader(this.anInt8608, this.anInt8615);
		OpenGL.glDeleteShader(this.anInt8615);
	    }
	    OpenGL.glDeleteProgram(this.anInt8608);
	    return false;
	}
	OpenGL.glUseProgram(this.anInt8608);
	int i = -1;
	for (int i_80_ = 0; i_80_ < this.aClass123_Sub2_8607.method1328(-1467018991); i_80_++) {
	    if (this.aClass123_Sub2_8607.method1369(i_80_, 1618766328) == this) {
		i = i_80_;
		break;
	    }
	}
	if (i == -1)
	    return false;
	int i_81_ = 0;
	int i_82_ = 0;
	for (int i_83_ = 0; i_83_ < this.aClass123_Sub2_8607.method1323(-851486259); i_83_++) {
	    Class298_Sub31_Sub1 class298_sub31_sub1 = this.aClass123_Sub2_8607.method1352(i_83_, 444661617);
	    if (class298_sub31_sub1 != null) {
		class298_sub31_sub1.method3124(i);
		Class100 class100 = class298_sub31_sub1.method3118(1111290915);
		int i_84_ = (((Class298_Sub31_Sub1_Sub1) class298_sub31_sub1).anIntArray9994[i]);
		if (class100 == Class100.aClass100_977 || class100 == Class100.aClass100_1023 || class100 == Class100.aClass100_1049 || class100 == Class100.aClass100_995 || class100 == Class100.aClass100_1001)
		    i_81_ = Math.max(i_84_, i_81_);
		else
		    i_82_ = Math.max(i_84_, i_82_);
	    }
	}
	for (int i_85_ = 0; i_85_ < this.aClass123_Sub2_8607.method1350((byte) -85); i_85_++) {
	    Class298_Sub31_Sub1 class298_sub31_sub1 = this.aClass123_Sub2_8607.method1370(i_85_, 1888215794);
	    if (class298_sub31_sub1 != null) {
		class298_sub31_sub1.method3124(i);
		Class100 class100 = class298_sub31_sub1.method3118(-1614356298);
		int i_86_ = (((Class298_Sub31_Sub1_Sub1) class298_sub31_sub1).anIntArray9994[i]);
		if (class100 == Class100.aClass100_977 || class100 == Class100.aClass100_1023 || class100 == Class100.aClass100_1049 || class100 == Class100.aClass100_995 || class100 == Class100.aClass100_1001)
		    i_81_ = Math.max(i_86_, i_81_);
		else
		    i_82_ = Math.max(i_86_, i_82_);
	    }
	}
	this.aFloatArray8621 = new float[i_81_ + 1];
	this.aFloatArray8619 = new float[i_81_ + 1];
	this.aFloatArray8604 = new float[i_81_ + 1];
	this.aFloatArray8618 = new float[i_81_ + 1];
	this.anIntArray8613 = new int[i_82_ + 1];
	for (int i_87_ = 0; i_87_ < this.anIntArray8613.length; i_87_++)
	    this.anIntArray8613[i_87_] = -1;
	this.aString8620 = null;
	this.aString8616 = null;
	this.aBoolean8603 = true;
	return true;
    }

    @Override
    void method1220(int i, int i_88_, Interface9 interface9) {
	this.aClass_ra_Sub3_Sub1_8606.method5357(i_88_);
	this.aClass_ra_Sub3_Sub1_8606.method5358(interface9);
	OpenGL.glUniform1i(i, i_88_);
    }

    @Override
    void method1199(int i, int i_89_, Interface9 interface9) {
	this.aClass_ra_Sub3_Sub1_8606.method5357(i_89_);
	this.aClass_ra_Sub3_Sub1_8606.method5358(interface9);
	OpenGL.glUniform1i(i, i_89_);
    }

    @Override
    void method1222(int i, float f, float f_90_, float f_91_, float f_92_) {
	OpenGL.glUniform4f(i, f, f_90_, f_91_, f_92_);
    }

    @Override
    void method1201(Class298_Sub31_Sub1 class298_sub31_sub1, Class233 class233) {
	if (!aBoolean8622 && (class298_sub31_sub1.method3118(-2072244145) != Class100.aClass100_963))
	    throw new AssertionError();
	int i = ((Class298_Sub31_Sub1_Sub1) class298_sub31_sub1).method3125();
	if (i != -1)
	    OpenGL.glUniform2fv(i, 4, class233.method2162(aFloatArray8614), 0);
    }
}
