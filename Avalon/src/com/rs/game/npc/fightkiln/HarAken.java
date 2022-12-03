package com.rs.game.npc.fightkiln;

import java.util.ArrayList;
import java.util.List;

import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.player.controlers.FightKiln;
import com.rs.utils.Utils;

@SuppressWarnings("serial")
public class HarAken extends NPC {

    private long time;
    private long spawnTentacleTime;
    private boolean underLava;
    private List<HarAkenTentacle> tentacles;
    private List<WorldTile> tentacleSpawns;

    private FightKiln controler;

    public void resetTimer() {
        underLava = !underLava;
        if (time == 0)
            spawnTentacleTime = Utils.currentTimeMillis() + 9000;
        time = Utils.currentTimeMillis() + (underLava ? 45000 : 30000);
    }

    public HarAken(int id, WorldTile tile, FightKiln controler) {
        super(id, tile, -1, true, true);
        setForceMultiArea(true);
        this.controler = controler;
        tentacles = new ArrayList<HarAkenTentacle>();
        tentacleSpawns = new ArrayList<WorldTile>();
    }

    @Override
    public void sendDeath(Entity source) {
        gfx(new Graphics(2924 + getSize()));
        if (time != 0) {
            removeTentacles();
            controler.removeNPC();
            time = 0;
        }
        super.sendDeath(source);
    }

    @Override
    public void processNPC() {
        if (isDead())
            return;
        cancelFaceEntityNoCheck();
    }

    public void process() {
        if (isDead())
            return;
        if (time != 0) {
            if (time < Utils.currentTimeMillis()) {
                if (underLava) {
                    controler.showHarAken();
                    resetTimer();
                } else
                    controler.hideHarAken();
            }
            if (spawnTentacleTime < Utils.currentTimeMillis())
                spawnTentacle();

        }
    }

    public void spawnTentacle() {
        WorldTile tile = controler.getTentacleTile();
		System.out.println("set tile to " + tile.getX()+ " - " + tile.getY());
        if (tentacleSpawns != null) {
            while (tentacleSpawns.contains(tile)) {
            	System.out.println("tentacle already spawned in " + tile.getX()+ " - " + tile.getY());
                tile = controler.getTentacleTile();
            }
        }
        tentacles.add(new HarAkenTentacle(Utils.random(1) == 0 ? 15209 : 15210, tile, this));
        tentacleSpawns.add(tile);
		System.out.println("spawned tentacle at " + tile.getX()+ " - " + tile.getY());
        spawnTentacleTime = Utils.currentTimeMillis() + Utils.random(15000, 25000);
    }

    public void removeTentacles() {
        for (HarAkenTentacle t : tentacles)
            t.finish();
        tentacles.clear();
        tentacleSpawns.clear();
    }

    public void removeTentacle(HarAkenTentacle tentacle) {
        WorldTile tile = tentacle.getTile();
        tentacles.remove(tentacle);
        tentacleSpawns.remove(tile);

    }

}
