/* Class279 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class279 {
    int anInt2970;
    int[] anIntArray2971;
    int[] anIntArray2972;
    int anInt2973;
    int anInt2974;
    int anInt2975;
    int anInt2976;
    int anInt2977;
    int anInt2978;
    int anInt2979 = 2;
    int anInt2980;

    final int method2605(int i) {
	if (this.anInt2980 >= this.anInt2976) {
	    this.anInt2970 = (this.anIntArray2972[this.anInt2977++]) << 15;
	    if (this.anInt2977 >= this.anInt2979)
		this.anInt2977 = this.anInt2979 - 1;
	    this.anInt2976 = (int) ((this.anIntArray2971[this.anInt2977]) / 65536.0 * i);
	    if (this.anInt2976 > this.anInt2980)
		this.anInt2978 = ((((this.anIntArray2972[this.anInt2977]) << 15) - this.anInt2970) / (this.anInt2976 - this.anInt2980));
	}
	this.anInt2970 += this.anInt2978;
	this.anInt2980++;
	return this.anInt2970 - this.anInt2978 >> 15;
    }

    final void method2606(RsByteBuffer class298_sub53) {
	this.anInt2975 = class298_sub53.readUnsignedByte();
	this.anInt2973 = class298_sub53.readInt((byte) 10);
	this.anInt2974 = class298_sub53.readInt((byte) -71);
	method2607(class298_sub53);
    }

    final void method2607(RsByteBuffer class298_sub53) {
	this.anInt2979 = class298_sub53.readUnsignedByte();
	this.anIntArray2971 = new int[this.anInt2979];
	this.anIntArray2972 = new int[this.anInt2979];
	for (int i = 0; i < this.anInt2979; i++) {
	    this.anIntArray2971[i] = class298_sub53.readUnsignedShort();
	    this.anIntArray2972[i] = class298_sub53.readUnsignedShort();
	}
    }

    final void method2608() {
	this.anInt2976 = 0;
	this.anInt2977 = 0;
	this.anInt2978 = 0;
	this.anInt2970 = 0;
	this.anInt2980 = 0;
    }

    Class279() {
	this.anIntArray2971 = new int[2];
	this.anIntArray2972 = new int[2];
	this.anIntArray2971[0] = 0;
	this.anIntArray2971[1] = 65535;
	this.anIntArray2972[0] = 0;
	this.anIntArray2972[1] = 65535;
    }
}
