package com.rs.utils;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.HashMap;

public class WeaponTypesLoader {
	
	private static final String PATH = "./data/items/weapons.dat";
	private static WeaponType DEFAULT_WEAPON_DEFINITION = new WeaponType(0, 0);

	private final static HashMap<Integer, WeaponType> weaponDefs = new HashMap<Integer, WeaponType>();
	
	private static void loadNext() {
		weaponDefs.put(812, new WeaponType(1, 10)); //Bronze dart (p)
		weaponDefs.put(813, new WeaponType(1, 10)); //Iron dart (p)
		weaponDefs.put(832, new WeaponType(1, 10)); //Iron javelin (p)
		weaponDefs.put(833, new WeaponType(1, 10)); //Steel javelin (p)
		weaponDefs.put(834, new WeaponType(1, 10)); //Mithril javelin (p)
		weaponDefs.put(835, new WeaponType(1, 10)); //Adamant javelin (p)
		weaponDefs.put(836, new WeaponType(1, 10)); //Rune javelin (p)
		weaponDefs.put(870, new WeaponType(1, 10)); //Bronze knife (p)
		weaponDefs.put(872, new WeaponType(1, 10)); //Steel knife (p)
		weaponDefs.put(873, new WeaponType(1, 10)); //Mithril knife (p)
		weaponDefs.put(874, new WeaponType(1, 10)); //Black knife (p)
		weaponDefs.put(875, new WeaponType(1, 10)); //Adamant knife (p)
		weaponDefs.put(876, new WeaponType(1, 10)); //Rune knife (p)
		weaponDefs.put(1233, new WeaponType(0, 5)); //Black dagger (p)
		weaponDefs.put(1251, new WeaponType(0, 5)); //Bronze spear (p)
		weaponDefs.put(1253, new WeaponType(0, 5)); //Iron spear (p)
		weaponDefs.put(1255, new WeaponType(0, 5)); //Steel spear (p)
		weaponDefs.put(1257, new WeaponType(0, 5)); //Mithril spear (p)
		weaponDefs.put(1259, new WeaponType(0, 5)); //Adamant spear (p)
		weaponDefs.put(1261, new WeaponType(0, 5)); //Rune spear (p)
		weaponDefs.put(1263, new WeaponType(0, 5)); //Dragon spear (p)
		weaponDefs.put(3094, new WeaponType(1, 10)); //Black dart (p)
		weaponDefs.put(4582, new WeaponType(0, 5)); //Black spear (p)
		weaponDefs.put(5628, new WeaponType(1, 10)); //Bronze dart (p+)
		weaponDefs.put(5629, new WeaponType(1, 10)); //Iron dart (p+)
		weaponDefs.put(5630, new WeaponType(1, 10)); //Steel dart (p+)
		weaponDefs.put(5632, new WeaponType(1, 10)); //Mithril dart (p+)
		weaponDefs.put(5633, new WeaponType(1, 10)); //Adamant dart (p+)
		weaponDefs.put(5634, new WeaponType(1, 10)); //Rune dart (p+)
		weaponDefs.put(5635, new WeaponType(1, 10)); //Bronze dart (p++)
		weaponDefs.put(5636, new WeaponType(1, 10)); //Iron dart (p++)
		weaponDefs.put(5637, new WeaponType(1, 10)); //Steel dart (p++)
		weaponDefs.put(5639, new WeaponType(1, 10)); //Mithril dart (p++)
		weaponDefs.put(5640, new WeaponType(1, 10)); //Adamant dart (p++)
		weaponDefs.put(5641, new WeaponType(1, 10)); //Rune dart (p++)
		weaponDefs.put(5642, new WeaponType(1, 10)); //Bronze javelin (p+)
		weaponDefs.put(5643, new WeaponType(1, 10)); //Iron javelin (p+)
		weaponDefs.put(5644, new WeaponType(1, 10)); //Steel javelin (p+)
		weaponDefs.put(5645, new WeaponType(1, 10)); //Mithril javelin (p+)
		weaponDefs.put(5646, new WeaponType(1, 10)); //Adamant javelin (p+)
		weaponDefs.put(5647, new WeaponType(1, 10)); //Rune javelin (p+)
		weaponDefs.put(5649, new WeaponType(1, 10)); //Iron javelin (p++)
		weaponDefs.put(5650, new WeaponType(1, 10)); //Steel javelin (p++)
		weaponDefs.put(5651, new WeaponType(1, 10)); //Mithril javelin (p++)
		weaponDefs.put(5652, new WeaponType(1, 10)); //Adamant javelin (p++)
		weaponDefs.put(5653, new WeaponType(1, 10)); //Rune javelin (p++)
		weaponDefs.put(5654, new WeaponType(1, 10)); //Bronze knife (p+)
		weaponDefs.put(5655, new WeaponType(1, 10)); //Iron knife (p+)
		weaponDefs.put(5656, new WeaponType(1, 10)); //Steel knife (p+)
		weaponDefs.put(5657, new WeaponType(1, 10)); //Mithril knife (p+)
		weaponDefs.put(5658, new WeaponType(1, 10)); //Black knife (p+)
		weaponDefs.put(5659, new WeaponType(1, 10)); //Adamant knife (p+)
		weaponDefs.put(5660, new WeaponType(1, 10)); //Rune knife (p+)
		weaponDefs.put(5661, new WeaponType(1, 10)); //Bronze knife (p++)
		weaponDefs.put(5662, new WeaponType(1, 10)); //Iron knife (p++)
		weaponDefs.put(5663, new WeaponType(1, 10)); //Steel knife (p++)
		weaponDefs.put(5664, new WeaponType(1, 10)); //Mithril knife (p++)
		weaponDefs.put(5665, new WeaponType(1, 10)); //Black knife (p++)
		weaponDefs.put(5666, new WeaponType(1, 10)); //Adamant knife (p++)
		weaponDefs.put(5667, new WeaponType(1, 10)); //Rune knife (p++)
		weaponDefs.put(5668, new WeaponType(0, 5)); //Iron dagger (p+)
		weaponDefs.put(5670, new WeaponType(0, 5)); //Bronze dagger (p+)
		weaponDefs.put(5672, new WeaponType(0, 5)); //Steel dagger (p+)
		weaponDefs.put(5674, new WeaponType(0, 5)); //Mithril dagger (p+)
		weaponDefs.put(5676, new WeaponType(0, 5)); //Adamant dagger (p+)
		weaponDefs.put(5678, new WeaponType(0, 5)); //Rune dagger (p+)
		weaponDefs.put(5678, new WeaponType(0, 5)); //Rune dagger (p+)
		weaponDefs.put(5680, new WeaponType(0, 5)); //Dragon dagger (p+)
		weaponDefs.put(5682, new WeaponType(0, 5)); //Black dagger (p+)
		weaponDefs.put(5686, new WeaponType(0, 5)); //Iron dagger (p++)
		weaponDefs.put(5688, new WeaponType(0, 5)); //Bronze dagger (p++)
		weaponDefs.put(5690, new WeaponType(0, 5)); //Steel dagger (p++)
		weaponDefs.put(5692, new WeaponType(0, 5)); //Mithril dagger (p++)
		weaponDefs.put(5694, new WeaponType(0, 5)); //Adamant dagger (p++)
		weaponDefs.put(5696, new WeaponType(0, 5)); //Rune dagger (p++)
		weaponDefs.put(5696, new WeaponType(0, 5)); //Rune dagger (p++)
		weaponDefs.put(5698, new WeaponType(0, 5)); //Dragon dagger (p++)
		weaponDefs.put(5700, new WeaponType(0, 5)); //Black dagger (p++)
		weaponDefs.put(5704, new WeaponType(0, 5)); //Bronze spear (p+)
		weaponDefs.put(5706, new WeaponType(0, 5)); //Iron spear (p+)
		weaponDefs.put(5708, new WeaponType(0, 5)); //Steel spear (p+)
		weaponDefs.put(5710, new WeaponType(0, 5)); //Mithril spear (p+)
		weaponDefs.put(5712, new WeaponType(0, 5)); //Adamant spear (p+)
		weaponDefs.put(5714, new WeaponType(0, 5)); //Rune spear (p+)
		weaponDefs.put(5714, new WeaponType(0, 5)); //Rune spear (p+)
		weaponDefs.put(5716, new WeaponType(0, 5)); //Dragon spear (p+)
		weaponDefs.put(5716, new WeaponType(0, 5)); //Dragon spear (p+)
		weaponDefs.put(5718, new WeaponType(0, 5)); //Bronze spear (p++)
		weaponDefs.put(5720, new WeaponType(0, 5)); //Iron spear (p++)
		weaponDefs.put(5722, new WeaponType(0, 5)); //Steel spear (p++)
		weaponDefs.put(5724, new WeaponType(0, 5)); //Mithril spear (p++)
		weaponDefs.put(5726, new WeaponType(0, 5)); //Adamant spear (p++)
		weaponDefs.put(5728, new WeaponType(0, 5)); //Rune spear (p++)
		weaponDefs.put(5728, new WeaponType(0, 5)); //Rune spear (p++)
		weaponDefs.put(5730, new WeaponType(0, 5)); //Dragon spear (p++)
		weaponDefs.put(5730, new WeaponType(0, 5)); //Dragon spear (p++)
		weaponDefs.put(5734, new WeaponType(0, 5)); //Black spear (p+)
		weaponDefs.put(5736, new WeaponType(0, 5)); //Black spear (p++)
		weaponDefs.put(6593, new WeaponType(0, 5)); //White dagger (p)
		weaponDefs.put(6595, new WeaponType(0, 5)); //White dagger (p+)
		weaponDefs.put(6597, new WeaponType(0, 5)); //White dagger (p++)
		weaponDefs.put(8874, new WeaponType(0, 5)); //Bone dagger (p)
		weaponDefs.put(10582, new WeaponType(0, 5)); //Keris (p)
		weaponDefs.put(10583, new WeaponType(0, 5)); //Keris (p+)
		weaponDefs.put(10584, new WeaponType(0, 5)); //Keris (p++)
		weaponDefs.put(11231, new WeaponType(1, 10)); //Dragon dart (p)
		weaponDefs.put(1231, new WeaponType(0, 5));//Dragon dagger (p)
		weaponDefs.put(13880, new WeaponType(1, 10));//Morrigan's javelin (p)
		weaponDefs.put(13881, new WeaponType(1, 10));//Morrigan's javelin (p+)
		weaponDefs.put(13882, new WeaponType(1, 10));//Morrigan's javelin (p++)
		weaponDefs.put(13954, new WeaponType(1, 10));//C. morrigan's javelin (p)
		weaponDefs.put(13955, new WeaponType(1, 10));//C. morrigan's javelin (p+)
		weaponDefs.put(13956, new WeaponType(1, 10));//C. morrigan's javelin (p++)
		weaponDefs.put(13466, new WeaponType(0, 5)); //Dragon dagger (p)
		weaponDefs.put(13467, new WeaponType(0, 5)); //Dragon dagger (p+)
		weaponDefs.put(13468, new WeaponType(0, 5)); //Dragon dagger (p++)
		weaponDefs.put(13766, new WeaponType(0, 5)); //Rune dagger (p)
		weaponDefs.put(13767, new WeaponType(0, 5)); //Rune dagger (p+)
		weaponDefs.put(13768, new WeaponType(0, 5)); //Rune dagger (p++)
		weaponDefs.put(13771, new WeaponType(0, 5)); //Rune spear (p)
		weaponDefs.put(13772, new WeaponType(0, 5)); //Dragon spear (p)
		weaponDefs.put(13773, new WeaponType(0, 5)); //Rune spear (p+)
		weaponDefs.put(13774, new WeaponType(0, 5)); //Dragon spear (p+)
		weaponDefs.put(13775, new WeaponType(0, 5)); //Rune spear (p++)
		weaponDefs.put(13776, new WeaponType(0, 5)); //Dragon spear (p++)
		weaponDefs.put(15849, new WeaponType(0, 5)); //Novite dagger (p) (b)
		weaponDefs.put(15853, new WeaponType(0, 5)); //Bathus dagger (p) (b)
		weaponDefs.put(15889, new WeaponType(0, 5)); //Primal dagger (p) (b)
		weaponDefs.put(16219, new WeaponType(0, 5)); //Novite spear (p) (b)
		weaponDefs.put(16220, new WeaponType(0, 5)); //Novite spear (p+) (b)
		weaponDefs.put(16221, new WeaponType(0, 5)); //Novite spear (p++) (b)
		weaponDefs.put(16223, new WeaponType(0, 5)); //Bathus spear (p) (b)
		weaponDefs.put(16224, new WeaponType(0, 5)); //Bathus spear (p+) (b)
		weaponDefs.put(16225, new WeaponType(0, 5)); //Bathus spear (p++) (b)
		weaponDefs.put(16228, new WeaponType(0, 5)); //Marmaros spear (p+) (b)
		weaponDefs.put(16229, new WeaponType(0, 5)); //Marmaros spear (p++) (b)
		weaponDefs.put(16236, new WeaponType(0, 5)); //Fractite spear (p+) (b)
		weaponDefs.put(16237, new WeaponType(0, 5)); //Fractite spear (p++) (b)
		weaponDefs.put(16240, new WeaponType(0, 5)); //Zephyrium spear (p+) (b)
		weaponDefs.put(16241, new WeaponType(0, 5)); //Zephyrium spear (p++) (b)
		weaponDefs.put(16243, new WeaponType(0, 5)); //Argonite spear (p) (b)
		weaponDefs.put(16244, new WeaponType(0, 5)); //Argonite spear (p+) (b)
		weaponDefs.put(16245, new WeaponType(0, 5)); //Argonite spear (p++) (b)
		weaponDefs.put(16247, new WeaponType(0, 5)); //Katagon spear (p) (b)
		weaponDefs.put(16251, new WeaponType(0, 5)); //Gorgonite spear (p) (b)
		weaponDefs.put(16252, new WeaponType(0, 5)); //Gorgonite spear (p+) (b)
		weaponDefs.put(16253, new WeaponType(0, 5)); //Gorgonite spear (p++) (b)
		weaponDefs.put(16255, new WeaponType(0, 5)); //Promethium spear (p) (b)
		weaponDefs.put(16259, new WeaponType(0, 5)); //Primal spear (p) (b)
		weaponDefs.put(16759, new WeaponType(0, 5)); //Novite dagger (p)
		weaponDefs.put(16761, new WeaponType(0, 5)); //Novite dagger (p+)
		weaponDefs.put(16763, new WeaponType(0, 5)); //Novite dagger (p++)
		weaponDefs.put(16767, new WeaponType(0, 5)); //Bathus dagger (p)
		weaponDefs.put(16801, new WeaponType(0, 5)); //Zephyrium dagger (p+)
		weaponDefs.put(16803, new WeaponType(0, 5)); //Zephyrium dagger (p++)
		weaponDefs.put(16807, new WeaponType(0, 5)); //Argonite dagger (p)
		weaponDefs.put(16815, new WeaponType(0, 5)); //Katagon dagger (p)
		weaponDefs.put(16823, new WeaponType(0, 5)); //Gorgonite dagger (p)
		weaponDefs.put(16831, new WeaponType(0, 5)); //Promethium dagger (p)
		weaponDefs.put(16833, new WeaponType(0, 5)); //Promethium dagger (p+)
		weaponDefs.put(16835, new WeaponType(0, 5)); //Promethium dagger (p++)
		weaponDefs.put(17073, new WeaponType(0, 5)); //Bathus spear (p)
		weaponDefs.put(17075, new WeaponType(0, 5)); //Bathus spear (p+)
		weaponDefs.put(17077, new WeaponType(0, 5)); //Bathus spear (p++)
		weaponDefs.put(17089, new WeaponType(0, 5)); //Kratonite spear (p)
		weaponDefs.put(17091, new WeaponType(0, 5)); //Kratonite spear (p+)
		weaponDefs.put(17093, new WeaponType(0, 5)); //Kratonite spear (p++)
		weaponDefs.put(17105, new WeaponType(0, 5)); //Zephyrium spear (p)
		weaponDefs.put(17107, new WeaponType(0, 5)); //Zephyrium spear (p+)
		weaponDefs.put(17109, new WeaponType(0, 5)); //Zephyrium spear (p++)
		weaponDefs.put(17137, new WeaponType(0, 5)); //Promethium spear (p)
		weaponDefs.put(17139, new WeaponType(0, 5)); //Promethium spear (p+)
		weaponDefs.put(17141, new WeaponType(0, 5)); //Promethium spear (p++)
		weaponDefs.put(25110, new WeaponType(0, 5)); //Royal court lance (spear)
		weaponDefs.put(25112, new WeaponType(0, 5)); //Royal court lance (rapier)		
	}
	
	public static void loadDefinitions() {
		try {
			RandomAccessFile in = new RandomAccessFile(PATH, "r");
			FileChannel channel = in.getChannel();
			ByteBuffer buffer = channel.map(MapMode.READ_ONLY, 0, channel.size());
			while (buffer.hasRemaining()) {
				int id = buffer.getShort() & 0xffff;
				short type = buffer.getShort();
				short style = buffer.getShort();
				weaponDefs.put(id, new WeaponType(Math.max(0, type), Math.max(0, style)));
			}
			channel.close();
			in.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		loadNext();
	}
	
	public static WeaponType getWeaponDefinition(int id) {
		WeaponType def = weaponDefs.get(id);
		if (def == null)
			def = DEFAULT_WEAPON_DEFINITION;
		return def;
	}
	
	public static class WeaponType {
		private final int type;
		private final int style;
		
		public WeaponType(int type, int style) {
			this.type = type;
			this.style = style;
		}

		public int getType() {
			return type;
		}

		public int getStyle() {
			return style;
		}
	}
}
