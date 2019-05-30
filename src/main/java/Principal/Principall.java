package Principal;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import java.awt.CardLayout;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;

import Clases.Linea;
import Clases.Parada;
import Clases.Autobus;
import Clases.Billete;
import Clases.Cliente;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import java.awt.GridLayout;
import java.awt.Toolkit;


public class Principall extends JFrame {
	private int bus = 0;
	private int posicion = 0;
	private ImageIcon fondo = new ImageIcon("Imagenes\\orig_83357-2.jpg");
	private ImageIcon fondo_login = new ImageIcon("Imagenes\\fondo_login2.jpg");
	private ImageIcon icono_login = new ImageIcon("Imagenes\\1.png");
	private Calendar fechaActual = new GregorianCalendar();
	private JTextField DineroIntroducido;
	private JTextField DineroFaltante;
	private double total_introducido=0;
	private double total_faltante;
	private double total_devolucion=0;
	private double valorBoton=0;
	private int contador_billete200 = 0;
	private int contador_billete100 = 0;
	private int contador_billete50 = 0;
	private int contador_billete20 = 0;
	private int contador_billete10 = 0;
	private int contador_billete5 = 0;
	private int contador_moneda2e = 0;
	private int contador_moneda1e = 0;
	private int contador_moneda50cent = 0;
	private int contador_moneda20cent = 0;
	private int contador_moneda10cent = 0;
	private int contador_moneda5cent = 0;
	private int contador_moneda2cent = 0;
	private int contador_moneda1cent = 0;
	private int contador_monedas [] = {contador_billete200,contador_billete100,contador_billete50,contador_billete20,
			contador_billete10,contador_billete5,contador_moneda2e,contador_moneda1e,contador_moneda50cent,contador_moneda20cent,
			contador_moneda10cent,contador_moneda5cent,contador_moneda2cent,contador_moneda1cent};
	private String monedas[] = {"200 euros","100 euros","50 euros","20 euros","10 euros","5 euros", "2 euros", "1 euro", 
			"50 centimos", "20 centimos", "10 centimos", "5 centimos", "2 centimos", "1 centimo"};
	private JPanel Linea = new JPanel();
	private JButton Linea4 = new JButton();
	private JButton Linea3 = new JButton();
	private JButton Linea2 = new JButton();
	private JButton Linea1 = new JButton();
	private ArrayList <Parada> misParadas = new ArrayList <Parada>();
	private ArrayList <Autobus> misBuses = new ArrayList <Autobus>();
	private ArrayList <Billete> misBilletes = new ArrayList<Billete>();
	private boolean first=true;
	private String x="";
	private Billete billete = new Billete();
	private gestorBD gestor = new gestorBD();
	private Cliente cliente = new Cliente();
	private String cod_linea = "";
	private String fecha_ida = "";
	private String fecha_vuelta2 = "";
	private String parada_origen = "";
	private String parada_destino = "";
	private int paradas_libres = 0;
	private int index_inicio = 0;
	private int index_final = 0;
	private double precio = 0;
	private String DNI = "";
	private int cod_parada_origen = 0;
	private int cod_parada_destino = 0;
	
	
	private JPanel contentPane;
	private JTextField borrar_DNI;
	private JTextField borrar_fecha;
	private JTextField borrar_cod_billete;
	private JTextField modificar_DNI;
	private JTextField modificar_fecha_actual;
	private JTextField modificar_fecha_cambiar;
	private JTextField modificar_cod_billete;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principall frame = new Principall();
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
	public Principall() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\eclip\\Desktop\\Cristian\\bus2.png"));
		setLocationRelativeTo(null);
		setTitle("Autobus");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		JLabel Imagen_Bienvenida = new JLabel();
		

		JPanel Bienvenida = new JPanel();
		contentPane.add(Bienvenida, "name_20954981551620");
		Bienvenida.setLayout(null);
		
		JButton btnBienvenida = new JButton("Bienvenido");
		btnBienvenida.setFont(new Font("Lucida Grande", Font.BOLD, 50));
		
		
		btnBienvenida.setBounds(0, 0, 890, 568);
		Bienvenida.add(btnBienvenida);

		gestorBD gestor = new gestorBD();
	
		
		try
		{
			gestor.conectar();
			ArrayList<Linea> misLineas = new ArrayList<Linea>();
			misLineas = gestor.seleccionarLinea();
			
			
			for (int cont= 0; cont < misLineas.size(); cont++ )
			{
				
				switch (cont)
				{
					case 0: Linea1.setText(misLineas.get(0).toString());
					case 1: Linea2.setText(misLineas.get(1).toString());
					case 2: Linea3.setText(misLineas.get(2).toString());
					case 3: Linea4.setText(misLineas.get(3).toString());
				}
			}
			
			gestor.close();
		}
		catch (Exception e)
		{
			System.out.println("No ha sido posible conectar con la base de datos");
		}
		
		JPanel CompraOModificar = new JPanel();
		contentPane.add(CompraOModificar, "name_32489648938987");
		CompraOModificar.setLayout(null);
		
		JButton btnAdquirirBillete = new JButton("Adquirir billete");
		btnAdquirirBillete.setFont(new Font("Arial", Font.BOLD, 20));
		btnAdquirirBillete.setBounds(70, 87, 772, 91);
		CompraOModificar.add(btnAdquirirBillete);
		
		JButton btnModificarBillete = new JButton("Modificar billete");
		btnModificarBillete.setFont(new Font("Arial", Font.BOLD, 20));
		btnModificarBillete.setBounds(70, 238, 772, 91);
		CompraOModificar.add(btnModificarBillete);
		
		JButton btnBorrarBillete = new JButton("Borrar billete");
		btnBorrarBillete.setFont(new Font("Arial", Font.BOLD, 20));
		btnBorrarBillete.setBounds(70, 384, 772, 91);
		CompraOModificar.add(btnBorrarBillete);
		
		JLabel fondoComprarOModificar = new JLabel("");
		fondoComprarOModificar.setIcon(new ImageIcon("C:\\Workspace\\Reto_3_Papus_Windows\\Imagenes\\orig_83357-2.jpg"));
		fondoComprarOModificar.setBounds(0, 0, 890, 568);
		CompraOModificar.add(fondoComprarOModificar);
		
		JPanel ModificarBillete = new JPanel();
		contentPane.add(ModificarBillete, "name_33092353313690");
		ModificarBillete.setLayout(null);
		
		JLabel lblDni = new JLabel("Introduzca su DNI:");
		lblDni.setFont(new Font("Arial", Font.BOLD, 15));
		lblDni.setBounds(234, 126, 141, 20);
		ModificarBillete.add(lblDni);
		
		JLabel lblFechaActual = new JLabel("Introduzca la fecha de su billete:");
		lblFechaActual.setFont(new Font("Arial", Font.BOLD, 15));
		lblFechaActual.setBounds(133, 194, 251, 20);
		ModificarBillete.add(lblFechaActual);
		
		JLabel lblFechaACambiar = new JLabel("Introduzca la fecha a cambiar:");
		lblFechaACambiar.setFont(new Font("Arial", Font.BOLD, 15));
		lblFechaACambiar.setBounds(152, 271, 232, 20);
		ModificarBillete.add(lblFechaACambiar);
		
