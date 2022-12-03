/* Class298_Sub37_Sub13 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class MapDetails extends Class298_Sub37 {
    int anInt9639 = 1105193455;
    public String namehash;
    public String name;
    Class453 aClass453_9642;
    public int fileId;
    public int tileHash;
    public int defaultZoom = 715250349;
    public int anInt9646;
    public int anInt9647;
    boolean load;
    public int anInt9649;
    public int anInt9650 = 1438003712;

    boolean method3450(int i, int i_0_, byte i_1_) {
	try {
	    for (MapRectangle class298_sub42 = (MapRectangle) this.aClass453_9642.method5939(1766612795); null != class298_sub42; class298_sub42 = (MapRectangle) this.aClass453_9642.method5944(49146)) {
		if (class298_sub42.method3521(i, i_0_, 2010350354))
		    return true;
	    }
	    return false;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aiu.a(").append(')').toString());
	}
    }

    public boolean method3451(int i, int i_2_, int[] is, int i_3_) {
	try {
	    for (MapRectangle class298_sub42 = (MapRectangle) this.aClass453_9642.method5939(1766612795); class298_sub42 != null; class298_sub42 = (MapRectangle) this.aClass453_9642.method5944(49146)) {
		if (class298_sub42.method3522(i, i_2_, (byte) -59)) {
		    class298_sub42.method3523(i, i_2_, is, 798889931);
		    return true;
		}
	    }
	    return false;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aiu.f(").append(')').toString());
	}
    }

    public boolean method3452(int i, int i_4_, int[] is, int i_5_) {
	try {
	    for (MapRectangle class298_sub42 = (MapRectangle) this.aClass453_9642.method5939(1766612795); class298_sub42 != null; class298_sub42 = (MapRectangle) this.aClass453_9642.method5944(49146)) {
		if (class298_sub42.method3521(i, i_4_, 1017053502)) {
		    class298_sub42.method3524(i, i_4_, is, -1549696053);
		    return true;
		}
	    }
	    return false;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aiu.b(").append(')').toString());
	}
    }

    public boolean method3453(int i, int i_6_, int i_7_, int[] is, int i_8_) {
	try {
	    for (MapRectangle class298_sub42 = (MapRectangle) this.aClass453_9642.method5939(1766612795); class298_sub42 != null; class298_sub42 = (MapRectangle) this.aClass453_9642.method5944(49146)) {
		if (class298_sub42.method3520(i, i_6_, i_7_, 985092463)) {
		    class298_sub42.method3524(i_6_, i_7_, is, -2077374034);
		    return true;
		}
	    }
	    return false;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aiu.p(").append(')').toString());
	}
    }

    void method3454(int i) {
	try {
	    anInt9650 = 1438003712;
	    anInt9647 = 0;
	    anInt9649 = 1374107136;
	    anInt9646 = 0;
	    for (MapRectangle class298_sub42 = (MapRectangle) this.aClass453_9642.method5939(1766612795); class298_sub42 != null; class298_sub42 = (MapRectangle) this.aClass453_9642.method5944(49146)) {
		if (class298_sub42.anInt7457 * 1528024175 < 1364716801 * anInt9650)
		    anInt9650 = (class298_sub42.anInt7457 * -878769809);
		if (class298_sub42.anInt7461 * 37578241 > anInt9647 * -2052483955)
		    anInt9647 = (class298_sub42.anInt7461 * -1211577275);
		if (50981941 * class298_sub42.anInt7463 < anInt9649 * 1345239131)
		    anInt9649 = -608005457 * (class298_sub42.anInt7463);
		if (1374138429 * class298_sub42.anInt7465 > 929385381 * anInt9646)
		    anInt9646 = (-1870852423 * class298_sub42.anInt7465);
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aiu.i(").append(')').toString());
	}
    }

    MapDetails(int id, String namehash, String name, int tileHash, int i_11_, boolean load, int defaultZoom, int mapSize) {
	anInt9647 = 0;
	anInt9649 = 1374107136;
	anInt9646 = 0;
	this.load = true;
	this.fileId = -1929943701 * id;
	this.namehash = namehash;
	this.name = name;
	this.tileHash = tileHash * 350230413;
	this.anInt9639 = -1105193455 * i_11_;
	this.load = load;
	this.defaultZoom = defaultZoom * -715250349;
	if (1110271707 * this.defaultZoom == 255)
	    this.defaultZoom = 0;
	Class94.method1020(mapSize, 1337257918);
	this.aClass453_9642 = new Class453();
    }

    public static final void method3455(int i, int i_14_) {
	try {
	    if (Class350.aClass298_Sub25_3757 != null && (i >= 0 && i < 649879491 * (Class350.aClass298_Sub25_3757.anInt7356))) {
		Class163 class163 = Class350.aClass298_Sub25_3757.members[i];
		if (-1 == class163.aByte1683) {
		    Class25 class25 = Class429.method5760((short) 512);
		    Class298_Sub36 class298_sub36 = Class18.method359(OutcommingPacket.aClass198_2045, class25.aClass449_330, (byte) 120);
		    class298_sub36.out.writeByte(2 + Class58.method693(class163.aString1681, 1064865624));
		    class298_sub36.out.writeShort(i, 16711935);
		    class298_sub36.out.writeString(class163.aString1681, 2115033353);
		    class25.method390(class298_sub36, (byte) -90);
		}
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aiu.mo(").append(')').toString());
	}
    }
}
