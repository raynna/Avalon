package com.rs.game.player.content.quest.impl.piratestreasure.interfaces;

import com.rs.game.player.Player;

/**
 * @Author Frostbite
 * @Contact<frostbitersps@gmail.com;skype:frostbitersps>
 */
public class Scroll {

    public static void initalizeScroll(Player player) {
        player.getInterfaceManager().sendInterface(220);
        player.getPackets().sendIComponentText(220, 8, "<col=000000>Visit the city of the White Knights. In the park,");
        player.getPackets().sendIComponentText(220, 9, "<col=000000>Saradomin points to the X which marks the spot.");
    }
}
