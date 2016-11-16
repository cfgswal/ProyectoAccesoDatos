package Proveedores;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Rectangle;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.VetoableChangeListener;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GestionProveedores {

	private JFrame frame;
	private JTextField textFieldcodigo;
	private JTextField textFieldnombre;
	private JTextField textFieldapellido;
	private JTextField textFielddireccion;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private int id = 1;
	private int idfin = 0;

	/**
	 * Launch the application.
	 */
	public void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionProveedores window = new GestionProveedores();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GestionProveedores() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();

		frame.setBounds(100, 100, 547, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Gestion de Proveedores", null, panel, null);
		panel.setLayout(null);

		JLabel lblAltasBajasY = new JLabel("ALTAS, BAJAS Y MODIFICACIONES");
		lblAltasBajasY.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAltasBajasY.setBounds(50, 6, 257, 39);
		panel.add(lblAltasBajasY);
		lblAltasBajasY.setLayout(null);

		JLabel lblCodigoDeProveedor = new JLabel("Codigo de Proveedor");
		lblCodigoDeProveedor.setSize(145, 14);
		panel.add(lblCodigoDeProveedor);
		lblCodigoDeProveedor.setLayout(null);
		lblCodigoDeProveedor.setLocation(50, 57);

		JLabel labelnombre = new JLabel("Nombre");
		labelnombre.setBounds(50, 82, 86, 14);
		panel.add(labelnombre);

		JLabel labelapellidos = new JLabel("Apellidos");
		labelapellidos.setBounds(50, 107, 86, 14);
		panel.add(labelapellidos);

		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(50, 132, 96, 14);
		panel.add(lblDireccion);

		textFieldcodigo = new JTextField();
		textFieldcodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (textFieldcodigo.getText().length() > 6) {
					JOptionPane.showMessageDialog(null, "MAXIMO 6 CARACTERES");
					textFieldcodigo.setText(textFieldcodigo.getText().substring(0, 6));
				} else {
					try {
						textFieldcodigo.setText(textFieldcodigo.getText().toUpperCase());
						Class.forName("com.mysql.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://192.168.33.10:3306/BDdni_alumno",
								"root", "root");
						PreparedStatement query = con.prepareStatement(
								"SELECT NOMBRE,APELLIDOS,DIRECCION FROM proveedores where CODIGO = ?");

						query.setString(1, textFieldcodigo.getText());

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

			}
		});

		textFieldcodigo.setBounds(180, 54, 86, 20);
		panel.add(textFieldcodigo);
		textFieldcodigo.setColumns(10);

		textFieldnombre = new JTextField();
		textFieldnombre.setBounds(121, 79, 145, 20);
		panel.add(textFieldnombre);
		textFieldnombre.setColumns(10);

		textFieldapellido = new JTextField();
		textFieldapellido.setBounds(121, 104, 240, 20);
		panel.add(textFieldapellido);
		textFieldapellido.setColumns(10);

		textFielddireccion = new JTextField();
		textFielddireccion.setBounds(121, 129, 281, 20);
		panel.add(textFielddireccion);
		textFielddireccion.setColumns(10);

		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarcampos();
			}
		});
		btnLimpiar.setBounds(33, 177, 89, 23);
		panel.add(btnLimpiar);

		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://192.168.33.10:3306/BDdni_alumno", "root",
							"root");
					Statement stm = con.createStatement();
					ResultSet resul = stm.executeQuery(
							"select CODIGO from proveedores where CODIGO = '" + textFieldcodigo.getText() + "'");

					if (resul.next()) {
						JOptionPane.showMessageDialog(null, "ESTE PROVEEDOR YA EXISTE EN LA BASE DE DATOS ");
					} else {
						String query = "insert into proveedores values(?,?,?,?)";
						PreparedStatement preparedStmt = con.prepareStatement(query);

						preparedStmt.setString(1, textFieldcodigo.getText());

						if (textFieldnombre.getText().length() > 20) {
							preparedStmt.setString(2, textFieldnombre.getText().substring(0, 20));
						} else {
							preparedStmt.setString(2, textFieldnombre.getText());
						}
						if (textFieldapellido.getText().length() > 30) {
							preparedStmt.setString(3, textFieldapellido.getText().substring(0, 30));
						} else {
							preparedStmt.setString(3, textFieldapellido.getText());
						}
						if (textFielddireccion.getText().length() > 40) {
							preparedStmt.setString(4, textFielddireccion.getText().substring(0, 40));
						} else {
							preparedStmt.setString(4, textFielddireccion.getText());
						}
						preparedStmt.execute();
						preparedStmt.close();
						con.close();
						borrarcampos();
						JOptionPane.showMessageDialog(null, "PROVEEDOR INSERTADO CON EXITO");
					}
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnInsertar.setBounds(132, 177, 89, 23);
		panel.add(btnInsertar);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://192.168.33.10:3306/BDdni_alumno", "root",
							"root");
					Statement stm = con.createStatement();
					ResultSet resul = stm.executeQuery(
							"select CODIGO,NOMBRE,APELLIDOS,DIRECCION from proveedores where CODIGO = '" + textFieldcodigo.getText() + "'");

					if (resul.next()) {
						
						if(textFieldnombre.getText().contentEquals(resul.getString(2))&&textFieldapellido.getText().contentEquals(resul.getString(3))&&
								textFielddireccion.getText().contentEquals(resul.getString(4))){
							JOptionPane.showMessageDialog(null, "NO SE HA MODIFICADO NINGUN CAMPO");
						}else{
						String query = "update proveedores set NOMBRE = ?,APELLIDOS = ?,DIRECCION = ? where CODIGO = ?";
						PreparedStatement preparedStmt = con.prepareStatement(query);
						if (textFieldnombre.getText().length() > 20) {
							preparedStmt.setString(1, textFieldnombre.getText().substring(0, 20));
						} else {
							preparedStmt.setString(1, textFieldnombre.getText());
						}
						if (textFieldapellido.getText().length() > 30) {
							preparedStmt.setString(2, textFieldapellido.getText().substring(0, 30));
						} else {
							preparedStmt.setString(2, textFieldapellido.getText());
						}
						if (textFielddireccion.getText().length() > 40) {
							preparedStmt.setString(3, textFielddireccion.getText().substring(0, 40));
						} else {
							preparedStmt.setString(3, textFielddireccion.getText());
						}
						preparedStmt.setString(4, textFieldcodigo.getText());
						preparedStmt.execute();
						preparedStmt.close();
						con.close();
						borrarcampos();
						JOptionPane.showMessageDialog(null, "PROVEEDOR MODIFICADO CON EXITO");
						}
					} else {
						JOptionPane.showMessageDialog(null, "ESTE PROVEEDOR NO EXISTE EN LA BASE DE DATOS ");
					}
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnModificar.setBounds(231, 177, 89, 23);
		panel.add(btnModificar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://192.168.33.10:3306/BDdni_alumno", "root",
							"root");
					Statement stm = con.createStatement();
					ResultSet resul = stm.executeQuery("select CODPROVEEDOR from gestion where CODPROVEEDOR = '"
							+ textFieldcodigo.getText() + "'");

					if (resul.next()) {
						JOptionPane.showMessageDialog(null, "NO SE PUEDE BORRAR UN PROVEEDOR CON UNA GESTION RELIZADA");
					} else {
						stm.executeUpdate("delete from proveedores where CODIGO = '" + textFieldcodigo.getText() + "'");
						stm.close();
						con.close();
						borrarcampos();
						JOptionPane.showMessageDialog(null, "PROVEEDOR ELIMINADO CON EXITO");
					}
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnEliminar.setBounds(330, 177, 89, 23);
		panel.add(btnEliminar);
		lblCodigoDeProveedor = new JLabel("Codigo de Proveedor");
		lblCodigoDeProveedor.setSize(145, 14);
		panel.add(lblCodigoDeProveedor);
		lblCodigoDeProveedor.setLayout(null);
		lblCodigoDeProveedor.setLocation(50, 57);
		labelnombre.setBounds(50, 82, 86, 14);
		JPanel panel_1 = new JPanel();
		JLabel labelini = new JLabel("__");
		labelini.setEnabled(false);
		labelini.setBounds(20, 167, 22, 16);
		panel_1.add(labelini);

		JLabel lblDe = new JLabel("DE");
		lblDe.setBounds(37, 167, 22, 16);
		panel_1.add(lblDe);

		JLabel labelfin = new JLabel("1");
		labelfin.setEnabled(false);
		labelfin.setBounds(60, 167, 22, 16);
		labelfin.setText("__");
		panel_1.add(labelfin);
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(180, 54, 86, 20);
		panel_1.add(textField);

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(121, 79, 145, 20);
		panel_1.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(121, 104, 240, 20);
		panel_1.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(121, 129, 281, 20);
		panel_1.add(textField_3);
		panel_1.setLayout(null);
		tabbedPane.addTab("Listado de Proveedores", null, panel_1, null);

		JLabel label_2 = new JLabel("Codigo de Proveedor");
		label_2.setBounds(50, 57, 145, 14);
		panel_1.add(label_2);

		JLabel label_3 = new JLabel("Nombre");
		label_3.setBounds(50, 82, 86, 14);
		panel_1.add(label_3);

		JLabel label_4 = new JLabel("Apellidos");
		label_4.setBounds(50, 107, 86, 14);
		panel_1.add(label_4);

		JLabel label_5 = new JLabel("Direccion");
		label_5.setBounds(50, 132, 96, 14);
		panel_1.add(label_5);

		JButton button = new JButton("|<<");
		button.setEnabled(false);
		JButton button_3 = new JButton(">>|");
		button_3.setEnabled(false);
		JButton button_1 = new JButton("<<");
		button_1.setEnabled(false);
		JButton button_2 = new JButton(">>");
		button_2.setEnabled(false);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				id = 1;
				labelini.setText("" + id);
				button.setEnabled(false);
				button_1.setEnabled(false);
				button_2.setEnabled(true);
				button_3.setEnabled(true);
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://192.168.33.10:3306/BDdni_alumno", "root",
							"root");
					Statement stm = con.createStatement();

					ResultSet resul = stm.executeQuery("select * from proveedores");
					resul.last();
					idfin = resul.getRow();
					labelfin.setText(""+idfin);
					resul.absolute(id);

					textField.setText(resul.getString(1));
					textField_1.setText(resul.getString(2));
					textField_2.setText(resul.getString(3));
					textField_3.setText(resul.getString(4));
					resul.close();
					stm.close();
					con.close();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setBounds(33, 203, 89, 23);
		panel_1.add(button);
		
		
		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				id = id - 1;
				labelini.setText("" + id);
				button_2.setEnabled(true);
				button_3.setEnabled(true);
				if (id == 1) {
					button.setEnabled(false);
					button_1.setEnabled(false);
				}
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://192.168.33.10:3306/BDdni_alumno", "root",
							"root");
					Statement stm = con.createStatement();
					ResultSet resul = stm.executeQuery("select * from proveedores");

					resul.absolute(id);

					textField.setText(resul.getString(1));
					textField_1.setText(resul.getString(2));
					textField_2.setText(resul.getString(3));
					textField_3.setText(resul.getString(4));
					resul.close();
					stm.close();
					con.close();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_1.setBounds(132, 203, 89, 23);
		panel_1.add(button_1);

		
		button_2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				id = id + 1;
				if(id ==idfin){
					button_2.setEnabled(false);
					button_3.setEnabled(false);
					
				}
				button.setEnabled(true);
				button_1.setEnabled(true);
				labelini.setText("" + id);
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://192.168.33.10:3306/BDdni_alumno", "root",
							"root");
					Statement stm = con.createStatement();
					ResultSet resul = stm.executeQuery("select * from proveedores");

					resul.absolute(id);

					textField.setText(resul.getString(1));
					textField_1.setText(resul.getString(2));
					textField_2.setText(resul.getString(3));
					textField_3.setText(resul.getString(4));
					resul.close();
					stm.close();
					con.close();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		button_2.setBounds(231, 203, 89, 23);
		panel_1.add(button_2);

		
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_2.setEnabled(false);
				button_3.setEnabled(false);
				button.setEnabled(true);
				button_1.setEnabled(true);
				labelini.setText("" + idfin);
				id=idfin;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://192.168.33.10:3306/BDdni_alumno", "root",
							"root");
					Statement stm = con.createStatement();

					ResultSet resul = stm.executeQuery("select * from proveedores");
					resul.last();					
						textField.setText(resul.getString(1));
						textField_1.setText(resul.getString(2));
						textField_2.setText(resul.getString(3));
						textField_3.setText(resul.getString(4));
						resul.close();
						stm.close();
						con.close();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_3.setBounds(330, 203, 89, 23);
		panel_1.add(button_3);

		JLabel label_6 = new JLabel("Codigo de Proveedor");
		label_6.setBounds(50, 57, 145, 14);
		panel_1.add(label_6);

		JLabel lblListaDeProveedores = new JLabel(
				"LISTA DE PROVEEDORES - UTILIZA LOS BOTONES PARA IR DE UN REG A OTRO");
		lblListaDeProveedores.setBounds(19, 10, 479, 15);
		panel_1.add(lblListaDeProveedores);
		lblListaDeProveedores.setFont(new Font("Tahoma", Font.BOLD, 12));

		JButton btnBaja = new JButton("BAJA");
		btnBaja.setEnabled(false);
		btnBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://192.168.33.10:3306/BDdni_alumno", "root",
							"root");
					Statement stm = con.createStatement();
					ResultSet resul = stm.executeQuery("select CODPROVEEDOR from gestion where CODPROVEEDOR = '"
							+ textField.getText() + "'");

					if (resul.next()) {
						JOptionPane.showMessageDialog(null, "NO SE PUEDE BORRAR UN PROVEEDOR CON UNA GESTION RELIZADA");
					} else {
						stm.executeUpdate("delete from proveedores where CODIGO = '" + textField.getText() + "'");
						stm.close();
						con.close();
						textField.setText("");
						textField_1.setText("");
						textField_2.setText("");
						textField_3.setText("");
						JOptionPane.showMessageDialog(null, "PROVEEDOR ELIMINADO CON EXITO");
					}
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnBaja.setBounds(403, 161, 117, 29);
		panel_1.add(btnBaja);

		JButton btnNewButton = new JButton("EJECUTAR CONSULTA");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button.setEnabled(true);
				button_1.setEnabled(true);
				button_2.setEnabled(true);
				button_3.setEnabled(true);
				btnBaja.setEnabled(true);
				id = 1;
				labelini.setText("" + id);
				button.setEnabled(false);
				button_1.setEnabled(false);
				button_2.setEnabled(true);
				button_3.setEnabled(true);
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://192.168.33.10:3306/BDdni_alumno", "root",
							"root");
					Statement stm = con.createStatement();

					ResultSet resul = stm.executeQuery("select * from proveedores");
					resul.last();
					idfin = resul.getRow();
					labelfin.setText(""+idfin);
					resul.absolute(id);

					textField.setText(resul.getString(1));
					textField_1.setText(resul.getString(2));
					textField_2.setText(resul.getString(3));
					textField_3.setText(resul.getString(4));
					resul.close();
					stm.close();
					con.close();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnNewButton.setBounds(110, 162, 293, 29);
		panel_1.add(btnNewButton);

	}

	private void borrarcampos() {
		textFieldcodigo.setText("");
		textFieldnombre.setText("");
		textFieldapellido.setText("");
		textFielddireccion.setText("");

	}
}
