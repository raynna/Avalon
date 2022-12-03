
/* Class52_Sub2_Sub1_Sub1 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.DirectColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.util.Hashtable;

public final class Class52_Sub2_Sub1_Sub1 extends Class52_Sub2_Sub1 {
    Image anImage9986;
    static Class353[] aClass353Array9987;

    void method592() {
	super.method591(251162115);
	DataBufferInt databufferint = new DataBufferInt(this.anIntArray9076, (this.anIntArray9076).length);
	DirectColorModel directcolormodel = new DirectColorModel(32, 16711680, 65280, 255);
	WritableRaster writableraster = (Raster.createWritableRaster((directcolormodel.createCompatibleSampleModel(this.anInt9074 * 1038713159, 2061776189 * this.anInt9075)), databufferint, null));
	this.anImage9986 = new BufferedImage(directcolormodel, writableraster, false, new Hashtable());
    }

    @Override
    public final int method586(int i, int i_0_) {
	Graphics graphics = this.aCanvas9072.getGraphics();
	graphics.drawImage(this.anImage9986, i, i_0_, this.aCanvas9072);
	return 0;
    }

    Class52_Sub2_Sub1_Sub1(Class_ra_Sub1 class_ra_sub1, Canvas canvas, int i, int i_1_) {
	super(class_ra_sub1, canvas, i, i_1_);
	new Rectangle();
	method591(251162115);
    }

    @Override
    public int method580() {
	try {
	    return method581(0, 0);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("akh.v(").append(')').toString());
	}
    }

    @Override
    public void method135() {
	try {
	    /* empty */
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("akh.b(").append(')').toString());
	}
    }

    @Override
    public void method137() {
	/* empty */
    }

    @Override
    void method591(int i) {
	try {
	    super.method591(251162115);
	    DataBufferInt databufferint = new DataBufferInt((this.anIntArray9076), (this.anIntArray9076).length);
	    DirectColorModel directcolormodel = new DirectColorModel(32, 16711680, 65280, 255);
	    WritableRaster writableraster = (Raster.createWritableRaster((directcolormodel.createCompatibleSampleModel(this.anInt9074 * 1038713159, 2061776189 * this.anInt9075)), databufferint, null));
	    this.anImage9986 = new BufferedImage(directcolormodel, writableraster, false, new Hashtable());
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("akh.z(").append(')').toString());
	}
    }

    void method593() {
	super.method591(251162115);
	DataBufferInt databufferint = new DataBufferInt(this.anIntArray9076, (this.anIntArray9076).length);
	DirectColorModel directcolormodel = new DirectColorModel(32, 16711680, 65280, 255);
	WritableRaster writableraster = (Raster.createWritableRaster((directcolormodel.createCompatibleSampleModel(this.anInt9074 * 1038713159, 2061776189 * this.anInt9075)), databufferint, null));
	this.anImage9986 = new BufferedImage(directcolormodel, writableraster, false, new Hashtable());
    }

    void method594() {
	super.method591(251162115);
	DataBufferInt databufferint = new DataBufferInt(this.anIntArray9076, (this.anIntArray9076).length);
	DirectColorModel directcolormodel = new DirectColorModel(32, 16711680, 65280, 255);
	WritableRaster writableraster = (Raster.createWritableRaster((directcolormodel.createCompatibleSampleModel(this.anInt9074 * 1038713159, 2061776189 * this.anInt9075)), databufferint, null));
	this.anImage9986 = new BufferedImage(directcolormodel, writableraster, false, new Hashtable());
    }

    @Override
    public void method138() {
	/* empty */
    }

    @Override
    public int method579() {
	return method581(0, 0);
    }

    @Override
    public final int method584(int i, int i_2_) {
	Graphics graphics = this.aCanvas9072.getGraphics();
	graphics.drawImage(this.anImage9986, i, i_2_, this.aCanvas9072);
	return 0;
    }

    @Override
    public final int method585(int i, int i_3_) {
	Graphics graphics = this.aCanvas9072.getGraphics();
	graphics.drawImage(this.anImage9986, i, i_3_, this.aCanvas9072);
	return 0;
    }

    @Override
    public final int method581(int i, int i_4_) {
	try {
	    Graphics graphics = this.aCanvas9072.getGraphics();
	    graphics.drawImage(this.anImage9986, i, i_4_, this.aCanvas9072);
	    return 0;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("akh.g(").append(')').toString());
	}
    }

    @Override
    public final int method587(int i, int i_5_) {
	Graphics graphics = this.aCanvas9072.getGraphics();
	graphics.drawImage(this.anImage9986, i, i_5_, this.aCanvas9072);
	return 0;
    }
}
