package com.rs.game.player.content;

import java.io.Serializable;

import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;

public class ArtisanWorkshop implements Serializable {
	
	/**
	 * seriazizable id
	 */
	private static final long serialVersionUID = -8945818662300168016L;
    /**
     * player stuff
     */
	private transient Player player;
	
	public void setPlayer(Player player){
		this.player = player;
	}
	
	public boolean quick_Learner,budding_Student,master_Student,expert,golden_Cannon,royal_Cannon,restocking;
	/**
	 * stored ores
	 */
	private int ironOre,mithrilOre,adamantOre,runiteOre,coal;
	/**
	 *  respect
	 */
	private int respect = 0;
	
	private double counter = 0;
	
	/**
 	* enum of all the ingots and their values
 	*
 	*/
	public enum Ingots{
		IRON_I(20632,30,101, 	new Item[]  {new Item(440,1)} , new Item[] {new Item(20572,1),new Item(20577,1),new Item(20582,1),new Item(20587,1)}),
		IRON_II(20637,30,202,   new Item[]  {new Item(440,9)}, new Item[] {new Item(20592,1),new Item(20597,1),new Item(20602,1),new Item(20607,1)}),
		IRON_III(20642,30,240,  new Item[]  {new Item(440,12)}, new Item[] {new Item(20612,1),new Item(20617,1),new Item(20622,1),new Item(20627,1)}),
		
		STEEL_I(20633,45,131, 	new Item[]   {new Item(440,1), new Item(453,2)} , new Item[] {new Item(20573,1),new Item(20578,1),new Item(20583,1),new Item(20588,1)}),
		STEEL_II(20638,45,253,   new Item[]  {new Item(440,4), new Item(453,7)}, new Item[] {new Item(20593,1),new Item(20598,1),new Item(20603,1),new Item(20608,1)}),
		STEEL_III(20643,45,354,  new Item[]  {new Item(440,9), new Item(453,17)}, new Item[] {new Item(20613,1),new Item(20618,1),new Item(20623,1),new Item(20628,1)}),
		
		MITHRIL_I(20634,60,164, 	new Item[] {new Item(447,1), new Item(453,4)} , new Item[] {new Item(20574,1),new Item(20579,1),new Item(20584,1),new Item(20589,1)}),
		MITHRIL_II(20639,60,316,   new Item[]  {new Item(447,3), new Item(453,12)}, new Item[] {new Item(20594,1),new Item(20599,1),new Item(20604,1),new Item(20609,1)}),
		MITHRIL_III(20644,60,404,  new Item[]  {new Item(447,6), new Item(453,24)}, new Item[] {new Item(20614,1),new Item(20619,1),new Item(20624,1),new Item(20629,1)}),
		
		ADAMANT_I(20635,70,278, 	new Item[] {new Item(449,1), new Item(453,6)} ,  new Item[] {new Item(20575,1),new Item(20580,1),new Item(20585,1),new Item(20590,1)}),
		ADAMANT_II(20640,70,455,   new Item[]  {new Item(449,3), new Item(453,14)} , new Item[] {new Item(20595,1),new Item(20600,1),new Item(20605,1),new Item(20610,1)}),
		ADAMANT_III(20645,70,568,  new Item[]  {new Item(449,4), new Item(453,22)} , new Item[] {new Item(20615,1),new Item(20620,1),new Item(20625,1),new Item(20630,1)}),
		
		RUNITE_I(20636,90,505, 	  new Item[]  {new Item(451,1), new Item(453,8)} ,  new Item[] {new Item(20576,1),new Item(20581,1),new Item(20586,1),new Item(20591,1)}),
		RUNITE_II(20641,90,631,   new Item[]  {new Item(451,2), new Item(453,16)}, 	new Item[] {new Item(20596,1),new Item(20601,1),new Item(20606,1),new Item(20611,1)}),
		RUNITE_III(20646,90,758,  new Item[]  {new Item(451,4), new Item(453,30)}, 	new Item[] {new Item(20616,1),new Item(20621,1),new Item(20626,1),new Item(20631,1)});
		
		int barId, level;
		Item ores[], products[];
		double exp;
		
		Ingots(int id, int level,double exp, Item ores[], Item products[]){
			this.barId = id;
			this.level = level;
			this.exp = exp;
			this.ores = ores;
			this.products = products;
		}
		public int getBarId(){
			return barId;
		}
		public int getLevel(){
			return level;
		}
		public Item[] getProducts(){
			return products;
		}
		
