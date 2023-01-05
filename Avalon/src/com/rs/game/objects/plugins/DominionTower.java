package com.rs.game.objects.plugins;

import com.rs.game.WorldObject;
import com.rs.game.objects.ObjectPlugin;
import com.rs.game.player.Player;

public class DominionTower extends ObjectPlugin {

    @Override
    public Object[] getKeys() {
        return new Object[]{ 62675, 62681, 62678, 62679, 62688, 62677, 62680 };
    }

    @Override
    public boolean processObject(Player player, WorldObject object) {
        switch (object.getId()) {
            case 62675:
                player.getCutscenesManager().play("DTPreview");
                break;
            case 62681:
                player.getDominionTower().viewScoreBoard();
                break;
            case 62678:
            case 62679:
                player.getDominionTower().openModes();
                break;
            case 62688:
                player.getDialogueManager().startDialogue("DTClaimRewards");
                break;
            case 62677:
                player.getDominionTower().talkToFace();
                break;
            case 62680:
                player.getDominionTower().openBankChest();
                break;
        }
        return true;
    }

    @Override
    public boolean processObject2(Player player, WorldObject object) {
        switch (object.getId()) {
            case 62677:
                player.getDominionTower().openRewards();
                break;
        }
        return true;
    }
}
