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
		
		JLabel LabelDescripprov = new JLabel("New label");
		LabelDescripprov.setBounds(43, 121, 61, 16);
		panel.add(LabelDescripprov);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
			}
			
		});
		comboBox.setBounds(18, 60, 322, 27);
		panel.add(comboBox);
		
		JButton btnBuscar = new JButton("Buscar Proveedor");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.removeAllItems();
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://192.168.33.10:3306/BDdni_alumno", "root",
							"root");
					Statement stm = con.createStatement();
				
					ResultSet resul = stm.executeQuery("select CODIGO from proveedores where CODIGO like '"
							+ textField.getText() + "%" + "'");	
					
					if(resul.next()&& textField.getText().length()>0){
						comboBox.addItem(resul.getString(1));
						while(resul.next()){
						comboBox.addItem(resul.getString(1));
						//prueba
						
						}
						
					}else{
						JOptionPane.showMessageDialog(null, "NO EXISTE ESTE PROVEEDOR");
					}
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
		
		
		
		
	}
}