		public Item getProduct(int index){
			return products[index];
		}
		public double getExp(){
			return exp;
		}
		public static Ingots forId(Ingots ing) {
			for (Ingots ingot : Ingots.values()) {
				if (ingot == ing) {
					return ingot;
				}
			}
			return null;
		}
		public static Ingots getBar(Player player) {
			int smithLevel = player.getSkills().getLevel(Skills.SMITHING);
			for(Ingots bar : Ingots.values()) {
				if(smithLevel < bar.getLevel() || !player.getInventory().containsItem(bar.getBarId(), 1))
					continue;
				return bar;
			}
			return null;
		}
	}
	/**
	 * enum of all thye ingots
	 */
	private Ingots ignots_I[] =  {Ingots.IRON_I, Ingots.STEEL_I,Ingots.MITHRIL_I,Ingots.ADAMANT_I,Ingots.RUNITE_I};
	private Ingots ignots_II[]  = {Ingots.IRON_II,Ingots.STEEL_II,Ingots.MITHRIL_II,Ingots.ADAMANT_II,Ingots.RUNITE_II};
	private Ingots ignots_III[] = {Ingots.IRON_III,Ingots.STEEL_III,Ingots.MITHRIL_III,Ingots.ADAMANT_III, Ingots.RUNITE_III};
	/**
	 * deletes all the items
	 */
	public void depositArmour(){
		for(Ingots bars : Ingots.values()){
			for(int i = 0; i < bars.products.length; i ++){
				int amount = player.getInventory().getAmountOf(bars.products[i].getId());
				if (amount > 0) {
					player.getInventory().deleteItem(bars.products[i].getId(), amount);
				}
			}
		}
		player.getInventory().refresh();
			
	}
	public void returnOres(int oreId){
		 switch(oreId){
		 case 440:
			 player.getInventory().addItem(new Item(441,ironOre));
			 ironOre = 0;
			 break;
		 case 447:
			 player.getInventory().addItem(new Item(448,mithrilOre));
			 mithrilOre = 0;
			 break;
		 case 449:
			 player.getInventory().addItem(new Item(450,adamantOre));
			 adamantOre = 0;
			 break;
		 case 451:
			 player.getInventory().addItem(new Item(452,runiteOre));
			 runiteOre= 0;
			 break;
		 case 453:
			 player.getInventory().addItem(new Item(454,coal));
			 coal = 0;
			 break;
		 }
	}
	/**
	 * deposits ingots and returns ores into the deposit
	 */
	public void despositIngots(){
		for(Ingots ing : Ingots.values()){
			int amount = player.getInventory().getAmountOf(ing.getBarId());
			depositOres(ing.ores[0].getId(),ing.ores[0].getAmount()* amount);
			if(ing.ores.length > 1)
				depositOres(ing.ores[1].getId(),ing.ores[1].getAmount()* amount);
			player.getInventory().deleteItem(ing.getBarId(),amount);
		}
		player.sm("You have deposit the ingots.");
	}
	/**
	 * deposit ores
	 * @param oreId
	 */
	public void depositOres(int oreId){
		int amount = player.getInventory().getAmountOf(oreId);
	 switch(oreId){
	 case 440:
	 case 441:
		 ironOre += amount;
		 player.getInventory().deleteItem(oreId,amount);
		 break;
	 case 447:
	 case 448:
		 mithrilOre += amount;
		 player.getInventory().deleteItem(oreId,amount);
		 break;
	 case 449:
	 case 450:
		 adamantOre += amount;
		 player.getInventory().deleteItem(oreId,amount);
		 break;
	 case 451:
	 case 452:
		 runiteOre += amount;
		 player.getInventory().deleteItem(oreId,amount);
		 break;
	 case 453:
	 case 454:
		 coal += amount;
		 player.getInventory().deleteItem(oreId,amount);
		 break;
	 }
	 player.sm("You have succesfully depost this ores.");
	 player.getInventory().refresh();
	}
	public void depositOres(int oreId, int amount){
	 switch(oreId){
	 case 440:
		 ironOre += amount;
		 break;
	 case 447:
		 mithrilOre += amount;
		 break;
	 case 449:
		 adamantOre += amount;
		 break;
	 case 451:
		 runiteOre += amount;
		 break;
	 case 453:
		 coal += amount;
		 break;
	 }
	}
	/**
	 * gives the player his bars
	 * @param ingots
	 * @param amount
	 */
	private void takeBars(Ingots ingots, int amount){
		Ingots ingot = Ingots.forId(ingots);
		if(!hasEnoughOres(ingots,amount)){
			player.sm("You don't have enough ores stored for making "+amount+" bars.");
			return;
		}
		if(!player.getInventory().hasFreeSlots()){
			player.sm("You don't have any empty invetory spaces.");
			return;
		}
		player.getInventory().addItem(ingot.barId,amount);
	}
	/**
	 * sends the interface to the player
	 */
	public void sendIngotInterface(){
		player.getInterfaceManager().sendInterface(1072);
		sendBarValues(ignots_I);
		selected = ignots_I;
		player.getPackets().sendIComponentText(1072, 53, ""+ironOre);
		player.getPackets().sendIComponentText(1072, 57, ""+mithrilOre);
		player.getPackets().sendIComponentText(1072, 61, ""+adamantOre);
		player.getPackets().sendIComponentText(1072, 65, ""+runiteOre);
		player.getPackets().sendIComponentText(1072, 49, ""+coal);
	}
	
