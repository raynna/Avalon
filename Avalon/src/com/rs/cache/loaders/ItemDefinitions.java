package com.rs.cache.loaders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.alex.utils.Constants;
import com.rs.Settings;
import com.rs.cache.Cache;
import com.rs.cache.loaders.ItemDefinitions.FileUtilities;
import com.rs.game.item.Item;
import com.rs.game.player.Equipment;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.content.ItemConstants;
import com.rs.game.player.content.grandexchange.LimitedGEReader;
import com.rs.io.InputStream;
import com.rs.io.Stream;
import com.rs.utils.ItemExamines;
import com.rs.utils.Logger;
import com.rs.utils.Utils;

@SuppressWarnings("unused")
public final class ItemDefinitions {

    private static final ItemDefinitions[] itemsDefinitions;

    static {
        itemsDefinitions = new ItemDefinitions[Utils.getItemDefinitionsSize()];
    }

    private int id;
    private boolean loaded;

    public int modelId;
    public String name;
    private static String itemName;

    // model size information
    private int modelZoom;
    private int modelRotation1;
    private int modelRotation2;
    private int modelOffset1;
    private int modelOffset2;

    // extra information
    private int stackable;
    private int value;
    private boolean membersOnly;
    private int price;

    // wearing model information
    private int maleEquip1;
    private int femaleEquip1;
    private int maleEquip2;
    private int femaleEquip2;

    // options
    private String[] groundOptions;
    public String[] inventoryOptions;

    // model information
    public int[] originalModelColors;
    public int[] modifiedModelColors;
    public short[] originalTextureColors;
    private short[] modifiedTextureColors;
    private byte[] unknownArray1;
    private int[] unknownArray2;
    // extra information, not used for newer items
    private boolean unnoted;

    private int maleEquipModelId3;
    private int femaleEquipModelId3;
    private int unknownInt1;
    private int unknownInt2;
    private int unknownInt3;
    private int unknownInt4;
    private int unknownInt5;
    private int unknownInt6;
    public int certId;
    private int certTemplateId;
    private int[] stackIds;
    private int[] stackAmounts;
    private int unknownInt7;
    private int unknownInt8;
    private int unknownInt9;
    private int unknownInt10;
    private int unknownInt11;
    public int teamId;
    private int lendId;
    private int lendTemplateId;
    private int unknownInt12;
    private int unknownInt13;
    private int unknownInt14;
    private int unknownInt15;
    private int unknownInt16;
    private int unknownInt17;
    private int unknownInt18;
    private int unknownInt19;
    private int unknownInt20;
    private int unknownInt21;
    private int unknownInt22;
    private int unknownInt23;
    public int equipSlot;
    private int equipType;

    // extra added
    private boolean noted;
    private boolean lended;

    private HashMap<Integer, Object> clientScriptData;
    public HashMap<Integer, Integer> itemRequiriments;

    public static final ItemDefinitions getItemDefinitions(int itemId) {
        if (itemId < 0 || itemId >= itemsDefinitions.length)
            itemId = 0;
        ItemDefinitions def = itemsDefinitions[itemId];
        if (def == null)
            itemsDefinitions[itemId] = def = new ItemDefinitions(itemId);
        return def;
    }

    public static final void clearItemsDefinitions() {
        for (int i = 0; i < itemsDefinitions.length; i++)
            itemsDefinitions[i] = null;
    }

    public ItemDefinitions(int id) {
        this.id = id;
        setDefaultsVariableValues();
        setDefaultOptions();
        loadItemDefinitions();
    }

    public boolean isLoaded() {
        return loaded;
    }

    public static int getId(String name) {
        int amount = 0;
        for (ItemDefinitions definition : itemsDefinitions) {
            name = name.toLowerCase();
            String output = definition.name.toLowerCase();
            if (name.contains("�") && output.equalsIgnoreCase(name.replace("#noted", "").substring(0, name.length() - 2)) || output.equalsIgnoreCase(name.replace("#noted", ""))) {
                if (name.contains("#noted"))
                    return definition.getCertId();
                if (output.equalsIgnoreCase("coins"))
                    return 995;
                if (name.contains("�")) {
                    amount = Integer.valueOf(name.substring(name.length() - 1, name.length()));
                    return definition.getId() + amount;
                }
                return definition.getId();
            }
        }
        return -1;
    }

