package com.rs.game.player.content.customtab;

import com.rs.game.player.Player;
import com.rs.game.player.controlers.WildernessControler;
import com.rs.utils.HexColours;
import com.rs.utils.HexColours.Colour;
import com.rs.utils.Utils;

public class SettingsTab extends CustomTab {

    public enum SettingsStore {

        TITLE(25) {
            @Override
            public void usage(Player p) {
            }

            @Override
            public String text(Player p) {
                return "Toggles";
            }
        },

        SETTINGS(3) {
            @Override
            public void usage(Player p) {
            }

            @Override
            public String text(Player p) {
                return "<u>Settings";
            }
        },

        BREAKVIALS(4) {
            @Override
            public void usage(Player p) {
                p.toggles.put("BREAK_VIALS", !p.toggles("BREAK_VIALS", false));
            }

            @Override
            public String text(Player p) {
                return "Break Vials: " + (p.toggles("BREAK_VIALS", false) ? "<col=04BB3B>On" : "<col=BB0404>Off");
            }
        },

        ITEMSLOOK(5) {
            @Override
            public void usage(Player p) {
                p.switchItemsLook();
            }

            @Override
            public String text(Player p) {
                return "Item Visuals: " + (p.isOldItemsLook() ? "<col=04BB3B>2011" : "<col=04BB3B>2012");
            }
        },

        SHIFTDROP(6) {
            @Override
            public void usage(Player p) {
                p.switchShiftDrop();
            }

            @Override
            public String text(Player p) {
                return "Shift Dropping: " + (p.isShiftDrop() ? "<col=04BB3B>On" : "<col=BB0404>Off");
            }
        },

        DRAGSETTING(7) {
            @Override
            public void usage(Player p) {
                p.switchSlowDrag();
                p.sm("You have to relog before noticing changes.");
            }

            @Override
            public String text(Player p) {
                return "Slow Drag: " + (p.isSlowDrag() ? "<col=04BB3B>On" : "<col=BB0404>Off");
            }
        },

        ZOOMSETTING(8) {
            @Override
            public void usage(Player p) {
                p.switchZoom();
            }

            @Override
            public String text(Player p) {
                return "Zoom: " + (p.isZoom() ? "<col=04BB3B>On" : "<col=BB0404>Off");
            }
        },

        HEALTHBAR(9) {
            @Override
            public void usage(Player p) {
                p.toggles.put("HEALTHBAR", !p.toggles("HEALTHBAR", false));
                if (p.getInterfaceManager().containsTab(30) && !p.toggles("HEALTHBAR", false)) {
                    p.getInterfaceManager().closeTab(p.getInterfaceManager().isResizableScreen(), 30);
                }
            }

            @Override
            public String text(Player p) {
                return "Health Overlay: " + (p.toggles("HEALTHBAR", false) ? "<col=04BB3B>On" : "<col=BB0404>Off");
            }
        },

        KDRINTER(10) {
            @Override
            public void usage(Player p) {
                p.toggles.put("KDRINTER", !p.toggles("KDRINTER", false));
                if (p.getInterfaceManager().containsTab(10) && !p.toggles("KDRINTER", false)) {
                    p.getInterfaceManager().closeTab(p.getInterfaceManager().isResizableScreen(), 10);
                } else {
                    if (WildernessControler.isAtWild(p)) {
                        WildernessControler.showKDRInter(p);
                    }
                }
            }

            @Override
            public String text(Player p) {
                return "KDR Overlay: " + (p.toggles("KDRINTER", false) ? "<col=04BB3B>On" : "<col=BB0404>Off");
            }
        },

        DROPS(12) {
            @Override
            public void usage(Player p) {
            }

            @Override
            public String text(Player p) {
                return "<u>Drop Settings";
            }
        },

        LOOTBEAMS(13) {
            @Override
            public void usage(Player p) {
                p.toggles.put("LOOTBEAMS", !p.toggles("LOOTBEAMS", false));
            }

            @Override
            public String text(Player p) {
                return "Lootbeams: " + (p.toggles("LOOTBEAMS", false) ? "<col=04BB3B>On" : "<col=BB0404>Off");
            }
        },

