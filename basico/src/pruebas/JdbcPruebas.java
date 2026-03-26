package pruebas;

import java.sql.*;

public class JdbcPruebas {
	public static void main(String[] args) throws SQLException {
		String url = "jdbc:sqlite:ejemplo.db"; // System.getenv("JDBC_URL");
		String usuario = "";
		String password = "";

		Connection con = DriverManager.getConnection(url, usuario, password);
		Statement st = con.createStatement();

		System.out.println(
				st.executeUpdate("INSERT INTO personas (nombre, fecha_nacimiento) VALUES ('Juan', '2000-01-02')"));

		ResultSet rs = st.executeQuery("SELECT * FROM personas");

		while (rs.next()) {
			System.out.printf("%5s %-20s %s\n", rs.getString("id"), rs.getString("nombre"),
					rs.getString("fecha_nacimiento"));
		}

	}
}
