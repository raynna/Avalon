package com.rs.game.player.dialogues;

import com.rs.cache.loaders.NPCDefinitions;

public class Auras extends Dialogue {

	private int npcId;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		stage = 0;
		sendEntityDialogue(SEND_1_TEXT_CHAT,
				new String[] { NPCDefinitions.getNPCDefinitions(npcId).name, "What aura would you like to buy?" },
				IS_NPC, npcId, 9827);
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 0) {
			stage = 1;
			sendOptionsDialogue("What aura would you like to buy?", "Salvation Auras", "Corruption Auras",
					"Harmony Auras", "Combat Auras", "Misc Auras");
		} else if (stage == 1) {
			if (componentId == OPTION_1) {
				stage = 10;
				sendOptionsDialogue("What aura would you like to buy?", "Salvation (50k)", "Greater Salvation (100k)",
						"Master Salvation (150k)", "Supreme Salvation (200k)", "Back");
			} else if (componentId == OPTION_2) {
				stage = 15;
				sendOptionsDialogue("What aura would you like to buy?", "Corruption (50k)", "Greater Corruption (100k)",
						"Master Corruption (150k)", "Supreme Corruption (200k)", "Back");
			} else if (componentId == OPTION_3) {
				stage = 20;
				sendOptionsDialogue("What aura would you like to buy?", "Harmony (50k)", "Greater Harmony (100k)",
						"Master Harmony (150k)", "Supreme Harmony (200k)", "Back");
			} else if (componentId == OPTION_4) {
				stage = 25;
				sendOptionsDialogue("What aura would you like to buy?", "Vampyrism (100k)", "Penance (100k)",
						"Supreme Poison Purge (100k)", "Supreme Runic Accuracy (100k)", "Next");
			} else if (componentId == OPTION_5) {
				stage = 30;
				sendOptionsDialogue("What aura would you like to buy?", "Quarrymaster (100k)", "Call of the Sea (100k)",
						"Lumberjack (100k)", "Five-finger discount (100k)", "Back");
			}
		} else if (stage == 10) {
			if (componentId == OPTION_1) {
				if (player.getAvalonPoints() >= 50000) {
					if (player.getInventory().hasFreeSlots()) {
						stage = 50;
						sendPlayerDialogue(9827, "Thanks!");
						player.setAvalonPoints(player.getAvalonPoints() - 50000);
						player.getInventory().addItem(22899, 1);
					} else {
						stage = 50;
						sendEntityDialogue(SEND_1_TEXT_CHAT,
								new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
										"You don't have enough inventory space." },
								IS_NPC, npcId, 9827);
					}
				} else {
					stage = 50;
					sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"You need at least 50,000 avalon points." }, IS_NPC, npcId, 9827);
				}
			} else if (componentId == OPTION_2) {
				if (player.getAvalonPoints() >= 100000) {
					if (player.getInventory().hasFreeSlots()) {
						stage = 50;
						sendPlayerDialogue(9827, "Thanks!");
						player.setAvalonPoints(player.getAvalonPoints() - 100000);
						player.getInventory().addItem(22901, 1);
					} else {
						stage = 50;
						sendEntityDialogue(SEND_1_TEXT_CHAT,
								new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
										"You don't have enough inventory space." },
								IS_NPC, npcId, 9827);
					}
				} else {
					stage = 50;
					sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"You need at least 100,000 avalon points." }, IS_NPC, npcId, 9827);
				}
			} else if (componentId == OPTION_3) {
				if (player.getAvalonPoints() >= 150000) {
					if (player.getInventory().hasFreeSlots()) {
						stage = 50;
						sendPlayerDialogue(9827, "Thanks!");
						player.setAvalonPoints(player.getAvalonPoints() - 150000);
						player.getInventory().addItem(22903, 1);
					} else {
						stage = 50;
						sendEntityDialogue(SEND_1_TEXT_CHAT,
								new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
										"You don't have enough inventory space." },
								IS_NPC, npcId, 9827);
					}
				} else {
					stage = 50;
					sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"You need at least 150,000 avalon points." }, IS_NPC, npcId, 9827);
				}
			} else if (componentId == OPTION_4) {
				if (player.getAvalonPoints() >= 200000) {
					if (player.getInventory().hasFreeSlots()) {
						stage = 50;
						sendPlayerDialogue(9827, "Thanks!");
						player.setAvalonPoints(player.getAvalonPoints() - 200000);
						player.getInventory().addItem(23876, 1);
					} else {
						stage = 50;
						sendEntityDialogue(SEND_1_TEXT_CHAT,
								new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
										"You don't have enough inventory space." },
								IS_NPC, npcId, 9827);
					}
				} else {
					stage = 50;
					sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"You need at least 200,000 avalon points." }, IS_NPC, npcId, 9827);
				}
			} else if (componentId == OPTION_5) {
				stage = 1;
				sendOptionsDialogue("What aura would you like to buy?", "Salvation Auras", "Corruption Auras",
						"Harmony Auras", "Combat Auras", "Misc Auras");
			}
		} else if (stage == 15) {
			if (componentId == OPTION_1) {
				if (player.getAvalonPoints() >= 50000) {
					if (player.getInventory().hasFreeSlots()) {
						stage = 50;
						sendPlayerDialogue(9827, "Thanks!");
						player.setAvalonPoints(player.getAvalonPoints() - 50000);
						player.getInventory().addItem(22905, 1);
					} else {
						stage = 50;
						sendEntityDialogue(SEND_1_TEXT_CHAT,
								new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
										"You don't have enough inventory space." },
								IS_NPC, npcId, 9827);
					}
				} else {
					stage = 50;
					sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"You need at least 50,000 avalon points." }, IS_NPC, npcId, 9827);
				}
			} else if (componentId == OPTION_2) {
				if (player.getAvalonPoints() >= 100000) {
					if (player.getInventory().hasFreeSlots()) {
						stage = 50;
						sendPlayerDialogue(9827, "Thanks!");
						player.setAvalonPoints(player.getAvalonPoints() - 100000);
						player.getInventory().addItem(22907, 1);
					} else {
						stage = 50;
						sendEntityDialogue(SEND_1_TEXT_CHAT,
								new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
										"You don't have enough inventory space." },
								IS_NPC, npcId, 9827);
					}
				} else {
					stage = 50;
					sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"You need at least 100,000 avalon points." }, IS_NPC, npcId, 9827);
				}
			} else if (componentId == OPTION_3) {
				if (player.getAvalonPoints() >= 150000) {
					if (player.getInventory().hasFreeSlots()) {
						stage = 50;
						sendPlayerDialogue(9827, "Thanks!");
						player.setAvalonPoints(player.getAvalonPoints() - 150000);
						player.getInventory().addItem(22909, 1);
					} else {
						stage = 50;
						sendEntityDialogue(SEND_1_TEXT_CHAT,
								new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
										"You don't have enough inventory space." },
								IS_NPC, npcId, 9827);
					}
				} else {
					stage = 50;
					sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"You need at least 150,000 avalon points." }, IS_NPC, npcId, 9827);
				}
			} else if (componentId == OPTION_4) {
				if (player.getAvalonPoints() >= 200000) {
					if (player.getInventory().hasFreeSlots()) {
						stage = 50;
						sendPlayerDialogue(9827, "Thanks!");
						player.setAvalonPoints(player.getAvalonPoints() - 200000);
						player.getInventory().addItem(23874, 1);
					} else {
						stage = 50;
						sendEntityDialogue(SEND_1_TEXT_CHAT,
								new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
										"You don't have enough inventory space." },
								IS_NPC, npcId, 9827);
					}
				} else {
					stage = 50;
					sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"You need at least 200,000 avalon points." }, IS_NPC, npcId, 9827);
				}
			} else if (componentId == OPTION_5) {
				stage = 1;
				sendOptionsDialogue("What aura would you like to buy?", "Salvation Auras", "Corruption Auras",
						"Harmony Auras", "Combat Auras", "Misc Auras");
			}
		} else if (stage == 20) {
			if (componentId == OPTION_1) {
				if (player.getAvalonPoints() >= 50000) {
					if (player.getInventory().hasFreeSlots()) {
						stage = 50;
						sendPlayerDialogue(9827, "Thanks!");
						player.setAvalonPoints(player.getAvalonPoints() - 50000);
						player.getInventory().addItem(23848, 1);
					} else {
						stage = 50;
						sendEntityDialogue(SEND_1_TEXT_CHAT,
								new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
										"You don't have enough inventory space." },
								IS_NPC, npcId, 9827);
					}
				} else {
					stage = 50;
					sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"You need at least 50,000 avalon points." }, IS_NPC, npcId, 9827);
				}
			} else if (componentId == OPTION_2) {
				if (player.getAvalonPoints() >= 100000) {
					if (player.getInventory().hasFreeSlots()) {
						stage = 50;
						sendPlayerDialogue(9827, "Thanks!");
						player.setAvalonPoints(player.getAvalonPoints() - 100000);
						player.getInventory().addItem(23850, 1);
					} else {
						stage = 50;
						sendEntityDialogue(SEND_1_TEXT_CHAT,
								new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
										"You don't have enough inventory space." },
								IS_NPC, npcId, 9827);
					}
				} else {
					stage = 50;
					sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"You need at least 100,000 avalon points." }, IS_NPC, npcId, 9827);
				}
			} else if (componentId == OPTION_3) {
				if (player.getAvalonPoints() >= 150000) {
					if (player.getInventory().hasFreeSlots()) {
						stage = 50;
						sendPlayerDialogue(9827, "Thanks!");
						player.setAvalonPoints(player.getAvalonPoints() - 150000);
						player.getInventory().addItem(23852, 1);
					} else {
						stage = 50;
						sendEntityDialogue(SEND_1_TEXT_CHAT,
								new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
										"You don't have enough inventory space." },
								IS_NPC, npcId, 9827);
					}
				} else {
					stage = 50;
					sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"You need at least 150,000 avalon points." }, IS_NPC, npcId, 9827);
				}
			} else if (componentId == OPTION_4) {
				if (player.getAvalonPoints() >= 200000) {
					if (player.getInventory().hasFreeSlots()) {
						stage = 50;
						sendPlayerDialogue(9827, "Thanks!");
						player.setAvalonPoints(player.getAvalonPoints() - 200000);
						player.getInventory().addItem(23854, 1);
					} else {
						stage = 50;
						sendEntityDialogue(SEND_1_TEXT_CHAT,
								new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
										"You don't have enough inventory space." },
								IS_NPC, npcId, 9827);
					}
				} else {
					stage = 50;
					sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"You need at least 200,000 avalon points." }, IS_NPC, npcId, 9827);
				}
			} else if (componentId == OPTION_5) {
				stage = 1;
				sendOptionsDialogue("What aura would you like to buy?", "Salvation Auras", "Corruption Auras",
						"Harmony Auras", "Combat Auras", "Misc Auras");
			}
		} else if (stage == 25) {
			if (componentId == OPTION_1) {
				if (player.getAvalonPoints() >= 100000) {
					if (player.getInventory().hasFreeSlots()) {
						stage = 50;
						sendPlayerDialogue(9827, "Thanks!");
						player.setAvalonPoints(player.getAvalonPoints() - 100000);
						player.getInventory().addItem(22298, 1);
					} else {
						stage = 50;
						sendEntityDialogue(SEND_1_TEXT_CHAT,
								new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
										"You don't have enough inventory space." },
								IS_NPC, npcId, 9827);
					}
				} else {
					stage = 50;
					sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"You need at least 100,000 avalon points." }, IS_NPC, npcId, 9827);
				}
			} else if (componentId == OPTION_2) {
				if (player.getAvalonPoints() >= 100000) {
					if (player.getInventory().hasFreeSlots()) {
						stage = 50;
						sendPlayerDialogue(9827, "Thanks!");
						player.setAvalonPoints(player.getAvalonPoints() - 100000);
						player.getInventory().addItem(22300, 1);
					} else {
						stage = 50;
						sendEntityDialogue(SEND_1_TEXT_CHAT,
								new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
										"You don't have enough inventory space." },
								IS_NPC, npcId, 9827);
					}
				} else {
					stage = 50;
					sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"You need at least 100,000 avalon points." }, IS_NPC, npcId, 9827);
				}
			} else if (componentId == OPTION_3) {
				if (player.getAvalonPoints() >= 100000) {
					if (player.getInventory().hasFreeSlots()) {
						stage = 50;
						sendPlayerDialogue(9827, "Thanks!");
						player.setAvalonPoints(player.getAvalonPoints() - 100000);
						player.getInventory().addItem(23862, 1);
					} else {
						stage = 50;
						sendEntityDialogue(SEND_1_TEXT_CHAT,
								new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
										"You don't have enough inventory space." },
								IS_NPC, npcId, 9827);
					}
				} else {
					stage = 50;
					sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"You need at least 100,000 avalon points." }, IS_NPC, npcId, 9827);
				}
			} else if (componentId == OPTION_4) {
				if (player.getAvalonPoints() >= 100000) {
					if (player.getInventory().hasFreeSlots()) {
						stage = 50;
						sendPlayerDialogue(9827, "Thanks!");
						player.setAvalonPoints(player.getAvalonPoints() - 100000);
						player.getInventory().addItem(23864, 1);
					} else {
						stage = 50;
						sendEntityDialogue(SEND_1_TEXT_CHAT,
								new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
										"You don't have enough inventory space." },
								IS_NPC, npcId, 9827);
					}
				} else {
					stage = 50;
					sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"You need at least 100,000 avalon points." }, IS_NPC, npcId, 9827);
				}
			} else if (componentId == OPTION_5) {
				stage = 26;
				sendOptionsDialogue("What aura would you like to buy?", "Sharpshooter", "Berserker", "Back");
			}
		} else if (stage == 26) {
			if (componentId == OPTION_1) {
				if (player.getAvalonPoints() >= 100000) {
					if (player.getInventory().hasFreeSlots()) {
						stage = 50;
						sendPlayerDialogue(9827, "Thanks!");
						player.setAvalonPoints(player.getAvalonPoints() - 100000);
						player.getInventory().addItem(23866, 1);
					} else {
						stage = 50;
						sendEntityDialogue(SEND_1_TEXT_CHAT,
								new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
										"You don't have enough inventory space." },
								IS_NPC, npcId, 9827);
					}
				} else {
					stage = 50;
					sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"You need at least 100,000 avalon points." }, IS_NPC, npcId, 9827);
				}
			} else if (componentId == OPTION_2) {
				if (player.getAvalonPoints() >= 100000) {
					if (player.getInventory().hasFreeSlots()) {
						stage = 50;
						sendPlayerDialogue(9827, "Thanks!");
						player.setAvalonPoints(player.getAvalonPoints() - 100000);
						player.getInventory().addItem(22897, 1);
					} else {
						stage = 50;
						sendEntityDialogue(SEND_1_TEXT_CHAT,
								new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
										"You don't have enough inventory space." },
								IS_NPC, npcId, 9827);
					}
				} else {
					stage = 50;
					sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"You need at least 100,000 avalon points." }, IS_NPC, npcId, 9827);
				}
			} else if (componentId == OPTION_3) {
				stage = 1;
				sendOptionsDialogue("What aura would you like to buy?", "Salvation Auras", "Corruption Auras",
						"Harmony Auras", "Combat Auras", "Misc Auras");
			}
		} else if (stage == 30) {
			if (componentId == OPTION_1) {
				if (player.getAvalonPoints() >= 100000) {
					if (player.getInventory().hasFreeSlots()) {
						stage = 50;
						sendPlayerDialogue(9827, "Thanks!");
						player.setAvalonPoints(player.getAvalonPoints() - 100000);
						player.getInventory().addItem(23858, 1);
					} else {
						stage = 50;
						sendEntityDialogue(SEND_1_TEXT_CHAT,
								new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
										"You don't have enough inventory space." },
								IS_NPC, npcId, 9827);
					}
				} else {
					stage = 50;
					sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"You need at least 100,000 avalon points." }, IS_NPC, npcId, 9827);
				}
			} else if (componentId == OPTION_2) {
				if (player.getAvalonPoints() >= 100000) {
					if (player.getInventory().hasFreeSlots()) {
						stage = 50;
						sendPlayerDialogue(9827, "Thanks!");
						player.setAvalonPoints(player.getAvalonPoints() - 100000);
						player.getInventory().addItem(23868, 1);
					} else {
						stage = 50;
						sendEntityDialogue(SEND_1_TEXT_CHAT,
								new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
										"You don't have enough inventory space." },
								IS_NPC, npcId, 9827);
					}
				} else {
					stage = 50;
					sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"You need at least 100,000 avalon points." }, IS_NPC, npcId, 9827);
				}
			} else if (componentId == OPTION_3) {
				if (player.getAvalonPoints() >= 100000) {
					if (player.getInventory().hasFreeSlots()) {
						stage = 50;
						sendPlayerDialogue(9827, "Thanks!");
						player.setAvalonPoints(player.getAvalonPoints() - 100000);
						player.getInventory().addItem(23860, 1);
					} else {
						stage = 50;
						sendEntityDialogue(SEND_1_TEXT_CHAT,
								new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
										"You don't have enough inventory space." },
								IS_NPC, npcId, 9827);
					}
				} else {
					stage = 50;
					sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"You need at least 100,000 avalon points." }, IS_NPC, npcId, 9827);
				}
			} else if (componentId == OPTION_4) {
				if (player.getAvalonPoints() >= 100000) {
					if (player.getInventory().hasFreeSlots()) {
						stage = 50;
						sendPlayerDialogue(9827, "Thanks!");
						player.setAvalonPoints(player.getAvalonPoints() - 100000);
						player.getInventory().addItem(23856, 1);
					} else {
						stage = 50;
						sendEntityDialogue(SEND_1_TEXT_CHAT,
								new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
										"You don't have enough inventory space." },
								IS_NPC, npcId, 9827);
					}
				} else {
					stage = 50;
					sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"You need at least 100,000 avalon points." }, IS_NPC, npcId, 9827);
				}
			} else if (componentId == OPTION_5) {
				stage = 1;
				sendOptionsDialogue("What aura would you like to buy?", "Salvation Auras", "Corruption Auras",
						"Harmony Auras", "Combat Auras", "Misc Auras");
			}
		} else if (stage == 50)
			end();
	}

	@Override
	public void finish() {
	}
}
