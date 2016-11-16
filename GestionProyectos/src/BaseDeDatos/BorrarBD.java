package BaseDeDatos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BorrarBD {

	public void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://192.168.33.10:3306", "root", "root");

		Statement stm = con.createStatement();
		stm.executeUpdate("DROP DATABASE BDdni_alumno");
		stm.close();
		con.close();
	}

}
