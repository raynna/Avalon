/* Class365 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class365 {
    Class365 aClass365_3956;
    Class235 aClass235_3957;
    Class235 aClass235_3958 = new Class235();
    Class235 aClass235_3959;
    boolean aBoolean3960;
    boolean aBoolean3961;
    Class222 aClass222_3962;
    Class365 aClass365_3963;
    boolean aBoolean3964;
    Class365 aClass365_3965;

    public final Class235 method4337() {
	if (this.aBoolean3964) {
	    this.aBoolean3964 = false;
	    if (this.aClass365_3963 != null) {
		this.aClass235_3957.method2186(this.aClass235_3958);
		this.aClass235_3957.method2187(this.aClass365_3963.method4337());
	    } else
		this.aClass235_3957.method2186(this.aClass235_3958);
	}
	return this.aClass235_3957;
    }

    final Class235 method4338() {
	if (this.aBoolean3960) {
	    this.aBoolean3960 = false;
	    this.aClass235_3959.method2186(method4337());
	    this.aClass235_3959.method2185();
	}
	return this.aClass235_3957;
    }

    final void method4339(Class235 class235) {
	if (this.aClass365_3963 != null) {
	    Class235 class235_0_ = new Class235(class235);
	    class235_0_.method2187(this.aClass365_3963.method4338());
	    method4348(class235_0_);
	} else
	    method4348(class235);
    }

    public final void method4340(Class217 class217) {
	this.aClass235_3958.aClass217_2599.method2013(class217);
	method4344();
	if (this.aClass365_3956 != null)
	    this.aClass365_3956.method4342();
    }

    public final void method4341(float f, float f_1_, float f_2_) {
	this.aClass235_3958.aClass217_2599.method2007(f, f_1_, f_2_);
	method4344();
	if (this.aClass365_3956 != null)
	    this.aClass365_3956.method4342();
    }

    final void method4342() {
	method4344();
	if (this.aClass365_3956 != null)
	    this.aClass365_3956.method4342();
	if (this.aClass365_3965 != null)
	    this.aClass365_3965.method4342();
    }

    public final Class235 method4343() {
	return this.aClass235_3958;
    }

    final void method4344() {
	this.aBoolean3964 = true;
	this.aBoolean3960 = true;
	this.aBoolean3961 = true;
    }

    public final void method4345() {
	if (this.aClass365_3963 != null) {
	    Class365 class365_3_ = this.aClass365_3963.aClass365_3956;
	    if (class365_3_ == this)
		this.aClass365_3963.aClass365_3956 = this.aClass365_3965;
	    else {
		for (/**/; class365_3_.aClass365_3965 != this; class365_3_ = class365_3_.aClass365_3965) {
		    /* empty */
		}
		class365_3_.aClass365_3965 = this.aClass365_3965;
	    }
	}
	method4344();
	if (this.aClass365_3956 != null) {
	    this.aClass365_3956.method4342();
	    Class365 class365_4_ = this.aClass365_3956;
	    for (;;) {
		class365_4_.aClass235_3958.method2187(this.aClass235_3958);
		class365_4_.aClass365_3963 = this.aClass365_3963;
		if (class365_4_.aClass365_3965 == null) {
		    class365_4_.aClass365_3965 = (this.aClass365_3963.aClass365_3956);
		    break;
		}
		class365_4_ = class365_4_.aClass365_3965;
	    }
	    this.aClass365_3963.aClass365_3956 = this.aClass365_3956;
	}
	this.aClass365_3963 = null;
	this.aClass365_3965 = null;
	this.aClass365_3956 = null;
    }

    public final void method4346(Class218 class218) {
	this.aClass235_3958.aClass218_2600.method2022(class218);
	method4344();
	if (this.aClass365_3956 != null)
	    this.aClass365_3956.method4342();
    }

    final Class222 method4347() {
	if (this.aBoolean3961) {
	    this.aBoolean3961 = false;
	    this.aClass222_3962.method2076(method4337());
	}
	return this.aClass222_3962;
    }

    Class365() {
	this.aClass235_3957 = new Class235();
	this.aBoolean3964 = true;
	this.aClass235_3959 = new Class235();
	this.aBoolean3960 = true;
	new Class233();
	new Class233();
	this.aClass222_3962 = new Class222();
	this.aBoolean3961 = true;
	this.aClass365_3963 = null;
	this.aClass365_3956 = null;
	this.aClass365_3965 = null;
    }

    final void method4348(Class235 class235) {
	this.aClass235_3958.method2186(class235);
	method4344();
	if (this.aClass365_3956 != null)
	    this.aClass365_3956.method4342();
    }
}