    public static ItemDefinitions getItemID(Player player, String name) {
        int count = 0;
        for (ItemDefinitions definition : itemsDefinitions) {
            name = name.toLowerCase();
            String output = definition.name.toLowerCase();
            int itemId = definition.getId();
            ItemDefinitions defs = ItemDefinitions.getItemDefinitions(itemId);
            if (output.contains(name)) {
                count++;
                String results = "" + count + " - " + defs.getName() + " (<col=ff0000>" + definition.getId() + "</col>)";
            }
        }
        player.getPackets().sendPanelBoxMessage("Found " + count + " results for '" + name + "'");
        return null;
    }

    public static ItemDefinitions forName(String replace) {
        for (ItemDefinitions definition : itemsDefinitions) {
            if (definition.name.equalsIgnoreCase(itemName)) {
                return definition;
            }
        }
        return null;
    }

    private final void loadItemDefinitions() {
        byte[] data = Cache.STORE.getIndexes()[Constants.ITEM_DEFINITIONS_INDEX].getFile(getArchiveId(), getFileId());
        if (data == null) {
            // System.out.println("Failed loading Item " + id+".");
            return;
        }
        readOpcodeValues(new InputStream(data));
        if (certTemplateId != -1)
            toNote();
        if (lendTemplateId != -1)
            toLend();
        if (unknownValue1 != -1)
            toBind();
        loaded = true;
    }

    private void toNote() {
        // ItemDefinitions noteItem; //certTemplateId
        ItemDefinitions realItem = getItemDefinitions(certId);
        membersOnly = realItem.membersOnly;
        value = realItem.value;
        name = realItem.name;
        stackable = 1;
        noted = true;
    }

    private void toBind() {
        // ItemDefinitions lendItem; //lendTemplateId
        ItemDefinitions realItem = getItemDefinitions(unknownValue2);
        originalModelColors = realItem.originalModelColors;
        maleEquipModelId3 = realItem.maleEquipModelId3;
        femaleEquipModelId3 = realItem.femaleEquipModelId3;
        teamId = realItem.teamId;
        value = 0;
        membersOnly = realItem.membersOnly;
        name = realItem.name;
        inventoryOptions = new String[5];
        groundOptions = realItem.groundOptions;
        if (realItem.inventoryOptions != null)
            for (int optionIndex = 0; optionIndex < 4; optionIndex++)
                inventoryOptions[optionIndex] = realItem.inventoryOptions[optionIndex];
        inventoryOptions[4] = "Discard";
        maleEquip1 = realItem.maleEquip1;
        maleEquip2 = realItem.maleEquip2;
        femaleEquip1 = realItem.femaleEquip1;
        femaleEquip2 = realItem.femaleEquip2;
        clientScriptData = realItem.clientScriptData;
        equipSlot = realItem.equipSlot;
        equipType = realItem.equipType;
    }

    private void toLend() {
        // ItemDefinitions lendItem; //lendTemplateId
        ItemDefinitions realItem = getItemDefinitions(lendId);
        originalModelColors = realItem.originalModelColors;
        maleEquipModelId3 = realItem.maleEquipModelId3;
        femaleEquipModelId3 = realItem.femaleEquipModelId3;
        teamId = realItem.teamId;
        value = 0;
        membersOnly = realItem.membersOnly;
        name = realItem.name;
        inventoryOptions = new String[5];
        groundOptions = realItem.groundOptions;
        if (realItem.inventoryOptions != null)
            for (int optionIndex = 0; optionIndex < 4; optionIndex++)
                inventoryOptions[optionIndex] = realItem.inventoryOptions[optionIndex];
        inventoryOptions[4] = "Discard";
        maleEquip1 = realItem.maleEquip1;
        maleEquip2 = realItem.maleEquip2;
        femaleEquip1 = realItem.femaleEquip1;
        femaleEquip2 = realItem.femaleEquip2;
        clientScriptData = realItem.clientScriptData;
        equipSlot = realItem.equipSlot;
        equipType = realItem.equipType;
        lended = true;
    }

    public int getArchiveId() {
        return getId() >>> 8;
    }

    public int getFileId() {
        return 0xff & getId();
    }

    public boolean isDestroyItem() {
        if (inventoryOptions == null)
            return false;
        for (String option : inventoryOptions) {
            if (option == null)
                continue;
            if (option.equalsIgnoreCase("destroy"))
                return true;
        }
        return false;
    }

