
/* Class52_Sub2_Sub3 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.awt.Canvas;
import java.awt.Dimension;

import jaggl.OpenGL;

public class Class52_Sub2_Sub3 extends Class52_Sub2 {
    Class_ra_Sub2 aClass_ra_Sub2_9081;
    Canvas aCanvas9082;
    OpenGL anOpenGL9083;
    long aLong9084;
    int anInt9085;
    int anInt9086;
    boolean aBoolean9087 = false;

    @Override
    public int method545() {
	return this.anInt9085;
    }

    @Override
    boolean method136() {
	if (this.aBoolean9087)
	    throw new IllegalStateException();
	this.anOpenGL9083.setSurface(this.aLong9084);
	this.aClass_ra_Sub2_9081.method5225();
	return true;
    }

    @Override
    public int method552() {
	return this.anInt9086;
    }

    @Override
    void method582(int i, int i_0_) {
	if (this.aBoolean9087)
	    throw new IllegalStateException();
	this.anOpenGL9083.surfaceResized(this.aLong9084);
	method597();
	if (this == this.aClass_ra_Sub2_9081.method4992((short) 2050))
	    this.aClass_ra_Sub2_9081.method5257();
    }

    void method597() {
	Dimension dimension = this.aCanvas9082.getSize();
	this.anInt9086 = dimension.height;
	this.anInt9085 = dimension.width;
    }

    @Override
    public int method580() {
	if (this.aBoolean9087)
	    throw new IllegalStateException();
	this.anOpenGL9083.swapBuffers(this.aLong9084);
	return 0;
    }

    Class52_Sub2_Sub3(Class_ra_Sub2 class_ra_sub2, Canvas canvas) {
	this(class_ra_sub2, canvas, class_ra_sub2.anOpenGL8116.prepareSurface(canvas));
    }

    @Override
    public void method138() {
	if (this.aBoolean9087)
	    throw new IllegalStateException();
	this.anOpenGL9083.releaseSurface(this.aCanvas9082, this.aLong9084);
	this.aBoolean9087 = true;
    }

    @Override
    public int method581(int i, int i_1_) {
	return 0;
    }

    @Override
    public void method135() {
	if (this.aBoolean9087)
	    throw new IllegalStateException();
	this.anOpenGL9083.releaseSurface(this.aCanvas9082, this.aLong9084);
	this.aBoolean9087 = true;
    }

    @Override
    public int method544() {
	return this.anInt9086;
    }

    @Override
    boolean method134() {
	if (this.aBoolean9087)
	    throw new IllegalStateException();
	this.anOpenGL9083.setSurface(this.aLong9084);
	this.aClass_ra_Sub2_9081.method5225();
	return true;
    }

    @Override
    boolean method548() {
	return true;
    }

    @Override
    public int method549() {
	return this.anInt9085;
    }

    @Override
    public int method550() {
	return this.anInt9085;
    }

    @Override
    void method583(int i, int i_2_) {
	if (this.aBoolean9087)
	    throw new IllegalStateException();
	this.anOpenGL9083.surfaceResized(this.aLong9084);
	method597();
	if (this == this.aClass_ra_Sub2_9081.method4992((short) -3580))
	    this.aClass_ra_Sub2_9081.method5257();
    }

    @Override
    boolean method546() {
	return true;
    }

    Class52_Sub2_Sub3(Class_ra_Sub2 class_ra_sub2, Canvas canvas, long l) {
	this.aClass_ra_Sub2_9081 = class_ra_sub2;
	this.aCanvas9082 = canvas;
	this.anOpenGL9083 = class_ra_sub2.anOpenGL8116;
	this.aLong9084 = l;
	method597();
    }

    @Override
    public int method551() {
	return this.anInt9085;
    }

    @Override
    public int method547() {
	return this.anInt9085;
    }

    @Override
    public int method579() {
	if (this.aBoolean9087)
	    throw new IllegalStateException();
	this.anOpenGL9083.swapBuffers(this.aLong9084);
	return 0;
    }

    @Override
    public int method584(int i, int i_3_) {
	return 0;
    }

    @Override
    public int method585(int i, int i_4_) {
	return 0;
    }

    @Override
    public int method586(int i, int i_5_) {
	return 0;
    }

    @Override
    public int method587(int i, int i_6_) {
	return 0;
    }

    @Override
    public void method137() {
	if (this.aBoolean9087)
	    throw new IllegalStateException();
	this.anOpenGL9083.releaseSurface(this.aCanvas9082, this.aLong9084);
	this.aBoolean9087 = true;
    }
}
