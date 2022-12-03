
/* Class298_Sub25 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.util.BitSet;

public class ClanChannel extends Class298 {
    boolean aBoolean7353;
    boolean aBoolean7354 = true;
    public byte aByte7355;
    public int anInt7356 = 0;
    int[] anIntArray7357;
    public String aString7358 = null;
    public Class163[] members;
    public byte aByte7360;
    long aLong7361;

    public ClanChannel(RsByteBuffer class298_sub53) {
	decode(class298_sub53, (byte) -94);
    }

    public int[] method3095(int i) {
	try {
	    if (this.anIntArray7357 == null) {
		String[] strings = new String[anInt7356 * 649879491];
		this.anIntArray7357 = new int[649879491 * anInt7356];
		for (int i_0_ = 0; i_0_ < anInt7356 * 649879491; i_0_++) {
		    strings[i_0_] = members[i_0_].aString1681;
		    this.anIntArray7357[i_0_] = i_0_;
		}
		Class298_Sub32_Sub32.method3345(strings, (this.anIntArray7357), 715814355);
	    }
	    return this.anIntArray7357;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("abj.f(").append(')').toString());
	}
    }

    void method3096(Class163 class163, int i) {
	try {
	    if (members == null || 649879491 * anInt7356 >= members.length)
		method3099(5 + 649879491 * anInt7356, (byte) 26);
	    members[(anInt7356 += 506595563) * 649879491 - 1] = class163;
	    this.anIntArray7357 = null;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("abj.b(").append(')').toString());
	}
    }

    public int method3097(String string, byte i) {
	try {
	    for (int i_1_ = 0; i_1_ < 649879491 * anInt7356; i_1_++) {
		if (members[i_1_].aString1681.equalsIgnoreCase(string))
		    return i_1_;
	    }
	    return -1;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("abj.i(").append(')').toString());
	}
    }

    void decode(RsByteBuffer stream, byte i) {
	try {
	    int i_2_ = stream.readUnsignedByte();
	    if ((i_2_ & 0x1) != 0)
		this.aBoolean7353 = true;
	    if ((i_2_ & 0x2) != 0)
		this.aBoolean7354 = true;
	    hash = (stream.readLong((short) 1568) * 4191220306876042991L);
	    this.aLong7361 = (stream.readLong((short) 25426) * 8816161044679006451L);
	    aString7358 = stream.readString(1262859658);
	    stream.readUnsignedByte();
	    aByte7355 = stream.readByte(-12558881);
	    aByte7360 = stream.readByte(-12558881);
	    anInt7356 = stream.readUnsignedShort() * 506595563;
	    if (anInt7356 * 649879491 > 0) {
		members = new Class163[649879491 * anInt7356];
		for (int i_3_ = 0; i_3_ < anInt7356 * 649879491; i_3_++) {
		    Class163 class163 = new Class163();
		    if (this.aBoolean7353)
			stream.readLong((short) 26032);
		    if (this.aBoolean7354)
			class163.aString1681 = stream.readString(254031265);
		    class163.aByte1683 = stream.readByte(-12558881);
		    class163.anInt1682 = stream.readUnsignedShort() * -62810701;
		    members[i_3_] = class163;
		}
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("abj.k(").append(')').toString());
	}
    }

    static {
	new BitSet(65536);
    }

    void method3099(int i, byte i_4_) {
	try {
	    if (null != members)
		Class425.arrayCopy(members, 0, members = new Class163[i], 0, anInt7356 * 649879491);
	    else
		members = new Class163[i];
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("abj.a(").append(')').toString());
	}
    }

    void method3100(int i, int i_5_) {
	try {
	    anInt7356 -= 506595563;
	    if (0 == anInt7356 * 649879491)
		members = null;
	    else
		Class425.arrayCopy(members, 1 + i, members, i, anInt7356 * 649879491 - i);
	    this.anIntArray7357 = null;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("abj.p(").append(')').toString());
	}
    }

    static final void method3101(Class403 class403, int i) {
	try {
	    Class107.method1138(class403, -807637826);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("abj.si(").append(')').toString());
	}
    }

    static final void method3102(Class403 class403, int i) {
	try {
	    MapDetails class298_sub37_sub13 = Class301.method3705(class403.anIntArray5244[(class403.anInt5239 -= -391880689) * 681479919]);
	    if (class298_sub37_sub13 == null || null == class298_sub37_sub13.name)
		class403.anObjectArray5240[((class403.anInt5241 += 969361751) * -203050393) - 1] = "";
	    else
		class403.anObjectArray5240[((class403.anInt5241 += 969361751) * -203050393) - 1] = class298_sub37_sub13.name;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("abj.adf(").append(')').toString());
	}
    }

    static final void method3103(Class403 class403, int i) {
	try {
	    int i_6_ = (class403.anIntArray5257[1883543357 * class403.anInt5259]);
	    Long var_long = (Long) Class313.anObjectArray3298[i_6_];
	    if (var_long == null)
		class403.aLongArray5251[((class403.anInt5245 += -682569305) * 1685767703) - 1] = -1L;
	    else
		class403.aLongArray5251[((class403.anInt5245 += -682569305) * 1685767703) - 1] = var_long.longValue();
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("abj.bs(").append(')').toString());
	}
    }
}
