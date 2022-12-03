package com.rs.utils;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.rs.game.npc.Drop;

public class NPCDrops {

	private final static String PACKED_PATH = "data/npcs/packedDrops.d";
	private static HashMap<Integer, Drop[]> npcDrops;

	public static final void init() {
		loadPackedNPCDrops();
	}

	public static Drop[] getDrops(int npcId) {
		return npcDrops.get(npcId);
	}

	private Map<Integer, ArrayList<Drop>> dropMapx = null;

	public Map<Integer, ArrayList<Drop>> getDropArray() {

		if (dropMapx == null)
			dropMapx = new LinkedHashMap<Integer, ArrayList<Drop>>();
		// dropMapx = new LinkedHashMap<Integer, ArrayList<Drop>>();
		for (int i : npcDrops.keySet()) {
			int npcId = i;
			ArrayList<Drop> temp = new ArrayList<Drop>();
			for (Drop mainDrop : npcDrops.get(npcId)) {
				temp.add(mainDrop);
			}
			dropMapx.put(i, temp);
		}

		return dropMapx;
	}

	public void insertDrop(int npcID, Drop d) {
		loadPackedNPCDrops();
		Drop[] oldDrop = npcDrops.get(npcID);
		if (oldDrop == null) {
			npcDrops.put(npcID, new Drop[] { d });
		} else {
			int length = oldDrop.length;
			Drop destination[] = new Drop[length + 1];
			System.arraycopy(oldDrop, 0, destination, 0, length);
			destination[length] = d;
			npcDrops.put(npcID, destination);
		}
	}

	private static void loadPackedNPCDrops() {
		try {
			RandomAccessFile in = new RandomAccessFile(PACKED_PATH, "r");
			FileChannel channel = in.getChannel();
			ByteBuffer buffer = channel.map(MapMode.READ_ONLY, 0, channel.size());
			int dropSize = buffer.getShort() & 0xffff;
			npcDrops = new HashMap<Integer, Drop[]>(dropSize);
			for (int i = 0; i < dropSize; i++) {
				int npcId = buffer.getShort() & 0xffff;
				Drop[] drops = new Drop[buffer.getShort() & 0xffff];
				for (int d = 0; d < drops.length; d++) {
					if (buffer.get() == 0)
						drops[d] = new Drop(buffer.getShort() & 0xffff, buffer.getInt(), buffer.getInt(),
								buffer.getInt());
					else
						drops[d] = new Drop(0, 0, 0, 0);

				}
				npcDrops.put(npcId, drops);
			}
			channel.close();
			in.close();
		} catch (Throwable e) {
			Logger.handle(e);
		}
	}

	public HashMap<Integer, Drop[]> getDropMap() {
		return npcDrops;
	}
}