    public String[] getInventoryOptions() {
        return inventoryOptions;
    }

    public boolean containsOption(int i, String option) {
        if (inventoryOptions == null || inventoryOptions[i] == null || inventoryOptions.length <= i)
            return false;
        return inventoryOptions[i].equals(option);
    }

    public String getInventoryOption(boolean print, int option) {
        if (getInventoryOptions() == null) {
            return "";
        }
        print = false;
		if (print) {
			int optionId = 1;
			StringBuilder builder = new StringBuilder();
			builder.append("Clicked option=" + getInventoryOptions()[option - 1] + "("+option+"), Length=" + getInventoryOptions().length + ", Options: ");
			for (String options : getInventoryOptions()) {
				if (options == null)
					builder.append("None(" + optionId + ") ");
				else
					builder.append(""+options.toLowerCase() + "(" + optionId + ") ");
				optionId++;
			}
			Logger.log("ItemDefinitions", builder);
		}
        return getInventoryOptions()[option - 1].toLowerCase();
    }

    public boolean containsOption(String option) {
        if (inventoryOptions == null)
            return false;
        for (String o : inventoryOptions) {
            if (o == null)
                continue;
            if (o.equalsIgnoreCase(option))
                return true;
        }
        return false;
    }

    public boolean isWearItem() {
        return equipSlot != -1;
    }

    public boolean isWearItem(boolean male) {
        if (equipSlot < Equipment.SLOT_RING && (male ? getMaleWornModelId1() == -1 : getFemaleWornModelId1() == -1))
            return false;
        return equipSlot != -1;
    }

    public int getStageOnDeath() {
        if (clientScriptData == null)
            return 0;
        Item item = new Item(id);
        if (ItemConstants.keptOnDeath(item))
            return 1;
        Object protectedOnDeath = clientScriptData.get(1397);
        if (protectedOnDeath != null && protectedOnDeath instanceof Integer)
            return (Integer) protectedOnDeath;
        return 0;
    }

    public boolean canBeNoted() {
        return (certId != -1 ? true : false);
    }

    public boolean hasSpecialBar() {
        if (clientScriptData == null)
            return false;
        Object specialBar = clientScriptData.get(686);
        if (specialBar != null && specialBar instanceof Integer)
            return (Integer) specialBar == 1;
        return false;
    }

    public int getRenderAnimId() {
        if (id == 18355 || id == 18351 || id == 18357)
            return 1426;
        if (clientScriptData == null)
            return 1426;
        Object animId = clientScriptData.get(644);
        if (animId != null && animId instanceof Integer)
            return (Integer) animId;
        return 1426;
    }

    public int getModelZoom() {
        return modelZoom;
    }

    public int getModelOffset1() {
        return modelOffset1;
    }

    public int getModelOffset2() {
        return modelOffset2;
    }

    public int getQuestId() {
        if (clientScriptData == null)
            return -1;
        Object questId = clientScriptData.get(861);
        if (questId != null && questId instanceof Integer)
            return (Integer) questId;
        return -1;
    }

    public HashMap<Integer, Integer> getCreateItemRequirements() {
        if (clientScriptData == null)
            return null;
        HashMap<Integer, Integer> items = new HashMap<Integer, Integer>();
        int requiredId = -1;
        int requiredAmount = -1;
        for (int key : clientScriptData.keySet()) {
            Object value = clientScriptData.get(key);
            if (value instanceof String)
                continue;
            if (key >= 538 && key <= 770) {
                if (key % 2 == 0)
                    requiredId = (Integer) value;
                else
                    requiredAmount = (Integer) value;
                if (requiredId != -1 && requiredAmount != -1) {
                    items.put(requiredAmount, requiredId);
                    requiredId = -1;
                    requiredAmount = -1;
                }
            }
        }
        return items;
    }

