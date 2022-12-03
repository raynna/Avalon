package com.rs.net.sql.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

import com.rs.game.player.content.grandexchange.Offer;
import com.rs.net.sql.DatabaseUtility;
import com.rs.net.sql.QueryTask;

public class sendGEOffer extends QueryTask {
	
	
	@Override
	public Optional<QueryTask> submitTask(Offer offer, boolean createNewStmt, boolean refresh) {//that is null
		try (Connection connection = DatabaseUtility.getConnection()) {
			connection.setAutoCommit(false);
			if (createNewStmt) {
				try (PreparedStatement saveStatement = connection.prepareStatement(
						"INSERT INTO ge (id, hash, itemId, itemName, offerType, itemPrice, itemAmount, amountSold) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
						PreparedStatement.RETURN_GENERATED_KEYS)) {
					saveStatement.setInt(1, 0); // must be a constant as it auto increments for sql pool
					saveStatement.setInt(2, offer.hashCode());
					saveStatement.setInt(3, offer.getId());
					saveStatement.setString(4, offer.getName());
					saveStatement.setBoolean(5, offer.isBuying());
					saveStatement.setInt(6, offer.getPrice());
					saveStatement.setInt(7, offer.getAmount());
					saveStatement.setInt(8, offer.getTotalAmmountSoFar());
					saveStatement.execute();
				}
				connection.commit();
				connection.setAutoCommit(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}
}