package com.rs.game.player.actions.skills.agility.impl;

import com.rs.game.Animation;
import com.rs.game.ForceMovement;
import com.rs.game.Graphics;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.content.FadingScreen;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

public class AgilityFalador {
	
	/**
	 * 
	 *   @Param player
	 *   @Param object
	 
	/**
	 * Made by PhaseGFX
	 */
	 
		     
		     public static void FaladorCrumbledWall(Player player, WorldObject object) {
		    	  if (player.getX() == 2936 && player.getY() == 3355) {
		    		  player.faceObject(object);
		    		  player.animate(new Animation(12915, 1));
						final WorldTile toTile = new WorldTile(object.getX() - 1, object.getY(), object.getPlane());
						player.setNextForceMovement(new ForceMovement(player, 1, toTile, 3, ForceMovement.WEST));
						WorldTasksManager.schedule(new WorldTask() {
							@Override
							public void run() {
								player.setNextWorldTile(toTile);
							}
						}, 2);
				  return;
			  	}  
		    	  player.faceObject(object);
		    	 player.animate(new Animation(12916, 1));
					final WorldTile toTile = new WorldTile(object.getX() + 1, object.getY(), object.getPlane());
					player.setNextForceMovement(new ForceMovement(player, 1, toTile, 3, ForceMovement.EAST));
					WorldTasksManager.schedule(new WorldTask() {
						@Override
						public void run() {
							player.setNextWorldTile(toTile);
						}
					}, 2);
		    		}
		
		     
		     public static  void FaladorGrappleWall(final Player player, final WorldObject object) {
		    		if (player.getSkills().getLevel(Skills.AGILITY) < 11 
		    				    || player.getSkills().getLevel(Skills.RANGE) < 19 || 
		    				               player.getSkills().getLevel(Skills.STRENGTH) < 37) {
		    		player.getDialogueManager().startDialogue("You need a Agility level of 11, Ranged level of 19 and a Strength level of 37",
		    				  "respectively in order to use this obstacle.");
		    		return;	
		    		} 
			 		player.setRunHidden(false);
			 		player.lock(13);
				    WorldTasksManager.schedule(new WorldTask() {
			 		    @Override
			 		    public void run() {
					 		player.animate(new Animation(442));
							player.gfx(new Graphics(23));
							
			 		  }
			 		}, 1);
				    WorldTasksManager.schedule(new WorldTask() {
			 		    @Override
			 		    public void run() {
						    player.getSkills().addXp(Skills.AGILITY, 2.5);
						    if (player.getX() == 3005 && player.getY() == 3392) {
						    	 player.setNextWorldTile(new WorldTile(3005, 3393, 1));  
						    	 return;
						    }	
			 		    
			 			    player.setNextWorldTile(new WorldTile(3006, 3394, 1));  
			 			   
			 		  }
			 		}, 12);
			 	
		    
		     }
		     
		     
		     public static void FaladorTunnel(final Player player, final WorldObject object) {
		    	 if (player.getSkills().getLevel(Skills.AGILITY) < 26)  {
		    		 player.sm("You need a Agility level of 26 in order to use this obstacle.");
		    		 return;
		    	 }
		    	 WorldTasksManager.schedule(new WorldTask() {

		 			int ticks = 0;
		 			int id = object.getId();

		 			@Override
		 			public void run() {
		 			    boolean withinFalador = id == 9310;
		 			    WorldTile tile = withinFalador ? new WorldTile(2948, 3310, 0) : new WorldTile(2948, 3312, 0);
		 			    player.lock();
		 			    ticks++;
		 			    if (ticks == 1) {
		 				player.animate(new Animation(2589));
		 				player.setNextForceMovement(new ForceMovement(object, 1, withinFalador ? ForceMovement.SOUTH : ForceMovement.NORTH));
		 			    } else if (ticks == 3) {
		 				player.setNextWorldTile(new WorldTile(2948, 3311, 0));
		 				player.animate(new Animation(2590));
		 			    } else if (ticks == 5) {
		 				player.animate(new Animation(2591));
		 				player.setNextWorldTile(tile);
		 			    } else if (ticks == 6) {
		 				player.setNextWorldTile(new WorldTile(tile.getX(), tile.getY() + (withinFalador ? -1 : 1), tile.getPlane()));
		 				player.unlock();
		 				stop();
		 			    }
		 			}
		 		    }, 0, 0);
		 	}
		    
		     public static void FaladorJumpDown(final Player player,  final WorldObject object) {
		    	 player.faceObject(object);
		    		player.animate(new Animation(2147));
			 		player.setRunHidden(false);
			 		player.lock(2);
						 FadingScreen.fade(player, new Runnable() {

								@Override
								public void run() {
									player.unlock();
									player.animate(new Animation(-1));
									  if (player.getX() == 3006 && player.getY() == 3394) {
									    	 player.setNextWorldTile(new WorldTile(3006, 3396, 0));  
									    	 return;
									    }	
									player.setNextWorldTile(new WorldTile(3005,3391,0));
					  }		
					});
						    player.getSkills().addXp(Skills.AGILITY, 0.5); 
			 			   
				    }
		    	     
}