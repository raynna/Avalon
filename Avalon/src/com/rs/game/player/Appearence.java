package com.rs.game.player;

import java.io.Serializable;
import java.util.Arrays;

import com.rs.cache.loaders.ClientScriptMap;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.World;
import com.rs.game.item.Item;
import com.rs.game.player.content.clans.ClansManager;
import com.rs.io.OutputStream;
import com.rs.utils.Utils;

public class Appearence implements Serializable {

	private static final long serialVersionUID = 7655608569741626586L;

	private transient int renderEmote;
	private int title;
	private int[] lookI;
	public byte[] colour;
	private boolean male;
	private transient boolean glowRed;
	private transient byte[] appeareanceData;
	private transient byte[] md5AppeareanceDataHash;
	private transient short transformedNpcId;
	private transient boolean hidePlayer;

	private transient int forcedWeapon, forcedShield, forcedAmulet;

	private transient Player player;

	public Appearence() {
		male = true;
		renderEmote = -1;
		title = -1;
		resetAppearence();
	}

	public void setGlowRed(boolean glowRed) {
		this.glowRed = glowRed;
		generateAppearenceData();
	}

	public void setPlayer(Player player) {
		this.player = player;
		transformedNpcId = -1;
		renderEmote = -1;
		forcedWeapon = forcedShield = forcedAmulet = -1;
		if (lookI == null)
			resetAppearence();
		else
			for (int i = 0; i < lookI.length; i++) { // temp fix
				if (lookI[i] >= 16384 || lookI[i] < 0) {
					resetAppearence();
					break;
				}
			}
	}

	public void transformIntoNPC(int id) {
		transformedNpcId = (short) id;
		generateAppearenceData();
	}

	public void switchHidden() {
		hidePlayer = !hidePlayer;
		generateAppearenceData();
	}

	public boolean isHidden() {
		return hidePlayer;
	}

	public boolean isGlowRed() {
		return glowRed;
	}

	private String titleName;

	public String customTitle() {
		return player.isTitle ? "<col=" + player.color + ">" + " " + player.title + "</col>"
				: "<col=" + player.color + ">" + player.title + " " + "</col>";
	}

