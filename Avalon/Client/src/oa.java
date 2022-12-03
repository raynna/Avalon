/* oa - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class oa implements Interface8_Impl1_Impl2, Interface25 {
    long nativeid = 0L;
    int anInt8979;
    int anInt8980;

    @Override
    public int k() {
	return this.anInt8980;
    }

    native void g(int i, int i_0_);

    @Override
    public int a() {
	return this.anInt8979;
    }

    @Override
    public int f() {
	return this.anInt8980;
    }

    native void s(long l, boolean bool);

    native void n(int i, int i_1_);

    @Override
    public void b() {
	ma(false);
    }

    @Override
    public int p() {
	return this.anInt8979;
    }

    @Override
    public int i() {
	return this.anInt8979;
    }

    @Override
    public void u() {
	ma(false);
    }

    native void za(long l, boolean bool);

    @Override
    public void ma(boolean bool) {
	za(this.nativeid, bool);
    }

    @Override
    public void x() {
	ma(false);
    }

    @Override
    public void d() {
	ma(false);
    }

    native void r(int i, int i_2_);

    native void q(int i, int i_3_);

    @Override
    public void z(boolean bool) {
	za(this.nativeid, bool);
    }

    oa(int i, int i_4_) {
	this.anInt8979 = i;
	this.anInt8980 = i_4_;
	g(i, i_4_);
    }

    native void y(long l, boolean bool);
}
