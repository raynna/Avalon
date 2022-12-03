package com.rs.game.player.dialogues;

import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.utils.Utils;

public class Pointless extends Dialogue {

	int npcId;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		sendNPCDialogue(npcId, 9827, "Good day, How may I help you?");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			stage = 0;
			sendNPCDialogue(npcId, 9827, "Ha LURED!");
		} else if (stage == 0) {
			stage = 1;
			sendNPCDialogue(npcId, 9827, "You going to die matt, hahaha");
		} else if (stage == 1) {
			sendPlayerDialogue(9827, "What the fuck is this??");
			player.setNextForceTalk(new ForceTalk("What the fuck is this??"));
			stage = 2;
		} else if (stage == 2) {
			sendPlayerDialogue(9827, "What the fuck are you doing stephan!");
			player.setNextForceTalk(new ForceTalk("What the fuck are you doing stephan!"));
			stage = 3;
		} else if (stage == 3) {
			sendNPCDialogue(npcId, 9827, "Im not doing anything ;)");
			stage = 4;
		} else if (stage == 4) {
			stage = 5;
			sendPlayerDialogue(9827, "Where the heck are you doing then?????????");
			player.setNextForceTalk(new ForceTalk("Where the heck are you doing then?????????"));
			stage = 5;
		} else if (stage == 5) {
			sendNPCDialogue(npcId, 9827, "No where ? :D");
			stage = 6;
		} else if (stage == 6) {
			player.setNextForceTalk(new ForceTalk("Oreally?????"));
			sendPlayerDialogue(9827, "Oreally?????");
			stage = 7;
		} else if (stage == 7) {
			sendNPCDialogue(npcId, 9827, "Bye! :DDDDD");
			stage = 8;
		} else if (stage == 8) {
			stage = 9;
			sendNPCDialogue(npcId, 9827, "You just got frogged!");
			player.setNextForceTalk(new ForceTalk("Oh i just got frogged!"));
			player.gfx(new Graphics(2169));
			player.lock(6);
			player.applyHit(new Hit(player, 500, HitLook.DESEASE_DAMAGE));
			player.gfx(new Graphics(2172));
			player.gfx(new Graphics(2196));
			player.lock(6);
			for (int i = 0; i < 35; i++)
				player.applyHit(new Hit(player, Utils.getRandom(10), HitLook.DESEASE_DAMAGE));
			player.gfx(new Graphics(2166));
			player.lock(6);
			for (int i = 0; i < 15; i++)
				player.applyHit(new Hit(player, Utils.getRandom(75), HitLook.HEALED_DAMAGE));
			player.gfx(new Graphics(2104));
			player.lock(6);
			player.applyHit(new Hit(player, 989, HitLook.REGULAR_DAMAGE));
			player.gfx(new Graphics(2105));
		} else if (stage == 9) {
			end();
		} else
			end();
	}

	@Override
	public void finish() {

	}

}
