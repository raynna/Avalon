package com.rs.net.sql.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

import com.rs.game.player.content.grandexchange.Offer;
import com.rs.net.sql.DatabaseUtility;
import com.rs.net.sql.QueryTask;

public class sendGEOfferRemoval extends QueryTask {

	@Override
	public Optional<QueryTask> submitTask(Offer offer, boolean createNewStmt, boolean refresh) {
		try (Connection connection = DatabaseUtility.getConnection()) {
			connection.setAutoCommit(false);
			if (createNewStmt) {
				try (PreparedStatement saveStatement = connection.prepareStatement("delete from ge where hash = ?",
						PreparedStatement.RETURN_GENERATED_KEYS)) {
					saveStatement.setInt(1, offer.hashCode());
					int row = saveStatement.executeUpdate();
					connection.commit();
					connection.setAutoCommit(true);
					System.out.println("Removed offer hashcode: " + row);
					if (refresh) {
						try (PreparedStatement update = connection.prepareStatement(
								"INSERT INTO ge (id, hash, itemId, itemName, offerType, itemPrice, itemAmount, amountSold) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
								PreparedStatement.RETURN_GENERATED_KEYS)) {
							update.setInt(1, 0);
							update.setInt(2, offer.hashCode());
							update.setInt(3, offer.getId());
							update.setString(4, offer.getName());
							update.setBoolean(5, offer.isBuying());
							update.setInt(6, offer.getPrice());
							update.setInt(7, offer.getAmount());
							update.setInt(8, offer.getTotalAmmountSoFar());
							update.execute();
						}
						connection.commit();
						connection.setAutoCommit(true);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}
}