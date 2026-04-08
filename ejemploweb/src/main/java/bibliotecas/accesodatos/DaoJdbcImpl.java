package bibliotecas.accesodatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;
import java.util.function.Function;

import bibliotecas.fabrica.Fabrica;
import bibliotecas.fabrica.FabricaException;

public class DaoJdbcImpl implements DaoJdbc {
	private final String url;
	private final String user;
	private final String pass;
	
	public DaoJdbcImpl() {
		Properties props = new Properties();
		
		try {
			props.load(Fabrica.class.getClassLoader().getResourceAsStream("accesodatos.properties"));
			
			this.url = props.getProperty("url");
			this.user = props.getProperty("user");
			this.pass = props.getProperty("pass");
		} catch (Exception e) {
			throw new FabricaException("No se ha podido cargar la configuración", e);
		}
	}
	
	@Override
	public <T> T ejecutarSqlUno(String sql, Function<ResultSet, T> mapeador, T objeto, Object... args) {
		Iterable<T> objetos = ejecutarSql(sql, mapeador, objeto, args);

		if (objetos.iterator().hasNext()) {
			return objetos.iterator().next();
		}

		return null;
	}

	@Override
	public <T> Iterable<T> ejecutarSql(String sql, Function<ResultSet, T> mapeador, T objeto, Object... args) {
		Collection<T> objetos = new ArrayList<>();

		try (Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement pst = con.prepareStatement(sql);) {
			for (int i = 0; i < args.length; i++) {
				pst.setObject(i + 1, args[i]);
			}

			if (sql.trim().startsWith("SELECT")) {
				try (ResultSet rs = pst.executeQuery()) {
					while (rs.next()) {
						objetos.add(mapeador.apply(rs));
					}

					return objetos;
				}
			} else {
				int numero = pst.executeUpdate();

				if(numero == 0) {
					throw new DaoException("No se ha encontrado el registro a modificar/borrar");
				}
				
				objetos.add(objeto);

				return objetos;
			}
		} catch (SQLException e) {
			throw new DaoException("Error en la capa DAO", e);
		}
	}

}
