
/* Class52_Sub2_Sub2_Sub1 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.awt.Canvas;
import java.awt.Dimension;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import jaggl.OpenGL;

public class Class52_Sub2_Sub2_Sub1 extends Class52_Sub2_Sub2 {
    OpenGL anOpenGL9988;
    long aLong9989;
    int anInt9990;
    Canvas aCanvas9991;
    int anInt9992;

    @Override
    public void method138() {
	this.anOpenGL9988.releaseSurface(this.aCanvas9991, this.aLong9989);
    }

    Class52_Sub2_Sub2_Sub1(Class_ra_Sub3_Sub1 class_ra_sub3_sub1, Canvas canvas, long l) {
	super(class_ra_sub3_sub1);
	this.aCanvas9991 = canvas;
	this.anOpenGL9988 = class_ra_sub3_sub1.anOpenGL9826;
	this.aLong9989 = l;
	method595();
    }

    @Override
    boolean method548() {
	return true;
    }

   /* static void method50123(int x) {
	try {
	    Class var_class = java.lang.ClassLoader.class;
	    Field field = var_class.getDeclaredField("nativeLibraries");
	    Class var_class_124_ = java.lang.reflect.AccessibleObject.class;
	    Method method = var_class_124_.getDeclaredMethod("setAccessible", (new Class[] { Boolean.TYPE }));
	    method.invoke(field, new Object[] { Boolean.TRUE });
	}
	catch (Throwable throwable) {
	    /* empty */
	/*}
    }*/

    @Override
    public int method552() {
	return this.anInt9992;
    }

    @Override
    public int method584(int i, int i_0_) {
	return 0;
    }

    void method595() {
	Dimension dimension = this.aCanvas9991.getSize();
	this.anInt9992 = dimension.height;
	this.anInt9990 = dimension.width;
    }

    @Override
    public int method549() {
	return this.anInt9990;
    }

    @Override
    public int method581(int i, int i_1_) {
	return 0;
    }

    @Override
    boolean method136() {
	return (this.anOpenGL9988.setSurface(this.aLong9989) && super.method136());
    }

    @Override
    boolean method546() {
	return true;
    }

    @Override
    public void method135() {
	this.anOpenGL9988.releaseSurface(this.aCanvas9991, this.aLong9989);
    }

    @Override
    public int method544() {
	return this.anInt9992;
    }

    @Override
    void method582(int i, int i_2_) {
	this.anOpenGL9988.surfaceResized(this.aLong9989);
	method595();
	super.method582(i, i_2_);
    }

    @Override
    public int method587(int i, int i_3_) {
	return 0;
    }

    @Override
    public void method137() {
	this.anOpenGL9988.releaseSurface(this.aCanvas9991, this.aLong9989);
    }

    @Override
    boolean method134() {
	return (this.anOpenGL9988.setSurface(this.aLong9989) && super.method136());
    }

    @Override
    public int method580() {
	this.anOpenGL9988.swapBuffers(this.aLong9989);
	return 0;
    }

    @Override
    public int method545() {
	return this.anInt9990;
    }

    @Override
    public int method551() {
	return this.anInt9990;
    }

    @Override
    public int method547() {
	return this.anInt9990;
    }

    @Override
    public int method579() {
	this.anOpenGL9988.swapBuffers(this.aLong9989);
	return 0;
    }

    Class52_Sub2_Sub2_Sub1(Class_ra_Sub3_Sub1 class_ra_sub3_sub1, Canvas canvas) {
	this(class_ra_sub3_sub1, canvas, class_ra_sub3_sub1.anOpenGL9826.prepareSurface(canvas));
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
    public int method550() {
	return this.anInt9990;
    }

    @Override
    void method583(int i, int i_6_) {
	this.anOpenGL9988.surfaceResized(this.aLong9989);
	method595();
	super.method582(i, i_6_);
    }
}
