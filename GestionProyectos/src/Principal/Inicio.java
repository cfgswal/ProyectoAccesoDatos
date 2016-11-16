package Principal;

import BaseDeDatos.*;
import Proveedores.ConsultaProveedorCodigo;
import Proveedores.GestionProveedores;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Inicio {

	boolean bd = false;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio window = new Inicio();
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
	public Inicio() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 547, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnBaseDeDatos = new JMenu("Base de Datos");
		menuBar.add(mnBaseDeDatos);

		JMenuItem mnCrear = new JMenuItem("Crear Base de Datos");
		mnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (bd == false) {
					bd = true;
					Insertarsql isql = new Insertarsql();
					try {
						isql.main(null);
					} catch (ClassNotFoundException | IOException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "BASE DE DATOS CREADA CON EXITO");

				} else {
					JOptionPane.showMessageDialog(null, "YA ESTA CREADA LA BASE DE DATOS");
				}
			}

		});
		mnBaseDeDatos.add(mnCrear);

		JMenuItem mnBorrarBaseDe = new JMenuItem("Borrar Base de Datos");
		mnBorrarBaseDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (bd == true) {
					BorrarBD isql = new BorrarBD();
					try {
						isql.main(null);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "BASE DE DATOS BORRADA CON EXITO");
					bd = false;
				} else {

					JOptionPane.showMessageDialog(null, "HAY QUE CREAR LA BASE DE DATOS PRIMERO");
				}
			}
		});
		mnBaseDeDatos.add(mnBorrarBaseDe);

		JMenuItem mnNewMenu = new JMenuItem("Salir");
		mnNewMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnBaseDeDatos.add(mnNewMenu);

		JMenu mnProveedores = new JMenu("Proveedores");
		mnProveedores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (bd == false) {
					JOptionPane.showMessageDialog(null, "HAY QUE CREAR LA BASE DE DATOS PRIMERO");
				}
			}
		});
		menuBar.add(mnProveedores);

		JMenuItem mnGestinDeProveedores = new JMenuItem("Gesti\u00F3n de Proveedores");
		mnGestinDeProveedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionProveedores gp = new GestionProveedores();
				gp.main(null);
			}
		});

		mnProveedores.add(mnGestinDeProveedores);

		JMenu mnConsultaDeProveedores = new JMenu("Consulta de Proveedores");
		mnProveedores.add(mnConsultaDeProveedores);

		JMenuItem mnPorCdigo = new JMenuItem("Por C\u00F3digo");
		mnPorCdigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultaProveedorCodigo cpc = new ConsultaProveedorCodigo();
				cpc.main(null);
			}
		});
		mnConsultaDeProveedores.add(mnPorCdigo);

		JMenuItem mnPorNombre = new JMenuItem("Por Nombre");
		mnConsultaDeProveedores.add(mnPorNombre);

		JMenuItem mnPorDireccin = new JMenuItem("Por Direcci\u00F3n");
		mnConsultaDeProveedores.add(mnPorDireccin);

		JMenu mnPiezas = new JMenu("Piezas");
		mnPiezas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (bd == false) {
					JOptionPane.showMessageDialog(null, "HAY QUE CREAR LA BASE DE DATOS PRIMERO");
				}
			}
		});
		

		menuBar.add(mnPiezas);

		JMenuItem mnGestinDePiezas = new JMenuItem("Gesti\u00F3n de Piezas");
		mnPiezas.add(mnGestinDePiezas);

		JMenu mnConsultaDePiezas = new JMenu("Consulta de Piezas");
		mnPiezas.add(mnConsultaDePiezas);

		JMenuItem mnPorCdigo_1 = new JMenuItem("Por C\u00F3digo");
		mnConsultaDePiezas.add(mnPorCdigo_1);

		JMenuItem mnPorNombre_1 = new JMenuItem("Por Nombre");
		mnConsultaDePiezas.add(mnPorNombre_1);

		JMenuItem mnPorDireccin_1 = new JMenuItem("Por Direcci\u00F3n");
		mnConsultaDePiezas.add(mnPorDireccin_1);

		JMenu mnProyectos = new JMenu("Proyectos");
		mnProyectos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (bd == false) {
					JOptionPane.showMessageDialog(null, "HAY QUE CREAR LA BASE DE DATOS PRIMERO");
				}
			}
		});
		

		menuBar.add(mnProyectos);

		JMenuItem mnGestinDeProyectos = new JMenuItem("Gesti\u00F3n de Proyectos");
		mnProyectos.add(mnGestinDeProyectos);

		JMenu mnConsultaDeProyectos = new JMenu("Consulta de Proyectos");
		mnProyectos.add(mnConsultaDeProyectos);

		JMenuItem mnPorCdigo_2 = new JMenuItem("Por C\u00F3digo");
		mnConsultaDeProyectos.add(mnPorCdigo_2);

		JMenuItem mnPorNombre_2 = new JMenuItem("Por Nombre");
		mnConsultaDeProyectos.add(mnPorNombre_2);

		JMenuItem mnPorDireccin_2 = new JMenuItem("Por Direcci\u00F3n");
		mnConsultaDeProyectos.add(mnPorDireccin_2);

		JMenu mnGestinGlobal = new JMenu("Gesti\u00F3n Global");
		mnGestinGlobal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (bd == false) {
					JOptionPane.showMessageDialog(null, "HAY QUE CREAR LA BASE DE DATOS PRIMERO");
				}
			}
		});
		

		menuBar.add(mnGestinGlobal);

		JMenuItem mnPiezasProveedoresY = new JMenuItem("Piezas, Proveedores y Proyectos");
		mnGestinGlobal.add(mnPiezasProveedoresY);

		JMenuItem mnSuministrosPorProveedor = new JMenuItem("Suministros por Proveedor");
		mnGestinGlobal.add(mnSuministrosPorProveedor);

		JMenuItem mnSuministrosPorPiezas = new JMenuItem("Suministros por Piezas");
		mnGestinGlobal.add(mnSuministrosPorPiezas);

		JMenuItem mnEstadsticas = new JMenuItem("Estad\u00EDsticas");
		mnGestinGlobal.add(mnEstadsticas);

		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);

		JMenuItem mnAcercaDe = new JMenuItem("Acerca de...");
		mnAyuda.add(mnAcercaDe);
	}

}