	public void refreshInterface(){
		player.getPackets().sendIComponentText(1072, 53, ""+ironOre);
		player.getPackets().sendIComponentText(1072, 57, ""+mithrilOre);
		player.getPackets().sendIComponentText(1072, 61, ""+adamantOre);
		player.getPackets().sendIComponentText(1072, 65, ""+runiteOre);
		player.getPackets().sendIComponentText(1072, 49, ""+coal);
	}
	/**
	 * sends the overlay interface
	 */
	public void sendOverlayInterface(){
		player.getInterfaceManager().sendOverlay(1073, false);
	}
	/**
	 * checks if the player has enough ores stored, needs to be rewritten
	 * @param ingots
	 * @param amount
	 * @return
	 */
	private boolean hasEnoughOres(Ingots ingots,int amount){
		if(ingots.ores.length == 1){
			if(ironOre >= ingots.ores[0].getAmount() * amount){
				ironOre -= (ingots.ores[0].getAmount() * amount);
				return true;
			}else
				return false;
		} else {
		   int oreId = ingots.ores[0].getId();
		   int oreAmount =  ingots.ores[0].getAmount() * amount;
		   int coalAmount =  ingots.ores[1].getAmount() * amount;
		   if(oreId == 440 && ironOre >= oreAmount  && coal >= coalAmount){
			   ironOre -= oreAmount;
			   coal -= coalAmount;
			   return true;
		   } else if(oreId == 447 && mithrilOre >= oreAmount && coal >= coalAmount){
			   mithrilOre -= oreAmount;
			   coal -= coalAmount;
			   return true;
		   } else if(oreId == 449 && adamantOre >= oreAmount  && coal >= coalAmount){
			   adamantOre -= oreAmount;
			   coal -= coalAmount;
			   return true;
		   } else if(oreId == 451 && runiteOre >= oreAmount  && coal >= coalAmount){
			  runiteOre -= oreAmount;
			   coal -= coalAmount;
			   return true;
		   }
		}
		return false;
	}
	/**
	 * sends the correct values of the bars
	 */
	public int barValuesCid[] = new int[] {46,71,72,12,13,6,7,2,3};
	public void sendBarValues(Ingots[] ing){
		int count = 0;
		for(int i = 0; i <ing.length; i ++){
			if(i == 0){
				player.getPackets().sendIComponentText(1072, barValuesCid[count], ""+ing[i].ores[0].getAmount());
				count++;
			} else {
			player.getPackets().sendIComponentText(1072, barValuesCid[count], ""+ing[i].ores[0].getAmount());
			player.getPackets().sendIComponentText(1072, barValuesCid[count+1], ""+ing[i].ores[1].getAmount());
			count += 2;
			}
			
		}
	}
	Ingots selected[] = ignots_I;
	public void handelButtons(int button){
		int amount = player.getInventory().getFreeSlots();
		switch(button){
		case 201:
			takeBars(selected[0], amount);
			refreshInterface();
			break;
		case 213:
			takeBars(selected[1], amount);
			refreshInterface();
			break;
		case 225:
			takeBars(selected[2], amount);
			refreshInterface();
			break;
		case 237:
			takeBars(selected[3], amount);
			refreshInterface();
			break;
		case 249:
			takeBars(selected[4], amount);
			refreshInterface();
			break;
		case 138:
			selected = ignots_I;
			sendBarValues(selected);
			break;
		case 174:
			selected = ignots_II;
			sendBarValues(selected);
			break;
		case 162:
			selected = ignots_III;
			sendBarValues(selected);
			break;
		}
	}
	/**
	 * handles buttons for the reward interface
	 * @param buttonId
	 */
	public void handelRewardButtons(int buttonId){
		switch(buttonId){
		case 93:
			player.getDialogueManager().startDialogue("artisanRewardsD", "quick",20);
			sendInfo("Quick Learner","Experience gained for creation of assigned burial armour is increased by 2%.");
			break;
		case 97:
			player.getDialogueManager().startDialogue("artisanRewardsD", "budding",40);
			sendInfo("Budding Student","Experience gained for creation of assigned burial armour is increased by 2%. Requires and stacks with Quick Learner for a 4% bonus.");
			break;
		case 101:
			player.getDialogueManager().startDialogue("artisanRewardsD", "master",60);
			sendInfo("Master Student","Experience gained for creation of assigned burial armour is increased by 1%. Requires and stacks with Quick Learner and Budding Student for a 5% bonus.");
			break;
		case 109:
			player.getDialogueManager().startDialogue("artisanRewardsD", "golden",50);
			sendInfo("Golden Cannon","Allows turning the Dwarf multicannon to Golden version. Increases cannonball capacity to 60.");
			break;
		case 113:
			player.getDialogueManager().startDialogue("artisanRewardsD", "royal",100);
			sendInfo("Royal Cannon","Allows turning the Dwarf multicannon to Royale version. Increases cannonball capacity to 90. Requires Golden Cannon.");
			break;
		case 117:
			player.getDialogueManager().startDialogue("artisanRewardsD", "restock",100);
			sendInfo("Restocking Cannon","Grants your cannon the ability to reload automatically. You must have cannonballs in your inventory and be within 15 squares for this to work. Requires Royale Cannon.");
			break;
		}
	}
	