    public List<Item> getCreateItemRequirements(boolean infusingScroll) {
        if (clientScriptData == null)
            return null;
        List<Item> items = new ArrayList<Item>();
        int requiredId = -1;
        int requiredAmount = -1;
        for (int key : clientScriptData.keySet()) {
            Object value = clientScriptData.get(key);
            if (value instanceof String)
                continue;
            if (key >= 536 && key <= 770) {
                if (key % 2 == 0)
                    requiredId = (Integer) value;
                else
                    requiredAmount = (Integer) value;
                if (requiredId != -1 && requiredAmount != -1) {
                    if (infusingScroll) {
                        requiredId = getId();
                        requiredAmount = 1;
                    }
                    if (items.size() == 0 && !infusingScroll)
                        items.add(new Item(requiredAmount, 1));
                    else
                        items.add(new Item(requiredId, requiredAmount));
                    requiredId = -1;
                    requiredAmount = -1;
                    if (infusingScroll) {
                        break;
                    }
                }
            }
        }
        return items;
    }

    public HashMap<Integer, Object> getClientScriptData() {
        return clientScriptData;
    }

    public HashMap<Integer, Integer> getWearingSkillRequiriments() {
        if (clientScriptData == null)
            return null;
        if (itemRequiriments == null) {
            HashMap<Integer, Integer> skills = new HashMap<Integer, Integer>();
            for (int i = 0; i < 10; i++) {
                Integer skill = (Integer) clientScriptData.get(749 + (i * 2));
                if (skill != null) {
                    Integer level = (Integer) clientScriptData.get(750 + (i * 2));
                    if (level != null)
                        skills.put(skill, level);
                }
            }
            Integer maxedSkill = (Integer) clientScriptData.get(277);
            if (maxedSkill != null)
                skills.put(maxedSkill, getId() == 19709 ? 120 : 99);
            itemRequiriments = skills;
            if (getId() == 19784) {
                itemRequiriments.put(Skills.ATTACK, 78);
                itemRequiriments.put(Skills.DEFENCE, 10);
            }
            if (getId() >= 21371 && getId() <= 21375) {
                itemRequiriments.put(Skills.ATTACK, 85);
                itemRequiriments.put(Skills.SLAYER, 80);
            }
            if (name.contains("Inferno adze")) {
                itemRequiriments.put(Skills.FIREMAKING, 92);
            }
            if (getId() == 7462)
                itemRequiriments.put(Skills.DEFENCE, 40);
            if (getId() == 7461)
                itemRequiriments.put(Skills.DEFENCE, 40);
            if (getId() == 7460)
                itemRequiriments.put(Skills.DEFENCE, 13);
            if (getId() == 12673)
                itemRequiriments.put(Skills.DEFENCE, 40);
            if (getId() == 12675)
                itemRequiriments.put(Skills.DEFENCE, 40);
            if (getId() == 8850)
                itemRequiriments.put(Skills.DEFENCE, 40);
            if (getId() == 8849)
                itemRequiriments.put(Skills.DEFENCE, 30);
            if (getId() == 8848)
                itemRequiriments.put(Skills.DEFENCE, 20);
            if (getId() == 8847)
                itemRequiriments.put(Skills.DEFENCE, 10);
            if (getId() == 8846)
                itemRequiriments.put(Skills.DEFENCE, 5);
            if (getId() == 12677)
                itemRequiriments.put(Skills.DEFENCE, 40);
            if (getId() == 12679)
                itemRequiriments.put(Skills.DEFENCE, 40);
            if (getId() == 12681)
                itemRequiriments.put(Skills.DEFENCE, 55);
            if (getId() >= 22358 && getId() <= 22369) {
                itemRequiriments.put(Skills.DEFENCE, 70);
                itemRequiriments.put(Skills.ATTACK, 80);
            } else if (name.equals("Dragon defender")) {
                itemRequiriments.put(Skills.ATTACK, 60);
                itemRequiriments.put(Skills.DEFENCE, 60);
            } else if (name.equals("void")) {
                itemRequiriments.put(Skills.ATTACK, 42);
                itemRequiriments.put(Skills.DEFENCE, 42);
                itemRequiriments.put(Skills.STRENGTH, 42);
                itemRequiriments.put(Skills.MAGIC, 42);
                itemRequiriments.put(Skills.PRAYER, 42);
                itemRequiriments.put(Skills.RANGE, 42);
            } else if (name.equals("godsword")) {
                itemRequiriments.put(Skills.ATTACK, 75);
            }
        }

        return itemRequiriments;
    }

