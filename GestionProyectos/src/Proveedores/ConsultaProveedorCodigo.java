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
import javax.swing.JDialog;

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
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

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
		
		JDialog.setDefaultLookAndFeelDecorated(true);
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
		
		textField.setBounds(260, 15, 81, 28);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(43, 121, 472, 28);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("New label");
		label_2.setBounds(43, 161, 472, 28);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("New label");
		label_3.setBounds(43, 201, 472, 28);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("New label");
		label_4.setBounds(43, 241, 472, 28);
		panel.add(label_4);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://192.168.33.10:3306/BDdni_alumno", "root",
							"root");
					Statement stm = con.createStatement();
				if(comboBox.getSelectedItem()!=null){
					String codigo = comboBox.getSelectedItem().toString();
				
					ResultSet resultado = stm.executeQuery("select CODIGO,NOMBRE,APELLIDOS,DIRECCION from proveedores where CODIGO = '"
							+codigo+ "'");	
				
					while(resultado.next()){
					label_1.setText("CODIGO:	" + resultado.getString(1));
					label_2.setText("NOMBRE:	" + resultado.getString(2));
					label_3.setText("APELLIDO:	" + resultado.getString(3));
					label_4.setText("DIRECCION:	" + resultado.getString(4));
					}
					stm.close();
					con.close();
				}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		comboBox.setBounds(18, 60, 322, 27);
		panel.add(comboBox);
		
		
		JButton btnBuscar = new JButton("Buscar Proveedor");
		btnBuscar.setBounds(344, 16, 197, 29);
		panel.add(btnBuscar);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.removeAllItems();
				label_1.setText("");
				label_2.setText("");
				label_3.setText("");
				label_4.setText("");
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://192.168.33.10:3306/BDdni_alumno", "root",
							"root");
					Statement stm = con.createStatement();
				
					ResultSet resultado = stm.executeQuery("select CODIGO from proveedores where CODIGO like '"
							+ textField.getText() + "%" + "'");	
					
					if(resultado.next() ){
						comboBox.addItem(resultado.getString(1));
						while(resultado.next()){
						comboBox.addItem(resultado.getString(1));
					
						
						}
						
					}else{
						JOptionPane.showMessageDialog(null, "NO EXISTE ESTE PROVEEDOR","TITULO VENTANA",JOptionPane.ERROR_MESSAGE);
					}
						stm.close();
						con.close();
						
					
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
	
		
		
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(18, 99, 511, 173);
		panel.add(textPane);
		
		
		
		
		
		
	}
}
