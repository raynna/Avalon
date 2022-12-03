
/* Class47_Sub5 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import jaggl.OpenGL;

public class Class47_Sub5 extends Class47 {
    static String aString6775 = "!!ARBvp1.0\nATTRIB  iPos         = vertex.position;\nATTRIB  iColour      = vertex.color;\nATTRIB  iTexCoord    = vertex.texcoord[0];\nOUTPUT  oPos         = result.position;\nOUTPUT  oColour      = result.color;\nOUTPUT  oTexCoord0   = result.texcoord[0];\nOUTPUT  oTexCoord1   = result.texcoord[1];\nOUTPUT  oFogCoord    = result.fogcoord;\nPARAM   fogParams    = program.local[0];\nPARAM   waterPlane   = program.local[1];\nPARAM   pMatrix[4]   = { state.matrix.projection };\nPARAM   mvMatrix[4]  = { state.matrix.modelview };\nPARAM   texMatrix[4] = { state.matrix.texture[0] };\nTEMP    viewPos, fogFactor, depth;\nDP4   viewPos.x, mvMatrix[0], iPos;\nDP4   viewPos.y, mvMatrix[1], iPos;\nDP4   viewPos.z, mvMatrix[2], iPos;\nDP4   viewPos.w, mvMatrix[3], iPos;\nSUB   fogFactor.x, viewPos.z, fogParams.x;\nMUL   fogFactor.x, fogFactor.x, 0.001953125;\nDP4   depth, waterPlane, viewPos;\nMAD   fogFactor.y, -depth, fogParams.z, fogParams.w;\nSUB   fogFactor.z, viewPos.z, fogParams.y;\nMUL   fogFactor.z, fogFactor.z, 0.00390625;\nMIN   fogFactor, fogFactor, 1;\nMAX   fogFactor, fogFactor, 0;\nMUL   fogFactor.z, fogFactor.z, -depth;\nMAD   viewPos.xyz, waterPlane.xyzw, fogFactor.zzzz, viewPos.xyzw;\nMAX   oTexCoord1.xyz, fogFactor.xxxx, fogFactor.yyyy;\nMOV   oTexCoord1.w, 1;\nMOV   oColour, iColour;\nDP4   oPos.x, pMatrix[0], viewPos;\nDP4   oPos.y, pMatrix[1], viewPos;\nDP4   oPos.z, pMatrix[2], viewPos;\nDP4   oPos.w, pMatrix[3], viewPos;\nMOV   oFogCoord.x, viewPos.z;\nDP4   oTexCoord0.x, texMatrix[0], iTexCoord;\nDP4   oTexCoord0.y, texMatrix[1], iTexCoord;\nDP4   oTexCoord0.z, texMatrix[2], iTexCoord;\nMOV   oTexCoord0.w, 1;\nEND\n";
    static String aString6776 = "!!ARBvp1.0\nATTRIB  iPos         = vertex.position;\nATTRIB  iColour      = vertex.color;\nATTRIB  iTexCoord    = vertex.texcoord[0];\nOUTPUT  oPos         = result.position;\nOUTPUT  oColour      = result.color;\nOUTPUT  oTexCoord0   = result.texcoord[0];\nOUTPUT  oTexCoord1   = result.texcoord[1];\nOUTPUT  oFogCoord    = result.fogcoord;\nPARAM   fogParams    = program.local[0];\nPARAM   waterPlane   = program.local[1];\nPARAM   tMatrix[4]   = { state.matrix.texture[0] };\nPARAM   pMatrix[4]   = { state.matrix.projection };\nPARAM   mvMatrix[4]  = { state.matrix.modelview };\nTEMP    viewPos, fogFactor;\nDP4   viewPos.x, mvMatrix[0], iPos;\nDP4   viewPos.y, mvMatrix[1], iPos;\nDP4   viewPos.z, mvMatrix[2], iPos;\nDP4   viewPos.w, mvMatrix[3], iPos;\nSUB   fogFactor.x, viewPos.z, fogParams.x;\nMUL   fogFactor.x, fogFactor.x, 0.001953125;\nMAD   fogFactor.y, iTexCoord.z, fogParams.z, fogParams.w;\nSUB   fogFactor.z, viewPos.z, fogParams.y;\nMUL   fogFactor.z, fogFactor.z, 0.00390625;\nMUL   fogFactor.x, fogFactor.x, fogFactor.y;\nMIN   fogFactor, fogFactor, 1;\nMAX   fogFactor, fogFactor, 0;\nMUL   fogFactor.z, fogFactor.z, iTexCoord.z;\nMAD   viewPos.xyz, waterPlane.xyzw, fogFactor.zzzz, viewPos.xyzw;\nMAX   oTexCoord1.xyz, fogFactor.xxxx, fogFactor.yyyy;\nMOV   oTexCoord1.w, 1;\nMOV   oColour, iColour;\nDP4   oPos.x, pMatrix[0], viewPos;\nDP4   oPos.y, pMatrix[1], viewPos;\nDP4   oPos.z, pMatrix[2], viewPos;\nDP4   oPos.w, pMatrix[3], viewPos;\nMOV   oFogCoord.x, viewPos.z;\nDP3   oTexCoord0.x, tMatrix[0], iTexCoord;\nDP3   oTexCoord0.y, tMatrix[1], iTexCoord;\nMOV   oTexCoord0.zw, iTexCoord;\nEND\n";
    Class39 aClass39_6777;
    static String aString6778 = "!!ARBvp1.0\nATTRIB  iPos         = vertex.position;\nATTRIB  iNormal      = vertex.normal;\nATTRIB  iColour      = vertex.color;\nATTRIB  iTexCoord    = vertex.texcoord[0];\nOUTPUT  oPos         = result.position;\nOUTPUT  oColour      = result.color;\nOUTPUT  oTexCoord0   = result.texcoord[0];\nOUTPUT  oTexCoord1   = result.texcoord[1];\nOUTPUT  oFogCoord    = result.fogcoord;\nPARAM   fogParams    = program.local[0];\nPARAM   waterPlane   = program.local[1];\nPARAM   pMatrix[4]   = { state.matrix.projection };\nPARAM   mvMatrix[4]  = { state.matrix.modelview };\nPARAM   texMatrix[4] = { state.matrix.texture[0] };\nTEMP    viewPos, viewNormal, fogFactor, depth, colour, ndotl;\nDP4   viewPos.x, mvMatrix[0], iPos;\nDP4   viewPos.y, mvMatrix[1], iPos;\nDP4   viewPos.z, mvMatrix[2], iPos;\nDP4   viewPos.w, mvMatrix[3], iPos;\nSUB   fogFactor.x, viewPos.z, fogParams.x;\nMUL   fogFactor.x, fogFactor.x, 0.001953125;\nDP4   depth, waterPlane, viewPos;\nMAD   fogFactor.y, -depth, fogParams.z, fogParams.w;\nSUB   fogFactor.z, viewPos.z, fogParams.y;\nMUL   fogFactor.z, fogFactor.z, 0.00390625;\nMIN   fogFactor, fogFactor, 1;\nMAX   fogFactor, fogFactor, 0;\nMUL   fogFactor.z, fogFactor.z, -depth;\nMAD   viewPos.xyz, waterPlane.xyzw, fogFactor.zzzz, viewPos.xyzw;\nMAX   oTexCoord1.xyz, fogFactor.xxxx, fogFactor.yyyy;\nMOV   oTexCoord1.w, 1;\nDP3   viewNormal.x, mvMatrix[0], iNormal;\nDP3   viewNormal.y, mvMatrix[1], iNormal;\nDP3   viewNormal.z, mvMatrix[2], iNormal;\nDP3   ndotl.x, viewNormal, state.light[0].position;\nDP3   ndotl.y, viewNormal, state.light[1].position;\nMAX   ndotl, ndotl, 0;\nMOV   colour, state.lightmodel.ambient;\nMAD   colour, state.light[0].diffuse, ndotl.xxxx, colour;\nMAD   colour, state.light[1].diffuse, ndotl.yyyy, colour;\nMUL   oColour, iColour, colour;\nDP4   oPos.x, pMatrix[0], viewPos;\nDP4   oPos.y, pMatrix[1], viewPos;\nDP4   oPos.z, pMatrix[2], viewPos;\nDP4   oPos.w, pMatrix[3], viewPos;\nMOV   oFogCoord.x, viewPos.z;\nDP4   oTexCoord0.x, texMatrix[0], iTexCoord;\nDP4   oTexCoord0.y, texMatrix[1], iTexCoord;\nDP4   oTexCoord0.z, texMatrix[2], iTexCoord;\nMOV   oTexCoord0.w, 1;\nEND\n";
    boolean aBoolean6779;
    boolean aBoolean6780;
    boolean aBoolean6781;
    static String aString6782 = "!!ARBvp1.0\nATTRIB  iPos         = vertex.position;\nATTRIB  iNormal      = vertex.normal;\nATTRIB  iColour      = vertex.color;\nATTRIB  iTexCoord    = vertex.texcoord[0];\nOUTPUT  oPos         = result.position;\nOUTPUT  oColour      = result.color;\nOUTPUT  oTexCoord0   = result.texcoord[0];\nOUTPUT  oTexCoord1   = result.texcoord[1];\nOUTPUT  oFogCoord    = result.fogcoord;\nPARAM   fogParams    = program.local[0];\nPARAM   waterPlane   = program.local[1];\nPARAM   tMatrix[4]   = { state.matrix.texture[0] };\nPARAM   pMatrix[4]   = { state.matrix.projection };\nPARAM   mvMatrix[4]  = { state.matrix.modelview };\nTEMP    viewPos, viewNormal, fogFactor, colour, ndotl;\nDP4   viewPos.x, mvMatrix[0], iPos;\nDP4   viewPos.y, mvMatrix[1], iPos;\nDP4   viewPos.z, mvMatrix[2], iPos;\nDP4   viewPos.w, mvMatrix[3], iPos;\nSUB   fogFactor.x, viewPos.z, fogParams.x;\nMUL   fogFactor.x, fogFactor.x, 0.001953125;\nMAD   fogFactor.y, iTexCoord.z, fogParams.z, fogParams.w;\nSUB   fogFactor.z, viewPos.z, fogParams.y;\nMUL   fogFactor.z, fogFactor.z, 0.00390625;\nMUL   fogFactor.x, fogFactor.x, fogFactor.y;\nMIN   fogFactor, fogFactor, 1;\nMAX   fogFactor, fogFactor, 0;\nMUL   fogFactor.z, fogFactor.z, iTexCoord.z;\nMAD   viewPos.xyz, waterPlane.xyzw, fogFactor.zzzz, viewPos.xyzw;\nMAX   oTexCoord1.xyz, fogFactor.xxxx, fogFactor.yyyy;\nMOV   oTexCoord1.w, 1;\nDP3   viewNormal.x, mvMatrix[0], iNormal;\nDP3   viewNormal.y, mvMatrix[1], iNormal;\nDP3   viewNormal.z, mvMatrix[2], iNormal;\nDP3   ndotl.x, viewNormal, state.light[0].position;\nDP3   ndotl.y, viewNormal, state.light[1].position;\nMAX   ndotl, ndotl, 0;\nMOV   colour, state.lightmodel.ambient;\nMAD   colour, state.light[0].diffuse, ndotl.xxxx, colour;\nMAD   colour, state.light[1].diffuse, ndotl.yyyy, colour;\nMUL   oColour, iColour, colour;\nDP4   oPos.x, pMatrix[0], viewPos;\nDP4   oPos.y, pMatrix[1], viewPos;\nDP4   oPos.z, pMatrix[2], viewPos;\nDP4   oPos.w, pMatrix[3], viewPos;\nMOV   oFogCoord.x, viewPos.z;\nDP3   oTexCoord0.x, tMatrix[0], iTexCoord;\nDP3   oTexCoord0.y, tMatrix[1], iTexCoord;\nMOV   oTexCoord0.zw, iTexCoord;\nEND\n";
    Class30_Sub2 aClass30_Sub2_6783;
    boolean aBoolean6784 = false;
    Class39 aClass39_6785;
    Class39 aClass39_6786;
    Class39 aClass39_6787;
    static float[] aFloatArray6788 = { 0.0F, -1.0F, 0.0F, 0.0F };

    @Override
    void method512() {
	if (this.aBoolean6780) {
	    OpenGL.glBindProgramARB(34336, 0);
	    OpenGL.glDisable(34820);
	    OpenGL.glDisable(34336);
	    this.aBoolean6780 = false;
	}
	this.aClass_ra_Sub2_491.method5255(1);
	this.aClass_ra_Sub2_491.method5256(null);
	this.aClass_ra_Sub2_491.method5258(8448, 8448);
	this.aClass_ra_Sub2_491.method5259(0, 5890, 768);
	this.aClass_ra_Sub2_491.method5259(2, 34166, 770);
	this.aClass_ra_Sub2_491.method5286(0, 5890, 770);
	this.aClass_ra_Sub2_491.method5255(0);
	if (this.aBoolean6784) {
	    this.aClass_ra_Sub2_491.method5259(0, 5890, 768);
	    this.aClass_ra_Sub2_491.method5286(0, 5890, 770);
	    this.aBoolean6784 = false;
	}
    }

    @Override
    void method506(boolean bool) {
	this.aBoolean6781 = bool;
	this.aClass_ra_Sub2_491.method5255(1);
	this.aClass_ra_Sub2_491.method5256(this.aClass30_Sub2_6783);
	this.aClass_ra_Sub2_491.method5258(34165, 7681);
	this.aClass_ra_Sub2_491.method5259(0, 34166, 768);
	this.aClass_ra_Sub2_491.method5259(2, 5890, 770);
	this.aClass_ra_Sub2_491.method5286(0, 34168, 770);
	this.aClass_ra_Sub2_491.method5255(0);
	method524();
    }

    @Override
    void method507(boolean bool) {
	this.aBoolean6781 = bool;
	this.aClass_ra_Sub2_491.method5255(1);
	this.aClass_ra_Sub2_491.method5256(this.aClass30_Sub2_6783);
	this.aClass_ra_Sub2_491.method5258(34165, 7681);
	this.aClass_ra_Sub2_491.method5259(0, 34166, 768);
	this.aClass_ra_Sub2_491.method5259(2, 5890, 770);
	this.aClass_ra_Sub2_491.method5286(0, 34168, 770);
	this.aClass_ra_Sub2_491.method5255(0);
	method524();
    }

    @Override
    void method518(boolean bool) {
	/* empty */
    }

    @Override
    void method504() {
	if (this.aBoolean6780) {
	    OpenGL.glBindProgramARB(34336, 0);
	    OpenGL.glDisable(34820);
	    OpenGL.glDisable(34336);
	    this.aBoolean6780 = false;
	}
	this.aClass_ra_Sub2_491.method5255(1);
	this.aClass_ra_Sub2_491.method5256(null);
	this.aClass_ra_Sub2_491.method5258(8448, 8448);
	this.aClass_ra_Sub2_491.method5259(0, 5890, 768);
	this.aClass_ra_Sub2_491.method5259(2, 34166, 770);
	this.aClass_ra_Sub2_491.method5286(0, 5890, 770);
	this.aClass_ra_Sub2_491.method5255(0);
	if (this.aBoolean6784) {
	    this.aClass_ra_Sub2_491.method5259(0, 5890, 768);
	    this.aClass_ra_Sub2_491.method5286(0, 5890, 770);
	    this.aBoolean6784 = false;
	}
    }

    @Override
    void method502(int i, int i_0_) {
	/* empty */
    }

    void method523() {
	if (this.aBoolean6780) {
	    float f = (this.aClass_ra_Sub2_491.aFloat8102);
	    float f_1_ = (this.aClass_ra_Sub2_491.aFloat8134);
	    float f_2_ = f - (f - f_1_) * 0.125F;
	    float f_3_ = f - (f - f_1_) * 0.25F;
	    OpenGL.glProgramLocalParameter4fARB(34336, 0, f_3_, f_2_, 256.0F / ((this.aClass_ra_Sub2_491.aClass78_8149.anInt725) * 1996750669), (this.aClass_ra_Sub2_491.aClass78_8149.anInt727) * -1475891183 / 255.0F);
	    this.aClass_ra_Sub2_491.method5255(1);
	    this.aClass_ra_Sub2_491.method5273((this.aClass_ra_Sub2_491.aClass78_8149.anInt726) * -1212608691);
	    this.aClass_ra_Sub2_491.method5255(0);
	}
    }

    @Override
    void method500(Class30 class30, int i) {
	if (class30 == null) {
	    if (!this.aBoolean6784) {
		this.aClass_ra_Sub2_491.method5256(this.aClass_ra_Sub2_491.aClass30_Sub2_8140);
		this.aClass_ra_Sub2_491.method5243(1);
		this.aClass_ra_Sub2_491.method5259(0, 34168, 768);
		this.aClass_ra_Sub2_491.method5286(0, 34168, 770);
		this.aBoolean6784 = true;
	    }
	} else {
	    if (this.aBoolean6784) {
		this.aClass_ra_Sub2_491.method5259(0, 5890, 768);
		this.aClass_ra_Sub2_491.method5286(0, 5890, 770);
		this.aBoolean6784 = false;
	    }
	    this.aClass_ra_Sub2_491.method5256(class30);
	    this.aClass_ra_Sub2_491.method5243(i);
	}
    }

    @Override
    void method509(boolean bool) {
	/* empty */
    }

    @Override
    boolean method501() {
	return this.aBoolean6779;
    }

    @Override
    void method516(int i, int i_4_) {
	/* empty */
    }

    @Override
    void method508(boolean bool) {
	/* empty */
    }

    Class47_Sub5(Class_ra_Sub2 class_ra_sub2) {
	super(class_ra_sub2);
	if (this.aClass_ra_Sub2_491.aBoolean8039) {
	    this.aClass39_6787 = (Class39.method477(this.aClass_ra_Sub2_491, 34336, "!!ARBvp1.0\nATTRIB  iPos         = vertex.position;\nATTRIB  iColour      = vertex.color;\nATTRIB  iTexCoord    = vertex.texcoord[0];\nOUTPUT  oPos         = result.position;\nOUTPUT  oColour      = result.color;\nOUTPUT  oTexCoord0   = result.texcoord[0];\nOUTPUT  oTexCoord1   = result.texcoord[1];\nOUTPUT  oFogCoord    = result.fogcoord;\nPARAM   fogParams    = program.local[0];\nPARAM   waterPlane   = program.local[1];\nPARAM   tMatrix[4]   = { state.matrix.texture[0] };\nPARAM   pMatrix[4]   = { state.matrix.projection };\nPARAM   mvMatrix[4]  = { state.matrix.modelview };\nTEMP    viewPos, fogFactor;\nDP4   viewPos.x, mvMatrix[0], iPos;\nDP4   viewPos.y, mvMatrix[1], iPos;\nDP4   viewPos.z, mvMatrix[2], iPos;\nDP4   viewPos.w, mvMatrix[3], iPos;\nSUB   fogFactor.x, viewPos.z, fogParams.x;\nMUL   fogFactor.x, fogFactor.x, 0.001953125;\nMAD   fogFactor.y, iTexCoord.z, fogParams.z, fogParams.w;\nSUB   fogFactor.z, viewPos.z, fogParams.y;\nMUL   fogFactor.z, fogFactor.z, 0.00390625;\nMUL   fogFactor.x, fogFactor.x, fogFactor.y;\nMIN   fogFactor, fogFactor, 1;\nMAX   fogFactor, fogFactor, 0;\nMUL   fogFactor.z, fogFactor.z, iTexCoord.z;\nMAD   viewPos.xyz, waterPlane.xyzw, fogFactor.zzzz, viewPos.xyzw;\nMAX   oTexCoord1.xyz, fogFactor.xxxx, fogFactor.yyyy;\nMOV   oTexCoord1.w, 1;\nMOV   oColour, iColour;\nDP4   oPos.x, pMatrix[0], viewPos;\nDP4   oPos.y, pMatrix[1], viewPos;\nDP4   oPos.z, pMatrix[2], viewPos;\nDP4   oPos.w, pMatrix[3], viewPos;\nMOV   oFogCoord.x, viewPos.z;\nDP3   oTexCoord0.x, tMatrix[0], iTexCoord;\nDP3   oTexCoord0.y, tMatrix[1], iTexCoord;\nMOV   oTexCoord0.zw, iTexCoord;\nEND\n"));
	    this.aClass39_6786 = (Class39.method477(this.aClass_ra_Sub2_491, 34336, "!!ARBvp1.0\nATTRIB  iPos         = vertex.position;\nATTRIB  iNormal      = vertex.normal;\nATTRIB  iColour      = vertex.color;\nATTRIB  iTexCoord    = vertex.texcoord[0];\nOUTPUT  oPos         = result.position;\nOUTPUT  oColour      = result.color;\nOUTPUT  oTexCoord0   = result.texcoord[0];\nOUTPUT  oTexCoord1   = result.texcoord[1];\nOUTPUT  oFogCoord    = result.fogcoord;\nPARAM   fogParams    = program.local[0];\nPARAM   waterPlane   = program.local[1];\nPARAM   tMatrix[4]   = { state.matrix.texture[0] };\nPARAM   pMatrix[4]   = { state.matrix.projection };\nPARAM   mvMatrix[4]  = { state.matrix.modelview };\nTEMP    viewPos, viewNormal, fogFactor, colour, ndotl;\nDP4   viewPos.x, mvMatrix[0], iPos;\nDP4   viewPos.y, mvMatrix[1], iPos;\nDP4   viewPos.z, mvMatrix[2], iPos;\nDP4   viewPos.w, mvMatrix[3], iPos;\nSUB   fogFactor.x, viewPos.z, fogParams.x;\nMUL   fogFactor.x, fogFactor.x, 0.001953125;\nMAD   fogFactor.y, iTexCoord.z, fogParams.z, fogParams.w;\nSUB   fogFactor.z, viewPos.z, fogParams.y;\nMUL   fogFactor.z, fogFactor.z, 0.00390625;\nMUL   fogFactor.x, fogFactor.x, fogFactor.y;\nMIN   fogFactor, fogFactor, 1;\nMAX   fogFactor, fogFactor, 0;\nMUL   fogFactor.z, fogFactor.z, iTexCoord.z;\nMAD   viewPos.xyz, waterPlane.xyzw, fogFactor.zzzz, viewPos.xyzw;\nMAX   oTexCoord1.xyz, fogFactor.xxxx, fogFactor.yyyy;\nMOV   oTexCoord1.w, 1;\nDP3   viewNormal.x, mvMatrix[0], iNormal;\nDP3   viewNormal.y, mvMatrix[1], iNormal;\nDP3   viewNormal.z, mvMatrix[2], iNormal;\nDP3   ndotl.x, viewNormal, state.light[0].position;\nDP3   ndotl.y, viewNormal, state.light[1].position;\nMAX   ndotl, ndotl, 0;\nMOV   colour, state.lightmodel.ambient;\nMAD   colour, state.light[0].diffuse, ndotl.xxxx, colour;\nMAD   colour, state.light[1].diffuse, ndotl.yyyy, colour;\nMUL   oColour, iColour, colour;\nDP4   oPos.x, pMatrix[0], viewPos;\nDP4   oPos.y, pMatrix[1], viewPos;\nDP4   oPos.z, pMatrix[2], viewPos;\nDP4   oPos.w, pMatrix[3], viewPos;\nMOV   oFogCoord.x, viewPos.z;\nDP3   oTexCoord0.x, tMatrix[0], iTexCoord;\nDP3   oTexCoord0.y, tMatrix[1], iTexCoord;\nMOV   oTexCoord0.zw, iTexCoord;\nEND\n"));
	    this.aClass39_6785 = (Class39.method477(this.aClass_ra_Sub2_491, 34336, "!!ARBvp1.0\nATTRIB  iPos         = vertex.position;\nATTRIB  iColour      = vertex.color;\nATTRIB  iTexCoord    = vertex.texcoord[0];\nOUTPUT  oPos         = result.position;\nOUTPUT  oColour      = result.color;\nOUTPUT  oTexCoord0   = result.texcoord[0];\nOUTPUT  oTexCoord1   = result.texcoord[1];\nOUTPUT  oFogCoord    = result.fogcoord;\nPARAM   fogParams    = program.local[0];\nPARAM   waterPlane   = program.local[1];\nPARAM   pMatrix[4]   = { state.matrix.projection };\nPARAM   mvMatrix[4]  = { state.matrix.modelview };\nPARAM   texMatrix[4] = { state.matrix.texture[0] };\nTEMP    viewPos, fogFactor, depth;\nDP4   viewPos.x, mvMatrix[0], iPos;\nDP4   viewPos.y, mvMatrix[1], iPos;\nDP4   viewPos.z, mvMatrix[2], iPos;\nDP4   viewPos.w, mvMatrix[3], iPos;\nSUB   fogFactor.x, viewPos.z, fogParams.x;\nMUL   fogFactor.x, fogFactor.x, 0.001953125;\nDP4   depth, waterPlane, viewPos;\nMAD   fogFactor.y, -depth, fogParams.z, fogParams.w;\nSUB   fogFactor.z, viewPos.z, fogParams.y;\nMUL   fogFactor.z, fogFactor.z, 0.00390625;\nMIN   fogFactor, fogFactor, 1;\nMAX   fogFactor, fogFactor, 0;\nMUL   fogFactor.z, fogFactor.z, -depth;\nMAD   viewPos.xyz, waterPlane.xyzw, fogFactor.zzzz, viewPos.xyzw;\nMAX   oTexCoord1.xyz, fogFactor.xxxx, fogFactor.yyyy;\nMOV   oTexCoord1.w, 1;\nMOV   oColour, iColour;\nDP4   oPos.x, pMatrix[0], viewPos;\nDP4   oPos.y, pMatrix[1], viewPos;\nDP4   oPos.z, pMatrix[2], viewPos;\nDP4   oPos.w, pMatrix[3], viewPos;\nMOV   oFogCoord.x, viewPos.z;\nDP4   oTexCoord0.x, texMatrix[0], iTexCoord;\nDP4   oTexCoord0.y, texMatrix[1], iTexCoord;\nDP4   oTexCoord0.z, texMatrix[2], iTexCoord;\nMOV   oTexCoord0.w, 1;\nEND\n"));
	    this.aClass39_6777 = (Class39.method477(this.aClass_ra_Sub2_491, 34336, "!!ARBvp1.0\nATTRIB  iPos         = vertex.position;\nATTRIB  iNormal      = vertex.normal;\nATTRIB  iColour      = vertex.color;\nATTRIB  iTexCoord    = vertex.texcoord[0];\nOUTPUT  oPos         = result.position;\nOUTPUT  oColour      = result.color;\nOUTPUT  oTexCoord0   = result.texcoord[0];\nOUTPUT  oTexCoord1   = result.texcoord[1];\nOUTPUT  oFogCoord    = result.fogcoord;\nPARAM   fogParams    = program.local[0];\nPARAM   waterPlane   = program.local[1];\nPARAM   pMatrix[4]   = { state.matrix.projection };\nPARAM   mvMatrix[4]  = { state.matrix.modelview };\nPARAM   texMatrix[4] = { state.matrix.texture[0] };\nTEMP    viewPos, viewNormal, fogFactor, depth, colour, ndotl;\nDP4   viewPos.x, mvMatrix[0], iPos;\nDP4   viewPos.y, mvMatrix[1], iPos;\nDP4   viewPos.z, mvMatrix[2], iPos;\nDP4   viewPos.w, mvMatrix[3], iPos;\nSUB   fogFactor.x, viewPos.z, fogParams.x;\nMUL   fogFactor.x, fogFactor.x, 0.001953125;\nDP4   depth, waterPlane, viewPos;\nMAD   fogFactor.y, -depth, fogParams.z, fogParams.w;\nSUB   fogFactor.z, viewPos.z, fogParams.y;\nMUL   fogFactor.z, fogFactor.z, 0.00390625;\nMIN   fogFactor, fogFactor, 1;\nMAX   fogFactor, fogFactor, 0;\nMUL   fogFactor.z, fogFactor.z, -depth;\nMAD   viewPos.xyz, waterPlane.xyzw, fogFactor.zzzz, viewPos.xyzw;\nMAX   oTexCoord1.xyz, fogFactor.xxxx, fogFactor.yyyy;\nMOV   oTexCoord1.w, 1;\nDP3   viewNormal.x, mvMatrix[0], iNormal;\nDP3   viewNormal.y, mvMatrix[1], iNormal;\nDP3   viewNormal.z, mvMatrix[2], iNormal;\nDP3   ndotl.x, viewNormal, state.light[0].position;\nDP3   ndotl.y, viewNormal, state.light[1].position;\nMAX   ndotl, ndotl, 0;\nMOV   colour, state.lightmodel.ambient;\nMAD   colour, state.light[0].diffuse, ndotl.xxxx, colour;\nMAD   colour, state.light[1].diffuse, ndotl.yyyy, colour;\nMUL   oColour, iColour, colour;\nDP4   oPos.x, pMatrix[0], viewPos;\nDP4   oPos.y, pMatrix[1], viewPos;\nDP4   oPos.z, pMatrix[2], viewPos;\nDP4   oPos.w, pMatrix[3], viewPos;\nMOV   oFogCoord.x, viewPos.z;\nDP4   oTexCoord0.x, texMatrix[0], iTexCoord;\nDP4   oTexCoord0.y, texMatrix[1], iTexCoord;\nDP4   oTexCoord0.z, texMatrix[2], iTexCoord;\nMOV   oTexCoord0.w, 1;\nEND\n"));
	    if (this.aClass39_6787 != null & this.aClass39_6786 != null & this.aClass39_6785 != null & this.aClass39_6777 != null) {
		this.aClass30_Sub2_6783 = new Class30_Sub2(class_ra_sub2, 3553, Class55.aClass55_567, Class77.aClass77_717, 2, 1, false, new byte[] { 0, -1 }, Class55.aClass55_567, false);
		this.aClass30_Sub2_6783.method420(false, false);
		this.aBoolean6779 = true;
	    } else
		this.aBoolean6779 = false;
	} else
	    this.aBoolean6779 = false;
    }

    @Override
    void method510(boolean bool) {
	/* empty */
    }

    @Override
    void method511() {
	if (this.aBoolean6780) {
	    OpenGL.glBindProgramARB(34336, 0);
	    OpenGL.glDisable(34820);
	    OpenGL.glDisable(34336);
	    this.aBoolean6780 = false;
	}
	this.aClass_ra_Sub2_491.method5255(1);
	this.aClass_ra_Sub2_491.method5256(null);
	this.aClass_ra_Sub2_491.method5258(8448, 8448);
	this.aClass_ra_Sub2_491.method5259(0, 5890, 768);
	this.aClass_ra_Sub2_491.method5259(2, 34166, 770);
	this.aClass_ra_Sub2_491.method5286(0, 5890, 770);
	this.aClass_ra_Sub2_491.method5255(0);
	if (this.aBoolean6784) {
	    this.aClass_ra_Sub2_491.method5259(0, 5890, 768);
	    this.aClass_ra_Sub2_491.method5286(0, 5890, 770);
	    this.aBoolean6784 = false;
	}
    }

    @Override
    void method513(int i, int i_5_) {
	/* empty */
    }

    @Override
    void method503(int i, int i_6_) {
	/* empty */
    }

    @Override
    void method515(int i, int i_7_) {
	/* empty */
    }

    @Override
    boolean method520() {
	return this.aBoolean6779;
    }

    @Override
    void method517(int i, int i_8_) {
	/* empty */
    }

    @Override
    void method514(Class30 class30, int i) {
	if (class30 == null) {
	    if (!this.aBoolean6784) {
		this.aClass_ra_Sub2_491.method5256(this.aClass_ra_Sub2_491.aClass30_Sub2_8140);
		this.aClass_ra_Sub2_491.method5243(1);
		this.aClass_ra_Sub2_491.method5259(0, 34168, 768);
		this.aClass_ra_Sub2_491.method5286(0, 34168, 770);
		this.aBoolean6784 = true;
	    }
	} else {
	    if (this.aBoolean6784) {
		this.aClass_ra_Sub2_491.method5259(0, 5890, 768);
		this.aClass_ra_Sub2_491.method5286(0, 5890, 770);
		this.aBoolean6784 = false;
	    }
	    this.aClass_ra_Sub2_491.method5256(class30);
	    this.aClass_ra_Sub2_491.method5243(i);
	}
    }

    @Override
    void method519(Class30 class30, int i) {
	if (class30 == null) {
	    if (!this.aBoolean6784) {
		this.aClass_ra_Sub2_491.method5256(this.aClass_ra_Sub2_491.aClass30_Sub2_8140);
		this.aClass_ra_Sub2_491.method5243(1);
		this.aClass_ra_Sub2_491.method5259(0, 34168, 768);
		this.aClass_ra_Sub2_491.method5286(0, 34168, 770);
		this.aBoolean6784 = true;
	    }
	} else {
	    if (this.aBoolean6784) {
		this.aClass_ra_Sub2_491.method5259(0, 5890, 768);
		this.aClass_ra_Sub2_491.method5286(0, 5890, 770);
		this.aBoolean6784 = false;
	    }
	    this.aClass_ra_Sub2_491.method5256(class30);
	    this.aClass_ra_Sub2_491.method5243(i);
	}
    }

    void method524() {
	Class233 class233 = (this.aClass_ra_Sub2_491.aClass233_8110);
	if (this.aBoolean6781)
	    OpenGL.glBindProgramARB(34336, ((this.aClass_ra_Sub2_491.anInt8155) == 2147483647 ? this.aClass39_6786.anInt430 : this.aClass39_6777.anInt430));
	else
	    OpenGL.glBindProgramARB(34336, ((this.aClass_ra_Sub2_491.anInt8155) == 2147483647 ? this.aClass39_6787.anInt430 : this.aClass39_6785.anInt430));
	float f = this.aClass_ra_Sub2_491.anInt8155;
	float f_9_ = class233.aFloatArray2594[4] * f + class233.aFloatArray2594[12];
	float f_10_ = class233.aFloatArray2594[5] * f + class233.aFloatArray2594[13];
	float f_11_ = class233.aFloatArray2594[6] * f + class233.aFloatArray2594[14];
	aFloatArray6788[0] = -class233.aFloatArray2594[4];
	aFloatArray6788[1] = -class233.aFloatArray2594[5];
	aFloatArray6788[2] = -class233.aFloatArray2594[6];
	aFloatArray6788[3] = -(aFloatArray6788[0] * f_9_ + aFloatArray6788[1] * f_10_ + aFloatArray6788[2] * f_11_);
	OpenGL.glProgramLocalParameter4fARB(34336, 1, aFloatArray6788[0], aFloatArray6788[1], aFloatArray6788[2], aFloatArray6788[3]);
	OpenGL.glEnable(34336);
	this.aBoolean6780 = true;
	method523();
    }

    @Override
    void method505(boolean bool) {
	this.aBoolean6781 = bool;
	this.aClass_ra_Sub2_491.method5255(1);
	this.aClass_ra_Sub2_491.method5256(this.aClass30_Sub2_6783);
	this.aClass_ra_Sub2_491.method5258(34165, 7681);
	this.aClass_ra_Sub2_491.method5259(0, 34166, 768);
	this.aClass_ra_Sub2_491.method5259(2, 5890, 770);
	this.aClass_ra_Sub2_491.method5286(0, 34168, 770);
	this.aClass_ra_Sub2_491.method5255(0);
	method524();
    }
}
