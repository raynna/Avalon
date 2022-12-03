/* Class298_Sub37_Sub16_Sub1 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class298_Sub37_Sub16_Sub1 extends Class298_Sub37_Sub16 {
    byte aByte10003;
    RsByteBuffer aClass298_Sub53_10004;
    int anInt10005;

    @Override
    int method3472() {
	if (null == this.aClass298_Sub53_10004)
	    return 0;
	return (-149528164 * (this.aClass298_Sub53_10004.index) / ((this.aClass298_Sub53_10004.buffer).length - this.aByte10003));
    }

    @Override
    byte[] method3465(short i) {
	try {
	    if (this.aBoolean9670 || ((this.aClass298_Sub53_10004.index) * 385051775 < ((this.aClass298_Sub53_10004.buffer).length - this.aByte10003)))
		throw new RuntimeException();
	    return (this.aClass298_Sub53_10004.buffer);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("akb.a(").append(')').toString());
	}
    }

    @Override
    int method3468(int i) {
	try {
	    if (null == this.aClass298_Sub53_10004)
		return 0;
	    return (-149528164 * (this.aClass298_Sub53_10004.index) / ((this.aClass298_Sub53_10004.buffer).length - this.aByte10003));
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("akb.f(").append(')').toString());
	}
    }

    @Override
    byte[] method3467() {
	if (this.aBoolean9670 || ((this.aClass298_Sub53_10004.index) * 385051775 < ((this.aClass298_Sub53_10004.buffer).length - this.aByte10003)))
	    throw new RuntimeException();
	return (this.aClass298_Sub53_10004.buffer);
    }

    @Override
    byte[] method3466() {
	if (this.aBoolean9670 || ((this.aClass298_Sub53_10004.index) * 385051775 < ((this.aClass298_Sub53_10004.buffer).length - this.aByte10003)))
	    throw new RuntimeException();
	return (this.aClass298_Sub53_10004.buffer);
    }

    @Override
    byte[] method3469() {
	if (this.aBoolean9670 || ((this.aClass298_Sub53_10004.index) * 385051775 < ((this.aClass298_Sub53_10004.buffer).length - this.aByte10003)))
	    throw new RuntimeException();
	return (this.aClass298_Sub53_10004.buffer);
    }

    @Override
    int method3470() {
	if (null == this.aClass298_Sub53_10004)
	    return 0;
	return (-149528164 * (this.aClass298_Sub53_10004.index) / ((this.aClass298_Sub53_10004.buffer).length - this.aByte10003));
    }

    @Override
    int method3471() {
	if (null == this.aClass298_Sub53_10004)
	    return 0;
	return (-149528164 * (this.aClass298_Sub53_10004.index) / ((this.aClass298_Sub53_10004.buffer).length - this.aByte10003));
    }

    Class298_Sub37_Sub16_Sub1() {
	/* empty */
    }
}
