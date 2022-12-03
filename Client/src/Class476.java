
/* Class476 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public final class Class476 {
    long aLong5983;
    RandomAccessFile aRandomAccessFile5984;
    long aLong5985;
    static Class453 aClass453_5986;

    final void method6077(long l) throws IOException {
	try {
	    this.aRandomAccessFile5984.seek(l);
	    this.aLong5985 = 5892097441890682535L * l;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("tp.a(").append(')').toString());
	}
    }

    public final void method6078(byte[] is, int i, int i_0_, int i_1_) throws IOException {
	try {
	    if ((-1855833292870549225L * this.aLong5985 + i_0_) > this.aLong5983 * -1095537870969537929L) {
		this.aRandomAccessFile5984.seek(-1095537870969537929L * this.aLong5983);
		this.aRandomAccessFile5984.write(1);
		throw new EOFException();
	    }
	    this.aRandomAccessFile5984.write(is, i, i_0_);
	    this.aLong5985 += 5892097441890682535L * i_0_;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("tp.f(").append(')').toString());
	}
    }

    public final void method6079(int i) throws IOException {
	try {
	    if (null != this.aRandomAccessFile5984) {
		this.aRandomAccessFile5984.close();
		this.aRandomAccessFile5984 = null;
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("tp.b(").append(')').toString());
	}
    }

    public final long method6080(int i) throws IOException {
	try {
	    return this.aRandomAccessFile5984.length();
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("tp.p(").append(')').toString());
	}
    }

    public final int method6081(byte[] is, int i, int i_2_, short i_3_) throws IOException {
	try {
	    int i_4_ = this.aRandomAccessFile5984.read(is, i, i_2_);
	    if (i_4_ > 0)
		this.aLong5985 += i_4_ * 5892097441890682535L;
	    return i_4_;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("tp.i(").append(')').toString());
	}
    }

    @Override
    protected void finalize() throws Throwable {
	try {
	    if (null != this.aRandomAccessFile5984) {
		System.out.println("");
		method6079(601212046);
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("tp.finalize(").append(')').toString());
	}
    }

    public Class476(File file, String string, long l) throws IOException {
	if (l == -1L)
	    l = 9223372036854775807L;
	if (file.length() > l) {
	    System.out.println("Error!" + file.getName() + " is corrupted. Deleting.");
	    file.delete();
	}
	this.aRandomAccessFile5984 = new RandomAccessFile(file, string);
	this.aLong5983 = l * 1728399846774964039L;
	this.aLong5985 = 0L;
	int i = this.aRandomAccessFile5984.read();
	if (-1 != i && !string.equals("r")) {
	    this.aRandomAccessFile5984.seek(0L);
	    this.aRandomAccessFile5984.write(i);
	}
	this.aRandomAccessFile5984.seek(0L);
    }

    static final void method6082(Class403 class403, byte i) {
	try {
	    class403.anIntArray5244[((class403.anInt5239 += -391880689) * 681479919 - 1)] = 707299179 * Class360.anInt3892;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("tp.ahm(").append(')').toString());
	}
    }

    static final void method6083(int i, int i_5_, int i_6_, int i_7_) {
	try {
	    String string = new StringBuilder().append("tele ").append(i).append(Class26.aString353).append(i_5_ >> 6).append(Class26.aString353).append(i_6_ >> 6).append(Class26.aString353).append(i_5_ & 0x3f).append(Class26.aString353).append(i_6_ & 0x3f).toString();
	    Class419.method5605(string, true, false, 1405738364);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("tp.gd(").append(')').toString());
	}
    }
}
