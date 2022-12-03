package com.rs.cache.loaders;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import com.rs.cache.Cache;
import com.rs.io.InputStream;

public final class GeneralRequirementMap {

	private HashMap<Long, Object> values;

	private static final ConcurrentHashMap<Integer, GeneralRequirementMap> maps = new ConcurrentHashMap<Integer, GeneralRequirementMap>();

	public static void main(String[] args) throws IOException {

		Cache.init();

		for (int i = 0; i < 100000; i++) {
			GeneralRequirementMap map = getMap(i);
			if (map == null || map.getValues() == null)
				continue;
				System.out.println(i + " - " + map.getValues().toString());
		}

	}
	
	public static final GeneralRequirementMap getMap(int scriptId) {
		return getMap(scriptId, false);
	}

	public static final GeneralRequirementMap getMap(int scriptId, boolean loadNewFormat) {
		GeneralRequirementMap script = maps.get(scriptId);
		if (script != null)
			return script;
		byte[] data = loadNewFormat ? Cache.STORE.getIndexes()[22].getFile(scriptId / 32, scriptId & 31)
				: Cache.STORE.getIndexes()[2].getFile(26, scriptId);
		script = new GeneralRequirementMap();
		if (data != null)
			script.readValueLoop(new InputStream(data));
		maps.put(scriptId, script);
		return script;

	}

	public HashMap<Long, Object> getValues() {
		return values;
	}

	public Object getValue(long key) {
		if (values == null)
			return null;
		return values.get(key);
	}

	public long getKeyForValue(Object value) {
		for (Long key : values.keySet()) {
			if (values.get(key).equals(value))
				return key;
		}
		return -1;
	}

	public int getSize() {
		if (values == null)
			return 0;
		return values.size();
	}

	public int getIntValue(long key) {
		if (values == null)
			return 0;
		Object value = values.get(key);
		if (value == null || !(value instanceof Integer))
			return 0;
		int v = (Integer) value;
		if (key == 1072 && v > 1) 
			v *= 10;
		return v;
		
	}

	public String getStringValue(long key) {
		if (values == null)
			return "";
		Object value = values.get(key);
		if (value == null || !(value instanceof String))
			return "";
		return (String) value;
	}

	private void readValueLoop(InputStream stream) {
		for (;;) {
			int opcode = stream.readUnsignedByte();
			if (opcode == 0)
				break;
			readValues(stream, opcode);
		}
	}

	private void readValues(InputStream stream, int opcode) {
		if (opcode == 249) {
			int length = stream.readUnsignedByte();
			if (values == null)
				values = new HashMap<Long, Object>(length);
			for (int index = 0; index < length; index++) {
				boolean stringInstance = stream.readUnsignedByte() == 1;
				int key = stream.read24BitInt();
				Object value = stringInstance ? stream.readString() : stream.readInt();
				values.put((long) key, value);
			}
		}
	}

	private GeneralRequirementMap() {
	}
}
