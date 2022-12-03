package com.rs.cache.loaders;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import com.rs.cache.Cache;
import com.rs.io.InputStream;
import com.rs.utils.MusicHints;
import com.rs.utils.Utils;

public final class ClientScriptMap {

	public int keyType;
	public int valueType;
	private String defaultStringValue;
	private int defaultIntValue;
	private HashMap<Long, Object> values;

	private static final ConcurrentHashMap<Integer, ClientScriptMap> interfaceScripts = new ConcurrentHashMap<Integer, ClientScriptMap>();

	public static void main(String[] args) throws IOException {
		// Cache.STORE = new Store("C:/.jagex_cache_32/runescape/");
		Cache.init();
		MusicHints.init();
		//ClientScriptMap names = ClientScriptMap.getMap(1345);
		//ClientScriptMap hint1 = ClientScriptMap.getMap(952);
		// ClientScriptMap hint2 = ClientScriptMap.getMap(1349);
		//System.out.println(hint1);
		for (Object key : ClientScriptMap.getMap(1345).values.keySet()) {
			int id = ClientScriptMap.getMap(1351).getIntValue((long) key);

			ClientScriptMap names = ClientScriptMap.getMap(1345);

			/*
			 * String text = hint.getStringValue(key);
			 * if(text.equals("automatically.")) System.out.println(id);
			 */
			String hint = MusicHints.getHint((int)(long) key);/*
			 * hint1.getValues().containsKey
			 * ((long) key) ? hint1
			 * .getStringValue(key) :
			 * hint2.getStringValue(key);
			 */
			System.out.println("index: "+key+", id: "+id+", name: "+names.getValue((long) key)+", "+hint);
			//	System.out.println(id + ", " + v + "; " + hint + ", "+", "+key);
		}
	}

	public static final ClientScriptMap getMap(int scriptId) {
		ClientScriptMap script = interfaceScripts.get(scriptId);
		if (script != null)
			return script;
		byte[] data = Cache.STORE.getIndexes()[17].getFile(scriptId >>> 0xba9ed5a8, scriptId & 0xff);
		script = new ClientScriptMap();
		if (data != null)
			script.readValueLoop(new InputStream(data));
		interfaceScripts.put(scriptId, script);
		return script;

	}

	public int getDefaultIntValue() {
		return defaultIntValue;
	}

	public String getDefaultStringValue() {
		return defaultStringValue;
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
			return defaultIntValue;
		Object value = values.get(key);
		if (value == null || !(value instanceof Integer))
			return defaultIntValue;
		return (Integer) value;
	}

	public int getKeyIndex(long key) {
		if (values == null)
			return -1;
		int i = 0;
		for (long k : values.keySet()) {
			if (k == key)
				return i;
			i++;
		}
		return -1;
	}

	public int getIntValueAtIndex(int i) {
		if (values == null)
			return -1;
		return (int) values.values().toArray()[i];
	}

	public String getStringValue(long key) {
		if (values == null)
			return defaultStringValue;
		Object value = values.get(key);
		if (value == null || !(value instanceof String))
			return defaultStringValue;
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
		if (opcode == 1) {
			keyType = stream.readByte();//Utils.method2782((byte) stream.readByte());
		}else if (opcode == 2) {
			valueType = stream.readByte();///Utils.method2782((byte) stream.readByte());
		}else if (opcode == 3)
			defaultStringValue = stream.readString();
		else if (opcode == 4)
			defaultIntValue = stream.readInt();
		else if (opcode == 5 || opcode == 6 || opcode == 7 || opcode == 8) {
			int count = stream.readUnsignedShort();
			int loop = opcode == 7 || opcode == 8 ? stream.readUnsignedShort() : count;
			if (values == null)
				values = new HashMap<Long, Object>(Utils.getHashMapSize(count));
			for (int i = 0; i < loop; i++) {
				int key = opcode == 7 || opcode == 8 ? stream.readUnsignedShort() : stream.readInt();
				Object value = opcode == 5 || opcode == 7 ? stream.readString() : stream.readInt();
				values.put((long) key, value);
			}
		} else if (opcode == 101) {
			keyType = stream.readSmart();
		} else if (opcode == 102) {
			valueType = stream.readSmart();
		} else {
			System.err.println("Missing Opcode: "+opcode);
		}
	}

	private ClientScriptMap() {
		defaultStringValue = "null";
	}
}
