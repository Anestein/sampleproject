package com.inventory.originssoft.inventoryserver.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

public class SqlDao {
	
	
	/*private static String dbURL = TravelersConfig.DB_URL;
	private static String dbUserName = TravelersConfig.DB_USERNAME;
	private static String dbPassword = TravelersConfig.DB_PASS;*/

private static final Logger logger = LoggerFactory.getLogger(SqlDao.class);
	
	private static BasicDataSource basicDataSource;

	private SqlDao() {
		throw new IllegalAccessError();
	}

	public static void _init() {
		if (basicDataSource == null || basicDataSource.isClosed()) {
			basicDataSource = new BasicDataSource();
			basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
			basicDataSource.setUrl("jdbc:mysql://localhost:3306/inventorydb?characterEncoding=UTF-8");
			basicDataSource.setUsername("root");
			basicDataSource.setPassword("IfssEgypt1");
			basicDataSource.setValidationQuery("SELECT 1");
			basicDataSource.setValidationQueryTimeout(1000);
		}
	}

	public static Connection getConnection() throws Throwable {
		_init();
		return basicDataSource.getConnection();
	}

	public static void closeResources(Connection connection, CallableStatement callableStatement) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {

			}
		}

		if (callableStatement != null) {
			try {
				callableStatement.close();
			} catch (SQLException e) {

			}
		}
	}

	public static void closeResources(Connection connection, CallableStatement callableStatement, ResultSet resultSet) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {

			}
		}

		if (callableStatement != null) {
			try {
				callableStatement.close();
			} catch (SQLException e) {

			}
		}

		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {

			}
		}
	}

	public static void closeResources(PreparedStatement preparedStatement, ResultSet resultSet) {
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {

			}
		}
	}

	public static void closeResources(Connection connection, PreparedStatement preparedStatement) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {

			}
		}

		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {

			}
		}
	}

	public static void closeResources(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {

			}
		}

		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {

			}
		}

		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {

			}
		}
	}
	
}
