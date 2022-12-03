package com.rs.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.rs.cache.Cache;
import com.rs.utils.ItemBonuses;
import com.rs.utils.Utils;

/**
 * @author Frosty Teh Snowman
 * 
 *         Unpacks the Item Bonuses that are packed into the bonuses.ib file.
 */
public class ItemBonusesUnpacker {

	public static final void main(String[] args) {
		log("Unpacker Started.");
		try {
			Cache.init();
			log("Cache Initiated.");
		} catch (IOException e) {
			log("Exception in initializing cache.");
		}
		ItemBonuses.init();
		log("Item Bonuses Initiated.");
		for (int itemId = 0; itemId < Utils.getItemDefinitionsSize(); itemId++) {
			log("Getting Values for item " + itemId);
			int[] bonuses = ItemBonuses.getItemBonuses(itemId);
			File file = new File("data/items/bonuses/" + itemId + ".txt");
			try {
				if (file.createNewFile()) {
					BufferedWriter writer = new BufferedWriter(new FileWriter(file));
					writer.write("Attack Bonus");
					writer.newLine();
					writer.write(Integer.toString(bonuses[0]));
					writer.newLine();
					writer.write(Integer.toString(bonuses[1]));
					writer.newLine();
					writer.write(Integer.toString(bonuses[2]));
					writer.newLine();
					writer.write(Integer.toString(bonuses[3]));
					writer.newLine();
					writer.write(Integer.toString(bonuses[4]));
					writer.newLine();
					writer.write("Defense Bonus");
					writer.newLine();
					writer.write(Integer.toString(bonuses[5]));
					writer.newLine();
					writer.write(Integer.toString(bonuses[6]));
					writer.newLine();
					writer.write(Integer.toString(bonuses[7]));
					writer.newLine();
					writer.write(Integer.toString(bonuses[8]));
					writer.newLine();
					writer.write(Integer.toString(bonuses[9]));
					writer.newLine();
					writer.write(Integer.toString(bonuses[10]));
					writer.newLine();
					writer.write("Damage Absorption");
					writer.newLine();
					writer.write(Integer.toString(bonuses[11]));
					writer.newLine();
					writer.write(Integer.toString(bonuses[12]));
					writer.newLine();
					writer.write(Integer.toString(bonuses[13]));
					writer.newLine();
					writer.write("Other Bonuses");
					writer.newLine();
					writer.write(Integer.toString(bonuses[14]));
					writer.newLine();
					writer.write(Integer.toString(bonuses[15]));
					writer.newLine();
					writer.write(Integer.toString(bonuses[16]));
					writer.newLine();
					writer.write(Integer.toString(bonuses[17]));
					writer.flush();
					writer.close();
				}
			} catch (Exception e) {
			}
		}
		log("Finished Unpacking.");
	}

	private static void log(String s) {
		System.out.println(s);
	}

}