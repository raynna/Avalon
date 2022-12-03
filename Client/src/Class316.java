
/* Class316 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public class Class316 implements Runnable {
    int anInt3311;
    InputStream anInputStream3312;
    IOException anIOException3313;
    byte[] aByteArray3314;
    int anInt3315;
    Thread aThread3316;
    int anInt3317 = 0;
    public static Class362 aClass362_3318;

    void method3839(int i) {
	try {
	    this.anInputStream3312 = new InputStream_Sub1();
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("ne.i(").append(')').toString());
	}
    }

    @Override
    public void run() {
	try {
	    for (;;) {
		int i;
		synchronized (this) {
		    for (;;) {
			if (null != this.anIOException3313)
			    return;
			if (this.anInt3317 * -352230015 == 0)
			    i = (1913311441 * this.anInt3311 - -884053309 * this.anInt3315 - 1);
			else if (-352230015 * this.anInt3317 <= this.anInt3315 * -884053309)
			    i = (this.anInt3311 * 1913311441 - this.anInt3315 * -884053309);
			else
			    i = (this.anInt3317 * -352230015 - -884053309 * this.anInt3315 - 1);
			if (i > 0)
			    break;
			try {
			    this.wait();
			}
			catch (InterruptedException interruptedexception) {
			    /* empty */
			}
		    }
		}
		int i_0_;
		try {
		    i_0_ = (this.anInputStream3312.read(this.aByteArray3314, -884053309 * this.anInt3315, i));
		    if (-1 == i_0_)
			throw new EOFException();
		}
		catch (IOException ioexception) {
		    synchronized (this) {
			this.anIOException3313 = ioexception;
			break;
		    }
		}
		synchronized (this) {
		    this.anInt3315 = (-157936149 * ((-884053309 * this.anInt3315 + i_0_) % (1913311441 * this.anInt3311)));
		}
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("ne.run(").append(')').toString());
	}
    }

    boolean method3840(int i, int i_1_) throws IOException {
	try {
	    if (i <= 0 || i >= this.anInt3311 * 1913311441)
		throw new IOException();
	    synchronized (this) {
		int i_2_;
		if (this.anInt3317 * -352230015 <= -884053309 * this.anInt3315)
		    i_2_ = (-884053309 * this.anInt3315 - -352230015 * this.anInt3317);
		else
		    i_2_ = (this.anInt3311 * 1913311441 - -352230015 * this.anInt3317 + this.anInt3315 * -884053309);
		if (i_2_ < i) {
		    if (null != this.anIOException3313)
			throw new IOException(this.anIOException3313.toString());
		    this.notifyAll();
		    boolean bool = false;
		    return bool;
		}
		boolean bool = true;
		return bool;
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("ne.a(").append(')').toString());
	}
    }

    int method3841(byte i) throws IOException {
	try {
	    synchronized (this) {
		int i_3_;
		if (this.anInt3317 * -352230015 <= -884053309 * this.anInt3315)
		    i_3_ = (-884053309 * this.anInt3315 - -352230015 * this.anInt3317);
		else
		    i_3_ = (this.anInt3315 * -884053309 + (1913311441 * this.anInt3311 - this.anInt3317 * -352230015));
		if (null != this.anIOException3313)
		    throw new IOException(this.anIOException3313.toString());
		this.notifyAll();
		int i_4_ = i_3_;
		return i_4_;
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("ne.f(").append(')').toString());
	}
    }

    void method3842(int i) {
	try {
	    synchronized (this) {
		if (null == this.anIOException3313)
		    this.anIOException3313 = new IOException("");
		this.notifyAll();
	    }
	    try {
		this.aThread3316.join();
	    }
	    catch (InterruptedException interruptedexception) {
		/* empty */
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("ne.p(").append(')').toString());
	}
    }

    int method3843(byte[] is, int i, int i_5_, byte i_6_) throws IOException {
	try {
	    if (i_5_ < 0 || i < 0 || i + i_5_ > is.length)
		throw new IOException();
	    synchronized (this) {
		int i_7_;
		if (-352230015 * this.anInt3317 <= this.anInt3315 * -884053309)
		    i_7_ = (-884053309 * this.anInt3315 - -352230015 * this.anInt3317);
		else
		    i_7_ = (this.anInt3311 * 1913311441 - this.anInt3317 * -352230015 + -884053309 * this.anInt3315);
		if (i_5_ > i_7_)
		    i_5_ = i_7_;
		if (i_5_ == 0 && null != this.anIOException3313)
		    throw new IOException(this.anIOException3313.toString());
		if (this.anInt3317 * -352230015 + i_5_ <= this.anInt3311 * 1913311441)
		    System.arraycopy(this.aByteArray3314, -352230015 * this.anInt3317, is, i, i_5_);
		else {
		    int i_8_ = (1913311441 * this.anInt3311 - -352230015 * this.anInt3317);
		    System.arraycopy(this.aByteArray3314, -352230015 * this.anInt3317, is, i, i_8_);
		    System.arraycopy(this.aByteArray3314, 0, is, i + i_8_, i_5_ - i_8_);
		}
		this.anInt3317 = (-1445143935 * ((-352230015 * this.anInt3317 + i_5_) % (this.anInt3311 * 1913311441)));
		this.notifyAll();
		int i_9_ = i_5_;
		return i_9_;
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("ne.b(").append(')').toString());
	}
    }

    Class316(InputStream inputstream, int i) {
	this.anInt3315 = 0;
	this.anInputStream3312 = inputstream;
	this.anInt3311 = (i + 1) * 1422886961;
	this.aByteArray3314 = new byte[this.anInt3311 * 1913311441];
	this.aThread3316 = new Thread(this);
	this.aThread3316.setDaemon(true);
	this.aThread3316.start();
    }

    static final void method3844(int i, HashTable class437, int i_10_) {
	try {
	    if (-1 != i && class437.method5812(i) == null)
		class437.method5817(new Class298(), i);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("ne.d(").append(')').toString());
	}
    }
}
