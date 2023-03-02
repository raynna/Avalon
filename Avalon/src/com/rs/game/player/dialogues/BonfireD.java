package com.rs.game.player.dialogues;

import com.rs.game.WorldObject;
import com.rs.game.item.Item;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.skills.crafting.SpinningWheel;
import com.rs.game.player.actions.skills.firemaking.Bonfire;
import com.rs.game.player.actions.skills.firemaking.Bonfire.Log;
import com.rs.game.player.actions.skills.firemaking.Firemaking.*;
import com.rs.game.player.content.SkillsDialogue;
import com.rs.utils.HexColours;

public class BonfireD extends Dialogue {

	private Log[] logs;
	private WorldObject object;

	@Override
	public void start() {
		this.logs = (Log[]) parameters[0];
		this.object = (WorldObject) parameters[1];
		int[] ids = new int[logs.length];
		for (int i = 0; i < ids.length; i++)
			ids[i] = logs[i].getLogId();
		SkillsDialogue.sendSkillsDialogue(player, SkillsDialogue.SELECT,
				"Which type of logs do you want to add to the bonfire?", -1, ids, new SkillsDialogue.ItemNameFilter() {
					int count = 0;

					@Override
					public String rename(String name) {
						Fire fire = Fire.getFireById(logs[count].getLogId());
						if (fire != null) {
							if (player.getSkills().getLevel(Skills.FIREMAKING) < fire.getLevel())
								name = "<col=ff0000>" + name + "<br><col=ff0000>Level " + fire.getLevel();
						}
						count++;
						return name;
					}
				}, false);
	}

	@Override
	public void run(int interfaceId, int componentId) {
		int slot = SkillsDialogue.getItemSlot(componentId);
		if (slot >= logs.length || slot < 0)
			return;
		player.getActionManager().setAction(new Bonfire(logs[slot], object));
		end();
	}

	@Override
	public void finish() {

	}

}
