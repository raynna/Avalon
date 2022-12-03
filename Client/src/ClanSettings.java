/* Class162 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class ClanSettings {
    public int clanLeaderID;
    boolean useUserHashes;
    boolean useDisplayNames;
    int[] anIntArray1652;
    int nextUpdateNumber = 0;
    public String clanName = null;
    int anInt1655 = 0;
    public boolean aBoolean1656;
    public byte[] membersRanks;
    public byte aByte1658;
    public byte aByte1659;
    public byte aByte1660;
    public int membersCount;
    long[] membersHashes;
    long aLong1663;
    HashTable configurations;
    int[] anIntArray1665;
    public int[] anIntArray1666;
    long[] bannedMembersHashes;
    public String[] bannedMembersDisplayNames;
    public int bannedMembersCount;
    public int deputyOwnerWithLeaderPriorityID;
    public String[] membersDisplayNames;
    public byte aByte1672;
    static byte aByte1673 = 126;
    static byte aByte1674 = 127;
    static int anInt1675 = 5;
    static byte aByte1676 = 125;
    static byte aByte1677 = 0;
    public static byte aByte1678 = -1;
    static int baseTileX;
    public static int anInt1680;

    void setMembersBufferSize(int i, byte i_0_) {
	try {
	    if (this.useUserHashes) {
		if (this.membersHashes != null)
		    Class425.method5738(this.membersHashes, 0, (this.membersHashes = new long[i]), 0, membersCount * 2125429757);
		else
		    this.membersHashes = new long[i];
	    }
	    if (this.useDisplayNames) {
		if (membersDisplayNames != null)
		    Class425.arrayCopy(membersDisplayNames, 0, membersDisplayNames = new String[i], 0, membersCount * 2125429757);
		else
		    membersDisplayNames = new String[i];
	    }
	    if (membersRanks != null)
		Class425.method5736(membersRanks, 0, membersRanks = new byte[i], 0, 2125429757 * membersCount);
	    else
		membersRanks = new byte[i];
	    if (this.anIntArray1665 != null)
		Class425.method5741(this.anIntArray1665, 0, (this.anIntArray1665 = new int[i]), 0, 2125429757 * membersCount);
	    else
		this.anIntArray1665 = new int[i];
	    if (anIntArray1666 != null)
		Class425.method5741(anIntArray1666, 0, anIntArray1666 = new int[i], 0, 2125429757 * membersCount);
	    else
		anIntArray1666 = new int[i];
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("gp.a(").append(')').toString());
	}
    }

    void setChattersBufferSize(int size) {
	try {
	    if (this.useUserHashes) {
		if (this.bannedMembersHashes != null)
		    Class425.method5738(this.bannedMembersHashes, 0, (this.bannedMembersHashes = new long[size]), 0, -1102843797 * bannedMembersCount);
		else
		    this.bannedMembersHashes = new long[size];
	    }
	    if (this.useDisplayNames) {
		if (null != bannedMembersDisplayNames)
		    Class425.arrayCopy(bannedMembersDisplayNames, 0, bannedMembersDisplayNames = new String[size], 0, -1102843797 * bannedMembersCount);
		else
		    bannedMembersDisplayNames = new String[size];
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("gp.f(").append(')').toString());
	}
    }

    public int getMemberID(String displayName, int i) {
	try {
	    if (displayName == null || displayName.length() == 0)
		return -1;
	    for (int i_2_ = 0; i_2_ < membersCount * 2125429757; i_2_++) {
		if (membersDisplayNames[i_2_].equals(displayName))
		    return i_2_;
	    }
	    return -1;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("gp.b(").append(')').toString());
	}
    }

    public int method1752(int i, int i_3_, int i_4_, int i_5_) {
	try {
	    int i_6_ = 31 == i_4_ ? -1 : (1 << i_4_ + 1) - 1;
	    return (this.anIntArray1665[i] & i_6_) >>> i_3_;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("gp.p(").append(')').toString());
	}
    }

    public Integer method1753(int i, byte i_7_) {
	try {
	    if (null == this.configurations)
		return null;
	    Class298 class298 = this.configurations.method5812(i);
	    if (null == class298 || !(class298 instanceof Class298_Sub35))
		return null;
	    return new Integer(((Class298_Sub35) class298).anInt7394 * -774922497);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("gp.i(").append(')').toString());
	}
    }

    public Integer method1754(int i, int i_8_, int i_9_, int i_10_) {
	try {
	    if (this.configurations == null)
		return null;
	    Class298 class298 = this.configurations.method5812(i);
	    if (null == class298 || !(class298 instanceof Class298_Sub35))
		return null;
	    int i_11_ = i_9_ == 31 ? -1 : (1 << 1 + i_9_) - 1;
	    return new Integer(((((Class298_Sub35) class298).anInt7394 * -774922497) & i_11_) >>> i_8_);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("gp.k(").append(')').toString());
	}
    }

    public String method1755(int i, int i_12_) {
	try {
	    if (null == this.configurations)
		return null;
	    Class298 class298 = this.configurations.method5812(i);
	    if (class298 == null || !(class298 instanceof Class298_Sub29))
		return null;
	    return (String) ((Class298_Sub29) class298).anObject7366;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("gp.u(").append(')').toString());
	}
    }

    public int[] method1756(byte i) {
	try {
	    if (null == this.anIntArray1652) {
		String[] strings = new String[2125429757 * membersCount];
		this.anIntArray1652 = new int[membersCount * 2125429757];
		for (int i_13_ = 0; i_13_ < 2125429757 * membersCount; i_13_++) {
		    strings[i_13_] = membersDisplayNames[i_13_];
		    this.anIntArray1652[i_13_] = i_13_;
		}
		Class298_Sub32_Sub32.method3345(strings, (this.anIntArray1652), -43867253);
	    }
	    return this.anIntArray1652;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("gp.x(").append(')').toString());
	}
    }

    void addMember(String name, long hash, int i, int i_14_) {
	try {
	    if (name != null && name.length() == 0)
		name = null;
	    if (this.useUserHashes != hash > 0L)
		throw new RuntimeException("");
	    if ((name != null) != this.useDisplayNames)
		throw new RuntimeException("");
	    if (hash > 0L && (this.membersHashes == null || (membersCount * 2125429757 >= this.membersHashes.length)) || (name != null && (membersDisplayNames == null || 2125429757 * membersCount >= membersDisplayNames.length)))
		setMembersBufferSize(5 + 2125429757 * membersCount, (byte) 1);
	    if (null != this.membersHashes)
		this.membersHashes[2125429757 * membersCount] = hash;
	    if (null != membersDisplayNames)
		membersDisplayNames[2125429757 * membersCount] = name;
	    if (-1 == clanLeaderID * -2079715533) {
		clanLeaderID = membersCount * -1008400369;
		membersRanks[membersCount * 2125429757] = (byte) 126;
	    } else
		membersRanks[2125429757 * membersCount] = (byte) 0;
	    this.anIntArray1665[membersCount * 2125429757] = 0;
	    anIntArray1666[2125429757 * membersCount] = i;
	    membersCount += -397756075;
	    this.anIntArray1652 = null;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("gp.r(").append(')').toString());
	}
    }

    void assignLeaders() {
	try {
	    if (0 == membersCount * 2125429757) {
		clanLeaderID = -2050262011;
		deputyOwnerWithLeaderPriorityID = 1697252921;
	    } else {
		clanLeaderID = -2050262011;
		deputyOwnerWithLeaderPriorityID = 1697252921;
		int highestRankMemberID = 0;
		byte highestMemberRank = membersRanks[0];
		for (int memberID = 1; memberID < 2125429757 * membersCount; memberID++) {
		    if (membersRanks[memberID] > highestMemberRank) {
			if (highestMemberRank == 125)
			    deputyOwnerWithLeaderPriorityID = highestRankMemberID * -1697252921;
			highestRankMemberID = memberID;
			highestMemberRank = membersRanks[memberID];
		    } else if (-1 == 873199607 * deputyOwnerWithLeaderPriorityID && membersRanks[memberID] == 125)
			deputyOwnerWithLeaderPriorityID = -1697252921 * memberID;
		}
		clanLeaderID = highestRankMemberID * 2050262011;
		if (-1 != -2079715533 * clanLeaderID)
		    membersRanks[-2079715533 * clanLeaderID] = (byte) 126;
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("gp.n(").append(')').toString());
	}
    }

    void addBannedMember(long l, String string, int i) {
	try {
	    if (null != string && string.length() == 0)
		string = null;
	    if (this.useUserHashes != l > 0L)
		throw new RuntimeException("");
	    if ((string != null) != this.useDisplayNames)
		throw new RuntimeException("");
	    if (l > 0L && (this.bannedMembersHashes == null || (-1102843797 * bannedMembersCount >= this.bannedMembersHashes.length)) || string != null && (bannedMembersDisplayNames == null || (-1102843797 * bannedMembersCount >= bannedMembersDisplayNames.length)))
		setChattersBufferSize(5 + bannedMembersCount * -1102843797);
	    if (null != this.bannedMembersHashes)
		this.bannedMembersHashes[bannedMembersCount * -1102843797] = l;
	    if (null != bannedMembersDisplayNames)
		bannedMembersDisplayNames[-1102843797 * bannedMembersCount] = string;
	    bannedMembersCount += 324877379;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("gp.s(").append(')').toString());
	}
    }

    boolean method1760(int i, String string, byte i_18_) {
	try {
	    if (null == string)
		string = "";
	    else if (string.length() > 80)
		string = string.substring(0, 80);
	    if (this.configurations != null) {
		Class298 class298 = this.configurations.method5812(i);
		if (class298 != null) {
		    if (class298 instanceof Class298_Sub29) {
			Class298_Sub29 class298_sub29 = (Class298_Sub29) class298;
			if (class298_sub29.anObject7366 instanceof String) {
			    if (string.equals(class298_sub29.anObject7366))
				return false;
			    class298_sub29.method2839(-1460969981);
			    class298_sub29.method2839(-1460969981);
			    this.configurations.method5817(new Class298_Sub29(string), (7051297995265073167L * class298_sub29.hash));
			    return true;
			}
		    }
		    class298.method2839(-1460969981);
		}
	    } else
		this.configurations = new HashTable(4);
	    this.configurations.method5817(new Class298_Sub29(string), i);
	    return true;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("gp.e(").append(')').toString());
	}
    }

    boolean method1761(int i, int i_19_, byte i_20_) {
	try {
	    if (this.configurations != null) {
		Class298 class298 = this.configurations.method5812(i);
		if (class298 != null) {
		    if (class298 instanceof Class298_Sub35) {
			Class298_Sub35 class298_sub35 = (Class298_Sub35) class298;
			if (i_19_ == class298_sub35.anInt7394 * -774922497)
			    return false;
			class298_sub35.anInt7394 = i_19_ * -898670337;
			return true;
		    }
		    class298.method2839(-1460969981);
		}
	    } else
		this.configurations = new HashTable(4);
	    this.configurations.method5817(new Class298_Sub35(i_19_), i);
	    return true;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("gp.h(").append(')').toString());
	}
    }

    boolean method1762(int i, int i_21_, int i_22_, int i_23_, int i_24_) {
	try {
	    int i_25_ = (1 << i_22_) - 1;
	    int i_26_ = i_23_ == 31 ? -1 : (1 << i_23_ + 1) - 1;
	    int i_27_ = i_26_ ^ i_25_;
	    i_21_ <<= i_22_;
	    i_21_ &= i_27_;
	    if (this.configurations != null) {
		Class298 class298 = this.configurations.method5812(i);
		if (class298 != null) {
		    if (class298 instanceof Class298_Sub35) {
			Class298_Sub35 class298_sub35 = (Class298_Sub35) class298;
			if (i_21_ == (class298_sub35.anInt7394 * -774922497 & i_27_))
			    return false;
			Class298_Sub35 class298_sub35_28_;
			(class298_sub35_28_ = class298_sub35).anInt7394 = (-774922497 * class298_sub35_28_.anInt7394 & (i_27_ ^ 0xffffffff)) * -898670337;
			Class298_Sub35 class298_sub35_29_;
			(class298_sub35_29_ = class298_sub35).anInt7394 = (-898670337 * (-774922497 * class298_sub35_29_.anInt7394 | i_21_));
			return true;
		    }
		    class298.method2839(-1460969981);
		}
	    } else
		this.configurations = new HashTable(4);
	    this.configurations.method5817(new Class298_Sub35(i_21_), i);
	    return true;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("gp.v(").append(')').toString());
	}
    }

    boolean method1763(int i, long l) {
	try {
	    if (this.configurations != null) {
		Class298 class298 = this.configurations.method5812(i);
		if (class298 != null) {
		    if (class298 instanceof Class298_Sub33) {
			Class298_Sub33 class298_sub33 = (Class298_Sub33) class298;
			if (-959724544626478745L * class298_sub33.aLong7385 == l)
			    return false;
			class298_sub33.aLong7385 = 2132790236050020951L * l;
			return true;
		    }
		    class298.method2839(-1460969981);
		}
	    } else
		this.configurations = new HashTable(4);
	    this.configurations.method5817(new Class298_Sub33(l), i);
	    return true;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("gp.g(").append(')').toString());
	}
    }

    void decode(RsByteBuffer stream, byte i) {
	try {
	    int version = stream.readUnsignedByte();
	    if (version < 1 || version > 5)
		throw new RuntimeException("Unsupported ClanSettings version: " + version);
	    int masks = stream.readUnsignedByte();
	    if (0 != (masks & 0x1))
		this.useUserHashes = true; // read name as
					   // longs
	    if (0 != (masks & 0x2))
		this.useDisplayNames = true; // read name as
					     // strings
	    if (!this.useUserHashes) {
		this.membersHashes = null;
		this.bannedMembersHashes = null;
	    }
	    if (!this.useDisplayNames) {
		membersDisplayNames = null;
		bannedMembersDisplayNames = null;
	    }
	    this.nextUpdateNumber = stream.readInt((byte) -76) * 1441392111;
	    this.anInt1655 = stream.readInt((byte) -65) * 34754587;
	    if (version <= 3 && this.anInt1655 * -1699149293 != 0)
		this.anInt1655 += 1039784928;
	    membersCount = stream.readUnsignedShort() * -397756075;
	    bannedMembersCount = stream.readUnsignedByte() * 324877379;
	    clanName = stream.readString(-1963300431);
	    if (version >= 4)
		stream.readInt((byte) 83);
	    aBoolean1656 = stream.readUnsignedByte() == 1;
	    aByte1672 = stream.readByte(-12558881);
	    aByte1658 = stream.readByte(-12558881); // some rank for something
						    // in clan channel
	    aByte1659 = stream.readByte(-12558881);
	    aByte1660 = stream.readByte(-12558881);
	    if (2125429757 * membersCount > 0) {
		if (this.useUserHashes && (null == this.membersHashes || (this.membersHashes.length < 2125429757 * membersCount)))
		    this.membersHashes = new long[membersCount * 2125429757];
		if (this.useDisplayNames && (null == membersDisplayNames || membersDisplayNames.length < membersCount * 2125429757))
		    membersDisplayNames = new String[membersCount * 2125429757];
		if (membersRanks == null || membersRanks.length < membersCount * 2125429757)
		    membersRanks = new byte[2125429757 * membersCount];
		if (null == this.anIntArray1665 || (this.anIntArray1665.length < membersCount * 2125429757))
		    this.anIntArray1665 = new int[membersCount * 2125429757];
		if (null == anIntArray1666 || anIntArray1666.length < 2125429757 * membersCount)
		    anIntArray1666 = new int[membersCount * 2125429757];
		for (int index = 0; index < 2125429757 * membersCount; index++) {
		    if (this.useUserHashes)
			this.membersHashes[index] = stream.readLong((short) 10823);
		    if (this.useDisplayNames)
			membersDisplayNames[index] = stream.readJNullString(-517364695);
		    membersRanks[index] = stream.readByte(-12558881);
		    if (version >= 2)
			this.anIntArray1665[index] = stream.readInt((byte) -36);
		    if (version >= 5)
			anIntArray1666[index] = stream.readUnsignedShort();
		    else
			anIntArray1666[index] = 0;
		}
		assignLeaders();
	    }
	    if (bannedMembersCount * -1102843797 > 0) {
		if (this.useUserHashes && (null == this.bannedMembersHashes || (this.bannedMembersHashes.length < bannedMembersCount * -1102843797)))
		    this.bannedMembersHashes = new long[-1102843797 * bannedMembersCount];
		if (this.useDisplayNames && (bannedMembersDisplayNames == null || bannedMembersDisplayNames.length < -1102843797 * bannedMembersCount))
		    bannedMembersDisplayNames = new String[bannedMembersCount * -1102843797];
		for (int index = 0; index < -1102843797 * bannedMembersCount; index++) {
		    if (this.useUserHashes)
			this.bannedMembersHashes[index] = stream.readLong((short) 4192);
		    if (this.useDisplayNames)
			bannedMembersDisplayNames[index] = stream.readJNullString(-517364695);
		}
	    }
	    if (version >= 3) {
		int configsCount = stream.readUnsignedShort();

		if (configsCount > 0) {
		    this.configurations = new HashTable(configsCount < 16 ? Class416.method5590(configsCount, (byte) 16) : 16);
		    while (configsCount-- > 0) {
			int configData = stream.readInt((byte) -32);
			int configID = configData & 0x3fffffff;
			int type = configData >>> 30;
			if (0 == type) {
			    int data = stream.readInt((byte) -111);
			    this.configurations.method5817(new Class298_Sub35(data), configID);
			} else if (type == 1) {
			    long data = stream.readLong((short) 2486);
			    this.configurations.method5817(new Class298_Sub33(data), configID);
			} else if (type == 2) {
			    String data = stream.readString(478616247);
			    this.configurations.method5817(new Class298_Sub29(data), configID);
			}
		    }
		}
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("gp.c(").append(')').toString());
	}
    }

    public ClanSettings(RsByteBuffer stream) {
	clanLeaderID = -2050262011;
	deputyOwnerWithLeaderPriorityID = 1697252921;
	decode(stream, (byte) -58);
    }

    void deleteBannedMember(int chatterID) {
	try {
	    bannedMembersCount -= 324877379;
	    if (0 == -1102843797 * bannedMembersCount) {
		this.bannedMembersHashes = null;
		bannedMembersDisplayNames = null;
	    } else {
		if (null != this.bannedMembersHashes)
		    Class425.method5738(this.bannedMembersHashes, chatterID + 1, this.bannedMembersHashes, chatterID, -1102843797 * bannedMembersCount - chatterID);
		if (null != bannedMembersDisplayNames)
		    Class425.arrayCopy(bannedMembersDisplayNames, chatterID + 1, bannedMembersDisplayNames, chatterID, -1102843797 * bannedMembersCount - chatterID);
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("gp.z(").append(')').toString());
	}
    }

    public Long method1766(int i, int i_40_) {
	try {
	    if (this.configurations == null)
		return null;
	    Class298 class298 = this.configurations.method5812(i);
	    if (null == class298 || !(class298 instanceof Class298_Sub33))
		return null;
	    return new Long(((Class298_Sub33) class298).aLong7385 * -959724544626478745L);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("gp.d(").append(')').toString());
	}
    }

    int method1767(int i, int i_41_, int i_42_, int i_43_, int i_44_) {
	try {
	    int i_45_ = (1 << i_42_) - 1;
	    int i_46_ = 31 == i_43_ ? -1 : (1 << i_43_ + 1) - 1;
	    int i_47_ = i_46_ ^ i_45_;
	    i_41_ <<= i_42_;
	    i_41_ &= i_47_;
	    int i_48_ = this.anIntArray1665[i];
	    if (i_41_ == (i_48_ & i_47_))
		return -1;
	    i_48_ &= i_47_ ^ 0xffffffff;
	    this.anIntArray1665[i] = i_48_ | i_41_;
	    return i;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("gp.t(").append(')').toString());
	}
    }

    void deleteMember(int memberID, byte i_49_) {
	try {
	    if (memberID < 0 || memberID >= 2125429757 * membersCount)
		throw new RuntimeException("");
	    membersCount -= -397756075;
	    this.anIntArray1652 = null;
	    if (membersCount * 2125429757 == 0) {
		this.membersHashes = null;
		membersDisplayNames = null;
		membersRanks = null;
		this.anIntArray1665 = null;
		anIntArray1666 = null;
		clanLeaderID = -2050262011;
		deputyOwnerWithLeaderPriorityID = 1697252921;
	    } else {
		Class425.method5736(membersRanks, memberID + 1, membersRanks, memberID, membersCount * 2125429757 - memberID);
		Class425.method5741(this.anIntArray1665, memberID + 1, this.anIntArray1665, memberID, membersCount * 2125429757 - memberID);
		Class425.method5741(anIntArray1666, 1 + memberID, anIntArray1666, memberID, 2125429757 * membersCount - memberID);
		if (null != this.membersHashes)
		    Class425.method5738(this.membersHashes, 1 + memberID, this.membersHashes, memberID, 2125429757 * membersCount - memberID);
		if (null != membersDisplayNames)
		    Class425.arrayCopy(membersDisplayNames, 1 + memberID, membersDisplayNames, memberID, 2125429757 * membersCount - memberID);
		if (memberID == -2079715533 * clanLeaderID || deputyOwnerWithLeaderPriorityID * 873199607 == memberID)
		    assignLeaders();
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("gp.q(").append(')').toString());
	}
    }

    int method1769(int i, byte i_50_, byte i_51_) {
	try {
	    if (126 == i_50_ || i_50_ == 127)
		return -1;
	    if (i == -2079715533 * clanLeaderID && (-1 == 873199607 * deputyOwnerWithLeaderPriorityID || membersRanks[deputyOwnerWithLeaderPriorityID * 873199607] < 125))
		return -1;
	    if (i_50_ == membersRanks[i])
		return -1;
	    membersRanks[i] = i_50_;
	    assignLeaders();
	    return i;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("gp.y(").append(')').toString());
	}
    }

    static final void method1770(Class403 class403, int i) {
	try {
	    client.aBoolean8680 = true;
	    Class52_Sub1.method566((byte) 12);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("gp.ahu(").append(')').toString());
	}
    }

    static final void method1771(int i) {
	try {
	    int i_52_ = Class10.anInt129 * 1168366243;
	    int[] is = Class10.anIntArray135;
	    for (int i_53_ = 0; i_53_ < i_52_; i_53_++) {
		Player class365_sub1_sub1_sub2_sub2 = client.aClass365_Sub1_Sub1_Sub2_Sub2Array8805[is[i_53_]];
		if (null != class365_sub1_sub1_sub2_sub2)
		    Class135.method1494(class365_sub1_sub1_sub2_sub2, false, -1941477240);
	    }
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("gp.hv(").append(')').toString());
	}
    }

    static final void method1772(Class403 class403, int i) {
	try {
	    int i_54_ = (class403.anIntArray5244[((class403.anInt5239 -= -391880689) * 681479919)]);
	    Class_na.method3479((Class350.aClass298_Sub25_3757 == (class403.myClanChannel)), i_54_, (byte) 1);
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("gp.yn(").append(')').toString());
	}
    }
}
