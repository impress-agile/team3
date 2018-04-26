package rodeo.agile.impress.pos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemDao {

	String dbPath;

	public ItemDao(String path) {
		this.dbPath = path;
	}

	public void insert(String name, int price) throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		Connection connection = null;
		Statement statement = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + this.dbPath);
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.execute("INSERT INTO items (name, price) VALUES ('" + name + "', " + price + ");");
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				throw e;
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw e;
			}
		}
	}

	public List select() throws SQLException {

		Connection connection = null;
		Statement statement = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + this.dbPath);
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			ResultSet result = statement.executeQuery("SELECT * from items");

			List list = new ArrayList();

			while (result.next()) {
				Item item = new Item();
				item.setId(result.getInt("id"));
				item.setName(result.getString("name"));
				item.setPrice(result.getInt("price"));
				list.add(item);
			}

			return list;

		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				throw e;
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw e;
			}
		}

	}
}