	public void generateAppearenceData() {
		player.clanTag = player.getClanManager() != null ? "<col=FF5B1A>" + player.getClanName() + "</col> " : "";
		OutputStream stream = new OutputStream();
		int flag = 0;
		if (!male)
			flag |= 0x1;
		if (transformedNpcId >= 0 && NPCDefinitions.getNPCDefinitions(transformedNpcId).aBoolean3190)
			flag |= 0x2;
		if (title != 0)
			flag |= title >= 32 && title <= 37 || title == 901 || title == 9001 || title == 9002 || title == 9003 ? 0x80
					: 0x40;
		stream.writeByte(flag);
		if (title != 0) {
			if (title == 65535 && !player.getPlayerRank().isIronman())
				title = 0;
			setTitleName(title == 25 ? "<col=C12006>Yt'Haar </col>"
					: title == 5000 ? "<col=C12006>" + player.clanTag + "</col>"
							: title == 9001 ? "<col=734d26> the Recruiter"
									: title == 9002 ? "<col=827e7d> the Recruiter"
											: title == 9003 ? "<col=ffc800> the Recruiter"
													: title == 800 ? "<col="+player.getCustomTitle() + ">" : title == 900 ? player.getCustomTitle()
															: title == 901 ? player.getCustomTitle2()
																	: title == 65535 ? player.getPlayerRank().getTitle()
																			: ClientScriptMap.getMap(male ? 1093 : 3872)
																					.getStringValue(title));
			stream.writeGJString(getTitleName());
		}
		stream.writeByte(player.hasSkull() ? player.getSkullId() : -1);
		stream.writeByte(player.getPrayer().getPrayerHeadIcon());
		stream.writeByte(hidePlayer ? 1 : 0);
		// npc
		if (transformedNpcId >= 0) {
			stream.writeShort(-1);
			stream.writeShort(transformedNpcId);
			stream.writeByte(0);
		} else {
			for (int index = 0; index < 4; index++) {
				if (index == Equipment.SLOT_WEAPON && forcedWeapon != -1)
					stream.writeShort(16384 + forcedWeapon);
				else if (index == Equipment.SLOT_AMULET && forcedAmulet != -1)
					stream.writeShort(16384 + forcedAmulet);
				else {
					Item item = player.getEquipment().getItems().get(index);
					if (item == null)
						stream.writeByte(0);
					else
						stream.writeShort(16384 + item.getId());
				}
			}
			Item item = player.getEquipment().getItems().get(Equipment.SLOT_CHEST);
			stream.writeShort(item == null ? 0x100 + lookI[2] : 16384 + item.getId());
			item = player.getEquipment().getItems().get(Equipment.SLOT_SHIELD);
			if (item == null || forcedShield != -1) {
				if (forcedShield == -1)
					stream.writeByte(0);
				else
					stream.writeShort(16384 + forcedShield);
			} else
				stream.writeShort(16384 + item.getId());
			item = player.getEquipment().getItems().get(Equipment.SLOT_CHEST);
			if (item == null || !Equipment.hideArms(item))
				stream.writeShort(0x100 + lookI[3]);
			else
				stream.writeByte(0);
			item = player.getEquipment().getItems().get(Equipment.SLOT_LEGS);
			stream.writeShort(item == null ? 0x100 + lookI[5] : 16384 + item.getId());
			item = player.getEquipment().getItems().get(Equipment.SLOT_HAT);
			if ((item == null || !player.getEquipment().hideHair(item)))
				stream.writeShort(0x100 + lookI[0]);
			else
				stream.writeByte(0);
			item = player.getEquipment().getItems().get(Equipment.SLOT_HANDS);
			stream.writeShort(item == null ? 0x100 + lookI[4] : 16384 + item.getId());
			item = player.getEquipment().getItems().get(Equipment.SLOT_FEET);
			stream.writeShort(item == null ? 0x100 + lookI[6] : 16384 + item.getId());
			item = player.getEquipment().getItems().get(male ? Equipment.SLOT_HAT : Equipment.SLOT_CHEST);
			if (item == null || (male && player.getEquipment().showBear(item)))
				stream.writeShort(0x100 + lookI[1]);
			else
				stream.writeByte(0);
			item = player.getEquipment().getItems().get(Equipment.SLOT_AURA);
			if (item == null)
				stream.writeByte(0);
			else
				stream.writeShort(16384 + item.getId());
			int pos = stream.getOffset();
			stream.writeShort(0);
			int hash = 0;
			int slotFlag = -1;
			for (int slotId = 0; slotId < player.getEquipment().getItems().getSize(); slotId++) {
				if (Equipment.DISABLED_SLOTS[slotId] != 0)
					continue;
				slotFlag++;
				if (slotId == Equipment.SLOT_HAT) {
					int hatId = player.getEquipment().getHatId();
					if (hatId == 20768 || hatId == 20770 || hatId == 20772) {
						ItemDefinitions defs = ItemDefinitions.getItemDefinitions(hatId - 1);
						if ((hatId == 20768 && Arrays.equals(player.getMaxedCapeCustomized(), defs.originalModelColors)
								|| ((hatId == 20770 || hatId == 20772) && Arrays
										.equals(player.getCompletionistCapeCustomized(), defs.originalModelColors))))
							continue;
						hash |= 1 << slotFlag;
						stream.writeByte(0x4);
						int[] hat = hatId == 20768 ? player.getMaxedCapeCustomized()
								: player.getCompletionistCapeCustomized();
						int slots = 0 | 1 << 4 | 2 << 8 | 3 << 12;
						stream.writeShort(slots);
						for (int i = 0; i < 4; i++)
							stream.writeShort(hat[i]);
					}
				} else if (slotId == Equipment.SLOT_CAPE) {
					int capeId = player.getEquipment().getCapeId();
					if (capeId == 20767 || capeId == 20769 || capeId == 20771) {
						ItemDefinitions defs = ItemDefinitions.getItemDefinitions(capeId);
						if ((capeId == 20767 && Arrays.equals(player.getMaxedCapeCustomized(), defs.originalModelColors)
								|| ((capeId == 20769 || capeId == 20771) && Arrays
										.equals(player.getCompletionistCapeCustomized(), defs.originalModelColors))))
							continue;
						hash |= 1 << slotFlag;
						stream.writeByte(0x4);
						int[] cape = capeId == 20767 ? player.getMaxedCapeCustomized()
								: player.getCompletionistCapeCustomized();
						int slots = 0 | 1 << 4 | 2 << 8 | 3 << 12;
						stream.writeShort(slots);
						for (int i = 0; i < 4; i++)
							stream.writeShort(cape[i]);
					} else if (capeId == 20708) {
						ClansManager manager = player.getClanManager();
						if (manager == null)
							continue;
						int[] colors = manager.getClan().getMottifColors();
						ItemDefinitions defs = ItemDefinitions.getItemDefinitions(20709);
						boolean modifyColor = !Arrays.equals(colors, defs.originalModelColors);
						int bottom = manager.getClan().getMottifBottom();
						int top = manager.getClan().getMottifTop();
						if (bottom == 0 && top == 0 && !modifyColor)
							continue;
						hash |= 1 << slotFlag;
						stream.writeByte((modifyColor ? 0x4 : 0) | (bottom != 0 || top != 0 ? 0x8 : 0));
						if (modifyColor) {
							int slots = 0 | 1 << 4 | 2 << 8 | 3 << 12;
							stream.writeShort(slots);
							for (int i = 0; i < 4; i++)
								stream.writeShort(colors[i]);
						}
						if (bottom != 0 || top != 0) {
							int slots = 0 | 1 << 4;
							stream.writeByte(slots);
							stream.writeShort(ClansManager.getMottifTexture(top));
							stream.writeShort(ClansManager.getMottifTexture(bottom));
						}

					}
				} else if (slotId == Equipment.SLOT_WEAPON) {
					int weaponId = player.getEquipment().getWeaponId();
					if (weaponId == 20709) {
						ClansManager manager = player.getClanManager();
						if (manager == null)
							continue;
						int[] colors = manager.getClan().getMottifColors();
						ItemDefinitions defs = ItemDefinitions.getItemDefinitions(20709);
						boolean modifyColor = !Arrays.equals(colors, defs.originalModelColors);
						int bottom = manager.getClan().getMottifBottom();
						int top = manager.getClan().getMottifTop();
						if (bottom == 0 && top == 0 && !modifyColor)
							continue;
						hash |= 1 << slotFlag;
						stream.writeByte((modifyColor ? 0x4 : 0) | (bottom != 0 || top != 0 ? 0x8 : 0));
						if (modifyColor) {
							int slots = 0 | 1 << 4 | 2 << 8 | 3 << 12;
							stream.writeShort(slots);
							for (int i = 0; i < 4; i++)
								stream.writeShort(colors[i]);
						}
						if (bottom != 0 || top != 0) {
							int slots = 0 | 1 << 4;
							stream.writeByte(slots);
							stream.writeShort(ClansManager.getMottifTexture(top));
							stream.writeShort(ClansManager.getMottifTexture(bottom));
						}

					}
				} else if (slotId == Equipment.SLOT_AURA) {
					int auraId = player.getEquipment().getAuraId();
					if (auraId == -1 || !player.getAuraManager().isActivated())
						continue;
					ItemDefinitions auraDefs = ItemDefinitions.getItemDefinitions(auraId);
					if (auraDefs.getMaleWornModelId1() == -1 || auraDefs.getFemaleWornModelId1() == -1)
						continue;
					hash |= 1 << slotFlag;
					stream.writeByte(0x1);
					int modelId = player.getAuraManager().getAuraModelId();
					stream.writeBigSmart(modelId);
					stream.writeBigSmart(modelId);
					if (auraDefs.getMaleWornModelId2() != -1 || auraDefs.getFemaleWornModelId2() != -1) {
						int modelId2 = player.getAuraManager().getAuraModelId2();
						stream.writeBigSmart(modelId2);
						stream.writeBigSmart(modelId2);
					}
				}
			}
			int pos2 = stream.getOffset();
			stream.setOffset(pos);
			stream.writeShort(hash);
			stream.setOffset(pos2);
		}

		for (int index = 0; index < colour.length; index++)
			stream.writeByte(colour[index]);
		stream.writeShort(getRenderEmote());
		stream.writeString(player.getDisplayName());
		boolean pvpArea = World.isPvpArea(player);
		int summoningLevels = player.getSkills().getCombatLevelWithSummoning() - player.getSkills().getCombatLevel();
		stream.writeByte(pvpArea ? player.getSkills().getCombatLevel() : player.getSkills().getCombatLevelWithSummoning());
		stream.writeByte(pvpArea ? player.getSkills().getCombatLevelWithSummoning() : 0);
		stream.writeByte(-1);
		stream.writeByte(transformedNpcId >= 0 ? 1 : 0);
		if (transformedNpcId >= 0) {
			NPCDefinitions defs = NPCDefinitions.getNPCDefinitions(transformedNpcId);
			stream.writeShort(defs.anInt876);
			stream.writeShort(defs.anInt842);
			stream.writeShort(defs.anInt884);
			stream.writeShort(defs.anInt875);
			stream.writeByte(defs.anInt875);
		}
		byte[] appeareanceData = new byte[stream.getOffset()];
		System.arraycopy(stream.getBuffer(), 0, appeareanceData, 0, appeareanceData.length);
		byte[] md5Hash = Utils.encryptUsingMD5(appeareanceData);
		this.appeareanceData = appeareanceData;
		md5AppeareanceDataHash = md5Hash;
	}