	public void sendInfo(String name, String info){
		player.getPackets().sendIComponentText(825, 66, ""+name);
		player.getPackets().sendIComponentText(825, 67, ""+info);
	}
	/**
	 * sends the reward interface
	 */
	public void sendRewardInterface(){
		player.getInterfaceManager().sendInterface(825);
		player.getPackets().sendHideIComponent(825, 125, true);
		player.getPackets().sendHideIComponent(825, 129, true);
		player.getPackets().sendHideIComponent(825, 133, true);
		player.getPackets().sendHideIComponent(825, 136, true);
		player.getPackets().sendIComponentText(825, 95, "Quick Learner");
		player.getPackets().sendIComponentText(825, 96, "20%");
		
		player.getPackets().sendIComponentText(825, 99, "Budding Student");
		player.getPackets().sendIComponentText(825, 100, "40%");
		
		player.getPackets().sendIComponentText(825, 103, "Master Student");
		player.getPackets().sendIComponentText(825, 104, "60%");
		
		player.getPackets().sendIComponentText(825, 107, "Expert");
		player.getPackets().sendIComponentText(825, 108, "80%");
		
		player.getPackets().sendIComponentText(825, 111, "Golden Cannon");
		player.getPackets().sendIComponentText(825, 112, "50%");
		
		player.getPackets().sendIComponentText(825, 115, "Royal Cannon");
		player.getPackets().sendIComponentText(825, 116, "100%");
		
		player.getPackets().sendIComponentText(825, 119, "Restocking Cannon");
		player.getPackets().sendIComponentText(825, 120, "100%");
		
		player.getPackets().sendIComponentText(825, 123, "Ingot kit");
		player.getPackets().sendIComponentText(825, 124, "100%");
		
		player.getPackets().sendIComponentText(825, 55, respect+"%");
		
		
	}
	
	public void addRespect(double xp) {
		respect += xp;
	}
	public void handelRespect(double exp){
		counter += exp;
		if(counter >= 10000 && respect != 100){
			respect++;
			counter = 0;
		}
	}
	
	public int getRespect() {
		return respect;
	}
	public void setRespect(int respect) {
		this.respect = respect;
	}
	public double getCounter() {
		return counter;
	}
	public void setCounter(double counter) {
		this.counter = counter;
	}

}
