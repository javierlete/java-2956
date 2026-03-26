package pruebas;

import java.sql.*;
import java.time.LocalDate;

public class JdbcPruebas {
	public static void main(String[] args) throws SQLException {
		String url = "jdbc:sqlite:ejemplo.db"; // System.getenv("JDBC_URL");
		String usuario = "";
		String password = "";

		Connection con = DriverManager.getConnection(url, usuario, password);
		Statement st = con.createStatement();

//		System.out.println(
//				st.executeUpdate("INSERT INTO personas (nombre, fecha_nacimiento) VALUES ('Juan', '2000-01-02')"));

		String nombreIntroducido = "María"; // "Robert', ''); DROP TABLE personas; --";
		LocalDate fechaNacimientoIntroducida = LocalDate.of(2001, 2, 3);

		String sql = "INSERT INTO personas (nombre, fecha_nacimiento) VALUES (?,?)";

		PreparedStatement pst = con.prepareStatement(sql);

		System.out.println(sql);

		pst.setString(1, nombreIntroducido);
		pst.setDate(2, java.sql.Date.valueOf(fechaNacimientoIntroducida));

		System.out.println(pst.executeUpdate());

		ResultSet rs = st.executeQuery("SELECT * FROM personas");

		while (rs.next()) {
			String id = rs.getString("id");
			String nombre = rs.getString("nombre");
			java.sql.Date fechaNacimiento = rs.getDate("fecha_nacimiento");

			System.out.printf("%5s %-20s %s\n", id, nombre,
					fechaNacimiento != null ? fechaNacimiento.toLocalDate() : "");
		}

	}
}
