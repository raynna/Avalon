/* Class309_Sub1 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class309_Sub1 extends Class309 {
    long aLong7676;
    long aLong7677 = 0L;
    long[] aLongArray7678;
    int anInt7679;
    long aLong7680 = 0L;
    int anInt7681;

    @Override
    long method3790() {
	return this.aLong7677 * 4255909063329169833L;
    }

    Class309_Sub1() {
	this.aLong7676 = 0L;
	this.aLongArray7678 = new long[10];
	this.anInt7679 = 0;
	this.anInt7681 = -1505940925;
	this.aLong7677 = System.nanoTime() * -5945124749373967719L;
	this.aLong7680 = System.nanoTime() * 1084187842630379629L;
    }

    @Override
    int method3791(long l) {
	try {
	    if (this.aLong7680 * 3383889029778923877L > this.aLong7677 * 4255909063329169833L) {
		this.aLong7676 += ((this.aLong7680 * -1779823502204740161L) - (this.aLong7677 * 1974423410268347339L));
		this.aLong7677 += ((-8946266981622927523L * this.aLong7680) - this.aLong7677 * 1L);
		this.aLong7680 += 1084187842630379629L * l;
		return 1;
	    }
	    int i = 0;
	    do {
		i++;
		this.aLong7680 += 1084187842630379629L * l;
	    }
	    while (i < 10 && ((3383889029778923877L * this.aLong7680) < (4255909063329169833L * this.aLong7677)));
	    if (this.aLong7680 * 3383889029778923877L < this.aLong7677 * 4255909063329169833L)
		this.aLong7680 = this.aLong7677 * 923002373878029557L;
	    return i;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aaf.i(").append(')').toString());
	}
    }

    @Override
    long method3794(int i) {
	try {
	    return this.aLong7677 * 4255909063329169833L;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aaf.k(").append(')').toString());
	}
    }

    @Override
    long method3796() {
	this.aLong7677 += method3805(-1990505491) * -5945124749373967719L;
	if (this.aLong7680 * 3383889029778923877L > this.aLong7677 * 4255909063329169833L)
	    return ((3383889029778923877L * this.aLong7680 - 4255909063329169833L * this.aLong7677) / 1000000L);
	return 0L;
    }

    @Override
    void method3798() {
	this.aLong7676 = 0L;
	if (this.aLong7680 * 3383889029778923877L > 4255909063329169833L * this.aLong7677)
	    this.aLong7677 += (-8946266981622927523L * this.aLong7680 - 1L * this.aLong7677);
    }

    @Override
    void method3792() {
	this.aLong7676 = 0L;
	if (this.aLong7680 * 3383889029778923877L > 4255909063329169833L * this.aLong7677)
	    this.aLong7677 += (-8946266981622927523L * this.aLong7680 - 1L * this.aLong7677);
    }

    @Override
    void method3793() {
	this.aLong7676 = 0L;
	if (this.aLong7680 * 3383889029778923877L > 4255909063329169833L * this.aLong7677)
	    this.aLong7677 += (-8946266981622927523L * this.aLong7680 - 1L * this.aLong7677);
    }

    @Override
    long method3797() {
	this.aLong7677 += method3805(847126204) * -5945124749373967719L;
	if (this.aLong7680 * 3383889029778923877L > this.aLong7677 * 4255909063329169833L)
	    return ((3383889029778923877L * this.aLong7680 - 4255909063329169833L * this.aLong7677) / 1000000L);
	return 0L;
    }

    @Override
    int method3799(long l) {
	if (this.aLong7680 * 3383889029778923877L > this.aLong7677 * 4255909063329169833L) {
	    this.aLong7676 += (this.aLong7680 * -1779823502204740161L - this.aLong7677 * 1974423410268347339L);
	    this.aLong7677 += (-8946266981622927523L * this.aLong7680 - this.aLong7677 * 1L);
	    this.aLong7680 += 1084187842630379629L * l;
	    return 1;
	}
	int i = 0;
	do {
	    i++;
	    this.aLong7680 += 1084187842630379629L * l;
	}
	while (i < 10 && (3383889029778923877L * this.aLong7680 < (4255909063329169833L * this.aLong7677)));
	if (this.aLong7680 * 3383889029778923877L < this.aLong7677 * 4255909063329169833L)
	    this.aLong7680 = this.aLong7677 * 923002373878029557L;
	return i;
    }

    @Override
    long method3795() {
	this.aLong7677 += method3805(195670472) * -5945124749373967719L;
	if (this.aLong7680 * 3383889029778923877L > this.aLong7677 * 4255909063329169833L)
	    return ((3383889029778923877L * this.aLong7680 - 4255909063329169833L * this.aLong7677) / 1000000L);
	return 0L;
    }

    long method3805(int i) {
	try {
	    long l = System.nanoTime();
	    long l_0_ = l - -5561332595849817637L * this.aLong7676;
	    this.aLong7676 = -5853783290180697517L * l;
	    if (l_0_ > -5000000000L && l_0_ < 5000000000L) {
		this.aLongArray7678[578168601 * this.anInt7679] = l_0_;
		this.anInt7679 = (974800169 * ((1 + 578168601 * this.anInt7679) % 10));
		if (this.anInt7681 * -774573461 < 1)
		    this.anInt7681 += -1505940925;
	    }
	    long l_1_ = 0L;
	    for (int i_2_ = 1; i_2_ <= -774573461 * this.anInt7681; i_2_++)
		l_1_ += (this.aLongArray7678[(578168601 * this.anInt7679 - i_2_ + 10) % 10]);
	    return (l_1_ / (-774573461 * this.anInt7681));
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aaf.e(").append(')').toString());
	}
    }

    @Override
    void method3788(int i) {
	try {
	    this.aLong7676 = 0L;
	    if (this.aLong7680 * 3383889029778923877L > 4255909063329169833L * this.aLong7677)
		this.aLong7677 += ((-8946266981622927523L * this.aLong7680) - 1L * this.aLong7677);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aaf.b(").append(')').toString());
	}
    }

    @Override
    long method3789(int i) {
	try {
	    this.aLong7677 += method3805(-99997412) * -5945124749373967719L;
	    if (this.aLong7680 * 3383889029778923877L > this.aLong7677 * 4255909063329169833L)
		return (3383889029778923877L * this.aLong7680 - (4255909063329169833L * this.aLong7677)) / 1000000L;
	    return 0L;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aaf.p(").append(')').toString());
	}
    }

    static final void method3806(Class403 class403, int i) {
	try {
	    int i_3_ = (class403.anIntArray5244[((class403.anInt5239 -= -391880689) * 681479919)]);
	    IComponentDefinition class105 = Class50.getIComponentDefinitions(i_3_, (byte) -30);
	    Class119 class119 = Class389.aClass119Array4165[i_3_ >> 16];
	    Class12.method337(class105, class119, class403, (byte) 24);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aaf.dm(").append(')').toString());
	}
    }

    public static SubIncommingPacket[] method3807(byte i) {
	try {
	    return (new SubIncommingPacket[] { SubIncommingPacket.aClass206_2347, SubIncommingPacket.aClass206_2343, SubIncommingPacket.REMOVE_GROUND_ITEM_PACKET, SubIncommingPacket.DESTROY_OBJECT_PACKET, SubIncommingPacket.aClass206_2346, SubIncommingPacket.TILE_MESSAGE_PACKET, SubIncommingPacket.PROJECTILE_PACKET, SubIncommingPacket.aClass206_2356, SubIncommingPacket.SPAWN_OBJECT_PACKET, SubIncommingPacket.SEND_GROUND_ITEM_PACKET, SubIncommingPacket.aClass206_2357, SubIncommingPacket.aClass206_2353, SubIncommingPacket.aClass206_2354, SubIncommingPacket.aClass206_2342, SubIncommingPacket.aClass206_2348 });
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aaf.a(").append(')').toString());
	}
    }
}
