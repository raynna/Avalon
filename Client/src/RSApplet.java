
/* Class319_Sub1 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class RSApplet extends Class319 implements MouseListener, MouseMotionListener, MouseWheelListener {
    Class453 aClass453_7691;
    static int anInt7692 = 2;
    int anInt7693;
    int anInt7694;
    static int anInt7695 = 4;
    int anInt7696;
    Class453 aClass453_7697 = new Class453();
    int anInt7698;
    int anInt7699;
    int anInt7700;
    static int anInt7701 = 1;
    Component aComponent7702;
    boolean aBoolean7703;

    @Override
    public boolean method3878(int i) {
	try {
	    return 0 != (-31480187 * this.anInt7696 & 0x1);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aci.p(").append(')').toString());
	}
    }

    void method3905(Component component, int i) {
	try {
	    method3906(1230262531);
	    this.aComponent7702 = component;
	    this.aComponent7702.addMouseListener(this);
	    this.aComponent7702.addMouseMotionListener(this);
	    this.aComponent7702.addMouseWheelListener(this);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aci.at(").append(')').toString());
	}
    }

    void method3906(int i) {
	try {
	    if (null != this.aComponent7702) {
		this.aComponent7702.removeMouseWheelListener(this);
		this.aComponent7702.removeMouseMotionListener(this);
		this.aComponent7702.removeMouseListener(this);
		this.aComponent7702 = null;
		this.anInt7696 = 0;
		this.anInt7700 = 0;
		this.anInt7694 = 0;
		this.anInt7693 = 0;
		this.anInt7699 = 0;
		this.anInt7698 = 0;
		this.aClass453_7697 = null;
		this.aClass453_7691 = null;
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aci.ah(").append(')').toString());
	}
    }

    @Override
    public boolean method3890() {
	return 0 != (-31480187 * this.anInt7696 & 0x1);
    }

    void method3907(int i, int i_0_, int i_1_) {
	try {
	    this.anInt7698 = i * -213131387;
	    this.anInt7699 = i_0_ * -968658837;
	    if (this.aBoolean7703)
		method3908(-1, i, i_0_, 0, 1553525097);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aci.al(").append(')').toString());
	}
    }

    @Override
    public boolean method3887(int i) {
	try {
	    return (-31480187 * this.anInt7696 & 0x2) != 0;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aci.i(").append(')').toString());
	}
    }

    RSApplet(Component component, boolean bool) {
	this.aClass453_7691 = new Class453();
	method3905(component, -1437014279);
	this.aBoolean7703 = bool;
    }

    void method3908(int i, int i_2_, int i_3_, int i_4_, int i_5_) {
	try {
	    Class298_Sub50_Sub1 class298_sub50_sub1 = Class523.method6333(i, i_2_, i_3_, Class122.method1319((byte) 1), i_4_, -1798824718);
	    this.aClass453_7691.add(class298_sub50_sub1);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aci.ai(").append(')').toString());
	}
    }

    @Override
    public boolean method3880(byte i) {
	try {
	    return (this.anInt7696 * -31480187 & 0x4) != 0;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aci.k(").append(')').toString());
	}
    }

    @Override
    public int method3899() {
	return this.anInt7700 * 28748355;
    }

    @Override
    public int method3883(byte i) {
	try {
	    return this.anInt7700 * 28748355;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aci.x(").append(')').toString());
	}
    }

    @Override
    public void method3882(byte i) {
	try {
	    method3906(868673993);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aci.q(").append(')').toString());
	}
    }

    @Override
    public synchronized void mouseClicked(MouseEvent mouseevent) {
	try {
	    if (mouseevent.isPopupTrigger())
		mouseevent.consume();
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aci.mouseClicked(").append(')').toString());
	}
    }

    @Override
    public synchronized void mouseEntered(MouseEvent mouseevent) {
	try {
	    method3907(mouseevent.getX(), mouseevent.getY(), 459711214);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aci.mouseEntered(").append(')').toString());
	}
    }

    @Override
    public synchronized void mouseExited(MouseEvent mouseevent) {
	try {
	    method3907(mouseevent.getX(), mouseevent.getY(), 1366931071);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aci.mouseExited(").append(')').toString());
	}
    }

    int method3909(MouseEvent mouseevent, int i) {
	try {
	    if (mouseevent.getButton() == 1) {
		if (mouseevent.isMetaDown())
		    return 4;
		return 1;
	    }
	    if (mouseevent.getButton() == 2)
		return 2;
	    if (mouseevent.getButton() == 3)
		return 4;
	    return 0;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aci.az(").append(')').toString());
	}
    }

    @Override
    public synchronized void mousePressed(MouseEvent mouseevent) {
	try {
	    int i = method3909(mouseevent, 1930435178);
	    if (1 == i)
		method3908(0, mouseevent.getX(), mouseevent.getY(), mouseevent.getClickCount(), 1989072538);
	    else if (i == 4)
		method3908(2, mouseevent.getX(), mouseevent.getY(), mouseevent.getClickCount(), 1922972976);
	    else if (2 == i)
		method3908(1, mouseevent.getX(), mouseevent.getY(), mouseevent.getClickCount(), 2016030026);
	    this.anInt7693 = ((2114498315 * this.anInt7693 | i) * 1282483363);
	    if (mouseevent.isPopupTrigger())
		mouseevent.consume();
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aci.mousePressed(").append(')').toString());
	}
    }

    @Override
    public synchronized void mouseReleased(MouseEvent mouseevent) {
	try {
	    int i = method3909(mouseevent, 1755922620);
	    if ((2114498315 * this.anInt7693 & i) == 0)
		i = this.anInt7693 * 2114498315;
	    if ((i & 0x1) != 0)
		method3908(3, mouseevent.getX(), mouseevent.getY(), mouseevent.getClickCount(), 1931509920);
	    if ((i & 0x4) != 0)
		method3908(5, mouseevent.getX(), mouseevent.getY(), mouseevent.getClickCount(), 1062616351);
	    if ((i & 0x2) != 0)
		method3908(4, mouseevent.getX(), mouseevent.getY(), mouseevent.getClickCount(), 1204760399);
	    this.anInt7693 = (this.anInt7693 * 2114498315 & (i ^ 0xffffffff)) * 1282483363;
	    if (mouseevent.isPopupTrigger())
		mouseevent.consume();
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aci.mouseReleased(").append(')').toString());
	}
    }

    @Override
    public synchronized void mouseDragged(MouseEvent mouseevent) {
	try {
	    method3907(mouseevent.getX(), mouseevent.getY(), -2027156244);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aci.mouseDragged(").append(')').toString());
	}
    }

    @Override
    public synchronized void mouseMoved(MouseEvent mouseevent) {
	try {
	    method3907(mouseevent.getX(), mouseevent.getY(), -1257091860);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aci.mouseMoved(").append(')').toString());
	}
    }

	public int mouseWheelDelta = -1;

    @Override
    public synchronized void mouseWheelMoved(MouseWheelEvent mousewheelevent) {
	try {
	    int i = mousewheelevent.getX();
	    int i_6_ = mousewheelevent.getY();
	    int i_7_ = mousewheelevent.getWheelRotation();
	    method3908(6, i, i_6_, i_7_, 1166829560);
	    mousewheelevent.consume();
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aci.mouseWheelMoved(").append(')').toString());
	}
	client.zoomDelta = mousewheelevent.isControlDown() ? 40 : 12;
	if (mousewheelevent.getWheelRotation() == 1) {
		client.zoomDelta = -client.zoomDelta;
		}
    }

    @Override
    public synchronized void method3877() {
	this.anInt7694 = this.anInt7698 * -867321853;
	this.anInt7700 = this.anInt7699 * -1698396671;
	this.anInt7696 = this.anInt7693 * 866311247;
	Class453 class453 = this.aClass453_7697;
	this.aClass453_7697 = this.aClass453_7691;
	this.aClass453_7691 = class453;
	this.aClass453_7691.method5943((byte) 1);
    }

    @Override
    public synchronized void method3884() {
	this.anInt7694 = this.anInt7698 * -867321853;
	this.anInt7700 = this.anInt7699 * -1698396671;
	this.anInt7696 = this.anInt7693 * 866311247;
	Class453 class453 = this.aClass453_7697;
	this.aClass453_7697 = this.aClass453_7691;
	this.aClass453_7691 = class453;
	this.aClass453_7691.method5943((byte) 1);
    }

    @Override
    public boolean method3889() {
	return (-31480187 * this.anInt7696 & 0x2) != 0;
    }

    @Override
    public void method3898() {
	method3906(-1710561997);
    }

    @Override
    public boolean method3888() {
	return 0 != (-31480187 * this.anInt7696 & 0x1);
    }

    @Override
    public boolean method3892() {
	return (-31480187 * this.anInt7696 & 0x2) != 0;
    }

    @Override
    public synchronized void method3886(int i) {
	try {
	    this.anInt7694 = this.anInt7698 * -867321853;
	    this.anInt7700 = this.anInt7699 * -1698396671;
	    this.anInt7696 = this.anInt7693 * 866311247;
	    Class453 class453 = this.aClass453_7697;
	    this.aClass453_7697 = this.aClass453_7691;
	    this.aClass453_7691 = class453;
	    this.aClass453_7691.method5943((byte) 1);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aci.b(").append(')').toString());
	}
    }

    @Override
    public boolean method3893() {
	return (this.anInt7696 * -31480187 & 0x4) != 0;
    }

    @Override
    public int method3895() {
	return this.anInt7694 * 1215441007;
    }

    @Override
    public Class298_Sub50 method3896() {
	return ((Class298_Sub50) this.aClass453_7697.method5936(2123266868));
    }

    @Override
    public Class298_Sub50 method3885() {
	return ((Class298_Sub50) this.aClass453_7697.method5936(2127727655));
    }

    @Override
    public int method3894(byte i) {
	try {
	    return this.anInt7694 * 1215441007;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aci.u(").append(')').toString());
	}
    }

    @Override
    public Class298_Sub50 method3879(int i) {
	try {
	    return (Class298_Sub50) this.aClass453_7697.method5936(2114584348);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("aci.r(").append(')').toString());
	}
    }

    @Override
    public void method3897() {
	method3906(-1322459721);
    }

    @Override
    public boolean method3891() {
	return 0 != (-31480187 * this.anInt7696 & 0x1);
    }
}
