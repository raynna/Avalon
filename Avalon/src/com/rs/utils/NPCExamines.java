package com.rs.utils;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.HashMap;

import com.rs.cache.loaders.NPCDefinitions;

/**
 * @author Savions Sw
 *
 */
public class NPCExamines {

	private final static HashMap<Integer, String> npcExamines = new HashMap<Integer, String>(
			Utils.getNPCDefinitionsSize());

	public static final String getExamine(final int id) {
		String examine = npcExamines.get(id);
		return examine == null || examine.length() < 1 ? "A " + NPCDefinitions.getNPCDefinitions(id).name + "."
				: examine;
	}

	public static void loadPackedExamines() {
		try {
			RandomAccessFile in = new RandomAccessFile("data/npcs/packedNpcExamines.e", "r");
			FileChannel channel = in.getChannel();
			ByteBuffer buffer = channel.map(MapMode.READ_ONLY, 0, channel.size());
			while (buffer.hasRemaining())
				npcExamines.put(buffer.getShort() & 0xffff, readString(buffer));
			channel.close();
			in.close();
		} catch (Throwable e) {
			Logger.handle(e);
		}
	}

	public static String readString(ByteBuffer buffer) {
		int count = buffer.get() & 0xff;
		byte[] bytes = new byte[count];
		buffer.get(bytes, 0, count);
		return new String(bytes);
	}
}