    /*
     * public HashMap<Integer, Integer> getWearingSkillRequiriments() { if
     * (clientScriptData == null) return null; HashMap<Integer, Integer> skills =
     * new HashMap<Integer, Integer>(); int nextLevel = -1; int nextSkill = -1; for
     * (int key : clientScriptData.keySet()) { Object value =
     * clientScriptData.get(key); if (value instanceof String) continue; if(key ==
     * 277) { skills.put((Integer) value, id == 19709 ? 120 : 99); }else if (key ==
     * 23 && id == 15241) { skills.put(4, (Integer) value); skills.put(11, 61); }
     * else if (key >= 749 && key < 797) { if (key % 2 == 0) nextLevel = (Integer)
     * value; else nextSkill = (Integer) value; if (nextLevel != -1 && nextSkill !=
     * -1) { skills.put(nextSkill, nextLevel); nextLevel = -1; nextSkill = -1; } }
     *
     * } return skills; }
     */

    private void setDefaultOptions() {
        groundOptions = new String[]{null, null, "take", null, null};
        inventoryOptions = new String[]{null, null, null, null, "drop"};
    }

    private void setDefaultsVariableValues() {
        name = "null";
        maleEquip1 = -1;
        maleEquip2 = -1;
        femaleEquip1 = -1;
        femaleEquip2 = -1;
        modelZoom = 2000;
        lendId = -1;
        lendTemplateId = -1;
        certId = -1;
        certTemplateId = -1;
        unknownInt9 = 128;
        value = 1;
        price = 1;
        maleEquipModelId3 = -1;
        femaleEquipModelId3 = -1;
        unknownValue1 = -1;
        unknownValue2 = -1;
        teamId = -1;
        equipSlot = -1;
        equipType = -1;
    }

