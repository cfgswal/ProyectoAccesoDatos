package BaseDeDatos;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Insertarsql {

	public void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://192.168.33.10:3306", "root", "root");

		Statement stm = con.createStatement();
		stm.executeUpdate("CREATE DATABASE IF NOT EXISTS BDdni_alumno");

		BufferedReader br = new BufferedReader(new FileReader(new File("creartablas.sql")));
		String linea = "";
		String salto = System.getProperty("line.separator");
		StringBuilder st = new StringBuilder();

		while ((linea = br.readLine()) != null) {
			st.append(linea);
			st.append(salto);
		}

		String consulta = st.toString();

		con = DriverManager.getConnection("jdbc:mysql://192.168.33.10:3306/BDdni_alumno?allowMultiQueries=true", "root",
				"root");

		stm = con.createStatement();
		stm.executeUpdate(consulta);

		stm.close();
		con.close();
	}
}
