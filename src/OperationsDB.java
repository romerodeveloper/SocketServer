import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OperationsDB {
	Connection con = null;

	public Ciudad_Persona Buscar(String telefono) {
		try {
			con = UConnection.getConnection();

			String consultaSQL = "select persona.dir_nombre, persona.dir_tel, persona.dir_direccion, ciudad.ciud_nombre FROM ciudad INNER JOIN persona ON ciudad.ciud_id=persona.dir_ciud_id where persona.dir_tel = ?";
			PreparedStatement statement = con.prepareStatement(consultaSQL);
			statement.setString(1, telefono);
			ResultSet resultado = statement.executeQuery();
			Ciudad_Persona dato = new Ciudad_Persona();
			while (resultado.next()) {
				dato.setDir_tel(resultado.getString("dir_tel"));
				dato.setCiud_nombre(resultado.getString("ciud_nombre"));
				dato.setDir_direccion(resultado.getString("dir_direccion"));
				dato.setDir_nombre(resultado.getString("dir_nombre"));

			}

			resultado.close();
			statement.close();

			return dato;
		} catch (SQLException e) {
			e.printStackTrace();
			return new Ciudad_Persona();
		}
	}


}
