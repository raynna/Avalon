
/* Class298_Sub24_Sub1 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import jagtheora.ogg.OggPacket;
import jagtheora.ogg.OggStreamState;
import jagtheora.vorbis.DSPState;
import jagtheora.vorbis.VorbisBlock;
import jagtheora.vorbis.VorbisComment;
import jagtheora.vorbis.VorbisInfo;

public class Class298_Sub24_Sub1 extends Class298_Sub24 {
    VorbisInfo aVorbisInfo9273 = new VorbisInfo();
    VorbisComment aVorbisComment9274 = new VorbisComment();
    VorbisBlock aVorbisBlock9275;
    static int anInt9276;
    Class270 aClass270_9277;
    Class298_Sub19_Sub3 aClass298_Sub19_Sub3_9278;
    double aDouble9279;
    int anInt9280;
    static int anInt9281;
    DSPState aDSPState9282;

    @Override
    void method3074(OggPacket oggpacket, int i) {
	try {
	    if (this.anInt7352 * -1312498565 < 3) {
		int i_0_ = (this.aVorbisInfo9273.headerIn(this.aVorbisComment9274, oggpacket));
		if (i_0_ < 0)
		    throw new IllegalStateException(new StringBuilder().append("").append(i_0_).toString());
		if (-1312498565 * this.anInt7352 == 2) {
		    if ((this.aVorbisInfo9273.channels > 2) || (this.aVorbisInfo9273.channels) < 1)
			throw new RuntimeException(new StringBuilder().append("").append(this.aVorbisInfo9273.channels).toString());
		    this.aDSPState9282 = new DSPState(this.aVorbisInfo9273);
		    this.aVorbisBlock9275 = new VorbisBlock(this.aDSPState9282);
		    this.aClass270_9277 = new Class270((this.aVorbisInfo9273.rate), 1164070869 * Class284.anInt3059);
		    this.aClass298_Sub19_Sub3_9278 = new Class298_Sub19_Sub3(this.aVorbisInfo9273.channels);
		}
	    } else {
		if (this.aVorbisBlock9275.synthesis(oggpacket) == 0)
		    this.aDSPState9282.blockIn(this.aVorbisBlock9275);
		float[][] fs = (this.aDSPState9282.pcmOut(this.aVorbisInfo9273.channels));
		this.aDouble9279 = this.aDSPState9282.granuleTime();
		if (-1.0 == this.aDouble9279)
		    this.aDouble9279 = (float) (253299067 * (this.anInt9280)) / (float) (this.aVorbisInfo9273.rate);
		this.aDSPState9282.read(fs[0].length);
		this.anInt9280 += fs[0].length * -87069261;
		Class298_Sub30 class298_sub30 = (this.aClass298_Sub19_Sub3_9278.method3032(fs[0].length, this.aDouble9279));
		Class482.method6120(fs, (class298_sub30.aShortArrayArray7368), (byte) -17);
		for (int i_1_ = 0; (i_1_ < this.aVorbisInfo9273.channels); i_1_++)
		    class298_sub30.aShortArrayArray7368[i_1_] = (this.aClass270_9277.method2533((class298_sub30.aShortArrayArray7368[i_1_]), (byte) 3));
		this.aClass298_Sub19_Sub3_9278.method3034(class298_sub30, -1634784761);
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aiy.f(").append(')').toString());
	}
    }

    @Override
    void method3079() {
	if (this.aVorbisBlock9275 != null)
	    this.aVorbisBlock9275.f();
	if (this.aDSPState9282 != null)
	    this.aDSPState9282.f();
	this.aVorbisComment9274.f();
	this.aVorbisInfo9273.f();
	if (null != this.aClass298_Sub19_Sub3_9278)
	    this.aClass298_Sub19_Sub3_9278.method3035(1484582250);
    }

    Class298_Sub24_Sub1(OggStreamState oggstreamstate) {
	super(oggstreamstate);
    }

    @Override
    void method3075(int i) {
	try {
	    if (this.aVorbisBlock9275 != null)
		this.aVorbisBlock9275.f();
	    if (this.aDSPState9282 != null)
		this.aDSPState9282.f();
	    this.aVorbisComment9274.f();
	    this.aVorbisInfo9273.f();
	    if (null != this.aClass298_Sub19_Sub3_9278)
		this.aClass298_Sub19_Sub3_9278.method3035(141709442);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aiy.b(").append(')').toString());
	}
    }

    @Override
    void method3072() {
	if (this.aVorbisBlock9275 != null)
	    this.aVorbisBlock9275.f();
	if (this.aDSPState9282 != null)
	    this.aDSPState9282.f();
	this.aVorbisComment9274.f();
	this.aVorbisInfo9273.f();
	if (null != this.aClass298_Sub19_Sub3_9278)
	    this.aClass298_Sub19_Sub3_9278.method3035(-1123680185);
    }

    @Override
    void method3076(OggPacket oggpacket) {
	if (this.anInt7352 * -1312498565 < 3) {
	    int i = (this.aVorbisInfo9273.headerIn(this.aVorbisComment9274, oggpacket));
	    if (i < 0)
		throw new IllegalStateException(new StringBuilder().append("").append(i).toString());
	    if (-1312498565 * this.anInt7352 == 2) {
		if (this.aVorbisInfo9273.channels > 2 || (this.aVorbisInfo9273.channels < 1))
		    throw new RuntimeException(new StringBuilder().append("").append(this.aVorbisInfo9273.channels).toString());
		this.aDSPState9282 = new DSPState(this.aVorbisInfo9273);
		this.aVorbisBlock9275 = new VorbisBlock(this.aDSPState9282);
		this.aClass270_9277 = new Class270((this.aVorbisInfo9273.rate), 1164070869 * Class284.anInt3059);
		this.aClass298_Sub19_Sub3_9278 = new Class298_Sub19_Sub3(this.aVorbisInfo9273.channels);
	    }
	} else {
	    if (this.aVorbisBlock9275.synthesis(oggpacket) == 0)
		this.aDSPState9282.blockIn(this.aVorbisBlock9275);
	    float[][] fs = (this.aDSPState9282.pcmOut(this.aVorbisInfo9273.channels));
	    this.aDouble9279 = this.aDSPState9282.granuleTime();
	    if (-1.0 == this.aDouble9279)
		this.aDouble9279 = (float) (253299067 * (this.anInt9280)) / (float) (this.aVorbisInfo9273.rate);
	    this.aDSPState9282.read(fs[0].length);
	    this.anInt9280 += fs[0].length * -87069261;
	    Class298_Sub30 class298_sub30 = (this.aClass298_Sub19_Sub3_9278.method3032(fs[0].length, this.aDouble9279));
	    Class482.method6120(fs, (class298_sub30.aShortArrayArray7368), (byte) -116);
	    for (int i = 0; i < this.aVorbisInfo9273.channels; i++)
		class298_sub30.aShortArrayArray7368[i] = (this.aClass270_9277.method2533((class298_sub30.aShortArrayArray7368[i]), (byte) 12));
	    this.aClass298_Sub19_Sub3_9278.method3034(class298_sub30, -585602714);
	}
    }

    @Override
    void method3077(OggPacket oggpacket) {
	if (this.anInt7352 * -1312498565 < 3) {
	    int i = (this.aVorbisInfo9273.headerIn(this.aVorbisComment9274, oggpacket));
	    if (i < 0)
		throw new IllegalStateException(new StringBuilder().append("").append(i).toString());
	    if (-1312498565 * this.anInt7352 == 2) {
		if (this.aVorbisInfo9273.channels > 2 || (this.aVorbisInfo9273.channels < 1))
		    throw new RuntimeException(new StringBuilder().append("").append(this.aVorbisInfo9273.channels).toString());
		this.aDSPState9282 = new DSPState(this.aVorbisInfo9273);
		this.aVorbisBlock9275 = new VorbisBlock(this.aDSPState9282);
		this.aClass270_9277 = new Class270((this.aVorbisInfo9273.rate), 1164070869 * Class284.anInt3059);
		this.aClass298_Sub19_Sub3_9278 = new Class298_Sub19_Sub3(this.aVorbisInfo9273.channels);
	    }
	} else {
	    if (this.aVorbisBlock9275.synthesis(oggpacket) == 0)
		this.aDSPState9282.blockIn(this.aVorbisBlock9275);
	    float[][] fs = (this.aDSPState9282.pcmOut(this.aVorbisInfo9273.channels));
	    this.aDouble9279 = this.aDSPState9282.granuleTime();
	    if (-1.0 == this.aDouble9279)
		this.aDouble9279 = (float) (253299067 * (this.anInt9280)) / (float) (this.aVorbisInfo9273.rate);
	    this.aDSPState9282.read(fs[0].length);
	    this.anInt9280 += fs[0].length * -87069261;
	    Class298_Sub30 class298_sub30 = (this.aClass298_Sub19_Sub3_9278.method3032(fs[0].length, this.aDouble9279));
	    Class482.method6120(fs, (class298_sub30.aShortArrayArray7368), (byte) -54);
	    for (int i = 0; i < this.aVorbisInfo9273.channels; i++)
		class298_sub30.aShortArrayArray7368[i] = (this.aClass270_9277.method2533((class298_sub30.aShortArrayArray7368[i]), (byte) -8));
	    this.aClass298_Sub19_Sub3_9278.method3034(class298_sub30, -830115957);
	}
    }

    @Override
    void method3078(OggPacket oggpacket) {
	if (this.anInt7352 * -1312498565 < 3) {
	    int i = (this.aVorbisInfo9273.headerIn(this.aVorbisComment9274, oggpacket));
	    if (i < 0)
		throw new IllegalStateException(new StringBuilder().append("").append(i).toString());
	    if (-1312498565 * this.anInt7352 == 2) {
		if (this.aVorbisInfo9273.channels > 2 || (this.aVorbisInfo9273.channels < 1))
		    throw new RuntimeException(new StringBuilder().append("").append(this.aVorbisInfo9273.channels).toString());
		this.aDSPState9282 = new DSPState(this.aVorbisInfo9273);
		this.aVorbisBlock9275 = new VorbisBlock(this.aDSPState9282);
		this.aClass270_9277 = new Class270((this.aVorbisInfo9273.rate), 1164070869 * Class284.anInt3059);
		this.aClass298_Sub19_Sub3_9278 = new Class298_Sub19_Sub3(this.aVorbisInfo9273.channels);
	    }
	} else {
	    if (this.aVorbisBlock9275.synthesis(oggpacket) == 0)
		this.aDSPState9282.blockIn(this.aVorbisBlock9275);
	    float[][] fs = (this.aDSPState9282.pcmOut(this.aVorbisInfo9273.channels));
	    this.aDouble9279 = this.aDSPState9282.granuleTime();
	    if (-1.0 == this.aDouble9279)
		this.aDouble9279 = (float) (253299067 * (this.anInt9280)) / (float) (this.aVorbisInfo9273.rate);
	    this.aDSPState9282.read(fs[0].length);
	    this.anInt9280 += fs[0].length * -87069261;
	    Class298_Sub30 class298_sub30 = (this.aClass298_Sub19_Sub3_9278.method3032(fs[0].length, this.aDouble9279));
	    Class482.method6120(fs, (class298_sub30.aShortArrayArray7368), (byte) -23);
	    for (int i = 0; i < this.aVorbisInfo9273.channels; i++)
		class298_sub30.aShortArrayArray7368[i] = (this.aClass270_9277.method2533((class298_sub30.aShortArrayArray7368[i]), (byte) -76));
	    this.aClass298_Sub19_Sub3_9278.method3034(class298_sub30, -1886056518);
	}
    }

    double method3081(int i) {
	try {
	    double d = this.aDouble9279;
	    if (null != this.aClass298_Sub19_Sub3_9278) {
		d = this.aClass298_Sub19_Sub3_9278.method3038(1578639792);
		if (d < 0.0)
		    d = this.aDouble9279;
	    }
	    return d - 256.0F / (Class284.anInt3059 * 1164070869);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aiy.s(").append(')').toString());
	}
    }

    public Class298_Sub19_Sub3 method3082(int i) {
	try {
	    return this.aClass298_Sub19_Sub3_9278;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aiy.n(").append(')').toString());
	}
    }

    int method3083(int i) {
	try {
	    return ((null == this.aClass298_Sub19_Sub3_9278) ? 0 : this.aClass298_Sub19_Sub3_9278.method3031(920577613));
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aiy.z(").append(')').toString());
	}
    }
}
