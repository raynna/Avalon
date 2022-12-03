package com.rs.net.sql;

import java.util.Optional;

import com.rs.game.player.Player;
import com.rs.game.player.content.grandexchange.Offer;

/**
 * A Query Task handler for sending various information
 * to the Avalon database, used primarily to record issues,
 * debugging, much more.
 * @author .css
 *
 */
public abstract class QueryTask {

	/**
	 * The Task that is being submited to the Database
	 * via query task
	 * @param offer
	 * @param createNewStmt
	 * @return {@link #submitTask(Player, boolean)}
	 */
	public abstract Optional<QueryTask> submitTask(Offer offer, boolean createNewStmt, boolean refresh);

}
