package com.rs.utils;

import com.rs.cache.loaders.ItemDefinitions;

public final class PKPPrices {

	public static int getPrice(int itemId) {
		ItemDefinitions defs = ItemDefinitions.getItemDefinitions(itemId);
		@SuppressWarnings("unused")
		ItemDefinitions defs2 = ItemDefinitions.getItemDefinitions(defs.getCertId());
		if (itemId == 11724)
			return 200;
		return defs.getValue();
	}

	private PKPPrices() {

	}
}
