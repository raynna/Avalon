package com.rs.game.player.dialogues.npcs;

import com.rs.cache.loaders.NPCDefinitions;
import com.rs.cores.CoresManager;
import com.rs.game.minigames.lividfarm.LividStore;
import com.rs.game.minigames.lividfarm.LividStore.Spell;
import com.rs.game.minigames.lividfarm.LividStore.Wish;
import com.rs.game.player.content.FadingScreen;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.Logger;

public class PaulinePolaris extends Dialogue {

	private int npcId;
	private Spell spell;
	private Wish wish;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		if (parameters.length >= 2) {
			if (parameters[1] instanceof Wish) {
				wish = (Wish) parameters[1];
			} else
				spell = (Spell) parameters[1];
		}
		if (spell != null) {
			stage = 100;
			sendEntityDialogue(SEND_1_TEXT_CHAT,
					new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"You've gone far in building up this farm! I'll teach you<br>about the " + spell.getName()
									+ " spell." },
					IS_NPC, npcId, 9827);
		} else if (wish != null) {
			stage = 125;
			sendEntityDialogue(SEND_1_TEXT_CHAT,
					new String[] { NPCDefinitions.getNPCDefinitions(npcId).name, "I'll make your wish come true.." },
					IS_NPC, npcId, 9827);
		} else {
			sendEntityDialogue(SEND_1_TEXT_CHAT,
					new String[] { NPCDefinitions.getNPCDefinitions(npcId).name, "'Hello tall person." }, IS_NPC, npcId,
					9827);
		}

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			stage = 0;
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "todo 1", "todo 2");
		} else if (stage == 0) {
			if (componentId == OPTION_1) {
				stage = -2;
				sendPlayerDialogue(9827, "todo 1");
			} else {
				stage = -2;
				sendPlayerDialogue(9827, "todo 2");
			}
		} else if (stage == 100) {
			player.getInterfaceManager().closeChatBoxInterface();
			player.getInterfaceManager().closeScreenInterface();
			final long time = FadingScreen.fade(player);
			CoresManager.slowExecutor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						FadingScreen.unfade(player, time, new Runnable() {
							@Override
							public void run() {
								stage = 101;
								sendDialogue("Congratulations! You've learned how to cast the " + spell.getName()
										+ " spell.");
								player.getLivid().getSpellsLearned().add(spell);
								player.getLivid().addSpellAmount();
								if (spell == Spell.BORROWED_POWER)
									player.getLivid().removeProduce(Spell.BORROWED_POWER.getProduce());
							}
						});
					} catch (Throwable e) {
						Logger.handle(e);
					}
				}
			});
		} else if (stage == 125) {
			player.getInterfaceManager().closeChatBoxInterface();
			player.getInterfaceManager().closeScreenInterface();
			final long time = FadingScreen.fade(player);
			CoresManager.slowExecutor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						FadingScreen.unfade(player, time, new Runnable() {
							@Override
							public void run() {
								stage = -2;
								wish.process(player);
							}
						});
					} catch (Throwable e) {
						Logger.handle(e);
					}
				}
			});
		} else if (stage == 101) {
			LividStore.openInterface(player);
			end();
		} else if (stage == -2) {
			end();
		} else
			end();

	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}