    private final void readValues(InputStream stream, int opcode) {
        if (opcode == 1)
            modelId = stream.readBigSmart();
        else if (opcode == 2) {
            name = stream.readString();
        } else if (opcode == 4) {
            modelZoom = stream.readUnsignedShort();
        } else if (opcode == 5)
            modelRotation1 = stream.readUnsignedShort();
        else if (opcode == 6)
            modelRotation2 = stream.readUnsignedShort();
        else if (opcode == 7) {
            modelOffset1 = stream.readUnsignedShort();
            if (modelOffset1 > 32767)
                modelOffset1 -= 65536;
            modelOffset1 <<= 0;
        } else if (opcode == 8) {
            modelOffset2 = stream.readUnsignedShort();
            if (modelOffset2 > 32767)
                modelOffset2 -= 65536;
            modelOffset2 <<= 0;
        } else if (opcode == 11)
            stackable = 1;
        else if (opcode == 12)
            price = stream.readInt();
        else if (opcode == 13)
            equipSlot = stream.readUnsignedByte();
        else if (opcode == 14)
            equipType = stream.readUnsignedByte();
        else if (opcode == 16)
            membersOnly = true;
        else if (opcode == 18)
            stream.readUnsignedShort();
        else if (opcode == 23)
            maleEquip1 = stream.readBigSmart();
        else if (opcode == 24)
            maleEquip2 = stream.readBigSmart();
        else if (opcode == 25)
            femaleEquip1 = stream.readBigSmart();
        else if (opcode == 26)
            femaleEquip2 = stream.readBigSmart();
        else if (opcode == 27)
            stream.readUnsignedByte();
        else if (opcode >= 30 && opcode < 35)
            groundOptions[opcode - 30] = stream.readString();
        else if (opcode >= 35 && opcode < 40) {
            inventoryOptions[opcode - 35] = stream.readString();
        } else if (opcode == 40) {
            int length = stream.readUnsignedByte();
            originalModelColors = new int[length];
            modifiedModelColors = new int[length];
            for (int index = 0; index < length; index++) {
                originalModelColors[index] = stream.readUnsignedShort();
                modifiedModelColors[index] = stream.readUnsignedShort();
            }
        } else if (opcode == 41) {
            int length = stream.readUnsignedByte();
            originalTextureColors = new short[length];
            modifiedTextureColors = new short[length];
            for (int index = 0; index < length; index++) {
                originalTextureColors[index] = (short) stream.readUnsignedShort();
                modifiedTextureColors[index] = (short) stream.readUnsignedShort();
            }
        } else if (opcode == 42) {
            int length = stream.readUnsignedByte();
            unknownArray1 = new byte[length];
            for (int index = 0; index < length; index++)
                unknownArray1[index] = (byte) stream.readByte();
        } else if (opcode == 65)
            unnoted = true;
        else if (opcode == 78)
            maleEquipModelId3 = stream.readBigSmart();
        else if (opcode == 79)
            femaleEquipModelId3 = stream.readBigSmart();
        else if (opcode == 90)
            unknownInt1 = stream.readBigSmart();
        else if (opcode == 91)
            unknownInt2 = stream.readBigSmart();
        else if (opcode == 92)
            unknownInt3 = stream.readBigSmart();
        else if (opcode == 93)
            unknownInt4 = stream.readBigSmart();
        else if (opcode == 95)
            unknownInt5 = stream.readUnsignedShort();
        else if (opcode == 96)
            unknownInt6 = stream.readUnsignedByte();
        else if (opcode == 97)
            certId = stream.readUnsignedShort();
        else if (opcode == 98)
            certTemplateId = stream.readUnsignedShort();
        else if (opcode >= 100 && opcode < 110) {
            if (stackIds == null) {
                stackIds = new int[10];
                stackAmounts = new int[10];
            }
            stackIds[opcode - 100] = stream.readUnsignedShort();
            stackAmounts[opcode - 100] = stream.readUnsignedShort();
        } else if (opcode == 110)
            unknownInt7 = stream.readUnsignedShort();
        else if (opcode == 111)
            unknownInt8 = stream.readUnsignedShort();
        else if (opcode == 112)
            unknownInt9 = stream.readUnsignedShort();
        else if (opcode == 113)
            unknownInt10 = stream.readByte();
        else if (opcode == 114)
            unknownInt11 = stream.readByte() * 5;
        else if (opcode == 115) {
            teamId = stream.readUnsignedByte();
        } else if (opcode == 121)
            lendId = stream.readUnsignedShort();
        else if (opcode == 122)
            lendTemplateId = stream.readUnsignedShort();
        else if (opcode == 125) {
            unknownInt12 = stream.readByte() << 0;
            unknownInt13 = stream.readByte() << 0;
            unknownInt14 = stream.readByte() << 0;
        } else if (opcode == 126) {
            unknownInt15 = stream.readByte() << 0;
            unknownInt16 = stream.readByte() << 0;
            unknownInt17 = stream.readByte() << 0;
        } else if (opcode == 127) {
            unknownInt18 = stream.readUnsignedByte();
            unknownInt19 = stream.readUnsignedShort();
        } else if (opcode == 128) {
            unknownInt20 = stream.readUnsignedByte();
            unknownInt21 = stream.readUnsignedShort();
        } else if (opcode == 129) {
            unknownInt20 = stream.readUnsignedByte();
            unknownInt21 = stream.readUnsignedShort();
        } else if (opcode == 130) {
            unknownInt22 = stream.readUnsignedByte();
            unknownInt23 = stream.readUnsignedShort();
        } else if (opcode == 132) {
            int length = stream.readUnsignedByte();
            unknownArray2 = new int[length];
            for (int index = 0; index < length; index++)
                unknownArray2[index] = stream.readUnsignedShort();
        } else if (opcode == 134) {
            int unknownValue = stream.readUnsignedByte();
        } else if (opcode == 139) {
            unknownValue2 = stream.readUnsignedShort();
        } else if (opcode == 140) {
            unknownValue1 = stream.readUnsignedShort();
        } else if (opcode == 249) {
            int length = stream.readUnsignedByte();
            if (clientScriptData == null)
                clientScriptData = new HashMap<Integer, Object>(length);
            for (int index = 0; index < length; index++) {
                boolean stringInstance = stream.readUnsignedByte() == 1;
                int key = stream.read24BitInt();
                Object value = stringInstance ? stream.readString() : stream.readInt();
                clientScriptData.put(key, value);
            }
        } else
            throw new RuntimeException("MISSING OPCODE " + opcode + " FOR ITEM " + getId());
    }

    private int unknownValue1;
    private int unknownValue2;

