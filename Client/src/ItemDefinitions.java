
/* Class468 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.awt.Dimension;
import java.util.Arrays;

public class ItemDefinitions {
    public int anInt5700;
    public static int anInt5701 = 1;
    int anInt5702;
    public int anInt5703;
    public int anInt5704;
    public static int anInt5705 = 2;
    short[] modifiedModelColors;
    short[] oldmodifiedModelColors;
    public String name = "Unknown item";
    byte[] aByteArray5708;
    public int anInt5709;
    int anInt5710;
    short[] originalTextureColors;
    short[] modifiedTextureColors;
    short[] oldoriginalTextureColors;
    short[] oldmodifiedTextureColors;
    public int modelSize = 730923888;
    public int anInt5714 = 0;
    public int anInt5715 = 0;
    public int anInt5716 = 0;
    public int anInt5717 = 0;
    public int anInt5718 = 0;
    public int anInt5719;
    public int anInt5720;
    public int anInt5721;
    public int anInt5722;
    public String[] aStringArray5723;
    public int maleEquipModelId1;
    public int anInt5725;
    public static int anInt5726 = 0;
    public int anInt5727;
    public int anInt5728;
    public int anInt5729;
    public int anInt5730;
    public boolean aBoolean5731;
    public String[] aStringArray5732;
    public int anInt5733;
    public boolean aBoolean5734;
    public int anInt5735;
    public int anInt5736;
    int maleEquipModelId2;
    int invModelId;
    int anInt5739;
    int anInt5740;
    int femaleEquipModelId3;
    int anInt5742;
    int anInt5743;
    int anInt5744;
    int anInt5745;
    int femaleEquipModelId2;
    public int femaleEquipModelId1;
    public int oldMaleEquipModelId1;
    public int oldFemaleEquipModelId1;
    public int oldMaleEquipModelId2;
    public int oldFemaleEquipModelId2;
    public int oldMaleEquipModelId3;
    public int oldFemaleEquipModelId3;
    int anInt5748;
    int anInt5749;
    int anInt5750;
    Class477 aClass477_5751;
    int[] anIntArray5752;
    int[] anIntArray5753;
    short[] originalModelColors;
    short[] oldoriginalModelColors;
    public int anInt5755;
    int anInt5756;
    public static short[] aShortArray5757 = new short[256];
    int anInt5758;
    int maleEquipModelId3;
    int anInt5760;
    int anInt5761;
    int anInt5762;
    public int anInt5763;
    int anInt5764;
    public int anInt5765;
    HashTable aClass437_5766;
    public int[] anIntArray5767;
    public int anInt5768;
    int anInt5769;
    public int anInt5770;
    public boolean aBoolean5771;
    public int anInt5772;
    private byte[] aByteArray5904;
    private byte[] aByteArray5609;
    private int[] aByteIntArray5869;
    private int[] anIntArray5960;
    private int oldInvModelId;

    public void setOldDefs() {
	String name = this.name.toLowerCase();
	if (name.contains("d'hide body") || name.contains("dragonhide body") || name.equals("stripy pirate shirt") || (name.contains("chainbody") && (name.contains("iron") || name.contains("bronze") || name.contains("steel") || name.contains("black") || name.contains("mithril") || name.contains("adamant") || name.contains("rune") || name.contains("white"))) || name.equals("leather body") || name.equals("hardleather body") || name.contains("studded body")) {
	    oldFemaleEquipModelId1 = -1;
	    oldFemaleEquipModelId3 = -1;
	    oldFemaleEquipModelId3 = -1;
	    oldMaleEquipModelId1 = -1;
	    oldMaleEquipModelId3 = -1;
	    oldMaleEquipModelId3 = -1;
	    return;
	}

	if (oldoriginalModelColors != null) {
	    this.originalModelColors = oldoriginalModelColors;
	    this.modifiedModelColors = oldmodifiedModelColors;
	}
	if (oldoriginalTextureColors != null) {
	    this.originalTextureColors = oldoriginalTextureColors;
	    this.modifiedTextureColors = oldmodifiedTextureColors;
	}
    }

    void method6025(int i) {
	try {
	    /* empty */
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("th.a(").append(')').toString());
	}
    }

    void decode(RsByteBuffer class298_sub53) {
	try {
	    for (;;) {
		int i_0_ = class298_sub53.readUnsignedByte();
		if (i_0_ == 0) {
		    break;
		}
		decode(class298_sub53, i_0_, false);
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("th.f(").append(')').toString());
	}
    }

    void method6027(ItemDefinitions class468_1_, ItemDefinitions class468_2_, int i) {
	try {
	    this.invModelId = class468_1_.invModelId * 1;
	    modelSize = class468_1_.modelSize * 1;
	    anInt5714 = class468_1_.anInt5714 * 1;
	    anInt5715 = class468_1_.anInt5715 * 1;
	    anInt5716 = 1 * class468_1_.anInt5716;
	    anInt5717 = class468_1_.anInt5717 * 1;
	    anInt5718 = class468_1_.anInt5718 * 1;
	    this.originalModelColors = class468_1_.originalModelColors;
	    this.modifiedModelColors = class468_1_.modifiedModelColors;
	    this.aByteArray5708 = class468_1_.aByteArray5708;
	    this.originalTextureColors = class468_1_.originalTextureColors;
	    this.modifiedTextureColors = class468_1_.modifiedTextureColors;
	    name = class468_2_.name;
	    aBoolean5731 = class468_2_.aBoolean5731;
	    anInt5721 = class468_2_.anInt5721 * 1;
	    anInt5704 = 126481113;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("th.p(").append(')').toString());
	}
    }

    public final Model method6028(boolean bool, Class464 class464, int i) {
	try {
	    int i_3_;
	    int i_4_;
	    int i_5_;
	    if (bool) {
		if (class464 != null && class464.anIntArray5695 != null) {
		    i_3_ = class464.anIntArray5695[0];
		    i_4_ = class464.anIntArray5695[1];
		    i_5_ = class464.anIntArray5695[2];
		} else {
		    i_3_ = Class477.OLD_ITEMS && oldFemaleEquipModelId1 != -1 ? oldFemaleEquipModelId1 : femaleEquipModelId1 * 1585491093;
		    i_4_ = Class477.OLD_ITEMS && oldFemaleEquipModelId2 != -1 ? oldFemaleEquipModelId2 : this.femaleEquipModelId2 * -1284247975;
		    i_5_ = Class477.OLD_ITEMS && oldFemaleEquipModelId3 != -1 ? oldFemaleEquipModelId3 : this.femaleEquipModelId3 * -1767718263;
		}
	    } else if (class464 != null && null != class464.anIntArray5694) {
		i_3_ = class464.anIntArray5694[0];
		i_4_ = class464.anIntArray5694[1];
		i_5_ = class464.anIntArray5694[2];
	    } else {
		i_3_ = Class477.OLD_ITEMS && oldMaleEquipModelId1 != -1 ? oldMaleEquipModelId1 : maleEquipModelId1 * 1343198193;
		i_4_ = Class477.OLD_ITEMS && oldMaleEquipModelId2 != -1 ? oldMaleEquipModelId2 : this.maleEquipModelId2 * 34210967;
		i_5_ = Class477.OLD_ITEMS && oldMaleEquipModelId3 != -1 ? oldMaleEquipModelId3 : this.maleEquipModelId3 * 1313278521;
	    }
	    if (i_3_ == -1)
		return null;
	    Model class64 = Model.method751(((this.aClass477_5751).aClass243_5992), i_3_, 0);
	    if (class64 == null)
		return null;
	    if (class64.anInt630 < 13)
		class64.method755(2);
	    if (i_4_ != -1) {
		Model class64_6_ = Model.method751((this.aClass477_5751.aClass243_5992), i_4_, 0);
		if (class64_6_.anInt630 < 13)
		    class64_6_.method755(2);
		if (i_5_ != -1) {
		    Model class64_7_ = Model.method751((this.aClass477_5751.aClass243_5992), i_5_, 0);
		    if (class64_7_.anInt630 < 13)
			class64_7_.method755(2);
		    Model[] class64s = { class64, class64_6_, class64_7_ };
		    class64 = new Model(class64s, 3);
		} else {
		    Model[] class64s = { class64, class64_6_ };
		    class64 = new Model(class64s, 2);
		}
	    }

	    if (!bool && (0 != this.anInt5742 * -1268579363 || 345393423 * this.anInt5744 != 0 || 0 != this.anInt5748 * 669576861))
		class64.method753(this.anInt5742 * -1268579363, 345393423 * this.anInt5744, 669576861 * this.anInt5748);
	    if (bool && (0 != -1436808331 * this.anInt5743 || -92251131 * this.anInt5745 != 0 || this.anInt5710 * 1813909637 != 0))
		class64.method753(this.anInt5743 * -1436808331, this.anInt5745 * -92251131, 1813909637 * this.anInt5710);
	    if (null != this.originalModelColors) {
		short[] is;
		if (class464 != null && class464.aShortArray5698 != null)
		    is = class464.aShortArray5698;
		else
		    is = this.modifiedModelColors;
		for (int i_8_ = 0; i_8_ < this.originalModelColors.length; i_8_++)
		    class64.method756(this.originalModelColors[i_8_], is[i_8_]);
	    }
	    if (null != this.originalTextureColors) {
		short[] is;
		if (class464 != null && null != class464.aShortArray5699)
		    is = class464.aShortArray5699;
		else
		    is = this.modifiedTextureColors;
		for (int i_9_ = 0; i_9_ < this.originalTextureColors.length; i_9_++)
		    class64.method752(this.originalTextureColors[i_9_], is[i_9_]);
	    }
	    return class64;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("th.z(").append(')').toString());
	}
    }

    public final Class387 method6029(GraphicsToolkit class_ra, int i, int i_10_, Class366 class366, Class438 class438, int i_11_, int i_12_, int i_13_, int i_14_, int i_15_) {
	try {
	    if (null != this.anIntArray5752 && i_10_ > 1) {
		int i_16_ = -1;
		for (int i_17_ = 0; i_17_ < 10; i_17_++) {
		    if (i_10_ >= this.anIntArray5753[i_17_] && 0 != this.anIntArray5753[i_17_])
			i_16_ = this.anIntArray5752[i_17_];
		}
		if (i_16_ != -1)
		    return (this.aClass477_5751.getItemDefinitions(i_16_).method6029(class_ra, i, 1, class366, class438, i_11_, i_12_, i_13_, i_14_, 2041211835));
	    }
	    int i_18_ = i;
	    if (null != class438)
		i_18_ |= class438.method5829(-1790708337);
	    Class387 class387;
	    synchronized (this.aClass477_5751.aClass348_5994) {
		class387 = ((Class387) (this.aClass477_5751.aClass348_5994.method4184(1027112447 * this.anInt5740 | class_ra.anInt5298 * 580915349 << 29)));
	    }
	    if (null == class387 || class_ra.method5017(class387.m(), i_18_) != 0) {
		if (null != class387)
		    i_18_ = class_ra.method5004(i_18_, class387.m());
		int i_19_ = i_18_;
		if (this.originalTextureColors != null)
		    i_19_ |= 0x8000;
		if (null != this.originalModelColors || class366 != null)
		    i_19_ |= 0x4000;
		if (128 != this.anInt5758 * -1773084507)
		    i_19_ |= 0x1;
		if (128 != this.anInt5758 * -1773084507)
		    i_19_ |= 0x2;
		if (this.anInt5758 * -1773084507 != 128)
		    i_19_ |= 0x4;
		Model class64 = Model.method751((this.aClass477_5751.aClass243_5992),

		Class477.OLD_ITEMS && oldInvModelId != -1 ? oldInvModelId : (this.invModelId * 381556007), 0);
		if (null == class64)
		    return null;
		if (class64.anInt630 < 13)
		    class64.method755(2);
		class387 = (class_ra.method5037(class64, i_19_, (this.aClass477_5751.anInt5991) * -424819635, 64 + 856370373 * this.anInt5761, this.anInt5762 * 1055603853 + 850));
		if (-1773084507 * this.anInt5758 != 128 || 128 != this.anInt5702 * 902366341 || 128 != -230848851 * this.anInt5739)
		    class387.oa(-1773084507 * this.anInt5758, 902366341 * this.anInt5702, this.anInt5739 * -230848851);
		if (this.originalModelColors != null) {
		    for (int i_20_ = 0; i_20_ < this.originalModelColors.length; i_20_++) {
			if (this.aByteArray5708 != null && i_20_ < this.aByteArray5708.length)
			    class387.X((this.originalModelColors[i_20_]), aShortArray5757[(this.aByteArray5708[i_20_]) & 0xff]);
			else
			    class387.X((this.originalModelColors[i_20_]), (this.modifiedModelColors[i_20_]));
		    }
		}
		if (null != this.originalTextureColors) {
		    for (int i_21_ = 0; i_21_ < this.originalTextureColors.length; i_21_++)
			class387.W(this.originalTextureColors[i_21_], this.modifiedTextureColors[i_21_]);
		}
		if (null != class366) {
		    for (int i_22_ = 0; i_22_ < 10; i_22_++) {
			for (int i_23_ = 0; (i_23_ < Class366.aShortArrayArray3970[i_22_].length); i_23_++) {
			    if (class366.anIntArray3973[i_22_] < (Class22.aShortArrayArrayArray278[i_22_][i_23_]).length)
				class387.X((Class366.aShortArrayArray3970[i_22_][i_23_]), (Class22.aShortArrayArrayArray278[i_22_][i_23_][class366.anIntArray3973[i_22_]]));
			}
		    }
		}
		class387.KA(i_18_);
		synchronized (this.aClass477_5751.aClass348_5994) {
		    this.aClass477_5751.aClass348_5994.method4194(class387, this.anInt5740 * 1027112447 | class_ra.anInt5298 * 580915349 << 29);
		}
	    }
	    if (null != class438 || 0 != i_14_) {
		class387 = class387.method4755((byte) 1, i_18_, true);
		if (null != class438)
		    class438.method5839(class387, 0, -1330952412);
		if (0 != i_14_)
		    class387.PA(i_11_, i_12_, i_13_, i_14_);
	    }
	    class387.KA(i);
	    return class387;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("th.d(").append(')').toString());
	}
    }

    public ItemDefinitions method6030(int i, int i_24_) {
	try {
	    if (null != this.anIntArray5752 && i > 1) {
		int i_25_ = -1;
		for (int i_26_ = 0; i_26_ < 10; i_26_++) {
		    if (i >= this.anIntArray5753[i_26_] && 0 != this.anIntArray5753[i_26_])
			i_25_ = this.anIntArray5752[i_26_];
		}
		if (-1 != i_25_)
		    return this.aClass477_5751.getItemDefinitions(i_25_);
	    }
	    return this;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("th.u(").append(')').toString());
	}
    }

    int[] method6031(GraphicsToolkit class_ra, GraphicsToolkit class_ra_27_, int i, int i_28_, int i_29_, boolean bool, int i_30_, Class264 class264, Class366 class366, int i_31_) {
	// class_ra beeing used to draw
	try {
	    Model class64 = Model.method751((this.aClass477_5751.aClass243_5992), Class477.OLD_ITEMS && oldInvModelId != -1 ? oldInvModelId : 381556007 * this.invModelId, 0);
	    if (null == class64)
		return null;
	    if (class64.anInt630 < 13)
		class64.method755(2);
	    if (null != this.originalModelColors) {
		for (int i_32_ = 0; i_32_ < this.originalModelColors.length; i_32_++) {
		    if (this.aByteArray5708 != null && i_32_ < this.aByteArray5708.length)
			class64.method756((this.originalModelColors[i_32_]), aShortArray5757[(this.aByteArray5708[i_32_]) & 0xff]);
		    else
			class64.method756((this.originalModelColors[i_32_]), (this.modifiedModelColors[i_32_]));
		}
	    }
	    if (null != this.originalTextureColors) {
		for (int i_33_ = 0; i_33_ < this.originalTextureColors.length; i_33_++)
		    class64.method752(this.originalTextureColors[i_33_], (this.modifiedTextureColors[i_33_]));
	    }
	    if (null != class366) {
		for (int i_34_ = 0; i_34_ < 10; i_34_++) {
		    for (int i_35_ = 0; i_35_ < Class366.aShortArrayArray3970[i_34_].length; i_35_++) {
			if (class366.anIntArray3973[i_34_] < (Class22.aShortArrayArrayArray278[i_34_][i_35_]).length)
			    class64.method756((Class366.aShortArrayArray3970[i_34_][i_35_]), (Class22.aShortArrayArrayArray278[i_34_][i_35_][(class366.anIntArray3973[i_34_])]));
		    }
		}
	    }
	    int i_36_ = 2048;
	    boolean bool_37_ = false;
	    if (-1773084507 * this.anInt5758 != 128 || 128 != 902366341 * this.anInt5702 || this.anInt5739 * -230848851 != 128) {
		bool_37_ = true;
		i_36_ |= 0x7;
	    }
	    Class387 class387 = class_ra.method5037(class64, i_36_, 64, (this.anInt5761 * 856370373 + 64), 768 + (this.anInt5762 * 1055603853));
	    if (!class387.method4743())
		return null;
	    if (bool_37_)
		class387.oa(-1773084507 * this.anInt5758, 902366341 * this.anInt5702, this.anInt5739 * -230848851);
	    Class57 class57 = null;
	    if (-1 != -1673957995 * anInt5755) {
		class57 = (this.aClass477_5751.method6086(class_ra, class_ra_27_, 809765849 * anInt5709, 10, 1, 0, true, true, 0, class264, class366, -1795675060));
		if (null == class57)
		    return null;
	    } else if (465190555 * anInt5719 != -1) {
		class57 = (this.aClass477_5751.method6086(class_ra, class_ra_27_, this.anInt5756 * -783380935, i, i_28_, i_29_, false, true, 0, class264, class366, 1124646536));
		if (null == class57)
		    return null;
	    } else if (-1 != anInt5770 * 2083650097) {
		class57 = (this.aClass477_5751.method6086(class_ra, class_ra_27_, 1981372535 * this.anInt5769, i, i_28_, i_29_, false, true, 0, class264, class366, 311548691));
		if (null == class57)
		    return null;
	    }
	    int i_38_;
	    if (bool)
		i_38_ = (int) (1396167403 * modelSize * 1.5) << 2;
	    else if (2 == i_28_)
		i_38_ = (int) (1396167403 * modelSize * 1.04) << 2;
	    else
		i_38_ = modelSize * 1396167403 << 2;
	    Class233 class233 = class_ra.method5045();
	    Class233 class233_39_ = class_ra.method5036();
	    class233_39_.method2152(16.0F, 16.0F, 512.0F, 512.0F, 50.0F, 2.14748365E9F, class_ra.method4992((short) -14576).method545(), class_ra.method4992((short) -3255).method552());
	    class_ra.method5182(class233_39_);
	    class_ra.method5187(0, 0, class_ra.method4992((short) 19509).method545(), class_ra.method4992((short) 21763).method552());
	    Class222 class222 = new Class222();
	    class_ra.method5043(class222);
	    class_ra.IA(0.95F + (float) (Math.random() / 10.0));
	    class_ra.m(16777215, 0.95F + (float) (Math.random() / 10.0), 0.95F + (float) (Math.random() / 10.0), -50.0F, -10.0F, -50.0F);
	    Class222 class222_40_ = class_ra.method5178(); // draws it seems
	    class222_40_.method2059(0.0F, 0.0F, 1.0F, Class220.method2049(-(anInt5716 * -1368639199) << 3));
	    class222_40_.method2071(0.0F, 1.0F, 0.0F, Class220.method2049(anInt5715 * -3355859 << 3));
	    class222_40_.method2064(252709809 * anInt5717 << 2, (((Class220.anIntArray2483[330519029 * anInt5714 << 3]) * i_38_) >> 14) - class387.YA() / 2 + (-1811316489 * anInt5718 << 2), (((Class220.anIntArray2474[330519029 * anInt5714 << 3]) * i_38_) >> 14) + (anInt5718 * -1811316489 << 2));
	    class222_40_.method2071(1.0F, 0.0F, 0.0F, Class220.method2049((anInt5714 * 330519029) << 3));
	    class_ra.ba(2, 0);
	    class_ra.L();
	    class_ra.B(0, 0, 36, 32, 0, 0);
	    class387.method4739(class222_40_, null, 1);
	    class_ra.method5182(class233);
	    int[] is = class_ra.aq(0, 0, 36, 32);
	    if (i_28_ >= 1) {
		is = method6032(is, -16777214, 224723616);
		if (i_28_ >= 2)
		    is = method6032(is, -1, 1899522127);
	    }
	    if (0 != i_29_)
		method6033(is, i_29_, 1738452390);
	    if (-1 != 465190555 * anInt5719)
		class57.method645(0, 0);
	    else if (-1 != anInt5770 * 2083650097)
		class57.method645(0, 0);
	    class_ra.method5031(is, 0, 36, 36, 32, -1432690829).method645(0, 0);
	    if (-1 != anInt5755 * -1673957995)
		class57.method645(0, 0);
	    if (1 == i_30_ || 2 == i_30_ && (anInt5704 * 789409129 == 1 || 1 != i) && i != -1)
		class264.method2488(Class108.AmountValues(i, ((this.aClass477_5751).aClass429_5997), -2134002342), 0, 9, -256, -16777215, 1072909030);
	    is = class_ra.aq(0, 0, 36, 32);
	    for (int i_41_ = 0; i_41_ < is.length; i_41_++) {
		if (0 == (is[i_41_] & 0xffffff))
		    is[i_41_] = 0;
		else
		    is[i_41_] |= ~0xffffff;
	    }
	    return is;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("th.x(").append(')').toString());
	}
    }

    int[] method6032(int[] is, int i, int i_42_) {
	try {
	    int[] is_43_ = new int[1152];
	    int i_44_ = 0;
	    for (int i_45_ = 0; i_45_ < 32; i_45_++) {
		for (int i_46_ = 0; i_46_ < 36; i_46_++) {
		    int i_47_ = is[i_44_];
		    if (i_47_ == 0) {
			if (i_46_ > 0 && 0 != is[i_44_ - 1])
			    i_47_ = i;
			else if (i_45_ > 0 && is[i_44_ - 36] != 0)
			    i_47_ = i;
			else if (i_46_ < 35 && 0 != is[1 + i_44_])
			    i_47_ = i;
			else if (i_45_ < 31 && 0 != is[i_44_ + 36])
			    i_47_ = i;
		    }
		    is_43_[i_44_++] = i_47_;
		}
	    }
	    return is_43_;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("th.r(").append(')').toString());
	}
    }

    void method6033(int[] is, int i, int i_48_) {
	try {
	    for (int i_49_ = 31; i_49_ > 0; i_49_--) {
		int i_50_ = i_49_ * 36;
		for (int i_51_ = 35; i_51_ > 0; i_51_--) {
		    if (is[i_51_ + i_50_] == 0 && 0 != is[i_50_ + i_51_ - 1 - 36])
			is[i_50_ + i_51_] = i;
		}
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("th.q(").append(')').toString());
	}
    }

    public final boolean method6034(boolean bool, Class464 class464, byte i) {
	try {
	    int i_52_;
	    int i_53_;
	    int i_54_;
	    if (bool) {
		if (class464 != null && class464.anIntArray5695 != null) {
		    i_52_ = class464.anIntArray5695[0];
		    i_53_ = class464.anIntArray5695[1];
		    i_54_ = class464.anIntArray5695[2];
		} else {
		    i_52_ = Class477.OLD_ITEMS && oldFemaleEquipModelId1 != -1 ? oldFemaleEquipModelId1 : femaleEquipModelId1 * 1585491093;
		    i_53_ = Class477.OLD_ITEMS && oldFemaleEquipModelId2 != -1 ? oldFemaleEquipModelId2 : this.femaleEquipModelId2 * -1284247975;
		    i_54_ = Class477.OLD_ITEMS && oldFemaleEquipModelId3 != -1 ? oldFemaleEquipModelId3 : this.femaleEquipModelId3 * -1767718263;
		}
	    } else if (class464 != null && null != class464.anIntArray5694) {
		i_52_ = class464.anIntArray5694[0];
		i_53_ = class464.anIntArray5694[1];
		i_54_ = class464.anIntArray5694[2];
	    } else {
		i_52_ = Class477.OLD_ITEMS && oldMaleEquipModelId1 != -1 ? oldMaleEquipModelId1 : maleEquipModelId1 * 1343198193;
		i_53_ = Class477.OLD_ITEMS && oldMaleEquipModelId2 != -1 ? oldMaleEquipModelId2 : this.maleEquipModelId2 * 34210967;
		i_54_ = Class477.OLD_ITEMS && oldMaleEquipModelId3 != -1 ? oldMaleEquipModelId3 : this.maleEquipModelId3 * 1313278521;
	    }
	    if (-1 == i_52_)
		return true;
	    boolean bool_55_ = true;
	    if (!this.aClass477_5751.aClass243_5992.method2290(i_52_, 0, -870541215))
		bool_55_ = false;
	    if (i_53_ != -1 && !this.aClass477_5751.aClass243_5992.method2290(i_53_, 0, -1713048725))
		bool_55_ = false;
	    if (i_54_ != -1 && !this.aClass477_5751.aClass243_5992.method2290(i_54_, 0, -818152674))
		bool_55_ = false;
	    return bool_55_;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("th.s(").append(')').toString());
	}
    }

    public final boolean method6035(boolean bool, Class464 class464, byte i) {
	try {
	    int i_56_;
	    int i_57_;
	    if (bool) {
		if (null != class464 && class464.anIntArray5697 != null) {
		    i_56_ = class464.anIntArray5697[0];
		    i_57_ = class464.anIntArray5697[1];
		} else {
		    i_56_ = -1531415419 * this.anInt5750;
		    i_57_ = this.anInt5764 * 1578724433;
		}
	    } else if (null != class464 && null != class464.anIntArray5690) {
		i_56_ = class464.anIntArray5690[0];
		i_57_ = class464.anIntArray5690[1];
	    } else {
		i_56_ = this.anInt5760 * -1282951055;
		i_57_ = this.anInt5749 * 86274879;
	    }
	    if (i_56_ == -1)
		return true;
	    boolean bool_58_ = true;
	    if (!this.aClass477_5751.aClass243_5992.method2290(i_56_, 0, -1403829212))
		bool_58_ = false;
	    if (-1 != i_57_ && !this.aClass477_5751.aClass243_5992.method2290(i_57_, 0, -1542572465))
		bool_58_ = false;
	    return bool_58_;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("th.y(").append(')').toString());
	}
    }

    void method6036(ItemDefinitions class468_59_, ItemDefinitions class468_60_, int i) {
	try {
	    anInt5721 = 0;
	    this.invModelId = 1 * class468_59_.invModelId;
	    modelSize = class468_59_.modelSize * 1;
	    anInt5714 = class468_59_.anInt5714 * 1;
	    anInt5715 = 1 * class468_59_.anInt5715;
	    anInt5716 = 1 * class468_59_.anInt5716;
	    anInt5717 = class468_59_.anInt5717 * 1;
	    anInt5718 = class468_59_.anInt5718 * 1;
	    this.originalModelColors = class468_60_.originalModelColors;
	    this.modifiedModelColors = class468_60_.modifiedModelColors;
	    this.aByteArray5708 = class468_60_.aByteArray5708;
	    this.originalTextureColors = class468_60_.originalTextureColors;
	    this.modifiedTextureColors = class468_60_.modifiedTextureColors;
	    name = class468_60_.name;
	    aBoolean5731 = class468_60_.aBoolean5731;
	    anInt5733 = class468_60_.anInt5733 * 1;
	    anInt5772 = class468_60_.anInt5772 * 1;
	    anInt5735 = class468_60_.anInt5735 * 1;
	    maleEquipModelId1 = 1 * class468_60_.maleEquipModelId1;
	    this.maleEquipModelId2 = 1 * class468_60_.maleEquipModelId2;
	    this.maleEquipModelId3 = class468_60_.maleEquipModelId3 * 1;
	    femaleEquipModelId1 = 1 * class468_60_.femaleEquipModelId1;
	    this.femaleEquipModelId2 = class468_60_.femaleEquipModelId2 * 1;
	    this.femaleEquipModelId3 = 1 * class468_60_.femaleEquipModelId3;
	    this.anInt5742 = 1 * class468_60_.anInt5742;
	    this.anInt5743 = 1 * class468_60_.anInt5743;
	    this.anInt5744 = 1 * class468_60_.anInt5744;
	    this.anInt5745 = class468_60_.anInt5745 * 1;
	    this.anInt5748 = class468_60_.anInt5748 * 1;
	    this.anInt5710 = 1 * class468_60_.anInt5710;
	    this.anInt5760 = class468_60_.anInt5760 * 1;
	    this.anInt5749 = class468_60_.anInt5749 * 1;
	    this.anInt5750 = class468_60_.anInt5750 * 1;
	    this.anInt5764 = class468_60_.anInt5764 * 1;
	    anInt5700 = 1 * class468_60_.anInt5700;
	    aStringArray5723 = class468_60_.aStringArray5723;
	    this.aClass437_5766 = class468_60_.aClass437_5766;
	    anInt5704 = 1 * class468_60_.anInt5704;
	    aStringArray5732 = new String[5];
	    if (null != class468_60_.aStringArray5732) {
		for (int i_61_ = 0; i_61_ < 4; i_61_++)
		    aStringArray5732[i_61_] = class468_60_.aStringArray5732[i_61_];
	    }
	    aStringArray5732[4] = Tradution.aClass470_5903.method6049(((this.aClass477_5751).aClass429_5997), -875414210);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("th.k(").append(')').toString());
	}
    }

    public int method6037(int i, int i_62_, int i_63_) {
	try {
	    if (null == this.aClass437_5766)
		return i_62_;
	    Class298_Sub35 class298_sub35 = ((Class298_Sub35) this.aClass437_5766.method5812(i));
	    if (null == class298_sub35)
		return i_62_;
	    return class298_sub35.anInt7394 * -774922497;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("th.h(").append(')').toString());
	}
    }

    void decode(RsByteBuffer class298_sub53, boolean oldItems) {
	try {
	    for (;;) {
		int i_0_ = class298_sub53.readUnsignedByte();
		if (i_0_ == 0) {
		    break;
		}
		decode(class298_sub53, i_0_, oldItems);
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("th.f(").append(')').toString());
	}
    }

    void decode(RsByteBuffer stream, int opcode, boolean oldItems) {
	try {
	    if (1 == opcode)
		this.invModelId = (oldItems ? stream.readCustomUnsignedShort() : stream.readBigSmart(1235052657)) * -1019082089;
	    else if (2 == opcode) {
		name = stream.readString(1338906243);// name
	    } else if (opcode == 4)
		modelSize = stream.readUnsignedShort() * -1885125181;
	    else if (5 == opcode)
		anInt5714 = stream.readUnsignedShort() * -2068311459;
	    else if (6 == opcode)
		anInt5715 = stream.readUnsignedShort() * -629359451;
	    else if (7 == opcode) {
		anInt5717 = stream.readUnsignedShort() * -1497539247;
		if (anInt5717 * 252709809 > 32767)
		    anInt5717 -= 1565589504;
	    } else if (opcode == 8) {
		anInt5718 = stream.readUnsignedShort() * -572964665;
		if (-1811316489 * anInt5718 > 32767)
		    anInt5718 -= 1086783488;
	    } else if (opcode == 11)
		anInt5704 = 126481113;
	    else if (12 == opcode)
		anInt5721 = stream.readInt((byte) -4) * -1375390745;
	    else if (opcode == 13)
		anInt5733 = stream.readUnsignedByte() * -2026784241;
	    else if (14 == opcode)
		anInt5772 = stream.readUnsignedByte() * -583083477;
	    else if (16 == opcode)
		aBoolean5731 = true;
	    else if (18 == opcode)
		anInt5720 = stream.readUnsignedShort() * -1741576309;
	    else if (opcode == 23)
		maleEquipModelId1 = (oldItems ? stream.readCustomUnsignedShort() : stream.readBigSmart(1235052657)) * -1612514031;
	    else if (opcode == 24)
		this.maleEquipModelId2 = (oldItems ? stream.readCustomUnsignedShort() : stream.readBigSmart(1235052657)) * 582597415;
	    else if (25 == opcode)
		femaleEquipModelId1 = (oldItems ? stream.readCustomUnsignedShort() : stream.readBigSmart(1235052657)) * -1430077763;
	    else if (26 == opcode)
		this.femaleEquipModelId2 = (oldItems ? stream.readCustomUnsignedShort() : stream.readBigSmart(1235052657)) * -336299543;
	    else if (opcode == 27)
		anInt5735 = stream.readUnsignedByte() * -1438605935;
	    else if (opcode >= 30 && opcode < 35)
		aStringArray5723[opcode - 30] = stream.readString(2111111360);
	    else if (opcode >= 35 && opcode < 40)
		aStringArray5732[opcode - 35] = stream.readString(-101192414);
	    else if (40 == opcode) {
		int i_65_ = stream.readUnsignedByte();
		this.originalModelColors = new short[i_65_];
		this.modifiedModelColors = new short[i_65_];
		for (int i_66_ = 0; i_66_ < i_65_; i_66_++) {
		    this.originalModelColors[i_66_] = (short) stream.readUnsignedShort();
		    this.modifiedModelColors[i_66_] = (short) stream.readUnsignedShort();
		}
	    } else if (41 == opcode) {
		int i_67_ = stream.readUnsignedByte();
		this.originalTextureColors = new short[i_67_];
		this.modifiedTextureColors = new short[i_67_];
		for (int i_68_ = 0; i_68_ < i_67_; i_68_++) {
		    this.originalTextureColors[i_68_] = (short) stream.readUnsignedShort();
		    this.modifiedTextureColors[i_68_] = (short) stream.readUnsignedShort();
		}
	    } else if (opcode == 42) {
		int i_69_ = stream.readUnsignedByte();
		this.aByteArray5708 = new byte[i_69_];
		for (int i_70_ = 0; i_70_ < i_69_; i_70_++)
		    this.aByteArray5708[i_70_] = stream.readByte(-12558881);
	    } else if (43 == opcode) {
		anInt5763 = stream.readInt((byte) 6) * 682064309;
		aBoolean5771 = true;
	    } else if (44 == opcode) {
		int i_92_ = (short) stream.readUnsignedShort();
		int i_93_ = 0;
		for (int i_94_ = i_92_; i_94_ > 0; i_94_ >>= 1)
		    i_93_++;
		aByteArray5609 = new byte[i_93_];
		byte i_95_ = 0;
		for (int i_96_ = 0; i_96_ < i_93_; i_96_++) {
		    if ((i_92_ & 1 << i_96_) > 0) {
			aByteArray5609[i_96_] = i_95_;
			i_95_++;
		    } else
			aByteArray5609[i_96_] = (byte) -1;
		}
	    } else if (45 == opcode) {
		int i_97_ = (short) stream.readUnsignedShort();
		int i_98_ = 0;
		for (int i_99_ = i_97_; i_99_ > 0; i_99_ >>= 1)
		    i_98_++;
		aByteArray5904 = new byte[i_98_];
		byte i_100_ = 0;
		for (int i_101_ = 0; i_101_ < i_98_; i_101_++) {
		    if ((i_97_ & 1 << i_101_) > 0) {
			aByteArray5904[i_101_] = i_100_;
			i_100_++;
		    } else
			aByteArray5904[i_101_] = (byte) -1;
		}
	    } else if (65 == opcode)
		aBoolean5734 = true;
	    else if (78 == opcode)
		this.maleEquipModelId3 = (oldItems ? stream.readCustomUnsignedShort() : stream.readBigSmart(1235052657)) * 756401161;
	    else if (opcode == 79)
		this.femaleEquipModelId3 = (oldItems ? stream.readCustomUnsignedShort() : stream.readBigSmart(1235052657)) * 2098680761;
	    else if (opcode == 90)
		this.anInt5760 = (oldItems ? stream.readCustomUnsignedShort() : stream.readBigSmart(1235052657)) * 1319962769;
	    else if (opcode == 91)
		this.anInt5750 = (oldItems ? stream.readCustomUnsignedShort() : stream.readBigSmart(1235052657)) * 1598907469;
	    else if (opcode == 92)
		this.anInt5749 = (oldItems ? stream.readCustomUnsignedShort() : stream.readBigSmart(1235052657)) * -1935344449;
	    else if (93 == opcode)
		this.anInt5764 = this.anInt5764 = (oldItems ? stream.readCustomUnsignedShort() : stream.readBigSmart(1235052657)) * -1194369871;
	    else if (95 == opcode)
		anInt5716 = stream.readUnsignedShort() * -883520799;
	    else if (96 == opcode)
		anInt5765 = stream.readUnsignedByte() * 135187667;
	    else if (opcode == 97)
		anInt5709 = stream.readUnsignedShort() * -384973719;
	    else if (98 == opcode)
		anInt5755 = stream.readUnsignedShort() * 1831957949;
	    else if (opcode >= 100 && opcode < 110) {
		if (this.anIntArray5752 == null) {
		    this.anIntArray5752 = new int[10];
		    this.anIntArray5753 = new int[10];
		}
		this.anIntArray5752[opcode - 100] = stream.readUnsignedShort();
		this.anIntArray5753[opcode - 100] = stream.readUnsignedShort();
	    } else if (opcode == 110)
		this.anInt5758 = stream.readUnsignedShort() * -237762771;
	    else if (111 == opcode)
		this.anInt5702 = stream.readUnsignedShort() * 355882061;
	    else if (opcode == 112)
		this.anInt5739 = stream.readUnsignedShort() * 1219480869;
	    else if (opcode == 113)
		this.anInt5761 = stream.readByte(-12558881) * 712208909;
	    else if (opcode == 114)
		this.anInt5762 = stream.readByte(-12558881) * -1050247335;
	    else if (115 == opcode) {
		anInt5700 = stream.readUnsignedByte() * 1325490629;
	    }  else if (121 == opcode)
		this.anInt5756 = stream.readUnsignedShort() * -970653687;
	    else if (122 == opcode)
		anInt5719 = stream.readUnsignedShort() * 2015131539;
	    else if (opcode == 125) {
		this.anInt5742 = ((stream.readByte(-12558881) << 2) * -1833538443);
		this.anInt5744 = (stream.readByte(-12558881) << 2) * -25983505;
		this.anInt5748 = (stream.readByte(-12558881) << 2) * 1243416501;
	    } else if (opcode == 126) {
		this.anInt5743 = ((stream.readByte(-12558881) << 2) * -1039520547);
		this.anInt5745 = (stream.readByte(-12558881) << 2) * 1803423949;
		this.anInt5710 = (stream.readByte(-12558881) << 2) * 519268429;
	    } else if (opcode == 127) {
		anInt5729 = stream.readUnsignedByte() * 947829311;
		anInt5725 = stream.readUnsignedShort() * 300082645;
	    } else if (128 == opcode) {
		anInt5730 = stream.readUnsignedByte() * -1036837039;
		anInt5703 = stream.readUnsignedShort() * 1829509431;
	    } else if (129 == opcode) {
		anInt5736 = stream.readUnsignedByte() * -2098727417;
		anInt5727 = stream.readUnsignedShort() * -48114927;
	    } else if (opcode == 130) {
		anInt5722 = stream.readUnsignedByte() * -969618575;
		anInt5728 = stream.readUnsignedShort() * -1531140281;
	    } else if (132 == opcode) {
		int i_71_ = stream.readUnsignedByte();
		anIntArray5767 = new int[i_71_];
		for (int i_72_ = 0; i_72_ < i_71_; i_72_++)
		    anIntArray5767[i_72_] = stream.readUnsignedShort();
	    } else if (134 == opcode)
		anInt5768 = stream.readUnsignedByte() * 1394978643;
	    else if (139 == opcode)
		this.anInt5769 = stream.readUnsignedShort() * 257950023;
	    else if (opcode == 140)
		anInt5770 = stream.readUnsignedShort() * 78716625;
	    else if (opcode >= 142 && opcode < 147) {
		if (aByteIntArray5869 == null) {
		    aByteIntArray5869 = new int[6];
		    Arrays.fill(aByteIntArray5869, -1);
		}
		aByteIntArray5869[opcode - 142] = (short) stream.readUnsignedShort();
	    } else if (opcode >= 150 && opcode < 155) {
		if (null == anIntArray5960) {
		    anIntArray5960 = new int[5];
		    Arrays.fill(anIntArray5960, -1);
		}
		anIntArray5960[opcode - 150] = (short) stream.readUnsignedShort();
	    } else if (249 == opcode) {
		int i_73_ = stream.readUnsignedByte();
		if (null == this.aClass437_5766) {
		    int i_74_ = Class416.method5590(i_73_, (byte) 16);
		    this.aClass437_5766 = new HashTable(i_74_);
		}
		for (int i_75_ = 0; i_75_ < i_73_; i_75_++) {// client script
							     // map
		    boolean bool = stream.readUnsignedByte() == 1;
		    int i_76_ = stream.read24BitUnsignedInteger((byte) 15);
		    Class298 class298;
		    if (bool)
			class298 = new Class298_Sub29(stream.readString(-1208778402));
		    else
			class298 = new Class298_Sub35(stream.readInt((byte) 69));
		    this.aClass437_5766.method5817(class298, i_76_);
		}
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("th.b(").append(')').toString());
	}
    }

    void method6039(ItemDefinitions class468_77_, ItemDefinitions class468_78_, int i) {
	try {
	    anInt5721 = 0;
	    this.invModelId = 1 * class468_77_.invModelId;
	    modelSize = class468_77_.modelSize * 1;
	    anInt5714 = 1 * class468_77_.anInt5714;
	    anInt5715 = class468_77_.anInt5715 * 1;
	    anInt5716 = class468_77_.anInt5716 * 1;
	    anInt5717 = 1 * class468_77_.anInt5717;
	    anInt5718 = 1 * class468_77_.anInt5718;
	    this.originalModelColors = class468_78_.originalModelColors;
	    this.modifiedModelColors = class468_78_.modifiedModelColors;
	    this.aByteArray5708 = class468_78_.aByteArray5708;
	    this.originalTextureColors = class468_78_.originalTextureColors;
	    this.modifiedTextureColors = class468_78_.modifiedTextureColors;
	    name = class468_78_.name;
	    aBoolean5731 = class468_78_.aBoolean5731;
	    anInt5733 = 1 * class468_78_.anInt5733;
	    anInt5772 = class468_78_.anInt5772 * 1;
	    anInt5735 = class468_78_.anInt5735 * 1;
	    maleEquipModelId1 = class468_78_.maleEquipModelId1 * 1;
	    this.maleEquipModelId2 = class468_78_.maleEquipModelId2 * 1;
	    this.maleEquipModelId3 = 1 * class468_78_.maleEquipModelId3;
	    femaleEquipModelId1 = 1 * class468_78_.femaleEquipModelId1;
	    this.femaleEquipModelId2 = class468_78_.femaleEquipModelId2 * 1;
	    this.femaleEquipModelId3 = class468_78_.femaleEquipModelId3 * 1;
	    this.anInt5742 = class468_78_.anInt5742 * 1;
	    this.anInt5743 = 1 * class468_78_.anInt5743;
	    this.anInt5744 = class468_78_.anInt5744 * 1;
	    this.anInt5745 = 1 * class468_78_.anInt5745;
	    this.anInt5748 = 1 * class468_78_.anInt5748;
	    this.anInt5710 = 1 * class468_78_.anInt5710;
	    this.anInt5760 = 1 * class468_78_.anInt5760;
	    this.anInt5749 = 1 * class468_78_.anInt5749;
	    this.anInt5750 = 1 * class468_78_.anInt5750;
	    this.anInt5764 = 1 * class468_78_.anInt5764;
	    anInt5700 = 1 * class468_78_.anInt5700;
	    aStringArray5723 = class468_78_.aStringArray5723;
	    this.aClass437_5766 = class468_78_.aClass437_5766;
	    aStringArray5732 = new String[5];
	    if (class468_78_.aStringArray5732 != null) {
		for (int i_79_ = 0; i_79_ < 4; i_79_++)
		    aStringArray5732[i_79_] = class468_78_.aStringArray5732[i_79_];
	    }
	    aStringArray5732[4] = Tradution.aClass470_5855.method6049(((this.aClass477_5751).aClass429_5997), -875414210);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("th.i(").append(')').toString());
	}
    }

    public final Model method6040(boolean bool, Class464 class464, int i) {
	try {
	    int i_80_;
	    int i_81_;
	    if (bool) {
		if (class464 != null && null != class464.anIntArray5697) {
		    i_80_ = class464.anIntArray5697[0];
		    i_81_ = class464.anIntArray5697[1];
		} else {
		    i_80_ = this.anInt5750 * -1531415419;
		    i_81_ = 1578724433 * this.anInt5764;
		}
	    } else if (null != class464 && null != class464.anIntArray5690) {
		i_80_ = class464.anIntArray5690[0];
		i_81_ = class464.anIntArray5690[1];
	    } else {
		i_80_ = -1282951055 * this.anInt5760;
		i_81_ = this.anInt5749 * 86274879;
	    }
	    if (-1 == i_80_)
		return null;
	    Model class64 = Model.method751(((this.aClass477_5751).aClass243_5992), i_80_, 0);
	    if (class64.anInt630 < 13)
		class64.method755(2);
	    if (-1 != i_81_) {
		Model class64_82_ = Model.method751((this.aClass477_5751.aClass243_5992), i_81_, 0);
		if (class64_82_.anInt630 < 13)
		    class64_82_.method755(2);
		Model[] class64s = { class64, class64_82_ };
		class64 = new Model(class64s, 2);
	    }
	    if (null != this.originalModelColors) {
		short[] is;
		if (class464 != null && null != class464.aShortArray5698)
		    is = class464.aShortArray5698;
		else
		    is = this.modifiedModelColors;
		for (int i_83_ = 0; i_83_ < this.originalModelColors.length; i_83_++)
		    class64.method756(this.originalModelColors[i_83_], is[i_83_]);
	    }
	    if (null != this.originalTextureColors) {
		short[] is;
		if (null != class464 && class464.aShortArray5699 != null)
		    is = class464.aShortArray5699;
		else
		    is = this.modifiedTextureColors;
		for (int i_84_ = 0; i_84_ < this.originalTextureColors.length; i_84_++)
		    class64.method752(this.originalTextureColors[i_84_], is[i_84_]);
	    }
	    return class64;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("th.t(").append(')').toString());
	}
    }

    public String method6041(int i, String string, short i_85_) {
	try {
	    if (this.aClass437_5766 == null)
		return string;
	    Class298_Sub29 class298_sub29 = ((Class298_Sub29) this.aClass437_5766.method5812(i));
	    if (class298_sub29 == null)
		return string;
	    return (String) class298_sub29.anObject7366;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("th.v(").append(')').toString());
	}
    }

    int id;

    ItemDefinitions() {
	anInt5704 = 0;
	anInt5720 = 1741576309;
	anInt5721 = -1375390745;
	aBoolean5731 = false;
	anInt5725 = -300082645;
	anInt5703 = -1829509431;
	anInt5727 = 48114927;
	anInt5728 = 1531140281;
	anInt5729 = -947829311;
	anInt5730 = 1036837039;
	anInt5736 = 2098727417;
	anInt5722 = 969618575;
	anInt5733 = 2026784241;
	anInt5772 = 583083477;
	anInt5735 = 1438605935;
	maleEquipModelId1 = 1612514031;
	this.maleEquipModelId2 = -582597415;
	femaleEquipModelId1 = 1430077763;
	oldMaleEquipModelId1 = -1;
	oldFemaleEquipModelId1 = -1;
	oldMaleEquipModelId2 = -1;
	oldFemaleEquipModelId2 = -1;
	oldMaleEquipModelId3 = -1;
	oldFemaleEquipModelId3 = -1;
	oldInvModelId = -1;
	this.femaleEquipModelId2 = 336299543;
	this.maleEquipModelId3 = -756401161;
	this.femaleEquipModelId3 = -2098680761;
	this.anInt5742 = 0;
	this.anInt5743 = 0;
	this.anInt5744 = 0;
	this.anInt5745 = 0;
	this.anInt5748 = 0;
	this.anInt5710 = 0;
	this.anInt5760 = -1319962769;
	this.anInt5749 = 1935344449;
	this.anInt5750 = -1598907469;
	this.anInt5764 = 1194369871;
	anInt5709 = 384973719;
	anInt5755 = -1831957949;
	this.anInt5756 = 970653687;
	anInt5719 = -2015131539;
	this.anInt5758 = -368863616;
	this.anInt5702 = -1691736448;
	this.anInt5739 = 1474728576;
	this.anInt5761 = 0;
	this.anInt5762 = 0;
	anInt5700 = 0;
	aBoolean5734 = false;
	anInt5765 = 0;
	anInt5768 = 0;
	this.anInt5769 = -257950023;
	anInt5770 = -78716625;
	aBoolean5771 = false;
    }

    static final void method6042(int i, int i_86_, int i_87_) {
	try {
	    if (Class301.aFloat3233 < Class301.aFloat3234) {
		Class301.aFloat3233 += Class301.aFloat3233 / 30.0;
		if (Class301.aFloat3233 > Class301.aFloat3234)
		    Class301.aFloat3233 = Class301.aFloat3234;
		Class225.method2103(65536);
		Class301.anInt3254 = (int) Class301.aFloat3233 >> 1;
		Class301.aByteArrayArrayArray3229 = IcmpService_Sub1.method6381(Class301.anInt3254, 818170582);
	    } else if (Class301.aFloat3233 > Class301.aFloat3234) {
		Class301.aFloat3233 -= Class301.aFloat3233 / 30.0;
		if (Class301.aFloat3233 < Class301.aFloat3234)
		    Class301.aFloat3233 = Class301.aFloat3234;
		Class225.method2103(65536);
		Class301.anInt3254 = (int) Class301.aFloat3233 >> 1;
		Class301.aByteArrayArrayArray3229 = IcmpService_Sub1.method6381(Class301.anInt3254, 1742182898);
	    }
	    if (Class301_Sub1.anInt7632 * -1198160439 != -1 && -1 != -19739017 * Class301_Sub1.anInt7627) {
		int i_88_ = (-1198160439 * Class301_Sub1.anInt7632 - Class82_Sub14.anInt6875 * 1196508279);
		if (i_88_ < 2 || i_88_ > 2)
		    i_88_ /= 8;
		int i_89_ = (Class301_Sub1.anInt7627 * -19739017 - Class376.anInt4090 * 1882038855);
		if (i_89_ < 2 || i_89_ > 2)
		    i_89_ /= 8;
		Class82_Sub14.anInt6875 = ((i_88_ + Class82_Sub14.anInt6875 * 1196508279) * -2076584633);
		Class376.anInt4090 = (i_89_ + 1882038855 * Class376.anInt4090) * -435591305;
		if (i_88_ == 0 && i_89_ == 0) {
		    Class301_Sub1.anInt7632 = 433609607;
		    Class301_Sub1.anInt7627 = 789877945;
		}
		Class225.method2103(65536);
	    }
	    if (Class137_Sub1.anInt6991 * -1983068885 > 0) {
		Class88.anInt810 -= -1077128129;
		if (0 == Class88.anInt810 * -1581933633) {
		    Class137_Sub1.anInt6991 -= -63057533;
		    Class88.anInt810 = -338630500;
		}
	    } else {
		Class301_Sub1.anInt7633 = -2138103821;
		Class301_Sub1.anInt7630 = -1998014133;
	    }
	    if (Class301_Sub1.aBoolean7628 && Class476.aClass453_5986 != null) {
		for (Class298_Sub6 class298_sub6 = ((Class298_Sub6) Class476.aClass453_5986.method5939(1766612795)); null != class298_sub6; class298_sub6 = (Class298_Sub6) Class476.aClass453_5986.method5944(49146)) {
		    Class352 class352 = (Class301.aClass339_3251.method4116(-530122905 * (class298_sub6.aClass298_Sub52_7206.anInt7608), -1186797555));
		    if (class298_sub6.method2862(i, i_86_, (byte) 19)) {
			if (class352.aStringArray3778 != null) {
			    if (null != class352.aStringArray3778[4])
				Class234.method2174(class352.aStringArray3778[4], class352.aString3789, -1, 1012, -1, (class298_sub6.aClass298_Sub52_7206.anInt7608) * -530122905, class352.anInt3817 * -804513353, 0, true, false, (class298_sub6.aClass298_Sub52_7206.anInt7608) * -530122905, false, -1324353170);
			    if (class352.aStringArray3778[3] != null)
				Class234.method2174(class352.aStringArray3778[3], class352.aString3789, -1, 1011, -1, -530122905 * (class298_sub6.aClass298_Sub52_7206.anInt7608), class352.anInt3817 * -804513353, 0, true, false, (class298_sub6.aClass298_Sub52_7206.anInt7608) * -530122905, false, -1509752920);
			    if (class352.aStringArray3778[2] != null)
				Class234.method2174(class352.aStringArray3778[2], class352.aString3789, -1, 1010, -1, (class298_sub6.aClass298_Sub52_7206.anInt7608) * -530122905, class352.anInt3817 * -804513353, 0, true, false, -530122905 * (class298_sub6.aClass298_Sub52_7206.anInt7608), false, -1074248386);
			    if (class352.aStringArray3778[1] != null)
				Class234.method2174(class352.aStringArray3778[1], class352.aString3789, -1, 1009, -1, -530122905 * (class298_sub6.aClass298_Sub52_7206.anInt7608), class352.anInt3817 * -804513353, 0, true, false, (class298_sub6.aClass298_Sub52_7206.anInt7608) * -530122905, false, -1424985903);
			    if (null != class352.aStringArray3778[0])
				Class234.method2174(class352.aStringArray3778[0], class352.aString3789, -1, 1008, -1, (class298_sub6.aClass298_Sub52_7206.anInt7608) * -530122905, class352.anInt3817 * -804513353, 0, true, false, (class298_sub6.aClass298_Sub52_7206.anInt7608) * -530122905, false, -2019134484);
			}
			if (!class298_sub6.aClass298_Sub52_7206.aBoolean7611) {
			    class298_sub6.aClass298_Sub52_7206.aBoolean7611 = true;
			    Class126.method1405(Class502.aClass502_6727, -530122905 * (class298_sub6.aClass298_Sub52_7206.anInt7608), class352.anInt3817 * -804513353, 20917601);
			}
			if (class298_sub6.aClass298_Sub52_7206.aBoolean7611)
			    Class126.method1405(Class502.aClass502_6721, -530122905 * (class298_sub6.aClass298_Sub52_7206.anInt7608), class352.anInt3817 * -804513353, -1586023399);
		    } else if (class298_sub6.aClass298_Sub52_7206.aBoolean7611) {
			class298_sub6.aClass298_Sub52_7206.aBoolean7611 = false;
			Class126.method1405(Class502.aClass502_6718, (-530122905 * (class298_sub6.aClass298_Sub52_7206.anInt7608)), -804513353 * class352.anInt3817, -1187185253);
		    }
		}
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("th.bz(").append(')').toString());
	}
    }

    public static int method6043(int i, int i_90_, int i_91_) {
	try {
	    if (HashTable.aClass371_5520.anInt4035 * -1919698893 == -1)
		return 1;
	    if (i != Class422_Sub25.aClass298_Sub48_8425.aClass422_Sub14_7571.method5677(-2106508116)) {
		Class371.method4584(i, (Tradution.aClass470_5789.method6049(Class321.aClass429_3357, -875414210)), true, (byte) 53);
		if (Class422_Sub25.aClass298_Sub48_8425.aClass422_Sub14_7571.method5677(-1001808225) != i)
		    return -1;
	    }
	    int i_92_;
	    try {
		Dimension dimension = Class52_Sub2_Sub1.aCanvas9079.getSize();
		Class139.method1548(Tradution.aClass470_5789.method6049(Class321.aClass429_3357, -875414210), true, Class373.aClass_ra4071, Class343.aClass264_3673, Class422_Sub2.aClass505_8369, (byte) 5);
		Model class64 = Model.method751(Class341.aClass243_3646, (-1919698893 * HashTable.aClass371_5520.anInt4035), 0);
		long l = Class122.method1319((byte) 1);
		Class373.aClass_ra4071.L();
		client.aClass222_8871.method2062(0.0F, 256.0F, 0.0F);
		Class373.aClass_ra4071.method5043(client.aClass222_8871);
		Class233 class233 = Class373.aClass_ra4071.method5036();
		class233.method2152(dimension.width / 2, dimension.height / 2, 512.0F, 512.0F, client.aClass283_8716.method2634((byte) -121), client.aClass283_8716.method2635(1789119116), dimension.width, dimension.height);
		Class373.aClass_ra4071.method5182(class233);
		Class373.aClass_ra4071.IA(1.0F);
		Class373.aClass_ra4071.m(16777215, 0.5F, 0.5F, 20.0F, -50.0F, 30.0F);
		Class387 class387 = Class373.aClass_ra4071.method5037(class64, 2048, 64, 64, 768);
		int i_93_ = 0;
		while_107_: for (int i_94_ = 0; i_94_ < 500; i_94_++) {
		    Class373.aClass_ra4071.ba(3, 0);
		    for (int i_95_ = 15; i_95_ >= 0; i_95_--) {
			for (int i_96_ = 0; i_96_ <= i_95_; i_96_++) {
			    client.aClass222_8968.method2062((int) (512.0F * (i_96_ - i_95_ / 2.0F)), 0.0F, (1 + i_95_) * 512);
			    class387.method4739(client.aClass222_8968, null, 0);
			    i_93_++;
			    if (Class122.method1319((byte) 1) - l >= i_90_) {
				if (i_91_ >= 1017103058)
				    throw new IllegalStateException();
				break while_107_;
			    }
			}
		    }
		}
		Class373.aClass_ra4071.method5075();
		long l_97_ = (i_93_ * 1000 / (Class122.method1319((byte) 1) - l));
		Class373.aClass_ra4071.ba(3, 0);
		i_92_ = (int) l_97_;
	    }
	    catch (Throwable throwable) {
		throwable.printStackTrace();
		return -1;
	    }
	    return i_92_;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("th.gt(").append(')').toString());
	}
    }
}
