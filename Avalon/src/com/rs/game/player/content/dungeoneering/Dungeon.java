package com.rs.game.player.content.dungeoneering;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import com.rs.Settings;
import com.rs.game.player.Skills;
import com.rs.game.player.content.dungeoneering.DungeonConstants.KeyDoors;
import com.rs.game.player.content.dungeoneering.DungeonConstants.SkillDoors;
import com.rs.game.player.content.dungeoneering.rooms.BossRoom;
import com.rs.utils.Utils;

@SuppressWarnings("unused")
public final class Dungeon {

	private int type;
	private int complexity;
	private int size;
	private Room[][] map;
	private int creationCount;
	private int critCount;
	private DungeonManager manager;
	private RoomReference startRoom;

	private static Dungeon test;

	public static void main(String[] args) {
		Settings.DEBUG = true;
		long lastDung = Utils.currentTimeMillis();
		JFrame frame = new JFrame() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paint(Graphics g) {

				super.paint(g);
				if (test != null)
					test.draw((Graphics2D) g);
			}
		};
		frame.pack();
		frame.setSize(800, 800);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		while (true) {
			lastDung = Utils.currentTimeMillis();
			test = new Dungeon(null, 1, 6, DungeonConstants.LARGE_DUNGEON);
			for (int i = test.map[0].length - 1; i >= 0; i--) {
				for (int j = 0; j < test.map.length; j++) {
					String s = "";
					if (test.map[j][i] != null) {
						int key = test.map[j][i].getDropId();

						/*
						 * if (test.map[j][i].getCreationIndex() == Integer.MAX_VALUE) { s += "B"; } else { s +=
						 * test.map[j][i].getCreationIndex(); }
						 */
						s += "(";
						if (test.map[j][i].hasNorthDoor()) {
							s += "N";
						}
						if (test.map[j][i].hasEastDoor()) {
							s += "E";
						}
						if (test.map[j][i].hasSouthDoor()) {
							s += "S";
						}
						if (test.map[j][i].hasWestDoor()) {
							s += "W";
						}
						s += ")" + test.map[j][i].getRoom().getDoorDirections().length + " ";
						if (key != -1) {
							s += " K" + (key - 18202) / 2;
						}
						for (int l = 0; l < test.map[j][i].getRoom().getDoorDirections().length; l++) {
							int lock0 = 0;// (test.map[j][i].getDoorTypes()[l]
							// >> 16 & 0xFFFF) - 50208;
							Door door = test.map[j][i].getDoor(l);
							lock0 = door != null && door.getType() == DungeonConstants.KEY_DOOR ? door.getId() : -1;
							int rotation = (test.map[j][i].getRoom().getDoorDirections()[l] + test.map[j][i].getRotation()) & 0x3;
							if (lock0 >= 0 && lock0 < 64) {
								s += " L" + rotation + "_" + lock0;
							}

						}
					}
					//System.out.print(padRight(s, 30));
				}
				//System.out.println();
			}
			//System.out.println("crit count: " + test.critCount);
			//System.out.println("Enter anything to continue:");
			frame.repaint();
			try {
				System.in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void draw(Graphics2D g) {
		g.scale(3, 3);
		g.setColor(Color.black);
		g.fillRect(0, 0, 1000, 1000);

		g.setFont(new Font("TimesRoman", Font.BOLD, 7));
		g.setColor(Color.white);
		g.drawString("Mangenta - path", 180, 200);
		g.drawString("Dark Grey Dot - Start Room", 180, 190);
		g.drawString("Black Dot - Boss Room", 180, 180);
		g.drawString("Black - Key Item", 180, 170);
		g.drawString("White - Key Door", 180, 160);
		g.drawString("Others - Branches", 180, 150);

		for (int y = 0; y < map[0].length; y++) {
			for (int x = 0; x < map.length; x++) {
				if (map[x][y] != null) {

					// g.setColor(Color.getHSBColor((map[x][y].getCreationIndex()
					// * 4f) / 360f, 1.0f, 0.8f));
					g.setColor(Color.GREEN);

					if (map[x][y].isCritPath())
						g.setColor(Color.WHITE);

					g.fillRect(x * 20 + 24, (7 - y) * 20 + 44, 12, 12);

					// if (map[x][y].getCreationIndex() == 0) {
					// g.setColor(Color.darkGray);
					// g.fillRect(x * 20 + 27, (7 - y) * 20 + 47, 6, 6);
					//
					// }
					if (map[x][y].getRoom() instanceof BossRoom) {
						g.setColor(Color.black);
						g.fillRect(x * 20 + 27, (7 - y) * 20 + 47, 6, 6);
					}
					g.setColor(Color.black);

					g.setColor(Color.magenta);
					if (map[x][y].hasNorthDoor()) {
						g.drawLine(x * 20 + 30, (7 - y) * 20 + 50, x * 20 + 30, ((7 - y) - 1) * 20 + 50);
					}
					if (map[x][y].hasEastDoor()) {
						g.drawLine(x * 20 + 30, (7 - y) * 20 + 50, (x + 1) * 20 + 30, ((7 - y)) * 20 + 50);
					}
					if (map[x][y].hasSouthDoor()) {
						g.drawLine(x * 20 + 30, (7 - y) * 20 + 50, x * 20 + 30, ((7 - y) + 1) * 20 + 50);
					}
					if (map[x][y].hasWestDoor()) {
						g.drawLine(x * 20 + 30, (7 - y) * 20 + 50, (x - 1) * 20 + 30, ((7 - y)) * 20 + 50);
					}
				}
			}
		}

		for (int y = 0; y < map[0].length; y++) {
			for (int x = 0; x < map.length; x++) {
				if (map[x][y] != null) {
					int key = map[x][y].getDropId();
					if (key != -1) {
						g.setFont(new Font("TimesRoman", Font.BOLD, 7));
						g.setColor(Color.white);
						g.drawString("\033" + (key - 18202) / 2, x * 20 + 36, (7 - y) * 20 + 56);
					}
					// int tier = map[x][y].getCreationIndex();// ==
					// creationCount ? branchCount - 1 :
					// creationBranchReference[map[x][y].getCreationIndex()];
					g.setFont(new Font("TimesRoman", Font.BOLD, 7));
					g.setColor(Color.green);
					// g.drawString("\033" + tier, x * 20 + 36, (7 - y) * 20 +
					// 49);

					for (int l = 0; l < map[x][y].getRoom().getDoorDirections().length; l++) {
						int lock0 = 0;// (map[x][y].getDoorTypes()[l] >> 16 &
						// 0xFFFF) - 50208;
						Door door = map[x][y].getDoor(l);
						lock0 = door != null && door.getType() == DungeonConstants.KEY_DOOR ? door.getId() : -1;
						int rotation = (map[x][y].getRoom().getDoorDirections()[l] + map[x][y].getRotation()) & 0x3;
						if (lock0 >= 0 && lock0 < 64) {
							if (rotation == DungeonConstants.NORTH_DOOR) {
								g.setFont(new Font("TimesRoman", Font.BOLD, 5));
								g.setColor(Color.black);
								g.drawString("\033" + lock0, x * 20 + 27, (7 - y) * 20 + 48);
							} else if (rotation == DungeonConstants.EAST_DOOR) {
								g.setFont(new Font("TimesRoman", Font.BOLD, 5));
								g.setColor(Color.black);
								g.drawString("\033" + lock0, x * 20 + 31, (7 - y) * 20 + 52);
							} else if (rotation == DungeonConstants.SOUTH_DOOR) {
								g.setFont(new Font("TimesRoman", Font.BOLD, 5));
								g.setColor(Color.black);
								g.drawString("\033" + lock0, x * 20 + 27, (7 - y) * 20 + 55);
							} else if (rotation == DungeonConstants.WEST_DOOR) {
								g.setFont(new Font("TimesRoman", Font.BOLD, 5));
								g.setColor(Color.black);
								g.drawString("\033" + lock0, x * 20 + 24, (7 - y) * 20 + 52);
							}
						}

					}
				}
			}
		}
	}

	public static String padRight(String s, int n) {
		return String.format("%1$-" + n + "s", s);
	}

	public Dungeon(DungeonManager manager, int floorId, int complexity, int size) {
		this.manager = manager;
		this.type = DungeonUtils.getFloorType(floorId);
		this.complexity = complexity;
		this.size = size;

		long seed = System.nanoTime();
		// seed = 3022668148508890112L;
		Random random = new Random(seed);
		DungeonStructure structure = new DungeonStructure(size, random, complexity, manager == null ? true : manager.getParty().isKeyShare());
		// map structure to matrix dungeon
		map = new Room[DungeonConstants.DUNGEON_RATIO[size][0]][DungeonConstants.DUNGEON_RATIO[size][1]];
		RoomNode base = structure.getBase();

		Room[] possibilities;
		startRoom = new RoomReference(base.x, base.y);
		List<RoomNode> children = base.getChildrenR();
		children.add(base);
		long eligiblePuzzleRooms = children.stream().filter(r -> r.children.size() > 0 && r.children.stream().allMatch(c -> c.lock == -1)).count();
		double puzzleChance = complexity < 6 ? 0 : 0.1 * children.size() / eligiblePuzzleRooms;
		for (RoomNode node : children) {
			creationCount++;
			boolean puzzle = false;
			if (node == base) {
				possibilities = DungeonUtils.selectPossibleRooms(DungeonConstants.START_ROOMS, complexity, type, base.north(), base.east(), base.south(), base.west());
			} else if (node.isBoss) {
				possibilities = DungeonUtils.selectPossibleBossRooms(type, complexity, floorId, node.north(), node.east(), node.south(), node.west(), node.rotation());
			} else if (node.children.size() > 0 && node.children.stream().allMatch(c -> c.lock == -1) && puzzleChance > random.nextDouble()) {
				puzzle = true;
				possibilities = DungeonUtils.selectPossibleRooms(DungeonConstants.PUZZLE_ROOMS, complexity, type, node.north(), node.east(), node.south(), node.west(), node.rotation());
			} else {
				possibilities = DungeonUtils.selectPossibleRooms(DungeonConstants.NORMAL_ROOMS, complexity, type, node.north(), node.east(), node.south(), node.west());
			}
			map[node.x][node.y] = possibilities[random.nextInt(possibilities.length)];
			if (node.isCritPath) {
				critCount++;
				map[node.x][node.y].setCritPath(true);
			}
			if (node.key != -1)
				map[node.x][node.y].setDropId(KeyDoors.values()[node.key].getKeyId());
			for (int doorDir = 0; doorDir < map[node.x][node.y].getRoom().getDoorDirections().length; doorDir++) {
				int rotation = (map[node.x][node.y].getRoom().getDoorDirections()[doorDir] + map[node.x][node.y].getRotation()) & 0x3;
				RoomNode neighbor = structure.getRoom(node.x + Utils.DOOR_ROTATION_DIR_X[rotation], node.y + Utils.DOOR_ROTATION_DIR_Y[rotation]);
				if (neighbor.parent == node) {
					if (puzzle) {
						map[node.x][node.y].setDoor(doorDir, new Door(DungeonConstants.CHALLENGE_DOOR));
					} else if (neighbor.lock != -1) {
						map[node.x][node.y].setDoor(doorDir, new Door(DungeonConstants.KEY_DOOR, neighbor.lock));
					} else if (complexity >= 5 && random.nextInt(3) == 0) {
						int doorIndex = random.nextInt(DungeonConstants.SkillDoors.values().length - 1);
						SkillDoors sd = DungeonConstants.SkillDoors.values()[doorIndex];
						if (sd.getClosedObject(type) == -1) // some frozen skill
							// doors dont exist
							continue;
						int level = manager == null ? 1 : neighbor.isCritPath ? (manager.getParty().getMaxLevel(sd.getSkillId()) - random.nextInt(10)) : random.nextInt(sd.getSkillId() == Skills.SUMMONING || sd.getSkillId() == Skills.PRAYER ? 100 : 106);
						map[node.x][node.y].setDoor(doorDir, new Door(DungeonConstants.SKILL_DOOR, doorIndex, level < 1 ? 1 : level));
					} else if (complexity >= 3 && random.nextInt(2) == 0) {
						map[node.x][node.y].setDoor(doorDir, new Door(DungeonConstants.GUARDIAN_DOOR));
					}

				}
			}
		}

		if (Settings.DEBUG)
			System.out.println("Dungeon roomCount: " + creationCount);
	}

	public int getRoomsCount() {
		return creationCount;
	}
	
	public void setRoomsCount(int value) {
		this.creationCount = value;
	}

	public int getCritCount() {
		return critCount;
	}

	public Room getRoom(RoomReference reference) {
		if (reference.getX() < 0 || reference.getY() < 0 || map.length <= reference.getX() || map[reference.getX()].length <= reference.getY())
			return null;
		return map[reference.getX()][reference.getY()];
	}

	public int getMapWidth() {
		return map.length;
	}

	public int getMapHeight() {
		return map[0].length;
	}

	public RoomReference getStartRoomReference() {
		return startRoom;
	}

}
