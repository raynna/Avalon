package com.rs.game.player.dialogues;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.player.content.grandexchange.GrandExchange;
import com.rs.game.player.content.grandexchange.LimitedGEReader;
import com.rs.game.player.content.grandexchange.UnlimitedGEReader;
import com.rs.utils.Utils;

public class PriceChecker extends Dialogue {

	int itemId;
	int cheapestPrice;

	@Override
	public void start() {
		itemId = (Integer) parameters[0];
		int sellprice = GrandExchange.getCheapestSellPrice(itemId);
		int sellamount = GrandExchange.getSellQuantity(itemId);
		int buyprice = GrandExchange.getBestBuyPrice(itemId);
		int buyamount = GrandExchange.getBuyQuantity(itemId);
		if (GrandExchange.getPrice(itemId) >= 250000 && !UnlimitedGEReader.itemIsLimited(itemId)
				|| LimitedGEReader.itemIsLimited(itemId))
			sendEntityDialogue(SEND_ITEM_DIALOGUE,
					new String[] { ItemDefinitions.getItemDefinitions(itemId).name,
							ItemDefinitions.getItemDefinitions(itemId).getName() + " Price: "
									+ Utils.getFormattedNumber(
											ItemDefinitions.getItemDefinitions(itemId).getTipitPrice(), ',')
									+ " gp<br>" + "Cheapest Selling Price: "
									+ (sellprice != 0 ? Utils.getFormattedNumber(sellprice, ',') : "None")
									+ "<br>Highest Buy Price: "
									+ (buyprice != 0 ? Utils.getFormattedNumber(buyprice, ',') : "None") + "" + "<br>"
									+ "Total Sell:Buy Quantity: " + Utils.getFormattedNumber(sellamount, ',') + " : "
									+ Utils.getFormattedNumber(buyamount, ',') },
					IS_ITEM, itemId, -1);
		else
			sendEntityDialogue(SEND_ITEM_DIALOGUE,
					new String[] { ItemDefinitions.getItemDefinitions(itemId).name,
							ItemDefinitions.getItemDefinitions(itemId).getName() + " Price: "
									+ Utils.getFormattedNumber(
											ItemDefinitions.getItemDefinitions(itemId).getTipitPrice(), ',')
							+ "<br>This item will instantly buy or sell for +5%/-5%" },
					IS_ITEM, itemId, -1);
		stage = 1;

	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case END:
			end();
			break;
		case 1:
			sendOptionsDialogue(TITLE, "Search for another item", "Close");
			stage = 2;
			break;
		case 2:
			switch (componentId) {
			case OPTION_1:
				player.getPackets().sendConfig(1109, -1);
				player.getPackets().sendConfig1(1241, 16750848);
				player.getPackets().sendConfig1(1242, 15439903);
				player.getPackets().sendConfig1(741, -1);
				player.getPackets().sendConfig1(743, -1);
				player.getPackets().sendConfig1(744, 0);
				player.getPackets().sendInterface(true, 752, 7, 389);
				player.getPackets().sendRunScript(570, new Object[] { "Price checker" });
				break;
			case OPTION_2:
				end();

			}
			break;
		}

	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}