	public int getSize() {
		if (transformedNpcId >= 0)
			return NPCDefinitions.getNPCDefinitions(transformedNpcId).size;
		return 1;
	}

	public void setRenderEmote(int id) {
		this.renderEmote = id;
		generateAppearenceData();
	}
	
	public void resetRender() {
		this.renderEmote = -1;
		generateAppearenceData();
	}

	public int getRenderEmote() {
		if (renderEmote >= 0)
			return renderEmote;
		if (transformedNpcId >= 0)
			return NPCDefinitions.getNPCDefinitions(transformedNpcId).renderEmote;
		return player.getEquipment().getWeaponRenderEmote();
	}

	public void resetAppearence() {
		lookI = new int[7];
		colour = new byte[10];
		male();
	}

	public void male() {
		lookI[0] = 7; // Hair
		lookI[1] = 14; // Beard
		lookI[2] = 18; // Torso
		lookI[3] = 26; // Arms
		lookI[4] = 34; // Bracelets
		lookI[5] = 38; // Legs
		lookI[6] = 42; // Shoes~
		colour[3] = 0;//boot
		colour[2] = 32;//legs
		colour[1] = 95;//top
		colour[0] = 17;//hair
		male = true;
	}

	/*
	 * 276 odango hair 274 ponytail spiked
	 *
	 */
	public void female() {
		lookI[0] = 276; // Hair
		lookI[1] = 57; // Beard
		lookI[2] = 57; // Torso
		lookI[3] = 65; // Arms
		lookI[4] = 68; // Bracelets
		lookI[5] = 77; // Legs
		lookI[6] = 80; // Shoes

		colour[2] = 95;
		colour[1] = 95;
		colour[0] = 78;
		male = false;
	}