    private final void readOpcodeValues(InputStream stream) {
        while (true) {
            int opcode = stream.readUnsignedByte();
            if (opcode == 0)
                break;
            readValues(stream, opcode);
        }
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public double getDungShopValueMultiplier() {
        if (clientScriptData == null)
            return 1;
        Object value = clientScriptData.get(1046);
        if (value != null && value instanceof Integer)
            return ((Integer) value).doubleValue() / 100;
        return 1;
    }

    public int getHighAlchPrice() {
        return (int) (price * 0.6);
    }

    public int getLowAlchPrice() {
        return (int) (price * 0.3);
    }

    public int getFemaleWornModelId1() {
        return femaleEquip1;
    }

    public int getFemaleWornModelId2() {
        return femaleEquip2;
    }

    public int getMaleWornModelId1() {
        return maleEquip1;
    }

    public int getMaleWornModelId2() {
        return maleEquip2;
    }

    public boolean isOverSized() {
        return modelZoom > 5000;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public boolean isLended() {
        return lended;
    }

    public boolean isMembersOnly() {
        return membersOnly;
    }

    public boolean isStackable() {
        if (id == 13663)
            return true;
        if (id == 3600)
            return true;
        if (id == 7237 || id == 7287 || id == 7240 || id == 19039)// casket reward chests
            return true;
        if (id == 19981 || id == 19983)
            return false;
        return stackable == 1;
    }

    public boolean isNoted() {
        return noted;
    }

    public int getLendId() {
        return lendId;
    }

    public int getCertId() {
        return certId;
    }

    public int getOGValue() {
        return value;
    }

    public int getValue() {
        return getTipitPrice();
    }

    public int getHighAlchValue() {
        return value;
    }

    public int getId() {
        return id;
    }

    public int getEquipSlot() {
        return equipSlot;
    }

    public int getEquipType() {
        if (id == 4710 || id >= 4862 && id <= 4866)
            return -1;
        return equipType;
    }

    public void setValue(int value) {
        this.value = getTipitPrice();
    }

    public int getTeamId() {
        return teamId;
    }

    public int getAttackSpeed() {
        if (clientScriptData == null)
            return 4;
        Object attackSpeed = clientScriptData.get(14);
        if (attackSpeed != null && attackSpeed instanceof Integer)
            return (int) attackSpeed;
        return 4;
    }

    public int getTipitPrice() {
        try {
            for (String lines : FileUtilities.readFile("./prices/prices.txt")) {
                String[] data = lines.split(" - ");
                if (lines.contains("originalPrices"))
                    continue;
                int itemId = Integer.parseInt(data[0]);
                int price = Integer.parseInt(data[1]);
                if (itemId == id) {
                    if (LimitedGEReader.itemIsLimited(id))
                        return price;
                    return (Settings.ECONOMY_MODE == 1 && price < Settings.LOWPRICE_LIMIT) ? 0 : price;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    public static class FileUtilities {

        public static final int BUFFER = 1024;

        public static boolean exists(String name) {

            File file = new File(name);

            return file.exists();

        }

        public static ByteBuffer fileBuffer(String name) throws IOException {

            File file = new File(name);

            if (!file.exists())

                return null;

            FileInputStream in = new FileInputStream(name);

            byte[] data = new byte[BUFFER];

            int read;

            try {

                ByteBuffer buffer = ByteBuffer.allocate(in.available() + 1);

                while ((read = in.read(data, 0, BUFFER)) != -1) {

                    buffer.put(data, 0, read);

                }

                buffer.flip();

                return buffer;

            } finally {

                if (in != null)

                    in.close();

                in = null;

            }

        }

        public static void writeBufferToFile(String name, ByteBuffer buffer) throws IOException {

            File file = new File(name);

            if (!file.exists())

                file.createNewFile();

            FileOutputStream out = new FileOutputStream(name);

            out.write(buffer.array(), 0, buffer.remaining());

            out.flush();

            out.close();

        }

        public static ItemDefinitions forName(String name) {
            for (ItemDefinitions definition : itemsDefinitions) {
                if (definition.name.equalsIgnoreCase(name)) {
                    return definition;
                }
            }
            return null;
        }

        public static LinkedList<String> readFile(String directory) throws IOException {

            LinkedList<String> fileLines = new LinkedList<String>();

            BufferedReader reader = null;

            try {

                reader = new BufferedReader(new FileReader(directory));

                String string;

                while ((string = reader.readLine()) != null) {
                    fileLines.add(string);
                }
            } finally {

                if (reader != null) {

                    reader.close();

                    reader = null;

                }

            }

            return fileLines;

        }

    }


    public String getExamine() {
        return ItemExamines.getExamine(new Item(id));
    }

    public void setModelZoom(int zoom) {
        this.modelZoom = zoom;
    }

    public boolean isTradeable() {
        if (ItemConstants.isTradeable(new Item(id)))
            return true;
        return false;
    }
}