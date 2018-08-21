package edu.jhu.sample.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import edu.jhu.sample.results.ResultExpenseBean;
import edu.jhu.sample.results.ResultUserBean;

public class CommandsDB {

	public static boolean emailExists(Connection connection, String email) throws SQLException {
		String sqlStatement = String.format("SELECT Email FROM Users " + "WHERE Email = '%s'", email);
		ResultSet resultSet = null;

		try {
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlStatement);
			boolean emailExists = resultSet.next();
			statement.close();
			resultSet.close();
			return emailExists;
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
	}

	public static void insertUser(Connection connection, ResultUserBean user) throws SQLException {
		// Users DB
		String sqlStatement = String.format("INSERT INTO Users (Name, Email, Password) " + "VALUES ('%s', '%s', '%s')",
				user.getName(), user.getEmail(), user.getPassword());
		Statement statement = connection.createStatement();
		statement.executeUpdate(sqlStatement);

		statement.close();
	}

	public static ResultUserBean selectUser(Connection connection, ResultUserBean user) throws SQLException {
		ResultUserBean validUser = new ResultUserBean();
		ResultSet resultSet = null;

		try {
			// Users DB
			String sqlStatement = String.format("SELECT * FROM Users " + "WHERE Email = '%s' AND Password = '%s'",
					user.getEmail(), user.getPassword());
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlStatement);

			if (resultSet.next()) {
				validUser.setEmail(resultSet.getString("Email"));
				validUser.setName(resultSet.getString("Name"));
				validUser.setPassword(resultSet.getString("Password"));
			}

			resultSet.close();
			statement.close();

			return validUser;
		} catch (SQLException e) {
			System.out.println(e);
			return validUser;
		}
	}

	public static void insertExpense(Connection connection, String email, ResultExpenseBean expense)
			throws SQLException {
		// Expenses DB
		String sqlStatement = String.format(
				"INSERT INTO Expenses (Email, Note, Amount, Description, Date) "
						+ "VALUES ('%s', '%s', '%s', '%s', '%s')",
				email, expense.getNote(), expense.getAmount(), expense.getDescription(), expense.getDate());
		Statement statement = connection.createStatement();
		statement.executeUpdate(sqlStatement);

		statement.close();
	}

	public static ResultExpenseBean selectExpense(Connection connection, Integer expenseID) throws SQLException {
		ResultExpenseBean validExpense = new ResultExpenseBean();
		ResultSet resultSet = null;

		try {
			// Users DB
			String sqlStatement = String.format("SELECT * FROM Expenses " + "WHERE ID = '%d'", expenseID);
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlStatement);

			if (resultSet.next()) {
				validExpense.setId(resultSet.getInt("ID"));
				validExpense.setAmount(resultSet.getString("Amount"));
				validExpense.setNote(resultSet.getString("Note"));
				validExpense.setDescription(resultSet.getString("Description"));
				validExpense.setDate(resultSet.getString("Date"));
			}

			resultSet.close();
			statement.close();

			return validExpense;
		} catch (SQLException e) {
			System.out.println(e);
			return validExpense;
		}
	}

	public static void editExpense(Connection connection, Integer expenseID, ResultExpenseBean expense)
			throws SQLException {
		// Expenses DB
		String sqlStatement = String.format(
				"UPDATE Expenses " + "SET Note = '%s', Description = '%s', Amount = '%s', Date = '%s' "
						+ "WHERE ID = '%d'",
				expense.getNote(), expense.getDescription(), expense.getAmount(), expense.getDate(), expenseID);
		Statement statement = connection.createStatement();
		statement.executeUpdate(sqlStatement);

		statement.close();
	}

	public static void deleteExpense(Connection connection, Integer expenseID) throws SQLException {
		// Expenses DB
		String sqlStatement = String.format("DELETE FROM Expenses " + "WHERE ID = '%d'", expenseID);
		Statement statement = connection.createStatement();
		statement.executeUpdate(sqlStatement);

		statement.close();
	}

	public static List<ResultExpenseBean> retrieveExpenses(Connection connection, String email) throws SQLException {
		ResultSet resultSet = null;
		List<ResultExpenseBean> expenses = new ArrayList<>();

		try {
			// Expenses DB
			String sqlStatement = String.format("SELECT * FROM Expenses " + "WHERE Email = '%s'", email);
			Statement statement = connection.createStatement();
			boolean isResultSet = statement.execute(sqlStatement);

			while (true) {
				if (isResultSet) {
					ResultExpenseBean expense;
					resultSet = statement.getResultSet();
					while (resultSet.next()) {
						expense = new ResultExpenseBean();
						expense.setId(resultSet.getInt("ID"));
						expense.setAmount(resultSet.getString("Amount"));
						expense.setNote(resultSet.getString("Note"));
						expense.setDescription(resultSet.getString("Description"));
						expense.setDate(resultSet.getString("Date"));
						expenses.add(expense);
					}
					resultSet.close();

				} else {
					if (statement.getUpdateCount() == -1) {
						break;
					}
				}
				isResultSet = statement.getMoreResults();
			}
			resultSet.close();
			statement.close();

			return expenses;
		} catch (SQLException e) {
			System.out.println(e);
			return expenses;
		}
	}
}