/* Class136_Sub1 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class136_Sub1 extends Class136 {
    int anInt8576 = 0;

    @Override
    public void method56(boolean bool) {
	int i = ((this.aClass158_6386.aClass139_6360.method1545(this.aClass57_6385.method271(), client.anInt8794 * 775068819, -2069269081)) + this.aClass158_6386.anInt6362 * -245579987);
	int i_0_ = ((this.aClass158_6386.aClass133_6363.method1482(this.aClass57_6385.method626(), client.anInt8803 * -791746413, -936935431)) + (this.aClass158_6386.anInt6359 * -1426302101));
	this.aClass57_6385.method673(i + this.aClass57_6385.method271() / 2, i_0_ + this.aClass57_6385.method626() / 2, 4096, this.anInt8576 * -157704951);
	this.anInt8576 += 315105141 * ((Class158_Sub1) this.aClass158_6386).anInt8567;
    }

    Class136_Sub1(Class243 class243, Class158_Sub1 class158_sub1) {
	super(class243, class158_sub1);
    }

    @Override
    public void method58(boolean bool, byte i) {
	try {
	    int i_1_ = ((this.aClass158_6386.aClass139_6360.method1545(this.aClass57_6385.method271(), client.anInt8794 * 775068819, -2041461531)) + (this.aClass158_6386.anInt6362 * -245579987));
	    int i_2_ = ((this.aClass158_6386.aClass133_6363.method1482(this.aClass57_6385.method626(), client.anInt8803 * -791746413, -2132177466)) + (this.aClass158_6386.anInt6359 * -1426302101));
	    this.aClass57_6385.method673(i_1_ + (this.aClass57_6385.method271() / 2), i_2_ + (this.aClass57_6385.method626() / 2), 4096, this.anInt8576 * -157704951);
	    this.anInt8576 += 315105141 * (((Class158_Sub1) this.aClass158_6386).anInt8567);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("zh.f(").append(')').toString());
	}
    }

    static final void method1502(int i, byte i_3_) {
	try {
	    client.anIntArray8793 = new int[i];
	    client.anIntArray8670 = new int[i];
	    client.anIntArray8795 = new int[i];
	    client.anIntArray8641 = new int[i];
	    client.anIntArray8797 = new int[i];
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("zh.iw(").append(')').toString());
	}
    }
}
