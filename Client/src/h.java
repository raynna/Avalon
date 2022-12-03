/* h - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class h extends Class387 implements Interface25 {
    long nativeid;
    ja aJa6674;
    ba aBa6675;
    Class68[] aClass68Array6676;
    Class85[] aClass85Array6677;

    @Override
    public native int dp();

    h(ja var_ja) {
	this.aJa6674 = var_ja;
	this.aBa6675 = null;
	ba(var_ja);
    }

    @Override
    void method4757() {
	if (this.aJa6674.anInt6692 > 1) {
	    synchronized (this) {
		while (aBoolean4148) {
		    try {
			this.wait();
		    }
		    catch (InterruptedException interruptedexception) {
			/* empty */
		    }
		}
		aBoolean4148 = true;
	    }
	}
    }

    native void ba(ja var_ja);

    @Override
    public native void ma(boolean bool);

    @Override
    public void method4786(Class222 class222) {
	method4852(ja.anIntArray6681, class222);
	int i = 0;
	if (this.aClass85Array6677 != null) {
	    for (int i_0_ = 0; i_0_ < this.aClass85Array6677.length; i_0_++) {
		Class85 class85 = this.aClass85Array6677[i_0_];
		class85.anInt777 = ja.anIntArray6681[i++] * -1879868075;
		class85.anInt783 = ja.anIntArray6681[i++] * -2041556771;
		class85.anInt779 = ja.anIntArray6681[i++] * -1434499227;
		class85.anInt772 = ja.anIntArray6681[i++] * 1070341177;
		class85.anInt781 = ja.anIntArray6681[i++] * 1802851857;
		class85.anInt782 = ja.anIntArray6681[i++] * 103846281;
		class85.anInt771 = ja.anIntArray6681[i++] * -2103324039;
		class85.anInt784 = ja.anIntArray6681[i++] * -526039059;
		class85.anInt785 = ja.anIntArray6681[i++] * 491030489;
	    }
	}
	if (this.aClass68Array6676 != null) {
	    for (int i_1_ = 0; i_1_ < this.aClass68Array6676.length; i_1_++) {
		Class68 class68 = this.aClass68Array6676[i_1_];
		Class68 class68_2_ = class68;
		if (class68.aClass68_672 != null)
		    class68_2_ = class68.aClass68_672;
		if (class68.aClass233_677 == null)
		    class68.aClass233_677 = new Class233();
		class68.aClass233_677.method2145(class222);
		class68_2_.anInt671 = ja.anIntArray6681[i++] * -1436341053;
		class68_2_.anInt675 = ja.anIntArray6681[i++] * 449866009;
		class68_2_.anInt676 = ja.anIntArray6681[i++] * 1336328763;
	    }
	}
    }

    void method4852(int[] is, Class222 class222) {
	this.aJa6674.method5571().method280(this, is, class222);
    }

    @Override
    public Class387 method4755(byte i, int i_3_, boolean bool) {
	return this.aJa6674.method5571().method276(this, i, i_3_, bool);
    }

    native void BA(h var_h_4_, h var_h_5_, int i, boolean bool, boolean bool_6_);

    @Override
    public void method4784() {
	/* empty */
    }

    @Override
    public native int m();

    @Override
    public void method4745(Class387 class387, int i, int i_7_, int i_8_, boolean bool) {
	this.aJa6674.method5571().method285(this, class387, i, i_7_, i_8_, bool);
    }

    @Override
    public native void f(int i);

    @Override
    public native void S(int i);

    @Override
    public native void t(int i);

    @Override
    public native void EA(int i);

    @Override
    public native void ia(int i, int i_9_, int i_10_);

    @Override
    public native void wa();

    @Override
    public native void by(int i);

    @Override
    public native void pa(int i, int i_11_, Class_xa class_xa, Class_xa class_xa_12_, int i_13_, int i_14_, int i_15_);

    @Override
    public native int ya();

    @Override
    public native int o();

    @Override
    native boolean ea();

    @Override
    final void method4738(int i, int[] is, int i_16_, int i_17_, int i_18_, int i_19_, boolean bool) {
	J(this.nativeid, i, is, i_16_, i_17_, i_18_, i_19_, bool);
    }

    @Override
    public native void bc(int i);

    @Override
    native void e(int i, int[] is, int i_20_, int i_21_, int i_22_, boolean bool, int i_23_, int[] is_24_);

    @Override
    public native void bs(int i, int i_25_, Class_xa class_xa, Class_xa class_xa_26_, int i_27_, int i_28_, int i_29_);

    @Override
    public native void bl(int i, int i_30_, int i_31_);

    @Override
    public void method4741(Class222 class222, int i, boolean bool) {
	Class233 class233 = this.aJa6674.method5571().aClass233_6673;
	class233.method2145(class222);
	aa(class233.aFloatArray2594, i, bool);
    }

    native void aa(float[] fs, int i, boolean bool);

    native void fq(long l, int i, int[] is, int i_32_, int i_33_, int i_34_, int i_35_, boolean bool);

    @Override
    public boolean method4787(int i, int i_36_, Class222 class222, boolean bool, int i_37_) {
	return this.aJa6674.method5571().method284(this, i, i_36_, class222, bool);
    }

    @Override
    public void method4779(Class387 class387, int i, int i_38_, int i_39_, boolean bool) {
	this.aJa6674.method5571().method285(this, class387, i, i_38_, i_39_, bool);
    }

    @Override
    public native int N();

    @Override
    public native int n();

    @Override
    public native void dc(int i);

    @Override
    public native int cu();

    @Override
    public native int YA();

    @Override
    public native Class_na ct(Class_na class_na);

    @Override
    native void cw(int i, int i_40_, int i_41_, int i_42_);

    @Override
    public native int ha();

    @Override
    public native void p(int i);

    @Override
    public native void Q(int i);

    @Override
    public native int c();

    @Override
    native void ka();

    @Override
    public native byte[] ah();

    @Override
    public native void X(short i, short i_43_);

    native void fp(h var_h_44_, h var_h_45_, int i, boolean bool, boolean bool_46_);

    native void IA(byte i, byte[] is);

    @Override
    public Class68[] method4774() {
	return this.aClass68Array6676;
    }

    @Override
    public native void PA(int i, int i_47_, int i_48_, int i_49_);

    @Override
    public boolean method4743() {
	return true;
    }

    @Override
    public native void dz(short i, short i_50_);

    @Override
    void method4734() {
	if (this.aJa6674.anInt6692 > 1) {
	    synchronized (this) {
		aBoolean4148 = false;
		this.notifyAll();
	    }
	}
    }

    @Override
    public Class85[] method4781() {
	return this.aClass85Array6677;
    }

    @Override
    public Class68[] method4728() {
	return this.aClass68Array6676;
    }

    @Override
    public native void ey(short i, short i_51_);

    @Override
    public Class85[] method4772() {
	return this.aClass85Array6677;
    }

    native void U(ja var_ja, ba var_ba, int i, int i_52_, int[] is, int[] is_53_, int[] is_54_, int[] is_55_, short[] is_56_, int i_57_, short[] is_58_, short[] is_59_, short[] is_60_, byte[] is_61_, byte[] is_62_, byte[] is_63_, byte[] is_64_, short[] is_65_, short[] is_66_, int[] is_67_, byte i_68_, short[] is_69_, int i_70_, byte[] is_71_, short[] is_72_, short[] is_73_, short[] is_74_, int[] is_75_, int[] is_76_, int[] is_77_, byte[] is_78_, byte[] is_79_, int[] is_80_, int[] is_81_, int[] is_82_, int[] is_83_, int i_84_, int i_85_, int i_86_, int i_87_, int i_88_, int i_89_, int[] is_90_);

    @Override
    public Class387 method4748(byte i, int i_91_, boolean bool) {
	return this.aJa6674.method5571().method276(this, i, i_91_, bool);
    }

    @Override
    public Class387 method4770(byte i, int i_92_, boolean bool) {
	return this.aJa6674.method5571().method276(this, i, i_92_, bool);
    }

    @Override
    public Class387 method4749(byte i, int i_93_, boolean bool) {
	return this.aJa6674.method5571().method276(this, i, i_93_, bool);
    }

    @Override
    public native boolean i();

    @Override
    public native int an();

    @Override
    public native void au(int i);

    @Override
    public native void ar(int i);

    @Override
    public native void ac(int i);

    @Override
    public void method4752() {
	/* empty */
    }

    @Override
    public void method4764() {
	/* empty */
    }

    @Override
    public native void bf(int i);

    @Override
    public native void be(int i);

    @Override
    native void cl(int i, int[] is, int i_94_, int i_95_, int i_96_, boolean bool, int i_97_, int[] is_98_);

    @Override
    public native void bm(int i);

    @Override
    public native void W(short i, short i_99_);

    @Override
    public native void bx(int i);

    @Override
    public native void bo(int i);

    @Override
    public native void df(short i, short i_100_);

    @Override
    public void method4740(Class222 class222, Class302_Sub1 class302_sub1, int i) {
	if (class302_sub1 == null)
	    this.aJa6674.method5571().method279(this, class222, null, i);
	else {
	    ja.anIntArray6704[5] = 0;
	    this.aJa6674.method5571().method279(this, class222, ja.anIntArray6704, i);
	    class302_sub1.anInt7641 = ja.anIntArray6704[0];
	    class302_sub1.anInt7642 = ja.anIntArray6704[1];
	    class302_sub1.anInt7643 = ja.anIntArray6704[2];
	    class302_sub1.anInt7640 = ja.anIntArray6704[3];
	    class302_sub1.anInt7645 = ja.anIntArray6704[4];
	    class302_sub1.aBoolean7644 = ja.anIntArray6704[5] != 0;
	}
    }

    @Override
    public native void bw(int i, int i_101_, int i_102_);

    @Override
    public native void bk(int i, int i_103_, int i_104_);

    @Override
    public native void bq(int i, int i_105_, int i_106_);

    @Override
    public native void bg(int i, int i_107_, Class_xa class_xa, Class_xa class_xa_108_, int i_109_, int i_110_, int i_111_);

    @Override
    public native int RA();

    @Override
    public native void bp(int i, int i_112_, Class_xa class_xa, Class_xa class_xa_113_, int i_114_, int i_115_, int i_116_);

    @Override
    void method4754() {
	if (this.aJa6674.anInt6692 > 1) {
	    synchronized (this) {
		while (aBoolean4148) {
		    try {
			this.wait();
		    }
		    catch (InterruptedException interruptedexception) {
			/* empty */
		    }
		}
		aBoolean4148 = true;
	    }
	}
    }

    @Override
    public void method4747(Class387 class387, int i, int i_117_, int i_118_, boolean bool) {
	this.aJa6674.method5571().method285(this, class387, i, i_117_, i_118_, bool);
    }

    @Override
    public void method4739(Class222 class222, Class302_Sub1 class302_sub1, int i) {
	if (class302_sub1 == null)
	    this.aJa6674.method5571().method279(this, class222, null, i);
	else {
	    ja.anIntArray6704[5] = 0;
	    this.aJa6674.method5571().method279(this, class222, ja.anIntArray6704, i);
	    class302_sub1.anInt7641 = ja.anIntArray6704[0];
	    class302_sub1.anInt7642 = ja.anIntArray6704[1];
	    class302_sub1.anInt7643 = ja.anIntArray6704[2];
	    class302_sub1.anInt7640 = ja.anIntArray6704[3];
	    class302_sub1.anInt7645 = ja.anIntArray6704[4];
	    class302_sub1.aBoolean7644 = ja.anIntArray6704[5] != 0;
	}
    }

    @Override
    void method4758() {
	if (this.aJa6674.anInt6692 > 1) {
	    synchronized (this) {
		aBoolean4148 = false;
		this.notifyAll();
	    }
	}
    }

    @Override
    native boolean bt();

    @Override
    native boolean bj();

    @Override
    native void br();

    @Override
    native void bz();

    @Override
    native void cm();

    @Override
    native void cd();

    @Override
    final void method4760(int i, int[] is, int i_119_, int i_120_, int i_121_, int i_122_, boolean bool) {
	J(this.nativeid, i, is, i_119_, i_120_, i_121_, i_122_, bool);
    }

    native void fe(ja var_ja);

    @Override
    void method4756() {
	if (this.aJa6674.anInt6692 > 1) {
	    synchronized (this) {
		while (aBoolean4148) {
		    try {
			this.wait();
		    }
		    catch (InterruptedException interruptedexception) {
			/* empty */
		    }
		}
		aBoolean4148 = true;
	    }
	}
    }

    native void gc(float[] fs, int i, boolean bool);

    @Override
    native void co(int i, int[] is, int i_123_, int i_124_, int i_125_, boolean bool, int i_126_, int[] is_127_);

    native void J(long l, int i, int[] is, int i_128_, int i_129_, int i_130_, int i_131_, boolean bool);

    @Override
    native void cv(int i, int i_132_, int i_133_, int i_134_);

    @Override
    public void method4776(Class222 class222, int i, boolean bool) {
	Class233 class233 = this.aJa6674.method5571().aClass233_6673;
	class233.method2145(class222);
	aa(class233.aFloatArray2594, i, bool);
    }

    @Override
    public void method4762(Class222 class222, int i, boolean bool) {
	Class233 class233 = this.aJa6674.method5571().aClass233_6673;
	class233.method2145(class222);
	aa(class233.aFloatArray2594, i, bool);
    }

    @Override
    public void method4759(Class222 class222, Class302_Sub1 class302_sub1, int i) {
	if (class302_sub1 == null)
	    this.aJa6674.method5571().method279(this, class222, null, i);
	else {
	    ja.anIntArray6704[5] = 0;
	    this.aJa6674.method5571().method279(this, class222, ja.anIntArray6704, i);
	    class302_sub1.anInt7641 = ja.anIntArray6704[0];
	    class302_sub1.anInt7642 = ja.anIntArray6704[1];
	    class302_sub1.anInt7643 = ja.anIntArray6704[2];
	    class302_sub1.anInt7640 = ja.anIntArray6704[3];
	    class302_sub1.anInt7645 = ja.anIntArray6704[4];
	    class302_sub1.aBoolean7644 = ja.anIntArray6704[5] != 0;
	}
    }

    native void gn(byte i, byte[] is);

    h(ja var_ja, ba var_ba, Model class64, int i, int i_135_, int i_136_, int i_137_) {
	this.aJa6674 = var_ja;
	this.aBa6675 = var_ba;
	this.aClass85Array6677 = class64.aClass85Array647;
	this.aClass68Array6676 = class64.aClass68Array613;
	int i_138_ = (class64.aClass85Array647 == null ? 0 : class64.aClass85Array647.length);
	int i_139_ = (class64.aClass68Array613 == null ? 0 : class64.aClass68Array613.length);
	int i_140_ = 0;
	int[] is = new int[i_138_ * 3 + i_139_];
	for (int i_141_ = 0; i_141_ < i_138_; i_141_++) {
	    is[i_140_++] = this.aClass85Array6677[i_141_].anInt773 * -710317103;
	    is[i_140_++] = this.aClass85Array6677[i_141_].anInt774 * 1705862021;
	    is[i_140_++] = this.aClass85Array6677[i_141_].anInt775 * 1636170731;
	}
	for (int i_142_ = 0; i_142_ < i_139_; i_142_++)
	    is[i_140_++] = this.aClass68Array6676[i_142_].anInt674 * -180596249;
	int i_143_ = (class64.aClass84Array649 == null ? 0 : class64.aClass84Array649.length);
	int[] is_144_ = new int[i_143_ * 8];
	int i_145_ = 0;
	for (int i_146_ = 0; i_146_ < i_143_; i_146_++) {
	    Class84 class84 = class64.aClass84Array649[i_146_];
	    Class173 class173 = Class298.method2844(class84.anInt768 * 1834782277, -67897652);
	    is_144_[i_145_++] = class84.anInt767 * 1512514121;
	    is_144_[i_145_++] = class173.anInt1755 * 1951943953;
	    is_144_[i_145_++] = class173.anInt1753 * 893949695;
	    is_144_[i_145_++] = class173.anInt1751 * 39181267;
	    is_144_[i_145_++] = class173.anInt1752 * -310074719;
	    is_144_[i_145_++] = class173.anInt1754 * 1092922159;
	    is_144_[i_145_++] = class173.aBoolean1757 ? -1 : 0;
	}
	for (int i_147_ = 0; i_147_ < i_143_; i_147_++) {
	    Class84 class84 = class64.aClass84Array649[i_147_];
	    is_144_[i_145_++] = class84.anInt766 * -1606786303;
	}
	U(this.aJa6674, this.aBa6675, class64.anInt614, class64.anInt626, class64.anIntArray616, class64.anIntArray642, class64.anIntArray618, class64.anIntArray619, class64.aShortArray620, class64.anInt621, class64.aShortArray644, class64.aShortArray646, class64.aShortArray624, class64.aByteArray625, class64.aByteArray633, class64.aByteArray627, class64.aByteArray635, class64.aShortArray629, class64.aShortArray617, class64.anIntArray631, class64.aByte632, class64.aShortArray615, class64.anInt634, class64.aByteArray622, class64.aShortArray623, class64.aShortArray636, class64.aShortArray638, class64.anIntArray639, class64.anIntArray640, class64.anIntArray641, class64.aByteArray645, class64.aByteArray628, class64.anIntArray637, class64.anIntArray643, class64.anIntArray648, is, i_138_, i_139_, i, i_135_, i_136_, i_137_, is_144_);
    }

    @Override
    public boolean method4746(int i, int i_148_, Class222 class222, boolean bool, int i_149_) {
	return this.aJa6674.method5571().method284(this, i, i_148_, class222, bool);
    }

    @Override
    public boolean method4778() {
	return true;
    }

    @Override
    public native void KA(int i);

    @Override
    public native int ca();

    @Override
    public native int ci();

    @Override
    public native int ce();

    @Override
    public native int cq();

    @Override
    public native int cr();

    @Override
    native void w(int i, int i_150_, int i_151_, int i_152_);

    @Override
    public native int cp();

    @Override
    public native void bb(int i);

    @Override
    public native int cf();

    @Override
    public native int dh();

    @Override
    final void method4761(int i, int[] is, int i_153_, int i_154_, int i_155_, int i_156_, boolean bool) {
	J(this.nativeid, i, is, i_153_, i_154_, i_155_, i_156_, bool);
    }

    native void fk(ja var_ja);

    @Override
    public Class85[] method4771() {
	return this.aClass85Array6677;
    }

    @Override
    public native int ds();

    @Override
    public native Class_na cc(Class_na class_na);

    @Override
    public native void dd(int i);

    @Override
    public native void dx(int i);

    native void fx(long l, int i, int[] is, int i_157_, int i_158_, int i_159_, int i_160_, boolean bool);

    @Override
    public native int dk();

    @Override
    public native int db();

    @Override
    public native int dn();

    @Override
    public native void oa(int i, int i_161_, int i_162_);

    @Override
    public void method4768(byte i, byte[] is) {
	IA(i, is);
    }

    @Override
    public void method4769(byte i, byte[] is) {
	IA(i, is);
    }

    @Override
    public native void du(short i, short i_163_);

    @Override
    public boolean method4788() {
	return true;
    }

    @Override
    public native void di(short i, short i_164_);

    @Override
    public void method4742(byte i, byte[] is) {
	IA(i, is);
    }

    @Override
    public Class68[] method4753() {
	return this.aClass68Array6676;
    }

    @Override
    public native void dt(int i, int i_165_, int i_166_, int i_167_);

    @Override
    public native Class_na ga(Class_na class_na);

    @Override
    void method4733() {
	if (this.aJa6674.anInt6692 > 1) {
	    synchronized (this) {
		while (aBoolean4148) {
		    try {
			this.wait();
		    }
		    catch (InterruptedException interruptedexception) {
			/* empty */
		    }
		}
		aBoolean4148 = true;
	    }
	}
    }

    @Override
    public native void dv(short i, short i_168_);

    @Override
    public native int AA();

    @Override
    public Class68[] method4773() {
	return this.aClass68Array6676;
    }

    @Override
    public native byte[] method_do();

    @Override
    public native void dj(int i, int i_169_, int i_170_, int i_171_);

    @Override
    public native int dl();

    @Override
    public native void bu(int i, int i_172_, int i_173_);

    @Override
    public boolean method4777() {
	return true;
    }

    @Override
    public Class68[] method4775() {
	return this.aClass68Array6676;
    }

    @Override
    public native boolean ev();

    @Override
    public native boolean eg();

    @Override
    public native boolean ex();

    @Override
    public native boolean ek();

    @Override
    public native int cb();

    @Override
    public native void em();

    @Override
    public native int ec();

    @Override
    public native void eb(short i, short i_174_);

    @Override
    public native int dq();

    @Override
    public void method4751(Class222 class222) {
	method4852(ja.anIntArray6681, class222);
	int i = 0;
	if (this.aClass85Array6677 != null) {
	    for (int i_175_ = 0; i_175_ < this.aClass85Array6677.length; i_175_++) {
		Class85 class85 = this.aClass85Array6677[i_175_];
		class85.anInt777 = ja.anIntArray6681[i++] * -1879868075;
		class85.anInt783 = ja.anIntArray6681[i++] * -2041556771;
		class85.anInt779 = ja.anIntArray6681[i++] * -1434499227;
		class85.anInt772 = ja.anIntArray6681[i++] * 1070341177;
		class85.anInt781 = ja.anIntArray6681[i++] * 1802851857;
		class85.anInt782 = ja.anIntArray6681[i++] * 103846281;
		class85.anInt771 = ja.anIntArray6681[i++] * -2103324039;
		class85.anInt784 = ja.anIntArray6681[i++] * -526039059;
		class85.anInt785 = ja.anIntArray6681[i++] * 491030489;
	    }
	}
	if (this.aClass68Array6676 != null) {
	    for (int i_176_ = 0; i_176_ < this.aClass68Array6676.length; i_176_++) {
		Class68 class68 = this.aClass68Array6676[i_176_];
		Class68 class68_177_ = class68;
		if (class68.aClass68_672 != null)
		    class68_177_ = class68.aClass68_672;
		if (class68.aClass233_677 == null)
		    class68.aClass233_677 = new Class233();
		class68.aClass233_677.method2145(class222);
		class68_177_.anInt671 = ja.anIntArray6681[i++] * -1436341053;
		class68_177_.anInt675 = ja.anIntArray6681[i++] * 449866009;
		class68_177_.anInt676 = ja.anIntArray6681[i++] * 1336328763;
	    }
	}
    }

    @Override
    public void method4782(Class222 class222) {
	method4852(ja.anIntArray6681, class222);
	int i = 0;
	if (this.aClass85Array6677 != null) {
	    for (int i_178_ = 0; i_178_ < this.aClass85Array6677.length; i_178_++) {
		Class85 class85 = this.aClass85Array6677[i_178_];
		class85.anInt777 = ja.anIntArray6681[i++] * -1879868075;
		class85.anInt783 = ja.anIntArray6681[i++] * -2041556771;
		class85.anInt779 = ja.anIntArray6681[i++] * -1434499227;
		class85.anInt772 = ja.anIntArray6681[i++] * 1070341177;
		class85.anInt781 = ja.anIntArray6681[i++] * 1802851857;
		class85.anInt782 = ja.anIntArray6681[i++] * 103846281;
		class85.anInt771 = ja.anIntArray6681[i++] * -2103324039;
		class85.anInt784 = ja.anIntArray6681[i++] * -526039059;
		class85.anInt785 = ja.anIntArray6681[i++] * 491030489;
	    }
	}
	if (this.aClass68Array6676 != null) {
	    for (int i_179_ = 0; i_179_ < this.aClass68Array6676.length; i_179_++) {
		Class68 class68 = this.aClass68Array6676[i_179_];
		Class68 class68_180_ = class68;
		if (class68.aClass68_672 != null)
		    class68_180_ = class68.aClass68_672;
		if (class68.aClass233_677 == null)
		    class68.aClass233_677 = new Class233();
		class68.aClass233_677.method2145(class222);
		class68_180_.anInt671 = ja.anIntArray6681[i++] * -1436341053;
		class68_180_.anInt675 = ja.anIntArray6681[i++] * 449866009;
		class68_180_.anInt676 = ja.anIntArray6681[i++] * 1336328763;
	    }
	}
    }

    @Override
    public void method4783(Class222 class222) {
	method4852(ja.anIntArray6681, class222);
	int i = 0;
	if (this.aClass85Array6677 != null) {
	    for (int i_181_ = 0; i_181_ < this.aClass85Array6677.length; i_181_++) {
		Class85 class85 = this.aClass85Array6677[i_181_];
		class85.anInt777 = ja.anIntArray6681[i++] * -1879868075;
		class85.anInt783 = ja.anIntArray6681[i++] * -2041556771;
		class85.anInt779 = ja.anIntArray6681[i++] * -1434499227;
		class85.anInt772 = ja.anIntArray6681[i++] * 1070341177;
		class85.anInt781 = ja.anIntArray6681[i++] * 1802851857;
		class85.anInt782 = ja.anIntArray6681[i++] * 103846281;
		class85.anInt771 = ja.anIntArray6681[i++] * -2103324039;
		class85.anInt784 = ja.anIntArray6681[i++] * -526039059;
		class85.anInt785 = ja.anIntArray6681[i++] * 491030489;
	    }
	}
	if (this.aClass68Array6676 != null) {
	    for (int i_182_ = 0; i_182_ < this.aClass68Array6676.length; i_182_++) {
		Class68 class68 = this.aClass68Array6676[i_182_];
		Class68 class68_183_ = class68;
		if (class68.aClass68_672 != null)
		    class68_183_ = class68.aClass68_672;
		if (class68.aClass233_677 == null)
		    class68.aClass233_677 = new Class233();
		class68.aClass233_677.method2145(class222);
		class68_183_.anInt671 = ja.anIntArray6681[i++] * -1436341053;
		class68_183_.anInt675 = ja.anIntArray6681[i++] * 449866009;
		class68_183_.anInt676 = ja.anIntArray6681[i++] * 1336328763;
	    }
	}
    }

    @Override
    native void cj(int i, int[] is, int i_184_, int i_185_, int i_186_, boolean bool, int i_187_, int[] is_188_);

    native void fc(ja var_ja, ba var_ba, int i, int i_189_, int[] is, int[] is_190_, int[] is_191_, int[] is_192_, short[] is_193_, int i_194_, short[] is_195_, short[] is_196_, short[] is_197_, byte[] is_198_, byte[] is_199_, byte[] is_200_, byte[] is_201_, short[] is_202_, short[] is_203_, int[] is_204_, byte i_205_, short[] is_206_, int i_207_, byte[] is_208_, short[] is_209_, short[] is_210_, short[] is_211_, int[] is_212_, int[] is_213_, int[] is_214_, byte[] is_215_, byte[] is_216_, int[] is_217_, int[] is_218_, int[] is_219_, int[] is_220_, int i_221_, int i_222_, int i_223_, int i_224_, int i_225_, int i_226_, int[] is_227_);

    native void fw(ja var_ja);

    @Override
    public native void z(boolean bool);

    @Override
    public native boolean u();

    @Override
    public native void dr(int i, int i_228_, int i_229_, int i_230_);

    @Override
    public native int Z();

    @Override
    public boolean method4763(int i, int i_231_, Class222 class222, boolean bool, int i_232_) {
	return this.aJa6674.method5571().method284(this, i, i_231_, class222, bool);
    }

    native void gt(float[] fs, int i, boolean bool);

    @Override
    public native int dg();

    native void gl(float[] fs, int i, boolean bool);

    native void gq(float[] fs, int i, boolean bool);

    native void gp(byte i, byte[] is);

    @Override
    public Class387 method4750(byte i, int i_233_, boolean bool) {
	return this.aJa6674.method5571().method276(this, i, i_233_, bool);
    }

    native void ge(byte i, byte[] is);
}
