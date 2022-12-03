
/* Class298_Sub16 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.nio.ByteBuffer;

import jaclib.memory.Stream;
import jaclib.memory.heap.NativeHeapBuffer;

public class Class298_Sub16 extends Class298 {
    int anInt7282;
    Class_xa_Sub1 aClass_xa_Sub1_7283;
    int anInt7284;
    Stream aStream7285;
    Class78 aClass78_7286;
    Interface7_Impl1 anInterface7_Impl1_7287;
    Class_ra_Sub3 aClass_ra_Sub3_7288;
    int[] anIntArray7289;
    int anInt7290 = 0;
    int anInt7291;
    int anInt7292;
    NativeHeapBuffer aNativeHeapBuffer7293;
    float aFloat7294;

    void method2910(int i) {
	this.aNativeHeapBuffer7293 = this.aClass_ra_Sub3_7288.method5517(i * 4, true);
	this.aStream7285 = new Stream(this.aNativeHeapBuffer7293, 0, i * 4);
    }

    void method2911(int i) {
	this.aStream7285.b(i * 4 + 3);
	this.aStream7285.p(-1);
    }

    void method2912(int i, int i_0_, int i_1_) {
	this.anIntArray7289[i_0_ * (this.aClass_xa_Sub1_7283.anInt6287 * -506105871) + i] |= 1 << i_1_;
	this.anInt7290++;
    }

    void method2913(int i, int i_2_, int i_3_, float f) {
	if (this.anInt7284 != -1) {
	    Class53 class53 = this.aClass_ra_Sub3_7288.anInterface_ma5299.method174(this.anInt7284, 833044999);
	    int i_4_ = class53.aByte533 & 0xff;
	    if (i_4_ != 0 && class53.aByte529 != 4) {
		int i_5_;
		if (i_3_ < 0)
		    i_5_ = 0;
		else if (i_3_ > 127)
		    i_5_ = 16777215;
		else
		    i_5_ = 131586 * i_3_;
		if (i_4_ == 256)
		    i_2_ = i_5_;
		else {
		    int i_6_ = i_4_;
		    int i_7_ = 256 - i_4_;
		    i_2_ = (((i_5_ & 0xff00ff) * i_6_ + (i_2_ & 0xff00ff) * i_7_ & ~0xff00ff) + ((i_5_ & 0xff00) * i_6_ + (i_2_ & 0xff00) * i_7_ & 0xff0000)) >> 8;
		}
	    }
	    int i_8_ = class53.aByte534 & 0xff;
	    if (i_8_ != 0) {
		i_8_ += 256;
		int i_9_ = ((i_2_ & 0xff0000) >> 16) * i_8_;
		if (i_9_ > 65535)
		    i_9_ = 65535;
		int i_10_ = ((i_2_ & 0xff00) >> 8) * i_8_;
		if (i_10_ > 65535)
		    i_10_ = 65535;
		int i_11_ = (i_2_ & 0xff) * i_8_;
		if (i_11_ > 65535)
		    i_11_ = 65535;
		i_2_ = (i_9_ << 8 & 0xff0000) + (i_10_ & 0xff00) + (i_11_ >> 8);
	    }
	}
	if (f != 1.0F) {
	    int i_12_ = i_2_ >> 16 & 0xff;
	    int i_13_ = i_2_ >> 8 & 0xff;
	    int i_14_ = i_2_ & 0xff;
	    i_12_ *= f;
	    if (i_12_ < 0)
		i_12_ = 0;
	    else if (i_12_ > 255)
		i_12_ = 255;
	    i_13_ *= f;
	    if (i_13_ < 0)
		i_13_ = 0;
	    else if (i_13_ > 255)
		i_13_ = 255;
	    i_14_ *= f;
	    if (i_14_ < 0)
		i_14_ = 0;
	    else if (i_14_ > 255)
		i_14_ = 255;
	    i_2_ = i_12_ << 16 | i_13_ << 8 | i_14_;
	}
	this.aStream7285.b(i * 4);
	if ((this.aClass_ra_Sub3_7288.anInt8346) == 0) {
	    this.aStream7285.p((byte) i_2_);
	    this.aStream7285.p((byte) (i_2_ >> 8));
	    this.aStream7285.p((byte) (i_2_ >> 16));
	} else {
	    this.aStream7285.p((byte) (i_2_ >> 16));
	    this.aStream7285.p((byte) (i_2_ >> 8));
	    this.aStream7285.p((byte) i_2_);
	}
    }

    void method2914(int i) {
	this.aStream7285.x();
	this.anInterface7_Impl1_7287 = this.aClass_ra_Sub3_7288.method5382(false);
	this.anInterface7_Impl1_7287.method71(i * 4, 4, this.aNativeHeapBuffer7293);
	this.aNativeHeapBuffer7293 = null;
	this.aStream7285 = null;
    }

    void method2915(int[] is, int i) {
	this.anInt7282 = 0;
	this.anInt7291 = 32767;
	this.anInt7292 = -32768;
	ByteBuffer bytebuffer = this.aClass_ra_Sub3_7288.aByteBuffer8216;
	for (int i_15_ = 0; i_15_ < i; i_15_++) {
	    int i_16_ = is[i_15_];
	    short[] is_17_ = (this.aClass_xa_Sub1_7283.aShortArrayArray8456[i_16_]);
	    int i_18_ = this.anIntArray7289[i_16_];
	    if (i_18_ != 0 && is_17_ != null) {
		int i_19_ = 0;
		int i_20_ = 0;
		while (i_20_ < is_17_.length) {
		    if ((i_18_ & 1 << i_19_++) != 0) {
			for (int i_21_ = 0; i_21_ < 3; i_21_++) {
			    int i_22_ = is_17_[i_20_++] & 0xffff;
			    if (i_22_ > this.anInt7292)
				this.anInt7292 = i_22_;
			    if (i_22_ < this.anInt7291)
				this.anInt7291 = i_22_;
			    bytebuffer.putShort((short) i_22_);
			}
			this.anInt7282 += 3;
		    } else
			i_20_ += 3;
		}
	    }
	}
    }

    Class298_Sub16(Class_xa_Sub1 class_xa_sub1, int i, int i_23_, Class78 class78) {
	this.anInt7282 = 0;
	this.anInt7291 = 0;
	this.anInt7292 = 0;
	this.aClass_xa_Sub1_7283 = class_xa_sub1;
	this.aClass_ra_Sub3_7288 = (this.aClass_xa_Sub1_7283.aClass_ra_Sub3_8467);
	this.anInt7284 = i;
	this.aFloat7294 = i_23_;
	this.aClass78_7286 = class78;
	this.anIntArray7289 = new int[(this.aClass_xa_Sub1_7283.anInt6287 * -506105871 * (this.aClass_xa_Sub1_7283.anInt6286 * -1148794921))];
	byte i_24_ = 10;
	if (i >= 0) {
	    Class53 class53 = this.aClass_ra_Sub3_7288.anInterface_ma5299.method174(i, 1520533155);
	    if (class53.aByte529 > 0)
		i_24_ = class53.aByte529;
	}
	this.aClass_ra_Sub3_7288.method5304(i_24_);
	this.aClass_ra_Sub3_7288.method5304(3);
    }
}
