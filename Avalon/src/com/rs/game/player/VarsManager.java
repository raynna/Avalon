package com.rs.game.player;

import com.rs.cache.Cache;
import com.rs.cache.loaders.VarBitDefinitions;

public class VarsManager {

	private static final int[] masklookup = new int[32];

	static {
		int i = 2;
		for (int i2 = 0; i2 < 32; i2++) {
			masklookup[i2] = i - 1;
			i += i;
		}
	}

	private int[] values;
	private transient Player player;

	public VarsManager(Player player) {
		this.player = player;
		values = new int[Cache.STORE.getIndexes()[2].getLastFileId(16) + 1];
	}

	public void sendVar(int id, int value) {
		sendVar(id, value, false);
	}

	public void forceSendVar(int id, int value) {
		sendVar(id, value, true);
	}

	private void sendVar(int id, int value, boolean force) {
		if (id < -1 || id >= values.length - 1)
			return;
		if (force || values[id] == value)
			return;
		setVar(id, value);
		sendClientVarp(id, false);
	}

	public void setVar(int id, int value) {
		if (id == -1)
			return;
		values[id] = value;
	}

	public int getValue(int id) {
		return values[id];
	}

	public void setVarBit(int id, int value, boolean save) {
		setVarBit(id, value, 0, save);
	}

	public void sendVarBit(int id, int value, boolean save) {
		setVarBit(id, value, 0x1, save);
	}

	public void forceSendVarBit(int id, int value) {
		setVarBit(id, value, 0x1 | 0x2, false);
	}

	public void sendVarBit(int id, int value) {
		setVarBit(id, value, 0x1, false);
	}

	public void setVarBit(int id, int value) {
		setVarBit(id, value, 0, false);
	}

	public int getBitValue(int id) {
		VarBitDefinitions defs = VarBitDefinitions.getClientVarpBitDefinitions(id);
		return values[defs.baseVar] >> defs.startBit & masklookup[defs.endBit - defs.startBit];
	}

	private void setVarBit(int id, int value, int flag, boolean save) {
		if (id == -1)
			return;
		VarBitDefinitions defs = VarBitDefinitions.getClientVarpBitDefinitions(id);
		int mask = masklookup[defs.endBit - defs.startBit];
		if (value < 0 || value > mask)
			value = 0;
		mask <<= defs.startBit;
		int varpValue = (values[defs.baseVar] & (mask ^ 0xffffffff) | value << defs.startBit & mask);
		if ((flag & 0x2) != 0 || varpValue != values[defs.baseVar]) {
			setVar(defs.baseVar, varpValue);
			if ((flag & 0x1) != 0)
				sendClientVarp(defs.baseVar, save);
			player.getTemporaryVarBits().put(id, value);
			if (save) {
				System.out.println("Save varbit: " + id + " - " + value);
				player.getSavedVarBits().put(id, value);
			}
		}
	}

	private void sendClientVarp(int id, boolean save) {
		player.getPackets().sendVar(id, values[id]);
	}
}
