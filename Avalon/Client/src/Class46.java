
/* Class46 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import jaclib.memory.Buffer;

public class Class46 {
    Buffer aBuffer489;
    Class_ra_Sub2 aClass_ra_Sub2_490;

    void method499(byte[] is, int i) {
	if (this.aBuffer489 == null || this.aBuffer489.a() < i)
	    this.aBuffer489 = this.aClass_ra_Sub2_490.aNativeHeap8181.f(i, false);
	this.aBuffer489.b(is, 0, 0, i);
    }

    Class46(Class_ra_Sub2 class_ra_sub2, Buffer buffer) {
	this.aClass_ra_Sub2_490 = class_ra_sub2;
	this.aBuffer489 = buffer;
    }

    Class46(Class_ra_Sub2 class_ra_sub2, byte[] is, int i) {
	this.aClass_ra_Sub2_490 = class_ra_sub2;
	this.aBuffer489 = this.aClass_ra_Sub2_490.aNativeHeap8181.f(i, false);
	if (is != null)
	    this.aBuffer489.b(is, 0, 0, i);
    }
}
