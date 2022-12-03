
/* Class309_Sub2 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import jaclib.nanotime.QueryPerformanceCounter;

public class Class309_Sub2 extends Class309 {
    int anInt7682;
    long aLong7683;
    int anInt7684;
    long[] aLongArray7685;
    long aLong7686 = 0L;
    long aLong7687;

    @Override
    long method3790() {
	return this.aLong7686 * 7092803054136495365L;
    }

    @Override
    long method3794(int i) {
	try {
	    return this.aLong7686 * 7092803054136495365L;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aau.k(").append(')').toString());
	}
    }

    @Override
    long method3795() {
	this.aLong7686 += method3808(970605565) * 805490578470680525L;
	if (this.aLong7683 * 2528788777947664827L > this.aLong7686 * 7092803054136495365L)
	    return ((this.aLong7683 * 2528788777947664827L - this.aLong7686 * 7092803054136495365L) / 1000000L);
	return 0L;
    }

    @Override
    void method3793() {
	this.aLong7687 = 0L;
	if (2528788777947664827L * this.aLong7683 > this.aLong7686 * 7092803054136495365L)
	    this.aLong7686 += (this.aLong7683 * -1445719683822198849L - 1L * this.aLong7686);
    }

    @Override
    int method3791(long l) {
	try {
	    if (2528788777947664827L * this.aLong7683 > 7092803054136495365L * this.aLong7686) {
		this.aLong7687 += (-266790697308425433L * this.aLong7683 - (this.aLong7686 * -8298770624837138791L));
		this.aLong7686 += ((-1445719683822198849L * this.aLong7683) - 1L * this.aLong7686);
		this.aLong7683 += l * -8587934381355922573L;
		return 1;
	    }
	    int i = 0;
	    do {
		i++;
		this.aLong7683 += -8587934381355922573L * l;
	    }
	    while (i < 10 && ((this.aLong7683 * 2528788777947664827L) < (7092803054136495365L * this.aLong7686)));
	    if (this.aLong7683 * 2528788777947664827L < this.aLong7686 * 7092803054136495365L)
		this.aLong7683 = this.aLong7686 * -3947023160226410433L;
	    return i;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aau.i(").append(')').toString());
	}
    }

    @Override
    void method3788(int i) {
	try {
	    this.aLong7687 = 0L;
	    if (2528788777947664827L * this.aLong7683 > this.aLong7686 * 7092803054136495365L)
		this.aLong7686 += ((this.aLong7683 * -1445719683822198849L) - 1L * this.aLong7686);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aau.b(").append(')').toString());
	}
    }

    @Override
    void method3798() {
	this.aLong7687 = 0L;
	if (2528788777947664827L * this.aLong7683 > this.aLong7686 * 7092803054136495365L)
	    this.aLong7686 += (this.aLong7683 * -1445719683822198849L - 1L * this.aLong7686);
    }

    @Override
    void method3792() {
	this.aLong7687 = 0L;
	if (2528788777947664827L * this.aLong7683 > this.aLong7686 * 7092803054136495365L)
	    this.aLong7686 += (this.aLong7683 * -1445719683822198849L - 1L * this.aLong7686);
    }

    @Override
    long method3789(int i) {
	try {
	    this.aLong7686 += method3808(970605565) * 805490578470680525L;
	    if (this.aLong7683 * 2528788777947664827L > this.aLong7686 * 7092803054136495365L)
		return (this.aLong7683 * 2528788777947664827L - (this.aLong7686 * 7092803054136495365L)) / 1000000L;
	    return 0L;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aau.p(").append(')').toString());
	}
    }

    @Override
    long method3797() {
	this.aLong7686 += method3808(970605565) * 805490578470680525L;
	if (this.aLong7683 * 2528788777947664827L > this.aLong7686 * 7092803054136495365L)
	    return ((this.aLong7683 * 2528788777947664827L - this.aLong7686 * 7092803054136495365L) / 1000000L);
	return 0L;
    }

    long method3808(int i) {
	try {
	    long l = QueryPerformanceCounter.nanoTime();
	    long l_0_ = l - this.aLong7687 * -749596307049612979L;
	    this.aLong7687 = l * -809283569091942523L;
	    if (l_0_ > -5000000000L && l_0_ < 5000000000L) {
		this.aLongArray7685[this.anInt7684 * -683538483] = l_0_;
		this.anInt7684 = (1094860037 * ((1 + this.anInt7684 * -683538483) % 10));
		if (this.anInt7682 * 769935805 < 1)
		    this.anInt7682 += 149830037;
	    }
	    long l_1_ = 0L;
	    for (int i_2_ = 1; i_2_ <= this.anInt7682 * 769935805; i_2_++)
		l_1_ += (this.aLongArray7685[(10 + (this.anInt7684 * -683538483 - i_2_)) % 10]);
	    return (l_1_ / (this.anInt7682 * 769935805));
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aau.e(").append(')').toString());
	}
    }

    @Override
    long method3796() {
	this.aLong7686 += method3808(970605565) * 805490578470680525L;
	if (this.aLong7683 * 2528788777947664827L > this.aLong7686 * 7092803054136495365L)
	    return ((this.aLong7683 * 2528788777947664827L - this.aLong7686 * 7092803054136495365L) / 1000000L);
	return 0L;
    }

    @Override
    int method3799(long l) {
	if (2528788777947664827L * this.aLong7683 > 7092803054136495365L * this.aLong7686) {
	    this.aLong7687 += (-266790697308425433L * this.aLong7683 - (this.aLong7686 * -8298770624837138791L));
	    this.aLong7686 += (-1445719683822198849L * this.aLong7683 - 1L * this.aLong7686);
	    this.aLong7683 += l * -8587934381355922573L;
	    return 1;
	}
	int i = 0;
	do {
	    i++;
	    this.aLong7683 += -8587934381355922573L * l;
	}
	while (i < 10 && (this.aLong7683 * 2528788777947664827L < (7092803054136495365L * this.aLong7686)));
	if (this.aLong7683 * 2528788777947664827L < this.aLong7686 * 7092803054136495365L)
	    this.aLong7683 = this.aLong7686 * -3947023160226410433L;
	return i;
    }

    Class309_Sub2() {
	this.aLong7683 = 0L;
	this.aLong7687 = 0L;
	this.aLongArray7685 = new long[10];
	this.anInt7684 = 0;
	this.anInt7682 = 149830037;
	this.aLong7683 = ((this.aLong7686 = QueryPerformanceCounter.nanoTime() * 805490578470680525L) * -3947023160226410433L);
	if (this.aLong7686 * 7092803054136495365L == 0L)
	    throw new RuntimeException();
    }
}
