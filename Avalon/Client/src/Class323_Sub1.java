
/* Class323_Sub1 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class Class323_Sub1 extends Class323 implements KeyListener, FocusListener {
    static int[] anIntArray7704 = { 0, 0, 0, 0, 0, 0, 0, 0, 85, 80, 84, 0, 91, 0, 0, 0, 81, 82, 86, 0, 0, 0, 0, 0, 0, 0, 0, 13, 0, 0, 0, 0, 83, 104, 105, 103, 102, 96, 98, 97, 99, 0, 0, 0, 0, 0, 0, 0, 25, 16, 17, 18, 19, 20, 21, 22, 23, 24, 0, 0, 0, 0, 0, 0, 0, 48, 68, 66, 50, 34, 51, 52, 53, 39, 54, 55, 56, 70, 69, 40, 41, 32, 35, 49, 36, 38, 67, 33, 65, 37, 64, 0, 0, 0, 0, 0, 228, 231, 227, 233, 224, 219, 225, 230, 226, 232, 89, 87, 0, 88, 229, 90, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 0, 0, 0, 101, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    static int anInt7705 = 128;
    Class453 aClass453_7706 = new Class453();
    static int anInt7707 = 112;
    boolean[] aBooleanArray7708;
    Component aComponent7709;
    Class453 aClass453_7710 = new Class453();

    void method3950(Component component, byte i) {
	try {
	    method3951(-1697556244);
	    this.aComponent7709 = component;
	    this.aComponent7709.addKeyListener(this);
	    this.aComponent7709.addFocusListener(this);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("acn.h(").append(')').toString());
	}
    }

    void method3951(int i) {
	try {
	    if (null != this.aComponent7709) {
		this.aComponent7709.removeKeyListener(this);
		this.aComponent7709.removeFocusListener(this);
		this.aComponent7709 = null;
		for (int i_0_ = 0; i_0_ < 112; i_0_++)
		    this.aBooleanArray7708[i_0_] = false;
		this.aClass453_7706.method5943((byte) 1);
		this.aClass453_7710.method5943((byte) 1);
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("acn.v(").append(')').toString());
	}
    }

    void method3952(int i, char c, int i_1_, int i_2_) {
	try {
	    Class298_Sub43 class298_sub43 = new Class298_Sub43();
	    class298_sub43.anInt6506 = i * -492671955;
	    class298_sub43.aChar6510 = c;
	    class298_sub43.anInt6508 = i_1_ * 666762723;
	    class298_sub43.aLong6509 = Class122.method1319((byte) 1) * -6780259989437506341L;
	    this.aClass453_7710.add(class298_sub43);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("acn.g(").append(')').toString());
	}
    }

    @Override
    public Interface16 method3937(byte i) {
	try {
	    return (Interface16) this.aClass453_7706.method5936(2081715499);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("acn.p(").append(')').toString());
	}
    }

    @Override
    public boolean method3936(int i, int i_3_) {
	try {
	    if (i < 0 || i >= 112)
		return false;
	    return this.aBooleanArray7708[i];
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("acn.b(").append(')').toString());
	}
    }

    @Override
    public void method3938(int i) {
	try {
	    method3951(-789412244);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("acn.i(").append(')').toString());
	}
    }

    void method3953(KeyEvent keyevent, int i, int i_4_) {
	try {
	    int i_5_ = keyevent.getKeyCode();
	    if (i_5_ != 0) {
		if (i_5_ >= 0 && i_5_ < anIntArray7704.length) {
		    i_5_ = anIntArray7704[i_5_];
		    if (i == 0 && 0 != (i_5_ & 0x80))
			i_5_ = 0;
		    else
			i_5_ &= ~0x80;
		} else
		    i_5_ = 0;
	    } else
		i_5_ = 0;
	    if (i_5_ != 0) {
		method3952(i, '\uffff', i_5_, -2128667292);
		keyevent.consume();
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("acn.c(").append(')').toString());
	}
    }

    @Override
    public synchronized void method3942(int i) {
	try {
	    this.aClass453_7706.method5943((byte) 1);
	    for (Class298_Sub43 class298_sub43 = (Class298_Sub43) this.aClass453_7710.method5936(2094124912); null != class298_sub43; class298_sub43 = (Class298_Sub43) this.aClass453_7710.method5936(2145579859)) {
		class298_sub43.anInt6507 = method3954(-2041069828) * -387842989;
		if (0 == (class298_sub43.anInt6506 * 1490207653)) {
		    if (!this.aBooleanArray7708[(class298_sub43.anInt6508 * 122236875)]) {
			Class298_Sub43 class298_sub43_6_ = new Class298_Sub43();
			class298_sub43_6_.anInt6506 = 0;
			class298_sub43_6_.aChar6510 = '\uffff';
			class298_sub43_6_.anInt6508 = class298_sub43.anInt6508 * 1;
			class298_sub43_6_.aLong6509 = class298_sub43.aLong6509 * 1L;
			class298_sub43_6_.anInt6507 = 1 * class298_sub43.anInt6507;
			this.aClass453_7706.add(class298_sub43_6_);
			this.aBooleanArray7708[(class298_sub43.anInt6508 * 122236875)] = true;
		    }
		    class298_sub43.anInt6506 = -985343910;
		    this.aClass453_7706.add(class298_sub43);
		} else if (1 == (class298_sub43.anInt6506 * 1490207653)) {
		    if (this.aBooleanArray7708[(class298_sub43.anInt6508 * 122236875)]) {
			this.aClass453_7706.add(class298_sub43);
			this.aBooleanArray7708[(class298_sub43.anInt6508 * 122236875)] = false;
		    }
		} else if ((1490207653 * class298_sub43.anInt6506) == -1) {
		    for (int i_7_ = 0; i_7_ < 112; i_7_++) {
			if (this.aBooleanArray7708[i_7_]) {
			    Class298_Sub43 class298_sub43_8_ = new Class298_Sub43();
			    class298_sub43_8_.anInt6506 = -492671955;
			    class298_sub43_8_.aChar6510 = '\uffff';
			    class298_sub43_8_.anInt6508 = 666762723 * i_7_;
			    class298_sub43_8_.aLong6509 = 1L * (class298_sub43.aLong6509);
			    class298_sub43_8_.anInt6507 = (class298_sub43.anInt6507 * 1);
			    this.aClass453_7706.add(class298_sub43_8_);
			    this.aBooleanArray7708[i_7_] = false;
			}
		    }
		} else if (3 == (class298_sub43.anInt6506 * 1490207653))
		    this.aClass453_7706.add(class298_sub43);
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("acn.f(").append(')').toString());
	}
    }

    @Override
    public synchronized void keyReleased(KeyEvent keyevent) {
	try {
	    method3953(keyevent, 1, 1838328599);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("acn.keyReleased(").append(')').toString());
	}
    }

    @Override
    public synchronized void keyTyped(KeyEvent keyevent) {
	try {
	    char c = keyevent.getKeyChar();
	    if (c != '\uffff' && Class398.method4921(c, 503878234)) {
		method3952(3, c, -1, -1849643187);
		keyevent.consume();
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("acn.keyTyped(").append(')').toString());
	}
    }

    @Override
    public void focusGained(FocusEvent focusevent) {
	try {
	    /* empty */
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("acn.focusGained(").append(')').toString());
	}
    }

    int method3954(int i) {
	try {
	    int i_9_ = 0;
	    if (this.aBooleanArray7708[81])
		i_9_ |= 0x1;
	    if (this.aBooleanArray7708[82])
		i_9_ |= 0x4;
	    if (this.aBooleanArray7708[86])
		i_9_ |= 0x2;
	    return i_9_;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("acn.e(").append(')').toString());
	}
    }

    @Override
    public synchronized void focusLost(FocusEvent focusevent) {
	try {
	    method3952(-1, '\0', 0, -1444007331);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("acn.focusLost(").append(')').toString());
	}
    }

    @Override
    public Interface16 method3939() {
	return ((Interface16) this.aClass453_7706.method5936(2115276453));
    }

    @Override
    public void method3943() {
	method3951(560458852);
    }

    @Override
    public Interface16 method3940() {
	return ((Interface16) this.aClass453_7706.method5936(2103688875));
    }

    Class323_Sub1(Component component) {
	this.aBooleanArray7708 = new boolean[112];
	Class82_Sub15.method915((byte) -1);
	method3950(component, (byte) 0);
    }

    @Override
    public void method3934() {
	method3951(1992664348);
    }

    @Override
    public synchronized void keyPressed(KeyEvent keyevent) {
	try {
	    method3953(keyevent, 0, 1184410257);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("acn.keyPressed(").append(')').toString());
	}
    }

    @Override
    public void method3941() {
	method3951(-743392356);
    }

    @Override
    public synchronized void method3935() {
	this.aClass453_7706.method5943((byte) 1);
	for (Class298_Sub43 class298_sub43 = (Class298_Sub43) this.aClass453_7710.method5936(2122541864); null != class298_sub43; class298_sub43 = (Class298_Sub43) this.aClass453_7710.method5936(2131772835)) {
	    class298_sub43.anInt6507 = method3954(-1895745792) * -387842989;
	    if (0 == class298_sub43.anInt6506 * 1490207653) {
		if (!this.aBooleanArray7708[(class298_sub43.anInt6508 * 122236875)]) {
		    Class298_Sub43 class298_sub43_10_ = new Class298_Sub43();
		    class298_sub43_10_.anInt6506 = 0;
		    class298_sub43_10_.aChar6510 = '\uffff';
		    class298_sub43_10_.anInt6508 = class298_sub43.anInt6508 * 1;
		    class298_sub43_10_.aLong6509 = class298_sub43.aLong6509 * 1L;
		    class298_sub43_10_.anInt6507 = 1 * class298_sub43.anInt6507;
		    this.aClass453_7706.add(class298_sub43_10_);
		    this.aBooleanArray7708[(class298_sub43.anInt6508 * 122236875)] = true;
		}
		class298_sub43.anInt6506 = -985343910;
		this.aClass453_7706.add(class298_sub43);
	    } else if (1 == (class298_sub43.anInt6506 * 1490207653)) {
		if (this.aBooleanArray7708[(class298_sub43.anInt6508 * 122236875)]) {
		    this.aClass453_7706.add(class298_sub43);
		    this.aBooleanArray7708[(class298_sub43.anInt6508 * 122236875)] = false;
		}
	    } else if (1490207653 * class298_sub43.anInt6506 == -1) {
		for (int i = 0; i < 112; i++) {
		    if (this.aBooleanArray7708[i]) {
			Class298_Sub43 class298_sub43_11_ = new Class298_Sub43();
			class298_sub43_11_.anInt6506 = -492671955;
			class298_sub43_11_.aChar6510 = '\uffff';
			class298_sub43_11_.anInt6508 = 666762723 * i;
			class298_sub43_11_.aLong6509 = 1L * class298_sub43.aLong6509;
			class298_sub43_11_.anInt6507 = class298_sub43.anInt6507 * 1;
			this.aClass453_7706.add(class298_sub43_11_);
			this.aBooleanArray7708[i] = false;
		    }
		}
	    } else if (3 == (class298_sub43.anInt6506 * 1490207653))
		this.aClass453_7706.add(class298_sub43);
	}
    }

    @Override
    public synchronized void method3944() {
	this.aClass453_7706.method5943((byte) 1);
	for (Class298_Sub43 class298_sub43 = (Class298_Sub43) this.aClass453_7710.method5936(2115108568); null != class298_sub43; class298_sub43 = (Class298_Sub43) this.aClass453_7710.method5936(2096117527)) {
	    class298_sub43.anInt6507 = method3954(-1945297664) * -387842989;
	    if (0 == class298_sub43.anInt6506 * 1490207653) {
		if (!this.aBooleanArray7708[(class298_sub43.anInt6508 * 122236875)]) {
		    Class298_Sub43 class298_sub43_12_ = new Class298_Sub43();
		    class298_sub43_12_.anInt6506 = 0;
		    class298_sub43_12_.aChar6510 = '\uffff';
		    class298_sub43_12_.anInt6508 = class298_sub43.anInt6508 * 1;
		    class298_sub43_12_.aLong6509 = class298_sub43.aLong6509 * 1L;
		    class298_sub43_12_.anInt6507 = 1 * class298_sub43.anInt6507;
		    this.aClass453_7706.add(class298_sub43_12_);
		    this.aBooleanArray7708[(class298_sub43.anInt6508 * 122236875)] = true;
		}
		class298_sub43.anInt6506 = -985343910;
		this.aClass453_7706.add(class298_sub43);
	    } else if (1 == (class298_sub43.anInt6506 * 1490207653)) {
		if (this.aBooleanArray7708[(class298_sub43.anInt6508 * 122236875)]) {
		    this.aClass453_7706.add(class298_sub43);
		    this.aBooleanArray7708[(class298_sub43.anInt6508 * 122236875)] = false;
		}
	    } else if (1490207653 * class298_sub43.anInt6506 == -1) {
		for (int i = 0; i < 112; i++) {
		    if (this.aBooleanArray7708[i]) {
			Class298_Sub43 class298_sub43_13_ = new Class298_Sub43();
			class298_sub43_13_.anInt6506 = -492671955;
			class298_sub43_13_.aChar6510 = '\uffff';
			class298_sub43_13_.anInt6508 = 666762723 * i;
			class298_sub43_13_.aLong6509 = 1L * class298_sub43.aLong6509;
			class298_sub43_13_.anInt6507 = class298_sub43.anInt6507 * 1;
			this.aClass453_7706.add(class298_sub43_13_);
			this.aBooleanArray7708[i] = false;
		    }
		}
	    } else if (3 == (class298_sub43.anInt6506 * 1490207653))
		this.aClass453_7706.add(class298_sub43);
	}
    }

    @Override
    public boolean method3945(int i) {
	if (i < 0 || i >= 112)
	    return false;
	return this.aBooleanArray7708[i];
    }

    public static void method3955(int i, int i_14_, int i_15_) {
	try {
	    if (0 != 617004265 * Class79.anInt734) {
		if (i < 0) {
		    for (int i_16_ = 0; i_16_ < 16; i_16_++)
			Class298_Sub10.anIntArray7229[i_16_] = i_14_;
		} else
		    Class298_Sub10.anIntArray7229[i] = i_14_;
	    }
	    Class79.aClass298_Sub19_Sub1_737.method2954(i, i_14_, 852255594);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("acn.u(").append(')').toString());
	}
    }
}
