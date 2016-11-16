package Proveedores;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Panel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ConsultaProveedorCodigo extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaProveedorCodigo frame = new ConsultaProveedorCodigo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ConsultaProveedorCodigo() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 547, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Panel panel = new Panel();
		panel.setBounds(0, 0, 547, 278);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Escribe el codigo o parte del codigo");
		lblNewLabel.setBounds(18, 21, 230, 16);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
					try {
						textField.setText(textField.getText().toUpperCase());
						Class.forName("com.mysql.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://192.168.33.10:3306/BDdni_alumno",
								"root", "root");
						PreparedStatement query = con.prepareStatement(
								"SELECT NOMBRE,APELLIDOS,DIRECCION FROM proveedores where CODIGO = ?");

						query.setString(1, textField.getText());

						query.executeQuery();
						ResultSet resul = query.getResultSet();
						while (resul.next()) {
							textFieldnombre.setText(resul.getString(1));
							textFieldapellido.setText(resul.getString(2));
							textFielddireccion.setText(resul.getString(3));

						}
						resul.close();
						query.close();
						con.close();

					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		textField.setBounds(260, 15, 81, 28);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel LabelDescripprov = new JLabel("New label");
		LabelDescripprov.setBounds(43, 121, 61, 16);
		panel.add(LabelDescripprov);
		
		JButton btnBuscar = new JButton("Buscar Proveedor");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://192.168.33.10:3306/BDdni_alumno", "root",
							"root");
					Statement stm = con.createStatement();
					ResultSet resul = stm.executeQuery("select CODPROVEEDOR from gestion where CODPROVEEDOR = '"
							+ textField.getText() + "'");	
					LabelDescripprov.setText("CODIGO: "+textField.getText()+"/nNOMBRE: ");
						stm.close();
						con.close();
						
					
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnBuscar.setBounds(344, 16, 197, 29);
		panel.add(btnBuscar);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(18, 99, 511, 173);
		panel.add(textPane);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(18, 60, 322, 27);
		panel.add(comboBox);
		
		
	}
}