        UNTRADEABLEMESSAGE(14) {
            @Override
            public void usage(Player p) {
                p.toggles.put("UNTRADEABLEMESSAGE", !p.toggles("UNTRADEABLEMESSAGE", false));
            }

            @Override
            public String text(Player p) {
                return "Untradeable Message: "
                        + (p.toggles("UNTRADEABLEMESSAGE", false) ? "<col=04BB3B>On" : "<col=BB0404>Off");
            }
        },

        DROPVALUE(15) {
            @Override
            public void usage(Player p) {
                p.getTemporaryAttributtes().put("SET_DROPVALUE", Boolean.TRUE);
                p.getPackets().sendRunScript(108, new Object[]{"Enter Amount:"});
            }

            @Override
            public String text(Player p) {
                int dropValue = Integer.parseInt(p.getToggleValue(p.toggles.get("DROPVALUE")));
                return "Valuable Drop: " + (dropValue < 1 ? HexColours.getMessage(Colour.RED, "0 - click to set") : HexColours.getMessage(Colour.GREEN, "" + Utils.getFormattedNumber(dropValue, ',') + " gp"));
            }
        },

        COMBATSETTINGS(17) {
            @Override
            public void usage(Player p) {
            }

            @Override
            public String text(Player p) {
                return "<u>Combat Settings";
            }
        },

        ONEXPPERHIT(18) {
            @Override
            public void usage(Player p) {
                p.toggles.put("ONEXPPERHIT", !p.toggles("ONEXPPERHIT", false));
                p.getSkills().switchXPPopup(true);
                p.getSkills().switchXPPopup(true);
            }

            @Override
            public String text(Player p) {
                return "One XP per Hit: " + (p.toggles("ONEXPPERHIT", false) ? "<col=04BB3B>On" : "<col=BB0404>Off");
            }
        },

        ONEXHITS(19) {
            @Override
            public void usage(Player p) {
                p.toggles.put("ONEXHITS", !p.toggles("ONEXHITS", false));
                p.getPrayer().refreshPrayerPoints();
                p.refreshHitPoints();
                p.getSkills().switchXPPopup(true);
                p.getSkills().switchXPPopup(true);
            }

            @Override
            public String text(Player p) {
                return "1x Hitmarks: " + (p.toggles("ONEXHITS", false) ? "<col=04BB3B>On" : "<col=BB0404>Off");
            }
        },

        DEVELOPER_SETTINGS(21) {
            @Override
            public void usage(Player p) {
            }

            @Override
            public String text(Player p) {
                return "<u>Developer Settings";
            }
        },
        INTERACTIVE(22) {
            @Override
            public void usage(Player p) {
                p.switchDeveloperMode();
            }

            @Override
            public String text(Player p) {
                return "Developer Mode: " + (p.isDeveloperMode() ? "<col=04BB3B>On" : "<col=BB0404>Off");
            }
        },
        ;

        private int compId;

        private SettingsStore(int compId) {
            this.compId = compId;
        }

        public abstract String text(Player p);

        public abstract void usage(Player p);

    }

    public static void open(Player player) {
        sendComponents(player);
        for (int i = 3; i <= 22; i++)
            player.getPackets().sendHideIComponent(3002, i, true);
        for (int i = 28; i <= 56; i++)
            player.getPackets().sendHideIComponent(3002, i, true);
        player.getTemporaryAttributtes().put("CUSTOMTAB", 2);
        player.getPackets().sendHideIComponent(3002, BACK_BUTTON, false);
        player.getPackets().sendHideIComponent(3002, FORWARD_BUTTON, false);
        player.getPackets().sendSpriteOnIComponent(3002, RED_STAR_COMP, RED_HIGHLIGHTED);
        for (SettingsStore store : SettingsStore.values()) {
            if (store != null) {
                player.getPackets().sendHideIComponent(3002, store.compId, false);
                if (store.text(player) != null) {
                    player.getPackets().sendIComponentText(3002, store.compId, store.text(player));
                }
            }
        }
    }

    public static void handleButtons(Player player, int compId) {
        for (SettingsStore store : SettingsStore.values()) {
            if (store != null) {
                if (compId != store.compId)
                    continue;
                store.usage(player);
                open(player);
            }
        }
        switch (compId) {
            case BACK_BUTTON:
                TeleportTab.open(player);
                break;
            case FORWARD_BUTTON:
                QuestTab.open(player);
                break;
            default:
                break;
        }
    }
}