		JLabel lblNewLabel = new JLabel("Introduzca el codigo billete:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel.setBounds(160, 351, 215, 20);
		ModificarBillete.add(lblNewLabel);
		
		modificar_DNI = new JTextField();
		modificar_DNI.setBounds(416, 124, 232, 26);
		ModificarBillete.add(modificar_DNI);
		modificar_DNI.setColumns(10);
		
		modificar_fecha_actual = new JTextField();
		modificar_fecha_actual.setBounds(416, 192, 232, 26);
		ModificarBillete.add(modificar_fecha_actual);
		modificar_fecha_actual.setColumns(10);
		
		modificar_fecha_cambiar = new JTextField();
		modificar_fecha_cambiar.setColumns(10);
		modificar_fecha_cambiar.setBounds(416, 269, 232, 26);
		ModificarBillete.add(modificar_fecha_cambiar);
		
		modificar_cod_billete = new JTextField();
		modificar_cod_billete.setColumns(10);
		modificar_cod_billete.setBounds(416, 349, 232, 26);
		ModificarBillete.add(modificar_cod_billete);
		
		JButton Continuar_ModificarBillete = new JButton("Continuar");
		Continuar_ModificarBillete.setFont(new Font("Arial", Font.BOLD, 15));
		Continuar_ModificarBillete.setBounds(686, 514, 198, 48);
		ModificarBillete.add(Continuar_ModificarBillete);
		
		JButton Atras_ModificarBillete = new JButton("Atras");
		Atras_ModificarBillete.setFont(new Font("Arial", Font.BOLD, 15));
		Atras_ModificarBillete.setBounds(-14, 514, 198, 48);
		ModificarBillete.add(Atras_ModificarBillete);
		
		JLabel fondo_modificarbillete = new JLabel("");
		fondo_modificarbillete.setIcon(new ImageIcon("C:\\Workspace\\Reto_3_Papus_Windows\\Imagenes\\orig_83357-2.jpg"));
		fondo_modificarbillete.setBounds(0, 0, 874, 551);
		ModificarBillete.add(fondo_modificarbillete);
		
		JPanel BorrarBillete = new JPanel();
		contentPane.add(BorrarBillete, "name_33105505364762");
		BorrarBillete.setLayout(null);
		
		JLabel lblIntroduzcaSuDni = new JLabel("Introduzca su DNI:");
		lblIntroduzcaSuDni.setFont(new Font("Arial", Font.BOLD, 15));
		lblIntroduzcaSuDni.setBounds(217, 158, 146, 20);
		BorrarBillete.add(lblIntroduzcaSuDni);
		
		JLabel lblIntroduzcaLaFecha = new JLabel("Introduzca la fecha del billete:");
		lblIntroduzcaLaFecha.setFont(new Font("Arial", Font.BOLD, 15));
		lblIntroduzcaLaFecha.setBounds(135, 304, 238, 20);
		BorrarBillete.add(lblIntroduzcaLaFecha);
		
		borrar_DNI = new JTextField();
		borrar_DNI.setBounds(414, 156, 210, 26);
		BorrarBillete.add(borrar_DNI);
		borrar_DNI.setColumns(10);
		
		borrar_fecha = new JTextField();
		borrar_fecha.setBounds(415, 302, 210, 26);
		BorrarBillete.add(borrar_fecha);
		borrar_fecha.setColumns(10);
		
		JButton Atras_BorrarBillete = new JButton("Atras");
		Atras_BorrarBillete.setFont(new Font("Arial", Font.BOLD, 15));
		Atras_BorrarBillete.setBounds(-14, 514, 198, 48);
		BorrarBillete.add(Atras_BorrarBillete);
		
		JButton Continuar_BorrarBillete = new JButton("Continuar");
		Continuar_BorrarBillete.setFont(new Font("Arial", Font.BOLD, 15));
		Continuar_BorrarBillete.setBounds(686, 514, 198, 48);
		BorrarBillete.add(Continuar_BorrarBillete);
		
		JLabel lblIntroduzcaElCodigo = new JLabel("Introduzca el codigo del billete:");
		lblIntroduzcaElCodigo.setFont(new Font("Arial", Font.BOLD, 15));
		lblIntroduzcaElCodigo.setBounds(124, 231, 249, 20);
		BorrarBillete.add(lblIntroduzcaElCodigo);
		
		borrar_cod_billete = new JTextField();
		borrar_cod_billete.setBounds(414, 229, 210, 26);
		BorrarBillete.add(borrar_cod_billete);
		borrar_cod_billete.setColumns(10);
		
		JLabel fondo_borrarbillete = new JLabel("");
		fondo_borrarbillete.setIcon(new ImageIcon("C:\\Workspace\\Reto_3_Papus_Windows\\Imagenes\\orig_83357-2.jpg"));
		fondo_borrarbillete.setBounds(0, 0, 874, 551);
		BorrarBillete.add(fondo_borrarbillete);
		
		
		
		contentPane.add(Linea, "name_46692256077497");
		Linea.setLayout(null);
		
		Linea2.setBounds(17, 245, 855, 69);
		Linea.add(Linea2);
		
		Linea3.setBounds(17, 348, 855, 69);
		Linea.add(Linea3);
		
		Linea4.setBounds(17, 450, 855, 69);
		Linea.add(Linea4);

		
		Linea1.setBounds(17, 144, 855, 69);
		Linea.add(Linea1);
		
		JLabel lblElijaLinea = new JLabel("Elija linea");
		lblElijaLinea.setFont(new Font("Arial", Font.BOLD, 25));
		lblElijaLinea.setHorizontalAlignment(SwingConstants.CENTER);
		lblElijaLinea.setBounds(24, 22, 848, 63);
		Linea.add(lblElijaLinea);
		
		JRadioButton rdbtnIda = new JRadioButton("Ida");
	
		rdbtnIda.setFont(new Font("Arial", Font.BOLD, 15));
		rdbtnIda.setBounds(224, 97, 141, 23);
		Linea.add(rdbtnIda);
		
		JRadioButton rdbtnVuelta = new JRadioButton("Vuelta");
		rdbtnVuelta.setFont(new Font("Arial", Font.BOLD, 15));
		rdbtnVuelta.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnVuelta.setBounds(573, 97, 141, 23);
		Linea.add(rdbtnVuelta);
		

		JLabel fondoLinea = new JLabel("");
		fondoLinea.setBounds(0, 0, 890, 568);
		Linea.add(fondoLinea);
		fondoLinea.setIcon(new ImageIcon("C:\\Workspace\\Reto_3_Papus_Windows\\Imagenes\\orig_83357-2.jpg"));
		
		
		JPanel Origen_Destino = new JPanel();
		contentPane.add(Origen_Destino, "name_43304760932416");
		Origen_Destino.setLayout(null);
		
		JComboBox <String> Destino = new JComboBox();
		Destino.setBounds(547, 202, 244, 27);
		Origen_Destino.add(Destino);
		
		JLabel lblSeleccioneIdaO = new JLabel("Seleccione ida o ida y vuelta: ");
		lblSeleccioneIdaO.setFont(new Font("Arial", Font.BOLD, 20));
		lblSeleccioneIdaO.setBounds(304, 60, 281, 25);
		Origen_Destino.add(lblSeleccioneIdaO);
		
		JRadioButton rdbtnIda_1 = new JRadioButton("Ida");
		rdbtnIda_1.setFont(new Font("Arial", Font.BOLD, 15));
		rdbtnIda_1.setBounds(304, 97, 141, 23);
		Origen_Destino.add(rdbtnIda_1);
		
		JRadioButton rdbtnIdaYVuelta = new JRadioButton("Ida y Vuelta");
		rdbtnIdaYVuelta.setFont(new Font("Arial", Font.BOLD, 15));
		rdbtnIdaYVuelta.setBounds(469, 97, 111, 23);
		Origen_Destino.add(rdbtnIdaYVuelta);
		
		JButton btnBuscarPrecio = new JButton("Buscar precio");
		btnBuscarPrecio.setEnabled(false);
		btnBuscarPrecio.setFont(new Font("Arial", Font.BOLD, 15));
		btnBuscarPrecio.setBounds(333, 514, 198, 48);
		Origen_Destino.add(btnBuscarPrecio);
		
		JComboBox <String> Origen = new JComboBox();
		Origen.setBounds(134, 202, 244, 27);
		Origen_Destino.add(Origen);
		
		JLabel lblOrigen = new JLabel("Origen");
		lblOrigen.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrigen.setFont(new Font("Arial", Font.BOLD, 20));
		lblOrigen.setBounds(134, 157, 244, 33);
		Origen_Destino.add(lblOrigen);	
		
		JLabel lblDestino = new JLabel("Destino");
		lblDestino.setHorizontalAlignment(SwingConstants.CENTER);
		lblDestino.setFont(new Font("Arial", Font.BOLD, 20));
		lblDestino.setBounds(547, 157, 244, 33);
		Origen_Destino.add(lblDestino);
		

		JDateChooser fecha_origen = new JDateChooser();
		fecha_origen.setMinSelectableDate(fechaActual.getTime());
		fecha_origen.setEnabled(false);
		fecha_origen.setBounds(134, 360, 244, 27);
		Origen_Destino.add(fecha_origen);
		
		JDateChooser fecha_vuelta = new JDateChooser();
		fecha_vuelta.setMinSelectableDate(fechaActual.getTime());
		fecha_vuelta.setEnabled(false);
		fecha_vuelta.setBounds(547, 360, 244, 27);
		Origen_Destino.add(fecha_vuelta);
		
		JLabel Ida = new JLabel("Fecha de Origen");
		Ida.setHorizontalAlignment(SwingConstants.CENTER);
		Ida.setFont(new Font("Arial", Font.BOLD, 20));
		Ida.setBounds(134, 315, 244, 33);
		Origen_Destino.add(Ida);
		
		JLabel Vuelta = new JLabel("Fecha de Vuelta");
		Vuelta.setHorizontalAlignment(SwingConstants.CENTER);
		Vuelta.setFont(new Font("Arial", Font.BOLD, 20));
		Vuelta.setBounds(547, 315, 244, 33);
		Origen_Destino.add(Vuelta);
		
		JLabel fondoOrigen_Destino = new JLabel("");
		fondoOrigen_Destino.setBounds(0, 0, 890, 568);
		Origen_Destino.add(fondoOrigen_Destino);
		fondoOrigen_Destino.setIcon(new ImageIcon("C:\\Workspace\\Reto_3_Papus_Windows\\Imagenes\\orig_83357-2.jpg"));
		
		JPanel Disponibilidad = new JPanel();
		contentPane.add(Disponibilidad, "name_30833085592439");
		Disponibilidad.setLayout(null);
		
		JButton btnContinuar_Disponibilidad = new JButton("Continuar");
		btnContinuar_Disponibilidad.setFont(new Font("Arial", Font.BOLD, 15));
		btnContinuar_Disponibilidad.setBounds(333, 514, 198, 48);
		Disponibilidad.add(btnContinuar_Disponibilidad);
		
		JScrollPane scrollPane_Disponibilidad = new JScrollPane();
		scrollPane_Disponibilidad.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_Disponibilidad.setBounds(87, 59, 744, 311);
		Disponibilidad.add(scrollPane_Disponibilidad);
		
		JPanel panel_scrollPane = new JPanel();
		scrollPane_Disponibilidad.setViewportView(panel_scrollPane);
		panel_scrollPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel fondoDisponibilidad = new JLabel("");
		fondoDisponibilidad.setBounds(0, 0, 890, 568);
		Disponibilidad.add(fondoDisponibilidad);
		fondoDisponibilidad.setIcon(new ImageIcon("C:\\Workspace\\Reto_3_Papus_Windows\\Imagenes\\orig_83357-2.jpg"));

		JPanel iniciosesion = new JPanel();
		contentPane.add(iniciosesion, "name_174148474137532");
		iniciosesion.setLayout(null);
		
		JLabel lblDni_iniciosesion = new JLabel("DNI:");
		lblDni_iniciosesion.setForeground(Color.WHITE);
		lblDni_iniciosesion.setFont(new Font("Arial", Font.BOLD, 15));
		lblDni_iniciosesion.setBounds(262, 246, 35, 14);
		iniciosesion.add(lblDni_iniciosesion);
		
		JLabel lblContrasena_iniciosesion = new JLabel("CONTRASE\u00D1A:");
		lblContrasena_iniciosesion.setForeground(Color.WHITE);
		lblContrasena_iniciosesion.setFont(new Font("Arial", Font.BOLD, 15));
		lblContrasena_iniciosesion.setBounds(187, 336, 110, 14);
		iniciosesion.add(lblContrasena_iniciosesion);
		
		JTextField dni_iniciosesion;
		dni_iniciosesion = new JTextField();
		dni_iniciosesion.setFont(new Font("Arial", Font.BOLD, 15));
		dni_iniciosesion.setBounds(342, 245, 138, 20);
		iniciosesion.add(dni_iniciosesion);
		dni_iniciosesion.setColumns(10);
		
		JPasswordField contrasena_iniciosesion;
		contrasena_iniciosesion = new JPasswordField();
		contrasena_iniciosesion.setFont(new Font("Arial", Font.BOLD, 15));
		contrasena_iniciosesion.setBounds(342, 335, 138, 20);
		iniciosesion.add(contrasena_iniciosesion);
		
		JButton continuar_iniciosesion = new JButton("Continuar");
		continuar_iniciosesion.setForeground(Color.BLACK);
		continuar_iniciosesion.setFont(new Font("Arial", Font.BOLD, 15));
		continuar_iniciosesion.setBackground(Color.WHITE);
		continuar_iniciosesion.setBounds(686, 514, 198, 48);
		iniciosesion.add(continuar_iniciosesion);
		
		JButton cancelar_iniciosesion = new JButton("Cancelar");
		cancelar_iniciosesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				iniciosesion.setVisible(false);
				Bienvenida.setVisible(true);
			}
		});
		cancelar_iniciosesion.setBackground(Color.WHITE);
		cancelar_iniciosesion.setForeground(Color.BLACK);
		cancelar_iniciosesion.setFont(new Font("Arial", Font.BOLD, 15));
		cancelar_iniciosesion.setBounds(-14, 514, 198, 48);
		iniciosesion.add(cancelar_iniciosesion);
		
		JButton registrarse_iniciosesion = new JButton("Registrarse");
		
		registrarse_iniciosesion.setBackground(Color.WHITE);
		registrarse_iniciosesion.setForeground(Color.BLACK);
		registrarse_iniciosesion.setFont(new Font("Arial", Font.BOLD, 15));
		registrarse_iniciosesion.setBounds(333, 514, 198, 48);
		iniciosesion.add(registrarse_iniciosesion);
		
		JLabel lblLogin_iniciosesion = new JLabel("");
		lblLogin_iniciosesion.setIcon(new ImageIcon("C:\\Workspace\\Reto_3_Papus_Windows\\Imagenes\\1.png"));
		lblLogin_iniciosesion.setBounds(365, 11, 82, 95);
		iniciosesion.add(lblLogin_iniciosesion);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon("C:\\Workspace\\Reto_3_Papus_Windows\\Imagenes\\fondo_login2.jpg"));
		lblFondo.setBounds(0, 0, 874, 551);
		iniciosesion.add(lblFondo);
		
		
		JPanel registro = new JPanel();
		contentPane.add(registro, "name_174213527404823");
		registro.setLayout(null);
		
		JLabel lblRegistrarse_registro = new JLabel("New label");
		lblRegistrarse_registro.setIcon(new ImageIcon("C:\\Workspace\\Reto_3_Papus_Windows\\Imagenes\\1.png"));
		lblRegistrarse_registro.setBounds(365, 11, 86, 88);
		registro.add(lblRegistrarse_registro);
		
		JLabel lblDni_registro = new JLabel("DNI:");
		lblDni_registro.setForeground(Color.WHITE);
		lblDni_registro.setFont(new Font("Arial", Font.BOLD, 15));
		lblDni_registro.setBounds(280, 112, 43, 14);
		registro.add(lblDni_registro);
		
		JLabel lblContrasena_registro = new JLabel("CONTRASE\u00D1A:");
		lblContrasena_registro.setForeground(Color.WHITE);
		lblContrasena_registro.setFont(new Font("Arial", Font.BOLD, 15));
		lblContrasena_registro.setBounds(199, 380, 116, 14);
		registro.add(lblContrasena_registro);
		
		JLabel lblFechaNacimiento_registro = new JLabel("FECHA DE NACIMIENTO:");
		lblFechaNacimiento_registro.setForeground(Color.WHITE);
		lblFechaNacimiento_registro.setFont(new Font("Arial", Font.BOLD, 15));
		lblFechaNacimiento_registro.setBounds(132, 270, 190, 14);
		registro.add(lblFechaNacimiento_registro);
		
		JLabel lblSexo_registro = new JLabel("SEXO:");
		lblSexo_registro.setForeground(Color.WHITE);
		lblSexo_registro.setFont(new Font("Arial", Font.BOLD, 15));
		lblSexo_registro.setBounds(260, 327, 55, 14);
		registro.add(lblSexo_registro);
		
		JLabel lblApellido_registro = new JLabel("APELLIDO:");
		lblApellido_registro.setFont(new Font("Arial", Font.BOLD, 15));
		lblApellido_registro.setForeground(Color.WHITE);
		lblApellido_registro.setBounds(229, 212, 86, 14);
		registro.add(lblApellido_registro);
		
		JLabel lblNombre_registro = new JLabel("NOMBRE:");
		lblNombre_registro.setFont(new Font("Arial", Font.BOLD, 15));
		lblNombre_registro.setForeground(Color.WHITE);
		lblNombre_registro.setBounds(239, 165, 76, 14);
		registro.add(lblNombre_registro);
		
		JTextField nombre_registro;
		nombre_registro = new JTextField();
		nombre_registro.setFont(new Font("Arial", Font.PLAIN, 15));
		nombre_registro.setBounds(341, 164, 138, 20);
		registro.add(nombre_registro);
		nombre_registro.setColumns(10);
		
		JTextField apellido_registro;
		apellido_registro = new JTextField();
		apellido_registro.setFont(new Font("Arial", Font.PLAIN, 15));
		apellido_registro.setBounds(342, 211, 138, 20);
		registro.add(apellido_registro);
		apellido_registro.setColumns(10);
		
		
		JRadioButton rdbtnV = new JRadioButton("V");
		rdbtnV.setFont(new Font("Arial", Font.BOLD, 11));
		rdbtnV.setBounds(341, 324, 43, 23);
		registro.add(rdbtnV);

		
		JRadioButton rdbtnM = new JRadioButton("M");
		rdbtnM.setFont(new Font("Arial", Font.BOLD, 11));
		rdbtnM.setBounds(436, 324, 43, 23);
		registro.add(rdbtnM);
		
		
		JPasswordField contrasena_registro;
		contrasena_registro = new JPasswordField();
		contrasena_registro.setBounds(341, 379, 138, 20);
		registro.add(contrasena_registro);
		
		
		MaskFormatter mf = null;
		try {
			mf = new MaskFormatter("########?");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JFormattedTextField dni1_registro = new JFormattedTextField(mf);
		dni1_registro.setFont(new Font("Arial", Font.PLAIN, 15));
		dni1_registro.setBounds(341, 110, 138, 20);
		registro.add(dni1_registro);
		
		MaskFormatter mft = null;
		try {
			mft = new MaskFormatter("####-##-##");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JFormattedTextField fechanacimiento1_registro = new JFormattedTextField(mft);
		fechanacimiento1_registro.setFont(new Font("Arial", Font.PLAIN, 15));
		fechanacimiento1_registro.setBounds(341, 268, 138, 20);
		registro.add(fechanacimiento1_registro);
		
		JButton cancelar_registro = new JButton("Cancelar");
		cancelar_registro.setForeground(Color.BLACK);
		cancelar_registro.setBackground(Color.WHITE);
		cancelar_registro.setFont(new Font("Arial", Font.BOLD, 15));
		cancelar_registro.setBounds(-14, 514, 198, 48);
		registro.add(cancelar_registro);
		
		JButton enviar_registro = new JButton("Enviar");
		enviar_registro.setForeground(Color.BLACK);
		
		enviar_registro.setBackground(Color.WHITE);
		enviar_registro.setFont(new Font("Arial", Font.BOLD, 15));
		enviar_registro.setBounds(686, 514, 198, 48);
		registro.add(enviar_registro);
		
		JLabel lblFondo_registro = new JLabel("New label");
		lblFondo_registro.setIcon(new ImageIcon("C:\\Workspace\\Reto_3_Papus_Windows\\Imagenes\\fondo_login2.jpg"));
		lblFondo_registro.setBounds(0, 0, 874, 551);
		registro.add(lblFondo_registro);

		JPanel Pago = new JPanel();
		contentPane.add(Pago, "name_30846681032716");
		DineroFaltante = new JTextField();
		DineroFaltante.setHorizontalAlignment(SwingConstants.RIGHT);
		JButton billete200 = new JButton();
		JButton billete100 = new JButton();
		JButton billete50 = new JButton();
		JButton billete20 = new JButton();
		JButton billete10 = new JButton();
		JButton billete5 = new JButton();
		JButton moneda2e = new JButton();
		JButton moneda1e = new JButton();
		JButton moneda50cent = new JButton();
		JButton moneda20cent = new JButton();
		JButton moneda10cent = new JButton();
		JButton moneda5cent = new JButton();
		JButton moneda2cent = new JButton();
		JButton moneda1cent = new JButton();
		
		billete200.setActionCommand("200");
		billete100.setActionCommand("100");
		billete50.setActionCommand("50");
		billete20.setActionCommand("20");
		billete10.setActionCommand("10");
		billete5.setActionCommand("5");
		moneda2e.setActionCommand("2");
		moneda1e.setActionCommand("1");
		moneda50cent.setActionCommand("0.5");
		moneda20cent.setActionCommand("0.2");
		moneda10cent.setActionCommand("0.1");
		moneda5cent.setActionCommand("0.05");
		moneda2cent.setActionCommand("0.02");
		moneda1cent.setActionCommand("0.01");
		
		Pago.setLayout(null);
		
				
		JButton Cancelar_Pago = new JButton("Cancelar");
		Cancelar_Pago.setFont(new Font("Arial", Font.BOLD, 15));
		JButton Continuar_Pago = new JButton("Continuar");
		Continuar_Pago.setFont(new Font("Arial", Font.BOLD, 15));
		Continuar_Pago.setBounds(686, 514, 198, 48);
		Pago.add(Continuar_Pago);		
		
		

		JPanel Devolucion = new JPanel();
		contentPane.add(Devolucion, "name_2181821626333");
		Devolucion.setLayout(null);
		
		JTextField ImporteADevolver = new JTextField();
		ImporteADevolver.setHorizontalAlignment(SwingConstants.RIGHT);
		ImporteADevolver.setEditable(false);
		ImporteADevolver.setBounds(402, 31, 130, 26);
		Devolucion.add(ImporteADevolver);
		ImporteADevolver.setColumns(10);
		
		JLabel lblVueltas = new JLabel("Aqui tiene sus vueltas");
		lblVueltas.setFont(new Font("Arial", Font.BOLD, 11));
		lblVueltas.setBounds(375, 102, 139, 16);
		Devolucion.add(lblVueltas);
		
		JTextArea Devolucion_dinero = new JTextArea();
		Devolucion_dinero.setEditable(false);
		Devolucion_dinero.setBounds(306, 130, 280, 293);
		Devolucion.add(Devolucion_dinero);
		
		JLabel lblDineroADevolver = new JLabel("Dinero a devolver");
		lblDineroADevolver.setFont(new Font("Arial", Font.BOLD, 11));
		lblDineroADevolver.setBounds(271, 36, 117, 16);
		Devolucion.add(lblDineroADevolver);
		
		JButton Continuar_Devolucion = new JButton("Continuar");
		Continuar_Devolucion.setFont(new Font("Arial", Font.BOLD, 17));
		Continuar_Devolucion.setBounds(686, 514, 198, 48);
		Devolucion.add(Continuar_Devolucion);
		
		JLabel fondo_devolucion = new JLabel("");
		fondo_devolucion.setIcon(new ImageIcon("C:\\Workspace\\Reto_3_Papus_Windows\\Imagenes\\orig_83357-2.jpg"));
		fondo_devolucion.setBounds(0, 0, 874, 551);
		Devolucion.add(fondo_devolucion);
		
		JPanel Desea_Ticket = new JPanel();
		contentPane.add(Desea_Ticket, "name_606371250619227");
		Desea_Ticket.setLayout(null);
		
		JButton Si_Desea_Ticket = new JButton("Si");
		Si_Desea_Ticket.setFont(new Font("Arial", Font.BOLD, 18));
		Si_Desea_Ticket.setBounds(-14, 514, 198, 48);
		Desea_Ticket.add(Si_Desea_Ticket);
		
		JButton No_Desea_Ticket = new JButton("No");
		No_Desea_Ticket.setFont(new Font("Arial", Font.BOLD, 15));
		No_Desea_Ticket.setBounds(686, 514, 198, 48);
		Desea_Ticket.add(No_Desea_Ticket);
		
		JLabel lblDeseaUnTicket = new JLabel("Desea un ticket?");
		lblDeseaUnTicket.setFont(new Font("Arial", Font.PLAIN, 40));
		lblDeseaUnTicket.setBounds(251, 187, 301, 91);
		Desea_Ticket.add(lblDeseaUnTicket);
		
		JLabel lblCompraRealizadaCon = new JLabel("Compra realizada con \u00E9xito");
		lblCompraRealizadaCon.setFont(new Font("Arial", Font.PLAIN, 40));
		lblCompraRealizadaCon.setBounds(161, 92, 493, 67);
		Desea_Ticket.add(lblCompraRealizadaCon);
		
		JLabel lblFondo_Desea_Ticket = new JLabel("");
		lblFondo_Desea_Ticket.setIcon(new ImageIcon("C:\\Workspace\\Reto_3_Papus_Windows\\Imagenes\\orig_83357-2.jpg"));
		lblFondo_Desea_Ticket.setBounds(0, 0, 890, 568);
		Desea_Ticket.add(lblFondo_Desea_Ticket);
		
		
		
		JPanel Ticket = new JPanel();
		contentPane.add(Ticket, "name_608088504252386");
		Ticket.setLayout(null);
		
		JTextPane txtpnTicket = new JTextPane();
		txtpnTicket.setEditable(false);
		txtpnTicket.setFont(new Font("Arial", Font.BOLD, 14));
		txtpnTicket.setForeground(new Color(0, 0, 0));
		txtpnTicket.setBounds(250, 172, 322, 336);
		Ticket.add(txtpnTicket);
		
		 
		JLabel lblAquEstaTu = new JLabel("Aqu\u00ED esta tu Ticket");
		lblAquEstaTu.setFont(new Font("Arial", Font.BOLD, 40));
		lblAquEstaTu.setBounds(244, 28, 349, 68);
		Ticket.add(lblAquEstaTu);
		
		JLabel lblFondo_Ticket = new JLabel("New label");
		lblFondo_Ticket.setIcon(new ImageIcon("C:\\Workspace\\Reto_3_Papus_Windows\\Imagenes\\orig_83357-2.jpg"));
		lblFondo_Ticket.setBounds(0, 0, 874, 551);
		Ticket.add(lblFondo_Ticket);
				
				
				
				
				
				
				
				
				
				btnBienvenida.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{	//Al presionar el boton el panel de "Bienvenida" deja de estar visible, y "ListaProductos" se hace visible
						Bienvenida.setVisible(false);
						CompraOModificar.setVisible(true);
							
					}
				});
				
				btnAdquirirBillete.addActionListener(new ActionListener()
				{
					public void actionPerformed (ActionEvent e)
					{
						CompraOModificar.setVisible(false);
						Linea.setVisible(true);
					}
				});
				
				btnModificarBillete.addActionListener(new ActionListener() 
				{
					public void actionPerformed (ActionEvent e)
					{
						CompraOModificar.setVisible(false);
						ModificarBillete.setVisible(true);
						modificar_DNI.setText("");
						modificar_cod_billete.setText("");
						modificar_fecha_actual.setText("");
						modificar_fecha_cambiar.setText("");
						
						try
						{
							gestor.conectar();
							misBilletes = gestor.seleccionarBillete();
							gestor.close();
						}
						
						catch (Exception ex)
						{
							
						}
					}
				});
				
				btnBorrarBillete.addActionListener(new ActionListener()
				{
					public void actionPerformed (ActionEvent e)
					{	
						CompraOModificar.setVisible(false);
						BorrarBillete.setVisible(true);
						borrar_DNI.setText("");
						borrar_cod_billete.setText("");
						borrar_fecha.setText("");
						
						try
						{
							gestor.conectar();
							misBilletes = gestor.seleccionarBillete();
							gestor.close();
						}
						
						catch (Exception ex)
						{
							
						}
					}
				});
				
				
				Continuar_BorrarBillete.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e)
					{
						if(borrar_DNI.getText().length() == 0 || borrar_cod_billete.getText().length() == 0 || borrar_fecha.getText().length() == 0)
						{
							JOptionPane.showConfirmDialog(contentPane, "Existen campos vacios, rellenelos antes de continuar"
									,"Aviso", JOptionPane.PLAIN_MESSAGE);
						}
						else
						{
							int respuesta = JOptionPane.showConfirmDialog(null, "Esta seguro de borrar su billete?", "Alerta!", JOptionPane.YES_NO_OPTION);
							if (respuesta == 0)
							{
								try
								{
									gestor.conectar();
									int dato = gestor.billete_borrado_modificado(borrar_DNI.getText(), borrar_cod_billete.getText(), borrar_fecha.getText());
									
									if(dato == 1)
									{
										gestor.borrar_billete(borrar_DNI.getText(), Integer.parseInt(borrar_cod_billete.getText()), borrar_fecha.getText());
										JOptionPane.showConfirmDialog(contentPane, "Borrado con exito"
												,"Aviso", JOptionPane.PLAIN_MESSAGE);
									}
									
									else
									{
											JOptionPane.showConfirmDialog(contentPane, "Datos no validos. Intentelo de nuevo."
													,"Aviso", JOptionPane.PLAIN_MESSAGE);
									}
									gestor.close();
								}
								catch (Exception ex)
								{
											
								}
							}
									BorrarBillete.setVisible(false);
									Bienvenida.setVisible(true);
						}
					}
				});
				
				Atras_BorrarBillete.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e)
					{
						BorrarBillete.setVisible(false);
						CompraOModificar.setVisible(true);
					}
				});

				
				Continuar_ModificarBillete.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(modificar_DNI.getText().length() == 0 || modificar_cod_billete.getText().length() == 0 
								|| modificar_fecha_actual.getText().length() == 0 || modificar_fecha_cambiar.getText().length() == 0)
						{
							JOptionPane.showConfirmDialog(contentPane, "Existen campos vacios, rellenelos antes de continuar"
									,"Aviso", JOptionPane.PLAIN_MESSAGE);
						}
						else
						{
							int respuesta = JOptionPane.showConfirmDialog(null, "Esta seguro de modificar su billete?", "Alerta!", JOptionPane.YES_NO_OPTION);
							if (respuesta == 0)
							{
								try
								{
									gestor.conectar();
									int dato = gestor.billete_borrado_modificado(modificar_DNI.getText(), modificar_cod_billete.getText(), modificar_fecha_actual.getText());
									
									if(dato == 1)
									{
										gestor.modificar_billete(modificar_DNI.getText(), modificar_fecha_actual.getText(), 
												modificar_fecha_cambiar.getText(), Integer.parseInt(modificar_cod_billete.getText()));
										JOptionPane.showConfirmDialog(contentPane, "Modificado con exito"
												,"Aviso", JOptionPane.PLAIN_MESSAGE);
									}
									
									else
									{
										JOptionPane.showConfirmDialog(contentPane, "Datos no validos. Intentelo de nuevo."
													,"Aviso", JOptionPane.PLAIN_MESSAGE);
									}
									gestor.close();
								}
								catch (Exception ex)
								{
									
								}
							}
									ModificarBillete.setVisible(false);
									Bienvenida.setVisible(true);
						}
					}
				});
				
				
				Atras_ModificarBillete.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e)
					{
						ModificarBillete.setVisible(false);
						CompraOModificar.setVisible(true);
					}
				});
				
				Linea1.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						try 
						{
							if (!rdbtnVuelta.isSelected() && !rdbtnIda.isSelected())
							{
								JOptionPane.showConfirmDialog(contentPane, "Seleccione Ida o Vuelta por favor"
										,"Aviso", JOptionPane.PLAIN_MESSAGE);
								return;
							}
							gestor.conectar();
							misParadas = gestor.seleccionarParada(Linea1.getActionCommand());
							misParadas = Metodos.ordenarParadas(misParadas);
							misBuses = gestor.seleccionarAutobus(Linea1.getActionCommand());
							Metodos.colocacionComboBox(rdbtnIda, misParadas, Origen, Destino,-1);
							gestor.close();
						}
						catch (Exception e1)
						{
							
						}
						cod_linea = Linea1.getActionCommand();
					}
				});
				
				Linea2.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						try 
						{
							if (!rdbtnVuelta.isSelected() && !rdbtnIda.isSelected())
							{
								JOptionPane.showConfirmDialog(contentPane, "Seleccione Ida o Vuelta por favor"
										,"Aviso", JOptionPane.PLAIN_MESSAGE);
								return;
							}
							gestor.conectar();
							misParadas = gestor.seleccionarParada(Linea2.getActionCommand());
							misParadas = Metodos.ordenarParadas(misParadas);	
							misBuses = gestor.seleccionarAutobus(Linea2.getActionCommand());
							Metodos.colocacionComboBox(rdbtnIda, misParadas, Origen, Destino,-1);
							gestor.close();
						}
						catch (Exception e1)
						{
							
						}
						cod_linea = Linea2.getActionCommand();
					}
				});
				
				Linea3.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						try 
						{
							if (!rdbtnVuelta.isSelected() && !rdbtnIda.isSelected())
							{
								JOptionPane.showConfirmDialog(contentPane, "Seleccione Ida o Vuelta por favor"
										,"Aviso", JOptionPane.PLAIN_MESSAGE);
								return;
							}
							gestor.conectar();
							misParadas = gestor.seleccionarParada(Linea3.getActionCommand());
							misParadas = Metodos.ordenarParadas(misParadas);	
							misBuses = gestor.seleccionarAutobus(Linea3.getActionCommand());
							Metodos.colocacionComboBox(rdbtnIda, misParadas, Origen, Destino,-1);
							gestor.close();
						}
						catch (Exception e1)
						{
							
						}
						cod_linea = Linea3.getActionCommand();
					}
					
				});
				
				Linea4.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						try 
						{
							if (!rdbtnVuelta.isSelected() && !rdbtnIda.isSelected())
							{
								JOptionPane.showConfirmDialog(contentPane, "Seleccione Ida o Vuelta por favor"
										,"Aviso", JOptionPane.PLAIN_MESSAGE);
								return;
							}
							gestor.conectar();
							misParadas = gestor.seleccionarParada(Linea4.getActionCommand());
							misParadas = Metodos.ordenarParadas(misParadas);
							misBuses = gestor.seleccionarAutobus(Linea4.getActionCommand());
							Metodos.colocacionComboBox(rdbtnIda, misParadas, Origen, Destino,-1);
							gestor.close();
						}
						catch (Exception e1)
						{
							
						}
						cod_linea = Linea4.getActionCommand();
					}
				});
				
				
				ActionListener lineas = new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						if (rdbtnIda.isSelected() || rdbtnVuelta.isSelected())
						{
							Linea.setVisible(false);
							Origen_Destino.setVisible(true);
						}
					}
				};
				
				Linea1.addActionListener(lineas);
				Linea2.addActionListener(lineas);
				Linea3.addActionListener(lineas);
				Linea4.addActionListener(lineas);
				Linea1.setActionCommand("L1");
				Linea2.setActionCommand("L2");
				Linea3.setActionCommand("L3");
				Linea4.setActionCommand("L4");
				
				
						Cancelar_Pago.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								Pago.setVisible(false);
								Bienvenida.setVisible(true);
								
								total_devolucion = 0;
								total_faltante = 0;
								total_introducido = 0;
								DineroFaltante.setText("");
								DineroIntroducido.setText("");

								
								billete200.setEnabled(true);
								billete100.setEnabled(true);
								billete50.setEnabled(true);
								billete20.setEnabled(true);
								billete10.setEnabled(true);
								billete5.setEnabled(true);
								moneda2e.setEnabled(true);
								moneda1e.setEnabled(true);
								moneda50cent.setEnabled(true);
								moneda20cent.setEnabled(true);
								moneda10cent.setEnabled(true);
								moneda5cent.setEnabled(true);
								moneda2cent.setEnabled(true);
								moneda1cent.setEnabled(true);
							}
						});
						
						Cancelar_Pago.setBounds(-14, 514, 198, 48);
						Pago.add(Cancelar_Pago);
						

						billete200.addActionListener(new ActionListener() 
						{
							public void actionPerformed(ActionEvent e) 
							{
								valorBoton = Double.parseDouble(billete200.getActionCommand());
								total_introducido=total_introducido+valorBoton;
								total_faltante = total_faltante-valorBoton;
								DineroFaltante.setText(String.format("%.2f",total_faltante));
								DineroIntroducido.setText(String.format("%.2f", total_introducido));
								
								if (total_faltante <= 0.01)
								{
									Metodos.DeshabilitarMonedas(Continuar_Pago, billete200, billete100, billete50, billete20, billete10, billete5, moneda2e,
											moneda1e, moneda50cent, moneda20cent, moneda10cent, moneda5cent, moneda2cent, moneda1cent, DineroFaltante);
								}
								if (total_introducido > total_faltante)
								{
									total_devolucion = total_introducido - precio;
									BigDecimal decimal = new BigDecimal(total_devolucion);
									total_devolucion = decimal.setScale(2, RoundingMode.HALF_UP).doubleValue();
								}
							}
						});
						
						billete200.setIcon(new ImageIcon("C:\\Workspace\\Reto_3_Papus_Windows\\Imagenes\\200-euro-note-104181.png"));
						billete200.setBounds(39, 78, 169, 97);
						Pago.add(billete200);
						
						
						billete100.addActionListener(new ActionListener() 
							{
								public void actionPerformed(ActionEvent e) 
								{
									valorBoton = Double.parseDouble(billete100.getActionCommand());
									total_introducido=total_introducido+valorBoton;
									total_faltante = total_faltante-valorBoton;
									DineroFaltante.setText(String.format("%.2f",total_faltante));
									DineroIntroducido.setText(String.format("%.2f", total_introducido));
									
									if (total_faltante <= 0.01)
									{
										Metodos.DeshabilitarMonedas(Continuar_Pago, billete200, billete100, billete50, billete20, billete10, billete5, moneda2e,
												moneda1e, moneda50cent, moneda20cent, moneda10cent, moneda5cent, moneda2cent, moneda1cent, DineroFaltante);
									}
									if (total_faltante < 0)
									{
										total_devolucion = total_introducido - precio;
									}
								}
							});
						
						billete100.setIcon(new ImageIcon("C:\\Workspace\\Reto_3_Papus_Windows\\Imagenes\\dollar_bill__100_euro__money__banknote.jpg"));
						billete100.setBounds(257, 78, 169, 97);
						Pago.add(billete100);
						
						
						billete50.addActionListener(new ActionListener() 
						{
							public void actionPerformed(ActionEvent e) 
							{
								valorBoton = Double.parseDouble(billete50.getActionCommand());
								total_introducido=total_introducido+valorBoton;
								total_faltante = total_faltante-valorBoton;
								DineroFaltante.setText(String.format("%.2f",total_faltante));
								DineroIntroducido.setText(String.format("%.2f", total_introducido));
								
								if (total_faltante <= 0.01)
								{
									Metodos.DeshabilitarMonedas(Continuar_Pago, billete200, billete100, billete50, billete20, billete10, billete5, moneda2e,
											moneda1e, moneda50cent, moneda20cent, moneda10cent, moneda5cent, moneda2cent, moneda1cent, DineroFaltante);
								}
								if (total_introducido > total_faltante)
								{
									total_devolucion = total_introducido - precio;
									BigDecimal decimal = new BigDecimal(total_devolucion);
									total_devolucion = decimal.setScale(2, RoundingMode.HALF_UP).doubleValue();
								}
							}
						});
						
						billete50.setIcon(new ImageIcon("C:\\Workspace\\Reto_3_Papus_Windows\\Imagenes\\50EuroSchein.jpg"));
						billete50.setBounds(470, 78, 169, 97);
						Pago.add(billete50);
						
						
						billete20.addActionListener(new ActionListener() 
						{
							public void actionPerformed(ActionEvent e) 
							{
								valorBoton = Double.parseDouble(billete20.getActionCommand());
								total_introducido=total_introducido+valorBoton;
								total_faltante = total_faltante-valorBoton;
								DineroFaltante.setText(String.format("%.2f",total_faltante));
								DineroIntroducido.setText(String.format("%.2f", total_introducido));
								
								if (total_faltante <= 0.01)
								{
									Metodos.DeshabilitarMonedas(Continuar_Pago, billete200, billete100, billete50, billete20, billete10, billete5, moneda2e,
											moneda1e, moneda50cent, moneda20cent, moneda10cent, moneda5cent, moneda2cent, moneda1cent, DineroFaltante);
								}
								if (total_introducido > total_faltante)
								{
									total_devolucion = total_introducido - precio;
									BigDecimal decimal = new BigDecimal(total_devolucion);
									total_devolucion = decimal.setScale(2, RoundingMode.HALF_UP).doubleValue();
								}
							}
						});
						
						billete20.setIcon(new ImageIcon("C:\\Workspace\\Reto_3_Papus_Windows\\Imagenes\\2018-01-15-20-evrov-bolezen-in-pravica-82152.jpg"));
						billete20.setBounds(39, 187, 169, 97);
						Pago.add(billete20);
						
						
						billete10.addActionListener(new ActionListener() 
						{
							public void actionPerformed(ActionEvent e) 
							{
								valorBoton = Double.parseDouble(billete10.getActionCommand());
								total_introducido=total_introducido+valorBoton;
								total_faltante = total_faltante-valorBoton;
								DineroFaltante.setText(String.format("%.2f",total_faltante));
								DineroIntroducido.setText(String.format("%.2f", total_introducido));
								
								if (total_faltante <= 0.01)
								{
									Metodos.DeshabilitarMonedas(Continuar_Pago, billete200, billete100, billete50, billete20, billete10, billete5, moneda2e,
											moneda1e, moneda50cent, moneda20cent, moneda10cent, moneda5cent, moneda2cent, moneda1cent, DineroFaltante);
								}
								if (total_introducido > total_faltante)
								{
									total_devolucion = total_introducido - precio;
									BigDecimal decimal = new BigDecimal(total_devolucion);
									total_devolucion = decimal.setScale(2, RoundingMode.HALF_UP).doubleValue();
								}
							}
						});
						
						billete10.setIcon(new ImageIcon("C:\\Workspace\\Reto_3_Papus_Windows\\Imagenes\\10-euro-640x334.jpg"));
						billete10.setBounds(257, 187, 169, 97);
						Pago.add(billete10);
						
						
						billete5.addActionListener(new ActionListener() 
						{
							public void actionPerformed(ActionEvent e) 
							{
								valorBoton = Double.parseDouble(billete5.getActionCommand());
								total_introducido=total_introducido+valorBoton;
								total_faltante = total_faltante-valorBoton;
								DineroFaltante.setText(String.format("%.2f",total_faltante));
								DineroIntroducido.setText(String.format("%.2f", total_introducido));
								
								if (total_faltante <= 0.01)
								{
									Metodos.DeshabilitarMonedas(Continuar_Pago, billete200, billete100, billete50, billete20, billete10, billete5, moneda2e,
											moneda1e, moneda50cent, moneda20cent, moneda10cent, moneda5cent, moneda2cent, moneda1cent, DineroFaltante);
								}
								if (total_introducido > total_faltante)
								{
									total_devolucion = total_introducido - precio;
									BigDecimal decimal = new BigDecimal(total_devolucion);
									total_devolucion = decimal.setScale(2, RoundingMode.HALF_UP).doubleValue();
								}
							}
						});
						
						billete5.setIcon(new ImageIcon("C:\\Workspace\\Reto_3_Papus_Windows\\Imagenes\\Captura de pantalla 2018-11-20 a las 2.30.56.png"));
						billete5.setBounds(470, 187, 169, 97);
						Pago.add(billete5);
						
						
						
						moneda2e.addActionListener(new ActionListener() 
						{
							public void actionPerformed(ActionEvent e) 
							{
								valorBoton = Double.parseDouble(moneda2e.getActionCommand());
								total_introducido=total_introducido+valorBoton;
								total_faltante = total_faltante-valorBoton;
								DineroFaltante.setText(String.format("%.2f",total_faltante));
								DineroIntroducido.setText(String.format("%.2f", total_introducido));
								
								if (total_faltante <= 0.01)
								{
									Metodos.DeshabilitarMonedas(Continuar_Pago, billete200, billete100, billete50, billete20, billete10, billete5, moneda2e,
											moneda1e, moneda50cent, moneda20cent, moneda10cent, moneda5cent, moneda2cent, moneda1cent, DineroFaltante);
								}
								if (total_introducido > total_faltante)
								{
									total_devolucion = total_introducido - precio;
									BigDecimal decimal = new BigDecimal(total_devolucion);
									total_devolucion = decimal.setScale(2, RoundingMode.HALF_UP).doubleValue();
								}
							}
						});
						
						moneda2e.setForeground(new Color(0, 0, 0));
						moneda2e.setIcon(new ImageIcon("C:\\Workspace\\Reto_3_Papus_Windows\\Imagenes\\2euro_nt.gif"));
						moneda2e.setBounds(39, 296, 101, 97);
						Pago.add(moneda2e);
						
						
						moneda1e.addActionListener(new ActionListener() 
						{
							public void actionPerformed(ActionEvent e) 
							{
								valorBoton = Double.parseDouble(moneda1e.getActionCommand());
								total_introducido=total_introducido+valorBoton;
								total_faltante = total_faltante-valorBoton;
								DineroFaltante.setText(String.format("%.2f",total_faltante));
								DineroIntroducido.setText(String.format("%.2f", total_introducido));
								
								if (total_faltante <= 0.01)
								{
									Metodos.DeshabilitarMonedas(Continuar_Pago, billete200, billete100, billete50, billete20, billete10, billete5, moneda2e,
											moneda1e, moneda50cent, moneda20cent, moneda10cent, moneda5cent, moneda2cent, moneda1cent, DineroFaltante);
								}
								if (total_introducido > total_faltante)
								{
									total_devolucion = total_introducido - precio;
									BigDecimal decimal = new BigDecimal(total_devolucion);
									total_devolucion = decimal.setScale(2, RoundingMode.HALF_UP).doubleValue();
								}
							}
						});
						
						moneda1e.setIcon(new ImageIcon("C:\\Workspace\\Reto_3_Papus_Windows\\Imagenes\\kisspng-euro-coins-europe-money-1-euro-coin-eur-5ada73a274ee58.337454961524265890479.png"));
						moneda1e.setBounds(207, 296, 101, 97);
						Pago.add(moneda1e);
						
						
						moneda50cent.addActionListener(new ActionListener() 
						{
							public void actionPerformed(ActionEvent e) 
							{
								valorBoton = Double.parseDouble(moneda50cent.getActionCommand());
								total_introducido=total_introducido+valorBoton;
								total_faltante = total_faltante-valorBoton;
								DineroFaltante.setText(String.format("%.2f",total_faltante));
								DineroIntroducido.setText(String.format("%.2f", total_introducido));
								
								if (total_faltante <= 0.01)
								{
									Metodos.DeshabilitarMonedas(Continuar_Pago, billete200, billete100, billete50, billete20, billete10, billete5, moneda2e,
											moneda1e, moneda50cent, moneda20cent, moneda10cent, moneda5cent, moneda2cent, moneda1cent, DineroFaltante);
								}
								if (total_introducido > total_faltante)
								{
									total_devolucion = total_introducido - precio;
									BigDecimal decimal = new BigDecimal(total_devolucion);
									total_devolucion = decimal.setScale(2, RoundingMode.HALF_UP).doubleValue();
								}
							}
						});
						
						moneda50cent.setIcon(new ImageIcon("C:\\Workspace\\Reto_3_Papus_Windows\\Imagenes\\50centimos_nt.gif"));
						moneda50cent.setBounds(370, 296, 101, 97);
						Pago.add(moneda50cent);
						
						
						moneda20cent.addActionListener(new ActionListener() 
						{
							public void actionPerformed(ActionEvent e) 
							{
								valorBoton = Double.parseDouble(moneda20cent.getActionCommand());
								total_introducido=total_introducido+valorBoton;
								total_faltante = total_faltante-valorBoton;
								DineroFaltante.setText(String.format("%.2f",total_faltante));
								DineroIntroducido.setText(String.format("%.2f", total_introducido));
								
								if (total_faltante <= 0.01)
								{
									Metodos.DeshabilitarMonedas(Continuar_Pago, billete200, billete100, billete50, billete20, billete10, billete5, moneda2e,
											moneda1e, moneda50cent, moneda20cent, moneda10cent, moneda5cent, moneda2cent, moneda1cent, DineroFaltante);
								}
								if (total_introducido > total_faltante)
								{
									total_devolucion = total_introducido - precio;
									BigDecimal decimal = new BigDecimal(total_devolucion);
									total_devolucion = decimal.setScale(2, RoundingMode.HALF_UP).doubleValue();
								}
							}
						});
						
						moneda20cent.setIcon(new ImageIcon("C:\\Workspace\\Reto_3_Papus_Windows\\Imagenes\\kisspng-20-cent-euro-coin-1-cent-euro-coin-euro-coins-portugal-clipart-5adbafbfd29330.4470813415243468158625.png"));
						moneda20cent.setBounds(528, 296, 101, 97);
						Pago.add(moneda20cent);
						
						
						moneda10cent.addActionListener(new ActionListener() 
						{
							public void actionPerformed(ActionEvent e) 
							{
								valorBoton = Double.parseDouble(moneda10cent.getActionCommand());
								total_introducido=total_introducido+valorBoton;
								total_faltante = total_faltante-valorBoton;
								DineroFaltante.setText(String.format("%.2f",total_faltante));
								DineroIntroducido.setText(String.format("%.2f", total_introducido));
							
								if (total_faltante <= 0.01)
								{
									Metodos.DeshabilitarMonedas(Continuar_Pago, billete200, billete100, billete50, billete20, billete10, billete5, moneda2e,
											moneda1e, moneda50cent, moneda20cent, moneda10cent, moneda5cent, moneda2cent, moneda1cent, DineroFaltante);
								}
								if (total_introducido > total_faltante)
								{
									total_devolucion = total_introducido - precio;
									BigDecimal decimal = new BigDecimal(total_devolucion);
									total_devolucion = decimal.setScale(2, RoundingMode.HALF_UP).doubleValue();
								}
							}
						});
						
						moneda10cent.setIcon(new ImageIcon("C:\\Workspace\\Reto_3_Papus_Windows\\Imagenes\\10centimos_nt.gif"));
						moneda10cent.setBounds(39, 405, 101, 97);
						Pago.add(moneda10cent);
						
						
						moneda5cent.addActionListener(new ActionListener() 
						{
							public void actionPerformed(ActionEvent e) 
							{
								valorBoton = Double.parseDouble(moneda5cent.getActionCommand());
								total_introducido=total_introducido+valorBoton;
								total_faltante = total_faltante-valorBoton;
								DineroFaltante.setText(String.format("%.2f",total_faltante));
								DineroIntroducido.setText(String.format("%.2f", total_introducido));
								
								if (total_faltante <= 0.01)
								{
									Metodos.DeshabilitarMonedas(Continuar_Pago, billete200, billete100, billete50, billete20, billete10, billete5, moneda2e,
											moneda1e, moneda50cent, moneda20cent, moneda10cent, moneda5cent, moneda2cent, moneda1cent, DineroFaltante);
								}
								if (total_introducido > total_faltante)
								{
									total_devolucion = total_introducido - precio;
									BigDecimal decimal = new BigDecimal(total_devolucion);
									total_devolucion = decimal.setScale(2, RoundingMode.HALF_UP).doubleValue();
								}
							}
						});
						
						moneda5cent.setIcon(new ImageIcon("C:\\Workspace\\Reto_3_Papus_Windows\\Imagenes\\5centimos_nt.gif"));
						moneda5cent.setBounds(207, 405, 101, 97);
						Pago.add(moneda5cent);
						
						
						moneda2cent.addActionListener(new ActionListener() 
						{
							public void actionPerformed(ActionEvent e) 
							{
								valorBoton = Double.parseDouble(moneda2cent.getActionCommand());
								total_introducido=total_introducido+valorBoton;
								total_faltante = total_faltante-valorBoton;
								DineroFaltante.setText(String.format("%.2f",total_faltante));
								DineroIntroducido.setText(String.format("%.2f", total_introducido));
							
								if (total_faltante <= 0.01)
								{
									Metodos.DeshabilitarMonedas(Continuar_Pago, billete200, billete100, billete50, billete20, billete10, billete5, moneda2e,
											moneda1e, moneda50cent, moneda20cent, moneda10cent, moneda5cent, moneda2cent, moneda1cent, DineroFaltante);
								}
								if (total_introducido > total_faltante)
								{
									total_devolucion = total_introducido - precio;
									BigDecimal decimal = new BigDecimal(total_devolucion);
									total_devolucion = decimal.setScale(2, RoundingMode.HALF_UP).doubleValue();
								}
							}
						});
						
						moneda2cent.setIcon(new ImageIcon("C:\\Workspace\\Reto_3_Papus_Windows\\Imagenes\\2_Centesimi_Italia_2015_Euro_Cent_Fdc_Unc_Romacoins_m.png"));
						moneda2cent.setBounds(370, 405, 101, 97);
						Pago.add(moneda2cent);
						
						
						moneda1cent.addActionListener(new ActionListener() 
						{
							public void actionPerformed(ActionEvent e) 
							{
								valorBoton = Double.parseDouble(moneda1cent.getActionCommand());
								total_introducido=total_introducido+valorBoton;
								total_faltante = total_faltante-valorBoton;
								DineroFaltante.setText(String.format("%.2f",total_faltante));
								DineroIntroducido.setText(String.format("%.2f", total_introducido));
								
								if (total_faltante <= 0.01)
								{
									Metodos.DeshabilitarMonedas(Continuar_Pago, billete200, billete100, billete50, billete20, billete10, billete5, moneda2e,
											moneda1e, moneda50cent, moneda20cent, moneda10cent, moneda5cent, moneda2cent, moneda1cent, DineroFaltante);
								}
								if (total_introducido > total_faltante)
								{
									total_devolucion = total_introducido - precio;
									BigDecimal decimal = new BigDecimal(total_devolucion);
									total_devolucion = decimal.setScale(2, RoundingMode.HALF_UP).doubleValue();
								}
							}
						});
						
						moneda1cent.setIcon(new ImageIcon("C:\\Workspace\\Reto_3_Papus_Windows\\Imagenes\\1_Centesimo_Italia_2014_Euro_Fdc_Unc_Romacoins_m.png"));
						moneda1cent.setBounds(528, 405, 101, 97);
						Pago.add(moneda1cent);
						
						DineroIntroducido = new JTextField();
						DineroIntroducido.setHorizontalAlignment(SwingConstants.RIGHT);
						DineroIntroducido.setEditable(false);
						DineroIntroducido.setBounds(783, 442, 101, 26);
						Pago.add(DineroIntroducido);
						DineroIntroducido.setColumns(10);
						
						JLabel lblDineroIntroducido = new JLabel("Dinero Introducido");
						lblDineroIntroducido.setFont(new Font("Arial", Font.BOLD, 13));
						lblDineroIntroducido.setBounds(652, 447, 119, 16);
						Pago.add(lblDineroIntroducido);
						
						
						DineroFaltante.setEditable(false);
						DineroFaltante.setColumns(10);
						DineroFaltante.setBounds(783, 405, 101, 26);
						Pago.add(DineroFaltante);
						
						JLabel lblDineroFaltante = new JLabel("Dinero A Pagar");
						lblDineroFaltante.setFont(new Font("Arial", Font.BOLD, 13));
						lblDineroFaltante.setBounds(652, 410, 119, 16);
						Pago.add(lblDineroFaltante);
						
						JLabel fondoPago = new JLabel("");
						fondoPago.setBounds(0, 0, 890, 568);
						Pago.add(fondoPago);
						fondoPago.setIcon(new ImageIcon("C:\\Workspace\\Reto_3_Papus_Windows\\Imagenes\\orig_83357-2.jpg"));
				
						
				fecha_origen.setCalendar(fechaActual);
				
						
				rdbtnIda_1.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if (rdbtnIdaYVuelta.isSelected())
						{
							rdbtnIdaYVuelta.setSelected(false);
							rdbtnIda_1.setSelected(true);
						}	
						
						if (rdbtnIda_1.isSelected())
						{
							fecha_origen.setEnabled(true);
							fecha_vuelta.setEnabled(false);
							btnBuscarPrecio.setEnabled(true);
						}
						else
						{
							fecha_origen.setEnabled(false);
							fecha_vuelta.setEnabled(false);
							btnBuscarPrecio.setEnabled(false);
						}
					}
				}
				);
				
				rdbtnIdaYVuelta.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						fecha_vuelta.setCalendar(fechaActual);
						if (rdbtnIda_1.isSelected())
						{
							rdbtnIda_1.setSelected(false);
							rdbtnIdaYVuelta.setSelected(true);
						}		
						if (rdbtnIdaYVuelta.isSelected())
						{
							fecha_origen.setEnabled(true);
							fecha_vuelta.setEnabled(true);
							btnBuscarPrecio.setEnabled(true);
						}
						else
						{
							fecha_origen.setEnabled(false);
							fecha_vuelta.setEnabled(false);
							btnBuscarPrecio.setEnabled(false);
						}
					}
				});
						
				Origen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) 
					{	
						if (!first) 
						{
							Metodos.colocacionComboBox(rdbtnIda, misParadas, Origen, Destino,Origen.getSelectedIndex());
						}
						else
						{
							first=false;
						}
					}
				});
				
				
				
				
				btnBuscarPrecio.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e)
					{
						String dia_ida = Integer.toString(fecha_origen.getCalendar().get(Calendar.DAY_OF_MONTH)); 
						String mes_ida = Integer.toString(fecha_origen.getCalendar().get(Calendar.MONTH) + 1);
						String ano_ida = Integer.toString(fecha_origen.getCalendar().get(Calendar.YEAR)); 
						fecha_ida = (ano_ida + "-" + mes_ida + "-" + dia_ida); 
						parada_origen = Origen.getSelectedItem().toString();
						parada_destino = Destino.getSelectedItem().toString();
						
						
						for(int cont=0; cont<misParadas.size(); cont++)
						{
							if(misParadas.get(cont).getNombre().equals(parada_origen))
							{
								cod_parada_origen = misParadas.get(cont).getCod_parada();
							}
							
							if(misParadas.get(cont).getNombre().equals(parada_destino))
							{
								cod_parada_destino = misParadas.get(cont).getCod_parada();
							}
						}
						index_inicio = Origen.getSelectedIndex();
						index_final = Destino.getSelectedIndex();
						
						
					
						JButton boton[] = new JButton[misParadas.size()];
	
						
						if (parada_origen == parada_destino)
						{
							JOptionPane.showConfirmDialog(contentPane, "El origen no puede ser igual al destino."
									,"Aviso", JOptionPane.PLAIN_MESSAGE);
						}
							
						if (rdbtnIdaYVuelta.isSelected())
						{
							String dia_vuelta = Integer.toString(fecha_vuelta.getCalendar().get(Calendar.DAY_OF_MONTH)); 
							String mes_vuelta = Integer.toString(fecha_vuelta.getCalendar().get(Calendar.MONTH) + 1);
							String ano_vuelta = Integer.toString(fecha_vuelta.getCalendar().get(Calendar.YEAR)); 
							fecha_vuelta2 = (ano_vuelta + "-" + mes_vuelta + "-" + dia_vuelta); 
							
							if (dia_ida != "" && dia_vuelta != "" && parada_origen != parada_destino)
							{
									Origen_Destino.setVisible(false);
									Disponibilidad.setVisible(true);

									ActionListener botones_buses = new ActionListener()
									{
										public void actionPerformed(ActionEvent e)
										{
											for(int cont = 0; cont < misBuses.size(); cont++)
											{
												boton[cont].setEnabled(false);
												
												if (e.getActionCommand().equals(Integer.toString(misBuses.get(cont).getCod_bus())));
												{
													bus = misBuses.get(cont).getCod_bus();	
													posicion = cont;		
													try
													{
														gestor.conectar();
														paradas_libres = misBuses.get(cont).getN_plazas()-gestor.numero_plazas_elegidas(misBuses.get(cont).getCod_bus(), fecha_ida);
														precio = 2 * Metodos.CalcularPrecio(index_inicio,index_final,
																misBuses.get(cont).getConsumo_km(), misBuses.get(cont).getN_plazas(),gestor.numero_plazas_elegidas(bus, fecha_ida),
																misParadas);
														BigDecimal decimal = new BigDecimal(precio);
														precio = decimal.setScale(2, RoundingMode.HALF_UP).doubleValue();
														gestor.close();
													}
													catch (Exception perfe)
													{
														
													}
												}
											}
											
											
										}
									};
										
									for(int cont=0; cont<misBuses.size(); cont++)
									{
										try
										{
											gestor.conectar();
											misBilletes = gestor.seleccionarBillete();
											precio = Metodos.CalcularPrecio(index_inicio,index_final,misBuses.get(cont).getConsumo_km(), misBuses.get(cont).getN_plazas(),
													gestor.numero_plazas_elegidas(bus, fecha_ida), misParadas);
											BigDecimal decimal = new BigDecimal(precio);
											precio = decimal.setScale(2, RoundingMode.HALF_UP).doubleValue();
											boton[cont] = new JButton(Integer.toString(misBuses.get(cont).getCod_bus())+"-----"+precio
													+"-----"+Integer.toString(misBuses.get(cont).getN_plazas()-gestor.numero_plazas_elegidas(misBuses.get(cont).getCod_bus(), fecha_ida))+" paradas libres");
											panel_scrollPane.add(boton[cont]);	
											boton[cont].addActionListener(botones_buses);
											boton[cont].setActionCommand(Integer.toString(misBuses.get(cont).getCod_bus()));
											gestor.close();
										}
										catch(Exception ea)
										{
											
										}
									}
							}
						}
						
						
						if (rdbtnIda_1.isSelected() && dia_ida != "" && parada_origen != parada_destino)
						{
							Origen_Destino.setVisible(false);
							Disponibilidad.setVisible(true);
							

							ActionListener botones_buses = new ActionListener()
							{
								public void actionPerformed(ActionEvent e)
								{
									for(int cont = 0; cont < misBuses.size(); cont++)
									{
										boton[cont].setEnabled(false);
										
										if (e.getActionCommand().equals(Integer.toString(misBuses.get(cont).getCod_bus())))
										{
											bus = misBuses.get(cont).getCod_bus();
											posicion = cont;		
											try
											{
												gestor.conectar();
												paradas_libres = misBuses.get(cont).getN_plazas()-gestor.numero_plazas_elegidas(misBuses.get(cont).getCod_bus(), fecha_ida);
												precio = Metodos.CalcularPrecio(index_inicio,index_final,misBuses.get(cont).getConsumo_km(), misBuses.get(cont).getN_plazas(),
														gestor.numero_plazas_elegidas(bus, fecha_ida), misParadas);
												BigDecimal decimal = new BigDecimal(precio);
												precio = decimal.setScale(2, RoundingMode.HALF_UP).doubleValue();
												gestor.close();
											}
											catch (Exception perfe)
											{
												
											}
										}
									}
									
									
								}
							};
								
							for(int cont=0; cont<misBuses.size(); cont++)
							{
								try
								{
									gestor.conectar();
									misBilletes = gestor.seleccionarBillete();
									precio = Metodos.CalcularPrecio(index_inicio,index_final,misBuses.get(cont).getConsumo_km(), misBuses.get(cont).getN_plazas(),
											gestor.numero_plazas_elegidas(bus, fecha_ida), misParadas);
									BigDecimal decimal = new BigDecimal(precio);
									precio = decimal.setScale(2, RoundingMode.HALF_UP).doubleValue();
									boton[cont] = new JButton(Integer.toString(misBuses.get(cont).getCod_bus())+"\t\t\t\t"+precio
											+"\u20ac\t\t\t\t"+Integer.toString(misBuses.get(cont).getN_plazas()-gestor.numero_plazas_elegidas(misBuses.get(cont).getCod_bus(), fecha_ida))+" paradas libres");
									panel_scrollPane.add(boton[cont]);	
									boton[cont].addActionListener(botones_buses);
									boton[cont].setActionCommand(Integer.toString(misBuses.get(cont).getCod_bus()));
									gestor.close();
								}
								catch(Exception er)
								{
									
								}
							}
						}
						
						
					}
				});
				
				
				btnContinuar_Disponibilidad.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{ 
						Disponibilidad.setVisible(false);
						iniciosesion.setVisible(true);
					}
				});
				
				rdbtnIda.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) 
					{
						if (rdbtnVuelta.isSelected())
						{
							rdbtnVuelta.setSelected(false);
							rdbtnIda.setSelected(true);
						}	
					}
				});
				
				rdbtnVuelta.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) 
					{
						if (rdbtnIda.isSelected())
						{
							rdbtnIda.setSelected(false);
							rdbtnVuelta.setSelected(true);
						}
					}
				});
				
				registrarse_iniciosesion.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent arg0) 
					{
						iniciosesion.setVisible(false);
						registro.setVisible(true);
					}
				});
				
				cancelar_registro.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent arg0) 
					{
						registro.setVisible(false);
						iniciosesion.setVisible(true);
					}
				});
				
				
				enviar_registro.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							
						//	gestor.insertarCliente(dni_registro.getText(),nombre_registro.getText(),apellido_registro.getText(),fechanacimiento_registro.getText(),sexo_registro.getText(),contrasena_registro.getText());
						//	JOptionPane.showMessageDialog(null, "Datos correctamente ingresados");
							cliente.setDni(dni1_registro.getText());
							cliente.setNombre(nombre_registro.getText());
							cliente.setApellido(apellido_registro.getText());
							cliente.setFechadenacimiento(fechanacimiento1_registro.getText());
							//cliente.setSexo(sexo_registro.getText());
							cliente.setContrasena(contrasena_registro.getText());
							
							gestor.insertarCliente(cliente.getDni(),cliente.getNombre(),cliente.getApellido(),cliente.getFechadenacimiento(),
									cliente.getSexo(),cliente.getContrasena());
				//			cliente.setDni("");
				//			cliente.setNombre("");
				//			cliente.setApellido("");
				//			cliente.setFechadenacimiento("");
				//			cliente.setSexo("");
				//			cliente.setContrasena("");
							
							registro.setVisible(false);
							iniciosesion.setVisible(true);
							
						} catch (Exception e1) {
					    	e1.printStackTrace();
						}
					}
				});
				
				continuar_iniciosesion.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						int dato;
						try {
							cliente.setDni(dni_iniciosesion.getText());
							cliente.setContrasena(contrasena_iniciosesion.getText());
							dato=gestor.Seleccionar(cliente.getDni(),cliente.getContrasena());
								if(dato==1) {
									JOptionPane.showMessageDialog(null,"Bienvenido");
									DNI = dni_iniciosesion.getText();
									DineroFaltante.setText(precio+" \u20ac");
									total_faltante = precio;
									iniciosesion.setVisible(false);
									Pago.setVisible(true);
								}
								else {
									JOptionPane.showMessageDialog(null,"DNI o Contrasena incorrectos");
									cliente.setDni("");
									cliente.setContrasena("");
									
								}
							
							}catch(Exception e1) {
								e1.printStackTrace();
								
							}
					}
				});

				Continuar_Pago.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e) 
					{
						Pago.setVisible(false);
						Devolucion.setVisible(true);
						ImporteADevolver.setText(total_devolucion+"\u20ac");
						
						while (total_devolucion != 0) //Mientras el dinero a devolver no sea 0 calculara los billetes y monedas a devolver
						{
							if (total_devolucion>=200) //Si el dinero a devolver es mayor o igual a 200 le devolvera un billete de 200, y asi con todas las monedas y billetes
							{
								total_devolucion = total_devolucion - 200;
								contador_monedas[0] = contador_monedas[0] + 1;
							}

							else if (total_devolucion < 200 && total_devolucion >= 100)
							{
								total_devolucion = total_devolucion - 100;
								contador_monedas[1] = contador_monedas[1] + 1;
							}
							else if (total_devolucion < 100 && total_devolucion >= 50)
							{
								total_devolucion = total_devolucion - 50;
								contador_monedas[2] = contador_monedas[2] + 1;
							}
							else if (total_devolucion < 50 && total_devolucion >= 20)
							{
								total_devolucion = total_devolucion - 20;
								contador_monedas[3] = contador_monedas[3] + 1;
							}
							else if (total_devolucion < 20 && total_devolucion >= 10)
							{
								total_devolucion = total_devolucion - 10;
								contador_monedas[4] = contador_monedas[4] + 1;
							}
							else if (total_devolucion < 10 && total_devolucion >= 5)
							{
								total_devolucion = total_devolucion - 5;
								contador_monedas[5] = contador_monedas[5] + 1;
							}
							else if (total_devolucion < 5 && total_devolucion >= 2)
							{
								total_devolucion = total_devolucion - 2;
								contador_monedas[6] = contador_monedas[6] + 1;
							}
							else if (total_devolucion < 2 && total_devolucion >= 0.999)
							{
								total_devolucion = total_devolucion - 1;
								contador_monedas[7] = contador_monedas[7] + 1;
							}
							else if (total_devolucion < 1 && total_devolucion >= 0.499)
							{
								total_devolucion = total_devolucion - 0.5;
								contador_monedas[8] = contador_monedas[8] + 1;
							}
							else if (total_devolucion < 0.5 && total_devolucion >= 0.199)
							{
								total_devolucion = total_devolucion - 0.2;
								contador_monedas[9] = contador_monedas[9] + 1;
							}
							else if (total_devolucion < 0.2 && total_devolucion >= 0.099)
							{
								total_devolucion = total_devolucion - 0.1;
								contador_monedas[10] = contador_monedas[10] + 1;
							}
							else if (total_devolucion < 0.1 && total_devolucion >= 0.049)
							{
								total_devolucion = total_devolucion - 0.05;
								contador_monedas[11] = contador_monedas[11] + 1;
							}
							else if (total_devolucion < 0.05 && total_devolucion >= 0.019)
							{
								total_devolucion = total_devolucion - 0.02;
								contador_monedas[12] = contador_monedas[12] + 1;
							}
							else if (total_devolucion < 0.02 && total_devolucion >= 0.01)
							{
								total_devolucion = total_devolucion - 0.01;
								contador_monedas[13] = contador_monedas[13] + 1;
								
							}
							else //Si no se cumple ninguna condicion y devolucion es mayor o igual que 0.009 convertira el dinero a devolver a 1 centimo
							{
								if (total_devolucion >= 0.009)
								{
									total_devolucion = 0.01;
									total_devolucion = total_devolucion-0.01;
									contador_monedas[13] = contador_monedas[13] + 1;
								}
								else //Si es menor que 0.009 saldra del bucle ignorando el resto de decimales
								{
									break;
								}
								
							}	
							
							
						}
					
						try
						{
							gestor.conectar();
							
							
							if (rdbtnIdaYVuelta.isSelected())
							{
								gestor.insertarBillete("0", cod_linea, String.valueOf(bus), String.valueOf(cod_parada_origen), String.valueOf(cod_parada_destino), fecha_ida, "-", DNI, String.valueOf(precio/2));
								gestor.insertarBillete("0", cod_linea, String.valueOf(bus), String.valueOf(cod_parada_destino), String.valueOf(cod_parada_origen), fecha_vuelta2, "-", DNI, String.valueOf(precio/2));
							}
							else
							{
								gestor.insertarBillete("0", cod_linea, String.valueOf(bus), String.valueOf(cod_parada_origen), String.valueOf(cod_parada_destino), fecha_ida, "-", DNI, String.valueOf(precio));
							}
							
							gestor.close();
						}
						catch (Exception el)
						{
							
						}
						
						
						Devolucion_dinero.append("\n");
						for (int posicion=0; posicion<contador_monedas.length;posicion++)
						{
							if (contador_monedas[posicion] > 0)
							{
								if(contador_monedas[posicion] == 1)
								{
									
									if (posicion < 6)
									{	
										Devolucion_dinero.append("   -  "+contador_monedas[posicion]+" x Billete de "+monedas[posicion]+"\n\n");
									}
									else
									{
										Devolucion_dinero.append("   -  "+contador_monedas[posicion]+" x Moneda de "+monedas[posicion]+"\n\n");
									}
									
								}
								else 
								{
									if (posicion < 6)
									{
										Devolucion_dinero.append("   -  "+contador_monedas[posicion]+" x Billetes de "+monedas[posicion]+"\n\n");
									}
									else
									{
										Devolucion_dinero.append("   -  "+contador_monedas[posicion]+" x Monedas de "+monedas[posicion]+"\n\n");
									}
								}
								
							}
						}
					}
				});
				
				rdbtnM.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) 
					{
						if (rdbtnM.isSelected())
						{
							rdbtnV.setSelected(false);
							rdbtnM.setSelected(true);
							cliente.setSexo(rdbtnM.getText());
						}	
					}
				});
				
				rdbtnV.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) 
					{
						if (rdbtnV.isSelected())
						{
							rdbtnM.setSelected(false);
							rdbtnV.setSelected(true);
							cliente.setSexo(rdbtnV.getText());
						}	
					}
				});
				
				Continuar_Devolucion.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Devolucion.setVisible(false);
						Desea_Ticket.setVisible(true);
					}});
				
				Si_Desea_Ticket.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) 
					{
						Desea_Ticket.setVisible(false);
						Ticket.setVisible(true);	
						
						try 
						{
							 txtpnTicket.getStyledDocument().insertString(txtpnTicket.getStyledDocument().getLength(),
									 gestor.EscribiroCrearFichearo(fecha_ida,DNI,cod_linea,cod_parada_origen,cod_parada_destino), null);
							 //cod_linea, String.valueOf(bus), String.valueOf(cod_parada_origen), String.valueOf(cod_parada_destino), fecha_ida, "-", DNI, String.valueOf(precio/2));
						}
						catch(Exception e) 
						{
							e.printStackTrace();
						}
					}
				});
				
				No_Desea_Ticket.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						System.exit(0);
					}
				});
			
	}
}