	public byte[] getAppeareanceData() {
		return appeareanceData;
	}

	public byte[] getMD5AppeareanceDataHash() {
		return md5AppeareanceDataHash;
	}

	public boolean isMale() {
		return male;
	}

	public void setLook(int i, int i2) {
		lookI[i] = i2;
	}

	public void setColor(int i, int i2) {
		colour[i] = (byte) i2;
	}

	public void setMale(boolean male) {
		this.male = male;
	}

	public void setHairStyle(int i) {
		lookI[0] = i;
	}

	public void setTopStyle(int i) {
		lookI[2] = i;
	}

	public void setBootsStyle(int i) {
		lookI[6] = i;
	}

	public int getTopStyle() {
		return lookI[2];
	}

	public void setWristsStyle(int i) {
		lookI[4] = i;
	}

	public void setLegsStyle(int i) {
		lookI[5] = i;
	}
	
	public int getLegsStyle() {
		return lookI[5];
	}

	public int getHairStyle() {
		return lookI[0];
	}

	public void setLookStyles(int sets[]) {
		for (int i = 2; i < 7; i++)
			lookI[i] = sets[i - 2];

	}

	public void setColours(int colours[]) {
		for (int i = 1; i < 4; i++)
			if (colours[i - 1] <= 127)
				colour[i] = (byte) colours[i - 1];

	}

	public int getBootsColor() {
		return colour[3];
	}

	public void setBeardStyle(int i) {
		lookI[1] = i;
	}

	public int getBeardStyle() {
		return lookI[1];
	}

	public void setSkinColor(int color) {
		colour[4] = (byte) color;
	}

	public int getSkinColor() {
		return colour[4];
	}

	public void setHairColor(int color) {
		colour[0] = (byte) color;
	}

	public void setTopColor(int color) {
		colour[1] = (byte) color;
	}

	public void setLegsColor(int color) {
		colour[2] = (byte) color;
	}

	public int getHairColor() {
		return colour[0];
	}
	
	public int getLegColor() {
		return colour[2];
	}
	
	public int getTopColor() {
		return colour[1];
	}

	public int getBootColor() {
		return colour[5];
	}

	public void setArmsStyle(int i) {
		lookI[3] = i;
	}

	public int getArmsStyle() {
		return lookI[3];
	}

	public void setBootsColor(int color) {
		colour[3] = (byte) color;
	}

	public void setTitle(int title) {
		this.title = title;
		generateAppearenceData();
	}

	public int getTitle() {
		return title;
	}

	public String getTitleString() {
		if (titleName == null)
			return "";
		return titleName;
	}

	public boolean isNPC() {
		return transformedNpcId != -1;
	}

	public int getForcedWeapon() {
		return forcedWeapon;
	}

	public void setForcedWeapon(int forcedWeapon) {
		this.forcedWeapon = forcedWeapon;
		generateAppearenceData();
	}

	public int getForcedShield() {
		return forcedShield;
	}

	public void setForcedShield(int forcedShield) {
		this.forcedShield = forcedShield;
		generateAppearenceData();
	}

	public int getForcedAmulet() {
		return forcedAmulet;
	}

	public void setForcedAmulet(int forcedAmulet) {
		this.forcedAmulet = forcedAmulet;
		generateAppearenceData();
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}


	public void setHidden(boolean hidden) {
		hidePlayer = hidden;
		generateAppearenceData();
	}

	public void setIdentityHide(boolean hide) {
		hidePlayer = hide;
		generateAppearenceData();
	}
}
