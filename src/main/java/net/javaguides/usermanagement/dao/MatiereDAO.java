package net.javaguides.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.javaguides.usermanagement.model.Matiere;


public class MatiereDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";

	private static final String INSERT_MATIERES_SQL = "INSERT INTO matiere" + "  (nomMatiere, coefficient) VALUES "+ " (?, ?);";
	private static final String SELECT_MATIERE_BY_ID = "select idMatiere,nomMatiere,coefficient from matiere where idMatiere =?";
	private static final String SELECT_ALL_MATIERES = "select * from matiere";
	private static final String DELETE_MATIERES_SQL = "delete from matiere where idMatiere = ?;";
	private static final String UPDATE_MATIERES_SQL = "update matiere set nomMatiere = ?,coefficient= ? where idMatiere = ?;";

	public MatiereDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public void insertMatiere(Matiere matiere) throws SQLException {
		System.out.println(INSERT_MATIERES_SQL);
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MATIERES_SQL)) {
			preparedStatement.setString(1, matiere.getNomMatiere());
			preparedStatement.setDouble(2, matiere.getCoefficient());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Matiere selectMatiere(Long id) {
		Matiere matiere = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MATIERE_BY_ID);) {
			preparedStatement.setLong(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String nomMatiere = rs.getString("nomMatiere");
				Double coefficient = rs.getDouble("coefficient");
				matiere = new Matiere(id, nomMatiere, coefficient);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return matiere;
	}

	public List<Matiere> selectAllMatieres() {

		List<Matiere> matieres = new ArrayList<>();
		try (Connection connection = getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MATIERES );) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Long idMatiere = rs.getLong("idMatiere");
				String nomMatiere = rs.getString("nomMatiere");
				Double coefficient = rs.getDouble("coefficient");
				matieres.add(new Matiere(idMatiere, nomMatiere, coefficient));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return matieres;
	}

	public boolean deleteMatiere(Long id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_MATIERES_SQL);) {
			statement.setLong(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateMatiere(Matiere matiere) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_MATIERES_SQL);) {
			statement.setString(1, matiere.getNomMatiere());
			statement.setDouble(2, matiere.getCoefficient());
			statement.setLong(3, matiere.getIdMatiere()); 

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
