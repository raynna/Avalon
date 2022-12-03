package com.rs.net.sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.rs.game.player.Player;
import com.rs.game.player.content.grandexchange.Offer;

public final class DatabaseUtility {

    private final static ComboPooledDataSource dataSource = new ComboPooledDataSource();
    private static boolean initiated = false;

    /**
     * initiates the datasource
     *
     * @throws Exception
     */
    public static void init() throws Exception {
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl(
                "jdbc:mysql://162.218.48.239:3306/hdbytes1_avalondb?useServerPrepStmts=false&rewriteBatchedStatements=true&autoReconnect=true");
        dataSource.setUser("hdbytes1_avalon");
        dataSource.setPassword("RUCg^rg%np_V");
        dataSource.setTestConnectionOnCheckout(true);
        dataSource.setTestConnectionOnCheckin(true);
        dataSource.setIdleConnectionTestPeriod(30);
        dataSource.setMaxStatementsPerConnection(50);
        dataSource.setMinPoolSize(3);
        dataSource.setMaxPoolSize(100);
        initiated = true;
        System.out.println(initiated ? "Online" : "offline");
    }

    /**
     * Gets a Jdbc connection from the datasource
     *
     * @return Jdbc connection
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        if (!initiated) {
            throw new RuntimeException("datasource has not been initiated");
        }
        return dataSource.getConnection();
    }

    /**
     * Submits the task
     *
     * @param offer
     * @param task
     * @return
     */
    public static Optional<QueryTask> sendTask(Offer offer, QueryTask task, boolean refresh) {
        return task.submitTask(offer, true, refresh);
    }
}